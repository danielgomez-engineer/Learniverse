package com.danieldev.Learniverse.service;

import com.danieldev.Learniverse.dto.request.ContentRequest;
import com.danieldev.Learniverse.dto.response.ContentResponse;

import java.util.List;

public interface ContentService {

    ContentResponse create (ContentRequest request);

    List<ContentResponse> findAll();

    ContentResponse findById(Long id);

    ContentResponse update(Long id, ContentRequest request);

    void delete (Long id);

    List<ContentResponse> findBySubtopicId(Long subtopicId);
}
