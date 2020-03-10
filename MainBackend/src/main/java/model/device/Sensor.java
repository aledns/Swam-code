package model.device;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import model.device.sensor.DHT11;
import model.device.sensor.Soil;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
@Table(name="SENSOR")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=As.PROPERTY,property="type")
@JsonSubTypes({
	@Type(value=Soil.class,name="Soil"),
	@Type(value=DHT11.class,name="DHT11")
})
public abstract class Sensor {
	private Long sensorid;
	private String name;
	private double threshold;
	
	public Sensor() {
		
	}

	public Sensor(String name, double threshold) {
		super();
		this.name = name;
		this.threshold = threshold;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getSensorid() {
		return sensorid;
	}

	public void setSensorid(Long sensorid) {
		this.sensorid = sensorid;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sensorid == null) ? 0 : sensorid.hashCode());
		long temp;
		temp = Double.doubleToLongBits(threshold);
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
		if (sensorid == null) {
			if (other.sensorid != null)
				return false;
		} else if (!sensorid.equals(other.sensorid))
			return false;
		if (Double.doubleToLongBits(threshold) != Double.doubleToLongBits(other.threshold))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sensor [sensorid=" + sensorid + ", name=" + name + ", threshold=" + threshold + "]";
	}

}
