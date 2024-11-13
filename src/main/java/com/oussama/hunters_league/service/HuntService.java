package com.oussama.hunters_league.service;

import com.oussama.hunters_league.domain.Competition;
import com.oussama.hunters_league.domain.Hunt;
import com.oussama.hunters_league.web.vm.hunt.AddHuntVM;

public interface HuntService {
    Hunt addHunt(AddHuntVM addHuntVM);
}
