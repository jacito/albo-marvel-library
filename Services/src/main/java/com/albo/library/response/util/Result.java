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
import lombok.Data;

/**
 *
 * @author jacito
 */
@Data
@AllArgsConstructor
public class Result {

    public static final Result SUCCESS = new Result(ResultCode.GENERIC_SUCCESS);
    public static final Result ERROR = new Result(ResultCode.GENERIC_ERROR);
    public static final Result NO_CONTENT = new Result(ResultCode.NO_CONTENT);

    private int responseCode;
    private String responseMessage;
    private String responseDescription;

    public Result(ResultCode resultCode) {
        this.responseCode = resultCode.getCode();
        this.responseMessage = resultCode.getMesage();
        this.responseMessage = resultCode.getDescription();
    }

}
