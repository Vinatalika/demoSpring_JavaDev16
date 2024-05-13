package com.example.demo.data.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "note")
public class NoteEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        @Column(nullable = false)
        private String title;

        @Column(nullable = false, length = 10000)
        private String content;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        private UserEntity user;


        public NoteEntity(String title, String content, UserEntity user) {
            this.title = title;
            this.content = content;
            this.user = user;
        }

    }
