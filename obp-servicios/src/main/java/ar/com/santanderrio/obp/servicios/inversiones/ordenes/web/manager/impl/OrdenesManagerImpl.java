/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.manager.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.utils.ListUtil;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.CodigoSistemaLoadOrdenesEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.OrdenBaseView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.bo.OrdenesBO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenBaseDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.manager.OrdenesManager;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.CuentaView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.FiltrosOrdenesView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.OrdenFondosView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.OrdenesView;

/**
 * The Class OrdenesManagerImpl.
 *
 * @author luis.ventocilla
 * @author emilio.watemberg
 * @see {@link OrdenesManager}
 * @since Mon 23, 2017
 */
@Component
public class OrdenesManagerImpl implements OrdenesManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrdenesManagerImpl.class);

	/** The ordenes BO. */
	@Autowired
	OrdenesBO ordenesBO;

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	SesionCliente sesionCliente;

	/** The mensaje manager. */
	@Autowired
	MensajeManager mensajeManager;

	/** The estadistica manager. */
	@Autowired
	EstadisticaManager estadisticaManager;

	/** The Mensaje dao. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The Constant ERROR_GENERICO. */
	public static final String ERROR_GENERICO = "Ha ocurrido un error al obtener las ordenes desde el SP";

	/** The formatter. */
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.manager.
	 * OrdenesManager#iniciarOrdenesOperaciones()
	 */
	@Override
	public Respuesta<OrdenesView> iniciarOrdenes() {
		Respuesta<OrdenesView> respuesta = null;
		try {
			Respuesta<OrdenesDTO> resp = ordenesBO.iniciarOrdenesOperaciones(sesionCliente.getCliente());
			if (EstadoRespuesta.OK.equals(resp.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_CONSULTA_ORDEN_Y_OPERACION,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				respuesta = cargarVistaConDTO(resp.getRespuesta());

			} else if (EstadoRespuesta.WARNING.equals(resp.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.CODIGO_CONSULTA_ORDEN_Y_OPERACION,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaFactory.crearRespuestaWarning("", TipoError.WARNING,
						CodigoMensajeConstantes.PLAZO_FIJO_SIN_ORDENES_Y_OPERACIONES_ULTIMOS_30_DIAS);
			} else {
				LOGGER.debug(ERROR_GENERICO);
				estadisticaManager.add(EstadisticasConstants.CODIGO_CONSULTA_ORDEN_Y_OPERACION,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return getRespuestaConErrorGenerico();
			}
		} catch (Exception e) {
			LOGGER.error(ERROR_GENERICO, e);
			estadisticaManager.add(EstadisticasConstants.CODIGO_CONSULTA_ORDEN_Y_OPERACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return getRespuestaConErrorGenerico();
		}
		return respuesta;
	}

	/**
	 * Gets the respuesta con error generico.
	 *
	 * @return the respuesta con error generico
	 */
	private Respuesta<OrdenesView> getRespuestaConErrorGenerico() {
		Mensaje mensaje = mensajeManager
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(mensaje.getMensaje());
		itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		Respuesta<OrdenesView> respuesta = new Respuesta<OrdenesView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuesta.add(itemMensajeRespuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.manager.
	 * OrdenesManager#buscarOrdenes(java.lang.String)
	 */
	@Override
	public Respuesta<OrdenesView> buscarOrdenesPorCuenta(String numeroCuenta) {
		Respuesta<OrdenesView> respuesta = respuestaFactory.crearRespuestaOk(OrdenesView.class);
		try {
			Respuesta<OrdenesDTO> resp = ordenesBO.buscarOrdenesOperacionesPorCuenta(numeroCuenta, CodigoSistemaLoadOrdenesEnum.FONDO.getCodigoSistema());
			if (resp.getEstadoRespuesta() != EstadoRespuesta.ERROR) {
				respuesta = cargarVistaConDTO(resp.getRespuesta());
			} else {
				LOGGER.debug(ERROR_GENERICO);
				return getRespuestaConErrorGenerico();
			}

		} catch (Exception e) {
			LOGGER.error(ERROR_GENERICO, e);
			return getRespuestaConErrorGenerico();
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.manager.
	 * OrdenesManager#buscarOrdenesOperaciones(ar.com.santanderrio.obp.servicios
	 * .inversiones.ordenes.web.view.FiltrosOrdenesView)
	 */
	@Override
	public Respuesta<OrdenesView> buscarOrdenes(FiltrosOrdenesView filtrosOrdenesView) {
		Respuesta<OrdenesView> respuesta = respuestaFactory.crearRespuestaOk(OrdenesView.class);
		try {
			Respuesta<OrdenesDTO> resp = ordenesBO.buscarOrdenesOperaciones(filtrosOrdenesView, CodigoSistemaLoadOrdenesEnum.FONDO.getCodigoSistema());
			if (resp.getEstadoRespuesta() != EstadoRespuesta.ERROR) {
				respuesta = cargarVistaConDTO(resp.getRespuesta());
				respuesta.getRespuesta().setMensajeSinOrdenes(mensajeBO.obtenerMensajePorCodigo(
						CodigoMensajeConstantes.INVERSIONES_SIN_ORDENES_Y_OPERACIONES).getMensaje());
			} else {
				LOGGER.debug(ERROR_GENERICO);
				estadisticaManager.add(EstadisticasConstants.BUSQUEDA_CONSULTA_ORDENES_OPERACIONES,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return getRespuestaConErrorGenerico();
			}

		} catch (Exception e) {
			LOGGER.error(ERROR_GENERICO, e);
			estadisticaManager.add(EstadisticasConstants.BUSQUEDA_CONSULTA_ORDENES_OPERACIONES,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return getRespuestaConErrorGenerico();
		}
		estadisticaManager.add(EstadisticasConstants.BUSQUEDA_CONSULTA_ORDENES_OPERACIONES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuesta;
	}

	/**
	 * Cargar vista con DTO.
	 *
	 * @param ordenes
	 *            the ordenes
	 * @return the ordenes view
	 */
	private Respuesta<OrdenesView> cargarVistaConDTO(OrdenesDTO ordenes) {
		return cargarVistaConDTO(ordenes, null);
	}

	/**
	 * Cargar vista con DTO.
	 *
	 * @param ordenes
	 *            the ordenes
	 * @param filtro
	 *            the filtro
	 * @return the ordenes view
	 */
	private Respuesta<OrdenesView> cargarVistaConDTO(OrdenesDTO ordenes, FiltrosOrdenesView filtro) {
		Respuesta<OrdenesView> respuesta = respuestaFactory.crearRespuestaOk(OrdenesView.class);
		OrdenesView ordenesView = new OrdenesView();
		ordenesView.setCuentas(getCuentasView(ordenes));
		ordenesView.setFiltro(filtro);
		ordenesView.setOrdenes(getOrdenesView(ordenes));
		ordenesView.setMensajeSinOrdenes(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.PLAZO_FIJO_SIN_ORDENES_Y_OPERACIONES_ULTIMOS_30_DIAS)
						.getMensaje());
		respuesta.setRespuesta(ordenesView);
		return respuesta;
	}

	/**
	 * Gets the cuentas view.
	 *
	 * @param ordenes
	 *            the ordenes
	 * @return the cuentas view
	 */
	private List<CuentaView> getCuentasView(OrdenesDTO ordenes) {
		List<CuentaView> lista = new ArrayList<CuentaView>();
		if (ordenes != null) {
			List<Cuenta> cuentas = ordenes.getCuentas();
			for (Cuenta cuenta : ListUtil.safe(cuentas)) {
				CuentaView cuentaView = new CuentaView();
				cuentaView.setAlias(cuenta.getAlias());
				cuentaView.setCbu(cuenta.getCbu());
				cuentaView.setNumero(cuenta.getNroCuentaProducto());
				String nroSucursalString = ISBANStringUtils.eliminarCeros(cuenta.getSucursalPaquete());
				int nroSucursal = Integer.parseInt(nroSucursalString);
				if(250 <= nroSucursal) {
					cuentaView.setNumeroFormateado(getNumeroCuentaFormateadoConSucursal(cuenta, true));
					cuentaView.setTituloCuenta("Cuenta Banca Privada");
				} else {
					cuentaView.setNumeroFormateado(getNumeroCuentaFormateadoConSucursal(cuenta, false));
				}
				cuentaView.setIntervinientes(cuenta.getIntervinientes());
				lista.add(cuentaView);
			}
		}
		return lista;
	}

	/**
	 * Gets the numero cuenta formateado con sucursal.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @return the numero cuenta formateado con sucursal
	 */
	private String getNumeroCuentaFormateadoConSucursal(Cuenta cuenta, Boolean esBancaPrivada) {
		String nroCuenta;
		if (esBancaPrivada) {
			nroCuenta = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto().substring(1, cuenta.getNroCuentaProducto().length()));
		} else {
			nroCuenta = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
		}
		return ISBANStringUtils.eliminarCeros(cuenta.getSucursalPaquete()) + "-" + nroCuenta;
	}

	/**
	 * Gets the ordenes view.
	 *
	 * @param ordenes
	 *            the ordenes
	 * @return the ordenes view
	 */
	private List<OrdenBaseView> getOrdenesView(OrdenesDTO ordenes) {
		List<OrdenBaseView> lista = new ArrayList<OrdenBaseView>();
		if (ordenes != null) {
			List<OrdenBaseDTO> listOrdenes = ordenes.getOrdenes();
			for (OrdenBaseDTO ordenBaseDTO : listOrdenes) {
				OrdenDTO ordenDTO = (OrdenDTO) ordenBaseDTO;
				OrdenFondosView ordenFondoView = new OrdenFondosView();
				ordenFondoView.setEstado(ordenDTO.getEstado());
				ordenFondoView.setFecha(ordenDTO.getFecha());
				ordenFondoView.setFondo(ordenDTO.getFondo());
				ordenFondoView.setImporte(ISBANStringUtils.formatearSaldo(new BigDecimal(ordenDTO.getImporte())));
				ordenFondoView.setNumero(ordenDTO.getNumero());
				ordenFondoView.setTipo(ordenDTO.getTipoOrden());
				OrdenBaseView ordenView = (OrdenBaseView) ordenFondoView;
				lista.add(ordenView);
			}
		}
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.manager.
	 * OrdenesManager#obtenerFiltrosDeBusqueda()
	 */
	@Override
	public Respuesta<FiltrosOrdenesView> obtenerFiltrosDeBusqueda() {
		Respuesta<FiltrosOrdenesView> resp = respuestaFactory.crearRespuestaOk(FiltrosOrdenesView.class);
		FiltrosOrdenesView filtrosOrdenesView = new FiltrosOrdenesView(true);
		resp.setRespuesta(filtrosOrdenesView);
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.manager.
	 * OrdenesManager#grabarEstadisticaBusqueda()
	 */
	@Override
	public void grabarEstadisticaBusqueda() {
		estadisticaManager.add(EstadisticasConstants.BUSQUEDA_CONSULTA_ORDENES_OPERACIONES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

}
