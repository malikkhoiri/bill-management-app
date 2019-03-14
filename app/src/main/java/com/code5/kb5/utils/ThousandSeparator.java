package com.code5.kb5.utils;

import java.util.Locale;

public class ThousandSeparator {
    public static String createCurrency(String s){
        String clean = "";
        try {
            if (!s.equals("")) {
                String a = s.replace(",", "");
                Double dbl = Double.parseDouble(a);
                clean = String.format(Locale.US, "%,.0f", dbl);
            }
        }catch(Exception e){
            clean = s;
        }

        return clean;
    }
}
