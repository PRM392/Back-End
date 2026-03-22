package org.foodie_tour.modules.booking.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProcessCancelRequest {
    String bookingCode;
    
    boolean approved;  // true = duyệt hủy, false = từ chối hủy
    
    String adminNote;  // Ghi chú của admin (lý do từ chối nếu có)
}
