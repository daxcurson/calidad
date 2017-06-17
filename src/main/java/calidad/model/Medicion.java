package calidad.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mediciones")
public class Medicion 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="metrica_id")
	private Metrica metrica;
	private double valor_medido;
	private Date fecha;
	private Proyecto proyecto;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Metrica getMetrica() {
		return metrica;
	}
	public void setMetrica(Metrica metrica) {
		this.metrica = metrica;
	}
	public double getValor_medido() {
		return valor_medido;
	}
	public void setValor_medido(double valor_medido) {
		this.valor_medido = valor_medido;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
}
