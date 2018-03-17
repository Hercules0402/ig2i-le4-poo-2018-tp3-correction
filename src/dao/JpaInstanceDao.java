package dao;

import javax.persistence.Query;
import metier.Instance;

/**
 * TODO.
 * @author dcattaru
 */
public class JpaInstanceDao extends JpaDao<Instance> implements InstanceDao{

	private static JpaInstanceDao instance;

	private JpaInstanceDao() {
		super(Instance.class);
	}

	protected static JpaInstanceDao getInstance(){
		if (instance == null) {
			instance = new JpaInstanceDao();
		}
		return instance;
	}

	@Override
	public Instance findByName(String name) {
		Instance instance;
		Query query = em.createNamedQuery("Instance.findByNom");
		query.setParameter("nom", name);
		instance = (Instance) query.getSingleResult();
		return instance;
	}

}
