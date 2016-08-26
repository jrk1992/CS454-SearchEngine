package Indexer;

import java.io.File;
import java.util.ArrayList;

public class RankModel {
	File f;
	int incomingLinks,outgoingLinks;
	ArrayList<String> inL,outL;
	
	public RankModel(File f, int incomingLinks, int outgoingLinks) {
		
		this.f = f;
		this.incomingLinks = incomingLinks;
		this.outgoingLinks = outgoingLinks;
	}
	public RankModel(File f){
		this.f=f;
		this.inL=new ArrayList<String>();
		this.outL=new ArrayList<String>();
	}
	
	public ArrayList<String> getInL() {
		return inL;
	}
	public void setInL(ArrayList<String> inL) {
		this.inL = inL;
	}
	public ArrayList<String> getOutL() {
		return outL;
	}
	public void setOutL(ArrayList<String> outL) {
		this.outL = outL;
	}
	public File getF() {
		return f;
	}
	public void setF(File f) {
		this.f = f;
	}
	public int getIncomingLinks() {
		return incomingLinks;
	}
	public void setIncomingLinks(int incomingLinks) {
		this.incomingLinks = incomingLinks;
	}
	public int getOutgoingLinks() {
		return outgoingLinks;
	}
	public void setOutgoingLinks(int outgoingLinks) {
		this.outgoingLinks = outgoingLinks;
	}
	
	
	
	
	

}
