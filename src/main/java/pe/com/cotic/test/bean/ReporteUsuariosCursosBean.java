package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import pe.com.cotic.test.dao.ReporteUsuariosCursosDao;
import pe.com.cotic.test.dao.ReporteUsuariosCursosPuestosDao;
import pe.com.cotic.test.daoImpl.ReporteUsuariosCursosDaoImpl;
import pe.com.cotic.test.daoImpl.ReporteUsuariosCursosPuestosDaoImpl;
import pe.com.cotic.test.modelo.Reporteusuarioscursos;
import pe.com.cotic.test.modelo.Reporteusuarioscursospuestos;

@ManagedBean
@SessionScoped
public class ReporteUsuariosCursosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Reporteusuarioscursos reporteUsuariosCursos;
	private ReporteUsuariosCursosDao reporteUsuariosCursosDao;
	private List<Reporteusuarioscursos> listarReporteUsuariosCursos;
	private List<Reporteusuarioscursospuestos> listarReporteUsuariosCursosPuestos;
	
	public Reporteusuarioscursos getReporteUsuariosCursos() {
		return reporteUsuariosCursos;
	}
	
	public void setReporteUsuariosCursos(Reporteusuarioscursos reporteUsuariosCursos) {
		this.reporteUsuariosCursos = reporteUsuariosCursos;
	}
	
	public ReporteUsuariosCursosDao getReporteUsuariosCursosDao() {
		return reporteUsuariosCursosDao;
	}
	
	public void setReporteUsuariosCursosDao(
			ReporteUsuariosCursosDao reporteUsuariosCursosDao) {
		this.reporteUsuariosCursosDao = reporteUsuariosCursosDao;
	}
	
	public List<Reporteusuarioscursos> getListarReporteUsuariosCursos() {
		ReporteUsuariosCursosDao reporteUsuariosCursosDao = new ReporteUsuariosCursosDaoImpl();
		this.listarReporteUsuariosCursos = reporteUsuariosCursosDao.listarReporteUsuariosCursos();
		return listarReporteUsuariosCursos;
	}
	
	public void setListarReporteUsuariosCursos(
			List<Reporteusuarioscursos> listarReporteUsuariosCursos) {
		this.listarReporteUsuariosCursos = listarReporteUsuariosCursos;
	}
	
	public List<Reporteusuarioscursospuestos> getListarReporteUsuariosCursosPuestos() {
		
		ReporteUsuariosCursosPuestosDao reporteUsuariosCursosPuestosDao = new ReporteUsuariosCursosPuestosDaoImpl();
		this.listarReporteUsuariosCursosPuestos = reporteUsuariosCursosPuestosDao.listarReporteUsuariosCursosPuestos();
		
		return listarReporteUsuariosCursosPuestos;
	}
	
	public void setListarReporteUsuariosCursosPuestos(
			List<Reporteusuarioscursospuestos> listarReporteUsuariosCursosPuestos) {
		this.listarReporteUsuariosCursosPuestos = listarReporteUsuariosCursosPuestos;
	}
	

}
