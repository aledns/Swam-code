package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import model.device.Sensor;


public class SensorDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void save(Sensor sensor) throws Exception {
		em.persist(sensor);
	}
	
	@Transactional
	public void update(Sensor sensor) throws Exception {
		em.merge(sensor);
	}
	
	@Transactional
	public Sensor get(Long id) throws Exception {
		
		Sensor sen =  em.find(Sensor.class,id);
		em.refresh(sen);
		return sen;
	}
	
	@Transactional
	public boolean exist(Long id) throws Exception {
		return em.find(Sensor.class, id)==null?false:true;
	}
	
	

}
