package com.employee.manager.mapper;


import com.employee.manager.service.http.AddRequest;
import com.employee.manager.service.http.AssignTypeRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AssignTypeMapper {

    void assignType(@Param("assignTypeRequest") AssignTypeRequest assignTypeRequest);
}
