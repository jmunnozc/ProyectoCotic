package pe.com.cotic.test.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.UsuarioDao;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.util.HibernateUtil;

public class UsuarioDaoImpl implements UsuarioDao {

	private Session session;

	@Override
	public Usuario verificarDatos(Usuario usuario) {

		Usuario us = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if (usuario.getUsuario() == null)
				usuario.setUsuario("");
			if (usuario.getClave() == null)
				usuario.setClave("");

			// hibernate query language
			String hql = "FROM Usuario WHERE usuario = '"
					+ usuario.getUsuario().toUpperCase() + "' and clave = '"
					+ usuario.getClave().toUpperCase() + "'";
			Query query = session.createQuery(hql);

			if (!query.list().isEmpty()) {
				us = (Usuario) query.list().get(0);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return us;
	}

	@Override
	public List<Usuario> ListarUsuarios() {

		List<Usuario> listarUsuarios = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		// hibernate query language [FROM Usuario as u INNER JOIN FETCH
		// u.usuariocursos LEFT JOIN FETCH u.usuariodispositivos]
		String hql = "FROM Usuario ";

		try {
			listarUsuarios = session.createQuery(hql).list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}

		return listarUsuarios;
	}

	@Override
	public boolean grabarUsuario(Usuario usuario) {
		boolean flag = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(usuario);
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
	public boolean modificarUsuario(Usuario usuario) {
		boolean flag = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(usuario);
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
	public boolean eliminarUsuario(Usuario usuario) {
		boolean flag = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(usuario);
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
