package ar.com.santanderrio.obp.servicios.consent.exceptions;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.consent.dto.ConsentDTO;
import ar.com.santanderrio.obp.servicios.consent.dto.UserInfoDTO;

import java.util.Collections;

public class UserNotFoundException extends BusinessException {
    private final UserInfoDTO emptyUserInfoDTO;
    public UserNotFoundException(String message) {
        super(message);
        this.emptyUserInfoDTO = UserInfoDTO.builder()
                .withConsents(Collections.<ConsentDTO>emptyList()).build();
    }

    public UserInfoDTO getEmptyUserInfoDTO() {
        return emptyUserInfoDTO;
    }
}
