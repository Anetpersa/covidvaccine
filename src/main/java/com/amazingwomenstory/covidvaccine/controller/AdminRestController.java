package com.amazingwomenstory.covidvaccine.controller;

import com.amazingwomenstory.covidvaccine.dto.request.VaccineReqDTO;
import com.amazingwomenstory.covidvaccine.dto.response.ResponseDTO;
import com.amazingwomenstory.covidvaccine.dto.response.VaccineResDTO;
import com.amazingwomenstory.covidvaccine.service.VaccineService;
import com.amazingwomenstory.covidvaccine.utils.Constants;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(Constants.API_PREFIX_ADMIN)
public class AdminRestController extends AbstractRestController {

    private Logger logger = LoggerFactory.getLogger(AdminRestController.class);

    @Autowired
    VaccineService vaccineService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_ADMIN_USER')")
    @ApiOperation(value = "Get all covid vaccines from database for admin.", notes = "Returns a list of all covid vaccines from database for admin.", response = VaccineResDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid request")})
    public @ResponseBody
    List<VaccineResDTO> findAllSkillsAdmin() {
        return vaccineService.findAllVaccines();
    }

    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN_USER')")
    @ApiOperation(value = "Get all covid vaccines from database by id.", notes = "Returns all covid vaccines from database by id parameter.", response = VaccineResDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid request")})
    public @ResponseBody
    VaccineResDTO findById(@PathVariable("id") Long id) {
        return vaccineService.findVaccineById(id);
    }

    @PutMapping
//    @PreAuthorize("hasAuthority('ROLE_ADMIN_USER')")
    @ApiOperation(value = "Creates a new vaccine.", notes = "Creates a new vaccine.", response = VaccineResDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid request")})
    public @ResponseBody
    VaccineResDTO createVaccine(@RequestBody VaccineReqDTO vaccineReqDTO) {

        VaccineResDTO createdVaccine = vaccineService.createVaccine(vaccineReqDTO);

        logger.info("A vaccine with name " + createdVaccine.getVaccineName() + " was created.");
        return createdVaccine;
    }

//    @PostMapping(value = Constants.API_PREFIX_ADMIN_UPLOAD_FILE)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN_USER')")
//    @ApiOperation(value = "Adds a list of vaccines from CSV file", notes = "Uploads CSV file and imports all vaccines from it.", response = VaccineResDTO.class)
//    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid request")})
//    public @ResponseBody
//    List<VaccineResDTO> importsAListOfVaccinesFromCSVFile(@RequestParam("file") MultipartFile file) throws IOException {
//        if (file.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please select a CSV file.");
//        } else {
//            return vaccineService.importsAListOfVaccinesFromCSVFile(file);
//        }
//    }

    @PostMapping(value = "/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN_USER')")
    @ApiOperation(value = "Updates vaccine", notes = "Updates a specific vaccine.", response = ResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid request")})
    public ResponseDTO updateVaccine(@PathVariable("id") Long id, @RequestBody VaccineReqDTO vaccineReqDTO) {
        try {
            vaccineService.updateVaccine(vaccineReqDTO, id);
            logger.info("A vaccine with a name " + vaccineReqDTO.getVaccineName() + " was updated.");
            return createResponseDTO(Constants.STATUS_CODE_SUCCESS);
        } catch (ResponseStatusException exception) {
            return createResponseDTO(exception.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN_USER')")
    @ApiOperation(value = "Deletes vaccine.", notes = "Deletes a specific vaccine.")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid request")})
    public @ResponseBody
    ResponseDTO deleteVaccine(@PathVariable("id") Long id) {
        try {
            vaccineService.deleteVaccineById(id);
            logger.info("A vaccine with the id " + id + " was deleted.");
            return createResponseDTO(Constants.STATUS_CODE_SUCCESS);
        } catch (ResponseStatusException exception) {
            return createResponseDTO(exception.getMessage());
        }
    }
}
