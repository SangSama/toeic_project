package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.GrammarAnswer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GrammarAnswer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GrammarAnswerRepository extends JpaRepository<GrammarAnswer, Long> {}
