package com.edn.base.multibeansInjection;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainValidator {

    List<Validator> validators;

    public MainValidator(List<Validator> validators) {
        this.validators = validators;
    }

    public void validate() {
        validators.forEach(Validator::validate);
    }

}
