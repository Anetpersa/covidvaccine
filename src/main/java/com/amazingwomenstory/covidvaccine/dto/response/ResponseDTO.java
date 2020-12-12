package com.amazingwomenstory.covidvaccine.dto.response;

import com.amazingwomenstory.covidvaccine.utils.Constants;

public class ResponseDTO {

    private String statusCode;

    public ResponseDTO(String statusCode) {
        this.statusCode = statusCode;
    }

    public ResponseDTO() {
        this.statusCode = Constants.STATUS_CODE_DEFAULT;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

}