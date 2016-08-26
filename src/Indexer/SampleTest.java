package Indexer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SampleTest {

	public static void main(String[] args){
		TestCalcPageRank t=new TestCalcPageRank();
		t.getLinkAnalysis();
		Map<String,RankModel> m=t.getInLinks2();
		System.out.println(m.size());
		Map<String,RankModel> m1=t.getOutLinks();
		System.out.println(m1.size());
		try{
			FileWriter fw = new FileWriter(new File("C:\\Jugal\\Courses\\CS454_SearchEngine\\WebCrawlerStorage\\Map2.txt"));
			BufferedWriter bw= new BufferedWriter(fw);
			String pprint="";
			Iterator entries2 = t.inLinks.entrySet().iterator();
			while (entries2.hasNext()) {
				Entry thisEntry2 = (Entry) entries2.next();

				RankModel value = (RankModel) thisEntry2.getValue();

				String w=value.getF().getAbsolutePath();
				bw.write(w);
				String o="";
				for(String s:value.getInL()){
					o = " In : "+s+"\n";
					
				}
				bw.write(o);
				//pprint=pprint+w +"\n"+o;
				//System.out.println(w);
			}
			//bw.write(pprint);
			bw.close();
		}catch(Exception e){
			System.out.println("error");
			e.printStackTrace();
		}



	}
}
