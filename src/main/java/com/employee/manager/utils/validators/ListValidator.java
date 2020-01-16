package com.employee.manager.utils.validators;

import com.employee.manager.model.dto.EmployeeDTO;
import com.employee.manager.service.http.EmployeeListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class ListValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListValidator.class);

    public Function<List<EmployeeDTO>, ResponseEntity<EmployeeListResponse>> obtainEmployeeListValidator() {
        return this::obtainList;
    }

    ResponseEntity<EmployeeListResponse> obtainList(List<EmployeeDTO> list) {
        LOGGER.info("List of {} employees was obtained", list.size());
        return ResponseEntity.ok(new EmployeeListResponse(list));
    }

    public Supplier<ResponseEntity<EmployeeListResponse>> obtainEmptyList() {
        return () -> {
            LOGGER.warn("There are no employees with the established search parameters");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new EmployeeListResponse(Collections.emptyList()));
        };
    }
}
