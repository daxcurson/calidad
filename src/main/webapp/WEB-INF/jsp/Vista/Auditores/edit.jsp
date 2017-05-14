<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<form:form method="post" commandName="auditor">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<form:input id="AuditorId" class="form-control" path="id"/>
	<tiles:insertAttribute name="form_auditor"/>
	<input type="submit" name="editar_auditor" value="Editar Auditor">
</form:form>