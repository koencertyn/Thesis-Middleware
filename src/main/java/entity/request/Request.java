package entity.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import enums.Action;
import enums.Priority;
import enums.Property;
import enums.Purpose;
/**
 * Created by koencertyn on 25/10/14.
 */

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table
public class Request {
	
	public Request(){
		setRequestDate(new Date());
	}
	
	private Priority prio = null;
	
	private Date requestDate = null;
	
	@ElementCollection
	private Map<String, String> content;
	
	@ElementCollection
	private Map<String, Integer> meta;
	
	public Map<String, Integer> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, Integer> meta) {
		this.meta = meta;
	}

	private ArrayList<Property> properties = new ArrayList<Property>();
	
	private Purpose purpose = null;

    public Date getRequestDate() {
		return requestDate;
	}

	public Map<String, String> getContent() {
		return content;
	}

	public void setContent(Map<String, String> map) {
		this.content = map;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	private boolean handled = false;
	
	public boolean getHandled(){
		return this.handled;
	}

    @Id
    @GeneratedValue
    private Long id;
    
    public Long getID(){
    	return this.id;
    }

    public void setHandled(){
        handled = true;
    }

    public Priority getPriority() {
        return prio;
    }

    public void setPriority(Priority prio) {
        this.prio = prio;
    }

	public ArrayList<Property> getProperties() {
		return properties;
	}

	public void setProperties(ArrayList<Property> properties) {
		this.properties = properties;
	}

	public Purpose getPurpose() {
		return purpose;
	}

	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}
	
	private Action action;
	
	public Action getAction(){
		return this.action;
	}
	
	public void setAction(Action action){
		this.action = action;
	}

}

