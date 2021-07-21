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

import albo.comics.library.dao.InitialDAO;
import com.albo.library.json.characters.Characters;
import com.albo.library.json.response.ArtistsResponse;
import com.albo.library.json.response.CharactersResponse;
import com.albo.library.response.util.Result;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jacito
 */
@Path("")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class ServicesResource {

    protected static final Logger LOG = LogManager.getLogger();

    @Inject
    private ServicesManager servicesManager;

    @GET
    @Path("colaborators/{character}")
    public Response getColaborators(@PathParam("character") String template) {
        LOG.info("Request get colaborators :: character [{}]", template);
        com.albo.library.response.util.Response<ArtistsResponse> response
                = new com.albo.library.response.util.Response(Result.SUCCESS, servicesManager.getColaborators(template));

        LOG.info("Response get :: Characters [{}]", response.getResult());
        return Response.status(response.getResult().getResponseCode()).entity(response).build();
    }

    @GET
    @Path("characters/{character}")
    public Response getCharacters(@PathParam("character") String template) {
        LOG.info("Request get characters :: character [{}]", template);
        com.albo.library.response.util.Response<CharactersResponse> response
                = new com.albo.library.response.util.Response(Result.SUCCESS, servicesManager.getCharacters(template));

        LOG.info("Response get :: Characters [{}]", response.getResult());
        return Response.status(response.getResult().getResponseCode()).entity(response).build();
    }
}
