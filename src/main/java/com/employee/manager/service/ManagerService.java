package com.employee.manager.service;

import com.employee.manager.mapper.AddMapper;
import com.employee.manager.service.http.AddRequest;
import com.employee.manager.service.http.AddResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.employee.manager.Utils.Utils.validateNotNullOrEmpty;
import static com.employee.manager.Utils.Utils.validateRequest;

@RestController
public class ManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerService.class);
    private final AddMapper addMapper;

    @Autowired
    public ManagerService(AddMapper addMapper) {
        this.addMapper = addMapper;
    }

    @PostMapping(
            value = "employee/manager/add",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddResponse> addEmployee (@RequestBody AddRequest addRequest){
        try {
            validateRequest(addRequest);
            validateNotNullOrEmpty(addRequest.getName());
            validateNotNullOrEmpty(addRequest.getLastName());
            validateNotNullOrEmpty(addRequest.getNickname());
            validateNotNullOrEmpty(addRequest.getPassword());
            addMapper.addEmployee(addRequest);
            LOGGER.info("The employee {} {} registered successfully"
                    ,addRequest.getName()
                    ,addRequest.getLastName());
            return ResponseEntity.ok(new AddResponse((byte) 0));
        }catch (IllegalArgumentException iae){
            LOGGER.warn("The parameters entered are not valid: ");
            return ResponseEntity.badRequest().body(new AddResponse(iae.getMessage()));
        }catch (Exception ex) {
        LOGGER.error("An error occurred while trying to add the employee {} {} "
                ,addRequest.getName()
                ,addRequest.getLastName()
                ,ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AddResponse(ex.getMessage()));
        }
    }
}
