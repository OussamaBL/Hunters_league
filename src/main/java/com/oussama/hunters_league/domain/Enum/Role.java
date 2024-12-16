package com.oussama.hunters_league.domain.Enum;

import java.util.Set;

public enum Role {
    MEMBER(Set.of(
            Authority.CAN_PARTICIPATE,
            Authority.CAN_VIEW_RANKINGS,
            Authority.CAN_VIEW_COMPETITIONS)),
    JURY(Set.of(
            Authority.CAN_PARTICIPATE,
            Authority.CAN_VIEW_RANKINGS,
            Authority.CAN_VIEW_COMPETITIONS,
            Authority.CAN_SCORE)),
    ADMIN(Set.of(
            Authority.CAN_PARTICIPATE,
            Authority.CAN_VIEW_RANKINGS,
            Authority.CAN_VIEW_COMPETITIONS,
            Authority.CAN_SCORE,
            Authority.CAN_MANAGE_COMPETITIONS,
            Authority.CAN_MANAGE_USERS,
            Authority.CAN_MANAGE_SPECIES,
            Authority.CAN_MANAGE_SETTINGS
    ));

    private final Set<Authority> authorities;

    Role(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }
}
