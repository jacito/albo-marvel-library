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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JACITO
 */
public class AlboComicsLibraryPUDAO extends AbstractDAO {
    
    @PersistenceContext(unitName = "AlboComicsLibrary_PU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
}
