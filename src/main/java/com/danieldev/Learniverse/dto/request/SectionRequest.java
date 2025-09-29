package com.danieldev.Learniverse.dto.request;

;
import com.danieldev.Learniverse.model.Language;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionRequest {

    private Long id;

    @NotBlank(message = "El titulo debe ser obligatorio")
    private String title;

    @NotBlank(message = "La descripcion debe ser obligatoria")
    private String description;
    private int orderIndex;
    private String code;
    private Language language;
    private String urlVideo;
    private String urlImage;
    private Long idContent;

}
