package model.device.smartenvironment;

import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.device.Sensor;
import model.device.SmartController;
import model.device.SmartEnvironment;

@Entity
@DiscriminatorValue("WemosEnv")
public class WemosEnv extends SmartEnvironment {
	
	public WemosEnv() {
		super();
	}

	public WemosEnv(String name, double frequency, Set<SmartController> smartcontrollers, Set<Sensor> sensors) {
		super(name, frequency, smartcontrollers, sensors);
	}
	
}
