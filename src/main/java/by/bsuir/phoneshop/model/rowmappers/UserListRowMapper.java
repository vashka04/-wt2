package by.bsuir.phoneshop.model.rowmappers;

import by.bsuir.phoneshop.model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserListRowMapper {
    public List<User> mapRows(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        UserRowMapper rowMapper = new UserRowMapper();
        do {
            User user = rowMapper.mapRows(rs).get();
            users.add(user);
        } while (rs.next());
        return users;
    }
}
