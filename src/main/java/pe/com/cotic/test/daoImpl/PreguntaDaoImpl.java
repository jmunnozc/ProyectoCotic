package pe.com.cotic.test.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.PreguntaDao;
import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.modelo.Pregunta;
import pe.com.cotic.test.util.HibernateUtil;

public class PreguntaDaoImpl implements PreguntaDao {
	
	private Session session;

	@SuppressWarnings("unchecked")
	@Override
	public List<Pregunta> ListarPreguntas() {

		List<Pregunta> listarPreguntas = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		//String hql = "FROM Pregunta AS pr INNER JOIN fetch pr.portafolio INNER JOIN fetch pr.alternativas ";
		String hql = "FROM Pregunta AS pr INNER JOIN fetch pr.portafolio ";
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
	
	@Override
	public boolean grabarPregunta(Pregunta pregunta) {

		boolean flag = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(pregunta);
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
	public boolean modificarPregunta(Pregunta pregunta) {

		boolean flag = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(pregunta);
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
	public boolean eliminarPregunta(Pregunta pregunta) {

		boolean flag = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(pregunta);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			flag = false;
			transaction.rollback();
		}
		return flag;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Portafolio> ListarPortafolios(Pregunta pregunta) {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Pregunta> buscarPreguntaXCodigo(Pregunta pregunta) {

		List<Pregunta> listarPreguntaXCodigo = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Pregunta AS al WHERE al.pregunta.codigoPregunta = " + pregunta.getCodigoPregunta();
		
		try {
			listarPreguntaXCodigo = session.createQuery(hql).list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		
		return listarPreguntaXCodigo;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pregunta> buscarPreguntasPortafolio(Portafolio portafolio) {

		List<Pregunta> listarPreguntasPortafolio = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Pregunta AS pr WHERE pr.portafolio.codigoPortafolio = " + portafolio.getCodigoPortafolio();
		
		try {
			listarPreguntasPortafolio = session.createQuery(hql).list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		
		return listarPreguntasPortafolio;
	}


}
