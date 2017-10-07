package pe.com.cotic.test.daoImpl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.RespuestaCabeceraDao;
import pe.com.cotic.test.modelo.Respuestacabecera;
import pe.com.cotic.test.util.HibernateUtil;

public class RespuestaCabeceraDaoImpl implements RespuestaCabeceraDao {

	
	private Session session;

	@Override
	public List<Respuestacabecera> ListarRespuestasCabecera() {

		List<Respuestacabecera> listarRespuestasCabecera = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Respuestacabecera ";
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
