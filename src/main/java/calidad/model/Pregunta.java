package calidad.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="preguntas")
public class Pregunta 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String texto_pregunta;
	@ManyToOne
	@JoinColumn(name="objetivo_id")
	private Objetivo objetivo;
	
	@OneToMany(mappedBy="pregunta")
	private List<Metrica> metricas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTexto_pregunta() {
		return texto_pregunta;
	}
	public void setTexto_pregunta(String texto_pregunta) {
		this.texto_pregunta = texto_pregunta;
	}
	public Objetivo getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}
	public List<Metrica> getMetricas() {
		return metricas;
	}
	public void setMetricas(List<Metrica> metricas) {
		this.metricas = metricas;
	}
}
