package com.rauloliva.footballservice.service.impl;

import com.rauloliva.football.dto.Countries;
import com.rauloliva.football.dto.Country;
import com.rauloliva.footballservice.mapper.CountryMapper;
import com.rauloliva.footballservice.client.AreaHttpService;
import com.rauloliva.footballservice.service.CountryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final AreaHttpService areaHttpService;
    private final CountryMapper countryMapper;

    @Override
    public Countries getCountries(Long areaId) {
        try {
            List<Country> countriesList = areaHttpService.fetchEuropeanCountries(areaId)
                    .getChildAreas();

            log.debug("Total Countries: {}", countriesList.size());

            return countryMapper.mapToCountries(countriesList);
        } catch (HttpClientErrorException.NotFound e) {
            log.error("No countries were found based on the areaId provided: {}, message: {}", areaId, e.getMessage());
            return new Countries();
        }
    }
}
