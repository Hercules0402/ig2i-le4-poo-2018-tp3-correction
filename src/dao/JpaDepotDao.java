package dao;

import metier.Depot;

/**
 * TODO.
 * @author dcattaru
 */
public class JpaDepotDao extends JpaDao<Depot> implements DepotDao{

	private static JpaDepotDao instance;

	private JpaDepotDao() {
		super(Depot.class);
	}

	protected static JpaDepotDao getInstance(){
		if (instance == null) {
			instance = new JpaDepotDao();
		}
		return instance;
	}
}
