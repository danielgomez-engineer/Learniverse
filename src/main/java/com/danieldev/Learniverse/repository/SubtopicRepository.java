package com.danieldev.Learniverse.repository;

import com.danieldev.Learniverse.model.Subtopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubtopicRepository extends JpaRepository<Subtopic, Long> {

    List<Subtopic> findByTopicId(Long topicId);
}
