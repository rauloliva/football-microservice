package com.rauloliva.footballservice.service.impl;

import com.rauloliva.football.dto.Country;
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

    public List<Country> getCountries() {
        List<Country> countries = areaHttpService.fetchEuropeanCountries(2077L).getChildAreas();

        log.debug("Total Countries: {}", countries.size());

        return countries.stream()
                .map(c -> {
                    String name = c.getName();
                    String flag = c.getFlag();
                    if (flag == null) {
                        c.setFlag(String.format(
                                "https://en.wikipedia.org/wiki/%s#/media/File:Flag_of_%s.svg", name, name));
                    }
                    return c;
                }).toList();
    }
}
