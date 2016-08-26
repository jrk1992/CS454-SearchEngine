package Indexer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;


public class LuceneTester {
	ArrayList<Data> x=new ArrayList<Data>();
	String indexDir = "C:\\Jugal\\Courses\\CS454_SearchEngine\\WebCrawlerStorage\\index";
	String dataDir = "C:\\Jugal\\Courses\\CS454_SearchEngine\\WebCrawlerStorage\\html";
	Indexer indexer;
	Searcher searcher;
	public static void main(String[] args) throws IOException {
		LuceneTester tester=new LuceneTester();
		tester.createIndex();
		

	}

	private void createIndex() {
		try{
			indexer = new Indexer(indexDir);
			int numIndexed;
			long startTime = System.currentTimeMillis();
			numIndexed = indexer.createIndex(dataDir);
			long endTime = System.currentTimeMillis();
			indexer.close();
			System.out.println(numIndexed + " File indexed, time taken: "
					+ (endTime - startTime) + " ms");
		}catch(Exception e){

		}
	}

	public void sortUsingRelevance(String searchQuery,ArrayList<Data> d){
		try{
			searcher = new Searcher(indexDir);
			long startTime = System.currentTimeMillis();
			// create a term to search file name
			Term term = new Term(LuceneConstants.CONTENTS, searchQuery);
			// create the term query object
			Query query = new FuzzyQuery(term,0.8f);
			searcher.setDefaultFieldSortScoring(true, false);
			// do the search

			TopDocs hits = searcher.search(query, Sort.RELEVANCE);
			long endTime = System.currentTimeMillis();

			System.out.println(hits.totalHits + " documents found. Time :"
					+ (endTime - startTime) + "ms");
			/*FileWriter fw = new FileWriter(new File("C:\\Jugal\\Courses\\CS454_SearchEngine\\WebCrawlerStorage\\Score.txt"));
		BufferedWriter bw= new BufferedWriter(fw);*/
			String pprint = "";
			for (ScoreDoc scoreDoc : hits.scoreDocs) {
				Document doc = searcher.getDocument(scoreDoc);

				Data data=new Data(0.0, scoreDoc.score , new File(doc.get(LuceneConstants.FILE_PATH)));
				d.add(data);
				pprint += "Score: " + scoreDoc.score + " #*.*# " + "File: " + doc.get(LuceneConstants.FILE_PATH)+"\n";
			}
			//bw.write(pprint);

			searcher.close();
		}catch(Exception e){
			System.out.println("Error");
		}
	}

	/*public void sortUsingIndex(String searchQuery) throws IOException,
	ParseException {
		searcher = new Searcher(indexDir);
		long startTime = System.currentTimeMillis();
		// create a term to search file name
		Term term = new Term(LuceneConstants.CONTENTS, searchQuery);
		// create the term query object
		Query query = new FuzzyQuery(term);
		searcher.setDefaultFieldSortScoring(true, false);
		// do the search
		TopDocs hits = searcher.search(query, Sort.INDEXORDER);
		long endTime = System.currentTimeMillis();

		System.out.println(hits.totalHits + " documents found. Time :"
				+ (endTime - startTime) + "ms");
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			System.out.print("Score: " + scoreDoc.score + " ");
			System.out.println("File: " + doc.get(LuceneConstants.FILE_PATH));
		}
		searcher.close();
	}
	 */
}