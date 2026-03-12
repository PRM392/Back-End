package org.foodie_tour.modules.images.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.foodie_tour.modules.images.enums.CheckPointImageStatus;
import org.foodie_tour.modules.routes.entity.CheckPoint;
import org.foodie_tour.modules.tours.enums.TourStatus;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "checkpoint_images")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckPointImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkpoint_image_id")
    private Long checkpointImageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checkpoint_id")
    private CheckPoint checkpoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "checkpoint_image_status")
    @Enumerated(EnumType.STRING)
    private CheckPointImageStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
