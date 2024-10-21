/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.transferencias.entities.DatosTransferenciaDestino;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaDetalleView;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.IPAddress;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.comun.web.view.AyudaView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.FrecuenciaTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.TransferenciaManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * Esta clase contiene metodos utiles y comunes para la creacion y formateo de
 * Transferencias.
 *
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since May 23, 2017
 */
public final class TransferenciaUtil {

	/** The Constant ERROR_AL_PARSEAR_LA_FECHA. */
	private static final String ERROR_AL_PARSEAR_LA_FECHA = "Error al parsear la fecha.";

	/** The Constant MSJ_INFO_GUARDANDO_HASH_EN_SESION. */
	private static final String MSJ_INFO_GUARDANDO_HASH_EN_SESION = "Se guarda el hash en sesion.";

	/** The Constant MSJ_INFO_CREANDO_HASH_ATRIBUTOS. */
	private static final String MSJ_INFO_CREANDO_HASH_ATRIBUTOS = "Creando hash de los atributos...";

	/** The Constant LAS_CUENTA_DESTINO_ES_NULA_O_VACIA. */
	private static final String LAS_CUENTA_DESTINO_ES_NULA_O_VACIA = "Las cuenta destino es nula o vacia";

	/** The Constant LA_TRANSFERENCIA_ES_HACIA_EL_MISMO_BANCO_RIO_RIO. */
	private static final String LA_TRANSFERENCIA_ES_HACIA_EL_MISMO_BANCO_RIO_RIO = "La transferencia es hacia el mismo banco, RIO/RIO";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaUtil.class);

	/** The Constant DIGITO_MONEDA_PESOS. */
	private static final String DIGITO_MONEDA_PESOS = "0";

	/** The Constant DIGITO_TIPO_CAJA_AHORRO. */
	private static final String DIGITO_TIPO_CAJA_AHORRO = "3";

	/** The Constant DIGITO_TIPO_CUENTA_CORRIENTE. */
	private static final String DIGITO_TIPO_CUENTA_CORRIENTE = "2";

	/** The Constant DIGITO_CUENTA_UNICA_TIPO_Y_MONEDA. */
	private static final String DIGITO_CUENTA_UNICA_TIPO_Y_MONEDA = "8";

	/** The Constant MONEDA_CUENTA_CBU_OFFSET. */
	private static final int MONEDA_CUENTA_CBU_OFFSET = 9;

	/** The Constant TIPO_CUENTA_CBU_OFFSET. */
	private static final int TIPO_CUENTA_CBU_OFFSET = 8;

	/** The Constant GUION_MEDIO. */
	private static final String GUION_MEDIO = "-";

	/** The Constant CARACTERES_PATTERN. */
	private static final String CARACTERES_PATTERN = "([A-Za-z0-9\\_\\-\\.\\s\\n\\Ã‘])*";

	/** The Constant MSJ_INFO_FECHA_NULA. */
	private static final String MSJ_INFO_FECHA_NULA = "La fecha de ejecucion es nula.";

	/** The Constant FORMATO_FECHA. */
	private static final String FORMATO_FECHA = "dd/MM/yyyy";

	/** The Constant MSJ_INFO_VALIDANDO_FORMATO_CUENTA. */
	private static final String MSJ_INFO_VALIDANDO_FORMATO_CUENTA = "Validando formato cuenta...";

	/** The Constant MSJ_INFO_FORMATO_CUENTA_VALIDA. */
	private static final String MSJ_INFO_FORMATO_CUENTA_VALIDA = "Formato cuenta valida.";

	/** The Constant MSJ_INFO_FORMATO_CUENTA_INVALIDA. */
	private static final String MSJ_INFO_FORMATO_CUENTA_INVALIDA = "Formato cuenta invalida.";

	/** The Constant STRING_VACIO. */
	private static final String STRING_VACIO = "";

	/** The Constant TC_REC_USS. */
	private static final int TC_REC_USS = 12;

	/** The Constant TC_CUD. */
	private static final int TC_CUD = 10;

	/** The Constant TC_CAD. */
	private static final int TC_CAD = 4;

	/** The Constant TC_CCP. */
	private static final int TC_CCP = 0;

	/** The Constant TC_CCD. */
	private static final int TC_CCD = 3;

	/** The Constant TC_CU. */
	private static final int TC_CU = 2;

	/** The Constant TC_REC_PESOS. */
	private static final int TC_REC_PESOS = 11;

	/** The Constant TC_CAP. */
	private static final int TC_CAP = 1;

	/** The Constant TC_CUP. */
	private static final int TC_CUP = 9;

	/** The Constant PESOS. */
	private static final String PESOS = "Pesos";
	/** The Constant DOLARES. */
	private static final String DOLARES = "Dolares";

	/** The Constant REALIZO. */
	private static final String REALIZO = "realizó";

	/** The Constant PROGRAMO. */
	private static final String PROGRAMO = "programó";

	/** The Constant TIMEOUT_EXCEPTION. */
	public static final String TIMEOUT_EXCEPTION = "Se envió la transacción al CICS pero no se recibió respuesta";
	
	/** The transferencia msg ayuda importemax. */
	public static final String TRANSF_MSG_AYUDA_IMPORTE_MAX = "<p><b>Límites de transferencias por día y por cuenta </b><p>Los límites de montos a transferir por día, por cuenta, son los siguientes:<br><ul><li>Compra de dólares: u$s{0}</li><li>Transferencias terceros otros bancos: ${1}</li><li>Transferencias terceros Santander: ${2}</li><li>Transferencias entre cuentas propias: ${3}</li><li>Transferencias en dólares: u$s{4}</li><li>Transferencias por CVU: ${6}</li><li>Límite general del canal: ${5} (este límite contempla que un cliente no puede transaccionar por el canal un importe superior a éste entre todas las operaciones de OBP)</li></ul>";

	/** The Constant LONGITUD_NRO_CUENTA. */
	private static final int LONGITUD_NRO_CUENTA = 16;

	/** The Constant ERROR_SIN_REITENTO_36. */
	public static final int ERROR_SIN_REITENTO_36 = 10000036;

	/** The Constant ERROR_SIN_REITENTO_57. */
	public static final int ERROR_SIN_REITENTO_57 = 10000057;

	/** The Constant ERROR_SIN_REITENTO_66. */
	public static final int ERROR_SIN_REINTENTO_66 = 10000066;

	/** ERROR USUARIO/TARJETA INEXISTENTE error 10000050. */
	public static final int ERROR_USUARIOTARJETA_INEXISTENTE_50 = 10000050;

	/** The Constant ERROR_USUARIOTARJETA_INEXISTENTE_56. */
	public static final int ERROR_USUARIOTARJETA_INEXISTENTE_56 = 10000056;

	/** The Constant MSG_ERROR_MONEDA_INVALIDA_CVU. */
	public static final String MSG_ERROR_MONEDA_INVALIDA_CVU = "Debe seleccionarse la moneda Pesos para destinatarios con CVU.";

	/** The Constant CUENTA_UNICA_CODIGO. */
	private static final String CUENTA_UNICA_CODIGO = "02";
	
	/** The Constant DECIMALES. */
	private static final String DECIMALES = ".00";

	/** The Constant ERROR_TRANSFERENCIA_CVU_IMPORTE_MAX. */
	private static final String ERROR_TRANSFERENCIA_CVU_IMPORTE_MAX = "ERROR en TRANSF POR CVU: importe supera al maximo permitido.";

	/** The Constant ERROR_TRANSFERENCIA_CVU_MONEDA_INVALIDA. */
	private static final String ERROR_TRANSFERENCIA_CVU_MONEDA_INVALIDA = "ERROR en TRANSF POR CVU: moneda invalida.";

	/**
	 * Instantiates a new transferencia util.
	 */
	private TransferenciaUtil() {
		// nothing
	}

	/**
	 * Este metodo retorna la moneda disponible para transferir.
	 *
	 * @param tienePesos
	 *            the tiene pesos
	 * @param tieneDolares
	 *            the tiene dolares
	 * @param haciaOtroBanco
	 *            the hacia otro banco
	 * @param tipoCuentaDestino
	 *            the tipo cuenta destino
	 * @return the string
	 * @throws BusinessException
	 *             the business exception
	 */
	public static List<String> armarListaDeMonedas(boolean tienePesos, boolean tieneDolares, boolean haciaOtroBanco,
			TipoCuenta tipoCuentaDestino) throws BusinessException {
		boolean destinoPesos = false;
		boolean destinoDolares = false;
		if (!haciaOtroBanco) {
			LOGGER.info(LA_TRANSFERENCIA_ES_HACIA_EL_MISMO_BANCO_RIO_RIO);
			if (tipoCuentaDestino == null) {
				throw new BusinessException(LAS_CUENTA_DESTINO_ES_NULA_O_VACIA);
			}
			// resuelvo monedas destino posibles
			switch (tipoCuentaDestino) {
			case CAJA_AHORRO_DOLARES:
			case CUENTA_CORRIENTE_DOLARES:
				destinoDolares = true;
				break;
			case CAJA_AHORRO_PESOS:
			case CUENTA_CORRIENTE_PESOS:
				destinoPesos = true;
				break;
			case CUENTA_UNICA:
				destinoPesos = true;
				destinoDolares = true;
				break;
			default:
				destinoPesos = false;
				destinoDolares = false;
				break;
			}
		} else {
			LOGGER.info("La transferencia es HACIA OTROS BANCOS");
			// hacia otros bancos, no puedo determinar la moneda destino
			destinoPesos = true;
			destinoDolares = true;
		}

		List<String> resultado = new ArrayList<String>();
		if (destinoPesos && tienePesos) {
			resultado.add(PESOS);
		}
		if (destinoDolares && tieneDolares) {
			resultado.add(DOLARES);
		}

		// workaround por defecto pesos
		if (resultado.isEmpty()) {
			resultado.add(PESOS);
		}
		return resultado;
	}

	/**
	 * Obtener cuentas validas para transferir.
	 *
	 * @param clienteOrigen
	 *            the cliente origen
	 * @return the list
	 */
	public static List<Cuenta> obtenerCuentasValidasParaTransferir(Cliente clienteOrigen) {
		List<Cuenta> cuentas = clienteOrigen.getCuentas();
		List<Cuenta> cuentasFiltradas = new ArrayList<Cuenta>();
		for (Cuenta cuenta : cuentas) {
			TipoCuenta tipoCuentaEnum = cuenta.getTipoCuentaEnum();
			if (tipoCuentaEnum == null) {
				continue;
			}
			switch (tipoCuentaEnum) {
			case CUENTA_CORRIENTE_DOLARES:
			case CUENTA_CORRIENTE_PESOS:
			case CAJA_AHORRO_DOLARES:
			case CAJA_AHORRO_PESOS:
			case CUENTA_UNICA:
			case CUENTA_UNICA_PESOS:
			case CUENTA_UNICA_DOLARES:
				cuentasFiltradas.add(cuenta);
				break;
			default:
				break;
			}
		}
		List<Cuenta> cuentasBP = clienteOrigen.getCuentasPrivadas();
		for (Cuenta cuenta : cuentasBP) {
			TipoCuenta tipoCuentaEnum = cuenta.getTipoCuentaEnum();
			if (tipoCuentaEnum == null) {
				continue;
			}
			switch (tipoCuentaEnum) {
			case CUENTA_CORRIENTE_DOLARES:
			case CUENTA_CORRIENTE_PESOS:
			case CAJA_AHORRO_DOLARES:
			case CAJA_AHORRO_PESOS:
			case CUENTA_UNICA:
			case CUENTA_UNICA_PESOS:
			case CUENTA_UNICA_DOLARES:
				cuentasFiltradas.add(cuenta);
				break;
			default:
				break;
			}
		}
		
		return cuentasFiltradas;
	}

	/**
	 * Gets the monedas validas para transferir view.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoCuentaDestino
	 *            the tipo cuenta destino
	 * @param haciaOtroBanco
	 *            the hacia otro banco
	 * @return the monedas validas para transferir view
	 * @throws BusinessException
	 *             the business exception
	 */
	public static List<String> getMonedasValidasParaTransferirView(Cliente cliente, TipoCuenta tipoCuentaDestino,
			boolean haciaOtroBanco) throws BusinessException {
		List<Cuenta> cuentas = TransferenciaUtil.obtenerCuentasValidasParaTransferir(cliente);
		int nroCuentasEnPesos = 0;
		int nroCuentasEnDolares = 0;
		nroCuentasEnPesos = TransferenciaUtil.obtenerCantidadDeCuentasPesos(cuentas);
		nroCuentasEnDolares = TransferenciaUtil.obtenerCantidadDeCuentasDolares(cuentas);
		boolean tienePesos = nroCuentasEnPesos > 0;
		boolean tieneDolares = nroCuentasEnDolares > 0;
		return armarListaDeMonedas(tienePesos, tieneDolares, haciaOtroBanco, tipoCuentaDestino);
	}

	/**
	 * Retorna la primer cuenta que cumple con el tipo de moneda. Primero se
	 * debe filtrar la lista de cuentas.
	 * 
	 * @param cuentas
	 *            the lista cuentas adhesion debito view list
	 * @param monedaDestino
	 *            the moneda destino, moneda ingresada
	 * @return the respuesta cuenta con la misma moneda o respuesta vacia
	 */
	public static Cuenta obtenerPrimerCuentaConMonedaDestino(List<Cuenta> cuentas, String monedaDestino) {
		DivisaEnum moneda = null;
		if (monedaDestino.equalsIgnoreCase(PESOS) || monedaDestino.equalsIgnoreCase(DivisaEnum.PESO.getMoneda())) {
			moneda = DivisaEnum.PESO;
		} else if (monedaDestino.equalsIgnoreCase(DOLARES)
				|| monedaDestino.equalsIgnoreCase(DivisaEnum.DOLAR.getMoneda())) {
			moneda = DivisaEnum.DOLAR;
		}
		if (DivisaEnum.PESO.equals(moneda)) {
			return verificandoSiExistenCuentasEnPeso(cuentas);
		} else {
			return verificandoSiExistenCuentasEnDolares(cuentas);
		}
	}

	/**
	 * Verificando si existen cuentas en dolares.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the cuenta
	 */
	private static Cuenta verificandoSiExistenCuentasEnDolares(List<Cuenta> cuentas) {
		for (Cuenta cuentaFiltrada : cuentas) {
			int tipo = Integer.valueOf(cuentaFiltrada.getTipoCuenta());
			if (new Integer(cuentaFiltrada.getTipoCuenta()).intValue() == TipoCuenta.CUENTA_UNICA.getCodigo()
					.intValue()) {
				return cuentaFiltrada;
			} else if (TransferenciaUtil.esCuentaDolar(tipo)) {
				return cuentaFiltrada;
			}
		}
		return null;
	}

	/**
	 * Verificando si existen cuentas en peso.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the cuenta
	 */
	private static Cuenta verificandoSiExistenCuentasEnPeso(List<Cuenta> cuentas) {
		for (Cuenta cuentaFiltrada : cuentas) {
			int tipo = Integer.parseInt(cuentaFiltrada.getTipoCuenta());
			if (tipo == TipoCuenta.CUENTA_UNICA.getCodigo()) {
				return cuentaFiltrada;
			} else if (TransferenciaUtil.esCuentaPesos(tipo)) {
				return cuentaFiltrada;
			}
		}
		return null;
	}

	/**
	 * Filtrar cuentas por divisa.
	 *
	 * @author manuel.vargas
	 * @param cuentas
	 *            the cuentas
	 * @param divisa
	 *            the divisa
	 * @return the list
	 */
	public static List<CuentasAdhesionDebitoView> filtrarCuentasPorDivisa(List<CuentasAdhesionDebitoView> cuentas,
			DivisaEnum divisa) {
		List<CuentasAdhesionDebitoView> cuentasFiltradas = new ArrayList<CuentasAdhesionDebitoView>();
		for (CuentasAdhesionDebitoView cuenta : cuentas) {
			int tipo = TipoCuenta.fromAbreviatura(cuenta.getAbreviaturaTipoCuenta().trim()).getCodigo();
			if (DivisaEnum.PESO.equals(divisa)) {
				if (TransferenciaUtil.esCuentaPesos(tipo)) {
					cuentasFiltradas.add(cuenta);
				}
			} else if (DivisaEnum.DOLAR.equals(divisa)) {
				if (TransferenciaUtil.esCuentaDolar(tipo)) {
					cuentasFiltradas.add(cuenta);
				}
			}
		}
		return cuentasFiltradas;
	}

	/**
	 * Obtener tipo cuenta desde CBU.
	 *
	 * @param cbu
	 *            the cbu
	 * @return the tipo cuenta
	 */
	public static TipoCuenta obtenerTipoCuentaDesdeCBU(String cbu) {
		String tipo = cbu.substring(TIPO_CUENTA_CBU_OFFSET, TIPO_CUENTA_CBU_OFFSET + 1);
		String moneda = cbu.substring(MONEDA_CUENTA_CBU_OFFSET, MONEDA_CUENTA_CBU_OFFSET + 1);
		if (DIGITO_CUENTA_UNICA_TIPO_Y_MONEDA.equals(tipo) && DIGITO_CUENTA_UNICA_TIPO_Y_MONEDA.equals(moneda)) {
			return TipoCuenta.CUENTA_UNICA;
		}
		if (DIGITO_TIPO_CUENTA_CORRIENTE.equals(tipo)) {
			return DIGITO_MONEDA_PESOS.equals(moneda) ? TipoCuenta.CUENTA_CORRIENTE_PESOS
					: TipoCuenta.CUENTA_CORRIENTE_DOLARES;
		}
		if (DIGITO_TIPO_CAJA_AHORRO.equals(tipo)) {
			return DIGITO_MONEDA_PESOS.equals(moneda) ? TipoCuenta.CAJA_AHORRO_PESOS : TipoCuenta.CAJA_AHORRO_DOLARES;
		}
		/* Se setea Cuenta Unica por default (definicion del usuario) */
		return TipoCuenta.CUENTA_UNICA;
	}

	/**
	 * Formatear numero cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	public static String formatearNumeroCuenta(Cuenta cuenta) {
		String sucursal = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal());
		String numeroCuenta = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
		return new StringBuilder().append(sucursal).append(GUION_MEDIO).append(numeroCuenta).toString();
	}

	/**
	 * Matchear tipo cuenta destino.
	 *
	 * @param moneda
	 *            the moneda
	 * @return the tipo cuenta
	 */
	public static TipoCuenta matchearTipoCuentaDestino(String moneda) {
		if (DivisaEnum.PESO.getMoneda().equalsIgnoreCase(moneda)) {
			return TipoCuenta.CUENTA_UNICA_PESOS;
		} else {
			return TipoCuenta.CUENTA_UNICA_DOLARES;
		}
	}
	
	/**
	 * Matchear tipo cuenta destino.
	 *
	 * @param moneda
	 *            the moneda
	 * @return the tipo cuenta
	 */
	public static TipoCuenta matchearTipoCuentaDestino(DivisaEnum moneda) {
        if (DivisaEnum.PESO.equals(moneda)) {
            return TipoCuenta.CUENTA_UNICA_PESOS;
        } else {
            return TipoCuenta.CUENTA_UNICA_DOLARES;
        }
    }

	/**
	 * Validar caracteres.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @return true, if successful
	 */
	public static boolean validarCaracteres(String descripcion) {
		Pattern pattern = Pattern.compile(CARACTERES_PATTERN);
		Matcher matcher = pattern.matcher(descripcion);
		return matcher.matches();
	}

	/**
	 * Verifica si el cuit/cuil de la cuenta destino corresponde con el DNI de
	 * la cuenta origen se utiliza para enviar a RSA si la cuenta destino es
	 * propia.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @return true, if successful
	 */
	public static boolean coincideCuitDocumento(TransferenciaDTO transferencia) {
		String cuit = transferencia.getCuit().trim();
		String dni = transferencia.getCuentaOrigen().getCliente().getDni().trim();
		cuit = cuit.substring(2, cuit.length() - 1);
		return Long.valueOf(cuit).equals(Long.valueOf(dni));
	}

	/**
	 * Checks if is transferencia es programada o inmediata.
	 *
	 * @param fechaEjecucion
	 *            the fecha ejecucion
	 * @return true, if is transferencia es programada
	 * @throws RobotException
	 *             the business exception
	 */
	public static boolean isTransferenciaProgramada(String fechaEjecucion) {

		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date fechaActual = Calendar.getInstance().getTime();
		try {
			Date fechaTransaccion = sdf.parse(fechaEjecucion);
			return fechaActual.before(fechaTransaccion);
		} catch (ParseException e) {
			LOGGER.error(MSJ_INFO_FECHA_NULA);
			throw new RobotException(MSJ_INFO_FECHA_NULA);
		}

	}

	/**
	 * Checks if is programada y devuelve la palabra indicada para el mensaje.
	 *
	 * @param fecha
	 *            the fecha
	 * @param isError
	 *            the is error
	 * @return the string
	 */
	public static String obtenerTituloTipoTransferencia(String fecha, boolean isError) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
			Date fechaActual = Calendar.getInstance().getTime();
			Date fechaTransaccion = sdf.parse(fecha);
			if (fechaActual.before(fechaTransaccion)) {
				if (isError) {
					return "programar";
				} else {
					return PROGRAMO;
				}
			} else {
				if (isError) {
					return "realizar";
				} else {
					return REALIZO;
				}
			}
		} catch (ParseException e) {
			throw new RobotException("Ocurrio un error al parsear las fechas");
		}
	}

	/**
	 * Es cuenta propia.
	 *
	 * @param cliente
	 *            the cliente origen
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param tipoCuentaDestinoAbrevitura
	 *            the tipo cuenta destino abrevitura
	 * @param filtroTipoCuenta
	 *            the filtro tipo cuenta
	 * @return true, if successful
	 */
	public static boolean esCuentaPropia(Cliente cliente, IdentificacionCuenta identificacionCuenta,
			String tipoCuentaDestinoAbrevitura, boolean filtroTipoCuenta) {
		LOGGER.info("Obteniendo las cuentas del cliente: " + cliente.getNombre());
		for (Cuenta cuenta : cliente.getCuentas()) {
			int nroSucCuentaCliente = new Integer(cuenta.getNroSucursal()).intValue();
			int nroSucIdentificacionCuentaDestino = new Integer(identificacionCuenta.getNroSucursal()).intValue();
			String nroCuentaProdCliente = StringUtils.leftPad(cuenta.getNroCuentaProducto(), LONGITUD_NRO_CUENTA, "0");
			String nroCuentaProdIdentificacionCuentaDestino = StringUtils
					.leftPad(identificacionCuenta.getNroCuentaProducto(), LONGITUD_NRO_CUENTA, "0");
			if (nroSucCuentaCliente == nroSucIdentificacionCuentaDestino
					&& nroCuentaProdCliente.equalsIgnoreCase(nroCuentaProdIdentificacionCuentaDestino)) {
				return true;
			}
		}
		LOGGER.info("La cuenta destino no es una cuenta propia.");
		return false;
	}

	/**
	 * Es cuenta propia de banca privada BP.
	 *
	 * @param cliente
	 *            the cliente origen
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param tipoCuentaDestinoAbrevitura
	 *            the tipo cuenta destino abrevitura
	 * @param filtroTipoCuenta
	 *            the filtro tipo cuenta
	 * @return true, if successful
	 */
	public static boolean esCuentaPropiaBP(Cliente cliente, IdentificacionCuenta identificacionCuenta,
			String tipoCuentaDestinoAbrevitura, boolean filtroTipoCuenta) {
		LOGGER.info("Obteniendo las cuentas BP del cliente: " + cliente.getNombre());
		for (Cuenta cuenta : cliente.getCuentasPrivadas()) {
			int nroSucCuentaCliente = new Integer(cuenta.getNroSucursal()).intValue();
			int nroSucIdentificacionCuentaDestino = new Integer(identificacionCuenta.getNroSucursal()).intValue();
			String nroCuentaProdCliente = StringUtils.leftPad(cuenta.getNroCuentaProducto(), LONGITUD_NRO_CUENTA, "0");
			String nroCuentaProdIdentificacionCuentaDestino = StringUtils
					.leftPad(identificacionCuenta.getNroCuentaProducto(), LONGITUD_NRO_CUENTA, "0");
			if (nroSucCuentaCliente == nroSucIdentificacionCuentaDestino
					&& nroCuentaProdCliente.equalsIgnoreCase(nroCuentaProdIdentificacionCuentaDestino)) {
				return true;
			}
		}
		LOGGER.info("La cuenta destino no es una cuenta propia BP.");
		return false;
	}

	public static boolean esCuentaPropiaCliente(Cliente cliente, IdentificacionCuenta identificacionCuenta) {

		return esCuentaPropia(cliente, identificacionCuenta, null, true)
				|| esCuentaPropiaBP(cliente, identificacionCuenta, null, true);
	}

	public static void loguearInfoPosibleFraudeCuentaPropia(Logger logger, String cuentaDestino ) {

			logger.info("PIF - Se detecta posible fraude por manipulación de datos. Cuenta destino fraudulenta: {}" ,
					cuentaDestino);

	}

	public static void loguearInfoPosibleFraudeTerceros(Logger logger, TransferenciaView transferenciaView, DatosTransferenciaDestino datosTransferenciaDestino) {

		logger.info("PIF - Se detecta posible fraude por manipulación de datos.");

		logger.info("Datos destino fraudulento. CBU: {}, Fecha Creación: {}, Alias: {}, Numero Cuenta Destino: {}" ,
				transferenciaView.getCbu(), transferenciaView.getFechaCreacionDestinatario(), transferenciaView.getAliasDestino(), transferenciaView.getNroCuentaDestino());

		logger.info("Datos destino original: {}", datosTransferenciaDestino);

	}

	public static void loguearInfoPosibleFraudeTercerosTransferenciaAgendada(Logger logger, TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView, DatosTransferenciaDestino datosTransferenciaDestino) {

		logger.info("PIF - Se detecta posible fraude por manipulación de datos.");

		logger.info("Datos destino fraudulento. Numero Destino: {}, Fecha Creación: {}, Tipo Destino: {}" ,
				transferenciaAgendadaDetalleView.getDestinoNumero(), transferenciaAgendadaDetalleView.getFechaCreacionDestinatario(), transferenciaAgendadaDetalleView.getDestinoTipo());

		logger.info("Datos destino original: {}", datosTransferenciaDestino);

	}

	public static void limpiarDatosTransferenciaDestinoDeSesionParametros(SesionParametros sesionParametros) {

		sesionParametros.getDatosTransferenciaDestino().setId(null);
		sesionParametros.getDatosTransferenciaDestino().setCbu(null);
		sesionParametros.getDatosTransferenciaDestino().setAlias(null);
		sesionParametros.getDatosTransferenciaDestino().setNumeroCuenta(null);
		sesionParametros.getDatosTransferenciaDestino().setFechaCreacion(null);
		sesionParametros.getDatosTransferenciaDestino().setTransferenciaAgendadaHaciaOtroBanco(false);

	}

	public static String formatearNumeroCuenta(DestinatarioEntity destinatario) {

		if (StringUtils.isBlank(destinatario.getSucursalCuentaDestinatario())) return null;

		String sucursal = ISBANStringUtils.eliminarCeros(destinatario.getSucursalCuentaDestinatario());
		String numeroCuenta = ISBANStringUtils.eliminarCeros(destinatario.getNumeroCuentaDestinatario());

		return new IdentificacionCuenta(sucursal, numeroCuenta).toString();

	}

	/**
	 * Es cuenta propia CBU.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cbu
	 *            the cbu
	 * @return true, if successful
	 */
	public static boolean esCuentaPropiaCBU(Cliente cliente, String cbu) {
		List<Cuenta> cuentas = cliente.getCuentas();
		boolean cuentaPropia = false;
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getCbu().equals(cbu)) {
				cuentaPropia = true;
				break;
			}
		}
		return cuentaPropia;
	}

	/**
	 * Valida el formato de la cuenta, sea cual sea el banco.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the boolean
	 */
	public static Boolean validarFormatoCuenta(TransferenciaView transferenciaView) {
		LOGGER.info(MSJ_INFO_VALIDANDO_FORMATO_CUENTA);
		if (null != transferenciaView.getTipoCuentaDestino() && null != transferenciaView.getNroCuentaDestino()
				&& !STRING_VACIO.equals(transferenciaView.getTipoCuentaDestino())
				&& !STRING_VACIO.equals(transferenciaView.getNroCuentaDestino())
				&& transferenciaView.getNroCuentaDestino().length() == ISBANStringUtils.getLongitudCuentaCompleta()) {
			LOGGER.info(MSJ_INFO_FORMATO_CUENTA_VALIDA);
			return Boolean.TRUE;
		}
		LOGGER.info(MSJ_INFO_FORMATO_CUENTA_INVALIDA);
		return Boolean.FALSE;
	}

	/**
	 * Obtener cantidad de cuentas pesos.
	 * 
	 * @author Manuel B041299
	 * @param cuentas
	 *            the cuentas
	 * @return the int
	 */
	public static int obtenerCantidadDeCuentasPesos(List<Cuenta> cuentas) {
		// TODO: controlar cantidad de tipo de cuentas en DOLARES
		int nroCuentasEnPesos = 0;
		for (Cuenta cuenta : cuentas) {
			if (!STRING_VACIO.equals(cuenta.getTipoCuentaSinUnificar())) {
				if (esCuentaPesos(new Integer(cuenta.getTipoCuentaSinUnificar()))) {
					nroCuentasEnPesos++;
				}
			}
		}
		LOGGER.info("La cantidad de cuentas es: " + nroCuentasEnPesos);
		return nroCuentasEnPesos;
	}

	/**
	 * Obtener cantidad de cuentas dolares.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the int
	 */
	public static int obtenerCantidadDeCuentasDolares(List<Cuenta> cuentas) {
		int nroCuentasEnDolares = 0;
		for (Cuenta cuenta : cuentas) {
			if (!STRING_VACIO.equals(cuenta.getTipoCuentaSinUnificarDls())) {
				if (esCuentaDolar(new Integer(cuenta.getTipoCuentaSinUnificarDls()))) {
					nroCuentasEnDolares++;
				}
			} else {
				if (esCuentaDolar(new Integer(cuenta.getTipoCuentaSinUnificar()))) {
					nroCuentasEnDolares++;
				}
			}
		}
		LOGGER.info("La cantidad de cuentas es: " + nroCuentasEnDolares);
		return nroCuentasEnDolares;
	}

	/**
	 * El metodo obtenerCuentasValidasDelTipoCuentaDestino recorre las cuentas
	 * del cliente origen. si encuentra el tipo de cuenta del destino devuelve
	 * true.
	 *
	 * @param tipoCuentaDestino
	 *            the tipo cuenta destino
	 * @param clienteOrigen
	 *            the cliente origen
	 * @return booelan
	 * @see TipoCuenta
	 */
	public static boolean obtenerCuentasValidasDelTipoCuentaDestino(TipoCuenta tipoCuentaDestino,
			Cliente clienteOrigen) {
		List<Cuenta> cuentas = clienteOrigen.getCuentas();
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getTipoCuentaEnum() != null) {
				int codTCOrigen = cuenta.getTipoCuentaEnum().getCodigo();
				int codTCDestino = tipoCuentaDestino.getCodigo();
				if (igualMoneda(codTCOrigen, codTCDestino)) {
					return true;
				}
			}
		}
		List<Cuenta> cuentasBP = clienteOrigen.getCuentasPrivadas();
		for (Cuenta cuenta : cuentasBP) {
			if (cuenta.getTipoCuentaEnum() != null) {
				int codTCOrigen = cuenta.getTipoCuentaEnum().getCodigo();
				int codTCDestino = tipoCuentaDestino.getCodigo();
				if (igualMoneda(codTCOrigen, codTCDestino)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Verificar moneda de cuentas orgen y destino.
	 *
	 * @param codTCOrigen
	 *            the tccocod
	 * @param codTCDestino
	 *            the tccdcod
	 * @return true si son cuentas de monedas iguales
	 */
	public static boolean igualMoneda(int codTCOrigen, int codTCDestino) {
		switch (codTCDestino) {
		case TC_CCP:
			return permitePesoAPeso(codTCOrigen);
		case TC_CAP:
			return permitePesoAPeso(codTCOrigen);
		case TC_CU:
			return permiteAUnica(codTCOrigen);
		case TC_CCD:
			return permiteADolar(codTCOrigen);
		case TC_CAD:
			return permiteADolar(codTCOrigen);
		case TC_CUP:
			return permiteAUnica(codTCOrigen);
		case TC_CUD:
			return permiteAUnica(codTCOrigen);
		default:
			return false;
		}
	}

	/**
	 * Permite A dolar.
	 *
	 * @param codTCOrigen
	 *            the cod TC origen
	 * @return true, if successful
	 */
	private static boolean permiteADolar(int codTCOrigen) {
		return codTCOrigen == TC_CU || codTCOrigen == TC_CCD || codTCOrigen == TC_CAD || codTCOrigen == TC_CUP
				|| codTCOrigen == TC_CUD;
	}

	/**
	 * Permite A unica.
	 *
	 * @param codTCOrigen
	 *            the cod TC origen
	 * @return true, if successful
	 */
	private static boolean permiteAUnica(int codTCOrigen) {
		return codTCOrigen == TC_CCP || codTCOrigen == TC_CAP || codTCOrigen == TC_CU || codTCOrigen == TC_CCD
				|| codTCOrigen == TC_CAD || codTCOrigen == TC_CUP || codTCOrigen == TC_CUD;
	}

	/**
	 * Permite peso A peso.
	 *
	 * @param codTCOrigen
	 *            the cod TC origen
	 * @return true, if successful
	 */
	private static boolean permitePesoAPeso(int codTCOrigen) {
		return codTCOrigen == TC_CCP || codTCOrigen == TC_CAP || codTCOrigen == TC_CU || codTCOrigen == TC_CUP
				|| codTCOrigen == TC_CUD;
	}

	/**
	 * Igual pesos origen destino.
	 *
	 * @param tccocod
	 *            the tccocod
	 * @param tccdcod
	 *            the tccdcod
	 * @return true, if successful
	 */
	public static boolean igualPesosOrigenDestino(int tccocod, int tccdcod) {
		if (tccocod == TC_CCP || tccocod == TC_CAP
				|| tccocod == TC_REC_PESOS) { /** casos pesos */
			if (tccdcod == TC_CCP || tccdcod == TC_CAP || tccdcod == TC_REC_PESOS) {
				return true;
			}

		}
		return false;
	}

	/**
	 * Igual dolares origen destino.
	 *
	 * @param tccocod
	 *            the tccocod
	 * @param tccdcod
	 *            the tccdcod
	 * @return true, if successful
	 */
	public static boolean igualDolaresOrigenDestino(int tccocod, int tccdcod) {
		if (tccocod == TC_REC_USS || tccocod == TC_CUD
				|| tccocod == TC_CAD) { /** casos dolares */
			if (tccdcod == TC_REC_USS || tccdcod == TC_CUD || tccdcod == TC_CAD) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Igual CU origen destino.
	 *
	 * @param tccocod
	 *            the tccocod
	 * @param tccdcod
	 *            the tccdcod
	 * @return true, if successful
	 */
	public static boolean igualCUOrigenDestino(int tccocod, int tccdcod) {
		if ((tccocod == TC_CU || tccocod == TC_CUP) && esCasoCU(tccdcod)) {
			return true;
		}
		return false;
	}

	/**
	 * Es caso CU.
	 *
	 * @param tccdcod
	 *            the tccdcod
	 * @return true, if successful
	 */
	public static boolean esCasoCU(int tccdcod) {
		return tccdcod == TC_CCP || tccdcod == TC_CAP || tccdcod == TC_REC_PESOS || tccdcod == TC_REC_USS
				|| tccdcod == TC_CUD || tccdcod == TC_CAD || tccdcod == TC_CU || tccdcod == TC_CUP;
	}

	/**
	 * Parsear A cuenta con ceros.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	public static String parsearACuentaConCeros(String cuenta) {
		String cuentaAux = ISBANStringUtils
				.formateadorConCerosIzq(cuenta.substring(4, cuenta.length()).replace("/", ""), 16);
		return cuentaAux;
	}

	/**
	 * Es cuenta pesos.
	 *
	 * @param tipo
	 *            the tipo
	 * @return true, if successful
	 */
	public static boolean esCuentaPesos(int tipo) {
		switch (tipo) {
		case TC_CCP:
		case TC_CAP:
		case TC_CUP:
		case TC_REC_PESOS:
		case TC_CU:
			return true;
		default:
			return false;
		}
	}

	/**
	 * Es cuenta dolar.
	 *
	 * @param tipo
	 *            the tipo
	 * @return true, if successful
	 */
	public static boolean esCuentaDolar(int tipo) {
		switch (tipo) {
		case TC_CCD:
		case TC_CAD:
		case TC_CUD:
		case TC_REC_USS:
		case TC_CU:
			return true;
		default:
			return false;
		}
	}

	/**
	 * Hay cambio en la seleccion original de cuenta origen.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param cuentaSeleccionada
	 *            the cuenta seleccionada
	 * @param monedaSeleccionada
	 *            the moneda seleccionada
	 * @param sesionParametros
	 *            the sesion parametros
	 * @return true, if successful
	 */
	public static boolean hayCambioEnCuentaOrigen(TransferenciaView transferenciaView, String cuentaSeleccionada, String monedaSeleccionada,
			SesionParametros sesionParametros) {
		String cuentaInicial = transferenciaView.getNroCuenta();
		String moneda = transferenciaView.getMoneda();
		if (null == cuentaSeleccionada || cuentaSeleccionada.isEmpty()) {
			sesionParametros.setCuentaSeleccionadaParaTransferencia(cuentaInicial, moneda);
			return false;
		} else {
			sesionParametros.setCuentaSeleccionadaParaTransferencia(cuentaInicial, moneda);
			return cuentaInicial.equals(cuentaSeleccionada) && moneda.equals(monedaSeleccionada);
		}
	}

	/**
	 * Cargar ayudas.
	 *
	 * @param ayudas
	 *            the ayudas
	 * @return the list
	 */
	public static List<AyudaView> cargarListaTemplateDeAyudas(AyudaView... ayudas) {
		return Arrays.asList(ayudas);
	}

	/**
	 * Obtener dia semana.
	 *
	 * @param calendar
	 *            the calendar
	 * @return the string
	 */
	private static String obtenerDiaSemana(Calendar calendar) {

		String dia;
		int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);

		switch (diaSemana) {
		case 1:
			dia = "D";
			break;
		case 2:
			dia = "L";
			break;
		case 3:
			dia = "T";
			break;
		case 4:
			dia = "M";
			break;
		case 5:
			dia = "J";
			break;
		case 6:
			dia = "V";
			break;
		case 7:
			dia = "S";
			break;
		default:
			dia = null;
			break;
		}

		return dia;

	}

	/**
	 * Obtener frecuencia.
	 *
	 * @param frecuenciaEnum
	 *            the frecuencia enum
	 * @param fechaEjecucion
	 *            the fecha ejecucion
	 * @return the string
	 */
	public static String obtenerFrecuencia(FrecuenciaTransferenciaAgendada frecuenciaEnum, Date fechaEjecucion) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaEjecucion);
		String diaSemana = obtenerDiaSemana(calendar);
		String diaMes = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		String mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		mes = StringUtils.leftPad(mes, 2, "0");

		switch (frecuenciaEnum) {

		case MISMO_DIA_TODAS_SEMANAS:
			return "S1" + diaSemana;

		case MISMO_DIA_2_SEMANAS:
			return "S2" + diaSemana;

		case TODOS_MESES_MISMO_DIA:
			return "M1" + diaMes + "01";

		case CADA_2_MESES:
			return "M1" + diaMes + "02";

		case CADA_3_MESES:
			return "M1" + diaMes + "03";

		case CADA_6_MESES:
			return "M1" + diaMes + "06";

		case CADA_ANIO:
			return "A1" + mes + diaMes;

		default:
			return null;
		}
	}

	/**
	 * Eliminar cuentas cerradas. Y filtra de la lista de destinos la cuenta de
	 * origen. Si es tx RioRio.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the list
	 * @see TransferenciaManager.solicitarNuevaTransferencia
	 */
	public static List<CuentasAdhesionDebitoView> eliminarCuentasCerradasYFiltroOrigen(CuentasView cuentas,
			TransferenciaView transferenciaView) {
		List<CuentasAdhesionDebitoView> cuentasAdhesionDebitoViewList = new ArrayList<CuentasAdhesionDebitoView>();
		LOGGER.info("Excluyendo cuentas cerradas. Si es Rio/Rio, quitar la cuenta destino de las cuentas de origen.");
		for (CuentasAdhesionDebitoView cuentaView : cuentas.getCuentas()) {
			if (!cuentaView.getIsCerrada() && null != transferenciaView.getNroCuentaDestino()
					&& !cuentaView.getNumero().equals(transferenciaView.getNroCuentaDestino())) {
				if (!ISBANStringUtils.isEmptyOrNull(transferenciaView.getCbu())) {
					validarCuentaPorLaMoneda(transferenciaView.getMoneda(), cuentaView, cuentasAdhesionDebitoViewList);
				} else {
					validarCuentaPorTipoDeCuenta(transferenciaView, cuentaView, cuentasAdhesionDebitoViewList);
				}
			}
		}
		return cuentasAdhesionDebitoViewList;
	}

	/**
	 * Validar cuenta por la moneda.
	 *
	 * @param moneda
	 *            the moneda
	 * @param cuentasAdhesionDebitoView
	 *            the cuentas adhesion debito view
	 * @param cuentasAdhesionDebitoViewList
	 *            the cuentas adhesion debito view list
	 */
	private static void validarCuentaPorLaMoneda(String moneda, CuentasAdhesionDebitoView cuentasAdhesionDebitoView,
			List<CuentasAdhesionDebitoView> cuentasAdhesionDebitoViewList) {
		if (PESOS.equalsIgnoreCase(moneda) && TransferenciaUtil.esCuentaPesos(
				TipoCuenta.fromDescripcionConMoneda(cuentasAdhesionDebitoView.getDescripcionTipoCuenta()).ordinal())) {
			cuentasAdhesionDebitoViewList.add(cuentasAdhesionDebitoView);
		} else if (!PESOS.equalsIgnoreCase(moneda) && TransferenciaUtil.esCuentaDolar(
				TipoCuenta.fromDescripcionConMoneda(cuentasAdhesionDebitoView.getDescripcionTipoCuenta()).ordinal())) {
			cuentasAdhesionDebitoViewList.add(cuentasAdhesionDebitoView);
		}
	}

	/**
	 * Validar cuenta por tipo de cuenta.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param cuentasAdhesionDebitoView
	 *            the cuentas adhesion debito view
	 * @param cuentasAdhesionDebitoViewList
	 *            the cuentas adhesion debito view list
	 */
	private static void validarCuentaPorTipoDeCuenta(TransferenciaView transferenciaView,
			CuentasAdhesionDebitoView cuentasAdhesionDebitoView,
			List<CuentasAdhesionDebitoView> cuentasAdhesionDebitoViewList) {
		if (TransferenciaUtil
				.esCuentaPesos(TipoCuenta.fromAbreviatura(transferenciaView.getTipoCuentaDestino()).ordinal())
				&& TransferenciaUtil.esCuentaPesos(TipoCuenta
						.fromDescripcionConMoneda(cuentasAdhesionDebitoView.getDescripcionTipoCuenta()).ordinal())) {
			cuentasAdhesionDebitoViewList.add(cuentasAdhesionDebitoView);
		} else if (TransferenciaUtil
				.esCuentaDolar(TipoCuenta.fromAbreviatura(transferenciaView.getTipoCuentaDestino()).ordinal())
				&& TransferenciaUtil.esCuentaDolar(TipoCuenta
						.fromDescripcionConMoneda(cuentasAdhesionDebitoView.getDescripcionTipoCuenta()).ordinal())) {
			cuentasAdhesionDebitoViewList.add(cuentasAdhesionDebitoView);
		}
	}

	/**
	 * Eliminar cuentas cerradas. Y filtra de la lista de destinos la cuenta de
	 * origen por moneda. Si es tx RioRio.
	 * 
	 * @author Manuel.Vargas B041299
	 * @param cuentas
	 *            the cuentas
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the list
	 * @see TransferenciaManager.solicitarNuevaTransferencia
	 */
	public static List<CuentasAdhesionDebitoView> eliminarCuentasCerradasYFiltroOrigenPorMoneda(CuentasView cuentas,
			TransferenciaView transferenciaView) {
		List<CuentasAdhesionDebitoView> cuentasAdhesionDebitoViewList = new ArrayList<CuentasAdhesionDebitoView>();
		LOGGER.info("Excluyendo cuentas cerradas. Si es Rio/Rio, quitar la cuenta destino de las cuentas de origen.");
		for (CuentasAdhesionDebitoView cuentaView : cuentas.getCuentas()) {
			if (!cuentaView.getIsCerrada() && null != transferenciaView.getNroCuentaDestino()
					&& !cuentaView.getNumero().equals(transferenciaView.getNroCuentaDestino())) {
				if (!ISBANStringUtils.isEmptyOrNull(transferenciaView.getCbu())) {
					validarCuentaPorMoneda(transferenciaView.getMoneda(), cuentaView, cuentasAdhesionDebitoViewList);
				} else {
					validarCuentaPorTipoCuenta(transferenciaView, cuentaView, cuentasAdhesionDebitoViewList);
				}
			}
		}
		return cuentasAdhesionDebitoViewList;
	}

	/**
	 * Validar cuenta por la moneda.
	 *
	 * @param moneda
	 *            the moneda
	 * @param unaCuentaAdADebitoView
	 *            the cuentas adhesion debito view
	 * @param cuentasFiltradasAdADebitoViewList
	 *            the cuentas adhesion debito view list
	 */
	private static void validarCuentaPorMoneda(String moneda, CuentasAdhesionDebitoView unaCuentaAdADebitoView,
			List<CuentasAdhesionDebitoView> cuentasFiltradasAdADebitoViewList) {

		int monedaDelTipoDeCuenta = TipoCuenta
				.fromDescripcionConMoneda(unaCuentaAdADebitoView.getDescripcionTipoCuenta()).ordinal();

		if (PESOS.equalsIgnoreCase(moneda) && TransferenciaUtil.esCuentaPesos(monedaDelTipoDeCuenta)) {
			cuentasFiltradasAdADebitoViewList.add(unaCuentaAdADebitoView);
		} else if (!PESOS.equalsIgnoreCase(moneda) && TransferenciaUtil.esCuentaDolar(monedaDelTipoDeCuenta)) {
			cuentasFiltradasAdADebitoViewList.add(unaCuentaAdADebitoView);
		}
	}

	/**
	 * Validar cuenta por tipo de cuenta.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param cuentasAdhesionDebitoView
	 *            the cuentas adhesion debito view
	 * @param cuentasAdhesionDebitoViewList
	 *            the cuentas adhesion debito view list
	 */
	private static void validarCuentaPorTipoCuenta(TransferenciaView transferenciaView,
			CuentasAdhesionDebitoView cuentasAdhesionDebitoView,
			List<CuentasAdhesionDebitoView> cuentasAdhesionDebitoViewList) {
		if (transferenciaView.getMoneda().equalsIgnoreCase(DivisaEnum.PESO.getMoneda())) {
			if (TransferenciaUtil.esCuentaPesos(TipoCuenta
					.fromDescripcionConMoneda(cuentasAdhesionDebitoView.getDescripcionTipoCuenta()).ordinal())) {
				cuentasAdhesionDebitoViewList.add(cuentasAdhesionDebitoView);
			} else if (TransferenciaUtil.esCuentaDolar(TipoCuenta
					.fromDescripcionConMoneda(cuentasAdhesionDebitoView.getDescripcionTipoCuenta()).ordinal())) {
				cuentasAdhesionDebitoViewList.add(cuentasAdhesionDebitoView);
			}
		}
	}

	/**
	 * Este metodo permite sumar un numero de dias a la fecha pasada como
	 * parametro. El formato de la fecha debe ser dd-MM-yyyy
	 *
	 * @param fecha
	 *            the fecha
	 * @param totalDias
	 *            the total dias
	 * @return the string
	 */
	public static String sumarDiasAunaFecha(String fecha, int totalDias) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(fecha));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RobotException(ERROR_AL_PARSEAR_LA_FECHA);
		}
		calendar.add(Calendar.DATE, 2);
		String nuevaFecha = sdf.format(calendar.getTime());
		return nuevaFecha;
	}

	/**
	 * Validar hash de la vista en sesion.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param sesionParametros
	 *            the sesion parametros
	 */
	public static void validarHashDeLaVistaEnSesion(TransferenciaView transferenciaView,
			SesionParametros sesionParametros) {
		String hashViewInput = HashUtils.obtenerHash(crearMapaDeTransferenciaView(transferenciaView));
		HashUtils.compareHash(sesionParametros.getValidacionHash(), hashViewInput);

	}

	/**
	 * Crear mapa de la vista.
	 * 
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the map
	 */
	public static Map<String, Object> crearMapaDeTransferenciaView(TransferenciaView transferenciaView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		String cuit = transferenciaView.getCuit() != null ? transferenciaView.getCuit().replace("-", "") : null;
		String nroCuentaDestino = transferenciaView.getNroCuentaDestino() != null
				? transferenciaView.getNroCuentaDestino() : "";
		mapaAtributos.put("idSesion", transferenciaView.getIdSesion());
		mapaAtributos.put("cbu", transferenciaView.getCbu());
		mapaAtributos.put("cuit", cuit);
		mapaAtributos.put("nroCuentaDestino", nroCuentaDestino);
		mapaAtributos.put("banco", transferenciaView.getBanco().trim());
		mapaAtributos.put("moneda", transferenciaView.getMoneda());
		mapaAtributos.put("importe", formatearCualquierImporteABigDecimal(StringUtils.defaultString(transferenciaView.getImporte())));
		mapaAtributos.put("nroCuenta", transferenciaView.getNroCuenta());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Formatear cualquier importe A big decimal.
	 *
	 * @param num the num
	 * @return the string
	 */
	public static String formatearCualquierImporteABigDecimal(String num) {
		String numeroFormateado = num;
		
		if (!num.isEmpty()) {
			try {
				String ultimos = numeroFormateado.substring(numeroFormateado.length()-2);
	            if (ultimos.contains(".") || ultimos.contains(",")) {
	            	numeroFormateado = numeroFormateado.concat("0");
	            	num = numeroFormateado;
	            }
		            
				String separadorDecimal = numeroFormateado.substring(numeroFormateado.length() - 3,
						numeroFormateado.length() - 2);
				if (",".equals(separadorDecimal)) {
					numeroFormateado = num.replace(".", "");
					numeroFormateado = numeroFormateado.replace(",", ".");
					return numeroFormateado;
				} else if(".".equals(separadorDecimal)) {
					return num;
				} else {
					return num.concat(DECIMALES);
				}
			} catch (StringIndexOutOfBoundsException ex) {
				return num.concat(DECIMALES);
			}
		} else {
			return num;
		}
	}

	/**
	 * Cargar hash de la vista en sesion.
	 * 
	 * <P>
	 * Solo crea un hash de los datos estaticos, es decir, que el usuario no
	 * puede cambiar desde la vista
	 * </P>
	 *
	 * @author emilio.watemberg
	 * @param transferenciaView
	 *            the transferencia view
	 * @param sesionParametros
	 *            the sesion parametros
	 */
	public static void cargarHashDeLaVistaEnSesion(TransferenciaView transferenciaView,
			SesionParametros sesionParametros) {
		String hashView = HashUtils.obtenerHash(crearMapaDeTransferenciaView(transferenciaView));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sesionParametros.setValidacionHash(hashView);
	}

	/**
	 * Validar tipo cuenta unificada segun moneda. El parametro tipoCuenta debe
	 * ser el codigo del tipo de cuenta si no es tipo de cuenta unica devuelve
	 * la ingresada
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param moneda
	 *            the moneda
	 * @return the string
	 */
	public static String validarTipoCuentaUnificadaSegunMoneda(String tipoCuenta, DivisaEnum moneda) {
		if (tipoCuenta.equals(CUENTA_UNICA_CODIGO) && moneda.equals(DivisaEnum.PESO)) {
			return "09";
		} else if (tipoCuenta.equals(CUENTA_UNICA_CODIGO) && moneda.equals(DivisaEnum.DOLAR)) {
			return "10";
		} else {
			return tipoCuenta;
		}
	}
	
	/**
	 * validarTransferenciaCVU.
	 *
	 * @param importe
	 *            the importe
	 * @param moneda
	 *            the moneda
	 * @param trfCvuDolaresHabilitado
	 *            the trf cvu dolares habilitado
	 * @param trfCVUImporteDolaresMax
	 *            the trf CVU importe dolares max
	 * @param trfCVUImportePesosMax
	 *            the trf CVU importe pesos max
	 * @return true, if successful
	 */
    public static boolean validarTransferenciaCVU(BigDecimal importe, DivisaEnum moneda, int trfCvuDolaresHabilitado, String trfCVUImporteDolaresMax, String trfCVUImportePesosMax) {
        if (trfCvuDolaresHabilitado == 0 && !DivisaEnum.PESO.equals(moneda)) {
            LOGGER.info(ERROR_TRANSFERENCIA_CVU_MONEDA_INVALIDA);
            throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
        }
        BigDecimal importeDolaresMax = new BigDecimal(trfCVUImporteDolaresMax.replace(".", "").replace(",", "."));
        BigDecimal importePesosMax = new BigDecimal(trfCVUImportePesosMax.replace(".", "").replace(",", "."));
        if ((DivisaEnum.DOLAR.equals(moneda) && importe.compareTo(importeDolaresMax) > 0)  
                || (DivisaEnum.PESO.equals(moneda) && importe.compareTo(importePesosMax) > 0)) {
            LOGGER.info(ERROR_TRANSFERENCIA_CVU_IMPORTE_MAX);
            return false;
        }
        return true;
    }
    
    /**
     * Retorna true si esRioRio = true && esInmediata = true && sucursalCuentaDestinara es banca privada;
     * false en cualquier otro caso
     * @param esRioRio
     * @param esInmediata
     * @param sucursalBancaPrivada
     * @return
     */
    public static boolean puedeAgendarDestinatarioBancaPrivadaRio(Boolean esRioRio, Boolean esInmediata, String sucursalBancaPrivada) {
    	return esRioRio && esInmediata && CuentasBancaPrivadaUtil.isCuentaBP(sucursalBancaPrivada);
    }

	public static String getIpSinPuntosYFormatoFijo(String ip) {
		if (StringUtils.isNotBlank(ip)) {
			if (IPAddress.isValidIPv4(ip)) {
				String[] octetos = StringUtils.splitPreserveAllTokens(ip, '.');
				StringBuilder ipv4 = new StringBuilder();
				for (String octeto : octetos) {
					ipv4.append(StringUtils.leftPad(octeto, 3, '0'));
				}
				return ipv4.toString();
			} else if (IPAddress.isValidIPv6(ip) && StringUtils.containsNone(ip, '.')) {
				String[] grupos = StringUtils.splitPreserveAllTokens(ip, ':');
				String grupo = StringUtils.leftPad(grupos[grupos.length-2], 4, '0') + StringUtils.leftPad(grupos[grupos.length-1], 4, '0');

				StringBuilder ipv6 = new StringBuilder();
				for (int i = 0; i < 8; i=i+2) {
					ipv6.append(StringUtils.leftPad(String.valueOf(Integer.parseInt(StringUtils.substring(grupo, i, i+2), 16)), 3,'0'));
				}
				return ipv6.toString();
			}
		}
		return StringUtils.repeat('0', 12);
	}

	public static String obtenerContratoAltair(String nroCuenta, String tipoCuenta){

		String tipoCtaDestino = TransferenciaUtil.obtenerTipoCuenta(TipoCuenta.fromAbreviatura(tipoCuenta));

		return new StringBuilder(16)
				.append('0').append(nroCuenta, 0, 3)
				.append(tipoCtaDestino)
				.append(String.format("%09d", Long.parseLong(nroCuenta.substring(4).replace("/", ""))))
				.toString();
	}

	public static String obtenerTipoCuenta(TipoCuenta tipoCuenta) {

		if ("CAP".equals(tipoCuenta.getAbreviatura())) {
			return "002";
		}
		if ("CAD".equals(tipoCuenta.getAbreviatura())) {
			return "003";
		}
		if ("CCP".equals(tipoCuenta.getAbreviatura())) {
			return "005";
		}
		if ("CCD".equals(tipoCuenta.getAbreviatura())) {
			return "006";
		}
		if ("CU".equals(tipoCuenta.getAbreviatura()) ||"CUP".equals(tipoCuenta.getAbreviatura()) || "CUD".equals(tipoCuenta.getAbreviatura())) {
			return "007";
		}
		return "000";
	}

	public static Long obtenerCantDiasUltimoCambioMail(Date createdAt, Date updateAt, Date currentDate){

		Long cantDiasUltimoCambioMail;

		if (updateAt != null && updateAt.after(createdAt)) {

			cantDiasUltimoCambioMail = diferenciaFechaEnDias(currentDate, updateAt);

		} else {

			cantDiasUltimoCambioMail = diferenciaFechaEnDias(currentDate, createdAt);
		}

		return cantDiasUltimoCambioMail;
	}

	private static long diferenciaFechaEnDias(Date currentDate, Date lastDate) {

		long diferenciaMiliSegundos =   currentDate.getTime() - lastDate.getTime();

		return diferenciaMiliSegundos / (24 * 60 * 60 * 1000);
	}

	public static String generateRandomId() {
		UUID idRandom = UUID.randomUUID();
		return idRandom.toString();
	}

	public static String convertirFormatoFecha(String fechaOriginal) {
		try {
			SimpleDateFormat formatoOriginal = new SimpleDateFormat(FORMATO_FECHA);
			Date fecha = formatoOriginal.parse(fechaOriginal);

			SimpleDateFormat formatoNuevo = new SimpleDateFormat("yyyy-MM-dd");
			return formatoNuevo.format(fecha);
		} catch (Exception e) {
			LOGGER.error("Error al parsear fecha ingresada.",e);
			LOGGER.info("fecha original: {}",fechaOriginal);
			return String.valueOf(LocalDate.now());
		}
	}

}
