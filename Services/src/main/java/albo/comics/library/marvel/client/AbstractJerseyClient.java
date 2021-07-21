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

import albo.comics.library.Util;
import static albo.comics.library.marvel.client.ApplicationConfiguration.API_CONNECT_TIMEOUT;
import static albo.comics.library.marvel.client.ApplicationConfiguration.API_READ_TIMEOUT;
import static albo.comics.library.marvel.client.ApplicationConfiguration.API_URL;
import static albo.comics.library.marvel.client.ApplicationConfiguration.PRIVATE_KEY;
import static albo.comics.library.marvel.client.ApplicationConfiguration.PUBLIC_KEY;
import java.util.logging.Level;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 *
 * @author jacito
 */
public class AbstractJerseyClient {

    public static final String READ_TIMEOUT = "jersey.config.client.readTimeout";
    public static final String CONNECT_TIMEOUT = "jersey.config.client.connectTimeout";
    protected static final java.util.logging.Logger WS_LOG
            = java.util.logging.Logger.getLogger(AbstractJerseyClient.class.getName());
    protected final ObjectMapperReference objectMapper = ObjectMapperReference.INSTANCE;

    private static final String[] PRINCIPAL_PARAMS = {"ts", "apikey", "hash", "nameStartsWith"};

    protected void close(Response response) {
        if (response != null) {
            response.close();
        }
    }

    protected Response getCharactersByName(String path, String name) {
        WebTarget target = loadTarget(path).queryParam(PRINCIPAL_PARAMS[3], name);
        target = getClient().target(target.getUri().normalize().toString());
        WS_LOG.log(Level.CONFIG, target.getUri().toString());
        return target.request(MediaType.APPLICATION_JSON).get();
    }

    protected Response getComicssByIdCharacter(String path1, String path2, String idCharacter) {
        WebTarget target = loadTarget(path1).path(idCharacter).path(path2);
        target = getClient().target(target.getUri().normalize().toString());
        WS_LOG.log(Level.CONFIG, target.getUri().toString());
        return target.request(MediaType.APPLICATION_JSON).get();
    }

    private WebTarget loadTarget(String path) {
        Long startRequest = System.nanoTime();
        return getClient().target(API_URL)
                .path(path)
                .queryParam(PRINCIPAL_PARAMS[0], startRequest.toString())
                .queryParam(PRINCIPAL_PARAMS[1], PUBLIC_KEY)
                .queryParam(PRINCIPAL_PARAMS[2], Util.getMD5(startRequest.toString(), PRIVATE_KEY, PUBLIC_KEY));
    }

    private Client getClient() {
        return ClientBuilder.newClient()
                .register(new LoggingFeature(WS_LOG, Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000))
                .register(MultiPartFeature.class)
                .property(CONNECT_TIMEOUT, API_CONNECT_TIMEOUT)
                .property(READ_TIMEOUT, API_READ_TIMEOUT);
    }

}
