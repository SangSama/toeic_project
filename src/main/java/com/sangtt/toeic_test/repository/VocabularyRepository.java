package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.Vocabulary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Vocabulary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {}
