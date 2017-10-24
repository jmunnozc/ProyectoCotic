package pe.com.cotic.test.modelo;

public class Reportecursodetalle {
	
	private Portafolio portafolio;
	private Usuario usuario;
	private Integer correctas;
	private Integer incorrectas;
	
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

}
