<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<fieldset>
<div class="form-group">
<label for="MetricaNombre">Nombre</label>
<form:input id="MetricaNombre" class="form-control" path="nombre"/>
<form:errors path="nombre"/>
</div>
<div class="form-group">
<label for="MetricaDescripcion">Descripci&oacute;n</label>
<form:textarea cols="80" rows="10" id="MetricaDescripcion" path="descripcion" class="form-control" />
<form:errors path="descripcion"/>
</div>
<div class="form-group">
<label for="MetricaValorObjetivo">Valor objetivo de esta metrica</label>
<form:input id="MetricaValorObjetivo" class="form-control" path="valor_objetivo"/>
<form:errors path="valor_objetivo"/>
</div>
<div class="form-group">
<form:label path="unidad_medida" id="LabelUnidadMedida">Unidad de medida</form:label>
<form:select path="unidad_medida" id="MetricaUnidadMedida" class="form-control">
<form:options items="${unidades}" itemValue="id" itemLabel="unidad" />
</form:select>
</div>
</fieldset>