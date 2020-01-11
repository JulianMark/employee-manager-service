package com.employee.manager.service;

import com.employee.manager.mapper.AddMapper;
import com.employee.manager.mapper.AssignTypeMapper;
import com.employee.manager.service.http.AddRequest;
import com.employee.manager.service.http.AssignTypeRequest;
import com.employee.manager.service.http.QueryResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.employee.manager.Utils.Utils.validateIdNumber;
import static com.employee.manager.Utils.Utils.validateNotNullOrEmpty;
import static com.employee.manager.Utils.Utils.validateRequest;

@RestController
@Api(value="Employee Manager WS", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerService.class);
    private final AddMapper addMapper;
    private final AssignTypeMapper assignTypeMapper;

    @Autowired
    public ManagerService(AddMapper addMapper, AssignTypeMapper assignTypeMapper) {
        this.addMapper = addMapper;
        this.assignTypeMapper = assignTypeMapper;
    }

    @PostMapping(
            value = "employee/manager/add",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Agregar empleado al sistema")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se obtiene el resultado de la consulta a base", response = QueryResponse.class),
            @ApiResponse(code = 400, message = "Argumentos inválidos", response = QueryResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del servicio web", response = QueryResponse.class)
    })
    public ResponseEntity<QueryResponse> addEmployee (@RequestBody AddRequest addRequest){
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
            return ResponseEntity.ok(new QueryResponse((byte) 0));
        }catch (IllegalArgumentException iae){
            LOGGER.warn("The parameters entered are not valid: ");
            return ResponseEntity.badRequest().body(new QueryResponse(iae.getMessage()));
        }catch (Exception ex) {
        LOGGER.error("An error occurred while trying to add the employee {} {} "
                ,addRequest.getName()
                ,addRequest.getLastName()
                ,ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).body(new QueryResponse(ex.getMessage()));
        }
    }

    @PostMapping(
            value = "employee/manager/assigntype",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Asignar un cargo especifico a un empleado especifico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se obtiene el resultado de la consulta a base", response = QueryResponse.class),
            @ApiResponse(code = 400, message = "Argumentos inválidos", response = QueryResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del servicio web", response = QueryResponse.class)
    })
    public ResponseEntity<QueryResponse> assignTypeEmployee (@RequestBody AssignTypeRequest assignTypeRequest){
        try {
            validateRequest(assignTypeRequest);
            validateIdNumber(assignTypeRequest.getIdType());
            validateIdNumber(assignTypeRequest.getIdEmployee());
            assignTypeMapper.assignType(assignTypeRequest);
            LOGGER.info("The employee with ID {} was assigned successfully"
                    ,assignTypeRequest.getIdEmployee());
            return ResponseEntity.ok(new QueryResponse((byte) 0));
        }catch (IllegalArgumentException iae){
            LOGGER.warn("The parameters entered are not valid: ");
            return ResponseEntity.badRequest().body(new QueryResponse(iae.getMessage()));
        }catch (Exception ex) {
            LOGGER.error("An error occurred while trying to assign the employee with ID {} to type ID {}"
                    ,assignTypeRequest.getIdEmployee()
                    ,assignTypeRequest.getIdType()
                    ,ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).body(new QueryResponse(ex.getMessage()));
        }
    }
}
