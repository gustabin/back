package ar.com.santanderrio.obp.servicios.login.manager.microfront.strategies;

public interface MicrofrontStrategy {
    Boolean debeAgregarCampania(String nup);
    String obtenerLink();
    String obtenerMicrofrontName();
}
