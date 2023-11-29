<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<tag:master pageTitle="Users"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<h3><fmt:message key="users"/></h3>
<form method="post" action="/controller">
  <input type="hidden" name="command" value="UPDATE_USERS">
  <table>
    <thead>
    <tr>
      <td><fmt:message key="name"/></td>
      <td><fmt:message key="discount"/></td>
      <td><fmt:message key="role"/></td>
      <td><fmt:message key="status"/></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
      <tr>
        <input type="hidden" name="id" value="${user.id}">
        <td>${user.login}</td>
        <td><input name="discount" value="${user.discount * 100}">%</td>
        <td>
          <select name="role">
            <c:choose>
              <c:when test="${user.role.toString() eq 'ADMIN'}">
                <option selected>ADMIN</option>
                <option>USER</option>
              </c:when>
              <c:otherwise>
                <option>ADMIN</option>
                <option selected>USER</option>
              </c:otherwise>
            </c:choose>
          </select>
        </td>
        <td>
          <select name="status">
            <c:choose>
              <c:when test="${user.status.toString() eq 'UNBANNED'}">
                <option selected>UNBANNED</option>
                <option>BANNED</option>
              </c:when>
              <c:otherwise>
                <option>UNBANNED</option>
                <option selected>BANNED</option>
              </c:otherwise>
            </c:choose>
          </select>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <button><fmt:message key="update"/></button>
</form>