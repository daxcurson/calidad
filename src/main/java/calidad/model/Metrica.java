package calidad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.JoinFormula;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="metricas")
public class Metrica 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String descripcion;
	private double valor_objetivo;
	@ManyToOne
	@JoinColumn(name="pregunta_id")
	private Pregunta pregunta;
	@ManyToOne
	@JoinColumn(name="unidad_medida_id")
	private UnidadMedida unidad_medida;
	
	/**
	 * La ultima medicion es un atributo calculado que obtiene la ultima
	 * medicion que se tomo para una metrica en particular.
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinFormula("("+
			"select m.id from mediciones m "+
			"where m.metrica_id = id "+
			"order by m.fecha DESC "+
			"limit 1"+
	")")
	private Medicion ultimaMedicion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getValor_objetivo() {
		return valor_objetivo;
	}
	public void setValor_objetivo(double valor_objetivo) {
		this.valor_objetivo = valor_objetivo;
	}
	@JsonBackReference
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	@JsonBackReference
	public Medicion getUltimaMedicion() {
		return ultimaMedicion;
	}
	public void setUltimaMedicion(Medicion ultimaMedicion) {
		this.ultimaMedicion = ultimaMedicion;
	}
	public UnidadMedida getUnidad_medida() {
		return unidad_medida;
	}
	public void setUnidad_medida(UnidadMedida unidad_medida) {
		this.unidad_medida = unidad_medida;
	}
}
