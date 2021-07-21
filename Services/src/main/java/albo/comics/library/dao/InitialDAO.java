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

import albo.comics.library.model.CharacterMarvel;
import albo.comics.library.model.CharactersRelationship;
import albo.comics.library.model.Comic;
import albo.comics.library.model.Creator;
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
public class InitialDAO extends AlboComicsLibraryPUDAO {

    private static final Logger LOG = LogManager.getLogger(InitialDAO.class);

    public CharacterMarvel loadInformationCharacters(CharacterMarvel characterMarvel) {
        CharacterMarvel auxCharacterMarvel = getCharacterMarvelByMarvelId(characterMarvel);
        auxCharacterMarvel = loadEntity(auxCharacterMarvel.getId(), auxCharacterMarvel);
        LOG.debug("Loaded CharacterMarvel [{} - {}] :: {}",
                auxCharacterMarvel.getId(), auxCharacterMarvel.getMarvelId(), auxCharacterMarvel.getMarvelName());
        return auxCharacterMarvel;
    }

    public Comic loadInformationComics(Comic comic) {
        Comic auxComic = getComicByMarvelId(comic);
        auxComic = loadEntity(auxComic.getId(), auxComic);
        LOG.debug("Loaded Comic [{} - {}] :: {}", auxComic.getId(), auxComic.getMarvelId(), auxComic.getMarvelTitle());
        return auxComic;
    }

    public void loadInformationCreators(Creator creator) {
        Creator auxCreator = getCreatorByAll(creator);
        auxCreator = loadEntity(auxCreator.getId(), auxCreator);
        LOG.debug("Loaded Creator [{} - {}] :: {}", auxCreator.getId(), auxCreator.getName(), auxCreator.getRole());

    }

    public void loadInformationCharactersRelationship(CharactersRelationship characterRelationship) {
        CharactersRelationship auxCharactersRelationship = getCharactersRelationshipByAll(characterRelationship);
        auxCharactersRelationship = loadEntity(auxCharactersRelationship.getId(), auxCharactersRelationship);
        LOG.debug("Loaded CharactersRelationship [{} - {}] :: {}",
                auxCharactersRelationship.getId(),
                auxCharactersRelationship.getMarvelId(),
                auxCharactersRelationship.getName());

    }

    private CharactersRelationship getCharactersRelationshipByAll(CharactersRelationship charactersRelationship) {
        try {
            Query query = em.createNamedQuery("CharactersRelationship.findByAll");
            query.setParameter("characterPrincipalId", charactersRelationship.getCharacterPrincipalId());
            query.setParameter("name", charactersRelationship.getName());
            query.setParameter("marvelId", charactersRelationship.getMarvelId());
            query.setParameter("comicId", charactersRelationship.getComicId());
            CharactersRelationship aux = (CharactersRelationship) query.getSingleResult();
            return aux.load(charactersRelationship);
        } catch (NoResultException ex) {
            LOG.trace("NoResult by :: CharactersRelationship [{}] - [{} - {}]",
                    charactersRelationship.getName(),
                    charactersRelationship.getCharacterPrincipalId().getId(),
                    charactersRelationship.getCharacterPrincipalId().getName());
            return charactersRelationship;
        }
    }

    private Creator getCreatorByAll(Creator creator) {
        try {
            Query query = em.createNamedQuery("Creator.findByAll");
            query.setParameter("comicId", creator.getComicId());
            query.setParameter("role", creator.getRole());
            query.setParameter("name", creator.getName());
            Creator aux = (Creator) query.getSingleResult();
            return aux.load(creator);
        } catch (NoResultException ex) {
            LOG.trace("NoResult by :: Creator [{}] ", creator.getName());
            return creator;
        }
    }

    private Comic getComicByMarvelId(Comic comic) {
        try {
            Query query = em.createNamedQuery("Comic.findByMarvelId");
            query.setParameter("marvelId", comic.getMarvelId());
            Comic aux = (Comic) query.getSingleResult();
            return aux.load(comic);
        } catch (NoResultException ex) {
            LOG.trace("NoResult by :: marvelId [{}] ", comic.getMarvelId());
            comic.setCreatorList(null);
            comic.setCharactersRelationshipList(null);
            return comic;
        }
    }

    private CharacterMarvel getCharacterMarvelByMarvelId(CharacterMarvel characterMarvel) {
        try {
            Query query = em.createNamedQuery("CharacterMarvel.findByMarvelId");
            query.setParameter("marvelId", characterMarvel.getMarvelId());
            CharacterMarvel aux = (CharacterMarvel) query.getSingleResult();
            return aux.load(characterMarvel);
        } catch (NoResultException ex) {
            LOG.trace("NoResult by :: marvelId [{}] ", characterMarvel.getMarvelId());
            return characterMarvel;
        }
    }

    private <T> T loadEntity(Integer id, T entity) {
        if (id == null) {
            return persist(entity);
        } else {
            return update(entity);
        }
    }
}
