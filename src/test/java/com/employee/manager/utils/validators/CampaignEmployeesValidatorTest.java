package com.employee.manager.utils.validators;

import com.employee.manager.builder.CampaignEmployeesBuilder;
import com.employee.manager.builder.EmployeeCampaignBuilder;
import com.employee.manager.model.EmployeeCampaign;
import com.employee.manager.model.dto.EmployeeCampaignDTO;
import com.employee.manager.service.http.CampaignEmployeesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;


class CampaignEmployeesValidatorTest {

    @Mock
    private CampaignEmployeesBuilder campaignEmployeesBuilder;

    @Mock
    private EmployeeCampaignBuilder employeeCampaignBuilder;

    @InjectMocks
    private CampaignEmployeesValidator sut;

    @BeforeEach
    public void setUp(){ MockitoAnnotations.initMocks(this); }

    @Test
    @DisplayName("When the method receives a list is not empty should returns 200")
    public void CampaignEmployeesValidator_ReceivesNotEmptyList_ReturnsOk () {
        EmployeeCampaignDTO validEmployeeCampaignDTO = new EmployeeCampaignDTO(1,"JOHN", "DOU",
                2f, 400f,1f,6f,2f,"","");
        List<EmployeeCampaignDTO> validList = Arrays.asList(validEmployeeCampaignDTO);
        EmployeeCampaign validEmployeeCampaign = new EmployeeCampaign(1,"JOHN", "DOU",
                2f, 400f,6f,2f,0f,
                0f,0.5f,"","");
        List<EmployeeCampaign> employeesCampaign = Arrays.asList(validEmployeeCampaign);
        EmployeeCampaign employeeCampaign = employeeCampaignBuilder.apply(validEmployeeCampaignDTO);
        when(employeeCampaignBuilder.apply(validEmployeeCampaignDTO)).thenReturn(employeeCampaign);
        when(campaignEmployeesBuilder.apply(validList)).thenReturn(employeesCampaign);

        ResponseEntity<CampaignEmployeesResponse> responseEntity = sut.apply(validList);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getEmployeeList().get(0).toString(),
                is (employeesCampaign.get(0).toString()));
    }

    @Test
    @DisplayName("When the method receives a empty list should returns 204")
    public void CampaignEmployeesValidator_ReceivesEmptyList_ReturnsNoContent () {
        List<EmployeeCampaignDTO> emptyList = Collections.EMPTY_LIST;

        ResponseEntity<CampaignEmployeesResponse> responseEntity = sut.apply(emptyList);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }


}