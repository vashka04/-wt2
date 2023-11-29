package by.bsuir.phoneshop.web.filters;

import by.bsuir.phoneshop.model.user.User;
import by.bsuir.phoneshop.model.user.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String command = Optional.ofNullable(httpServletRequest.getParameter("command")).orElse("");
        User user = Optional.ofNullable((User)httpServletRequest.getSession().getAttribute("user")).orElse(new User());
        if(user.getRole() != UserRole.ADMIN && command.equals("USERS")) {
            ((HttpServletResponse) response).sendRedirect("/controller?command=PRODUCT_LIST");
        } else {
            chain.doFilter(request, response);
        }
    }
}
