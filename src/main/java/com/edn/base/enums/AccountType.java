package com.edn.base.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AccountType {
    BUSINESS, PERSONAL;

    @JsonCreator
    public static AccountType forName(String name) {
        for (AccountType accountType: values()) {
            if (accountType.name().equals(name)) {
                return accountType;
            }
        }

        return null;
    }

}
