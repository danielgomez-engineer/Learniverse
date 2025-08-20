package com.danieldev.Learniverse.dto.response;

import com.danieldev.Learniverse.model.Content;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubtopicResponse {

    private Long id;
    private String title;
    private String description;
    private List<Content> contents;
    private Long idTopic;
}
