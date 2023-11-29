package by.bsuir.phoneshop.model.rowmappers;

import by.bsuir.phoneshop.model.order.Order;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper {
    public Order mapRows(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setSecureId(rs.getString("secureId"));
        order.setTotalCost(BigDecimal.valueOf(rs.getLong("totalCost")));
        order.setFirstName(rs.getString("firstName"));
        order.setLastName(rs.getString("lastName"));
        order.setPhone(rs.getString("phone"));
        order.setDeliveryAddress(rs.getString("deliveryAddress"));
        return order;
    }
}
