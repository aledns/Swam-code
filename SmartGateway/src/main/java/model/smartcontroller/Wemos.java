package model.smartcontroller;

import java.util.LinkedList;
import java.util.List;

import model.Sensor;
import model.SmartController;

public class Wemos extends SmartController {
	
	public Wemos() {
		super();
	}
	
	public Wemos(String name, List<Sensor> sensors, double frequency) {
		super(name,sensors,frequency);
	}

	@Override
	public List<Sensor> checkValidDataRead() {
		List<Sensor> outOfThreshold = new LinkedList<Sensor>();
		for (Sensor temp: this.getSensors()) {
			if (!temp.exceedThreshold())
				outOfThreshold.add(temp);
		}
		return outOfThreshold;
	}
	
	

}
