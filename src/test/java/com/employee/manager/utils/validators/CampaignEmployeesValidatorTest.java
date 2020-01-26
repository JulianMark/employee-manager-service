package com.employee.manager.utils.validators;

import com.employee.manager.builder.CampaignEmployeesBuilder;
import com.employee.manager.model.EmployeeCampaign;
import com.employee.manager.model.dto.EmployeeCampaignDTO;
import com.employee.manager.model.dto.EmployeeDTO;
import com.employee.manager.service.http.CampaignEmployeesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class CampaignEmployeesValidatorTest {

    @Mock
    private CampaignEmployeesBuilder builder;

    @InjectMocks
    private CampaignEmployeesValidator sut;

    @BeforeEach
    public void setUp(){ MockitoAnnotations.initMocks(this); }

    @Test
    @DisplayName("When the method receives a list is not empty should returns 200")
    public void CampaignEmployeesValidator_ReceivesNotEmptyList_ReturnsOk () {
        EmployeeCampaignDTO validEmployeeCampaignDTO = new EmployeeCampaignDTO(1,"JOHN", "DOU",
                2f, 400f,6f,2f,"","");
        List<EmployeeCampaignDTO> validList = Arrays.asList(validEmployeeCampaignDTO);
        System.out.println(validList.size());

        when(builder.apply(validList)).thenReturn(any());

        ResponseEntity<CampaignEmployeesResponse> responseEntity = sut.apply(validList);
        System.out.println(responseEntity.getBody().getEmployeeList().size());

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getEmployeeList().get(0).toString(),
                is (validList.get(0).toString()));
    }
}