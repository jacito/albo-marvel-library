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

import albo.comics.library.model.CharacterPrincipal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jacito
 */
@Stateless
public class ServicesDAO extends AlboComicsLibraryPUDAO {

    private static final Logger LOG = LogManager.getLogger(InitialDAO.class);

    private static final String GET_ARTISTS = "\n"
            + "SELECT C.name, C.role\n"
            + "FROM creator C\n"
            + "     JOIN comic CO ON CO.id = C.comic_id\n"
            + "WHERE CO.character_principal_id = ?    \n"
            + "GROUP BY C.name, C.role";

    private static final String GET_RELATIONSHIP = "\n"
            + "SELECT C.marvel_title, CR.name \n"
            + "FROM characters_relationship CR\n"
            + "     JOIN comic C ON C.id = CR.comic_id\n"
            + "     JOIN character_principal CP ON CP.id = CR.character_principal_id \n"
            + "WHERE CR.character_principal_id = ? AND CR.name NOT LIKE CONCAT('%', CP.marvel_name, '%')\n"
            + "GROUP BY CR.name, C.marvel_title";

    public CharacterPrincipal getCharacterPrincipalByTemplate(String template) {
        try {
            Query query = em.createNamedQuery("CharacterPrincipal.findByTemplate");
            query.setParameter("template", template);
            return (CharacterPrincipal) query.getSingleResult();
        } catch (NoResultException ex) {
            LOG.trace("NoResult by :: template [{}] ", template);
            return null;
        }

    }

    public HashMap<String, List<String>> getArtists(Integer idCharacter) {
        return getHashMap(idCharacter, GET_ARTISTS);
    }

    public HashMap<String, List<String>> getRelationships(Integer idCharacter) {
        return getHashMap(idCharacter, GET_RELATIONSHIP);
    }

    private HashMap<String, List<String>> getHashMap(Integer idCharacter, String queryString) {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        try {
            Query query = em.createNativeQuery(queryString);
            query.setParameter(1, idCharacter);

            LOG.trace("Excecute :: {}", query.toString());
            LOG.trace("Param :: idCharacter [{}]", idCharacter);

            List<Object[]> result = query.getResultList();
            result.forEach((object) -> {
                String name = (String) object[0];
                String role = (String) object[1];

                if (!hashMap.containsKey(role)) {
                    hashMap.put(role, new ArrayList<>());
                }
                hashMap.get(role).add(name);
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return hashMap;
    }

}
