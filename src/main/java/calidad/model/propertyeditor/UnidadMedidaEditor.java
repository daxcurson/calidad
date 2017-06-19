package calidad.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import calidad.model.UnidadMedida;
import calidad.service.UnidadMedidaService;

public class UnidadMedidaEditor extends PropertyEditorSupport {

	private final UnidadMedidaService unidadMedidaService;
	public UnidadMedidaEditor(UnidadMedidaService unidadMedidaService) 
	{
		this.unidadMedidaService=unidadMedidaService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		UnidadMedida g=unidadMedidaService.getUnidadMedidaById(Integer.parseInt(text));
        setValue(g);
	}
}
