package com.danieldev.Learniverse.service;

import com.danieldev.Learniverse.dto.request.SubtopicRequest;
import com.danieldev.Learniverse.dto.response.SubtopicResponse;

import java.util.List;

public interface SubtopicService {

    SubtopicResponse createSubtopic (SubtopicRequest request);

    List<SubtopicResponse> findAllSubtopics();

    SubtopicResponse findByIdSubtopic ( Long id);

    SubtopicResponse updateSubtopic (SubtopicRequest request, Long id);

    void deleteSubtopic ( Long id);

    List<SubtopicResponse> findByTopicId(Long topicId);


}
