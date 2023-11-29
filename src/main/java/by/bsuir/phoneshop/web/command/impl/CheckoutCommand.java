package by.bsuir.phoneshop.web.command.impl;

import by.bsuir.phoneshop.model.order.Order;
import by.bsuir.phoneshop.model.service.CartService;
import by.bsuir.phoneshop.model.service.OrderService;
import by.bsuir.phoneshop.model.service.impl.JdbcOrderService;
import by.bsuir.phoneshop.model.service.impl.HttpSessionCartService;
import by.bsuir.phoneshop.web.JspPageName;
import by.bsuir.phoneshop.web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class CheckoutCommand implements Command {
    private CartService cartService = HttpSessionCartService.getInstance();
    private OrderService orderService = JdbcOrderService.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        Order order = orderService.getOrder(cartService.getCart(request));
        request.setAttribute("order", order);
        return JspPageName.CHECKOUT_JSP;
    }
}
