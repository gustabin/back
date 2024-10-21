/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.bo.SoftTokenBO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.entitites.HomeDTO;
import ar.com.santanderrio.obp.servicios.home.web.manager.CalendarioPagosHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.ChanceHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.CuentaHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.InversionesHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.PrestamoHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeguroHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.TableroHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.TarjetaHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.TransferenciasHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;
import ar.com.santanderrio.obp.servicios.home.web.view.TableroHomeView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Class TableroHomeManagerImpl.
 */
@Component
public class TableroHomeManagerImpl implements TableroHomeManager {

	/** The Constant FORMATO_FECHA. */
	private static final String FORMATO_FECHA = "dd/MM/yyyy - HH:mm";

	/** The Constant HS. */
	private static final String HS = " hs.";

	/** The cuenta home manager. */
	@Autowired
	private CuentaHomeManager cuentaHomeManager;

	/** The tarjeta home manager. */
	@Autowired
	private TarjetaHomeManager tarjetaHomeManager;

	/** The calendario pagos home manager. */
	@Autowired
	private CalendarioPagosHomeManager calendarioPagosHomeManager;

	/** The sesion parametros. */
	@Autowired
	private PrestamoHomeManager prestamosHomeManager;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The ModuloPermiso BO. */
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	/** The seguro home manager. */
	@Autowired
	private SeguroHomeManager seguroHomeManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The administrador permisos. */
	@Autowired
	private AdministradorPermisos administradorPermisos;

	/** The inversiones home manager. */
	@Autowired
	@Qualifier("inversionesHomeManagerImpl")
	private InversionesHomeManager inversionesHomeManager;

	/** The inversiones home BancaPrivada manager. */
	@Autowired
	@Qualifier("inversionesHomeBPManagerImpl")
	private InversionesHomeManager inversionesHomeBPManager;

	/** The transferencias home manager. */
	@Autowired
	private TransferenciasHomeManager transferenciasHomeManager;
	
	/** The change home manager. */
	@Autowired
	private ChanceHomeManager chanceHomeManager;

	@Autowired
	private PuntoSuperclubHomeManagerImpl puntosSuperclubHomeManager;

	@Autowired
	private SuperclubPlusHomeManagerImpl superclubPlusHomeManager;
	
	@Autowired
	private RefinancingHomeManagerImpl refinancingHomeManagerImpl;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	@Autowired
	private SoftTokenBO softTokenBo;

	@Value("${TABLERO_SIN_PRODUCTOS:'irSuperClub, irAadvantage, irHomeSeguro'}")
	private String tableroSinProductos;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.home.web.manager.TableroHomeManager#
	 * obtenerTablero()
	 */
	@Override
	public Respuesta<TableroHomeView> obtenerTablero() {
		contarIngresoHome();
		// colecciono las respuestas para procesar un estado final
		List<Respuesta<GrupoCajaHomeView>> respuestasGrupos = new ArrayList<Respuesta<GrupoCajaHomeView>>();

        respuestasGrupos.add(agregarGrupo(cuentaHomeManager));
        respuestasGrupos.add(agregarGrupo(tarjetaHomeManager));
        if (!sesionCliente.getCliente().isNova()) {
        	respuestasGrupos.add(agregarGrupo(superclubPlusHomeManager));
        }
        respuestasGrupos.add(agregarGrupo(calendarioPagosHomeManager));
        respuestasGrupos.add(agregarGrupo(transferenciasHomeManager));

        if (!sesionCliente.getCliente().isNova()) {
	        respuestasGrupos.add(agregarGrupo(inversionesHomeManager));
	        respuestasGrupos.add(agregarGrupo(inversionesHomeBPManager));
	        respuestasGrupos.add(agregarGrupo(seguroHomeManager));
        }
        
        respuestasGrupos.add(agregarGrupo(prestamosHomeManager));
        respuestasGrupos.add(agregarGrupo(refinancingHomeManagerImpl));
//		respuestasGrupos.add(agregarGrupo(chanceHomeManager));
		
		if (sesionCliente.getCliente().getSinProductos()) {
			estadisticaManager.add(EstadisticasConstants.LOGIN_HOME_SIN_PRODUCTOS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		
		return obtenerRespuestaTableroHomeView(respuestasGrupos);
	}

	/**
	 * Determina si aplica el grupo, crea la respuesta con el estado e items
	 * correspondientes.
	 *
	 * @param grupoHomeManager
	 *            the grupo home manager
	 * @return respuesta con grupo, con estado items y grupo
	 */
	private Respuesta<GrupoCajaHomeView> agregarGrupo(GrupoHomeManager grupoHomeManager) {
		Respuesta<Boolean> respuestaAplicaGrupo = grupoHomeManager.aplicaGrupo();
		Respuesta<GrupoCajaHomeView> respuesta = Respuesta.copy(GrupoCajaHomeView.class, respuestaAplicaGrupo);
		if (respuestaValida(respuestaAplicaGrupo, grupoHomeManager.obtenerAccion())) {
			ModuloPermiso moduloPermiso = moduloPermisoBO
					.obtenerModuloPermisoPorAccion(grupoHomeManager.obtenerAccion());
			if (moduloPermiso.tienePermisoDeVisibilidad()) {
				GrupoCajaHomeView grupo = grupoHomeManager.obtenerGrupoElementos();
				if (grupo.getSinError()) {
					respuesta.setRespuesta(grupo);
				} else {
					respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_TABLERO_HOME,
							CodigoMensajeConstantes.ERROR_TABLERO_HOME);
					respuesta.setRespuesta(grupo);
				}
			}
			administradorPermisos.addNewGrant(grupoHomeManager.obtenerAccion());
		} else {
			administradorPermisos.removeGrant(grupoHomeManager.obtenerAccion());
		}
		return respuesta;
	}

	/**
	 * Respuesta valida.
	 *
	 * @param respuestaAplicaGrupo
	 *            the respuesta aplica grupo
	 * @return true, if successful
	 */
	private boolean respuestaValida(Respuesta<Boolean> respuestaAplicaGrupo, AccionController accionController) {
		return !EstadoRespuesta.ERROR.equals(respuestaAplicaGrupo.getEstadoRespuesta())
				&& respuestaAplicaGrupo.getRespuesta() && productosValidos(accionController);
	}

	private boolean productosValidos(AccionController accionController) {
		if(sesionCliente.getCliente().getSinProductos()) {
			return StringUtils.contains(tableroSinProductos, accionController.getDescripcion());
		}
		return true;
	}

	/**
	 * Genera la respuesta tablero, recolectando items y asignando un estado
	 * adecuado.
	 * 
	 * @param respuestasGrupos
	 *            the respuestas grupos
	 * @return the respuesta
	 */
	private Respuesta<TableroHomeView> obtenerRespuestaTableroHomeView(
			List<Respuesta<GrupoCajaHomeView>> respuestasGrupos) {

		Respuesta<TableroHomeView> respuestaTableroHomeView = new Respuesta<TableroHomeView>();
		TableroHomeView tableroHomeView = new TableroHomeView();
		boolean hayOk = false;
		boolean hayWarning = false;
		boolean hayError = false;

		if (respuestasGrupos != null && !respuestasGrupos.isEmpty()) {
			List<GrupoCajaHomeView> grupos = new ArrayList<GrupoCajaHomeView>();
			for (Respuesta<GrupoCajaHomeView> respuesta : respuestasGrupos) {
				if (respuesta.getRespuesta() != null
						&& CollectionUtils.isNotEmpty(respuesta.getRespuesta().getCajas())) {
					GrupoCajaHomeView grupo = respuesta.getRespuesta();
					grupos.add(grupo);
				}
				hayOk = hayOk || EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta());
				hayWarning = hayWarning || EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta());
				hayError = hayError || EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta());
				if (!CollectionUtils.isEmpty(respuesta.getItemsMensajeRespuesta())) {
					respuestaTableroHomeView.addAll(respuesta.getItemsMensajeRespuesta());
				}
			}
			tableroHomeView.setGrupos(grupos);
			tableroHomeView.setUltimaConexion(obtenerUltimaConexion());
			if (sesionCliente.getCliente().tieneTarjetasDebitoSinCoordenadasNiToken()
					&& sesionCliente.getPrimerIngreso()) {
				tableroHomeView.setLlamadaAfinidad(Boolean.TRUE);
				sesionCliente.setPrimerIngreso(Boolean.FALSE);
			}

		}
		if (!sesionCliente.getCliente().isNova()) {
			tableroHomeView.setMostrarBuscador(debePrenderBuscador());
		}
		tableroHomeView.setMostrarOnboarding(debePrenderOnboarding());
		tableroHomeView.setHasTp(softTokenBo.tieneSoftToken());
		respuestaTableroHomeView.setRespuesta(tableroHomeView);

		respuestaTableroHomeView = generateRespuestaByCondicion(respuestaTableroHomeView, hayOk, hayWarning, hayError);

		return respuestaTableroHomeView;
	}

	/**
	 * Generate respuesta by condicion.
	 *
	 * @param respuestaTableroHomeView
	 *            the respuesta tablero home view
	 * @param hayOk
	 *            the hay ok
	 * @param hayWarning
	 *            the hay warning
	 * @param hayError
	 *            the hay error
	 * @return the respuesta
	 */
	private Respuesta<TableroHomeView> generateRespuestaByCondicion(Respuesta<TableroHomeView> respuestaTableroHomeView,
			boolean hayOk, boolean hayWarning, boolean hayError) {
		Respuesta<TableroHomeView> result = new Respuesta<TableroHomeView>();
		result.setRespuesta(respuestaTableroHomeView.getRespuesta());
		result.setItemMensajeRespuesta(respuestaTableroHomeView.getItemsMensajeRespuesta());
		if (hayOk) {
			if (hayWarning || hayError) {
				result.setEstadoRespuesta(EstadoRespuesta.WARNING);
				estadisticaManager.add(EstadisticasConstants.CODIGO_TABLERO_HOME,
						EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
			} else {
				result.setEstadoRespuesta(EstadoRespuesta.OK);
				estadisticaManager.add(EstadisticasConstants.CODIGO_TABLERO_HOME,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}
		} else {
			if (hayWarning) {
				result.setEstadoRespuesta(EstadoRespuesta.WARNING);
				estadisticaManager.add(EstadisticasConstants.CODIGO_TABLERO_HOME,
						EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
			} else {
				result.setEstadoRespuesta(EstadoRespuesta.ERROR);
				estadisticaManager.add(EstadisticasConstants.CODIGO_TABLERO_HOME,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		}
		return result;
	}

	/**
	 * Cuenta los ingresos a la home y los guarda en sesion.
	 */
	private void contarIngresoHome() {
		HomeDTO homeDto = sesionParametros.getHomeDTO();
		if (homeDto == null) {
			homeDto = new HomeDTO();
			homeDto.setCantidadIngresos(0);
			sesionParametros.setHomeDTO(homeDto);
		}
		homeDto.setCantidadIngresos(homeDto.getCantidadIngresos() + 1);
	}

	/**
	 * Obtener ultima conexion.
	 *
	 * @return the string
	 */
	private String obtenerUltimaConexion() {
		Date fechaUltima = sesionParametros.getUltimoAcceso();
		if (fechaUltima != null) {
			return new SimpleDateFormat(FORMATO_FECHA).format(fechaUltima) + HS;
		} else {
			return null;
		}
	}
	
	private boolean debePrenderBuscador() {
		return moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.VER_BUSCADOR_DASHBOARD).tienePermisoDeVisibilidad();
	}

	private boolean debePrenderOnboarding() {
		return moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.VER_ONBOARDING).tienePermisoDeVisibilidad();
	}
}
