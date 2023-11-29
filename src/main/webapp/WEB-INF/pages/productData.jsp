<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:master pageTitle="Product data"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<h1>${product.model}</h1>
<c:if test="${not empty error}">
  <span class="error">
    <fmt:message key="add_to_cart_error"/>
  </span>
</c:if>
<c:if test="${not empty message && empty error}">
  <span class="success">
    ${message}
  </span>
</c:if>
<form method="post" action="/controller">
  <input type="hidden" name="command" value="ADD_TO_CART">
  <input type="hidden" name="prevPage" value="PRODUCT_DETAILS">
  <input type="hidden" name="productId" value="${phone.id}">
  <input type="hidden" name="id" value="${phone.id}">
  <img class="details-img" src="${phone.imageUrl}">
  <div>
    ${phone.description}
  </div>
  <div>
    Price: ${phone.price}$
  </div>
  <input type="text" name="quantity" value="${not empty param.quantity ? param.quantity : 1}">
  <button><fmt:message key="add_to_cart"/></button>
  <c:if test="${not empty error}">
    <br>
    <span class="error">
      ${error.message}
    </span>
  </c:if>
</form>