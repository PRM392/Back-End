package org.foodie_tour.modules.images.entity;

import jakarta.persistence.*;
import lombok.*;
import org.foodie_tour.modules.images.enums.CheckPointImageStatus;
import org.foodie_tour.modules.routes.entity.CheckPoint;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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

    @Builder.Default
    @Column(name = "is_primary")
    private Boolean isPrimary = false;

    @Builder.Default
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CheckPointImageStatus status = CheckPointImageStatus.ACTIVE;

    @Builder.Default
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
