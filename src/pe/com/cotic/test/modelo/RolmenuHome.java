package pe.com.cotic.test.modelo;

// Generated 07/09/2017 11:40:56 PM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Rolmenu.
 * @see pe.com.cotic.test.modelo.Rolmenu
 * @author Hibernate Tools
 */
public class RolmenuHome {

	private static final Log log = LogFactory.getLog(RolmenuHome.class);

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

	public void persist(Rolmenu transientInstance) {
		log.debug("persisting Rolmenu instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Rolmenu instance) {
		log.debug("attaching dirty Rolmenu instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Rolmenu instance) {
		log.debug("attaching clean Rolmenu instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Rolmenu persistentInstance) {
		log.debug("deleting Rolmenu instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Rolmenu merge(Rolmenu detachedInstance) {
		log.debug("merging Rolmenu instance");
		try {
			Rolmenu result = (Rolmenu) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Rolmenu findById(int id) {
		log.debug("getting Rolmenu instance with id: " + id);
		try {
			Rolmenu instance = (Rolmenu) sessionFactory.getCurrentSession()
					.get("pe.com.cotic.test.modelo.Rolmenu", id);
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

	public List findByExample(Rolmenu instance) {
		log.debug("finding Rolmenu instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("pe.com.cotic.test.modelo.Rolmenu")
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
