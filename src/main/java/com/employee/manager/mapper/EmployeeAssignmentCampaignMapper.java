package com.employee.manager.mapper;


import com.employee.manager.service.http.EmployeeAssignmentCampaignRequest;
import com.employee.manager.service.http.EmployeeAssignmentCampaignResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface EmployeeAssignmentCampaignMapper {

    EmployeeAssignmentCampaignResponse obtainEmployeeAssignmentCampaign(
            @Param("request") EmployeeAssignmentCampaignRequest request);
}
