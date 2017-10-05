package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import pe.com.cotic.test.dao.PortafolioDao;
import pe.com.cotic.test.daoImpl.PortafolioDaoImpl;
import pe.com.cotic.test.modelo.Portafolio;

@ManagedBean
@ViewScoped
public class GestionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Portafolio portafolio;
	private PortafolioDao portafolioDao;
	private List<SelectItem> listarPortafolio;
	
	public GestionBean() {
		this.portafolio = new Portafolio();
		this.portafolioDao = new PortafolioDaoImpl();
	}
	
	public Portafolio getPortafolio() {
		return portafolio;
	}
	public void setPortafolio(Portafolio portafolio) {
		this.portafolio = portafolio;
	}
	public PortafolioDao getPortafolioDao() {
		return portafolioDao;
	}
	public void setPortafolioDao(PortafolioDao portafolioDao) {
		this.portafolioDao = portafolioDao;
	}
	public List<SelectItem> getListarPortafolio() {
		
		this.listarPortafolio = new ArrayList<SelectItem>();
		PortafolioDao portafolioDao = new PortafolioDaoImpl();
		List<Portafolio> n = portafolioDao.ListarPortafoliosxNivel(3);
		listarPortafolio.clear();
		
		for (Portafolio portafolio : n){
			SelectItem portafolioItem = new SelectItem(portafolio.getCodigoPortafolio(), portafolio.getTituloPortafolio());
			this.listarPortafolio.add(portafolioItem);
		}		
		return listarPortafolio;
		
	}
	
	public void setListarPortafolio(List<SelectItem> listarPortafolio) {
		this.listarPortafolio = listarPortafolio;
	}
	

}
