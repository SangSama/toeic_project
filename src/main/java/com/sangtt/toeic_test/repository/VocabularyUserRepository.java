package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.VocabularyUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the VocabularyUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VocabularyUserRepository extends JpaRepository<VocabularyUser, Long> {}
