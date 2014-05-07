package sius.osgi.impl;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import sius.osgi.LogisticsService;

public class Activator implements BundleActivator {
	private ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws Exception {
	    Dictionary<String, String> properties = new Hashtable<String, String>();
	    
	    properties.put("service.exported.interfaces", "*");
	    properties.put("service.exported.configs", "org.apache.cxf.ws");
	    properties.put("org.apache.cxf.ws.address", "http://localhost:9090/logistics");
	    
		serviceRegistration = context.registerService(
				LogisticsService.class.getName(),
				new LogisticsServiceImpl("http://localhost:9090/logistics"), properties);
	}

	public void stop(BundleContext context) throws Exception {
		serviceRegistration.unregister();
	}
}
