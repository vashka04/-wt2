package by.bsuir.phoneshop.model.dao.impl;

import by.bsuir.phoneshop.model.Product;
import by.bsuir.phoneshop.model.dao.ProductDao;
import by.bsuir.phoneshop.model.dao.sort.SortOrder;
import by.bsuir.phoneshop.model.dao.sort.SortType;
import by.bsuir.phoneshop.model.exception.ProductNotFoundException;
import by.bsuir.phoneshop.model.pool.ConnectionPool;
import by.bsuir.phoneshop.model.rowmappers.ProductListRowMapper;
import by.bsuir.phoneshop.model.rowmappers.ProductRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class JdbcProductDao implements ProductDao {
    private static class SingletonHandler {
        private static final JdbcProductDao INSTANCE = new JdbcProductDao();
    }

    public static JdbcProductDao getInstance() {
        return SingletonHandler.INSTANCE;
    }

    private JdbcProductDao() {

    }

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public Product getProduct(Long id) {
        Product product;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from phones where id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                product = new ProductRowMapper().mapRows(resultSet);
            } else {
                throw new SQLException();
            }
            connectionPool.releaseConnection(connection);
        } catch (SQLException e) {
            throw new ProductNotFoundException(String.format("Product with id %d not found", id));
        }
        return product;
    }

    @Override
    public List<Product> findProducts(String search, SortType type, SortOrder order) {
        Connection connection = connectionPool.getConnection();
        String[] words = search.isBlank() ? new String[0] : Arrays.stream(search.split("\\s"))
                .map(word -> "%".concat(word).concat("%"))
                .toArray(String[]::new);
        String query = getFindProductsQuery(words, type, order);
        List<Product> products;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < words.length; i++) {
                preparedStatement.setString(i + 1, words[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            products = new ProductListRowMapper().mapRows(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        connectionPool.releaseConnection(connection);
        return products;
    }

    private String getFindProductsQuery(String[] words, SortType type, SortOrder order) {
        StringBuilder query = new StringBuilder("select * from phones where stock > 0");
        if (words.length > 0) {
            query.append(" and (lower(model) like ?");
            for (int i = 1; i < words.length; i++) {
                query.append(" or lower(model) like ?");
            }
            query.append(")");
        }
        if (type != null) {
            query.append(" order by ").append(type).append(" ").append(order);
        }
        return query.toString();
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(Long id) {

    }
}
