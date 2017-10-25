package pe.com.cotic.test.daoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.UsuarioDao;
import pe.com.cotic.test.modelo.Institucion;
import pe.com.cotic.test.modelo.Reportecurso;
import pe.com.cotic.test.modelo.Rol;
import pe.com.cotic.test.modelo.Rolusuario;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.util.HibernateUtil;
import pe.com.cotic.test.util.Seguridad;

public class UsuarioDaoImpl implements UsuarioDao {

	private Session session;

	@Override
	public Usuario verificarDatos(Usuario usuario) {

		Usuario us = null;
		List<Usuario> listarUsuarios = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			if (usuario.getUsuario() == null) usuario.setUsuario("");
			if (usuario.getClave() == null) usuario.setClave("");			
			
			// hibernate query language
			String hql = "SELECT u, rr.rol.descripcionRol "
					+	" FROM Usuario u " 
					+	" 	INNER JOIN FETCH u.institucion i "
					+	" 	INNER JOIN FETCH u.rolusuarios rr " 	  
					+	" WHERE correo=:user AND clave=:pass";							
			//String hql = "FROM Usuario WHERE correo=:user AND clave=:pass";			
			Query query = session.createQuery(hql);
			query.setString("user", usuario.getUsuario().toUpperCase());
			query.setString("pass", Seguridad.fn_sEncrypting("PASSCANGA", usuario.getClave().toUpperCase()));
			
			if (!query.list().isEmpty()) {
				//us = (Usuario) query.list().get(0);				
				List<Object[]> res = query.list();
				for (Object[] elements: res){
					us = (Usuario) elements[0];
					if (elements[1] != null) {
						us.setPerfil(elements[1].toString().trim().toUpperCase());
					}					
				}			
				session.close();				
			}
			
			/*Query query1 = session.createSQLQuery(
					"CALL autenticarUsuario(:param1,:param2)")
					.addEntity(Usuario.class)
					.setParameter("param1", usuario.getUsuario().toUpperCase())
					.setParameter("param2", Seguridad.fn_sEncrypting("PASSCANGA", usuario.getClave().toUpperCase()));
			
			
			if (!query1.list().isEmpty()) {
				us = (Usuario) query1.list().get(0);
			}*/
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return us;
	}

	@Override
	public Usuario verificarDatosSP(Usuario usuario) {
		
		Usuario us = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Query callStoredProcedure_MYSQL = session.createSQLQuery("CALL autenticarUsuario (:param1, :param2)").addEntity(Usuario.class);
			callStoredProcedure_MYSQL.setString("param1", usuario.getUsuario().toUpperCase());
			callStoredProcedure_MYSQL.setString("param2", usuario.getClave().toUpperCase());
			transaction.commit();
			
			if (!callStoredProcedure_MYSQL.list().isEmpty()) {
				us = (Usuario) callStoredProcedure_MYSQL.list().get(0);
			}		
			session.close();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		return us;
	}
	
	@Override
	public List<Usuario> ListarUsuarios() {

		/*List<Usuario> listarUsuarios = null;
		Usuario usuario = null;
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Usuario AS usu INNER JOIN FETCH usu.institucion AS ins "
				+	" WHERE usu.estado = 1 AND ins.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion(); 
				
		try {
			listarUsuarios = session.createQuery(hql).list();
						
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}*/

		
/* ************************************************** */
		List<Usuario> listarUsuarios1 = null;
		Usuario usuario = null;
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction1 = session.beginTransaction();
		String hql1 = "SELECT u, rr.rol.descripcionRol "
				+	" FROM Usuario u " 
				+	" 	INNER JOIN FETCH u.institucion i "
				+	" 	INNER JOIN FETCH u.rolusuarios rr " 	  
				+	" WHERE u.estado = 1 AND i.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion();		
		
		try {
			
			Query query = session.createQuery(hql1);
			List<Object[]> res = query.list();

			listarUsuarios1 = new ArrayList<Usuario>();
			for (Object[] elements: res){
				Usuario usu = new Usuario();
				usu = (Usuario) elements[0];
				if (elements[1] != null) {
					usu.setPerfil(elements[1].toString().trim().toUpperCase());
				}
				listarUsuarios1.add(usu);
			}
			
			transaction1.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction1.rollback();
		}
		
		
		return listarUsuarios1;
	}

	@Override
	public boolean grabarUsuario(Usuario usuario) {
		boolean flag = false;		
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			usuario.setClave(Seguridad.fn_sEncrypting("PASSCANGA", usuario.getClave().toUpperCase()));
			
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

	@Override
	public List<Institucion> ListarInstituciones(Usuario usuario) {

		List<Institucion> listarInstituciones = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Institucion ";

		try {
			listarInstituciones = session.createQuery(hql).list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		
		return listarInstituciones;
	}

	@Override
	public List<Rol> ListarPerfil(Usuario usuario) {
		List<Rol> listarRol = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Rol ";

		try {
			listarRol = session.createQuery(hql).list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		
		return listarRol;
	}

	@Override
	public boolean modificarPerfilUsuario(Rolusuario rolusuario) {
		boolean flag = false;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(rolusuario);
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
	public int buscarCodigoRolusuario(Usuario usuario) {

		int codigoRolUsuario = 0;
		List<Rolusuario> listarRolusuario = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Rolusuario ru "
				+	" WHERE ru.usuario.codigoUsuario = " + usuario.getCodigoUsuario(); 
				
		try {
			listarRolusuario = session.createQuery(hql).list();

			codigoRolUsuario = listarRolusuario.get(0).getCodigoRolUsuario();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		return codigoRolUsuario;
	}

}
