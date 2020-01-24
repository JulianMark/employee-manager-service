package com.employee.manager.mapper;


import com.employee.manager.model.dto.EmployeeCampaignDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CampaignEmployeesMapper {

    List<EmployeeCampaignDTO> obtainCampaignEmployees(@Param("idCampaign") Integer idCampaign);
}
