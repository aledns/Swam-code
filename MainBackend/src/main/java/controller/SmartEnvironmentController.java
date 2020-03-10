package controller;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;

import dao.SmartEnvironmentDao;
import model.device.EnvironmentTypes;
import model.device.FactorySmartEnvironment;
import model.device.Sensor;
import model.device.SensorTypes;
import model.device.SmartController;
import model.device.SmartEnvironment;

/** Si occupa delle operazioni relative agli SmartEnvironment.
 * Deve essere usata dal layer piu' esterno, ovvero da GUI o API rest.
 */
public class SmartEnvironmentController {
	
	@Inject
	private Logger logger;
	
	@Inject
	private SmartEnvironmentDao envDao;
	
	@Inject
	private SensorController sensorController;
	
	@Inject
	private FactorySmartEnvironment factoryEnvironment;
	
	@Transactional
	public SmartEnvironment createSmartEnvironment(EnvironmentTypes type, String name) throws Exception {
		SmartEnvironment env = factoryEnvironment.getSmartEnvironment(type, name);
		Sensor s = sensorController.create(SensorTypes.DHT11);
		List<Sensor> listSensor = new LinkedList<Sensor>();
		listSensor.add(s);
		env.setSensors(listSensor);
		envDao.save(env);
		logger.info("New environment created");
		return env;
	}
	
	@Transactional
	public void update(SmartEnvironment env) throws Exception {
		envDao.update(env);
		logger.info("Environment updated");
	}
	
	@Transactional
	public void addSmartController(Long id, SmartController controller) throws Exception {
		SmartEnvironment env = envDao.get(id);
		if (env.getSmartcontrollers()==null) {
			List<SmartController> list = new LinkedList<SmartController> ();
			list.add(controller);
			env.setSmartcontrollers(list);
			envDao.update(env);
			logger.info("Added new smartcontroller to environment");
			return;
		}
		env.getSmartcontrollers().add(controller);
		envDao.update(env);
		logger.info("Added new smartcontroller to environment");
	}
	
	@Transactional
	public SmartEnvironment get(Long id) throws Exception  {
		logger.info("Retrive smartcontroller");
		return envDao.get(id);
	}
	
	@Transactional
	public List<SmartController> getAll(Long id) throws Exception {
		SmartEnvironment env = envDao.get(id);
		logger.info("Obtained list of all smartcontroller");
		return env.getSmartcontrollers();
	}
}
