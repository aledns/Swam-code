package model.device;

import model.device.smartcontroller.Wemos;
/** Implementazione di FactoryDevice. 
 * Il metodo getSmartEnvironment ritorna null.
 */
public class FactorySmartController implements FactoryDevice {

	@Override
	public SmartEnvironment getSmartEnvironment(EnvironmentTypes type, String name) {
		return null;
	}

	@Override
	public SmartController getSmartController(ControllerTypes type, String name) {
		if (type==ControllerTypes.WEMOS) {
			SmartController sc = new Wemos();
			sc.setName(name);
			sc.setFrequency(15);
			return sc;
		}
		return null;
	}

}
