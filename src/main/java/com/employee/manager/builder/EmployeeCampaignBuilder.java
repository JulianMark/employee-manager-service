package com.employee.manager.builder;

import com.employee.manager.model.EmployeeCampaign;
import com.employee.manager.model.dto.EmployeeCampaignDTO;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EmployeeCampaignBuilder implements Function<EmployeeCampaignDTO, EmployeeCampaign> {

    @Override
    public EmployeeCampaign apply(EmployeeCampaignDTO dto) {

        final Float totalDonations = (dto.getTotalDonations() == null)
                ? 0f
                : dto.getTotalDonations();
        final Float totalAmountDonations = (dto.getTotalAmountDonations() == null)
                ? 0f
                : dto.getTotalAmountDonations();
        final Float totalProductiveHours = (dto.getTotalProductiveHours() == null)
                ? 0f
                : dto.getTotalProductiveHours();
        final Float totalNonProductiveHours = (dto.getTotalNonProductiveHours() == null)
                ? 0f
                : dto.getTotalNonProductiveHours();
        final Float totalAverageCatchment = (totalDonations == 0f)
                ? 0f
                : (totalDonations / totalProductiveHours);
        final Float totalAverageAmount = (totalAmountDonations == 0f)
                ? 0f
                : (totalAmountDonations / totalDonations);
        final Float totalAverageCreditType = (totalDonations == 0f || dto.getCreditType() == 0f)
                ? 0f
                : (dto.getCreditType() / totalDonations) * 100 ;

        return new EmployeeCampaign(dto.getId()
                , dto.getName()
                , dto.getLastName()
                , totalDonations
                , totalAmountDonations
                , totalProductiveHours
                , totalNonProductiveHours
                , totalAverageCatchment
                , totalAverageAmount
                , totalAverageCreditType
                , dto.getInitialDate()
                , dto.getFinalDate());
    }
}
