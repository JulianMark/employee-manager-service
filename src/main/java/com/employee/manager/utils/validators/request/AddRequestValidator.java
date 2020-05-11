package com.employee.manager.utils.validators.request;

import com.employee.manager.service.http.AddRequest;

import static com.employee.manager.utils.Utils.validateNotNullOrEmpty;
import static com.employee.manager.utils.Utils.validateRequest;

public class AddRequestValidator {

    public static void validateAddRequest(AddRequest addRequest) {
        validateRequest(addRequest);
        validateNotNullOrEmpty(addRequest.getName());
        validateNotNullOrEmpty(addRequest.getLastName());
        validateNotNullOrEmpty(addRequest.getNickname());
        validateNotNullOrEmpty(addRequest.getPassword());
    }
}
