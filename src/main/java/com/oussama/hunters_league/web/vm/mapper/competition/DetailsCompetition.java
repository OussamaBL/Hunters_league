package com.oussama.hunters_league.web.vm.mapper.competition;

import com.oussama.hunters_league.domain.Competition;
import com.oussama.hunters_league.web.vm.competition.AddCompetitionVM;
import com.oussama.hunters_league.web.vm.competition.ResponseCompetitionVM;
import com.oussama.hunters_league.web.vm.competition.ResponseDetailsCompetitionVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetailsCompetition {
    ResponseDetailsCompetitionVM toResponseDetailsCompetitionVM(Competition competition);
}
