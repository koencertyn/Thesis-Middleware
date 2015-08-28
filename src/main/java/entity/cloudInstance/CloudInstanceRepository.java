/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the 
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package entity.cloudInstance;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import enums.Purpose;

@ApplicationScoped
public class CloudInstanceRepository {

    @Inject
    private EntityManager em;

    public CloudInstance findById(Long id) {
        return em.find(CloudInstance.class, id);
    }

    public List<CloudInstance> findAll() {
        CriteriaQuery<CloudInstance> cq = em.getCriteriaBuilder().createQuery(CloudInstance.class);
        cq.select(cq.from(CloudInstance.class));
        return em.createQuery(cq).getResultList();
    }
    
    public List<CloudInstance> findNonUsedInstance() {
    	List<CloudInstance> result = new ArrayList<CloudInstance>();
    	for (CloudInstance inst : findAll()) {
			if(inst.getGoal().equals(Purpose.NONE)){
				result.add(inst);
			}
		}
    	return result;
    }

	public CloudInstance findCertainInstance(Purpose purpose) {
		for (CloudInstance inst : findAll()) {
			if(inst.getGoal().equals(purpose)){
				return inst;
			}
		}
    	return null;
		
	}
}
