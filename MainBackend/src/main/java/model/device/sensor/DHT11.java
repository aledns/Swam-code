package model.device.sensor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.device.Sensor;

@Entity
@DiscriminatorValue("DHT11")
public class DHT11 extends Sensor {
	
	public DHT11() {
		super();
	}

	public DHT11(String name, double threshold) {
		super(name, threshold);
	}
	
}
