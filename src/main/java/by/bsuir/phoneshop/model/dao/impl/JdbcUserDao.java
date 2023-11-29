package by.bsuir.phoneshop.model.dao.impl;

import by.bsuir.phoneshop.model.dao.UserDao;
import by.bsuir.phoneshop.model.pool.ConnectionPool;
import by.bsuir.phoneshop.model.rowmappers.UserListRowMapper;
import by.bsuir.phoneshop.model.rowmappers.UserRowMapper;
import by.bsuir.phoneshop.model.user.User;
import by.bsuir.phoneshop.model.user.UserRole;
import by.bsuir.phoneshop.model.user.UserStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcUserDao implements UserDao {
    private static class SingletonHandler {
        private static final JdbcUserDao INSTANCE = new JdbcUserDao();
    }
    public static JdbcUserDao getInstance() {
        return SingletonHandler.INSTANCE;
    }
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private JdbcUserDao() {

    }
    @Override
    public Optional<User> getUser(String login, String password) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String hashPassword = Integer.toString(password.hashCode());
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where login=?" +
                " and password=?");
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, hashPassword);
        ResultSet rs = preparedStatement.executeQuery();
        Optional<User> user = new UserRowMapper().mapRows(rs);
        connectionPool.releaseConnection(connection);
        return user;
    }

    @Override
    public List<User> getUsers(Long id) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where not(id=?)");
        preparedStatement.setLong(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        List<User> users = new UserListRowMapper().mapRows(rs);
        connectionPool.releaseConnection(connection);
        return users;
    }

    @Override
    public void updateUser(Long id, Double discount, UserRole role, UserStatus status) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update users set role=?," +
                "status=?,discount=? where id=?");
        preparedStatement.setString(1, role.toString());
        preparedStatement.setString(2, status.toString());
        preparedStatement.setDouble(3, discount);
        preparedStatement.setLong(4, id);
        preparedStatement.execute();
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void saveUser(String login, String password) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into users (login," +
                " password, discount, role, status) values (?,?,?,?,?)");
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, Integer.toString(password.hashCode()));
        preparedStatement.setDouble(3, 0);
        preparedStatement.setString(4, "USER");
        preparedStatement.setString(5, "UNBANNED");
        preparedStatement.execute();
        connectionPool.releaseConnection(connection);
    }
}
