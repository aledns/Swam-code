package controller;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;

import dao.SensorDao;
import model.device.FactorySensor;
import model.device.Sensor;
import model.device.SensorTypes;


/** Si occupa delle operazioni relative ai Sensori.
 * Deve essere usata dal layer piu' esterno, ovvero da GUI o API rest.
 */
public class SensorController {
	
	@Inject
	private Logger logger;
	
	@Inject
	private SensorDao sensorDao;
	
	@Inject
	private FactorySensor factorySensor;
	
	@Transactional
	public Sensor create(SensorTypes type) throws Exception {
		Sensor s = factorySensor.getSensor(type);
		sensorDao.save(s);
		logger.info("Sensor created");
		return s;
	}
	
	public void chnageThreshold(Long id, double value) throws Exception {
		Sensor sensor = sensorDao.get(id);
		sensor.setThreshold(value);
		sensorDao.update(sensor);
		logger.info("Setted new threshold");
	}

}
