/**
 *
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagosPendientesBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.ConsumoPendienteConfirmacionBo;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.UltimosConsumosTarjetaBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetasDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleUltimosConsumosPDF;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaDetalleConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.MovimientosTarjetaExcel2;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimosConsumosDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ErrorTarjetasEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.UltimosConsumosManager;
import ar.com.santanderrio.obp.servicios.tarjetas.web.util.EstadisticasTarjetasUtil;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumoTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumosView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.LineaDetalleConsumoTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetasView;

/**
 * 
 * The Class UltimosConsumosManagerImpl.
 * 
 * @author sabrina.cis
 */
@Component
public class UltimosConsumosManagerImpl implements UltimosConsumosManager {

	/** The Constant PAGO_TARJETA_EFECTIVO. */
	private static final String PAGO_TARJETA_EFECTIVO = "01";

	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UltimosConsumosManagerImpl.class);

	/** The Constant REPORT_NAME. */
	private static final String REPORT_NAME = "excelUltimosConsumosYPendientes";

	/** The Constant REPORT_NAME. */
	private static final String REPORT_NAME_RECARGABLE = "excelUltimosConsumosYPendientesRecargables";

	/** The Constant SERVICIO_DEVOLVIO_LOG_LABEL. */
	public static final String SERVICIO_DEVOLVIO_LOG_LABEL = "El servicio devolvio: ";

	/** The pagos pendientes BO. */
	@Autowired
	private PagosPendientesBO pagosPendientesBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The sesion cliente. */
	@Autowired
	protected SesionCliente sesionCliente;

	/** The sesion tarjetas. */
	@Autowired
	protected SesionTarjetas sesionTarjetas;

	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The respuestaFactory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The ultimosConsumosService. */
	@Autowired
	private UltimosConsumosTarjetaBO ultimosConsumosBO;

	/** The consumoPendienteConfirmacion bo. */
	@Autowired
	private ConsumoPendienteConfirmacionBo consumosPendientesBO;

	/** The registro sesion. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * UltimosConsumosManager#exportarUltimosConsumos()
	 */
	@Override
	public Respuesta<Reporte> exportarUltimosConsumos() {
		ConsumoTarjetasDTO tarjetas = sesionTarjetas.obtenerConsumoTarjetas();
		Cliente cliente = sesionCliente.getCliente();
		Respuesta<Reporte> respuesta = ultimosConsumosBO.obtenerReporte(tarjetas, REPORT_NAME, cliente);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * UltimosConsumosManager#verDetalleUltimosConsumos()
	 */
	@Override
	public Respuesta<Void> verDetalleUltimosConsumos() {
		crearLogEstadistica(EstadisticasConstants.CODIGO_VER_DETALLE_ULTIMOS_CONSUMOS,
				EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/**
	 * 
	 * Inicio Tarjetas: Obtiene la respuesta de ultimos consumos.
	 * 
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ConsumosView> obtenerUltimosConsumos(IdentificacionCuenta identificacionCuenta) {
		try {
			return crearRespuestaDeConsumosYPendientes(identificacionCuenta);
		} catch (BusinessException ex) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, ex);
		}
		Boolean mostrarStopDebit = muestroStopDebit(sesionCliente.getCliente(),
				(Cuenta) cuentaBO.buscarCuentaPorId(sesionCliente.getCliente(), identificacionCuenta));
		return crearRespuestaFallaConsumosFallaPendientes(mostrarStopDebit);
	}

	/**
	 * Selector de Tarjetas: Obtiene la respuesta de ultimos consumos.
	 * 
	 * @param idCuenta
	 *            the id cuenta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ConsumosView> obtenerUltimosConsumos(String idCuenta) {
		Respuesta<ConsumosView> consumos = obtenerUltimosConsumos(new IdentificacionCuenta(idCuenta));
		sesionParametros.getTarjetasView().setConsumoTarjetaDefault(consumos.getRespuesta().getConsumosTarjetas());
		return consumos;
	}

	/**
	 * Crear respuesta de consumos Y pendientes.
	 * 
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	private Respuesta<ConsumosView> crearRespuestaDeConsumosYPendientes(IdentificacionCuenta identificacionCuenta)
			throws BusinessException {
		try {
			Cuenta cuenta = (Cuenta) cuentaBO.buscarCuentaPorId(sesionCliente.getCliente(), identificacionCuenta);
			String marca = TarjetaUtils.getMarca(cuenta);
			Boolean mostrarStopDebit = muestroStopDebit(sesionCliente.getCliente(), cuenta);
			Respuesta<UltimosConsumosDTO> rtaConsumosDTO = ultimosConsumosBO.obtenerUltimosConsumos(cuenta);
			Respuesta<UltimosConsumosDTO> rtaPendientesDTO = consumosPendientesBO.obtenerConsumoPendiente(cuenta);
			if (esGrillaOK(rtaConsumosDTO, rtaPendientesDTO)) {
				Respuesta<ConsumosView> resView = crearRespuestaCasuisticaOK(rtaConsumosDTO, rtaPendientesDTO, marca,
						cuenta, mostrarStopDebit);
				return resView;
			}
			return crearRespuestaCasuisticaError(rtaConsumosDTO, rtaPendientesDTO, marca, mostrarStopDebit);
		} catch (NullPointerException ex) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, ex);
			Cuenta cuenta = (Cuenta) cuentaBO.buscarCuentaPorId(sesionCliente.getCliente(), identificacionCuenta);
			Boolean mostrarStopDebit = muestroStopDebit(sesionCliente.getCliente(), cuenta);
			return crearRespuestaFallaConsumosFallaPendientes(mostrarStopDebit);
		}
	}

	/**
	 * Muestro stop debit.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	private Boolean muestroStopDebit(Cliente cliente, Cuenta cuenta) {
		DatosTarjeta datosTarjeta = new DatosTarjeta();
		try {
			datosTarjeta = pagosPendientesBO.obtenerDatosUnaTarjeta(cliente, cuenta);
		} catch (BusinessException e) {
			LOGGER.error("Error recuperando Datos de Tarjeta para determinar Stop Debit", e);
			return false;
		}
		String formaPago = datosTarjeta.getFormaPagoTarjetaCredito();
		Boolean isRecargable = TarjetaBOUtils.esCuentaRecargable(cuenta);
		return !isRecargable && "TI".equals(cuenta.getCodigoTitularidad()) && !PAGO_TARJETA_EFECTIVO.equals(formaPago);
	}

	/**
	 * Crear respuesta casuistica OK.
	 *
	 * @param rtaConsumosDTO
	 *            the rta consumos DTO
	 * @param rtaPendientesDTO
	 *            the rta pendientes DTO
	 * @param marca
	 *            the marca
	 * @param cuenta
	 *            the cuenta
	 * @param mostrarStopDebit
	 *            the mostrar stop debit
	 * @return the respuesta
	 */
	private Respuesta<ConsumosView> crearRespuestaCasuisticaOK(Respuesta<UltimosConsumosDTO> rtaConsumosDTO,
			Respuesta<UltimosConsumosDTO> rtaPendientesDTO, String marca, Cuenta cuenta, Boolean mostrarStopDebit) {
		if (esRespuestaGrillaOK(rtaConsumosDTO, rtaPendientesDTO)) {
			sesionTarjetas.setConsumosTarjeta(rtaConsumosDTO.getRespuesta().getUltimosConsumos());
			return crearRespuestaOKGrilla(marca, cuenta,
					obtenerListaUnificada(rtaConsumosDTO, rtaPendientesDTO, esRecargable(cuenta)),
					mostrarStopDebit);
		} else {
			if (esRespuestaGrillaConsumosOKYSinPendientes(rtaConsumosDTO, rtaPendientesDTO)) {
				sesionTarjetas.setConsumosTarjeta(rtaConsumosDTO.getRespuesta().getUltimosConsumos());
				iniciarTotalesPendientes(rtaConsumosDTO, true);
				return crearRespuestaOKGrilla(marca, cuenta, obtenerListaConsumosSinUnificar(rtaConsumosDTO),
						mostrarStopDebit);
			} else {
				iniciarTotalesConfirmados(rtaPendientesDTO, true);
				return crearRespuestaOKGrillaSinConsumosConPendientes(marca, cuenta,
						obtenerListaConsumosSinUnificar(rtaPendientesDTO), mostrarStopDebit);
			}
		}
	}

	private Boolean esRecargable(Cuenta cuenta) {
		return TarjetaBOUtils.esTipoCuentaYClaseCuenta(TipoCuenta.VISA.getCodigo(),
				TipoCuenta.VISA_RECARGABLE.getCodigo(), TarjetaUtils.VISA_RECARGABLE, cuenta);
	}

	/**
	 * Iniciar en cero totales pendientes, si no hay error de consumos pendientes.
	 * Ok Confirmados, falla o es sin consumos pendientes.
	 * 
	 * @param rtaConsumosDTO
	 *            the rta consumos DTO
	 * @param sinConsumos
	 *            the sin consumos
	 */
	private void iniciarTotalesPendientes(Respuesta<UltimosConsumosDTO> rtaConsumosDTO, Boolean sinConsumos) {
		BigDecimal dato = null;
		if (sinConsumos)
			dato = new BigDecimal(0);
		for (ConsumoTarjetaDTO tarjeta : rtaConsumosDTO.getRespuesta().getUltimosConsumos()) {
			tarjeta.setConsumoPesosPendientes(dato);
			tarjeta.setConsumoDolaresPendientes(dato);
		}
	}

	/**
	 * Iniciar en cero totales pendientes. Ok Consumos pendientes, falla o es sin
	 * consumos confirmados.
	 * 
	 * @param rtaConsumosDTO
	 *            the rta consumos DTO
	 * @param sinConsumos
	 *            the sin consumos
	 */
	private void iniciarTotalesConfirmados(Respuesta<UltimosConsumosDTO> rtaConsumosDTO, Boolean sinConsumos) {
		BigDecimal dato = null;
		if (sinConsumos)
			dato = new BigDecimal(0);
		for (ConsumoTarjetaDTO tarjeta : rtaConsumosDTO.getRespuesta().getUltimosConsumos()) {
			tarjeta.setConsumoPesos(dato);
			tarjeta.setConsumoDolares(dato);
		}
	}

	/**
	 * Iniciar en cero totales pendientes, si no hay error de consumos pendientes.
	 * 
	 * @param rtaConsumosDTO
	 *            the rta consumos DTO
	 * @param sinConsumos
	 *            the sin consumos
	 * @param sinPendiente
	 *            the sin pendiente
	 */
	private void iniciarTotalesConfirmadosYPendientes(Respuesta<UltimosConsumosDTO> rtaConsumosDTO, Boolean sinConsumos,
			Boolean sinPendiente) {
		BigDecimal datoConfirmado = null;
		BigDecimal datoPendiente = null;
		if (sinConsumos)
			datoConfirmado = new BigDecimal(0);
		if (sinPendiente)
			datoPendiente = new BigDecimal(0);
		for (ConsumoTarjetaDTO tarjeta : rtaConsumosDTO.getRespuesta().getUltimosConsumos()) {
			tarjeta.setConsumoPesos(datoConfirmado);
			tarjeta.setConsumoDolares(datoConfirmado);
			tarjeta.setConsumoPesosPendientes(datoPendiente);
			tarjeta.setConsumoDolaresPendientes(datoPendiente);
		}
	}

	/**
	 * Obtener lista unificada.
	 * 
	 * @param rtaConsumosDTO
	 *            the rta consumos DTO
	 * @param rtaPendientesDTO
	 *            the rta pendientes DTO
	 * @return the list
	 */
	private List<ConsumoTarjetaDTO> obtenerListaUnificada(Respuesta<UltimosConsumosDTO> rtaConsumosDTO,
			Respuesta<UltimosConsumosDTO> rtaPendientesDTO, Boolean esRecargable) {
		List<ConsumoTarjetaDTO> pendientes = rtaPendientesDTO.getRespuesta().getUltimosConsumos();
		List<ConsumoTarjetaDTO> ultimosConsumos = rtaConsumosDTO.getRespuesta().getUltimosConsumos();

		for (ConsumoTarjetaDTO ultimoConsumo : ultimosConsumos) {
			for (ConsumoTarjetaDTO pendiente : pendientes) {
				if (ultimoConsumo.getNumero().equals(pendiente.getNumero())) {
					procesarConsumoTarjeta(pendiente, ultimoConsumo, esRecargable);
					break;
				}
			}
		}
		return ultimosConsumos;
	}

	/**
	 * Procesar consumo tarjeta.
	 * 
	 * @param pendiente
	 *            the pendiente
	 * @param ultimoConsumo
	 *            the ultimo consumo
	 */
	private void procesarConsumoTarjeta(ConsumoTarjetaDTO pendiente, ConsumoTarjetaDTO ultimoConsumo,
			Boolean esRecargable) {
		List<LineaDetalleConsumoTarjetaDTO> listaUnificada = new ArrayList<LineaDetalleConsumoTarjetaDTO>();
		if (esRecargable) {
			listaUnificada = filtrarPendientesDuplicados(pendiente, ultimoConsumo);
		} else {
			listaUnificada = pendiente.getLineas();
		}
		for (LineaDetalleConsumoTarjetaDTO linea : ultimoConsumo.getLineas()) {
			listaUnificada.add(linea);
		}
		ultimoConsumo.setLineas(listaUnificada);
		if (pendiente.getLineas().isEmpty()) {
			ultimoConsumo.setConsumoPesosPendientes(new BigDecimal(0));
			ultimoConsumo.setConsumoDolaresPendientes(new BigDecimal(0));
		} else {
			ultimoConsumo.setConsumoPesosPendientes(pendiente.getConsumoPesosPendientes());
			ultimoConsumo.setConsumoDolaresPendientes(pendiente.getConsumoDolaresPendientes());
		}
		if (ultimoConsumo.getLineas().isEmpty()) {
			ultimoConsumo.setConsumoPesos(new BigDecimal(0));
			ultimoConsumo.setConsumoDolares(new BigDecimal(0));
		} else {
			ultimoConsumo.setConsumoPesos(ultimoConsumo.getConsumoPesos());
			ultimoConsumo.setConsumoDolares(ultimoConsumo.getConsumoDolares());
		}
	}

	private List<LineaDetalleConsumoTarjetaDTO> filtrarPendientesDuplicados(ConsumoTarjetaDTO pendiente,
			ConsumoTarjetaDTO ultimoConsumo) {
		List<LineaDetalleConsumoTarjetaDTO> pendientesDuplicados = new ArrayList<LineaDetalleConsumoTarjetaDTO>();
		BigDecimal totalPesosDuplicados = BigDecimal.ZERO;
		BigDecimal totalDolaresDuplicados = BigDecimal.ZERO;
		for (LineaDetalleConsumoTarjetaDTO lineaPendiente : pendiente.getLineas()) {
			for (LineaDetalleConsumoTarjetaDTO lineaConsumo : ultimoConsumo.getLineas()) {
				if (Integer.valueOf(lineaPendiente.getNroReferencia().trim())
						.equals(Integer.valueOf(lineaConsumo.getNroReferencia().trim()))) {
					pendientesDuplicados.add(lineaPendiente);
					if (lineaPendiente.getConsumoPesos()) {
						totalPesosDuplicados = totalPesosDuplicados.add(lineaPendiente.getImportePesos().negate());
					} else {
						totalDolaresDuplicados = totalDolaresDuplicados.add(lineaPendiente.getImporteDolares().negate());
					}
					break;
				}
			}
		}
		pendiente.getLineas().removeAll(pendientesDuplicados);
		pendiente.setConsumoPesosPendientes(pendiente.getConsumoPesosPendientes().subtract(totalPesosDuplicados));
		pendiente.setConsumoDolaresPendientes(pendiente.getConsumoDolaresPendientes().subtract(totalDolaresDuplicados));
		return pendiente.getLineas();
	}
	
	/**
	 * Obtener lista consumos sin unificar.
	 * 
	 * @param rtaConsumosDTO
	 *            the rta consumos DTO
	 * @return the list
	 */
	private List<ConsumoTarjetaDTO> obtenerListaConsumosSinUnificar(Respuesta<UltimosConsumosDTO> rtaConsumosDTO) {
		List<ConsumoTarjetaDTO> consumos = rtaConsumosDTO.getRespuesta().getUltimosConsumos();
		ordenarConsumosTarjetaPorTipoConsumoYFecha(consumos);
		return consumos;
	}

	/**
	 * Crear estadistica exportar ultimos consumos.
	 */
	private void crearEstadisticaExportarUltimosConsumos() {
		crearLogEstadistica(EstadisticasTarjetasUtil.getCodigoEstadisticaExportarUltimosConsumos(),
				EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
	}

	/**
	 * Ordenar consumos tarjeta por tipo consumo Y fecha.
	 * 
	 * @param tarjetas
	 *            the tarjetas
	 */
	private void ordenarConsumosTarjetaPorTipoConsumoYFecha(List<ConsumoTarjetaDTO> tarjetas) {
		for (ConsumoTarjetaDTO tarjeta : tarjetas) {
			ordenarConsumosPorFecha(tarjeta);
			ordenarConsumosPorTipoConsumo(tarjeta);
		}
		ordenarTarjetasPorTitularidad(tarjetas);
	}

	/**
	 * Ordenar consumos por tipo consumo.
	 * 
	 * @param tarjeta
	 *            the tarjeta
	 */
	private void ordenarConsumosPorTipoConsumo(ConsumoTarjetaDTO tarjeta) {
		Collections.sort(tarjeta.getLineas(), new Comparator<LineaDetalleConsumoTarjetaDTO>() {
			@Override
			public int compare(LineaDetalleConsumoTarjetaDTO linea1, LineaDetalleConsumoTarjetaDTO linea2) {
				return linea1.getTipoConsumo().getPrioridad().compareTo(linea2.getTipoConsumo().getPrioridad());
			}
		});
	}

	/**
	 * Ordenar consumos por fecha.
	 * 
	 * @param tarjeta
	 *            the tarjeta
	 */
	private void ordenarConsumosPorFecha(ConsumoTarjetaDTO tarjeta) {
		Collections.sort(tarjeta.getLineas(), new Comparator<LineaDetalleConsumoTarjetaDTO>() {
			@Override
			public int compare(LineaDetalleConsumoTarjetaDTO linea1, LineaDetalleConsumoTarjetaDTO linea2) {
				if (linea1.getTipoConsumo().equals(linea2.getTipoConsumo())) {
					return linea2.getFecha().compareTo(linea1.getFecha());
				} else {
					return 0;
				}
			}
		});
	}

	/**
	 * Ordenar tarjetas por titularidad.
	 * 
	 * @param tarjetas
	 *            the tarjetas
	 */
	private void ordenarTarjetasPorTitularidad(List<ConsumoTarjetaDTO> tarjetas) {
		Collections.sort(tarjetas, new Comparator<ConsumoTarjetaDTO>() {
			@Override
			public int compare(ConsumoTarjetaDTO consumo1, ConsumoTarjetaDTO consumo2) {
				int i = consumo1.getPrioridad().compareTo(consumo2.getPrioridad());
				if (i != 0) {
					return i;
				}
				return consumo1.getPrioridad().compareTo(consumo2.getPrioridad());
			}
		});
	}

	/**
	 * Crear respuesta casuistica error.
	 *
	 * @param rtaConsumosDTO
	 *            the rta consumos DTO
	 * @param rtaPendientesDTO
	 *            the rta pendientes DTO
	 * @param marca
	 *            the marca
	 * @param mostrarStopDebit
	 *            the mostrar stop debit
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	private Respuesta<ConsumosView> crearRespuestaCasuisticaError(Respuesta<UltimosConsumosDTO> rtaConsumosDTO,
			Respuesta<UltimosConsumosDTO> rtaPendientesDTO, String marca, Boolean mostrarStopDebit)
			throws BusinessException {

		ItemMensajeRespuesta itemConsumos = obtenerItem(rtaConsumosDTO.getItemsMensajeRespuesta());
		ItemMensajeRespuesta itemPendientes = obtenerItem(rtaPendientesDTO.getItemsMensajeRespuesta());

		// Caso1: GrillaERROR(SIN_CONSUMOS_FALLA_PENDIENTES)
		if (esSinConsumos(rtaConsumosDTO.getItemsMensajeRespuesta()) && fallaPendientes(itemPendientes)) {
			return crearRespuestaWarningSinConsumosFallaPendientes(marca, mostrarStopDebit);
		}

		// Caso2: GrillaERROR(SIN_CONSUMOS_SIN_PENDIENTES)
		if (esSinConsumos(rtaConsumosDTO.getItemsMensajeRespuesta())
				&& esSinConsumos(rtaPendientesDTO.getItemsMensajeRespuesta())) {
			return crearRespuestaErrorSinConsumosSinPendientes(marca, mostrarStopDebit);
		}

		// Caso3: GrillaWARNING(OK_CONSUMOS_FALLA_PENDIENTES)
		if (esRespuestaOK(rtaConsumosDTO.getEstadoRespuesta()) && fallaPendientes(itemPendientes)) {
			iniciarTotalesPendientes(rtaConsumosDTO, false);
			return crearRespuestaWarningOKConsumosFallaPendientes(rtaConsumosDTO, marca, mostrarStopDebit);
		}

		// Caso4: GrillaWARNING(FALLA_CONSUMOS_OK_PENDIENTES)
		if (fallaConsumos(itemConsumos) && esRespuestaOK(rtaPendientesDTO.getEstadoRespuesta())) {
			iniciarTotalesConfirmados(rtaPendientesDTO, false);
			return crearRespuestaWarningFallaConsumosOKPendientes(rtaPendientesDTO, marca, mostrarStopDebit);
		}

		// Caso5: GrillaERROR(FALLA_CONSUMOS_FALLA_PENDIENTES)
		if (fallaConsumos(itemConsumos) && fallaPendientes(itemPendientes)) {
			return crearRespuestaFallaConsumosFallaPendientes(marca, mostrarStopDebit);
		}

		return crearRespuestaFallaConsumosFallaPendientes(marca, mostrarStopDebit);
	}

	/**
	 * Es grilla OK.
	 * 
	 * @param rtaConsumosDTO
	 *            the rta consumos DTO
	 * @param rtaPendientesDTO
	 *            the rta pendientes DTO
	 * @return the boolean
	 */
	private Boolean esGrillaOK(Respuesta<UltimosConsumosDTO> rtaConsumosDTO,
			Respuesta<UltimosConsumosDTO> rtaPendientesDTO) {
		return esRespuestaGrillaOK(rtaConsumosDTO, rtaPendientesDTO)
				|| esRespuestaGrillaConsumosOKYSinPendientes(rtaConsumosDTO, rtaPendientesDTO)
				|| esRespuestaGrillaSinConsumosYPendientesOK(rtaConsumosDTO, rtaPendientesDTO);
	}

	/**
	 * Es respuesta grilla sin consumos y pendientes OK.
	 * 
	 * @param rtaConsumosDTO
	 *            the rta consumos DTO
	 * @param rtaPendientesDTO
	 *            the rta pendientes DTO
	 * @return the boolean
	 */
	private Boolean esRespuestaGrillaSinConsumosYPendientesOK(Respuesta<UltimosConsumosDTO> rtaConsumosDTO,
			Respuesta<UltimosConsumosDTO> rtaPendientesDTO) {
		return esSinConsumos(rtaConsumosDTO.getItemsMensajeRespuesta())
				&& esRespuestaOK(rtaPendientesDTO.getEstadoRespuesta());
	}

	/**
	 * Es respuesta grilla consumos OK y sin pendientes.
	 * 
	 * @param rtaConsumosDTO
	 *            the rta consumos DTO
	 * @param rtaPendientesDTO
	 *            the rta pendientes DTO
	 * @return the boolean
	 */
	private Boolean esRespuestaGrillaConsumosOKYSinPendientes(Respuesta<UltimosConsumosDTO> rtaConsumosDTO,
			Respuesta<UltimosConsumosDTO> rtaPendientesDTO) {
		return esRespuestaOK(rtaConsumosDTO.getEstadoRespuesta())
				&& esSinConsumos(rtaPendientesDTO.getItemsMensajeRespuesta());
	}

	/**
	 * Es respuesta grilla OK.
	 * 
	 * @param rtaConsumosDTO
	 *            the rta consumos DTO
	 * @param rtaPendientesDTO
	 *            the rta pendientes DTO
	 * @return the boolean
	 */
	private Boolean esRespuestaGrillaOK(Respuesta<UltimosConsumosDTO> rtaConsumosDTO,
			Respuesta<UltimosConsumosDTO> rtaPendientesDTO) {
		return esRespuestaOK(rtaConsumosDTO.getEstadoRespuesta())
				&& esRespuestaOK(rtaPendientesDTO.getEstadoRespuesta());
	}

	/**
	 * Es sin consumos.
	 * 
	 * @param items
	 *            the items
	 * @return the boolean
	 */
	private Boolean esSinConsumos(List<ItemMensajeRespuesta> items) {
		ItemMensajeRespuesta item = obtenerItem(items);
		return item != null && item.getTipoError() != null
				&& item.getTipoError().trim().equals(TipoError.ERROR_SIN_CONSUMOS.getDescripcion().trim());
	}

	/**
	 * Falla consumos.
	 * 
	 * @param item
	 *            the item
	 * @return the boolean
	 */
	private Boolean fallaConsumos(ItemMensajeRespuesta item) {
		return item != null && item.getTipoError() != null
				&& item.getTipoError().equals(TipoError.ERROR_CARGA_ULTIMOS_CONSUMOS.getDescripcion().trim());
	}

	/**
	 * Falla pendientes.
	 * 
	 * @param item
	 *            the item
	 * @return the boolean
	 */
	private Boolean fallaPendientes(ItemMensajeRespuesta item) {
		return item != null && item.getTipoError() != null
				&& item.getTipoError().equals(TipoError.ERROR_CONSUMOS_PENDIENTES.getDescripcion().trim());
	}

	/**
	 * Crear respuesta OK grilla.
	 *
	 * @param marca
	 *            the marca
	 * @param cuenta
	 *            the cuenta
	 * @param listaUnificada
	 *            the lista unificada
	 * @param mostrarStopDebit
	 *            the mostrar stop debit
	 * @return the respuesta
	 */
	private Respuesta<ConsumosView> crearRespuestaOKGrilla(String marca, Cuenta cuenta,
			List<ConsumoTarjetaDTO> listaUnificada, Boolean mostrarStopDebit) {
		crearLogEstadisticaOKConsumos(marca);
		crearLogEstadisticaOKPendientes(marca);
		return respuestaFactory.crearRespuestaOk(ConsumosView.class, new ConsumosView(
				ultimosConsumosBO.generarUltimosConsumosDTO(listaUnificada, cuenta), mostrarStopDebit));
	}

	/**
	 * Crear respuesta OK grilla sin consumos con pendientes.
	 *
	 * @param marca
	 *            the marca
	 * @param cuenta
	 *            the cuenta
	 * @param pendientes
	 *            the pendientes
	 * @param mostrarStopDebit
	 *            the mostrar stop debit
	 * @return the respuesta
	 */
	private Respuesta<ConsumosView> crearRespuestaOKGrillaSinConsumosConPendientes(String marca, Cuenta cuenta,
			List<ConsumoTarjetaDTO> pendientes, Boolean mostrarStopDebit) {
		crearLogEstadisticaOKConsumos(marca);
		crearLogEstadisticaOKPendientes(marca);
		return respuestaFactory.crearRespuestaOk(ConsumosView.class,
				new ConsumosView(ultimosConsumosBO.generarUltimosConsumosDTO(pendientes, cuenta), mostrarStopDebit));
	}

	/**
	 * Crear respuesta warning sin consumos falla pendientes.
	 *
	 * @param marca
	 *            the marca
	 * @param mostrarStopDebit
	 *            the mostrar stop debit
	 * @return the respuesta
	 */
	private Respuesta<ConsumosView> crearRespuestaWarningSinConsumosFallaPendientes(String marca,
			Boolean mostrarStopDebit) {
		crearLogEstadisticaOKConsumos(marca);
		crearLogEstadisticaErrorPendientes(marca);
		ConsumosView view = new ConsumosView(new ArrayList<ConsumoTarjetaView>(), null, EstadoRespuesta.ERROR, false,
				mostrarStopDebit);
		return crearRespuestaErrorSinConsumosYFallaPendientes(ConsumosView.class, view);
	}

	/**
	 * Crear respuesta warning OK consumos falla pendientes.
	 *
	 * @param rtaConsumosDTO
	 *            the rta consumos DTO
	 * @param marca
	 *            the marca
	 * @param mostrarStopDebit
	 *            the mostrar stop debit
	 * @return the respuesta
	 */
	private Respuesta<ConsumosView> crearRespuestaWarningOKConsumosFallaPendientes(
			Respuesta<UltimosConsumosDTO> rtaConsumosDTO, String marca, Boolean mostrarStopDebit) {
		crearLogEstadisticaOKConsumos(marca);
		crearLogEstadisticaErrorPendientes(marca);
		ConsumosView view = new ConsumosView(rtaConsumosDTO.getRespuesta(), mostrarStopDebit);
		view.setEstadoRespuestaConsumosPendientes(EstadoRespuesta.ERROR);
		view.setMuestraTarjetasConCabecera(true);
		return crearRespuestaWarningConConsumosErrorEnPendientes(ConsumosView.class, view);
	}

	/**
	 * Crear respuesta error sin consumos sin pendientes. Grilla
	 * WARNING(SIN_CONSUMOS_SIN_PENDIENTES)
	 *
	 * @param marca
	 *            the marca
	 * @param mostrarStopDebit
	 *            the mostrar stop debit
	 * @return the respuesta
	 */
	private Respuesta<ConsumosView> crearRespuestaErrorSinConsumosSinPendientes(String marca,
			Boolean mostrarStopDebit) {
		crearLogEstadisticaOKConsumos(marca);
		crearLogEstadisticaOKPendientes(marca);
		ConsumosView view = new ConsumosView(new ArrayList<ConsumoTarjetaView>(), null, EstadoRespuesta.OK, false,
				mostrarStopDebit);
		return crearRespuestaErrorSinConsumos(ConsumosView.class, view);
	}

	/**
	 * Crear respuesta warning error consumos con consumos pendientes.Grilla
	 * WARNING(FALLA_CONSUMOS_OK_PENDIENTES)
	 *
	 * @param rtaPendientesDTO
	 *            the rta pendientes DTO
	 * @param marca
	 *            the marca
	 * @param mostrarStopDebit
	 *            the mostrar stop debit
	 * @return the respuesta
	 */
	private Respuesta<ConsumosView> crearRespuestaWarningFallaConsumosOKPendientes(
			Respuesta<UltimosConsumosDTO> rtaPendientesDTO, String marca, Boolean mostrarStopDebit) {
		crearLogEstadisticaErrorConsumos(marca);
		crearLogEstadisticaOKPendientes(marca);
		ConsumosView view = new ConsumosView(rtaPendientesDTO.getRespuesta(), mostrarStopDebit);
		view.setEstadoRespuestaConsumos(EstadoRespuesta.ERROR);
		view.setEstadoRespuestaConsumosPendientes(EstadoRespuesta.OK);
		view.setMuestraTarjetasConCabecera(true);
		return crearRespuestaWarningErrorConsumosConConsumosPendientes(ConsumosView.class, view);
	}

	/**
	 * Crear respuesta error generico. GrillaERROR(FALLA_CONSUMOS_FALLA_PENDIENTES)
	 *
	 * @param marca
	 *            the marca
	 * @param mostrarStopDebit
	 *            the mostrar stop debit
	 * @return the respuesta
	 */
	private Respuesta<ConsumosView> crearRespuestaFallaConsumosFallaPendientes(String marca, Boolean mostrarStopDebit) {
		crearLogEstadisticaErrorConsumos(marca);
		crearLogEstadisticaErrorPendientes(marca);
		return crearRespuestaFallaConsumosFallaPendientes(mostrarStopDebit);
	}

	/**
	 * Crear respuesta falla consumos falla pendientes.
	 *
	 * @param mostrarStopDebit
	 *            the mostrar stop debit
	 * @return the respuesta
	 */
	private Respuesta<ConsumosView> crearRespuestaFallaConsumosFallaPendientes(Boolean mostrarStopDebit) {
		ConsumosView view = new ConsumosView(new ArrayList<ConsumoTarjetaView>(), null, EstadoRespuesta.ERROR, false,
				mostrarStopDebit);
		return crearRespuestaErrorGenerico(ConsumosView.class, view);
	}

	/**
	 * Obtener item.
	 * 
	 * @param list
	 *            the list
	 * @return the item mensaje respuesta
	 */
	private ItemMensajeRespuesta obtenerItem(List<ItemMensajeRespuesta> list) {
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * Crear log estadistica OK consumos.
	 * 
	 * @param marca
	 *            the marca
	 */
	private void crearLogEstadisticaOKConsumos(String marca) {
		crearLogEstadistica(EstadisticasTarjetasUtil.getCodigoEstadisticaUltimosConsumos(marca),
				EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
	}

	/**
	 * Crear log estadistica OK pendientes.
	 * 
	 * @param marca
	 *            the marca
	 */
	private void crearLogEstadisticaOKPendientes(String marca) {
		crearLogEstadistica(EstadisticasTarjetasUtil.getCodigoEstadisticaConsumosPendientes(marca),
				EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
	}

	/**
	 * Crear log estadistica error consumos.
	 * 
	 * @param marca
	 *            the marca
	 */
	private void crearLogEstadisticaErrorConsumos(String marca) {
		crearLogEstadistica(EstadisticasTarjetasUtil.getCodigoEstadisticaUltimosConsumos(marca),
				EstadisticasTarjetasUtil.getCodigoEstadisticaError());
	}

	/**
	 * Crear log estadistica error pendientes.
	 * 
	 * @param marca
	 *            the marca
	 */
	private void crearLogEstadisticaErrorPendientes(String marca) {
		crearLogEstadistica(EstadisticasTarjetasUtil.getCodigoEstadisticaConsumosPendientes(marca),
				EstadisticasTarjetasUtil.getCodigoEstadisticaError());
	}

	/**
	 * Crear log estadistica.
	 * 
	 * @param codTransaccion
	 *            the cod transaccion
	 * @param codigoError
	 *            the codigo error
	 */
	private void crearLogEstadistica(String codTransaccion, String codigoError) {
		estadisticaManager.add(codTransaccion, codigoError);
	}

	/**
	 * Es respuesta OK.
	 * 
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @return the boolean
	 */
	private Boolean esRespuestaOK(EstadoRespuesta estadoRespuesta) {
		return estadoRespuesta.equals(EstadoRespuesta.OK);
	}

	/**
	 * Crear respuesta error sin consumos Y falla pendientes. Caso de no poseer
	 * consumos, y en que el servicio de ultimos consumos pendientes de confirmacion
	 * falla, se debe visualizar los mensajes - 1197 --> No posee consumos. - 1203
	 * --> Servicio no recupera últimos consumos pendientes de confirmación.
	 * 
	 * @param respuestaClass
	 *            the respuesta class
	 * @param data
	 *            the data
	 * @return the respuesta
	 */
	private Respuesta<ConsumosView> crearRespuestaErrorSinConsumosYFallaPendientes(Class<ConsumosView> respuestaClass,
			ConsumosView data) {
		ErrorTarjetasEnum sinConsumos = ErrorTarjetasEnum.SIN_CONSUMOS;
		ErrorTarjetasEnum errorPendientes = ErrorTarjetasEnum.ERROR_SERVICIO_CONSUMOS_PENDIENTES;
		List<DatoItemMensaje> datos = new ArrayList<DatoItemMensaje>();
		datos.add(new DatoItemMensaje(sinConsumos.getCodigoMensaje(), sinConsumos.getTipoError(),
				sinConsumos.getTag().getDescripcionTag()));
		datos.add(new DatoItemMensaje(errorPendientes.getCodigoMensaje(), errorPendientes.getTipoError(),
				errorPendientes.getTag().getDescripcionTag()));
		return respuestaFactory.crearRespuestaError(respuestaClass, data, datos);
	}

	/**
	 * Crear respuesta warning. Tiene consumos. Falla el servicio de consumos
	 * pendientes. Se debe mostrar mensaje de error y la grilla con consumos. Tanto
	 * para una o mas tarjetas. El totalizador se muestra con los importes de
	 * ultimos consumos.
	 * 
	 * @param respuestaClass
	 *            the respuesta class
	 * @param data
	 *            the data
	 * @return the respuesta
	 */

	private Respuesta<ConsumosView> crearRespuestaWarningConConsumosErrorEnPendientes(
			Class<ConsumosView> respuestaClass, ConsumosView data) {
		ErrorTarjetasEnum error = ErrorTarjetasEnum.ERROR_SERVICIO_CONSUMOS_PENDIENTES;
		List<DatoItemMensaje> datos = new ArrayList<DatoItemMensaje>();
		datos.add(new DatoItemMensaje(error.getCodigoMensaje(), error.getTipoError(),
				error.getTag().getDescripcionTag()));
		return respuestaFactory.crearRespuestaWarning(datos, data);
	}

	/**
	 * Crear respuesta error sin consumos. No tiene Consumos. No tiene pendientes.
	 * Se muestra la grilla con mensaje de que no tiene consumos. 1197 ERROR
	 * 
	 * @param respuestaClass
	 *            the respuesta class
	 * @param data
	 *            the data
	 * @return the respuesta
	 */
	private Respuesta<ConsumosView> crearRespuestaErrorSinConsumos(Class<ConsumosView> respuestaClass,
			ConsumosView data) {
		ErrorTarjetasEnum sinConsumos = ErrorTarjetasEnum.SIN_CONSUMOS;
		List<DatoItemMensaje> datos = new ArrayList<DatoItemMensaje>();
		datos.add(new DatoItemMensaje(sinConsumos.getCodigoMensaje(), sinConsumos.getTipoError(),
				sinConsumos.getTag().getDescripcionTag()));
		return respuestaFactory.crearRespuestaError(respuestaClass, data, datos);
	}

	/**
	 * Crear respuesta warning. Falla el servicio de ultimos consumos. Tiene
	 * consumos pendientes. Se debe mostrar mensaje de error y la grilla con
	 * consumos pendiente de confirmacion. Tanto para una o mas tarjetas. El
	 * totalizador se muestra con un guión ( - ) 1198 WARNING
	 * 
	 * @param respuestaClass
	 *            the respuesta class
	 * @param data
	 *            the data
	 * @return the respuesta
	 */
	private Respuesta<ConsumosView> crearRespuestaWarningErrorConsumosConConsumosPendientes(
			Class<ConsumosView> respuestaClass, ConsumosView data) {
		ErrorTarjetasEnum error = ErrorTarjetasEnum.ERROR_SERVICIO_ULTIMOS_CONSUMOS;
		List<DatoItemMensaje> datos = new ArrayList<DatoItemMensaje>();
		datos.add(new DatoItemMensaje(error.getCodigoMensaje(), error.getTipoError(),
				error.getTag().getDescripcionTag()));
		return respuestaFactory.crearRespuestaWarning(datos, data);
	}

	/**
	 * Crear respuesta error total en grilla de ultimos consumos.Falla el servicio
	 * de ultimos consumos. Falla los consumos pendientes. Se muestra mensaje de
	 * error total de la grilla 1293
	 * 
	 * @param respuestaClass
	 *            the respuesta class
	 * @param data
	 *            the data
	 * @return the respuesta
	 */
	private Respuesta<ConsumosView> crearRespuestaErrorGenerico(Class<ConsumosView> respuestaClass, ConsumosView data) {
		ErrorTarjetasEnum error = ErrorTarjetasEnum.ERROR_GENERICO_ULTIMOS_CONSUMOS;
		List<DatoItemMensaje> datos = new ArrayList<DatoItemMensaje>();
		datos.add(new DatoItemMensaje(error.getCodigoMensaje(), error.getTipoError(),
				error.getTag().getDescripcionTag()));
		return respuestaFactory.crearRespuestaError(respuestaClass, data, datos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.web.manager.
	 * UltimosConsumosManager#descargarExcelUltimosConsumos()
	 */
	@Override
	public Respuesta<ReporteView> descargarExcelUltimosConsumos() {
		Respuesta<Reporte> reporte = obtenerReporte(sesionParametros.getTarjetasView());
		Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.DESCARGA_EXCEL_ULTIMOS_CONSUMOS,
					EstadisticasTarjetasUtil.getCodigoEstadisticaOk());
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.DESCARGA_EXCEL_ULTIMOS_CONSUMOS,
					EstadisticasTarjetasUtil.getCodigoEstadisticaError());
		}
		return respuestaView;
	}

	/**
	 * Obtener reporte.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @return the respuesta
	 */
	private Respuesta<Reporte> obtenerReporte(TarjetasView tarjetas) {
		Cliente cliente = sesionCliente.getCliente();
		List<ConsumoTarjetaDTO> ultimosConsumosConfirmados = new ArrayList<ConsumoTarjetaDTO>();
		List<ConsumoTarjetaDTO> ultimosConsumosPendientes = new ArrayList<ConsumoTarjetaDTO>();
		cargarTodosLosConsumos(ultimosConsumosConfirmados, ultimosConsumosPendientes, tarjetas);
		MovimientosTarjetaExcel2 movimientos = new MovimientosTarjetaExcel2(ultimosConsumosConfirmados,
				ultimosConsumosPendientes);
		if (movimientos.getRecargable()) {
			Respuesta<Reporte> reporte = ultimosConsumosBO.obtenerReporte(movimientos, REPORT_NAME_RECARGABLE, cliente);
			if (reporte.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				reporte.getRespuesta().setNombre(REPORT_NAME + ".xls");
			}
			return reporte;
		}
		return ultimosConsumosBO.obtenerReporte(movimientos, REPORT_NAME, cliente);
	}

	/**
	 * Cargar todos los consumos.
	 *
	 * @param ultimosConsumosConfirmados
	 *            the ultimos consumos confirmados
	 * @param ultimosConsumosPendientes
	 *            the ultimos consumos pendientes
	 * @param tarjetas
	 *            the tarjetas
	 */
	private void cargarTodosLosConsumos(List<ConsumoTarjetaDTO> ultimosConsumosConfirmados,
			List<ConsumoTarjetaDTO> ultimosConsumosPendientes, TarjetasView tarjetas) {
		for (ConsumoTarjetaView consumo : tarjetas.getConsumoTarjetaDefault()) {
			if (consumo.getConsumoPesos() != null || consumo.getConsumoDolares() != null) {
				ConsumoTarjetaDTO consumoDTO = crearUltimosConsumosDTO(consumo);
				if ((BigDecimal.ZERO.compareTo(consumoDTO.getConsumoDolares()) != 0)
						|| (BigDecimal.ZERO.compareTo(consumoDTO.getConsumoPesos()) != 0)) {
					ultimosConsumosConfirmados.add(consumoDTO);
				}
			}
			if (consumo.getConsumoPesosPendientes() != null || consumo.getConsumoDolaresPendientes() != null) {
				ConsumoTarjetaDTO consumoDTO = crearConsumosPendientesDTO(consumo);
				if ((consumoDTO.getConsumoDolares() != null
						&& BigDecimal.ZERO.compareTo(consumoDTO.getConsumoDolares()) != 0)
						|| (consumoDTO.getConsumoPesos() != null
								&& BigDecimal.ZERO.compareTo(consumoDTO.getConsumoPesos()) != 0)) {
					ultimosConsumosPendientes.add(consumoDTO);
				}
			}
		}
	}

	/**
	 * Crear consumos pendientes DTO.
	 *
	 * @param consumo
	 *            the consumo
	 * @return the consumo tarjeta DTO
	 */
	private ConsumoTarjetaDTO crearConsumosPendientesDTO(ConsumoTarjetaView consumo) {
		ConsumoTarjetaDTO consumoDTO = new ConsumoTarjetaDTO();
		consumoDTO.setLineas(crearLineasUltimoConsumoDTO(consumo.getLineas(), true));
		consumoDTO.setMarca(consumo.getMarca());
		consumoDTO.setNumero(consumo.getNumero());
		consumoDTO.setConsumoPesos(
				consumo.getConsumoPesosPendientes() != null && consumo.getConsumoPesosPendientes() != "-"
						? ISBANStringUtils.convertirABigDecimal(consumo.getConsumoPesosPendientes())
						: null);
		consumoDTO.setConsumoPesosPendientes(
				consumo.getConsumoPesosPendientes() != null && consumo.getConsumoPesosPendientes() != "-"
						? ISBANStringUtils.convertirABigDecimal(consumo.getConsumoPesosPendientes())
						: null);
		consumoDTO.setConsumoDolares(
				consumo.getConsumoDolaresPendientes() != null && consumo.getConsumoDolaresPendientes() != "-"
						? ISBANStringUtils.convertirABigDecimal(consumo.getConsumoDolaresPendientes())
						: null);
		consumoDTO.setConsumoDolaresPendientes(
				consumo.getConsumoDolaresPendientes() != null && consumo.getConsumoDolaresPendientes() != "-"
						? ISBANStringUtils.convertirABigDecimal(consumo.getConsumoDolaresPendientes())
						: null);
		consumoDTO.setIsTitular(consumo.getIsTitular());
		consumoDTO.setIsAdicional(consumo.getIsAdicional());
		consumoDTO.setNombreAdicional(consumo.getNombreAdicional());
		return consumoDTO;
	}

	/**
	 * Crear ultimos consumos DTO.
	 *
	 * @param consumo
	 *            the consumo
	 * @return the consumo tarjeta DTO
	 */
	private ConsumoTarjetaDTO crearUltimosConsumosDTO(ConsumoTarjetaView consumo) {
		ConsumoTarjetaDTO consumoDTO = new ConsumoTarjetaDTO();
		consumoDTO.setLineas(crearLineasUltimoConsumoDTO(consumo.getLineas(), false));
		consumoDTO.setMarca(consumo.getMarca());
		consumoDTO.setNumero(consumo.getNumero());
		consumoDTO.setConsumoPesos(
				consumo.getConsumoPesos() != null ? ISBANStringUtils.convertirABigDecimal(consumo.getConsumoPesos())
						: null);
		consumoDTO.setConsumoDolares(
				consumo.getConsumoDolares() != null ? ISBANStringUtils.convertirABigDecimal(consumo.getConsumoDolares())
						: null);
		consumoDTO.setIsTitular(consumo.getIsTitular());
		consumoDTO.setIsTarjetaRecargable(consumo.getMarca().contains("Tarjeta Recargable"));
		consumoDTO.setIsAdicional(consumo.getIsAdicional());
		consumoDTO.setNombreAdicional(consumo.getNombreAdicional());
		return consumoDTO;
	}

	/**
	 * Crear lineas ultimo consumo DTO.
	 *
	 * @param lineasUltimoConsumo
	 *            the lineas ultimo consumo
	 * @param isPendiente
	 *            the is pendiente
	 * @return the list
	 */
	private List<LineaDetalleConsumoTarjetaDTO> crearLineasUltimoConsumoDTO(
			List<LineaDetalleConsumoTarjetaView> lineasUltimoConsumo, boolean isPendiente) {
		List<LineaDetalleConsumoTarjetaDTO> listaLineas = new ArrayList<LineaDetalleConsumoTarjetaDTO>();
		for (LineaDetalleConsumoTarjetaView linea : lineasUltimoConsumo) {
			LineaDetalleConsumoTarjetaDTO lineaDTO = new LineaDetalleConsumoTarjetaDTO();
			lineaDTO.setFecha(ISBANStringUtils.formatearFecha(linea.getFecha()));
			lineaDTO.setFechaExcel(linea.getFecha());
			if (linea.getTieneCuota()) {
				lineaDTO.setDescripcion(linea.getDescripcion() + " Cuota: " + linea.getCuota());
			} else {
				lineaDTO.setDescripcion(linea.getDescripcion());
			}
			lineaDTO.setCodigoEstablecimiento(linea.getCodigoEstablecimiento());
			lineaDTO.setNroReferencia(linea.getNroReferencia());
			lineaDTO.setImportePesos(
					linea.getImportePesos() != null ? ISBANStringUtils.convertirABigDecimal(linea.getImportePesos())
							: null);
			lineaDTO.setImporteDolares(
					linea.getImporteDolares() != null ? ISBANStringUtils.convertirABigDecimal(linea.getImporteDolares())
							: null);
			lineaDTO.setEsPendienteConfirmacion(
					linea.getEsPendienteConfirmacion() != null && linea.getEsPendienteConfirmacion());
			if (isPendiente && lineaDTO.getEsPendienteConfirmacion()) {
				listaLineas.add(lineaDTO);
			}
			if (!isPendiente && !lineaDTO.getEsPendienteConfirmacion()) {
				listaLineas.add(lineaDTO);
			}
		}
		return listaLineas;
	}

	@Override
	public Respuesta<ReporteView> exportarUltimosConsumosPDF() {
		Respuesta<Reporte> reporte = obtenerReportePDF(sesionParametros.getTarjetasView());
		Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
		}
		return respuestaView;
	}

	private Respuesta<Reporte> obtenerReportePDF(TarjetasView tarjetas) {
		List<ConsumoTarjetaDTO> ultimosConsumosConfirmados = new ArrayList<ConsumoTarjetaDTO>();
		List<ConsumoTarjetaDTO> ultimosConsumosPendientes = new ArrayList<ConsumoTarjetaDTO>();
		cargarTodosLosConsumos(ultimosConsumosConfirmados, ultimosConsumosPendientes, tarjetas);
		MovimientosTarjetaExcel2 movimientos = new MovimientosTarjetaExcel2(ultimosConsumosConfirmados,
				ultimosConsumosPendientes);
		return reporteBO.obtenerComprobantePDF(new DetalleUltimosConsumosPDF(movimientos));
	}
}
