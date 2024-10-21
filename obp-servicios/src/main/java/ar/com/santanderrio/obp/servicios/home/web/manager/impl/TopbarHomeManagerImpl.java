/*
 *
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.OperadorEjecutivo;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.manager.ControllerHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.TopbarHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.ControllerView;
import ar.com.santanderrio.obp.servicios.home.web.view.MicroFEView;
import ar.com.santanderrio.obp.servicios.home.web.view.OperadorEjecutivoView;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;
import ar.com.santanderrio.obp.servicios.home.web.view.TopbarHomeView;
import ar.com.santanderrio.obp.servicios.microfrontend.bo.MicrofrontendsBO;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;

/**
 * The Class TopbarHomeManagerImpl.
 */
@Component
public class TopbarHomeManagerImpl implements TopbarHomeManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TopbarHomeManagerImpl.class);

	/** The Constant FF_PREFIX. */
	private static final String FF_PREFIX = "FF.";

	/** The Constant MICROAPP_PREFIX. */
	private static final String MICROAPP_PREFIX = "MICROAPP_";

	/** The Constant SUFFIX_FF_PERM. */
	private static final String SUFFIX_FF_PERM = "-ff";

	/** The Constant SUFFIX_ALL_PERM. */
	private static final String SUFFIX_ALL_PERM = "-all";

	/** The Constant MICROAPP_SUFFIX_ACTIONS. */
	private static final String MICROAPP_SUFFIX_ACTION = ".ACTION_LIST";

	/** The Constant MICROAPP_SUFFIX_PATH. */
	private static final String MICROAPP_SUFFIX_PATH = ".PATH_LIST";

	/** The Constant MICROAPP_SUFFIX_FF. */
	private static final String MICROAPP_SUFFIX_FF = ".FF";

	/** The Constant MICROAPP_SUFFIX_PERMISO_PREFIX. */
	private static final String MICROAPP_SUFFIX_PERMISO_PREFIX = ".PERMISO_PREFIX";

	/** The Constant MICROAPP_SUFFIX_VALIDAR_MENUITEM. */
	private static final String MICROAPP_SUFFIX_VALIDAR_MENUITEM = ".VALIDAR_MENUITEM_LIST";

	/** The Constant FF_LISTADO_SUFFIX. */
	private static final String FF_LISTADO_SUFFIX = ".LISTADO_SUFFIX";

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The controller home manager. */
	@Autowired
	private ControllerHomeManager controllerHomeManager;

	/** The alias cuenta BO. */
	@Autowired
	private AliasCuentaBO aliasCuentaBO;

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	/** The mensaje bo. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The modulo permiso bo . */
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	/** The property map. */
	@Autowired
	private PropertyMap propertyMap;

    /** The microfrontends BO. */
    @Autowired
    private MicrofrontendsBO microfrontendsBO;

	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.home.web.manager.TopbarHomeManager#
	 * obtenerTopbar()
	 */
	@Override
	public Respuesta<TopbarHomeView> obtenerTopbar() {

		TopbarHomeView topbarHomeView = new TopbarHomeView();
		Respuesta<TopbarHomeView> respuesta = new Respuesta<TopbarHomeView>();
		respuesta.setRespuesta(topbarHomeView);
		Cliente cliente = sesionCliente.getCliente();
		Segmento segmento = cliente.getSegmento();

		topbarHomeView.setIsNova(cliente.isNova());

		if (segmento != null) {
			topbarHomeView.setIsIU(segmento.isiU());
			topbarHomeView.setIsDuo(segmento.isDuo());
			topbarHomeView.setIsPyme(segmento.isPyme());
			topbarHomeView.setIsSelect(segmento.isSelect());
			topbarHomeView.setIsUniversidad(segmento.isUniversidad());
			if (segmento.getOperadorEjecutivo() != null
					&& moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_INICIO_SELECT_ONLINE)
						.tienePermisoDeVisibilidad()) {
				topbarHomeView.setOperadorEjecutivo(mapearOperadorEjecutivo(segmento));
			}
		}

		if(topbarHomeView.getOperadorEjecutivo() != null){
			topbarHomeView.getOperadorEjecutivo().setExecutiveOnlineMicrofrontIsEnabled(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_EJECUTIVO_ONLINE_MICROFRONT).tienePermisoDeVisibilidad());
		}

		if (cliente.getNumeroCUILCUIT() == null) {
			try {
				DetalleDocumentoDTO detalleDocumento;
				detalleDocumento = aliasCuentaBO.obtenerDocumentoValidoDTO(cliente);
				String cuilCuitCliente = aliasCuentaBO.obtenerCuitPorDetalle(detalleDocumento);
				cliente.setNumeroCUILCUIT(cuilCuitCliente);
				Map<String, DetalleDocumentoDTO> detalleDocumentos = aliasCuentaBO.obtenerDetalleDocumentos();
				setTipoNroDocCnsDocXNup(cliente, detalleDocumentos, NupDTO.TIPO_DOC_CUIT, NupDTO.TIPO_DOC_CUIL, NupDTO.TIPO_DOC_CDI);
			} catch (DAOException e) {
				cliente.setNumeroCUILCUIT(null);
			}
		}

		if (!cliente.getSinProductos()) {
			LOGGER.info("ML entre a cargar el menu");
			ControllerView controllerView = controllerHomeManager.obtenerController(cliente);
			topbarHomeView.setController(controllerView);
		}

		topbarHomeView.setMfs(obtenerMfs(cliente, topbarHomeView.getController()));

		topbarHomeView.setSinProductos(cliente.getSinProductos());
		topbarHomeView.setUnicaCuentaMf(cuentaBO.tieneUnaSolaCuentaIncluyendoBancaPrivada(cliente));
		respuesta = respuestaFactory.crearRespuestaOk(topbarHomeView);

		return respuesta;
	}

    private List<MicroFEView> obtenerMfs(Cliente cliente, ControllerView controllerView) {
    	List<MicroFEView> mfs = new ArrayList<MicroFEView>();
    	Set<String> mfKeys = new HashSet<String>();
		Map<String, Object> props = propertyMap.getProperties();
        for (String key : props.keySet()) {
        	if (key.contains(MICROAPP_PREFIX)) {
        		int off = key.lastIndexOf(".");
        		String mfkey = key.substring(MICROAPP_PREFIX.length(), off).trim();
        		mfKeys.add(mfkey);
        	}
        }
	    for (String mfKey : mfKeys) {
	    	try {
	        	cargarMf(mfKey, mfs, cliente, controllerView);
	        } catch (RuntimeException e) {
	        	LOGGER.error("Error al obtener configuracion de microfronts", e);
	        }
	    }
		return mfs;
	}

	/**
	 * Cargar mf.
	 *
	 * @param mfKey the mf key
	 * @param mfs the mfs
	 * @param cliente the cliente
	 * @param controllerView the controller view
	 */
	private void cargarMf(String mfKey, List<MicroFEView> mfs, Cliente cliente, ControllerView controllerView) {
		String actionLst = propertyMap.get(MICROAPP_PREFIX + mfKey + MICROAPP_SUFFIX_ACTION);
    	String pathLst = propertyMap.get(MICROAPP_PREFIX + mfKey + MICROAPP_SUFFIX_PATH);
    	String menuValLst = propertyMap.get(MICROAPP_PREFIX + mfKey + MICROAPP_SUFFIX_VALIDAR_MENUITEM);
    	if (actionLst == null) {
    		return;
    	}
    	String[] actions = actionLst.split("\\|");
    	String[] menuVals = menuValLst.split("\\|");
    	String[] paths = pathLst.split("\\|");
    	for (int i = 0; i < actions.length; i++) {
    		String action = actions[i].trim();
    		String path = paths[i].trim();
        	if (!"1".equals(menuVals[i].trim()) || tieneItemActivo(controllerView, action)) {
        		// Verifica permisos de microfe-<module> y acceso por nup
        		String mfPermisoPrefix = propertyMap.get(MICROAPP_PREFIX + mfKey + MICROAPP_SUFFIX_PERMISO_PREFIX);
        		if (tienePermisoVisibilidadAll(mfPermisoPrefix)) {
        			mfs.add(new MicroFEView(actions[i], path));
        		} else if (tienePermisoVisibilidadFF(mfPermisoPrefix) && verificarHabilitacionPorNup(mfKey, cliente)) {
        			mfs.add(new MicroFEView(action, path));
        		}
        	}
    	}
	}

	/**
	 * Verificar habilitacion por nup.
	 *
	 * @param mfKey the mf key
	 * @param cliente the cliente
	 * @return the boolean
	 */
	private Boolean verificarHabilitacionPorNup(String mfKey, Cliente cliente) {
		Map<String, Boolean> habilitadoFF = sesionParametros.getHabilitadoFF();
		String ffId = propertyMap.get(MICROAPP_PREFIX + mfKey + MICROAPP_SUFFIX_FF);
		if (ffId != null) {
			ffId = ffId.trim();
			if (habilitadoFF.get(ffId) == null) {
				String mfListadoSuffix = propertyMap.get(FF_PREFIX + ffId + FF_LISTADO_SUFFIX);
				habilitadoFF.put(ffId, tieneAccesoPorNup(cliente.getNup(), mfListadoSuffix));
			}
			return habilitadoFF.get(ffId);
		} else {
			return false;
		}
	}

	/**
	 * Tiene item activo.
	 *
	 * @param controllerView the controller view
	 * @param action the action
	 * @return true, if successful
	 */
	private boolean tieneItemActivo(ControllerView controllerView, String action) {
		if (controllerView != null) {
			for (SeccionView seccionView : controllerView.getSecciones()) {
				if (verificarSeccion(action, seccionView)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Verificar seccion.
	 *
	 * @param action the action
	 * @param seccionView the seccion view
	 * @return true, if successful
	 */
	private boolean verificarSeccion(String action, SeccionView seccionView) {
		if (seccionView != null && seccionView.getItems() != null) {
			for (SeccionView item : seccionView.getItems()) {
				if (item != null && action.equals(item.getAccion())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Tiene acceso por nup.
	 *
	 * @param nup the nup
	 * @param mfListadoSuffix the mfListadoSuffix
	 * @return true, if successful
	 */
	private boolean tieneAccesoPorNup(String nup, String mfListadoSuffix) {
		return microfrontendsBO.consultaVisibilidadPorNup(nup, mfListadoSuffix, Boolean.FALSE);
	}

	/**
	 * Tiene permiso visibilidad FF.
	 *
	 * @param mfPermisoPrefix the mfPermisoPrefix
	 * @return true, if successful
	 */
	private boolean tienePermisoVisibilidadFF(String mfPermisoPrefix) {
		String nombrePermiso = mfPermisoPrefix + SUFFIX_FF_PERM;
		return tienePermisoVisibilidad(nombrePermiso.toLowerCase());
	}

	/**
	 * Tiene permiso visibilidad all.
	 *
	 * @param mfPermisoPrefix the mfPermisoPrefix
	 * @return true, if successful
	 */
	private boolean tienePermisoVisibilidadAll(String mfPermisoPrefix) {
		String nombrePermiso = mfPermisoPrefix + SUFFIX_ALL_PERM;
		return tienePermisoVisibilidad(nombrePermiso.toLowerCase());
	}

	/**
	 * Tiene permiso visibilidad.
	 *
	 * @param nombrePermiso the nombre permiso
	 * @return true, if successful
	 */
	private boolean tienePermisoVisibilidad(String nombrePermiso) {
		AccionController accion = AccionController.obtenerEnumPorDescripcion(nombrePermiso);
		if (accion != null) {
			return moduloPermisoBO.tienePermisoMostrar(accion);
		}
		return false;
	}

	/**
     * Sets the tipo nro doc cns doc X nup.
     *
     * @param cliente the cliente
     * @param detalleDocumentos the detalle documentos
     * @param tiposDoc the tipos doc
     */
    private void setTipoNroDocCnsDocXNup(Cliente cliente, Map<String, DetalleDocumentoDTO> detalleDocumentos, String... tiposDoc) {
    	for (String tipoDoc : tiposDoc) {
    		DetalleDocumentoDTO dto = detalleDocumentos.get(tipoDoc);
            if (dto == null) {
            	continue;
            }
    		String nroDoc = dto.getNroDocumento();
            if (StringUtils.isNotEmpty(nroDoc)) {
            	cliente.setNroDocCnsDocXNup(nroDoc);
                cliente.setTipoDocCnsDocXNup(tipoDoc);
                break;
            }
        }
    }

	/**
	 * Mapear operador ejecutivo.
	 *
	 * @param segmento the segmento
	 * @return the operador ejecutivo view
	 */
	private OperadorEjecutivoView mapearOperadorEjecutivo(Segmento segmento) {
		OperadorEjecutivoView ejecutivoView = null;
		if (segmento.getOperadorEjecutivo() != null) {

			ejecutivoView = new OperadorEjecutivoView();
			OperadorEjecutivo operadorEjecutivo = segmento.getOperadorEjecutivo();
			ejecutivoView.setFotoEjecutivo(operadorEjecutivo.getFotoEjecutivo());
			if (!operadorEjecutivo.getErrorOperador()) {
				ejecutivoView.setEmail(operadorEjecutivo.getEmail());
				ejecutivoView.setHorarioAtencion(operadorEjecutivo.getHorarioAtencion());
				Boolean estadoActivo = Boolean.FALSE;
				if(operadorEjecutivo.getDiaLaborable() != null){
					estadoActivo = validarHorarioEjecutivoActivo(operadorEjecutivo) && operadorEjecutivo.getDiaLaborable();
				}
				 
				ejecutivoView.setEstadoEjecutivo(estadoActivo);
				ejecutivoView.setDescripcionHorario(obtenerDescripcionHorario(estadoActivo, operadorEjecutivo));
				ejecutivoView.setNombreEjecutivo(operadorEjecutivo.getNombreEjecutivo());
				ejecutivoView.setWhatsappEjecutivo(operadorEjecutivo.getWhatsappEjecutivo());
				ejecutivoView.setTelefonoMasivo(operadorEjecutivo.getTelefonoMasivo());
				ejecutivoView.setMensajeEjecutivoTransitorio(operadorEjecutivo.getMensajeEjecutivoTransitorio());
				ejecutivoView.setIdEjecutivo(operadorEjecutivo.getIdEjecutivo());
			} else {
				ejecutivoView.setMensajeErrorEjecutivo(mensajeBO
				        .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_MENSAJE_EJECUTIVO).getMensaje());
			}
		}
		return ejecutivoView;
	}

	/**
	 * Determinar si corresponde actualizar la disponibilidad/indisponibilidad del
	 * ejecutivo si esta fuera de horario, status.
	 *
	 * @param operadorEjecutivo
	 * @return
	 */
	private Boolean validarHorarioEjecutivoActivo(OperadorEjecutivo operadorEjecutivo) {
		SimpleDateFormat sdfHM = new SimpleDateFormat("HH:mm");
		if (StringUtils.isNotBlank(operadorEjecutivo.getHorarioAtencionInicio())
				&& StringUtils.isNotBlank(operadorEjecutivo.getHorarioAtencionFin())) {
			try {
				int from = getSegs(sdfHM, operadorEjecutivo.getHorarioAtencionInicio());
				int to = getSegs(sdfHM, operadorEjecutivo.getHorarioAtencionFin());
				int ahora = getSegs(sdfHM, null);
				return to > from && ahora >= from && ahora <= to || to < from && (ahora >= from || ahora <= to);
			} catch (ParseException e) {
				LOGGER.error(
						"No se pudo calcular el horario del supervisor para activar el topbar se solicita que se muestre activo",
						e);
			}
		} else {
			LOGGER.info(
					"No se pudo obtener el horario de atencion del ejecutivo se opta devolverlo activo por definicion");
		}
		return Boolean.TRUE;
	}

	/**
	 * Obtener los segundos apartir de una hora recibida.
	 *
	 * @param sdfHM
	 * @param hora
	 * @return
	 * @throws ParseException
	 */
	private int getSegs(SimpleDateFormat sdfHM, String hora) throws ParseException {
		Calendar cal = Calendar.getInstance();
		if (StringUtils.isEmpty(hora)) {
			Date date = new Date();
			cal.setTime(date);
		} else {
			Date dateHM = sdfHM.parse(hora);
			cal.setTime(dateHM);
		}
		return cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
	}

	/**
	 * Determinar si corresponde actualizar la disponibilidad/indisponibilidad del
	 * ejecutivo si esta fuera de horario, mensaje.
	 *
	 * @param estadoActivo
	 * @param operadorEjecutivo
	 * @return
	 */
	private String obtenerDescripcionHorario(Boolean estadoActivo, OperadorEjecutivo operadorEjecutivo) {
		if (estadoActivo) {
			return legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.CODIGO_EJECUTIVO_DIPONIBLE);
		} else {
			if (StringUtils.isNotBlank(operadorEjecutivo.getHorarioAtencionInicio())) {
				return MessageFormat.format(
						legalBO.obtenerLegalesPorCodigo(
								CodigoMensajeConstantes.CODIGO_MODELO_HORARIOS_OPERADOR_EJECUTIVO_NO_DISPONIBLE),
						operadorEjecutivo.getHorarioAtencionInicio(), operadorEjecutivo.getHorarioAtencionFin());
			} else {
				LOGGER.info("Se devuelve descripcion vacia por no disponer de horario de atencion notificado");
				return StringUtils.EMPTY;
			}
		}
	}



}
