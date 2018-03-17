package dao;

import java.util.Collection;
import java.util.HashSet;
import javax.persistence.Query;
import metier.Vehicule;

/**
 * TODO.
 * @author dcattaru
 */
public class JpaVehiculeDao extends JpaDao<Vehicule> implements VehiculeDao {

	private static JpaVehiculeDao instance;

	private JpaVehiculeDao() {
		super(Vehicule.class);
	}

	protected static JpaVehiculeDao getInstance() {
		if (instance == null) {
			instance = new JpaVehiculeDao();
		}
		return instance;
	}

	@Override
	public Collection<Vehicule> findAllNotUsed() {
		Collection<Vehicule> vehicles = new HashSet<>();
		Query query = em.createNamedQuery("Vehicle.findNotUsed");
		vehicles = query.getResultList();
		return vehicles;
	}

}
