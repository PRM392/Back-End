package org.foodie_tour.modules.report.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.foodie_tour.modules.booking.enums.PaymentMethod;
import org.foodie_tour.modules.transaction.enums.CashFlow;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionReportResponse {
    Long amount;
    LocalDateTime createdAt;
    CashFlow cashFlow;
    PaymentMethod paymentMethod;
}