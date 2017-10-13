package pe.com.cotic.test.daoImpl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.RespuestaCabeceraDao;
import pe.com.cotic.test.modelo.Respuestacabecera;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.util.HibernateUtil;

public class RespuestaCabeceraDaoImpl implements RespuestaCabeceraDao {

	
	private Session session;

	@Override
	public List<Respuestacabecera> ListarRespuestasCabecera() {

		List<Respuestacabecera> listarRespuestasCabecera = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "";
		Usuario usuario = null;
		
		String administrador = System.getProperty("usuario_administrador") != null ? System.getProperty("usuario_administrador") : "";
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		
		if (administrador.equals(usuario.getCorreo().toUpperCase().trim()) ) {
			//hql = "select rc.usuario.codigoUsuario, rc.fechaRespuesta, rc.portafolio.tituloPortafolio, rc.usuario.usuario "
					hql = "FROM Respuestacabecera AS rc WHERE rc.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion();
		} else {
			//hql = "select rc.usuario.codigoUsuario, rc.fechaRespuesta, rc.portafolio.tituloPortafolio, rc.usuario.usuario "
			hql = "FROM Respuestacabecera AS rc WHERE rc.usuario.codigoUsuario = " + usuario.getCodigoUsuario() + " AND rc.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion();
		}
		Query query = session.createQuery(hql);
		//query.setInteger("codigoUsuario", usuario.getCodigoUsuario());
		
		
		try {
			Logger.getLogger(getClass().getName()).log(Level.INFO,"LANZANDO QUERY: " + hql);
			listarRespuestasCabecera = session.createQuery(hql).list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.INFO,"ERROR LANZANDO QUERY: " + hql);
			Logger.getLogger(getClass().getName()).log(Level.INFO,"EL ERROR ES: " + e.getMessage());
			transaction.rollback();
		}

		return listarRespuestasCabecera;		
	}
	

}
