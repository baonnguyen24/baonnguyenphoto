package com.baonnguyen.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @CreationTimestamp
    private LocalDateTime uploadedOn;
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
