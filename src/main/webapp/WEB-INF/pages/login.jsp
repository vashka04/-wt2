<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<tag:master pageTitle="Login"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<c:if test="${not empty sessionScope['user']}">
    <c:redirect url="/controller?command=PRODUCT_LIST"/>
</c:if>
<a href="/controller?command=REGISTER"><fmt:message key="signup"/></a>
<h3><fmt:message key="login"/></h3>
<form action="/controller" method="post">
    <input type="hidden" name="command" value="AUTHORISE">
    <label><fmt:message key="login"/></label>
    <input type="text" name="login"><br>
    <label><fmt:message key="password"/> </label>
    <input type="password" name="password"><br>
    <button><fmt:message key="submit"/> </button>
</form>