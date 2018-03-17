package dao;

import java.util.Collection;
import java.util.HashSet;
import javax.persistence.Query;
import metier.Client;

/**
 * TODO.
 * @author dcattaru
 */
public class JpaClientDao extends JpaDao<Client> implements ClientDao {

	private static JpaClientDao instance;

	private JpaClientDao() {
		super(Client.class);
	}

	protected static JpaClientDao getInstance() {
		if (instance == null){ 
			instance = new JpaClientDao();
		}
		return instance;
	}

	@Override
	public Collection<Client> findNotServed() {
		Collection<Client> clients = new HashSet<>();
		Query query = em.createNamedQuery("Client.findNotServed");
		clients = query.getResultList();
		return clients;
	}

}
