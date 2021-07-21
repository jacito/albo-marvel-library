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
package albo.comics.library.ws;

import albo.comics.library.dao.ServicesDAO;
import albo.comics.library.model.CharacterPrincipal;
import com.albo.library.json.response.ArtistsResponse;
import com.albo.library.json.response.CharactersResponse;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author jacito
 */
@Model
public class ServicesManager {

    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    public static final String LABEL_LAST_SYNC = "Fecha de la última sincronización en ";

    @Inject
    ServicesDAO servicesDAO;

    protected ArtistsResponse getColaborators(String template) {
        CharacterPrincipal characterPrincipal = servicesDAO.getCharacterPrincipalByTemplate(template);
        if (characterPrincipal != null) {
            HashMap<String, List<String>> artist = servicesDAO.getArtists(characterPrincipal.getId());
            return new ArtistsResponse(LABEL_LAST_SYNC + SDF.format(characterPrincipal.getSystemUpdateDate()), artist);
        }
        return null;
    }

    protected CharactersResponse getCharacters(String template) {
        CharacterPrincipal characterPrincipal = servicesDAO.getCharacterPrincipalByTemplate(template);
        if (characterPrincipal != null) {
            HashMap<String, List<String>> characters = servicesDAO.getRelationships(characterPrincipal.getId());
            CharactersResponse charactersResponse = new CharactersResponse(LABEL_LAST_SYNC + SDF.format(characterPrincipal.getSystemUpdateDate()));
            characters.keySet().forEach((key) -> {
                charactersResponse.getCharacters().add(new com.albo.library.json.response.Character(key, characters.get(key)));
            });
            return charactersResponse;
        }
        return null;
    }

}
