package com.employee.manager.mapper;


import com.employee.manager.model.dto.EmployeeDTO;
import com.employee.manager.service.http.SearchRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface EmployeeListMapper {

    List<EmployeeDTO> obtainEmployeeList(@Param("request") SearchRequest request);
}
