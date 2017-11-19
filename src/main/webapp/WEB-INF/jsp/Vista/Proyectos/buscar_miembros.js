$(document).ready(function()
{
	$('#BotonAgregarColaborador').click(
			function()
			{
				var url="${pageContext.request.contextPath}/proyectos/agregar_colaborador";
				$.getJSON(url,
				{
					colaborador_id: $("#ColaboradoresProyectoSelect").val()
				},
				function(miembros)
				{
					if(miembros!==null)
					{
						tabla_miembros(miembros);
					}
				}
				);
			});
});
function tabla_miembros(miembros)
{
	var options="<table>"+
	"<tr><th>Nombre</th><th>Acciones</th></tr>";
	$.each(miembros,function(index,miembro)
	{
		options+="<tr>"+
		'<td>'+miembro.colaborador.nombre+"</td>"+
		'<td class="miembro_proyecto" id="'+miembro.colaborador.id+'"><img src="${pageContext.request.contextPath}/img/cross.png" onclick="quitar_miembro('+miembro.colaborador.id+')">'+
		'</td>'+
		'</tr>';
	}
	);
	options+="</table>";
	$("#ColaboradoresProyecto").html(options);
}
/**
 * Quita a un miembro del proyecto
 * @param id_miembro id del RolPersona que hay que quitar
 * @returns
 */
function quitar_miembro(id_miembro)
{
	var url="${pageContext.request.contextPath}/proyectos/quitar_colaborador";
	$.getJSON(url,
	{
		colaborador_proyecto_id: id_miembro
	},
	function(miembros)
	{
		if(miembros!==null)
		{
			tabla_miembros(miembros);
		}
	}
	);
}