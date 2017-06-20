<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Unidades de medida</h1>

<p>
<a href="${pageContext.request.contextPath}/unidades/add">Agregar nueva unidad de medida</a>
</p>

<table class="table">
<tr>
<th>Id</th>
<th>Unidad</th>
<th>S&iacute;mbolo</th>
<th>Descripci&oacute;n</th>
<th>Acciones</th>
</tr>
<c:forEach items="${unidades}" var="unidad">
<tr>
<td><c:out value="${unidad.id}"/></td>
<td>${unidad.unidad}</td>
<td>${unidad.simbolo}</td>
<td>${unidad.descripcion}</td>
<td><a href="${pageContext.request.contextPath}/unidades/edit/${unidad.id}">Editar</a> 
</td>
</tr>
</c:forEach>
</table>