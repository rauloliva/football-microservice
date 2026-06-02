package com.rauloliva.footballservice.service.impl;

import com.rauloliva.football.dto.Countries;
import com.rauloliva.football.dto.Country;
import com.rauloliva.footballservice.mapper.CountryMapper;
import com.rauloliva.footballservice.service.AreaHttpService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaHttpService areaHttpService;
    private final CountryMapper countryMapper;

    public Countries getCountries() {
        List<Country> countriesList = areaHttpService.fetchEuropeanCountries(2077L)
                .getChildAreas();

        log.debug("Total Countries: {}", countriesList.size());

        return countryMapper.mapToCountries(countriesList);
    }
}
