package com.employee.manager.utils.suppliers;

import com.employee.manager.service.http.EmployeeAssignmentCampaignResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class EmployeeAssignmentSupplier implements Supplier<ResponseEntity<EmployeeAssignmentCampaignResponse>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAssignmentSupplier.class);

    @Override
    public ResponseEntity<EmployeeAssignmentCampaignResponse> get() {
        LOGGER.warn("There are no employees with the established search parameters");
        return ResponseEntity.noContent().build();
    }
}
