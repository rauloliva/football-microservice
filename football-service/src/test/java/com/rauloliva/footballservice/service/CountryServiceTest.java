package com.rauloliva.footballservice.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rauloliva.football.dto.Area;
import com.rauloliva.football.dto.Countries;
import com.rauloliva.football.dto.Country;
import com.rauloliva.footballservice.client.AreaHttpService;
import com.rauloliva.footballservice.mapper.CountryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @Mock
    private AreaHttpService areaHttpService;

    @Mock
    private CountryMapper countryMapper;

    @InjectMocks
    private CountryService countryService;

    private static List<Country> countries;
    private static Area area;

    private static void buildCountries() {
        var country = new Country()
                .id(2077)
                .name("Russia")
                .countryCode("RUS")
                .flag("russia_flag.png");

        countries = new ArrayList<>();
        countries.add(country);
    }

    private static void buildArea() {
        area = new Area()
                .id(2077)
                .childAreas(countries);
    }

    @BeforeEach
    void setUp() {
        buildCountries();
        buildArea();
    }

    @Test
    @DisplayName("should return all countries from area 2077")
    void testGetCountries() {

        Countries expectedCountries = new Countries().countries(countries);

        when(areaHttpService.fetchEuropeanCountries(2077L))
                .thenReturn(area);

        when(countryMapper.mapToCountries(countries))
                .thenReturn(expectedCountries);

        Countries mockedCountries = countryService.getCountries(2077L);

        assertNotNull(mockedCountries);

        verify(areaHttpService).fetchEuropeanCountries(2077L);
        verify(countryMapper).mapToCountries(countries);
    }
}
