package com.danieldev.Learniverse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "secciones")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "titulo", nullable = false)
    private String title;

    @Column(name = "descripcion", length = 5000, nullable = false)
    private String description;

    @Column(name = "url_video", length = 500)
    private String urlVideo;

    @Column(name = "url_continuacion", length = 500)
    private String urlContinuation;

    @ManyToOne
    @JoinColumn(name = "contenido_id")
    private Content content;
}
