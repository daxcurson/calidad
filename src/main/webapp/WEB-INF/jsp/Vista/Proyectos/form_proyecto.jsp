<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<fieldset>
<div class="form-group">
<label for="ProyectoNombre">Nombre</label>
<form:input id="ProyectoNombre" class="form-control" path="nombre"/>
<form:errors path="nombre"/>
</div>
<div class="form-group">
<label for="ProyectoDescripcion">Descripci&oacute;n</label>
<form:textarea cols="80" rows="10" id="ProyectoDescripcion" path="descripcion" class="form-control" />
<form:errors path="descripcion"/>
</div>
</fieldset>