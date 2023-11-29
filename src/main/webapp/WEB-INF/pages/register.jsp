<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<tag:master pageTitle="Registration"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<c:if test="${not empty sessionScope['user']}">
  <c:redirect url="/controller?command=PRODUCT_LIST"/>
</c:if>
<a href="/controller?command=LOGIN"><fmt:message key="signin"/></a>
<h3><fmt:message key="register"/></h3>
<form action="/controller">
  <input type="hidden" name="command" value="SIGNUP">
  <label><fmt:message key="login"/></label>
  <input type="text" name="login"><br>
  <label><fmt:message key="password"/></label>
  <input type="password" name="password"><br>
  <label><fmt:message key="repeat_password"/> </label>
  <input type="password" name="repeated"><br>
  <c:if test="${not empty error}">
    <span class="error">
        ${error}
    </span><br>
  </c:if>
  <button><fmt:message key="register"/></button>
</form>