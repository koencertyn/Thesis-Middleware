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
package entity.request;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

@ApplicationScoped
public class RequestRepository {

    @Inject
    private EntityManager em;

    public Request findById(Long id) {
        return em.find(Request.class, id);
    }

    public List<Request> findAll() {
        CriteriaQuery<Request> cq = em.getCriteriaBuilder().createQuery(Request.class);
        cq.select(cq.from(Request.class));
        return em.createQuery(cq).getResultList();
    }
    
    public int findFromLastInterval(int seconds) {
    	int res = 0;
    	for (Request req : findAll()) {
			if(req.getRequestDate().after(new Date(System.currentTimeMillis() - seconds * 1000)))
				res++;
		}
    	return res;
    }
}
