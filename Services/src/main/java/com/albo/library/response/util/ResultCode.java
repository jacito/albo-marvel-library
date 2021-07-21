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
    RESULT_GENERIC_SUCCESS(200, "Transacción exitosa"),
    RESULT_VALID_COUPON(200, "Cupon válido"),
    RESULT_INVALID_COUPON(209, "Cupon inválido"),
    RESULT_BAD_REQUEST(400, "Problema con la solicitud"),
    RESULT_NOT_FOUND(404, "Recurso no encontrado con el identificador proporcionado "),
    RESULT_NO_IMPLEMENTED(501, "No implementado"),
    RESULT_GENERIC_ERROR(500, "Error desconocido");

    private final int code;
    private final String description;

}
