/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestino;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteEstadoEnum;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaCodigoRetornoErrorException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaMailRegistradoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaServiceException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaTelefonoRegistradoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaWarningException;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.dao.ContratosMyaDAO;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.ConsultaCliente;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.ConsultaClienteParam;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.ContratoParam;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.RegistroClienteParam;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.login.dao.MyaWSDAO;
import ar.com.santanderrio.obp.servicios.login.dao.ReporteTyCDAO;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMyaIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.login.entity.TipoIdEnum;
import ar.com.santanderrio.obp.servicios.login.entity.TipoPersonaEnum;
import ar.com.santanderrio.obp.servicios.mya.web.view.ConfirmarMailViewIn;

/**
 * The Class MyaBOImpl.
 */
@Component
public class MyaBOImpl implements MyaBO {

	/** The Constant DOS. */
	private static final String DOS = "2";

	/** The Constant UNO. */
	private static final String UNO = "1";

	/** The Constant NO_SE_INVOCA_MYA. */
	private static final String NO_SE_INVOCA_MYA = "No se invoca al servicio MyA. Sin datos para actualizar.-";

	/** The Constant GUION_CELULAR. */
	private static final String GUION_CELULAR = "-";

	/** The Constant SECUENCIA_DEFAULT. */
	private static final String SECUENCIA_DEFAULT = UNO;

	/** The Constant SECUENCIA_SECUNDARIA. */
	private static final String SECUENCIA_SECUNDARIA = DOS;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MyaBOImpl.class);

	/** The contratos mya dao. */
	@Autowired
	private ContratosMyaDAO contratosMyaDAO;

	/** The reporte ty CDAO. */
	@Autowired
	private ReporteTyCDAO reporteTyCDAO;

	/** The mya WSDAO. */
	@Autowired
	private MyaWSDAO myaWSDAO;

	/** The Constant ACEPTA_CONTRATO. */
	private static final String ACEPTA_CONTRATO = UNO;

	private static final String ESTADO_DE_CAMPAÑA = "2";
	
	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The codigos de las compañias de celular. */
	@Value("#{'${COMPANIAS.CELULAR.CODIGOS}'.split(',')}")
	private List<String> companiasCelular;
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.login.bo.MyaBO#obtenerEstadoMya(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente, java.lang.Boolean)
	 */
	public Respuesta<CredencialesMya> obtenerEstadoMya(Cliente cliente, Boolean clienteSinonimo) {

		MyaDTOIn myaDTOIn = new MyaDTOIn();

		Respuesta<CredencialesMya> respuesta = new Respuesta<CredencialesMya>();
		ConsultaClienteParam consultaClienteParam = new ConsultaClienteParam();
		ConsultaCliente consultaCliente;
		consultaClienteParam.setDni(cliente.getDni());
		if (clienteSinonimo) {
			consultaClienteParam.setNup(cliente.getNup());
			consultaClienteParam.setFechaNacimiento(cliente.getFechaNacimiento());
		}

		try {
			consultaCliente = contratosMyaDAO.consultaClientes(consultaClienteParam);
		} catch (DAOException ex) {
			LOGGER.error(ex.getMessage(), ex);
			return respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_TOTAL,
					CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
		}

		myaDTOIn.setSoloPrincipales(false);
		MyaDTOOut myaDTOOut = consultaWsStatusCliente(cliente, myaDTOIn);
		respuesta.setRespuesta(mapearCredencialesMya(consultaCliente, myaDTOOut));
		respuesta.setEstadoRespuesta(obtenerEstadoRespuestaMya(consultaCliente, myaDTOOut));
		return respuesta;
	}

	/**
	 * Obtener estado respuesta mya.
	 *
	 * @param consultaCliente
	 *            the consulta cliente
	 * @param myaDTOOut
	 *            the mya DTO out
	 * @return the estado respuesta
	 */
	private EstadoRespuesta obtenerEstadoRespuestaMya(ConsultaCliente consultaCliente, MyaDTOOut myaDTOOut) {

		if (consultaCliente.getAceptacionContrato() != null
				&& ACEPTA_CONTRATO.equals(consultaCliente.getAceptacionContrato())
				&& (ClienteEstadoEnum.SUSCRIPTO_ACTIVO.equals(myaDTOOut.getClienteEstadoEnum())
						|| ClienteEstadoEnum.TIMEOUT.equals(myaDTOOut.getClienteEstadoEnum()))) {
			return EstadoRespuesta.OK;
		}
		return EstadoRespuesta.WARNING;
	}

	/**
	 * Consulta el estado del cliente en el WS de MYA.
	 *
	 * @param cliente
	 *            the cliente
	 * @param myaDTOIn
	 *            the mya DTO in
	 * @return the mya DTO out
	 */
	public MyaDTOOut consultaWsEstadoCliente(Cliente cliente, MyaDTOIn myaDTOIn) {
		MyaDTOOut myaDTOOut = new MyaDTOOut();
		try {
			// Este seria primer acceso
			myaDTOIn.setNup(cliente.getNup());
			myaDTOOut = myaWSDAO.getEstadoCliente(myaDTOIn);
		} catch (MyaServiceException e) {
			LOGGER.error(e.getMessage(), e);
			myaDTOOut.setClienteEstadoEnum(ClienteEstadoEnum.TIMEOUT);
		}
		return myaDTOOut;
	}
	
	public MyaDTOOut consultaWsStatusCliente(Cliente cliente, MyaDTOIn myaDTOIn) {
		MyaDTOOut myaDTOOut = new MyaDTOOut();
		try {
			// Este seria primer acceso
			myaDTOIn.setNup(cliente.getNup());
			myaDTOOut = myaWSDAO.getStatusCliente(myaDTOIn);
		} catch (MyaServiceException e) {
			LOGGER.error(e.getMessage(), e);
			myaDTOOut.setClienteEstadoEnum(ClienteEstadoEnum.TIMEOUT);
		}
		return myaDTOOut;
	}

	@Override
	public MyaDTOOut consultaWsEstadoClienteV3(Cliente cliente, MyaDTOIn myaDTOIn) {
		MyaDTOOut myaDTOOut = new MyaDTOOut();
		try {
			myaDTOIn.setNup(cliente.getNup());
			myaDTOOut = myaWSDAO.getEstadoClienteV3(myaDTOIn);
		} catch (MyaServiceException e) {
			LOGGER.error(e.getMessage(), e);
			myaDTOOut.setClienteEstadoEnum(ClienteEstadoEnum.TIMEOUT);
		}
		return myaDTOOut;
	}

	/**
	 * * Mapea los datos que consulto al SP y al WS Mya para devolver al front.
	 *
	 * @param cc
	 *            the cc
	 * @param myaDTOOut
	 *            the mya DTO out
	 * @return the credenciales mya
	 */
	private CredencialesMya mapearCredencialesMya(ConsultaCliente cc, MyaDTOOut myaDTOOut) {

		CredencialesMya credencialesMya = new CredencialesMya();

		credencialesMya.setCompanias(companiasCelular);
		String celular = myaDTOOut.getCelular();
		String celularSecundario = myaDTOOut.getCelularSecundario();
		if (StringUtils.isNotBlank(celular)) {
			if (celular.contains(GUION_CELULAR)) {

				String[] array = celular.split(GUION_CELULAR);
				credencialesMya.setCodigoArea(array[0]);
				credencialesMya.setCelular(array[1]);
			} else {
				credencialesMya.setCelular(celular);
			}
		}
		if (StringUtils.isNotBlank(celularSecundario)) {
			if (celularSecundario.contains(GUION_CELULAR)) {

				String[] array = celularSecundario.split(GUION_CELULAR);
				credencialesMya.setCodigoAreaSecundario(array[0]);
				credencialesMya.setCelularSecundario(array[1]);
			} else {
				credencialesMya.setCelularSecundario(celularSecundario);
			}
		}
		credencialesMya.setCelularId(myaDTOOut.getCelularId());
		credencialesMya.setCelularSecundarioId(myaDTOOut.getCelularSecundarioId());
		credencialesMya.setEmailId(myaDTOOut.getEmailId());
		credencialesMya.setEmail(myaDTOOut.getEmail());
		credencialesMya.setEmailSecundario(myaDTOOut.getEmailSecundario());
		credencialesMya.setEmailSecundarioId(myaDTOOut.getEmailSecundarioId());
		credencialesMya.setMostrarStackActualizarDatos(revisarSiCorrespondeMostrarStack(myaDTOOut));
		
		if (myaDTOOut.getClienteEstadoEnum() != null) {
			credencialesMya.setClienteEstado(myaDTOOut.getClienteEstadoEnum().getCodigo());
		}
		if (myaDTOOut.getTipoCompaniaEnum() != null) {
			credencialesMya.setCompaniaSeleccionada(myaDTOOut.getTipoCompaniaEnum().getDescripcion());
		}
		if (myaDTOOut.getTipoCompaniaSecundarioEnum() != null) {
			credencialesMya
					.setCompaniaSeleccionadaSecundaria(myaDTOOut.getTipoCompaniaSecundarioEnum().getDescripcion());
		}
		if (!StringUtils.isBlank(cc.getAceptacionContrato())) {
			credencialesMya.setAceptacionContrato(Long.valueOf(cc.getAceptacionContrato()));
		} else {
			credencialesMya.setAceptacionContrato(null);
		}

		return credencialesMya;
	}

	private Boolean revisarSiCorrespondeMostrarStack (MyaDTOOut myaDTOOut) {
		
		return ESTADO_DE_CAMPAÑA.equals(myaDTOOut.getDestinoValidadoEmail()) || ESTADO_DE_CAMPAÑA.equals(myaDTOOut.getDestinoValidadoEmailSecundario());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.login.bo.MyaBO#updateDestinos(ar.com.
	 * santanderrio.obp.servicios.login.dto.CredencialesMyaIn,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	public Respuesta<Void> updateDestinos(CredencialesMyaIn datos, Cliente cliente) {
		Respuesta<Void> respuesta;
		MyaDTOIn myaDTOIn = new MyaDTOIn();
		try {
			List<MyaDestino> destinos = buildListaDestinos(datos);
			// Si no es una alta
			if (!destinos.isEmpty()) {
				myaDTOIn.setNup(cliente.getNup());
				myaDTOIn.setListaDestinos(destinos);
				myaWSDAO.updateDestino(myaDTOIn);
			} else {
				LOGGER.info(NO_SE_INVOCA_MYA);
			}
			respuesta = respuestaFactory.crearRespuestaOk(Void.class);
		} catch (MyaMailRegistradoException e) {
			// Cuando rompe el servicio de mya
			respuesta = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.MYA_MAIL_YA_REGISTRADO,
					CodigoMensajeConstantes.MYA_FEEDBACK_WARNING_MAIL);
			LOGGER.error(e.getMessage(), e);
		} catch (MyaTelefonoRegistradoException e) {
			// Cuando rompe el servicio de mya
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.MYA_CELULAR_YA_REGISTRADO,
					CodigoMensajeConstantes.MYA_FEEDBACK_ERROR_CELULAR);
		} catch (MyaCodigoRetornoErrorException e) {
			// Cuando falla Mya y da un error
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.MYA_ERROR, null);
		} catch (MyaServiceException e) {
			// Cuando rompe el servicio de mya
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.MYA_ERROR, null);
		}
		return respuesta;
	}

	/**
	 * * Llamada al store de actualizarContrato.
	 *
	 * @param cliente
	 *            the cliente
	 * @param clienteSinonimo
	 *            the cliente sinonimo
	 * @return true, if successful
	 */
	public Respuesta<Void> actualizaContrato(Cliente cliente, Boolean clienteSinonimo) {

		Respuesta<Void> respuesta;

		try {

			ContratoParam contrato = new ContratoParam();
			if (clienteSinonimo) {
				contrato.setNup(cliente.getNup());
				contrato.setFechaNacimiento(cliente.getFechaNacimiento());
			}
			contrato.setDni(cliente.getDni());
			contratosMyaDAO.actualizarContrato(contrato);
			respuesta = respuestaFactory.crearRespuestaOk(Void.class);

		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_TOTAL,
					CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);

		}
		return respuesta;
	}

	/**
	 * * Llamada al store de alta de cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return true, if successful
	 */
	public Respuesta<Void> altaCliente(Cliente cliente) {
		Boolean isSaved = false;
		Respuesta<Void> respuesta;
		RegistroClienteParam registroCliente = new RegistroClienteParam();
		try {
			registroCliente.setFechaNacimiento(cliente.getFechaNacimiento());
			registroCliente.setNombre(cliente.getNombre());
			registroCliente.setApellido(cliente.getApellido1());
			registroCliente.setDni(cliente.getDni());
			List<Cuenta> cuentas = cliente.getCuentas();
			if (CollectionUtils.isNotEmpty(cuentas)) {
				Cuenta cuenta = cuentas.get(0);
				registroCliente.setSucursalOrigen(cuenta.getNroSucursal());
			}
			registroCliente.setNup(cliente.getNup());
			isSaved = contratosMyaDAO.crearRegistro(registroCliente);
			if (isSaved) {
				respuesta = respuestaFactory.crearRespuestaOk(Void.class);
			} else {
				respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_TOTAL,
						CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
			}
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_TOTAL,
					CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
		}
		return respuesta;
	}

	/**
	 * build que arma que objeto request para UpdateListaDestino.
	 * 
	 * @param datos
	 *            the datos
	 * @return the list
	 */
	public List<MyaDestino> buildListaDestinos(CredencialesMyaIn datos) {
		List<MyaDestino> listaDestinos = new ArrayList<MyaDestino>();

		if (datos.getTipoOperacionCelular() != null) {
			MyaDestino destinoCelular = new MyaDestino();
			destinoCelular.setId(datos.getCelularId());
			destinoCelular.setCodigoOperacion(datos.getTipoOperacionCelular());
			destinoCelular.setTipo(MyaDestino.CEL);
			destinoCelular.setSecuencia(SECUENCIA_DEFAULT);
			destinoCelular.setDescripcion(datos.getCodigoArea() + GUION_CELULAR + datos.getCelular());
			destinoCelular.setEmpresaCel(datos.getCompaniaSeleccionada().getCodigo());
			listaDestinos.add(destinoCelular);
		}

		if (datos.getTipoOperacionEmail() != null) {
			MyaDestino destinoEmail = new MyaDestino();
			destinoEmail.setId(datos.getEmailId());
			destinoEmail.setCodigoOperacion(datos.getTipoOperacionEmail());
			destinoEmail.setTipo(MyaDestino.MAIL);
			destinoEmail.setSecuencia(SECUENCIA_DEFAULT);
			destinoEmail.setDescripcion(datos.getEmail());
			listaDestinos.add(destinoEmail);
		}

		if (!datos.isSoloPrimarios()) {
		    if (datos.getTipoOperacionCelularSecundaria() != null) {
				MyaDestino destinoCelular = new MyaDestino();
				destinoCelular.setId(datos.getCelularId());
				destinoCelular.setCodigoOperacion(datos.getTipoOperacionCelularSecundaria());
				destinoCelular.setTipo(MyaDestino.CEL);
				destinoCelular.setSecuencia(SECUENCIA_SECUNDARIA);
				destinoCelular
						.setDescripcion(datos.getCodigoAreaSecundario() + GUION_CELULAR + datos.getCelularSecundario());
				destinoCelular.setEmpresaCel(datos.getCompaniaSeleccionadaSecundaria().getCodigo());
				listaDestinos.add(destinoCelular);
			}

		    if (datos.getTipoOperacionEmailSecundaria() != null) {
				MyaDestino destinoEmail = new MyaDestino();
				destinoEmail.setId(datos.getEmailSecundarioId());
				destinoEmail.setCodigoOperacion(datos.getTipoOperacionEmailSecundaria());
				destinoEmail.setTipo(MyaDestino.MAIL);
				destinoEmail.setSecuencia(SECUENCIA_SECUNDARIA);
				destinoEmail.setDescripcion(datos.getEmailSecundario());
				listaDestinos.add(destinoEmail);
			}
		}
		return listaDestinos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.login.bo.MyaBO#registrarClienteMya(ar.
	 * com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.login.dto.CredencialesMyaIn)
	 */
	@Override
	public Respuesta<Void> registrarClienteMya(Cliente cliente, CredencialesMyaIn datos) {
		Respuesta<Void> respuesta = null;
		List<MyaDestino> destinos = null;
		try {

			destinos = buildListaDestinos(datos);
			// Si no es una alta
			if (!destinos.isEmpty()) {

				MyaDTOIn myaDTOIn = new MyaDTOIn();
				myaDTOIn.setMyaTipoIdEnum(TipoIdEnum.obtenerTipoIdPorCodigo(cliente.getTipoDocumento()));
				myaDTOIn.setDni(cliente.getDni());
				myaDTOIn.setMyaTipoPersonaEnum(TipoPersonaEnum.obtenerTipoPersonaPorCodigo(cliente.getTipoPersona()));
				myaDTOIn.setFechaDeNacimiento(cliente.getFechaNacimiento());
				myaDTOIn.setNombre(cliente.getNombre());
				myaDTOIn.setPrimerApellido(cliente.getApellido1());
				myaDTOIn.setSegundoApellido(cliente.getApellido2());
				myaDTOIn.setListaDestinos(destinos);
				myaDTOIn.setNup(cliente.getNup());
				myaWSDAO.registrarConDestino(myaDTOIn);
				respuesta = respuestaFactory.crearRespuestaOk(Void.class);
			}
		} catch (MyaWarningException e) {
			// Cuando falla Mya y da un error
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.MYA_MAIL_YA_REGISTRADO,
					CodigoMensajeConstantes.MYA_FEEDBACK_WARNING_MAIL);
		} catch (MyaServiceException e) {
			// Cuando rompe el servicio de mya
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.MYA_ERROR, null);
		} catch (MyaTelefonoRegistradoException e) {
			// Cuando rompe el servicio de mya
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.MYA_CELULAR_YA_REGISTRADO,
					null);
		} catch (MyaCodigoRetornoErrorException e) {
			// Cuando rompe el servicio de mya
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.MYA_ERROR, null);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.login.bo.MyaBO#actualizarEstadoMya(ar.
	 * com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<Void> actualizarEstadoMya(Cliente cliente) {
		MyaDTOIn myaDTOIn = new MyaDTOIn();
		myaDTOIn.setNup(cliente.getNup());
		Respuesta<Void> respuesta = respuestaFactory.crearRespuestaOk(Void.class);

		try {
			myaWSDAO.updateEstadoCliente(myaDTOIn);
		} catch (MyaWarningException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.MYA_ERROR, null);
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.login.bo.MyaBO#
	 * obtenerTerminosCondicionesPDF(java.lang.String)
	 */
	@Override
	public Respuesta<Reporte> obtenerTerminosCondicionesPDF(String texto) {
		Respuesta<Reporte> respuestaReporteTyC = new Respuesta<Reporte>();
		try {
			Reporte reporte = reporteTyCDAO.obtenerReporteTyCMya(texto);
			respuestaReporteTyC.setEstadoRespuesta(EstadoRespuesta.OK);
			respuestaReporteTyC.setRespuesta(reporte);
			return respuestaReporteTyC;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setMensaje(e.getMessage());
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			respuestaReporteTyC.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuestaReporteTyC.add(itemMensajeRespuesta);
		}
		return respuestaReporteTyC;
	}

	@Override
	public String confirmarEmail(Cliente cliente, ConfirmarMailViewIn confirmarMailIn) throws BusinessException {
		
		try {
			return myaWSDAO.confirmarEmail(cliente, confirmarMailIn);
		} catch (MyaServiceException e) {
			throw new BusinessException(e);
		}
	}
}