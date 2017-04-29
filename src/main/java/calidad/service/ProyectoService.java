package calidad.service;

import java.util.List;

import calidad.model.Proyecto;

public interface ProyectoService 
{
	List<Proyecto> listarProyectos();
	void agregar(Proyecto proyecto);
	Proyecto getProyectoById(Integer proyectoId);
	void grabar(Proyecto proyecto);
}
