package com.employee.manager.service;

import com.employee.manager.mapper.AddMapper;
import com.employee.manager.mapper.AssignTypeMapper;
import com.employee.manager.mapper.EmployeeAssignmentCampaignMapper;
import com.employee.manager.mapper.EmployeeListMapper;
import com.employee.manager.mapper.EmployeeListWithoutAssignmentMapper;
import com.employee.manager.service.http.AddRequest;
import com.employee.manager.service.http.AssignTypeRequest;
import com.employee.manager.service.http.EmployeeAssignmentCampaignRequest;
import com.employee.manager.service.http.EmployeeAssignmentCampaignResponse;
import com.employee.manager.service.http.EmployeeListResponse;
import com.employee.manager.service.http.QueryResponse;
import com.employee.manager.utils.validators.EmployeeAssignmentValidator;
import com.employee.manager.utils.validators.ListValidator;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.employee.manager.utils.validators.request.AddRequestValidator.validateAddRequest;
import static com.employee.manager.utils.validators.request.AssignTypeRequestValidator.validateAssignTypeRequest;
import static com.employee.manager.utils.validators.request.EmployeeAssignmentRequestValidator.validateEmployeeAssignmentRequest;


@RestController
@Api(value="Employee Manager WS", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerService.class);
    private final AddMapper addMapper;
    private final AssignTypeMapper assignTypeMapper;
    private final EmployeeListWithoutAssignmentMapper employeeListWithoutAssignmentMapper;
    private final EmployeeListMapper employeeListMapper;
    private final EmployeeAssignmentCampaignMapper employeeAssignmentCampaignMapper;
    private final ListValidator listValidator;
    private final EmployeeAssignmentValidator employeeAssignmentValidator;

    @Autowired
    public ManagerService(AddMapper addMapper,
                          AssignTypeMapper assignTypeMapper,
                          EmployeeListWithoutAssignmentMapper employeeListWithoutAssignmentMapper,
                          EmployeeListMapper employeeListMapper,
                          EmployeeAssignmentCampaignMapper employeeAssignmentCampaignMapper,
                          ListValidator listValidator,
                          EmployeeAssignmentValidator employeeAssignmentValidator) {
        this.addMapper = addMapper;
        this.assignTypeMapper = assignTypeMapper;
        this.employeeListWithoutAssignmentMapper = employeeListWithoutAssignmentMapper;
        this.employeeListMapper = employeeListMapper;
        this.employeeAssignmentCampaignMapper = employeeAssignmentCampaignMapper;
        this.listValidator = listValidator;
        this.employeeAssignmentValidator = employeeAssignmentValidator;
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
            validateAddRequest(addRequest);
            addMapper.addEmployee(addRequest);
            LOGGER.info("The employee {} {} registered successfully"
                    ,addRequest.getName()
                    ,addRequest.getLastName());
            return ResponseEntity.ok(new QueryResponse((byte) 0,null));
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
            value = "employee/manager/assignType",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Asignar un cargo especifico a un empleado especifico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se obtiene el resultado de la consulta a base", response = QueryResponse.class),
            @ApiResponse(code = 400, message = "Argumentos inválidos", response = QueryResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del servicio web", response = QueryResponse.class)
    })
    public ResponseEntity<QueryResponse> assignTypeEmployee (@RequestBody AssignTypeRequest assignTypeRequest){
        try {
            validateAssignTypeRequest(assignTypeRequest);
            assignTypeMapper.assignType(assignTypeRequest);
            LOGGER.info("The employee with ID {} was assigned successfully"
                    ,assignTypeRequest.getIdEmployee());
            return ResponseEntity.ok(new QueryResponse((byte) 0,null));
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

    @PostMapping(
            value = "employee/manager/employeeListWithoutAssignment",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Consultar lista de empleados sin asignación")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se obtiene el resultado de la consulta a base", response = EmployeeListResponse.class),
            @ApiResponse(code = 204, message = "No se obtuvieron empleados sin asignacion", response = EmployeeListResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del servicio web", response = EmployeeListResponse.class)
    })
    public ResponseEntity<EmployeeListResponse> obtainEmployeeListWithoutAssignment (){
        try {
            return Optional.ofNullable(employeeListWithoutAssignmentMapper.obtainEmployeeListWithoutAssignment())
                    .map(listValidator.obtainEmployeeListValidator())
                    .orElseGet(listValidator.obtainEmptyList());
        }catch (Exception ex) {
            LOGGER.error("An error occurred while consulting the list of employees without assignment",ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmployeeListResponse(ex.getMessage()));
        }
    }

    @PostMapping(
            value = "employee/manager/searchEmployee",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Consultar lista de empleados con cierto parametro de busqueda")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se obtiene el resultado de la consulta a base", response = EmployeeListResponse.class),
            @ApiResponse(code = 204, message = "No se obtuvieron empleados con los parametros de busqueda establecidos", response = EmployeeListResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del servicio web", response = EmployeeListResponse.class)
    })
    public ResponseEntity<EmployeeListResponse> searchEmployee(@RequestParam String param){
        try {
            return Optional.ofNullable(employeeListMapper.obtainEmployeeList(param))
                    .map(listValidator.obtainEmployeeListValidator())
                    .orElseGet(listValidator.obtainEmptyList());
        }catch (Exception ex) {
            LOGGER.error("An error occurred while consulting the list of employees with assignment",ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmployeeListResponse(ex.getMessage()));
        }
    }

    @PostMapping(
            value = "employee/manager/obtainEmployeeAssignmentCampaign",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Consultar empleado asignado con cierto cargo para una campaña especifica")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se obtiene el resultado de la consulta a base",
                    response = EmployeeAssignmentCampaignResponse.class),
            @ApiResponse(code = 204, message = "No se obtuvieron empleados con los parametros de busqueda establecidos",
                    response = EmployeeAssignmentCampaignResponse.class),
            @ApiResponse(code = 400, message = "Argumentos inválidos",
                    response = EmployeeAssignmentCampaignResponse.class),
            @ApiResponse(code = 500, message = "Error inesperado del servicio web",
                    response = EmployeeAssignmentCampaignResponse.class)
    })
    public ResponseEntity<EmployeeAssignmentCampaignResponse> obtainEmployeeAssignmentCampaign (
            @RequestBody EmployeeAssignmentCampaignRequest request
            ){
        try {
            validateEmployeeAssignmentRequest(request);
            return Optional.ofNullable(employeeAssignmentCampaignMapper.obtainEmployeeAssignmentCampaign(request))
                    .map(employeeAssignmentValidator.obtainEmployeeAssignmentValidator())
                    .orElseGet(employeeAssignmentValidator.obtainEmptyEmployeeAssignment());
        }catch (IllegalArgumentException iae){
            LOGGER.warn("The parameters entered are not valid");
            return ResponseEntity.badRequest()
                    .body(new EmployeeAssignmentCampaignResponse(iae.getMessage()));
        }catch (Exception ex) {
            LOGGER.error("An error occurred while consulting the assignment of employee for the specific campaign",ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new EmployeeAssignmentCampaignResponse(ex.getMessage()));
        }
    }
}
