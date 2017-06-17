<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Auditores</h1>

<p>
<a href="${pageContext.request.contextPath}/auditores/add">Agregar nuevo auditor</a>
</p>

<table class="table">
<tr>
<th>Id</th>
<th>Nombre</th>
<th>Acciones</th>
</tr>
<c:forEach items="${auditores}" var="auditor">
<tr>
<td><c:out value="${auditor.id}"/></td>
<td>${auditor.nombre}</td>
<td><a href="${pageContext.request.contextPath}/auditores/edit/${auditor.id}">Editar</a>
</td>
</tr>
</c:forEach>
</table>