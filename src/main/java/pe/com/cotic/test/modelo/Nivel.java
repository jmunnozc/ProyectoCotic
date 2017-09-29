package pe.com.cotic.test.modelo;

// Generated 28/09/2017 10:33:35 AM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Nivel generated by hbm2java
 */
public class Nivel implements java.io.Serializable {

	private Integer codigoNivel;
	private String descripcionNivel;
	private Set portafolios = new HashSet(0);
	private Set usuarioportafolios = new HashSet(0);

	public Nivel() {
	}

	public Nivel(String descripcionNivel) {
		this.descripcionNivel = descripcionNivel;
	}

	public Nivel(String descripcionNivel, Set portafolios,
			Set usuarioportafolios) {
		this.descripcionNivel = descripcionNivel;
		this.portafolios = portafolios;
		this.usuarioportafolios = usuarioportafolios;
	}

	public Integer getCodigoNivel() {
		return this.codigoNivel;
	}

	public void setCodigoNivel(Integer codigoNivel) {
		this.codigoNivel = codigoNivel;
	}

	public String getDescripcionNivel() {
		return this.descripcionNivel;
	}

	public void setDescripcionNivel(String descripcionNivel) {
		this.descripcionNivel = descripcionNivel;
	}

	public Set getPortafolios() {
		return this.portafolios;
	}

	public void setPortafolios(Set portafolios) {
		this.portafolios = portafolios;
	}

	public Set getUsuarioportafolios() {
		return this.usuarioportafolios;
	}

	public void setUsuarioportafolios(Set usuarioportafolios) {
		this.usuarioportafolios = usuarioportafolios;
	}

}
