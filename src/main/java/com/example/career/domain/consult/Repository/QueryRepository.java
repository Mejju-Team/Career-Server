package com.example.career.domain.consult.Repository;

import com.example.career.domain.consult.Entity.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRepository extends JpaRepository<Query,Long> {
    public Query findByConsultId(Long consultId);
}
