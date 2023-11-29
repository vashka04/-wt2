<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<tag:master pageTitle="Error">
  <h1>Sorry, an error has occurred</h1>
  <c:if test="${not empty message}">
    <h4>
      ${message}
    </h4>
  </c:if>
</tag:master>
