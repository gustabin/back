/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.CuentaInvalidaException;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.Factura;
import ar.com.santanderrio.obp.servicios.pagos.dao.BanelcoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPagoMisCuentas;

/**
 * Conector con el servicio IDEPESBANE.
 *
 * @author B025331
 */
@Component
public class BanelcoDAOImpl implements BanelcoDAO {

	/** The Constant SALTEAR_CAMPOS. */
	private static final int SALTEAR_CAMPOS = 17;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BanelcoDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The mensaje. */
	@Autowired
	private MensajeDAO mensaje;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The servicio ide pes bane. */
	@Value("${SERVICIO.PREFIJO.IDEPESBANE}")
	private String servicioIdePesBane;

	/** The version ide pes bane. */
	@Value("${SERVICIO.VERSION.IDEPESBANE}")
	private String versionIdePesBane;

	/** The servicio ctrl nuptar. */
	private String servicioCtrlNuptar = "CTRLNUPTAR";

	/** The version 100. */
	private String version100 = "100";

	/** The Constant CNSPESSDEU. */
	private static final String CNSPESSDEU = "CNSPESSDEU";

	/** The Constant VERSION. */
	private static final String VERSION = "110";

	/** The Constant CODIGO_DE_ERROR_DESCONOCIDO_MSG. */
	private static final String CODIGO_DE_ERROR_DESCONOCIDO_MSG = "Codigo de error iatx desconocido ";

	/** The Constant CANTIDAD_MAXIMA_BANELCO. */
	private static final int CANTIDAD_MAXIMA_BANELCO = 5;

	/** The Constant CODIGO_DE_ERROR_NO_BANELCO. */
	private static final String CODIGO_DE_ERROR_NO_BANELCO = "No tiene banelcos.";

	/** The Constant RESPUESTA_ALTA_CLIENTE. */
	private static final int RESPUESTA_ALTA_CLIENTE = 50;

	/** The Constant VALORES_ESQUIVADOS. */
	private static final int VALORES_ESQUIVADOS = 3;

	/** The Constant CANTIDAD_DATOS_FACTURA. */
	private static final int CANTIDAD_DATOS_FACTURA = 6;

	/** The Constant CODIGO_ERROR_DEBITO_BLOQUEADA. */
	private static final int CODIGO_ERROR_DEBITO_BLOQUEADA = 10005020;

	/** The Constant CODIGO_ERROR_DEBITO_NUMEROS_EQUIVOCADOS. */
	private static final int CODIGO_WARNING_DEBITO_NUMEROS_EQUIVOCADOS = 10005030;

	/** The Constant CODIGO_MENSAJE_OCHO_DIGITOS_ERROR. */
	private static final String CODIGO_MENSAJE_OCHO_DIGITOS_ERROR = "1217";

	/** The Constant MENSAJE_BANELCO_BLOQUEADA. */
	private static final String MENSAJE_BANELCO_BLOQUEADA = "1291";

	/** The Constant infoLog. */
	private static final String infoLog = "Valida los ultimos 8 digitos de la tarjeta";

	/**
	 * Devuelve las cuentas q sean de de tipo Banelco(5). Esta siendo cacheado y la
	 * coleccion que se devuelve es inmutable no asi los elementos por lo que hay
	 * que tener cuidado de no modificar el estado de dichos items. (al migrar a
	 * redis evaluar pasar a builders).
	 * 
	 * @param cliente the cliente
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	@Override
	@Cacheable(cacheNames = CacheConstants.Names.CACHE_NUP_IDEPESBANE, key = "#cliente.nup")
	public List<CuentaPagoMisCuentas> obtenerCuentasBanelcoHabilitadas(Cliente cliente) throws DAOException {
		LOGGER.info("Se va a cachear {} para el nup {}", CacheConstants.Names.CACHE_NUP_IDEPESBANE, cliente.getNup());
		Collection<Cuenta> cuentasBanelco = new ArrayList<Cuenta>();
		Collection<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
		Collection<Cuenta> cuentasAconsultar = new ArrayList<Cuenta>();

		for (Cuenta cuenta : cliente.getCuentas()) {
			// filtro la cuentas banelco
			if (TipoCuenta.BANELCO.equals(cuenta.getTipoCuentaEnum())) {
				cuentasBanelco.add(cuenta);
			}
			// filtro por las cuentas candidatas a enviar a IDEPESBANE
			if (existeTipoCuentaConsultable(cuenta)) {
				cuentasCliente.add(cuenta);
			}
		}
		// comparo por cada cuenta banelco, que el campo altair sea igual al de cuenta
		// producto
		for (Cuenta cuentaBanelco : cuentasBanelco) {
			if (existeCuentaProducto(cuentasCliente, cuentaBanelco)) {
				cuentasAconsultar.add(cuentaBanelco);
			}
		}
		// si ninguna de las cuentas coincide de todas formas invoca con las que cumplen
		// que son cuentas banelco
		if ((cuentasAconsultar.size() == 0) && cuentasBanelco.size() > 0) {
			cuentasAconsultar = cuentasBanelco;
		}

		IatxRequest request = new IatxRequest(servicioIdePesBane, versionIdePesBane);
		return Collections.unmodifiableList(invocarServicio(cliente, cuentasAconsultar, request));
	}
	
	
	@Override
	public List<CuentaPagoMisCuentas> obtenerCuentasBanelcoHabilitadasSinCache(Cliente cliente) throws DAOException {
	    LOGGER.info("Se consulta IDEPESBANE sin  cache.");
	    return obtenerCuentasBanelcoHabilitadas(cliente);
	}
	

	public boolean existeCuentaProducto(Collection<Cuenta> cuentasCliente, Cuenta cuentaBanelco) {
		for (Cuenta cuentaCliente : cuentasCliente) {
			if (cuentaCliente.getNroCuentaProducto().equals(cuentaBanelco.getContratoAltair())) {
				return true;
			}
		}
		return false;
	}

	public boolean existeTipoCuentaConsultable(Cuenta cuenta) {
		if (TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(cuenta.getTipoCuentaEnum())
		        || TipoCuenta.CAJA_AHORRO_PESOS.equals(cuenta.getTipoCuentaEnum())
		        || TipoCuenta.CAJA_AHORRO_DOLARES.equals(cuenta.getTipoCuentaEnum())
		        || TipoCuenta.CUENTA_UNICA_PESOS.equals(cuenta.getTipoCuentaEnum())
		        || TipoCuenta.CUENTA_UNICA_DOLARES.equals(cuenta.getTipoCuentaEnum())
		        || TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(cuenta.getTipoCuentaEnum())) {
			return true;
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public List<Factura> obtenerListaFacturas(Cliente cliente, String nombreFantasia, String codigoPagoElectronico)
	        throws DAOException {

		IatxRequest request = new IatxRequest(CNSPESSDEU, VERSION);
		try {
			IatxRequestData requestData = new IatxRequestData(cliente);

			requestData.addBodyValue(nombreFantasia);
			requestData.addBodyValue(codigoPagoElectronico);
			request.setData(requestData);
			IatxResponse iatxResponse = iatxComm.exec(request);
			// Cod_retorno
			int codigoDeRetorno = iatxResponse.getErrorCode();

			if (codigoDeRetorno == 0 || codigoDeRetorno == RESPUESTA_ALTA_CLIENTE) {
				return parsearListaFacturasIatx(iatxResponse);
			} else {
				LOGGER.debug("Error en Iatx:" + iatxResponse.getErrorCode());
				throw new DAOException(iatxResponse.getErrorMessage(), String.valueOf(iatxResponse.getErrorCode()));
			}

		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Valida con el servicio CTRLNUPTAR, el codigo de 8 digitos ingresado.
	 *
	 * @param cliente    the cliente
	 * @param numTarjeta the num tarjeta
	 * @return the string
	 * @throws DAOException the DAO exception
	 */
	@Override
	public Integer validarOchoDigitos(Cliente cliente, String numTarjeta) throws DAOException {
		LOGGER.info(infoLog);
		final String tipoControl = "BA";
		final String digitosValidacion = "08";

		IatxRequest request = new IatxRequest(servicioCtrlNuptar, version100);
		try {
			IatxRequestData requestData = new IatxRequestData(cliente);

			requestData.addBodyValue(numTarjeta);
			requestData.addBodyValue(digitosValidacion);
			requestData.addBodyValue(tipoControl);

			request.setData(requestData);
			IatxResponse iatxResponse = iatxComm.exec(request);

			return iatxResponse.getErrorCode();

		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/** {@inheritDoc} */
	@Override
	@Deprecated // usar #validarOchoDigitos que esta integrado con el
	            // Autentificador generico.
	public Respuesta<Boolean> validarTarjetaDebito(Cliente cliente, String numTarjeta) throws DAOException {

		int codigoRetornoOk = 0;
		final String tipoControl = "BA";
		final String digitosValidacion = "08";

		IatxRequest request = new IatxRequest(servicioCtrlNuptar, version100);
		try {
			IatxRequestData requestData = new IatxRequestData(cliente);

			requestData.addBodyValue(numTarjeta);
			requestData.addBodyValue(digitosValidacion);
			requestData.addBodyValue(tipoControl);

			request.setData(requestData);
			IatxResponse iatxResponse = iatxComm.exec(request);

			Respuesta<Boolean> respuesta = new Respuesta<Boolean>();
			if (iatxResponse.getErrorCode() == codigoRetornoOk) {
				return respuestaFactory.crearRespuestaOk(Boolean.class, new Boolean(true));

			} else if (iatxResponse.getErrorCode() == CODIGO_ERROR_DEBITO_BLOQUEADA) {
				List<ItemMensajeRespuesta> itemsMensaje = new LinkedList<ItemMensajeRespuesta>();
				ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
				        mensaje.obtenerMensajeDescripcion(MENSAJE_BANELCO_BLOQUEADA));
				itemMensajeRespuesta.setTipoError("DEBITO_BLOQUEADA");
				itemsMensaje.add(itemMensajeRespuesta);
				respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
				respuesta.setRespuestaVacia(true);
				respuesta.setItemMensajeRespuesta(itemsMensaje);
				respuesta.setRespuesta(false);

			} else if (iatxResponse.getErrorCode() == CODIGO_WARNING_DEBITO_NUMEROS_EQUIVOCADOS) {
				List<ItemMensajeRespuesta> itemsMensaje = new LinkedList<ItemMensajeRespuesta>();
				ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
				        mensaje.obtenerMensajeDescripcion(CODIGO_MENSAJE_OCHO_DIGITOS_ERROR));
				itemMensajeRespuesta.setTipoError("DEBITO_NUMEROS_EQUIVOCADOS");
				itemsMensaje.add(itemMensajeRespuesta);
				respuesta.setItemMensajeRespuesta(itemsMensaje);
				respuesta.setRespuestaVacia(true);
				respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
				respuesta.setRespuesta(false);
			}

			LOGGER.info("Se devuelve el codigo de retorno, para que lo pueda usar el BO");
			return respuesta;

		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}

	}

	/**
	 * Parsear lista facturas iatx.
	 *
	 * @param iatxResponse the iatx response
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	public List<Factura> parsearListaFacturasIatx(IatxResponse iatxResponse) throws DAOException {
		LOGGER.info("Inicio parseo IATX para obtener tarjetas.");

		List<Factura> facturas = new ArrayList<Factura>();
		String nombreEmpresa = iatxResponse.getNextData().trim();

		LOGGER.info("Empresa {} con {} facturas.", nombreEmpresa, Integer.valueOf(iatxResponse.getNextData().trim()));

		// "valores esquivados" hace referencia a los datos del body que no
		// forman parte de la factura
		// "cantidad datos factura" hace referencia a la longitud total que
		// tiene cada una
		final int inicializaEn100 = 100;
		int cantidadFacturas = (iatxResponse.getIatxBody().size() - VALORES_ESQUIVADOS) / CANTIDAD_DATOS_FACTURA;
		int resto = (iatxResponse.getIatxBody().size() - VALORES_ESQUIVADOS) % CANTIDAD_DATOS_FACTURA;

		if (resto != 0) {
			LOGGER.debug("Error en la trama de facturas, hay {} atributos de mas.", resto);
			throw new DAOException("Error en la trama de facturas");
		}

		for (int i = 0; i < cantidadFacturas; i++) {

			Factura factura = new Factura();

			// paso este valor que no me interesa
			iatxResponse.getNextData();

			// agrego los valores a la factura
			factura.setEmpresa(nombreEmpresa);
			factura.setNumeroReferenciaPago(iatxResponse.getNextData().trim());

			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("ddMMyyy");
			String strFecha = iatxResponse.getNextData().trim();
			if (strFecha.contains("*")) {
				factura.setFechaVencimiento("**/**/****");
			} else {
				try {
					factura.setFechaVencimiento(ISBANStringUtils.formatearFecha(formatoDelTexto.parse(strFecha)));
				} catch (ParseException ex) {
					LOGGER.error("Error fomateo de texto fecha ", ex);
				}
			}

			BigDecimal bigMonto = new BigDecimal(iatxResponse.getNextData().trim().replaceFirst("^0+(?!$)", ""));

			// La trama enviada el monto con dos ceros extra atras, con esta
			// operacion se los saco
			bigMonto = bigMonto.divide(new BigDecimal(inicializaEn100));

			// Utilizo el formateador de saldo para que me quede bien para
			// mandar a la vista
			factura.setMonto(ISBANStringUtils.formatearSaldo(bigMonto));
			iatxResponse.getNextData();
			factura.setNumeroFactura(iatxResponse.getNextData().trim());
			if ("0,00".equals(factura.getMonto())) {
				factura.setPrePago(true);
			} else {
				factura.setPrePago(false);
			}

			facturas.add(factura);
		}
		LOGGER.info("Finalmente, devuelvo la cantidad de {} facturas.", facturas.size());
		return facturas;

	}

	/**
	 * Invocar servicio.
	 *
	 * @param cliente        the cliente
	 * @param cuentasBanelco the cuentas banelco
	 * @param request        the request
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	private List<CuentaPagoMisCuentas> invocarServicio(Cliente cliente, Collection<Cuenta> cuentasBanelco,
	        IatxRequest request) throws DAOException {

		try {
			IatxRequestData requestData = generarIatxRequestData(cliente, cuentasBanelco);
			request.setData(requestData);
			IatxResponse iatxResponse = iatxComm.exec(request);
			// Cod_retorno
			int codigoDeRetorno = iatxResponse.getErrorCode();

			if (codigoDeRetorno == 0 || codigoDeRetorno == RESPUESTA_ALTA_CLIENTE) {
				return parsearResponseCuentaPagoMisCuentasIatx(cliente, iatxResponse);
			} else {
				// Cualquier otro codigo de error de iatx
				LOGGER.debug(CODIGO_DE_ERROR_DESCONOCIDO_MSG + String.valueOf(codigoDeRetorno));
				throw new DAOException(CODIGO_DE_ERROR_DESCONOCIDO_MSG, String.valueOf(codigoDeRetorno));
			}

		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Parsear response cuenta pago mis cuentas iatx.
	 *
	 * @param cliente      the cliente
	 * @param iatxResponse the iatx response
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	private List<CuentaPagoMisCuentas> parsearResponseCuentaPagoMisCuentasIatx(Cliente cliente,
	        IatxResponse iatxResponse) throws DAOException {
		// Campos a omitir:
		// Nombre-Apellido-Usuario A24 Nombre y apellido del usuario
		// Estado-Civil A1 Estado Civil del usuario
		// Fecha-Nacimiento N8 Fecha de Nacimiento del Usuario (ddmmaaaa)
		// Sexo A1 Sexo del Usuario
		// Teléfono A11 Teléfono particular del usuario
		// Teléfono-laboral A11 Teléfono laboral del usuario
		// Email A65 Dirección e-mail del usuario
		// Tipo-Tarjeta-1 A2 Tipo de Tarjeta
		// Nro-Tarjeta-1 N19 Número de Tarjeta
		// Tipo-Tarjeta-2 A2 Tipo de Tarjeta
		// Nro-Tarjeta-2 N19 Número de Tarjeta
		// Tipo-Tarjeta-3 A2 Tipo de Tarjeta
		// Nro-Tarjeta-3 N19 Número de Tarjeta
		// Tipo-Tarjeta-4 A2 Tipo de Tarjeta
		// Nro-Tarjeta-4 N19 Número de Tarjeta
		// Tipo-Tarjeta-5 A2 Tipo de Tarjeta
		// Nro-Tarjeta-5 N19 Número de Tarjeta
		for (int i = 0; i < SALTEAR_CAMPOS; i++) {
			iatxResponse.getNextData();
		}
		int cantidadCuentas = Integer.parseInt(iatxResponse.getNextData());
		HashSet<CuentaPagoMisCuentas> cuentasPMC = new HashSet<CuentaPagoMisCuentas>();
		for (int i = 0; i < cantidadCuentas; i++) {
			String tipo = iatxResponse.getNextData();
			String sucursal = iatxResponse.getNextData().substring(1);
			String numeroCuenta = iatxResponse.getNextData();

			/* estado cuenta */
			iatxResponse.getNextData();
			/* saldo */
			iatxResponse.getNextData();
			/* limite sobregiro */
			iatxResponse.getNextData();
			/* intereses devengados */
			iatxResponse.getNextData();

			String nrocanonico = ISBANStringUtils.formatearSucursal(sucursal)
			        + ISBANStringUtils.formatearNumeroCuenta(numeroCuenta);
			String tipocanonico = null;
			try {
				tipocanonico = ISBANStringUtils.tipoCuentaCanonico(tipo);
			} catch (CuentaInvalidaException e) {
				throw new DAOException(e.getMessage());
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(tipocanonico + " " + nrocanonico);
			}
			CuentaPagoMisCuentas cpmc = new CuentaPagoMisCuentas(tipocanonico, nrocanonico, numeroCuenta);
			cpmc.setNroSucursal(sucursal);
			cuentasPMC.add(cpmc);
		}
		return new ArrayList<CuentaPagoMisCuentas>(cuentasPMC);
	}

	/**
	 * Generar iatx request data.
	 *
	 * @param cliente        the cliente
	 * @param cuentasBanelco the cuentas banelco
	 * @return the iatx request data
	 * @throws DAOException the DAO exception
	 */
	private IatxRequestData generarIatxRequestData(Cliente cliente, Collection<Cuenta> cuentasBanelco)
	        throws DAOException {
		if (cuentasBanelco.size() == 0) {
			throw new DAOException(CODIGO_DE_ERROR_NO_BANELCO);
		}

		IatxRequestData requestData = new IatxRequestData(cliente);

		int i = 0;
		for (Cuenta cuenta : cuentasBanelco) {

			requestData.addBodyValue(cuenta.getNroTarjetaCredito());

			if (++i == CANTIDAD_MAXIMA_BANELCO) {
				break;
			}
		}
		// completo hasta el total de cuentas del servicio
		while (i++ < CANTIDAD_MAXIMA_BANELCO) {
			requestData.addBodyValue("");
		}
		return requestData;
	}

}
