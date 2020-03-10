package model;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactoryLogger {
	
	@Produces
	public Logger getLogger(InjectionPoint injectionPoint) {
		System.out.println("Logger created");
		return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}

}
