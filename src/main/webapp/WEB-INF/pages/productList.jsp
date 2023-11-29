<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:master pageTitle="Product List">
  <fmt:setLocale value="${sessionScope.lang}"/>
  <fmt:setBundle basename="messages"/>
  <br>
  <form action="/controller?">
    <input type="hidden" name="command" value="PRODUCT_LIST">
    <input type="text" name="search" value="${param.search}">
    <input type="submit" value="Search">
  </form>
  <c:if test="${not empty message}">
    <span class="success">
      ${message}
    </span>
  </c:if>
  <c:if test="${not empty error}">
    <span class="error">
      <fmt:message key="add_to_cart_error"/>
    </span>
  </c:if>
  <table>
    <thead>
      <tr>
        <td><fmt:message key="image"/></td>
        <td><fmt:message key="model"/>
          <tags:sortList sort="MODEL"/>
        </td>
        <td class="quantity">
          <fmt:message key="quantity"/>
        </td>
        <td class="price"><fmt:message key="price"/>
          <tags:sortList sort="PRICE"/>
        </td>
        <td></td>
      </tr>
    </thead>
    <c:forEach var="product" items="${phones}">
      <c:set var="productId" value="${product.id}"/>
      <tr>
        <form method="post" action="${pageContext.servletContext.contextPath}/controller">
          <input type="hidden" name="command" value="ADD_TO_CART">
          <input type="hidden" name="prevPage" value="PRODUCT_LIST">
        <td>
          <img class="product-tile" src="${product.imageUrl}">
        </td>
        <td><a href="/controller?command=PRODUCT_DETAILS&id=${product.id}">${product.model}</a></td>
        <td>
          <input type="hidden" name="productId" value="${product.id}">
          <input class="quantity" type="text" name="quantity" value="${not empty error && error.phoneId eq productId ? param.quantity : 1}">
          <c:if test="${not empty error && error.phoneId eq productId}">
            <br>
            <span class="error">
              ${error.message}
            </span>
          </c:if>
        </td>
        <td class="price">
            ${product.price}$
        </td>
        <td>
          <button><fmt:message key="add_to_cart"/></button>
        </td>
        </form>
      </tr>
    </c:forEach>
  </table>

</tags:master>