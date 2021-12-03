package com.edn.base.testEnum;

import com.edn.base.enums.Category;

public class TestEnum {

    public static void main(String[] args) {
        String v = "";

        Category c = Category.valueOf(v);

        System.out.println(c);
    }

}
