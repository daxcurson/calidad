<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<fieldset>
<div class="form-group">
<label for="UnidadMedidaUnidad">Unidad</label>
<form:input id="UnidadMedidaUnidad" class="form-control" path="unidad"/>
<form:errors path="unidad"/>
</div>
<div class="form-group">
<label for="UnidadMedidaSimbolo">S&iacute;mbolo</label>
<form:input id="UnidadMedidaSimbolo" path="simbolo" class="form-control" />
<form:errors path="simbolo"/>
</div>
<div class="form-group">
<label for="UnidadMedidaDescripcion">Descripci&oacute;n</label>
<form:input id="UnidadMedidaDescripcion" path="descripcion" class="form-control" />
<form:errors path="descripcion"/>
</div>
</fieldset>