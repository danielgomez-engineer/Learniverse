package com.danieldev.Learniverse.service;

import com.danieldev.Learniverse.dto.request.SectionRequest;
import com.danieldev.Learniverse.dto.response.SectionResponse;

import java.util.List;

public interface SectionService {

    SectionResponse create(SectionRequest request);
    List<SectionResponse> findAll();
    SectionResponse findById(Long id);
    SectionResponse update(Long id, SectionRequest request);
    void delete(Long id);
    List<SectionResponse> findByContentId(Long contentId);

    SectionResponse findPrevious(Long currentSectionId);

    SectionResponse findNext(Long currentSectionId);
}
