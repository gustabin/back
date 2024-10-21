package ar.com.santanderrio.obp.servicios.login.manager.microfront.strategies;

import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import org.springframework.stereotype.Component;

@Component
public class DeliveryStrategyImpl extends MicrofrontStrategyImpl{

    @Override
    public Boolean debeAgregarCampania(String nup) {
        return true;
    }

    @Override
    public String obtenerLink() {
        return AccionController.IR_TRACKING_SOLICITUDES.getDescripcion();
    }
}
