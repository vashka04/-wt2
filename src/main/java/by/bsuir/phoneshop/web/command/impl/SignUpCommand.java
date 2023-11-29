package by.bsuir.phoneshop.web.command.impl;

import by.bsuir.phoneshop.model.dao.UserDao;
import by.bsuir.phoneshop.model.dao.impl.JdbcUserDao;
import by.bsuir.phoneshop.model.user.User;
import by.bsuir.phoneshop.web.JspPageName;
import by.bsuir.phoneshop.web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.Optional;

public class SignUpCommand implements Command {
    private UserDao userDao = JdbcUserDao.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        String login = Optional.ofNullable(request.getParameter("login")).orElse("");
        String password = Optional.ofNullable(request.getParameter("password")).orElse("");
        String repeated = Optional.ofNullable(request.getParameter("repeated")).orElse("");
        if (!password.equals(repeated)) {
            request.setAttribute("error", "Different passwords");
        } else {
            try {
                userDao.saveUser(login, password);
                Optional<User> user = userDao.getUser(login, password);
                request.getSession().setAttribute("user", user.get());
            } catch (SQLException e) {
                request.setAttribute("error", "Login is already used");
            }
        }
        return JspPageName.REGISTER_JSP;
    }
}
