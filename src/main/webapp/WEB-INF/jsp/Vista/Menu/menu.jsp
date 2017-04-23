<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h2>Men&uacute; principal</h2>

<p>Bienvenido a la aplicaci&oacute;n Calidad. Haga click en una de las siguientes opciones
para continuar.</p>

<ul>
<sec:authorize access="hasRole('ROLE_PROYECTOS_MOSTRAR_MENU')">
<li><a href="${pageContext.request.contextPath}/proyectos/index">Proyectos</a></li>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_OBJETIVOS_MOSTRAR_MENU')">
<li><a href="${pageContext.request.contextPath}/objetivos/index">Objetivos</a></li>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_METRICAS_MOSTRAR_MENU')">
<li><a href="${pageContext.request.contextPath}/metricas/index">M&eacute;tricas</a></li>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_MEDICIONES_MOSTRAR_MENU')">
<li><a href="${pageContext.request.contextPath}/evaluaciones/index">Mediciones</a></li>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_CONFIGURACION_MOSTRAR_MENU')">
<li><a href="${pageContext.request.contextPath}/configuracion/index">Configuraci&oacute;n</a></li>
</sec:authorize>
</ul>