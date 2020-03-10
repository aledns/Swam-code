package model.sensor;

import model.Sensor;

public class DHT11 extends Sensor {
	
	public DHT11() {
		super();
	}
	
	public DHT11(String name, double threshold) {
		super(name,threshold);
	}

	@Override
	public boolean exceedThreshold() {
		return this.getValueReaded() < this.getThreshold();
	}
	
	

}
