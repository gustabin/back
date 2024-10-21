/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesPagoTarjetasBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ComprobantesPagoTarjetaDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteVisaAmexIATXInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteVisaAmexIATXOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteVisaIATXEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CabeceraComprobantesEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteIatxPagoDeTarjetaCreditoDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.HistorialComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoDetalleComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;

/**
 * Comprobantes de pago de tarjetas de credito visa/amex.
 *
 * @author federico.n.flores
 */
@Component
public class ComprobantesPagoTarjetasBOImpl extends ComprobantesBOImpl implements ComprobantesPagoTarjetasBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobantesPagoTarjetasBOImpl.class);

	/** The ComprobantesPagoTarjetaDAO. */
	@Autowired
	private ComprobantesPagoTarjetaDAO comprobantesPagoTarjetaDAO;

	/** The diasFechaDesde. */
	@Value("${COMPROBANTES.FECHA.ANTERIOR}")
	private String diasFechaDesde;

	/** The Constant estadoTarjeta. */
	private final String estadoTarjeta = "29";

	/** The Constant menos. */
	private final String menos = "-";

	/** The Constant estadoTarjeta. */
	private final String retornoSinComprobantes = "10000036";

	/** The Constant formatoFecha. */
	private final String formatoFecha = "yyyyMMdd";

	/** The Constant tipoMonedaArs. */
	private final String tipoMonedaArs = "ARS";

	/** The Constant cero. */
	private final String cero = "0";

	/** The Constant guion. */
	private final String estadoA = "A";

	/** The Constant guion. */
	private final String estadoE = "E";

	/** The Constant guion. */
	private final String estadoV = "V";

	/** The Constant guion. */
	private final String estadoR = "R";

	/** The Constant DEBITO_AUTOMATICO. */
	private static final String DEBITO_AUTOMATICO = "DA";

	/**
	 * Consultar iatx comprobantes pago tarjetas.
	 *
	 * @param cuentasVisaAmex
	 *            the cuentas visa amex
	 * @param cliente
	 *            the cliente
	 * @param dto
	 *            the dto
	 * @return the hash map
	 */
	private HashMap<Long, Future<ComprobanteVisaAmexIATXOutEntity>> consultarIatxComprobantesPagoTarjetas(
			List<Cuenta> cuentasVisaAmex, Cliente cliente, TransaccionDTO dto) {
		HashMap<Long, Future<ComprobanteVisaAmexIATXOutEntity>> listaComprobantesIatx = new HashMap<Long, Future<ComprobanteVisaAmexIATXOutEntity>>();
		for (Cuenta cuenta : cuentasVisaAmex) {
			ComprobanteVisaAmexIATXInEntity inEntity = obtenerInEntityPagoTarjeta(cuenta, cliente, dto.getFechaDesde(),
					dto.getFechaHasta());
			try {
				Future<ComprobanteVisaAmexIATXOutEntity> outEntity = comprobantesPagoTarjetaDAO
						.consultarAsync(inEntity);
				listaComprobantesIatx.put(cuenta.getId(), outEntity);
			} catch (DAOException e) {
				LOGGER.error("Error en servicio Pago de Tarjetas: ", e);
				listaComprobantesIatx.put(cuenta.getId(), comprobantesPagoTarjetaDAO.obtenerOutEntityErrorAsync());
			}
		}
		return listaComprobantesIatx;
	}

	/**
	 * Obtener cuentas visa amex master.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the list
	 */
	private List<Cuenta> obtenerCuentasVisaAmexMaster(Cliente cliente) {
		List<String> numerosCuenta = new ArrayList<String>();
		List<Cuenta> cuentasVisaAmexMaster = new ArrayList<Cuenta>();
		for (Cuenta cuenta : cliente.getCuentas()) {
			boolean esVisaOAmexOMaster = cuenta.getTipoCuentaEnum().equals(TipoCuenta.VISA)
					|| cuenta.getTipoCuentaEnum().equals(TipoCuenta.AMEX)
					|| cuenta.getTipoCuentaEnum().equals(TipoCuenta.MASTERCARD);
			if (esVisaOAmexOMaster && !cuenta.getEstadoTarjetaCredito().equals(estadoTarjeta)
					&& !numerosCuenta.contains(cuenta.getNroCuentaProducto())
					&& "TI".equals(cuenta.getCodigoTitularidad())) {
				numerosCuenta.add(cuenta.getNroCuentaProducto());
				cuentasVisaAmexMaster.add(cuenta);
			}
		}
		return cuentasVisaAmexMaster;
	}

	/**
	 * Obtener in entity pago tarjeta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param cliente
	 *            the cliente
	 * @param fechaDesde
	 *            the fecha desde
	 * @param fechaHasta
	 *            the fecha hasta
	 * @return the comprobante visa amex IATX in entity
	 */
	private ComprobanteVisaAmexIATXInEntity obtenerInEntityPagoTarjeta(Cuenta cuenta, Cliente cliente, Date fechaDesde,
			Date fechaHasta) {
		ComprobanteVisaAmexIATXInEntity inEntity = new ComprobanteVisaAmexIATXInEntity();
		inEntity.setCliente(cliente);
		inEntity.setSucursalCuentaTarjeta(cuenta.getNroSucursal());
		inEntity.setNroTarjeta(cuenta.getNroTarjetaCredito());
		inEntity.setCodTitularidad(StringUtils.left(cuenta.getCodigoTitularidad(), 1));
		SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
		if (fechaDesde == null || fechaHasta == null) {
			Date date = new Date();
			inEntity.setFechaHasta(sdf.format(date));
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, Integer.parseInt(menos + diasFechaDesde));
			date = cal.getTime();
			inEntity.setFechaDesde(sdf.format(date));
		} else {
			inEntity.setFechaHasta(sdf.format(fechaHasta));
			inEntity.setFechaDesde(sdf.format(fechaDesde));
		}
		return inEntity;
	}

	/**
	 * Sleep while execute.
	 *
	 * @param comprobantesIatx
	 *            the comprobantes iatx
	 */
	private void sleepWhileExecute(HashMap<Long, Future<ComprobanteVisaAmexIATXOutEntity>> comprobantesIatx) {
		while (!terminaronLosHilos(comprobantesIatx)) {
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.error("error" + e);
			}
		}
	}

	/**
	 * Terminaron los hilos.
	 *
	 * @param comprobantesIatx
	 *            the comprobantes iatx
	 * @return true, if successful
	 */
	private boolean terminaronLosHilos(HashMap<Long, Future<ComprobanteVisaAmexIATXOutEntity>> comprobantesIatx) {
		boolean termino = true;
		for (Future<ComprobanteVisaAmexIATXOutEntity> response : comprobantesIatx.values()) {
			if (!response.isDone()) {
				termino = false;
			}
		}
		return termino;
	}

	/**
	 * Procesar respuestas iatx.
	 *
	 * @param comprobantesIatx
	 *            the comprobantes iatx
	 * @param cuentasVisaAmex
	 *            the cuentas visa amex
	 * @param comprobantesDTO
	 *            the comprobantes DTO
	 * @return the int
	 */
	private int procesarRespuestasIatx(HashMap<Long, Future<ComprobanteVisaAmexIATXOutEntity>> comprobantesIatx,
			List<Cuenta> cuentasVisaAmex, List<ComprobantesDTO> comprobantesDTO, Boolean debitoAutomatico) {
		Integer errores = 0;
		for (Cuenta cuenta : cuentasVisaAmex) {
			ComprobanteVisaAmexIATXOutEntity outEntity = getOutEntity(comprobantesIatx.get(cuenta.getId()));
			if (StringUtils.containsOnly(outEntity.getCodigoRetornoExtendido(), "0")) {
				comprobantesDTO.add(obtenerComprobantesDTO(outEntity, cuenta.getTipoCuentaEnum(),
						cuenta.getNroTarjetaCredito(), debitoAutomatico));
			} else {
				if (!retornoSinComprobantes.equals(outEntity.getCodigoRetornoExtendido())) {
					errores++;
				}
			}
		}
		return errores;
	}

	/**
	 * Crear respuesta.
	 *
	 * @param comprobantesDTO
	 *            the comprobantes DTO
	 * @param errores
	 *            the errores
	 * @param cuentasVisaAmex
	 *            the cuentas visa amex
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuesta(List<ComprobantesDTO> comprobantesDTO, Integer errores,
			List<Cuenta> cuentasVisaAmex, TransaccionDTO transaccion) {
		if (errores == cuentasVisaAmex.size()) {
			return respuestaFactory.crearRespuestaError(ComprobantesDTO.class, new ArrayList<ItemMensajeRespuesta>());
		}
		Integer erroresDTO = 0;
		List<ComprobanteDTO> listaComprobantesDTO = new LinkedList<ComprobanteDTO>();
		for (ComprobantesDTO comprobanteDTO : comprobantesDTO) {
			listaComprobantesDTO.addAll(comprobanteDTO.getComprobantes());
			if (comprobanteDTO.getTieneError()) {
				erroresDTO++;
			}
		}
		if (transaccion.getImporteDesde() != null && transaccion.getImporteHasta() != null) {
			listaComprobantesDTO = filtrarComprobantesPorImporte(listaComprobantesDTO, transaccion.getImporteDesde(),
					transaccion.getImporteHasta());
		}
		if (errores == 0 && erroresDTO == 0) {
			return respuestaFactory.crearRespuestaOk(new ComprobantesDTO(listaComprobantesDTO));
		}
		return respuestaFactory.crearRespuestaWarning(new ArrayList<DatoItemMensaje>(),
				new ComprobantesDTO(listaComprobantesDTO));
	}

	/**
	 * Gets the out entity.
	 *
	 * @param futureOutEntity
	 *            the future out entity
	 * @return the out entity
	 */
	private ComprobanteVisaAmexIATXOutEntity getOutEntity(Future<ComprobanteVisaAmexIATXOutEntity> futureOutEntity) {
		try {
			return futureOutEntity.get();
		} catch (ExecutionException e) {
			if (e.getCause() instanceof DAOException) {
				LOGGER.error("Dao Exception:" + e);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.error("Error:" + e);
		}
		ComprobanteVisaAmexIATXOutEntity outEntity = new ComprobanteVisaAmexIATXOutEntity();
		outEntity.setCodigoRetornoExtendido("1");
		return outEntity;
	}

	/**
	 * Obtener comprobantes DTO.
	 *
	 * @param outEntity
	 *            the out entity
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the comprobantes DTO
	 */
	private ComprobantesDTO obtenerComprobantesDTO(ComprobanteVisaAmexIATXOutEntity outEntity, TipoCuenta tipoCuenta,
			String nroCuenta, Boolean debitoAutomatico) {
		Boolean error = false;
		LinkedList<ComprobanteDTO> listaComprobantesDTO = new LinkedList<ComprobanteDTO>();
		List<String> estadosValidos = new ArrayList<String>(Arrays.asList(estadoA, estadoE, estadoV, estadoR));
		for (ComprobanteVisaIATXEntity comprobante : outEntity.getComprobantes()) {
			if (estadoValido(comprobante, estadosValidos)
					&& debitoAutomatico.equals(StringUtils.equals(comprobante.getMedioDePago(), DEBITO_AUTOMATICO))) {
				ComprobanteDTO comprobanteDTO = obtenerComprobanteDTO(comprobante, tipoCuenta, nroCuenta,
						debitoAutomatico);
				if (comprobanteDTO.getTieneError()) {
					error = true;
				} else {
					listaComprobantesDTO.add(comprobanteDTO);
				}
			}
		}
		return new ComprobantesDTO(listaComprobantesDTO, error);
	}

	/**
	 * Estado valido.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @param estadosValidos
	 *            the estados validos
	 * @return the boolean
	 */
	private Boolean estadoValido(ComprobanteVisaIATXEntity comprobante, List<String> estadosValidos) {
		if (estadosValidos.contains(comprobante.getEstado())) {
			return true;
		}
		return false;
	}

	/**
	 * Obtener comprobante DTO.
	 *
	 * @param outEntity
	 *            the out entity
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTO(ComprobanteVisaIATXEntity outEntity, TipoCuenta tipoCuenta,
			String nroCuenta, Boolean debitoAutomatico) {
		ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
		try {
			comprobanteDTO
					.setFecha(new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(outEntity.getFechaPagoDebito()));
			comprobanteDTO.setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_TARJETA);
			comprobanteDTO.setHistorial(debitoAutomatico ? HistorialComprobanteEnum.DEBITO_AUTOMATICO_PAGO_TARJETA
					: HistorialComprobanteEnum.PAGO_TARJETA);
			setearImportes(outEntity, comprobanteDTO);
			setearTipoCuenta(comprobanteDTO, tipoCuenta, nroCuenta);
			comprobanteDTO.setCtaMedioDePagoPesos(obtenerMedioPagoTarjeta(outEntity.getMedioDePago()));
			comprobanteDTO.setDetalle(obtenerDetalle(tipoCuenta, nroCuenta));
		} catch (ParseException e) {
			comprobanteDTO.setTieneError(true);
		} catch (ImporteConvertException e) {
			LOGGER.error("Error: " + e);
			comprobanteDTO.setTieneError(true);
		}
		return comprobanteDTO;
	}

	/**
	 * Obtener medio pago tarjeta.
	 *
	 * @param medioDePago
	 *            the medio de pago
	 * @return the string
	 */
	private String obtenerMedioPagoTarjeta(String medioDePago) {
		if (DEBITO_AUTOMATICO.equals(medioDePago)) {
			return "Débito automático en cuenta";
		}
		return "Débito aplicado";
	}

	/**
	 * Obtener detalle.
	 *
	 * @return the detalle comprobante DTO
	 */
	private DetalleComprobanteDTO obtenerDetalle(TipoCuenta tipoCuenta, String nroCuenta) {
		DetalleComprobanteIatxPagoDeTarjetaCreditoDTO detalle = new DetalleComprobanteIatxPagoDeTarjetaCreditoDTO();
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PAGO_TARJETA);
		detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO_SANTANDER_RIO.getDetalle());
		detalle.setNroTarjetaCredito(nroCuenta);
		detalle.setTipoCuenta(tipoCuenta);
		return detalle;
	}

	/**
	 * Setear importes.
	 *
	 * @param outEntity
	 *            the out entity
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 */
	private void setearImportes(ComprobanteVisaIATXEntity outEntity, ComprobanteDTO comprobanteDTO)
			throws ImporteConvertException {
		if (esPagoSimultaneo(outEntity)) {
			comprobanteDTO.setImportePesos(
					ISBANStringUtils.convertirStrToBigDecimal(outEntity.getImportePagadoDebitado(), 2));
			comprobanteDTO
					.setImporteDolares(ISBANStringUtils.convertirStrToBigDecimal(outEntity.getImporteDolares(), 2));
		} else {
			BigDecimal importe = ISBANStringUtils.convertirStrToBigDecimal(outEntity.getImportePagadoDebitado(), 2);
			if (StringUtils.equals(outEntity.getTipoMoneda(), tipoMonedaArs)) {
				comprobanteDTO.setImportePesos(importe);
			} else {
				comprobanteDTO.setImporteDolares(importe);
			}
		}
	}

	/**
	 * Setear tipo cuenta.
	 *
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param nroCuenta
	 *            the nro cuenta
	 */
	private void setearTipoCuenta(ComprobanteDTO comprobanteDTO, TipoCuenta tipoCuenta, String nroCuenta) {
		comprobanteDTO.setTipoCuentaDestino(tipoCuenta);
		if (tipoCuenta.equals(TipoCuenta.MASTERCARD)) {
			comprobanteDTO.setDestinatario(
					TipoCuentaTarjeta.TIPOCTA_MASTER.getDescripcion() + " XXXX-" + StringUtils.right(nroCuenta, 4));
		} else if (tipoCuenta.equals(TipoCuenta.AMEX)) {
			comprobanteDTO.setDestinatario(
					TipoCuentaTarjeta.TIPOCTA_AMEX.getDescripcion() + " XXXX-" + StringUtils.right(nroCuenta, 5));
		} else {
			comprobanteDTO.setDestinatario(
					TipoCuentaTarjeta.TIPOCTA_VISA.getDescripcion() + " XXXX-" + StringUtils.right(nroCuenta, 4));
		}
	}

	/**
	 * Es pago simultaneo.
	 *
	 * @param visaIatxEntity
	 *            the visa iatx entity
	 * @return the boolean
	 */
	private Boolean esPagoSimultaneo(ComprobanteVisaIATXEntity visaIatxEntity) {
		String importeDolares = visaIatxEntity.getImporteDolares();
		String importeDolaresSinSigno = StringUtils.substring(importeDolares, 0, importeDolares.length() - 1);
		if (StringUtils.containsOnly(importeDolaresSinSigno, cero)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesPagoTarjetasBO#obtenerComprobantesPagoTarjetas(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPagoTarjetas(Cliente cliente, TransaccionDTO dto,
			Boolean debitoAutomatico) {
		List<Cuenta> cuentasVisaAmex = obtenerCuentasVisaAmexMaster(cliente);
		if (cuentasVisaAmex.isEmpty()) {
			return respuestaFactory.crearRespuestaOk(ComprobantesDTO.class,
					new ComprobantesDTO(new LinkedList<ComprobanteDTO>()));
		}
		HashMap<Long, Future<ComprobanteVisaAmexIATXOutEntity>> comprobantesPagoTarjeta = consultarIatxComprobantesPagoTarjetas(
				cuentasVisaAmex, cliente, dto);
		sleepWhileExecute(comprobantesPagoTarjeta);
		List<ComprobantesDTO> comprobantesDTO = new ArrayList<ComprobantesDTO>();
		int erroresServicio = procesarRespuestasIatx(comprobantesPagoTarjeta, cuentasVisaAmex, comprobantesDTO,
				debitoAutomatico);
		return crearRespuesta(comprobantesDTO, erroresServicio, cuentasVisaAmex, dto);
	}

	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPagoTarjetasPorCuenta(Cliente cliente, TransaccionDTO dto,
			Boolean debitoAutomatico, Cuenta cuenta) {
		List<Cuenta> cuentasVisaAmex = new ArrayList<Cuenta>(Arrays.asList(cuenta));
		HashMap<Long, Future<ComprobanteVisaAmexIATXOutEntity>> comprobantesPagoTarjeta = consultarIatxComprobantesPagoTarjetas(
				cuentasVisaAmex, cliente, dto);
		sleepWhileExecute(comprobantesPagoTarjeta);
		List<ComprobantesDTO> comprobantesDTO = new ArrayList<ComprobantesDTO>();
		int erroresServicio = procesarRespuestasIatx(comprobantesPagoTarjeta, cuentasVisaAmex, comprobantesDTO,
				debitoAutomatico);
		return crearRespuesta(comprobantesDTO, erroresServicio, cuentasVisaAmex, dto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesPagoTarjetasBO#obtenerComprobantesPagoTarjetasAsync(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPagoTarjetasAsync(Cliente cliente, TransaccionDTO dto,
			Boolean debitoAutomatico) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(
				obtenerComprobantesPagoTarjetas(cliente, dto, debitoAutomatico));
	}
}
