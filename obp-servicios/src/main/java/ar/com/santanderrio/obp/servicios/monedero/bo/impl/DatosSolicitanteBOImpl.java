/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.ConsultaPadronOCuitDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.DatosSolicitanteDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitOutEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteCriterioEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.monedero.bo.DatosSolicitanteBO;
import ar.com.santanderrio.obp.servicios.monedero.dao.MonederoWebDAO;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteCriterioDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsTransacDTO;
import ar.com.santanderrio.obp.servicios.monedero.entities.EstadoCivilIatxEnum;
import ar.com.santanderrio.obp.servicios.monedero.entities.TagEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TipoDocumentoDescripcionEnum;
import ar.com.santanderrio.obp.servicios.monedero.entities.TransaccionEntity;
import ar.com.santanderrio.obp.servicios.monedero.utils.MonederoUtils;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteCriterioView;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.MonederoDTO;

/**
 * The Class DatosSolicitanteBOImpl.
 */
@Component
public class DatosSolicitanteBOImpl implements DatosSolicitanteBO {

	/** The datos solicitante DAO. */
	@Autowired
	private DatosSolicitanteDAO datosSolicitanteDAO;

	/** The consulta padron cuit DAO. */
	@Autowired
	private ConsultaPadronOCuitDAO consultaPadronOCuitDAO;

	/** The monedero web DAO. */
	@Autowired
	private MonederoWebDAO monederoWebDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The descripciones por id. */
	@Autowired
	private DatosSelectoresDAO descripcionesPorId;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DatosSolicitanteBOImpl.class);

	/** The Constant ERROR_CON_DATOS_SOLICITANTE. */
	private static final String ERROR_CON_DATOS_SOLICITANTE = "ERROR al obtener los datos del solicitante de tag monedero";

	/** The Constant ERROR_CON_DATOS_SOLICITANTE_MONEDERO_TAG. */
	public static final String ERROR_CON_DATOS_SOLICITANTE_MONEDERO_TAG = "sinDatos";

	/** The Constant ERROR_CON_DATOS_TARJETA_MONEDERO. */
	private static final String ERROR_CON_DATOS_TARJETA_MONEDERO = "ERROR al obtener los datos en tarjeta monedero";

	/** The Constant ERROR_CON_DATOS_WS_OBTENER_TAGS. */
	private static final String ERROR_CON_DATOS_WS_OBTENER_TAGS = "ERROR al obtener los datos WS obtenerTags";

	/** The Constant CODIGO_MENSAJE_ERROR_TIME_OUT. */
	private static final String CODIGO_MENSAJE_ERROR_TIME_OUT = "1176";

	/** The Constant TAG_PAIS_NACIMIENTO. */
	private static final String TAG_PAIS_NACIMIENTO = "paisNacimiento";

	/** The Constant TAG_PROVINCIA. */
	private static final String TAG_PROVINCIA = "provincias";

	/** The Constant BUSQUEDA_PADRON_ARGUMENTO. */
	private static final String BUSQUEDA_PADRON_ARGUMENTO = "DNI";

	/** The Constant BUSQUEDA_PADRON_ARGUMENTO_MAP_DNI. */
	/** private static final String BUSQUEDA_PADRON_ARGUMENTO_MAP_DNI = "1"; */
	// En el documento de CNSPADCUIT dice que hay que si es DNI el campo opcion
	// es 1.. ESTA MAL!! ES 3!!
	private static final String BUSQUEDA_PADRON_ARGUMENTO_MAP_DNI = "3";

	/** The Constant BUSQUEDA_PADRON_ARGUMENTO_MAP_CUITL. */
	private static final String BUSQUEDA_PADRON_ARGUMENTO_MAP_CUITL = "2";
	
	/** The Constant MASCULINO. */
	private static final String MASCULINO = "M";
	
	/** The Constant FEMENINO. */
	private static final String FEMENINO = "F";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.bo.DatosSolicitanteBO#
	 * getDatosDelSolicitante(ar.com.santanderrio.obp.servicios.monedero.web.
	 * view.DatosSolicitanteCriterioView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<DatosSolicitanteDTO> getDatosDelSolicitante(DatosSolicitanteCriterioDTO datosSolicitante,
			Cliente cliente) throws BusinessException {
		try {
			LOGGER.debug("Llamando al DAO para la realizar la operacion getDatosDelSolicitante: {}.", datosSolicitante);
			DatosSolicitanteCriterioEntity datosSolicitanteCriterioEntity = new DatosSolicitanteCriterioEntity();
			BeanUtils.copyProperties(datosSolicitanteCriterioEntity, datosSolicitante);
			DatosSolicitanteEntity datosSolicitanteEntity = datosSolicitanteDAO
					.getDatosDelSolicitante(datosSolicitanteCriterioEntity, cliente);

			return validacionesDatosDelSolicitante(datosSolicitante, cliente, datosSolicitanteEntity);

		} catch (Exception e) {
			LOGGER.error(ERROR_CON_DATOS_SOLICITANTE, e);
			return creaRespuestaErroneaGetDatosSolicitanteAdicional();
		}
	}

	/**
	 * Validaciones datos del solicitante.
	 *
	 * @param datosSolicitante
	 *            the datos solicitante
	 * @param cliente
	 *            the cliente
	 * @param datosSolicitanteEntity
	 *            the datos solicitante entity
	 * @return the respuesta
	 */
	private Respuesta<DatosSolicitanteDTO> validacionesDatosDelSolicitante(DatosSolicitanteCriterioDTO datosSolicitante,
			Cliente cliente, DatosSolicitanteEntity datosSolicitanteEntity) {
		DatosSolicitanteDTO datosSolicitanteDTO = null;
		if (datosSolicitanteEntity != null && !datosSolicitanteEntity.getTieneError()
				&& datosSolicitante.isAdicional()) {
			/** Adicional */
			return validacionesIsAdicionalTrue(datosSolicitante, cliente, datosSolicitanteEntity);
		} else if (datosSolicitanteEntity != null && !datosSolicitanteEntity.getTieneError()
				&& !datosSolicitante.isAdicional()) {
			/** Titular */
			datosSolicitanteDTO = crearDatosResponseSolicitante(datosSolicitanteEntity);
			return getRespuestaFactory().crearRespuestaOk(DatosSolicitanteDTO.class, datosSolicitanteDTO);
		}
		return null;
	}

	/**
	 * Validaciones is adicional true.
	 *
	 * @param datosSolicitante
	 *            the datos solicitante
	 * @param cliente
	 *            the cliente
	 * @param datosSolicitanteEntity
	 *            the datos solicitante entity
	 * @return the respuesta
	 */
	private Respuesta<DatosSolicitanteDTO> validacionesIsAdicionalTrue(DatosSolicitanteCriterioDTO datosSolicitante,
			Cliente cliente, DatosSolicitanteEntity datosSolicitanteEntity) {

		if (!MonederoUtils.validaFechaIngresada(datosSolicitante.getFechaNacimiento(),
				MonederoUtils.ddMmAaaa2CanonicoConBarra(datosSolicitanteEntity.getFechaNacimiento()))) {
			LOGGER.debug("Fecha de nacimiento invalida, no coincide con la info de ALTAIR");
			estadisticaManager.add(EstadisticasConstants.MONEDERO_FECHA_NACIMIENTO_ERRONEA_ADICIONAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return getRespuestaFactory().crearRespuestaWarning("", TipoError.FECHA_NACIMIENTO_INVALIDA,
					CodigoMensajeConstantes.MENSAJE_ERROR_FECHA_NACIMIENTO_INVALIDA);
		} else if (MonederoUtils.mismoNup(datosSolicitanteEntity.getNup(), cliente.getNup())) {
			LOGGER.debug("NUP adicional invalido, mismo NUP que el titular");
			estadisticaManager.add(EstadisticasConstants.NUP_INVALIDO_MONEDERO_ADICIONAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return getRespuestaFactory().crearRespuestaError("", TipoError.ERROR_NUP_IDENTICO,
					CodigoMensajeConstantes.MENSAJE_ERROR_NUP_INVALIDO);
		}
		DatosSolicitanteDTO datosSolicitanteDTO = crearDatosResponseSolicitante(datosSolicitanteEntity);
		return getRespuestaFactory().crearRespuestaOk(DatosSolicitanteDTO.class, datosSolicitanteDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.bo.DatosSolicitanteBO#
	 * getDatosTarjetaMonedero(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente, java.lang.String)
	 */
	@Override
	public Respuesta<List<MonederoDTO>> getDatosTarjetaMonedero(Cliente cliente, String tipoDeConsulta) {
		try {
			LOGGER.debug("Llamando al DAO para la realizar la operacion getDatosTarjetaMonedero: {}.");
			List<MonederoDTO> monederoDTOList = datosSolicitanteDAO.getDatosTarjetaMonedero(cliente, tipoDeConsulta);
			if (monederoDTOList == null) {
				LOGGER.error(ERROR_CON_DATOS_TARJETA_MONEDERO, "");
				return respuestaFactory.crearRespuestaWarning(null, TipoError.ERROR_TAG,
						CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_DATOS_TAG_MONEDERO);
			} else {
				Respuesta<List<MonederoDTO>> respuesta = new Respuesta<List<MonederoDTO>>();
				respuesta.setRespuesta(monederoDTOList);
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				return respuesta;
			}
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			if ("TIME_OUT".equals(e.getMessage())) {
				return respuestaFactory.crearRespuestaError("", TipoError.TIMEOUT.getDescripcion(),
						CODIGO_MENSAJE_ERROR_TIME_OUT);
			}
		} catch (Exception e) {
			LOGGER.error(ERROR_CON_DATOS_TARJETA_MONEDERO, e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_TAG,
					CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_DATOS_TAG_MONEDERO);
		}
		return null;
	}

	/**
	 * Crea respuesta erronea get datos solicitante adicional.
	 *
	 * @return the respuesta
	 */
	public Respuesta<DatosSolicitanteDTO> creaRespuestaErroneaGetDatosSolicitanteAdicional() {
		return getRespuestaFactory().crearRespuestaWarning(null, TipoError.DATOS_INVALIDOS,
				CodigoMensajeConstantes.MENSAJE_WARNING_DATOS_INVALIDOS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.bo.DatosSolicitanteBO#
	 * obtenerTags(ar.com.santanderrio.obp.servicios.monedero.dto.TagsDTO)
	 */
	@Override
	public Respuesta<List<TagEntity>> obtenerTags(TagsDTO dto) {

		try {
			LOGGER.debug("Llamando al DAO para la realizar la operacion obtenerTags: {}.");
			Respuesta<List<TagEntity>> respuesta = new Respuesta<List<TagEntity>>();
			List<TagEntity> obtenerTagsList = monederoWebDAO.obtenerTags(dto);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuesta(obtenerTagsList);
			return respuesta;
		} catch (DAOException e) {
			LOGGER.error(ERROR_CON_DATOS_TARJETA_MONEDERO, e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_TAG,
					CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_DATOS_TAG_MONEDERO);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.bo.DatosSolicitanteBO#
	 * obtenerTransaccionesTags(ar.com.santanderrio.obp.servicios.monedero.dto.
	 * TagsTransacDTO)
	 */
	@Override
	public Respuesta<List<TransaccionEntity>> obtenerTransaccionesTags(TagsTransacDTO dto) {

		try {
			Respuesta<List<TransaccionEntity>> respuesta = new Respuesta<List<TransaccionEntity>>();
			/** se obtienen las transacciones segun el idTag */
			List<TransaccionEntity> obtenerTransaccionesTagsList = monederoWebDAO.obtenerTransaccionesTag(dto);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuesta(obtenerTransaccionesTagsList);
			return respuesta;
		} catch (DAOException e) {
			LOGGER.error(ERROR_CON_DATOS_WS_OBTENER_TAGS, e);
			return respuestaFactory.crearRespuestaWarning(null, TipoError.ERROR_CARGA_SALDOS_Y_MOVIMIENTOS,
					CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_DATOS_WS_OBTENER_TAGS);
		}
	}

	/**
	 * //ver como se hace con la clase TerminosCondiciones.
	 *
	 * @param datosSolicitanteEntity
	 *            the datos solicitante entity
	 * @return the datos solicitante DTO
	 * @Override public Respuesta<Legal> cargarTerminosCondiciones() throws
	 *           DAOException { String strLegal =
	 *           legalDAO.obtenerLegal("10100"); Legal legalObj = new
	 *           Legal(strLegal); Respuesta<Legal> respuesta = new
	 *           Respuesta<Legal>(); respuesta.setRespuesta(legalObj); return
	 *           respuesta; }
	 */
	public DatosSolicitanteDTO crearDatosResponseSolicitante(DatosSolicitanteEntity datosSolicitanteEntity) {
		DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();
		datosSolicitanteDTO.setApellido(
				WordUtils.capitalize(StringUtils.lowerCase(getValorEmbozado(datosSolicitanteEntity.getApellido()))));
		datosSolicitanteDTO.setNombre(
				WordUtils.capitalize(StringUtils.lowerCase(getValorEmbozado(datosSolicitanteEntity.getNombre()))));
		datosSolicitanteDTO.setFechaNacimiento(
				MonederoUtils.ddMmAaaa2CanonicoConBarra(datosSolicitanteEntity.getFechaNacimiento()));
		datosSolicitanteDTO.setDocTipo(TipoDocumentoDescripcionEnum
				.obtenerTipoDocumentoDescripcion(datosSolicitanteEntity.getDocTipo()).getDescripcion());
		Integer documentoNro = new Integer(datosSolicitanteEntity.getDoc());
		datosSolicitanteDTO.setDocumento(MonederoUtils.puntosDNI(documentoNro.toString()));
		datosSolicitanteDTO.setPaisNacimiento(descripcionesPorId.obtenerOpcionDescripcion(TAG_PAIS_NACIMIENTO,
				datosSolicitanteEntity.getPaisNacimiento()));
		datosSolicitanteDTO.setSexo(MonederoUtils.traerDescSexo(datosSolicitanteEntity.getSexo()));
		if (MASCULINO.equals(datosSolicitanteDTO.getSexo())) 
		datosSolicitanteDTO.setEstadoCivil(EstadoCivilIatxEnum
				.obtenerEstadoCivilIatxDescripcion(datosSolicitanteEntity.getEstadoCivil()).getDescripcionM());
		if (FEMENINO.equals(datosSolicitanteDTO.getSexo()))
		datosSolicitanteDTO.setEstadoCivil(EstadoCivilIatxEnum
				.obtenerEstadoCivilIatxDescripcion(datosSolicitanteEntity.getEstadoCivil()).getDescripcionF());
		datosSolicitanteDTO.setNacionalidad(descripcionesPorId.obtenerOpcionDescripcion(TAG_PAIS_NACIMIENTO,
				datosSolicitanteEntity.getNacionalidad()));
		datosSolicitanteDTO.setCalle(
				WordUtils.capitalize(StringUtils.lowerCase(getValorEmbozado(datosSolicitanteEntity.getCalle()))));
		Integer calleNro = new Integer(StringUtils.trim(datosSolicitanteEntity.getCalleNro()));
		datosSolicitanteDTO.setCalleNro(calleNro.toString());
		datosSolicitanteDTO.setPiso(StringUtils.trim(datosSolicitanteEntity.getPiso()));
		datosSolicitanteDTO.setDepto(StringUtils.trim(datosSolicitanteEntity.getDepto()));
		datosSolicitanteDTO.setLocalidad(
				StringUtils.capitalize(StringUtils.lowerCase(getValorEmbozado(datosSolicitanteEntity.getLocalidad()))));
		Integer cp = new Integer(StringUtils.trim(datosSolicitanteEntity.getCp()));
		datosSolicitanteDTO.setCp(cp.toString());
		datosSolicitanteDTO.setCpManzana(StringUtils.trim(datosSolicitanteEntity.getCpManzana()));
		datosSolicitanteDTO.setCpPatente(StringUtils.trim(datosSolicitanteEntity.getCpPatente()));
		datosSolicitanteDTO.setProvincia(
				descripcionesPorId.obtenerOpcionDescripcion(TAG_PROVINCIA, datosSolicitanteEntity.getProvincia()));
		datosSolicitanteDTO.setCodProvincia(StringUtils.trim(datosSolicitanteEntity.getProvincia()));
		datosSolicitanteDTO.setTelefono(StringUtils.trim(datosSolicitanteEntity.getTelefono()));
		datosSolicitanteDTO.setNup(StringUtils.trim(datosSolicitanteEntity.getNup()));
		return datosSolicitanteDTO;
	}

	/**
	 * Gets the valor embozado.
	 *
	 * @param valor
	 *            the valor
	 * @return the valor embozado
	 */
	private String getValorEmbozado(String valor) {
		return StringUtils.replaceEach(valor, new String[] { "#", "?" }, new String[] { "Ñ", "Ñ" });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.bo.DatosSolicitanteBO#
	 * getDatosPadronBO(ar.com.santanderrio.obp.servicios.monedero.web.view.
	 * DatosSolicitanteCriterioView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ConsultaPadronCuitOutEntity> getDatosPadronBO(DatosSolicitanteCriterioView datosSolicitante,
			Cliente cliente) throws DAOException {
		ConsultaPadronCuitInEntity objConsultaPadronInEntity = new ConsultaPadronCuitInEntity();
		objConsultaPadronInEntity.setCliente(cliente);
		if (datosSolicitante.getDocTipo().trim().equals(BUSQUEDA_PADRON_ARGUMENTO)
				|| datosSolicitante.getDocTipo().trim().equals(TipoDocumentoEnum.DNI.getCampo())) {
			objConsultaPadronInEntity.setOpcion(BUSQUEDA_PADRON_ARGUMENTO_MAP_DNI);
			objConsultaPadronInEntity.setArgumento(String.format("%011d", Integer.parseInt(datosSolicitante.getDoc())));
		} else {
			objConsultaPadronInEntity.setOpcion(BUSQUEDA_PADRON_ARGUMENTO_MAP_CUITL);
			objConsultaPadronInEntity.setArgumento(datosSolicitante.getDoc());
		}
		List<ConsultaPadronCuitOutEntity> coincidencias = this.consultaPadronOCuitDAO
				.consultaPadronRetornandoListaCoincidencias(objConsultaPadronInEntity);
		ConsultaPadronCuitOutEntity primeraCoincidencia = this.obtienePrimerCoincidencia(datosSolicitante,
				coincidencias);

		if (primeraCoincidencia != null) {
			return getRespuestaFactory().crearRespuestaOk(ConsultaPadronCuitOutEntity.class, primeraCoincidencia);
		} else {
			return respuestaFactory.crearRespuestaWarning("", TipoError.PERSONA_NO_EXISTE_EN_PADRON,
					CodigoMensajeConstantes.ERROR_NO_EXISTE_EN_PADRON);
		}
	}

	/**
	 * Obtiene primer coincidencia.
	 *
	 * @param datosSolicitante
	 *            the datos solicitante
	 * @param coincidencias
	 *            the coincidencias
	 * @return the consulta padron cuit out entity
	 */
	private ConsultaPadronCuitOutEntity obtienePrimerCoincidencia(DatosSolicitanteCriterioView datosSolicitante,
			List<ConsultaPadronCuitOutEntity> coincidencias) {
		ConsultaPadronCuitOutEntity respuesta = null;
		if (!coincidencias.isEmpty()) {
			for (Iterator<ConsultaPadronCuitOutEntity> iterator = coincidencias.iterator(); iterator.hasNext();) {
				ConsultaPadronCuitOutEntity objConsultaPadronOutEntity = iterator.next();
				if (fechasNacimientoNotNull(datosSolicitante, objConsultaPadronOutEntity) && objConsultaPadronOutEntity
						.getAbaFechaNacimiento().trim().equals(datosSolicitante.getFechaNacimiento().trim())) {
					respuesta = objConsultaPadronOutEntity;
				}
			}
		}
		return respuesta;
	}

	/**
	 * fechasNacimientoNotNull.
	 *
	 * @param datosSolicitante
	 *            the datos solicitante
	 * @param objConsultaPadronOutEntity
	 *            the obj consulta padron out entity
	 * @return true, if successful
	 */
	private boolean fechasNacimientoNotNull(DatosSolicitanteCriterioView datosSolicitante,
			ConsultaPadronCuitOutEntity objConsultaPadronOutEntity) {
		return datosSolicitante.getFechaNacimiento() != null && !"".equals(datosSolicitante.getFechaNacimiento().trim())
				&& objConsultaPadronOutEntity.getAbaFechaNacimiento() != null
				&& !"".equals(objConsultaPadronOutEntity.getAbaFechaNacimiento().trim());
	}

	/**
	 * Gets the respuesta factory.
	 *
	 * @return the respuesta factory
	 */
	public RespuestaFactory getRespuestaFactory() {
		return respuestaFactory;
	}
}
