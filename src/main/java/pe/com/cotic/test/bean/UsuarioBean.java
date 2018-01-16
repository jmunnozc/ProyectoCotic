package pe.com.cotic.test.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import pe.com.cotic.test.dao.UsuarioDao;
import pe.com.cotic.test.daoImpl.UsuarioDaoImpl;
import pe.com.cotic.test.modelo.CambiaClave;
import pe.com.cotic.test.modelo.Institucion;
import pe.com.cotic.test.modelo.Rol;
import pe.com.cotic.test.modelo.Rolusuario;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.util.MyUtil;

import java.io.Serializable;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private List<Usuario> listarUsuarios;
	private Usuario selectedUsuario;
	private CambiaClave claveUsuario;
	private Rol selectedRol;
	private Rolusuario selectedRolusuario;

	private List<SelectItem> listarSexo;
	private List<SelectItem> listarEstado;
	private List<SelectItem> listarInstitucion;
	private List<SelectItem> listarPerfil;

	public UsuarioBean() {
		this.usuarioDao = new UsuarioDaoImpl();
		if (this.usuario == null) {
			this.usuario = new Usuario();
		}
		this.listarUsuarios = new ArrayList<Usuario>();
		this.selectedUsuario = new Usuario();
		this.claveUsuario = new CambiaClave();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListarUsuarios() {
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		this.listarUsuarios = usuarioDao.ListarUsuarios();
		return listarUsuarios;
	}

	public Usuario getSelectedUsuario() {
		return selectedUsuario;
	}

	public void setSelectedUsuario(Usuario selectedUsuario) {
		this.selectedUsuario = selectedUsuario;
	}

	public Rol getSelectedRol() {
		return selectedRol;
	}

	public void setSelectedRol(Rol selectedRol) {
		this.selectedRol = selectedRol;
	}

	public Rolusuario getSelectedRolusuario() {
		return selectedRolusuario;
	}

	public void setSelectedRolusuario(Rolusuario selectedRolusuario) {
		this.selectedRolusuario = selectedRolusuario;
	}

	public List<SelectItem> getListarSexo() {

		this.listarSexo = new ArrayList<SelectItem>();
		listarSexo.clear();

		SelectItem sexoItem1 = new SelectItem("M", "MASCULINO");
		this.listarSexo.add(sexoItem1);
		SelectItem sexoItem2 = new SelectItem("F", "FEMENINO");
		this.listarSexo.add(sexoItem2);

		return listarSexo;
	}

	public void setListarSexo(List<SelectItem> listarSexo) {
		this.listarSexo = listarSexo;
	}

	public List<SelectItem> getListarEstado() {

		this.listarEstado = new ArrayList<SelectItem>();
		listarEstado.clear();

		SelectItem estadoItem1 = new SelectItem(1, "ACTIVO");
		this.listarEstado.add(estadoItem1);
		SelectItem estadoItem2 = new SelectItem(0, "INACTIVO");
		this.listarEstado.add(estadoItem2);

		return listarEstado;
	}

	public void setListarEstado(List<SelectItem> listarEstado) {
		this.listarEstado = listarEstado;
	}

	public List<SelectItem> getListarInstitucion() {
		
		this.listarInstitucion = new ArrayList<SelectItem>();
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		List<Institucion> n = usuarioDao.ListarInstituciones(usuario);
		listarInstitucion.clear();
		
		for (Institucion institucion : n){
			SelectItem institucionItem = new SelectItem(institucion.getCodigoInstitucion(), institucion.getNombreInstitucion());
			this.listarInstitucion.add(institucionItem);
		}
		
		return listarInstitucion;
	}

	public void setListarInstitucion(List<SelectItem> listarInstitucion) {
		this.listarInstitucion = listarInstitucion;
	}
	
	public List<SelectItem> getListarPerfil() {
		
		this.listarPerfil = new ArrayList<SelectItem>();
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		
		List<Rol> r = usuarioDao.ListarPerfil(usuario);
		listarPerfil.clear();
		
		for (Rol rol : r){
			SelectItem rolItem = new SelectItem(rol.getCodigoRol(), rol.getDescripcionRol());
			this.listarPerfil.add(rolItem);
		}
		
		return listarPerfil;
	}

	public void setListarPerfil(List<SelectItem> listarPerfil) {
		this.listarPerfil = listarPerfil;
	}
	
	public CambiaClave getClaveUsuario() {
		return claveUsuario;
	}

	public void setClaveUsuario(CambiaClave claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	public void verificarDatos() throws Exception {
		Usuario us;
		FacesMessage message;
		boolean loggedIn;
		String ruta = "";

		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		RequestContext context = RequestContext.getCurrentInstance();

		try {
			us = usuarioDao.verificarDatos(this.usuario);
			//us = usuarioDao.verificarDatosSP(this.usuario);

			if (us != null) {
				loggedIn = true;
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Bienvenido", this.usuario.getUsuario());
				ruta = MyUtil.baseurl() + "views/paginaPrincipal.jsf";
			} else {
				loggedIn = false;
				message = new FacesMessage(FacesMessage.SEVERITY_WARN,"Error de Acceso", "Credenciales Invalidas...");
				ruta = MyUtil.baseurl() + "index.jsf";
			}

		} catch (Exception ex) {
			throw ex;
		}

		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("loggedIn", loggedIn);
		context.addCallbackParam("ruta", ruta);
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
	}

	public boolean verificarSesion() {
		boolean estado;
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

		/*if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") == null) {
		if (usuario == null) {
			estado = false;
		} else {
			estado = true;
		}*/

		return true;
	}

	public String cerrarSesion() {
		
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, MyUtil.baseurl() +  "index.jsf");
		return MyUtil.baseurl() + "index.jsf";
		// return "index";
	}

	public void logout() {
		String ruta = MyUtil.basepathlogin() + "index.jsf";
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext facesContext = FacesContext.getCurrentInstance();

		HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
		sesion.invalidate();

		context.addCallbackParam("loggetOut", true);
		context.addCallbackParam("ruta", ruta);
	}

	public void btnGrabarUsuario() {
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		String msg;

		//Validar Cantidad de Usuarios Creados
		boolean totalAutorizado = usuarioDao.totalUsuarios();
		
		if (totalAutorizado) {
			usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			String nuevoUsuario = this.selectedUsuario.getNombres().substring(0, 1) + this.selectedUsuario.getApellidoPaterno();
			this.selectedUsuario.setUsuario(nuevoUsuario.toUpperCase());
			this.selectedUsuario.setClave("ADMIN");
			this.selectedUsuario.setCorreo(this.selectedUsuario.getCorreo());
			this.selectedUsuario.setNombres(this.selectedUsuario.getNombres().toUpperCase());
			this.selectedUsuario.setApellidoPaterno(this.selectedUsuario.getApellidoPaterno().toUpperCase());
			this.selectedUsuario.setApellidoMaterno(this.selectedUsuario.getApellidoMaterno().toUpperCase());
			// Campos de Auditoria
			Date today = new Date();
			String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(today);
			this.selectedUsuario.setFechaNacimiento(java.sql.Date.valueOf(fechaActual));
			
			this.selectedUsuario.setUsuarioCreacion(usuario.getCodigoUsuario().toString());
			this.selectedUsuario.setFechaCreacion(java.sql.Date.valueOf(fechaActual));
			Institucion inst = new Institucion();
			inst.setCodigoInstitucion(usuario.getInstitucion().getCodigoInstitucion());
			this.selectedUsuario.setInstitucion(inst);	
			
	
			if (usuarioDao.grabarUsuario(this.selectedUsuario)) {
				
				Integer codigoUsuarioGrabado = usuarioDao.buscarCodigoUsuario(nuevoUsuario);
				Rolusuario rolusuario = new Rolusuario();
				Rol rol = new Rol();
				Usuario usu = new Usuario();
				rol.setCodigoRol(2);
				usu.setCodigoUsuario(this.selectedUsuario.getCodigoUsuario());
				rolusuario.setUsuario(usu);
				rolusuario.setRol(rol);
				this.selectedUsuario.setRolusuario(rolusuario);
				usuarioDao.grabarRolUsuario(rolusuario);
				msg = "Se creó correctamente el registro...";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				msg = "Error al crear el registro...";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} else {
			msg = "Error al crear el usuario, la cantidad de usuarios llego al limite, comuníquese con su administrador...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public void btnModificarUsuario() {
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		String msg;
		
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		this.selectedUsuario.setNombres(this.selectedUsuario.getNombres().toUpperCase());
		this.selectedUsuario.setApellidoPaterno(this.selectedUsuario.getApellidoPaterno().toUpperCase());
		this.selectedUsuario.setApellidoMaterno(this.selectedUsuario.getApellidoMaterno().toUpperCase());

		// Campos de Auditoria
		Date today = new Date();
		String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(today);
		this.selectedUsuario.setFechaNacimiento(java.sql.Date.valueOf(fechaActual));
		this.selectedUsuario.setFechaCreacion(java.sql.Date.valueOf(fechaActual));
		this.selectedUsuario.setUsuarioModificacion(usuario.getCodigoUsuario().toString());
		this.selectedUsuario.setFechaModificacion(java.sql.Date.valueOf(fechaActual));
		Institucion inst = new Institucion();
		inst.setCodigoInstitucion(usuario.getInstitucion().getCodigoInstitucion());
		this.selectedUsuario.setInstitucion(inst);	
		
		/*institucion.setCodigoInstitucion(1);
		this.selectedUsuario.setInstitucion(institucion);*/

		if (usuarioDao.modificarUsuario(this.selectedUsuario)) {
			msg = "Se modificó correctamente el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnModificarPerfilUsuario() {
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		String msg;
		Rolusuario ru = new Rolusuario();
		Rol r = new Rol();
		if (this.selectedUsuario.getPerfil().equals("1")) r.setCodigoRol(1);
		if (this.selectedUsuario.getPerfil().equals("2")) r.setCodigoRol(2);	
		ru.setCodigoRolUsuario(usuarioDao.buscarCodigoRolusuario(this.selectedUsuario));
		ru.setRol(r);
		ru.setUsuario(this.selectedUsuario);
		this.selectedRolusuario = ru;

		if (usuarioDao.modificarPerfilUsuario(this.selectedRolusuario)) {
			msg = "Se modificó correctamente el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void btnEliminarUsuario() {
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		String msg;
		
		/*institucion.setCodigoInstitucion(1);
		this.selectedUsuario.setInstitucion(institucion);*/
		if (usuarioDao.eliminarUsuario(this.selectedUsuario)) {
			msg = "Se eliminó correctamente el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void btnResetearUsuario() {
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		String msg;
		if (usuarioDao.resetearUsuario(this.selectedUsuario)) {
			msg = "Se reseteo correctamente el usuario...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al realizar el reseteo del usuario seleccionado...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void btnGrabarCambiaClaveUsuario() {
		String msg;				
		
		if (this.claveUsuario.getClaveAnterior().toUpperCase().equals(this.claveUsuario.getClaveNueva().toUpperCase())) {
			msg = "La clave nueva no puede ser igual a la clave anterior...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			if (usuarioDao.cambiarClaveUsuario(this.claveUsuario)) {
				msg = "La clave nueva se registró correctamente...";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				msg = "Error al realizar el registro de la nueva clave...";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);	
			}
		}
	}
}
