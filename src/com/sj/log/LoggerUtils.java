package com.sj.log;

import java.util.logging.Logger;

public class LoggerUtils {
	private static Logger logger = null;

	private LoggerUtils() {

	}

	public static Logger getLogger() {
		if (logger == null) {
			logger = Logger.getLogger("name");
		}
		return logger;
	}
}
