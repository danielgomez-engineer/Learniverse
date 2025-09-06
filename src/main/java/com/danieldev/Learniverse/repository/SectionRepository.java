package com.danieldev.Learniverse.repository;

import com.danieldev.Learniverse.model.Content;
import com.danieldev.Learniverse.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {

    List<Section> findByContentId(Long idContent);

    // traer la siguiente sección
    Optional<Section> findFirstByContentAndOrderIndexGreaterThanOrderByOrderIndexAsc(Content content, int orderIndex);

    // traer la anterior
    Optional<Section> findFirstByContentAndOrderIndexLessThanOrderByOrderIndexDesc(Content content, int orderIndex);

    @Query("SELECT MAX(s.orderIndex) FROM Section s WHERE s.content = :content")
    Integer findMaxOrderIndexByContent(@Param("content") Content content);

}
