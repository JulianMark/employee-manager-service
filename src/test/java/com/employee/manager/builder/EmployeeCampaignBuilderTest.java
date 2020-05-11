package com.employee.manager.builder;

import com.employee.manager.model.EmployeeCampaign;
import com.employee.manager.model.dto.EmployeeCampaignDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


class EmployeeCampaignBuilderTest {

    private Integer id = 1;
    private String name = "juan";
    private String lastName= "dou";
    private Float totalDonations  = 0f;
    private Float totalAmountDonations = 1500f;
    private Float creditType = 2f;
    private Float totalProductiveHours = 2f;
    private Float totalNonProductiveHours = 30f;
    private String initialDate;
    private String finalDate;

    private EmployeeCampaignDTO VALID_EMPLOYEE_DTO = new EmployeeCampaignDTO(id, name, lastName, totalDonations,
            totalAmountDonations, creditType, totalProductiveHours, totalNonProductiveHours, initialDate, finalDate);


    @InjectMocks
    private EmployeeCampaignBuilder sut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void applySut() {
        EmployeeCampaign expected = sut.apply(VALID_EMPLOYEE_DTO);

        assertThat(expected.getTotalAverageCatchment(), is(0f));
    }
}