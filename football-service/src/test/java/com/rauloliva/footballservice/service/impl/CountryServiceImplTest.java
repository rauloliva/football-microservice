package com.rauloliva.footballservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rauloliva.football.dto.Area;
import com.rauloliva.football.dto.Countries;
import com.rauloliva.football.dto.Country;
import com.rauloliva.footballservice.client.AreaHttpService;
import com.rauloliva.footballservice.mapper.CountryMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CountryServiceImplTest {

    @Mock
    private AreaHttpService areaHttpService;

    @Mock
    private CountryMapper countryMapper;

    @InjectMocks
    private CountryServiceImpl countryService;

    @Test
    @DisplayName("should return all countries from area 2077")
    void testGetCountries() {
        List<Country> countriesList = new ArrayList<>();
        Country country = new Country();
        country.setId(2077);
        countriesList.add(country);

        Area area = new Area();
        area.setId(2077);
        area.setChildAreas(countriesList);

        Countries expectedCountries = new Countries().countries(countriesList);


        when(areaHttpService.fetchEuropeanCountries(2077L))
                .thenReturn(area);

        when(countryMapper.mapToCountries(countriesList))
                .thenReturn(expectedCountries);

        Countries countries = countryService.getCountries(2077L);

        assertNotNull(countries);

        verify(areaHttpService).fetchEuropeanCountries(2077L);
        verify(countryMapper).mapToCountries(countriesList);
    }
}
