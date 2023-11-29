package by.bsuir.phoneshop.model.service;

import by.bsuir.phoneshop.model.cart.Cart;

public interface StockService {
    int getAvailableQuantity(Cart cart, Long id);
}
