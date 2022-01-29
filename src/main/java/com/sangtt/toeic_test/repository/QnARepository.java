package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.QnA;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the QnA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QnARepository extends JpaRepository<QnA, Long> {}
