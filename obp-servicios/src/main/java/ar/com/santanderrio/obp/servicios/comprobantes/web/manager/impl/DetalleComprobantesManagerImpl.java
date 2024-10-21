/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.CoordinadorComprobantesBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.DetallePMCBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.HistorialComprobantesBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteCompraVentaDolarDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobantePagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.FiltroComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoDetalleComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.web.manager.DetalleComprobantesManager;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class DetalleComprobantesManagerImpl.
 * 
 * @author sabrina.cis
 */
@Component
public class DetalleComprobantesManagerImpl implements DetalleComprobantesManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobantesManagerImpl.class);

	/** The comprobantes BO. */
	@Autowired
	private CoordinadorComprobantesBO coordinadorBO;

	/** The detalle PMC BO. */
	@Autowired
	private DetallePMCBO detallePMCBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The registro sesion. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	/** The reporte BO. */
	@Autowired
	private LegalBO legalBO;

	/** The historial comprobantes BO. */
	@Autowired
	private HistorialComprobantesBO historialBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.manager.
	 * ComprobantesManager#obtenerDetalleComprobantes(java.lang.String)
	 */
	@Override
	public Respuesta<DetalleComprobanteView> obtenerDetalleComprobantes(String id, boolean esGrillaComprobantes) {
		Cliente cliente = sesionCliente.getCliente();
		FiltroComprobanteDTO dto = new FiltroComprobanteDTO();
		dto.setCliente(sesionCliente.getCliente());
		Respuesta<ComprobantesDTO> respuestaDTO = coordinadorBO.obtenerComprobantes(dto.getCliente(), dto);
		if (esGrillaComprobantes) {
			respuestaDTO = coordinadorBO.obtenerComprobantes(cliente, dto);
		} else {
			respuestaDTO = historialBO.obtenerHistorial(new ComprobanteDTO(), cliente);
		}
		if (EstadoRespuesta.ERROR.equals(respuestaDTO.getEstadoRespuesta())) {
			grabarEstadisticaDetalleComprobante(EstadoRespuesta.ERROR, esGrillaComprobantes);
			return respuestaFactory.crearRespuestaError(DetalleComprobanteView.class,
					respuestaDTO.getItemsMensajeRespuesta());
		}
		ComprobanteDTO comprobante = respuestaDTO.getRespuesta().getComprobantes().get(Integer.valueOf(id));
		Respuesta<DetalleComprobanteView> respuesta;

		if (esComprobanteTransferenciaProgramadaRio(comprobante)) {
			actualizarDestinatarioDeCuentasTit(cliente, comprobante);
		}

		if (comprobante.getTipoOperacion() == TipoOperacionComprobanteEnum.PAGO_SERVICIOS) {
			respuesta = obtenerDetalleIatxPagosDeServicios(comprobante, esGrillaComprobantes);
		} else if (TipoDetalleComprobanteEnum.COMPRAVENTA_DOLARES
				.equals(comprobante.getDetalle().getTipoComprobante())) {
			respuesta = obtenerDetalleCompraVenta(comprobante, esGrillaComprobantes);
		} else {
			// verifico acceso, para luego en caso de que la fecha venga null rellenarlo
			if (sesionParametros.getRegistroSession().isMobile()) {
				comprobante.setEsMobile(true);
			} else {
				comprobante.setEsMobile(false);
			}
			respuesta = generarRespuestaOK(comprobante.getDetalle().getView(comprobante), esGrillaComprobantes);
		}
		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			guardarEnSesionElDetalle(respuesta.getRespuesta(), comprobante.getTipoOperacion());
		}
		return respuesta;
	}

	/**
	 * Grabar estadistica.
	 * 
	 * @param codigoEstadistica
	 *            the codigo estadistica
	 * @param codigoError
	 *            the codigo error
	 */
	public void grabarEstadistica(String codigoEstadistica, String codigoError) {
		estadisticaManager.add(codigoEstadistica, codigoError);
	}

	/**
	 * Obtener detalle iatx pagos de servicios.
	 * 
	 * @param comprobante
	 *            the comprobante
	 * @param esGrillaComprobantes
	 *            the es grilla comprobantes
	 * @return the respuesta
	 */
	private Respuesta<DetalleComprobanteView> obtenerDetalleIatxPagosDeServicios(ComprobanteDTO comprobante,
			boolean esGrillaComprobantes) {
		DetalleComprobantePagoMisCuentasDTO detalle = (DetalleComprobantePagoMisCuentasDTO) comprobante.getDetalle();
		Respuesta<DetalleComprobanteDTO> res = obtenerDetallesIATXPMC(detalle, comprobante);
		if (res.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return generarRespuestaOK(res.getRespuesta().getView(comprobante), esGrillaComprobantes);
		} else {
			if (isDetalleSinDeudas(res.getItemsMensajeRespuesta())) {
				// comprobante.setDetalle(new DetalleComprobanteIatxPMCDefaultDTO());
				grabarEstadisticaDetalleComprobante(EstadoRespuesta.WARNING, esGrillaComprobantes);
				return respuestaFactory.crearRespuestaOk(DetalleComprobanteView.class,
						comprobante.getDetalle().getView(comprobante));
			} else {
				LOGGER.info("Fallo el servicio CNSPESPAGE con lo cual se usaran los datos del servicio CNSPESPAGC");
				grabarEstadisticaDetalleComprobante(EstadoRespuesta.WARNING, esGrillaComprobantes);
				return respuestaFactory.crearRespuestaOk(DetalleComprobanteView.class,
						comprobante.getDetalle().getView(comprobante));
			}
		}
	}

	/**
	 * Obtener detalle compra venta.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the respuesta
	 */
	private Respuesta<DetalleComprobanteView> obtenerDetalleCompraVenta(ComprobanteDTO comprobante,
			Boolean esGrillaComprobantes) {
		DetalleComprobanteCompraVentaDolarDTO detalle = (DetalleComprobanteCompraVentaDolarDTO) comprobante
				.getDetalle();
		Respuesta<String> respuestaLegal = legalBO
				.buscarLegal(TipoOperacionComprobanteEnum.COMPRA_DOLARES.equals(comprobante.getTipoOperacion())
						? CompraVentaStringUtil.COD_LEGAL_COMPRA_DOLARES
						: CompraVentaStringUtil.COD_LEGAL_VENTA_DOLARES);
		if (EstadoRespuesta.ERROR.equals(respuestaLegal.getEstadoRespuesta())) {
			grabarEstadisticaDetalleComprobante(EstadoRespuesta.ERROR, esGrillaComprobantes);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_COMPROBANTES,
					CodigoMensajeConstantes.ERROR_COMPROBANTES);
		}
		detalle.setLegal(respuestaLegal.getRespuesta());
		grabarEstadisticaDetalleComprobante(EstadoRespuesta.OK, esGrillaComprobantes);
		return respuestaFactory.crearRespuestaOk(DetalleComprobanteView.class, detalle.getView(comprobante));
	}

	/**
	 * Obtener detalles IATXPMC. Los detalles de Pago mis cuentas son los unicos
	 * detalles que no estan cargados en el inicio de la grilla, por lo que se debe
	 * obtener el detalle desde un llamado a IATX.
	 * 
	 * @param detalle
	 *            the detalle
	 * @param comprobante
	 *            the comprobante
	 * @return the respuesta
	 */
	private Respuesta<DetalleComprobanteDTO> obtenerDetallesIATXPMC(DetalleComprobantePagoMisCuentasDTO detalle,
			ComprobanteDTO comprobante) {
		return detallePMCBO.obtenerDetallePMC(detalle.getFiid(), detalle.getCodigoValidacion(),
				comprobante.getDestinatario(), sesionCliente.getCliente());
	}

	/**
	 * Guardar en sesion el detalle.
	 * 
	 * @param respuesta
	 *            the respuesta
	 * @param tipoOperacion
	 *            the tipo operacion
	 */
	private void guardarEnSesionElDetalle(DetalleComprobanteView respuesta,
			TipoOperacionComprobanteEnum tipoOperacion) {
		sesionParametros.setDetalleComprobanteView(respuesta);
		sesionParametros.setTipoOperacionComprobante(tipoOperacion);
	}

	/**
	 * Es comprobante transferencia programada rio.
	 * 
	 * @param comprobante
	 *            the comprobante
	 * @return true, if successful
	 */
	private boolean esComprobanteTransferenciaProgramadaRio(ComprobanteDTO comprobante) {
		return comprobante.getDetalle() != null && TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_RIO_RIO
				.equals(comprobante.getDetalle().getTipoComprobante());
	}

	/**
	 * Actualiza destinatario de cuentas tit, en transferencias programadas Rio Rio.
	 * 
	 * @param cliente
	 *            the cliente
	 * @param comprobante
	 *            the comprobante
	 */
	private void actualizarDestinatarioDeCuentasTit(Cliente cliente, ComprobanteDTO comprobante) {
		DetalleComprobanteTransferenciaDTO detalle = (DetalleComprobanteTransferenciaDTO) comprobante.getDetalle();
		IdentificacionCuenta cuenta = new IdentificacionCuenta(detalle.getNroCuentaDestino());
		boolean tieneCuenta = verificarCuenta(cliente.getCuentas(), cuenta,
				comprobante.getDetalle().getInformacionAdicional());
		if (!tieneCuenta) {
			try {
				Respuesta<Cliente> verificacion = cuentaBO.verificarCuenta(sesionCliente.getCliente(),
						comprobante.getDetalle().getInformacionAdicional(), cuenta.getNroSucursal(),
						cuenta.getNroCuentaProducto());
				if (EstadoRespuesta.OK.equals(verificacion.getEstadoRespuesta())) {
					detalle.setDestinatario(verificacion.getRespuesta().getNombre());
					comprobante.setDetalle(detalle);
				}
			} catch (BusinessException e) {
				LOGGER.error("Business exception llamando a CNSCTATIT_ :" + e);
			}
		}
	}

	/**
	 * Checks if is detalle sin deudas.
	 * 
	 * @param items
	 *            the items
	 * @return the boolean
	 */
	protected Boolean isDetalleSinDeudas(List<ItemMensajeRespuesta> items) {
		return items != null && !items.isEmpty()
				&& items.get(0).getTipoError().equals(TipoError.ERROR_DETALLE_SIN_DEUDAS.getDescripcion());
	}

	/**
	 * Verificar cuenta.
	 * 
	 * @param cuentas
	 *            the cuentas
	 * @param idCuenta
	 *            the id cuenta
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return true, if successful
	 */
	private boolean verificarCuenta(List<Cuenta> cuentas, IdentificacionCuenta idCuenta, String tipoCuenta) {
		for (Cuenta cuenta : cuentas) {
			if (ISBANStringUtils.eliminarCeros(cuenta.getNroCuentaProducto())
					.equals(ISBANStringUtils.eliminarCeros(idCuenta.getNroCuentaProducto()))
					&& ISBANStringUtils.eliminarCeros(cuenta.getNroSucursal())
							.equals(ISBANStringUtils.eliminarCeros(idCuenta.getNroSucursal()))
					&& Integer.valueOf(cuenta.getTipoCuenta()).equals(Integer.valueOf(tipoCuenta))) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.manager.
	 * ComprobantesManager#descargarComprobantePDF()
	 */
	private Respuesta<ReporteView> descargarComprobantePDF(DetalleComprobanteView detalleView) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		reporte = reporteBO.obtenerComprobantePDF(detalleView);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_COMPROBANTES,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_COMPROBANTES,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/**
	 * Generar respuesta OK.
	 * 
	 * @param view
	 *            the view
	 * @param esGrillaComprobantes
	 *            the es grilla comprobantes
	 * @return the respuesta
	 */
	private Respuesta<DetalleComprobanteView> generarRespuestaOK(DetalleComprobanteView view,
			boolean esGrillaComprobantes) {
		grabarEstadisticaDetalleComprobante(EstadoRespuesta.OK, esGrillaComprobantes);
		return respuestaFactory.crearRespuestaOk(DetalleComprobanteView.class, view);
	}

	/**
	 * Grabar estadistica detalle comprobante.
	 * 
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @param esGrillaComprobantes
	 *            the es grilla comprobantes
	 */
	private void grabarEstadisticaDetalleComprobante(EstadoRespuesta estadoRespuesta, boolean esGrillaComprobantes) {
		if (esGrillaComprobantes) {
			if (EstadoRespuesta.OK.equals(estadoRespuesta)) {
				estadisticaManager.add(EstadisticasConstants.DETALLE_COMPROBANTES,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else if (EstadoRespuesta.WARNING.equals(estadoRespuesta)) {
				estadisticaManager.add(EstadisticasConstants.DETALLE_COMPROBANTES,
						EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
			} else {
				estadisticaManager.add(EstadisticasConstants.DETALLE_COMPROBANTES,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		}
	}

	@Override
	public Respuesta<ReporteView> descargaHistorial(String idComprobante) {
		Respuesta<DetalleComprobanteView> respuestaDetalle = obtenerDetalleComprobantes(idComprobante, false);
		if (EstadoRespuesta.ERROR.equals(respuestaDetalle.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError(ReporteView.class, new ArrayList<ItemMensajeRespuesta>());
		}
		return descargarComprobantePDF(respuestaDetalle.getRespuesta());
	}

	@Override
	public Respuesta<ReporteView> descargaComprobanteGrilla() {
		return descargarComprobantePDF(sesionParametros.getDetalleComprobanteView());
	}

}
