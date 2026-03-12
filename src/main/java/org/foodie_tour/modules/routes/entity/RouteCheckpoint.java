package org.foodie_tour.modules.routes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.foodie_tour.modules.routes.enums.RouteCheckPointStatus;

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

    @Column(name = "checkpoint_id")
    private Long checkpointId;

    @Column(name = "\"order\"")
    private Integer order;

    @Column(name = "route_checkpoint_status")
    @Enumerated(EnumType.STRING)
    private RouteCheckPointStatus status;
}
