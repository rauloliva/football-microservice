package com.rauloliva.footballservice.ApiMappings;

import com.rauloliva.football.dto.Country;

import java.util.List;

public record AreaApiResponse (Long id, List<Country> childAreas) {
}
