package pe.com.cotic.test.modelo;

// Generated 12/09/2017 12:25:55 PM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Usuarioportafolio.
 * @see pe.com.cotic.test.modelo.Usuarioportafolio
 * @author Hibernate Tools
 */
public class UsuarioportafolioHome {

	private static final Log log = LogFactory
			.getLog(UsuarioportafolioHome.class);

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

	public void persist(Usuarioportafolio transientInstance) {
		log.debug("persisting Usuarioportafolio instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Usuarioportafolio instance) {
		log.debug("attaching dirty Usuarioportafolio instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usuarioportafolio instance) {
		log.debug("attaching clean Usuarioportafolio instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Usuarioportafolio persistentInstance) {
		log.debug("deleting Usuarioportafolio instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Usuarioportafolio merge(Usuarioportafolio detachedInstance) {
		log.debug("merging Usuarioportafolio instance");
		try {
			Usuarioportafolio result = (Usuarioportafolio) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Usuarioportafolio findById(java.lang.Integer id) {
		log.debug("getting Usuarioportafolio instance with id: " + id);
		try {
			Usuarioportafolio instance = (Usuarioportafolio) sessionFactory
					.getCurrentSession().get(
							"pe.com.cotic.test.modelo.Usuarioportafolio", id);
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

	public List findByExample(Usuarioportafolio instance) {
		log.debug("finding Usuarioportafolio instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"pe.com.cotic.test.modelo.Usuarioportafolio")
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
