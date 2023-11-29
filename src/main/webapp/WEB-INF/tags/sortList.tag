<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="sort" required="true" %>
<a href="?command=PRODUCT_LIST&search=${param.search}&sort=${sort}&order=ASC" style="${param.sort eq sort and param.order eq 'ASC' ? 'font-weight: bold' : ''}">&#8593</a>
<a href="?command=PRODUCT_LIST&search=${param.search}&sort=${sort}&order=DESC" style="${param.sort eq sort and param.order eq 'DESC' ? 'font-weight: bold' : ''}">&#8595</a>