<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="errors" required="true" type="java.util.Map" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="order" required="true" type="by.bsuir.phoneshop.model.order.Order" %>
<tr>
    <c:set var="error" value="${errors[name]}"/>
    <td>${label}<span style="color: red">*</span></td>
    <td>
        <input name="${name}" value="${not empty errors ? param[name] : order[name]}">
        <c:if test="${not empty error}">
            <div class="error">
                ${error}
            </div>
        </c:if>
    </td>
</tr>
