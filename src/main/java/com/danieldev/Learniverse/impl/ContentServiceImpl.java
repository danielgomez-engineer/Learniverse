package com.danieldev.Learniverse.impl;

import com.danieldev.Learniverse.dto.request.ContentRequest;
import com.danieldev.Learniverse.dto.response.ContentResponse;
import com.danieldev.Learniverse.dto.response.SectionResponse;
import com.danieldev.Learniverse.exception.ResourceNotFoundException;
import com.danieldev.Learniverse.model.Content;
import com.danieldev.Learniverse.model.Section;
import com.danieldev.Learniverse.model.Subtopic;
import com.danieldev.Learniverse.repository.ContentRepository;
import com.danieldev.Learniverse.repository.SectionRepository;
import com.danieldev.Learniverse.repository.SubtopicRepository;
import com.danieldev.Learniverse.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final SubtopicRepository subtopicRepository;
    private final ContentRepository contentRepository;
    private final SectionRepository sectionRepository;
    private final ModelMapper mapper;


    @Override
    public ContentResponse create(ContentRequest request) {
        Subtopic subtopic = subtopicRepository.findById(request.getIdSubtopic())
                .orElseThrow(() -> new ResourceNotFoundException("Subtema No encontrado. contentImpl/create"));

        Content content = mapper.map(request, Content.class);
        content.setSubtopic(subtopic);

        Content savedContent = contentRepository.save(content);
        return mapper.map(savedContent,ContentResponse.class);

    }

    @Override
    public List<ContentResponse> findAll() {
        List<Content> contents = contentRepository.findAll();

        if (contents.isEmpty()) {
            throw new ResourceNotFoundException("No hay contenidos registrados. contentImpl/findAll");
        }

        return contents.stream().map(content -> {
            ContentResponse dto = mapper.map(content, ContentResponse.class);

            // mapeo manual de secciones con orden por orderIndex
            if (content.getSections() != null) {
                List<SectionResponse> sectionDtos = content.getSections().stream()
                        .sorted(Comparator.comparingInt(Section::getOrderIndex)) // 👈 ordena por orderIndex ASC
                        .map(section -> mapper.map(section, SectionResponse.class))
                        .toList();
                dto.setSections(sectionDtos);
            }

            return dto;
        }).toList();
    }



    @Override
    public ContentResponse findById(Long id) {
        Content content = contentRepository.findById(id).
                orElseThrow (() -> new ResourceNotFoundException("Contenido no encontrado. contentImpl/findById"));

        return mapper.map(content, ContentResponse.class);

    }

    @Override
    public ContentResponse update(Long id, ContentRequest request) {
        Content existingContent = contentRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("COntenido no encontrado. contentImpl/update"));

        Subtopic subtopic = subtopicRepository.findById(request.getIdSubtopic()).
                orElseThrow(() -> new ResourceNotFoundException("Subtema no encontrado. contentImpl/update"));

        //actualizamos campos requeridos
        existingContent.setTitle(request.getTitle());
        existingContent.setDescription(request.getDescription());
        existingContent.setSubtopic(subtopic);

        Content updateContent = contentRepository.save(existingContent);

        return mapper.map(updateContent, ContentResponse.class);
    }

    @Override
    public void delete(Long id) {

        if(!contentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contenido no encontrado. contentImpl/delete");
        }
        contentRepository.deleteById(id);

    }

    @Override
    public List<ContentResponse> findBySubtopicId(Long subtopicId) {
        subtopicRepository.findById(subtopicId).
                orElseThrow (() -> new ResourceNotFoundException("Subtema no encontrado. contentImpl/findBySubtopicId"));

        List<Content> contents = contentRepository.findBySubtopicId(subtopicId);

        if(contents.isEmpty()) {
            throw new ResourceNotFoundException("no hay contenidos asociados al id subtema " + subtopicId + " contentImpl/findBySubtopicId");
        }
        return contents.stream()
                .map(content -> mapper.map(content, ContentResponse.class))
                .toList();
    }
}
