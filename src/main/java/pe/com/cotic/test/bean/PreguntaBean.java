package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import pe.com.cotic.test.dao.PreguntaDao;
import pe.com.cotic.test.daoImpl.PreguntaDaoImpl;
import pe.com.cotic.test.modelo.Alternativa;
import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.modelo.Pregunta;


@ManagedBean
@SessionScoped
public class PreguntaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pregunta pregunta;
	private PreguntaDao preguntaDao;
	private List<Pregunta> listarPreguntas;
	private Pregunta selectedPregunta;
	
	//private Alternativa alternativa;
	private List<Alternativa> listarAlternativas;
	
	private List<SelectItem> listarPortafolio;
	private List<SelectItem> listarEstado;

	public PreguntaBean() {
		pregunta = new Pregunta(); 
		this.preguntaDao = new PreguntaDaoImpl();
		if (this.pregunta == null) { 
			this.pregunta = new Pregunta(); 
		}
		this.listarPreguntas = new ArrayList<Pregunta>();
		this.selectedPregunta = new Pregunta();
	}
	
	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	/*
	public Alternativa getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}
	*/
	
	public PreguntaDao getPreguntaDao() {
		return preguntaDao;
	}

	public void setPreguntaDao(PreguntaDao preguntaDao) {
		this.preguntaDao = preguntaDao;
	}

	public Pregunta getSelectedPregunta() {
		return selectedPregunta;
	}

	public void setSelectedPregunta(Pregunta selectedPregunta) {
		this.selectedPregunta = selectedPregunta;
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

	public List<Pregunta> getListarPreguntas() {
		
		PreguntaDao preguntaDao = new PreguntaDaoImpl();
		this.listarPreguntas = preguntaDao.ListarPreguntas();
		//this.listarAlternativas = preguntaDao.ListarAlternativas(pregunta);
	
		return listarPreguntas;
	}

	
	public List<Alternativa> getListarAlternativas() {
		
		PreguntaDao preguntaDao = new PreguntaDaoImpl();
		this.pregunta.setCodigoPregunta(1);
		//this.listarAlternativas = preguntaDao.ListarAlternativas(this.listarPreguntas);
		
		return listarAlternativas;
	}
	
	
	public List<SelectItem> getListarPortafolio() {
		
		this.listarPortafolio = new ArrayList<SelectItem>();
		PreguntaDao preguntaDao = new PreguntaDaoImpl();
		List<Portafolio> n = preguntaDao.ListarPortafolios(pregunta);
		listarPortafolio.clear();
		
		for (Portafolio portafolio : n){
			SelectItem portafolioItem = new SelectItem(portafolio.getCodigoPortafolio(), portafolio.getTituloPortafolio());
			this.listarPortafolio.add(portafolioItem);
		}
		
		return listarPortafolio;
	}

	public void setListarPortafolio(List<SelectItem> listarPortafolio) {
		this.listarPortafolio = listarPortafolio;
	}

	public void btnGrabarPregunta() {
		PreguntaDao preguntaDao = new PreguntaDaoImpl();
		String msg;
		
		/*Date today = new Date();
		String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(today);
		this.selectedPortafolio.setFechaInicioPortafolio(fechaActual);*/		
		
		if (preguntaDao.grabarPregunta(this.selectedPregunta)) {
			msg = "Se creó correctamente el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al crear el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
	}
	
	public void btnModificarPregunta() {
		PreguntaDao preguntaDao = new PreguntaDaoImpl();
		String msg;		
		
		if (preguntaDao.modificarPregunta(this.selectedPregunta)) {
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
		PreguntaDao preguntaDao = new PreguntaDaoImpl();
		String msg;
		
		if (preguntaDao.eliminarPregunta(this.selectedPregunta)) {
			msg = "Se eliminó correctamente el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar el registro...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	private boolean isPostBack() {
		boolean rpta; 
		return FacesContext.getCurrentInstance().isPostback();
	}
}
