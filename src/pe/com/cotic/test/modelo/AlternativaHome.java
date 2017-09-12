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
 * Home object for domain model class Alternativa.
 * @see pe.com.cotic.test.modelo.Alternativa
 * @author Hibernate Tools
 */
public class AlternativaHome {

	private static final Log log = LogFactory.getLog(AlternativaHome.class);

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

	public void persist(Alternativa transientInstance) {
		log.debug("persisting Alternativa instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Alternativa instance) {
		log.debug("attaching dirty Alternativa instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Alternativa instance) {
		log.debug("attaching clean Alternativa instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Alternativa persistentInstance) {
		log.debug("deleting Alternativa instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Alternativa merge(Alternativa detachedInstance) {
		log.debug("merging Alternativa instance");
		try {
			Alternativa result = (Alternativa) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Alternativa findById(java.lang.Integer id) {
		log.debug("getting Alternativa instance with id: " + id);
		try {
			Alternativa instance = (Alternativa) sessionFactory
					.getCurrentSession().get(
							"pe.com.cotic.test.modelo.Alternativa", id);
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

	public List findByExample(Alternativa instance) {
		log.debug("finding Alternativa instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("pe.com.cotic.test.modelo.Alternativa")
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
