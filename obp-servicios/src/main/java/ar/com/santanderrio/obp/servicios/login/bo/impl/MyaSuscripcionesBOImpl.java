/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaAtributosPer;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaAtributosVinculados;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaCodOperacionEnum;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestino;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestinoVinculado;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestinosPermitidos;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaFrecPermitida;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaMensaje;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaMsgMultiple;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaSuscripcion;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaXmlResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaServiceException;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.MyaSuscripcionesBO;
import ar.com.santanderrio.obp.servicios.login.dao.MyaWSDAO;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.mya.entities.UpdateMensajesMyaDTOIn;
import ar.com.santanderrio.obp.servicios.mya.entities.UpdateMensajesMyaSuscripcion;
import ar.com.santanderrio.obp.servicios.mya.web.view.MyaUpdateMensajeView;
import ar.com.santanderrio.obp.servicios.mya.web.view.ProductoMyAEnum;
import ar.com.santanderrio.obp.servicios.mya.web.view.UpdateSuscripcionMensajeMyaView;
import ar.com.santanderrio.obp.servicios.suscripciones.dao.SuscripcionesParametrosDAO;
import ar.com.santanderrio.obp.servicios.suscripciones.dto.SuscripcionesDTO;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.DiaAvisoPrevio;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.FrecuenciaMya;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.MensajeSuscripcion;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.SorpresaMya;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.TarjetaSuscripcion;

/**
 * The Class MyaSuscripcionesBOImpl.
 */
@Component
public class MyaSuscripcionesBOImpl implements MyaSuscripcionesBO {

	/** The Constant DOS. */
	private static final String DOS = "2";

	/** The Constant UNO. */
	private static final String UNO = "1";

	/** The Constant NO. */
	private static final String NO = "No";

	/** The Constant CLAVE_TODOS_LOS_DIAS. */
	private static final String CLAVE_TODOS_LOS_DIAS = "LMAMIJV";

	/** The Constant ESPACIO. */
	private static final String ESPACIO = " ";

	/** The Constant CLAVE_VIERNES. */
	private static final String CLAVE_VIERNES = "V";

	/** The Constant CLAVE_JUEVES. */
	private static final String CLAVE_JUEVES = "J";

	/** The Constant CLAVE_MIERCOLES. */
	private static final String CLAVE_MIERCOLES = "MI";

	/** The Constant CLAVE_MARTES. */
	private static final String CLAVE_MARTES = "MA";

	/** The Constant CLAVE_LUNES. */
	private static final String CLAVE_LUNES = "L";

	/** The Constant SI. */
	private static final String SI = "S";

	/** The Constant SI2. */
	private static final String SI2 = "SI";

	/** The Constant NO2. */
	private static final String NO2 = "NO";

	/** The Constant DIA_VENCIMIENTO. */
	private static final String DIA_VENCIMIENTO = "0";

	/** The Constant UN_DIA_ANTICIPACION. */
	private static final String UN_DIA_ANTICIPACION = "1";

	/** The Constant DOS_DIAS_ANTICIPACION. */
	private static final String DOS_DIAS_ANTICIPACION = "2";

	/** The Constant TRES_DIAS_ANTICIPACION. */
	private static final String TRES_DIAS_ANTICIPACION = "3";

	/** The Constant MAIL. */
	private static final String MAIL = "MAIL";

	/** The Constant MAIL. */
	private static final String CELULAR = "CEL";

	/** The Constant ALTA. */
	private static final String ALTA = "alta";

	/** The Constant BAJA. */
	private static final String BAJA = "baja";

	/** The Constant MODIFICACION. */
	private static final String MODIFICACION = "modificacion";

	/** The Constant DIA_MIERCOLES. */
	private static final String DIA_MIERCOLES = "Miercoles";

	/** The Constant ALERTAS_DE. */
	private static final String ALERTAS_DE = "Alertas de ";

	/** The Constant SALDO_MINIMO_PESOS. */
	private static final String SALDO_MINIMO_PESOS = "MINARS";

	/** The Constant CODIGO_CERO. */
	private static final String CODIGO_CERO = "0";

	/** The Constant ESTADO_TARJETA_CON_PROBLEMAS. */
	private static final String ESTADO_TARJETA_CON_PROBLEMAS = "25";

	/** The Constant CODIGO_LEGALES. */
	private static final String CODIGO_LEGALES = "1013";
	
	/** The Constant CODIGO_LEGALES. */
	private static final String NROMENSAJE_ERESUMEN = "203";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MyaSuscripcionesBOImpl.class);

	/** The suscripciones parametros DAO. */
	@Autowired
	private SuscripcionesParametrosDAO suscripcionesParametrosDAO;

	/** The mya WSDAO. */
	@Autowired
	private MyaWSDAO myaWSDAO;

	/** The legal DAO. */
	@Autowired
	private LegalDAO legalDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The codigos de las compañias de celular. */
	@Value("#{'${COMPANIAS.CELULAR.CODIGOS}'.split(',')}")
	private List<String> companiasCelular;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.login.bo.MyaSuscripcionesBO#
	 * obtenerMensajesSuscripciones(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya,
	 * ar.com.santanderrio.obp.servicios.mya.web.view.ProductoMyAEnum)
	 */
	@Override
	public Respuesta<SuscripcionesDTO> obtenerMensajesSuscripciones(Cliente cliente, CredencialesMya credenciales,
			ProductoMyAEnum producto) {
		SuscripcionesDTO suscripcionesDTO = new SuscripcionesDTO();
		try {
			MyaXmlResponse respuesta = myaWSDAO.getSuscripciones(cliente, producto.getId().toString());
			if (!StringUtils.equals(CODIGO_CERO, respuesta.getCodigoRetorno())) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO,
						CodigoMensajeConstantes.ERROR_SERVICIO_MYA);
			}
			if (producto.equals(ProductoMyAEnum.TARJETAS)) {
				Respuesta<SuscripcionesDTO> respuestaDTO = revisarDestinosActivos(respuesta);
				if (EstadoRespuesta.ERROR.equals(respuestaDTO.getEstadoRespuesta())) {
					return respuestaDTO;
				}
			}
			suscripcionesDTO = parsearRespuestaGetSuscripciones(respuesta, credenciales, producto.getLabel());
		} catch (MyaServiceException e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO,
					CodigoMensajeConstantes.ERROR_SERVICIO_MYA);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO,
					CodigoMensajeConstantes.ERROR_SERVICIO_MYA);
		}
		return buscarErroresYObtenerRespuesta(suscripcionesDTO);
	}

	/**
	 * Revisar destinos activos.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the respuesta
	 */
	private Respuesta<SuscripcionesDTO> revisarDestinosActivos(MyaXmlResponse respuesta) {

		Respuesta<SuscripcionesDTO> respuestaDTO = new Respuesta<SuscripcionesDTO>();

		int cantidadDestinosActivos = 0;
		Boolean tieneMail = false;
		Boolean tieneCelular = false;
		for (MyaDestino myaDestino : respuesta.getListMyaDestino()) {
			if (myaDestino.getEstado().equals("1")) {
				cantidadDestinosActivos++;
				if (myaDestino.getTipo().trim().equals(MAIL)) {
					tieneMail = true;
				} else if (myaDestino.getTipo().trim().equals(CELULAR)) {
					tieneCelular = true;
				}
			}
		}

		if (cantidadDestinosActivos == 0) {
			respuestaDTO = respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO,
					CodigoMensajeConstantes.MYA_SIN_DESTINOS);
		}
		int cantidadDestinosActivosQueEstanPermitidos = 0;
		for (MyaMensaje myaMensaje : respuesta.getListMyaProducto().get(0).getListMyaMensaje()) {
			cantidadDestinosActivosQueEstanPermitidos = revisarDestinosPermitidosPorMensaje(myaMensaje, tieneMail,
					tieneCelular);
		}
		if (cantidadDestinosActivosQueEstanPermitidos == 0) {
			respuestaDTO = respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO,
					CodigoMensajeConstantes.MYA_SIN_DESTINOS);
		}

		return respuestaDTO;
	}

	/**
	 * Revisar destinos permitidos por mensaje.
	 *
	 * @param myaMensaje
	 *            the mya mensaje
	 * @param tieneMail
	 *            the tiene mail
	 * @param tieneCelular
	 *            the tiene celular
	 * @return the int
	 */
	private int revisarDestinosPermitidosPorMensaje(MyaMensaje myaMensaje, Boolean tieneMail, Boolean tieneCelular) {

		int cantidadDestinosActivosQueEstanPermitidos = 0;

		if (SI2.equals(myaMensaje.getDestinosPermitidos().getDpMail()) && tieneMail) {
			cantidadDestinosActivosQueEstanPermitidos++;
		}

		if (SI2.equals(myaMensaje.getDestinosPermitidos().getDpCelular()) && tieneCelular) {
			cantidadDestinosActivosQueEstanPermitidos++;
		}

		return cantidadDestinosActivosQueEstanPermitidos;
	}

	/**
	 * Buscar errores Y obtener respuesta.
	 *
	 * @param suscripcionesDTO
	 *            the suscripciones DTO
	 * @return the respuesta
	 */
	private Respuesta<SuscripcionesDTO> buscarErroresYObtenerRespuesta(SuscripcionesDTO suscripcionesDTO) {
		if (CollectionUtils.isEmpty(suscripcionesDTO.getMensajes())) {
			return respuestaFactory.crearRespuestaError("", TipoError.SIN_MENSAJES,
					CodigoMensajeConstantes.MYA_SIN_MENSAJES);
		}
		List<ItemMensajeRespuesta> items = obtenerItems(suscripcionesDTO);
		if (CollectionUtils.isEmpty(items)) {
			return respuestaFactory.crearRespuestaOk(suscripcionesDTO);
		} else {
			return respuestaFactory.crearRespuestaWarning(suscripcionesDTO, items);
		}
	}

	/**
	 * Buscar error.
	 *
	 * @param suscripcionesDTO
	 *            the suscripciones DTO
	 * @param tipoError
	 *            the tipo error
	 * @return the boolean
	 */
	private Boolean buscarError(SuscripcionesDTO suscripcionesDTO, TipoError tipoError) {
		for (MensajeSuscripcion mensaje : suscripcionesDTO.getMensajes()) {
			if (mensaje.getTipoError() != null && mensaje.getTipoError().equals(tipoError)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Obtener items.
	 *
	 * @param suscripcionesDTO
	 *            the suscripciones DTO
	 * @return the list
	 */
	private List<ItemMensajeRespuesta> obtenerItems(SuscripcionesDTO suscripcionesDTO) {
		Boolean errorServicio = buscarError(suscripcionesDTO, TipoError.ERROR_SERVICIO);
		Boolean errorSinDestinos = buscarError(suscripcionesDTO, TipoError.SIN_DESTINOS);
		Boolean errorSinTarjetas = buscarError(suscripcionesDTO, TipoError.ERROR_SIN_TARJETAS);
		List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		if (errorServicio) {
			items.add(respuestaFactory.generarItemMensajeRespuesta("", TipoError.ERROR_SERVICIO,
					CodigoMensajeConstantes.ERROR_SERVICIO_MYA));
		}
		if (errorSinDestinos) {
			items.add(respuestaFactory.generarItemMensajeRespuesta("", TipoError.SIN_DESTINOS,
					CodigoMensajeConstantes.MYA_SIN_DESTINOS));
		}
		if (errorSinTarjetas) {
			items.add(respuestaFactory.generarItemMensajeRespuesta("", TipoError.ERROR_SIN_TARJETAS,
					CodigoMensajeConstantes.MYA_SIN_MENSAJES));
		}
		return items;
	}

	/**
	 * No tiene destinos habilitados.
	 *
	 * @param destinosPermitidos
	 *            the destinos permitidos
	 * @return the boolean
	 */
	private Boolean noTieneDestinosHabilitados(MyaDestinosPermitidos destinosPermitidos) {
		return StringUtils.equalsIgnoreCase(NO, destinosPermitidos.getDpMail())
				&& StringUtils.equalsIgnoreCase(NO, destinosPermitidos.getDpCelular());
	}

	/**
	 * Mails validos.
	 *
	 * @param credenciales
	 *            the credenciales
	 * @param respuesta
	 *            the respuesta
	 * @return the boolean
	 */
	private Boolean mailsValidos(CredencialesMya credenciales, MyaXmlResponse respuesta) {
		String mailPrincipal = obtenerMailDesdeGetSuscripciones(true, respuesta);
		String mailSecundario = obtenerMailDesdeGetSuscripciones(false, respuesta);
		if (StringUtils.isEmpty(mailSecundario)) {
			if (StringUtils.equalsIgnoreCase(mailPrincipal, credenciales.getEmail())) {
				return true;
			}
		} else if (StringUtils.equalsIgnoreCase(mailPrincipal, credenciales.getEmail())
				&& StringUtils.equalsIgnoreCase(mailSecundario, credenciales.getEmailSecundario())) {
			return true;
		}
		return false;
	}

	/**
	 * Obtener mail desde get suscripciones.
	 *
	 * @param esMailPrincipal
	 *            the es mail principal
	 * @param respuesta
	 *            the respuesta
	 * @return the string
	 */
	private String obtenerMailDesdeGetSuscripciones(Boolean esMailPrincipal, MyaXmlResponse respuesta) {
		for (MyaDestino destino : respuesta.getListMyaDestino()) {
			if (StringUtils.equals(destino.getTipo(), MAIL)
					&& StringUtils.equals(destino.getSecuencia(), esMailPrincipal ? UNO : DOS)) {
				return destino.getDescripcion();
			}
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Parsear respuesta get suscripciones.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param credenciales
	 *            the credenciales
	 * @param nombreProducto
	 *            the nombre producto
	 * @return the suscripciones DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	private SuscripcionesDTO parsearRespuestaGetSuscripciones(MyaXmlResponse respuesta, CredencialesMya credenciales,
			String nombreProducto) throws DAOException {
		SuscripcionesDTO suscripcionesDTO = new SuscripcionesDTO();
		suscripcionesDTO.setMailUno(credenciales.getEmail());
		suscripcionesDTO.setMailDos(credenciales.getEmailSecundario());
		suscripcionesDTO.setTituloSuscripcion(ALERTAS_DE + nombreProducto);
		suscripcionesDTO.setMensajes(obtenerListaMensajes(respuesta, credenciales));
		return suscripcionesDTO;
	}

	/**
	 * Obtener lista mensajes.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param credenciales
	 *            the credenciales
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	private List<MensajeSuscripcion> obtenerListaMensajes(MyaXmlResponse respuesta, CredencialesMya credenciales)
			throws DAOException {
		Boolean mailsValidos = mailsValidos(credenciales, respuesta);
		List<MensajeSuscripcion> mensajes = new ArrayList<MensajeSuscripcion>();
		if (CollectionUtils.isNotEmpty(respuesta.getListMyaProducto())
				&& CollectionUtils.isNotEmpty(respuesta.getListMyaProducto().get(0).getListMyaMensaje())) {
			for (MyaMensaje mensaje : respuesta.getListMyaProducto().get(0).getListMyaMensaje()) {
				MensajeSuscripcion mensajeSuscripcion;
				if (noTieneDestinosHabilitados(mensaje.getDestinosPermitidos())) {
					mensajeSuscripcion = obtenerMensajeConError(mensaje, TipoError.SIN_DESTINOS);
				} else if (!mailsValidos) {
					mensajeSuscripcion = obtenerMensajeConError(mensaje, TipoError.ERROR_SERVICIO);
				} else {
					mensajeSuscripcion = obtenerMensajeSuscripcion(mensaje);
					mensajeSuscripcion
							.setTipoError(errorFrecuencias(mensajeSuscripcion) ? TipoError.ERROR_SERVICIO : null);
					mensajeSuscripcion = revisarSiCorrespondeErrorTarjetas(mensaje, mensajeSuscripcion);
				}
				mensajeSuscripcion = setearAtributosSorpresa(mensaje, mensajeSuscripcion, credenciales);
				mensajeSuscripcion.setNumeroMensaje(mensaje.getNroMensaje());
				mensajes.add(mensajeSuscripcion);
			}
		}
		return mensajes;
	}

	/**
	 * Revisar si corresponde error tarjetas.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @param mensajeSuscripcion
	 *            the mensaje suscripcion
	 * @return the mensaje suscripcion
	 */
	private MensajeSuscripcion revisarSiCorrespondeErrorTarjetas(MyaMensaje mensaje,
			MensajeSuscripcion mensajeSuscripcion) {
		MensajeSuscripcion mensajeSuscripcionTarjetas = mensajeSuscripcion;
		String mensajeTarjetaUno = "40";
		String mensajeTarjetaDos = "41";
		String mensajeTarjetaTres = "42";
		if ((mensajeTarjetaUno.equals(mensaje.getNroMensaje()) || mensajeTarjetaDos.equals(mensaje.getNroMensaje())
				|| mensajeTarjetaTres.equals(mensaje.getNroMensaje()))
				&& revisarSiListaDeTarjetasVacia(mensajeSuscripcionTarjetas)) {
			mensajeSuscripcionTarjetas = obtenerMensajeConError(mensaje, TipoError.ERROR_SIN_TARJETAS);
		}
		return mensajeSuscripcionTarjetas;
	}

	/**
	 * Revisar si lista de tarjetas vacia.
	 *
	 * @param mensajeSuscripcion
	 *            the mensaje suscripcion
	 * @return the boolean
	 */
	private Boolean revisarSiListaDeTarjetasVacia(MensajeSuscripcion mensajeSuscripcion) {
		return CollectionUtils.isEmpty(mensajeSuscripcion.getListaMsgMultiples())
				|| CollectionUtils.isEmpty(mensajeSuscripcion.getListaTarjetas());
	}

	/**
	 * Obtener mensaje con error.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @param tipoError
	 *            the tipo error
	 * @return the mensaje suscripcion
	 */
	private MensajeSuscripcion obtenerMensajeConError(MyaMensaje mensaje, TipoError tipoError) {
		MensajeSuscripcion mensajeSuscripcion = new MensajeSuscripcion();
		mensajeSuscripcion.setLabel(obtenerLabel(mensaje.getNombreMensaje()));
		if (CollectionUtils.isNotEmpty(mensaje.getListMyaSuscripcion())) {
			mensajeSuscripcion.setActiva(true);
		}
		mensajeSuscripcion.setTipoError(tipoError);
		return mensajeSuscripcion;
	}

	/**
	 * Error frecuencias.
	 *
	 * @param mensajeSuscripcion
	 *            the mensaje suscripcion
	 * @return the boolean
	 */
	private Boolean errorFrecuencias(MensajeSuscripcion mensajeSuscripcion) {
		return errorFrecuencia(mensajeSuscripcion) || errorListaDias(mensajeSuscripcion)
				|| errorDiasAvisoPrevio(mensajeSuscripcion) || errorFrecuenciasInversiones(mensajeSuscripcion);
	}

	/**
	 * Error frecuencia.
	 *
	 * @param mensajeSuscripcion
	 *            the mensaje suscripcion
	 * @return the boolean
	 */
	private Boolean errorFrecuencia(MensajeSuscripcion mensajeSuscripcion) {
		return mensajeSuscripcion.getFrecuencia() != null && mensajeSuscripcion.getFrecuencia().isEmpty();
	}

	/**
	 * Error lista dias.
	 *
	 * @param mensajeSuscripcion
	 *            the mensaje suscripcion
	 * @return the boolean
	 */
	private Boolean errorListaDias(MensajeSuscripcion mensajeSuscripcion) {
		return errorFrecuencia(mensajeSuscripcion.getListaDiasMailUno())
				|| errorFrecuencia(mensajeSuscripcion.getListaDiasMailDos());
	}

	/**
	 * Error frecuencia.
	 *
	 * @param frecuencias
	 *            the frecuencias
	 * @return the boolean
	 */
	private Boolean errorFrecuencia(List<FrecuenciaMya> frecuencias) {
		if (!CollectionUtils.isEmpty(frecuencias)) {
			for (FrecuenciaMya dia : frecuencias) {
				if (StringUtils.isEmpty(dia.getFrecuencia())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Error dias aviso previo.
	 *
	 * @param mensajeSuscripcion
	 *            the mensaje suscripcion
	 * @return the boolean
	 */
	private Boolean errorDiasAvisoPrevio(MensajeSuscripcion mensajeSuscripcion) {
		return errorDiasAvisoPrevio(mensajeSuscripcion.getListaDAPMailUno())
				|| errorDiasAvisoPrevio(mensajeSuscripcion.getListaDAPMailDos());
	}

	/**
	 * Error dias aviso previo.
	 *
	 * @param dias
	 *            the dias
	 * @return the boolean
	 */
	private Boolean errorDiasAvisoPrevio(List<DiaAvisoPrevio> dias) {
		if (!CollectionUtils.isEmpty(dias)) {
			for (DiaAvisoPrevio dia : dias) {
				if (StringUtils.isEmpty(dia.getFrecuencia())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Error frecuencias inversiones.
	 *
	 * @param mensajeSuscripcion
	 *            the mensaje suscripcion
	 * @return the boolean
	 */
	private Boolean errorFrecuenciasInversiones(MensajeSuscripcion mensajeSuscripcion) {
		return errorFrecuencia(mensajeSuscripcion.getListaFrecuenciasMailUno())
				|| errorFrecuencia(mensajeSuscripcion.getListaFrecuenciasMailDos());
	}

	/**
	 * Setear atributos sorpresa.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @param mensajeSuscripcion
	 *            the mensaje suscripcion
	 * @param credenciales
	 *            the credenciales
	 * @return the mensaje suscripcion
	 * @throws DAOException
	 *             the DAO exception
	 */
	private MensajeSuscripcion setearAtributosSorpresa(MyaMensaje mensaje, MensajeSuscripcion mensajeSuscripcion,
			CredencialesMya credenciales) throws DAOException {

		if ("102".equals(mensaje.getNroMensaje())) {
			SorpresaMya sorpresa = new SorpresaMya();
			sorpresa.setCelularUno(credenciales.getCelular());
			sorpresa.setCelularDos(credenciales.getCelularSecundario());
			sorpresa.setCodigoAreaUno(credenciales.getCodigoArea());
			sorpresa.setCodigoAreaDos(credenciales.getCodigoAreaSecundario());
			sorpresa.setEmpresaCelularUno(credenciales.getCompaniaSeleccionada());
			sorpresa.setEmpresaCelularDos(credenciales.getCompaniaSeleccionadaSecundaria());
			sorpresa.setCompanias(companiasCelular);
			sorpresa.setLegales(legalDAO.obtenerLegal(CODIGO_LEGALES));
			if (CollectionUtils.isNotEmpty(mensaje.getListMyaSuscripcion())) {
				sorpresa.settYcAceptados(true);

				for (MyaSuscripcion suscripcion : mensaje.getListMyaSuscripcion()) {
					if (UNO.equals(suscripcion.getDestinoVinculado().getDestVincSecuencia())) {
						sorpresa.setCelularUnoActivo(true);
						sorpresa.setNumeroSuscripcionCelUno(suscripcion.getNroSuscripcion());
					}
					if (DOS.equals(suscripcion.getDestinoVinculado().getDestVincSecuencia())) {
						sorpresa.setCelularDosActivo(true);
						sorpresa.setNumeroSuscripcionCelDos(suscripcion.getNroSuscripcion());
					}
				}
			}
			mensajeSuscripcion.setSorpresa(sorpresa);
		}

		return mensajeSuscripcion;
	}

	/**
	 * Obtener mensaje suscripcion.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @return the mensaje suscripcion
	 */
	private MensajeSuscripcion obtenerMensajeSuscripcion(MyaMensaje mensaje) {
		MensajeSuscripcion mensajeSuscripcion = new MensajeSuscripcion();
		if (CollectionUtils.isNotEmpty(mensaje.getListMyaFrecPermitida())
				&& mensaje.getListMyaFrecPermitida().size() == 1) {
			mensajeSuscripcion.setFrecuencia(suscripcionesParametrosDAO
					.obtenerFrecuencia(mensaje.getListMyaFrecPermitida().get(0).getFrecuenciaPermitida()));
		}
		mensajeSuscripcion.setLabel(obtenerLabel(mensaje.getNombreMensaje()));
		mensajeSuscripcion.setMensaje(mensaje.getDescripcionMensaje());
		if (CollectionUtils.isNotEmpty(mensaje.getListMyaSuscripcion())) {
			mensajeSuscripcion.setActiva(true);
			for (MyaSuscripcion suscripcion : mensaje.getListMyaSuscripcion()) {
				mensajeSuscripcion = armarSuscripcion(suscripcion, mensajeSuscripcion, mensaje);
			}
		}
		if ("70".equals(mensaje.getNroMensaje()) || "7".equals(mensaje.getNroMensaje())) {
			mensajeSuscripcion = revisarListaFrecuencias(mensajeSuscripcion, mensaje);
		} else if ("1".equals(mensaje.getNroMensaje())) {
			mensajeSuscripcion = revisarListaDAP(mensajeSuscripcion, mensaje);
		} else {
			mensajeSuscripcion = revisarListaDias(mensajeSuscripcion, mensaje);
		}
		if (CollectionUtils.isNotEmpty(mensaje.getMsgMultiple())) {
			mensajeSuscripcion.setListaMsgMultiples(mensaje.getMsgMultiple());
			mensajeSuscripcion.setListaTarjetas(obtenerListaTarjetas(mensaje));
		}
		if (mensaje.getListMyaSuscripcion() !=null && mensaje.getListMyaSuscripcion().size() == 1) {
			mensajeSuscripcion.setListaDiasMailDos(armarListaDias(mensaje.getFrecuenciaMensaje()));
		}
		mensajeSuscripcion.setAlertaSaldoMinimoPesos(
				"18".equals(mensaje.getNroMensaje()) ? obtenerSaldoMinimo(mensaje) : StringUtils.EMPTY);
		return mensajeSuscripcion;
	}

	/**
	 * Revisar lista frecuencias.
	 *
	 * @param mensajeSuscripcion
	 *            the mensaje suscripcion
	 * @param mensaje
	 *            the mensaje
	 * @return the mensaje suscripcion
	 */
	private MensajeSuscripcion revisarListaFrecuencias(MensajeSuscripcion mensajeSuscripcion, MyaMensaje mensaje) {
		if (CollectionUtils.isEmpty(mensajeSuscripcion.getListaFrecuenciasMailUno())) {
			mensajeSuscripcion.setListaFrecuenciasMailUno(
					armarListaFrecuencias(mensaje.getFrecuenciaMensaje(), mensaje.getListMyaFrecPermitida()));
		}
		if (CollectionUtils.isEmpty(mensajeSuscripcion.getListaFrecuenciasMailDos())) {
			mensajeSuscripcion.setListaFrecuenciasMailDos(
					armarListaFrecuencias(mensaje.getFrecuenciaMensaje(), mensaje.getListMyaFrecPermitida()));
		}

		return mensajeSuscripcion;
	}

	/**
	 * Revisar lista DAP.
	 *
	 * @param mensajeSuscripcion
	 *            the mensaje suscripcion
	 * @param mensaje
	 *            the mensaje
	 * @return the mensaje suscripcion
	 */
	private MensajeSuscripcion revisarListaDAP(MensajeSuscripcion mensajeSuscripcion, MyaMensaje mensaje) {
		if (CollectionUtils.isEmpty(mensajeSuscripcion.getListaDAPMailUno())) {
			mensajeSuscripcion.setListaDAPMailUno(armarListaDAP(mensaje.getDapDefault()));
		}
		if (CollectionUtils.isEmpty(mensajeSuscripcion.getListaDAPMailDos())) {
			mensajeSuscripcion.setListaDAPMailDos(armarListaDAP(mensaje.getDapDefault()));
		}

		return mensajeSuscripcion;
	}

	/**
	 * Revisar lista dias.
	 *
	 * @param mensajeSuscripcion
	 *            the mensaje suscripcion
	 * @param mensaje
	 *            the mensaje
	 * @return the mensaje suscripcion
	 */
	private MensajeSuscripcion revisarListaDias(MensajeSuscripcion mensajeSuscripcion, MyaMensaje mensaje) {
		String[] mensajesConListaDeDias = { "5", "6", "11" };
		for (String mensajeQueRequiereDias : mensajesConListaDeDias) {
			if (mensajeQueRequiereDias.equals(mensaje.getNroMensaje())) {
				if (CollectionUtils.isEmpty(mensajeSuscripcion.getListaDiasMailUno())) {
					mensajeSuscripcion.setListaDiasMailUno(armarListaDias(mensaje.getFrecuenciaMensaje()));
				}
				if (CollectionUtils.isEmpty(mensajeSuscripcion.getListaDiasMailDos())) {
					mensajeSuscripcion.setListaDiasMailDos(armarListaDias(mensaje.getFrecuenciaMensaje()));
				}
			}
		}
		return mensajeSuscripcion;
	}

	/**
	 * Armar suscripcion.
	 *
	 * @param suscripcion
	 *            the suscripcion
	 * @param mensajeSuscripcion
	 *            the mensaje suscripcion
	 * @param mensaje
	 *            the mensaje
	 * @return the mensaje suscripcion
	 */
	private MensajeSuscripcion armarSuscripcion(MyaSuscripcion suscripcion, MensajeSuscripcion mensajeSuscripcion,
			MyaMensaje mensaje) {
		if (MAIL.equals(suscripcion.getDestinoVinculado().getDestVincTipo())
				&& UNO.equals(suscripcion.getDestinoVinculado().getDestVincSecuencia())) {
			mensajeSuscripcion.setMailUnoActivo(true);
			mensajeSuscripcion.setNumeroSuscripcionMailUno(suscripcion.getNroSuscripcion());
			if ("70".equals(mensaje.getNroMensaje()) || "7".equals(mensaje.getNroMensaje())) {
				mensajeSuscripcion.setListaFrecuenciasMailUno(
						armarListaFrecuencias(suscripcion.getFrcVinculada(), mensaje.getListMyaFrecPermitida()));
			} else if ("1".equals(mensaje.getNroMensaje())) {
				mensajeSuscripcion.setListaDAPMailUno(armarListaDAP(suscripcion.getDapVinculada()));
			} else {
				mensajeSuscripcion.setListaDiasMailUno(armarListaDias(suscripcion.getFrcVinculada()));
			}
		}
		if (MAIL.equals(suscripcion.getDestinoVinculado().getDestVincTipo())
				&& DOS.equals(suscripcion.getDestinoVinculado().getDestVincSecuencia())) {
			mensajeSuscripcion.setMailDosActivo(true);
			mensajeSuscripcion.setNumeroSuscripcionMailDos(suscripcion.getNroSuscripcion());
			if ("70".equals(mensaje.getNroMensaje()) || "7".equals(mensaje.getNroMensaje())) {
				mensajeSuscripcion.setListaFrecuenciasMailDos(
						armarListaFrecuencias(suscripcion.getFrcVinculada(), mensaje.getListMyaFrecPermitida()));
			} else if ("1".equals(mensaje.getNroMensaje())) {
				mensajeSuscripcion.setListaDAPMailDos(armarListaDAP(suscripcion.getDapVinculada()));
			} else {
				mensajeSuscripcion.setListaDiasMailDos(armarListaDias(suscripcion.getFrcVinculada()));
			}
		}

		return mensajeSuscripcion;
	}

	/**
	 * Armar lista dias.
	 *
	 * @param frecuencia
	 *            the frecuencia
	 * @return the list
	 */
	private List<FrecuenciaMya> armarListaDias(String frecuencia) {
		List<FrecuenciaMya> listaDias = new ArrayList<FrecuenciaMya>();
		int contadorDiasActivos = 0;
		String[] dias = { CLAVE_LUNES, CLAVE_MARTES, CLAVE_MIERCOLES, CLAVE_JUEVES, CLAVE_VIERNES };
		for (String dia : dias) {
			FrecuenciaMya diaSuscripto = new FrecuenciaMya();
			diaSuscripto
					.setFrecuencia(suscripcionesParametrosDAO.obtenerFrecuencia(StringUtils.rightPad(dia, 7, ESPACIO)));
			diaSuscripto.setFrecuencia(
					DIA_MIERCOLES.equals(diaSuscripto.getFrecuencia()) ? "Miércoles" : diaSuscripto.getFrecuencia());
			if (frecuencia.contains(dia)) {
				diaSuscripto.setActivo(true);
				contadorDiasActivos++;
			}
			listaDias.add(diaSuscripto);
		}

		FrecuenciaMya todosLosDias = new FrecuenciaMya();
		todosLosDias.setFrecuencia(suscripcionesParametrosDAO.obtenerFrecuencia(CLAVE_TODOS_LOS_DIAS));

		if (contadorDiasActivos == 5) {
			todosLosDias.setActivo(true);
		}

		listaDias.add(0, todosLosDias);
		return listaDias;
	}

	/**
	 * Armar lista frecuencias.
	 *
	 * @param frecuenciaEnviada
	 *            the frecuencia enviada
	 * @param listaMyaFrecuencias
	 *            the lista mya frecuencias
	 * @return the list
	 */
	private List<FrecuenciaMya> armarListaFrecuencias(String frecuenciaEnviada,
			List<MyaFrecPermitida> listaMyaFrecuencias) {
		List<String> frecuenciasPermitidas = obtenerFrecuenciasPermitidas(listaMyaFrecuencias);
		List<FrecuenciaMya> listafrecuenciaInversiones = new ArrayList<FrecuenciaMya>();
		for (String frecuencia : frecuenciasPermitidas) {
			FrecuenciaMya frecuenciaInversiones = new FrecuenciaMya();
			frecuenciaInversiones.setFrecuencia(
					suscripcionesParametrosDAO.obtenerFrecuencia(StringUtils.rightPad(frecuencia, 7, ESPACIO)));
			if (frecuenciaEnviada.contains(frecuencia)) {
				frecuenciaInversiones.setActivo(true);
			}
			listafrecuenciaInversiones.add(frecuenciaInversiones);
		}
		return listafrecuenciaInversiones;
	}

	/**
	 * Obtener frecuencias permitidas.
	 *
	 * @param listaMyaFrecuencias
	 *            the lista mya frecuencias
	 * @return the list
	 */
	private List<String> obtenerFrecuenciasPermitidas(List<MyaFrecPermitida> listaMyaFrecuencias) {
		List<String> frecuenciasPermitidas = new ArrayList<String>();
		for (MyaFrecPermitida frec : listaMyaFrecuencias) {
			frecuenciasPermitidas.add(StringUtils.trim(frec.getFrecuenciaPermitida()));
		}
		return frecuenciasPermitidas;
	}

	/**
	 * Armar lista DAP.
	 *
	 * @param dapEnviado
	 *            the dap enviado
	 * @return the list
	 */
	private List<DiaAvisoPrevio> armarListaDAP(String dapEnviado) {
		List<DiaAvisoPrevio> listaDAP = new ArrayList<DiaAvisoPrevio>();
		String[] diasAvisoPrevio = { DIA_VENCIMIENTO, UN_DIA_ANTICIPACION, DOS_DIAS_ANTICIPACION,
				TRES_DIAS_ANTICIPACION };
		for (String dia : diasAvisoPrevio) {
			DiaAvisoPrevio dap = new DiaAvisoPrevio();
			dap.setFrecuencia(suscripcionesParametrosDAO.obtenerDiaAvisoPrevio(dia));
			dap.setCantidadDias(suscripcionesParametrosDAO.obtenerCodigoDiaAvisoPrevio(dia).trim());
			if (dapEnviado.contains(dia)) {
				dap.setActivo(true);
			}
			listaDAP.add(dap);
		}
		return listaDAP;
	}

	/**
	 * Obtener saldo minimo.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @return the string
	 */
	private String obtenerSaldoMinimo(MyaMensaje mensaje) {

		String saldoMinimo = "0";

		if (CollectionUtils.isNotEmpty(mensaje.getListMyaSuscripcion())) {
			for (MyaAtributosVinculados atributo : mensaje.getListMyaSuscripcion().get(0)
					.getListMyaAtributosVinculados()) {
				if (SALDO_MINIMO_PESOS.equals(atributo.getAttrVincClave())
						&& NumberUtils.isNumber(atributo.getAttrVincDato())) {
					saldoMinimo = revisarLongitud(saldoMinimo, atributo.getAttrVincDato());
				}
			}
		}

		if (CollectionUtils.isNotEmpty(mensaje.getListMyaAtributosPer())) {
			for (MyaAtributosPer atributo : mensaje.getListMyaAtributosPer()) {
				if (SALDO_MINIMO_PESOS.equals(atributo.getAttrPerClave())
						&& NumberUtils.isNumber(atributo.getAttrPerDefault())) {
					saldoMinimo = revisarLongitud(saldoMinimo, atributo.getAttrPerDefault());
				}
			}
		}
		return saldoMinimo;
	}

	/**
	 * Revisar longitud.
	 *
	 * @param saldoMinimo
	 *            the saldo minimo
	 * @param atributo
	 *            the atributo
	 * @return the string
	 */
	private String revisarLongitud(String saldoMinimo, String atributo) {
		if (atributo.length() >= 3) {
			return atributo.substring(0, atributo.length() - 2);
		}
		return saldoMinimo;
	}

	/**
	 * Obtener lista tarjetas.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @return the list
	 */
	private List<TarjetaSuscripcion> obtenerListaTarjetas(MyaMensaje mensaje) {
		List<MyaMsgMultiple> listaMsgMultiples = mensaje.getMsgMultiple();
		List<TarjetaSuscripcion> listaTarjetas = new ArrayList<TarjetaSuscripcion>();
		Boolean eResumen = StringUtils.equals(mensaje.getNroMensaje(), NROMENSAJE_ERESUMEN);
		for (MyaMsgMultiple msgMultiple : listaMsgMultiples) {
			TarjetaSuscripcion tarjeta = new TarjetaSuscripcion(msgMultiple, eResumen);
			if (!tarjetaActivaTitular(msgMultiple.getNroTarjeta()) && eResumen) {
				tarjeta.setMostrar(Boolean.FALSE);
			}
			tarjeta.setMontoSuscripto(
					StringUtils.equals("42", mensaje.getNroMensaje()) ? null : obtenerImporteTarjeta(msgMultiple));
			tarjeta.setSuscripto(StringUtils.equalsIgnoreCase(SI, msgMultiple.getSuscripto()) ? true : false);
			listaTarjetas.add(tarjeta);
		}
		return listaTarjetas;
	}

	private boolean tarjetaActivaTitular(String nroTarjeta) {
		for (Cuenta cuenta : sesionCliente.getCliente().getCuentas()) {
			if (nroTarjeta.equals(cuenta.getNroTarjetaCredito())) {
				return (cuenta.esEstadoTarjetaCredito()
						|| ESTADO_TARJETA_CON_PROBLEMAS.equals(cuenta.getEstadoTarjetaCredito())) && cuenta.esTitular()
						&& cuenta.esTarjetaDeCredito();
			}
		}
		return false;
	}

	/**
	 * Obtener importe tarjeta.
	 *
	 * @param msj
	 *            the msj
	 * @return the string
	 */
	private String obtenerImporteTarjeta(MyaMsgMultiple msj) {
		return msj.getMonto();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.login.bo.MyaSuscripcionesBO#
	 * updateMensajes(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente,
	 * ar.com.santanderrio.obp.servicios.mya.web.view.MyaUpdateMensajeView)
	 */
	@Override
	public Respuesta<Void> updateMensajes(Cliente cliente, MyaUpdateMensajeView myaUpdateMensajeView) {

		UpdateMensajesMyaDTOIn updateMensajesMyaDTOIn = mapearUpdateMensajeView(myaUpdateMensajeView);
		try {
			myaWSDAO.updateMensajes(cliente, updateMensajesMyaDTOIn);
		} catch (MyaServiceException e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO_MYA,
					CodigoMensajeConstantes.ERROR_MYA_UPDATE_MENSAJES);
		}
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/**
	 * Mapear update mensaje view.
	 *
	 * @param myaUpdateMensajeView
	 *            the mya update mensaje view
	 * @return the update mensajes mya DTO in
	 */
	private UpdateMensajesMyaDTOIn mapearUpdateMensajeView(MyaUpdateMensajeView myaUpdateMensajeView) {

		UpdateMensajesMyaDTOIn updateMensajesMyaDTOIn = new UpdateMensajesMyaDTOIn();
		List<UpdateMensajesMyaSuscripcion> listaUpdateMensajesMyaSuscripcion = new ArrayList<UpdateMensajesMyaSuscripcion>();
		List<UpdateSuscripcionMensajeMyaView> listaUpdateSuscripcionMensajeMyaView = myaUpdateMensajeView.getDatos()
				.getListaUpdatesSuscripciones();
		for (UpdateSuscripcionMensajeMyaView updateSuscripcionMensajeMyaView : listaUpdateSuscripcionMensajeMyaView) {
			if (updateSuscripcionMensajeMyaView.getOperacionUpdate() != null) {
				UpdateMensajesMyaSuscripcion updateMensajesMyaSuscripcion = new UpdateMensajesMyaSuscripcion();
				updateMensajesMyaSuscripcion.setCodigoDapSuscripcion(obtenerCodigoDAP(updateSuscripcionMensajeMyaView));
				updateMensajesMyaSuscripcion
						.setCodigoFrecuenciaSuscripcion(obtenerCodigoFrecuencia(updateSuscripcionMensajeMyaView));
				updateMensajesMyaSuscripcion
						.setMyaCodOperacionEnum(obtenerMyaCodigoOperacion(updateSuscripcionMensajeMyaView));
				updateMensajesMyaSuscripcion
						.setMyaDestinoVinculado(obtenerDestinoVinculado(updateSuscripcionMensajeMyaView));
				updateMensajesMyaSuscripcion
						.setNumeroMensajeSuscripcion(updateSuscripcionMensajeMyaView.getNumeroMensaje());
				updateMensajesMyaSuscripcion
						.setNumeroSuscripcion(updateSuscripcionMensajeMyaView.getNumeroSuscripcion());
				if (updateSuscripcionMensajeMyaView.getImporteMinimo() != null) {
					updateMensajesMyaSuscripcion.setAtributoVinculadoClave("MINARS");
					updateMensajesMyaSuscripcion.setAtributoVinculadoValor(
							formatearImporteMinimo(updateSuscripcionMensajeMyaView.getImporteMinimo()));
				}
				if (CollectionUtils.isNotEmpty(myaUpdateMensajeView.getListaMsgMultiples())) {
					updateMensajesMyaSuscripcion.setListaMsgMultiples(myaUpdateMensajeView.getListaMsgMultiples());
				}
				listaUpdateMensajesMyaSuscripcion.add(updateMensajesMyaSuscripcion);
			}
		}
		updateMensajesMyaDTOIn.setListaUpdateMensajesMyaSuscripcion(listaUpdateMensajesMyaSuscripcion);
		return updateMensajesMyaDTOIn;
	}

	/**
	 * Formatear importe minimo.
	 *
	 * @param importeMinimo
	 *            the importe minimo
	 * @return the string
	 */
	private String formatearImporteMinimo(String importeMinimo) {
		String importe = importeMinimo.replace(",", "");
		return importe + "00";
	}

	/**
	 * Obtener destino vinculado.
	 *
	 * @param updateSuscripcionMensajeMyaView
	 *            the update suscripcion mensaje mya view
	 * @return the mya destino vinculado
	 */
	private MyaDestinoVinculado obtenerDestinoVinculado(
			UpdateSuscripcionMensajeMyaView updateSuscripcionMensajeMyaView) {

		MyaDestinoVinculado myaDestino = new MyaDestinoVinculado();
		myaDestino.setDestVincTipo(updateSuscripcionMensajeMyaView.getDestinoSuscripcion());
		myaDestino.setDestVincSecuencia(updateSuscripcionMensajeMyaView.getSecuencia());

		return myaDestino;
	}

	/**
	 * Obtener mya codigo operacion.
	 *
	 * @param updateSuscripcionMensajeMyaView
	 *            the update suscripcion mensaje mya view
	 * @return the mya cod operacion enum
	 */
	private MyaCodOperacionEnum obtenerMyaCodigoOperacion(
			UpdateSuscripcionMensajeMyaView updateSuscripcionMensajeMyaView) {

		if (MODIFICACION.equals(updateSuscripcionMensajeMyaView.getOperacionUpdate())) {
			return MyaCodOperacionEnum.MODIFICACION;
		} else if (ALTA.equals(updateSuscripcionMensajeMyaView.getOperacionUpdate())) {
			return MyaCodOperacionEnum.ALTA;
		} else if (BAJA.equals(updateSuscripcionMensajeMyaView.getOperacionUpdate())) {
			return MyaCodOperacionEnum.BAJA;
		}

		return null;
	}

	/**
	 * Obtener codigo DAP.
	 *
	 * @param updateSuscripcionMensajeMyaView
	 *            the update suscripcion mensaje mya view
	 * @return the string
	 */
	private String obtenerCodigoDAP(UpdateSuscripcionMensajeMyaView updateSuscripcionMensajeMyaView) {

		String codigoDAP;
		if (CollectionUtils.isEmpty(updateSuscripcionMensajeMyaView.getListaDAP())) {
			codigoDAP = "NONE";
		} else {
			List<String> listaDAP = updateSuscripcionMensajeMyaView.getListaDAP();
			StringBuilder codigoDAPBuilder = new StringBuilder();
			for (String dap : listaDAP) {
				codigoDAPBuilder.append(dap);
			}
			codigoDAP = codigoDAPBuilder.toString();
		}
		return codigoDAP;
	}

	/**
	 * Obtener codigo frecuencia.
	 *
	 * @param updateSuscripcionMensajeMyaView
	 *            the update suscripcion mensaje mya view
	 * @return the string
	 */
	private String obtenerCodigoFrecuencia(UpdateSuscripcionMensajeMyaView updateSuscripcionMensajeMyaView) {

		String codigoFrecuencia;
		if (StringUtils.isNotBlank(updateSuscripcionMensajeMyaView.getFrecuencia())) {
			codigoFrecuencia = suscripcionesParametrosDAO.obtenerClave(updateSuscripcionMensajeMyaView.getFrecuencia());
		} else {
			codigoFrecuencia = "NONE";
		}
		List<String> listaDias = updateSuscripcionMensajeMyaView.getListaDias();
		if (CollectionUtils.isNotEmpty(listaDias)) {
			StringBuilder frecuencia = obtenerFrecuenciaDias(listaDias);
			codigoFrecuencia = frecuencia.toString();
		}

		return codigoFrecuencia;
	}

	/**
	 * Obtener frecuencia dias.
	 *
	 * @param listaDias
	 *            the lista dias
	 * @return the string builder
	 */
	private StringBuilder obtenerFrecuenciaDias(List<String> listaDias) {
		StringBuilder frecuencia = new StringBuilder();
		for (String dia : listaDias) {
			if ("Lunes".equals(dia)) {
				frecuencia.append("L");
			}
			if ("Martes".equals(dia)) {
				frecuencia.append("MA");
			}
			if ("Miércoles".equals(dia)) {
				frecuencia.append("MI");
			}
			if ("Jueves".equals(dia)) {
				frecuencia.append("J");
			}
			if ("Viernes".equals(dia)) {
				frecuencia.append("V");
			}
		}
		return frecuencia;
	}

	/**
	 * Obtener label.
	 *
	 * @param nombreMensaje
	 *            the nombre mensaje
	 * @return the string
	 */
	private String obtenerLabel(String nombreMensaje) {
		if (StringUtils.containsIgnoreCase(nombreMensaje, "Sorpresa")) {
			return StringUtils.replace(nombreMensaje, "Rio", "Río");
		}
		return StringUtils.capitalize(StringUtils.lowerCase(nombreMensaje));
	}

}