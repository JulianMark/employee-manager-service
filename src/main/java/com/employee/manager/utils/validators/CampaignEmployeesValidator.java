package com.employee.manager.utils.validators;

import com.employee.manager.builder.CampaignEmployeesBuilder;
import com.employee.manager.model.EmployeeCampaign;
import com.employee.manager.model.dto.EmployeeCampaignDTO;
import com.employee.manager.service.http.CampaignEmployeesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class CampaignEmployeesValidator {

    private final CampaignEmployeesBuilder campaignEmployeesBuilder;

    public CampaignEmployeesValidator(CampaignEmployeesBuilder campaignEmployeesBuilder) {
        this.campaignEmployeesBuilder = campaignEmployeesBuilder;
    }

    public Function <List<EmployeeCampaignDTO>, ResponseEntity<CampaignEmployeesResponse>> obtainCampaignEmployeesValidator(){
        return this::obtainCampaignEmployees;
    }

    private ResponseEntity<CampaignEmployeesResponse> obtainCampaignEmployees(List<EmployeeCampaignDTO> employeeCampaignDTOS) {
        if (!employeeCampaignDTOS.isEmpty()){
            List<EmployeeCampaign> listResponse = campaignEmployeesBuilder.apply(employeeCampaignDTOS);
            return ResponseEntity.ok(new CampaignEmployeesResponse(listResponse));
        }
        return ResponseEntity.noContent().build();
    }
}
