package com.employee.manager.utils.validators;

import com.employee.manager.builder.CampaignEmployeesBuilder;
import com.employee.manager.model.EmployeeCampaign;
import com.employee.manager.model.dto.EmployeeCampaignDTO;
import com.employee.manager.service.http.CampaignEmployeesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class CampaignEmployeesValidator implements Function<List<EmployeeCampaignDTO>, ResponseEntity<CampaignEmployeesResponse>>{

    private final Function<List<EmployeeCampaignDTO>, List<EmployeeCampaign>> campaignEmployeesBuilder;

    @Autowired
    public CampaignEmployeesValidator(Function<List<EmployeeCampaignDTO>, List<EmployeeCampaign>> campaignEmployeesBuilder) {
        this.campaignEmployeesBuilder = campaignEmployeesBuilder;
    }

    @Override
    public ResponseEntity<CampaignEmployeesResponse> apply(List<EmployeeCampaignDTO> employeeCampaignDTOS) {
        if (!employeeCampaignDTOS.isEmpty()){
            List<EmployeeCampaign> listResponse = campaignEmployeesBuilder.apply(employeeCampaignDTOS);
            return ResponseEntity.ok(new CampaignEmployeesResponse(listResponse));
        }
        return ResponseEntity.noContent().build();
    }
}
