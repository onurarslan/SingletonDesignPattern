package SingletonLoggingOrnek;

import javax.security.auth.login.Configuration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class SingletonLogging {
	
	//singleton kurallarý
	private static SingletonLogging instance=null;
	private static Object lock=new Object();
	
	protected SingletonLogging(){	}
	
	//logging field
	public enum LogLevel {DEBUG, INFO, WARNING, ERROR, FATAL};
	private Logger logger;
	
	public SingletonLogging (Logger logger){
		this();
		setLogger(logger);
	}
	
	public void setLogger(Logger logger){
		this.logger=logger;
	}
	
	public static SingletonLogging getLogger(){
		if(instance==null){
			synchronized (lock) {
				if(instance==null){
					instance=new SingletonLogging(LogManager.getLogger(SingletonLogging.class));
				}
			}
		}
		return instance;
	}
	
	
	public void Log(String msg, LogLevel logLevel){
		switch (logLevel) {
		case DEBUG:
			logger.debug(msg);
			break;
		case INFO:
			logger.info(msg);
			break;
		case WARNING:
			logger.warn(msg);
			break;
		case ERROR:
			logger.error(msg);
			break;
		case FATAL:
			logger.fatal(msg);
			break;
		default:
		}
	}
	

}
