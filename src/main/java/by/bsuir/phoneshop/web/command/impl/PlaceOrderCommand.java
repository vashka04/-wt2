package by.bsuir.phoneshop.web.command.impl;

import by.bsuir.phoneshop.model.order.Order;
import by.bsuir.phoneshop.model.service.CartService;
import by.bsuir.phoneshop.model.service.OrderService;
import by.bsuir.phoneshop.model.service.impl.HttpSessionCartService;
import by.bsuir.phoneshop.model.service.impl.JdbcOrderService;
import by.bsuir.phoneshop.web.command.Command;
import by.bsuir.phoneshop.web.command.CommandHandler;
import by.bsuir.phoneshop.web.command.CommandName;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PlaceOrderCommand implements Command {
    private OrderService orderService = JdbcOrderService.getInstance();
    private CartService cartService = HttpSessionCartService.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        String firstName = Optional.ofNullable(request.getParameter("firstName")).orElse("");
        String lastName = Optional.ofNullable(request.getParameter("lastName")).orElse("");
        String address = Optional.ofNullable(request.getParameter("deliveryAddress")).orElse("");
        String phone = Optional.ofNullable(request.getParameter("phone")).orElse("");
        Map<String, String> errors = validate(firstName, lastName, address, phone);
        Order order = orderService.getOrder(cartService.getCart(request));
        order.setPhone(phone);
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setDeliveryAddress(address);
        if (errors.isEmpty()) {
            try {
                orderService.placeOrder(order);
                cartService.clearCart(request);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("secureId", order.getSecureId());
        } else {
            request.setAttribute("errors", errors);
        }
        return CommandHandler.getInstance().getCommandByName(CommandName.CHECKOUT).execute(request);
    }

    private Map<String, String> validate(String firstName, String lastName, String address, String phone) {
        Map<String, String> errors = new HashMap<>();
        if (firstName.isBlank()) {
            errors.put("firstName", "Name is required");
        }
        if (lastName.isBlank()) {
            errors.put("lastName", "Surname is required");
        }
        if (address.isBlank()) {
            errors.put("deliveryAddress", "Address is required");
        }
        if (phone.isBlank()) {
            errors.put("phone", "Phone is required");
        }
        if (!phone.matches("\\+375(33|29|25|44)\\d{7}")) {
            errors.put("phone", "Phone is invalid");
        }
        return errors;
    }
}
