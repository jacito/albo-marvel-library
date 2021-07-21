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
package albo.comics.library.load;

import albo.comics.library.dao.InitialDAO;
import static albo.comics.library.load.AlboComicsLibraryContext.CHARACTERS_MARVEL_QUEUE;
import albo.comics.library.marvel.client.MarvelClient;
import albo.comics.library.model.CharacterMarvel;
import albo.comics.library.model.CharacterPrincipal;
import com.albo.library.json.characters.Characters;
import com.albo.library.json.characters.Result;
import java.util.Date;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jacito
 */
@Model
public class InformationBasic {

    private static final Logger LOG = LogManager.getLogger(InformationBasic.class);

    @Inject
    private MarvelClient marvelClient;
    @Inject
    private InitialDAO initialDAO;

    public void initial() {
        List<CharacterPrincipal> charactersPrincipal = initialDAO.findAll(CharacterPrincipal.class);
        for (CharacterPrincipal characterPrincipal : charactersPrincipal) {
            characterPrincipal.setSystemUpdateDate(new Date());
            characterPrincipal = initialDAO.update(characterPrincipal);
            LOG.debug("Load [{}] information", characterPrincipal.getName());
            Characters characters = marvelClient.getCharacters(characterPrincipal.getMarvelName());
            for (Result result : characters.getData().getResults()) {
                CHARACTERS_MARVEL_QUEUE.add(new CharacterMarvel(characterPrincipal, result));
            }
            LOG.trace(characterPrincipal);
        }
    }

}
