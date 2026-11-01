package com.samuel.entrega_01.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class I18n {
    private static final String BUNDLE_BASE_NAME = "i18n.Bundle";
    private static Locale currentLocale = Locale.of("ca", "ES"); // Default to catalan
    private static ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME, currentLocale);

    private I18n(){
        // Utility class. No instance has to be created
    }

    public static Locale getLocale() {
        return currentLocale;
    }

    public static void setLocale(Locale newLocale){
        currentLocale = newLocale;
        bundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME, currentLocale);
    }

    public static String get(String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return "!" + key + "!"; // If key is missing, shows !key! so that typos can be spotted easily
        }
    }

    public static String get(String key, Object... args) {
        String pattern = get(key);
        return MessageFormat.format(pattern, args);
    }

}
