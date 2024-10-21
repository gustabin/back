/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.Future;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import ar.com.santanderrio.obp.servicios.comprobantes.dto.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.WordUtils;
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
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.AltaComprobanteRequestBuilder;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.ValidationEntity;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.ComprobanteResponse;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.Fecha;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.Filtros;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.Comprobante;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteCompraVentaDolar;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteFinanciacionResumen;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCAfip;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCConDeuda;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCOpenBank;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCSinDeuda;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCVEP;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePagoCompraOk;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteScomp;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTarjRecargOk;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTransfInmRioOB;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTransfInmRioRio;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTrfcci;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTrfcta;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTrfcta7x24;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.Origen;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaOB;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaPMCAfip;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaRio;
import ar.com.santanderrio.obp.servicios.scomp.dao.ScompDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.transferencias.entities.PlazoAcreditacion;

/**
 * The Class TransferenciaInmediataBOImpl.
 *
 * @author dante.omar.olmedo
 */
@Component
public class ScompBOImpl extends ComprobantesBOImpl implements ScompBO {

	/** The scomp DAO. */
	@Autowired
	private ScompDAO scompDAO;

	/** The medios pago BO. */
	@Autowired
	private MediosPagoBO mediosPagoBO;

	/** The medios pago DAO. */
	@Autowired
	private BuscadorEmpresaDAO buscadorMediosPagoDAO;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ScompBOImpl.class);

	/** The Constant OBTENIENDO_COMPROBANTES_RIO. */
	private static final String OBTENIENDO_COMPROBANTES_RIO = "Obteniendo Comprobantes Scomp_Rio";

	/** The Constant OBTENIENDO_COMPROBANTES_OTROS_BANCOS. */
	private static final String OBTENIENDO_COMPROBANTES_OTROS_BANCOS = "Obteniendo Comprobantes Scomp_Otros Bancos";

	/** The Constant MSJ_ERROR_INESPERADO_COMPROBANTES_OTROS_COMPROBANTES. */
	private static final String MSJ_ERROR_INESPERADO_COMPROBANTES_OTROS_COMPROBANTES = "Error scomp comprobantes Otros Comprobantes: {}";

	/** The paginado desktop. */
	@Value("${SCOMP.FECHA.INICIO}")
	private String fechaMinima;

	/** The Constant PLAZO_ACREDITACION. */
	private static final String PLAZO_ACREDITACION = "Inmediata";

	/** The Constant PLAZO_ACREDITACION_A_TERCEROS. */
	private static final String PLAZO_ACREDITACION_A_TERCEROS = "En el día, según la cuenta de destino";

	/** The Constant CUENTA_DE_TERCEROS. */
	private static final String CUENTA_DE_TERCEROS = "Otra cuenta de terceros";

	/** The Constant PLAZO_ACREDITACION_2. */
	private static final String PLAZO_ACREDITACION_2 = "48 horas hábiles";

	/** The Constant IMPORTE_PESOS_DEBITO. */
	private static final String IMPORTE_PESOS_DEBITO = "Importe en pesos debitado";

	/** The Constant IMPORTE_DOLARES_DEBITO . */
	private static final String IMPORTE_DOLARES_DEBITO = "Importe en dólares debitado";

	/** The Constant IMPORTE_PESOS_CREDITO. */
	private static final String IMPORTE_PESOS_CREDITO = "Importe en pesos acreditado";

	/** The Constant IMPORTE_DOLARES_CREDITO. */
	private static final String IMPORTE_DOLARES_CREDITO = "Importe en dólares acreditado";

	/** The Constant CERO_CERO. */
	private static final String CERO_CERO = "00";

	/** The Constant CERO_UNO. */
	private static final String CERO_UNO = "01";

	/** The Constant CERO_NUEVE. */
	private static final String CERO_NUEVE = "09";

	private static final String HORA_MINUTO_SEGUNDO = "HHmmss";

	/**
	 * Crea el objeto filtro que contiene el intervalo de fechas en el cual traer
	 * comprobante, el nup del cliente , tipo y subtipo de operacion (lo que indica
	 * que servicio se llama) y si lleva estado operacion o no.
	 *
	 * @param nup
	 *            the nup
	 * @param fechaDesde
	 *            the fecha desde
	 * @param fechaHasta
	 *            the fecha hasta
	 * @param tipoComprobante
	 *            the tipo comprobante
	 * @param subTipoComprobante
	 *            the sub tipo comprobante
	 * @param tieneEstadoOper
	 *            the tiene estado oper
	 * @return the filtros
	 */
	private Filtros obtenerFiltro(String nup, Date fechaDesde, Date fechaHasta, String tipoComprobante,
			String subTipoComprobante, boolean tieneEstadoOper) {
		Filtros filtro = new Filtros();
		filtro.setNup(nup);
		Fecha fecha = new Fecha();
		if (fechaDesde == null && fechaHasta == null) {
			Calendar gcDate = GregorianCalendar.getInstance();
			gcDate.setTimeZone(TimeZone.getTimeZone("UTC-3"));

			try {
				XMLGregorianCalendar fechaHastaXML = fecha.getXmlGregorianCalendar(gcDate.getTime()).normalize();
				fechaHastaXML.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
				gcDate.add(GregorianCalendar.MONTH, -6);
				fecha.setHasta(fechaHastaXML);
			} catch (DatatypeConfigurationException e) {
				LOGGER.error("Error al parsear la fecha:" + e);
			}

			try {
				XMLGregorianCalendar fechaDesdeXML = fecha.getXmlGregorianCalendar(gcDate.getTime()).normalize();
				fechaDesdeXML.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
				fecha.setDesde(fechaDesdeXML);
			} catch (DatatypeConfigurationException e) {
				LOGGER.error("Error al parsear la fecha:" + e);
			}
		} else {
			try {
				XMLGregorianCalendar fechaDesdeXML = fecha.getXmlGregorianCalendar(fechaDesde).normalize();
				fechaDesdeXML.setTimezone(0);
				fechaDesdeXML.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
				fechaDesdeXML.setHour(0);
				fechaDesdeXML.setMinute(0);
				fechaDesdeXML.setSecond(0);
				fecha.setDesde(fechaDesdeXML);

				XMLGregorianCalendar fechaHastaXML = fecha.getXmlGregorianCalendar(fechaHasta).normalize();
				GregorianCalendar gc = fechaHastaXML.toGregorianCalendar();
				gc.add(Calendar.HOUR, -3);
				fechaHastaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
				fechaHastaXML.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
				fechaHastaXML.setHour(23);
				fechaHastaXML.setMinute(59);
				fechaHastaXML.setSecond(59);
				fecha.setHasta(fechaHastaXML);
			} catch (DatatypeConfigurationException e) {
				LOGGER.error("Error al parsear la fecha:" + e);
			}
		}
		filtro.setFecha(fecha);
		filtro.setTpoComp(tipoComprobante);
		filtro.setSubTpoComp(subTipoComprobante);
		if (tieneEstadoOper) {
			filtro.setEstadoOper("A");
		}
		return filtro;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * TransferenciaInmediataBO#obtenerComprobantesRioAsync(java.lang.String)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesRioAsync(String nup, TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesRio(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * TransferenciaInmediataBO#obtenerComprobantesRio(java.lang.String)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesRio(String nup, TransaccionDTO transaccion) {
		LOGGER.info(OBTENIENDO_COMPROBANTES_RIO);
		return obtenerComprobantesSinEstadoOper(nup, transaccion, "18", "0", 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * TransferenciaInmediataBO#obtenerComprobantesOtrosBancosAsync(java.lang.
	 * String)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesOtrosBancosAsync(String nup,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesOtrosBancos(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * TransferenciaInmediataBO#obtenerComprobantesOtrosBancos(java.lang.String)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesOtrosBancos(String nup, TransaccionDTO transaccion) {
		LOGGER.info(OBTENIENDO_COMPROBANTES_OTROS_BANCOS);
		return obtenerComprobantesSinEstadoOper(nup, transaccion, "18", "1", 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * TransferenciaInmediataBO#obtenerComprobantesOtrosComprobantesAsync(java.
	 * lang.String)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesOtrosComprobantesAsync(String nup,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesOtrosComprobantes(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * TransferenciaInmediataBO#obtenerComprobantesOtrosComprobantes(java.lang.
	 * String)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesOtrosComprobantes(String nup, TransaccionDTO transaccion) {
		return obtenerComprobantesSinEstadoOper(nup, transaccion, "18", "2", 2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * TransferenciaInmediataBO#obtenerComprobantesTarjetaRecargableAsync(java.
	 * lang.String)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesTarjetaRecargableAsync(String nup,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesTarjetaRecargable(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * TransferenciaInmediataBO#obtenerComprobantesTarjetaRecargable(java.lang.
	 * String)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesTarjetaRecargable(String nup, TransaccionDTO transaccion) {
		return obtenerComprobantesConEstadoOper(nup, transaccion, "8", "0", "200", "99", 3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * TransferenciaInmediataBO#obtenerComprobantesPagoDeComprasAsync(java.lang.
	 * String)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPagoDeComprasAsync(String nup,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesPagoDeCompras(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * TransferenciaInmediataBO#obtenerComprobantesPagoDeCompras(java.lang. String)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPagoDeCompras(String nup, TransaccionDTO transaccion) {
		return obtenerComprobantesConEstadoOper(nup, transaccion, "5", "0", "200", "99", 4);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * TransferenciaInmediataBO#obtenerComprobantesTarjetaRecargableAsync(java.5
	 * lang.String)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesRioTBancoAsync(String nup,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesRioTBanco(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesRioTBanco(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesRioTBanco(String nup, TransaccionDTO transaccion) {
		return obtenerComprobantesConEstadoOper(nup, transaccion, "2", "9", "200", "99", 5);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * TransferenciaInmediataBO#obtenerComprobantesTarjetaRecargableAsync(java.5
	 * lang.String)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesOBTBancoAsync(String nup, TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesOBTBanco(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesRioTBanco(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesOBTBanco(String nup, TransaccionDTO transaccion) {
		return obtenerComprobantesConEstadoOper(nup, transaccion, "2", "10", "200", "99", 6);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * TransferenciaInmediataBO#obtenerComprobantesTarjetaRecargableAsync(java.
	 * lang.String)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCConDeudaAsync(String nup,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesPMCConDeuda(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesPMCConDeuda(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPMCConDeuda(String nup, TransaccionDTO transaccion) {
		return obtenerComprobantesConEstadoOper(nup, transaccion, "2", "11", "200", "99", 8);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * TransferenciaInmediataBO#obtenerComprobantesTarjetaRecargableAsync(java.
	 * lang.String)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCSinDeudaAsync(String nup,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesPMCSinDeuda(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesPMCSinDeuda(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPMCSinDeuda(String nup, TransaccionDTO transaccion) {
		return obtenerComprobantesConEstadoOper(nup, transaccion, "2", "12", "200", "99", 7);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesPMCVEPAsync(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCVEPAsync(String nup, TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesPMCVEP(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesPMCVEP(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPMCVEP(String nup, TransaccionDTO transaccion) {
		return obtenerComprobantesConEstadoOper(nup, transaccion, "2", "13", "200", "99", 9);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesPMCAfipAsync(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCAfipAsync(String nup, TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesPMCAfip(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesPMCAfip(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPMCAfip(String nup, TransaccionDTO transaccion) {
		return obtenerComprobantesConEstadoOper(nup, transaccion, "2", "14", "200", "99", 10);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesCompraVentaDolarAsync(java.lang.String,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesCompraVentaDolarAsync(String nup,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesCompraVentaDolar(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesCompraVentaDolar(java.lang.String,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesCompraVentaDolar(String nup, TransaccionDTO transaccion) {
		return obtenerComprobantesConEstadoOper(nup, transaccion, "2", "31", "200", "99", 12);
	}

	/**
	 * Obtener comprobantes con estado oper.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @param tipoComprobante
	 *            the tipo comprobante
	 * @param subTipoComprobante
	 *            the sub tipo comprobante
	 * @param version
	 *            the version
	 * @param subcanal
	 *            the subcanal
	 * @param key
	 *            the key
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> obtenerComprobantesConEstadoOper(String nup, TransaccionDTO transaccion,
			String tipoComprobante, String subTipoComprobante, String version, String subcanal, int key) {
		Filtros filtros = obtenerFiltro(nup, transaccion.getFechaDesde(), transaccion.getFechaHasta(), tipoComprobante,
				subTipoComprobante, true);
		try {
			ComprobanteResponse res = scompDAO.listarComprobantes(filtros, version, subcanal);
			if (res.getCodRet() == 0) {
				return crearRespuestaComprobanteDTO(res, key, transaccion);
			} else {
				LOGGER.warn("Se invoca a scomp con tipo {}, subtipo {}, y se obtiene {}", filtros.getTpoComp(),
						res.getCodRet());
			}
		} catch (DAOException e) {
			LOGGER.debug("No se crea respuesta por error en el dao.");
		}
		return crearRespuestaErrorGenerico();
	}

	/**
	 * Crear respuesta comprobante DTO.
	 *
	 * @param res
	 *            the res
	 * @param tipoComprobante
	 *            the tipo comprobante
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaComprobanteDTO(ComprobanteResponse res, int tipoComprobante,
			TransaccionDTO transaccion) {
		if (!ValidationEntity.validate(res)) {
			return crearRespuestaErrorGenerico();
		}
		return respuestaFactory.crearRespuestaOk(ComprobantesDTO.class,
				crearComprobantesDTO(res, tipoComprobante, transaccion));
	}

	/**
	 * Obtener comprobantes sin estado oper.
	 *
	 * @param nup
	 *            the nup
	 * @param transaccion
	 *            the transaccion
	 * @param tipoComprobante
	 *            the tipo comprobante
	 * @param subTipoComprobante
	 *            the sub tipo comprobante
	 * @param key
	 *            the key
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> obtenerComprobantesSinEstadoOper(String nup, TransaccionDTO transaccion,
			String tipoComprobante, String subTipoComprobante, int key) {
		try {
			ComprobanteResponse res = scompDAO.listarComprobantes(obtenerFiltro(nup, transaccion.getFechaDesde(),
					transaccion.getFechaHasta(), tipoComprobante, subTipoComprobante, false), "200", "99");
			if (res.getCodRet() == 0) {
				return crearRespuestaComprobanteDTO(res, key, transaccion);
			}
		} catch (DAOException e) {
			LOGGER.error(MSJ_ERROR_INESPERADO_COMPROBANTES_OTROS_COMPROBANTES, e);
		}
		return crearRespuestaErrorGenerico();
	}

	/**
	 * Crear comprobantes DTO.
	 *
	 * @param res
	 *            the res
	 * @param tipoComprobante
	 *            the tipo comprobante
	 * @param transaccion
	 *            the transaccion
	 * @return the comprobantes DTO
	 */
	private ComprobantesDTO crearComprobantesDTO(ComprobanteResponse res, int tipoComprobante,
			TransaccionDTO transaccion) {
		List<ComprobanteScomp> comprobantesScomp = new LinkedList<ComprobanteScomp>();
		if (res.getRespuestaScomp().getComprobantes() != null) {
			comprobantesScomp.addAll(res.getRespuestaScomp().getComprobantes());
		}
		if (res.getRespuestaScomp().getComprobante() != null) {
			comprobantesScomp.addAll(res.getRespuestaScomp().getComprobante());
		}
		List<ComprobanteDTO> comprobantes = new LinkedList<ComprobanteDTO>();
		for (ComprobanteScomp comprobanteScomp : comprobantesScomp) {
			if (CollectionUtils.isNotEmpty(comprobanteScomp.getComprobanteList())) {
				Comprobante comprobante = comprobanteScomp.getComprobanteList().get(0);
				ComprobanteDTO dto = comprobantePorTipo(comprobante, tipoComprobante);
				dto.getDetalle().setEsScomp(true);
				comprobantes.add(dto);
			}
			if (comprobanteScomp.getComprobante() != null) {
				Comprobante comprobante = comprobanteScomp.getComprobante();
				ComprobanteDTO dto = comprobantePorTipo(comprobante, tipoComprobante);
				dto.getDetalle().setEsScomp(true);
				comprobantes.add(dto);
			}
		}

		if (transaccion.getImporteDesde() != null && transaccion.getImporteHasta() != null) {
			comprobantes = filtrarComprobantesPorImporte(comprobantes, transaccion.getImporteDesde(),
					transaccion.getImporteHasta());
		}
		return new ComprobantesDTO(comprobantes, false);
	}

	/**
	 * Transforma comprobante a comprobanteDTO.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @param tipoComprobante
	 *            the tipo comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO comprobantePorTipo(Comprobante comprobante, int tipoComprobante) {
		switch (tipoComprobante) {
		case 0:
			return obtenerComprobanteDTOParaTransferenciaInmediataRioRio((ComprobanteTrfcta) comprobante);// <<<HECHO>>>
		case 1:
			return obtenerComprobanteDTOParaTransferenciaInmediataOtrosBancos((ComprobanteTrfcci) comprobante);// <<<HECHO>>>
		case 2:
			return obtenerComprobanteDTOParaTransferenciaInmediataRioRio7x24((ComprobanteTrfcta7x24) comprobante);// <<<HECHO>>>
		case 3:
			return obtenerComprobanteDTOParaRecargaTarjetaRecargable((ComprobanteTarjRecargOk) comprobante); // <<<HECHO>>>
		case 4:
			return obtenerComprobanteDTOParaPagoDeCompras((ComprobantePagoCompraOk) comprobante);// <<<HECHO>>>
		case 5:
			return obtenerComprobanteDTOParaTransfInmediatasRioRioTBanco((ComprobanteTransfInmRioRio) comprobante);// <<<HECHO>>>
		case 6:
			return obtenerComprobanteDTOParaTransfInmediatasRioOBTBanco((ComprobanteTransfInmRioOB) comprobante);// <<<HECHO>>>
		case 7:
			return obtenerComprobanteDTOParaPMCSinDeudaCuenta((ComprobantePMCSinDeuda) comprobante); // <<<HECHO>>>
		case 8:
			return obtenerComprobanteDTOParaPMCConDeudaCuenta((ComprobantePMCConDeuda) comprobante); // <<<HECHO>>>
		case 9:
			return obtenerComprobanteDTOParaPMCVep((ComprobantePMCVEP) comprobante); // <<<HECHO>>>
		case 10:
			return obtenerComprobanteDTOParaPMCAfip((ComprobantePMCAfip) comprobante);// <<<HECHO>>>
		case 11:
			return obtenerComprobanteDTOParaFinanciacionResumen((ComprobanteFinanciacionResumen) comprobante);
		case 12:
			return obtenerComprobanteDTOParaCompraVentaDolares((ComprobanteCompraVentaDolar) comprobante);
		case 13:
			return obtenerComprobanteDTOParaPMCSinDeudaTC((ComprobantePMCSinDeuda) comprobante); // <<<HECHO>>>
		case 14:
			return obtenerComprobanteDTOParaPMCConDeudaTC((ComprobantePMCConDeuda) comprobante); // <<<HECHO>>>
		case 15:
			return obtenerComprobanteDTOParaPMCVEPTC((ComprobantePMCVEP) comprobante); // <<<HECHO>>>
		case 16:
			return obtenerComprobanteDTOParaPMCAfipTC((ComprobantePMCAfip) comprobante); // <<<HECHO>>>
		case 17:
			return obtenerComprobanteDTOParaPMCOpenBankCuenta((ComprobantePMCOpenBank) comprobante); // <<<HECHO>>>
		default:
			return null;
		}
	}

	/**
	 * Obtener comprobante DTO para compra venta dolares.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaCompraVentaDolares(ComprobanteCompraVentaDolar comprobante) {
		ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
		comprobanteDTO.setFecha(obtenerDate(comprobante.getFechaOper()));
		setearTipoOperacionImporteYTipoCuenta(comprobanteDTO, comprobante);
		comprobanteDTO.setHistorial(HistorialComprobanteEnum.COMPRA_VENTA);
		comprobanteDTO
				.setDestinatario(new IdentificacionCuenta(comprobante.getCuentaDestino().getSucursalCuentaDestino(),
						comprobante.getCuentaDestino().getNumeroCuentaDestino().replace("/", "")).toString());
		comprobanteDTO
				.setTipoCuentaDestino(TipoCuenta.fromCodigo(comprobante.getCuentaDestino().getTipoCuentaDestino()));
		comprobanteDTO.setDetalle(generarDetalleCompraVentaDolares(comprobante, comprobanteDTO));
		return comprobanteDTO;
	}

	/**
	 * Setear nro Y tipo cta origen.
	 *
	 * @param detalle
	 *            the detalle
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 */
	private void setearNroYTipoCtaOrigen(DetalleComprobanteCompraVentaDolarDTO detalle, ComprobanteDTO comprobanteDTO) {
		if (TipoOperacionComprobanteEnum.COMPRA_DOLARES.equals(comprobanteDTO.getTipoOperacion())) {
			detalle.setNroCuentaOrigen(comprobanteDTO.getCtaMedioDePagoPesos());
			detalle.setTipoCuentaOrigen(comprobanteDTO.getTipoCtaMedioDePagoPesos());
		} else {
			detalle.setNroCuentaOrigen(comprobanteDTO.getCtaMedioDePagoDolares());
			detalle.setTipoCuentaOrigen(comprobanteDTO.getTipoCtaDestinatario());
		}
	}

	/**
	 * Generar detalle compra venta dolares.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 * @return the detalle comprobante DTO
	 */
	private DetalleComprobanteDTO generarDetalleCompraVentaDolares(ComprobanteCompraVentaDolar comprobante,
			ComprobanteDTO comprobanteDTO) {
		DetalleComprobanteCompraVentaDolarDTO detalle = new DetalleComprobanteCompraVentaDolarDTO();
		setearNroYTipoCtaOrigen(detalle, comprobanteDTO);
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.COMPRAVENTA_DOLARES);
		detalle.setNroCuentaDestino(comprobanteDTO.getDestinatario());
		detalle.setTipoCuentaDestino(comprobanteDTO.getTipoCuentaDestino());
		detalle.setCotizacionAplicada(
				new BigDecimal(comprobante.getCotizacionAplicada().replace("u$s 1 = $", "").replace(",", ".")));
		generarLabelDebitoYCredito(detalle, comprobanteDTO.getTipoOperacion());
		detalle.setNroOperacion(comprobante.getNumeroOperacion());
		detalle.setFechaDePago(obtenerDate(obtenerFechaConHora(comprobante.getFechaOper(), comprobante.getHoraOper())));
		detalle.setNroComprobante(comprobante.getNroComprobante());
		detalle.setSaldoDebitado(new BigDecimal(comprobante.getImporteDebitado().split(" ")[1].replace(",", ".")));
		detalle.setHora(comprobante.getHoraOper().substring(0, 5));
		return detalle;
	}

	/**
	 * Generar label debito Y credito.
	 *
	 * @param detalle
	 *            the detalle
	 * @param tipoOperacion
	 *            the tipo operacion
	 */
	private void generarLabelDebitoYCredito(DetalleComprobanteCompraVentaDolarDTO detalle,
			TipoOperacionComprobanteEnum tipoOperacion) {
		if (TipoOperacionComprobanteEnum.COMPRA_DOLARES.equals(tipoOperacion)) {
			detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPRA_DOLARES.getDetalle());
			detalle.setLabelDebito(IMPORTE_PESOS_DEBITO);
			detalle.setLabelCredito(IMPORTE_DOLARES_CREDITO);
		} else {
			detalle.setTituloComprobante(CabeceraComprobantesEnum.VENTA_DOLARES.getDetalle());
			detalle.setLabelDebito(IMPORTE_DOLARES_DEBITO);
			detalle.setLabelCredito(IMPORTE_PESOS_CREDITO);
		}

	}

	/**
	 * Setear tipo operacion importe Y tipo cuenta.
	 *
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 * @param comprobante
	 *            the comprobante
	 */
	private void setearTipoOperacionImporteYTipoCuenta(ComprobanteDTO comprobanteDTO,
			ComprobanteCompraVentaDolar comprobante) {
		List<String> tipoCuentasDolar = new ArrayList<String>(Arrays.asList(CERO_CERO, CERO_UNO, CERO_NUEVE));
		if (tipoCuentasDolar.contains(comprobante.getCuentaOrigen().getTipoCuentaOrigen())) {
			comprobanteDTO.setTipoOperacion(TipoOperacionComprobanteEnum.COMPRA_DOLARES);
			comprobanteDTO.setImporteDolares(
					new BigDecimal(comprobante.getImporteAcreditado().split(" ")[1].replace(",", ".")));
			comprobanteDTO.setCtaMedioDePagoPesos(
					new IdentificacionCuenta(comprobante.getCuentaOrigen().getSucursalCuentaOrigen(),
							comprobante.getCuentaOrigen().getNumeroCuentaOrigen().replace("/", "")).toString());
			comprobanteDTO.setTipoCtaMedioDePagoPesos(
					TipoCuenta.fromCodigo(comprobante.getCuentaOrigen().getTipoCuentaOrigen()));
		} else {
			comprobanteDTO.setTipoOperacion(TipoOperacionComprobanteEnum.VENTA_DOLARES);
			comprobanteDTO.setTipoCtaMedioDePagoPesos(
					TipoCuenta.fromCodigo(comprobante.getCuentaOrigen().getTipoCuentaOrigen()));
			comprobanteDTO.setImportePesos(
					new BigDecimal(comprobante.getImporteAcreditado().split(" ")[1].replace(",", ".")));
			comprobanteDTO.setCtaMedioDePagoDolares(
					new IdentificacionCuenta(comprobante.getCuentaOrigen().getSucursalCuentaOrigen(),
							comprobante.getCuentaOrigen().getNumeroCuentaOrigen().replace("/", "")).toString());
			comprobanteDTO.setTipoCtaMedioDePagoDolares(
					TipoCuenta.fromCodigo(comprobante.getCuentaOrigen().getTipoCuentaOrigen()));
		}

	}

	/**
	 * Obtener comprobante DTO para transferencia inmediata rio rio.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaTransferenciaInmediataRioRio(ComprobanteTrfcta comprobante) {
		ComprobanteDTO comprobanteDTO = crearComprobanteDTOTransferenciaInmediata(false,
				obtenerDate(comprobante.getFechaOper()), TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA,
				comprobante.getOrigen(), comprobante.getTitularCtaCredito(), comprobante.getImporteDebito());

		comprobanteDTO.setDetalle(obtenerDetalleComprobanteScompTrasnferenciaInmediataRioRio(comprobante));
		comprobanteDTO.setHistorial(HistorialComprobanteEnum.TRANSFERENCIA_RIORIO);
		return comprobanteDTO;
	}

	/**
	 * Crear comprobante DTO transferencia inmediata.
	 *
	 * @param necesitaMoneda
	 *            the necesita moneda
	 * @param fecha
	 *            the fecha
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param origen
	 *            the origen
	 * @param titularDeCredito
	 *            the titular de credito
	 * @param importeDebito
	 *            the importe debito
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO crearComprobanteDTOTransferenciaInmediata(Boolean necesitaMoneda, Date fecha,
			TipoOperacionComprobanteEnum tipoOperacion, Origen origen, String titularDeCredito, String importeDebito) {
		ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
		comprobanteDTO.setNecesitaMoneda(necesitaMoneda);
		comprobanteDTO.setFecha(fecha);
		comprobanteDTO.setTipoOperacion(tipoOperacion);
		comprobanteDTO.setHistorial(HistorialComprobanteEnum.TRANSFERENCIA_RIORIO);
		String moneda = obtenerMoneda(origen.getTipoCuentaOrigen());
		setearImportePesosDolar(moneda, importeDebito, comprobanteDTO);
		setearDestinatarioByString(titularDeCredito, comprobanteDTO);
		setearNumeroCuentaPesoDolar(origen.getSucursalCuentaOrigen(), origen.getNumeroCuentaOrigen(), comprobanteDTO);
		obtenerDescripcion(origen.getTipoCuentaOrigen(), moneda, comprobanteDTO);
		return comprobanteDTO;
	}

	/**
	 * Obtener detalle comprobante scomp trasnferencia inmediata rio rio.
	 *
	 * @param comprobanteTrfcta
	 *            the comprobante trfcta
	 * @return the detalle comprobante DTO
	 */
	private DetalleComprobanteDTO obtenerDetalleComprobanteScompTrasnferenciaInmediataRioRio(
			ComprobanteTrfcta comprobanteTrfcta) {
		DetalleComprobanteTransferenciaInmediataRioRioDTO detalle = new DetalleComprobanteTransferenciaInmediataRioRioDTO();
		setearDatosGeneralesDeDetalleDTO(detalle, CabeceraComprobantesEnum.COMPROBANTE_TRANSFERENCIA.getDetalle(),
				TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO, comprobanteTrfcta.getOrigen(),
				comprobanteTrfcta.getNroComprobante());
		detalle.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
		detalle.setPlazoAcreditacion(PLAZO_ACREDITACION);
		detalle.setRecordatorio(comprobanteTrfcta.getRecordatorio());
		detalle.setNroCuentaDestino(obtenernumeroCuenta(comprobanteTrfcta.getDestino().getSucursalCuentaDestino(),
				comprobanteTrfcta.getDestino().getNumeroCuentaDestino()));
		detalle.setTipoCuentaDestino(obtenerTipoCuenta(comprobanteTrfcta.getDestino().getTipoCuentaDestino()));
		detalle.setDestinatario(comprobanteTrfcta.getTitularCtaCredito());
		detalle.setConcepto(comprobanteTrfcta.getConcepto());
		detalle.setDesConcepto(comprobanteTrfcta.getDescConcepto());
		return detalle;
	}

	/**
	 * Setear datos generales de detalle DTO.
	 *
	 * @param detalle
	 *            the detalle
	 * @param titulo
	 *            the titulo
	 * @param tipo
	 *            the tipo
	 * @param origen
	 *            the origen
	 * @param nroComprobante
	 *            the nro comprobante
	 */
	private void setearDatosGeneralesDeDetalleDTO(DetalleComprobanteDTO detalle, String titulo,
			TipoDetalleComprobanteEnum tipo, Origen origen, String nroComprobante) {
		detalle.setTituloComprobante(titulo);
		detalle.setTipoComprobante(tipo);
		detalle.setTipoCuentaOrigen(obtenerTipoCuenta(origen.getTipoCuentaOrigen()));
		detalle.setNroCuentaOrigen(
				obtenernumeroCuenta(origen.getSucursalCuentaOrigen(), origen.getNumeroCuentaOrigen()));
		if (StringUtils.isNotBlank(nroComprobante)) {
			detalle.setNroComprobante(ISBANStringUtils.eliminarCeros(nroComprobante));
		}
	}

	/**
	 * Obtener comprobante DTO para transferencia inmediata rio rio 7 x 24.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaTransferenciaInmediataRioRio7x24(
			ComprobanteTrfcta7x24 comprobante) {
		ComprobanteDTO comprobanteDTO = crearComprobanteDTOTransferenciaInmediata(false,
				obtenerDate(comprobante.getFechaOper()), TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA,
				comprobante.getOrigen(), comprobante.getTitularCtaCredito(), comprobante.getImporteDebito());
		comprobanteDTO.setDetalle(obtenerDetalleComprobanteScompTrasnferenciaInmediataRioRio7x24(comprobante));
		comprobanteDTO.setHistorial(HistorialComprobanteEnum.TRANSFERENCIA_RIORIO);
		return comprobanteDTO;
	}

	/**
	 * Obtener detalle comprobante. "scompRio"
	 *
	 * @param comprobanteTrfcta7x24
	 *            the comprobante trfcta 7 x 24
	 * @return the detalle comprobante DTO
	 */
	private DetalleComprobanteDTO obtenerDetalleComprobanteScompTrasnferenciaInmediataRioRio7x24(
			ComprobanteTrfcta7x24 comprobanteTrfcta7x24) {
		DetalleComprobanteTransferenciaInmediataRioRioDTO detalle = new DetalleComprobanteTransferenciaInmediataRioRioDTO();
		detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_TRANSFERENCIA.getDetalle());
		detalle.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
		detalle.setPlazoAcreditacion(PLAZO_ACREDITACION);
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO);
		detalle.setRecordatorio(comprobanteTrfcta7x24.getRecordatorio());
		detalle.setTipoCuentaOrigen(obtenerTipoCuenta(comprobanteTrfcta7x24.getOrigen().getTipoCuentaOrigen()));
		detalle.setNroCuentaOrigen(obtenernumeroCuenta(comprobanteTrfcta7x24.getOrigen().getSucursalCuentaOrigen(),
				comprobanteTrfcta7x24.getOrigen().getNumeroCuentaOrigen()));
		detalle.setTipoCuentaDestino(obtenerTipoCuenta(comprobanteTrfcta7x24.getDestino().getTipoCuentaDestino()));
		detalle.setNroCuentaDestino(obtenernumeroCuenta(comprobanteTrfcta7x24.getDestino().getSucursalCuentaDestino(),
				comprobanteTrfcta7x24.getDestino().getNumeroCuentaDestino()));
		detalle.setDestinatario(comprobanteTrfcta7x24.getTitularCtaCredito());
		detalle.setConcepto(comprobanteTrfcta7x24.getConcepto());
		detalle.setDesConcepto(comprobanteTrfcta7x24.getDescConcepto());
		detalle.setNroComprobante(ISBANStringUtils.eliminarCeros(comprobanteTrfcta7x24.getNroComprobante()));
		return detalle;
	}

	/**
	 * Obtener comprobante DTO para transferencia inmediata otros bancos.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaTransferenciaInmediataOtrosBancos(ComprobanteTrfcci comprobante) {
		ComprobanteDTO comprobanteDTO = crearComprobanteDTOTransferenciaInmediata(false,
				obtenerDate(obtenerFechaConHora(comprobante.getFechaOper(), comprobante.getHoraOper())),
				TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA, comprobante.getOrigen(),
				comprobante.getDestino().getTitularCtaDestino(), comprobante.getImporteDebito());

		setearNumeroCuentaPesoDolar(comprobante.getOrigen().getSucursalCuentaOrigen(),
				comprobante.getOrigen().getNumeroCuentaOrigen(), comprobanteDTO);
		comprobanteDTO.setDetalle(obtenerDetalleScompInmediataOtrosBancos(comprobante));
		comprobanteDTO.setHistorial(HistorialComprobanteEnum.TRANSFERENCIA_OTROSBANCOS);
		return comprobanteDTO;
	}

	/**
	 * Obtener detalle scomp inmediata otros bancos.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the detalle comprobante DTO
	 */
	private DetalleComprobanteDTO obtenerDetalleScompInmediataOtrosBancos(ComprobanteTrfcci comprobante) {
		DetalleComprobanteTransferenciaInmediataOtrosBancosDTO detalle = new DetalleComprobanteTransferenciaInmediataOtrosBancosDTO();
		setearDatosGeneralesDeDetalleDTO(detalle, CabeceraComprobantesEnum.COMPROBANTE_TRANSFERENCIA.getDetalle(),
				TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_OTROS_BANCOS, comprobante.getOrigen(),
				comprobante.getNroComprobante());

		detalle.setNroCuentaDestino(comprobante.getDestino().getCbu());
		detalle.setBanco(CUENTA_DE_TERCEROS);
		detalle.setDestinatario(comprobante.getDestino().getTitularCtaDestino());
		CuitDTO cuit = new CuitDTO(CuitEnum.CUIT_DESTINATARIO,
				parsearCuitCuil(comprobante.getDestino().getIdBeneficiario()));
		detalle.setCuit(cuit);
		if (PlazoAcreditacion.INMEDIATO.getCodigoTrfcci().equals(comprobante.getPlazoAcreditacion())
				|| PlazoAcreditacion.INMEDIATO_COELSA.getCodigoTrfcci().equals(comprobante.getPlazoAcreditacion())) {
			detalle.setPlazoAcreditacion(PLAZO_ACREDITACION_A_TERCEROS);
		} else {
			detalle.setPlazoAcreditacion(PLAZO_ACREDITACION_2);
		}
		detalle.setConcepto(comprobante.getConcepto());
		if (" ".equals(comprobante.getInformacionAdicional())) {
			detalle.setDesConcepto("VAR");
		} else {
			detalle.setDesConcepto(comprobante.getInformacionAdicional());
		}
		return detalle;
	}

	/**
	 * Obtener comprobante DTO para recarga tarjeta recargable.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaRecargaTarjetaRecargable(ComprobanteTarjRecargOk comprobante) {
		ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
		comprobanteDTO.setNecesitaMoneda(false);
		String ultimosCuatro = comprobante.getNroTarjeta().split("-")[3];
		comprobanteDTO.setDestinatario("Visa Recargable XXXX-" + ultimosCuatro);
		comprobanteDTO.setFecha(obtenerDate(comprobante.getFechaOper()));
		comprobanteDTO.setTipoOperacion(TipoOperacionComprobanteEnum.RECARGA_VISA);
		comprobanteDTO.setImportePesos(ISBANStringUtils.convertirABigDecimal(comprobante.getImporteDebitado()));
		obtenerDescripcion(comprobante.getTipoCtaDeb(), "$", comprobanteDTO);
		comprobanteDTO.setCtaMedioDePagoPesos(
				obtenerSucursalParseada(comprobante.getSucCtaDeb(), comprobante.getNroCtaDeb()));
		comprobanteDTO.setDetalle(obtenerDetalleComprobanteRecargaTarjetaRecargable(comprobante));
		return comprobanteDTO;
	}

	/**
	 * Obtener detalle comprobante recarga tarjeta recargable.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the detalle comprobante DTO
	 */
	private DetalleComprobanteDTO obtenerDetalleComprobanteRecargaTarjetaRecargable(
			ComprobanteTarjRecargOk comprobante) {
		DetalleComprobanteScompRecargaTarjetaRecargableDTO detalle = new DetalleComprobanteScompRecargaTarjetaRecargableDTO();
		detalle.setTituloComprobante(CabeceraComprobantesEnum.RECARGA_TARJETA_RECARGABLE.getDetalle());
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RECARGA_TARJETA);
		detalle.setComisionTotal(ComprobantesBOEnum.MONEDA_ARG.getDetalle() + comprobante.getImporteTotalComision());
		detalle.setImporteAcreditado(ComprobantesBOEnum.MONEDA_ARG.getDetalle() + comprobante.getImporteAcreditado());
		detalle.setNroComprobante(comprobante.getNroComprobante());
		return detalle;
	}

	/**
	 * Obtener comprobante DTO de un comprobante de pago de compras.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	public ComprobanteDTO obtenerComprobanteDTOParaPagoDeCompras(ComprobantePagoCompraOk comprobante) {
		ComprobanteDTO comprobantePagoCompra = new ComprobanteDTO();
		comprobantePagoCompra.setNecesitaMoneda(false);
		comprobantePagoCompra.setFecha(obtenerDate(comprobante.getFechaOper()));
		comprobantePagoCompra.setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_PUNTUAL);
		comprobantePagoCompra.setHistorial(HistorialComprobanteEnum.PAGO_SERVICIOS_C);
		if ("$".equals(comprobante.getMonedaCtaDeb())) {
			comprobantePagoCompra
					.setImportePesos(ISBANStringUtils.convertirABigDecimal(comprobante.getImporteTotalDebito()));
			comprobantePagoCompra.setCtaMedioDePagoPesos(
					obtenerSucursalParseada(comprobante.getSucCtaDeb(), comprobante.getNroCtaDeb()));
			comprobantePagoCompra
					.setTipoCtaMedioDePagoPesos(obtenerTipoCuentaDeDescripcion(comprobante.getDescCuenta(), true));
		} else {
			comprobantePagoCompra
					.setImporteDolares(ISBANStringUtils.convertirABigDecimal(comprobante.getImporteTotalDebito()));
			comprobantePagoCompra.setCtaMedioDePagoDolares(
					obtenerSucursalParseada(comprobante.getSucCtaDeb(), comprobante.getNroCtaDeb()));
			comprobantePagoCompra
					.setTipoCtaMedioDePagoDolares(obtenerTipoCuentaDeDescripcion(comprobante.getDescCuenta(), false));
		}
		comprobantePagoCompra.setDestinatario(comprobante.getNombreEmpresa());
		comprobantePagoCompra.setDetalle(obtenerDetalleComprobantePagoDeCompras(comprobante));
		return comprobantePagoCompra;
	}

	/**
	 * Obtener comprobante DTO para transf inmediatas rio rio T banco.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	@Override
	public ComprobanteDTO obtenerComprobanteDTOParaTransfInmediatasRioRioTBanco(
			ComprobanteTransfInmRioRio comprobante) {
		ComprobanteDTO comprobanteTrfcta = new ComprobanteDTO();
		comprobanteTrfcta.setNecesitaMoneda(false);
		comprobanteTrfcta
				.setFecha(obtenerDate(obtenerFechaConHora(comprobante.getFechaOper(), comprobante.getHoraOper())));
		comprobanteTrfcta.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA);
		comprobanteTrfcta.setHistorial(HistorialComprobanteEnum.TRANSFERENCIA_RIORIO);
		TransferenciaRio transferencia = comprobante.getTransferencia();

		String moneda = obtenerMoneda(transferencia.getTipoCuentaOrigen());
		String importe = transferencia.getImporte().replace("$ ", "").replace(".", ",");

		if (!ISBANStringUtils.isEmptyOrNull(importe)) {
			setearImportePesosDolar(moneda, importe, comprobanteTrfcta);
		}
		String destinatario = transferencia.getAlias();
		if (StringUtils.isBlank(destinatario)) {
			destinatario = transferencia.getNombreDestinatario();
		}
		setearDestinatarioByString(destinatario, comprobanteTrfcta);
		obtenerDescripcion(transferencia.getTipoCuentaOrigen(), moneda, comprobanteTrfcta);

		setNumeroCuentaPesoDolarParseado(transferencia.getSucursalCuentaOrigen(), transferencia.getNumeroCuentaOrigen(),
				comprobanteTrfcta);
		comprobanteTrfcta.setDetalle(obtenerDetalleComprobanteTransfInmediatasRioRioTBanco(comprobante));
		return comprobanteTrfcta;
	}

	/**
	 * Obtener detalle comprobante transf inmediatas rio rio T banco.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the detalle comprobante DTO
	 */
	private DetalleComprobanteDTO obtenerDetalleComprobanteTransfInmediatasRioRioTBanco(
			ComprobanteTransfInmRioRio comprobante) {
		DetalleComprobanteTransferenciaInmediataRioRioDTO detalle = new DetalleComprobanteTransferenciaInmediataRioRioDTO();
		detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_TRANSFERENCIA.getDetalle());
		detalle.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
		detalle.setPlazoAcreditacion(PLAZO_ACREDITACION);
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO);
		TransferenciaRio transferencia = comprobante.getTransferencia();

		detalle.setTipoCuentaOrigen(obtenerTipoCuenta(transferencia.getTipoCuentaOrigen()));
		detalle.setNroCuentaOrigen(
				new IdentificacionCuenta(transferencia.getSucursalCuentaOrigen(), transferencia.getNumeroCuentaOrigen())
						.toString());
		detalle.setTipoCuentaDestino(obtenerTipoCuenta(transferencia.getTipoCuentaDestino()));
		detalle.setNroCuentaDestino(new IdentificacionCuenta(transferencia.getSucursalCuentaDestino(),
				transferencia.getNumeroCuentaDestino()).toString());
		detalle.setConcepto(transferencia.getConcepto());
		detalle.setDesConcepto(transferencia.getDescripcionConcepto());
		if (StringUtils.isBlank(transferencia.getEmailDestinatario())) {
			detalle.setAvisoTransferencia("No");
		} else {
			detalle.setAvisoTransferencia("Si");
			detalle.setMail(transferencia.getEmailDestinatario());
			detalle.setComentarios(transferencia.getMensaje());
		}
		detalle.setNroComprobante(ISBANStringUtils.eliminarCeros(transferencia.getNroComprobante()));
		return detalle;
	}

	/**
	 * Obtener comprobante DTO para transf inmediatas rio rio T banco.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaTransfInmediatasRioOBTBanco(ComprobanteTransfInmRioOB comprobante) {
		ComprobanteDTO comprobanteTrfcta = new ComprobanteDTO();
		comprobanteTrfcta.setNecesitaMoneda(false);
		comprobanteTrfcta
				.setFecha(obtenerDate(obtenerFechaConHora(comprobante.getFechaOper(), comprobante.getHoraOper())));
		comprobanteTrfcta.setTipoOperacion(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA);
		comprobanteTrfcta.setHistorial(HistorialComprobanteEnum.TRANSFERENCIA_OTROSBANCOS);
		TransferenciaOB transferencia = comprobante.getTransferencia();

		String moneda = obtenerMoneda(transferencia.getTipoCuentaOrigen());
		String importe = transferencia.getImporte();

		if (!ISBANStringUtils.isEmptyOrNull(importe) && "$".equals(moneda)) {
			comprobanteTrfcta.setImportePesos(new BigDecimal(importe.replace("$ ", "")));
		} else if (!ISBANStringUtils.isEmptyOrNull(importe) && "U$S".equals(moneda)) {
			comprobanteTrfcta.setImporteDolares(new BigDecimal(importe.replace("U$S ", "")));
		}
		String destinatario = transferencia.getAlias();
		if (StringUtils.isBlank(destinatario)) {
			destinatario = transferencia.getNombreDestinatario();
		}
		setearDestinatarioByString(destinatario, comprobanteTrfcta);
		obtenerDescripcion(transferencia.getTipoCuentaOrigen(), moneda, comprobanteTrfcta);

		setNumeroCuentaPesoDolarParseado(transferencia.getSucursalCuentaOrigen(), transferencia.getNumeroCuentaOrigen(),
				comprobanteTrfcta);
		comprobanteTrfcta.setDetalle(obtenerDetalleComprobanteTransfInmediatasRioOBTBanco(comprobante));
		return comprobanteTrfcta;
	}

	/**
	 * Obtener detalle comprobante transf inmediatas rio OBT banco.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the detalle comprobante DTO
	 */
	private DetalleComprobanteDTO obtenerDetalleComprobanteTransfInmediatasRioOBTBanco(
			ComprobanteTransfInmRioOB comprobante) {
		DetalleComprobanteTransferenciaInmediataOtrosBancosDTO detalle = new DetalleComprobanteTransferenciaInmediataOtrosBancosDTO();
		detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_TRANSFERENCIA.getDetalle());
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO);
		TransferenciaOB transferencia = comprobante.getTransferencia();
		detalle.setBanco(transferencia.getBanco());
		detalle.setTipoCuentaOrigen(obtenerTipoCuenta(transferencia.getTipoCuentaOrigen()));
		detalle.setNroCuentaOrigen(
				new IdentificacionCuenta(transferencia.getSucursalCuentaOrigen(), transferencia.getNumeroCuentaOrigen())
						.toString());
		detalle.setNroCuentaDestino(comprobante.getTransferencia().getCbu());
		detalle.setAliasCBU(comprobante.getTransferencia().getAliasCBU());
		CuitDTO cuit = new CuitDTO(CuitEnum.CUIT_DESTINATARIO, parsearCuitCuil(transferencia.getCuitCuil()));
		detalle.setCuit(cuit);
		detalle.setPlazoAcreditacion(PLAZO_ACREDITACION_A_TERCEROS);
		detalle.setConcepto(transferencia.getConcepto());
		detalle.setInformacionAdicional(transferencia.getDescripcionConcepto());
		if (StringUtils.isBlank(transferencia.getEmailDestinatario())) {
			detalle.setAvisoTransferencia("No");
		} else {
			detalle.setAvisoTransferencia("Si");
			detalle.setMail(transferencia.getEmailDestinatario());
			detalle.setComentarios(transferencia.getMensaje());
		}
		detalle.setNroComprobante(transferencia.getNroComprobante());
		return detalle;
	}

	/**
	 * Obtener detalle comprobante.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the detalle comprobante DTO
	 */
	private DetalleComprobanteDTO obtenerDetalleComprobantePagoDeCompras(ComprobantePagoCompraOk comprobante) {
		DetalleComprobantePagoDeComprasDTO detalle = new DetalleComprobantePagoDeComprasDTO();
		detalle.setIdentificacion(comprobante.getIdentificacion());
		detalle.setNroComprobante(comprobante.getNroComprobante());
		detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		detalle.setNroBoleta(StringUtils.trimToNull(comprobante.getNroBoleta()));
		detalle.setLabelDinamico(obtenerLabelDinamicoCompras(comprobante.getIdEmpresa()));
		detalle.setIdEmpresa(comprobante.getIdEmpresa());
		if (comprobante.getPagos() != null && CollectionUtils.isNotEmpty(comprobante.getPagos().getPago())) {
			detalle.setNroOrden(StringUtils.trimToNull(comprobante.getPagos().getPago().get(0).getNroOrden()));
		}
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PAGO_COMPRAS);
		return detalle;
	}

	/**
	 * Obtener label dinamico compras.
	 *
	 * @param idEmpresa
	 *            the id empresa
	 * @return the string
	 */
	private String obtenerLabelDinamicoCompras(String idEmpresa) {
		MedioPago medioPago = mediosPagoBO.obtenerEmpresaPorIdAcuerdoCompras(idEmpresa);
		String label = "Número de Identificación";
		if (medioPago != null) {
			return StringUtils.defaultIfBlank(WordUtils.capitalizeFully(medioPago.getPpdctaIdentificadorPyrip()),
					label);
		}
		return label;
	}

	/**
	 * Obtener comprobante DTO para PMC sin deuda cuenta.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaPMCSinDeudaCuenta(ComprobantePMCSinDeuda comprobante) {
		ComprobanteDTO comprobantePMC = obtenerComprobanteDTOParaPMCSinDeuda(comprobante);
		comprobantePMC.setearMedioDePagoCuentaPesos(comprobante.getTransferencia().getTipoCuentaDebito(),
				comprobante.getTransferencia().getSucursalCuentaDebito(),
				comprobante.getTransferencia().getNumeroCuentaDebito());
		return comprobantePMC;
	}

	/**
	 * Obtener comprobante DTO para PMC VEP TC.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaPMCVEPTC(ComprobantePMCVEP comprobante) {
		ComprobanteDTO comprobantePMC = obtenerComprobanteDTOParaPMCVep(comprobante);
		comprobantePMC.setearMedioDePagoTC(comprobante.getTransferencia().getTipoTarjetaCredito(),
				comprobante.getTransferencia().getNumeroTarjetaCredito());
		return comprobantePMC;
	}

	/**
	 * Obtener comprobante DTO para PMC AFIP TC.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaPMCAfipTC(ComprobantePMCAfip comprobante) {
		ComprobanteDTO comprobantePMC = obtenerComprobanteDTOParaPMCAfip(comprobante);
		comprobantePMC.setearMedioDePagoTC(comprobante.getTransferencia().getTipoTarjetaCredito(),
				comprobante.getTransferencia().getNumeroTarjetaCredito());
		return comprobantePMC;
	}

	/**
	 * Obtener comprobante DTO para PMC sin deuda TC.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaPMCSinDeudaTC(ComprobantePMCSinDeuda comprobante) {
		ComprobanteDTO comprobantePMC = obtenerComprobanteDTOParaPMCSinDeuda(comprobante);
		comprobantePMC.setearMedioDePagoTC(comprobante.getTransferencia().getTipoTarjetaCredito(),
				comprobante.getTransferencia().getNumeroTarjetaCredito());
		return comprobantePMC;
	}

	/**
	 * Obtener comprobante DTO de un comprobante de PMC sin deuda.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaPMCSinDeuda(ComprobantePMCSinDeuda comprobante) {
		ComprobanteDTO comprobantePMC = new ComprobanteDTO();

		comprobantePMC.setFecha(ISBANStringUtils.formatearFecha(comprobante.getTransferencia().getFechaHoraPago(),
				ISBANStringUtils.FORMATO_FECHA_IATX + HORA_MINUTO_SEGUNDO));
		comprobantePMC.setTipoOperacion(TipoOperacionComprobanteEnum.SCOMP_PAGO_SERVICIOS);
		comprobantePMC.setHistorial(HistorialComprobanteEnum.PAGO_SERVICIOS_B);
		comprobantePMC.setDestinatario(comprobante.getTransferencia().getEmpresa());

		if (!ISBANStringUtils.isEmptyOrNull(comprobante.getTransferencia().getImporte())) {
			String importe = comprobante.getTransferencia().getImporte().replace(",", "").replace(".", "");
			importe = importe.substring(0, importe.length() - 2) + "." + importe.substring(importe.length() - 2);
			importe = ISBANStringUtils.formatearConComaYDosDecimales(importe);
			setearImportePesosDolar(comprobante.getTransferencia().getMoneda(), importe, comprobantePMC);
		}
		comprobantePMC.setDetalle(obtenerDetalleComprobantePMCSinDeuda(comprobante));
		return comprobantePMC;
	}

	/**
	 * Obtener detalle comprobante PMC sin deuda.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the detalle comprobante DTO
	 */
	private DetalleComprobanteDTO obtenerDetalleComprobantePMCSinDeuda(ComprobantePMCSinDeuda comprobante) {
		DetalleComprobanteScompSinDeudaDTO detalle = new DetalleComprobanteScompSinDeudaDTO();
		String horaSinFormato = StringUtils.right(comprobante.getTransferencia().getFechaHoraPago(), 6);
		detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_PMC_SIN_DEUDA);
		detalle.setEmpresa(comprobante.getTransferencia().getEmpresa());
		String importe = comprobante.getTransferencia().getImporte().replace(",", "").replace(".", "");
		importe = importe.substring(0, importe.length() - 2) + "." + importe.substring(importe.length() - 2);
		detalle.setImporte(new BigDecimal(importe));
		detalle.setHoraDePago(StringUtils.left(horaSinFormato, 2) + ":" + StringUtils.substring(horaSinFormato, 2, 4));
		detalle.setNroControl(comprobante.getTransferencia().getNumControl());
		detalle.setNroComprobante(comprobante.getTransferencia().getNroComprobante());
		detalle.setLabelDinamico(obtenerLabelDinamico(comprobante.getTransferencia().getEmpresa()));
		detalle.setIdentificacion(comprobante.getTransferencia().getIdentificacion());
		detalle.setTipoPMC(ComprobantesBOEnum.PMC_SIN_DEUDA.getId());
		detalle.setFechaDePago(ISBANStringUtils.formatearFecha(comprobante.getTransferencia().getFechaHoraPago(),
				ISBANStringUtils.FORMATO_FECHA_IATX + HORA_MINUTO_SEGUNDO));
		detalle.setIdClienteEmpresa(comprobante.getTransferencia().getIdentificacion());
		return detalle;

	}

	/**
	 * Obtener comprobante DTO para PMC con deuda cuenta.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaPMCConDeudaCuenta(ComprobantePMCConDeuda comprobante) {
		ComprobanteDTO comprobantePMC = obtenerComprobanteDTOParaPMCConDeuda(comprobante);
		comprobantePMC.setearMedioDePagoCuentaPesos(comprobante.getTransferencia().getTipoCuentaDebito(),
				comprobante.getTransferencia().getSucursalCuentaDebito(),
				comprobante.getTransferencia().getNumeroCuentaDebito());
		return comprobantePMC;
	}

	/**
	 * Obtener comprobante DTO para PMC con deuda TC.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaPMCConDeudaTC(ComprobantePMCConDeuda comprobante) {
		ComprobanteDTO comprobantePMC = obtenerComprobanteDTOParaPMCConDeuda(comprobante);
		comprobantePMC.setearMedioDePagoTC(comprobante.getTransferencia().getTipoTarjetaCredito(),
				comprobante.getTransferencia().getNumeroTarjetaCredito());
		return comprobantePMC;
	}

	/**
	 * Obtener comprobante DTO.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaPMCConDeuda(ComprobantePMCConDeuda comprobante) {
		ComprobanteDTO comprobantePMC = new ComprobanteDTO();
		comprobantePMC.setFecha(ISBANStringUtils.formatearFecha(comprobante.getTransferencia().getFechaHoraPago(),
				ISBANStringUtils.FORMATO_FECHA_IATX + HORA_MINUTO_SEGUNDO));
		comprobantePMC.setTipoOperacion(TipoOperacionComprobanteEnum.SCOMP_PAGO_SERVICIOS);
		comprobantePMC.setHistorial(HistorialComprobanteEnum.PAGO_SERVICIOS_B);
		comprobantePMC.setDestinatario(comprobante.getTransferencia().getEmpresa());
		if (!ISBANStringUtils.isEmptyOrNull(comprobante.getTransferencia().getImporte())) {
			String importe = comprobante.getTransferencia().getImporte().replace(",", "").replace(".", "");
			importe = importe.substring(0, importe.length() - 2) + "." + importe.substring(importe.length() - 2);
			importe = ISBANStringUtils.formatearConComaYDosDecimales(importe);
			setearImportePesosDolar(comprobante.getTransferencia().getMoneda(), importe, comprobantePMC);
		}
		comprobantePMC.setDetalle(obtenerDetalleComprobantePMCConDeuda(comprobante));
		return comprobantePMC;
	}

	/**
	 * Obtener detalle comprobante PMC con deuda.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the detalle comprobante DTO
	 */
	private DetalleComprobanteDTO obtenerDetalleComprobantePMCConDeuda(ComprobantePMCConDeuda comprobante) {
		DetalleComprobanteScompConDeudaDTO detalle = new DetalleComprobanteScompConDeudaDTO();
		String horaSinFormato = StringUtils.right(comprobante.getTransferencia().getFechaHoraPago(), 6);
		detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_PMC_CON_DEUDA);
		detalle.setTipoPMC(ComprobantesBOEnum.PMC_CON_DEUDA.getId());
		detalle.setEmpresa(comprobante.getTransferencia().getEmpresa());
		String importe = comprobante.getTransferencia().getImporte().replace(",", "").replace(".", "");
		importe = importe.substring(0, importe.length() - 2) + "." + importe.substring(importe.length() - 2);
		detalle.setImporte(new BigDecimal(importe));
		if (comprobante.getTransferencia().getFechaVencimiento() != null) {
			detalle.setFechaVencimiento(ISBANStringUtils.formatearFecha(
					comprobante.getTransferencia().getFechaVencimiento().replace("/", ""),
					ISBANStringUtils.FORMATO_FECHA_SIN_BARRAS));
		}
		detalle.setFactura(comprobante.getTransferencia().getLeyendaFactura());
		detalle.setHoraDePago(StringUtils.left(horaSinFormato, 2) + ":" + StringUtils.substring(horaSinFormato, 2, 4));
		detalle.setNroControl(comprobante.getTransferencia().getNumControl());
		detalle.setNroComprobante(comprobante.getTransferencia().getNroComprobante());
		detalle.setLabelDinamico(obtenerLabelDinamico(comprobante.getTransferencia().getEmpresa()));
		detalle.setIdentificacion(comprobante.getTransferencia().getIdentificacion());
		detalle.setEsPagoConDeuda(true);
		detalle.setFechaDePago(ISBANStringUtils.formatearFecha(comprobante.getTransferencia().getFechaHoraPago(),
				ISBANStringUtils.FORMATO_FECHA_IATX + HORA_MINUTO_SEGUNDO));
		detalle.setIdClienteEmpresa(comprobante.getTransferencia().getIdentificacion());
		return detalle;
	}

	/**
	 * Obtener comprobante DTO para PMC vep.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaPMCVep(ComprobantePMCVEP comprobante) {
		ComprobanteDTO comprobanteVEP = new ComprobanteDTO();
		IdentificacionCuenta identificacion = new IdentificacionCuenta(
				comprobante.getTransferencia().getSucursalCuentaDebito(),
				comprobante.getTransferencia().getNumeroCuentaDebito());
		comprobanteVEP.setFecha(ISBANStringUtils.formatearFecha(comprobante.getTransferencia().getFechaHoraPago(),
				ISBANStringUtils.FORMATO_FECHA_IATX + HORA_MINUTO_SEGUNDO));
		comprobanteVEP.setTipoOperacion(TipoOperacionComprobanteEnum.SCOMP_PAGO_SERVICIOS2);
		comprobanteVEP.setHistorial(HistorialComprobanteEnum.PAGO_SERVICIOS_B);
		comprobanteVEP.setDestinatario(comprobante.getTransferencia().getEmpresa());
		comprobanteVEP.setCtaMedioDePagoPesos(identificacion.toString());
		String tipoCuentaDeb = comprobante.getTransferencia().getTipoCuentaDebito();
		if (ISBANStringUtils.isEmptyOrNull(tipoCuentaDeb)) {
			tipoCuentaDeb = "9";
		}
		comprobanteVEP.setTipoCtaMedioDePagoPesos(TipoCuenta.fromCodigo(tipoCuentaDeb));
		if (!ISBANStringUtils.isEmptyOrNull(comprobante.getTransferencia().getImporte())) {
			String importe = comprobante.getTransferencia().getImporte().replace(",", "").replace(".", "");
			importe = importe.substring(0, importe.length() - 2) + "." + importe.substring(importe.length() - 2);
			importe = ISBANStringUtils.formatearConComaYDosDecimales(importe);
			setearImportePesosDolar(comprobante.getTransferencia().getMoneda(), importe, comprobanteVEP);
		}
		comprobanteVEP.setDetalle(obtenerDetalleComprobantePMCVep(comprobante, identificacion,
				comprobanteVEP.getTipoCtaMedioDePagoPesos()));
		return comprobanteVEP;
	}

	/**
	 * Obtener detalle comprobante.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @param identificacion
	 *            the identificacion
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the detalle comprobante DTO
	 */
	private DetalleComprobanteDTO obtenerDetalleComprobantePMCVep(ComprobantePMCVEP comprobante,
			IdentificacionCuenta identificacion, TipoCuenta tipoCuenta) {
		DetalleComprobanteScompVepDTO detalle = new DetalleComprobanteScompVepDTO();
		detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		String horaSinFormato = StringUtils.right(comprobante.getTransferencia().getFechaHoraPago(), 6);
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_PMC_VEP);
		detalle.setTipoPMC(ComprobantesBOEnum.PMC_VEP2.getId());
		detalle.setEmpresa(comprobante.getTransferencia().getEmpresa());
		if (comprobante.getTransferencia().getFechaVencimiento() != null) {
			detalle.setFechaVencimiento(ISBANStringUtils.formatearFecha(
					comprobante.getTransferencia().getFechaVencimiento().replace("/", ""),
					ISBANStringUtils.FORMATO_FECHA_SIN_BARRAS));
		}
		detalle.setCuit(new CuitDTO(CuitEnum.CUIT_CONTRIBUYENTE,
				obtenerCuitConGuion(comprobante.getTransferencia().getIdentificacion())));
		detalle.setFechaDePago(ISBANStringUtils.formatearFecha(comprobante.getTransferencia().getFechaHoraPago(),
				ISBANStringUtils.FORMATO_FECHA_IATX + HORA_MINUTO_SEGUNDO));
		detalle.setHoraDePago(StringUtils.left(horaSinFormato, 2) + ":" + StringUtils.substring(horaSinFormato, 2, 4));
		detalle.setCuitVEP(new CuitDTO(CuitEnum.CUIT_GENERADOR_VEP,
				obtenerCuitConGuion(comprobante.getTransferencia().getIdentificacion2())));
		detalle.setNroVEP(comprobante.getTransferencia().getNumeroVep());
		if (!ISBANStringUtils.isEmptyOrNull(comprobante.getTransferencia().getImporte())) {
			detalle.setImporte(ISBANStringUtils.convertirABigDecimal(comprobante.getTransferencia().getImporte()));
		}
		detalle.setNroMedioDePago(identificacion.toString());
		detalle.setTipoMedioDePago(tipoCuenta);
		String periodo = comprobante.getTransferencia().getPeriodo();
		if (periodo != null && periodo.length() > 2) {
			periodo = periodo.substring(0, 2) + "/" + periodo.substring(2);
		}
		detalle.setPeriodo(periodo);
		detalle.setAnticipo(comprobante.getTransferencia().getAnticipoCuota());

		detalle.setNroControl(comprobante.getTransferencia().getNumControl());
		detalle.setNroComprobante(comprobante.getTransferencia().getNroComprobante());
		detalle.setIdClienteEmpresa(comprobante.getTransferencia().getIdentificacion());
		return detalle;
	}

	/**
	 * Obtener comprobante DTO.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaPMCAfip(ComprobantePMCAfip comprobante) {
		ComprobanteDTO comprobanteAfip = new ComprobanteDTO();
		IdentificacionCuenta identificacion = new IdentificacionCuenta(
				comprobante.getTransferencia().getSucursalCuentaDebito(),
				comprobante.getTransferencia().getNumeroCuentaDebito());
		comprobanteAfip.setFecha(ISBANStringUtils.formatearFecha(comprobante.getTransferencia().getFechaHoraPago(),
				ISBANStringUtils.FORMATO_FECHA_IATX + HORA_MINUTO_SEGUNDO));
		comprobanteAfip.setTipoOperacion(TipoOperacionComprobanteEnum.SCOMP_PAGO_SERVICIOS2);
		comprobanteAfip.setHistorial(HistorialComprobanteEnum.PAGO_SERVICIOS_B);
		comprobanteAfip.setDestinatario(comprobante.getTransferencia().getEmpresa());
		comprobanteAfip.setCtaMedioDePagoPesos(identificacion.toString());
		comprobanteAfip.setTipoCtaMedioDePagoPesos(
				TipoCuenta.fromCodigo(comprobante.getTransferencia().getTipoCuentaDebito()));
		if (!ISBANStringUtils.isEmptyOrNull(comprobante.getTransferencia().getImporte())) {
			String importe = comprobante.getTransferencia().getImporte().replace(",", "").replace(".", "");
			importe = importe.substring(0, importe.length() - 2) + "." + importe.substring(importe.length() - 2);
			importe = ISBANStringUtils.formatearConComaYDosDecimales(importe);
			setearImportePesosDolar(comprobante.getTransferencia().getMoneda(), importe, comprobanteAfip);
		}
		comprobanteAfip.setDetalle(obtenerDetalleComprobantePMCAfip(comprobante, identificacion,
				comprobanteAfip.getTipoCtaMedioDePagoPesos()));
		return comprobanteAfip;
	}

	/**
	 * Obtener comprobante DTO para financiacion resumen.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTOParaFinanciacionResumen(ComprobanteFinanciacionResumen comprobante) {
		ComprobanteDTO comprobanteFinanciacion = new ComprobanteDTO();
		comprobanteFinanciacion
				.setFecha(obtenerDate(obtenerFechaConHora(comprobante.getFechaOper(), comprobante.getHoraOper())));
		comprobanteFinanciacion.setTipoOperacion(TipoOperacionComprobanteEnum.COMPROBANTE_FINANCIACION_RESUMEN);
		String destinatarioYMedioPago = comprobante.getTarjeta().getTipoTarjeta() + " "
				+ TarjetaUtils.cortarNumeroTarjetaComoTarjetaActiva(comprobante.getTarjeta().getNumTarjeta(),
						comprobante.getTarjeta().getTipoTarjeta());
		comprobanteFinanciacion.setDestinatario(destinatarioYMedioPago);
		comprobanteFinanciacion.setCtaMedioDePagoPesos(destinatarioYMedioPago);
		setearImportePesosDolar("$", comprobante.getTarjeta().getImporteCuota(), comprobanteFinanciacion);
		comprobanteFinanciacion.setDetalle(null);
		return comprobanteFinanciacion;

	}

	/**
	 * Obtener detalle comprobante PMC afip.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @param identificacion
	 *            the identificacion
	 * @param tipoCtaMedioDePagoPesos
	 *            the tipo cta medio de pago pesos
	 * @return the detalle comprobante DTO
	 */
	private DetalleComprobanteDTO obtenerDetalleComprobantePMCAfip(ComprobantePMCAfip comprobante,
			IdentificacionCuenta identificacion, TipoCuenta tipoCtaMedioDePagoPesos) {
		DetalleComprobanteScompAfipDTO detalle = new DetalleComprobanteScompAfipDTO();
		detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		String horaSinFormato = StringUtils.right(comprobante.getTransferencia().getFechaHoraPago(), 6);
		detalle.setTipoPMC(ComprobantesBOEnum.PMC_AFIP.getId());
		detalle.setEmpresa(comprobante.getTransferencia().getEmpresa());
		if (Integer.valueOf(comprobante.getTransferencia().getFechaVencimiento()) != 0) {
			detalle.setFechaVencimiento(ISBANStringUtils.formatearFecha(
					comprobante.getTransferencia().getFechaVencimiento(), ISBANStringUtils.FORMATO_FECHA_SIN_BARRAS));
		}
		detalle.setCuit(new CuitDTO(CuitEnum.CUIT_EMPLEADOR,
				obtenerCuitConGuion(comprobante.getTransferencia().getIdentificacion2())));
		detalle.setCuitVEP(new CuitDTO(CuitEnum.CUIT_EMPLEADOR,
				obtenerCuitConGuion(comprobante.getTransferencia().getIdentificacion())));
		detalle.setLabelDinamico(obtenerLabelDinamico(comprobante.getTransferencia().getEmpresa()));
		obtenerElementoAdicional(detalle, comprobante.getTransferencia());
		detalle.setFechaDePago(ISBANStringUtils.formatearFecha(comprobante.getTransferencia().getFechaHoraPago(),
				ISBANStringUtils.FORMATO_FECHA_IATX + HORA_MINUTO_SEGUNDO));
		detalle.setHoraDePago(StringUtils.left(horaSinFormato, 2) + ":" + StringUtils.substring(horaSinFormato, 2, 4));
		detalle.setImporte(ISBANStringUtils.convertirABigDecimal(comprobante.getTransferencia().getImporte()));
		detalle.setNroMedioDePago(identificacion.toString());
		detalle.setTipoMedioDePago(tipoCtaMedioDePagoPesos);
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_PMC_AFIP);
		detalle.setNroControl(comprobante.getTransferencia().getNumControl());
		detalle.setNroComprobante(comprobante.getTransferencia().getNroComprobante());
		detalle.setIdClienteEmpresa(StringUtils.left(comprobante.getTransferencia().getIdentificacion(), 11));
		return detalle;
	}

	/**
	 * Obtener elemento adicional.
	 *
	 * @param detalle
	 *            the detalle
	 * @param transferencia
	 *            the transferencia
	 */
	private void obtenerElementoAdicional(DetalleComprobanteDTO detalle, TransferenciaPMCAfip transferencia) {
		if (ISBANStringUtils.isNullOrBlank(transferencia.getPeriodoPago())) {
			detalle.setInformacionAdicional(transferencia.getPeriodoPago());
			detalle.setAnotaciones("Período");
		} else {
			detalle.setInformacionAdicional(transferencia.getDatosAdicionales());
			MedioPago medioPago = mediosPagoBO.obtenerPorNombreFantasia(transferencia.getEmpresa());
			if (null == medioPago) {
				detalle.setAnotaciones(StringUtils.EMPTY);
			} else {
				String datoAdicional = medioPago.getDatosAdicionales();
				if ("1".equals(datoAdicional)) {
					detalle.setAnotaciones("Anticipo");
				} else if ("2".equals(datoAdicional)) {
					detalle.setAnotaciones("Anticipo/Año");
				} else if ("3".equals(datoAdicional)) {
					detalle.setAnotaciones("Mes/Año");
				} else if ("5".equals(datoAdicional)) {
					detalle.setAnotaciones("Cuota");
				}
			}
		}
	}

	/**
	 * Sets the número cuenta peso dólar parseado.
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * altaScompTransferencia(ar.com.santanderrio.obp.servicios.comprobantes.
	 * entities.AltaComprobanteRequestBuilder)
	 */
	@Override
	public Respuesta<Void> altaScompTransferencia(AltaComprobanteRequestBuilder altaRequest) {
		LOGGER.info("Iniciando alta transferencia scomp");
		try {
			return obtenerRespuestaAlta(altaRequest);
		} catch (Exception e) {
			LOGGER.error("Error inesperado en alta scomp transf rio rio", e);
			return crearErrorSinMensaje("DAOExcepcion", "DAO_EXCEPTION");
		}
	}

	/**
	 * Obtener respuesta alta.
	 *
	 * @param altaRequest
	 *            the alta request
	 * @return the respuesta
	 */
	private Respuesta<Void> obtenerRespuestaAlta(AltaComprobanteRequestBuilder altaRequest) {
		try {
			ComprobanteResponse response = scompDAO.altaComprobante(altaRequest.buildComprobanteRequest());
			return crearResponseDeAlta(response);
		} catch (DAOException e) {
			LOGGER.error("Error en DAO de altaComprobante", e);
		}
		return crearErrorSinMensaje("DAOExcepcion", "DAO_EXCEPTION");
	}

	/**
	 * Crear response de alta.
	 *
	 * @param response
	 *            the response
	 * @return the respuesta
	 */
	private Respuesta<Void> crearResponseDeAlta(ComprobanteResponse response) {
		Respuesta<Void> res;
		if (response.getCodRet() == 0) {
			res = respuestaFactory.crearRespuestaOk(void.class);
		} else {
			res = crearErrorSinMensaje("errorGeneral", "ERROR_GENERAL");
		}
		return res;
	}

	/**
	 * Crear error sin mensaje.
	 *
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @return the respuesta
	 */
	private Respuesta<Void> crearErrorSinMensaje(String tag, String tipoError) {
		List<ItemMensajeRespuesta> itemRespuesta = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta resError = new ItemMensajeRespuesta();
		resError.setTag(tag);
		resError.setTipoError(tipoError);
		itemRespuesta.add(resError);
		return respuestaFactory.crearRespuestaError(void.class, itemRespuesta);
	}

	/**
	 * Obtener label dinamico.
	 *
	 * @param empresa
	 *            the empresa
	 * @return the string
	 */
	private String obtenerLabelDinamico(String empresa) {
		Set<MedioPago> mediosPago = buscadorMediosPagoDAO.searchEmpresaByNombreFantasia(empresa.trim());
		for (MedioPago medioPago : mediosPago) {
			if (medioPago.getNombreFantasia().equals(empresa) && !medioPago.getPesIdentificacion().isEmpty()) {
				return medioPago.getPesIdentificacion().substring(0, 1).toUpperCase()
						+ medioPago.getPesIdentificacion().substring(1).toLowerCase();
			}
		}
		return "Número de Identificación";
	}

	/**
	 * Numero cuenta origen.
	 *
	 * @param sucursalCuentaOrigen
	 *            the sucursal cuenta origen
	 * @param numeroCuentaOrigen
	 *            the numero cuenta origen
	 * @return the string
	 */
	private String obtenernumeroCuenta(String sucursalCuentaOrigen, String numeroCuentaOrigen) {
		return sucursalCuentaOrigen + "-" + numeroCuentaOrigen;
	}

	/**
	 * Obtener fecha con hora.
	 *
	 * @param fechaOper
	 *            the fecha oper
	 * @param horaOper
	 *            the hora oper
	 * @return the string
	 */
	private String obtenerFechaConHora(String fechaOper, String horaOper) {
		return fechaOper.replace("00:00:00", horaOper).replace("/", "-");
	}

	/**
	 * Obtener cuit con guion.
	 *
	 * @param identificacion
	 *            the identificacion
	 * @return the string
	 */
	private String obtenerCuitConGuion(String identificacion) {
		if (identificacion != null && identificacion.length() > 2) {
			return identificacion.substring(0, 2) + "-" + identificacion.substring(2, 10) + "-"
					+ identificacion.substring(10, 11);
		} else {
			return identificacion;
		}
	}

	/**
	 * Parsear cuit cuil.
	 *
	 * @param idBeneficiario
	 *            the id beneficiario
	 * @return the string
	 */
	private String parsearCuitCuil(String idBeneficiario) {
		if (idBeneficiario != null && idBeneficiario.length() > 2) {
			String lowstringCuit = idBeneficiario.substring(0, 2);
			String midstringCuit = idBeneficiario.substring(2, idBeneficiario.length() - 1);
			String highstringCuit = idBeneficiario.substring(idBeneficiario.length() - 1);
			return lowstringCuit + "-" + midstringCuit + "-" + highstringCuit;
		} else {
			return idBeneficiario;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesPagoDeComprasAsync(ar.com.santanderrio.obp.servicios.
	 * clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPagoDeComprasAsync(Cliente cliente,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(
				obtenerComprobantesPagoDeCompras(cliente.getNup(), transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesPMCSinDeudaTCAsync(java.lang.String,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCSinDeudaTCAsync(String nup,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesPMCSinDeudaTC(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesPMCSinDeudaTC(java.lang.String,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPMCSinDeudaTC(String nup, TransaccionDTO transaccion) {
		return obtenerComprobantesConEstadoOper(nup, transaccion, "2", "36", "100", "11", 13);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesPMCConDeudaTCAsync(java.lang.String,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCConDeudaTCAsync(String nup,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesPMCConDeudaTC(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesPMCConDeudaTC(java.lang.String,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPMCConDeudaTC(String nup, TransaccionDTO transaccion) {
		return obtenerComprobantesConEstadoOper(nup, transaccion, "2", "38", "100", "11", 14);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesPMCVEPTCAsync(java.lang.String,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCVEPTCAsync(String nup, TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesPMCVEPTC(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesPMCVEPTC(java.lang.String,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPMCVEPTC(String nup, TransaccionDTO transaccion) {
		return obtenerComprobantesConEstadoOper(nup, transaccion, "2", "37", "100", "11", 15);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesPMCAfipTCAsync(java.lang.String,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCAfipTCAsync(String nup,
			TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesPMCAfipTC(nup, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO#
	 * obtenerComprobantesPMCAfipTC(java.lang.String,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPMCAfipTC(String nup, TransaccionDTO transaccion) {
		return obtenerComprobantesConEstadoOper(nup, transaccion, "2", "39", "100", "11", 16);
	}

	@Async
	@Override
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCOpenBankAsync(String nup, TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesPMCOpenBank(nup, transaccion));
	}

	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPMCOpenBank(String nup, TransaccionDTO transaccion) {
		return obtenerComprobantesConEstadoOper(nup, transaccion, "92", "1", "200", "99", 17);
	}

	private ComprobanteDTO obtenerComprobanteDTOParaPMCOpenBankCuenta(ComprobantePMCOpenBank comprobante) {
		ComprobanteDTO comprobantePMC = obtenerComprobanteDTOParaPMCOpenBank(comprobante);
		comprobantePMC.setearMedioDePagoCuentaPesos(comprobante.getTransferencia().getTipoCuentaDebito(),
				comprobante.getTransferencia().getSucursalCuentaDebito(),
				comprobante.getTransferencia().getNumeroCuentaDebito());
		return comprobantePMC;
	}

	private ComprobanteDTO obtenerComprobanteDTOParaPMCOpenBank(ComprobantePMCOpenBank comprobante) {
		ComprobanteDTO comprobantePMCOpenBank = new ComprobanteDTO();
		comprobantePMCOpenBank.setFecha(ISBANStringUtils.formatearFecha(comprobante.getTransferencia().getFechaHoraPago(),
				ISBANStringUtils.FORMATO_FECHA_IATX + HORA_MINUTO_SEGUNDO));
		comprobantePMCOpenBank.setTipoOperacion(TipoOperacionComprobanteEnum.SCOMP_PAGO_SERVICIOS);
		comprobantePMCOpenBank.setHistorial(HistorialComprobanteEnum.PAGO_SERVICIOS_B);
		comprobantePMCOpenBank.setDestinatario(comprobante.getTransferencia().getEmpresa());
		if (!ISBANStringUtils.isEmptyOrNull(comprobante.getTransferencia().getImporte())) {
			String importe = comprobante.getTransferencia().getImporte().replace(",", "").replace(".", "");
			importe = importe.substring(0, importe.length() - 2) + "." + importe.substring(importe.length() - 2);
			importe = ISBANStringUtils.formatearConComaYDosDecimales(importe);
			setearImportePesosDolar(comprobante.getTransferencia().getMoneda(), importe, comprobantePMCOpenBank);
		}
		comprobantePMCOpenBank.setDetalle(obtenerDetalleComprobantePMCOpenBank(comprobante));
		return comprobantePMCOpenBank;
	}

	/**
	 * Obtener detalle comprobante PMC con deuda.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the detalle comprobante DTO
	 */
	private DetalleComprobanteDTO obtenerDetalleComprobantePMCOpenBank(ComprobantePMCOpenBank comprobante) {
		DetalleComprobanteScompOpenBank detalleOpenBank = new DetalleComprobanteScompOpenBank();
		String horaSinFormato = StringUtils.right(comprobante.getTransferencia().getFechaHoraPago(), 6);
		detalleOpenBank.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		detalleOpenBank.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_PMC_CON_DEUDA);
		detalleOpenBank.setTipoPMC(ComprobantesBOEnum.PMC_CON_DEUDA.getId());
		detalleOpenBank.setEmpresa(comprobante.getTransferencia().getEmpresa());
		String importe = comprobante.getTransferencia().getImporte().replace(",", "").replace(".", "");
		importe = importe.substring(0, importe.length() - 2) + "." + importe.substring(importe.length() - 2);
		detalleOpenBank.setImporte(new BigDecimal(importe));
		if (comprobante.getTransferencia().getFechaVencimiento() != null) {
			detalleOpenBank.setFechaVencimiento(ISBANStringUtils.formatearFecha(
					comprobante.getTransferencia().getFechaVencimiento().replace("/", ""),
					ISBANStringUtils.FORMATO_FECHA_SIN_BARRAS));
		}
		detalleOpenBank.setFactura(comprobante.getTransferencia().getLeyendaFactura());
		detalleOpenBank.setHoraDePago(StringUtils.left(horaSinFormato, 2) + ":" + StringUtils.substring(horaSinFormato, 2, 4));
		detalleOpenBank.setNroControl(comprobante.getTransferencia().getNumControl());
		detalleOpenBank.setNroTransaccion(comprobante.getTransferencia().getNroTransaccion());
		detalleOpenBank.setLabelDinamico(obtenerLabelDinamico(comprobante.getTransferencia().getEmpresa()));
		detalleOpenBank.setIdentificacion(comprobante.getTransferencia().getIdentificacion());
		detalleOpenBank.setEsPagoConDeuda(true);
		detalleOpenBank.setFechaDePago(ISBANStringUtils.formatearFecha(comprobante.getTransferencia().getFechaHoraPago(),
				ISBANStringUtils.FORMATO_FECHA_IATX + HORA_MINUTO_SEGUNDO));
		detalleOpenBank.setIdClienteEmpresa(comprobante.getTransferencia().getIdentificacion());
		return detalleOpenBank;
	}

}
