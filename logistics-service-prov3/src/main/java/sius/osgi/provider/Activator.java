package sius.osgi.provider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import sius.osgi.LogisticsService;

public class Activator implements BundleActivator {
	private ServiceTracker serviceTracker;
	private LogisticsService logisticsService = null;
	private Thread sender;
	private long counter = 1;

	public void start(BundleContext context) throws Exception {
		serviceTracker = new ServiceTracker(context,
				LogisticsService.class.getName(), null) {
			public Object addingService(ServiceReference reference) {
				Object svc = super.addingService(reference);
				if (svc instanceof LogisticsService) {
					logisticsService = (LogisticsService) svc;
					System.out
							.println("Provider [3]: Adding logistics service: "
									+ logisticsService.getID() + " ("
									+ logisticsService + ")");
				}
				return svc;
			}

			public void removedService(ServiceReference reference,
					Object service) {
				System.out.println("Provider [3]: Removing logistics service: "
						+ logisticsService.getID());
				logisticsService = null;
				super.removedService(reference, service);
			}
		};
		serviceTracker.open();

		sender = new Thread(new Runnable() {
			public void run() {
				while (true) {
					if (logisticsService != null) {
						String content = "charge " + counter++;
						System.out
								.println("Provider [3]: Sending content to logistics: "
										+ content);
						logisticsService.send(content);
					}

					try {
						Thread.sleep(10000);
					} catch (InterruptedException ex) {
						/* nop */
					}
				}
			}
		});
		sender.start();
	}

	public void stop(BundleContext context) throws Exception {
		serviceTracker.close();
		sender.interrupt();
	}

}
