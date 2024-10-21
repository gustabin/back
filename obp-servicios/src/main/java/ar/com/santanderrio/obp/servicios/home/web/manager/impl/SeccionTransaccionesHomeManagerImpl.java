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
import ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionTransaccionesHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Class SeccionTransaccionesHomeManagerImpl.
 */
@Component
public class SeccionTransaccionesHomeManagerImpl extends AbstractSeccionHomeManager
		implements SeccionTransaccionesHomeManager {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SeccionTransaccionesHomeManagerImpl.class);

	/** The transacciones. */
	private static final String TRANSACCIONES = "Transacciones";

	/** The transferencias. */
	private static final String TRANSFERENCIAS = "Transferencias";

	/** The calendario de pagos. */
	private static final String CALENDARIO_DE_PAGOS = "Pago de Servicios e Impuestos";

	/** The pago de tarjeta. */
	private static final String PAGO_DE_TARJETA = "Pago de Tarjeta";

	/** The envio de efectivo. */
	private static final String ENVIO_DE_EFECTIVO = "Envío de Efectivo";

	/** The billetera virtual. */
	private static final String BILLETERA_VIRTUAL = "Billetera Virtual";

	/** The pago de haberes. */
	private static final String PAGO_DE_HABERES = "Pago de Sueldo";

	/** The Debin. */
	private static final String DEBIN = "Debin";

	/** The Constant COMPRA_VENTA_DOLARES. */
	private static final String COMPRA_VENTA_DOLARES = "Compra-Venta de dólares";

	/** The Constant DESCUENTO_CHEQUES. */
	private static final String DESCUENTO_CHEQUES = "Descuento de Cheques";

	/** The Constant TRANSFERENCIAS_EXTERIOR. */
	private static final String TRANSFERENCIAS_EXTERIOR = "Transferencias al exterior";
	private static final String TRANSFERENCIAS_EXTERIOR_MF = "transferencias-al-exterior";

	/** The Constant ORDEN_PAGO_EXTERIOR. */
	private static final String ORDEN_PAGO_EXTERIOR = "Cobros del exterior";	
	private static final String ORDEN_PAGO_EXTERIOR_MF = "cobros-del-exterior";	
	
	private static final String EXTRACCION_EFECTIVO = "Extracción sin tarjeta";
	
	/** The Constant ECHEQ. */
	private static final String ECHEQ = "Echeq";

	/** The consultas controller home BO. */
	@Autowired
	private TransaccionesControllerHomeBO transaccionesControllerHomeBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/**
	 * Obtener seccion inversiones.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the seccion view
	 */

	public SeccionView obtenerSeccion(Cliente cliente) {
		ModuloPermiso moduloPermiso = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_TRANSACCION);
		if (moduloPermiso.isModuloOculto()) {
			LOGGER.info("No se cargan los menu del topBar para la accion {} ya que esta deshabilitado desde la base.",
					AccionController.IR_TRANSACCION);
			return null;
		} else {
			LOGGER.info("ML entre a validar obtenerSeccion");
			List<SeccionView> items = new ArrayList<SeccionView>();
			SeccionView seccion = new SeccionView();
			seccion.setTitulo(TRANSACCIONES);
			seccion.setAccion(AccionController.IR_TRANSACCION.getDescripcion());
			if (moduloPermiso.tienePermisoDeVisibilidad()) {
				agregarItem(transaccionesControllerHomeBO.aplicaCalendarioPagos(cliente), items,
						AccionController.IR_INICIO_CALENDARIO_DE_PAGOS, CALENDARIO_DE_PAGOS);
				agregarItem(transaccionesControllerHomeBO.aplicaTransferencias(cliente), items,
						AccionController.IR_INICIO_TRASNFERENCIAS, TRANSFERENCIAS);
				agregarItem(transaccionesControllerHomeBO.aplicaPagoTarjeta(cliente), items,
						AccionController.IR_INICIO_PAGO_DE_TARJETA, PAGO_DE_TARJETA);
				agregarItem(transaccionesControllerHomeBO.aplicaExtraccionEfectivo(cliente),
						items, AccionController.IR_EXTRACCION_EFECTIVO, EXTRACCION_EFECTIVO);
				agregarItem(transaccionesControllerHomeBO.aplicaPagoHaberes(cliente), items,
						AccionController.IR_INICIO_PAGO_DE_HABERES, PAGO_DE_HABERES);
				agregarItemConMicrofront(transaccionesControllerHomeBO.aplicaTransferenciasExterior(cliente),
						items, AccionController.IR_TRANSFERENCIAS_EXTERIOR_MF, TRANSFERENCIAS_EXTERIOR, TRANSFERENCIAS_EXTERIOR_MF);
				agregarItemConMicrofront(transaccionesControllerHomeBO.aplicaOrdenDePagoDelExterior(cliente),
						items, AccionController.IR_ORDEN_PAGO_EXTERIOR_MF, ORDEN_PAGO_EXTERIOR, ORDEN_PAGO_EXTERIOR_MF);
				agregarItem(transaccionesControllerHomeBO.aplicaDolares(cliente), items,
						AccionController.IR_INICIO_DOLARES, COMPRA_VENTA_DOLARES);
				agregarItem(transaccionesControllerHomeBO.aplicaDolaresBP(cliente), items,
						AccionController.IR_INICIO_DOLARES_BANCA_PRIVADA, COMPRA_VENTA_DOLARES);
				agregarItem(transaccionesControllerHomeBO.aplicaDebin(cliente), items, 
						AccionController.IR_INICIO_DEBIN, DEBIN);
				agregarItem(transaccionesControllerHomeBO.aplicaECheq(cliente),	items,
						AccionController.IR_INICIO_ECHEQ, ECHEQ);
				agregarItem(transaccionesControllerHomeBO.aplicaEnvioEfectivo(cliente), items,
						AccionController.IR_INICIO_ENVIO_DE_EFECTIVO, ENVIO_DE_EFECTIVO);
				agregarItem(transaccionesControllerHomeBO.aplicaBilleteraVirtual(cliente), items,
						AccionController.IR_INICIO_BILLETERA_VIRTUAL, BILLETERA_VIRTUAL);
				LOGGER.info("ML entre a agregar item cheques");
				agregarItem(true, items,
						AccionController.IR_INICIO_DESCUENTO_DE_CHEQUES, DESCUENTO_CHEQUES);
				seccion.setItems(items);
			} else if (moduloPermiso.muestraMensaje()) {
				seccion.setMensajeNoDisponible(moduloPermiso.getMensaje());
			}
			return seccion;
		}
	}

	/**
	 * Grabar Estadisticas Acceso a Calendario de pagos.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> notificarAccesoCalendario() {

		estadisticaManager.add(EstadisticasConstants.ACCESO_CONTROLLER_TRANSACCIONES_CALENDARIO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);

	}

	/**
	 * Grabar Estadisticas Acceso Transferencias.
	 *
	 * @return the respuesta
	 */

	@Override
	public Respuesta<Void> notificarAccesoTransferencias() {

		estadisticaManager.add(EstadisticasConstants.ACCESO_CONTROLLER_TRANSACCIONES_TRANSFERENCIAS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}

	/**
	 * Grabar Estadisticas Acceso Pago de Haberes.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> notificarAccesoPagoHaberes() {
		estadisticaManager.add(EstadisticasConstants.ACCESO_CONTROLLER_TRANSACCIONES_PAGO_HABERES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}

	/**
	 * Grabar Estadisticas Acceso Monedero.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> notificarAccesoConsultaDescuentoCheques() {
		estadisticaManager.add(EstadisticasConstants.ACCESO_CONTROLLER_CONSULTAS_DESCUENTO_CHEQUES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}


	/**
	 * Grabar Estadisticas acceso transferencias banca privada.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> notificarAccesoTransaccionesTransferenciasBancaPrivada() {
		estadisticaManager.add(EstadisticasConstants.ACCESO_CONTROLLER_TRANSFERENCIAS_BANCA_PRIVADA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}
	
	/**
	 * Notificar acceso orden de pago exterior.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Void> notificarAccesoTransaccionesOrdenPagoExterior() {
		return respuestaFactory.crearRespuestaOk(null);
	}


}
