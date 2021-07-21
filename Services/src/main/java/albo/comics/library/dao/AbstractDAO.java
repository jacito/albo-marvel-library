/*
 * 
 * Jazmín Velázquez Bustos 2021. All Rights Reserved.
 * JACITO 2021. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of JACITO
 * ("Confidential Information"). You shall not disclose such Confidential 
 * Information and shall use it only in accordance with the terms of the license 
 * agreement you entered into with  Jazmín V.B or his authorized licensees. 
 * 
 * JACITO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE 
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR 
 * NON-INFRINGEMENT. JACITO SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY 
 * LICENSEE AS A RESULT OF USING,MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 *
 */
package albo.comics.library.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jacito
 */
public abstract class AbstractDAO {
    
    protected abstract EntityManager getEntityManager();
    
    public <T> T persist(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        getEntityManager().refresh(entity);
        return entity;
    }
    
    public <T> void persist(List<T> entities) {
        entities.stream().forEachOrdered(entity -> {
            getEntityManager().persist(entity);
            getEntityManager().flush();
        });
    }
    
    public <T> T update(T entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
        //getEntityManager().refresh(entity);
        return entity;
    }
    
    public <T> void update(List<T> entities) {
        entities.stream().forEachOrdered(entity -> {
            getEntityManager().merge(entity);
            getEntityManager().flush();
        });
    }
    
    public <T> void delete(Class<T> entityClass, Object id) {
        T reference = getEntityManager().getReference(entityClass, id);
        getEntityManager().remove(reference);
        getEntityManager().flush();
    }
    
    public <T> List<T> findAll(Class<T> entityClass) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public <T> T find(Class<T> entityClass, Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    public <T> List<T> findByNamedQuery(String namedQueryName) {
        return getEntityManager().createNamedQuery(namedQueryName).getResultList();
    }
    
    public <T> List<T> findByNamedQuery(String namedQueryName,
            Map<String, Object> parameters) {
        return findByNamedQuery(namedQueryName, parameters, 0);
    }
    
    public <T> T findByNamedQueryUnique(String namedQueryName,
            Map<String, Object> parameters) {
        try {
            List<T> result = findByNamedQuery(namedQueryName, parameters, 0);
            return result.get(0);
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    public <T> List<T> findByNamedQuery(String namedQueryName, int resultLimit) {
        return getEntityManager().createNamedQuery(namedQueryName).
                setMaxResults(resultLimit).getResultList();
    }
    
    public <T> List<T> findByNamedQuery(String namedQueryName,
            Map<String, Object> parameters, int resultLimit) {
        Set<Map.Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = getEntityManager().createNamedQuery(namedQueryName);
        
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        rawParameters.forEach((entry) -> {
            query.setParameter(entry.getKey(), entry.getValue());
        });
        return query.getResultList();
    }
    
    public <T> List<T> findByNativeQuery(String sql, Class type) {
        return getEntityManager().createNativeQuery(sql, type).getResultList();
    }
    
}
