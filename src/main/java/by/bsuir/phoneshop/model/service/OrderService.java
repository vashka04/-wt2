package by.bsuir.phoneshop.model.service;

import by.bsuir.phoneshop.model.cart.Cart;
import by.bsuir.phoneshop.model.order.Order;

import java.sql.SQLException;

public interface OrderService {
    Order getOrder(Cart cart);
    void placeOrder(Order order) throws SQLException;
}
