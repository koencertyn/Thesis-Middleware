package entity.cloudInstance;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import entity.cloudInformation.CloudInformation;
import enums.Purpose;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table
public class CloudInstance {
	
	private String platform;
	private String url;
	private Purpose goal;
	private String platformInstanceName;
	
	public String getPlatformInstanceName() {
		return platformInstanceName;
	}

	public void setPlatformInstanceName(String platformInstanceName) {
		this.platformInstanceName = platformInstanceName;
	}

	@Id
    @GeneratedValue
    private Long id;

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Purpose getGoal() {
		return goal;
	}

	public void setGoal(Purpose goal) {
		this.goal = goal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToMany(targetEntity=CloudInformation.class,mappedBy="cloudinstance",cascade={CascadeType.ALL},orphanRemoval=true)
    Set<CloudInformation> information = new HashSet<CloudInformation>();
	
}
