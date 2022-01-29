package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.ToeicUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ToeicUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ToeicUserRepository extends JpaRepository<ToeicUser, Long> {}
