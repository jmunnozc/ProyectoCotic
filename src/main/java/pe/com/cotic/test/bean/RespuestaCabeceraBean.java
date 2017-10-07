package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import pe.com.cotic.test.dao.RespuestaCabeceraDao;
import pe.com.cotic.test.daoImpl.RespuestaCabeceraDaoImpl;
import pe.com.cotic.test.modelo.Respuestacabecera;

@ManagedBean
@ViewScoped
public class RespuestaCabeceraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Respuestacabecera respuestacabecera;
	private List<Respuestacabecera> listarRespuestasCabecera;	
	private Respuestacabecera selecRespuestacabecera;

	/* Para crear la gráfica */
	//private PieChartModel pieModel;	

	public RespuestaCabeceraBean() {
		if (this.respuestacabecera == null) {
			this.respuestacabecera = new Respuestacabecera();
		}
	}
	
	
	public List<Respuestacabecera> getListarRespuestasCabecera() {
		
		RespuestaCabeceraDao respuestacabeceraDao = new RespuestaCabeceraDaoImpl();
		this.listarRespuestasCabecera = respuestacabeceraDao.ListarRespuestasCabecera();
		//graficar(this.listarRespuestasCabecera);
		return listarRespuestasCabecera;
	}

	public void setListarRespuestasCabecera(
			List<Respuestacabecera> listarRespuestasCabecera) {		
		this.listarRespuestasCabecera = listarRespuestasCabecera;
	}
	
	public Respuestacabecera getSelecRespuestacabecera() {
		return selecRespuestacabecera;
	}
	
	public void setSelecRespuestacabecera(Respuestacabecera selecRespuestacabecera) {
		this.selecRespuestacabecera = selecRespuestacabecera;
	}
	
}
