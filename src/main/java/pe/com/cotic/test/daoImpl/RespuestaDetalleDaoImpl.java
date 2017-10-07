package pe.com.cotic.test.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.RespuestaDetalleDao;
import pe.com.cotic.test.modelo.Respuestadetalle;
import pe.com.cotic.test.util.HibernateUtil;

public class RespuestaDetalleDaoImpl implements RespuestaDetalleDao{
	
	private Session session;

	@Override
	public List<Respuestadetalle> ListarRespuestasDetalle(int rc) {

		List<Respuestadetalle> listarRespuestasDetalle = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Respuestadetalle WHERE codigoRespuestacabecera = " + rc;

		try {
			listarRespuestasDetalle = session.createQuery(hql).list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}

		return listarRespuestasDetalle;
	}

}
