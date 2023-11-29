<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="pageTitle" required="true" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
  <title>${pageTitle}</title>
  <link href='http://fonts.googleapis.com/css?family=Lobster+Two' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">
</head>
<body class="product-list">
  <header>
    <a href="/">
      <img src="/images/logo.svg"/>
      PhoneShop
    </a>
  </header>
  <main>
    <a href="/controller?command=${command}&locale=en"><fmt:message key="eng"/></a>
    <a href="/controller?command=${command}&locale=ru"><fmt:message key="rus"/></a>
    <br>
    <c:if test="${not empty sessionScope['user']}">
      <a href="/controller?command=LOGOUT"><fmt:message key="logout"/></a><br>
      <c:if test="${sessionScope['user'].role.toString() eq 'ADMIN'}">
        <a href="/controller?command=USERS"><h5><fmt:message key="users"/></h5></a>
      </c:if>
      <jsp:include page="/controller?command=MINICART"/>
    </c:if>
    <jsp:doBody/>
  </main>
</body>
</html>