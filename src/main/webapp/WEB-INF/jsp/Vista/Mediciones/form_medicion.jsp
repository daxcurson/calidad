<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<fieldset>
<div class="form-group">
<label for="MedicionValorMedido">Valor medido</label>
<form:input id="MedicionValorMedido" class="form-control" path="valor_medido"/>
<form:errors path="valor_medido"/>
</div>
</fieldset>