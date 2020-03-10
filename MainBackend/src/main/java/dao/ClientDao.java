package dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import model.Client;


public class ClientDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void save(Client client) throws Exception {
		em.persist(client);
	}
	
	@Transactional
	public void update(Client client) throws Exception {
		em.merge(client);
	}
	
	
	@Transactional
	public Client get(String email) {		
		Client client = em.find(Client.class, email);
		em.refresh(client);
		System.out.println("Object retrived");
		return client;
	}
	
	@Transactional
	public boolean exist(String email) {
		return em.find(Client.class, email)==null?false:true;
	}

}
