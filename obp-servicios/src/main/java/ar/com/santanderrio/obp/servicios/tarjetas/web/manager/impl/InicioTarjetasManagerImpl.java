/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.manager.CambioGrupoAfinidadManager;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.dao.DatosSolicitanteDAO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.contracargos.service.ContracargosService;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.perfil.bo.CambioDomicilioBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.AliasFavoritoTarjetaBo;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.LimitesYDisponiblesBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DisponiblesYConsumoDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LimitesYDisponiblesDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ResumenTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TagItemMensajeTarjetaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TokenW2W;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TokenW2WFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaActivaView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaSeleccionada;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.InicioTarjetasManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.UltimosConsumosManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.util.EstadisticasTarjetasUtil;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumosView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.DatosSelectorTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.LimitesYDisponiblesView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.StackTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.StackTarjetasView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetasView;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * 
 * The Class InicioTarjetasManagerImpl.
 *
 * @author florencia.n.martinez
 *
 */
@Component
public class InicioTarjetasManagerImpl extends TarjetasManagerImpl implements InicioTarjetasManager {

	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(InicioTarjetasManagerImpl.class);

	/** The Constant ERROR_LIMITES. */
	private static final String ERROR_LIMITES = "Error al Generar Limites y Disponibles.";

	/** The Constant ERROR_FAVORITA. */
	private static final String ERROR_FAVORITA = "Error al obtener la tarjeta favorita desde el servicio.";

	/** The Constant ERROR_ALIAS. */
	private static final String ERROR_ALIAS = "Error al obtener el alias desde el servicio.";

	/** The Constant CLIENTE_SESION. */
	private static final String CLIENTE_SESION = "Cliente en Sesion {}.";

	/** The alias favorito tarjeta bo. */
	@Autowired
	private AliasFavoritoTarjetaBo aliasFavoritoTarjetaBo;

	/** The ultimos consumos manager. */
	@Autowired
	private UltimosConsumosManager ultimosConsumosManager;

	/** The validacion tarjeta. */
	@Autowired
	private LimitesYDisponiblesBO limitesYDisponiblesBO;
	
	/** The registro sesion. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;
	
	/** The CambioDomicilio BO. */
	@Autowired
	protected CambioDomicilioBO cambioDomicilioBO;
    
    /** The Constant GOTO_RECAMBIO_CHIP. */
    private static final String GOTO_RECAMBIO_CHIP = "gotoRecambioChip()";

	/** The consultar sucursales service. */
	@Autowired
	private ConsultarSucursalesBO consultarSucursalesBO;

    /** The tokenW2WFactory. */
    @Autowired
    private TokenW2WFactory tokenW2WFactory;

    /** The datos solicitante DAO. */
    @Autowired
    private DatosSolicitanteDAO datosSolicitanteDAO;

    /** The estadistica manager. */
    @Autowired
    protected EstadisticaManager estadisticaManager;

    /** The modulo permiso BO. */
    @Autowired
    private ModuloPermisoBO moduloPermisoBO;
    
    @Autowired
    private CambioGrupoAfinidadManager cambioGrupoAfinidadManager;
    
    @Autowired
    private ContracargosService contracargosService;
    
    /** The app encoding. */
    @Value("${APP.ENCODING}")
    private String appEncoding;

    /** The sign. */
    @Autowired
    private Sign sign;

    /** The w2wUrl. */
    @Value("${W2W.URL}")
    private String w2wUrl;

    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * InicioTarjetasManager#actualizarAlias(java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<Void> actualizarAlias(String nroCuenta, String alias) {
		try {
			Cuenta cuenta = (Cuenta) getCuentaBO().buscarCuentaPorId(sesionCliente.getCliente(),
					obtenerIdCuenta(nroCuenta));
			Respuesta<Void> reporteAlias = aliasFavoritoTarjetaBo.actualizarAlias(cuenta, alias);
			if (reporteAlias.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL + EstadoRespuesta.OK);
				actualizarAliasEnSesion(cuenta, alias);
				this.crearLogEstadistica(EstadisticasTarjetasUtil.getCodigoEstadisticaAlias(),
						EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
			} else {
				LOGGER.debug(SERVICIO_DEVOLVIO_LOG_LABEL + EstadoRespuesta.ERROR);
				crearEstadisticaAliasError();
			}
			return reporteAlias;
		} catch (BusinessException ex) {
			LOGGER.error(ERROR_ALIAS, ex);
			crearEstadisticaAliasError();
		} catch (NullPointerException ex) {
			LOGGER.error(ERROR_ALIAS, ex);
			crearEstadisticaAliasError();
		} catch (IndexOutOfBoundsException ex) {
			LOGGER.error(ERROR_ALIAS, ex);
			crearEstadisticaAliasError();
		}
		return null;
	}

	/**
	 * Guardar estadistica de Tarjetas.
	 *
	 * @param codTransaccion
	 *            the codigo transaccion
	 */
	@Override
	public void guardarEstadisticaTarjetas(String codTransaccion) {
		LOGGER.info("Guardando la Estadistica...");
		crearLogEstadistica(codTransaccion, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/**
	 * Se actualiza la tarjeta favorita.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return Respuesta<Void>
	 */
	@Override
	public Respuesta<Void> actualizarTarjetaFavorita(String cuenta) {
		Respuesta<Void> respuestaTarjetaFav = new Respuesta<Void>();
		try {
			LOGGER.info("Actualizar tarjeta {} a favorita.", cuenta);
			respuestaTarjetaFav = aliasFavoritoTarjetaBo.marcarFavorita(obtenerIdCuenta(cuenta),
					sesionCliente.getCliente());

			if (respuestaTarjetaFav.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL + EstadoRespuesta.OK);
				this.crearLogEstadistica(EstadisticasTarjetasUtil.getCodigoEstadisticaTarjetaFavorita(),
						EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
			} else {
				LOGGER.debug(SERVICIO_DEVOLVIO_LOG_LABEL + EstadoRespuesta.ERROR);
				crearEstadisticaTarjetaFavoritaError();
			}
		} catch (BusinessException ex) {
			LOGGER.error(ERROR_FAVORITA, ex);
			crearEstadisticaTarjetaFavoritaError();
			return null;
		}
		LOGGER.info("Respuesta del manager al obtener la tarjeta favorita: {}.", respuestaTarjetaFav.toString());
		return respuestaTarjetaFav;
	}

	/**
	 * Obtiene los limites y disponibles de la tarjeta titular.
	 *
	 * @author florencia.n.martinez
	 * @param idCuenta
	 *            the id cuenta
	 * @return Respuesta<LimitesYDisponiblesView>
	 */
	@Override
	public Respuesta<LimitesYDisponiblesView> obtenerLimitesYDisponibles(String idCuenta) {
		try {
			Cliente cliente = sesionCliente.getCliente();
			LOGGER.info(CLIENTE_SESION, cliente.toString());
			IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(idCuenta);
			String marca = obtenerMarcaDeTarjeta(identificacionCuenta);
			return obtenerRespuestaLimitesYDisponibles(identificacionCuenta, marca);
		} catch (BusinessException e) {
			LOGGER.error(ERROR_LIMITES, e);
			return crearRespuestaErrorLimitesYDisponibles();
		} catch (NullPointerException e) {
			LOGGER.error(ERROR_LIMITES, e);
			return crearRespuestaErrorLimitesYDisponibles();
		}
	}

	/**
	 * Obtener respuesta limites Y disponibles.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param marca
	 *            the marca
	 * @return the respuesta
	 */
	private Respuesta<LimitesYDisponiblesView> obtenerRespuestaLimitesYDisponibles(
			IdentificacionCuenta identificacionCuenta, String marca) {
		LimitesYDisponiblesDTO dto = limitesYDisponiblesBO.buscarLimiteYDisponibleDeCuenta(identificacionCuenta,
				sesionCliente.getCliente(), sesionTarjetas.getLimitesYDisponibles());
		if (dto != null && dto.getCodigoError() == null) {
			return armarRespuestaOKLimitesYDisponibles(dto, marca);
		}
		return crearRespuestaErrorLimitesYDisponibles(marca);
	}

	/**
	 * Gets the tarjetas.
	 *
	 * @param tarjetaSeleccionada
	 *            the tarjeta seleccionada
	 * @return the tarjetas
	 */
	@Override
	public Respuesta<TarjetasView> getTarjetas(TarjetaSeleccionada tarjetaSeleccionada) {
		try {
			getCuentaBO().cargarAliasYFavoritos(sesionCliente.getCliente(), false);
		} catch (BusinessException e) { 
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, e);
		}
		try {
			DatosSelectorTarjetaView datosSelectorTarjetas = generarListaTarjetasView(tarjetaSeleccionada);

			List<TarjetaView> listaTarjetasView = datosSelectorTarjetas.getList();
			if (!listaTarjetasView.isEmpty() && datosSelectorTarjetas.getIdentificacionCuenta() != null) {

				Respuesta<ConsumosView> rtaUltimosConsumosView = ultimosConsumosManager
						.obtenerUltimosConsumos(datosSelectorTarjetas.getIdentificacionCuenta());

				Respuesta<TarjetasView> respuestaView = crearRespuesta(listaTarjetasView, rtaUltimosConsumosView,
						tarjetaSeleccionada, datosSelectorTarjetas);
				actualizarTarjetaFavorita(respuestaView, tarjetaSeleccionada);
				revisarSiEsIngresoDeGoto(respuestaView);
				return respuestaView;
			}
			return crearRespuestaError();

		} catch (BusinessException e) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return crearRespuestaError();
		} catch (NullPointerException ex) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, ex);
			return crearRespuestaError();
		} catch (Exception ex) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, ex);
			return crearRespuestaError();
		}
	}
	
	/**
	 * Gets the lista tarjetas Cliente.
	 *
	 * @return the list tarjetas
	 */
	@Override
	public Respuesta<StackTarjetasView> getlistaTarjetasCliente() {
		Respuesta<StackTarjetasView> respuesta = new Respuesta<StackTarjetasView>();
		StackTarjetasView stackTarjetasView = new StackTarjetasView();
		List<StackTarjetaView> listaStackTarjetas = new ArrayList<StackTarjetaView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		try {
			List<Cuenta> productos = TarjetaBOUtils
					.filtrarCuentasDeTipoCuentaTarjeta(sesionCliente.getCliente().getCuentas());
			for (Cuenta tarjeta : productos) {
				if (tarjeta.getTipoCuenta() != null && tarjeta.esValidaParaRealizarPago()) {
					StackTarjetaView stackTarjeta = new StackTarjetaView();
					stackTarjeta.setMarca(TarjetaUtils.getMarca(tarjeta));
					stackTarjeta.setNumero(TarjetaBOUtils.formatearNumeroTarjeta(tarjeta.getNroTarjetaCredito(),TarjetaUtils.getMarca(tarjeta)));															
					stackTarjeta.setNumeroCuenta(ISBANStringUtils.formatearNroCuentaProductoConSucursal(tarjeta));
					listaStackTarjetas.add(stackTarjeta);
				}
			}
			stackTarjetasView.setTarjetas(listaStackTarjetas);
			respuesta.setRespuesta(stackTarjetasView);
		} catch (TarjetaBOUtilsException e) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return crearRespuestaErrorListaTarjetasCliente();
		}
		return respuesta;
	}

	private void revisarSiEsIngresoDeGoto(Respuesta<TarjetasView> respuestaView) {
		
		TarjetaView tarjetaSeleccionada = respuestaView.getRespuesta().getTarjetas().get(respuestaView.getRespuesta().getTarjetaSeleccionada());
		
		if (sesionParametros.getIngresoDesdeGoto() && tarjetaSeleccionada.getIsAdicional()) {
			respuestaView.getRespuesta().setTarjetaSeleccionada(0);		
		}
		
		sesionParametros.setIngresoDesdeGoto(Boolean.FALSE);				
	}
	
	/**
	 * Generar lista tarjetas view.
	 *
	 * @param tarjetaSeleccionada
	 *            the tarjeta seleccionada
	 * @return the list
	 * @throws BusinessException
	 *             the business exception
	 */
	private DatosSelectorTarjetaView generarListaTarjetasView(TarjetaSeleccionada tarjetaSeleccionada)
			throws BusinessException {
		Respuesta<DisponiblesYConsumoDTO> rtaDispyConsDTO = getSelectorYCabeceraBO()
				.obtenerTarjetas(sesionCliente.getCliente());
		if (!esRespuestaERROR(rtaDispyConsDTO.getEstadoRespuesta())) {
			sesionTarjetas.setLimitesYDisponibles(rtaDispyConsDTO.getRespuesta().getLimitesYDisponiblesDTO());

			List<TarjetaView> list = generarListaTarjetaView(rtaDispyConsDTO.getRespuesta().getResumenes());

			return new DatosSelectorTarjetaView(rtaDispyConsDTO.getItemsMensajeRespuesta(), list,
					obtenerIdentificacionCuentaDefault(rtaDispyConsDTO, tarjetaSeleccionada));
		}
		return new DatosSelectorTarjetaView(rtaDispyConsDTO.getItemsMensajeRespuesta(), new ArrayList<TarjetaView>(),
				null);
	}

	/**
	 * Generar tarjeta view.
	 *
	 * @param listaTarjetasView
	 *            the lista tarjetas view
	 * @param rtaUltimosConsumosView
	 *            the rta ultimos consumos view
	 * @param tarjetaSeleccionada
	 *            the tarjeta seleccionada
	 * @param items
	 *            the items
	 * @return the tarjetas view
	 */
	private TarjetasView generarTarjetaView(List<TarjetaView> listaTarjetasView,
			Respuesta<ConsumosView> rtaUltimosConsumosView, TarjetaSeleccionada tarjetaSeleccionada,
			List<ItemMensajeRespuesta> items) {

		TarjetasView tarjetasView = new TarjetasView();
		if (tarjetaSeleccionada != null && tarjetaSeleccionada.getId() != null) {
			Cuenta cuenta = buscarTarjetaById(tarjetaSeleccionada.getId());
			int index = buscarIndexByCuenta(cuenta, listaTarjetasView);
			tarjetasView.setTarjetaSeleccionada(index);
		} else {
			tarjetasView.setTarjetaSeleccionada(0);
		}
		tarjetasView.setTarjetas(listaTarjetasView);
		completarConGrillaUltimosConsumos(tarjetasView, rtaUltimosConsumosView, items);
		tarjetasView.setTooltipTarjetaFavorita(getSelectorYCabeceraBO().obtenerTooltipFavorito());
		tarjetasView.setTooltipTarjetaNoFavorita(getSelectorYCabeceraBO().obtenerTooltipNoFavorito());
		ModuloPermiso permisoPagoTarjetaCredito = moduloPermisoBO
				.obtenerModuloPermisoPorAccion(AccionController.IR_INICIO_PAGO_DE_TARJETA);
		tarjetasView.setMostrarOpcionPagoTarjetaCredito((permisoPagoTarjetaCredito.isModuloOculto()
				|| sesionCliente.getCliente().getCuentasParaEfectuarPago().isEmpty()) ? false : true);
		tarjetasView.setMensajeOpcionPagoTarjetaCredito(permisoPagoTarjetaCredito.isModuloOculto() ? permisoPagoTarjetaCredito.getMensaje() : "");
		tarjetasView.setLegalesUltimosConsumos(legalBO.obtenerLegalesPorCodigo("1012"));
		tarjetasView.setGrupoAfinidad(cambioGrupoAfinidadManager.obtenerGrupoAfinidadParaFlujos().getRespuesta());
		tarjetasView.setMostrarOpcionContracargos(contracargosService.getOptionContracargos(sesionCliente.getCliente().getNup()));
		return tarjetasView;
	}

	/**
	 * Completar con grilla ultimos consumos.
	 *
	 * @param tarjetasView
	 *            the tarjetas view
	 * @param rtaUltimosConsumosView
	 *            the rta ultimos consumos view
	 * @param items
	 *            the items
	 */
	private void completarConGrillaUltimosConsumos(TarjetasView tarjetasView,
			Respuesta<ConsumosView> rtaUltimosConsumosView, List<ItemMensajeRespuesta> items) {
		tarjetasView.setConsumoTarjetaDefault(rtaUltimosConsumosView.getRespuesta().getConsumosTarjetas());
		tarjetasView.setEstadoRespuestaConsumo(rtaUltimosConsumosView.getRespuesta().getEstadoRespuestaConsumos());
		tarjetasView.setEstadoRespuestaConsumosPendientes(
				rtaUltimosConsumosView.getRespuesta().getEstadoRespuestaConsumosPendientes());
		tarjetasView
				.setMuestraTarjetasConCabecera(rtaUltimosConsumosView.getRespuesta().getMuestraTarjetasConCabecera());
		if (!esRespuestaOK(rtaUltimosConsumosView.getEstadoRespuesta())) {
			List<ItemMensajeRespuesta> itemsGrilla = rtaUltimosConsumosView.getItemsMensajeRespuesta();
			if (items != null && itemsGrilla != null && !itemsGrilla.isEmpty()) {
				items.addAll(itemsGrilla);
			}
		}
	}

	/**
	 * Buscar index by cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param listaTarjetasView
	 *            the lista tarjetas view
	 * @return the int
	 */
	private int buscarIndexByCuenta(Cuenta cuenta, List<TarjetaView> listaTarjetasView) {
		int posicion = 0;
		for (TarjetaView tarjetaView : listaTarjetasView) {
			if (tarjetaView.getNumeroCuenta()
					.equals(obtenerNumeroCuenta(cuenta.getNroSucursal(), cuenta.getNroCuentaProducto()))) {
				return posicion;
			}
			posicion++;
		}
		return -1;
	}

	/**
	 * Buscar tarjeta by id.
	 *
	 * @param id
	 *            the id
	 * @return the cuenta
	 */
	private Cuenta buscarTarjetaById(String id) {
		Cuenta result = new Cuenta();
		Cliente cliente = sesionCliente.getCliente();
		List<Cuenta> cuentas = cliente.getCuentas();
		Integer index = Integer.valueOf(id);
		for (Cuenta cuenta : cuentas) {
			if (index.equals(cuenta.getIndex())) {
				result = cuenta;
			}
		}
		return result;
	}

	/**
	 * Obtener identidicacion cuenta.
	 *
	 * @param respuestaDispyConsDTO
	 *            the respuesta dispy cons DTO
	 * @param tarjetaSeleccionada
	 *            the tarjeta seleccionada
	 * @return the identificacion cuenta
	 */
	private IdentificacionCuenta obtenerIdentificacionCuentaDefault(
			Respuesta<DisponiblesYConsumoDTO> respuestaDispyConsDTO, TarjetaSeleccionada tarjetaSeleccionada) {
		DetalleTarjetaDTO detalle = new DetalleTarjetaDTO();
		if (tarjetaSeleccionada != null && tarjetaSeleccionada.getId() != null) {
			Cuenta cuenta = buscarTarjetaById(tarjetaSeleccionada.getId());
			for (ResumenTarjetaDTO resumen : respuestaDispyConsDTO.getRespuesta().getResumenes()) {
				if (StringUtils.equals(cuenta.getNroCuentaProducto(), resumen.getDetalle().getNroCuentaProducto())) {
					detalle = resumen.getDetalle();
					break;
				}
			}
		} else {
			detalle = obtenerDetalleTarjetaDTODefault(respuestaDispyConsDTO);
		}
		return new IdentificacionCuenta(detalle.getNroSucursal(), detalle.getNroCuentaProducto());
	}

	/**
	 * Generar lista tarjeta view.
	 *
	 * @param resumenTarjetaList
	 *            the resumen tarjeta list
	 * @return the list
	 */
	private List<TarjetaView> generarListaTarjetaView(List<ResumenTarjetaDTO> resumenTarjetaList) {
		List<TarjetaView> tarjetas = new ArrayList<TarjetaView>();
		for (ResumenTarjetaDTO resumenTarjetaDTO : resumenTarjetaList) {
			DetalleTarjetaDTO detalleDTO = resumenTarjetaDTO.getDetalle();
			TarjetaView tarjetaView = new TarjetaView(detalleDTO,
					obtenerNumeroCuenta(detalleDTO.getNroSucursal(), detalleDTO.getNroCuentaProducto()));
			tarjetas.add(tarjetaView);
		}
		return tarjetas;
	}

	/**
	 * Actualizar tarjeta favorita.
	 *
	 * @param respuestaView
	 *            the respuesta view
	 * @param tarjetaSeleccionada
	 *            the tarjeta seleccionada
	 */
	private void actualizarTarjetaFavorita(Respuesta<TarjetasView> respuestaView,
			TarjetaSeleccionada tarjetaSeleccionada) {
		actualizarEstadoFavorita(respuestaView);
		boolean tengoTarjetaSeleccionada = tarjetaSeleccionada != null && tarjetaSeleccionada.getId() != null;
		boolean tengoFavorita = false;
		List<TarjetaView> tarjetas = respuestaView.getRespuesta().getTarjetas();
		for (int i = 0; i < tarjetas.size(); i++) {
			TarjetaView tarjetaView = tarjetas.get(i);
			if (tarjetaView.getIsFavorito()) {
				tengoFavorita = true;
				if (!tengoTarjetaSeleccionada) {
					respuestaView.getRespuesta().setTarjetaSeleccionada(i);
				}
				break;
			}
		}
		if (!tengoFavorita) {
			tarjetas.get(0).setIsFavorito(true);
		}
	}

	/**
	 * Hay error en carga de tarjeta favorita.
	 *
	 * @param respuestaView
	 *            the respuesta view
	 * @return the boolean
	 */
	private void actualizarEstadoFavorita(Respuesta<TarjetasView> respuestaView) {
		List<ItemMensajeRespuesta> itemsRespuesta = obtenerItemMensajeRespuestaDeCliente();
		if (itemsRespuesta != null && !itemsRespuesta.isEmpty()) {
			for (ItemMensajeRespuesta itemMensajeRespuesta : itemsRespuesta) {
				if (itemMensajeRespuesta != null
						&& TipoError.ERROR_OBTENER_FAVORITO.equals(itemMensajeRespuesta.getTipoError())) {
					respuestaView.add(itemMensajeRespuesta);
				}
			}
		}
		if (respuestaView.getItemsMensajeRespuesta() != null && !respuestaView.getItemsMensajeRespuesta().isEmpty()) {
			respuestaView.setEstadoRespuesta(EstadoRespuesta.WARNING);
		}
	}

	/**
	 * Obtener detalle tarjeta DTO default.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the cuenta
	 */
	private DetalleTarjetaDTO obtenerDetalleTarjetaDTODefault(Respuesta<DisponiblesYConsumoDTO> respuesta) {
		try {
			DetalleTarjetaDTO detalleTarjetaDTO = null;
			List<ResumenTarjetaDTO> listaResumenes = respuesta.getRespuesta().getResumenes();
			if (!listaResumenes.isEmpty()) {
				for (ResumenTarjetaDTO resumen : listaResumenes) {
					DetalleTarjetaDTO detalle = resumen.getDetalle();
					if (detalle.getIsFavorita()) {
						detalleTarjetaDTO = detalle;
						break;
					}
				}
				if (detalleTarjetaDTO == null) {
					detalleTarjetaDTO = listaResumenes.get(0).getDetalle();
				}
			}
			return detalleTarjetaDTO;
		} catch (Exception e) {
			LOGGER.info("Error al obtener tarjeta favorito {}.", e);
			return null;
		}
	}

	/**
	 * Armar respuesta ERROR limites Y disponibles.
	 *
	 * @param marca
	 *            the marca
	 * @return the respuesta
	 */
	private Respuesta<LimitesYDisponiblesView> crearRespuestaErrorLimitesYDisponibles(String marca) {
		this.crearLogEstadistica(EstadisticasTarjetasUtil.getCodigoEstadisticaLimitesYDisponibles(marca),
				EstadisticasTarjetasUtil.getCodigoEstadisticaError());
		return this.crearRespuestaError(TagItemMensajeTarjetaEnum.LIMITESYDISPONIBLES.getDescripcionTag(),
				TipoError.ERROR_LIMITES_Y_DISPONIBLES_TARJETA,
				CodigoMensajeConstantes.CODIGO_ERROR_TARJETA_LIMITES_DISPONIBLES);
	}

	/**
	 * Armar respuesta ERROR limites Y disponibles.
	 * 
	 * //TODO falta el codigo de estadistica para el error generico de limites y
	 * disponibles
	 *
	 * @return the respuesta
	 */
	private Respuesta<LimitesYDisponiblesView> crearRespuestaErrorLimitesYDisponibles() {
		return this.crearRespuestaError(TagItemMensajeTarjetaEnum.LIMITESYDISPONIBLES.getDescripcionTag(),
				TipoError.ERROR_LIMITES_Y_DISPONIBLES_TARJETA,
				CodigoMensajeConstantes.CODIGO_ERROR_TARJETA_LIMITES_DISPONIBLES);
	}

	/**
	 * Armar respuesta OK limites Y disponibles.
	 *
	 * @param limitesYDisponiblesDTO
	 *            the limites Y disponibles DTO
	 * @param marca
	 *            the marca
	 * @return the respuesta
	 */
	private Respuesta<LimitesYDisponiblesView> armarRespuestaOKLimitesYDisponibles(
			LimitesYDisponiblesDTO limitesYDisponiblesDTO, String marca) {
		LimitesYDisponiblesView view = new LimitesYDisponiblesView(limitesYDisponiblesDTO);
		this.crearLogEstadistica(EstadisticasTarjetasUtil.getCodigoEstadisticaLimitesYDisponibles(marca),
				EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
		return casuistica.crearRespuestaOk(LimitesYDisponiblesView.class, view);
	}

	/**
	 * Actualiza el alias de la cuenta que se encuentra en sesion.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param alias
	 *            the alias
	 */
	private void actualizarAliasEnSesion(Cuenta cuenta, String alias) {
		for (Cuenta c : sesionCliente.getCliente().getCuentas()) {
			if (c.equals(cuenta)) {
				c.setAlias(alias);
				break;
			}
		}
	}

	/**
	 * Crear estadistica tarjeta favorita error.
	 */
	private void crearEstadisticaTarjetaFavoritaError() {
		this.crearLogEstadistica(EstadisticasTarjetasUtil.getCodigoEstadisticaTarjetaFavorita(),
				EstadisticasTarjetasUtil.getCodigoEstadisticaError());
	}

	/**
	 * Crear estadistica alias error.
	 */
	private void crearEstadisticaAliasError() {
		this.crearLogEstadistica(EstadisticasTarjetasUtil.getCodigoEstadisticaAlias(),
				EstadisticasTarjetasUtil.getCodigoEstadisticaError());
	}

	/**
	 * Crear estadistica ok.
	 */
	private void crearEstadisticaOk() {
		this.crearLogEstadistica(EstadisticasTarjetasUtil.getCodigoEstadisticaTarjetas(),
				EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
	}

	/**
	 * Crear estadistica error.
	 */
	private void crearEstadisticaError() {
		this.crearLogEstadistica(EstadisticasTarjetasUtil.getCodigoEstadisticaTarjetas(),
				EstadisticasTarjetasUtil.getCodigoEstadisticaError());
	}

	/**
	 * Armar respuesta no ok.
	 *
	 * @param listaTarjetasView
	 *            the lista tarjetas view
	 * @param rtaUltimosConsumosView
	 *            the rta ultimos consumos view
	 * @param tarjetaSeleccionada
	 *            the tarjeta seleccionada
	 * @param datosSelectorTarjetas
	 *            the datos selector tarjetas
	 * @return the respuesta
	 */
	private Respuesta<TarjetasView> crearRespuesta(List<TarjetaView> listaTarjetasView,
			Respuesta<ConsumosView> rtaUltimosConsumosView, TarjetaSeleccionada tarjetaSeleccionada,
			DatosSelectorTarjetaView datosSelectorTarjetas) {
		List<ItemMensajeRespuesta> items = datosSelectorTarjetas.getItemsMensajeRespuesta();
		TarjetasView tarjetasView = generarTarjetaView(listaTarjetasView, rtaUltimosConsumosView, tarjetaSeleccionada,
				items);
		sesionParametros.setTarjetasView(tarjetasView);
		if (!items.isEmpty()) {
			crearEstadisticaOk();
			return casuistica.crearRespuestaWarning(TarjetasView.class, tarjetasView, items);
		}
		crearEstadisticaOk();
		return casuistica.crearRespuestaOk(TarjetasView.class, tarjetasView);
	}

	/**
	 * Armar respuesta no ok.
	 *
	 * @return the respuesta
	 */
	private Respuesta<TarjetasView> crearRespuestaError() {
	    crearEstadisticaError();
		return crearRespuestaErrorGenerico(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
	}
	
	/**
	 * Armar respuesta no ok.
	 *
	 * @return the respuesta
	 */
	private Respuesta<StackTarjetasView> crearRespuestaErrorListaTarjetasCliente() {
	    crearEstadisticaError();
		return crearRespuestaErrorGenerico(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
	}
	/**
	 * validar el reemplazo de tarjetas que se encuentra en sesion.
	 *
	 * @return Respuesta<TarjetasView>      
	 * 
	 */
	@Override
	public Respuesta<TarjetasView> validarReemplazoDeTarjetas() {
		//El atributo "tarjetas" de "TarjetasView" es un "list" y no esta instanciado, tengo que instaciarlo antes de agregar cosas.
		Respuesta<TarjetasView> respuestaReemplazoDeTarjetasValidas = new Respuesta<TarjetasView>() ;
		TarjetasView tarjetasView = new TarjetasView();
		
		Cliente cliente = sesionCliente.getCliente();
		String nombre = cliente.getApellido1() + cliente.getNombre(); 
		List<OfertaComercialDTO> ofertasComerciales = sesionParametros.getOfertasComerciales().getOfertas();
		
		//veridicar si hay cliente
		if(cliente == null) {
			return crearRespuestaError(respuestaReemplazoDeTarjetasValidas,EstadoRespuesta.ERROR,tarjetasView);
		}
		
		List<Cuenta> cuentas = cliente.getCuentas();
		
		//si hay cuentas o tarjetas del cliente	
		if(cuentas == null || cuentas.isEmpty()) {
			return crearRespuestaError(respuestaReemplazoDeTarjetasValidas,EstadoRespuesta.ERROR,tarjetasView);
		}
		
		//filtro de tarjetas validas para el reemplzado de tarjetas
		boolean resumenAnualHabilitado = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_INICIO_RESUMEN_ANUAL)
				.tienePermisoDeVisibilidad();
		for (Cuenta cuenta : cuentas) {
			if(TarjetaBOUtils.esValidoReemplazoDeTarjeta(cuenta)) {
				boolean permiteResumenAnual = TarjetaUtils.permiteResumenAnual(cuenta) && resumenAnualHabilitado;
				tarjetasView.getTarjetas().add(new TarjetaView(cuenta, permiteResumenAnual));
			}
		}
		
		//filtro de ofertas para la obtener la variable: "variable1Char"
		for (OfertaComercialDTO oferta : ofertasComerciales) {
    		if (GOTO_RECAMBIO_CHIP.equals(oferta.getGotoLink().getLink())) {
    			tarjetasView.setVariable1Char(oferta.getVariable1Char());
    			sesionParametros.setOfertaRecambioChip(oferta);
    			break;
    		}
    	}
		
		//Iterator<OfertaComercialDTO> iteratorOfertas = ofertasComerciales.iterator();
		
		//verifico si hay tarjetas validad para el reemplazo de tarjetas
		if(tarjetasView.getTarjetas() == null || tarjetasView.getTarjetas().isEmpty()) 
			return crearRespuestaError(respuestaReemplazoDeTarjetasValidas,EstadoRespuesta.ERROR,tarjetasView);
		
		// Obtener sucursal principal del cliente
		String nroSucursalPrincipal = cliente.obtenerCuentaPrincipal().getNroSucursal();
		String direccionSucursal = "";
		if (StringUtils.isNotBlank(nroSucursalPrincipal)) {
            Sucursal sucursalCuenta = consultarSucursalesBO.consultarSucursalPorId(nroSucursalPrincipal)
                    .getRespuesta();
            if (sucursalCuenta != null) {
                direccionSucursal = sucursalCuenta.getDireccion();
            }
		}
		
		//si hay cliente y ese cliente tiene cuentas y esas cuentas son validas para el reemplazo de tarjetas. OK
		tarjetasView.setNombreTitular(nombre);
		tarjetasView.setDireccionSucursal(direccionSucursal);
		respuestaReemplazoDeTarjetasValidas.setRespuesta(tarjetasView);
		respuestaReemplazoDeTarjetasValidas.setEstadoRespuesta(EstadoRespuesta.OK);
		return respuestaReemplazoDeTarjetasValidas;
	}
	
	/**
	 * crea una respuesta del tipo error. 
	 *
	 * @return Respuesta<TarjetasView>      
	 * 
	 */
	private Respuesta<TarjetasView> crearRespuestaError(Respuesta<TarjetasView> respReemDeTarjeVal,
			EstadoRespuesta error, TarjetasView tarjetasView) {
		respReemDeTarjeVal.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respReemDeTarjeVal.setRespuesta(tarjetasView);
		return respReemDeTarjeVal;
		
	}

	/**
	 * Obtener token acceso resumen anual.
	 *
	 * @param tarjetaActiva the tarjeta activa
	 * @return the respuesta
	 */
	@Override
	public Respuesta<TokenView> obtenerTokenAccesoResumenAnual(TarjetaActivaView tarjetaActiva) {
		Respuesta<TokenView> respuestaToken = new Respuesta<TokenView>();
		TokenView tokenView = new TokenView();
		String tokenFirmado;
		try {
			IdentificacionCuenta idCuenta = new IdentificacionCuenta(tarjetaActiva.getTarjetaActiva());
			Cuenta cuenta = (Cuenta) getCuentaBO().buscarCuentaPorId(sesionCliente.getCliente(), idCuenta);
//			try {
//				datosSolicitanteDAO.obtenerSexoCliente(sesionCliente.getCliente());
//			} catch (DAOException e) {
//				LOGGER.error("Error al obtener los datos de cliente {}", e);
//				throw e;
//		    }
			tokenFirmado = obtenerTokenFirmado(cuenta);
			tokenView.setTokenFirmado(tokenFirmado);
			tokenView.setUrl(w2wUrl);
			respuestaToken.setEstadoRespuesta(EstadoRespuesta.OK);
			respuestaToken.setRespuesta(tokenView);
			estadisticaManager.add(EstadisticasConstants.ACCESO_RESUMEN_ANUAL_VISA, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} catch (DAOException e) {
			respuestaToken.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuestaToken.setRespuestaVacia(true);
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje(
					mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_TARJETA_GENERICO).getMensaje());
			item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			respuestaToken.add(item);
			estadisticaManager.add(EstadisticasConstants.ACCESO_RESUMEN_ANUAL_VISA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
        return respuestaToken;
	}

	/**
	 * Obtener token firmado.
	 *
	 * @param cuenta the cuenta
	 * @return the string
	 * @throws DAOException the DAO exception
	 */
	private String obtenerTokenFirmado(Cuenta cuenta) throws DAOException {
		LOGGER.info("Generando Token para la cuenta {}", cuenta);
		String firma = "";
		try {
			TokenW2W token = obtenerW2WToken(cuenta);
			LOGGER.debug("Generando firma con encoding {}.", appEncoding);
			firma = sign.buildPemSignature(token.toString().getBytes(appEncoding), TarjetaUtils.KEY_STORE_VISAAMEX, true);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error al generar firma. {}.", e);
			throw new DAOException(e);
		} catch (Exception eb) {
			LOGGER.error("Error al obtener Token firmado. {}.", eb);
			throw new DAOException(eb);
		}
		LOGGER.info("Firma Generada {}.", firma);
		return firma;
	}

	private TokenW2W obtenerW2WToken(Cuenta cuenta) throws Exception {
		LOGGER.info("Crear token W2W para ser firmado.");
		TokenW2W token = null;
		try {
			token = tokenW2WFactory.createToken(cuenta, sesionCliente.getCliente());
			LOGGER.info("Token generado\r\n{}", token);
		} catch (Exception eb) {
			LOGGER.error("Error al generar Token {}.", eb);
			throw eb;
		}
		return token;
	}

}
