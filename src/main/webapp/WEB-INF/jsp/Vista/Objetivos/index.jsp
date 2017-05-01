<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Objetivos del proyecto <c:out value="${proyecto.nombre}"/></h1>

<p>
<a href="${pageContext.request.contextPath}/objetivos/add/${proyecto.id}">Agregar nuevo objetivo en el proyecto</a>
</p>

<table class="table">
<tr>
<th>Id</th>
<th>T&iacute;tulo</th>
<th>Descripci&oacute;n</th>
<th>Acciones</th>
</tr>
<c:forEach items="${objetivos}" var="objetivo">
<tr>
<td><c:out value="${objetivo.id}"/></td>
<td>${objetivo.titulo}</td>
<td>${objetivo.descripcion}</td>
<td><a href="${pageContext.request.contextPath}/objetivos/edit/${objetivo.id}">Editar</a> | <a href="${pageContext.request.contextPath}/preguntas/listar/${objetivo.id}">Listar preguntas asociadas</a></td>
</tr>
</c:forEach>
</table>