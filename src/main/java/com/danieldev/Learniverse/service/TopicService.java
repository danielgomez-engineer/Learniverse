package com.danieldev.Learniverse.service;

import com.danieldev.Learniverse.dto.request.TopicRequest;
import com.danieldev.Learniverse.dto.response.SubtopicResponse;
import com.danieldev.Learniverse.dto.response.TopicResponse;

import java.util.List;

public interface TopicService {

    TopicResponse createTopic(TopicRequest request);

    List<TopicResponse> findAllTopics();

    TopicResponse findTopicById(Long id);

    TopicResponse updateTopic (TopicRequest request, Long id);

    void deleteTopic (Long id);


}
