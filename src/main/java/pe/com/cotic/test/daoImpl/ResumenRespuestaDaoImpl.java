package pe.com.cotic.test.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.ResumenRespuestaDao;
import pe.com.cotic.test.modelo.Resumenrespuesta;
import pe.com.cotic.test.util.HibernateUtil;

public class ResumenRespuestaDaoImpl implements ResumenRespuestaDao{

	private Session session;
	
	@Override
	public List<Resumenrespuesta> listarResumenRespuesta() {
		List<Resumenrespuesta> listarRespuestas = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "SELECT rc.portafolio.codigoPortafolio, rc.usuario.codigoUsuario, rd.flagAlternativaCorrecta, count(rd.flagAlternativaCorrecta) "
					+ " FROM Respuestacabecera AS rc INNER JOIN rc.respuestadetalles AS rd "
					+ " WHERE rc.codigoRespuestaCabecera = 8 "
					+ " GROUP BY rc.portafolio.codigoPortafolio, rc.usuario.codigoUsuario, rd.flagAlternativaCorrecta";

		try {
			listarRespuestas = session.createQuery(hql).list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}

		return listarRespuestas;
	}

}
