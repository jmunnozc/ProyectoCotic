package pe.com.cotic.test.daoImpl;

import java.util.List;

import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.PortafolioDao;
import pe.com.cotic.test.modelo.Nivel;
import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.util.HibernateUtil;

public class PortafolioDaoImpl implements PortafolioDao {

	private Session session;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Portafolio> ListarPortafolios() {
		
		List<Portafolio> listarPortafolios = null;
		Usuario usuario = null;
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Portafolio AS p INNER JOIN FETCH p.nivel " 
				+	" WHERE p.usuario.institucion.codigoInstitucion = "
				+	usuario.getInstitucion().getCodigoInstitucion()
				+	" ORDER BY codigoPortafolio ";
		//FROM Portafolio as p JOIN p.Nivel
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Nivel> ListarNiveles(Portafolio portafolio) {

		List<Nivel> listarNiveles = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		//String hql = "FROM Niveles WHERE codigoNivel = '" + portafolio.getNivel().getCodigoNivel() + "'";
		String hql = "FROM Nivel ORDER BY 1";

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Portafolio> ListarPortafoliosxNivel(int nivel) {

		//Portafolio por = null;
		List<Portafolio> listarPortafolios = null;
		Usuario usuario = null;
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		//String hql = "FROM Portafolio WHERE codigoNivel = " + nivel;		
		String hql = "FROM Portafolio AS p "
				+	" WHERE p.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion();  
		
		try {
			listarPortafolios = session.createQuery(hql).list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		
		
/*		try {
			Query callStoredProcedure_MYSQL = session.createSQLQuery("CALL listarPortafolioxNivel (:param1)").addEntity(Portafolio.class);
			callStoredProcedure_MYSQL.setInteger("param1", nivel);
			transaction.commit();
			
			if (!callStoredProcedure_MYSQL.list().isEmpty()) {
				por = (Portafolio) callStoredProcedure_MYSQL.list().get(0);
			}		
			session.close();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}*/
	
		return listarPortafolios;
	}

	@Override
	public int ultimoPortafolio() {
		int portafolio = 0;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "SELECT MAX(p.codigoPortafolio) as totalPortafolio, 'total' as total "
				+	" FROM Portafolio p ";
		try {
			Query query = session.createQuery(hql);			
			if (!query.list().isEmpty()) {			
				List<Object[]> res = query.list();
				for (Object[] elements: res){
					portafolio = Integer.parseInt(elements[0].toString());
				}
			}
			session.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return portafolio;
	}

}
