package SingletonOrnek1;

public class Singleton {
	
	private static Singleton instance=null;
	private Singleton(){
		System.out.println("Singleton olu�tu!");
	}// protected belirtecinide tan�mlayabilirdik
	/*
	 * yani Singleton singletonNesne1=new Singleton(); bunu yazmam�z durumunda art�k hata verecek
	 * ��nk� private yapt���m�z i�in nesne olu�mas�n� engelledik.
	 */
	
	//getInstance metodu ile kontroll� bir �ekilde nesne olu�turuyoruz.
	//daha �nce olu�tuysa nesne olu�mayacak.
	public static Singleton getInstance(){
		if(instance==null){
			instance=new Singleton();
		}
		return instance;
		
	}

}
