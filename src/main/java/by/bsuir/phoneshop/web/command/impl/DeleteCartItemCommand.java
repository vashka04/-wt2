package by.bsuir.phoneshop.web.command.impl;

import by.bsuir.phoneshop.model.cart.Cart;
import by.bsuir.phoneshop.model.service.CartService;
import by.bsuir.phoneshop.model.service.impl.HttpSessionCartService;
import by.bsuir.phoneshop.web.JspPageName;
import by.bsuir.phoneshop.web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteCartItemCommand implements Command {
    private CartService cartService = HttpSessionCartService.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        Long phoneId = Long.parseLong(request.getParameter("phoneId"));
        Cart cart = cartService.getCart(request);
        cartService.delete(cart, phoneId);
        request.setAttribute("deleted", true);
        return JspPageName.CART_JSP;
    }
}
