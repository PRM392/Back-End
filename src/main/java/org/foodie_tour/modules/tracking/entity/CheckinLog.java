package org.foodie_tour.modules.tracking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.foodie_tour.modules.routes.entity.RouteCheckpoint;
import org.foodie_tour.modules.tracking.enums.CheckinStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "checkin_logs", indexes = {
        @Index(name = "idx_checkin_session", columnList = "session_id"),
        @Index(name = "idx_checkin_checkpoint", columnList = "route_checkpoint_id")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckinLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkin_id")
    private Long checkinId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private TourSession session;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_checkpoint_id", nullable = false)
    private RouteCheckpoint routeCheckpoint;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CheckinStatus status;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "note")
    private String note;

    @CreationTimestamp
    @Column(name = "checked_at", updatable = false)
    private LocalDateTime checkedAt;
}
