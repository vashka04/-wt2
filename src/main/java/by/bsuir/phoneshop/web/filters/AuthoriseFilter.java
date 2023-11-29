package by.bsuir.phoneshop.web.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class AuthoriseFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Object userObj = httpServletRequest.getSession().getAttribute("user");
        String command = Optional.ofNullable(httpServletRequest.getParameter("command")).orElse("");
        if (userObj == null && !command.equals("LOGIN") && !command.equals("AUTHORISE") && !command.equals("REGISTER")
        && !command.equals("SIGNUP")) {
            httpServletResponse.sendRedirect("/controller?command=LOGIN");
        } else {
            chain.doFilter(request, response);
        }
    }
}
