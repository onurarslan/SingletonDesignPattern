package SingletonCacheOrnek;

import java.util.List;

public class Test {
	public static void main(String[] args){
		SingletonCache singletonCache=SingletonCache.getInstance();
		List nameList=  singletonCache.getNameList();
	}
}
