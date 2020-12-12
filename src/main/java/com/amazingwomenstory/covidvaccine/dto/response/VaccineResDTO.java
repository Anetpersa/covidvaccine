package com.amazingwomenstory.covidvaccine.dto.response;

import com.amazingwomenstory.covidvaccine.persistence.entity.RouteOfAdministration;
import com.amazingwomenstory.covidvaccine.persistence.entity.SideEffects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VaccineResDTO {

    private Long id;

    private String vaccineName;

    private String description;

    private Integer numberOfDoses;

    private Integer timingOfDoses;

    private RouteOfAdministration routeOfAdministration;

    private Integer efficacy;

    private Long numberOfPeopleTestedOn;

    private SideEffects sideEffects;

    private boolean isRegisteredInSerbia;
}
