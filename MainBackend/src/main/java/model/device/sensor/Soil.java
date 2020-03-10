package model.device.sensor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import model.device.Sensor;

@Entity
@DiscriminatorValue("SOIL")
public class Soil extends Sensor {
	
	public Soil() {
		super();
	}

	public Soil(String name, double threshold) {
		super(name, threshold);
	}
	
}
