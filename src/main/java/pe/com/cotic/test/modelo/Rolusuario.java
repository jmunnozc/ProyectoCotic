package pe.com.cotic.test.modelo;

// Generated 12/09/2017 12:25:55 PM by Hibernate Tools 4.0.0

/**
 * Rolusuario generated by hbm2java
 */
public class Rolusuario implements java.io.Serializable {

	private Integer codigoRolUsuario;
	private Rol rol;
	private Usuario usuario;

	public Rolusuario() {
	}

	public Rolusuario(Rol rol, Usuario usuario) {
		this.rol = rol;
		this.usuario = usuario;
	}

	public Integer getCodigoRolUsuario() {
		return this.codigoRolUsuario;
	}

	public void setCodigoRolUsuario(Integer codigoRolUsuario) {
		this.codigoRolUsuario = codigoRolUsuario;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
