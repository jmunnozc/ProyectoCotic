package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import pe.com.cotic.test.dao.RespuestaDetalleDao;
import pe.com.cotic.test.dao.ResumenRespuestaDao;
import pe.com.cotic.test.daoImpl.RespuestaDetalleDaoImpl;
import pe.com.cotic.test.daoImpl.ResumenRespuestaDaoImpl;
import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.modelo.Respuestacabecera;
import pe.com.cotic.test.modelo.Respuestadetalle;
import pe.com.cotic.test.modelo.Resumenrespuesta;

@ManagedBean
@ViewScoped
public class RespuestaDetalleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Respuestadetalle respuestadetalle;
	private List<Respuestadetalle> listarRespuestasDetalle;
	private Respuestadetalle selecRespuestadetalle;
	
	private List<Resumenrespuesta> listarResumenRespuesta = null;
	private ChartSeries resumenRespuestaData;
	private BarChartModel barModel;
	
	public RespuestaDetalleBean() {
		if (this.respuestadetalle == null) {
			this.respuestadetalle = new Respuestadetalle();
		}
	}	
	
	public List<Resumenrespuesta> getListarResumenRespuesta() {
		ResumenRespuestaDao resumenrespuestaDao = new ResumenRespuestaDaoImpl();
		this.listarResumenRespuesta = resumenrespuestaDao.listarResumenRespuesta();
		createBarModel();
		return listarResumenRespuesta;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();
		
		resumenRespuestaData = new ChartSeries();
		resumenRespuestaData.setLabel("Respuestas");
		/*for (int i = 0; i < listarResumenRespuesta.size(); i++) {
			System.out.println(String.valueOf(listarResumenRespuesta.get(i)));
			Resumenrespuesta rr = listarResumenRespuesta.get(i);
			resumenRespuestaData.set(rr.getFlagAlternativaCorrecta(), rr.getTotal());
		}*/
		
		for (int i = 0; i < listarResumenRespuesta.size(); i++) {
	        System.out.println( String.valueOf(listarResumenRespuesta.get(i)) ); //Imprime tipo
	        Resumenrespuesta tu = listarResumenRespuesta.get(i);
	        System.out.println(tu.getCodigoPortafolio().toString());
	        System.out.println(tu.getCodigoUsuario().toString());
	        System.out.println(tu.getFlagAlternativaCorrecta().toString());
	        System.out.println(tu.getTotal().toString());
	    }
		
/*		for (Resumenrespuesta cData : listarResumenRespuesta) {
			resumenRespuestaData.set(cData.getFlagAlternativaCorrecta().toString(), cData.getTotal());
		}*/
		
		
		
		model.addSeries(resumenRespuestaData);
		
		return model;
	}
	
	private void createBarModel() {
		barModel = initBarModel();
		
		barModel.setTitle("Respuestas Contestadas");
		barModel.setLegendPosition("ne");
		
		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Respuestas");
		
		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Total");
		yAxis.setMin(0);
		yAxis.setMax(75);
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

	public void setListarRespuestasDetalle(
			List<Respuestadetalle> listarRespuestasDetalle) {
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
		} else {
			msg = "Error al mostrar el listado...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return listarRespuestasDetalle;
		
	}

}
