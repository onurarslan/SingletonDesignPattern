package SingletonOrnek1;

public class Test {

	public static void main(String[] args) {
		Singleton singletonNesne1=Singleton.getInstance();
		Singleton singletonNesne2=Singleton.getInstance();

		//Singleton singletonNesne2=new Singleton();
		
		if(singletonNesne1==singletonNesne2)
			System.out.println("Nesneler Ayn�!");
		else
			System.out.println("Nesneler Farkl�!");

	}

}
