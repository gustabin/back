/*
 *
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.bo.SolicitudesControllerHomeBO;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionSolicitudesHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Class SeccionSolicitudesHomeManagerImpl.
 */
@Component
public class SeccionSolicitudesHomeManagerImpl extends AbstractSeccionHomeManager
        implements SeccionSolicitudesHomeManager {
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(SeccionSolicitudesHomeManagerImpl.class);

    /**
     * The solicitudes.
     */
    static final String SOLICITUDES = "Solicitudes y trámites";

    /**
     * The cuentas y paquetes.
     */
    static final String CUENTAS_Y_PAQUETES = "Cuentas y Paquetes";

    /**
     * The prestamos.
     */
    static final String PRESTAMOS = "Préstamos";

    /**
     * The tarjetas.
     */
    static final String TARJETAS = "Tarjetas";

    /**
     * The seguros.
     */
    static final String SEGUROS = "Seguros | Multiasistencias";

    /**
     * The otros medios pago.
     */
    static final String OTROS_MEDIOS_PAGO = "Otros Medios de Pago";

    /**
     * Tracking.
     */
    static final String SEGUIMIENTO_DE_ENVIOS = "Seguimiento de Envío de tus Tarjetas";

    /**
     * Tracking.
     */
    static final String DELIVERY = "delivery";
    
    /**
     * The estadistica manager.
     */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /**
     * The consultas controller home BO.
     */
    @Autowired
    private SolicitudesControllerHomeBO solicitudesControllerHomeBO;

    /**
     * The respuesta factory.
     */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /**
     * Grabar Estadisticas Acceso a Prestamos.
     *
     * @return the respuesta
     */
    @Override
    public Respuesta<Void> notificarAccesoPrestamos() {

        estadisticaManager.add(EstadisticasConstants.ACCESO_CONTROLLER_SOLICITUDES_PRESTAMOS,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaFactory.crearRespuestaOk(null);
    }

    /**
     * Grabar Estadisticas Acceso a Monedero.
     *
     * @return the respuesta
     */
    @Override
    public Respuesta<Void> notificarAccesoSolicitudesTarjetaMonedero() {

        estadisticaManager.add(EstadisticasConstants.ACCESO_CONTROLLER_SOLICITUDES_TARJETA_MONEDERO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaFactory.crearRespuestaOk(null);
    }

    /**
     * Grabar Estadisticas Acceso a Solicitud de Tarjeta Crédito Adicional.
     *
     * @return the respuesta
     */
    @Override
    public Respuesta<Void> notificarAccesoSolicitudesTarjetaCreditoAdicional() {

        estadisticaManager.add(EstadisticasConstants.ACCESO_CONTROLLER_SOLICITUDES_TARJETA_CREDITO_ADICIONAL,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaFactory.crearRespuestaOk(null);
    }

    /**
     * Grabar Estadisticas Acceso a Billetera Virtual.
     *
     * @return the respuesta
     */
    @Override
    public Respuesta<Void> notificarAccesoTransaccionesBilleteraVirtual() {

        estadisticaManager.add(EstadisticasConstants.ACCESO_CONTROLLER_BILLETERA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaFactory.crearRespuestaOk(null);
    }

    /**
     * Obtener seccion inversiones.
     * <p>
     * Si la accion tiene permiso de visibilidad se cargan los item segun los
     * productos y los permisos de la base.<br/>
     * Si no tiene visibilidad se muestra el mensaje y no se cargan items.
     *
     * @param cliente the cliente
     * @return the seccion view
     */

    public SeccionView obtenerSeccion(Cliente cliente) {
        ModuloPermiso moduloPermiso = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_SOLICITUD);
        if (moduloPermiso.isModuloOculto()) {
            LOGGER.info("No se cargan los menu del topBar para la accion {} ya que esta deshabilitado desde la base.",
                    AccionController.IR_SOLICITUD);
            return null;
        } else {
            List<SeccionView> items = new ArrayList<SeccionView>();
            SeccionView seccion = new SeccionView();
            seccion.setTitulo(SOLICITUDES);
            seccion.setAccion(AccionController.IR_SOLICITUD.getDescripcion());
            if (moduloPermiso.tienePermisoDeVisibilidad()) {
                agregarItem(solicitudesControllerHomeBO.aplicaCuentasPaquetes(cliente), items,
                        AccionController.IR_INICIO_CUENTA_Y_PAQUETES, CUENTAS_Y_PAQUETES);
                agregarItem(solicitudesControllerHomeBO.aplicaPrestamos(cliente), items,
                        AccionController.IR_INICIO_PRESTAMOS_SOLICITUDES, PRESTAMOS);
                agregarItem(solicitudesControllerHomeBO.aplicaTarjetas(cliente), items,
                        AccionController.IR_INICIO_TARJETAS_SOLICITUDES, TARJETAS);
                agregarItem(solicitudesControllerHomeBO.aplicaSeguros(cliente), items,
                        AccionController.IR_INICIO_SEGUROS, SEGUROS);
                agregarItem(solicitudesControllerHomeBO.aplicaOtrosMediosPago(cliente), items,
                        AccionController.IR_INICIO_MEDIOS_PAGO_SOLICITUDES, OTROS_MEDIOS_PAGO);
                agregarItemConMicrofront(Boolean.TRUE, items,
                        AccionController.IR_TRACKING_SOLICITUDES, SEGUIMIENTO_DE_ENVIOS, DELIVERY);
                seccion.setItems(items);
            } else if (moduloPermiso.muestraMensaje()) {
                seccion.setMensajeNoDisponible(moduloPermiso.getMensaje());
            }
            return seccion;
        }
    }
}
