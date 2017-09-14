package pe.com.cotic.test.modelo;

// Generated 14/09/2017 09:19:37 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Usuariodispositivo.
 * @see pe.com.cotic.test.modelo.Usuariodispositivo
 * @author Hibernate Tools
 */
public class UsuariodispositivoHome {

	private static final Log log = LogFactory
			.getLog(UsuariodispositivoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Usuariodispositivo transientInstance) {
		log.debug("persisting Usuariodispositivo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Usuariodispositivo instance) {
		log.debug("attaching dirty Usuariodispositivo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usuariodispositivo instance) {
		log.debug("attaching clean Usuariodispositivo instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Usuariodispositivo persistentInstance) {
		log.debug("deleting Usuariodispositivo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Usuariodispositivo merge(Usuariodispositivo detachedInstance) {
		log.debug("merging Usuariodispositivo instance");
		try {
			Usuariodispositivo result = (Usuariodispositivo) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Usuariodispositivo findById(java.lang.Integer id) {
		log.debug("getting Usuariodispositivo instance with id: " + id);
		try {
			Usuariodispositivo instance = (Usuariodispositivo) sessionFactory
					.getCurrentSession().get(
							"pe.com.cotic.test.modelo.Usuariodispositivo", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Usuariodispositivo instance) {
		log.debug("finding Usuariodispositivo instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"pe.com.cotic.test.modelo.Usuariodispositivo")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
