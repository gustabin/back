/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesSietePorVenticuatroBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CuitDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CuitEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaProgramadaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaProgramadaPAGHABONDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaProgramadaRioRioDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.HistorialComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoDetalleComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao.SietePorVenticuatroV1DAO;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.CONFIG;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.DATOSENTRADA.Parametros;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.META;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.META.Canal;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.META.Subcanal;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.META.Usuario;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro.XMLEntrada.DATOSENTRADA;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro.XMLResultado;

/**
 * The Class ComprobantesSietePorVenticuatroBOImpl.
 */
@Component
public class ComprobantesSietePorVenticuatroBOImpl extends ComprobantesBOImpl
		implements ComprobantesSietePorVenticuatroBO {

	/** The siete por venticuatro V 1 DAO. */
	@Autowired
	private SietePorVenticuatroV1DAO sietePorVenticuatroV1DAO;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobantesSietePorVenticuatroBOImpl.class);

	/** The Constant OBTENIENDO_COMPROBANTES_RIO. */
	private static final String OBTENIENDO_COMPROBANTES_RIO = "Obteniendo Comprobantes 7x24_Rio ";

	/** The Constant OBTENIENDO_COMPROBANTES_OTROS_BANCOS. */
	private static final String OBTENIENDO_COMPROBANTES_OTROS_BANCOS = "Obteniendo Comprobantes 7x24_Otros Bancos ";

	/** The Constant OBTENIENDO_COMPROBANTES_ACH. */
	private static final String OBTENIENDO_COMPROBANTES_ACH = "Obteniendo Comprobantes 7x24_ACH ";

	/** The Constant OBTENIENDO_COMPROBANTES_PAGHABON. */
	private static final String OBTENIENDO_COMPROBANTES_PAGHABON = "Obteniendo Comprobantes 7x24_PAGHABON ";

	/** The Constant EFECTUADA. */
	private static final String EFECTUADA = "efectuada:{}";

	/** The Constant NO_EFECTUADA. */
	private static final String NO_EFECTUADA = "no efectuada:{}";

	/** The Constant PLAZO_ACREDITACION. */
	private static final String PLAZO_ACREDITACION = "Inmediata";

	/** The estado no efectuada. */
	private static final String ESTADO_NO_EFECTUADA = "X,Y,R,L,D";

	/** The estado efectuada. */
	private static final String ESTADO_EFECTUADA = "E";

	/** The llamado rio. */
	private static final String LLAMADO_RIO = "TRANSF_BCO_RIO";

	/** The llamado OB. */
	private static final String LLAMADO_OB = "TRFCCI";

	/** The llamado ACH. */
	private static final String LLAMADO_ACH = "TRFACH";

	/** The llamado paghabon. */
	private static final String LLAMADO_PAGHABON = "PAGHABHON_";

	/** The no efectuada. */
	private static final String RECHAZADO = "Rechazado";

	/** The Constant CUARENTAIOCHO_HORAS. */
	private static final String CUARENTAIOCHO_HORAS = "48 hs";

	/** The alquiler. */
	@Value("${CIMPRA209.ALQ}")
	private String alquiler;

	/** The alquiler. */
	@Value("${CIMPRA209.CUO}")
	private String cuotas;

	/** The alquiler. */
	@Value("${CIMPRA209.EXP}")
	private String expensas;

	/** The alquiler. */
	@Value("${CIMPRA209.FAC}")
	private String factura;

	/** The alquiler. */
	@Value("${CIMPRA209.PRE}")
	private String prestamo;

	/** The alquiler. */
	@Value("${CIMPRA209.SEG}")
	private String seguro;

	/** The alquiler. */
	@Value("${CIMPRA209.HON}")
	private String honorarios;

	/** The alquiler. */
	@Value("${CIMPRA209.VAR}")
	private String varios;

	/** The alquiler. */
	@Value("${CIMPRA209.HAB}")
	private String haberes;

	/** The inmobiliaria. */
	@Value("${CIMPRA209.OIN}")
	private String inmobiliaria;

	/**
	 * Crear comprobantes DTO.
	 *
	 * @param esEfectuada
	 *            the es efectuada
	 * @param respuesta
	 *            the respuesta
	 * @param transaccion
	 *            the transaccion
	 * @return the comprobantes DTO
	 */
	private ComprobantesDTO crearComprobantesDTO(boolean esEfectuada, XMLResponseEntity respuesta,
			TransaccionDTO transaccion) {
		ComprobantesDTO comprobantes = new ComprobantesDTO(false);
		if (respuesta.getDATOSRESULTADO().getRegistro() != null) {
			procesarRegistros(respuesta, esEfectuada, comprobantes, transaccion);
		}
		return comprobantes;
	}

	/**
	 * Procesar registros.
	 *
	 * @param response
	 *            the response
	 * @param isEfectuada
	 *            the is efectuada
	 * @param comprobantes
	 *            the comprobantes
	 * @param transaccion
	 *            the transaccion
	 */
	private void procesarRegistros(XMLResponseEntity response, boolean isEfectuada, ComprobantesDTO comprobantes,
			TransaccionDTO transaccion) {
		for (Registro registro : response.getDATOSRESULTADO().getRegistro()) {
			try {
				ComprobanteDTO comprobanteDTO = obtenerComprobanteDTO(registro, isEfectuada);
				if (comprobanteDTO.getTieneError()) {
					comprobantes.setTieneError(true);
				} else if (!comprobantes.getComprobantes().contains(comprobanteDTO)) {
					comprobantes.getComprobantes().add(comprobanteDTO);
				}
				if (transaccion.getImporteDesde() != null && transaccion.getImporteHasta() != null) {
					comprobantes.setComprobantes(
							filtrarComprobantesPorImporte((List<ComprobanteDTO>) comprobantes.getComprobantes(),
									transaccion.getImporteDesde(), transaccion.getImporteHasta()));
				}
				if (transaccion.getFechaDesde() != null && transaccion.getFechaHasta() != null) {
					comprobantes.setComprobantes(filtrarPorFecha(comprobantes.getComprobantes(), transaccion));
				}
			} catch (NullPointerException e) {
				LOGGER.error("Error:" + e);
				comprobantes.setTieneError(true);
			}
		}

	}

	/**
	 * Filtra los comprobantes por aquellos que sean validos, osea por aquellos cuya
	 * fecha desde viene antes o en el mismo momento de fecha hasta.
	 *
	 * @param comprobantes
	 *            the comprobantes
	 * @param dto
	 *            the dto
	 * @return the list
	 */
	private List<ComprobanteDTO> filtrarPorFecha(List<ComprobanteDTO> comprobantes, TransaccionDTO dto) {
		List<ComprobanteDTO> comprobantesFiltrados = new LinkedList<ComprobanteDTO>();
		for (ComprobanteDTO comprobante : comprobantes) {
			if ((comprobante.getFecha().after(dto.getFechaDesde())
					&& comprobante.getFecha().before(dto.getFechaHasta()))
					|| comprobante.getFecha().equals(dto.getFechaDesde())
					|| comprobante.getFecha().equals(dto.getFechaHasta())) {
				comprobantesFiltrados.add(comprobante);
			}
		}
		return comprobantesFiltrados;

	}

	/**
	 * Obtener comprobante DTO.
	 *
	 * @param datosEntrada
	 *            the datos entrada
	 * @param isEfectuada
	 *            the is efectuada
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTO(Registro datosEntrada, boolean isEfectuada) {
		ComprobanteDTO comprobante = new ComprobanteDTO();
		comprobante.setNecesitaMoneda(false);
		comprobante.setNoEfectuada(!isEfectuada);
		if ("TRANSF_BCO_RIO".equals(datosEntrada.getNombreGuardado())) {
			completarComprobanteRio(datosEntrada, isEfectuada, comprobante);
		} else if ("TRFACH".equals(datosEntrada.getNombreGuardado())) {
			completarComprobanteACH(datosEntrada, isEfectuada, comprobante);
		} else if ("TRFCCI".equals(datosEntrada.getNombreGuardado())) {
			completarComprobanteOtrosBancos(datosEntrada, isEfectuada, comprobante);
		} else if ("PAGHABHON_".equals(datosEntrada.getNombreGuardado())) {
			completarComprobantePagoHaberesYHonorarios(datosEntrada, isEfectuada, comprobante);
		}

		return comprobante;
	}

	/**
	 * Completar comprobante pago haberes Y honorarios.
	 *
	 * @param datosEntrada
	 *            the datos entrada
	 * @param isEfectuada
	 *            the is efectuada
	 * @param comprobante
	 *            the comprobante
	 */
	private void completarComprobantePagoHaberesYHonorarios(Registro datosEntrada, boolean isEfectuada,
			ComprobanteDTO comprobante) {
		comprobante.setHistorial(HistorialComprobanteEnum.PAGO_SUELDOS);
		try {
			comprobante.setFecha(obtenerDate7x24(datosEntrada.getFechaEjecucion()));
		} catch (ParseException e) {
			comprobante.setTieneError(true);
			return;
		}
		String tipoPago = datosEntrada.getXmlEntrada().getDatosAdicionales().getDatosSueldos().getTipoPago();
		if ("S".equals(tipoPago)) {
			comprobante.setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_SUELDOS);
		} else if ("H".equals(tipoPago)) {
			comprobante.setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_HONORARIOS);
		}
		setearDestinatarioByString(
				datosEntrada.getXmlEntrada().getDatosAdicionales().getDatosSueldos().getDestinatario(), comprobante);
		if (datosEntrada.getXmlEntrada().getDATOSENTRADA() == null) {
			completarComprobantePagHabonConXmlEntrada(datosEntrada.getXmlEntrada(), isEfectuada, comprobante);
		} else {
			completarComprobantePagHabonConDATOSEntrada(datosEntrada.getXmlEntrada().getDATOSENTRADA(), isEfectuada,
					comprobante);
		}
		if (isEfectuada) {
			comprobante.getDetalle().setNroComprobante(datosEntrada.getXmlResultado().getComprobanteBackEnd());
		}
	}

	/**
	 * Completar comprobante pag habon con DATOS entrada.
	 *
	 * @param datosentrada
	 *            the datosentrada
	 * @param isEfectuada
	 *            the is efectuada
	 * @param comprobante
	 *            the comprobante
	 */
	private void completarComprobantePagHabonConDATOSEntrada(DATOSENTRADA datosentrada, boolean isEfectuada,
			ComprobanteDTO comprobante) {
		String moneda = obtenerMoneda(datosentrada.getTipoCtaDebito());
		String importe = datosentrada.getImporte();
		// $ o u$s ambos tienen el simbolo
		if (importe.contains("$")) {
			importe = importe.split(" ")[1];
		}
		if (importe.contains(",")) {
			setearImportePesosDolar(moneda, String.valueOf(importe), comprobante);
		} else {
			setImportePesosDolarSinComa(moneda, String.valueOf(importe), comprobante);
		}
		obtenerDescripcion(datosentrada.getTipoCtaDebito(), moneda, comprobante);
		setNumeroCuentaPesoDolarParseado(datosentrada.getSucCtaDebito(), datosentrada.getNroCtaDebito(), comprobante);
		comprobante.setDetalle(obtenerDetalleTrasfProgramadasPAGHABON(datosentrada, isEfectuada));
	}

	/**
	 * Completar comprobante pag habon con xml entrada.
	 *
	 * @param xmlEntrada
	 *            the xml entrada
	 * @param isEfectuada
	 *            the is efectuada
	 * @param comprobante
	 *            the comprobante
	 */
	private void completarComprobantePagHabonConXmlEntrada(XMLEntrada xmlEntrada, boolean isEfectuada,
			ComprobanteDTO comprobante) {
		String moneda = obtenerMoneda(xmlEntrada.getTipoCtaDebito());
		String importe = xmlEntrada.getImporte();
		// $ o u$s ambos tienen el simbolo
		if (importe.contains("$")) {
			importe = importe.split(" ")[1];
		}
		if (importe.contains(",")) {
			setearImportePesosDolar(moneda, String.valueOf(importe), comprobante);
		} else {
			setImportePesosDolarSinComa(moneda, String.valueOf(importe), comprobante);
		}
		obtenerDescripcion(xmlEntrada.getTipoCtaDebito(), moneda, comprobante);
		setNumeroCuentaPesoDolarParseado(xmlEntrada.getSucCtaDebito(), xmlEntrada.getNroCtaDebito(), comprobante);
		comprobante.setDetalle(obtenerDetalleTrasfProgramadasPAGHABON(xmlEntrada, isEfectuada));
	}

	/**
	 * Completar comprobante otros bancos.
	 *
	 * @param datosEntrada
	 *            the datos entrada
	 * @param isEfectuada
	 *            the is efectuada
	 * @param comprobante
	 *            the comprobante
	 */
	private void completarComprobanteOtrosBancos(Registro datosEntrada, boolean isEfectuada,
			ComprobanteDTO comprobante) {
		try {
			comprobante.setFecha(obtenerDate7x24(datosEntrada.getFechaEjecucion()));
		} catch (ParseException e) {
			comprobante.setTieneError(true);
			return;
		}
		comprobante.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA);
		comprobante.setHistorial(HistorialComprobanteEnum.TRANSFERENCIA_OTROSBANCOS);
		if (datosEntrada.getXmlEntrada().getDATOSENTRADA() == null) {
			completarComprobanteOtrosBancosConXmlEntrada(datosEntrada.getXmlEntrada(), datosEntrada.getXmlResultado(),
					isEfectuada, comprobante);
		} else {
			completarComprobanteOtrosBancosConDATOSEntrada(datosEntrada.getXmlEntrada().getDATOSENTRADA(),
					datosEntrada.getXmlResultado(), isEfectuada, comprobante);
		}

	}

	/**
	 * Completar comprobante otros bancos con DATOS entrada.
	 *
	 * @param datosentrada
	 *            the datosentrada
	 * @param xmlResultado
	 *            the xml resultado
	 * @param isEfectuada
	 *            the is efectuada
	 * @param comprobante
	 *            the comprobante
	 */
	private void completarComprobanteOtrosBancosConDATOSEntrada(DATOSENTRADA datosentrada, XMLResultado xmlResultado,
			boolean isEfectuada, ComprobanteDTO comprobante) {
		String moneda = "U$S";
		if ("0".equals(datosentrada.getMonedaTransferencia())) {
			moneda = "$";
		}
		if (isEfectuada) {
			setImportePesosDolarSinComa(moneda, xmlResultado.getImporteDebitado(), comprobante);
			setearDestinatarioByString(datosentrada.getTitular(), comprobante);
			obtenerDescripcion(datosentrada.getTipoCtaDebito(), moneda, comprobante);
			setNumeroCuentaPesoDolarParseado(datosentrada.getSucCtaDebito(), datosentrada.getNroCtaDebito(),
					comprobante);
		} else {
			setImportePesosDolarSinComa(moneda, datosentrada.getImporteTransferencia(), comprobante);
			setearDestinatarioByString(datosentrada.getTitular(), comprobante);
			obtenerDescripcion(datosentrada.getTipoCtaDebito(), moneda, comprobante);
			setNumeroCuentaPesoDolarParseado(datosentrada.getSucCtaDebito(), datosentrada.getNroCtaDebito(),
					comprobante);
		}
		comprobante.setDetalle(obtenerDetalleTrasfProgramadasOtrosBancos(datosentrada, isEfectuada));
		if (isEfectuada) {
			comprobante.getDetalle().setNroComprobante(xmlResultado.getComprobanteBackEnd());
		}
	}

	/**
	 * Completar comprobante otros bancos con xml entrada.
	 *
	 * @param xmlEntrada
	 *            the xml entrada
	 * @param xmlResultado
	 *            the xml resultado
	 * @param isEfectuada
	 *            the is efectuada
	 * @param comprobante
	 *            the comprobante
	 */
	private void completarComprobanteOtrosBancosConXmlEntrada(XMLEntrada xmlEntrada, XMLResultado xmlResultado,
			boolean isEfectuada, ComprobanteDTO comprobante) {
		String moneda = "U$S";
		if ("0".equals(xmlEntrada.getMonedaTransferencia())) {
			moneda = "$";
		}
		if (isEfectuada) {
			if (xmlResultado.getImporteDebitado() != null) {
				setImportePesosDolarSinComa(moneda, xmlResultado.getImporteDebitado(), comprobante);
			}
			setearDestinatarioByString(xmlEntrada.getTitular(), comprobante);
			obtenerDescripcion(xmlEntrada.getTipoCtaDebito(), moneda, comprobante);
			setNumeroCuentaPesoDolarParseado(xmlEntrada.getSucCtaDebito(), xmlEntrada.getNroCtaDebito(), comprobante);
		} else {
			if (xmlEntrada.getImporteTransferencia() != null) {
				setImportePesosDolarSinComa(moneda, xmlEntrada.getImporteTransferencia(), comprobante);
			}
			setearDestinatarioByString(xmlEntrada.getTitular(), comprobante);
			obtenerDescripcion(xmlEntrada.getTipoCtaDebito(), moneda, comprobante);
			setNumeroCuentaPesoDolarParseado(xmlEntrada.getSucCtaDebito(), xmlEntrada.getNroCtaDebito(), comprobante);
		}
		comprobante.setDetalle(obtenerDetalleTrasfProgramadasOtrosBancos(xmlEntrada, isEfectuada));
		if (isEfectuada) {
			comprobante.getDetalle().setNroComprobante(xmlResultado.getComprobanteBackEnd());
		}
	}

	/**
	 * Completar comprobante ACH.
	 *
	 * @param datosEntrada
	 *            the datos entrada
	 * @param isEfectuada
	 *            the is efectuada
	 * @param comprobante
	 *            the comprobante
	 */
	private void completarComprobanteACH(Registro datosEntrada, boolean isEfectuada, ComprobanteDTO comprobante) {
		try {
			comprobante.setFecha(obtenerDate7x24(datosEntrada.getFechaEjecucion()));
		} catch (ParseException e) {
			comprobante.setTieneError(true);
			return;
		}
		comprobante.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA);
		if (datosEntrada.getXmlEntrada().getDATOSENTRADA() == null) {
			completarComprobanteACHConXmlEntrada(datosEntrada.getXmlEntrada(), isEfectuada, comprobante);
		} else {
			completarComprobanteACHConDATOSEntrada(datosEntrada.getXmlEntrada().getDATOSENTRADA(), isEfectuada,
					comprobante);
		}
		comprobante.setDetalle(new DetalleComprobanteDTO());
	}

	/**
	 * Completar comprobante ACH con DATOS entrada.
	 *
	 * @param datosentrada
	 *            the datosentrada
	 * @param isEfectuada
	 *            the is efectuada
	 * @param comprobante
	 *            the comprobante
	 */
	private void completarComprobanteACHConDATOSEntrada(DATOSENTRADA datosentrada, boolean isEfectuada,
			ComprobanteDTO comprobante) {
		if (isEfectuada) {
			String moneda = obtenerMoneda(datosentrada.getTipoCtaDebito());
			setImportePesosDolarSinComa(moneda, datosentrada.getImporteDebito(), comprobante);
			setearDestinatarioByString(datosentrada.getNombreCtaCredito(), comprobante);
			obtenerDescripcion(datosentrada.getTipoCtaDebito(), moneda, comprobante);
			setNumeroCuentaPesoDolarParseado(datosentrada.getSucCtaDebito(), datosentrada.getNroCtaDebito(),
					comprobante);
		} else {
			String moneda = obtenerMoneda(datosentrada.getTipoCtaDebito());
			setImportePesosDolarSinComa(moneda, datosentrada.getImporteTransferencia(), comprobante);
			setearDestinatarioByString(datosentrada.getNombreCtaCredito(), comprobante);
			obtenerDescripcion(datosentrada.getTipoCtaDebito(), moneda, comprobante);
			setNumeroCuentaPesoDolarParseado(datosentrada.getSucCtaDebito(), datosentrada.getNroCtaDebito(),
					comprobante);
		}

	}

	/**
	 * Completar comprobante ACH con xml entrada.
	 *
	 * @param xmlEntrada
	 *            the xml entrada
	 * @param isEfectuada
	 *            the is efectuada
	 * @param comprobante
	 *            the comprobante
	 */
	private void completarComprobanteACHConXmlEntrada(XMLEntrada xmlEntrada, boolean isEfectuada,
			ComprobanteDTO comprobante) {
		if (isEfectuada) {
			String moneda = obtenerMoneda(xmlEntrada.getTipoCtaDebito());
			setImportePesosDolarSinComa(moneda, xmlEntrada.getImporteDebito(), comprobante);
			setearDestinatarioByString(xmlEntrada.getNombreCtaCredito(), comprobante);
			obtenerDescripcion(xmlEntrada.getTipoCtaDebito(), moneda, comprobante);
			setNumeroCuentaPesoDolarParseado(xmlEntrada.getSucCtaDebito(), xmlEntrada.getNroCtaDebito(), comprobante);
		} else {
			String moneda = obtenerMoneda(xmlEntrada.getTipoCtaDebito());
			setImportePesosDolarSinComa(moneda, xmlEntrada.getImporteTransferencia(), comprobante);
			setearDestinatarioByString(xmlEntrada.getNombreCtaCredito(), comprobante);
			obtenerDescripcion(xmlEntrada.getTipoCtaDebito(), moneda, comprobante);
			setNumeroCuentaPesoDolarParseado(xmlEntrada.getSucCtaDebito(), xmlEntrada.getNroCtaDebito(), comprobante);
		}

	}

	/**
	 * Completar comprobante rio.
	 *
	 * @param datosEntrada
	 *            the datos entrada
	 * @param isEfectuada
	 *            the is efectuada
	 * @param comprobante
	 *            the comprobante
	 */
	private void completarComprobanteRio(Registro datosEntrada, boolean isEfectuada, ComprobanteDTO comprobante) {
		try {
			comprobante.setFecha(obtenerDate7x24(datosEntrada.getFechaEjecucion()));
		} catch (ParseException e) {
			comprobante.setTieneError(true);
			return;
		}
		comprobante.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA);
		comprobante.setHistorial(HistorialComprobanteEnum.TRANSFERENCIA_RIORIO);
		if (datosEntrada.getXmlEntrada().getDATOSENTRADA() == null) {
			completarComprobanteRioConXmlEntrada(datosEntrada.getXmlEntrada(), datosEntrada.getXmlResultado(),
					isEfectuada, comprobante);
		} else {
			completarComprobanteRioConDATOSEntrada(datosEntrada.getXmlEntrada().getDATOSENTRADA(),
					datosEntrada.getXmlResultado(), isEfectuada, comprobante,
					datosEntrada.getXmlEntrada().getDatosAdicionales().getDatosMensAvisos().getTitularDebito());
		}
		if (datosEntrada.getXmlEntrada() != null && datosEntrada.getXmlEntrada().getDatosAdicionales() != null
				&& datosEntrada.getXmlEntrada().getDatosAdicionales().getDatosMensAvisos() != null) {
			((DetalleComprobanteTransferenciaProgramadaRioRioDTO) comprobante.getDetalle())
					.setConcepto(datosEntrada.getXmlEntrada().getDatosAdicionales().getDatosMensAvisos().getConcepto());
		}
	}

	/**
	 * Completar comprobante rio con DATOS entrada.
	 *
	 * @param datosentrada
	 *            the datosentrada
	 * @param xmlResultado
	 *            the xml resultado
	 * @param isEfectuada
	 *            the is efectuada
	 * @param comprobante
	 *            the comprobante
	 * @param titular
	 *            the titular
	 */
	private void completarComprobanteRioConDATOSEntrada(DATOSENTRADA datosentrada, XMLResultado xmlResultado,
			boolean isEfectuada, ComprobanteDTO comprobante, String titular) {
		if (isEfectuada) {
			String moneda = obtenerMoneda(datosentrada.getTipoCtaDebito());
			setImportePesosDolarSinComa(moneda, xmlResultado.getImporteOrigen(), comprobante);
			setearDestinatarioByString(datosentrada.getNombreCtaCredito(), comprobante);
			obtenerDescripcion(datosentrada.getTipoCtaDebito(), moneda, comprobante);
			setNumeroCuentaPesoDolarParseado(datosentrada.getSucCtaDebito(), datosentrada.getNroCtaDebito(),
					comprobante);
		} else {
			String moneda = obtenerMoneda(datosentrada.getTipoCtaDebito());
			setearDestinatarioByString(datosentrada.getNombreCtaCredito(), comprobante);
			obtenerDescripcion(datosentrada.getTipoCtaDebito(), moneda, comprobante);
			setNumeroCuentaPesoDolarParseado(datosentrada.getSucCtaDebito(), datosentrada.getNroCtaDebito(),
					comprobante);
			setImportePesosDolarSinComa(moneda, datosentrada.getImporteDebito(), comprobante);
		}
		comprobante.setDetalle(obtenerDetalleTrasfProgramadasRioRio(datosentrada, isEfectuada, titular));
		if (isEfectuada) {
			comprobante.getDetalle().setNroComprobante(xmlResultado.getComprobanteBackEnd());
		}
	}

	/**
	 * Completar comprobante rio con xml entrada.
	 *
	 * @param xmlEntrada
	 *            the xml entrada
	 * @param xmlResultado
	 *            the xml resultado
	 * @param isEfectuada
	 *            the is efectuada
	 * @param comprobante
	 *            the comprobante
	 */
	private void completarComprobanteRioConXmlEntrada(XMLEntrada xmlEntrada, XMLResultado xmlResultado,
			boolean isEfectuada, ComprobanteDTO comprobante) {
		if (isEfectuada) {
			String moneda = obtenerMoneda(xmlEntrada.getTipoCtaDebito());
			setImportePesosDolarSinComa(moneda, xmlResultado.getImporteOrigen(), comprobante);
			setearDestinatarioByString(xmlEntrada.getNombreCtaCredito(), comprobante);
			obtenerDescripcion(xmlEntrada.getTipoCtaDebito(), moneda, comprobante);
			setNumeroCuentaPesoDolarParseado(xmlEntrada.getSucCtaDebito(), xmlEntrada.getNroCtaDebito(), comprobante);
		} else {
			String moneda = obtenerMoneda(xmlEntrada.getTipoCtaDebito());
			setearDestinatarioByString(xmlEntrada.getNombreCtaCredito(), comprobante);
			obtenerDescripcion(xmlEntrada.getTipoCtaDebito(), moneda, comprobante);
			setNumeroCuentaPesoDolarParseado(xmlEntrada.getSucCtaDebito(), xmlEntrada.getNroCtaDebito(), comprobante);
			setImportePesosDolarSinComa(moneda, xmlEntrada.getImporteDebito(), comprobante);
		}
		comprobante.setDetalle(obtenerDetalleTrasfProgramadasRioRio(xmlEntrada, isEfectuada));
		if (isEfectuada) {
			comprobante.getDetalle().setNroComprobante(xmlResultado.getComprobanteBackEnd());
		}
	}

	/**
	 * Sets the numero cuenta peso dolar parseado.
	 *
	 * @param sucursalCuentaOrigen
	 *            the sucursal cuenta origen
	 * @param numeroCuentaOrigen
	 *            the numero cuenta origen
	 * @param comprobante
	 *            the comprobante
	 */
	private void setNumeroCuentaPesoDolarParseado(String sucursalCuentaOrigen, String numeroCuentaOrigen,
			ComprobanteDTO comprobante) {
		if (comprobante.getTipoCtaMedioDePagoDolares() == null) {
			comprobante.setCtaMedioDePagoPesos(
					(new IdentificacionCuenta(sucursalCuentaOrigen, numeroCuentaOrigen)).toString());
		} else {
			comprobante.setCtaMedioDePagoDolares(
					(new IdentificacionCuenta(sucursalCuentaOrigen, numeroCuentaOrigen)).toString());
		}

	}

	/**
	 * Sets the importe pesos dolar sin coma.
	 *
	 * @param moneda
	 *            the moneda
	 * @param importeDebito
	 *            the importe debito
	 * @param comprobante
	 *            the comprobante
	 */
	private void setImportePesosDolarSinComa(String moneda, String importeDebito, ComprobanteDTO comprobante) {
		if ("$".equals(moneda)) {
			comprobante.setImportePesos(new BigDecimal(importeDebito));
		} else {
			comprobante.setImporteDolares(new BigDecimal(importeDebito));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#obtenerComprobantesRio(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente, java.util.Date,
	 * java.util.Date)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesRio(Cliente cliente, TransaccionDTO transaccion) {
		try {
			XMLResponseEntity response = sietePorVenticuatroV1DAO.ejecutar(crearRequestEntityBase(cliente, LLAMADO_RIO,
					ESTADO_EFECTUADA, transaccion.getFechaDesde(), transaccion.getFechaHasta()));
			if ("0".equals(response.getDATOSRESULTADO().getCodRet())) {
				return crearRespuestaComprobanteDTO(response, true, transaccion);
			}
		} catch (DAOException e) {
			LOGGER.error(ERROR + OBTENIENDO_COMPROBANTES_RIO + EFECTUADA + e);
		}
		return respuestaFactory.crearRespuestaError(new ArrayList<DatoItemMensaje>());
	}

	/**
	 * Obtener date 7 x 24.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	private Date obtenerDate7x24(String fecha) throws ParseException {
		return new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(fecha);
	}

	/**
	 * Crear respuesta comprobante DTO.
	 *
	 * @param response
	 *            the response
	 * @param isEfectuada
	 *            the is efectuada
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaComprobanteDTO(XMLResponseEntity response, boolean isEfectuada,
			TransaccionDTO transaccion) {
		return respuestaFactory.crearRespuestaOk(ComprobantesDTO.class,
				crearComprobantesDTO(isEfectuada, response, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#obtenerComprobantesRioAsync(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente, java.util.Date,
	 * java.util.Date)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesRioAsync(Cliente cliente, TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesRio(cliente, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#obtenerComprobantesOtrosBancos(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente, java.util.Date,
	 * java.util.Date)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesOtrosBancos(Cliente cliente, TransaccionDTO transaccion) {
		try {
			XMLResponseEntity response = sietePorVenticuatroV1DAO.ejecutar(crearRequestEntityBase(cliente, LLAMADO_OB,
					ESTADO_EFECTUADA, transaccion.getFechaDesde(), transaccion.getFechaHasta()));
			if ("0".equals(response.getDATOSRESULTADO().getCodRet())) {
				return crearRespuestaComprobanteDTO(response, true, transaccion);
			}
		} catch (DAOException e) {
			LOGGER.error(ERROR + OBTENIENDO_COMPROBANTES_OTROS_BANCOS + EFECTUADA + e);
		}
		return respuestaFactory.crearRespuestaError(new ArrayList<DatoItemMensaje>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#obtenerComprobantesOtrosBancosAsync(ar.
	 * com.santanderrio.obp.servicios.clientes.entities.Cliente, java.util.Date,
	 * java.util.Date)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesOtrosBancosAsync(Cliente cliente,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesOtrosBancos(cliente, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#obtenerComprobantesACH(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente, java.util.Date,
	 * java.util.Date)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesACH(Cliente cliente, TransaccionDTO transaccion) {
		try {
			XMLResponseEntity response = sietePorVenticuatroV1DAO.ejecutar(crearRequestEntityBase(cliente, LLAMADO_ACH,
					ESTADO_EFECTUADA, transaccion.getFechaDesde(), transaccion.getFechaHasta()));
			if ("0".equals(response.getDATOSRESULTADO().getCodRet())) {
				return crearRespuestaComprobanteDTO(response, true, transaccion);
			}
		} catch (DAOException e) {
			LOGGER.error(ERROR + OBTENIENDO_COMPROBANTES_ACH + EFECTUADA + e);
		}
		return respuestaFactory.crearRespuestaError(new ArrayList<DatoItemMensaje>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#obtenerComprobantesACHAsync(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente, java.util.Date,
	 * java.util.Date)
	 */
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesACHAsync(Cliente cliente, TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesACH(cliente, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#
	 * obtenerComprobantesPagoHaberesYHonorarios(ar.com.santanderrio.obp.
	 * servicios.clientes.entities.Cliente, java.util.Date, java.util.Date)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPagoHaberesYHonorarios(Cliente cliente,
			TransaccionDTO transaccion) {
		try {
			XMLResponseEntity response = sietePorVenticuatroV1DAO.ejecutar(crearRequestEntityBase(cliente,
					LLAMADO_PAGHABON, ESTADO_EFECTUADA, transaccion.getFechaDesde(), transaccion.getFechaHasta()));
			if ("0".equals(response.getDATOSRESULTADO().getCodRet())) {
				return crearRespuestaComprobanteDTO(response, true, transaccion);
			}
		} catch (DAOException e) {
			LOGGER.error(ERROR + OBTENIENDO_COMPROBANTES_PAGHABON + EFECTUADA + e);
		}
		return respuestaFactory.crearRespuestaError(new ArrayList<DatoItemMensaje>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#
	 * obtenerComprobantesPagoHaberesYHonorariosAsync(ar.com.santanderrio.obp.
	 * servicios.clientes.entities.Cliente, java.util.Date, java.util.Date)
	 */
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPagoHaberesYHonorariosAsync(Cliente cliente,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(
				obtenerComprobantesPagoHaberesYHonorarios(cliente, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#obtenerComprobantesRioNoEfec(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente, java.util.Date,
	 * java.util.Date)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesRioNoEfec(Cliente cliente, TransaccionDTO transaccion) {
		try {

			XMLRequestEntity request = crearRequestEntityBase(cliente, LLAMADO_RIO, ESTADO_NO_EFECTUADA,
					transaccion.getFechaDesde(), transaccion.getFechaHasta());
			request.getDATOSENTRADA().getParametros().setEjecutadoPorAgendamiento("S");
			XMLResponseEntity response = sietePorVenticuatroV1DAO.ejecutar(request);
			if ("0".equals(response.getDATOSRESULTADO().getCodRet())) {
				return crearRespuestaComprobanteDTO(response, false, transaccion);
			}
		} catch (DAOException e) {
			LOGGER.error(ERROR + OBTENIENDO_COMPROBANTES_RIO + NO_EFECTUADA + e);
		}
		return respuestaFactory.crearRespuestaError(new ArrayList<DatoItemMensaje>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#obtenerComprobantesRioNoEfecAsync(ar.
	 * com.santanderrio.obp.servicios.clientes.entities.Cliente, java.util.Date,
	 * java.util.Date)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesRioNoEfecAsync(Cliente cliente,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesRioNoEfec(cliente, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#obtenerComprobantesOtrosBancosNoEfec(ar
	 * .com.santanderrio.obp.servicios.clientes.entities.Cliente, java.util.Date,
	 * java.util.Date)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesOtrosBancosNoEfec(Cliente cliente,
			TransaccionDTO transaccion) {
		try {
			XMLRequestEntity request = crearRequestEntityBase(cliente, LLAMADO_OB, ESTADO_NO_EFECTUADA,
					transaccion.getFechaDesde(), transaccion.getFechaHasta());
			request.getDATOSENTRADA().getParametros().setEjecutadoPorAgendamiento("S");
			XMLResponseEntity response = sietePorVenticuatroV1DAO.ejecutar(request);
			if ("0".equals(response.getDATOSRESULTADO().getCodRet())) {
				return crearRespuestaComprobanteDTO(response, false, transaccion);
			}
		} catch (DAOException e) {
			LOGGER.error(ERROR + OBTENIENDO_COMPROBANTES_OTROS_BANCOS + NO_EFECTUADA + e);
		}
		return respuestaFactory.crearRespuestaError(new ArrayList<DatoItemMensaje>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#
	 * obtenerComprobantesOtrosBancosNoEfecAsync(ar.com.santanderrio.obp.
	 * servicios.clientes.entities.Cliente, java.util.Date, java.util.Date)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesOtrosBancosNoEfecAsync(Cliente cliente,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesOtrosBancosNoEfec(cliente, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#obtenerComprobantesACHNoEfec(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente, java.util.Date,
	 * java.util.Date)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesACHNoEfec(Cliente cliente, TransaccionDTO transaccion) {
		try {
			XMLRequestEntity request = crearRequestEntityBase(cliente, LLAMADO_ACH, ESTADO_NO_EFECTUADA,
					transaccion.getFechaDesde(), transaccion.getFechaHasta());
			request.getDATOSENTRADA().getParametros().setEjecutadoPorAgendamiento("S");
			Future<XMLResponseEntity> thread = sietePorVenticuatroV1DAO.ejecutarAsync(request);
			sleepWhileExecute(thread);
			XMLResponseEntity response = obtenerRespuestaDeAsync(thread);
			if ("0".equals(response.getDATOSRESULTADO().getCodRet())) {
				return crearRespuestaComprobanteDTO(response, false, transaccion);
			}
		} catch (DAOException e) {
			LOGGER.error(ERROR + OBTENIENDO_COMPROBANTES_ACH + NO_EFECTUADA + e);
		}
		return respuestaFactory.crearRespuestaError(new ArrayList<DatoItemMensaje>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#obtenerComprobantesACHNoEfecAsync(ar.
	 * com.santanderrio.obp.servicios.clientes.entities.Cliente, java.util.Date,
	 * java.util.Date)
	 */
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesACHNoEfecAsync(Cliente cliente,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesACHNoEfec(cliente, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#
	 * obtenerComprobantesPagoHaberesYHonorariosNoEfec(ar.com.santanderrio.obp.
	 * servicios.clientes.entities.Cliente, java.util.Date, java.util.Date)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPagoHaberesYHonorariosNoEfec(Cliente cliente,
			TransaccionDTO transaccion) {
		try {
			XMLRequestEntity request = crearRequestEntityBase(cliente, LLAMADO_PAGHABON, ESTADO_NO_EFECTUADA,
					transaccion.getFechaDesde(), transaccion.getFechaHasta());
			request.getDATOSENTRADA().getParametros().setEjecutadoPorAgendamiento("S");
			Future<XMLResponseEntity> thread = sietePorVenticuatroV1DAO.ejecutarAsync(request);
			sleepWhileExecute(thread);
			XMLResponseEntity response = obtenerRespuestaDeAsync(thread);
			if ("0".equals(response.getDATOSRESULTADO().getCodRet())) {
				return crearRespuestaComprobanteDTO(response, false, transaccion);
			}
		} catch (DAOException e) {
			LOGGER.error(ERROR + OBTENIENDO_COMPROBANTES_PAGHABON + NO_EFECTUADA + e);
		}
		return respuestaFactory.crearRespuestaError(new ArrayList<DatoItemMensaje>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#
	 * obtenerComprobantesPagoHaberesYHonorariosNoEfecAsync(ar.com.santanderrio.
	 * obp.servicios.clientes.entities.Cliente, java.util.Date, java.util.Date)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPagoHaberesYHonorariosNoEfecAsync(Cliente cliente,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(
				obtenerComprobantesPagoHaberesYHonorariosNoEfec(cliente, transaccion));
	}

	/**
	 * Sleep while execute.
	 *
	 * @param res
	 *            the res
	 */
	private void sleepWhileExecute(Future<XMLResponseEntity> res) {
		while (!res.isDone()) {
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.error(ERROR + e);
			}
		}
	}

	/**
	 * Obtener respuesta de async.
	 *
	 * @param thread
	 *            the thread
	 * @return the respuesta
	 */
	private XMLResponseEntity obtenerRespuestaDeAsync(Future<XMLResponseEntity> thread) {
		if (thread != null) {
			try {
				return thread.get();
			} catch (ExecutionException e) {
				if (e.getCause() instanceof DAOException) {
					LOGGER.error("Dao Exception:" + e);
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.error(ERROR + e);
			}
		}
		XMLResponseEntity response = new XMLResponseEntity();
		DATOSRESULTADO value = new DATOSRESULTADO();
		value.setCodRet("1");
		response.setDATOSRESULTADO(value);
		return response;
	}

	/**
	 * Obtener detalle trasf programadas rio rio.
	 *
	 * @param xmlEntrada
	 *            the xml entrada
	 * @param isEfectuada
	 *            the is efectuada
	 * @return the detalle comprobante transferencia programada DTO
	 */
	private DetalleComprobanteTransferenciaProgramadaDTO obtenerDetalleTrasfProgramadasRioRio(XMLEntrada xmlEntrada,
			boolean isEfectuada) {
		DetalleComprobanteTransferenciaProgramadaRioRioDTO detalle = new DetalleComprobanteTransferenciaProgramadaRioRioDTO();
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_RIO_RIO);
		detalle.setNroCuentaDestino(
				(new IdentificacionCuenta(xmlEntrada.getSucCtaCredito(), xmlEntrada.getNroCtaCredito())).toString());
		detalle.setTipoCuentaDestino(obtenerTipoCuenta(xmlEntrada.getTipoCtaCredito()));
		detalle.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
		detalle.setDesConcepto(returnIfNotEmpty(xmlEntrada.getDescripcionConcepto()));
		detalle.setAnotaciones(returnIfNotEmpty(xmlEntrada.getAnotacionesPersonales()));
		if ("N".equals(xmlEntrada.getIndTransfDiferida())) {
			detalle.setPlazoAcreditacion(PLAZO_ACREDITACION);
		} else {
			detalle.setPlazoAcreditacion(CUARENTAIOCHO_HORAS);
		}
		detalle.setMail(returnIfNotEmptySinCamelcase(xmlEntrada.getMailCredito()));
		if (detalle.getMail() == null) {
			detalle.setAvisoTransferencia("No");
		} else {
			detalle.setAvisoTransferencia("Si");
		}
		detalle.setInformacionAdicional(xmlEntrada.getTipoCtaCredito());
		if (isEfectuada) {
			detalle.setComentarios(returnIfNotEmpty(xmlEntrada.getComentarios()));
		} else {
			detalle.setEstado(RECHAZADO);
			detalle.setDestinatario(xmlEntrada.getTitularCredito());
		}
		detalle.setTituloComprobante(
				obtenerTituloArchivo(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA, isEfectuada));
		return detalle;
	}

	/**
	 * Completar rio.
	 *
	 * @param datosentrada
	 *            the datosentrada
	 * @param isEfectuada
	 *            the is efectuada
	 * @param titular
	 *            the titular
	 * @return the detalle comprobante transferencia programada DTO
	 */
	private DetalleComprobanteTransferenciaProgramadaDTO obtenerDetalleTrasfProgramadasRioRio(DATOSENTRADA datosentrada,
			boolean isEfectuada, String titular) {
		String titularDebito;
		if (datosentrada.getTitularCredito() != null) {
			titularDebito = datosentrada.getTitularCredito();
		} else {
			titularDebito = titular;
		}
		DetalleComprobanteTransferenciaProgramadaRioRioDTO detalle = new DetalleComprobanteTransferenciaProgramadaRioRioDTO();
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_RIO_RIO);
		detalle.setNroCuentaDestino(
				(new IdentificacionCuenta(datosentrada.getSucCtaCredito(), datosentrada.getNroCtaCredito()))
						.toString());
		detalle.setTipoCuentaDestino(obtenerTipoCuenta(datosentrada.getTipoCtaCredito()));
		detalle.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
		detalle.setDesConcepto(returnIfNotEmpty(datosentrada.getDescripcionConcepto()));
		detalle.setAnotaciones(returnIfNotEmpty(datosentrada.getAnotacionesPersonales()));
		if ("N".equals(datosentrada.getIndTransfDiferida())) {
			detalle.setPlazoAcreditacion(PLAZO_ACREDITACION);
		} else {
			detalle.setPlazoAcreditacion(CUARENTAIOCHO_HORAS);
		}
		detalle.setMail(returnIfNotEmptySinCamelcase(datosentrada.getMailCredito()));
		if (detalle.getMail() == null) {
			detalle.setAvisoTransferencia("No");
		} else {
			detalle.setAvisoTransferencia("Si");
		}
		detalle.setInformacionAdicional(datosentrada.getTipoCtaCredito());
		if (isEfectuada) {
			detalle.setComentarios(returnIfNotEmpty(datosentrada.getComentarios()));
		} else {
			detalle.setEstado(RECHAZADO);
			detalle.setDestinatario(titularDebito);
		}
		detalle.setTituloComprobante(
				obtenerTituloArchivo(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA, isEfectuada));
		return detalle;
	}

	/**
	 * Obtener detalle trasf programadas otros bancos.
	 *
	 * @param xmlEntrada
	 *            the xml entrada
	 * @param isEfectuada
	 *            the is efectuada
	 * @return the detalle comprobante transferencia programada otros bancos DTO
	 */
	private DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO obtenerDetalleTrasfProgramadasOtrosBancos(
			XMLEntrada xmlEntrada, boolean isEfectuada) {
		DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO detalle = new DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO();
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_OTROS_BANCOS);
		detalle.setNroCuentaDestino(xmlEntrada.getCbu());
		detalle.setDescCuenta(conseguirBancoOB(xmlEntrada.getCaracteristicaCtaCredito()));
		if (xmlEntrada.getBancoDestino() != null) {
			detalle.setBanco(returnIfNotEmpty(xmlEntrada.getBancoDestino()).trim());
		}
		detalle.setCaractCuenta(xmlEntrada.getNombreCtaCredito());
		CuitDTO cuit = new CuitDTO(CuitEnum.CUIT_DESTINATARIO, parsearCuitCuil(xmlEntrada.getCuit1()));
		detalle.setCuit(cuit);
		detalle.setPlazoAcreditacion(getPlazoAcreditacionOB(xmlEntrada.getPlazoAcreditacion()));
		setearConceptoEInfoAdicional(xmlEntrada.getReferenciaUnivoca(), detalle);
		detalle.setAnotaciones(returnIfNotEmpty(xmlEntrada.getAnotacionesPersonales()));
		if (detalle.getMail() != null)
			detalle.setComentarios(returnIfNotEmpty(xmlEntrada.getComentario()));
		if (detalle.getMail() == null) {
			detalle.setAvisoTransferencia("No");
		} else {
			detalle.setAvisoTransferencia("Si");
		}
		if (isEfectuada) {
			detalle.setNroComprobante(xmlEntrada.getComprobanteBackEnd());
		} else {
			detalle.setEstado(RECHAZADO);
		}
		detalle.setTituloComprobante(
				obtenerTituloArchivo(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA, isEfectuada));
		return detalle;
	}

	/**
	 * Completar OB.
	 *
	 * @param datosentrada
	 *            the datosentrada
	 * @param isEfectuada
	 *            the is efectuada
	 * @return the detalle comprobante transferencia programada otros bancos DTO
	 */
	private DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO obtenerDetalleTrasfProgramadasOtrosBancos(
			DATOSENTRADA datosentrada, boolean isEfectuada) {
		DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO detalle = new DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO();
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_OTROS_BANCOS);
		detalle.setNroCuentaDestino(datosentrada.getCbu());
		detalle.setDescCuenta(conseguirBancoOB(datosentrada.getCaracteristicaCtaCredito()));
		if (datosentrada.getBancoDestino() != null) {
			detalle.setBanco(returnIfNotEmpty(datosentrada.getBancoDestino()).trim());
		}
		detalle.setCaractCuenta(datosentrada.getNombreCtaCredito());
		CuitDTO cuit = new CuitDTO(CuitEnum.CUIT_DESTINATARIO, parsearCuitCuil(datosentrada.getCuit1()));
		detalle.setCuit(cuit);
		detalle.setPlazoAcreditacion(getPlazoAcreditacionOB(datosentrada.getPlazoAcreditacion()));
		setearConceptoEInfoAdicional(datosentrada.getReferenciaUnivoca(), detalle);
		detalle.setMail(returnIfNotEmptySinCamelcase(datosentrada.getMailCredito()));
		if (detalle.getMail() != null) {
			detalle.setComentarios(returnIfNotEmpty(datosentrada.getComentario()));
		}
		if (detalle.getMail() == null) {
			detalle.setAvisoTransferencia("No");
		} else {
			detalle.setAvisoTransferencia("Si");
		}
		if (isEfectuada) {
			detalle.setNroComprobante(datosentrada.getComprobanteBackEnd());
		} else {
			detalle.setEstado(RECHAZADO);
		}
		detalle.setTituloComprobante(
				obtenerTituloArchivo(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA, isEfectuada));
		return detalle;
	}

	/**
	 * Obtener detalle trasf programadas PAGHABON.
	 *
	 * @param xmlEntrada
	 *            the xml entrada
	 * @param isEfectuada
	 *            the is efectuada
	 * @return the detalle comprobante transferencia DTO
	 */
	private DetalleComprobanteTransferenciaDTO obtenerDetalleTrasfProgramadasPAGHABON(XMLEntrada xmlEntrada,
			boolean isEfectuada) {
		DetalleComprobanteTransferenciaProgramadaPAGHABONDTO detalle = new DetalleComprobanteTransferenciaProgramadaPAGHABONDTO();
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_PAGHABON);
		detalle.setNroCuentaDestino(
				(new IdentificacionCuenta(xmlEntrada.getSucCtaCredito(), xmlEntrada.getNroCtaCredito())).toString());
		detalle.setTipoCuentaDestino(obtenerTipoCuenta(xmlEntrada.getTipoCtaCredito()));
		if (isEfectuada) {
			detalle.setNroComprobante(xmlEntrada.getComprobanteBackEnd());
		}
		if (xmlEntrada.getDatosAdicionales() != null && xmlEntrada.getDatosAdicionales().getDatosSueldos() != null
				&& xmlEntrada.getDatosAdicionales().getDatosSueldos().getDescripcionConcepto() != null) {
			detalle.setConcepto(xmlEntrada.getDatosAdicionales().getDatosSueldos().getDescripcionConcepto());
		}
		detalle.setTituloComprobante(obtenerTituloArchivo(TipoOperacionComprobanteEnum.PAGO_SUELDOS, isEfectuada));
		return detalle;
	}

	/**
	 * Completar PAGHABON.
	 *
	 * @param datosentrada
	 *            the datosentrada
	 * @param isEfectuada
	 *            the is efectuada
	 * @return the detalle comprobante transferencia DTO
	 */
	private DetalleComprobanteTransferenciaDTO obtenerDetalleTrasfProgramadasPAGHABON(DATOSENTRADA datosentrada,
			boolean isEfectuada) {
		DetalleComprobanteTransferenciaProgramadaPAGHABONDTO detalle = new DetalleComprobanteTransferenciaProgramadaPAGHABONDTO();
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_PAGHABON);
		detalle.setNroCuentaDestino(
				(new IdentificacionCuenta(datosentrada.getSucCtaCredito(), datosentrada.getNroCtaCredito()))
						.toString());
		detalle.setTipoCuentaDestino(obtenerTipoCuenta(datosentrada.getTipoCtaCredito()));
		if (isEfectuada) {
			detalle.setNroComprobante(datosentrada.getComprobanteBackEnd());
		}
		if (datosentrada.getDatosAdicionales() != null && datosentrada.getDatosAdicionales().getDatosSueldos() != null
				&& datosentrada.getDatosAdicionales().getDatosSueldos().getDescripcionConcepto() != null) {
			detalle.setConcepto(datosentrada.getDatosAdicionales().getDatosSueldos().getDescripcionConcepto());
		}
		detalle.setTituloComprobante(obtenerTituloArchivo(TipoOperacionComprobanteEnum.PAGO_SUELDOS, isEfectuada));
		return detalle;

	}

	/**
	 * Return if not empty.
	 *
	 * @param entrada
	 *            the entrada
	 * @return the string
	 */
	private String returnIfNotEmpty(String entrada) {
		if (!"".equals(entrada)) {
			return ISBANStringUtils.convertirStringToCamelcase(entrada);
		}
		return null;
	}

	/**
	 * Return if not empty.
	 *
	 * @param entrada
	 *            the entrada
	 * @return the string
	 */
	private String returnIfNotEmptySinCamelcase(String entrada) {
		if (!"".equals(entrada)) {
			return entrada;
		}
		return null;
	}

	/**
	 * Sets the ear concepto E info adicional.
	 *
	 * @param referenciaUnivoca
	 *            the new ear concepto E info adicional
	 * @param detalle
	 *            the detalle
	 */
	private void setearConceptoEInfoAdicional(String referenciaUnivoca,
			DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO detalle) {
		if (referenciaUnivoca != null && referenciaUnivoca.length() > 2 && !"Transferencia".equals(referenciaUnivoca)) {
			String primeros3 = referenciaUnivoca.substring(0, 3);
			detalle.setConcepto(obtenerConceptoDeProperty(primeros3));
			if (!StringUtils.isBlank(referenciaUnivoca.substring(3))) {
				detalle.setInformacionAdicional(
						ISBANStringUtils.convertirStringToCamelcase(referenciaUnivoca.substring(3)));
			}
			if (detalle.getConcepto() == null) {
				detalle.setConcepto(primeros3);
			}

		}
	}

	/**
	 * Obtener concepto de property.
	 *
	 * @param primeros3
	 *            the primeros 3
	 * @return the string
	 */
	private String obtenerConceptoDeProperty(String primeros3) {
		String conceptoRet = null;
		if ("ALQ".equals(primeros3)) {
			conceptoRet = alquiler;
		} else if ("CUO".equals(primeros3)) {
			conceptoRet = cuotas;
		} else if ("EXP".equals(primeros3)) {
			conceptoRet = expensas;
		} else if ("FAC".equals(primeros3)) {
			conceptoRet = factura;
		} else if ("PRE".equals(primeros3)) {
			conceptoRet = prestamo;
		} else if ("SEG".equals(primeros3)) {
			conceptoRet = seguro;
		} else if ("HON".equals(primeros3)) {
			conceptoRet = honorarios;
		} else if ("VAR".equals(primeros3)) {
			conceptoRet = varios;
		} else if ("HAB".equals(primeros3)) {
			conceptoRet = haberes;
		} else if ("OIN".equals(primeros3)) {
			conceptoRet = inmobiliaria;
		}
		return conceptoRet;
	}

	/**
	 * Gets the plazo acreditacion OB.
	 *
	 * @param plazoAcreditacion
	 *            the plazo acreditacion
	 * @return the plazo acreditacion OB
	 */
	private String getPlazoAcreditacionOB(String plazoAcreditacion) {
		if ("1".equals(plazoAcreditacion)) {
			return CUARENTAIOCHO_HORAS;
		}
		return PLAZO_ACREDITACION;
	}

	/**
	 * Parsear cuit cuil.
	 *
	 * @param idBeneficiario
	 *            the id beneficiario
	 * @return the string
	 */
	private String parsearCuitCuil(String idBeneficiario) {
		String lowstringCuit = idBeneficiario.substring(0, 2);
		String midstringCuit = idBeneficiario.substring(2, idBeneficiario.length() - 1);
		String highstringCuit = idBeneficiario.substring(idBeneficiario.length() - 1);
		return lowstringCuit + "-" + midstringCuit + "-" + highstringCuit;
	}

	/**
	 * Conseguir banco OB.
	 *
	 * @param caracteristicaCtaCredito
	 *            the caracteristica cta credito
	 * @return the string
	 */
	private String conseguirBancoOB(String caracteristicaCtaCredito) {
		if ("0".equals(caracteristicaCtaCredito) || "2".equals(caracteristicaCtaCredito)) {
			return "Otra cuenta de terceros";
		}
		return "Cuenta corriente teceros";
	}

	/**
	 * Crear request entity base.
	 *
	 * @param cliente
	 *            the cliente
	 * @param nombreGuardado
	 *            the nombre guardado
	 * @param estado
	 *            the estado
	 * @param fechaDesde
	 *            the fecha desde
	 * @param fechaHasta
	 *            the fecha hasta
	 * @return the XML request entity
	 */
	private XMLRequestEntity crearRequestEntityBase(Cliente cliente, String nombreGuardado, String estado,
			Date fechaDesde, Date fechaHasta) {
		XMLRequestEntity request = new XMLRequestEntity();
		CONFIG config = new CONFIG();
		config.setVersionXML("2.0.0");
		config.setEcoDatosEntrada("N");
		request.setCONFIG(config);
		META meta = new META();
		meta.setNombre("CONSULTA_TX_HIS");
		meta.setVersion("100");
		ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.META.Cliente clienteXML = new ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.META.Cliente();
		clienteXML.setFechaNac(cliente.getFechaNacimiento());
		clienteXML.setIdCliente(cliente.getDni());
		clienteXML.setNUP(cliente.getNup());
		clienteXML.setTipoId(cliente.getTipoDocumento());
		clienteXML.setTipoPersona("I");
		meta.setCliente(clienteXML);
		Usuario usuario = new Usuario();
		usuario.setUsuarioAtrib(ISBANStringUtils.repeat(" ", 2));
		usuario.setUsuarioId(cliente.getUsuarioRacf());
		usuario.setUsuarioPwd(cliente.getPasswordRacf());
		usuario.setUsuarioTipo("03");
		meta.setUsuario(usuario);
		meta.setIndAuten("0");
		meta.setIdSesionCnt(ISBANStringUtils.repeat(" ", 8));
		Canal canal = new Canal();
		canal.setCanalId("HTML");
		canal.setCanalTipo("04");
		canal.setCanalVersion("000");
		meta.setCanal(canal);
		Subcanal subcanal = new Subcanal();
		subcanal.setSubcanalId("0001");
		subcanal.setSubcanalTipo("99");
		meta.setSubcanal(subcanal);
		meta.setModoEjecucion("I");
		request.setMETA(meta);
		ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.DATOSENTRADA datosEntrada = new ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.DATOSENTRADA();
		Parametros parametros = new Parametros();
		parametros.setNupGuardado(cliente.getNup());
		parametros.setNombreGuardado(nombreGuardado);
		parametros.setModoEjecucionGuardado("I,IA");
		parametros.setEstado(estado);
		parametros.setAccion("E");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (fechaDesde == null || fechaHasta == null) {
			Date date = new Date();
			parametros.setFechaEjecucionHasta(sdf.format(date));
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, -180);
			date = cal.getTime();
			parametros.setFechaEjecucionDesde(sdf.format(date));
		} else {
			parametros.setFechaEjecucionHasta(sdf.format(fechaHasta));
			parametros.setFechaEjecucionDesde(sdf.format(fechaDesde));
		}
		datosEntrada.setParametros(parametros);
		request.setDATOSENTRADA(datosEntrada);
		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesSietePorVenticuatroBO#
	 * obtenerComprobantesPagoHaberesYHonorariosUnificadosAsync(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPagoHaberesYHonorariosUnificadosAsync(Cliente cliente,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(
				obtenerComprobantesPagoHaberesYHonorariosUnificados(cliente, transaccion));
	}

	/**
	 * Obtener comprobantes pago haberes Y honorarios unificados.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPagoHaberesYHonorariosUnificados(Cliente cliente,
			TransaccionDTO transaccion) {
		List<Future<XMLResponseEntity>> listaFuture = new ArrayList<Future<XMLResponseEntity>>();
		List<Future<Respuesta<ComprobantesDTO>>> listaCompraVenta = new ArrayList<Future<Respuesta<ComprobantesDTO>>>();
		listaFuture.add(obtenerRequestEntityResponse(cliente, transaccion, LLAMADO_PAGHABON, ESTADO_EFECTUADA));
		listaFuture.add(obtenerRequestEntityResponse(cliente, transaccion, LLAMADO_PAGHABON, ESTADO_NO_EFECTUADA));
		esperarHastaFinDeListaDeHilos(listaFuture, listaCompraVenta);
		XMLResponseEntity response = obtenerRespuestaDeAsync(listaFuture.get(0));
		XMLResponseEntity response2 = obtenerRespuestaDeAsync(listaFuture.get(1));
		return obtenerRespuestaUnificada(response, response2, transaccion);
	}

	/**
	 * Obtener respuesta unificada.
	 *
	 * @param response
	 *            the response
	 * @param response2
	 *            the response 2
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> obtenerRespuestaUnificada(XMLResponseEntity response,
			XMLResponseEntity response2, TransaccionDTO transaccion) {
		Respuesta<ComprobantesDTO> res = null;
		Respuesta<ComprobantesDTO> res2 = null;
		int cantidadConError = 2;
		if ("0".equals(response.getDATOSRESULTADO().getCodRet())) {
			res = crearRespuestaComprobanteDTO(response, true, transaccion);
			cantidadConError--;
		}
		if ("0".equals(response2.getDATOSRESULTADO().getCodRet())) {
			res2 = crearRespuestaComprobanteDTO(response2, false, transaccion);
			cantidadConError--;
		}
		ComprobantesDTO comprobantes = null;
		if (cantidadConError != 2) {
			if (res != null) {
				comprobantes = res.getRespuesta();
			}
			if (comprobantes != null && res2 != null && res2.getRespuesta() != null) {
				comprobantes.getComprobantes().addAll(res2.getRespuesta().getComprobantes());
			} else if (res2 != null) {
				comprobantes = res2.getRespuesta();
			}
		}
		return crearRespuestaConComprobantes(comprobantes, cantidadConError, 2);
	}

	/**
	 * Obtener request entity response.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @param tipoLlamado
	 *            the tipo llamado
	 * @param tipoEstado
	 *            the tipo estado
	 * @return the future
	 */
	private Future<XMLResponseEntity> obtenerRequestEntityResponse(Cliente cliente, TransaccionDTO transaccion,
			String tipoLlamado, String tipoEstado) {
		try {
			return sietePorVenticuatroV1DAO.ejecutarAsync(crearRequestEntityBase(cliente, tipoLlamado, tipoEstado,
					transaccion.getFechaDesde(), transaccion.getFechaHasta()));
		} catch (DAOException e) {
			LOGGER.error("Error en el llamado asincronico de listar comprobantes", e);
			return sietePorVenticuatroV1DAO.obtenerComprobanteErroneoAsync();
		}
	}

	/**
	 * Crear respuesta con comprobantes.
	 *
	 * @param comprobantes
	 *            the comprobantes
	 * @param cantidadConError
	 *            the cantidad con error
	 * @param cantidadservicios
	 *            the cantidadservicios
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaConComprobantes(ComprobantesDTO comprobantes, int cantidadConError,
			int cantidadservicios) {
		if (cantidadConError > 0) {
			if (cantidadConError == cantidadservicios) {
				return respuestaFactory.crearRespuestaError(new ArrayList<DatoItemMensaje>());
			} else {
				return respuestaFactory.crearRespuestaWarning(ComprobantesDTO.class, comprobantes,
						new ArrayList<ItemMensajeRespuesta>());
			}
		}
		return respuestaFactory.crearRespuestaOk(ComprobantesDTO.class, comprobantes);
	}

}
