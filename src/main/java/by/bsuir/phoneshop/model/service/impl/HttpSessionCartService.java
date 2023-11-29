package by.bsuir.phoneshop.model.service.impl;

import by.bsuir.phoneshop.model.Product;
import by.bsuir.phoneshop.model.cart.Cart;
import by.bsuir.phoneshop.model.cart.CartItem;
import by.bsuir.phoneshop.model.dao.ProductDao;
import by.bsuir.phoneshop.model.dao.impl.JdbcProductDao;
import by.bsuir.phoneshop.model.exception.OutOfStockException;
import by.bsuir.phoneshop.model.service.CartService;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class HttpSessionCartService implements CartService {
    private static final String CART_SESSION_ATTRIBUTE = HttpSessionCartService.class.getName() + ".cart";
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private ProductDao productDao = JdbcProductDao.getInstance();

    private static class SingletonHolder {
        private static final HttpSessionCartService INSTANCE = new HttpSessionCartService();
    }
    private HttpSessionCartService() {
    }
    public static HttpSessionCartService getInstance() {
        return SingletonHolder.INSTANCE;
    }
    @Override
    public Cart getCart(HttpServletRequest request) {
        lock.writeLock().lock();
        try {
            Cart cart = (Cart) request.getSession().getAttribute(CART_SESSION_ATTRIBUTE);
            if (cart == null) {
                request.getSession().setAttribute(CART_SESSION_ATTRIBUTE, cart = new Cart());
            }
            return cart;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void add(Cart cart, Long id, int quantity) {
        lock.writeLock().lock();
        try {
            Product product = productDao.getProduct(id);
            CartItem cartItem = cart.getItems().stream()
                    .filter(item -> item.getProduct().equals(product))
                    .findFirst()
                    .orElse(null);
            if (cartItem != null) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            } else {
                cart.getItems().add(new CartItem(product, quantity));
            }
            recalculate(cart);
        } finally {
            lock.writeLock().unlock();
        }
    }
    @Override
    public void update(Cart cart, Long id, int quantity) throws OutOfStockException {
        lock.writeLock().lock();
        try {
            Integer stock = productDao.getProduct(id).getStock();
            if(quantity > stock) {
                throw new OutOfStockException(stock);
            }
            cart.getItems().stream()
                    .filter(item -> item.getProduct().getId().equals(id))
                    .findFirst()
                    .get().setQuantity(quantity);
            recalculate(cart);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(Cart cart, Long id) {
        lock.writeLock().lock();
        try {
            cart.getItems().removeIf(item -> item.getProduct().getId().equals(id));
            recalculate(cart);
        } finally {
            lock.writeLock().unlock();
        }
    }
    private void recalculate(Cart cart) {
        cart.setTotalQuantity(cart.getItems().stream()
                .map(CartItem::getQuantity)
                .collect(Collectors.summingInt(q -> q.intValue())));
        cart.setTotalCost(cart.getItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    @Override
    public void clearCart(HttpServletRequest request) {
        lock.writeLock().lock();
        try {
            request.getSession().setAttribute(CART_SESSION_ATTRIBUTE, null);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
