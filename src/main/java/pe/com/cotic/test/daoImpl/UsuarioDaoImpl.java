package pe.com.cotic.test.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pe.com.cotic.test.dao.UsuarioDao;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.util.HibernateUtil;

public class UsuarioDaoImpl implements UsuarioDao {

	private Session session;

	@Override
	public Usuario verificarDatos(Usuario usuario) {
		// TODO Auto-generated method stub
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
			throw e;
		}

		return us;
	}

	@Override
	public List<Usuario> ListarUsuarios() {
		// TODO Auto-generated method stub
		List<Usuario> listarUsuarios = null;
		session = HibernateUtil.getSessionFactory().openSession();
		// hibernate query language
		String hql = "FROM Usuario ";
		try {
			session.beginTransaction();
			listarUsuarios = session.createQuery(hql).list();
			session.beginTransaction().commit();
			
		} catch (Exception e) {
			session.beginTransaction().rollback();
		}

		return listarUsuarios;
	}

}
