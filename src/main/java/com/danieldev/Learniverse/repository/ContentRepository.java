package com.danieldev.Learniverse.repository;

import com.danieldev.Learniverse.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository <Content, Long> {

    List<Content> findBySubtopicId(Long idSubtopic);
}
