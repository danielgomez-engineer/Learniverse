package com.danieldev.Learniverse.impl;

import com.danieldev.Learniverse.dto.request.TopicRequest;
import com.danieldev.Learniverse.dto.response.SubtopicResponse;
import com.danieldev.Learniverse.dto.response.TopicResponse;
import com.danieldev.Learniverse.exception.ResourceNotFoundException;
import com.danieldev.Learniverse.model.Subtopic;
import com.danieldev.Learniverse.model.Topic;
import com.danieldev.Learniverse.repository.TopicRepository;
import com.danieldev.Learniverse.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository repository;
    private final ModelMapper mapper;

    @Override
    public TopicResponse createTopic(TopicRequest request) {
        Topic topic = mapper.map(request, Topic.class);
        Topic createdTopic = repository.save(topic);
        return mapper.map(createdTopic, TopicResponse.class);

    }

    @Override
    public List<TopicResponse> findAllTopics() {
        List<Topic> topics = repository.findAll();

        if (topics.isEmpty()) {
            throw new ResourceNotFoundException("No hay temas disponibles");
        }

        return topics.stream()
                .map(topic -> mapper.map(topic, TopicResponse.class))
                .collect(Collectors.toList());
    }


    @Override
    public TopicResponse findTopicById(Long id) {
        Topic topic = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Tema no encontrado con id: " + id));
        return mapper.map(topic, TopicResponse.class);
    }

    @Override
    public TopicResponse updateTopic(TopicRequest request, Long id) {
        Topic topic = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Tema no encontrado con id: " + id));

        mapper.map(request, topic);
        Topic updatedTopic = repository.save(topic);
        return mapper.map(updatedTopic, TopicResponse.class);
    }

    @Override
    public void deleteTopic(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tema no encontrado con id: " + id);
        }
        repository.deleteById(id);

    }



}
