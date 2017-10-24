package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import pe.com.cotic.test.dao.RespuestaDetalleDao;
import pe.com.cotic.test.dao.ResumenRespuestaDao;
import pe.com.cotic.test.daoImpl.RespuestaDetalleDaoImpl;
import pe.com.cotic.test.daoImpl.ResumenRespuestaDaoImpl;
import pe.com.cotic.test.modelo.Respuestacabecera;
import pe.com.cotic.test.modelo.Respuestadetalle;
import pe.com.cotic.test.modelo.Resumenrespuesta;

@ManagedBean
//@Named("respuestadetalleBean")
@ViewScoped
public class RespuestaDetalleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Respuestadetalle respuestadetalle;
	private List<Respuestadetalle> listarRespuestasDetalle;
	private Respuestadetalle selecRespuestadetalle;
	
	private PieChartModel model;
	
	public RespuestaDetalleBean() {
		if (this.respuestadetalle == null) {
			this.respuestadetalle = new Respuestadetalle();
		}
	}	

	public void listar(Respuestacabecera respuestacabecera) {
		List<Resumenrespuesta> lista;		
		try {
			ResumenRespuestaDao resumenRespuestaDao = new ResumenRespuestaDaoImpl();
			lista = resumenRespuestaDao.listarResumenRespuesta(respuestacabecera);
			graficar(lista);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			
		}		
	}
	
	private void graficar(List<Resumenrespuesta> lista) {
		model = new PieChartModel();		
		for (Resumenrespuesta rr : lista) {
			model.set(rr.getRespuestaCorrecta().toString(), Integer.parseInt(rr.getTotal()));
		}
		/*model.setTitle("Preguntas");
		model.setLegendPosition("e");*/
		model.setFill(false);
		model.setShowDataLabels(true);
		model.setDiameter(150);
		
	}
	
	/*private void graficar() {
		model = new PieChartModel();
		model.set("Correctas", 2);
		model.set("Incorrectas", 4);
		model.setTitle("Respuestas Realizadas");
		model.setLegendPosition("n");
	}*/
	
	public PieChartModel getModel() {

		return model;
	}
	
	public Respuestadetalle getRespuestadetalle() {
		return respuestadetalle;
	}

	public void setRespuestadetalle(Respuestadetalle respuestadetalle) {
		this.respuestadetalle = respuestadetalle;
	}

	public List<Respuestadetalle> getListarRespuestasDetalle(Respuestacabecera respuestacabecera) {
		
		RespuestaDetalleDao respuestadetalleDao = new RespuestaDetalleDaoImpl();
		this.listarRespuestasDetalle = respuestadetalleDao.ListarRespuestasDetalle(respuestacabecera.getCodigoRespuestaCabecera());
		
		return listarRespuestasDetalle;
	}

	public void setListarRespuestasDetalle(List<Respuestadetalle> listarRespuestasDetalle) {
		this.listarRespuestasDetalle = listarRespuestasDetalle;
	}

	public Respuestadetalle getSelecRespuestadetalle() {
		return selecRespuestadetalle;
	}

	public void setSelecRespuestadetalle(Respuestadetalle selecRespuestadetalle) {
		this.selecRespuestadetalle = selecRespuestadetalle;
	}
	
	public List<Respuestadetalle> btnBuscarRespuestaDetalle(Respuestacabecera respuestacabecera) {
		RespuestaDetalleDao respuestadetalleDao = new RespuestaDetalleDaoImpl();
		String msg;
		
		this.listarRespuestasDetalle = respuestadetalleDao.ListarRespuestasDetalle(respuestacabecera.getCodigoRespuestaCabecera());
		
		if (this.listarRespuestasDetalle != null) {
			msg = "Se muestra correctamente el listado...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			listar(respuestacabecera);
		} else {
			msg = "Error al mostrar el listado...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return listarRespuestasDetalle;
		
	}

}
