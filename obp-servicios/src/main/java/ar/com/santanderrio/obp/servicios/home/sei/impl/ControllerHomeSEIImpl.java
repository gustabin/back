/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionConsultasHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionInversionesHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionSolicitudesHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionTransaccionesHomeManager;

/**
 * The Class HomeSEIImpl.
 */
@Component
public class ControllerHomeSEIImpl implements ControllerHomeSEI {

    /** The seccion consultas home manager. */
    @Autowired
    private SeccionConsultasHomeManager seccionConsultasHomeManager;

    /** The seccion transacciones home manager. */
    @Autowired
    private SeccionTransaccionesHomeManager seccionTransaccionesHomeManager;

    /** The seccion solicitudes home manager. */
    @Autowired
    private SeccionSolicitudesHomeManager seccionSolicitudesHomeManager;

    /** The seccion inversiones home manager. */
    @Autowired
    private SeccionInversionesHomeManager seccionInversionesHomeManager;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#
     * notificarAccesoConsultasCuentas()
     */
    @Override
    public Respuesta<Void> notificarAccesoConsultasCuentas() {

        return seccionConsultasHomeManager.notificarAccesoCuentas();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#
     * notificarAccesoConsultasCuentasBancaPrivada()
     */
    @Override
    public Respuesta<Void> notificarAccesoConsultasCuentasBancaPrivada() {
        return seccionConsultasHomeManager.notificarAccesoCuentasBancaPrivada();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#
     * notificarAccesoConsultasTarjetas()
     */
    @Override
    public Respuesta<Void> notificarAccesoConsultasTarjetas() {

        return seccionConsultasHomeManager.notificarAccesoTarjetas();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#
     * notificarAccesoConsultasPrestamos()
     */
    @Override
    public Respuesta<Void> notificarAccesoConsultasPrestamos() {

        return seccionConsultasHomeManager.notificarAccesoPrestamos();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#
     * notificarAccesoTransaccionesCalendario()
     */
    @Override
    public Respuesta<Void> notificarAccesoTransaccionesCalendario() {

        return seccionTransaccionesHomeManager.notificarAccesoCalendario();

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#
     * notificarAccesoTransaccionesTransferencias()
     */
    @Override
    public Respuesta<Void> notificarAccesoTransaccionesTransferencias() {

        return seccionTransaccionesHomeManager.notificarAccesoTransferencias();

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#
     * notificarAccesoTransaccionesPagoTarjetas()
     */
    @Override
    public Respuesta<Void> notificarAccesoTransaccionesPagoHaberes() {

        return seccionTransaccionesHomeManager.notificarAccesoPagoHaberes();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#
     * notificarAccesoSolicitudesPrestamos()
     */
    @Override
    public Respuesta<Void> notificarAccesoSolicitudesPrestamos() {

        return seccionSolicitudesHomeManager.notificarAccesoPrestamos();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#
     * notificarAccesoConsultasMonedero()
     */
    @Override
    public Respuesta<Void> notificarAccesoConsultasMonedero() {

        return seccionConsultasHomeManager.notificarAccesoMonedero();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#
     * notificarAccesoSolicitudesTarjetaMonedero()
     */
    @Override
    public Respuesta<Void> notificarAccesoSolicitudesTarjetaMonedero() {

        return seccionSolicitudesHomeManager.notificarAccesoSolicitudesTarjetaMonedero();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#
     * notificarAccesoComprobantes()
     */
    @Override
    public Respuesta<Void> notificarAccesoComprobantes() {
        return seccionConsultasHomeManager.notificarAccesoComprobantes();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#
     * notificarAccesoSolicitudesTarjetaCreditoAdicional()
     */
    @Override
    public Respuesta<Void> notificarAccesoSolicitudesTarjetaCreditoAdicional() {

        return seccionSolicitudesHomeManager.notificarAccesoSolicitudesTarjetaCreditoAdicional();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#
     * notificarAccesoTransaccionesBilleteraVirtual()
     */
    @Override
    public Respuesta<Void> notificarAccesoTransaccionesBilleteraVirtual() {

        return seccionSolicitudesHomeManager.notificarAccesoTransaccionesBilleteraVirtual();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#
     * notificarAccesoConsultaResumenImpositivo()
     */
    @Override
    public Respuesta<Void> notificarAccesoConsultaResumenImpositivo() {
        return seccionConsultasHomeManager.notificarAccesoConsultaResumenImpositivo();
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#notificarAccesoConsultaDescuentoCheques()
     */
    @Override
    public Respuesta<Void> notificarAccesoConsultaDescuentoCheques() {
        return seccionTransaccionesHomeManager.notificarAccesoConsultaDescuentoCheques();
    } 
    
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEI#notificarAccesoTransaccionesTransferenciasBancaPrivada()
     */
    @Override
    public Respuesta<Void> notificarAccesoServiciosDeInversion() {
        return seccionInversionesHomeManager.notificarAccesoServiciosDeInversion();
    }

    @Override
    public Respuesta<Void> notificarAccesoTransaccionesTransferenciasBancaPrivada() {
        return seccionTransaccionesHomeManager.notificarAccesoTransaccionesTransferenciasBancaPrivada();
    }

    
}
