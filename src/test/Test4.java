package test;

import dao.DaoFactory;
import dao.InstanceDao;
import dao.PersistenceType;
import metier.Instance;

/**
 * TODO.
 * @author Admin
 */
public class Test4 {
	
	/**
	 * TODO.
	 * @param args TODO.
	 */
	public static void main(String[] args) {
		DaoFactory fabrique = DaoFactory.getDaoFactory(PersistenceType.JPA);
		InstanceDao instanceManager = fabrique.getInstanceDao();
		Instance inst = instanceManager.findByName("tiny_test");
		System.out.println(inst);
	}
}
