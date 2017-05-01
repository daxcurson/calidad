<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<fieldset>
<div class="form-group">
<label for="PreguntaTextoPregunta">Texto de la Pregunta</label>
<form:textarea cols="80" rows="10" id="PreguntaTextoPregunta" path="texto_pregunta" class="form-control" />
<form:errors path="texto_pregunta"/>
</div>
</fieldset>