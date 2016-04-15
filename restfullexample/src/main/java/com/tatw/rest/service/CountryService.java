package com.tatw.rest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tatw.rest.model.Country;
import lombok.Getter;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hans on 15-04-16.
 */
@Component
public class CountryService implements ResourceLoaderAware {

    @Getter
    private List<Country> countryList;

    private ResourceLoader resourceLoader;

    public CountryService() {
    }

    @PostConstruct
    public void init() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        final Gson gson = gsonBuilder.create();
        try {
            Reader reader = new InputStreamReader(this.resourceLoader.getResource("classpath:countries.json").getInputStream(), "UTF-8");
            Type listType = new TypeToken<ArrayList<Country>>() {
            }.getType();
            countryList = gson.fromJson(reader, listType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Do nothing just to catch an exception
        } finally {
            if (countryList == null) {
                countryList = new ArrayList<Country>();
            }
        }
    }

    public Country findByCode(String code) {
        for (Country country : countryList) {
            if (code.equalsIgnoreCase(country.getCode())) {
                return new Country(country.getCode(), country.getName());
            }
        }
        return null;
    }

    public Country replaceByCode(Country countryChange) {
        Country countryFound = null;
        for (int x=0; x< countryList.size(); x++) {
            Country country = countryList.get(x);
            if (country.getCode().equalsIgnoreCase(countryChange.getCode())) {
                country.setName(countryChange.getName());
                countryFound = country;
                countryList.remove(x);
                x = countryList.size();
            }
        }
        /**
         * country is found and deleted so added again
         */
        if (countryFound != null) {
            countryList.add(countryFound);
        }
        return countryFound;
    }

    public Boolean deleteCountry(String code) {
        Boolean isDeleted = false;
        for (int x=0; x< countryList.size(); x++) {
            Country country = countryList.get(x);
            if (country.getCode().equalsIgnoreCase(code)) {
                countryList.remove(x);
                x = countryList.size();
                isDeleted = true;
            }
        }
        return isDeleted;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
