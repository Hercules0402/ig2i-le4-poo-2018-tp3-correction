package dao;

import java.util.Collection;
import metier.Vehicule;

/**
 * TODO.
 * @author dcattaru
 */
public interface VehiculeDao extends Dao<Vehicule> {
	public Collection<Vehicule> findAllNotUsed();
}
