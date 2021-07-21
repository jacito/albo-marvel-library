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

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import static java.util.stream.Collectors.toMap;

/**
 *
 * @author jacito
 */
public class ApplicationConfiguration {

    public static int API_CONNECT_TIMEOUT;
    public static int API_READ_TIMEOUT;
    public static String API_URL;
    public static String PUBLIC_KEY;
    public static String PRIVATE_KEY;

    private static final Properties PROPERTIES = new Properties();
    private static Map<String, String> PROPERTIES_MAP = Collections.EMPTY_MAP;

    public static void initConfiguration(Properties properties) {
        PROPERTIES.clear();
        PROPERTIES.putAll(properties);
        PROPERTIES_MAP = Collections.unmodifiableMap(
                PROPERTIES.entrySet().stream()
                        .collect(toMap(e -> String.valueOf(e.getKey()),
                                e -> String.valueOf(e.getValue()))));
        API_CONNECT_TIMEOUT = Integer.parseInt(properties.getProperty("api.connect.timeout", "5000"));
        API_READ_TIMEOUT = Integer.parseInt(properties.getProperty("api.read.timeout", "5000"));
        API_URL = properties.getProperty("api.url");
        PUBLIC_KEY = properties.getProperty("public.key");
        PRIVATE_KEY = properties.getProperty("private.key");
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static Map<String, String> getPropertyMap() {
        return PROPERTIES_MAP;
    }

}
