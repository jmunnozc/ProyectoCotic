package pe.com.cotic.test.daoImpl;

import java.util.List;

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
		String hql = "FROM Respuestacabecera ";

		try {
			listarRespuestasCabecera = session.createQuery(hql).list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}

		return listarRespuestasCabecera;
		
	}
	

}
