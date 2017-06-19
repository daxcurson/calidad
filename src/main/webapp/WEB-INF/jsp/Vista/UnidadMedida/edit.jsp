<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<form:form method="post" commandName="unidad_medida">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<form:input id="UnidadMedidaId" class="form-control" path="id"/>
	<tiles:insertAttribute name="form_unidad_medida"/>
	<input type="submit" name="editar_unidad_medida" value="Editar Unidad de Medida">
</form:form>