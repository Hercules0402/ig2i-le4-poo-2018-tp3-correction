package dao;


import metier.Instance;

/**
 * TODO.
 * @author dcattaru
 */
public interface InstanceDao extends Dao<Instance> {
	public Instance findByName(String name);
}
