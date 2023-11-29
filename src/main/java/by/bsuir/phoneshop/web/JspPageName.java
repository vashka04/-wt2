package by.bsuir.phoneshop.web;

public class JspPageName {
    private JspPageName() {
    }
    private static final String PREFIX = "/WEB-INF/pages/";
    public static final String LOGIN_JSP = String.format("%slogin.jsp", PREFIX);
    public static final String PRODUCT_LIST_JSP = String.format("%sproductList.jsp", PREFIX);
    public static final String PRODUCT_DETAILS_JSP = String.format("%sproductData.jsp", PREFIX);
    public static final String CART_JSP = String.format("%scart.jsp", PREFIX);
    public static final String CHECKOUT_JSP = String.format("%scheckout.jsp", PREFIX);
    public static final String ORDER_OVERVIEW_JSP = String.format("%sorderOverview.jsp", PREFIX);
    public static final String MINI_CART_JSP = String.format("%sminiCart.jsp", PREFIX);
    public static final String REGISTER_JSP = String.format("%sregister.jsp", PREFIX);
    public static final String USERS_JSP = String.format("%susers.jsp", PREFIX);
}
