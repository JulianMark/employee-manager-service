package com.employee.manager.utils.validators;

import com.employee.manager.model.dto.EmployeeDTO;
import com.employee.manager.service.http.EmployeeListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class ListValidator implements Function<List<EmployeeDTO>, ResponseEntity<EmployeeListResponse>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListValidator.class);

    @Override
    public ResponseEntity<EmployeeListResponse> apply(List<EmployeeDTO> list) {
        if (!list.isEmpty()) {
            LOGGER.info("List of {} employees was obtained", list.size());
            return ResponseEntity.ok(new EmployeeListResponse(list));
        }
        LOGGER.warn("There are no employees with the established search parameters");
        return ResponseEntity.noContent().build();
    }
}
