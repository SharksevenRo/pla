package com.pla.utils;

import com.pla.PLAConfiguration;
import org.hibernate.cfg.NamingStrategy;

public class PropertyConvert {
    public static String convert(String key) {
        NamingStrategy namingStrategy = PLAConfiguration.getNamingStrategy();
        if (namingStrategy != null) {
            return namingStrategy.propertyToColumnName(key).toUpperCase();
        } else {
            return key.toUpperCase();
        }
    }
}
