package SingletonLoggingOrnek;

import SingletonLoggingOrnek.SingletonLogging.LogLevel;

public class Test {

	public static void main(String[] args) {
		SingletonLogging.getLogger().Log("main metodu baþlayamadý", LogLevel.ERROR);

	}

}
