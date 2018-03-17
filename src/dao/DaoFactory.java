package dao;

/**
 * TODO.
 * @author Admin
 */
public abstract class DaoFactory {
	/**
	 * TODO.
	 * @param type TODO.
	 * @return 
	 */
	public static DaoFactory getDaoFactory(PersistenceType type) {
		if (type.equals(PersistenceType.JPA)) {
			return new DaoFactoryJpa();
		}
		return null;
	}

	public abstract DepotDao getDepotDao();

	public abstract ClientDao getClientDao();

	public abstract RouteDao getRouteDao();

	public abstract VehiculeDao getVehiculeDao();

	public abstract PlanningDao getPlanningDao();

	public abstract InstanceDao getInstanceDao();
}
