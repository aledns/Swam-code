package model.device.smartcontroller;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.device.Sensor;
import model.device.SmartController;

@Entity
@DiscriminatorValue("WEMOS")
public class Wemos extends SmartController {
	
	public Wemos() {
		super();
	}

	public Wemos(String name, double frequency, List<Sensor> sensors) {
		super(name, frequency, sensors);
	}
	
	

}
