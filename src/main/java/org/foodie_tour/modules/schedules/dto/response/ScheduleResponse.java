package org.foodie_tour.modules.schedules.dto.response;

import lombok.Builder;
import lombok.Data;
import org.foodie_tour.modules.schedules.enums.ScheduleStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class ScheduleResponse {

    private Long scheduleId;
    private Long tourId;
    private Long routeId;
    private String scheduleNote;
    private String scheduleDescription;
    private Integer minPax;
    private Integer maxPax;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime departureAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
    private ScheduleStatus scheduleStatus;
    
    private Integer totalBookedPax;
    private Integer availableSlots;
    private Boolean isAvailable;
}
