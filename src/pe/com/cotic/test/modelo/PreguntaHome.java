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
 * Home object for domain model class Pregunta.
 * @see pe.com.cotic.test.modelo.Pregunta
 * @author Hibernate Tools
 */
public class PreguntaHome {

	private static final Log log = LogFactory.getLog(PreguntaHome.class);

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

	public void persist(Pregunta transientInstance) {
		log.debug("persisting Pregunta instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Pregunta instance) {
		log.debug("attaching dirty Pregunta instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Pregunta instance) {
		log.debug("attaching clean Pregunta instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Pregunta persistentInstance) {
		log.debug("deleting Pregunta instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Pregunta merge(Pregunta detachedInstance) {
		log.debug("merging Pregunta instance");
		try {
			Pregunta result = (Pregunta) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Pregunta findById(java.lang.Integer id) {
		log.debug("getting Pregunta instance with id: " + id);
		try {
			Pregunta instance = (Pregunta) sessionFactory.getCurrentSession()
					.get("pe.com.cotic.test.modelo.Pregunta", id);
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

	public List findByExample(Pregunta instance) {
		log.debug("finding Pregunta instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("pe.com.cotic.test.modelo.Pregunta")
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
