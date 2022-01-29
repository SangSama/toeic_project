package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.DetailsVocabularyUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DetailsVocabularyUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetailsVocabularyUserRepository extends JpaRepository<DetailsVocabularyUser, Long> {}
