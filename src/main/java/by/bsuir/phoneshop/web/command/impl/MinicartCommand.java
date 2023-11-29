package by.bsuir.phoneshop.web.command.impl;

import by.bsuir.phoneshop.model.cart.Cart;
import by.bsuir.phoneshop.model.service.CartService;
import by.bsuir.phoneshop.model.service.impl.HttpSessionCartService;
import by.bsuir.phoneshop.web.JspPageName;
import by.bsuir.phoneshop.web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class MinicartCommand implements Command {
    private CartService cartService = HttpSessionCartService.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        Cart cart = cartService.getCart(request);
        request.setAttribute("cart", cart);
        return JspPageName.MINI_CART_JSP;
    }
}
