package model.device;

import model.device.smartenvironment.WemosEnv;

/** Implementazione di FactoryDevice.
 * Il metodo getSmartController ritorna null.
 */
public class FactorySmartEnvironment implements FactoryDevice {

	@Override
	public SmartEnvironment getSmartEnvironment(EnvironmentTypes type, String name) {
		if(type==EnvironmentTypes.WEMOS) {
			SmartEnvironment env = new WemosEnv();
			env.setFrequency(15);
			env.setName(name);
			return env;
		}
		return null;
	}

	@Override
	public SmartController getSmartController(ControllerTypes type, String name) {
		return null;
	}

}
