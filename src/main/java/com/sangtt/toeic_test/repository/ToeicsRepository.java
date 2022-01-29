package com.sangtt.toeic_test.repository;

import com.sangtt.toeic_test.domain.Toeics;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Toeics entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ToeicsRepository extends JpaRepository<Toeics, Long> {}
