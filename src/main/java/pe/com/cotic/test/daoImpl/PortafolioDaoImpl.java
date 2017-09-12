package pe.com.cotic.test.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.PortafolioDao;
import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.util.HibernateUtil;

public class PortafolioDaoImpl implements PortafolioDao {

	private Session session;
	
	@Override
	public List<Portafolio> ListarPortafolios() {
		
		List<Portafolio> listarPortafolios = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Portafolio ";

		try {
			listarPortafolios = session.createQuery(hql).list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}

		return listarPortafolios;
	}

	@Override
	public boolean grabarPortafolio(Portafolio portafolio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificarPortafolio(Portafolio portafolio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarPortafolio(Portafolio portafolio) {
		// TODO Auto-generated method stub
		return false;
	}

}
