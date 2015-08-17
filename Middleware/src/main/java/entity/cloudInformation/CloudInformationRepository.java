package entity.cloudInformation;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import entity.cloudInstance.CloudInstance;

@ApplicationScoped
public class CloudInformationRepository {

    @Inject
    private EntityManager em;

    public CloudInformation findById(Long id) {
        return em.find(CloudInformation.class, id);
    }

    public List<CloudInformation> findAll() {
        CriteriaQuery<CloudInformation> cq = em.getCriteriaBuilder().createQuery(CloudInformation.class);
        cq.select(cq.from(CloudInformation.class));
        return em.createQuery(cq).getResultList();
    }
    
    public CloudInformation findByKey(String key) {
    	for (CloudInformation inf : findAll()) {
			if(inf.getIdentifier().equals(key)){
				return inf;
			}
		}
    	return null;
    }
    
    public CloudInformation findByInstance(CloudInstance instance) {
    	for (CloudInformation inst : findAll()) {
			if(inst.getInstance().equals(instance)){
				return inst;
			}
		}
    	return null;
    }
}