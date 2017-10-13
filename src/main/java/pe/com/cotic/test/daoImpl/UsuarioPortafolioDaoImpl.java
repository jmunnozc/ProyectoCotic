package pe.com.cotic.test.daoImpl;

import java.util.List;

import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.UsuarioPortafolioDao;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.modelo.Usuarioportafolio;
import pe.com.cotic.test.util.HibernateUtil;

public class UsuarioPortafolioDaoImpl implements UsuarioPortafolioDao {

	private Session session;
	
	@Override
	public List<Usuarioportafolio> ListarUsuariosPortafolios() {

		List<Usuarioportafolio> listarUsuarioPortafolio = null;
		Usuario usuario = null;
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		//String hql = "FROM Pregunta AS pr INNER JOIN fetch pr.portafolio INNER JOIN fetch pr.alternativas ";
		//String hql = "FROM Pregunta AS pr INNER JOIN fetch pr.portafolio ";
		String hql = "FROM Usuarioportafolio AS up " 
				+	" WHERE up.usuario.institucion.codigoInstitucion = " +  usuario.getInstitucion().getCodigoInstitucion()
				+	" ORDER BY codigoPortafolio ";
		try {
			listarUsuarioPortafolio = session.createQuery(hql).list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		
		return listarUsuarioPortafolio;
	}

	@Override
	public boolean grabarUsuarioPortafolio(Usuarioportafolio usuarioportafolio) {
		boolean flag = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(usuarioportafolio);
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
	public boolean eliminarUsuarioPortafolio(Usuarioportafolio usuarioportafolio) {
		boolean flag = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(usuarioportafolio);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			flag = false;
			transaction.rollback();
		}
		return flag;
	}

}
