<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>M&eacute;tricas de la pregunta <c:out value="${pregunta.texto_pregunta}"/></h1>

<p>
<a href="${pageContext.request.contextPath}/metricas/add/${pregunta.id}">Agregar nueva m&eacute;trica para la pregunta</a>
</p>

<table class="table">
<tr>
<th>Id</th>
<th>Nombre</th>
<th>Descripci&oacute;n</th>
<th>Valor objetivo</th>
<th>Unidad medida</th>
<th>Acciones</th>
</tr>
<c:forEach items="${metricas}" var="metrica">
<tr>
<td><c:out value="${metrica.id}"/></td>
<td>${metrica.nombre}</td>
<td>${metrica.descripcion}</td>
<td>${metrica.valor_objetivo}</td>
<td>${metrica.unidad_medida.simbolo}</td>
<td>
<a href="${pageContext.request.contextPath}/metricas/edit/${metrica.id}">Editar</a> | 
<a href="${pageContext.request.contextPath}/medicion/historial/${metrica.id}">Historial de mediciones</a>

</td>
</tr>
</c:forEach>
</table>