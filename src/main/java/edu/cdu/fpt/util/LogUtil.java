package edu.cdu.fpt.util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtil {

	
	public static Logger getLogger(@SuppressWarnings("rawtypes") Class clazz){
		Logger log = Logger.getLogger(clazz.getName());
		log = setLog(log);
		return log;
	}

	/**
	 * @param log
	 */
	private static Logger setLog(Logger log) {
		log.setLevel(Level.INFO);
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(new CustFormatter());
		log.addHandler(consoleHandler);
		log.setUseParentHandlers(false);
		return log;
	}
	
	public static Logger getLogger(){
		Logger log = Logger.getGlobal();
		log =setLog(log);
		return log;
	}
}
