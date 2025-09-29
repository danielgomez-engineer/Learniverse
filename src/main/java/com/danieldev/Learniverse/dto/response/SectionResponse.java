package com.danieldev.Learniverse.dto.response;


import com.danieldev.Learniverse.model.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionResponse {

    private Long id;
    private String title;
    private String description;
    private int orderIndex;
    private String code;
    private Language language;
    private String urlVideo;
    private String urlImage;
    private Long idContent;
}
