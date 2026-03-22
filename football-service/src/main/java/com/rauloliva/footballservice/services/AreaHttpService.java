package com.rauloliva.footballservice.services;

import com.rauloliva.footballservice.ApiMappings.AreaApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "http://api.football-data.org/v4/areas", accept = "application/json")
public interface AreaHttpService {

    @GetExchange("/{areaId}")
    AreaApiResponse fetchEuropeanCountries(@PathVariable Long areaId);
}
