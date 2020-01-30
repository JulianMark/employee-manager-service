package com.employee.manager.mapper;


import com.employee.manager.service.http.AddRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
@FunctionalInterface
public interface AddMapper {

    void addEmployee (@Param("addRequest") AddRequest addRequest);
}
