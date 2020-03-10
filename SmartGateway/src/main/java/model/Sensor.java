package model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import model.sensor.Soil;
import model.sensor.DHT11;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include= As.PROPERTY, property="type")
@JsonSubTypes({
	@Type(value=Soil.class, name="Soil"),
	@Type(value=DHT11.class, name="DHT11")
})
public abstract class Sensor {
	private String name;
	private double valueReaded;
	private double threshold;
	
	public Sensor() {
		
	}
	
	public Sensor(String name, double threshold) {
		this.name = name;
		this.threshold = threshold;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValueReaded() {
		return valueReaded;
	}

	public void setValueReaded(double valueReaded) {
		this.valueReaded = valueReaded;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	
	public abstract boolean exceedThreshold();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(threshold);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valueReaded);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sensor other = (Sensor) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(threshold) != Double.doubleToLongBits(other.threshold))
			return false;
		if (Double.doubleToLongBits(valueReaded) != Double.doubleToLongBits(other.valueReaded))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sensor [name=" + name + ", valueReaded=" + valueReaded + ", threshold=" + threshold + "]";
	}
	

}
