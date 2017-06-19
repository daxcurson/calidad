<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Preguntas del objetivo <c:out value="${objetivo.titulo}"/></h1>

<p>
<a href="${pageContext.request.contextPath}/preguntas/add/${objetivo.id}">Agregar nueva pregunta para el objetivo</a>
</p>

<table class="table">
<tr>
<th>Id</th>
<th>Texto Pregunta</th>
<th>Acciones</th>
</tr>
<c:forEach items="${preguntas}" var="pregunta">
<tr>
<td><c:out value="${pregunta.id}"/></td>
<td>${pregunta.texto_pregunta}</td>
<td><a href="${pageContext.request.contextPath}/preguntas/edit/${pregunta.id}">Editar</a> | <a href="${pageContext.request.contextPath}/metricas/listar/${pregunta.id}">Listar m&eacute;tricas asociadas</a></td>
</tr>
</c:forEach>
</table>