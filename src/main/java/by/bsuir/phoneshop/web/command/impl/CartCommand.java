package by.bsuir.phoneshop.web.command.impl;

import by.bsuir.phoneshop.model.service.CartService;
import by.bsuir.phoneshop.model.service.impl.HttpSessionCartService;
import by.bsuir.phoneshop.web.JspPageName;
import by.bsuir.phoneshop.web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class CartCommand implements Command {
    private CartService cartService = HttpSessionCartService.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("cart", cartService.getCart(request));
        return JspPageName.CART_JSP;
    }
}
