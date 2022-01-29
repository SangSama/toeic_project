package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.GrammarTopic;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GrammarTopic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GrammarTopicRepository extends JpaRepository<GrammarTopic, Long> {}
