package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.QuestionPart;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the QuestionPart entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionPartRepository extends JpaRepository<QuestionPart, Long> {}
