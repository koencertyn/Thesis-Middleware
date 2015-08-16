package controller.cloud;

import java.util.List;

import enums.Property;

public class CloudConfig {
	
	List<Property> properties;
	
	public CloudConfig(List<Property> properties){
		this.properties = properties;
		
	}
	
	String instance;
	
	public void setInstance(String instance){
		this.instance = instance;
	}
	
	public String getInstance(){
		return this.instance;
	}

}
