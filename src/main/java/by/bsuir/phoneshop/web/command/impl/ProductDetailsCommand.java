package by.bsuir.phoneshop.web.command.impl;

import by.bsuir.phoneshop.model.Product;
import by.bsuir.phoneshop.model.dao.ProductDao;
import by.bsuir.phoneshop.model.dao.impl.JdbcProductDao;
import by.bsuir.phoneshop.web.JspPageName;
import by.bsuir.phoneshop.web.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class ProductDetailsCommand implements Command {
    private ProductDao productDao = JdbcProductDao.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("id"));
        Product product = productDao.getProduct(id);
        request.setAttribute("phone", product);
        return JspPageName.PRODUCT_DETAILS_JSP;
    }
}
