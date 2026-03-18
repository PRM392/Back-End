package org.foodie_tour.modules.tracking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.foodie_tour.modules.employee.entity.Employee;
import org.foodie_tour.modules.schedules.entity.Schedule;
import org.foodie_tour.modules.tracking.enums.TourSessionStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tour_sessions", indexes = {
        @Index(name = "idx_tour_session_schedule", columnList = "schedule_id"),
        @Index(name = "idx_tour_session_guide", columnList = "guide_id"),
        @Index(name = "idx_tour_session_status", columnList = "session_status")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long sessionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guide_id", nullable = false)
    private Employee guide;

    @Column(name = "session_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TourSessionStatus sessionStatus;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder.Default
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuideLocation> locations = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CheckinLog> checkinLogs = new ArrayList<>();
}
