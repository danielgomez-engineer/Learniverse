package com.danieldev.Learniverse.impl;

import com.danieldev.Learniverse.dto.request.SectionRequest;
import com.danieldev.Learniverse.dto.response.SectionResponse;
import com.danieldev.Learniverse.exception.ResourceNotFoundException;
import com.danieldev.Learniverse.model.Content;
import com.danieldev.Learniverse.model.Section;
import com.danieldev.Learniverse.repository.ContentRepository;
import com.danieldev.Learniverse.repository.SectionRepository;
import com.danieldev.Learniverse.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final ContentRepository contentRepository;
    private final SectionRepository sectionRepository;
    private final ModelMapper mapper;

    @Override
    public SectionResponse create(SectionRequest request) {
        Content content = contentRepository.findById(request.getIdContent())
                .orElseThrow(() -> new ResourceNotFoundException("Contenido no encontrado. sectionImpl/create."));

        Section section = mapper.map(request, Section.class);
        section.setContent(content);

        // Calcular el siguiente orderIndex
        Integer maxOrderIndex = sectionRepository.findMaxOrderIndexByContent(content);
        int nextOrderIndex = (maxOrderIndex == null) ? 1 : maxOrderIndex + 1;
        section.setOrderIndex(nextOrderIndex);

        Section saveSection = sectionRepository.save(section);
        return mapper.map(saveSection, SectionResponse.class);
    }


    @Override
    public List<SectionResponse> findAll() {
        List<Section> sections = sectionRepository.findAll(Sort.by(Sort.Direction.ASC, "orderIndex"));

        if (sections.isEmpty()) {
            throw new ResourceNotFoundException("No hay secciones en la base de datos. sectionImpl/findAll.");
        }

        return sections.stream()
                .map(section -> mapper.map(section, SectionResponse.class))
                .toList();
    }

    @Override
    public SectionResponse findById(Long id) {
        Section section = sectionRepository.findById(id).
                orElseThrow(() ->new  ResourceNotFoundException("No hay una seccion con el id: " + id + " SectionImpl/findById."));

        return mapper.map(section, SectionResponse.class);
    }

    @Override
    public SectionResponse update(Long id, SectionRequest request) {
        Section existingSection = sectionRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Seccion no encontrada. SectionImpl/update."));

        Content content = contentRepository.findById(request.getIdContent()).
                orElseThrow(() -> new ResourceNotFoundException("Contenido no encontrado. SectionImpl/update."));

        existingSection.setTitle(request.getTitle());
        existingSection.setDescription(request.getDescription());
        existingSection.setCode(request.getCode());
        existingSection.setContent(content);
        existingSection.setLanguage(request.getLanguage());
        existingSection.setUrlVideo(request.getUrlVideo());
        existingSection.setUrlImage(request.getUrlImage());

        Section updateSection = sectionRepository.save(existingSection);
        return mapper.map(updateSection, SectionResponse.class);
    }

    @Override
    public void delete(Long id) {
        if(!sectionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Seccion no encontrada. SectionImpl/delete");
        }
        sectionRepository.deleteById(id);
    }

    @Override
    public List<SectionResponse> findByContentId(Long contentId) {
        contentRepository.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("Contenido no encontrado. SectionImpl/delete"));

        List<Section> sections = sectionRepository.findByContentId(contentId);

        if (sections.isEmpty()) {
            throw new ResourceNotFoundException("No hay secciones en el id de contenido. SectionImpl/findByContentId.");
        }

        return sections.stream()
                .sorted(Comparator.comparingInt(Section::getOrderIndex)) // 👈 ordena por orderIndex ASC
                .map(section -> mapper.map(section, SectionResponse.class))
                .toList();
    }


    @Override
    public SectionResponse findPrevious(Long currentSectionId) {
        Section current = sectionRepository.findById(currentSectionId)
                .orElseThrow(() -> new ResourceNotFoundException("Sección no encontrada con id: " + currentSectionId + " SectionImpl/findPrevius."));
        System.out.println("Buscando anterior de sección " + current.getId() + " con orden: " + current.getOrderIndex());

        return sectionRepository
                .findFirstByContentAndOrderIndexLessThanOrderByOrderIndexDesc(current.getContent(), current.getOrderIndex())
                .map(section -> mapper.map(section, SectionResponse.class))
                .orElse(null);
    }

    @Override
    public SectionResponse findNext(Long currentSectionId) {
        Section current = sectionRepository.findById(currentSectionId)
                .orElseThrow(() -> new ResourceNotFoundException("Sección no encontrada con id: " + currentSectionId + " SectionImpl/findNext."));

        System.out.println("Buscando siguiente de sección " + current.getId() + " con orden: " + current.getOrderIndex());
        return sectionRepository
                .findFirstByContentAndOrderIndexGreaterThanOrderByOrderIndexAsc(current.getContent(), current.getOrderIndex())
                .map(section -> mapper.map(section, SectionResponse.class))
                .orElse(null);
    }


}
