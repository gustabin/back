/*
 * 
 */
package ar.com.santanderrio.obp.servicios.solicitudes.sei.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.adhesionwomen.manager.AdhesionWomenManager;
import ar.com.santanderrio.obp.servicios.solicitudes.manager.SolicitudesManager;
import ar.com.santanderrio.obp.servicios.solicitudes.sei.SolicitudesSEI;
import ar.com.santanderrio.obp.servicios.solicitudes.view.AdhesionWomenView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.BeneficioTransferiTuSueldoView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.ConfirmacionBajaAdhesionView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.SolicitudesView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.ValidaAltaView;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.manager.TrackingTarjetasManager;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.view.TrackingTarjetasView;


/**
 * The Class SolicitudesSEIImpl.
 */
@Component("solicitudesSEI")
public class SolicitudesSEIImpl implements SolicitudesSEI {
    
    /** The nueva recarga manager. */
    @Autowired
    private SolicitudesManager solicitudesManager;
    
    /** The tracking tarjetas manager. */
    @Autowired
    private TrackingTarjetasManager trackingTarjetasManager;
    
    @Autowired
    private AdhesionWomenManager adhesionWomenManager;
    
    
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.solicitudes.sei.SolicitudesSEI#obtenerCuentasYPaquetes()
     */
    @Override
    public Respuesta<SolicitudesView> obtenerCuentasYPaquetes() {
        return solicitudesManager.obtenerCuentasYPaquetes();
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.solicitudes.sei.SolicitudesSEI#
	 * obtenerTarjetas()
     */
    @Override
    public Respuesta<SolicitudesView> obtenerTarjetas() {
        return solicitudesManager.obtenerTarjetas();
    }
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.solicitudes.sei.SolicitudesSEI#
	 * obtenerDatosBasicosACCT()
	 */
    @Override
    public Respuesta<Map<String, String>> obtenerDatosBasicosACCT() {
        return solicitudesManager.obtenerDatosBasicosACCT();
    }
    
    /**
	 * obtenerTrackingTarjetas.
	 *
	 * @return the respuesta
	 */
    @Override
    public Respuesta<TrackingTarjetasView> obtenerTrackingTarjetas(){
        return trackingTarjetasManager.obtenerTrackingTarjetas();
    }

    /* 
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.solicitudes.sei.SolicitudesSEI#
     * obtenerOtrosMediosPago()
     */
    @Override
    public Respuesta<SolicitudesView> obtenerOtrosMediosPago() {
        return solicitudesManager.obtenerOtrosMediosPago();
    }

	@Override
	public Respuesta<AdhesionWomenView> inicioAdhesionWomen() {
		return adhesionWomenManager.inicioAdhesionWomen();
	}

	@Override
	public Respuesta<AdhesionWomenView> configuracionAdhesionWomen() {
		return adhesionWomenManager.configuracionAdhesionWomen();
	}

	@Override
	public Respuesta<AdhesionWomenView> confirmacionAdhesionWomen() {
		return adhesionWomenManager.confirmacionAdhesionWomen();
	}

	@Override
	public Respuesta<AdhesionWomenView> ejecutarAdhesionWomen(AdhesionWomenView tarjetasParaHabilitar) {
		return adhesionWomenManager.ejecutarAdhesionWomen(tarjetasParaHabilitar);
	}

	@Override
	public Respuesta<AdhesionWomenView> ejecutarBajaAdhesionWomen(AdhesionWomenView tarjetasHabilitadas) {
		return adhesionWomenManager.ejecutarBajaAdhesionWomen(tarjetasHabilitadas);
	}

	@Override
	public Respuesta<ConfirmacionBajaAdhesionView> confirmacionBajaAdhesionWomen() {
		return adhesionWomenManager.confirmacionBajaAdhesionWomen();
	}

	@Override
	public Respuesta<ValidaAltaView> solicitudCtaTit() {
		return solicitudesManager.solicitudCtaTit();
	}

	@Override
	public Respuesta<BeneficioTransferiTuSueldoView> inicioBeneficioTransferiTuSueldo() {
		return solicitudesManager.inicioBeneficioTransferiTuSueldo();
	}

	@Override
	public Respuesta<BeneficioTransferiTuSueldoView> solicitarBeneficioTransferiTuSueldo() {
		return solicitudesManager.solicitarBeneficioTransferiTuSueldo();
	}
	
	

}