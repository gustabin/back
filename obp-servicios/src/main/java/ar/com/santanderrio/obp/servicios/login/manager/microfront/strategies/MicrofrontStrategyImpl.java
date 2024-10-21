package ar.com.santanderrio.obp.servicios.login.manager.microfront.strategies;

public abstract class MicrofrontStrategyImpl implements MicrofrontStrategy{

    @Override
    public String obtenerMicrofrontName() {
        return obtenerLink().replace("microfront-", "");
    }
}
