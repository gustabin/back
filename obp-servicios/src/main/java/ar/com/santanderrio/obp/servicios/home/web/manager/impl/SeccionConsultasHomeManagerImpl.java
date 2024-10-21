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
import ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionConsultasHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Class SeccionConsultasHomeManagerImpl.
 */
@Component
public class SeccionConsultasHomeManagerImpl extends AbstractSeccionHomeManager implements SeccionConsultasHomeManager {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SeccionConsultasHomeManagerImpl.class);

	/** The consultas. */
	private static final String CONSULTAS = "Consultas";

	/** The cuentas. */
	private static final String CUENTAS = "Cuentas";

	/** The cuentas banca privada. */
	private static final String CUENTAS_BANCA_PRIVADA = "Cuentas Banca Privada";
	
	/** The tarjetas. */
	private static final String TARJETAS = "Tarjetas";

	/** The prestamos. */
	private static final String PRESTAMOS = "Préstamos";

	/** The ahorros y beneficios. */
	private static final String AHORROS_Y_BENEFICIOS = "Ahorros y Beneficios";

	/** The comprobantes. */
	private static final String COMPROBANTES = "Comprobantes";

	/** The seguros. */
	private static final String SEGUROS = "Seguros | Multiasistencias";

	/** The tarjeta monedero. */
	private static final String TARJETA_MONEDERO = "Tarjeta Monedero";

	/** The resumen impositivo. */
	private static final String RESUMEN_IMPOSITIVO = "Resumen Impositivo";
	
	/** The Constant ECHEQ. */
	private static final String ECHEQ = "Echeq";

	private static final String RECLAMOS = "Seguimiento de Reclamos";
	private static final String GESTIONES = "Seguimiento de Gestiones";
	
	/**
     * Recuperaciones.
     */
    static final String FINANCIAL_HEALTH = "financialHealth";

	private static final String RECUPE_FINANCIACION_DEUDAS = "Tu Salud Financiera";
	
    static final String CENTRO_DE_AYUDA = "Centro de Ayuda";

	/** The consultas controller home BO. */
	@Autowired
	private ConsultasControllerHomeBO consultasControllerHomeBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	/**
	 * Obtener seccion consultas.<br/>
	 * Si la accion tiene permiso de visibilidad se cargan los item segun los
	 * productos y los permisos de la base.<br/>
	 * Si no tiene visibilidad se muestra el mensaje y no se cargan items.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the seccion view
	 */

	public SeccionView obtenerSeccion(Cliente cliente) {
		ModuloPermiso moduloPermiso = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_CONSULTA);
		if (moduloPermiso.isModuloOculto()) {
			LOGGER.info("No se cargan los menu del topBar para la accion {} ya que esta deshabilitada desde la base.",
					AccionController.IR_CONSULTA);
			return null;
		} else {
			List<SeccionView> items = new ArrayList<SeccionView>();

			SeccionView seccion = new SeccionView();
			seccion.setTitulo(CONSULTAS);
			seccion.setAccion(AccionController.IR_CONSULTA.getDescripcion());
			if (moduloPermiso.tienePermisoDeVisibilidad()) {
				AccionController consulta = consultasControllerHomeBO.tieneUnaSolaCuenta(cliente) ? 
						AccionController.IR_INICIO_UNA_SOLA_CUENTA : AccionController.IR_INICIO_CUENTAS;
				agregarItem(consultasControllerHomeBO.aplicaCuentas(cliente), items, consulta, CUENTAS);
				agregarItem(consultasControllerHomeBO.aplicaCuentasBancaPrivada(cliente), items, consultasControllerHomeBO.tieneUnaSolaCuentaBancaPrivada(cliente) ? 
						AccionController.IR_INICIO_UNA_SOLA_CUENTA_BANCA_PRIVADA : AccionController.IR_INICIO_CUENTAS_BANCA_PRIVADA, CUENTAS_BANCA_PRIVADA);
				agregarItem(consultasControllerHomeBO.aplicaTarjetas(cliente), items,
						AccionController.IR_INICIO_TARJETAS, TARJETAS);
				agregarItem(consultasControllerHomeBO.aplicaPrestamos(cliente), items,
						AccionController.IR_INICIO_PRESTAMOS_CONSULTAS, PRESTAMOS);
				agregarItem(consultasControllerHomeBO.aplicaSeguros(cliente), items, AccionController.IR_INICIO_SEGUROS,
						SEGUROS);
				agregarItem(consultasControllerHomeBO.aplicaAhorrosBeneficios(cliente), items,
						AccionController.IR_INICIO_AHORROS_Y_BENEFICIOS, AHORROS_Y_BENEFICIOS);
				agregarItem(consultasControllerHomeBO.aplicaComprobantes(cliente), items,
						AccionController.IR_INICIO_COMPROBANTES, COMPROBANTES);
				agregarItem(consultasControllerHomeBO.aplicaMonedero(cliente), items, 
						AccionController.IR_INICIO_TARJETA_MONEDERO, TARJETA_MONEDERO);
				agregarItem(consultasControllerHomeBO.aplicaResumenImpositivo(cliente), items,
						AccionController.IR_INICIO_RESUMEN_IMPOSITIVO, RESUMEN_IMPOSITIVO);
				agregarItem(consultasControllerHomeBO.aplicaECheq(cliente),
						items, AccionController.IR_INICIO_ECHEQ, ECHEQ);
				agregarItem(consultasControllerHomeBO.aplicaReclamos(cliente),
						items, AccionController.IR_SEGUIMIENTO_DE_GESTIONES, GESTIONES);
				agregarItemConMicrofront(consultasControllerHomeBO.aplicaRecuperaciones(), items,
                        AccionController.IR_FINANCIAL_HEALTH, RECUPE_FINANCIACION_DEUDAS, FINANCIAL_HEALTH);
                if (!cliente.isNova()) {
					agregarItem(consultasControllerHomeBO.aplicaTurnosOnline(cliente), items,
	                        AccionController.IR_INICIO_TURNOS_SOLICITUDES, CENTRO_DE_AYUDA);
                }
				seccion.setItems(items);
			} else if (moduloPermiso.muestraMensaje()) {
				seccion.setMensajeNoDisponible(moduloPermiso.getMensaje());
			}
			return seccion;
		}
	}

	/**
	 * Grabar Estadisticas Acceso cuentas.
	 *
	 * @return the respuesta
	 */
	public Respuesta<Void> notificarAccesoCuentas() {
		estadisticaManager.add(EstadisticasConstants.ESTADISTICA_ACCESO_CUENTAS_CONTROLLER,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}
	
	/**
	 * Grabar Estadisticas Acceso cuentas banca privada.
	 *
	 * @return the respuesta
	 */
	public Respuesta<Void> notificarAccesoCuentasBancaPrivada() {
		estadisticaManager.add(EstadisticasConstants.ESTADISTICA_ACCESO_CUENTAS_BANCA_PRIVADA_CONTROLLER,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}

	/**
	 * Grabar Estadisticas Acceso Tarjetas.
	 *
	 * @return the respuesta
	 */
	public Respuesta<Void> notificarAccesoTarjetas() {
		estadisticaManager.add(EstadisticasConstants.ACCESO_CONTROLLER_CONSULTAS_TARJETAS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}

	/**
	 * Grabar Estadisticas Acceso Prestamos.
	 *
	 * @return the respuesta
	 */
	public Respuesta<Void> notificarAccesoPrestamos() {
		estadisticaManager.add(EstadisticasConstants.ACCESO_CONTROLLER_CONSULTAS_PRESTAMOS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}

	/**
	 * Grabar Estadisticas Acceso Monedero.
	 *
	 * @return the respuesta
	 */
	public Respuesta<Void> notificarAccesoMonedero() {
		estadisticaManager.add(EstadisticasConstants.ACCESO_CONTROLLER_CONSULTAS_TARJETAS_MONEDERO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.
	 * SeccionConsultasHomeManager#notificarAccesoComprobantes()
	 */
	@Override
	public Respuesta<Void> notificarAccesoComprobantes() {
		estadisticaManager.add(EstadisticasConstants.ESTADISTICA_INICIO_COMPROBANTE,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.
	 * SeccionConsultasHomeManager#notificarAccesoConsultaResumenImpositivo()
	 */
	@Override
	public Respuesta<Void> notificarAccesoConsultaResumenImpositivo() {
		return respuestaFactory.crearRespuestaOk(null);
	}

}
