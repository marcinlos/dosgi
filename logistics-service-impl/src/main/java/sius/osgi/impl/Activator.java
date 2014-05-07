package sius.osgi.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import sius.osgi.LogisticsService;

public class Activator implements BundleActivator {
	private ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws Exception {
		serviceRegistration = context.registerService(
				LogisticsService.class.getName(),
				new LogisticsServiceImpl("none"), null);
	}

	public void stop(BundleContext context) throws Exception {
		serviceRegistration.unregister();
	}
}
