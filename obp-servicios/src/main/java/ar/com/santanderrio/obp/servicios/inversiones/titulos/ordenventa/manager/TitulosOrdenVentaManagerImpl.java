/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.comun.JaxbUtils;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.inversiones.entities.Mensaje;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CuentasPDC;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.bo.TituloOrdenVentaBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.CompraVentaTitulosValoresDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConsultaDeTenenciaResponseDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConsultaSuscripcionSaldoPDCDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ComprobanteOrdenVenta;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.DatosTenencia;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.InfoTitulo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.IngresoOrdenVentaInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.TituloView;

/**
 * The Class TitulosOrdenVentaManagerImpl.
 */
@Component
public class TitulosOrdenVentaManagerImpl implements TitulosOrdenVentaManager {

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The titulo orden venta BO. */
	@Autowired
	private TituloOrdenVentaBO tituloOrdenVentaBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;
	
	@Autowired
	private LegalBO legalBO;

	/** The Constant ERROR_NO_HAY_TITULOS. */
	private static final String ERROR_NO_HAY_TITULOS = "NO_HAY_TITULOS";

	/** The Constant ERROR_GENERICO. */
	private static final String ERROR_GENERICO = "ERROR_GENERICO";

	/** The Constant PESOS. */
	private static final String PESOS = "Pesos";

	/** The Constant PASAR_SIN_DISCLAIMER. */
	private static final int PASAR_SIN_DISCLAIMER = 0;

	/** The Constant PASAR_CON_DISCLAIMER. */
	private static final int PASAR_CON_DISCLAIMER = 1;

	/** The Constant ADHERIDO_A_PDC. */
	private static final String ADHERIDO_A_PDC = "AD";

	/** The Constant SIMULACION. */
	private static final String SIMULACION = "S";

	/** The Constant DIRECTA. */
	private static final String DIRECTA = "D";

	/** The Constant SIGNO_PESOS. */
	private static final String SIGNO_PESOS = "$ ";

	/** The Constant SIGNO_DOLAR. */
	private static final String SIGNO_DOLAR = "u$s";

	/** The Constant MENSAJE_LOG_ERROR. */
	private static final String MENSAJE_LOG_ERROR = "No hay titulos para vender";

	/** The Constant SIN_COTIZACION. */
	private static final String SIN_COTIZACION = "Sin cotización";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TitulosOrdenVentaManagerImpl.class);

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager.TitulosOrdenVentaManager#ingresoOrdenVenta(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.IngresoOrdenVentaInView)
	 */
	@Override
	public Respuesta<List<TituloView>> ingresoOrdenVenta(IngresoOrdenVentaInView ingresoView) {

		Cliente cliente = sesionCliente.getCliente();
		sessionParametros.setComprobanteOrdenVenta(null);
		List<Cuenta> listaCuentasTitulos = obtenerCuentasTituloPorBanca(cliente, ingresoView.getEsBancaPrivada());
		Respuesta<List<TituloView>> respuesta = new Respuesta<List<TituloView>>();
		try {
			ConsultaDeTenenciaResponseDTO consultaDeTenenciaResponseDTO = tituloOrdenVentaBO
					.consultarCuentasTitulosWebService(sesionCliente.getCliente(), listaCuentasTitulos,
							ingresoView.getEsBancaPrivada());
			List<TituloView> listaTitulosView = crearListaTitulosView(listaCuentasTitulos,
					ingresoView.getEsBancaPrivada(), consultaDeTenenciaResponseDTO);
			if (CollectionUtils.isEmpty(listaTitulosView)) {
				LOGGER.error(MENSAJE_LOG_ERROR);
				respuesta = respuestaFactory.crearRespuestaError("", ERROR_NO_HAY_TITULOS, null);
				respuesta.getItemsMensajeRespuesta().get(0).setTipoError(ERROR_NO_HAY_TITULOS);
				estadisticaIngresoOrdenVenta(ingresoView.getEsBancaPrivada(), true);
			} else {
				respuesta = respuestaFactory.crearRespuestaOk(listaTitulosView);
				estadisticaIngresoOrdenVenta(ingresoView.getEsBancaPrivada(), false);
			}
		} catch (BusinessException e) {
			estadisticaIngresoOrdenVenta(ingresoView.getEsBancaPrivada(), true);
			String mensajeErrorNuevo = "10429";
			respuesta = respuestaFactory.crearRespuestaError("", ERROR_GENERICO, mensajeErrorNuevo);
			respuesta.getItemsMensajeRespuesta().get(0).setTipoError(ERROR_GENERICO);
		}

		return respuesta;
	}

	/**
	 * Estadistica ingreso orden venta.
	 *
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @param error
	 *            the error
	 */
	private void estadisticaIngresoOrdenVenta(Boolean esBancaPrivada, Boolean error) {
		estadisticaManager.add(
				esBancaPrivada ? EstadisticasConstants.INGRESO_FUNCION_VENTA_TITULOS_VALORES_BPRIV
						: EstadisticasConstants.INGRESO_FUNCION_VENTA_TITULOS_VALORES,
				error ? EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR : EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/**
	 * Obtener cuentas titulo por banca.
	 *
	 * @param cliente
	 *            the cliente
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @return the list
	 */
	private List<Cuenta> obtenerCuentasTituloPorBanca(Cliente cliente, Boolean esBancaPrivada) {
		List<Cuenta> listaCuentasTitulo = new ArrayList<Cuenta>();
		if (esBancaPrivada) {
			for (CuentaBancaPrivada cuentaBP : cliente.getCuentasBancaPrivada()) {
				listaCuentasTitulo.add(cuentaBP.getCuentaTitulo());
			}
		} else {
			listaCuentasTitulo = cliente.getCuentasRetail();
		}
		return listaCuentasTitulo;
	}

	/**
	 * Crear lista titulos view.
	 *
	 * @param listaCuentasTitulos
	 *            the lista cuentas titulos
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @param consultaDeTenenciaResponseDTO
	 *            the consulta de tenencia response DTO
	 * @return the list
	 * @throws BusinessException
	 *             the business exception
	 */
	private List<TituloView> crearListaTitulosView(List<Cuenta> listaCuentasTitulos, Boolean esBancaPrivada,
			ConsultaDeTenenciaResponseDTO consultaDeTenenciaResponseDTO) throws BusinessException {
		List<TituloView> listaTitulosView = new ArrayList<TituloView>();
		if (CollectionUtils.isEmpty(consultaDeTenenciaResponseDTO.getDatos().getListaTenencia())) {
			return listaTitulosView;
		}
		for (Cuenta cuenta : listaCuentasTitulos) {
			TituloView tituloView = obtenerTituloView(cuenta, consultaDeTenenciaResponseDTO, esBancaPrivada);
			if (CollectionUtils.isNotEmpty(tituloView.getTitulosParaVenta())) {
				listaTitulosView.add(tituloView);
			}
		}
		return listaTitulosView;
	}

	/**
	 * Obtener titulo view.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param consultaDeTenenciaResponseDTO
	 *            the consulta de tenencia response DTO
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @return the titulo view
	 * @throws BusinessException
	 *             the business exception
	 */
	private TituloView obtenerTituloView(Cuenta cuenta, ConsultaDeTenenciaResponseDTO consultaDeTenenciaResponseDTO,
			Boolean esBancaPrivada) throws BusinessException {
		TituloView tituloView = new TituloView();
		// si es banca privada no se muestra en el selector los datos de la
		// cuenta
		// titulo , sino los de la cuenta operativa asociada a la cuenta titulo
		if (esBancaPrivada) {
			Cuenta cuentaOperativa = buscarCuentaOperativa(sesionCliente.getCliente(), cuenta);
			tituloView.setNumeroCuentaOperativa(
					new IdentificacionCuenta(cuentaOperativa.getNroSucursal(), cuentaOperativa.getNroCuentaProducto())
							.toString());
		}
		tituloView.setTitulares(tituloOrdenVentaBO.consultarTitulares(cuenta.getIntervinientes()));
		String numeroCuentaSinFormato = ISBANStringUtils.sacarCerosYBlancosIzq(cuenta.getNroCuentaProducto());
		String numeroCuentaTituloFormateado = numeroCuentaSinFormato.substring(0, numeroCuentaSinFormato.length() - 1)
				+ "/" + numeroCuentaSinFormato.substring(numeroCuentaSinFormato.length() - 1,
						numeroCuentaSinFormato.length());
		tituloView.setNumeroCuentaTitulo(numeroCuentaTituloFormateado);
		tituloView.setTitulosParaVenta(obtenerListaTitulosParaVenta(cuenta, consultaDeTenenciaResponseDTO));
		return tituloView;
	}

	/**
	 * Buscar cuenta operativa.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuentaTitulo
	 *            the cuenta titulo
	 * @return the cuenta
	 * @throws BusinessException
	 *             the business exception
	 */
	private Cuenta buscarCuentaOperativa(Cliente cliente, Cuenta cuentaTitulo) throws BusinessException {
		for (CuentaBancaPrivada cuentaBP : cliente.getCuentasBancaPrivada()) {
			if (StringUtils.equals(cuentaBP.getCuentaTitulo().getNroCuentaProducto(),
					cuentaTitulo.getNroCuentaProducto())) {
				return cuentaBP.getCuentaOperativa();
			}
		}
		throw new BusinessException();
	}

	/**
	 * Obtener lista titulos para venta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param consultaDeTenenciaResponseDTO
	 *            the consulta de tenencia response DTO
	 * @return the list
	 */
	private List<InfoTitulo> obtenerListaTitulosParaVenta(Cuenta cuenta,
			ConsultaDeTenenciaResponseDTO consultaDeTenenciaResponseDTO) {
		List<InfoTitulo> listaTitulosParaVenta = new ArrayList<InfoTitulo>();
		for (DatosTenencia datosTenencia : consultaDeTenenciaResponseDTO.getDatos().getListaTenencia()) {
			if (cuenta.getNroCuentaProducto().contains(datosTenencia.getCtaTitulo().toString())) {
				InfoTitulo tituloParaVenta = new InfoTitulo();
				tituloParaVenta.setDescripcionEspecie(datosTenencia.getDescripcionEspecie());
				tituloParaVenta.setCodigoEspecie(datosTenencia.getCodigoAmigable());
				tituloParaVenta
						.setTipo(ISBANStringUtils.formateoStringPrimeraLetraMayuscula(datosTenencia.getInstrumento()));
				tituloParaVenta
						.setTenenciaNominalNegociable(formatearTenencia(datosTenencia.getTenenciaNominalNegociable()));
				tituloParaVenta.setCodigoRossi(datosTenencia.getCodigoEspecieRossi());
				tituloParaVenta.setPlazo(String.valueOf(datosTenencia.getPlazo()));
				tituloParaVenta.setTipoEspecie(datosTenencia.getTipoEspecie());
				listaTitulosParaVenta.add(tituloParaVenta);
			}
		}
		return listaTitulosParaVenta;
	}

	/**
	 * Formatear tenencia.
	 *
	 * @param tenenciaNegociable
	 *            the tenencia negociable
	 * @return the string
	 */
	public static String formatearTenencia(BigDecimal tenenciaNegociable) {
		DecimalFormatSymbols sym = new DecimalFormatSymbols();
		sym.setDecimalSeparator(',');
		sym.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat(",##0.00#", sym);
		df.setMaximumFractionDigits(7);
		df.setRoundingMode(RoundingMode.DOWN);
		return df.format(tenenciaNegociable);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager.TitulosOrdenVentaManager#confirmacionOrdenVenta(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosInView)
	 */
	@Override
	public Respuesta<ConfirmacionVentaTitulosView> confirmacionOrdenVenta(
			ConfirmacionVentaTitulosInView confirmacionInView) {

		try {
			ConfirmacionVentaTitulosView confirmacion = new ConfirmacionVentaTitulosView();
			CompraVentaTitulosValoresDTO compraVentaTitulosValoresDTO = tituloOrdenVentaBO
					.ejecutarMetodoCompraVentaTitulosValores(sesionCliente.getCliente(), confirmacionInView,
							SIMULACION);

			if (compraVentaTitulosValoresDTO.getTipoError() != null) {
				grabarEstadisticaErrorConfirmacionOrdenVenta(confirmacionInView);
				return obtenerRespuestaErrorConfirmacionOrdenVenta(compraVentaTitulosValoresDTO);
			}

			sessionParametros.setIdCumplimientoVentaTitulos(
					compraVentaTitulosValoresDTO.getDatos().getRespuestaSimulacionERI().getIdEvaluacion());

			if (PASAR_SIN_DISCLAIMER == compraVentaTitulosValoresDTO.getDatos().getRespuestaSimulacionERI()
					.getTipoDisclaimer()) {
				confirmacion.setMostrarERI(false);
				confirmacion.setPermiteOperar(true);
			} else {
				Mensaje mensajeDisclaimer = JaxbUtils.transformarXmlAObject(
						compraVentaTitulosValoresDTO.getDatos().getRespuestaSimulacionERI().getDisclaimer(),
						Mensaje.class);
				if (PASAR_CON_DISCLAIMER == compraVentaTitulosValoresDTO.getDatos().getRespuestaSimulacionERI()
						.getTipoDisclaimer()) {
					confirmacion.setMostrarERI(true);
					confirmacion.setPermiteOperar(true);
					confirmacion.setLegalesERI(mensajeDisclaimer.getDisclaimer().getDisclaimers());
				} else {
					confirmacion.setMostrarERI(true);
					confirmacion.setPermiteOperar(false);
					confirmacion.setLegalesERI(mensajeDisclaimer.getDisclaimer().getDisclaimers());
				}
			}
			
			confirmacion.setBonificacion(compraVentaTitulosValoresDTO.getDatos().getDatosConsultaComisionResponse().getBonificacion());
			confirmacion.setComisionOriginal(compraVentaTitulosValoresDTO.getDatos().getDatosConsultaComisionResponse().getComisionOriginal());
			confirmacion.setComision(compraVentaTitulosValoresDTO.getDatos().getDatosConsultaComisionResponse().getComision());
			confirmacion.setInformacion(compraVentaTitulosValoresDTO.getDatos().getDatosConsultaComisionResponse().getInformacion());
			confirmacion.setTieneBonificacion(compraVentaTitulosValoresDTO.getDatos().getDatosConsultaComisionResponse().getTieneBonificacion());
			revisarValoresIVA(confirmacion, compraVentaTitulosValoresDTO);
			confirmacion.setLegales(compraVentaTitulosValoresDTO.getDatos().getLegales());
			String fechaLiquidacion = confirmacionInView.getFechaLiquidacion().substring(0, 10);
			confirmacion.setFechaLiquidacionFormateada(fechaLiquidacion.substring(8, fechaLiquidacion.length()) + "/"
					+ fechaLiquidacion.substring(5, 7) + "/" + fechaLiquidacion.substring(0, 4));
			ConsultaSuscripcionSaldoPDCDTO consultaSuscripcionSaldoPDCDTO = tituloOrdenVentaBO
					.consultarDatosSuscripcionSaldoPDC(sesionCliente.getCliente(), confirmacionInView);

			String cuentaTitulo = confirmacionInView.getCuentaTitulo().replaceAll("\\/", "");

			if (!consultaSuscripcionSaldoPDCDTO.getFalloServicio()) {
				for (CuentasPDC cuentaPDC : consultaSuscripcionSaldoPDCDTO.getDatos().getListaCuentas()) {
					if (cuentaTitulo.equals(cuentaPDC.getCuentaTitulos())
							&& ADHERIDO_A_PDC.equals(cuentaPDC.getIndicadorPDC())) {
						confirmacion.setPoderDeCompra(true);
						ComprobanteOrdenVenta comprobante = new ComprobanteOrdenVenta();
						comprobante.setAdhesionPoderCompra("Activo");
						sessionParametros.setComprobanteOrdenVenta(comprobante);
					}
				}
			}
			estadisticaManager.add(
					confirmacionInView.getEsBancaPrivada()
							? EstadisticasConstants.TITULOS_VALORES_ORDEN_VENTA_BANCA_PRIVADA_CONFIRMACION
							: EstadisticasConstants.TITULOS_VALORES_ORDEN_VENTA_BANCA_PERSONAL_CONFIRMACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

			return respuestaFactory.crearRespuestaOk(confirmacion);
		} catch (BusinessException e) {
			grabarEstadisticaErrorConfirmacionOrdenVenta(confirmacionInView);
			return respuestaFactory.crearRespuestaError("", ERROR_GENERICO,
					CodigoMensajeConstantes.ORDEN_VENTA_TITULOS_ERROR_SERVICIO);
		} catch (JAXBException e) {
			grabarEstadisticaErrorConfirmacionOrdenVenta(confirmacionInView);
			return respuestaFactory.crearRespuestaError("", ERROR_GENERICO,
					CodigoMensajeConstantes.ORDEN_VENTA_TITULOS_ERROR_SERVICIO);
		}
	}

	/**
	 * Obtener respuesta error confirmacion orden venta.
	 *
	 * @param compraVentaTitulosValoresDTO
	 *            the compra venta titulos valores DTO
	 * @return the respuesta
	 */
	private Respuesta<ConfirmacionVentaTitulosView> obtenerRespuestaErrorConfirmacionOrdenVenta(
			CompraVentaTitulosValoresDTO compraVentaTitulosValoresDTO) {
		if (compraVentaTitulosValoresDTO.getTipoError().equals(TipoError.FUERADEHORARIO)) {
			return respuestaFactory.crearRespuestaError("", TipoError.FUERADEHORARIO,
					CodigoMensajeConstantes.COMPRAVTA_ORDENES_FUERA_HORARIO);
		} else if (compraVentaTitulosValoresDTO.getTipoError().equals(TipoError.ERROR_SALDO_INSUFICIENTE)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SALDO_INSUFICIENTE,
					CodigoMensajeConstantes.COMPRAVTA_ORDENES_SIN_SALDO);
		} else if (compraVentaTitulosValoresDTO.getTipoError().equals(TipoError.CUENTA_SIN_OPERAR_MAS_180_DIAS)) {
			return respuestaFactory.crearRespuestaError("", TipoError.CUENTA_SIN_OPERAR_MAS_180_DIAS,
					CodigoMensajeConstantes.CUENTA_SIN_OPERAR_MAS_180_DIAS);
		} else if (compraVentaTitulosValoresDTO.getTipoError().equals(TipoError.EXCEDE_LIMITE_DE_CANAL)) {
			return respuestaFactory.crearRespuestaError("", TipoError.EXCEDE_LIMITE_DE_CANAL,
					CodigoMensajeConstantes.EXCEDE_LIMITE_DE_CANAL);
		} else if (compraVentaTitulosValoresDTO.getTipoError().equals(TipoError.CUENTA_NO_FIRMADA)) {
			return respuestaFactory.crearRespuestaError("", TipoError.CUENTA_NO_FIRMADA,
					CodigoMensajeConstantes.CUENTA_NO_FIRMADA);
		} else if (compraVentaTitulosValoresDTO.getTipoError().equals(TipoError.CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA)) {
			return respuestaFactory.crearRespuestaError("", TipoError.CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA,
					CodigoMensajeConstantes.CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA);
		} else if (compraVentaTitulosValoresDTO.getTipoError().equals(TipoError.SE_ENCUENTRA_FUERA_DEL_TUNEL)) {
			return respuestaFactory.crearRespuestaError("", TipoError.SE_ENCUENTRA_FUERA_DEL_TUNEL,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (compraVentaTitulosValoresDTO.getTipoError().equals(TipoError.CUENTA_TITULOS_BLOQUEADA)) {
			return respuestaFactory.crearRespuestaError("", TipoError.CUENTA_TITULOS_BLOQUEADA,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (compraVentaTitulosValoresDTO.getTipoError().equals(TipoError.TENENCIA_BLOQUEADA)) {
			return respuestaFactory.crearRespuestaError("", TipoError.TENENCIA_BLOQUEADA,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (compraVentaTitulosValoresDTO.getTipoError().equals(TipoError.CUENTA_ESPECIAL_EMPLEADOS_100_ACCIONES)) {
			return respuestaFactory.crearRespuestaError("", TipoError.CUENTA_ESPECIAL_EMPLEADOS_100_ACCIONES,
					CodigoMensajeConstantes.CUENTA_ESPECIAL_EMPLEADOS_100_ACCIONES);
		} else if (compraVentaTitulosValoresDTO.getTipoError().equals(TipoError.SUPERA_TIEMPO_DE_ESPERA)) {
			return respuestaFactory.crearRespuestaError("", TipoError.SUPERA_TIEMPO_DE_ESPERA,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (compraVentaTitulosValoresDTO.getTipoError().equals(TipoError.NO_CUMPLE_CONDICIONES_VALIDACIONES_DATOS_INGRESADOS)) {
			return respuestaFactory.crearRespuestaError("", TipoError.NO_CUMPLE_CONDICIONES_VALIDACIONES_DATOS_INGRESADOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (compraVentaTitulosValoresDTO.getTipoError().equals(TipoError.OTROS_COMPRA_VENTA_CUENTA_TITULOS)) {
			return respuestaFactory.crearRespuestaError("", TipoError.OTROS_COMPRA_VENTA_CUENTA_TITULOS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (compraVentaTitulosValoresDTO.getTipoError().equals(TipoError.ERROR_PROCESO_ORDEN_VENTA)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_PROCESO_ORDEN_VENTA,
					CodigoMensajeConstantes.ERROR_TITULO_VENTA_PROCESO_COM_7001);
		} else if (compraVentaTitulosValoresDTO.getTipoError().equals(TipoError.TENENCIA_NO_DISPONIBLE_PARA_OPERAR)) {
			return respuestaFactory.crearRespuestaError("", TipoError.NO_CORRESPONDE_REINTENTO,
					CodigoMensajeConstantes.ERROR_TITULO_VENTA_NO_OPERABLE);
		}
		return respuestaFactory.crearRespuestaError("", ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/**
	 * Revisar valores IVA.
	 *
	 * @param confirmacion
	 *            the confirmacion
	 * @param compraVentaTitulosValoresDTO
	 *            the compra venta titulos valores DTO
	 */
	private void revisarValoresIVA(ConfirmacionVentaTitulosView confirmacion,
			CompraVentaTitulosValoresDTO compraVentaTitulosValoresDTO) {

		if (compraVentaTitulosValoresDTO.getDatos().getComision() != null) {
			confirmacion.setComisiones(SIGNO_PESOS + ISBANStringUtils.formatearConComaYDosDecimales(
					String.valueOf(compraVentaTitulosValoresDTO.getDatos().getComision())));
		}

		if (compraVentaTitulosValoresDTO.getDatos().getIva() != null) {
			confirmacion.setIvaSobreComision(SIGNO_PESOS + ISBANStringUtils
					.formatearConComaYDosDecimales(String.valueOf(compraVentaTitulosValoresDTO.getDatos().getIva())));
		}

		if (compraVentaTitulosValoresDTO.getDatos().getDerechos() != null) {
			confirmacion.setDerechoDeMercado(SIGNO_PESOS + ISBANStringUtils.formatearConComaYDosDecimales(
					String.valueOf(compraVentaTitulosValoresDTO.getDatos().getDerechos())));
		}

		if (compraVentaTitulosValoresDTO.getDatos().getImpuestos() != null) {
			confirmacion.setIvaSobreDerechoDeMercado(SIGNO_PESOS + ISBANStringUtils.formatearConComaYDosDecimales(
					String.valueOf(compraVentaTitulosValoresDTO.getDatos().getImpuestos())));
		}
	}

	/**
	 * Grabar estadistica error confirmacion orden venta.
	 *
	 * @param confirmacionInView
	 *            the confirmacion in view
	 */
	private void grabarEstadisticaErrorConfirmacionOrdenVenta(ConfirmacionVentaTitulosInView confirmacionInView) {
		if (confirmacionInView.getEsBancaPrivada()) {
			estadisticaManager.add(EstadisticasConstants.TITULOS_VALORES_ORDEN_VENTA_BANCA_PRIVADA_CONFIRMACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else {
			estadisticaManager.add(EstadisticasConstants.TITULOS_VALORES_ORDEN_VENTA_BANCA_PERSONAL_CONFIRMACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager.TitulosOrdenVentaManager#ejecutarOrdenVenta(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosInView)
	 */
	@Override
	public Respuesta<ConfirmacionVentaTitulosView> ejecutarOrdenVenta(
			ConfirmacionVentaTitulosInView confirmacionInView) {

		Respuesta<ConfirmacionVentaTitulosView> respuesta = new Respuesta<ConfirmacionVentaTitulosView>();
		try {
			confirmacionInView.setIdCumplimiento(sessionParametros.getIdCumplimientoVentaTitulos());
			CompraVentaTitulosValoresDTO compraVentaTitulosValoresDTO = tituloOrdenVentaBO
					.ejecutarMetodoCompraVentaTitulosValores(sesionCliente.getCliente(), confirmacionInView, DIRECTA);

			if (compraVentaTitulosValoresDTO.getTipoError() != null) {
				grabarEstadisticaEjecucionOrdenVenta(confirmacionInView,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return obtenerRespuestaErrorEjecutarOrdenVenta(confirmacionInView,
						compraVentaTitulosValoresDTO.getTipoError());
			}

			ConfirmacionVentaTitulosView feedback = new ConfirmacionVentaTitulosView();

			feedback.setFechaHoraOperacion(armarFechaHoraActual());
			feedback.setNumeroOrden(String.valueOf(compraVentaTitulosValoresDTO.getDatos().getNumeroOrden()));
			feedback.setNumeroComprobante(String.valueOf(compraVentaTitulosValoresDTO.getDatos().getNumeroOperacion()));
			feedback.setLegales(compraVentaTitulosValoresDTO.getDatos().getLegales());

			String mensajeFeedback = mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_VENTA_TITULOS_OK).getMensaje();
			mensajeFeedback = MessageFormat.format(mensajeFeedback, confirmacionInView.getNombreEspecie());
			feedback.setMensajeOK(mensajeFeedback);
			revisarValoresIVA(feedback, compraVentaTitulosValoresDTO);

			grabarEstadisticaEjecucionOrdenVenta(confirmacionInView, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			if (!PESOS.equals(confirmacionInView.getMonedaOperacion())) {
				estadisticaManager.add(EstadisticasConstants.VENTA_TITULOS_CREDITO_TASA_CERO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}
			insertarDatosEnComprobante(compraVentaTitulosValoresDTO, confirmacionInView, feedback);

			respuesta = respuestaFactory.crearRespuestaOk(feedback);

		} catch (BusinessException e) {
			grabarEstadisticaEjecucionOrdenVenta(confirmacionInView, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return obtenerRespuestaErrorEjecutarOrdenVenta(confirmacionInView, null);
		}

		return respuesta;
	}

	/**
	 * Obtener respuesta error ejecutar orden venta.
	 *
	 * @param confirmacionInView
	 *            the confirmacion in view
	 * @param tipoErrorDTO
	 *            the tipo error DTO
	 * @return the respuesta
	 */
	private Respuesta<ConfirmacionVentaTitulosView> obtenerRespuestaErrorEjecutarOrdenVenta(
			ConfirmacionVentaTitulosInView confirmacionInView, TipoError tipoErrorDTO) {
		if (TipoError.TENENCIA_NO_DISPONIBLE_PARA_OPERAR.equals(tipoErrorDTO)) {
			return respuestaFactory.crearRespuestaError("", TipoError.NO_CORRESPONDE_REINTENTO, CodigoMensajeConstantes.ERROR_TITULO_VENTA_NO_OPERABLE);
		} else if (TipoError.ERROR_PROCESO_ORDEN_VENTA.equals(tipoErrorDTO)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_PROCESO_ORDEN_VENTA, CodigoMensajeConstantes.ERROR_TITULO_VENTA_PROCESO_COM_7001);
		}
		String mensajeFeedbackError = obtenerMensajeFeedbackError(confirmacionInView, tipoErrorDTO);
		TipoError tipoError = !sessionParametros.getContador().permiteReintentar() ? TipoError.ERROR_REINTENTOS_AGOTADOS
				: TipoError.WARNING_REINTENTOS;
		Boolean correspondeReintento = revisarSiCorrespondeReintentar(tipoErrorDTO);
		Respuesta<ConfirmacionVentaTitulosView> respuesta = respuestaFactory.crearRespuestaErrorPersonalizado(
				ConfirmacionVentaTitulosView.class, mensajeFeedbackError, correspondeReintento ? tipoError.getDescripcion() : "NO_CORRESPONDE_REINTENTO");
		if (tipoErrorDTO != null) {
			respuesta.getItemsMensajeRespuesta().get(0).setDetalleTipoError(tipoErrorDTO.getDescripcion());
		}
		return respuesta;
	}

	private Boolean revisarSiCorrespondeReintentar(TipoError tipoErrorDTO) {

		return TipoError.CUENTA_NO_FIRMADA.equals(tipoErrorDTO)
			|| TipoError.CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA.equals(tipoErrorDTO)
			|| TipoError.SUPERA_TIEMPO_DE_ESPERA.equals(tipoErrorDTO)
			|| TipoError.NO_CUMPLE_CONDICIONES_VALIDACIONES_DATOS_INGRESADOS.equals(tipoErrorDTO)
			|| TipoError.FUERADEHORARIO.equals(tipoErrorDTO) 
			|| TipoError.ERROR_SALDO_INSUFICIENTE.equals(tipoErrorDTO);
	}
	
	/**
	 * Obtener mensaje feedback error.
	 *
	 * @param confirmacionInView
	 *            the confirmacion in view
	 * @param tipoErrorDTO
	 *            the tipo error DTO
	 * @return the string
	 */
	private String obtenerMensajeFeedbackError(ConfirmacionVentaTitulosInView confirmacionInView,
			TipoError tipoErrorDTO) {
		if (tipoErrorDTO != null) {
			if (tipoErrorDTO.equals(TipoError.FUERADEHORARIO)) {
				return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.COMPRAVTA_ORDENES_FUERA_HORARIO)
						.getMensaje();
			} else if (tipoErrorDTO.equals(TipoError.ERROR_SALDO_INSUFICIENTE)) {
				return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.COMPRAVTA_ORDENES_SIN_SALDO)
						.getMensaje();
			} else if (tipoErrorDTO.equals(TipoError.CUENTA_SIN_OPERAR_MAS_180_DIAS)) {
				return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CUENTA_SIN_OPERAR_MAS_180_DIAS)
						.getMensaje();
			} else if (tipoErrorDTO.equals(TipoError.EXCEDE_LIMITE_DE_CANAL)) {
				return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.EXCEDE_LIMITE_DE_CANAL)
						.getMensaje();
			} else if (tipoErrorDTO.equals(TipoError.CUENTA_NO_FIRMADA)) {
				return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CUENTA_NO_FIRMADA)
						.getMensaje();
			} else if (tipoErrorDTO.equals(TipoError.CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA)) {
				return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CAMBIOS_SIGNIFICATIVOS_PRECIO_REFERENCIA)
						.getMensaje();
			} else if (tipoErrorDTO.equals(TipoError.CUENTA_ESPECIAL_EMPLEADOS_100_ACCIONES)) {
				return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CUENTA_ESPECIAL_EMPLEADOS_100_ACCIONES)
						.getMensaje();
			} 
		}
		String mensajeFeedbackError = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_VENTA_TITULOS_ERROR).getMensaje();
		return MessageFormat.format(mensajeFeedbackError, confirmacionInView.getNombreEspecie());
	}

	/**
	 * Insertar datos en comprobante.
	 *
	 * @param compraVentaTitulosValoresDTO
	 *            the compra venta titulos valores DTO
	 * @param confirmacionInView
	 *            the confirmacion in view
	 * @param feedback
	 *            the feedback
	 */
	private void insertarDatosEnComprobante(CompraVentaTitulosValoresDTO compraVentaTitulosValoresDTO,
			ConfirmacionVentaTitulosInView confirmacionInView, ConfirmacionVentaTitulosView feedback) {

		ComprobanteOrdenVenta comprobante;
		if (sessionParametros.getComprobanteOrdenVenta() != null) {
			comprobante = sessionParametros.getComprobanteOrdenVenta();
		} else {
			comprobante = new ComprobanteOrdenVenta();
		}

		comprobante.setNombreEspecie(
				confirmacionInView.getNombreEspecie().trim() + " - " + confirmacionInView.getCodigoEspecie());
		comprobante.setCuentaTitulos(confirmacionInView.getCuentaTitulo());
		comprobante.setTipo(confirmacionInView.getInstrumento());
		comprobante.setPlazoLiquidacion(confirmacionInView.getPlazo());
		String moneda = PESOS.equals(confirmacionInView.getMonedaOperacion()) ? SIGNO_PESOS : SIGNO_DOLAR;
		if (StringUtils.equals(SIN_COTIZACION, confirmacionInView.getCotizacion())) {
			comprobante.setPrecioReferencia(confirmacionInView.getCotizacion());
		} else {
			comprobante.setPrecioReferencia(moneda + " " + confirmacionInView.getCotizacion());
		}

		comprobante.setCantidadNominales(formatearCotizacionLimite(confirmacionInView.getCantidadTitulo()));
		if (!StringUtils.equals(SIN_COTIZACION, confirmacionInView.getCotizacion())) {
			comprobante.setFechaReferencia(
					confirmacionInView.getFechaReferencia() + " - " + confirmacionInView.getHoraReferencia());
		} else {
			comprobante.setFechaReferencia(null);
		}
		comprobante.setMonedaLiquidacion(confirmacionInView.getMonedaOperacion());
		String fechaCortada = confirmacionInView.getFechaLiquidacion().substring(0, 10);
		comprobante.setFechaLiquidacion(fechaCortada.substring(8, fechaCortada.length()) + "/"
				+ fechaCortada.substring(5, 7) + "/" + fechaCortada.substring(0, 4));
		if (confirmacionInView.getCotizacionLimite() == null || confirmacionInView.getCotizacionLimite().isEmpty()) {
			comprobante.setPrecioLimite("0");
		}
		comprobante.setPrecioLimite(moneda + " " + formatearCotizacionLimite(confirmacionInView.getCotizacionLimite()));
		comprobante.setNumeroCuentaDestino(confirmacionInView.getNumeroCuenta());
		comprobante.setTipoDeCuentaDestino(
				TipoCuenta.fromAbreviatura(confirmacionInView.getTipoCuenta()).getDescripcion());
		comprobante.setNumeroOrden(feedback.getNumeroOrden());
		comprobante.setComprobante(
				feedback.getNumeroComprobante() == null ? feedback.getNumeroOrden() : feedback.getNumeroComprobante());
		comprobante.setLegales(compraVentaTitulosValoresDTO.getDatos().getLegales());
		comprobante.setTituloComprobante("Comprobante Orden Venta Titulos Valores");

		if (!StringUtils.isEmpty(feedback.getComisiones()) && !ISBANStringUtils.esCero(feedback.getComisiones())) {
			comprobante.setComisiones(feedback.getComisiones());
		}else {
			comprobante.setComisiones(null);
		}

		if (!StringUtils.isEmpty(feedback.getIvaSobreComision()) && !ISBANStringUtils.esCero(feedback.getIvaSobreComision())) {
			comprobante.setIvaComision(feedback.getIvaSobreComision());
		}else{
			comprobante.setIvaComision(null);
		}

		if (!StringUtils.isEmpty(feedback.getDerechoDeMercado())&& !ISBANStringUtils.esCero(feedback.getDerechoDeMercado()) ) {
			comprobante.setDerechoMercado(feedback.getDerechoDeMercado());
		}else{
			comprobante.setDerechoMercado(null);
		}

		if (!StringUtils.isEmpty(feedback.getIvaSobreDerechoDeMercado())&& !ISBANStringUtils.esCero(feedback.getIvaSobreDerechoDeMercado())) {
			comprobante.setIvaDerechoMercado(feedback.getIvaSobreDerechoDeMercado());
		}else{
			comprobante.setIvaDerechoMercado(null);
		}

		comprobante.setFechaActual(armarFechaHoraActual());

		sessionParametros.setComprobanteOrdenVenta(comprobante);

	}

	/**
	 * Grabar estadistica ejecucion orden venta.
	 *
	 * @param confirmacionInView
	 *            the confirmacion in view
	 * @param estado
	 *            the estado
	 */
	private void grabarEstadisticaEjecucionOrdenVenta(ConfirmacionVentaTitulosInView confirmacionInView,
			String estado) {

		if (confirmacionInView.getEsBancaPrivada()) {
			estadisticaManager.add(EstadisticasConstants.FEEDBACK_VENTA_TITULOS_BANCA_PRIVADA, estado);
		} else {
			estadisticaManager.add(EstadisticasConstants.FEEDBACK_VENTA_TITULOS_BANCA_PERSONAL, estado);
		}

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager.TitulosOrdenVentaManager#descargarComprobanteOrdenVentaPDF(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.IngresoOrdenVentaInView)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteOrdenVentaPDF(IngresoOrdenVentaInView descargaInView)
			throws IOException {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;

		ComprobanteOrdenVenta comprobante = sessionParametros.getComprobanteOrdenVenta();
		comprobante.setPrecioLimite(descargaInView.getPrecioLimite());

		reporte = reporteBO.obtenerComprobantePDF(comprobante);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(
					descargaInView.getEsBancaPrivada() ? EstadisticasConstants.DESCARGA_PDF_ORDEN_VENTA_TITULOS_PRIVADA
							: EstadisticasConstants.DESCARGA_PDF_ORDEN_VENTA_TITULOS_RETAIL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(
					descargaInView.getEsBancaPrivada() ? EstadisticasConstants.DESCARGA_PDF_ORDEN_VENTA_TITULOS_PRIVADA
							: EstadisticasConstants.DESCARGA_PDF_ORDEN_VENTA_TITULOS_RETAIL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/**
	 * Armar fecha hora actual.
	 *
	 * @return the string
	 */
	private String armarFechaHoraActual() {

		Date fecha = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formateadorHora = new SimpleDateFormat("HH:mm:ss");

		return formateador.format(fecha) + " · " + formateadorHora.format(fecha);

	}

	/**
	 * Formatear cotizacion limite.
	 *
	 * @param cotizacion
	 *            the cotizacion
	 * @return the string
	 */
	private String formatearCotizacionLimite(String cotizacion) {

		String cotizacionFormateada;

		if (cotizacion.contains(".")) {
			cotizacion = cotizacion.replaceAll("\\.", ",");
			String[] partes = cotizacion.split(",");
			String entero = partes[0];
			String decimal = partes[1];
			if (decimal.length() < 2) {
				decimal = decimal + "0";
			}
			cotizacionFormateada = entero + "," + decimal;
		} else {
			cotizacionFormateada = cotizacion + ",00";
		}

		return cotizacionFormateada;

	}

}