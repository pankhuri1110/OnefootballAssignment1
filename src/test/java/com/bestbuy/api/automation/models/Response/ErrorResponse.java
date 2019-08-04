package com.bestbuy.api.automation.models.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String name;
    private String message;
    private int code;
    private String className;
    private List<String> errors;
    private Object data;

}
