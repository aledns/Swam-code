package model.device;

import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import model.device.smartenvironment.WemosEnv;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
@Table(name="SMARTENVIRONMENT")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=As.PROPERTY,property="type")
@JsonSubTypes({
	@Type(value=WemosEnv.class,name="WemosEnv")
})
public abstract class SmartEnvironment {
	private Long smartenvironmentid;
	private String name;
	private double frequency;
	private List<SmartController> smartcontrollers;
	private List<Sensor> sensors;
	
	public SmartEnvironment() {
		
	}

	public SmartEnvironment(String name, double frequency, List<SmartController> smartcontrollers,
			List<Sensor> sensors) {
		super();
		this.name = name;
		this.frequency = frequency;
		this.smartcontrollers = smartcontrollers;
		this.sensors = sensors;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getSmartenvironmentid() {
		return smartenvironmentid;
	}

	public void setSmartenvironmentid(Long smartenvironmentid) {
		this.smartenvironmentid = smartenvironmentid;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	
	@OneToMany
	public List<SmartController> getSmartcontrollers() {
		return smartcontrollers;
	}

	public void setSmartcontrollers(List<SmartController> smartcontrollers) {
		this.smartcontrollers = smartcontrollers;
	}
	
	@OneToMany
	public List<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(frequency);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sensors == null) ? 0 : sensors.hashCode());
		result = prime * result + ((smartcontrollers == null) ? 0 : smartcontrollers.hashCode());
		result = prime * result + ((smartenvironmentid == null) ? 0 : smartenvironmentid.hashCode());
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
		SmartEnvironment other = (SmartEnvironment) obj;
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
		if (smartcontrollers == null) {
			if (other.smartcontrollers != null)
				return false;
		} else if (!smartcontrollers.equals(other.smartcontrollers))
			return false;
		if (smartenvironmentid == null) {
			if (other.smartenvironmentid != null)
				return false;
		} else if (!smartenvironmentid.equals(other.smartenvironmentid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SmartEnvironment [smartenvironmentid=" + smartenvironmentid + ", name=" + name + ", frequency="
				+ frequency + ", smartcontrollers=" + smartcontrollers + ", sensors=" + sensors + "]";
	}
	
}
