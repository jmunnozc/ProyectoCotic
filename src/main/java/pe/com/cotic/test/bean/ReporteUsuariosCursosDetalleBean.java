package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import pe.com.cotic.test.dao.ReporteCursoDao;
import pe.com.cotic.test.dao.ReporteUsuariosCursosPuestosDao;
import pe.com.cotic.test.dao.RespuestaDetalleDao;
import pe.com.cotic.test.daoImpl.ReporteCursosDaoImpl;
import pe.com.cotic.test.daoImpl.ReporteUsuariosCursosDaoImpl;
import pe.com.cotic.test.daoImpl.ReporteUsuariosCursosPuestosDaoImpl;
import pe.com.cotic.test.daoImpl.RespuestaDetalleDaoImpl;
import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.modelo.Reportecurso;
import pe.com.cotic.test.modelo.Reportecursodetalle;
import pe.com.cotic.test.modelo.Reporteusuarioscursospuestos;
import pe.com.cotic.test.modelo.Respuestacabecera;
import pe.com.cotic.test.modelo.Respuestadetalle;

@ManagedBean
@SessionScoped
public class ReporteUsuariosCursosDetalleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Reportecursodetalle reportecursodetalle;
	private ReporteUsuariosCursosDetalleBean reporteusuarioscursosdetalle;
	private ReporteUsuariosCursosPuestosDao reporteusuarioscursospuestosDao;
	private List<Reportecursodetalle> listarReportecursosdetalle;
	private Reportecursodetalle selectedReportecursodetalle;
	
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
			//listar(respuestacabecera);
		} else {
			msg = "Error al mostrar el listado...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return listarReportecursosdetalle;
		
	}
}
