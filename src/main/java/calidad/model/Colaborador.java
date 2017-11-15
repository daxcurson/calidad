package calidad.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="colaboradores")
public class Colaborador extends Persona
{
	public Colaborador()
	{
		this.setHabilitada(true);
	}
}
