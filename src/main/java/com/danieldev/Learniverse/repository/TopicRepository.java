package com.danieldev.Learniverse.repository;

import com.danieldev.Learniverse.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository <Topic, Long> {
}
