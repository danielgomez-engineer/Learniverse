package com.danieldev.Learniverse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "contenidos")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "titulo", nullable = false)
    private String title;

    @Column(name = "descripcion", length = 3000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "subtema_id")
    private Subtopic subtopic;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections = new ArrayList<>();


    }