package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.event.ToggleEvent;

import pe.com.cotic.test.dao.PreguntaDao;
import pe.com.cotic.test.daoImpl.PreguntaDaoImpl;
import pe.com.cotic.test.modelo.Alternativa;
import pe.com.cotic.test.modelo.Pregunta;

@ManagedBean
@ViewScoped
public class Pregunta1Bean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Pregunta pregunta;
	private PreguntaDao preguntaDao;
	
	private List<Alternativa> listarAlternativas;
	
	private List<Pregunta> listarPreguntas;
	private List<Alternativa> alternativaDetalles = new ArrayList<Alternativa>();
	
	public Pregunta1Bean() {
		pregunta = new Pregunta(); 
		this.preguntaDao = new PreguntaDaoImpl();
		if (this.pregunta == null) { 
			this.pregunta = new Pregunta(); 
		}
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public List<Pregunta> getListarPreguntas() {
		PreguntaDao preguntaDao = new PreguntaDaoImpl();
		this.listarPreguntas = preguntaDao.ListarPreguntas();
		//this.listarAlternativas = preguntaDao.ListarAlternativas(pregunta);
	
		return listarPreguntas;
	}

	public List<Alternativa> getAlternativaDetalles() {
		return alternativaDetalles;
	}

	public void setAlternativaDetalles(List<Alternativa> alternativaDetalles) {
		this.alternativaDetalles = alternativaDetalles;
	}
	
	public List<Alternativa> getListarAlternativas(Pregunta pregunta) {
		PreguntaDao preguntaDao = new PreguntaDaoImpl();
		this.listarAlternativas = preguntaDao.ListarAlternativas(this.pregunta.getCodigoPregunta());
		
		return listarAlternativas;
	}

	public void onRowToggle(ToggleEvent event) {
		//int evento = 
		//FacesMessage mensage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Pregunta Seleccionada","--> " + ((Pregunta) event.getData()).getCodigoPregunta());
		FacesMessage mensage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Pregunta Seleccionada","--> " + ((Pregunta) event.getData()).getAlternativas());
		FacesContext.getCurrentInstance().addMessage(null, mensage);
		
		this.listarAlternativas = preguntaDao.ListarAlternativas(((Pregunta) event.getData()).getCodigoPregunta());
	}
	
}
