package pe.com.cotic.test.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.ReporteUsuariosCursosDao;
import pe.com.cotic.test.modelo.Reporteusuarioscursos;
import pe.com.cotic.test.util.HibernateUtil;

public class ReporteUsuariosCursosDaoImpl implements ReporteUsuariosCursosDao {

	private Session session;
	
	@Override
	public List<Reporteusuarioscursos> listarReporteUsuariosCursos() {

		List<Reporteusuarioscursos> listarReporteUsuariosCursos = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Reporteusuarioscursos ";
		try {
			listarReporteUsuariosCursos = session.createQuery(hql).list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		
		return listarReporteUsuariosCursos;
		
	}

}
