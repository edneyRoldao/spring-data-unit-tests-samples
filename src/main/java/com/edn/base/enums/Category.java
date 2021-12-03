package com.edn.base.enums;

import java.util.stream.Stream;

public enum Category {

    electronic, clothes, toy, furniture, bag;

    public static Category forName(String name) {
        return Stream.of(values()).filter(c -> c.name().equals(name)).findFirst().orElse(null);
    }

}
