package calculationManagement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table
public class CalculationInstance {
	
	
	@Id
    @GeneratedValue
    private Long id;
	
	private Long givenID;
	
	private String value;
	
	public String getValue(){
		return this.value;
	}
	
	public Long getGivenID(){
		return this.givenID;
	}
	
	public void setGivenID(Long id){
		this.givenID = id;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
}
