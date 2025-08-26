package com.danieldev.Learniverse.dto.response;

import com.danieldev.Learniverse.model.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentResponse {

    private Long id;
    private String title;
    private String description;
    private List<Section> sections;
    private Long idSubtopic;
}
