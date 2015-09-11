package SingletonLoggingOrnek;

import javax.security.auth.login.Configuration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class SingletonLogging {
	
	//singleton kurallarý
	private static SingletonLogging instance=null;
	private static Object lock=new Object();//synchronized e giren threadler bu deðiþkeni tutacak sonrasýnda serbest býrakacak
	
	private SingletonLogging(Logger logger){//Log kayýtlarýný Logger sýnýfý ile oluþturulacak
		setLogger(logger);	//yapýcý methoda aktarýlan geçici logger nesnesi setLogger metodu ile kalýcý olarak logger deðiþkenine aktarýlacak
	}
	
	//logging field
	public enum LogLevel {DEBUG, INFO, WARNING, ERROR, FATAL};//log hata seviyeleri
	private Logger logger;//loglarý tutan deðiþken
	
	//metoda parametre olarak gelen logger nesnesi kalýcý olarak logger deðiþkenine atanýyor
	public void setLogger(Logger logger){
		this.logger=logger;
	}
	
	//singleton kurallarýna uygun olarak tek nesne üretiyoruz
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
	
	//log düzeylerine göre mesajlarý yaz
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
