package by.bsuir.phoneshop.web.command.impl;

import by.bsuir.phoneshop.model.dao.UserDao;
import by.bsuir.phoneshop.model.dao.impl.JdbcUserDao;
import by.bsuir.phoneshop.model.user.UserRole;
import by.bsuir.phoneshop.model.user.UserStatus;
import by.bsuir.phoneshop.web.JspPageName;
import by.bsuir.phoneshop.web.command.Command;
import by.bsuir.phoneshop.web.command.CommandHandler;
import by.bsuir.phoneshop.web.command.CommandName;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.Optional;

public class UpdateUsersCommand implements Command {
    private UserDao userDao = JdbcUserDao.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        String[] discounts = request.getParameterValues("discount");
        String[] roles = request.getParameterValues("role");
        String[] statuses = request.getParameterValues("status");
        String[] ids = request.getParameterValues("id");
        for (int i = 0; i < discounts.length; i++) {
            UserRole role = UserRole.valueOf(roles[i]);
            UserStatus status = UserStatus.valueOf(statuses[i]);
            Long id = Long.parseLong(ids[i]);
            Double discount = Double.parseDouble(Optional.ofNullable(discounts[i]).orElse("0"));
            try {
                userDao.updateUser(id, discount, role, status);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return CommandHandler.getInstance().getCommandByName(CommandName.USERS).execute(request);
    }
}