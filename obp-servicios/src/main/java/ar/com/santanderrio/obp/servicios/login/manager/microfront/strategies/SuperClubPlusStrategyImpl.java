package ar.com.santanderrio.obp.servicios.login.manager.microfront.strategies;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.santanderrio.obp.servicios.premify.bo.impl.PremifyBOImpl;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;

@Component
public class SuperClubPlusStrategyImpl extends MicrofrontStrategyImpl{

    @Autowired
    private PremifyBOImpl premifyBO;

    @Override
    public Boolean debeAgregarCampania(String nup) {
        return true;
    }

    @Override
    public String obtenerLink() {
        return AccionController.IR_SUPERCLUB_PREMIFY.getDescripcion();
    }
}
