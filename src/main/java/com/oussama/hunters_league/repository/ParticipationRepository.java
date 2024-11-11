package com.oussama.hunters_league.repository;

import com.oussama.hunters_league.domain.Competition;
import com.oussama.hunters_league.domain.Participation;
import com.oussama.hunters_league.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, UUID> {
    Boolean existsByUserAndCompetition(User user, Competition competition);
}
