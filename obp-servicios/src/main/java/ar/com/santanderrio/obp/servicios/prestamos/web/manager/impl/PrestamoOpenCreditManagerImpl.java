/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.utils.ListUtil;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoOpenCreditBO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConsultaPagosMinimosOpenCreditInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.DetallePagosMinimosOpenCreditDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoOpenCreditDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoOpenCreditDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoOpenCreditInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PrestamoOpenCreditUtils;
import ar.com.santanderrio.obp.servicios.prestamos.view.CnsDetallePagosMinimosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.DetallePagosMinimosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagoOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagosFechaOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamoOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ReportePagosMinimosOpenCreditInView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoOpenCreditManager;

/**
 * OLYMPUS PrestamoOpenCreditManagerImpl.
 *
 * @author Silvina_Luque
 */
@Component
public class PrestamoOpenCreditManagerImpl implements PrestamoOpenCreditManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoOpenCreditManagerImpl.class);

	/** Fecha sin valor. */
	private static final String FORMATO_FECHA_VACIA = "--/--/----";

	/** Pago sin valor. */
	private static final String FORMATO_PAGO_VACIO = "-";

	/** Primer recibo. */
	private static final int NUMERO_PRIMER_RECIBO = 1;

	/** The prestamo BO. */
	@Autowired
	private PrestamoOpenCreditBO prestamoOpenCreditBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The legal BO. */
	@Autowired
	protected LegalBO legalBO;

	/**
	 * Obtener prestamos open credit OLYMPUS.
	 *
	 * @return Respuesta<List<PrestamoOpenCreditView>>
	 */
	@Override
	public Respuesta<PrestamosOpenCreditView> obtenerPrestamosOpenCredit() {
		LOGGER.info("PrestamoOpenCreditManagerImpl iniciando obtenerPrestamosOpenCredit");
		PrestamoOpenCreditInDTO prestamoOpenCreditInDTO = generarPrestamoOpenCreditInDTO();
		List<PrestamoOpenCreditView> prestamosOpenCreditView;
		PrestamosOpenCreditView prestamosOC = new PrestamosOpenCreditView();
		Respuesta<List<PrestamoOpenCreditDTO>> respuestaBO = prestamoOpenCreditBO
				.obtenerPrestamosOpenCredit(prestamoOpenCreditInDTO);
		if (!EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
			prestamosOpenCreditView = obtenerPrestamosOpenCreditView(respuestaBO.getRespuesta());
			prestamosOC.setPrestamos(prestamosOpenCreditView);
			Respuesta<String> respuestaLegales = legalBO.buscarLegal(CodigoMensajeConstantes.DETALLE_OPEN_CREDIT);
			if (EstadoRespuesta.OK.equals(respuestaLegales.getEstadoRespuesta())) {
				prestamosOC.setLegales(respuestaLegales.getRespuesta());
			}
			if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
    			LOGGER.debug("PrestamoOpenCreditManagerImpl finalizando obtenerPrestamosOpenCredit OK");
    			return respuestaFactory.crearRespuestaOk(prestamosOC);
			} else {
                LOGGER.debug("PrestamoOpenCreditManagerImpl finalizando obtenerPrestamosOpenCredit WARNING");
                return respuestaFactory.crearRespuestaWarning(prestamosOC, respuestaBO.getItemsMensajeRespuesta());			    
			}
		}
		LOGGER.debug("PrestamoOpenCreditManagerImpl finalizando obtenerPrestamosOpenCredit error");
		return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_BUSCAR_PRESTAMOS_OC,
				CodigoMensajeConstantes.ERROR_BUSCAR_PRESTAMOS);
	}

	/**
	 * Obtener detalle de pagos minimos.
	 *
	 * @param consultaDetallePagoMinimoView
	 *            the consulta detalle pago minimo view
	 * @return Respuesta DetallePagosMinimosOpenCreditView
	 */
	@Override
	public Respuesta<DetallePagosMinimosOpenCreditView> obtenerDetallePagosMinimos(
			CnsDetallePagosMinimosOpenCreditView consultaDetallePagoMinimoView) {
		LOGGER.info("PrestamoOpenCreditManager iniciando metodo obtenerDetallePagoMinimo");
		ConsultaPagosMinimosOpenCreditInDTO consultaPagosMinimosInDTO = generarConsultaPagosInDTO(
				consultaDetallePagoMinimoView);
		Respuesta<DetallePagosMinimosOpenCreditDTO> respuestaBO = prestamoOpenCreditBO
				.obtenerDetallePagosMinimos(consultaPagosMinimosInDTO);
		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			DetallePagosMinimosOpenCreditView detallePagosPorFecha = new DetallePagosMinimosOpenCreditView();
			if (!(respuestaBO.getRespuesta().getPagos().isEmpty())) {
				LOGGER.debug("PrestamoOpenCreditManager obtenerDetallePagoMinimo iniciando generacion de vistas");
				detallePagosPorFecha.setPagosFecha(new ArrayList<PagosFechaOpenCreditView>());
				for (PagoOpenCreditDTO pagoDTO : respuestaBO.getRespuesta().getPagos()) {
					PagoOpenCreditView pagoView = generarPagoOpenCreditView(pagoDTO);
					String anio = pagoDTO.getFechaVencimiento().substring(0, 4);
					obtenerListaPagosPorFecha(anio, detallePagosPorFecha).add(pagoView);
				}
			}
			LOGGER.debug("PrestamoOpenCreditManager finalizando obtenerDetallePagoMinimo respuesta ok");
			Respuesta<String> respuestaLegales = legalBO.buscarLegal(CodigoMensajeConstantes.HISTORIAL_OPEN_CREDIT);
			if (EstadoRespuesta.OK.equals(respuestaLegales.getEstadoRespuesta())) {
				detallePagosPorFecha.setLegales(respuestaLegales.getRespuesta());
			}
			return respuestaFactory.crearRespuestaOk(detallePagosPorFecha);
		}
		LOGGER.debug("PrestamoOpenCreditManager finalizando obtenerDetallePagoMinimo respuesta error");
		return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_BUSCAR_PRESTAMOS,
				CodigoMensajeConstantes.PRESTAMOS_OPEN_CREDIT_ERROR_GENERICO);
	}

	/**
	 * Exportar historial de pagos minimos.
	 *
	 * @param exportarPagosMinimosOpenCreditView
	 *            the exportar pagos minimos open credit view
	 * @return ReporteView
	 */
	@Override
	public Respuesta<ReporteView> exportarHistorialPagosMinimos(
			ReportePagosMinimosOpenCreditInView exportarPagosMinimosOpenCreditView) {
		Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
		try {
			LOGGER.info("Inicio exportarHistorialPagosMinimos ");
			Respuesta<Reporte> respuestaReporte;
			respuestaReporte = prestamoOpenCreditBO.exportarHistorialPagosMinimos(exportarPagosMinimosOpenCreditView,
					sesionCliente.getCliente());
			Reporte reporte = respuestaReporte.getRespuesta();
			if (reporte != null) {
				ReporteView resumenesView = ReporteView.fromReporte(reporte);
				respuestaView.setRespuesta(resumenesView);
				respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
			}

		} catch (RuntimeException e) {
			LOGGER.error("Error openCredit exportarHistorialPagosMinimos ");
			respuestaView.setRespuestaVacia(true);
			respuestaView.setRespuesta(null);
			respuestaView.setEstadoRespuesta(EstadoRespuesta.ERROR);
		}
		LOGGER.info("Finalizando exportarHistorialPagosMinimos");
		return respuestaView;

	}

	/**
	 * Generar prestamos View .
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @return List<PrestamoOpenCreditView>
	 */
	private List<PrestamoOpenCreditView> obtenerPrestamosOpenCreditView(List<PrestamoOpenCreditDTO> respuestaDTO) {
		LOGGER.debug("PrestamoOpenCreditManager inicio obtenerPrestamosOpenCreditView");
		List<PrestamoOpenCreditView> listOpenCreditView = new ArrayList<PrestamoOpenCreditView>();
		for (Iterator<PrestamoOpenCreditDTO> iterator = ListUtil.safe(respuestaDTO).iterator(); iterator.hasNext();) {
			PrestamoOpenCreditDTO prestamoDTO = iterator.next();
			generarPrestamoOpenCreditView(listOpenCreditView, prestamoDTO);
		}
		LOGGER.debug("PrestamoOpenCreditManager finalizando obtenerPrestamosOpenCreditView");
		return listOpenCreditView;
	}

	/**
	 * Generar Prestamo Open Credit view.
	 *
	 * @param listOpenCreditView
	 *            the list open credit view
	 * @param prestamoDTO
	 *            the prestamo DTO
	 */
	private void generarPrestamoOpenCreditView(List<PrestamoOpenCreditView> listOpenCreditView,
			PrestamoOpenCreditDTO prestamoDTO) {
		PrestamoOpenCreditView prestamoView = new PrestamoOpenCreditView();
		String simboloMoneda = prestamoDTO.getDivisa().getSimbolo();
		prestamoView.setId(String.valueOf(prestamoDTO.getCuenta().getIndex()));
		prestamoView.setDescripcion(prestamoDTO.getDescripcion());
		prestamoView.setNumeroPrestamo(ISBANStringUtils.formatearSucursal(prestamoDTO.getCuenta().getNroSucursal())
				+ "-" + ISBANStringUtils.formatearNumeroCuenta(prestamoDTO.getCuenta().getNroCuentaProducto()));
		prestamoView.setDisponibleParaUso(
				PrestamoOpenCreditUtils.formatearValorMoneda(prestamoDTO.getDisponibleParaUso(), simboloMoneda));
		prestamoView.setLineaCreditoTotal(
				PrestamoOpenCreditUtils.formatearValorMoneda(prestamoDTO.getLineaCreditoTotal(), simboloMoneda));
		if (prestamoDTO.getDisponibleParaUso().compareTo(prestamoDTO.getLineaCreditoTotal()) == 0) {
			prestamoView.setFechaVencimiento(FORMATO_FECHA_VACIA);
			prestamoView.setPagoMinimo(FORMATO_PAGO_VACIO);
		} else {
			prestamoView.setFechaVencimiento(ISBANStringUtils.formatearFecha(prestamoDTO.getVencimientoCuota()));
			prestamoView.setPagoMinimo(
					PrestamoOpenCreditUtils.formatearValorMoneda(prestamoDTO.getPagoMinimo(), simboloMoneda));
		}
		prestamoView.setIngresosBrutos(prestamoDTO.getIngresosBrutos().compareTo(BigDecimal.ZERO) == 0 ? ""
				: PrestamoOpenCreditUtils.formatearValorMoneda(prestamoDTO.getIngresosBrutos(), simboloMoneda));
		prestamoView.setIntCompensatoriosPeriodo(
				prestamoDTO.getIntCompensatoriosPeriodo().compareTo(BigDecimal.ZERO) == 0 ? ""
						: PrestamoOpenCreditUtils.formatearValorMoneda(prestamoDTO.getIntCompensatoriosPeriodo(),
								simboloMoneda));
		prestamoView.setIntCompensatoriosPostVenciemiento(
				prestamoDTO.getIntCompensatoriosPostVencimiento().compareTo(BigDecimal.ZERO) == 0 ? ""
						: PrestamoOpenCreditUtils.formatearValorMoneda(
								prestamoDTO.getIntCompensatoriosPostVencimiento(), simboloMoneda));
		prestamoView.setIntPunitorios(prestamoDTO.getInteresesPunitorios().compareTo(BigDecimal.ZERO) == 0 ? ""
				: PrestamoOpenCreditUtils.formatearValorMoneda(prestamoDTO.getInteresesPunitorios(), simboloMoneda));
		prestamoView.setIva(prestamoDTO.getIva().compareTo(BigDecimal.ZERO) == 0 ? ""
				: PrestamoOpenCreditUtils.formatearValorMoneda(prestamoDTO.getIva(), simboloMoneda));
		prestamoView.setOtrosImpuestos(prestamoDTO.getOtrosImpuestos().compareTo(BigDecimal.ZERO) == 0 ? ""
				: PrestamoOpenCreditUtils.formatearValorMoneda(prestamoDTO.getOtrosImpuestos(), simboloMoneda));
		prestamoView.setTasaTNA(BigDecimal.ZERO.compareTo(prestamoDTO.getTasaTNA()) == 0 ? StringUtils.EMPTY : PrestamoOpenCreditUtils.formatearPorcentaje(prestamoDTO.getTasaTNA()));
		prestamoView.setVerDetalle(
				prestamoDTO.getLineaCreditoTotal().compareTo(prestamoDTO.getDisponibleParaUso()) == 0 ? false : true);
		prestamoView.setVerHistorialPagosMinimos(prestamoDTO.getNumeroRecibo() > NUMERO_PRIMER_RECIBO ? true : false);
		prestamoView.setFechaInicio(prestamoDTO.getFechaInicio());
		prestamoView.setCapital(BigDecimal.ZERO.compareTo(prestamoDTO.getCapital()) == 0 ? StringUtils.EMPTY : PrestamoOpenCreditUtils.formatearValorMoneda(prestamoDTO.getCapital(), simboloMoneda));
		prestamoView.setTasaTEA(BigDecimal.ZERO.compareTo(prestamoDTO.getTasaTEA()) == 0 ? StringUtils.EMPTY : PrestamoOpenCreditUtils.formatearPorcentaje(prestamoDTO.getTasaTEA()));
		prestamoView.setCftConImp(BigDecimal.ZERO.compareTo(prestamoDTO.getCftConImp()) == 0 ? StringUtils.EMPTY : PrestamoOpenCreditUtils.formatearPorcentaje(prestamoDTO.getCftConImp()));
		prestamoView.setCftSinImp(BigDecimal.ZERO.compareTo(prestamoDTO.getCftSinImp()) == 0 ? StringUtils.EMPTY : PrestamoOpenCreditUtils.formatearPorcentaje(prestamoDTO.getCftSinImp()));
		listOpenCreditView.add(prestamoView);
	}

	/**
	 * generarConsultaPagosInDTO.
	 *
	 * @param consultaDetallePagoMinimoView
	 *            the consulta detalle pago minimo view
	 * @return the consulta pagos minimos open credit in DTO
	 */
	private ConsultaPagosMinimosOpenCreditInDTO generarConsultaPagosInDTO(
			CnsDetallePagosMinimosOpenCreditView consultaDetallePagoMinimoView) {
		LOGGER.debug("PrestamoOpenCreditManager generarConsultaPagosInDTO");
		ConsultaPagosMinimosOpenCreditInDTO consultaPagosMinimosInDTO = new ConsultaPagosMinimosOpenCreditInDTO();
		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuentaPrestamo = cliente.obtenerPrestamoPorID(consultaDetallePagoMinimoView.getId());
		consultaPagosMinimosInDTO.setCuenta(cuentaPrestamo);
		consultaPagosMinimosInDTO.setCliente(cliente);
		consultaPagosMinimosInDTO.setFechaInicio(consultaDetallePagoMinimoView.getFechaInicio());
		return consultaPagosMinimosInDTO;
	}

	/**
	 * generarPagoOpenCreditView.
	 *
	 * @param pagoDTO
	 *            the pago DTO
	 * @return the pago open credit view
	 */
	private PagoOpenCreditView generarPagoOpenCreditView(PagoOpenCreditDTO pagoDTO) {
		LOGGER.debug("PrestamoOpenCreditManager generarPagoOpenCreditView");
		PagoOpenCreditView pagoView = new PagoOpenCreditView();
		String simboloMoneda = pagoDTO.getDivisa().getSimbolo();
		pagoView.setNumeroPagoMinimo(pagoDTO.getNumeroPagoMinimo());
		pagoView.setFechaVencimiento(PrestamoOpenCreditUtils.formatearFecha(pagoDTO.getFechaVencimiento()));
		pagoView.setPagoMinimo(PrestamoOpenCreditUtils.formatearValorMoneda(
				ISBANStringUtils.stringToBigDecimal(pagoDTO.getPagoMinimo(), 13, 4, false), simboloMoneda));
		BigDecimal tasaTNA = ISBANStringUtils.stringToBigDecimal(pagoDTO.getTasaTNA(), 3, 6, false);
		pagoView.setTasaTNA(BigDecimal.ZERO.compareTo(tasaTNA) == 0 ? StringUtils.EMPTY : PrestamoOpenCreditUtils
				.formatearPorcentaje(tasaTNA));
		BigDecimal interesesCompensatoriosPeriodo = ISBANStringUtils
				.stringToBigDecimal(pagoDTO.getIntCompensatoriosPeriodo(), 13, 4, false);
		pagoView.setIntCompensatoriosPeriodo(interesesCompensatoriosPeriodo.compareTo(BigDecimal.ZERO) == 0 ? ""
				: PrestamoOpenCreditUtils.formatearValorMoneda(interesesCompensatoriosPeriodo, simboloMoneda));
		BigDecimal impuestos = ISBANStringUtils.stringToBigDecimal(pagoDTO.getImpuestos(), 13, 4, false);
		pagoView.setImpuestos(impuestos.compareTo(BigDecimal.ZERO) == 0 ? ""
				: PrestamoOpenCreditUtils.formatearValorMoneda(impuestos, simboloMoneda));
		BigDecimal capital = ISBANStringUtils.stringToBigDecimal(pagoDTO.getCapital(), 13, 4, false);
		pagoView.setCapital(BigDecimal.ZERO.compareTo(capital) == 0 ? StringUtils.EMPTY : PrestamoOpenCreditUtils.formatearValorMoneda(capital, simboloMoneda));
		BigDecimal tasaTEA = ISBANStringUtils.stringToBigDecimal(pagoDTO.getTasaTEA(), 3, 6, false);
		pagoView.setTasaTEA(BigDecimal.ZERO.compareTo(tasaTEA) == 0 ? StringUtils.EMPTY : PrestamoOpenCreditUtils.formatearPorcentaje(tasaTEA));
		BigDecimal cftConImp = ISBANStringUtils.stringToBigDecimal(pagoDTO.getCftConImp(), 3, 6, false);
		pagoView.setCftConImp(BigDecimal.ZERO.compareTo(cftConImp) == 0 ? StringUtils.EMPTY : PrestamoOpenCreditUtils.formatearPorcentaje(cftConImp));
		BigDecimal cftSinImp = ISBANStringUtils.stringToBigDecimal(pagoDTO.getCftSinImp(), 3, 6, false);
		pagoView.setCftSinImp(BigDecimal.ZERO.compareTo(cftSinImp) == 0 ? StringUtils.EMPTY : PrestamoOpenCreditUtils.formatearPorcentaje(cftSinImp));
		return pagoView;
	}

	/**
	 * obtenerListaPagosPorFecha.
	 *
	 * @param anio
	 *            the anio
	 * @param detallePagosPorFecha
	 *            the detalle pagos por fecha
	 * @return the list
	 */
	private List<PagoOpenCreditView> obtenerListaPagosPorFecha(String anio,
			DetallePagosMinimosOpenCreditView detallePagosPorFecha) {
		LOGGER.debug("PrestamoOpenCreditManager obtenerListaPagosPorFecha");
		List<PagosFechaOpenCreditView> listaPagosFecha = detallePagosPorFecha.getPagosFecha();
		for (PagosFechaOpenCreditView pagosFecha : listaPagosFecha) {
			if (pagosFecha.getFecha().equals(anio)) {
				return pagosFecha.getPagos();
			}
		}
		PagosFechaOpenCreditView nuevoPagosFecha = new PagosFechaOpenCreditView();
		nuevoPagosFecha.setFecha(anio);
		listaPagosFecha.add(nuevoPagosFecha);
		nuevoPagosFecha.setPagos(new ArrayList<PagoOpenCreditView>());
		return nuevoPagosFecha.getPagos();
	}

	/**
	 * generarPrestamoOpenCreditInDTO.
	 *
	 * @return PrestamoOpenCreditInDTO
	 */
	private PrestamoOpenCreditInDTO generarPrestamoOpenCreditInDTO() {
		LOGGER.debug("PrestamoOpenCreditManagerImpl generarPrestamoOpenCreditInDTO");
		PrestamoOpenCreditInDTO prestamoOpenCreditInDTO = new PrestamoOpenCreditInDTO();
		prestamoOpenCreditInDTO.setCliente(sesionCliente.getCliente());
		return prestamoOpenCreditInDTO;
	}

}
