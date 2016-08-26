package Indexer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexerServlet
 */
@WebServlet("/IndexerServlet")
public class IndexerServlet extends HttpServlet {
	Map<String,RankModel> inL;
	Map<String,RankModel> outL;
	Map<String,Double> ranks;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TestCalcPageRank t=new TestCalcPageRank();
		t.getLinkAnalysis();
		inL=t.getInLinks2();
		System.out.println("##"+inL.size());
		outL=t.getOutLinks();
		System.out.println(outL.size());
		PageRank pr=new PageRank(inL, outL);
		ranks=pr.getFinalRank();
		request.getRequestDispatcher("/WEB-INF/Display.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String q=request.getParameter("search");
		ArrayList<Data> d=new ArrayList<Data>();
		LuceneTester t=new LuceneTester();
		t.sortUsingRelevance(q,d);
		Collections.sort(d);
		double maxTF=d.get(d.size()-1).getTfidfScore();
		for(Data x:d){
			double temp=x.getTfidfScore();
			temp=(x.getTfidfScore()/maxTF)*0.4;
			x.setTfidfScore(temp);
		}
		double maxRank=0.0;
		maxRank=(double)Collections.max(ranks.values());
		System.out.println("Max rank : ");
		for(Map.Entry<String, Double> entry:ranks.entrySet()){
			double tempRank=0.0;
			tempRank=(double)entry.getValue();
			tempRank=(tempRank/maxRank)*0.6;
			System.out.println(tempRank );
			entry.setValue(tempRank);
		}

		Iterator entries2 = ranks.entrySet().iterator();
		while (entries2.hasNext()) {
			Entry thisEntry2 = (Entry) entries2.next();

			for(Data x:d){
				if(thisEntry2.getKey().equals(x.getFile().getAbsolutePath())){
					x.setLinkScore((double)thisEntry2.getValue());
				}
			}


		}

		for(Data x:d){

			double a=(x.getTfidfScore()/2)+(x.getLinkScore()/2);
			x.setAvg(a);
		}
		System.out.println("linkscore: " + d.get(1).getLinkScore());

		for (int i = 0; i <= d.size()-2; i++) {
			int smallest=i;
			for (int j = i+1; j <= d.size()-1; j++) {
				/*if(Integer.parseInt(num[smallest])>Integer.parseInt(num[j])){
					smallest=j;
				}*/
				if(d.get(smallest).getAvg()>d.get(j).getAvg()){
					smallest=j;
				}
			}
			Collections.swap(d, smallest, i);
			/*Data temp=d.get(smallest);
			d.get(smallest)=d.get(i);
			num[i]=Integer.toString(temp);*/
		}



		//System.out.println("="+d.size());
		//TestCalcPageRank x=new TestCalcPageRank();
		/*ArrayList<RankModel> p=x.getLinkAnalysis();
		for(Data da:d){
			for(RankModel p2:p){
				if(da.file.getAbsolutePath().equals(p2.f.getAbsolutePath())){
					da.setLinkScore(p2.incomingLinks);
					//da.setAvg((da.getLinkScore()+da.getTfidfScore())/2);
				}
			}
			PageRank pgrank=new PageRank();
			da.setLinkScore(pgrank.pageRank());
			//da.setAvg((da.getLinkScore()+da.getTfidfScore())/2);
		}*/
		
		//tfidf/maxtfidf * 0.4
		this.getServletContext().setAttribute("Files", d);
		request.getRequestDispatcher("/WEB-INF/Display.jsp").forward(request, response);
	}

}
