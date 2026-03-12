package org.foodie_tour.modules.routes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.foodie_tour.modules.images.entity.CheckPointImage;
import org.foodie_tour.modules.routes.enums.CheckPointType;
import org.foodie_tour.modules.tours.entity.Tour;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "checkpoints")
public class CheckPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkpoint_Id")
    private Long checkpointId;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "description")
    private String description;

    @Column(name = "duration_at_location")
    private int durationAtLocation;

    @Column(name = "ggmap_url")
    private String ggmapUrl;

    @Column(name = "checkpoint_type")
    @Enumerated(EnumType.STRING)
    private CheckPointType type;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @OneToMany(mappedBy = "checkpoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CheckPointImage> checkpointImages;
}
