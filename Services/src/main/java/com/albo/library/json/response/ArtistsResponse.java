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
package com.albo.library.json.response;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author jacito
 */
public class ArtistsResponse {

    private String last_sync;
    private HashMap<String, List<String>> jsonContent;

    public ArtistsResponse(String last_sync, HashMap<String, List<String>> jsonContent) {
        this.last_sync = last_sync;
        this.jsonContent = jsonContent;
    }

    public String getLast_sync() {
        return last_sync;
    }

    public void setLast_sync(String last_sync) {
        this.last_sync = last_sync;
    }

    public HashMap<String, List<String>> getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(HashMap<String, List<String>> jsonContent) {
        this.jsonContent = jsonContent;
    }

}
