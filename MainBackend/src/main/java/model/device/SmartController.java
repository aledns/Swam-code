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
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import model.device.smartcontroller.Wemos;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
@Table(name="SMARTCONTROLLER")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=As.PROPERTY,property="type")
@JsonSubTypes({
	@Type(value=Wemos.class,name="Wemos")
})
public abstract class SmartController {
	private Long smartcontrollerid;
	private String name;
	private double frequency;
	private List<Sensor> sensors;
	
	public SmartController() {
		
	}

	public SmartController(String name, double frequency, List<Sensor> sensors) {
		super();
		this.name = name;
		this.frequency = frequency;
		this.sensors = sensors;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getSmartcontrollerid() {
		return smartcontrollerid;
	}

	public void setSmartcontrollerid(Long smartcontrollerid) {
		this.smartcontrollerid = smartcontrollerid;
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
		result = prime * result + ((smartcontrollerid == null) ? 0 : smartcontrollerid.hashCode());
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
		if (smartcontrollerid == null) {
			if (other.smartcontrollerid != null)
				return false;
		} else if (!smartcontrollerid.equals(other.smartcontrollerid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SmartController [smartcontrollerid=" + smartcontrollerid + ", name=" + name + ", frequency=" + frequency
				+ ", sensors=" + sensors + "]";
	}
	
	

}
