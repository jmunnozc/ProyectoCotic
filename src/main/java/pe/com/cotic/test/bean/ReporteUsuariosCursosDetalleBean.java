package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.PieChartModel;

import pe.com.cotic.test.dao.ReporteCursoDao;
import pe.com.cotic.test.dao.ReporteUsuariosCursosPuestosDao;
import pe.com.cotic.test.dao.RespuestaDetalleDao;
import pe.com.cotic.test.dao.ResumenRespuestaDao;
import pe.com.cotic.test.daoImpl.ReporteCursosDaoImpl;
import pe.com.cotic.test.daoImpl.ReporteUsuariosCursosDaoImpl;
import pe.com.cotic.test.daoImpl.ReporteUsuariosCursosPuestosDaoImpl;
import pe.com.cotic.test.daoImpl.RespuestaDetalleDaoImpl;
import pe.com.cotic.test.daoImpl.ResumenRespuestaDaoImpl;
import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.modelo.Reportecurso;
import pe.com.cotic.test.modelo.Reportecursodetalle;
import pe.com.cotic.test.modelo.Reporteusuarioscursospuestos;
import pe.com.cotic.test.modelo.Respuestacabecera;
import pe.com.cotic.test.modelo.Respuestadetalle;
import pe.com.cotic.test.modelo.Resumenrespuesta;

@ManagedBean
@SessionScoped
public class ReporteUsuariosCursosDetalleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Reportecursodetalle reportecursodetalle;
	private ReporteUsuariosCursosDetalleBean reporteusuarioscursosdetalle;
	private ReporteUsuariosCursosPuestosDao reporteusuarioscursospuestosDao;
	private List<Reportecursodetalle> listarReportecursosdetalle;
	private Reportecursodetalle selectedReportecursodetalle;
	
	private PieChartModel model;
	
	public ReporteUsuariosCursosDetalleBean() {
		reportecursodetalle = new Reportecursodetalle(); 
		this.reporteusuarioscursospuestosDao = new ReporteUsuariosCursosPuestosDaoImpl();
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

	public ReporteUsuariosCursosDetalleBean getReporteusuarioscursosdetalle() {
		return reporteusuarioscursosdetalle;
	}

	public void setReporteusuarioscursosdetalle(
			ReporteUsuariosCursosDetalleBean reporteusuarioscursosdetalle) {
		this.reporteusuarioscursosdetalle = reporteusuarioscursosdetalle;
	}

	public ReporteUsuariosCursosPuestosDao getReporteusuarioscursospuestosDao() {
		return reporteusuarioscursospuestosDao;
	}

	public void setReporteusuarioscursospuestosDao(
			ReporteUsuariosCursosPuestosDao reporteusuarioscursospuestosDao) {
		this.reporteusuarioscursospuestosDao = reporteusuarioscursospuestosDao;
	}

	public List<Reportecursodetalle> getListarReportecursosdetalle() {
		return listarReportecursosdetalle;
	}

	public void setListarReportecursosdetalle(
			List<Reportecursodetalle> listarReportecursosdetalle) {
		this.listarReportecursosdetalle = listarReportecursosdetalle;
	}

	public Reportecursodetalle getSelectedReportecursodetalle() {
		return selectedReportecursodetalle;
	}

	public void setSelectedReportecursodetalle(
			Reportecursodetalle selectedReportecursodetalle) {
		this.selectedReportecursodetalle = selectedReportecursodetalle;
	}
	
	public PieChartModel getModel() {
		return model;
	}

	public void listarCursoCuestionario(Reporteusuarioscursospuestos reporteusuariocursospuestos) {
		List<Reportecursodetalle> lista;
		int codigoUsuario = reporteusuariocursospuestos.getCodigoUsuario();
		try {
			ReporteUsuariosCursosPuestosDao reporteusuarioscursospuestosDao = new ReporteUsuariosCursosPuestosDaoImpl();
			lista = reporteusuarioscursospuestosDao.ListarReporteUsuariosCursosDetalle(codigoUsuario);
			graficarCursoCuestionario(lista);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			
		}		
	}
	
	private void graficarCursoCuestionario(List<Reportecursodetalle> lista) {
		model = new PieChartModel();
		int correctas=0, incorrectas=0, nocontestadas=0;
		for (Reportecursodetalle rr : lista) {
			correctas+=rr.getCorrectas();
			incorrectas+=rr.getIncorrectas();
			nocontestadas+=rr.getNocontestadas();
			//model.set(rr.getPortafolio().toString(), rr.getIntentos());
		}
		model.set("Correctas", correctas);
		model.set("Incorrectas", incorrectas);
		model.set("No Contestadas", nocontestadas);

		/*model.setTitle("Preguntas");*/
		model.setLegendPosition("s");
		model.setFill(true);
		model.setShowDataLabels(true);
		model.setDiameter(150);
		
	}

	public List<Reportecursodetalle> btnBuscarReporteCursoDetalle(Reporteusuarioscursospuestos reporteusuariocursospuestos) {
		ReporteUsuariosCursosPuestosDao reporteusuarioscursospuestosDao = new ReporteUsuariosCursosPuestosDaoImpl();
		String msg;
		int codigoUsuario = reporteusuariocursospuestos.getCodigoUsuario();
		
		System.out.println("Probando");
		this.listarReportecursosdetalle = reporteusuarioscursospuestosDao.ListarReporteUsuariosCursosDetalle(codigoUsuario);
				
		if (this.listarReportecursosdetalle != null) {
			msg = "Se muestra correctamente el listado...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			listarCursoCuestionario(reporteusuariocursospuestos);
		} else {
			msg = "Error al mostrar el listado...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return listarReportecursosdetalle;
		
	}
}
