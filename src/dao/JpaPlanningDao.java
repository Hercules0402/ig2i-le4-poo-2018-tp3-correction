package dao;

import metier.Planning;

/**
 * TODO.
 * @author dcattaru
 */
public class JpaPlanningDao extends JpaDao<Planning> implements PlanningDao{

	private static JpaPlanningDao instance;

	private JpaPlanningDao() {
		super(Planning.class);
	}

	protected static JpaPlanningDao getInstance(){
		if (instance == null) {
			instance = new JpaPlanningDao();
		}
		return instance;
	}
}
