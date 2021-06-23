/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DecimalFormat;

/**
 *
 * @author TRANTATDAT
 */
public class FormatPrice {

    public static String getTotalPriceWithDot(double totalPrice) {
        String priceDot = "" + totalPrice;
        int i = priceDot.length() - 3;
        while (i > 0) {
            priceDot = priceDot.substring(0, i) + "." + priceDot.substring(i, priceDot.length());
            i -= 3;
        }
        return priceDot;
    }

    public static String getTotalPriceWithSeparator(double totalPrice) {
        DecimalFormat myFormatter = new DecimalFormat("###,###.###");
        String output = myFormatter.format(totalPrice);
        return output;
    }
}
