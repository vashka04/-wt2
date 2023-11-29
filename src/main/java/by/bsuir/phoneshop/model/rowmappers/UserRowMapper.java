package by.bsuir.phoneshop.model.rowmappers;

import by.bsuir.phoneshop.model.user.User;
import by.bsuir.phoneshop.model.user.UserRole;
import by.bsuir.phoneshop.model.user.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRowMapper {
    public Optional<User> mapRows(ResultSet rs) throws SQLException {
        if (rs.next()) {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setRole(UserRole.valueOf(rs.getString("role")));
            user.setDiscount(rs.getDouble("discount"));
            user.setStatus(UserStatus.valueOf(rs.getString("status")));
            user.setLogin(rs.getString("login"));
            user.setHashPassword(rs.getString("password"));
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
