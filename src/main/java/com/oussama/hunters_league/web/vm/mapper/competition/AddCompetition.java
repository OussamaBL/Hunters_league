package com.oussama.hunters_league.web.vm.mapper.competition;

import com.oussama.hunters_league.domain.Competition;
import com.oussama.hunters_league.web.vm.ResponseCompetitionVM;
import com.oussama.hunters_league.web.vm.competition.AddCompetitionVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddCompetition {
    ResponseCompetitionVM toResponseCompetitionVM(Competition competition);
    Competition toCompetition(AddCompetitionVM addCompetitionVM);
}
