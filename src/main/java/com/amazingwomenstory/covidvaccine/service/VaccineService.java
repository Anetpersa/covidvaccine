package com.amazingwomenstory.covidvaccine.service;

import com.amazingwomenstory.covidvaccine.dto.request.VaccineReqDTO;
import com.amazingwomenstory.covidvaccine.dto.response.VaccineResDTO;
import com.amazingwomenstory.covidvaccine.persistence.entity.Vaccine;
import com.amazingwomenstory.covidvaccine.persistence.repository.VaccineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class VaccineService {


    private Logger logger = LoggerFactory.getLogger(VaccineService.class);

    @Autowired
    protected VaccineRepository vaccineRepository;

//    @Autowired
//    private PasswordEncoder usernameEncoder;
//
//    public String getTheUserNameOfSession() {
//        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//        logger.debug(currentUserName);
//        return currentUserName;
//    }

    public List<VaccineResDTO> findAllVaccines() {
        List<Vaccine> vaccines = vaccineRepository.findAll();
        List<VaccineResDTO> vaccinesResDTO = new ArrayList<>();
        for (Vaccine vaccine : vaccines) {
            VaccineResDTO vaccineResDTO = new VaccineResDTO();
            vaccineResDTO.setId(vaccine.getId());
            vaccineResDTO.setVaccineName(vaccine.getVaccineName());
            vaccineResDTO.setDescription(vaccine.getDescription());
            vaccineResDTO.setNumberOfDoses(vaccine.getNumberOfDoses());
            vaccineResDTO.setTimingOfDoses(vaccine.getTimingOfDoses());
            vaccineResDTO.setRouteOfAdministration(vaccine.getRouteOfAdministration());
            vaccineResDTO.setEfficacy(vaccine.getEfficacy());
            vaccineResDTO.setNumberOfPeopleTestedOn(vaccine.getNumberOfPeopleTestedOn());
            vaccineResDTO.setSideEffects(vaccine.getSideEffects());
            vaccinesResDTO.add(vaccineResDTO);
        }
        return vaccinesResDTO;

    }

    public VaccineResDTO findVaccineById(Long id) {
        Vaccine vaccine = vaccineRepository.findById(id).get();
        VaccineResDTO vaccineResDTO = new VaccineResDTO();
        vaccineResDTO.setId(vaccine.getId());
        vaccineResDTO.setVaccineName(vaccine.getVaccineName());
        vaccineResDTO.setDescription(vaccine.getDescription());
        vaccineResDTO.setNumberOfDoses(vaccine.getNumberOfDoses());
        vaccineResDTO.setTimingOfDoses(vaccine.getTimingOfDoses());
        vaccineResDTO.setRouteOfAdministration(vaccine.getRouteOfAdministration());
        vaccineResDTO.setEfficacy(vaccine.getEfficacy());
        vaccineResDTO.setNumberOfPeopleTestedOn(vaccine.getNumberOfPeopleTestedOn());
        vaccineResDTO.setSideEffects(vaccine.getSideEffects());
        return vaccineResDTO;
    }

    public VaccineResDTO createVaccine(VaccineReqDTO vaccineReqDTO) {
        List<Vaccine> vaccinesInDB = vaccineRepository.findAll();

        boolean vaccineExistsInDB = false;
        for (Vaccine vaccineInDB : vaccinesInDB) {
            assert vaccineReqDTO != null;
            if (vaccineReqDTO.getVaccineName().equals(vaccineInDB.getVaccineName())) {
                vaccineExistsInDB = true;
                break;
            }
        }
        if (vaccineExistsInDB) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Vaccine with the name: " + vaccineReqDTO.getVaccineName() + " already exists in database.");
        } else {
            Vaccine vaccine = new Vaccine();
            convertVaccineReqDTOToVaccine(vaccineReqDTO, vaccine);
//            skill.setCreatedBy(usernameEncoder.encode(getTheUserNameOfSession()));
//            skill.setModifiedBy(usernameEncoder.encode(getTheUserNameOfSession()));
            vaccineRepository.save(vaccine);

            return convertVaccineToVaccineResDTO(vaccine);
        }
    }

//    public List<VaccineResDTO> importsAListOfVaccinesFromCSVFile(MultipartFile file) {
//    }

    public VaccineResDTO updateVaccine(VaccineReqDTO vaccineReqDTO, Long id) {
        Vaccine vaccineToBeUpdated = vaccineRepository.findById(id).get();
        if (vaccineToBeUpdated.isRegisteredInSerbia()) {
            throw new ResponseStatusException(HttpStatus.LOCKED, "This vaccine can not be updated because it is already registered in Serbia.");
        } else {
            Vaccine updatedVaccine = convertVaccineReqDTOToVaccine(vaccineReqDTO, vaccineToBeUpdated);
//            vaccineToBeUpdated.setModifiedBy(usernameEncoder.encode(getTheUserNameOfSession()));
            vaccineRepository.save(vaccineToBeUpdated);
            return convertVaccineToVaccineResDTO(updatedVaccine);
        }
    }

    public void deleteVaccineById(Long id) {
        Vaccine deletedVaccine = vaccineRepository.findVaccineById(id);
        if (!deletedVaccine.isRegisteredInSerbia()) {
            vaccineRepository.delete(deletedVaccine);
        } else {
            throw new ResponseStatusException(HttpStatus.LOCKED, "This vaccine can not be deleted because it is already registered in Serbia.");
        }
    }

    private Vaccine convertVaccineReqDTOToVaccine(VaccineReqDTO vaccineReqDTO, Vaccine vaccine) {
        vaccine.setVaccineName(vaccineReqDTO.getVaccineName());
        vaccine.setDescription(vaccineReqDTO.getDescription());
        vaccine.setNumberOfDoses(vaccineReqDTO.getNumberOfDoses());
        vaccine.setTimingOfDoses(vaccineReqDTO.getTimingOfDoses());
        vaccine.setRouteOfAdministration(vaccineReqDTO.getRouteOfAdministration());
        vaccine.setEfficacy(vaccineReqDTO.getEfficacy());
        vaccine.setNumberOfPeopleTestedOn(vaccineReqDTO.getNumberOfPeopleTestedOn());
        vaccine.setSideEffects(vaccineReqDTO.getSideEffects());

        return vaccine;
    }

    private VaccineResDTO convertVaccineToVaccineResDTO(Vaccine vaccine) {
        VaccineResDTO vaccineResDTO = new VaccineResDTO();
        vaccineResDTO.setId(vaccine.getId());
        vaccineResDTO.setVaccineName(vaccine.getVaccineName());
        vaccineResDTO.setDescription(vaccine.getDescription());
        vaccineResDTO.setNumberOfDoses(vaccine.getNumberOfDoses());
        vaccineResDTO.setTimingOfDoses(vaccine.getTimingOfDoses());
        vaccineResDTO.setRouteOfAdministration(vaccine.getRouteOfAdministration());
        vaccineResDTO.setEfficacy(vaccine.getEfficacy());
        vaccineResDTO.setNumberOfPeopleTestedOn(vaccine.getNumberOfPeopleTestedOn());
        vaccineResDTO.setSideEffects(vaccine.getSideEffects());

        return vaccineResDTO;
    }
}
