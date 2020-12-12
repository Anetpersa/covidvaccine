package com.amazingwomenstory.covidvaccine.persistence.repository;

import com.amazingwomenstory.covidvaccine.persistence.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    Vaccine findVaccineById(Long id);
}
