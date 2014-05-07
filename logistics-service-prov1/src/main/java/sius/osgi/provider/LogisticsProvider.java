package sius.osgi.provider;

import sius.osgi.LogisticsService;

public class LogisticsProvider {
    
    private LogisticsService logisticsService = null;
    private Thread sender;
    private long counter = 1;
    
    void bindLogistics(LogisticsService logisticsService) {
        this.logisticsService = logisticsService;
    }

    void unbindLogistics(LogisticsService logisticsService) {
        this.logisticsService = null;
    }

    void start() {
        sender = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (logisticsService != null) {
                        String content = "charge " + counter++;
                        System.out
                                .println("Provider [1]: Sending content to logistics: "
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
}
