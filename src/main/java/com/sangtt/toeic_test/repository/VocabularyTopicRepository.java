package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.VocabularyTopic;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the VocabularyTopic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VocabularyTopicRepository extends JpaRepository<VocabularyTopic, Long> {}
