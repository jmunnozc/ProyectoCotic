package pe.com.cotic.test.modelo;

public class Reportecurso {
	
	private Integer codigoPortafolio;
	private String tituloPortafolio;
	private Integer intentos;
	private Integer intentosAnalizados;
	private Integer codigoRespuestaCabecera;
	private Integer correctas;
	private Integer incorrectas;
	private Double porcentajeAciertos;
	private Reportecursodetalle reportecursodetalle;
	
	public Integer getCodigoPortafolio() {
		return codigoPortafolio;
	}
	public void setCodigoPortafolio(Integer codigoPortafolio) {
		this.codigoPortafolio = codigoPortafolio;
	}
	public String getTituloPortafolio() {
		return tituloPortafolio;
	}
	public void setTituloPortafolio(String tituloPortafolio) {
		this.tituloPortafolio = tituloPortafolio;
	}
	public Integer getIntentos() {
		return intentos;
	}
	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}
	public Integer getIntentosAnalizados() {
		return intentosAnalizados;
	}
	public void setIntentosAnalizados(Integer intentosAnalizados) {
		this.intentosAnalizados = intentosAnalizados;
	}
	public Integer getCodigoRespuestaCabecera() {
		return codigoRespuestaCabecera;
	}
	public void setCodigoRespuestaCabecera(Integer codigoRespuestaCabecera) {
		this.codigoRespuestaCabecera = codigoRespuestaCabecera;
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
	public Double getPorcentajeAciertos() {
		return porcentajeAciertos;
	}
	public void setPorcentajeAciertos(Double porcentajeAciertos) {
		this.porcentajeAciertos = porcentajeAciertos;
	}
	public Reportecursodetalle getReportecursodetalle() {
		return reportecursodetalle;
	}
	public void setReportecursodetalle(Reportecursodetalle reportecursodetalle) {
		this.reportecursodetalle = reportecursodetalle;
	}
	


}
