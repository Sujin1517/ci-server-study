package com.example.test_back.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BOARDS")
@Builder
@EqualsAndHashCode
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTENT_ID")
    private Long id;

    @Column(name = "CONTENT_NAME")
    private String name;

    @Column(name = "CONTENT_TEXT")
    private String text;
}
