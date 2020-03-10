package controller;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;

import dao.SmartControllerDao;
import model.device.ControllerTypes;
import model.device.FactorySmartController;
import model.device.Sensor;
import model.device.SensorTypes;
import model.device.SmartController;

/** Si occupa delle operazioni relative agli SmartController.
 * Deve essere usata dal layer piu' esterno, ovvero da GUI o API rest.
 */
public class SmartControllerController {
	
	@Inject
	private Logger logger;
	
	@Inject
	private SmartControllerDao controllerDao;
	
	@Inject
	private SensorController sensorController;
	
	@Inject
	private FactorySmartController factoryController;
	
	
	@Transactional
	public SmartController createSmartController(ControllerTypes type, String name) throws Exception {
		SmartController sc = factoryController.getSmartController(type, name);
		Sensor soil = sensorController.create(SensorTypes.Soil);
		Sensor dht11 = sensorController.create(SensorTypes.DHT11);
		List<Sensor> listSensor = new LinkedList<Sensor>();
		listSensor.add(soil);
		listSensor.add(dht11);
		sc.setSensors(listSensor);
		controllerDao.save(sc);
		logger.info("New smartcontroller created");
		return sc;
	}
	
	@Transactional
	public void update(SmartController controller) throws Exception {
		controllerDao.update(controller);
		logger.info("Smartcontroller updated");
	}
	
	@Transactional
	public void addSensor(Long id, Sensor sensor) throws Exception {
		SmartController controller = controllerDao.get(id);
		controller.getSensors().add(sensor);
		controllerDao.update(controller);
		logger.info("Added new sensor into smartcontroller");
	}
	
	@Transactional
	public void changeFrequency(Long id, double value) throws Exception {
		SmartController sc = controllerDao.get(id);
		sc.setFrequency(value);
		controllerDao.update(sc);
		logger.info("Frequency changed");
	}
	
	

}
