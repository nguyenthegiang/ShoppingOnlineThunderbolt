/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Random;

/**
 *
 * @author TRANTATDAT
 */
public class GenerateRandomString {

    public static String generateNumber(int size) {
        // These are the valid charecters use to random.
        String strValid = "0123456789";

        Random random = new Random();
        String mystring = "";
        for (int i = 0; i < size; i++) {
            int randnum = random.nextInt(strValid.length());
            mystring = mystring + strValid.charAt(randnum);
        }
        return mystring;
    }

    public static String generateString(int size) {
        String str = "abcdefghijklmnopqrstuvwxyz";
        String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String str2 = "0123456789";

        // These are the valid charecters use to random.
        String strValid = str + str1 + str2;

        Random random = new Random();
        String mystring = "";
        for (int i = 0; i < size; i++) {
            int randnum = random.nextInt(strValid.length());
            mystring = mystring + strValid.charAt(randnum);
        }
        return mystring;
    }
}
