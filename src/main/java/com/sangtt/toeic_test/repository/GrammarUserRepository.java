package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.GrammarUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the GrammarUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GrammarUserRepository extends JpaRepository<GrammarUser, Long> {}
