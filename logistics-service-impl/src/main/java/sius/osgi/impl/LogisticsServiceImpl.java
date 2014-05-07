package sius.osgi.impl;

import org.osgi.service.component.ComponentContext;

import sius.osgi.LogisticsService;

public class LogisticsServiceImpl implements LogisticsService {
	private String id;


	public void activate(ComponentContext context) {
	    this.id = "http://localhost:9090/logistics";
	}
	
	public void deactivate(ComponentContext context) {
	    
	}
	
	public void send(String content) {
		System.out.println(String.format("Logistics service has received new pack [%s]: %s", id, content));
	}

	public String getID() {
		return this.id;
	}
}
