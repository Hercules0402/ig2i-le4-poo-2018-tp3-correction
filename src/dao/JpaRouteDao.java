package dao;

import metier.Route;

/**
 * TODO.
 * @author dcattaru
 */
public class JpaRouteDao extends JpaDao<Route> implements RouteDao{

	private static JpaRouteDao instance;

	private JpaRouteDao() {
		super(Route.class);
	}

	protected static JpaRouteDao getInstance(){
		if (instance == null) {
			instance = new JpaRouteDao();
		}
		return instance;
	}
}
