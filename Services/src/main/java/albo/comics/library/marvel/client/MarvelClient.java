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
package albo.comics.library.marvel.client;

import albo.comics.library.AlboComicsLibraryException;
import com.albo.library.json.characters.Characters;
import com.albo.library.json.comics.Comics;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.enterprise.inject.Model;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jacito
 */
@Model
public class MarvelClient extends AbstractJerseyClient {

    protected static final Logger LOG = LogManager.getLogger();

    private static final String CHARACTERS_PATH = "characters";
    private static final String COMICS_PATH = "comics";

    public Characters getCharacters(String name) {
        LOG.debug("getCharacters :: name [{}]", name);
        return getObject(Characters.class, CHARACTERS_PATH, null, name);
    }

    public Comics getComics(String idCharacter) {
        LOG.debug("getComics :: idCharacter [{}]", idCharacter);
        return getObject(Comics.class, CHARACTERS_PATH, COMICS_PATH, idCharacter);
    }

    private <T> T getObject(Class<T> clazz, String path1, String path2, String params) {
        LOG.info("Load initial information for :: {} :: [{}-{}]", params, path1, path2);

        Long startRequest = System.nanoTime();
        String responseStr = null;
        Response response;

        int statusCode = -1;
        try {
            if (path2 == null) {
                LOG.trace("getCharactersByName :: [{}] :: [{}]", path1, params);
                response = getCharactersByName(path1, params);
            } else {
                LOG.trace("getComicssByIdCharacter :: [{}] :: [{}] :: [{}]", path1, path2, params);
                response = getComicssByIdCharacter(path1, path2, params);
            }

            statusCode = response.getStatus();
            responseStr = response.readEntity(String.class);
            if (statusCode == 200) {
                LOG.trace("statusCode :: OK - 200 :: load object Json");
                return objectMapper.fromJson(responseStr, clazz);
            }
        } catch (IOException
                | WebApplicationException
                | IllegalStateException
                | ProcessingException ex) {
            throw new AlboComicsLibraryException(ex);
        } finally {
            long responseTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startRequest);
            LOG.info("GET {} (statusCode: {}, responseTime: {}ms)", path1, statusCode, responseTime);
            if (LOG.isTraceEnabled()) {
                LOG.trace("GET {} |>> response {}", path1, responseStr);
            }
        }
        return null;
    }

}
