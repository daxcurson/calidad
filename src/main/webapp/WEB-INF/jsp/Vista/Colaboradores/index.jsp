<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Auditores</h1>

<p>
<a href="${pageContext.request.contextPath}/colaboradores/add">Agregar nuevo colaborador</a>
</p>

<table class="table">
<tr>
<th>Id</th>
<th>Nombre</th>
<th>Acciones</th>
</tr>
<c:forEach items="${colaboradores}" var="colaborador">
<tr>
<td><c:out value="${colaborador.id}"/></td>
<td>${colaborador.nombre}</td>
<td><a href="${pageContext.request.contextPath}/colaboradores/edit/${colaborador.id}">Editar</a>
</td>
</tr>
</c:forEach>
</table>