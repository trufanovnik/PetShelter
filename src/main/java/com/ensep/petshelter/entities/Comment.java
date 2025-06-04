package com.ensep.petshelter.entities;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @PreRemove
    private void preRemove() {
        if (this.author != null) {
            this.author.getComments().remove(this);
        }
        if (this.shelter != null) {
            this.shelter.getComments().remove(this);
        }
        if (this.pet != null) {
            this.pet.getComments().remove(this);
        }
    }
}
