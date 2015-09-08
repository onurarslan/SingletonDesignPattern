package SingletonCacheOrnek;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/*
 * bir web projesinde performansartt�rmak i�in cache i�lemleri yap�lmaktad�r.
 * 
 */
public class SingletonCache {
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	private Calendar queryDate;
	private List nameList=new ArrayList();
	
	private static SingletonCache instance=null;
	private static Object lock=new Object();
	private SingletonCache(){}
	
	public static SingletonCache getInstance(){
		if(instance==null){
			synchronized (lock) {//1. thread lock nesnesini tutar ve o b�rakmadan 2. thread �ileme ge�mez.
				if(instance==null){
					instance=new SingletonCache();
				}
			}
		}
		
		return instance;
	}
	
	//bu method 2 saatte bir caching i�lemi yapmaktad�r
	public List getNameList(){
		Calendar intervalBefore=Calendar.getInstance();//tarihi verir
		intervalBefore.add(Calendar.HOUR,-2);//�uan ki saatten 2 saat ��kart�r
		
		if(queryDate==null || queryDate.before(intervalBefore)){
			//queryDate null veya queryDate de tutulan tarih intervalBefore'dan �nce mi? 
			try{
				conn=DriverManager.getConnection("veritabani_adi","kullanici_adi","sifre");
				stmt=conn.createStatement();
				rs=stmt.executeQuery("Select name from personel");//personel tablosundan name s�tunu �ek
				while(rs.next()){
					nameList.add(rs.getString("name"));//getirilen verilerden name ad�nda ki s�tuna ait verileri nameList listesine ekle
				}
				queryDate=Calendar.getInstance();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					if(rs!=null){
						rs.close();
						rs=null;
					}
					if(stmt!=null){
						stmt.close();
						stmt=null;
					}
					if(conn!=null){
						conn.close();
						conn=null;
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return nameList;
	}

}
