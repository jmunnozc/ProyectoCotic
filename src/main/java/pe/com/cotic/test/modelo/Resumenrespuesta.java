package pe.com.cotic.test.modelo;

public class Resumenrespuesta {
	
	private Integer codigoPortafolio;
	private Integer codigoUsuario;
	private Integer flagAlternativaCorrecta;
	private Integer total;
	
	public Resumenrespuesta(Integer codigoPortafolio, Integer codigoUsuario, Integer flagAlternativaCorrecta, Integer total) {
		this.codigoPortafolio = codigoPortafolio;
		this.codigoUsuario = codigoUsuario;
		this.flagAlternativaCorrecta = flagAlternativaCorrecta;
		this.total = total;
	}

	public Integer getCodigoPortafolio() {
		return codigoPortafolio;
	}

	public void setCodigoPortafolio(Integer codigoPortafolio) {
		this.codigoPortafolio = codigoPortafolio;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Integer getFlagAlternativaCorrecta() {
		return flagAlternativaCorrecta;
	}

	public void setFlagAlternativaCorrecta(Integer flagAlternativaCorrecta) {
		this.flagAlternativaCorrecta = flagAlternativaCorrecta;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
