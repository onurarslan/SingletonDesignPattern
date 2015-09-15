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
			Class.forName("com.mysql.jdbc.Driver");//hangi veritabaný türü ile çalýþacaðýmýzý tanýmlýyoruz
			dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/veritabani","kullaniciadi","sigre");//veri tabanýna ait host ve veritabaný adýný ve veritabanýna ulaþacak olan kullanýcý adý ve þifresini tanýmlýyoruz
		}catch(SQLException e) {
            System.err.println(e.getMessage());            
		}catch(ClassNotFoundException e){
			System.err.println(e.getMessage());
        }
		return dbConn;
	}
	
	public List<Personel> getPersonelList(){
		List<Personel> listPersonel=new ArrayList<Personel>();
		Connection conn = null;//veritabaný baðlantýsý saðlar
		PreparedStatement stmt = null;//veritabaný sorgularý yapmamýzý saðlar
		ResultSet rs = null;//sorgu sonuçlarýný tutar
		String query="SELECT * FROM personel";//veritabaný sorgumuz
		try {
			conn=getConnection();//veritabaný baðlantýsýný açar
			stmt = conn.prepareStatement("SELECT * FROM personel");//sorgu iþlemini gerçekleþtirir
			rs=stmt.executeQuery();//sorgu sonucu gelen verileri tutar
			
			while (rs.next()) {//gelen her veriyi
				Personel personel=new Personel();//personel sýnýf deiþkenini oluþtur
				personel.setAd(rs.getString("AD"));//ilgili satýrýn AD sütununa ait veriyi personel sýnýfýnýn ad deiþkeninde tut
				personel.setSoyad(rs.getString("SOYAD"));//ilgili satýrýn SOYAD sütununa ait veriyi personel sýnýfýnýn soyad deðiþkeninde tut
				
				listPersonel.add(personel);//sýnýfý listeye ekle
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(conn!=null){
				try {//baðlantýlarý kapat
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
