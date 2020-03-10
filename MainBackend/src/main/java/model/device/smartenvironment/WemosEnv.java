package model.device.smartenvironment;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.device.Sensor;
import model.device.SmartController;
import model.device.SmartEnvironment;

@Entity
@DiscriminatorValue("WEMOSENV")
public class WemosEnv extends SmartEnvironment {
	
	public WemosEnv() {
		super();
	}

	public WemosEnv(String name, double frequency, List<SmartController> smartcontrollers, List<Sensor> sensors) {
		super(name, frequency, smartcontrollers, sensors);
	}
	
}
