package entity.cloudInformation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import entity.cloudInstance.CloudInstance;

@Entity
@XmlRootElement
@Table
public class CloudInformation {
	
	private String identifier;
	
	@MapsId @OneToOne
	@JoinColumn(name = "instance_id")
	private CloudInstance instance;
	
	@Id
    @GeneratedValue
    private Long id;

	public Long getId() {
		return id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public CloudInstance getInstance() {
		return instance;
	}

	public void setInstance(CloudInstance instance) {
		this.instance = instance;
	}
	
}
