/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.LimitesFiltrosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.FiltroComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesViewIn;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.TransaccionViewIn;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class LimitesFiltrosBOImpl.
 * 
 */
@Component
public class LimitesFiltrosBOImpl implements LimitesFiltrosBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LimitesFiltrosBOImpl.class);

	/** The error parceo fechas. */
	private static final String ERROR_PARCEO_FECHAS = "Error validando las fechas del filtro: ";

	/** The anios filtros. */
	@Value("${ANIOS.FILTROS}")
	private String aniosFiltros;

	/** The parametrizacion invocacion scomp. */
	@Value("${PARAMETRIZACION.INVOCACION.SCOMP}")
	private String parametrizacionInvocacionScomp;

	/** The parametrizacion invocacion iatx. */
	@Value("${PARAMETRIZACION.INVOCACION.IATX}")
	private String parametrizacionInvocacionIatx;

	/** The limite datos pago servicios. */
	@Value("${LIMITE.DATOS.PAGO.SERVICIOS}")
	private String limiteDatosPagoServicios;

	/** The anios limite de pago mis cuentas. */
	@Value("${ANIOS.PMC}")
	private String aniosPMC;

	/** The anios limite para el llamado de pago de tarjeta. */
	@Value("${ANIOS.PAGOTARJETA}")
	private String aniosPagoDeTarjeta;

	/** The anios debitos automaticos. */
	@Value("${ANIOS.DEBITOSAUTOMATICOS}")
	private String aniosDebitosAutomaticos;

	/** The anios filtros. */
	@Value("${ANIOS.PAGOSUELDOSHONORARIOS}")
	private String aniosPagoDeSueldosHonorarios;

	/** The anios filtros. */
	@Value("${ANIOS.TRANSFERENCIAS}")
	private String aniosTransferencias;

	/** The anios compra venta. */
	@Value("${ANIOS.COMPRAVENTA}")
	private String aniosCompraVenta;

	/** The anios compra venta. */
	@Value("${ANIOS.PAGOCOMPRAS}")
	private String aniosPagoCompras;

	/** The transaccion. */
	private TransaccionEnum transaccion;

	/**
	 * Crea el FiltroComprobanteDTO a devolver con completo con los datos
	 * correspondientes.
	 *
	 * @param viewIn
	 *            the view in
	 * @param cliente
	 *            the cliente
	 * @return the date
	 */
	@Override
	public FiltroComprobanteDTO obtenerFiltroComprobanteDTO(ComprobantesViewIn viewIn, Cliente cliente) {
		FiltroComprobanteDTO filtro;
		try {
			filtro = new FiltroComprobanteDTO(cliente);
			List<TransaccionDTO> lista = obtenerListaTransacciones(viewIn, filtro);
			filtro.setTransacciones(lista);
		} catch (ParseException e) {
			LOGGER.error("Error de parseo: " + e.toString());
			filtro = null;
		}
		return filtro;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.LimitesFiltrosBO#
	 * obtenerLimitesDeFecha()
	 */
	@Override
	public List<String> obtenerLimitesDeFecha() {
		return Arrays.asList(aniosPMC, aniosTransferencias, aniosPagoDeTarjeta);
	}

	/**
	 * Convierte la informacion guardada en el viewIn en la lista de TransaccionDTO
	 * que tiene las fechas desde y hasta correctas para cada tipo de transaccion ,
	 * y guarda los datos del error en FiltroComprobanteDTO.
	 *
	 * @param viewIn
	 *            the view in
	 * @param filtro
	 *            the filtro
	 * @return the list
	 */
	private List<TransaccionDTO> obtenerListaTransacciones(ComprobantesViewIn viewIn, FiltroComprobanteDTO filtro) {
		List<TransaccionDTO> lista = new ArrayList<TransaccionDTO>();
		for (TransaccionViewIn transaccionIn : viewIn.getTransacciones()) {
			if (filtroFechaEsValido(viewIn.getFechaDesde(), viewIn.getFechaHasta())) {
				lista.add(generarTransaccionDTO(transaccionIn, viewIn.getFechaDesde(), viewIn.getFechaHasta(),
						viewIn.getEmpresa(), viewIn.getImporteDesde(), viewIn.getImporteHasta(), filtro));
			} else {
				filtro.setFechaError(true);
			}
		}
		return lista;
	}

	/**
	 * Corrobora si las fechas pasadas por parametros tienen el orden correcto(fecha
	 * desde deberia estar antes de fechaHasta).
	 *
	 * @param fechaDesdeString
	 *            the fecha desde string
	 * @param fechaHastaString
	 *            the fecha hasta string
	 * @return the boolean
	 */
	private Boolean filtroFechaEsValido(String fechaDesdeString, String fechaHastaString) {
		try {
			Date fechaDesde = ISBANStringUtils.parsearFechaConAnio(fechaDesdeString);
			Date fechaHasta = ISBANStringUtils.parsearFechaConAnio(fechaHastaString);
			return !fechaHasta.before(fechaDesde);
		} catch (ParseException e) {
			LOGGER.error(ERROR_PARCEO_FECHAS + e);
			return false;
		}
	}

	/**
	 * Obtener fecha basado en transaccion. La fecha es una property
	 *
	 * @param idTransaccion
	 *            the id transaccion
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	private Date obtenerFechaBasadoEnTransaccion(Integer idTransaccion) throws ParseException {
		Date fechaMinima;
		switch (idTransaccion) {
		case 0:
		case 7:
		case 10:
			fechaMinima = ISBANStringUtils.parsearFechaConAnio(parametrizacionInvocacionScomp);
			fechaMinima = ultima(fechaMinima, obtenerFechaInicioRangoPermitido(aniosPMC));
			break;
		case 1:
			fechaMinima = obtenerFechaInicioRangoPermitido(aniosDebitosAutomaticos);
			break;
		case 2:
		case 9:
			fechaMinima = obtenerFechaInicioRangoPermitido(aniosTransferencias);
			break;
		case 3:
			fechaMinima = obtenerFechaInicioRangoPermitido(aniosPagoDeTarjeta);
			break;
		case 4:
			fechaMinima = ultima(obtenerFechaInicioRangoPermitido(aniosPagoDeSueldosHonorarios),
					obtenerFechaInicioRangoPermitido(aniosCompraVenta));
			break;
		case 5:
			fechaMinima = obtenerFechaInicioRangoPermitido(aniosPagoDeSueldosHonorarios);
			break;
		case 6:
			fechaMinima = obtenerFechaInicioRangoPermitido(aniosCompraVenta);
			break;
		case 8:
			fechaMinima = obtenerFechaInicioRangoPermitido(aniosPagoCompras);
			break;
		default:
			throw new ParseException("error en idTransaccion", 123);
		}
		return ultima(obtenerFechaInicioRangoPermitido(aniosFiltros), fechaMinima);
	}

	/**
	 * Safely compare two dates, null being considered "greater" than a Date.
	 *
	 * @param a
	 *            the a
	 * @param b
	 *            the b
	 * @return the earliest of the two
	 */
	public static Date ultima(Date a, Date b) {
		return a == null ? b : (b == null ? a : (a.after(b) ? a : b));
	}

	/**
	 * Crea un Date con la fecha actual - la cantidad de años indicada como
	 * parametro.
	 *
	 * @param anios
	 *            the anios
	 * @return the date
	 */
	public Date obtenerFechaInicioRangoPermitido(String anios) {
		try {
			Integer y10 = Integer.parseInt(anios);
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int mYear = cal.get(Calendar.YEAR);
			int year2 = mYear - y10;
			cal.set(Calendar.YEAR, year2);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			return cal.getTime();
		} catch (NumberFormatException e) {
			LOGGER.error("Error validando las fechas del filtro:" + e);
			return null;
		}
	}

	/**
	 * Genera transaccion DTO. Realiza todos los calculos necesarios para elegir la
	 * fecha correcta dependiendo de los servicios a llamar, Se supone las fechas
	 * desde y hasta deberian estar encima de una fecha tope del servicio, en caso
	 * de no estarlo pero tener parte del intervalo dentro la nueva fecha desde
	 * seria el tope de servicio. En caso de tener este error guarda el estado en
	 * FiltroComprobanteDTO
	 *
	 * @param transaccionIn
	 *            the transaccion in
	 * @param fechaDesdeString
	 *            the fecha desde string
	 * @param fechaHastaString
	 *            the fecha hasta string
	 * @param empresa
	 *            the empresa
	 * @param importeDesde
	 *            the importe desde
	 * @param importeHasta
	 *            the importe hasta
	 * @param filtro
	 *            the filtro
	 * @return the transaccion DTO
	 */
	private TransaccionDTO generarTransaccionDTO(TransaccionViewIn transaccionIn, String fechaDesdeString,
			String fechaHastaString, String empresa, BigDecimal importeDesde, BigDecimal importeHasta,
			FiltroComprobanteDTO filtro) {
		TransaccionDTO transaccionDTO = null;
		try {
			Date fechaBase = obtenerFechaEnHorario(obtenerFechaBasadoEnTransaccion(transaccionIn.getIdTransaccion()),
					true);
			Date fechaDesde = obtenerFechaEnHorario(ISBANStringUtils.parsearFechaConAnio(fechaDesdeString), true);
			Date fechaHasta = obtenerFechaEnHorario(ISBANStringUtils.parsearFechaConAnio(fechaHastaString), false);
			if (importeDesde == null && importeHasta == null) {
				transaccionDTO = new TransaccionDTO(transaccionIn.getIdTransaccion(), ultima(fechaBase, fechaDesde),
						ultima(fechaBase, fechaHasta), empresa);
			} else {
				transaccionDTO = new TransaccionDTO(transaccionIn.getIdTransaccion(), ultima(fechaBase, fechaDesde),
						ultima(fechaBase, fechaHasta), empresa, importeDesde,
						importeHasta);
			}

			filtro.setExcedidoLimiteParcial(fechaBase.after(fechaHasta) || fechaBase.after(fechaDesde));
			filtro.setFechaError(false);
			filtro.setComprobantesExcedidos(false);
		} catch (ParseException e) {
			LOGGER.error(ERROR_PARCEO_FECHAS + e);
			filtro.setFechaError(true);
		}
		return transaccionDTO;
	}

	/**
	 * Obtener fecha en horario.Si la fecha es una fechaDesde deberia arrancar a las
	 * 0:00:00 , si es fechaHasta lo opuesto para asi cubrir el rango de
	 * comprobantes
	 *
	 * @param date
	 *            the date
	 * @param esInicial
	 *            the es inicial
	 * @return the date
	 */
	private Date obtenerFechaEnHorario(Date date, boolean esInicial) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (esInicial) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
		}
		return calendar.getTime();
	}

	/**
	 * Gets the anios filtros.
	 * 
	 * @return the anios filtros
	 */
	public String getAniosFiltros() {
		return aniosFiltros;
	}

	/**
	 * Sets the anios filtros.
	 * 
	 * @param aniosFiltros
	 *            the new anios filtros
	 */
	public void setAniosFiltros(String aniosFiltros) {
		this.aniosFiltros = aniosFiltros;
	}

	/**
	 * Gets the parametrizacion invocacion scomp.
	 * 
	 * @return the parametrizacion invocacion scomp
	 */
	public String getParametrizacionInvocacionScomp() {
		return parametrizacionInvocacionScomp;
	}

	/**
	 * Sets the parametrizacion invocacion scomp.
	 * 
	 * @param parametrizacionInvocacionScomp
	 *            the new parametrizacion invocacion scomp
	 */
	public void setParametrizacionInvocacionScomp(String parametrizacionInvocacionScomp) {
		this.parametrizacionInvocacionScomp = parametrizacionInvocacionScomp;
	}

	/**
	 * Gets the parametrizacion invocacion iatx.
	 * 
	 * @return the parametrizacion invocacion iatx
	 */
	public String getParametrizacionInvocacionIatx() {
		return parametrizacionInvocacionIatx;
	}

	/**
	 * Sets the parametrizacion invocacion iatx.
	 * 
	 * @param parametrizacionInvocacionIatx
	 *            the new parametrizacion invocacion iatx
	 */
	public void setParametrizacionInvocacionIatx(String parametrizacionInvocacionIatx) {
		this.parametrizacionInvocacionIatx = parametrizacionInvocacionIatx;
	}

	/**
	 * Gets the limite datos pago servicios.
	 * 
	 * @return the limite datos pago servicios
	 */
	public String getLimiteDatosPagoServicios() {
		return limiteDatosPagoServicios;
	}

	/**
	 * Sets the limite datos pago servicios.
	 * 
	 * @param limiteDatosPagoServicios
	 *            the new limite datos pago servicios
	 */
	public void setLimiteDatosPagoServicios(String limiteDatosPagoServicios) {
		this.limiteDatosPagoServicios = limiteDatosPagoServicios;
	}

	/**
	 * Gets the transaccion.
	 * 
	 * @return the transaccion
	 */
	public TransaccionEnum getTransaccion() {
		return transaccion;
	}

	/**
	 * Sets the transaccion.
	 * 
	 * @param transaccion
	 *            the new transaccion
	 */
	public void setTransaccion(TransaccionEnum transaccion) {
		this.transaccion = transaccion;
	}

}
