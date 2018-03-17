package dao;

import java.util.Collection;
import metier.Client;

/**
 * TODO.
 * @author dcattaru
 */
public interface ClientDao extends Dao<Client> {
	public Collection<Client> findNotServed();
}
