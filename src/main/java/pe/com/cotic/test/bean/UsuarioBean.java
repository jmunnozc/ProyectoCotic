package pe.com.cotic.test.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import pe.com.cotic.test.dao.UsuarioDao;
import pe.com.cotic.test.daoImpl.UsuarioDaoImpl;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.util.MyUtil;

@ManagedBean
@RequestScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();

	private List<Usuario> usuarios;
	private Usuario selectedUsuarios;
	
	public void usuarioBean() {
		this.usuarios = new ArrayList<Usuario>();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
	
	public Usuario getSelectedUsuarios() {
		return selectedUsuarios;
	}

	public void setSelectedUsuarios(Usuario selectedUsuarios) {
		this.selectedUsuarios = selectedUsuarios;
	}

	public List<Usuario> getUsuarios() {
		
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		this.usuario = (Usuario) usuarioDao.ListarUsuarios();
		return usuarios;
	}

	public void verificarDatos() throws Exception {
		//UsuarioDao usuDAO = new UsuarioDao();
		
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		
		Usuario us;
		String resultado;
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message;
		boolean loggedIn;
		String ruta = "";

		try {
			us = usuarioDao.verificarDatos(this.usuario);

			if (us != null) {
				// resultado = "exito";
				loggedIn = true;
				FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap().put("usuario", us);
				// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario1",
				// this.usuario.getUsuario());
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Bienvenido", this.usuario.getUsuario());				
				
				//ruta = MyUtil.baseurl() + "views/principal.jsf";
				ruta = MyUtil.baseurl() + "views/paginaCliente.jsf";
			} else {
				// resultado = "error";
				loggedIn = false;
				message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Error de Acceso", "Credenciales Invalidas...");
				ruta = MyUtil.baseurl() + "index.jsf";
			}

		} catch (Exception ex) {
			throw ex;
		}

		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("loggedIn", loggedIn);
		context.addCallbackParam("ruta", ruta);
		// return resultado;

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
}
