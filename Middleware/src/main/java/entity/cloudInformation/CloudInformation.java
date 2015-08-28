package entity.cloudInformation;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne(targetEntity=CloudInstance.class)
    @JoinColumn(name="cloudinstance")
	private CloudInstance cloudinstance;
	
	@Id
    @GeneratedValue
    private Long id;
	
	private String fetcher;

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
		return cloudinstance;
	}

	public void setInstance(CloudInstance instance) {
		this.cloudinstance = instance;
	}

	public void setFetcher(String fetcher) {
		this.fetcher = fetcher;
	}
	
	public String getFetcher(){
		return this.fetcher;
	}
	
}
