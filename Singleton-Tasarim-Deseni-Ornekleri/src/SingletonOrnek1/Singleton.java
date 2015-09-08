package SingletonOrnek1;

public class Singleton {
	
	private static Singleton instance=null;
	private Singleton(){	}// protected belirteçde tanýmlayabilirdik
	/*
	 * yani Singleton singletonNesne1=new Singleton(); bunu yazmamýz durumunda artýk hata verecek
	 * çünkü private yaptýðýmýz için nesne oluþmasýný engelledik.
	 */
	
	//getInstance metodu ile kontrollü bir þekilde nesne oluþturuyoruz.
	//daha önce oluþtuysa nesne oluþmayacak.
	public static Singleton getInstance(){
		if(instance==null){
			instance=new Singleton();
		}

		return instance;
		
	}

}
