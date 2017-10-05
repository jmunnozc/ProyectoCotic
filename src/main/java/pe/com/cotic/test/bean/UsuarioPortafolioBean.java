package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import pe.com.cotic.test.dao.PortafolioDao;
import pe.com.cotic.test.dao.UsuarioPortafolioDao;
import pe.com.cotic.test.daoImpl.PortafolioDaoImpl;
import pe.com.cotic.test.daoImpl.UsuarioPortafolioDaoImpl;
import pe.com.cotic.test.modelo.Nivel;
import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.modelo.Usuarioportafolio;


@ManagedBean
@ViewScoped
public class UsuarioPortafolioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Usuario usuario;
	public Portafolio portafolio;
	private Usuarioportafolio usuarioportafolio;
	private UsuarioPortafolioDao usuarioportafolioDao;
	private List<Usuarioportafolio> listarUsuariosPortafolios;
	private Usuarioportafolio selectedUsuarioPortafolio;
	
	private List<SelectItem> listarPortafoliosLibres;	
	private List<SelectItem> listarNivel;
	
	public UsuarioPortafolioBean(){
		usuarioportafolio = new Usuarioportafolio();
		if (this.usuarioportafolio == null) { 
			this.usuarioportafolio = new Usuarioportafolio(); 
		}
		this.selectedUsuarioPortafolio = new Usuarioportafolio();
	}
	
	public List<Usuarioportafolio> getListarUsuariosPortafolios() {
		UsuarioPortafolioDao usuarioportafolioDao = new UsuarioPortafolioDaoImpl();
		this.listarUsuariosPortafolios = usuarioportafolioDao.ListarUsuariosPortafolios();
		return listarUsuariosPortafolios;
	}

	public Usuarioportafolio getSelectedUsuarioPortafolio() {
		return selectedUsuarioPortafolio;
	}

	public void setSelectedUsuarioPortafolio(
			Usuarioportafolio selectedUsuarioPortafolio) {
		this.selectedUsuarioPortafolio = selectedUsuarioPortafolio;
	}

	public List<SelectItem> getListarNivel() {
		
		this.listarNivel = new ArrayList<SelectItem>();
		PortafolioDao portafolioDao = new PortafolioDaoImpl();
		List<Nivel> n = portafolioDao.ListarNiveles(portafolio);
		listarNivel.clear();
		
		for (Nivel nivel : n){
			SelectItem nivelItem = new SelectItem(nivel.getCodigoNivel(), nivel.getDescripcionNivel());
			this.listarNivel.add(nivelItem);
		}
		
		return listarNivel;
	}


	public void setListarNivel(List<SelectItem> listarNivel) {
		this.listarNivel = listarNivel;
	}

	public List<SelectItem> getListarPortafoliosLibres() {
		
		this.listarPortafoliosLibres = new ArrayList<SelectItem>();
		PortafolioDao portafolioDao = new PortafolioDaoImpl();
		List<Portafolio> p = portafolioDao.ListarPortafoliosxNivel(3);
		listarPortafoliosLibres.clear();
		
		for (Portafolio portafolio : p){
			SelectItem portafolioItem = new SelectItem(portafolio.getCodigoPortafolio(), portafolio.getTituloPortafolio());
			this.listarPortafoliosLibres.add(portafolioItem);
		}
		
		return listarPortafoliosLibres;
	}


	public void setListarPortafoliosLibres(List<SelectItem> listarPortafoliosLibres) {
		this.listarPortafoliosLibres = listarPortafoliosLibres;
	}

	
	public void btnGrabarUsuarioPortafolio() {
				
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		
		this.selectedUsuarioPortafolio.setUsuario(usuario);
		
		Logger.getLogger(getClass().getName()).log(Level.INFO,"CODIGO NIVEL: " + this.selectedUsuarioPortafolio.getNivel().getCodigoNivel());
		Logger.getLogger(getClass().getName()).log(Level.INFO,"CODIGO PORTAFOLIO: " + this.selectedUsuarioPortafolio.getPortafolioByCodigoPortafolio().getCodigoPortafolio());
		Logger.getLogger(getClass().getName()).log(Level.INFO,"CODIGO USUARIO: " + usuario.getCodigoUsuario().toString() + " - " + usuario.getUsuario());
		Logger.getLogger(getClass().getName()).log(Level.INFO,"CODIGO PORTAFOLIO ENLACE: " + this.selectedUsuarioPortafolio.getPortafolioByCodigoPortafolioEnlace().getCodigoPortafolio());
		
		UsuarioPortafolioDao usuarioportafolioDao = new UsuarioPortafolioDaoImpl();
		String msg;		
		
		if (usuarioportafolioDao.grabarUsuarioPortafolio(this.selectedUsuarioPortafolio)) {
			msg = "Se creó correctamente el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al crear el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
	}
}
