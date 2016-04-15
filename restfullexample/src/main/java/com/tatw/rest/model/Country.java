package com.tatw.rest.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by hans on 15-04-16.
 */
@Getter
@Setter
public class Country {

    private String code;
    private String name;

    public Country(){

    }

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
