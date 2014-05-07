package sius.osgi.impl;

import sius.osgi.LogisticsService;

public class LogisticsServiceImpl implements LogisticsService {
	private String id;

	public LogisticsServiceImpl(String id) {
		this.id = id;
	}

	public void send(String content) {
		System.out.println(String.format("Logistics service has received new pack [%s]: %s", id, content));
	}

	public String getID() {
		return this.id;
	}
}
