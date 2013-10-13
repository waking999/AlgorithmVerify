package edu.cdu.fpt.util;

import java.util.logging.ConsoleHandler;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;



public class LogUtil {

	
	public static Logger getLogger(@SuppressWarnings("rawtypes") Class clazz){
		Logger log = Logger.getLogger(clazz);
		log = setLog(log);
		return log;
	}

	/**
	 * @param log
	 */
	private static Logger setLog(Logger log) {
//		log.setLevel(Level.INFO);
//		ConsoleHandler consoleHandler = new ConsoleHandler();
//		consoleHandler.setFormatter(new CustFormatter());
//		log.addHandler(consoleHandler);
//		log.setUseParentHandlers(false);
		return log;
	}
	
	public static Logger getLogger(){
		Logger log = Logger.getRootLogger();
		log =setLog(log);
		return log;
	}
}
