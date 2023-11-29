<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<a href="/controller?command=LOGOUT"><fmt:message key="logout"/></a>
<h1><fmt:message key="banned_message"/></h1>