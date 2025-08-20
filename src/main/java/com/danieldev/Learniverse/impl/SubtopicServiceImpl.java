package com.danieldev.Learniverse.impl;

import com.danieldev.Learniverse.dto.request.SubtopicRequest;
import com.danieldev.Learniverse.dto.response.SubtopicResponse;
import com.danieldev.Learniverse.exception.ResourceNotFoundException;
import com.danieldev.Learniverse.model.Subtopic;
import com.danieldev.Learniverse.model.Topic;
import com.danieldev.Learniverse.repository.SubtopicRepository;
import com.danieldev.Learniverse.repository.TopicRepository;
import com.danieldev.Learniverse.service.SubtopicService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubtopicServiceImpl implements SubtopicService {

    private final TopicRepository topicRepository;
    private final SubtopicRepository subtopicRepository;
    private final ModelMapper mapper;

    @Override
    public SubtopicResponse createSubtopic(SubtopicRequest request) {
        Topic topic = topicRepository.findById(request.getIdTopic())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "NO existe tema asociado con id: " + request.getIdTopic()
                ));

        Subtopic subtopic = mapper.map(request, Subtopic.class);
        subtopic.setTopic(topic);

        Subtopic savedSubtopic = subtopicRepository.save(subtopic);

        return mapper.map(savedSubtopic, SubtopicResponse.class);
    }


    @Override
    public List<SubtopicResponse> findAllSubtopics() {
        List<Subtopic> subtopics = subtopicRepository.findAll();

        if (subtopics.isEmpty()) {
            throw new ResourceNotFoundException("No hay subtemas disponibles");
        }
        return subtopics.stream().map(subtopic -> mapper.map(subtopic, SubtopicResponse.class)).
                toList();

    }

    @Override
    public SubtopicResponse findByIdSubtopic(Long id) {
        Subtopic subtopic = subtopicRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("NO existe subtema asociado con id: " + id));
        return mapper.map(subtopic, SubtopicResponse.class);
    }

    @Override
    public SubtopicResponse updateSubtopic(SubtopicRequest request, Long id) {

        // Buscar subtema existente
        Subtopic existingSubtopic = subtopicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subtema no encontrado"));

        // Buscar tema asociado
        Topic topic = topicRepository.findById(request.getIdTopic())
                .orElseThrow(() -> new ResourceNotFoundException("Tema no encontrado"));

        // Actualizar campos manualmente
        existingSubtopic.setTitle(request.getTitle());
        existingSubtopic.setDescription(request.getDescription());
        existingSubtopic.setTopic(topic);

        // Guardar cambios
        Subtopic updatedSubtopic = subtopicRepository.save(existingSubtopic);

        // Mapear a response
        return mapper.map(updatedSubtopic, SubtopicResponse.class);
    }


    @Override
    public void deleteSubtopic(Long id) {
        if (!subtopicRepository.existsById(id)) {
            throw new ResourceNotFoundException("NO existe subtema asociado con id: " + id);
        }
        subtopicRepository.deleteById(id);
    }

    @Override
    public List<SubtopicResponse> findByTopicId(Long topicId) {

        topicRepository.findById(topicId).
                orElseThrow(() -> new ResourceNotFoundException("NO existe tema asociado con id: " + topicId));

        List<Subtopic> subtopics = subtopicRepository.findByTopicId(topicId);

        if(subtopics.isEmpty()) {
            throw new ResourceNotFoundException("No hay subtemas asociados con el id " + topicId);
        }
        return subtopics.stream()
                .map(subtopic -> mapper.map(subtopic, SubtopicResponse.class))
                .toList();
    }


}

