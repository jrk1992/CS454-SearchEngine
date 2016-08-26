package Indexer;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class TestRank {
	public static void main(String[] args){
		Rank r=new Rank();
		try {
			r.start("C:\\Jugal\\Courses\\CS454_SearchEngine\\en\\articles\\(\\1\\5\\");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
