package dao;

import java.util.Collection;

/**
 * TODO.
 * @author dcattaru
 */
public interface Dao<T> {
	public boolean create(T obj) ;
	public boolean update (T obj);
	public T find (Integer id);
	public Collection<T> findAll();
	public boolean delete (T obj);
	public boolean deleteAll();
	public void close();
}
