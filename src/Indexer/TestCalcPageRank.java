package Indexer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestCalcPageRank {
	Map<String, RankModel> outLinks=new HashMap<String, RankModel>();
	Map<String, RankModel> inLinks=new HashMap<String, RankModel>();
	ArrayList<String> allFiles=new ArrayList<String>();

	public void getLinkAnalysis(){
		
		//t.getOutLinks("C:\\Jugal\\Courses\\CS454_SearchEngine\\WebCrawlerStorage\\html\\1_ocean.pcwerk.com.html");
		createIndex("C:\\Jugal\\Courses\\CS454_SearchEngine\\WebCrawlerStorage\\html");
		System.out.println("== " + outLinks.size());
		setOutLinks();
		System.out.println("Out L over");
		setIn();
		
		//System.out.println("-------------"+outLinks.size());
		//Iterator entries = t.outLinks.entrySet().iterator();
		ArrayList<RankModel> r=new ArrayList<RankModel>();
		/*while (entries.hasNext()) {
			Entry thisEntry = (Entry) entries.next();

			RankModel value = (RankModel) thisEntry.getValue();
			r.add(value);
			String w=value.getF().getAbsolutePath()+" In : "+value.getIncomingLinks() +" Out : "+value.getOutgoingLinks()+"\n";
			System.out.println(w);
		}*/

	}
	/*public static void main(String[] args){

		TestCalcPageRank t=new TestCalcPageRank();
		//t.getOutLinks("C:\\Jugal\\Courses\\CS454_SearchEngine\\WebCrawlerStorage\\html\\1_ocean.pcwerk.com.html");
		t.createIndex("C:\\Jugal\\Courses\\CS454_SearchEngine\\en");
		System.out.println("== " + t.m.size());
		t.setOutLinks();

		System.out.println("size : "+t.m.size());
		t.getInLinks();
		Iterator entries = t.m.entrySet().iterator();
		try {
			FileWriter fw = new FileWriter(new File("C:\\Jugal\\Courses\\CS454_SearchEngine\\WebCrawlerStorage\\Map.txt"));
			BufferedWriter bw= new BufferedWriter(fw);
			while (entries.hasNext()) {
				Entry thisEntry = (Entry) entries.next();

				RankModel value = (RankModel) thisEntry.getValue();
				String w=value.getF().getAbsolutePath()+" In : "+value.getIncomingLinks() +" Out : "+value.getOutgoingLinks()+"\n";
				System.out.println(w);

				bw.write(w);
				//System.out.println("Path : "+value.getF().getAbsolutePath()+" "+value.getIncomingLinks() +" "+value.getOutgoingLinks());

			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	 */
	public void getInLinks(){
		Iterator entries = outLinks.entrySet().iterator();
		while (entries.hasNext()) {
			Entry thisEntry = (Entry) entries.next();

			RankModel value = (RankModel) thisEntry.getValue();
			setInLinks(value.f.getAbsolutePath());

		}

	}

	public void setIn(){
		for (int i = 0; i < allFiles.size(); i++) {


			File f=new File(allFiles.get(i));
			//System.out.println(f);

			try {
				Document doc = Jsoup.parse(f, null);
				Elements linksOnPage = doc.select("a[href]");
				RankModel r;
				String hrefValue="";
				String key;
				//ArrayList<String> out=new ArrayList<String>();
				//m.put(allFiles.get(i), new RankModel(new File(allFiles.get(i)),0,linksOnPage.size()));
				for(Element e:linksOnPage){
					hrefValue=e.attr("href");
					
					key="";
					if(hrefValue.endsWith(".html")){
						//String[] y=hrefValue.split("articles");
						//y[1]=y[1].replace("/", "\\");
						key="C:\\Jugal\\Courses\\CS454_SearchEngine\\WebCrawlerStorage\\html\\"+hrefValue;
						System.out.println("Key : " + key);
						File v=new File(key);
						if(inLinks.get(key)!=null){
							//System.out.println("Obj from in Map");
							r=inLinks.get(key);
							ArrayList<String> arr=r.getInL();
							arr.add(allFiles.get(i));
							r.setInL(arr);
							inLinks.put(allFiles.get(i), r);
						}else if(v.exists()){
							//System.out.println("new entry");
							RankModel x=new RankModel(v);
							//x.inL.add(allFiles.get(i));
							ArrayList<String> arr=x.getInL();
							arr.add(allFiles.get(i));
							x.setInL(arr);
							inLinks.put(allFiles.get(i),x);
						}

					}
				}
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}

	public void setInLinks(String link){
		File f=new File(link);
		//System.out.println("link to set Inlinks "+f.getAbsolutePath());
		//System.out.println(f);

		try {
			Document doc = Jsoup.parse(f, null);
			Elements linksOnPage = doc.select("a[href]");
			ArrayList<String> in;
			for(Element e:linksOnPage){
				String hrefValue=e.attr("href");
				String key="";


				if(hrefValue.endsWith(".html") && hrefValue.contains("articles")){
					//String[] y=hrefValue.split("articles");
					//y[1]=y[1].replace("/", "\\");
					key="C:\\Jugal\\Courses\\CS454_SearchEngine\\WebCrawlerStorage\\html\\"+hrefValue;
					System.out.println("Key to Access "+ key);
					if(outLinks.get(key)!=null){
						RankModel k=outLinks.get(key);
						in=k.getInL();
						in.add(link);
						k.setInL(in);
						outLinks.put(key, k);
						//m.get(key).setInL(in);
						//System.out.println(k.f.getAbsolutePath());
						//m.get(key).setIncomingLinks(m.get(key).getIncomingLinks()+1);
					}
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void createIndex(String dataDirPath){
		//get all files in the data directory
		File[] files = new File(dataDirPath).listFiles();

		for (File file : files) {
			if(!file.isDirectory()
					&& !file.isHidden()
					&& file.exists()
					){
				//add to Map
				// m.put(file.getAbsolutePath(), new RankModel(file,0,getOutLinks(file.getAbsolutePath())));
				allFiles.add(file.getAbsolutePath());
			}else{
				createIndex(file.getAbsolutePath());
			}
		}
		// return writer.numDocs();
	}

	public int getOutLinks(String link){
		File f=new File(link);
		//System.out.println(f);
		int x=0;
		try {
			Document doc = Jsoup.parse(f, null);
			Elements linksOnPage = doc.select("a[href]");
			x=linksOnPage.size();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return x;
	}
	public void setOutLinks(){
		for (int i = 0; i < allFiles.size(); i++) {


			File f=new File(allFiles.get(i));
			//System.out.println(f);

			try {
				Document doc = Jsoup.parse(f, null);
				Elements linksOnPage = doc.select("a[href]");
				ArrayList<String> out=new ArrayList<String>();
				//m.put(allFiles.get(i), new RankModel(new File(allFiles.get(i)),0,linksOnPage.size()));
				for(Element e:linksOnPage){
					String hrefValue=e.attr("href");
					String key="";
					if(hrefValue.endsWith(".html") ){
						//String[] y=hrefValue.split("articles");
						//y[1]=y[1].replace("/", "\\");
						key="C:\\Jugal\\Courses\\CS454_SearchEngine\\WebCrawlerStorage\\html\\"+hrefValue;
						out.add(key);

					}else{
						out.add(e.attr("href"));
					}
				}
				RankModel rm=new RankModel(new File(allFiles.get(i)));
				rm.setOutL(out);
				outLinks.put(allFiles.get(i), rm);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}
	public Map<String, RankModel> getOutLinks() {
		return outLinks;
	}
	public void setOutLinks(Map<String, RankModel> outLinks) {
		this.outLinks = outLinks;
	}
	public ArrayList<String> getAllFiles() {
		return allFiles;
	}
	public void setAllFiles(ArrayList<String> allFiles) {
		this.allFiles = allFiles;
	}
	public void setInLinks(Map<String, RankModel> inLinks) {
		this.inLinks = inLinks;
	}
	public Map<String, RankModel> getInLinks2(){
		return inLinks;
	}

}
