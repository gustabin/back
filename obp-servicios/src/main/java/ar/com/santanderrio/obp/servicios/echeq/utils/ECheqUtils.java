package ar.com.santanderrio.obp.servicios.echeq.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import ar.com.santanderrio.obp.generated.webservices.echeq.Aval;
import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;
import ar.com.santanderrio.obp.servicios.echeq.dto.AvalDTO;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.generated.webservices.echeq.Cesion;
import ar.com.santanderrio.obp.generated.webservices.echeq.Endoso;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.echeq.dto.CesionDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.EndosoDTO;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;

/**
 * The Class ECheqUtils.
 */
public class ECheqUtils {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ECheqUtils.class);

	/** The Constant BREAK. */
	public static final String BREAK = "<br>";

	/** The Constant MASCARA_FECHA_AMCO. */
	public static final String MASCARA_FECHA_AMCO = "yyyy-MM-dd";

	/** The Constant MASCARA_FECHA_COMPROBANTE. */
	public static final String MASCARA_FECHA_COMPROBANTE = "dd/MM/yyyy - HH:mm";

	/** The Constant MASCARA_FECHA_FRONT. */
	public static final String MASCARA_FECHA_FRONT = "dd/MM/yyyy";

	/** The Constant MASCARA_FECHA_IATX. */
	public static final String MASCARA_FECHA_IATX = "yyyy-MM-dd";
	
	/** The Constant MASCARA_FECHA_EMISION_AMCO. */
	public static final String MASCARA_FECHA_EMISION_BUSCADOR_AMCO = "yyyyMMdd";

	/** The Constant PREFIJO_PESOS. */
	public static final String PREFIJO_PESOS = "$ ";

	/** The Constant MODALIDAD_CHEQUE_A_LA_ORDEN. */
	public static final String MODALIDAD_CHEQUE_A_LA_ORDEN = "0";

	/** The Constant MODALIDAD_CHEQUE_NO_A_LA_ORDEN. */
	public static final String MODALIDAD_CHEQUE_NO_A_LA_ORDEN = "1";

	/** The Constant TIPO_CHEQUE_COMUN. */
	public static final String TIPO_CHEQUE_COMUN = "0";

	/** The Constant TIPO_CHEQUE_CRUZADO. */
	public static final String TIPO_CHEQUE_CRUZADO = "1";

	/** The Constant TIPO_CHEQUE_DIFERIDO. */
	public static final String TIPO_CHEQUE_DIFERIDO = "1";

	/** The Constant TIPO_CHEQUE_NO_CRUZADO. */
	public static final String TIPO_CHEQUE_NO_CRUZADO = "0";

	/** The Constant TIPO_ENDOSO_NEGOCIACION. */
	public static final String TIPO_ENDOSO_NEGOCIACION = "NEG";

	/** The Constant TIPO_ENDOSO_NOMINADO. */
	public static final String TIPO_ENDOSO_NOMINADO = "NOM";

	public static final String ENDOSO_ESTADO_ANULADO = "anulado";

	/** The Constant LEN_FECHA_AMCO. */
	public static final int LEN_FECHA_AMCO = MASCARA_FECHA_AMCO.length();

	public static final String AVAL_ESTADO_PENDIENTE = "PENDIENTE";

	public static final String ESTADO_CESION_CUSTODIA = "CUSTODIA";
	
	public static final String ESTADO_CADUCADOS = "CADUCADO";
	
	public static final String ESTADO_CADUCADOS_EMITIDOS = "Caducado - Certificado Emitido";

	public static final Predicate PREDICATE_AVAL_PENDIENTE = new Predicate() {
		@Override
		public boolean evaluate(Object object) {
			return AVAL_ESTADO_PENDIENTE.equals(((Aval) object).getAvalEstado());
		}
	};

	/**
	 * Instantiates a new e cheq utils.
	 */
	private ECheqUtils() {
	}

	/**
	 * Parsear fecha.
	 *
	 * @param fecha the fecha
	 * @param mascara the mascara
	 * @return the string
	 */
	public static String parsearFecha(String fecha, String mascara) {
		SimpleDateFormat sd1 = new SimpleDateFormat(MASCARA_FECHA_AMCO);
		String fechaAmco = fecha.substring(0, LEN_FECHA_AMCO);
		Date dt = null;
		try {
			dt = sd1.parse(fechaAmco);
			SimpleDateFormat sd2 = new SimpleDateFormat(mascara);
			return sd2.format(dt);
		} catch (ParseException e) {
			LOGGER.error("Error al parsear Fecha: {} -> {} to: {}.-", fechaAmco, MASCARA_FECHA_AMCO, mascara);
			return null;
		}
	}

    /**
     * Formatear fecha IATX.
     *
     * @param fecha the fecha
     * @param mascaraOrigen the mascara origen
     * @return the string
     */
    public static String formatearFechaIatx(String fecha, String mascaraOrigen) {
		SimpleDateFormat sd1 = new SimpleDateFormat(mascaraOrigen);
		Date dt = null;
		try {
			dt = sd1.parse(fecha);
			SimpleDateFormat sd2 = new SimpleDateFormat(MASCARA_FECHA_IATX);
			return sd2.format(dt);
		} catch (ParseException e) {
			LOGGER.error("Error al formatear Fecha: {} -> {} to: {}.-", fecha, mascaraOrigen, MASCARA_FECHA_IATX, e);
			return null;
		}
	}

	/**
	 * Obtener tipo cuenta echeq.
	 *
	 * @param cuenta the cuenta
	 * @return the string
	 */
	public static String obtenerTipoCuentaEcheq(Cuenta cuenta) {
		if (Cuenta.TIPOCTA_CU.equals(cuenta.getTipoCuenta())) {
			return Cuenta.TIPOCTA_CUP;
		}
		return cuenta.getTipoCuenta();
	}

	/**
	 * Obtener tipo cheque.
	 *
	 * @param fechaEmision the fecha emision
	 * @param fechaPago the fecha pago
	 * @return the string
	 * @throws ParseException the parse exception
	 */
	public static String obtenerTipoCheque(String fechaEmision, String fechaPago) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(MASCARA_FECHA_AMCO);
		Calendar calEmision = getCalendar(sdf.parse(fechaEmision.substring(0, LEN_FECHA_AMCO)));
		Calendar calPago = getCalendar(sdf.parse(fechaPago.substring(0, LEN_FECHA_AMCO)));
		return calEmision.equals(calPago) ? TIPO_CHEQUE_COMUN : TIPO_CHEQUE_DIFERIDO;
	}

	/**
	 * Obtener tipo cheque.
	 *
	 * @param fechaPago the fecha pago
	 * @return the string
	 * @throws ParseException the parse exception
	 */
	public static String obtenerTipoCheque(String fechaPago) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(MASCARA_FECHA_FRONT);
		Calendar calPago = getCalendar(sdf.parse(fechaPago));
		Calendar cal = getCalendar(new Date());
		return cal.equals(calPago) ? TIPO_CHEQUE_COMUN : TIPO_CHEQUE_DIFERIDO;
	}

	/**
	 * Eliminar espacios.
	 *
	 * @param str the str
	 * @return the string
	 */
	public static String eliminarEspacios(String str) {
		if (str != null) {
			return str.trim().replaceAll("\\s{2,}", " ");
		}
		return null;
	}

	/**
	 * Gets the calendar.
	 *
	 * @param dt the dt
	 * @return the calendar
	 */
	public static Calendar getCalendar(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	/**
	 * Es horario habilitado.
	 *
	 * @return the boolean
	 * @throws ParseException the parse exception
	 */
	public static Boolean esHorarioHabilitado() {
		String echeqHoraDesde = ContextHolder.getContext().getEnvironment().getProperty("ECHEQ.HORA.DESDE");
		String echeqHoraHasta = ContextHolder.getContext().getEnvironment().getProperty("ECHEQ.HORA.HASTA");
		if (!ISBANStringUtils.isNullOrBlank(echeqHoraDesde) && !ISBANStringUtils.isNullOrBlank(echeqHoraHasta)) {
			try {
				return ISBANStringUtils.compararHoras(echeqHoraDesde, echeqHoraHasta);
			} catch (ParseException e) {
				//Dont do anything, that type of exception is never thrown
			}
		}
		return false;
	}

	/**
	 * Obtener documento.
	 *
	 * @param documento the documento
	 * @return the string
	 */
	public static String obtenerDocumento(String documento) {
		return ISBANStringUtils.agregarGuionesANumeroCuitCuil(documento);
	}

	/**
	 * Obtener endosos.
	 *
	 * @param endosos the endosos
	 * @return the list
	 */
	public static List<EndosoDTO> obtenerEndosos(List<Endoso> endosos) {
		if (endosos == null || endosos.isEmpty()) {
			return null;
		}
		List<EndosoDTO> retorno = new ArrayList<EndosoDTO>();
		for (int i = 0; i < endosos.size(); i++) {
	    	EndosoDTO endosoDTO = new EndosoDTO();
	    	Endoso endoso = endosos.get(i);
	    	String cuit = obtenerDocumento(endoso.getBenefDocumento());
	    	endosoDTO.setCuit(endoso.getBenefDocumentoTipo() +" " + cuit);
	    	endosoDTO.setBeneficiario(endoso.getBenefRazonSocial() != null && endoso.getBenefRazonSocial().length() > 0 ? ISBANStringUtils.formateoStringPrimeraLetraMayuscula(endoso.getBenefRazonSocial()) : "");
	    	endosoDTO.setFecha(parsearFecha(endoso.getFechaHora(), MASCARA_FECHA_FRONT));
	    	endosoDTO.setTachado(ENDOSO_ESTADO_ANULADO.equals(endoso.getEstadoEndoso()));
	    	retorno.add(endosoDTO);
	    }
		return retorno;
	}
	
	/**
	 * Obtener cesiones
	 * @param cesiones
	 * @return
	 */
	public static List<CesionDTO> obtenerCesiones(List<Cesion> cesiones) {
		if (cesiones == null || cesiones.isEmpty()) {
			return null;
		}
		List<CesionDTO> retorno = new ArrayList<CesionDTO>();
		for (int i = 0; i < cesiones.size(); i++) {
	    	CesionDTO cesionesDTO = new CesionDTO();
	    	Cesion cesion = cesiones.get(i);
	    	cesionesDTO.setCesionarioDocumento(obtenerDocumento(cesion.getCesionarioDocumento() == null ? "" : cesion.getCesionarioDocumento().longValue() + ""));
	    	cesionesDTO.setCesionarioDocumentoTipo(cesion.getCesionarioDocumentoTipo());
	    	cesionesDTO.setCesionarioNombre(cesion.getCesionarioNombre() != null && cesion.getCesionarioNombre().length() > 0 ? ISBANStringUtils.formateoStringPrimeraLetraMayuscula(cesion.getCesionarioNombre()): "");
	    	cesionesDTO.setCesionarioDomicilio(cesion.getCesionarioDomicilio());
	    	cesionesDTO.setFechaEmisionCesion(parsearFecha(cesion.getFechaEmisionCesion(), MASCARA_FECHA_FRONT));
	    	retorno.add(cesionesDTO);
	    }
		return retorno;
	}

	/**
	 * Verificar cuentas habilitadas.
	 *
	 * @param operacion the operacion
	 * @return the boolean
	 */
	public static Boolean verificarCuentasHabilitadas(OperacionEcheqEnum operacion) {
		return OperacionEcheqEnum.ALTA.equals(operacion) || OperacionEcheqEnum.CUSTODIAR.equals(operacion) || OperacionEcheqEnum.DEPOSITAR.equals(operacion);
	}

	/**
	 * Obtener cuentas habilitadas.
	 *
	 * @param cliente the cliente
	 * @param cuentaBO the cuenta BO
	 * @param cuentaParamsHab the cuenta params hab
	 * @return the list
	 */
	public static List<Cuenta> obtenerCuentasHabilitadas(Cliente cliente, CuentaBO cuentaBO, List<String> cuentaParamsHab) {
		List<Cuenta> cuentasHabilitadasOperacion = new ArrayList<Cuenta>();
		if (cuentaParamsHab != null) {
			for (Cuenta cuenta : cliente.getCuentas()) {
				if (evaluaCuenta(cliente, cuenta, cuentaBO, cuentaParamsHab)) {
					cuentasHabilitadasOperacion.add(cuenta);
				}
			}
		}
		return cuentasHabilitadasOperacion;
	}

	/**
	 * Gets the params cuentas habilitadas.
	 *
	 * @param prefix the prefix
	 * @param propertyMap the property map
	 * @return the params cuentas habilitadas
	 */
	public static List<String> getParamsCuentasHabilitadas(String prefix, PropertyMap propertyMap) {
		StringBuilder tipos = new StringBuilder();
		Map<String, Object> props = propertyMap.getProperties();
        for (String key : props.keySet()) {
        	StringBuilder tipo = new StringBuilder();
        	String propValue = props.get(key).toString().trim();
        	if (key.contains(prefix) && "1".equals(propValue)) {
        		if (tipos.length() > 0) {
        			tipos.append(",");
        		}
        		String params = key.substring(prefix.length() + 1).trim();
        		tipo.append(params.replaceAll("\\.", "\\|"));
        		tipos.append(tipo);
        	}
        }
        return Arrays.asList(StringUtils.split(tipos.toString(), ','));
	}

	/**
	 * Evalua cuenta.
	 *
	 * @param cliente the cliente
	 * @param cuenta the cuenta
	 * @param cuentaBO the cuenta BO
	 * @param cuentaParamsHab the cuenta params hab
	 * @return true, if successful
	 */
	private static boolean evaluaCuenta(Cliente cliente, Cuenta cuenta, CuentaBO cuentaBO, List<String> cuentaParamsHab) {
		return !cuenta.isCuentaCerrada()
				&& matchCuentaParams(cliente, cuenta, cuentaBO, cuentaParamsHab);
	}

	/**
	 * Match cuenta params.
	 *
	 * @param cliente the cliente
	 * @param cuenta the cuenta
	 * @param cuentaBO the cuenta BO
	 * @param cuentaParamsHab the cuenta params hab
	 * @return true, if successful
	 */
	private static boolean matchCuentaParams(Cliente cliente, Cuenta cuenta, CuentaBO cuentaBO, List<String> cuentaParamsHab) {
		boolean match = false;
		String prodAltair = cuenta.getProductoAltair();
		String subProdAltair = cuenta.getSubproductoAltair();
		String monedaAltair = cuenta.getMonedaAltair();
		String codTitularidad = cuenta.getCodigoTitularidad();
		for (String ctaParHab : cuentaParamsHab) {
			StringTokenizer st = new StringTokenizer(ctaParHab, "\\|");
			String prodAltairHab = st.nextToken().trim();
			String subProdAltairHab = st.nextToken().trim();
			String monedaAltairHab = st.nextToken().trim();
			String codTitularidadHab = st.nextToken().trim();
			String indSobregiroHab = "*";
			if (st.hasMoreTokens()) {
				indSobregiroHab = st.nextToken();
			}
			if (prodAltairHab.equals(prodAltair) && (subProdAltairHab.equals(subProdAltair) || "*".equals(subProdAltairHab))
					&& (monedaAltairHab.equals(monedaAltair) || "*".equals(monedaAltairHab))
					&& codTitularidadHab.equals(codTitularidad) && ("*".equals(indSobregiroHab)
							|| evaluarIndSobregiro(cliente, cuenta, cuentaBO, indSobregiroHab))) {
				match = true;
				break;
			}
		}
		return match;
	}

	/**
	 * Evaluar ind sobregiro.
	 *
	 * @param cliente the cliente
	 * @param cuenta the cuenta
	 * @param cuentaBO the cuenta BO
	 * @param indSobregiroHab the ind sobregiro hab
	 * @return true, if successful
	 */
	private static boolean evaluarIndSobregiro(Cliente cliente, Cuenta cuenta, CuentaBO cuentaBO, String indSobregiroHab) {
		return indSobregiroHab.equalsIgnoreCase(obtenerIndSobregiro(cliente, cuenta, cuentaBO));
	}

	/**
	 * Obtener ind sobregiro.
	 *
	 * @param cliente the cliente
	 * @param cuenta the cuenta
	 * @param cuentaBO the cuenta BO
	 * @return the string
	 */
	private static String obtenerIndSobregiro(Cliente cliente, Cuenta cuenta, CuentaBO cuentaBO) {
		Respuesta<ResumenDetalleCuenta> respuestaDetalle;
		try {
			respuestaDetalle = cuentaBO.obtenerCuenta(cliente,
					ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
		} catch (BusinessException e) {
			return "";
		}
		if (EstadoRespuesta.OK.equals(respuestaDetalle.getEstadoRespuesta())) {
			ResumenDetalleCuenta detalleCuenta = respuestaDetalle.getRespuesta();
			return detalleCuenta.getIndicadorSobregiro();
		}
		return "";
	}
	
	/**
	 * Convierte la fecha con la mascara del front a la mascara que interpreta amco
	 * 
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public static String obtenerFechaEmision(String fecha) {
		String retorno = null;
		if(fecha != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(MASCARA_FECHA_FRONT);
			try {
				Date dateFormat = sdf.parse(fecha);
				sdf.applyPattern(MASCARA_FECHA_EMISION_BUSCADOR_AMCO);
				retorno = sdf.format(dateFormat);
			} catch (ParseException e) {
				LOGGER.error("Error al parsear Fecha: {} -> {} to: {}.-", fecha, MASCARA_FECHA_FRONT, MASCARA_FECHA_AMCO, e);
			}
		}
		return retorno;
	}
	
	public static Endoso getFirstEndosoByFilter(List<Endoso> endosos, Predicate predicate) {
		for(Endoso endoso : endosos) {
			if(predicate.evaluate(endoso)) {
				return endoso;
			}
		}
		return null;
	}

	public static List<AvalDTO> mapAvalistas(List<Aval> avalistas) {
		if (avalistas == null || avalistas.isEmpty()) {
			return new ArrayList<AvalDTO>();
		}

		final List<AvalDTO> avalDTOList = new ArrayList<AvalDTO>();
		for(Aval aval : avalistas) {
			AvalDTO avalDto = new AvalDTO();
			avalDto.setTipoDocumentoAvalista(aval.getAvalDocumentoTipo());
			avalDto.setDocumentoAvalista(aval.getAvalDocumento());
			avalDto.setNombreAvalista(aval.getAvalRazonSocial());
			avalDto.setEstadoAval(aval.getAvalEstado());
			avalDto.setFechaAval(aval.getAvalFecha());
			avalDto.setDomicilioAvalista(aval.getAvalDomicilio());
			avalDTOList.add(avalDto);
		}
		return avalDTOList;
	}

	public static Boolean isAvalista(Cliente cliente, Aval aval) {
		return isAvalistaPredicate(cliente).evaluate(aval);
	}

	public static Boolean isAvalista(Cliente cliente, Cheque cheque) {
		for(Aval aval : cheque.getAvalistas()) {
			if(isAvalista(cliente, aval)){
				return true;
			}
		}
		return false;
	}

	public static Predicate isAvalistaPredicate(final Cliente cliente) {
		return new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				return object != null &&
						((Aval) object).getAvalDocumento().equals(cliente.getNumeroCUILCUIT().replace("-", ""));
			}
		};
	}

	public static Boolean isBeneficiario(Cliente cliente, Cheque cheque) {
		return isBeneficiarioPredicate(cliente).evaluate(cheque);
	}

	public static Predicate isBeneficiarioPredicate(final Cliente cliente) {
		return new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				return object != null &&
						((Cheque) object).getTenencia().getBeneficiarioDocumento()
								.equals(cliente.getNumeroCUILCUIT().replace("-", ""));
			}
		};
	}

	public static Aval getAvalPendiente(Cheque cheque) {
		List<Aval> avalList = cheque.getAvalistas() != null ? cheque.getAvalistas() : new ArrayList<Aval>();
		for(Aval aval : avalList) {
			if (PREDICATE_AVAL_PENDIENTE.evaluate(aval)) {
				return aval;
			}
		}
		return null;
	}
}
