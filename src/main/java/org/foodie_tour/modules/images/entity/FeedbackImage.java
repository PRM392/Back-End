package org.foodie_tour.modules.images.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.foodie_tour.modules.feedback.entity.Feedback;
import org.foodie_tour.modules.feedback.enums.FeedbackStatus;
import org.foodie_tour.modules.images.enums.FeedBackImageStatus;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedback_images")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackImageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "feedback_image_status")
    @Enumerated(EnumType.STRING)
    private FeedBackImageStatus status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
