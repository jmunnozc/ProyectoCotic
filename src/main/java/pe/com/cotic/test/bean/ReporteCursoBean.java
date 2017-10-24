package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import pe.com.cotic.test.dao.ReporteCursoDao;
import pe.com.cotic.test.dao.RespuestaDetalleDao;
import pe.com.cotic.test.daoImpl.ReporteCursosDaoImpl;
import pe.com.cotic.test.daoImpl.RespuestaDetalleDaoImpl;
import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.modelo.Reportecurso;
import pe.com.cotic.test.modelo.Reportecursodetalle;
import pe.com.cotic.test.modelo.Respuestacabecera;
import pe.com.cotic.test.modelo.Respuestadetalle;

@ManagedBean
@SessionScoped
public class ReporteCursoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Reportecurso reportecurso;
	private ReporteCursoDao reportecursoDao;
	private List<Reportecurso> listarReportecursos;
	private Reportecurso selectedReportecurso;
	
	public ReporteCursoBean() {
		reportecurso = new Reportecurso(); 
		this.reportecursoDao = new ReporteCursosDaoImpl();
		if (this.reportecurso == null) { 
			this.reportecurso = new Reportecurso(); 
		}
		this.listarReportecursos = new ArrayList<Reportecurso>();
		this.selectedReportecurso = new Reportecurso();
	}

	public Reportecurso getReportecurso() {
		return reportecurso;
	}

	public void setReportecurso(Reportecurso reportecurso) {
		this.reportecurso = reportecurso;
	}

	public ReporteCursoDao getReportecursoDao() {
		return reportecursoDao;
	}

	public void setReportecursoDao(ReporteCursoDao reportecursoDao) {
		this.reportecursoDao = reportecursoDao;
	}

	public List<Reportecurso> getListarReportecursos() {
		ReporteCursoDao reportecursosDao = new ReporteCursosDaoImpl();
		this.listarReportecursos = reportecursosDao.ListarReporteCursos();
		return listarReportecursos;
	}

	public void setListarReportecursos(List<Reportecurso> listarReportecursos) {
		this.listarReportecursos = listarReportecursos;
	}

	public Reportecurso getSelectedReportecurso() {
		return selectedReportecurso;
	}

	public void setSelectedReportecurso(Reportecurso selectedReportecurso) {
		this.selectedReportecurso = selectedReportecurso;
	}
	
	
}
