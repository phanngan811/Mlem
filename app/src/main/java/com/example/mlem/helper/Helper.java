package com.example.mlem.helper;

import java.text.NumberFormat;
import java.util.Locale;

public class Helper {
    public static String CurrencyFormatter(double price) {
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(price);
    }
}
