package calidad.dao;

public interface GenericDAO<T>
{
	public T getById(int id);
	void add(T t);
	void update(T t);
}
