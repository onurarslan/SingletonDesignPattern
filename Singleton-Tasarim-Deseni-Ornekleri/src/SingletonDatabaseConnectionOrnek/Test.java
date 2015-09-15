package SingletonDatabaseConnectionOrnek;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		List<Personel> personelTest =new ArrayList<Personel>();
		personelTest=SingletonDBConnection.getInstance().getPersonelList();
		System.out.println(personelTest.get(0).getAd() +" " + personelTest.get(0).getSoyad());
	
	}
}
