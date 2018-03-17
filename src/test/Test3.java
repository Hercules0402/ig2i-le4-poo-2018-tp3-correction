package test;

import dao.DaoFactory;
import dao.PersistenceType;
import dao.PlanningDao;
import dao.VehiculeDao;
import metier.Planning;
import metier.Vehicule;

/**
 * TODO.
 * @author dcattaru
 */
public class Test3 {

	/**
	 * TODO.
	 * @param args TODO.
	 */
	public static void main(String[] args) {
		DaoFactory fabrique = DaoFactory.getDaoFactory(PersistenceType.JPA);
		Planning p = new Planning();
		Vehicule v1 = new Vehicule();
		Vehicule v2 = new Vehicule();
		PlanningDao planningManager = fabrique.getPlanningDao();
		VehiculeDao vehiculeManager = fabrique.getVehiculeDao();
		planningManager.deleteAll();
		vehiculeManager.deleteAll();
		vehiculeManager.create(v1);
		vehiculeManager.create(v2);
		if (p.addVehicule(v1)) {
			System.out.println("added");
		}
		if (p.addVehicule(v2)) {
			System.out.println("added");
		}
		planningManager.create(p);
		for (Vehicule v : vehiculeManager.findAll()) {
			System.out.println(v);
		}
	}
}
