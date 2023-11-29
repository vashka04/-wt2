<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty secureId}">
    <c:redirect url="/controller?command=ORDER_OVERVIEW&secureId=${secureId}"/>
</c:if>
<tag:master pageTitle="Product data">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="messages"/>
    <c:if test="${not empty errors}">
        <span class="error">
            <fmt:message key="place_order_error"/>
        </span>
    </c:if>
    <table>
        <thead>
        <tr>
            <td><fmt:message key="image"/></td>
            <td><fmt:message key="model"/></td>
            <td class="quantity">
                <fmt:message key="quantity"/>
            </td>
            <td class="price"><fmt:message key="price"/></td>
        </tr>
        </thead>
        <c:forEach var="item" items="${order.items}" varStatus="status">
            <tr>
                <td>
                    <img class="product-tile" src="${item.product.imageUrl}">
                </td>
                <td>${item.product.model}</td>
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
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="PLACE_ORDER">
        <table>
            <fmt:message var="firstname" key="firstname"/>
            <fmt:message var="lastname" key="lastname"/>
            <fmt:message key="phone" var="phone"/>
            <fmt:message var="address" key="delivery_address"/>
            <tag:orderRowForm label="${firstname}" name="firstName" errors="${errors}" order="${order}"/>
            <tag:orderRowForm label="${lastname}" name="lastName" errors="${errors}" order="${order}"/>
            <tag:orderRowForm label="${phone}" name="phone" errors="${errors}" order="${order}"/>
            <tag:orderRowForm label="${address}" name="deliveryAddress" errors="${errors}" order="${order}"/>
        </table>
        <br><button><fmt:message key="place_order"/></button>
    </form>
</tag:master>