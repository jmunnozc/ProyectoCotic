package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pe.com.cotic.test.modelo.Respuestacabecera;

@ManagedBean
@ViewScoped
public class RespuestaCabeceraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Respuestacabecera respuestacabecera;
	private List<Respuestacabecera> listarRespuestas;
	private Respuestacabecera selecRespuestacabecera;
	
	public RespuestaCabeceraBean() {
		if (this.respuestacabecera == null) {
			this.respuestacabecera = new Respuestacabecera();
		}
	}
	
	public List<Respuestacabecera> getListarRespuestas() {
		return listarRespuestas;
	}
	public void setListarRespuestas(List<Respuestacabecera> listarRespuestas) {
		this.listarRespuestas = listarRespuestas;
	}
	public Respuestacabecera getSelecRespuestacabecera() {
		return selecRespuestacabecera;
	}
	public void setSelecRespuestacabecera(Respuestacabecera selecRespuestacabecera) {
		this.selecRespuestacabecera = selecRespuestacabecera;
	}
	
	
	
}
