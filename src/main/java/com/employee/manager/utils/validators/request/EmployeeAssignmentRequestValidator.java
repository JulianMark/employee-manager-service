package com.employee.manager.utils.validators.request;

import com.employee.manager.service.http.AssignTypeRequest;
import com.employee.manager.service.http.EmployeeAssignmentCampaignRequest;

import static com.employee.manager.utils.Utils.validateIdNumber;
import static com.employee.manager.utils.Utils.validateRequest;

public class EmployeeAssignmentRequestValidator {

    public static void validateEmployeeAssignmentRequest(EmployeeAssignmentCampaignRequest request) {
        validateRequest(request);
        validateIdNumber(request.getIdType());
        validateIdNumber(request.getIdCampaign());
    }
}
