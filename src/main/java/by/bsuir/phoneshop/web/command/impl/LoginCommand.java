package by.bsuir.phoneshop.web.command.impl;

import by.bsuir.phoneshop.web.JspPageName;
import by.bsuir.phoneshop.web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return JspPageName.LOGIN_JSP;
    }
}
