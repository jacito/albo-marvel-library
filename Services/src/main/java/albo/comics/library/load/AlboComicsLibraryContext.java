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

import albo.comics.library.Util;
import albo.comics.library.marvel.client.ApplicationConfiguration;
import albo.comics.library.model.CharacterMarvel;
import albo.comics.library.model.CharactersRelationship;
import albo.comics.library.model.Comic;
import albo.comics.library.model.Creator;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Web application lifecycle listener.
 *
 * @author jacito
 */
public class AlboComicsLibraryContext implements ServletContextListener {

    private static final Logger LOG = LogManager.getLogger();
    private static final String APP_NAME = "AlboComicsLibrary-0.1-rc1";
    private static final String PROPERTIES_PATH = "/albo_comics_library.properties";

    public static Queue<CharacterMarvel> CHARACTERS_MARVEL_QUEUE = new ConcurrentLinkedQueue<>();
    public static Queue<Comic> COMICS_QUEUE = new ConcurrentLinkedQueue<>();
    public static Queue<Creator> CREATORS_QUEUE = new ConcurrentLinkedQueue<>();
    public static Queue<CharactersRelationship> CHARACTERS_RELATIONSHIP_QUEUE = new ConcurrentLinkedQueue<>();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        initConfiguration();
        LOG.info(APP_NAME + " initialized correctly.");
        System.out.println(Util.logPlay());
    }

    private void initConfiguration() {
        Properties properties = new Properties();
        properties.putAll(getConfigProperties());
        ApplicationConfiguration.initConfiguration(properties);
    }

    private Properties getConfigProperties() {
        Properties properties = new Properties();
        try {
            properties.load(
                    new InputStreamReader(this.getClass().getResourceAsStream(PROPERTIES_PATH),
                            StandardCharsets.UTF_8));
            return properties;
        } catch (IOException ex) {
            LOG.error("Unable to load " + PROPERTIES_PATH + " file.", ex);
            throw new RuntimeException("Error while loading configuration file.", ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info(APP_NAME + " shutdowm complete.");
        System.out.println(Util.logStop());
    }
}
