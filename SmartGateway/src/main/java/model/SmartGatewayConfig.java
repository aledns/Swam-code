package model;

public class SmartGatewayConfig {
	
	public static final String PATH_BACKEND = "http://aledns:8080/MainBackend/api";
	public static final String ADD_CONTROLLER = "http://aledns:8080/MainBackend/api/environment/{%d}/{%s}";
	public static final String ADD_ENV = "http://aledns:8080/MainBackend/api/environment/{%s}";

	
	public static String EMAIL_GATEWAY = "";
	public static String HOSTNAME_GATEWAY = "";

}
