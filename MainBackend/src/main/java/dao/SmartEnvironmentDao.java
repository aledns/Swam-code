package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import model.device.SmartEnvironment;

public class SmartEnvironmentDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void save(SmartEnvironment smartEnvironment) throws Exception {
		em.persist(smartEnvironment);
	}
	
	@Transactional
	public void update(SmartEnvironment smartEnvironment) throws Exception {
		em.merge(smartEnvironment);
	}
	
	@Transactional
	public SmartEnvironment get(Long id) throws Exception {
		SmartEnvironment env =  em.find(SmartEnvironment.class,id);
		em.refresh(env);
		return env;
	}
	
	@Transactional
	public boolean exist(Long id) throws Exception {
		return em.find(SmartEnvironment.class, id)==null?false:true;
	}

}
