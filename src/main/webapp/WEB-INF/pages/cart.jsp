<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="cart" class="by.bsuir.phoneshop.model.cart.Cart" scope="request"/>
<tag:master pageTitle="Product data">
  <fmt:setLocale value="${sessionScope.lang}"/>
  <fmt:setBundle basename="messages"/>
  <c:if test="${not empty deleted}">
    <c:redirect url="/controller?command=CART"/>
  </c:if>
  <c:if test="${not empty updated}">
    <c:redirect url="/controller?command=CART&message=Cart was successfully updated"/>
  </c:if>
  <c:if test="${not empty errors}">
    <span class="error">
      <fmt:message key="update_cart_error"/>
    </span>
  </c:if>
  <c:if test="${not empty param.message}">
    <span class="success">
      ${param.message}
    </span>
  </c:if>
  <p></p>
  <form method="post" action="/controller">
    <input type="hidden" name="command" value="UPDATE_CART">
    <table>
      <thead>
      <tr>
        <td><fmt:message key="image"/></td>
        <td><fmt:message key="model"/></td>
        <td class="quantity">
          <fmt:message key="quantity"/>
        </td>
        <td class="price">
          <fmt:message key="price"/>
        </td>
        <td></td>
      </tr>
      </thead>
      <c:forEach var="item" items="${cart.items}" varStatus="status">
        <tr>
          <td>
            <img class="product-tile" src="${item.product.imageUrl}">
          </td>
          <td><a href="${pageContext.servletContext.contextPath}/products/${item.product.id}">${item.product.model}</a></td>
          <td>
            <input type="hidden" name="productId" value="${item.product.id}">
            <c:set var="error" value="${errors[item.product.id]}"/>
            <input type="text" name="quantity" value="${not empty error ? paramValues["quantity"][status.index] : item.quantity }" class="quantity">
            <c:if test="${not empty error}">
              <br>
              <span class="error">
                ${error}
              </span>
            </c:if>
          </td>
          <td class="price">
            ${item.product.price}$
          </td>
          <td>
            <button form="deleteCartItem" formaction="/controller?phoneId=${item.product.id}"><fmt:message key="delete"/></button>
          </td>
        </tr>
      </c:forEach>
      <tr>
        <td></td>
        <td></td>
        <td><fmt:message key="totalPrice"/></td>
        <td>${cart.totalCost}$</td>
      </tr>
    </table>
    <c:if test="${not (cart.items.size() eq 0)}">
      <button><fmt:message key="update"/></button>
    </c:if>
  </form>
  <c:if test="${not (cart.items.size() eq 0)}">
    <button onclick="location.href='${pageContext.servletContext.contextPath}/controller?command=CHECKOUT'"><fmt:message key="checkout"/></button>
  </c:if>
  <form method="post" id="deleteCartItem">
    <input type="hidden" name="command" value="DELETE_CART_ITEM">
  </form>
</tag:master>