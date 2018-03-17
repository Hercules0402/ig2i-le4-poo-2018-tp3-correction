package dao;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * TODO.
 * @author dcattaru
 */
public abstract class JpaDao<T> implements Dao<T> {
	protected static EntityManager em;
	//CHECKSTYLE:OFF: MemberNameCheck
	private final String PUname = "routagePU";
	//CHECKSTYLE:ON
	private final Class<T> entityClass;

	/**
	 * TODO.
	 * @param entityClass 
	 */
	public JpaDao(Class<T> entityClass) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PUname);
		em = emf.createEntityManager();
		this.entityClass = entityClass;
	}

	@Override
	public boolean create(T obj) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(obj);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(T obj) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(obj);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(T obj) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.remove(obj);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			return false;
		}
		return true;
	}

	@Override
	public void close() {
		if (em != null) {
			em.close();
		}
	}

	@Override
	public T find(Integer id) {
		return em.find(entityClass, id);
	}

	@Override
	public Collection<T> findAll() {
		CriteriaQuery q = em.getCriteriaBuilder().createQuery(entityClass);
		Root<T> all = q.from(entityClass);
		q.select(all);
		return em.createQuery(q).getResultList();
	}

	@Override
	public boolean deleteAll() {
		EntityTransaction et = em.getTransaction();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaDelete<T> cd = cb.createCriteriaDelete(entityClass);
			et.begin();
			em.createQuery(cd).executeUpdate();
			et.commit();
		} catch(Exception e) {
			et.rollback();
			return false;
		}
		return true;
	}
}
