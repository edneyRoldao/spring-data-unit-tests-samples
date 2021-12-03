package com.edn.base.multibeansInjection;

import org.springframework.stereotype.Component;

@Component
public class Validator3 implements Validator {

    @Override
    public void validate() {
        System.out.println("THIRD VALIDATOR");
    }

}
