package com.example.selfreferencingmanytomany.persistence;

import com.example.selfreferencingmanytomany.domain.TestCase;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCaseRepository extends JpaRepository<TestCase, Long> {

  Optional<TestCase> findByName(String name);

}
