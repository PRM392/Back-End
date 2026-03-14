package org.foodie_tour.modules.routes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.foodie_tour.modules.routes.enums.RouteCheckPointStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "route_checkpoints")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteCheckpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeCheckpointId;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checkpoint_id")
    private CheckPoint checkPoint;

    @Column(name = "\"order\"")
    private Integer order;

    @Column(name = "route_checkpoint_status")
    @Enumerated(EnumType.STRING)
    private RouteCheckPointStatus status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
