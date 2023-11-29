package by.bsuir.phoneshop.web.command.impl;

import by.bsuir.phoneshop.model.dao.UserDao;
import by.bsuir.phoneshop.model.dao.impl.JdbcUserDao;
import by.bsuir.phoneshop.model.user.User;
import by.bsuir.phoneshop.web.JspPageName;
import by.bsuir.phoneshop.web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;

public class UsersCommand implements Command {
    private UserDao userDao = JdbcUserDao.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        List<User> users;
        try {
            users = userDao.getUsers(((User)request.getSession().getAttribute("user")).getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("users", users);
        return JspPageName.USERS_JSP;
    }
}
