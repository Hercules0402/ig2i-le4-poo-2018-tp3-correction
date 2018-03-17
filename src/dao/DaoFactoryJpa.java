package dao;

/**
 * TODO.
 * @author Admin
 */
public class DaoFactoryJpa extends DaoFactory {

	@Override
	public DepotDao getDepotDao() {
		return JpaDepotDao.getInstance();
	}

	@Override
	public ClientDao getClientDao() {
		return JpaClientDao.getInstance();
	}

	@Override
	public RouteDao getRouteDao() {
		return JpaRouteDao.getInstance();
	}

	@Override
	public VehiculeDao getVehiculeDao() {
		return JpaVehiculeDao.getInstance();
	}

	@Override
	public PlanningDao getPlanningDao() {
		return JpaPlanningDao.getInstance();
	}

	@Override
	public InstanceDao getInstanceDao() {
		return JpaInstanceDao.getInstance();
	}

}
