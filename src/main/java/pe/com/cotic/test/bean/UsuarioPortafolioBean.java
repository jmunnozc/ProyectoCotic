package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.modelo.Usuario;


@ManagedBean
@ViewScoped
public class UsuarioPortafolioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Usuario usuario;
	
	private int codigoNivel;
	private int codigoPortafolio;
	private int codigoUsuario;
	private int codigoPortafolioEnlace;
	
	
	public int getCodigoNivel() {
		return codigoNivel;
	}
	public void setCodigoNivel(int codigoNivel) {
		this.codigoNivel = codigoNivel;
	}
	public int getCodigoPortafolio() {
		return codigoPortafolio;
	}
	public void setCodigoPortafolio(int codigoPortafolio) {
		this.codigoPortafolio = codigoPortafolio;
	}
	public int getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public int getCodigoPortafolioEnlace() {
		return codigoPortafolioEnlace;
	}
	public void setCodigoPortafolioEnlace(int codigoPortafolioEnlace) {
		this.codigoPortafolioEnlace = codigoPortafolioEnlace;
	}
	
	public void btnGrabarUsuarioPortafolio(Portafolio portafolio) {
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		
		Logger.getLogger(getClass().getName()).log(Level.INFO,portafolio.getCodigoPortafolio().toString());
		Logger.getLogger(getClass().getName()).log(Level.INFO,usuario.getCodigoUsuario().toString());
	}
}
