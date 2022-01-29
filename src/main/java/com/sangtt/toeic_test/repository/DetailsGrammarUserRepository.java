package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.DetailsGrammarUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DetailsGrammarUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetailsGrammarUserRepository extends JpaRepository<DetailsGrammarUser, Long> {}
