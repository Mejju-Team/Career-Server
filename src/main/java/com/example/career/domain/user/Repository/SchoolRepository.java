package com.example.career.domain.user.Repository;

import com.example.career.domain.user.Entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School,Long> {
}
