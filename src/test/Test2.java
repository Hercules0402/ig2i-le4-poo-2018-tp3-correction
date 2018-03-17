package test;

import dao.ClientDao;
import dao.DaoFactory;
import dao.DepotDao;
import dao.PersistenceType;
import dao.PlanningDao;
import dao.VehiculeDao;
import metier.Client;
import metier.Depot;
import metier.Planning;
import metier.Vehicule;

/**
 * TODO.
 * @author dcattaru
 */
public class Test2 {

	/**
	 * TODO.
	 * @param args 
	 */
	public static void main(String[] args) {
		DaoFactory fabrique = DaoFactory.getDaoFactory(PersistenceType.JPA);
		Depot d = new Depot(0, 0);
		DepotDao depotManager = fabrique.getDepotDao();
		depotManager.deleteAll();
		depotManager.create(d);

		Client c1 = new Client(10, 10, 10);
		Client c2 = new Client(-10, 10, 5);
		Client c3 = new Client(10, -10, 10);
		ClientDao clientManager = fabrique.getClientDao();
		clientManager.deleteAll();
		clientManager.create(c1);
		clientManager.create(c2);
		clientManager.create(c3);

		d.addDestination(c1, 14.1);
		d.addDestination(c2, 14.1);
		d.addDestination(c3, 14.1);
		c1.addDestination(d, 14.1);
		c1.addDestination(c2, 20);
		c1.addDestination(c3, 20);
		c2.addDestination(d, 14.1);
		c2.addDestination(c1, 20);
		c2.addDestination(c3, 20);
		c3.addDestination(d, 14.1);
		c3.addDestination(c1, 20);
		c3.addDestination(c2, 20);
		depotManager.update(d);
		clientManager.update(c1);
		clientManager.update(c2);
		clientManager.update(c3);

		Vehicule v1 = new Vehicule(d, 15);
		Vehicule v2 = new Vehicule(d, 15);
		VehiculeDao vehiculeManager = fabrique.getVehiculeDao();
		vehiculeManager.create(v1);
		vehiculeManager.create(v2);

		Planning p = new Planning();

		PlanningDao planningManager = fabrique.getPlanningDao();

		vehiculeManager.deleteAll();
		planningManager.deleteAll();

		p.addVehicule(v1);
		p.addVehicule(v2);
		planningManager.create(p);

		if (!v1.addClient(c1)) {
			v2.addClient(c1);
		}
		if (!v1.addClient(c2)) {
			v2.addClient(c2);
		}
		if (!v1.addClient(c3)) {
			v2.addClient(c3);
		}

		p.updatePositionClients();

		clientManager.update(c1);
		clientManager.update(c2);
		clientManager.update(c3);

		planningManager.update(p);

		System.out.println(p.toString());
	}
}
