package com.sj.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log4jUtils {
	public static void info(Object o,String method,String msg) {
		String className=getObjectClassName(o);
		Logger logger = LoggerUtils.getLogger();
		logger.logp(Level.INFO, className, method, msg);
		
	}

	public static void warning(Object o,String method,String msg) {
		String className=getObjectClassName(o);
		Logger logger = LoggerUtils.getLogger();
		logger.logp(Level.WARNING, className, method, msg);
		
	}
	
	public static void sever(Object o,String method,String msg) {
		String className=getObjectClassName(o);
		Logger logger = LoggerUtils.getLogger();
		logger.logp(Level.SEVERE, className, method, msg);
		
	}
	private static String getObjectClassName(Object o){
		String className;
		if(o instanceof String){			
			className=o.toString();
		}else if(o instanceof Class){
			className=o.getClass().toString();
		}else {
			className=o.toString();
		}
		return className;
	}
}
