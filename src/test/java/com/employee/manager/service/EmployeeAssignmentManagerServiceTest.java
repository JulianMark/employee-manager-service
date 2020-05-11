package com.employee.manager.service;

import com.employee.manager.mapper.EmployeeAssignmentCampaignMapper;
import com.employee.manager.service.http.EmployeeAssignmentCampaignRequest;
import com.employee.manager.service.http.EmployeeAssignmentCampaignResponse;
import com.employee.manager.utils.validators.EmployeeAssignmentValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Assign employee to a campaign")
class EmployeeAssignmentManagerServiceTest {

    private final EmployeeAssignmentCampaignRequest VALID_REQUEST = new EmployeeAssignmentCampaignRequest(2, 1);

    @Mock
    private EmployeeAssignmentCampaignMapper employeeAssignmentCampaignMapper;

    @Mock
    public EmployeeAssignmentValidator employeeAssignmentValidator;

    @InjectMocks
    private ManagerService sut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    static class EmployeeAssignmentRequestArgumentsSource implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    null
                    , new EmployeeAssignmentCampaignRequest(null, 1)
                    , new EmployeeAssignmentCampaignRequest(1, null)
                    , new EmployeeAssignmentCampaignRequest(0, 1)
                    , new EmployeeAssignmentCampaignRequest(-1, 1)
            ).map(Arguments::of);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(EmployeeAssignmentRequestArgumentsSource.class)
    @DisplayName("When EmployeeAssignmentCampaignRequest is null should return 400 (Bad Request)")
    void obtainEmployeeAssignmentCampaign__RequestIsNull_ReturnsBadRequest(EmployeeAssignmentCampaignRequest request) {
        ResponseEntity<EmployeeAssignmentCampaignResponse> responseEntity = sut
                .obtainEmployeeAssignmentCampaign(request);

        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.BAD_REQUEST));
    }

    @Test
    @DisplayName("When obtainEmployeeList is empty. Should return 204 (No Content)")
    public void obtainEmployeeListWithoutAssignment_EmployeeListIsEmpty_ReturnsNoContent() {
        when(employeeAssignmentCampaignMapper
                .obtainEmployeeAssignmentCampaign(any(EmployeeAssignmentCampaignRequest.class)))
                .thenReturn(new EmployeeAssignmentCampaignResponse());
        when(employeeAssignmentValidator.apply(any(EmployeeAssignmentCampaignResponse.class)))
                .thenReturn(ResponseEntity.noContent().build());

        ResponseEntity<EmployeeAssignmentCampaignResponse> responseEntity = sut
                .obtainEmployeeAssignmentCampaign(VALID_REQUEST);

        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.NO_CONTENT));
    }


    @Test
    @DisplayName("When obtainEmployeeAssignmentCampaign did not catch exceptions. Should return 200 (OK)")
    public void obtainEmployeeAssignmentCampaign_NoExceptionCaught_ReturnsOk() {
        final EmployeeAssignmentCampaignResponse employeeResponse = new EmployeeAssignmentCampaignResponse(
                1, "JUAN", "PEREZ", "35797912", null);
        when(employeeAssignmentCampaignMapper
                .obtainEmployeeAssignmentCampaign(any(EmployeeAssignmentCampaignRequest.class)))
                .thenReturn(employeeResponse);
        when(employeeAssignmentValidator.apply(any(EmployeeAssignmentCampaignResponse.class)))
                .thenReturn(ResponseEntity.ok(employeeResponse));

        ResponseEntity<EmployeeAssignmentCampaignResponse> responseEntity = sut
                .obtainEmployeeAssignmentCampaign(VALID_REQUEST);

        assertThat("Status Code Response", responseEntity.getStatusCode(),
                is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getId(), is(employeeResponse.getId()));
    }
}