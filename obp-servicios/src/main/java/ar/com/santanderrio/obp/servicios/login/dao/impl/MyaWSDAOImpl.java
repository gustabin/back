/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaAtributosVinculados;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaBodyDatosRequest;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaBodyRequest;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaCodOperacionEnum;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestino;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestinoV3;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaHeaderRequest;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaMensajeError;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaProductoReq;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaServiciosEnum;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaSuscripcion;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaXmlRequest;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaXmlResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteEstadoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoCompaniaEnum;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaCodigoRetornoErrorException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaMailRegistradoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaServiceException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaTelefonoRegistradoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaWarningException;
import ar.com.santanderrio.obp.servicios.login.dao.MyaWSDAO;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.mya.dao.MyaDAO;
import ar.com.santanderrio.obp.servicios.mya.entities.GetEstadoClienteV3DTOOut;
import ar.com.santanderrio.obp.servicios.mya.entities.GetStatusClienteDTOOut;
import ar.com.santanderrio.obp.servicios.mya.entities.UpdateMensajesMyaDTOIn;
import ar.com.santanderrio.obp.servicios.mya.entities.UpdateMensajesMyaSuscripcion;
import ar.com.santanderrio.obp.servicios.mya.web.view.ConfirmarMailViewIn;

/**
 * The Class MyaWSDAOImpl.
 */
@Component
public class MyaWSDAOImpl implements MyaWSDAO {

	/** The mya DAO. */
	@Autowired
	private MyaDAO myaDAO;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MyaWSDAOImpl.class);

	/** The Constant MYACANAL. */
	private static final String MYACANAL = "04";

	/** The Constant MYASUBCANAL. */
	private static final String MYASUBCANAL = "99";

	/** The Constant CODIGO_ERROR. */
	private static final String CODIGO_OK = "0";

	/** The Constant CODIGO_WARNING. */
	private static final String CODIGO_WARNING = "2";

	/** The Constant CODIGO_ERROR. */
	private static final String CODIGO_ERROR = "1";

	/** The Constant ERROR_EMAIL. */
	private static final String ERROR_TELEFONO = "8";

	/** The Constant SECUENCIA_PRINCIPAL. */
	private static final String NRO_SECUENCIA_PRINCIPAL = "1";

	/** The Constant SECUENCIA_PRINCIPAL. */
	private static final String NRO_SECUENCIA_SECUNDARIO = "2";

	/** The Constant MAIL_YA_REGISTRADO. */
	private static final String MAIL_YA_REGISTRADO = "20";

	/** The app encoding. */
	@Value("${APP.ENCODING}")
	private String appEncoding;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.login.dao.MyaWSDAO#getEstadoCliente(ar.
	 * com.santanderrio.obp.servicios.login.dto.MyaDTOIn)
	 */
	public MyaDTOOut getEstadoCliente(MyaDTOIn myaDTOIn) throws MyaServiceException {

		MyaXmlRequest token = new MyaXmlRequest();
		MyaDTOOut myaDTOOut = new MyaDTOOut();
		MyaHeaderRequest header = new MyaHeaderRequest();
		header.setServicio(MyaServiciosEnum.ESTADO_CLIENTE);
		header.setCanal(MYACANAL);
		header.setSubCanal(MYASUBCANAL);
		header.setNup(myaDTOIn.getNup());
		token.setHeader(header);
		MyaBodyRequest body = new MyaBodyRequest();
		MyaBodyDatosRequest datos = new MyaBodyDatosRequest();
		body.setDatos(datos);
		token.setDatosAFirmar(body);

		MyaXmlResponse respuesta;
		try {
			respuesta = myaDAO.invocarMya(token);
			if (myaDTOIn.isSoloPrincipales()) {
				myaDTOOut = mapearEstadoCliente(respuesta);
			} else {
				myaDTOOut = mapearEstadoClienteTodos(respuesta);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new MyaServiceException(e.getMessage());
		}
		return myaDTOOut;

	}

	/**
	 * Mapear estado cliente.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the mya DTO out
	 */
	private MyaDTOOut mapearEstadoCliente(MyaXmlResponse respuesta) {

		MyaDTOOut myaDTOOut = new MyaDTOOut();
		List<MyaDestino> destinos = respuesta.getListMyaDestino();

		for (MyaDestino myaDestino : destinos) {
			if (MyaDestino.CEL.equals(myaDestino.getTipo().trim())
					&& NRO_SECUENCIA_PRINCIPAL.equals(myaDestino.getSecuencia())) {
				myaDTOOut.setCelularId(myaDestino.getId());
				myaDTOOut.setCelular(myaDestino.getDescripcion());
				myaDTOOut.setTipoCompaniaEnum(TipoCompaniaEnum.fromCodigo(myaDestino.getEmpresaCel()));
			}
			if (MyaDestino.MAIL.equals(myaDestino.getTipo())
					&& NRO_SECUENCIA_PRINCIPAL.equals(myaDestino.getSecuencia())) {
				myaDTOOut.setEmailId(myaDestino.getId());
				myaDTOOut.setEmail(myaDestino.getDescripcion());
			}
		}

		myaDTOOut.setClienteEstadoEnum(ClienteEstadoEnum.fromCodigoString(respuesta.getClienteEstado()));

		return myaDTOOut;
	}

	/**
	 * Mapear estado cliente.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the mya DTO out
	 */
	private MyaDTOOut mapearEstadoClienteTodos(MyaXmlResponse respuesta) {

		MyaDTOOut myaDTOOut = new MyaDTOOut();
		List<MyaDestino> destinos = respuesta.getListMyaDestino();

		for (MyaDestino myaDestino : destinos) {
			if (MyaDestino.CEL.equals(myaDestino.getTipo().trim())
					&& NRO_SECUENCIA_PRINCIPAL.equals(myaDestino.getSecuencia())) {
				myaDTOOut.setCelularId(myaDestino.getId());
				myaDTOOut.setCelular(myaDestino.getDescripcion());
				myaDTOOut.setTipoCompaniaEnum(TipoCompaniaEnum.fromCodigo(myaDestino.getEmpresaCel()));
			}
			if (MyaDestino.MAIL.equals(myaDestino.getTipo())
					&& NRO_SECUENCIA_PRINCIPAL.equals(myaDestino.getSecuencia())) {
				myaDTOOut.setEmailId(myaDestino.getId());
				myaDTOOut.setEmail(myaDestino.getDescripcion());
			}

			if (MyaDestino.CEL.equals(myaDestino.getTipo().trim())
					&& NRO_SECUENCIA_SECUNDARIO.equals(myaDestino.getSecuencia())) {
				myaDTOOut.setCelularSecundarioId(myaDestino.getId());
				myaDTOOut.setCelularSecundario(myaDestino.getDescripcion());
				myaDTOOut.setTipoCompaniaSecundarioEnum(TipoCompaniaEnum.fromCodigo(myaDestino.getEmpresaCel()));
			}
			if (MyaDestino.MAIL.equals(myaDestino.getTipo())
					&& NRO_SECUENCIA_SECUNDARIO.equals(myaDestino.getSecuencia())) {
				myaDTOOut.setEmailSecundarioId(myaDestino.getId());
				myaDTOOut.setEmailSecundario(myaDestino.getDescripcion());
			}
		}

		myaDTOOut.setClienteEstadoEnum(ClienteEstadoEnum.fromCodigoString(respuesta.getClienteEstado()));

		return myaDTOOut;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.login.dao.MyaWSDAO#updateDestino(ar.com
	 * .santanderrio.obp.servicios.login.dto.MyaDTOIn)
	 */
	public void updateDestino(MyaDTOIn myaDTOIn) throws MyaCodigoRetornoErrorException, MyaServiceException,
			MyaMailRegistradoException, MyaTelefonoRegistradoException {
		MyaXmlRequest token = new MyaXmlRequest();
		MyaHeaderRequest header = new MyaHeaderRequest();
		header.setServicio(MyaServiciosEnum.UPDATE_DESTINOS);
		header.setCanal(MYACANAL);
		header.setSubCanal(MYASUBCANAL);
		header.setNup(myaDTOIn.getNup());
		token.setHeader(header);
		MyaBodyRequest body = new MyaBodyRequest();
		MyaBodyDatosRequest datos = new MyaBodyDatosRequest();
		datos.setListMyaDestino(myaDTOIn.getListaDestinos());
		body.setDatos(datos);
		token.setDatosAFirmar(body);

		MyaXmlResponse respuesta;
		try {
			respuesta = myaDAO.invocarMya(token);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new MyaServiceException(e.getMessage());
		}

		if (CODIGO_WARNING.equals(respuesta.getCodigoRetorno()) && buscarErrorPorTipo(respuesta, MAIL_YA_REGISTRADO)) {
			LOGGER.debug("Warning en servicio Mya", "Warning mail ya registrado");
			throw new MyaMailRegistradoException(respuesta.getStatusDescripcion());
		}

		if (CODIGO_ERROR.equals(respuesta.getCodigoRetorno())) {
			if (buscarErrorPorTipo(respuesta, ERROR_TELEFONO)) {
				LOGGER.debug("Error en servicio Mya", "Error telefono ya registrado");
				throw new MyaTelefonoRegistradoException(respuesta.getStatusDescripcion());
			}
		}

		if (CODIGO_ERROR.equals(respuesta.getCodigoRetorno())) {
			throw new MyaCodigoRetornoErrorException("Ocurrio un error en el servicio");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.login.dao.MyaWSDAO#registrarConDestino(
	 * ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn)
	 */
	public void registrarConDestino(MyaDTOIn myaDTOIn) throws MyaServiceException, MyaWarningException,
			MyaCodigoRetornoErrorException, MyaTelefonoRegistradoException {
		MyaXmlRequest token = new MyaXmlRequest();
		MyaHeaderRequest header = new MyaHeaderRequest();
		header.setServicio(MyaServiciosEnum.REGISTRAR_CON_DESTINO);
		header.setCanal(MYACANAL);
		header.setSubCanal(MYASUBCANAL);
		header.setNup(myaDTOIn.getNup());
		token.setHeader(header);
		MyaBodyRequest body = new MyaBodyRequest();
		MyaBodyDatosRequest datos = new MyaBodyDatosRequest();
		datos.setTipoId(myaDTOIn.getMyaTipoIdEnum());
		datos.setClienteId(myaDTOIn.getDni());
		datos.setTipoPersona(myaDTOIn.getMyaTipoPersonaEnum());
		datos.setFechaNacimiento(myaDTOIn.getFechaDeNacimiento());
		datos.setNombre(myaDTOIn.getNombre());
		datos.setPrimerNombre(myaDTOIn.getPrimerApellido());
		datos.setSegundoNombre(myaDTOIn.getSegundoApellido());

		datos.setListMyaDestino(myaDTOIn.getListaDestinos());
		body.setDatos(datos);
		token.setDatosAFirmar(body);

		MyaXmlResponse respuesta;
		try {
			respuesta = myaDAO.invocarMya(token);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new MyaServiceException(e.getMessage());
		}
		if (CODIGO_WARNING.equals(respuesta.getCodigoRetorno()) && buscarErrorPorTipo(respuesta, MAIL_YA_REGISTRADO)) {
			LOGGER.debug("Warning en servicio Mya", "Warning mail ya registrado");
			throw new MyaWarningException(respuesta.getStatusDescripcion());
		}
		if (CODIGO_ERROR.equals(respuesta.getCodigoRetorno())) {
			if (buscarErrorPorTipo(respuesta, ERROR_TELEFONO)) {
				LOGGER.debug("Error en servicio Mya", "Error telefono ya registrado");
				throw new MyaTelefonoRegistradoException(respuesta.getStatusDescripcion());

			}
			LOGGER.debug("Error en servicio Mya", respuesta.getStatusDescripcion());
			throw new MyaCodigoRetornoErrorException(respuesta.getStatusDescripcion());
		}
	}

	/**
	 * busca error por tipo en la lista de errores.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param tipoError
	 *            the tipo error
	 * @return true, if successful
	 */
	private boolean buscarErrorPorTipo(MyaXmlResponse respuesta, String tipoError) {

		if (!CollectionUtils.isEmpty(respuesta.getListMyaMensajeError())) {

			for (MyaMensajeError error : respuesta.getListMyaMensajeError()) {

				if (tipoError.equals(error.getCodMensaje())) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.login.dao.MyaWSDAO#updateEstadoCliente(
	 * ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn)
	 */
	@Override
	public void updateEstadoCliente(MyaDTOIn myaDTOIn) throws MyaWarningException {
		MyaXmlRequest token = new MyaXmlRequest();
		MyaHeaderRequest header = new MyaHeaderRequest();
		header.setServicio(MyaServiciosEnum.UPDATE_ESTADO_CLIENTE);
		header.setCanal(MYACANAL);
		header.setSubCanal(MYASUBCANAL);
		header.setNup(myaDTOIn.getNup());
		header.setNivel("Cliente");
		token.setHeader(header);
		MyaBodyRequest body = new MyaBodyRequest();
		MyaBodyDatosRequest datos = new MyaBodyDatosRequest();
		body.setDatos(datos);
		token.setDatosAFirmar(body);
		MyaXmlResponse respuesta;
		try {
			respuesta = myaDAO.invocarMya(token);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new MyaWarningException("Error actualizando el estado del Cliente en MyA");
		}

		if (!CODIGO_OK.equals(respuesta.getCodigoRetorno())) {
			throw new MyaWarningException(respuesta.getStatusDescripcion());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.login.dao.MyaWSDAO#getSuscripciones(ar.
	 * com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * java.lang.String)
	 */
	@Override
	public MyaXmlResponse getSuscripciones(Cliente cliente, String numeroProducto) throws MyaServiceException {
		MyaXmlRequest token = new MyaXmlRequest();
		MyaHeaderRequest header = new MyaHeaderRequest();
		header.setServicio(MyaServiciosEnum.SUSCRIPCIONES);
		header.setCanal(MYACANAL);
		header.setSubCanal(MYASUBCANAL);
		header.setNup(cliente.getNup());
		token.setHeader(header);
		MyaBodyRequest body = new MyaBodyRequest();
		MyaBodyDatosRequest datos = armarDatosSuscripcionesParaFirmar(numeroProducto);
		body.setDatos(datos);
		token.setDatosAFirmar(body);

		MyaXmlResponse respuesta;
		try {
			respuesta = myaDAO.invocarMya(token);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new MyaServiceException(e.getMessage());
		}
		return respuesta;
	}

	/**
	 * Armar datos suscripciones para firmar.
	 *
	 * @param numeroProducto
	 *            the numero producto
	 * @return the mya body datos request
	 */
	private MyaBodyDatosRequest armarDatosSuscripcionesParaFirmar(String numeroProducto) {
		MyaBodyDatosRequest myaBodyDatosRequest = new MyaBodyDatosRequest();
		List<MyaProductoReq> listaProductos = new ArrayList<MyaProductoReq>();
		MyaProductoReq productoUno = new MyaProductoReq();

		productoUno.setNumeroProducto(numeroProducto);
		listaProductos.add(productoUno);
		myaBodyDatosRequest.setListMyaProductoReq(listaProductos);

		return myaBodyDatosRequest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.login.dao.MyaWSDAO#updateMensajes(ar.
	 * com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.mya.entities.UpdateMensajesMyaDTOIn)
	 */
	@Override
	public void updateMensajes(Cliente cliente, UpdateMensajesMyaDTOIn updateMensajesMyaDTOIn)
			throws MyaServiceException {
		MyaXmlRequest token = new MyaXmlRequest();
		MyaHeaderRequest header = new MyaHeaderRequest();
		header.setServicio(MyaServiciosEnum.UPDATE_MENSAJES);
		header.setCanal(MYACANAL);
		header.setSubCanal(MYASUBCANAL);
		header.setNup(cliente.getNup());
		token.setHeader(header);
		MyaBodyRequest body = new MyaBodyRequest();
		MyaBodyDatosRequest datos = armarDatosUpdateMensajes(updateMensajesMyaDTOIn);
		body.setDatos(datos);
		token.setDatosAFirmar(body);

		MyaXmlResponse respuesta;
		try {
			respuesta = myaDAO.invocarMya(token);
			if (Integer.parseInt(respuesta.getCodigoRetorno()) != 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new MyaServiceException(e.getMessage());
		}
	}

	/**
	 * Armar datos update mensajes.
	 *
	 * @param updateMensajesMyaDTOIn
	 *            the update mensajes mya DTO in
	 * @return the mya body datos request
	 */
	private MyaBodyDatosRequest armarDatosUpdateMensajes(UpdateMensajesMyaDTOIn updateMensajesMyaDTOIn) {
		MyaBodyDatosRequest myaBodyDatosRequest = new MyaBodyDatosRequest();
		List<MyaSuscripcion> listaSuscripcion = new ArrayList<MyaSuscripcion>();

		for (UpdateMensajesMyaSuscripcion updateMensajesMyaSuscripcion : updateMensajesMyaDTOIn
				.getListaUpdateMensajesMyaSuscripcion()) {
			MyaSuscripcion suscripcion = new MyaSuscripcion();
			suscripcion.setCodigoOperacion(updateMensajesMyaSuscripcion.getMyaCodOperacionEnum());
			suscripcion.setNroMensajeSuscripcion(updateMensajesMyaSuscripcion.getNumeroMensajeSuscripcion());
			if (!MyaCodOperacionEnum.BAJA.equals(updateMensajesMyaSuscripcion.getMyaCodOperacionEnum())) {
				suscripcion.setDestinoVinculado(updateMensajesMyaSuscripcion.getMyaDestinoVinculado());
				suscripcion.setCodFrecSuscripcion(updateMensajesMyaSuscripcion.getCodigoFrecuenciaSuscripcion());
				suscripcion.setCodDapSuscripcion(updateMensajesMyaSuscripcion.getCodigoDapSuscripcion());
				suscripcion.setMsgMultiple(updateMensajesMyaSuscripcion.getListaMsgMultiples());
			}
			if (!MyaCodOperacionEnum.ALTA.equals(updateMensajesMyaSuscripcion.getMyaCodOperacionEnum())) {
				suscripcion.setNroSuscripcion(updateMensajesMyaSuscripcion.getNumeroSuscripcion());
			}
			if (StringUtils.isNotBlank(updateMensajesMyaSuscripcion.getAtributoVinculadoClave())
					&& StringUtils.isNotBlank(updateMensajesMyaSuscripcion.getAtributoVinculadoValor())) {
				List<MyaAtributosVinculados> listMyaAtributosVinculados = new ArrayList<MyaAtributosVinculados>();
				MyaAtributosVinculados myaAtributosVinculados = new MyaAtributosVinculados();
				myaAtributosVinculados.setAttrVincClave(updateMensajesMyaSuscripcion.getAtributoVinculadoClave());
				myaAtributosVinculados.setAttrVincDato(updateMensajesMyaSuscripcion.getAtributoVinculadoValor());
				listMyaAtributosVinculados.add(myaAtributosVinculados);
				suscripcion.setListMyaAtributosVinculados(listMyaAtributosVinculados);
			}
			listaSuscripcion.add(suscripcion);
		}

		myaBodyDatosRequest.setListMyaSuscripcion(listaSuscripcion);

		return myaBodyDatosRequest;
	}

	@Override
	public MyaDTOOut getStatusCliente(MyaDTOIn myaDTOIn) throws MyaServiceException {
	
		MyaXmlRequest myaXmlRequest = new MyaXmlRequest();
		MyaHeaderRequest myaHeaderRequest = new MyaHeaderRequest();
		MyaDTOOut myaDTOOut = new MyaDTOOut();
		myaHeaderRequest.setServicio(MyaServiciosEnum.STATUS_CLIENTE);
		myaHeaderRequest.setCanal(MYACANAL);
		myaHeaderRequest.setSubCanal(MYASUBCANAL);
		myaHeaderRequest.setNup(myaDTOIn.getNup());
		myaXmlRequest.setHeader(myaHeaderRequest);
		
		
		GetStatusClienteDTOOut respuesta = new GetStatusClienteDTOOut();
		try {
			respuesta = myaDAO.invocarGetStatusCliente(myaXmlRequest);
			myaDTOOut = mapearStatusCliente(respuesta);
				
			} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new MyaServiceException(e.getMessage());
		}
				
		return myaDTOOut;
	
	}
	
	private MyaDTOOut mapearStatusCliente(GetStatusClienteDTOOut respuesta) {

		MyaDTOOut myaDTOOut = new MyaDTOOut();
		List<MyaDestino> destinos = respuesta.getListMyaDestino();

		for (MyaDestino myaDestino : destinos) {
			if (MyaDestino.CEL.equals(myaDestino.getTipo().trim())
					&& NRO_SECUENCIA_PRINCIPAL.equals(myaDestino.getSecuencia())) {
				myaDTOOut.setCelularId(myaDestino.getId());
				myaDTOOut.setCelular(myaDestino.getDescripcion());
				myaDTOOut.setTipoCompaniaEnum(TipoCompaniaEnum.fromCodigo(myaDestino.getEmpresaCel()));
				myaDTOOut.setDestinoValidadoCelular(myaDestino.getDestinoValidado());
			}
			if (MyaDestino.MAIL.equals(myaDestino.getTipo())
					&& NRO_SECUENCIA_PRINCIPAL.equals(myaDestino.getSecuencia())) {
				myaDTOOut.setEmailId(myaDestino.getId());
				myaDTOOut.setEmail(myaDestino.getDescripcion());
				myaDTOOut.setDestinoValidadoEmail(myaDestino.getDestinoValidado());
			}

			if (MyaDestino.CEL.equals(myaDestino.getTipo().trim())
					&& NRO_SECUENCIA_SECUNDARIO.equals(myaDestino.getSecuencia())) {
				myaDTOOut.setCelularSecundarioId(myaDestino.getId());
				myaDTOOut.setCelularSecundario(myaDestino.getDescripcion());
				myaDTOOut.setTipoCompaniaSecundarioEnum(TipoCompaniaEnum.fromCodigo(myaDestino.getEmpresaCel()));
				myaDTOOut.setDestinoValidadoCelularSecundario(myaDestino.getDestinoValidado());
			}
			if (MyaDestino.MAIL.equals(myaDestino.getTipo())
					&& NRO_SECUENCIA_SECUNDARIO.equals(myaDestino.getSecuencia())) {
				myaDTOOut.setEmailSecundarioId(myaDestino.getId());
				myaDTOOut.setEmailSecundario(myaDestino.getDescripcion());
				myaDTOOut.setDestinoValidadoEmailSecundario(myaDestino.getDestinoValidado());
			}
		}

		myaDTOOut.setClienteEstadoEnum(ClienteEstadoEnum.fromCodigoString(respuesta.getClienteEstado()));

		return myaDTOOut;
	}

	@Override
	public String confirmarEmail(Cliente cliente, ConfirmarMailViewIn confirmarMailIn) throws MyaServiceException {
		
		MyaXmlRequest myaXmlRequest = new MyaXmlRequest();
		MyaHeaderRequest myaHeaderRequest = new MyaHeaderRequest();
		myaHeaderRequest.setServicio(MyaServiciosEnum.CONFIRMAR_EMAIL);
		myaHeaderRequest.setCanal(MYACANAL);
		myaHeaderRequest.setSubCanal(MYASUBCANAL);
		myaHeaderRequest.setNup(cliente.getNup());
		myaXmlRequest.setHeader(myaHeaderRequest);
		MyaBodyRequest myaBodyRequest = new MyaBodyRequest();
		MyaBodyDatosRequest datos = new MyaBodyDatosRequest();
		datos.setEmailConfirmado(confirmarMailIn.getMailAConfirmar());
		myaBodyRequest.setDatos(datos);
		myaXmlRequest.setDatosAFirmar(myaBodyRequest);
		
		String respuesta = StringUtils.EMPTY;
		try {
			respuesta = myaDAO.invocarConfirmarEmail(myaXmlRequest);
			} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new MyaServiceException(e.getMessage());
		}
				
		return respuesta;
	}

	@Override
	public MyaDTOOut getEstadoClienteV3(MyaDTOIn myaDTOIn) throws MyaServiceException {

		MyaXmlRequest myaXmlRequest = new MyaXmlRequest();
		MyaHeaderRequest myaHeaderRequest = new MyaHeaderRequest();
		MyaDTOOut myaDTOOut = new MyaDTOOut();
		myaHeaderRequest.setServicio(MyaServiciosEnum.ESTADO_CLIENTE_V3);
		myaHeaderRequest.setCanal(MYACANAL);
		myaHeaderRequest.setSubCanal(MYASUBCANAL);
		myaHeaderRequest.setNup(myaDTOIn.getNup());
		myaXmlRequest.setHeader(myaHeaderRequest);
		
		GetEstadoClienteV3DTOOut respuesta = new GetEstadoClienteV3DTOOut();
		try {
			respuesta = myaDAO.invocarGetEstadoClienteV3(myaXmlRequest);
			myaDTOOut = mapearEstadoClienteV3(respuesta);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new MyaServiceException(e.getMessage());
		}

		return myaDTOOut;
	}

	/**
	 * mapearEstadoClienteV3
	 *
	 * @param respuesta the respuesta
	 * @return the MyaDTOOut
	 */
	private MyaDTOOut mapearEstadoClienteV3(GetEstadoClienteV3DTOOut respuesta) {

		MyaDTOOut myaDTOOut = new MyaDTOOut();
		List<MyaDestinoV3> destinos = respuesta.getListMyaDestino();

		for (MyaDestinoV3 myaDestino : destinos) {
			if (MyaDestino.CEL.equals(myaDestino.getTipo().trim())
					&& NRO_SECUENCIA_PRINCIPAL.equals(myaDestino.getSecuencia())) {
				myaDTOOut.setCelularId(myaDestino.getId());
				myaDTOOut.setCelular(myaDestino.getDescripcion());
				myaDTOOut.setTipoCompaniaEnum(TipoCompaniaEnum.fromCodigo(myaDestino.getEmpresaCel()));
				myaDTOOut.setDestinoValidadoCelular(myaDestino.getDestinoValidado());
				myaDTOOut.setFechaAltaCelular(myaDestino.getDestinoAlta());
				myaDTOOut.setFechaModificadoCelular(myaDestino.getDestinoModificado());
				myaDTOOut.setCanalCelular(myaDestino.getDestinoCanal());
				myaDTOOut.setSubCanalCelular(myaDestino.getDestinoSubCanal());
			}
			if (MyaDestino.MAIL.equals(myaDestino.getTipo())
					&& NRO_SECUENCIA_PRINCIPAL.equals(myaDestino.getSecuencia())) {
				myaDTOOut.setEmailId(myaDestino.getId());
				myaDTOOut.setEmail(myaDestino.getDescripcion());
				myaDTOOut.setDestinoValidadoEmail(myaDestino.getDestinoValidado());
				myaDTOOut.setFechaAltaEmail(myaDestino.getDestinoAlta());
				myaDTOOut.setFechaModificadoEmail(myaDestino.getDestinoModificado());
				myaDTOOut.setCanalEmail(myaDestino.getDestinoCanal());
				myaDTOOut.setSubCanalEmail(myaDestino.getDestinoSubCanal());
			}

			if (MyaDestino.CEL.equals(myaDestino.getTipo().trim())
					&& NRO_SECUENCIA_SECUNDARIO.equals(myaDestino.getSecuencia())) {
				myaDTOOut.setCelularSecundarioId(myaDestino.getId());
				myaDTOOut.setCelularSecundario(myaDestino.getDescripcion());
				myaDTOOut.setTipoCompaniaSecundarioEnum(TipoCompaniaEnum.fromCodigo(myaDestino.getEmpresaCel()));
				myaDTOOut.setDestinoValidadoCelularSecundario(myaDestino.getDestinoValidado());
				myaDTOOut.setFechaAltaCelularSecundario(myaDestino.getDestinoAlta());
				myaDTOOut.setFechaModificadoCelularSecundario(myaDestino.getDestinoModificado());
				myaDTOOut.setCanalCelularSecundario(myaDestino.getDestinoCanal());
				myaDTOOut.setSubCanalCelularSecundario(myaDestino.getDestinoSubCanal());
			}
			if (MyaDestino.MAIL.equals(myaDestino.getTipo())
					&& NRO_SECUENCIA_SECUNDARIO.equals(myaDestino.getSecuencia())) {
				myaDTOOut.setEmailSecundarioId(myaDestino.getId());
				myaDTOOut.setEmailSecundario(myaDestino.getDescripcion());
				myaDTOOut.setDestinoValidadoEmailSecundario(myaDestino.getDestinoValidado());
				myaDTOOut.setFechaAltaEmailSecundario(myaDestino.getDestinoAlta());
				myaDTOOut.setFechaModificadoEmailSecundario(myaDestino.getDestinoModificado());
				myaDTOOut.setCanalEmailSecundario(myaDestino.getDestinoCanal());
				myaDTOOut.setSubCanalEmailSecundario(myaDestino.getDestinoSubCanal());
			}
		}

		myaDTOOut.setClienteEstadoEnum(ClienteEstadoEnum.fromCodigoString(respuesta.getClienteEstado()));

		return myaDTOOut;
	}

}
