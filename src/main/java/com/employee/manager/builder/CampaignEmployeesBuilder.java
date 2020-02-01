package com.employee.manager.builder;

import com.employee.manager.model.EmployeeCampaign;
import com.employee.manager.model.dto.EmployeeCampaignDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CampaignEmployeesBuilder implements Function<List<EmployeeCampaignDTO>, List<EmployeeCampaign>> {

    private final Function<EmployeeCampaignDTO, EmployeeCampaign> employeeCampaignBuilder;

    @Autowired
    public CampaignEmployeesBuilder(Function<EmployeeCampaignDTO, EmployeeCampaign> employeeCampaignBuilder) {
        this.employeeCampaignBuilder = employeeCampaignBuilder;
    }

    @Override
    public List<EmployeeCampaign> apply(List<EmployeeCampaignDTO> employeeListCampaignDTO) {
         return employeeListCampaignDTO
                .stream()
                 .filter(employeeCampaignDTO -> (employeeCampaignDTO.getName() != null
                         && employeeCampaignDTO.getLastName() != null))
                .map(employeeCampaignBuilder)
                .collect(Collectors.toList());
    }
}
