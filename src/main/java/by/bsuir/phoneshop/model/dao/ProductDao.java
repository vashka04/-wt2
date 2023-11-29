package by.bsuir.phoneshop.model.dao;

import by.bsuir.phoneshop.model.Product;
import by.bsuir.phoneshop.model.dao.sort.SortOrder;
import by.bsuir.phoneshop.model.dao.sort.SortType;

import java.util.List;

public interface ProductDao {
    Product getProduct(Long id);

    List<Product> findProducts(String search, SortType type, SortOrder order);

    void save(Product product);

    void delete(Long id);
}
