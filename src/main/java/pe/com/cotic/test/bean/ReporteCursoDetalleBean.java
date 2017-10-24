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
public class ReporteCursoDetalleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Reportecursodetalle reportecursodetalle;
	private ReporteCursoDao reportecursoDao;
	private List<Reportecursodetalle> listarReportecursosdetalle;
	private Reportecursodetalle selectedReportecursodetalle;
	
	public ReporteCursoDetalleBean() {
		reportecursodetalle = new Reportecursodetalle(); 
		this.reportecursoDao = new ReporteCursosDaoImpl();
		if (this.reportecursodetalle == null) { 
			this.reportecursodetalle = new Reportecursodetalle(); 
		}
		this.listarReportecursosdetalle = new ArrayList<Reportecursodetalle>();
		this.selectedReportecursodetalle = new Reportecursodetalle();
	}

	public Reportecursodetalle getReportecursodetalle() {
		return reportecursodetalle;
	}

	public void setReportecursodetalle(Reportecursodetalle reportecursodetalle) {
		this.reportecursodetalle = reportecursodetalle;
	}

	public ReporteCursoDao getReportecursoDao() {
		return reportecursoDao;
	}

	public void setReportecursoDao(ReporteCursoDao reportecursoDao) {
		this.reportecursoDao = reportecursoDao;
	}

	public List<Reportecursodetalle> getListarReportecursosdetalle() {	
		return listarReportecursosdetalle;
	}

	public void setListarReportecursosdetalle(List<Reportecursodetalle> listarReportecursosdetalle) {
		this.listarReportecursosdetalle = listarReportecursosdetalle;
	}

	public Reportecursodetalle getSelectedReportecursodetalle() {
		return selectedReportecursodetalle;
	}

	public void setSelectedReportecursodetalle(
			Reportecursodetalle selectedReportecursodetalle) {
		this.selectedReportecursodetalle = selectedReportecursodetalle;
	}
	
	public List<Reportecursodetalle> btnBuscarReporteCursoDetalle(Reportecurso reportecurso) {
		ReporteCursoDao reportecursosDao = new ReporteCursosDaoImpl();
		String msg;
		
		this.listarReportecursosdetalle = reportecursosDao.ListarReporteCursosDetalle(reportecurso);
		
		if (this.listarReportecursosdetalle != null) {
			msg = "Se muestra correctamente el listado...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			//listar(respuestacabecera);
		} else {
			msg = "Error al mostrar el listado...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return listarReportecursosdetalle;
		
	}
}
