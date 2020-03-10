package model.smartcontroller;

import java.util.List;

import model.Sensor;
import model.SmartController;

public class WemosEnv extends SmartController {
	
	public WemosEnv() {
		super();
	}

	public WemosEnv(String name, List<Sensor> sensors, double frequency) {
		super(name, sensors, frequency);
	}

	@Override
	public List<Sensor> checkValidDataRead() {
		return null;
	}

}
