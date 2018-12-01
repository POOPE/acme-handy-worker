
package generic;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import domain.DomainEntity;

public abstract class AbstractHibernateDao<T extends DomainEntity> {

	private Class<T>	clazz;

	@Autowired
	SessionFactory		sessionFactory;


	public final void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T findOne(Long id) {
		return (T) this.getCurrentSession().get(this.clazz, id);
	}

	public List<T> findAll() {
		return this.getCurrentSession().createQuery("from " + this.clazz.getName()).list();
	}

	public void create(T entity) {
		this.getCurrentSession().persist(entity);
	}

	public void update(T entity) {
		this.getCurrentSession().merge(entity);
	}

	public void delete(T entity) {
		this.getCurrentSession().delete(entity);
	}
	public void deleteById(Long entityId) {
		T entity = this.findOne(entityId);
		this.delete(entity);
	}

	protected final Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
