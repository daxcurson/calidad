<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Asignar colaboradores al proyecto ${proyecto.nombre}</h1>

<script type="text/javascript">
<%@include file="/WEB-INF/jsp/Vista/Proyectos/buscar_miembros.js" %>
</script>

<select name="colaboradores_proyecto" id="ColaboradoresProyectoSelect">
<c:forEach items="${colaboradores}" var="colaborador">
<option value="${colaborador.id}"><c:out value="${colaborador.nombre}"/></option>
</c:forEach>
</select>
<input type="button" value="Agregar Colaborador" id="BotonAgregarColaborador" />
<div id="ColaboradoresProyecto">
<table>
<tr><th>Nombre</th><th>Acciones</th></tr>
<c:choose>
	<c:when test="${not empty colaboradores_proyecto}">
		<c:forEach items="${colaboradores_proyecto}" var="miembro">
			<tr>
			<td><c:out value="${miembro.colaborador.nombre}" /></td>
			<td class="miembro_proyecto" id="<c:out value="${miembro.id}"/>">
			<img src="${pageContext.request.contextPath}/img/cross.png" onclick="quitar_miembro(<c:out value="${miembro.id}"/>)">
			</td>

			</tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr><td colspan="5" style="text-align:center">-- No hay miembros --</td></tr>
	</c:otherwise>
</c:choose>
</table>
</div>