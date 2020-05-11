package com.employee.manager.utils.validators.request;

import com.employee.manager.service.http.AddRequest;
import com.employee.manager.service.http.AssignTypeRequest;

import static com.employee.manager.utils.Utils.validateIdNumber;
import static com.employee.manager.utils.Utils.validateNotNullOrEmpty;
import static com.employee.manager.utils.Utils.validateRequest;

public class AssignTypeRequestValidator {

    public static void validateAssignTypeRequest(AssignTypeRequest assignTypeRequest) {
        validateRequest(assignTypeRequest);
        validateIdNumber(assignTypeRequest.getIdType());
        validateIdNumber(assignTypeRequest.getIdEmployee());
    }
}
