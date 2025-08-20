package com.danieldev.Learniverse.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicRequest {

    private Long id;

    @NotBlank(message = "El titulo es obligatorio")
    private String title;

    @NotBlank(message = "La descripcion es obligatoria")
    private String description;
}
