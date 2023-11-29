package by.bsuir.phoneshop.model.dao;

import by.bsuir.phoneshop.model.user.User;
import by.bsuir.phoneshop.model.user.UserRole;
import by.bsuir.phoneshop.model.user.UserStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> getUser(String login, String password) throws SQLException;
    void saveUser(String login, String password) throws SQLException;
    List<User> getUsers(Long id) throws SQLException;
    void updateUser(Long id, Double discount, UserRole role, UserStatus status) throws SQLException;
}
