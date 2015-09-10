package SingletonLoggingOrnek;

import java.io.ObjectInputStream.GetField;

import SingletonLoggingOrnek.SingletonLogging.LogLevel;

public class Test {

	public static void main(String[] args) {
		SingletonLogging.getLogger().Log("Deneme", LogLevel.ERROR);

	}

}
