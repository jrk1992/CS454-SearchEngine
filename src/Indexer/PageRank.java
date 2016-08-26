package Indexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PageRank {
	Map<String, RankModel> outLinks;
	Map<String, RankModel> inLinks;
	Map<String, Double> ranks;
	public PageRank(Map<String, RankModel> in,Map<String, RankModel> out){
		this.ranks=new HashMap<String, Double>();
		this.outLinks=out;
		this.inLinks=in;
		for(Map.Entry<String, RankModel> r:outLinks.entrySet()){
			Double rank=(double) (1/outLinks.size());
			ranks.put(r.getKey(), rank);
		}
	}
	
	public Map<String, Double> getFinalRank(){
		for(Map.Entry<String, RankModel> entry:outLinks.entrySet()){
			Double ra=0.0;
			ArrayList<String> incoming=this.inLinks.get(entry.getKey()).getInL();
			if(incoming!=null){
				for(String i:incoming){
					Double r= ranks.get(i)/outLinks.get(i).outL.size();
					ra=ra+r;
				}
				ra=(0.85*ra)+0.15;
			}else{
				ra=ra+ranks.get(entry.getKey());
			}
			ranks.remove(entry.getKey(), ra);
		}
		
		return ranks;
	}

}
