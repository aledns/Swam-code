package model.sensor;

import model.Sensor;

public class Soil extends Sensor {
	
	public Soil() {
		super();
	}
	
	public Soil(String name, double threshold) {
		super(name,threshold);
	}

	@Override
	public boolean exceedThreshold() {
		return this.getValueReaded() < this.getThreshold();
	}

}
