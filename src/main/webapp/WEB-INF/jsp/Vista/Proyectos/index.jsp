<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Proyectos</h1>

<p>
<a href="${pageContext.request.contextPath}/proyectos/add">Agregar nuevo proyecto</a>
</p>

<table class="table">
<tr>
<th>Id</th>
<th>Nombre</th>
<th>Descripci&oacute;n</th>
<th>Acciones</th>
</tr>
<c:forEach items="${proyectos}" var="proyecto">
<tr>
<td><c:out value="${proyecto.id}"/></td>
<td>${proyecto.nombre}</td>
<td>${proyecto.descripcion}</td>
<td><a href="${pageContext.request.contextPath}/proyectos/edit/${proyecto.id}">Editar</a> | <a href="${pageContext.request.contextPath}/objetivos/listar/${proyecto.id}">Listar objetivos</a> | 
<a href="${pageContext.request.contextPath}/tablero/index/${proyecto.id}">Tablero de m&eacute;tricas y progreso</a>
</td>
</tr>
</c:forEach>
</table>