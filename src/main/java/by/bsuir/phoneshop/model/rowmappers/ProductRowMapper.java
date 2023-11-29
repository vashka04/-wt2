package by.bsuir.phoneshop.model.rowmappers;

import by.bsuir.phoneshop.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper {
    public Product mapRows(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setDescription(rs.getString("description"));
        product.setStock(rs.getInt("stock"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setModel(rs.getString("model"));
        product.setImageUrl(rs.getString("image_url"));
        product.setId(rs.getLong("id"));
        return product;
    }
}
