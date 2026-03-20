package org.foodie_tour.modules.report.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportResponse {
    long bookingCompleted;
    long bookingCancelled;
    long totalRevenue;
    long income;
    long outcome;
    long totalCustomer;
    List<TransactionReportResponse> incomeList;
    List<TransactionReportResponse> outcomeList;
    List<BookingReportResponse> bookingCompletedList;
}