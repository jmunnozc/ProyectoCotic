package pe.com.cotic.test.modelo;

import java.sql.Date;

public class Reportecursodetalle {
	
	private Portafolio portafolio;
	private Usuario usuario;
	private Double correctas;
	private Double incorrectas;
	private Double nocontestadas;
	private Integer intentos;
	private Integer puestos;
	private Integer cumplimiento;
	private Integer preguntas;
	private Date fechaRespuesta;
	
	public Portafolio getPortafolio() {
		return portafolio;
	}
	public void setPortafolio(Portafolio portafolio) {
		this.portafolio = portafolio;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Double getCorrectas() {
		return correctas;
	}
	public void setCorrectas(Double correctas) {
		this.correctas = correctas;
	}
	public Double getIncorrectas() {
		return incorrectas;
	}
	public void setIncorrectas(Double incorrectas) {
		this.incorrectas = incorrectas;
	}
	public Double getNocontestadas() {
		return nocontestadas;
	}
	public void setNocontestadas(Double nocontestadas) {
		this.nocontestadas = nocontestadas;
	}
	public Integer getIntentos() {
		return intentos;
	}
	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}
	public Integer getPuestos() {
		return puestos;
	}
	public void setPuestos(Integer puestos) {
		this.puestos = puestos;
	}
	public Integer getCumplimiento() {
		return cumplimiento;
	}
	public void setCumplimiento(Integer cumplimiento) {
		this.cumplimiento = cumplimiento;
	}
	public Integer getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(Integer preguntas) {
		this.preguntas = preguntas;
	}
	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}
	public void setFechaRespuesta(Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}	
	
}
