package controller;

import java.security.MessageDigest;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import dao.ClientDao;
import model.Client;
import model.device.SmartEnvironment;

/** Si occupa di gestire i client.
 * Permette la creazione di un cliente
 * e la successiva aggiunta degli ambienti ad esso collegati.
 * Tale classe sarà usato dal layer più esterno, sia esso una GUI o API rest.
 * 
 */
public class ClientController {
	
	@Inject
	private Logger logger;
	
	@Inject
	private ClientDao clientDao;
	
	public void createClient(String email,String password, String hostname) throws Exception {
		if (clientDao.exist(email))
			throw new Exception("Client gia' esistente");
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		password = new String(md.digest(password.getBytes()));
		Client client = new Client(email,password);
		client.setHostname(hostname);
		clientDao.save(client);
		logger.info("Client created");
	}
	
	public void addEnvironment(String email, SmartEnvironment se) throws Exception {
		Client client = clientDao.get(email);
		if (client.getEnvironments()==null) {
			List<SmartEnvironment> le = new LinkedList<SmartEnvironment>();
			le.add(se);
			client.setEnvironments(le);
			clientDao.update(client);
			logger.info("Added new environment");
			return;
		}
		client.getEnvironments().add(se);
		clientDao.update(client);
		logger.info("Added new environment");
	}
	
	public List<SmartEnvironment> getEnvironments(String email) throws Exception {
		Client client = clientDao.get(email);
		logger.info("Get all environments");
		return client.getEnvironments();
	}
	
	public Client get(String email) {
		return clientDao.get(email);
	}

}
