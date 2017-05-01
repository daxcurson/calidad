<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>M&eacute;tricas de la pregunta <c:out value="${pregunta.texto_pregunta}"/></h1>

<p>
<a href="${pageContext.request.contextPath}/objetivos/add/${proyecto.id}">Agregar nueva m&eacute;trica para la pregunta</a>
</p>

<table class="table">
<tr>
<th>Id</th>
<th>Nombre</th>
<th>Descripci&oacute;n</th>
<th>Valor objetivo</th>
<th>Acciones</th>
</tr>
<c:forEach items="${metricas}" var="metrica">
<tr>
<td><c:out value="${metrica.id}"/></td>
<td>${metrica.nombre}</td>
<td>${metrica.descripcion}</td>
<td>${metrica.valor_objetivo}</td>
<td><a href="${pageContext.request.contextPath}/metricas/edit/${metrica.id}">Editar</a></td>
</tr>
</c:forEach>
</table>