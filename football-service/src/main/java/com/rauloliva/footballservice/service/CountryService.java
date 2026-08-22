package com.rauloliva.footballservice.service;

import com.rauloliva.football.dto.Area;
import com.rauloliva.football.dto.Countries;
import com.rauloliva.football.dto.Country;
import com.rauloliva.footballservice.mapper.CountryMapper;
import com.rauloliva.footballservice.client.AreaHttpService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryService {

    private final AreaHttpService areaHttpService;
    private final CountryMapper countryMapper;

    public Countries getCountries(Long areaId) {
        Area area = areaApi(areaId);
        List<Country> countriesList = area.getChildAreas();

        log.debug("Total Countries: {}", countriesList.size());

        return countryMapper.mapToCountries(countriesList);
    }

    private Area areaApi(Long areaId) {
        try {
            return areaHttpService.fetchEuropeanCountries(areaId);
        } catch (HttpClientErrorException.NotFound e) {
            log.error("No countries were found based on the areaId provided: {}, message: {}",
                    areaId, e.getMessage());
            return new Area();
        }
    }
}
