package com.employee.manager.mapper;


import com.employee.manager.model.dto.EmployeeDTO;
import com.employee.manager.service.http.AssignTypeRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface EmployeeListWithoutAssignmentMapper {

    List<EmployeeDTO> obtainEmployeeListWithoutAssignment();
}
