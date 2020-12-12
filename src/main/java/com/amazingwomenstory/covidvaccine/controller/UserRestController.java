package com.amazingwomenstory.covidvaccine.controller;

import com.amazingwomenstory.covidvaccine.dto.response.VaccineResDTO;
import com.amazingwomenstory.covidvaccine.service.VaccineService;
import com.amazingwomenstory.covidvaccine.utils.Constants;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.API_PREFIX_USER)
public class UserRestController {

    private Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    VaccineService vaccineService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_ADMIN_USER')")
    @ApiOperation(value = "Get all covid vaccines from database for user.", notes = "Returns a list of all covid vaccines from database for user.", response = VaccineResDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid request")})
    public @ResponseBody
    List<VaccineResDTO> findAllSkillsUser() {
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

}
