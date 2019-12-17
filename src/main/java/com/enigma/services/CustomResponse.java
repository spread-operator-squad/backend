package com.enigma.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Getter @AllArgsConstructor
public class CustomResponse {
    private Status status;
    private Object data;

    public CustomResponse(Status status) {
        this.status = status;
    }

    @JsonIgnore
    public Integer getCode() {
        return status.getCode();
    }
}

@Getter @AllArgsConstructor
class Status {
    private HttpStatus code;
    private String message;

    public Integer getCode() {
        return code.value();
    }
}
