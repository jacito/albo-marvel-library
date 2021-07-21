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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;

/**
 *
 * @author jacito
 */
public enum ObjectMapperReference {

    INSTANCE;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private ObjectMapperReference() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.findAndRegisterModules();
    }

    public <T> ObjectReader getReader(Class<T> clazz) {
        return objectMapper.readerFor(clazz);
    }

    public <T> ObjectWriter getWriter(Class<T> clazz) {
        return objectMapper.writerFor(clazz);
    }

    public <T> String toJson(Class<T> clazz, T entity) throws JsonProcessingException {
        return getWriter(clazz).writeValueAsString(entity);
    }

    public <T> T fromJson(String json, Class<T> clazz) throws IOException {
        return getReader(clazz).readValue(json);
    }

    public JsonNode asJsonNode(String json) throws IOException {
        return objectMapper.readTree(json);
    }
}
