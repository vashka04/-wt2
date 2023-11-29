package by.bsuir.phoneshop.web.command.impl;

import by.bsuir.phoneshop.model.dao.ProductDao;
import by.bsuir.phoneshop.model.dao.impl.JdbcProductDao;
import by.bsuir.phoneshop.model.dao.sort.SortOrder;
import by.bsuir.phoneshop.model.dao.sort.SortType;
import by.bsuir.phoneshop.web.JspPageName;
import by.bsuir.phoneshop.web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class ProductListCommand implements Command {
    private ProductDao productDao = JdbcProductDao.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        String search = Optional.ofNullable(request.getParameter("search")).orElse("");
        String typeParam = request.getParameter("sort");
        String orderParam = request.getParameter("order");
        SortType type = typeParam == null ? null : SortType.valueOf(typeParam);
        SortOrder order = orderParam == null ? null : SortOrder.valueOf(orderParam);
        request.setAttribute("phones", productDao.findProducts(search, type, order));
        return JspPageName.PRODUCT_LIST_JSP;
    }
}
