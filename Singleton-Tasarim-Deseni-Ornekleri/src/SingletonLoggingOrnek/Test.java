package SingletonLoggingOrnek;

import SingletonLoggingOrnek.SingletonLogging.LogLevel;

public class Test {

	public static void main(String[] args) {
		SingletonLogging.getLogger().Log("main metodu ba�layamad�", LogLevel.ERROR);

	}

}
