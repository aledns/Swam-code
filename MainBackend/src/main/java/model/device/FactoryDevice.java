package model.device;

/** Interfaccia comune per la creazione dei vari Device.
 * I device possibili sono: SmartController e SmartEnvironment
 * Le sue implementazioni devono essere chiamate solo all'interno
 * dei controllori che ne fanno uso. In questo caso le implementazioni
 * devono essere usate da SmartControllerController e da
 * SmartEnvironmentController.
 */
public interface FactoryDevice {
	
	public SmartEnvironment getSmartEnvironment(EnvironmentTypes type, String name);
	
	public SmartController getSmartController(ControllerTypes type, String name);

}
