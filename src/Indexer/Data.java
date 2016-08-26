package Indexer;

import java.io.File;

public class Data implements Comparable<Data> {

	double linkScore,tfidfScore,avg;
	File file;


	public Data(double linkScore, double tfidfScore, File file) {

		this.linkScore = linkScore;
		this.tfidfScore = tfidfScore;
		this.file = file;
	}
	public double getLinkScore() {
		return linkScore;
	}
	public void setLinkScore(double linkScore) {
		this.linkScore = linkScore;
	}
	public double getTfidfScore() {
		return tfidfScore;
	}
	public void setTfidfScore(double tfidfScore) {
		this.tfidfScore = tfidfScore;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	@Override
	public int compareTo(Data o) {
		// TODO Auto-generated method stub
		if(this.linkScore>o.getTfidfScore())
			return -1;
		else if(this.linkScore<o.getTfidfScore())
			return 1;
		else
			return 0;
	}



}
