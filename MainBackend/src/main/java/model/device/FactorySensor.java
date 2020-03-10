package model.device;

import model.device.sensor.DHT11;
import model.device.sensor.Soil;

/** Si occupa di creare i sensori
 * Deve essere chiamato solo da SensorController*/
public class FactorySensor {
	
	/** Ritorna un sensore secondo il tipo specificato*/
	public Sensor getSensor(SensorTypes type) {
		if (type==SensorTypes.DHT11) {
			return new DHT11("acqua",11.5);
		}
		if (type==SensorTypes.Soil) {
			return new Soil("aria",22.5);
		}
		return null;
	}

}
