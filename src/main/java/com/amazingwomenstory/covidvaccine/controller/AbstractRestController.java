package com.amazingwomenstory.covidvaccine.controller;

import com.amazingwomenstory.covidvaccine.dto.response.ResponseDTO;

public class AbstractRestController {

    protected ResponseDTO createResponseDTO(String statusCode) {
        return new ResponseDTO(statusCode);
    }
}