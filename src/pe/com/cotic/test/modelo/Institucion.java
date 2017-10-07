package pe.com.cotic.test.modelo;

// Generated 06/10/2017 10:40:55 AM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Institucion generated by hbm2java
 */
public class Institucion implements java.io.Serializable {

	private Integer codigoInstitucion;
	private String nombreInstitucion;
	private String direccionInstitucion;
	private int estado;
	private Set usuarios = new HashSet(0);

	public Institucion() {
	}

	public Institucion(String nombreInstitucion, int estado) {
		this.nombreInstitucion = nombreInstitucion;
		this.estado = estado;
	}

	public Institucion(String nombreInstitucion, String direccionInstitucion,
			int estado, Set usuarios) {
		this.nombreInstitucion = nombreInstitucion;
		this.direccionInstitucion = direccionInstitucion;
		this.estado = estado;
		this.usuarios = usuarios;
	}

	public Integer getCodigoInstitucion() {
		return this.codigoInstitucion;
	}

	public void setCodigoInstitucion(Integer codigoInstitucion) {
		this.codigoInstitucion = codigoInstitucion;
	}

	public String getNombreInstitucion() {
		return this.nombreInstitucion;
	}

	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

	public String getDireccionInstitucion() {
		return this.direccionInstitucion;
	}

	public void setDireccionInstitucion(String direccionInstitucion) {
		this.direccionInstitucion = direccionInstitucion;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Set getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set usuarios) {
		this.usuarios = usuarios;
	}

}
