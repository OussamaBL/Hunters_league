package com.oussama.hunters_league.repository;

import com.oussama.hunters_league.domain.Species;
import com.oussama.hunters_league.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, UUID> {
    Optional<Species> findByName(String name);
}
