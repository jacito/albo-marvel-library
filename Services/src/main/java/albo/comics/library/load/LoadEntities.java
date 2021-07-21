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
import static albo.comics.library.load.AlboComicsLibraryContext.CHARACTERS_RELATIONSHIP_QUEUE;
import albo.comics.library.model.CharactersRelationship;
import albo.comics.library.model.Creator;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static albo.comics.library.load.AlboComicsLibraryContext.COMICS_QUEUE;
import static albo.comics.library.load.AlboComicsLibraryContext.CREATORS_QUEUE;
import albo.comics.library.marvel.client.MarvelClient;
import albo.comics.library.model.CharacterMarvel;
import albo.comics.library.model.Comic;
import com.albo.library.json.comics.Comics;
import com.albo.library.json.comics.Item;
import com.albo.library.json.comics.Item__1;

/**
 *
 * @author jacito
 */
@Stateless
public class LoadEntities {

    private static final Logger LOG = LogManager.getLogger(LoadEntities.class);

    @Inject
    private InitialDAO initialDAO;
    @Inject
    private MarvelClient marvelClient;
    @Inject
    private InformationBasic informationBasic;

    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
    private void characterMarvel() {
        LOG.debug("RUN characterMarvel load process ... ");
        try {
            CharacterMarvel characterMarvel;
            while ((characterMarvel = (CHARACTERS_MARVEL_QUEUE.isEmpty() ? null : CHARACTERS_MARVEL_QUEUE.remove())) != null) {
                characterMarvel = initialDAO.loadInformationCharacters(characterMarvel);
                loadComics(characterMarvel);
            }
        } catch (Exception ex) {
            LOG.warn(ex);
        }
    }

    @Schedule(second = "*/20", minute = "*", hour = "*", persistent = false)
    private void comics() {
        LOG.debug("RUN comics load process ... ");
        try {
            Comic comic;
            while ((comic = (COMICS_QUEUE.isEmpty() ? null : COMICS_QUEUE.remove())) != null) {
                Comic auxComic = initialDAO.loadInformationComics(comic);
                if (comic.getResult() != null) {
                    for (Item item : comic.getResult().getCreators().getItems()) {
                        CREATORS_QUEUE.add(new Creator(item, auxComic));
                    }
                    for (Item__1 item : comic.getResult().getCharacters().getItems()) {
                        CHARACTERS_RELATIONSHIP_QUEUE.add(new CharactersRelationship(item, auxComic.getCharacterPrincipalId(), auxComic));
                    }
                    LOG.trace(comic);
                }
            }
        } catch (Exception ex) {
            LOG.warn(ex);
        }
    }

    @Schedule(second = "*/40", minute = "*", hour = "*", persistent = false)
    private void creators() {
        LOG.debug("RUN creators load process ... ");
        try {
            Creator creator;
            while ((creator = (CREATORS_QUEUE.isEmpty() ? null : CREATORS_QUEUE.remove())) != null) {
                initialDAO.loadInformationCreators(creator);
            }
        } catch (Exception ex) {
            LOG.warn(ex);
        }
    }

    @Schedule(second = "*/50", minute = "*", hour = "*", persistent = false)
    private void charactersRelationship() {
        LOG.debug("RUN charactersRelationship load process ... ");
        try {
            CharactersRelationship charactersRelationship;
            while ((charactersRelationship = (CHARACTERS_RELATIONSHIP_QUEUE.isEmpty() ? null : CHARACTERS_RELATIONSHIP_QUEUE.remove())) != null) {
                initialDAO.loadInformationCharactersRelationship(charactersRelationship);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Schedule(second = "*", minute = "*", hour = "*/6", persistent = false)
    private void all() {
        LOG.debug("Update system information ... ");
        try {
            informationBasic.initial();
        } catch (Exception ex) {
            LOG.warn(ex);
        }
    }

    private void loadComics(CharacterMarvel character) {
        if (character.getMarvelComicsAvailable() > 0) {
            Comics marvelComic = marvelClient.getComics(Integer.toString(character.getMarvelId()));
            LOG.trace("Generate entities by json :: Comics [{}]", marvelComic.getCode());
            marvelComic.getData().getResults().forEach((result) -> {
                COMICS_QUEUE.add(new Comic(character.getCharacterPrincipalId(), result));
            });
        }
    }

}
