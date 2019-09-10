package demo;

import java.util.List;
import java.util.ArrayList;

public class Meituan {
	
	static class OOMObject{
		
	}
	
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		System.out.println(list.hashCode());
		/*while(true){
			list.add(new OOMObject());
		}*/
	}
}
