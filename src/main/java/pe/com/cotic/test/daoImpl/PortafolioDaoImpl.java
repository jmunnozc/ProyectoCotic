package pe.com.cotic.test.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.PortafolioDao;
import pe.com.cotic.test.modelo.Nivel;
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
		
		boolean flag = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(portafolio);
			transaction.commit();
			session.close();
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			flag = false;
			transaction.rollback();
		}
		return flag;
		
	}

	@Override
	public boolean modificarPortafolio(Portafolio portafolio) {

		boolean flag = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(portafolio);
			transaction.commit();
			session.close();
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			flag = false;
			transaction.rollback();
		}
		return flag;
		
	}

	@Override
	public boolean eliminarPortafolio(Portafolio portafolio) {

		boolean flag = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(portafolio);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			flag = false;
			transaction.rollback();
		}
		return flag;
		
	}

	@Override
	public List<Nivel> ListarNiveles(Portafolio portafolio) {

		List<Nivel> listarNiveles = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		//String hql = "FROM Niveles WHERE codigoNivel = '" + portafolio.getNivel().getCodigoNivel() + "'";
		String hql = "FROM Nivel ";

		try {
			listarNiveles = session.createQuery(hql).list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		
		return listarNiveles;
	}

}
