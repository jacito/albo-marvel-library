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
package com.albo.library.response.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author jacito
 */
@AllArgsConstructor
@Getter
public enum ResultCode {
    GENERIC_SUCCESS(200, "OK", "Succesful transaction"),
    GENERIC_ERROR(500, "Unknown Error", "Internal Server Error"),
    NO_CONTENT(503, "Service Unavailable", "The server is not ready to handle the request. [TEMPLATE]");

    private final int code;
    private final String mesage;
    private final String description;

}
