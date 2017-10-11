package pe.com.cotic.test.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import pe.com.cotic.test.modelo.Institucion;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.util.MyUtil;

import java.io.Serializable;

//@ManagedBean
@Named("usuario")
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private List<Usuario> listarUsuarios;
	private Usuario selectedUsuario;

	private List<SelectItem> listarSexo;
	private List<SelectItem> listarEstado;
	private List<SelectItem> listarInstitucion;

	public UsuarioBean() {
		this.usuarioDao = new UsuarioDaoImpl();
		if (this.usuario == null) {
			this.usuario = new Usuario();
		}
		this.listarUsuarios = new ArrayList<Usuario>();
		this.selectedUsuario = new Usuario();
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

		String nuevoUsuario = this.selectedUsuario.getNombres().substring(0, 1)
				+ this.selectedUsuario.getApellidoPaterno();
		this.selectedUsuario.setUsuario(nuevoUsuario.toUpperCase());
		this.selectedUsuario.setClave("ADMIN");
		this.selectedUsuario.setNombres(this.selectedUsuario.getNombres().toUpperCase());
		this.selectedUsuario.setApellidoPaterno(this.selectedUsuario.getApellidoPaterno().toUpperCase());
		this.selectedUsuario.setApellidoMaterno(this.selectedUsuario.getApellidoMaterno().toUpperCase());
		// Campos de Auditoria
		Date today = new Date();
		String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(today);
		this.selectedUsuario.setFechaNacimiento(java.sql.Date.valueOf(fechaActual));
		this.selectedUsuario.setUsuarioCreacion("JAMBROCIO");
		this.selectedUsuario.setFechaCreacion(java.sql.Date.valueOf(fechaActual));
		
		/*institucion.setCodigoInstitucion(1);
		this.selectedUsuario.setInstitucion(institucion);*/

		if (usuarioDao.grabarUsuario(this.selectedUsuario)) {
			msg = "Se creó correctamente el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al crear el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public void btnModificarUsuario() {
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		String msg;
		this.selectedUsuario.setNombres(this.selectedUsuario.getNombres().toUpperCase());
		this.selectedUsuario.setApellidoPaterno(this.selectedUsuario.getApellidoPaterno().toUpperCase());
		this.selectedUsuario.setApellidoMaterno(this.selectedUsuario.getApellidoMaterno().toUpperCase());

		// Campos de Auditoria
		Date today = new Date();
		String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(today);
		this.selectedUsuario.setFechaNacimiento(java.sql.Date.valueOf(fechaActual));
		this.selectedUsuario.setFechaCreacion(java.sql.Date.valueOf(fechaActual));
		this.selectedUsuario.setUsuarioModificacion("JAMBROCIO");
		this.selectedUsuario.setFechaModificacion(java.sql.Date.valueOf(fechaActual));
		
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
	
	
	public void btnCambiarClaveUsuario() {
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		String msg;
		
	}
}
