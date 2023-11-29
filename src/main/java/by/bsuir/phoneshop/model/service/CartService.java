package by.bsuir.phoneshop.model.service;

import by.bsuir.phoneshop.model.exception.OutOfStockException;
import by.bsuir.phoneshop.model.cart.Cart;
import jakarta.servlet.http.HttpServletRequest;

public interface CartService {
    Cart getCart(HttpServletRequest request);
    void add(Cart cart, Long id, int quantity);
    void update(Cart cart, Long id, int quantity) throws OutOfStockException;
    void delete(Cart cart, Long id);
    void clearCart(HttpServletRequest request);
}
