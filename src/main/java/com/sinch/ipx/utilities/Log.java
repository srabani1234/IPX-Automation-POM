package com.sinch.ipx.utilities;

import org.apache.log4j.Logger;

public class Log {
	
	// initialize log
		public static Logger Log = Logger.getLogger(Log.class.getName());
		
		public static void onTestCaseStart(String testCaseName) {
			Log.info("=========================="+testCaseName+"==============================");
			
		}
		
		public static void onTestCaseEnd(String testCaseName) {
			Log.info("=========================="+testCaseName+"==============================");
			
		}
		
		public static void fatal(String message) {
			Log.fatal(message);
		}
		public static void debug(String message) {
			Log.debug(message);
		}
		public static void warn(String message) {
			Log.warn(message);
		}
		public static void error(String message) {
			Log.error(message);
		}
		public static void info(String message) {
			Log.info(message);
		}

}
