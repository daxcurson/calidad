package calidad.service;

import java.util.List;

import calidad.model.Colaborador;
import calidad.model.ColaboradorProyecto;
import calidad.model.Proyecto;

public interface ProyectoService 
{
	List<Proyecto> listarProyectos();
	void agregar(Proyecto proyecto);
	Proyecto getProyectoById(Integer proyectoId);
	void grabar(Proyecto proyecto);
	List<ColaboradorProyecto> getColaboradores(int proyecto_id);
	void agregarColaboradorProyecto(Proyecto proyecto, Colaborador colaborador);
	void quitarColaborador(int id, Integer colaborador_proyecto_id);
}
