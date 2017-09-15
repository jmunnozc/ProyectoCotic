package pe.com.cotic.test.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.AlternativaDao;
import pe.com.cotic.test.modelo.Alternativa;
import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.modelo.Pregunta;
import pe.com.cotic.test.util.HibernateUtil;

public class AlternativaDaoImpl implements AlternativaDao {
	
	private Session session;

	@Override
	public List<Alternativa> ListarAlternativas() {

		List<Alternativa> listarAlternativas = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Alternativa AS al INNER JOIN FETCH al.pregunta ORDER BY al.codigoAlternativa";
		try {
			listarAlternativas = session.createQuery(hql).list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		
		return listarAlternativas;
	}

	@Override
	public boolean grabarAlternativa(Alternativa alternativa) {

		boolean flag = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(alternativa);
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
	public boolean modificarAlternativa(Alternativa alternativa) {

		boolean flag = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(alternativa);
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
	public boolean eliminarAlternativa(Alternativa alternativa) {

		boolean flag = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(alternativa);
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
	public List<Pregunta> ListarPreguntas(Alternativa alternativa) {

		List<Pregunta> listarPreguntas = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Pregunta ";

		try {
			listarPreguntas = session.createQuery(hql).list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		
		return listarPreguntas;
		
	}

}
