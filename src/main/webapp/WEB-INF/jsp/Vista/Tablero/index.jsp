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
<th>Valor objetivo</th>
<th>U. medida</th>
<th>&Uacute;ltima medici&oacute;n</th>
<th>Acciones</th>
</tr>
<c:forEach items="${objetivo.preguntas}" var="pregunta">
	<c:forEach items="${pregunta.metricas}" var="metrica">
		<tr>
		<td><c:out value="${metrica.nombre}"/></td>
		<td>${metrica.valor_objetivo}</td>
		<td>${metrica.unidad_medida.simbolo}</td>
		<c:set var="porcentaje" value="${metrica.ultimaMedicion.valor_medido/metrica.valor_objetivo*100}"/>
		<td>${metrica.ultimaMedicion.valor_medido} ${metrica.unidad_medida.simbolo} (${porcentaje}%)</td>
		<td>
		<a href="${pageContext.request.contextPath}/medicion/add/${metrica.id}">Agregar nueva medici&oacute;n</a> | 
		<a href="${pageContext.request.contextPath}/medicion/historial/${metrica.id}">Historial de mediciones</a>
		</td>
		</tr>
	</c:forEach>
</c:forEach>
</table>
</c:forEach>