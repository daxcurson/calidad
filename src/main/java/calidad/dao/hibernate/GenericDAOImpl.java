package calidad.dao.hibernate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import calidad.dao.GenericDAO;

public class GenericDAOImpl<T> implements GenericDAO<T>
{
	protected static Logger log=LogManager.getLogger(GenericDAOImpl.class);
	@Autowired
	protected SessionFactory sessionFactory;
	
	protected Class<T> type;

    @SuppressWarnings("unchecked")
	public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class<T>) pt.getActualTypeArguments()[0];
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public T getById(int id) 
	{
		return (T)sessionFactory.getCurrentSession().createQuery("from "+type.getSimpleName()+" where id="+id).getSingleResult();
	}
	@Override
	@Transactional
	public void add(T t) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void update(T t) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}
}
