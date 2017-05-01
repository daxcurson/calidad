<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<fieldset>
<div class="form-group">
<label for="ObjetivoTitulo">T&iacute;tulo</label>
<form:input id="ObjetivoTitulo" class="form-control" path="titulo"/>
<form:errors path="titulo"/>
</div>
<div class="form-group">
<label for="ObjetivoDescripcion">Descripci&oacute;n</label>
<form:textarea cols="80" rows="10" id="ObjetivoDescripcion" path="descripcion" class="form-control" />
<form:errors path="descripcion"/>
</div>
</fieldset>