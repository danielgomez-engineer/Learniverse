package com.danieldev.Learniverse.service;

import com.danieldev.Learniverse.dto.TopicRequest;
import com.danieldev.Learniverse.dto.TopicResponse;

import java.util.List;

public interface TopicService {

    TopicResponse createTopic(TopicRequest request);

    List<TopicResponse> findAllTopics();

    TopicResponse findTopicById(Long id);

    TopicResponse updateTopic (TopicRequest request, Long id);

    void deleteTopic (Long id);
}
