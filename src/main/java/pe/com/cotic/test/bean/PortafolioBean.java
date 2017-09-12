package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import pe.com.cotic.test.dao.PortafolioDao;
import pe.com.cotic.test.daoImpl.PortafolioDaoImpl;
import pe.com.cotic.test.modelo.Portafolio;

@ManagedBean
@SessionScoped
public class PortafolioBean implements Serializable {

private static final long serialVersionUID = 1L;
	
	private Portafolio portafolio;
	private PortafolioDao portafolioDao;
	private List<Portafolio> listarPortafolios;
	private Portafolio selectedPortafolio;
	
	private List<SelectItem> listarNivel;
	private List<SelectItem> listarEstado;
	
	
	public PortafolioBean() {
		this.portafolioDao = new PortafolioDaoImpl();
		if (this.portafolio == null) {
			this.portafolio = new Portafolio();
		}
		this.listarPortafolios = new ArrayList<Portafolio>();
		this.selectedPortafolio = new Portafolio(); 
	}


	public Portafolio getPortafolio() {
		return portafolio;
	}


	public void setPortafolio(Portafolio portafolio) {
		this.portafolio = portafolio;
	}


	public Portafolio getSelectedPortafolio() {
		return selectedPortafolio;
	}


	public void setSelectedPortafolio(Portafolio selectedPortafolio) {
		this.selectedPortafolio = selectedPortafolio;
	}


	public List<SelectItem> getListarEstado() {
		
		this.listarEstado = new ArrayList<SelectItem>();
		listarEstado.clear();
		
		SelectItem estadoItem1 = new SelectItem(1,"ACTIVO");
		this.listarEstado.add(estadoItem1);
		SelectItem estadoItem2 = new SelectItem(0,"INACTIVO");
		this.listarEstado.add(estadoItem2);		
		
		return listarEstado;
	}


	public void setListarEstado(List<SelectItem> listarEstado) {
		this.listarEstado = listarEstado;
	}


	public List<SelectItem> getListarNivel() {
		
		this.listarNivel = new ArrayList<SelectItem>();
		listarNivel.clear();
		
		SelectItem estadoItem1 = new SelectItem(1,"TEMA");
		this.listarNivel.add(estadoItem1);
		SelectItem estadoItem2 = new SelectItem(2,"MODULO");
		this.listarNivel.add(estadoItem2);
		SelectItem estadoItem3 = new SelectItem(3,"CURSO");
		this.listarNivel.add(estadoItem3);	
		
		return listarNivel;
	}


	public void setListarNivel(List<SelectItem> listarNivel) {
		this.listarNivel = listarNivel;
	}


	public List<Portafolio> getListarPortafolios() {
		PortafolioDao portafolioDao = new PortafolioDaoImpl();
		this.listarPortafolios = portafolioDao.ListarPortafolios();
		
		return listarPortafolios;
	}
	

	public void btnGrabarPortafolio() {
		PortafolioDao portafolioDao = new PortafolioDaoImpl();
		String msg;
		
		this.selectedPortafolio.setTituloPortafolio(this.selectedPortafolio.getTituloPortafolio().toUpperCase());
		this.selectedPortafolio.setDescripcionPortafolio(this.selectedPortafolio.getDescripcionPortafolio().toUpperCase());
		
		if (portafolioDao.grabarPortafolio(this.selectedPortafolio)) {
			msg = "Se creó correctamente el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al crear el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
	}
	
	public void btnModificarPortafolio() {
		PortafolioDao portafolioDao = new PortafolioDaoImpl();
		String msg;
		
		this.selectedPortafolio.setTituloPortafolio(this.selectedPortafolio.getTituloPortafolio().toUpperCase());
		this.selectedPortafolio.setDescripcionPortafolio(this.selectedPortafolio.getDescripcionPortafolio().toUpperCase());	
		
		if (portafolioDao.modificarPortafolio(this.selectedPortafolio)) {
			msg = "Se modificó correctamente el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}	
	}
	
	
	public void btnEliminarPortafolio() {
		PortafolioDao portafolioDao = new PortafolioDaoImpl();
		String msg;
		if (portafolioDao.eliminarPortafolio(this.selectedPortafolio)) {
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
