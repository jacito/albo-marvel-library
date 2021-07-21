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
package albo.comics.library;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * author jacito
 */
public class Util {

    // <editor-fold defaultstate="collapsed" desc="Logger Objects (init & shutdown). Click on the + sign on the left to edit the code.">
    private static String[] ALBO = {
        "...............................................................................",
        "...............................................................................",
        "...............................................................................",
        "...............................................................................",
        "...............................................................................",
        "...............................................................................",
        "...............................................................................",
        "..........................lllll...bbbbbb.......................................",
        "..........................lllll...bbbbbb.......................................",
        "..........................lllll...bbbbbb.......................................",
        "..........................lllll...bbbbbb.......................................",
        "....aaaaaaaaaaaaa.........lllll...bbbbbbbbbbbbbbbbbb...........oooooooooooo....",
        "..aaaaaaaaaaaaaaaaa.......lllll...bbbbbbbbbbbbbbbbbbbb......oooooooooooooooooo.",
        ".aaaaaaa.....aaaaaaaa.....lllll...bbbbbbbb......bbbbbbb....ooooooo......ooooooo",
        "aaaaaa..........aaaaa.....lllll...bbbbbb..........bbbbbb..oooooo..........ooooo",
        "aaaaa...........aaaaaa....lllll...bbbbbb...........bbbbb..ooooo............oooo",
        "aaaaa...........aaaaaa....lllll...bbbbbb...........bbbbb..ooooo............oooo",
        "aaaaaa..........aaaaaa....lllll...bbbbbb..........bbbbbb..oooooo..........ooooo",
        ".aaaaaaa......aaaaaaaa....lllll...bbbbbbbb......bbbbbbb....ooooooo......ooooooo",
        "..aaaaaaaaaaaaaaaaaaaa....lllll...bbbbbbbbbbbbbbbbbbbb......oooooooooooooooooo.",
        "....aaaaaaaaaaaaa..aaa....lllll...bbbbbbbbbbbbbbbbbb..........oooooooooooooo...",
        "...............................................................................",
        "...............................................................................",
        "...............................................................................",
        "...............................................................................",
        "...............................................................................",
        "...............................................................................",
        "..............................................................................."
    };

    private static String[] PLAY = {
        ".......................................................",
        ".......................................................",
        ".......................................................",
        ".......................................................",
        "...................>>>>>>>>>>>>>>>>>...................",
        "...............>>>>>>>>>>>>>>>>>>>>>>>>>...............",
        ".............>>>>>>>>.............>>>>>>>>.............",
        "...........>>>>>>.....................>>>>>>...........",
        "..........>>>>>.........................>>>>>..........",
        ".........>>>>>...........................>>>>>.........",
        "........>>>>>...........>>................>>>>>........",
        ".......>>>>>............>>>>>..............>>>>>.......",
        ".......>>>>.............>>>>>>>>............>>>>.......",
        ".......>>>>.............>>>>>>>>>>>.........>>>>.......",
        "......>>>>>.............>>>>>>>>>>>.........>>>>>......",
        ".......>>>>.............>>>>>>>>>...........>>>>.......",
        ".......>>>>>............>>>>>>.............>>>>>.......",
        "........>>>>............>>>................>>>>........",
        "........>>>>>.............................>>>>>........",
        ".........>>>>>>.........................>>>>>>.........",
        "...........>>>>>>.....................>>>>>>...........",
        "............>>>>>>>>...............>>>>>>>>............",
        "..............>>>>>>>>>>>>>>>>>>>>>>>>>>>..............",
        "..................>>>>>>>>>>>>>>>>>>>..................",
        ".......................>>>>>>>>>.......................",
        ".......................................................",
        ".......................................................",
        "......................................................."
    };

    private static String[] STOP = {
        ".......................................................",
        ".......................................................",
        ".......................................................",
        ".......................................................",
        "...................=================...................",
        "...............=========================...............",
        ".............========.............========.............",
        "...........======.....................======...........",
        "..........=====.........................=====..........",
        ".........=====...........................=====.........",
        "........=====.......===============.......=====........",
        ".......=====........===============........=====.......",
        ".......====.........===============.........====.......",
        ".......====.........===============.........====.......",
        "......=====.........===============.........=====......",
        ".......====.........===============.........====.......",
        ".......=====........===============........=====.......",
        "........====........===============........====........",
        "........=====.............................=====........",
        ".........======.........................======.........",
        "...........======.....................======...........",
        "............========...............========............",
        "..............===========================..............",
        "..................===================..................",
        ".......................=========.......................",
        ".......................................................",
        ".......................................................",
        "......................................................."
    };
    // </editor-fold>

    public static String getMD5(String ts, String privateKey, String publiKey) {
        String compoundKey = ts + privateKey + publiKey;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest((compoundKey).getBytes());
            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String logPlay() {
        return log(PLAY);
    }

    public static String logStop() {
        return log(STOP);
    }

    private static String log(String[] type) {
        String log = "\n";
        for (int i = 0; i < ALBO.length; i++) {
            log += type[i] + " " + ALBO[i] + "\n";
        }
        return log;
    }

}
