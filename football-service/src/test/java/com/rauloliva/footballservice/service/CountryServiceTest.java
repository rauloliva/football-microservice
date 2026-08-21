package com.rauloliva.footballservice.service;

import com.rauloliva.football.dto.Area;
import com.rauloliva.football.dto.Countries;
import com.rauloliva.football.dto.Country;
import com.rauloliva.footballservice.client.AreaHttpService;
import com.rauloliva.footballservice.mapper.CountryMapper;
import com.rauloliva.footballservice.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @Mock
    private AreaHttpService areaHttpService;

    @Mock
    private CountryMapper countryMapper;

    @InjectMocks
    private CountryServiceImpl countryService;

    @Test
    void getCountriesTest() {
        Country country = new Country();
        country.setId(1);
        country.setName("Germany");
        country.setFlag("germany_flag.png");
        country.setCountryCode("GER");

        Area area = new Area();
        area.setId(2077);
        area.setChildAreas(List.of(country));

        Countries countries = new Countries();
        countries.addCountriesItem(country);

        when(areaHttpService.fetchEuropeanCountries(2077L))
                .thenReturn(area);

        List<Country> countryList = area.getChildAreas();

        when(countryMapper.mapToCountries(countryList))
                .thenReturn(countries);

        countryService.getCountries(2077L);

        verify(areaHttpService).fetchEuropeanCountries(anyLong());
        verify(countryMapper).mapToCountries(countryList);
    }
}
