package com.employee.manager.mapper;


import com.employee.manager.model.dto.EmployeeCampaignDTO;
import com.employee.manager.service.http.CampaignStatusRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CampaignStatusMapper {

    List<EmployeeCampaignDTO> obtainCampaignEmployees(@Param("request") CampaignStatusRequest request);
}
