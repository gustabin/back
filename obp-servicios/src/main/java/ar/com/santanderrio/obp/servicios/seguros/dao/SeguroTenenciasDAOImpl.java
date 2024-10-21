/*
 *
 */
package ar.com.santanderrio.obp.servicios.seguros.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.seguros.dto.*;
import ar.com.santanderrio.obp.servicios.seguros.entities.*;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;

/**
 * The Class Impl.
 */
@Component
public class SeguroTenenciasDAOImpl implements SeguroTenenciasDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SeguroTenenciasDAOImpl.class);

	/** The path tenencias. */
	private static String pathTenencias = "/polizas/tenencias";

	private static String pathOfertaIntegradaGastoProtegido = "/cotizar/ofertaintegrada/GP";

	private static String pathOfertaIntegradaCompraProtegida = "/cotizar/ofertaintegrada/CP";

	private static String pathOfertaIntegradaEmitir = "/emitir/ofertaintegrada";

	private static String pathOfertaIntegradaFlag = "/cotizar/ofertaintegrada";

	/** The codigo retorno ok. */
	private static String CODIGO_RETORNO_OK = "0";

	/** The codigo retorno ok. */
	private static String CODIGO_RETORNO_SIN_POLIZAS = "1";

	/** The path login. */
	private static String pathLogin = "/login";

	/** The rest web client. */
	@Autowired
	private RestWebClient restWebClient;

	/** The codigo web service. */
	private static String CODIGO_WEB_SERVICE = "TENENCIA";
	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.seguros.dao.SeguroTenenciasDAO#
	 * consultarSeguros(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente)
	 */
	@Override
	public List<Poliza> consultarSeguros(String token, Cliente cliente) throws DAOException {
		TenenciaPolizaResponse tenenciaPolizaResponse = null;
		LOGGER.info("Consultando seguro tenencias.-");
		try {
			tenenciaPolizaResponse = obtenerPolizas(this.callLoginSeguros(token), cliente);
		} catch (RuntimeException e) {
			LOGGER.info("Error en el servicio.-", e);
			throw new DAOException();
		}
		if (!sinErrores(tenenciaPolizaResponse.getCodigoRespuesta())) {
			LOGGER.info("Error en consulta poliza.-");
			throw new DAOException();
		}
		if (tenenciaPolizaResponse.getRespuesta() == null) {
			LOGGER.info("Error en respuesta obtener poliza.-");
			throw new DAOException();
		}
		return tenenciaPolizaResponse.getRespuesta();
	}

	@Override
	public List<Poliza> consultarSegurosJwt(String tokenJwt, Cliente cliente) throws DAOException {
		TenenciaPolizaResponse tenenciaPolizaResponse = null;
		LOGGER.info("Consultando seguro tenencias.-");
		try {
			tenenciaPolizaResponse = obtenerPolizas(tokenJwt, cliente);
		} catch (RuntimeException e) {
			LOGGER.info("Error en el servicio.-", e);
			throw new DAOException();
		}
		if (!sinErrores(tenenciaPolizaResponse.getCodigoRespuesta())) {
			LOGGER.info("Error en consulta poliza.-");
			throw new DAOException();
		}
		if (tenenciaPolizaResponse.getRespuesta() == null) {
			LOGGER.info("Error en respuesta obtener poliza.-");
			throw new DAOException();
		}
		return tenenciaPolizaResponse.getRespuesta();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.seguros.dao.SeguroTenenciasDAO#
	 * callLoginSeguros(java.lang.String)
	 */
	@Override
	public String callLoginSeguros(String token) throws DAOException {
		WebClient clientLogin = restWebClient.obtenerClienteRest(CODIGO_WEB_SERVICE);

		clientLogin.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
		clientLogin.path(pathLogin);
		TenenciaLogin tenenciaLogin = new TenenciaLogin();
		tenenciaLogin.setLoginToken(token);
		TenenciaResponse tokenLogin = clientLogin.post(tenenciaLogin, TenenciaResponse.class);

		return tokenLogin.getRespuesta().getToken();

	}

	/**
	 * Sin errores.
	 *
	 * @param codigoRespuesta
	 *            the codigo respuesta
	 * @return true, if successful
	 */
	private boolean sinErrores(String codigoRespuesta) {

		return CODIGO_RETORNO_OK.equals(codigoRespuesta) || CODIGO_RETORNO_SIN_POLIZAS.equals(codigoRespuesta);
	}


	/**
	 * Obtener polizas.
	 *
	 * @param tokenJWT
	 *            the token
	 * @param cliente
	 *            the cliente
	 * @return the tenencia poliza response
	 * @throws DAOException
	 *             the DAO exception
	 */
	private TenenciaPolizaResponse obtenerPolizas(String tokenJWT, Cliente cliente) throws DAOException {
		TenenciaPoliza poliza = new TenenciaPoliza();
		poliza.setNup(cliente.getNup());
		poliza.setFechaNacimiento(cliente.getFechaNacimiento());
		poliza.setNumeroDocumento(cliente.getDni());
		poliza.setTipoDocumento(cliente.getTipoDocumento());
		WebClient clientPoliza = restWebClient.obtenerClienteRest(CODIGO_WEB_SERVICE);
		clientPoliza.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
		clientPoliza.header("token", tokenJWT);
		clientPoliza.path(pathTenencias);
		return clientPoliza.post(poliza, TenenciaPolizaResponse.class);
	}

	public CompraProtegidaResponse obtenerCompraProtegida(String tokenJWT, Cliente cliente, String numeroTarjeta, String numeroCuenta, String tipoTarjeta) throws DAOException {
		CompraProtegidaOut compraProtegida = new CompraProtegidaOut();
		compraProtegida.setNumeroCuenta(numeroCuenta);
		compraProtegida.setNumeroTarjeta(numeroTarjeta);
		if(tipoTarjeta.equalsIgnoreCase("7")) {
		compraProtegida.setTipoTarjeta("0" + tipoTarjeta);
		}
		else {
		compraProtegida.setTipoTarjeta(tipoTarjeta);
		}
		WebClient clientCompraProtegida = restWebClient.obtenerClienteRest(CODIGO_WEB_SERVICE);
		clientCompraProtegida.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
		clientCompraProtegida.header("token", tokenJWT);
		clientCompraProtegida.path(pathOfertaIntegradaCompraProtegida);
		return clientCompraProtegida.post(compraProtegida,  CompraProtegidaResponse.class);
	}


	public CompraProtegidaDTO consultarCompraProtegida(Cliente cliente, String numeroTarjeta, String numeroCuenta, String tipoTarjeta, String tokenJwt) throws DAOException {
		CompraProtegidaResponse compraProtegidaResponse = null;
		LOGGER.info("Consultando seguro tenencias.-");
		try {
			compraProtegidaResponse = obtenerCompraProtegida(tokenJwt, cliente, numeroTarjeta, numeroCuenta, tipoTarjeta);
		} catch (RuntimeException e) {
			LOGGER.info("Error en el servicio.-", e);
			throw new DAOException();
		}
		if (!sinErrores(compraProtegidaResponse.getCodigoRespuesta())) {
			LOGGER.info("Error en consulta gasto protegido.-");
			throw new DAOException();
		}
		if (compraProtegidaResponse.getRespuesta() == null) {
			LOGGER.info("Error en respuesta obtener gasto protegido.-");
			throw new DAOException();
		}
		return compraProtegidaResponse.getRespuesta().get(0);
	}


	public GastoProtegidoDTO consultarGastosProtegidos(Cliente cliente, GastoProtegido gastoProtegido, String tokenJwt) throws DAOException {
		GastoProtegidoResponse gastoProtegidoResponse = null;
		LOGGER.info("Consultando seguro tenencias.-");
		try {
			gastoProtegidoResponse = obtenerGastoProtegido(tokenJwt, cliente, gastoProtegido);
		} catch (RuntimeException e) {
			LOGGER.info("Error en el servicio.-", e);
			throw new DAOException();
		}
		if (!sinErrores(gastoProtegidoResponse.getCodigoRespuesta())) {
			LOGGER.info("Error en consulta gasto protegido.-");
			throw new DAOException();
		}
		if (gastoProtegidoResponse.getRespuesta() == null) {
			LOGGER.info("Error en respuesta obtener gasto protegido.-");
			throw new DAOException();
		}
		return gastoProtegidoResponse.getRespuesta().get(0);
	}

	public GastoProtegidoResponse obtenerGastoProtegido(String tokenJWT, Cliente cliente, GastoProtegido gastoProtegidoIn) throws DAOException {
		GastoProtegido gastoProtegido = new GastoProtegido();
		gastoProtegido.setCondicionLaboral(gastoProtegidoIn.getCondicionLaboral());
		WebClient clientGastoProtegido = restWebClient.obtenerClienteRest(CODIGO_WEB_SERVICE);
		clientGastoProtegido.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
		clientGastoProtegido.header("token", tokenJWT);
		clientGastoProtegido.path(pathOfertaIntegradaGastoProtegido);
		return clientGastoProtegido.post(gastoProtegido, GastoProtegidoResponse.class);
	}

	public EmisionOfertaIntegradaDTO emitirOfertaIntegradaGastoProtegido(String token, Cliente cliente, EmisionOfertaIntegrada nuevaEmisioView,  String sucursal, String tipoCuenta, String nroCuenta, String descripcionServicioPago) throws DAOException{

		EmisionOfertaIntegradaResponse emisionOfertaIntegradaResponse = null;

		LOGGER.info("Emitiendo Oferta Integrada.-");
		try {
			emisionOfertaIntegradaResponse = emitirPolizaOfertaIntegradaGastoProtegido(this.callLoginSeguros(token), cliente, nuevaEmisioView, sucursal, tipoCuenta, nroCuenta, descripcionServicioPago);
		} catch (RuntimeException e) {
			LOGGER.info("Error en el servicio.-", e);
			throw new DAOException();
		}
		if (!sinErrores(emisionOfertaIntegradaResponse.getCodigoRespuesta())) {
			LOGGER.info("Error en consulta emision oferta integrada.-");
			throw new DAOException();
		}
		if (emisionOfertaIntegradaResponse.getRespuesta() == null) {
			LOGGER.info("Error en respuesta obtener emision oferta integrada.-");
			throw new DAOException();
		}
		return emisionOfertaIntegradaResponse.getRespuesta().get(0);
	}



	public EmisionOfertaIntegradaDTO emitirOfertaIntegrada(String token, Cliente cliente, EmisionOfertaIntegrada nuevaEmisioView,  String numeroTarjeta, String numeroCuenta, Cuenta tarjetaElegida) throws DAOException{
		EmisionOfertaIntegradaResponse emisionOfertaIntegradaResponse = null;

		LOGGER.info("Emitiendo Oferta Integrada.-");
		try {
			emisionOfertaIntegradaResponse = emitirPolizaOfertaIntegrada(this.callLoginSeguros(token), cliente, nuevaEmisioView, numeroTarjeta, numeroCuenta, tarjetaElegida);
		} catch (RuntimeException e) {
			LOGGER.info("Error en el servicio.-", e);
			throw new DAOException();
		}
		if (!sinErrores(emisionOfertaIntegradaResponse.getCodigoRespuesta())) {
			LOGGER.info("Error en consulta emision oferta integrada.-");
			throw new DAOException();
		}
		if (emisionOfertaIntegradaResponse.getRespuesta() == null) {
			LOGGER.info("Error en respuesta obtener emision oferta integrada.-");
			throw new DAOException();
		}
		return emisionOfertaIntegradaResponse.getRespuesta().get(0);
	}

	public EmisionOfertaIntegradaResponse emitirPolizaOfertaIntegrada(String tokenJWT, Cliente cliente, EmisionOfertaIntegrada nuevaEmisioView, String numeroTarjeta, String numeroCuenta, Cuenta tarjetaElegida) throws DAOException {
		EmisionOfertaIntegradaOut emisionOfertaIntegrada = new EmisionOfertaIntegradaOut();
		emisionOfertaIntegrada.setNumeroCotizacion(nuevaEmisioView.getNumeroCotizacion());
		emisionOfertaIntegrada.setCodigoRamo(nuevaEmisioView.getCodigoRamo());
		emisionOfertaIntegrada.setCodigoProducto(nuevaEmisioView.getCodigoProducto());
		emisionOfertaIntegrada.setCodigoPlan(nuevaEmisioView.getCodigoPlan());
		emisionOfertaIntegrada.setTipoCuenta(null);
		emisionOfertaIntegrada.setNumeroCuenta(TarjetaUtils.sacarCerosDeAdelante(numeroCuenta));
		emisionOfertaIntegrada.setSucursal(null);
		if(nuevaEmisioView.getTipoTarjeta() != null && nuevaEmisioView.getTipoTarjeta().equalsIgnoreCase("7")) {
			emisionOfertaIntegrada.setTipoTarjeta("0"+ nuevaEmisioView.getTipoTarjeta());
			emisionOfertaIntegrada.setNumeroTarjeta(TarjetaUtils.sacarCerosDeAdelante(numeroTarjeta));
			emisionOfertaIntegrada.setOrigenServicio("VISA");
		}
		emisionOfertaIntegrada.setCargoPEP(nuevaEmisioView.getCargoPEP());
		emisionOfertaIntegrada.setOrigenFondos(nuevaEmisioView.getOrigenFondos());
		emisionOfertaIntegrada.setCodigoOcupacion(nuevaEmisioView.getCodigoOcupacion());
		emisionOfertaIntegrada.setIndicadorPEP(nuevaEmisioView.getIndicadorPEP());
		emisionOfertaIntegrada.setTipoPoliza(nuevaEmisioView.getTipoPoliza());
		TarjetaCompraProtegidaDTO tarjeta = null;
		if(tarjetaElegida.esAmex()){
			emisionOfertaIntegrada.setTipoTarjeta(nuevaEmisioView.getTipoTarjeta());
			emisionOfertaIntegrada.setNumeroTarjeta(TarjetaUtils.sacarCerosDeAdelante(numeroTarjeta).substring(0,15));
			emisionOfertaIntegrada.setOrigenServicio("AMEX");
			tarjeta = new TarjetaCompraProtegidaDTO(emisionOfertaIntegrada.getTipoTarjeta(), TarjetaUtils.sacarCerosDeAdelante(numeroTarjeta).substring(0,15), TarjetaUtils.sacarCerosDeAdelante(numeroCuenta));
		}
		else{
			tarjeta = new TarjetaCompraProtegidaDTO(emisionOfertaIntegrada.getTipoTarjeta(), TarjetaUtils.sacarCerosDeAdelante(numeroTarjeta), TarjetaUtils.sacarCerosDeAdelante(numeroCuenta));
		}
		List<TarjetaCompraProtegidaDTO> tarjetas = new ArrayList<TarjetaCompraProtegidaDTO>();
		tarjetas.add(tarjeta);
		emisionOfertaIntegrada.setTarjetas(tarjetas);
		EmisionOfertaIntegradaResponse respuesta = null;
		try {
			WebClient clientEmisionPolizaOfertaIntegrada = restWebClient.obtenerClienteRest(CODIGO_WEB_SERVICE);
			clientEmisionPolizaOfertaIntegrada.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
			clientEmisionPolizaOfertaIntegrada.header("token", tokenJWT);
			clientEmisionPolizaOfertaIntegrada.path(pathOfertaIntegradaEmitir);
			respuesta = clientEmisionPolizaOfertaIntegrada.post(emisionOfertaIntegrada, EmisionOfertaIntegradaResponse.class);
		}
		catch (ResponseProcessingException e) {
			throw new DAOException();
		} catch (ProcessingException e) {
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return respuesta;
	}



	public EmisionOfertaIntegradaResponse emitirPolizaOfertaIntegradaGastoProtegido(String tokenJWT, Cliente cliente, EmisionOfertaIntegrada nuevaEmisioView,  String sucursal, String tipoCuenta, String nroCuenta, String descripcionServicioPago) throws DAOException {
		EmisionOfertaIntegradaOut emisionOfertaIntegrada = new EmisionOfertaIntegradaOut();
		emisionOfertaIntegrada.setNumeroCotizacion(nuevaEmisioView.getNumeroCotizacion());
		emisionOfertaIntegrada.setCodigoRamo(nuevaEmisioView.getCodigoRamo());
		emisionOfertaIntegrada.setCodigoProducto(nuevaEmisioView.getCodigoProducto());
		emisionOfertaIntegrada.setCodigoPlan(nuevaEmisioView.getCodigoPlan());
		if(tipoCuenta != null && !tipoCuenta.isEmpty() && tipoCuenta.equalsIgnoreCase("02")){
			tipoCuenta = "09";
		}
		emisionOfertaIntegrada.setTipoCuenta(tipoCuenta);
		emisionOfertaIntegrada.setNumeroCuenta(TarjetaUtils.sacarCerosDeAdelante(nroCuenta));
		emisionOfertaIntegrada.setSucursal(sucursal);
		emisionOfertaIntegrada.setCargoPEP(nuevaEmisioView.getCargoPEP());
		emisionOfertaIntegrada.setOrigenFondos(nuevaEmisioView.getOrigenFondos());
		emisionOfertaIntegrada.setCodigoOcupacion(nuevaEmisioView.getCodigoOcupacion());
		emisionOfertaIntegrada.setIndicadorPEP(nuevaEmisioView.getIndicadorPEP());
		emisionOfertaIntegrada.setTipoPoliza(nuevaEmisioView.getTipoPoliza());
		emisionOfertaIntegrada.setTipoTarjeta(null);
		emisionOfertaIntegrada.setNumeroTarjeta(null);
		emisionOfertaIntegrada.setTarjetas(null);
		emisionOfertaIntegrada.setOrigenServicio(descripcionServicioPago);
		EmisionOfertaIntegradaResponse respuesta;
		try {
			WebClient clientEmisionPolizaOfertaIntegrada = restWebClient.obtenerClienteRest(CODIGO_WEB_SERVICE);
			clientEmisionPolizaOfertaIntegrada.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
			clientEmisionPolizaOfertaIntegrada.header("token", tokenJWT);
			clientEmisionPolizaOfertaIntegrada.path(pathOfertaIntegradaEmitir);
			respuesta = clientEmisionPolizaOfertaIntegrada.post(emisionOfertaIntegrada, EmisionOfertaIntegradaResponse.class);
		}
		catch (ResponseProcessingException e) {
			throw new DAOException();
		} catch (ProcessingException e) {
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}

		return respuesta;
	}

	public FlagCompraProtegidaDTO obtenerFlagCompraProtegida(String token) throws DAOException{

		FlagCompraProtegidaResponse flagCompraProtegidaResponse = null;
		String tokenJwt = null;
		LOGGER.info("Emitiendo Oferta Integrada.-");
		try {
			tokenJwt = this.callLoginSeguros(token);
			flagCompraProtegidaResponse = consultarFlagCompraProtegida(tokenJwt);
		} catch (RuntimeException e) {
			LOGGER.info("Error en el servicio.-", e);
			throw new DAOException();
		}
		if (!sinErrores(flagCompraProtegidaResponse.getCodigoRespuesta())) {
			LOGGER.info("Error en consulta emision oferta integrada.-");
			throw new DAOException();
		}
		if (flagCompraProtegidaResponse.getRespuesta() == null) {
			LOGGER.info("Error en respuesta obtener emision oferta integrada.-");
			throw new DAOException();
		}
		FlagCompraProtegidaDTO flag = new FlagCompraProtegidaDTO();
		flag.setRespuesta(flagCompraProtegidaResponse.getRespuesta());
		flag.setTokenJwt(tokenJwt);
		return flag;
	}


	public FlagCompraProtegidaResponse consultarFlagCompraProtegida(String tokenJWT) throws DAOException {
		WebClient clientFlagCompraProtegida = restWebClient.obtenerClienteRest(CODIGO_WEB_SERVICE);
		clientFlagCompraProtegida.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
		clientFlagCompraProtegida.header("token", tokenJWT);
		clientFlagCompraProtegida.path(pathOfertaIntegradaFlag);
		return clientFlagCompraProtegida.get(FlagCompraProtegidaResponse.class);
	}

}
