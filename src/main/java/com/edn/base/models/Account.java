package com.edn.base.models;

import com.edn.base.enums.AccountType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Account {

    private Integer id;
    private String name;
    private AccountType type;

}
