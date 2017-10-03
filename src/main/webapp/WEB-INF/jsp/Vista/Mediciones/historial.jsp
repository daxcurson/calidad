<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<script src="${pageContext.request.contextPath}/js/d3.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/dimple.latest.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	mostrarGraficoHistorial();
}
);
function mostrarGraficoHistorial()
{
	var url="${pageContext.request.contextPath}/medicion/historial_json/${metrica.id}";
	var element="#graficomediciones";
	$(element).html('');
	$.ajax({
		async:false,
		url:url,
		dataType:"json",
		success:function(graph_data)
		{
			var svg = dimple.newSvg(element, 1000, 400);
			var myChart = new dimple.chart(svg, graph_data);
			myChart.addCategoryAxis("x", ["fecha"]);
			var quantity=myChart.addMeasureAxis("y", "value");
			quantity.tickFormat="0d";
			var serie_status=myChart.addSeries("name", dimple.plot.line);
			myChart.addLegend(200, 10, 380, 20, "right");
			myChart.draw();
			// Override the tooltip function
			serie_status.getTooltipText = function(event) {

				// Get the key of the item over which we're hovering.
				var key = event.key;

				// Find the datum with the corresponding key:
				for (var i = 0; i < graph_data.length; i++) 
				{
					if (graph_data[i].key == key)

					// Define the tooltip content.
					return [
						"Fecha" + ":" + graph_data[i].fecha,
						"Valor:"+graph_data[i].value+" "+graph_data[i].unit,
					];
				}
			}
		}
	});
}
</script>

<div id="graficomediciones">
</div>

<table class="table">
<tr><th>Fecha y hora</th><th>Valor medido</th><th>Auditor</th></tr>
<c:forEach items="${lista_mediciones}" var="medicion">
<tr>
<td><c:out value="${medicion.fecha}"/></td>
<td><c:out value="${medicion.valor_medido}"/></td>
<td></td>
</tr>
</c:forEach>
</table>