package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import pe.com.cotic.test.dao.ResumenRespuestaDao;
import pe.com.cotic.test.modelo.Resumenrespuesta;

@ManagedBean
@SessionScoped
public class ResumenRespuestaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Resumenrespuesta resumenRespuesta;
	private ResumenRespuestaDao resumenRespuestaDao;
	private List<Resumenrespuesta> listarResumenRespuesta;
	private Resumenrespuesta selectedResumenRespuesta;
	
	public Resumenrespuesta getResumenRespuesta() {
		return resumenRespuesta;
	}
	public void setResumenRespuesta(Resumenrespuesta resumenRespuesta) {
		this.resumenRespuesta = resumenRespuesta;
	}
	public ResumenRespuestaDao getResumenRespuestaDao() {
		return resumenRespuestaDao;
	}
	public void setResumenRespuestaDao(ResumenRespuestaDao resumenRespuestaDao) {
		this.resumenRespuestaDao = resumenRespuestaDao;
	}
	public List<Resumenrespuesta> getListarResumenRespuesta() {
		return listarResumenRespuesta;
	}
	public void setListarResumenRespuesta(
			List<Resumenrespuesta> listarResumenRespuesta) {
		this.listarResumenRespuesta = listarResumenRespuesta;
	}
	public Resumenrespuesta getSelectedResumenRespuesta() {
		return selectedResumenRespuesta;
	}
	public void setSelectedResumenRespuesta(
			Resumenrespuesta selectedResumenRespuesta) {
		this.selectedResumenRespuesta = selectedResumenRespuesta;
	}

	
}
