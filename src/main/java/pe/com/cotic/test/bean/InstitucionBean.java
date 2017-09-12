package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import pe.com.cotic.test.dao.InstitucionDao;
import pe.com.cotic.test.daoImpl.InstitucionDaoImpl;
import pe.com.cotic.test.modelo.Institucion;


@ManagedBean
@SessionScoped
public class InstitucionBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Institucion institucion;
	private InstitucionDao institucionDao;
	private List<Institucion> listarInstituciones;
	private Institucion selectedInstitucion;
	
	private List<SelectItem> listarEstado;
	
	
	public InstitucionBean() {
		this.institucionDao = new InstitucionDaoImpl();
		if (this.institucion == null) {
			this.institucion = new Institucion();
		}
		this.listarInstituciones = new ArrayList<Institucion>();
		this.selectedInstitucion = new Institucion(); 
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public Institucion getSelectedInstitucion() {
		return selectedInstitucion;
	}

	public void setSelectedInstitucion(Institucion selectedInstitucion) {
		this.selectedInstitucion = selectedInstitucion;
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

	public List<Institucion> getListarInstituciones() {
		InstitucionDao institucionDao = new InstitucionDaoImpl();
		this.listarInstituciones = institucionDao.ListarInstituciones();		
		
		return listarInstituciones;
	}

	public void btnGrabarInstitucion() {
		InstitucionDao institucionDao = new InstitucionDaoImpl();
		String msg;
		
		this.selectedInstitucion.setNombreInstitucion(this.selectedInstitucion.getNombreInstitucion().toUpperCase());
		this.selectedInstitucion.setDireccionInstitucion(this.selectedInstitucion.getDireccionInstitucion().toUpperCase());
		
		if (institucionDao.grabarInstitucion(this.selectedInstitucion)) {
			msg = "Se creó correctamente el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al crear el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
	}
	
	public void btnModificarInstitucion() {
		InstitucionDao institucionDao = new InstitucionDaoImpl();
		String msg;
		
		this.selectedInstitucion.setNombreInstitucion(this.selectedInstitucion.getNombreInstitucion().toUpperCase());		
		this.selectedInstitucion.setDireccionInstitucion(this.selectedInstitucion.getDireccionInstitucion().toUpperCase());	
		
		if (institucionDao.modificarInstitucion(this.selectedInstitucion)) {
			msg = "Se modificó correctamente el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}	
	}
	
	
	public void btnEliminarInstitucion() {
		InstitucionDao institucionDao = new InstitucionDaoImpl();
		String msg;
		if (institucionDao.eliminarInstitucion(this.selectedInstitucion)) {
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
