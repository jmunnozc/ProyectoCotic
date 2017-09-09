package pe.com.cotic.test.dao;

import java.util.List;

import pe.com.cotic.test.modelo.Usuario;

public interface UsuarioDao {
	
	public Usuario verificarDatos(Usuario usuario);
	public List<Usuario> ListarUsuarios();
	
	/*private Session session;
	
	public Usuario verificarDatos(Usuario usuario) throws Exception {
		Usuario us = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if (usuario.getUsuario() == null) usuario.setUsuario("");
			if (usuario.getClave() == null) usuario.setClave("");
			
			//hibernate query language
			String hql = "FROM Usuario WHERE usuario = '" + usuario.getUsuario().toUpperCase() + "' and clave = '" + usuario.getClave().toUpperCase() + "'";
			Query query = session.createQuery(hql);
			
			if (!query.list().isEmpty()) {
				us = (Usuario) query.list().get(0);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return us;
		
	}*/

}
