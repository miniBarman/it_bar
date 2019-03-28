package com.project.itbar.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final Map<String, String> INGREDIENT_GROUP_MAPPING = Collections.unmodifiableMap(new HashMap<String, String>(){{
        put("STRONG_ALCOHOL", "Крепкий алкоголь");
        put("VERMOUTH", "Вермуты");
        put("LIQUEUR", "Ликёры");
        put("BITTER", "Биттеры");
        put("VINE", "Вина");
        put("BEER", "Пиво");
        put("SYRUP", "Сиропы");
        put("JUICE", "Соки");
        put("NON_ALCH_DRINK", "Безалкагольные напитки");
        put("FRUIT", "Фрукты");
        put("HERBS", "Травы и специи");
        put("SAUCE_OIL", "Соусы и масла");
        put("ICE", "Лёд");
    }});
}
