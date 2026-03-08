package com.rauloliva.footballservice.controllers;

import com.rauloliva.football.api.CountryApi;
import com.rauloliva.football.dto.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController implements CountryApi {

    @Override
    public ResponseEntity<List<Country>> getCountries() {
        return ResponseEntity.ok(List.of(new Country("1", "Mexico"), new Country("1", "USA")));
    }


}
