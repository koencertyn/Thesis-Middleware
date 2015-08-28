package controller.cloud;

import java.util.ArrayList;

import enums.Property;

public class CloudPick {

	private String bestCloud;

	private ArrayList<Property> properties;
	
	public CloudPick(ArrayList<Property> p){
		this.properties = p;
	}
	
	public void setBestCloud(String cloud){
		this.bestCloud = cloud;
	}
	
	public String getBestCloud(){
		return this.bestCloud;
	}
	
	public ArrayList<Property> getProperties(){
		return this.properties;
	}
}
