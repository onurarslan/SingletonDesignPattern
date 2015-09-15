package SingletonDatabaseConnectionOrnek;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SingletonDBConnection{
	
	private static SingletonDBConnection instance=null;
	private SingletonDBConnection(){ }
	
	public static SingletonDBConnection getInstance(){
		if(instance==null){
			synchronized (SingletonDBConnection.class) {
				if (instance==null) {
					instance=new SingletonDBConnection();
				}
			}
		}
		return instance;
	}
	
	public Connection getConnection(){
		Connection dbConn=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");//hangi veritaban� t�r� ile �al��aca��m�z� tan�ml�yoruz
			dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/veritabani","kullaniciadi","sigre");//veri taban�na ait host ve veritaban� ad�n� ve veritaban�na ula�acak olan kullan�c� ad� ve �ifresini tan�ml�yoruz
		}catch(SQLException e) {
            System.err.println(e.getMessage());            
		}catch(ClassNotFoundException e){
			System.err.println(e.getMessage());
        }
		return dbConn;
	}
	
	public List<Personel> getPersonelList(){
		List<Personel> listPersonel=new ArrayList<Personel>();
		Connection conn = null;//veritaban� ba�lant�s� sa�lar
		PreparedStatement stmt = null;//veritaban� sorgular� yapmam�z� sa�lar
		ResultSet rs = null;//sorgu sonu�lar�n� tutar
		String query="SELECT * FROM personel";//veritaban� sorgumuz
		try {
			conn=getConnection();//veritaban� ba�lant�s�n� a�ar
			stmt = conn.prepareStatement("SELECT * FROM personel");//sorgu i�lemini ger�ekle�tirir
			rs=stmt.executeQuery();//sorgu sonucu gelen verileri tutar
			
			while (rs.next()) {//gelen her veriyi
				Personel personel=new Personel();//personel s�n�f dei�kenini olu�tur
				personel.setAd(rs.getString("AD"));//ilgili sat�r�n AD s�tununa ait veriyi personel s�n�f�n�n ad dei�keninde tut
				personel.setSoyad(rs.getString("SOYAD"));//ilgili sat�r�n SOYAD s�tununa ait veriyi personel s�n�f�n�n soyad de�i�keninde tut
				
				listPersonel.add(personel);//s�n�f� listeye ekle
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(conn!=null){
				try {//ba�lant�lar� kapat
					conn.close();
					stmt.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return listPersonel;
	}
}
