package pe.com.cotic.test.modelo;

public class Usuariospuesto implements Comparable<Usuariospuesto> {

	private Integer codigoUsuario;
	private Integer codigoPortafolio;
	private Integer codigoRespuestaCabecera;
	private Integer codigoRespuestaDetalle;
	private Integer codigoPregunta;
	private Integer codigoAlternativa;
	private Integer flagAlternativa;
	private Integer total;
	private Integer puestoUsuario;
	
	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}
	
	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	public Integer getCodigoPortafolio() {
		return codigoPortafolio;
	}
	
	public void setCodigoPortafolio(Integer codigoPortafolio) {
		this.codigoPortafolio = codigoPortafolio;
	}
	
	public Integer getCodigoRespuestaCabecera() {
		return codigoRespuestaCabecera;
	}
	
	public void setCodigoRespuestaCabecera(Integer codigoRespuestaCabecera) {
		this.codigoRespuestaCabecera = codigoRespuestaCabecera;
	}
	
	public Integer getCodigoRespuestaDetalle() {
		return codigoRespuestaDetalle;
	}
	
	public void setCodigoRespuestaDetalle(Integer codigoRespuestaDetalle) {
		this.codigoRespuestaDetalle = codigoRespuestaDetalle;
	}
	
	public Integer getCodigoPregunta() {
		return codigoPregunta;
	}
	
	public void setCodigoPregunta(Integer codigoPregunta) {
		this.codigoPregunta = codigoPregunta;
	}
	
	public Integer getCodigoAlternativa() {
		return codigoAlternativa;
	}
	
	public void setCodigoAlternativa(Integer codigoAlternativa) {
		this.codigoAlternativa = codigoAlternativa;
	}
	
	public Integer getFlagAlternativa() {
		return flagAlternativa;
	}
	
	public void setFlagAlternativa(Integer flagAlternativa) {
		this.flagAlternativa = flagAlternativa;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPuestoUsuario() {
		return puestoUsuario;
	}

	public void setPuestoUsuario(Integer puestoUsuario) {
		this.puestoUsuario = puestoUsuario;
	}

	@Override
	public int compareTo(Usuariospuesto o) {
		return total.compareTo(o.getTotal());
	}
	
	@Override
	public String toString() {
		return "Usuariospuesto [codigoUsuario=" + codigoUsuario
				+ ", codigoPortafolio=" + codigoPortafolio
				+ ", codigoRespuestaCabecera=" + codigoRespuestaCabecera
				+ ", codigoRespuestaDetalle=" + codigoRespuestaDetalle
				+ ", codigoPregunta=" + codigoPregunta + ", codigoAlternativa="
				+ codigoAlternativa + ", flagAlternativa=" + flagAlternativa
				+ ", total=" + total + ", puestoUsuario=" + puestoUsuario + "]";
	}
	
}
