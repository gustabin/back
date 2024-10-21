/*
 * 
 */
package ar.com.santanderrio.obp.servicios.solicitudes.manager;

import java.util.Map;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.solicitudes.view.BeneficioTransferiTuSueldoView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.SolicitudesView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.ValidaAltaView;


/**
 * The Interface SolicitudesManager.
 */
public interface SolicitudesManager {

    /**
     * Obtener cuentas Y paquetes.
     *
     * @return the respuesta
     */
    Respuesta<SolicitudesView> obtenerCuentasYPaquetes();

    /**
     * Obtener tarjetas.
     *
     * @return the respuesta
     */
    Respuesta<SolicitudesView> obtenerTarjetas();
    

    /**
	 * Obtiene datos basicos para las solicitudes de <b>Caja de Ahorro</b> y
	 * <b>Cuenta Tiutlo</b>.
     * 
	 * @return the respuesta
     */
    Respuesta<Map<String, String>> obtenerDatosBasicosACCT();

    /**
     * Obtener otros medios pago.
     *
     * @return the respuesta
     */
    Respuesta<SolicitudesView> obtenerOtrosMediosPago();
    
    Respuesta<ValidaAltaView> solicitudCtaTit();
    
    Respuesta<BeneficioTransferiTuSueldoView> inicioBeneficioTransferiTuSueldo();

    Respuesta<BeneficioTransferiTuSueldoView> solicitarBeneficioTransferiTuSueldo();

    
}