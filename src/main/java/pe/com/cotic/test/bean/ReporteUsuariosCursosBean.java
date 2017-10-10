package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import pe.com.cotic.test.dao.ReporteUsuariosCursosDao;
import pe.com.cotic.test.daoImpl.ReporteUsuariosCursosDaoImpl;
import pe.com.cotic.test.modelo.Reporteusuarioscursos;

@ManagedBean
@SessionScoped
public class ReporteUsuariosCursosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Reporteusuarioscursos reporteUsuariosCursos;
	private ReporteUsuariosCursosDao reporteUsuariosCursosDao;
	private List<Reporteusuarioscursos> listarReporteUsuariosCursos;
	
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
	
	

}
