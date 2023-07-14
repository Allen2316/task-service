package com.allen.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity(name = "task")
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)    
    private Long id;

    @Column(unique = true)
    private String name;
    private String description;
    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    private Long userId;

}
