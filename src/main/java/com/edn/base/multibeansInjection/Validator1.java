package com.edn.base.multibeansInjection;

import org.springframework.stereotype.Component;

@Component
public class Validator1 implements Validator {

    @Override
    public void validate() {
        System.out.println("FIRST VALIDATOR");
    }

}
