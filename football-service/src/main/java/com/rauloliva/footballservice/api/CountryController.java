package com.rauloliva.footballservice.api;

import com.rauloliva.football.api.CountryApi;
import com.rauloliva.football.dto.Countries;
import com.rauloliva.footballservice.service.impl.AreaService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CountryController implements CountryApi {

    private final AreaService areaService;

    @Override
    public ResponseEntity<Countries> getCountries() {
        return ResponseEntity.ok(areaService.getCountries());
    }


}
