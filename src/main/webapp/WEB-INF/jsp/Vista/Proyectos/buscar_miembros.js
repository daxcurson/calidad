$(document).ready(function()
{
	$('#BotonAgregarColaborador').click(
			function()
			{
				var url="${pageContext.request.contextPath}/proyectos/agregar_colaborador";
				$.getJSON(url,
				{
					persona_id: $("#ProyectoMiembros").val()
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
	"<tr><th>Nombre</th><th>Rol</th><th>Acciones</th></tr>";
	$.each(miembros,function(index,miembro)
	{
		options+="<tr>"+
		'<td>'+miembro.persona.nombre+"</td>"+
		'<td>'+miembro.rol.nombre+"</td>"+
		'<td class="miembro_proyecto" id="'+miembro.persona.id+'"><img src="${pageContext.request.contextPath}/img/cross.png" onclick="quitar_miembro('+miembro.persona.id+')">'+
		'</td>'+
		'</tr>';
	}
	);
	options+="</table>";
	$("#MiembrosProyecto").html(options);
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
		rol_id: id_miembro
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