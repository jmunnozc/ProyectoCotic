package pe.com.cotic.test.bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import pe.com.cotic.test.dao.UsuarioDao;
import pe.com.cotic.test.daoImpl.UsuarioDaoImpl;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.util.MyUtil;

import java.awt.event.ActionEvent;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private List<Usuario> listarUsuarios;
	private Usuario selectedUsuario;
	
	public UsuarioBean() {
		this.usuarioDao = new UsuarioDaoImpl();
		if (this.usuario == null) {
			this.usuario = new Usuario();
		}
		this.listarUsuarios = new ArrayList<Usuario>();
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

	public void verificarDatos() throws Exception {			
		Usuario us;
		FacesMessage message;
		boolean loggedIn;
		String ruta = "";
		
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		RequestContext context = RequestContext.getCurrentInstance();
		
		try {
			us = usuarioDao.verificarDatos(this.usuario);

			if (us != null) {
				loggedIn = true;
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Bienvenido", this.usuario.getUsuario());				
				ruta = MyUtil.baseurl() + "views/paginaCliente.jsf";
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
	}

	public boolean verificarSesion() {
		boolean estado;

		if (FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get("usuario") == null) {
			estado = false;
		} else {
			estado = true;
		}

		return estado;
	}

	public String cerrarSesion() {

		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();

		return "index?faces-redirect=true";
		//return "index";
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
	
	public void btnGrabarUsuario(ActionEvent actionEvent) {
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		String msg;
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
	
	public void btnModificarUsuario(ActionEvent actionEvent) {
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		String msg;
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
	
	public void btnEliminarUsuario(ActionEvent actionEvent) {
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		String msg;
		if (usuarioDao.eliminarUsuario(this.selectedUsuario.getCodigoUsuario())) {
			msg = "Se eliminó correctamente el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
}
