package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import model.device.SmartController;


public class SmartControllerDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void save(SmartController smartController) throws Exception {
		em.persist(smartController);
	}
	
	@Transactional
	public void update(SmartController smartController) throws Exception {
		em.merge(smartController);
	}
	
	@Transactional
	public SmartController get(Long id) throws Exception {
		SmartController sc =  em.find(SmartController.class,id);
		em.refresh(sc);
		return sc;
	}
	
	@Transactional
	public boolean exist(Long id) throws Exception {
		return em.find(SmartController.class, id)==null?false:true;
	}

}
