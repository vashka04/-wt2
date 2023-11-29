<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="order" class="by.bsuir.phoneshop.model.order.Order" scope="request"/>
<tag:master pageTitle="Product data">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="messages"/>
    <h1><fmt:message key="your_order"/></h1>
    <table>
        <thead>
        <tr>
            <td><fmt:message key="image"/></td>
            <td><fmt:message key="model"/>
            </td>
            <td class="quantity">
                <fmt:message key="quantity"/>
            </td>
            <td class="price"><fmt:message key="price"/>
            </td>
        </tr>
        </thead>
        <c:forEach var="item" items="${order.items}" varStatus="status">
            <tr>
                <td>
                    <img class="product-tile" src="${item.product.imageUrl}">
                </td>
                <td><a href="${pageContext.servletContext.contextPath}/controller?command=PRODUCT_DETAILS&id=${item.product.id}">${item.product.model}</a></td>
                <td>
                    ${item.quantity}
                </td>
                <td class="price">
                    ${item.product.price}$
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td><fmt:message key="totalPrice"/></td>
            <td>${order.totalCost}$</td>
        </tr>
    </table>
    <h2><fmt:message key="your_details"/></h2>
    <table><fmt:message var="firstname" key="firstname"/>
        <fmt:message var="lastname" key="lastname"/>
        <fmt:message key="phone" var="phone"/>
        <fmt:message var="address" key="delivery_address"/>
        <tag:orderRowForm label="${firstname}" name="firstName" errors="${errors}" order="${order}"/>
        <tag:orderRowForm label="${lastname}" name="lastName" errors="${errors}" order="${order}"/>
        <tag:orderRowForm label="${phone}" name="phone" errors="${errors}" order="${order}"/>
        <tag:orderRowForm label="${address}" name="deliveryAddress" errors="${errors}" order="${order}"/>
    </table>

</tag:master>