package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.DetailsToeicUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DetailsToeicUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetailsToeicUserRepository extends JpaRepository<DetailsToeicUser, Long> {}
