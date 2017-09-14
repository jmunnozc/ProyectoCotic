package pe.com.cotic.test.modelo;

// Generated 14/09/2017 09:19:37 AM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario implements java.io.Serializable {

	private Integer codigoUsuario;
	private Institucion institucion;
	private String usuario;
	private String clave;
	private String dni;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombres;
	private Date fechaNacimiento;
	private String correo;
	private String telefono;
	private String celular;
	private int estado;
	private String usuarioCreacion;
	private Date fechaCreacion;
	private String usuarioModificacion;
	private Date fechaModificacion;
	private String sexo;
	private Set rolusuarios = new HashSet(0);
	private Set usuariodispositivos = new HashSet(0);
	private Set usuarioportafolios = new HashSet(0);

	public Usuario() {
	}

	public Usuario(Institucion institucion, String clave, String dni,
			String apellidoPaterno, String nombres, Date fechaNacimiento,
			int estado, String usuarioCreacion, Date fechaCreacion) {
		this.institucion = institucion;
		this.clave = clave;
		this.dni = dni;
		this.apellidoPaterno = apellidoPaterno;
		this.nombres = nombres;
		this.fechaNacimiento = fechaNacimiento;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
	}

	public Usuario(Institucion institucion, String usuario, String clave,
			String dni, String apellidoPaterno, String apellidoMaterno,
			String nombres, Date fechaNacimiento, String correo,
			String telefono, String celular, int estado,
			String usuarioCreacion, Date fechaCreacion,
			String usuarioModificacion, Date fechaModificacion, String sexo,
			Set rolusuarios, Set usuariodispositivos, Set usuarioportafolios) {
		this.institucion = institucion;
		this.usuario = usuario;
		this.clave = clave;
		this.dni = dni;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.nombres = nombres;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.telefono = telefono;
		this.celular = celular;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaModificacion = fechaModificacion;
		this.sexo = sexo;
		this.rolusuarios = rolusuarios;
		this.usuariodispositivos = usuariodispositivos;
		this.usuarioportafolios = usuarioportafolios;
	}

	public Integer getCodigoUsuario() {
		return this.codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioModificacion() {
		return this.usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Set getRolusuarios() {
		return this.rolusuarios;
	}

	public void setRolusuarios(Set rolusuarios) {
		this.rolusuarios = rolusuarios;
	}

	public Set getUsuariodispositivos() {
		return this.usuariodispositivos;
	}

	public void setUsuariodispositivos(Set usuariodispositivos) {
		this.usuariodispositivos = usuariodispositivos;
	}

	public Set getUsuarioportafolios() {
		return this.usuarioportafolios;
	}

	public void setUsuarioportafolios(Set usuarioportafolios) {
		this.usuarioportafolios = usuarioportafolios;
	}

}
