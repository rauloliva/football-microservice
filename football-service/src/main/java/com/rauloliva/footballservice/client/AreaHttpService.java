package com.rauloliva.footballservice.client;

import com.rauloliva.football.dto.Area;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/areas", accept = "application/json")
public interface AreaHttpService {

    @GetExchange("/{areaId}")
    Area fetchEuropeanCountries(@PathVariable Long areaId);
}
