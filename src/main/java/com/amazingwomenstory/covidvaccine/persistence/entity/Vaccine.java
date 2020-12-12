package com.amazingwomenstory.covidvaccine.persistence.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = AbstractEntity.TABLE_PREFIX + "vaccine")
public class Vaccine extends AbstractEntity{

    @CsvBindByName
    @Column(name = "vaccine_name", nullable = false, unique = true)
    private String vaccineName;

    @CsvBindByName
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "number_of_doses", nullable = false)
    private Integer numberOfDoses;

    @Column(name = "timing_of_doses", nullable = false)
    private Integer timingOfDoses;

    @CsvBindByName
    @Enumerated(EnumType.STRING)
    @Column(name = "route_of_administration", nullable = false)
    private RouteOfAdministration routeOfAdministration;

    @Column(name = "efficacy", nullable = false)
    private Integer efficacy;

    @Column(name = "number_of_people_tested_on", nullable = false)
    private Long numberOfPeopleTestedOn;

    @CsvBindByName
    @Enumerated(EnumType.STRING)
    @Column(name = "side_effects", nullable = false)
    private SideEffects sideEffects;

    @Column(name = "is_registered_in_Serbia", nullable = false)
    private boolean isRegisteredInSerbia;
}
