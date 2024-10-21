package ar.com.santanderrio.obp.servicios.premify.bo;

import ar.com.santanderrio.obp.servicios.premify.entity.MemberEntity;

public interface MemberBO {
    MemberEntity getMember(String nup);
}
