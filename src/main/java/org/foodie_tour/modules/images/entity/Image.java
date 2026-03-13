package org.foodie_tour.modules.images.entity;

import jakarta.persistence.*;
import lombok.*;
import org.foodie_tour.modules.images.enums.ImageStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images")
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private Long imageId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_description")
    private String imageDescription;

    @Builder.Default
    @Column(name = "image_status")
    @Enumerated(EnumType.STRING)
    private ImageStatus imageStatus = ImageStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CheckPointImage> checkPointImages;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TourImage> tourImages;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FeedbackImage> feedbackImages;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DishImage> dishImages;
}