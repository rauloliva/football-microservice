package com.rauloliva.footballservice.mapper;

import com.rauloliva.football.dto.Countries;
import com.rauloliva.football.dto.Country;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    Country map(Country country);

    @AfterMapping
    default void enrichFlag(@MappingTarget Country target) {
        if (target.getFlag() == null && target.getName() != null) {
            String name = target.getName();
            target.setFlag(String.format(
                    "https://en.wikipedia.org/wiki/%s#/media/File:Flag_of_%s.svg",
                    name, name));
        }
    }

    default Countries mapToCountries(List<Country> countriesList) {
        if (countriesList == null) return null;

        List<Country> enrichedList = countriesList.stream()
                .map(this::map)
                .toList();

        return new Countries().countries(enrichedList);
    }
}
