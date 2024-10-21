package ar.com.santanderrio.obp.servicios.premify.bo.impl;

import ar.com.santanderrio.obp.servicios.premify.bo.MemberBO;
import ar.com.santanderrio.obp.servicios.premify.contracts.MemberResponse;
import ar.com.santanderrio.obp.servicios.premify.dao.PremifyDAO;
import ar.com.santanderrio.obp.servicios.premify.entity.MemberEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberBOImpl implements MemberBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberBOImpl.class);

    @Autowired
    PremifyDAO premifyDAO;

    @Override
    public MemberEntity getMember(String nup) {
        try {
            MemberResponse response = premifyDAO.getMember(nup);
            if(response == null){
                return new MemberEntity(0);
            }
            return new MemberEntity(response.points);
        } catch (Exception e) {
            LOGGER.info("Ocurrio un error al consultar el miembro superclub+");
            return new MemberEntity(0);
        }
    }
}
