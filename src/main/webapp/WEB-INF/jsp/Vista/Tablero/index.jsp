<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Tablero de m&eacute;tricas y Mediciones del proyecto <c:out value="${proyecto.nombre}"/></h1>

<%
// Por cada objetivo, busco las metricas, cada una en su propia tabla.
// Por cada metrica, veo que mediciones hay. Y de la ultima medicion que hay,
// me fijo si alcanzo el valor objetivo.
%>
<c:forEach items="${objetivos}" var="objetivo">

<h1>Objetivo: <c:out value="${objetivo.titulo}"/></h1>

<table class="table">
<tr>
<th>M&eacute;trica</th>
<th>Mediciones</th>
<th>Acciones</th>
</tr>
<tr>
<td></td>
<td>
<table>
<c:forEach items="${objetivo.preguntas}" var="pregunta">
	<c:forEach items="${pregunta.metricas}" var="metrica">
<tr>
<td><c:out value="${metrica.nombre}"/></td>
<td>${metrica.valor_objetivo}</td>
<%
// Aqui tengo que buscar la ultima metrica.
%>
</tr>
	</c:forEach>
</c:forEach>
</table>
</td>
</tr>
</table>
</c:forEach>