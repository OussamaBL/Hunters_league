package com.oussama.hunters_league.repository;

import com.oussama.hunters_league.domain.Competition;
import com.oussama.hunters_league.domain.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {
    Optional<Competition> findByCode(String code);
}
