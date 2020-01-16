package com.employee.manager.utils.validators;

import com.employee.manager.service.http.EmployeeAssignmentCampaignResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class EmployeeAssignmentValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAssignmentValidator.class);

    public Function<EmployeeAssignmentCampaignResponse,
            ResponseEntity<EmployeeAssignmentCampaignResponse>> obtainEmployeeAssignmentValidator (){
        return this::obtainEmployeeAssignment;
    }

    ResponseEntity<EmployeeAssignmentCampaignResponse> obtainEmployeeAssignment(
            EmployeeAssignmentCampaignResponse employeeAssignmentCampaignResponse) {

        LOGGER.info("The employee {} {} was obtained"
                ,employeeAssignmentCampaignResponse.getName()
                ,employeeAssignmentCampaignResponse.getLastName());
        return ResponseEntity.status(HttpStatus.OK).body(employeeAssignmentCampaignResponse);
    }

    public Supplier<ResponseEntity<EmployeeAssignmentCampaignResponse>> obtainEmptyEmployeeAssignment(){
        return () ->{
            LOGGER.warn("There are no employees with the established search parameters");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new EmployeeAssignmentCampaignResponse());
        };
    }

}
