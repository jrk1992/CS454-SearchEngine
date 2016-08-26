package Indexer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Test {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		PageRank r=new PageRank();
		
		double x=r.pageRank();
		System.out.println("Size :" + x);
		/*Iterator i=x.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry<String, Double> pair=(Entry<String, Double>) i.next();
			System.out.println("String : "+pair.getKey() + "  Double : "+ pair.getValue());
			
		}*/
	}

}
