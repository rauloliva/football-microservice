package com.rauloliva.footballservice.api;

import com.rauloliva.football.api.CountryApi;
import com.rauloliva.football.dto.Countries;
import com.rauloliva.footballservice.service.impl.CountryServiceImpl;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CountryController implements CountryApi {

    private final CountryServiceImpl countryService;

    @Override
    public ResponseEntity<Countries> getCountries() {
        return ResponseEntity.ok(countryService.getCountries());
    }


}
