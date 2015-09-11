package SingletonLoggingOrnek;

import javax.security.auth.login.Configuration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class SingletonLogging {
	
	//singleton kurallar�
	private static SingletonLogging instance=null;
	private static Object lock=new Object();//synchronized e giren threadler bu de�i�keni tutacak sonras�nda serbest b�rakacak
	
	private SingletonLogging(Logger logger){//Log kay�tlar�n� Logger s�n�f� ile olu�turulacak
		setLogger(logger);	//yap�c� methoda aktar�lan ge�ici logger nesnesi setLogger metodu ile kal�c� olarak logger de�i�kenine aktar�lacak
	}
	
	//logging field
	public enum LogLevel {DEBUG, INFO, WARNING, ERROR, FATAL};//log hata seviyeleri
	private Logger logger;//loglar� tutan de�i�ken
	
	//metoda parametre olarak gelen logger nesnesi kal�c� olarak logger de�i�kenine atan�yor
	public void setLogger(Logger logger){
		this.logger=logger;
	}
	
	//singleton kurallar�na uygun olarak tek nesne �retiyoruz
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
	
	//log d�zeylerine g�re mesajlar� yaz
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
