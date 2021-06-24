/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author TRANTATDAT
 */
public class FormatString {

    public static String formatToUTF8String(String stringToFormat) {       
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(stringToFormat);
        String utf8EncodedString = StandardCharsets.UTF_8.decode(buffer).toString();
        return utf8EncodedString;
    }
        
}
