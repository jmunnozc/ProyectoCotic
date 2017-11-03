package pe.com.cotic.test.modelo;

public class Reportecursodetalle {
	
	private Portafolio portafolio;
	private Usuario usuario;
	private Integer correctas;
	private Integer incorrectas;
	private Integer nocontestadas;
	private Integer intentos;
	private Integer puestos;
	private Integer cumplimiento;
	private Integer preguntas;
	
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
	public Integer getCorrectas() {
		return correctas;
	}
	public void setCorrectas(Integer correctas) {
		this.correctas = correctas;
	}
	public Integer getIncorrectas() {
		return incorrectas;
	}
	public void setIncorrectas(Integer incorrectas) {
		this.incorrectas = incorrectas;
	}
	public Integer getNocontestadas() {
		return nocontestadas;
	}
	public void setNocontestadas(Integer nocontestadas) {
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
	
}
