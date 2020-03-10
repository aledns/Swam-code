package model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import model.smartcontroller.Wemos;
import model.smartcontroller.WemosEnv;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include= As.PROPERTY, property="type")
@JsonSubTypes({
	@Type(value=Wemos.class, name="Wemos"),
	@Type(value=WemosEnv.class, name="WemosEnv")
})
public abstract class SmartController {
	//usato anche come hostname
	private String name;
	private List<Sensor> sensors;
	private double frequency;
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date lastread;
	
	public SmartController() {
		
	}

	public SmartController(String name, List<Sensor> sensors, double frequency) {
		this.name = name;
		this.sensors = sensors;
		this.frequency = frequency;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getLastread() {
		return lastread;
	}
	
	public void setLastread(Date lastread) {
		this.lastread = lastread;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	
	public abstract List<Sensor> checkValidDataRead();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(frequency);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sensors == null) ? 0 : sensors.hashCode());
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
		SmartController other = (SmartController) obj;
		if (Double.doubleToLongBits(frequency) != Double.doubleToLongBits(other.frequency))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sensors == null) {
			if (other.sensors != null)
				return false;
		} else if (!sensors.equals(other.sensors))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SmartController [name=" + name + ", sensors=" + sensors + ", frequency=" + frequency + "]";
	}
	
	
}
