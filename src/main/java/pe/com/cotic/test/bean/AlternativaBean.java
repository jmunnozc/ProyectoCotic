package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import pe.com.cotic.test.dao.AlternativaDao;
import pe.com.cotic.test.daoImpl.AlternativaDaoImpl;
import pe.com.cotic.test.modelo.Alternativa;
import pe.com.cotic.test.modelo.Pregunta;

@ManagedBean
@SessionScoped
public class AlternativaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Alternativa alternativa;
	private AlternativaDao alternativaDao;
	private List<Alternativa> listarAlternativas;
	private Alternativa selectedAlternativa;
	
	private List<SelectItem> listarPregunta;
	private List<SelectItem> listarEstado;

	public AlternativaBean() {
		alternativa = new Alternativa(); 
		this.alternativaDao = new AlternativaDaoImpl();
		if (this.alternativa == null) { 
			this.alternativa = new Alternativa(); 
		}
		this.listarAlternativas = new ArrayList<Alternativa>();
		this.selectedAlternativa = new Alternativa();
	}

	public Alternativa getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}

	public AlternativaDao getAlternativaDao() {
		return alternativaDao;
	}

	public void setAlternativaDao(AlternativaDao alternativaDao) {
		this.alternativaDao = alternativaDao;
	}

	public Alternativa getSelectedAlternativa() {
		return selectedAlternativa;
	}

	public void setSelectedAlternativa(Alternativa selectedAlternativa) {
		this.selectedAlternativa = selectedAlternativa;
	}

	public List<SelectItem> getListarPregunta() {
		
		this.listarPregunta = new ArrayList<SelectItem>();
		AlternativaDao alternativaDao = new AlternativaDaoImpl();
		List<Pregunta> p = alternativaDao.ListarPreguntas(alternativa);
		listarPregunta.clear();
		
		for (Pregunta pregunta : p){
			SelectItem preguntaItem = new SelectItem(pregunta.getCodigoPregunta(), pregunta.getTituloPregunta());
			this.listarPregunta.add(preguntaItem);
		}
		
		return listarPregunta;
	}

	public void setListarPregunta(List<SelectItem> listarPregunta) {
		this.listarPregunta = listarPregunta;
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

	public List<Alternativa> getListarAlternativas() {
		
		AlternativaDao alternativaDao = new AlternativaDaoImpl();
		this.listarAlternativas = alternativaDao.ListarAlternativas();
		
		return listarAlternativas;
	}

	public void btnGrabarAlternativa() {
		AlternativaDao alternativaDao = new AlternativaDaoImpl();
		String msg;
		
		/*Date today = new Date();
		String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(today);
		this.selectedPortafolio.setFechaInicioPortafolio(fechaActual);*/		
		
		if (alternativaDao.grabarAlternativa(this.selectedAlternativa)) {
			msg = "Se creó correctamente el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al crear el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
	}
	
	public void btnModificarAlternativa() {
		AlternativaDao alternativaDao = new AlternativaDaoImpl();
		String msg;		
		
		if (alternativaDao.modificarAlternativa(this.selectedAlternativa)) {
			msg = "Se modificó correctamente el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}	
	}
	
	
	public void btnEliminarPregunta() {
		AlternativaDao alternativaDao = new AlternativaDaoImpl();
		String msg;
		
		if (alternativaDao.eliminarAlternativa(this.selectedAlternativa)) {
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
