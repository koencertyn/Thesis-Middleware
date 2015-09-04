package monitor;

import javax.inject.Inject;

import resources.Configs;
import entity.request.RequestRepository;

public class MonitorMeasurement {
	
	@Inject
	private RequestRepository reqRepo;
	
	private double load;
	private double processor;
	private double memory;
	private int nbRequests;
	
	public MonitorMeasurement(double load, double processor, double memory){
		setLoad(load);
		setProcessor(processor);
		setMemory(memory);
	}
	public int getNbRequests() {
		return nbRequests;
	}
	public void setNbRequests(int reqs) {
		this.nbRequests = reqs;
	}
	public double getLoad() {
		return load;
	}
	public void setLoad(double load) {
		this.load = load;
	}
	public double getProcessor() {
		return processor;
	}
	public void setProcessor(double processor) {
		this.processor = processor;
	}
	public double getMemory() {
		return memory;
	}
	public void setMemory(double memory) {
		this.memory = memory;
	}
	
	private boolean handled = false;
	
	public void setHandled(){
		this.handled = true;
	}
	
	private boolean possibleDanger = false;
	
	public void isDanger(){
		this.possibleDanger = true;
	}
	
	public void noDanger(){
		this.possibleDanger = false;
	}
	
	public boolean hasDanger(){
		return this.possibleDanger;
	}
	

}
