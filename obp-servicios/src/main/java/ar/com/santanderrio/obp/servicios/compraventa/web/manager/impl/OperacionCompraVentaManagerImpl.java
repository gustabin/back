/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.manager.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.compraventa.bo.OperacionClienteCompraBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.OperacionClienteVentaBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.dto.ComprobanteCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.VentaUSDDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.LimiteCompraVentaUSDEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosOperacion;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ConfirmarClienteCompraEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ConfirmarClienteCompraVenta;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ConfirmarClienteVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.Cotizacion;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.ConfiguracionCompraVentaManager;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.OperacionCompraVentaManager;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.CompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.CompraVentaBuilder;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.comun.MonedaEspecieEnum;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.queue.bo.QueueSTBO;
import ar.com.santanderrio.obp.servicios.queue.entities.QueueSTOperations;
import ar.com.santanderrio.obp.servicios.queue.entities.TurnStatesEnum;
import ar.com.santanderrio.obp.servicios.queue.utils.QueueSTUtils;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;


/**
 * The Class OperacionCompraVentaManagerImpl.
 *
 * @author sabrina.cis
 */
@Component
public class OperacionCompraVentaManagerImpl extends CompraVentaDolaresManagerImpl
		implements OperacionCompraVentaManager {

	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OperacionCompraVentaManagerImpl.class);

	/** The Constant TIPO_PESOS. */
	private static final String TIPO_PESOS = "$";

	/** The Constant TIPO_USD. */
	private static final String TIPO_USD = "u$s";

	/** The Constant MSJ_INFO_CREANDO_HASH_ATRIBUTOS. */
	private static final String MSJ_INFO_CREANDO_HASH_ATRIBUTOS = "Creando hash de los atributos...";

	/** The operacion cliente compra BO. */
	@Autowired
	private OperacionClienteCompraBO operacionClienteCompraBO;

	/** The operacion cliente compra BO. */
	@Autowired
	private OperacionClienteVentaBO operacionClienteVentaBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The configuracion compra venta manager. */
	@Autowired
	private ConfiguracionCompraVentaManager configuracionCompraVentaManager;

	/** The mensaje manager. */
	@Autowired
	protected MensajeManager mensajeManager;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The alta comprobante. */
	@Autowired
	private ScompBO altaComprobante;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The cuenta banca privada BO. */
	@Autowired
	private CuentasBancaPrivadaBO cuentaBancaPrivadaBO;

	/** The ModuloPermiso BO. */
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	/** The queue ST BO. */
	@Autowired
	private QueueSTBO queueSTBO;
	
	@Autowired
	private ClienteManager clienteManager;
	
	
	/** The autentificacion manager. */
	@Autowired
	private AutentificacionManager autentificacionManager;
	
	/** The valor desafio. */
	@Value("${TRJCOORD.OPERAINDISTINTO.VENTAUSD}")
	private String valorDesafioVentaUSD;

	/**
	 * Continuar del paso uno para la compra de dolares.
	 *
	 * @param entity
	 *            the entity
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ComprobanteCompraVentaView> operacionClienteCompra(ConfirmarClienteCompraEntity entity) {
		validarHashDeLaVistaEnSesion(entity, true);
		try {
			iniciarContador(true);
			validarCuentasSeleccionadas(entity.getNumeroCuentaOrigen(), entity.getNumeroCuentaDestino());
			ParametrosOperacion parametrosOperacion = crearParametrosClienteCompra(getCliente(), entity);
			Respuesta<ComprobanteCompraVentaView> res = crearRespuestaClienteCompra(parametrosOperacion);
			if (res.getEstadoRespuesta().equals(EstadoRespuesta.WARNING) && !res.isRespuestaVacia()) {
				Cotizacion cotizacion = new Cotizacion();
				cotizacion.setIsDolar(false);
				cotizacion.setCuentaOrigen(entity.getNumeroCuentaOrigen());
				Respuesta<CompraVentaDolarView> respuesta = configuracionCompraVentaManager
						.obtenerCotizacionParaCompra(cotizacion);
				res.getRespuesta().setCotizacion(BigDecimal.valueOf(respuesta.getRespuesta().getCotizacion()));
			}
			if (res.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
				ItemMensajeRespuesta mensajeFondos = new ItemMensajeRespuesta(mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_FONDO_COMUN_INV_CYV_USD).getMensaje());
				res.add(mensajeFondos);
				obtenerMensajeReintentar(res.getItemsMensajeRespuesta().get(0), true);
			}
			if(EstadoRespuesta.OK.equals(res.getEstadoRespuesta())) {
				res.getRespuesta().setMensajeFondoCumunInv(mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_FONDO_COMUN_INV_CYV_USD).getMensaje());
			}
			return res;
		} catch (Exception e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			Respuesta<ComprobanteCompraVentaView> res =  casuistica.crearRespuestaErroneaInformacionNoDisponible();
			ItemMensajeRespuesta mensajeFondos = new ItemMensajeRespuesta(mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_FONDO_COMUN_INV_CYV_USD).getMensaje());
			res.add(mensajeFondos);
			return res;
		}
	}

	/**
	 * Validar hash de la vista en sesion.
	 *
	 * @param compraVentaView
	 *            the compra venta view
	 * @param esCompra
	 *            the es compra
	 */
	private void validarHashDeLaVistaEnSesion(ConfirmarClienteCompraVenta compraVentaView, boolean esCompra) {
		String hashViewInput = HashUtils.obtenerHash(crearMapaDeCompraVenta(compraVentaView, esCompra));
		HashUtils.compareHash(sesionParametros.getValidacionHash(), hashViewInput);

	}

	/**
	 * Crear mapa de compra venta.
	 *
	 * @param compraVentaView
	 *            the compra venta view
	 * @param esCompra
	 *            the es compra
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeCompraVenta(ConfirmarClienteCompraVenta compraVentaView, boolean esCompra) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("cotizacion", compraVentaView.getCotizacion().replace(".", ""));
		mapaAtributos.put("numeroCuentaDestino", compraVentaView.getNumeroCuentaDestino());
		mapaAtributos.put("numeroCuentaOrigen", compraVentaView.getNumeroCuentaOrigen());
		mapaAtributos.put("isDolar", String.valueOf(compraVentaView.getIsDolar()));
		if ((compraVentaView.getIsDolar() && esCompra) || (!compraVentaView.getIsDolar() && !esCompra)) {
			mapaAtributos.put("importe",
					ISBANStringUtils.formatearConComaYDosDecimales(compraVentaView.getMontoCache()));
		} else {
			mapaAtributos.put("importe", compraVentaView.getImporteDebito());
		}
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Continuar del paso uno para la compra de dolares.
	 *
	 * @param confirmarClienteVenta
	 *            the confirmar cliente venta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ComprobanteCompraVentaView> operacionClienteVende(
			ConfirmarClienteVentaEntity confirmarClienteVenta) {
		validarHashDeLaVistaEnSesion(confirmarClienteVenta, false);
		try {
			iniciarContador(false);
			validarCuentasSeleccionadas(confirmarClienteVenta.getNumeroCuentaOrigen(),
					confirmarClienteVenta.getNumeroCuentaDestino());
			ParametrosOperacion parametrosOperacion = crearParametrosClienteVende(getCliente(), confirmarClienteVenta);
			if(sesionParametros.getImporteUSDAcumCpr() == null) {
				// LLAMA A NUEVO SERVICIO QUE CHECKEA LIMITE Y AGREGAR CUSTOM FACTS
				LimiteCompraVentaUSDEntity limiteOperacionCompraVenta = operacionClienteVentaBO.limiteMaximoCompraVentaUSD(sesionCliente.getCliente(), parametrosOperacion, esSucursalBancaPrivada(confirmarClienteVenta), true);
				if(limiteOperacionCompraVenta == null) {
					return casuistica.crearRespuestaErroneaInformacionNoDisponible();
				}
				sesionParametros.setImporteUSDAcumCpr(limiteOperacionCompraVenta.getImporteDolarAumCpr());
			}
			// CALLING RSA
			VentaUSDDTO ventaDTO = obtenerVentaUSDTO(parametrosOperacion, sesionParametros.getImporteUSDAcumCpr());
			
			Respuesta<ComprobanteCompraVentaView> resultado = validarOperacionRSA(confirmarClienteVenta, ventaDTO);
			
			if(!EstadoRespuesta.OK.equals(resultado.getEstadoRespuesta())) {
				return resultado;
			} else {
				//ALLOW
				sesionParametros.setImporteUSDAcumCpr(null);
				return impactarOperacion(parametrosOperacion);
			}
			
		} catch (Exception e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaErroneaInformacionNoDisponible();
		}
	}
	
	/**
	 * Valida desafio
	 * @param confirmarClienteVenta
	 * @param ventaDTO 
	 * @return
	 * @throws CompraVentaDolaresException 
	 */
	private Respuesta<ComprobanteCompraVentaView> validarOperacionRSA(
			ConfirmarClienteVentaEntity confirmarClienteVenta, VentaUSDDTO ventaDTO) throws CompraVentaDolaresException {
		AutentificacionDTO autentificacionDTO;

		Respuesta<ComprobanteCompraVentaView> estadoDesafio = autentificacionManager
				.verificarEstadoDesafio(confirmarClienteVenta.getDesafio(), Integer.parseInt(valorDesafioVentaUSD));
		if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = confirmarClienteVenta.getDesafio();	
		} else if (EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = cargarAutentificacionDTO(confirmarClienteVenta, ventaDTO);
		} else {
			return estadoDesafio;
		}

		Respuesta<AutentificacionDTO> respEjecucionMetodoAutentificacion = autentificacionManager.ejecutarValidacionRSA(autentificacionDTO);
		
		if(EstadoRespuesta.ERROR.equals(respEjecucionMetodoAutentificacion.getEstadoRespuesta())) { // DENY
			return respuestaFactory.crearRespuestaError("", TipoError.OPERACION_NO_PERMITIDA, CodigoMensajeConstantes.VENTA_USD_DENY_RSA);
		}
		
//		estadoDesafio.setEstadoRespuesta(respEjecucionMetodoAutentificacion.getEstadoRespuesta());
		ComprobanteCompraVentaView respuesta = new ComprobanteCompraVentaView();
		
		respuesta.setDesafio(respEjecucionMetodoAutentificacion.getRespuesta());
		
		respuesta.setDesafio(cargarCodigosEstadística(respEjecucionMetodoAutentificacion.getRespuesta()));
		respuesta.setCotizacion(ISBANStringUtils.convertirABigDecimal(confirmarClienteVenta.getCotizacion()));
		respuesta.setMontoCache(confirmarClienteVenta.getMontoCache());
		respuesta.setNumeroCuentaOrigen(confirmarClienteVenta.getNumeroCuentaOrigen());
		respuesta.setNumeroCuentaDestino(confirmarClienteVenta.getNumeroCuentaDestino());
		respuesta.setImporteDebito(confirmarClienteVenta.getImporteDebito());
		respuesta.setImporteCredito(confirmarClienteVenta.getImporteCredito());
		respuesta.setDolar(confirmarClienteVenta.getIsDolar());
		respuesta.setEsCompra(false);
		respuesta.setEsVenta(true);
//		estadoDesafio.setRespuesta(respuesta);
//		estadoDesafio.setItemMensajeRespuesta(respEjecucionMetodoAutentificacion.getItemsMensajeRespuesta());
//		return estadoDesafio;
		
		return respuestaFactory.transformar(respuesta, respEjecucionMetodoAutentificacion);
	}

	/**
	 * Cargamos datos para el desafío
	 * @param confirmarClienteVenta
	 * @param ventaDTO
	 * @return
	 */
	private AutentificacionDTO cargarAutentificacionDTO(ConfirmarClienteVentaEntity confirmarClienteVenta, VentaUSDDTO ventaDTO) {
		
		Integer operacion = Integer.parseInt(valorDesafioVentaUSD);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setOperacion(operacion);
        autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
                new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));
        
        autentificacionDTO.setCodigoEstadisticaSolicitudBanelco(EstadisticasConstants.SOLUCITUD_BANELCO_VENTA_USD);
		autentificacionDTO.setCodigoEstadisticaValidacionBanelco(EstadisticasConstants.VALIDACION_BANELCO_VENTA_USD);
		autentificacionDTO.setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.SOLUCITUD_COORDENADAS_VENTA_USD);
		autentificacionDTO.setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.VALIDACION_COORDENADAS_VENTA_USD);
		autentificacionDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOLUCITUD_TOKEN_VENTA_USD);
		autentificacionDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.VALIDACION_TOKEN_VENTA_USD);
		
        autentificacionDTO.setRsaDTO(ventaDTO);

        return autentificacionDTO;
	}
	
	/**
	 * Carga codigos de estadistica
	 * @param autentificacionDTO
	 * @return
	 */
	private AutentificacionDTO cargarCodigosEstadística(AutentificacionDTO autentificacionDTO) {

		// Seteo de estadisticas de RSA
		
		autentificacionDTO.setCodigoEstadisticaSolicitudBanelco(EstadisticasConstants.SOLUCITUD_BANELCO_VENTA_USD);
		autentificacionDTO.setCodigoEstadisticaValidacionBanelco(EstadisticasConstants.VALIDACION_BANELCO_VENTA_USD);
		autentificacionDTO.setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.SOLUCITUD_COORDENADAS_VENTA_USD);
		autentificacionDTO.setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.VALIDACION_COORDENADAS_VENTA_USD);
		autentificacionDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOLUCITUD_TOKEN_VENTA_USD);
		autentificacionDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.VALIDACION_TOKEN_VENTA_USD);
		return autentificacionDTO;
	}
	
	/**
	 * Realiza la operacion
	 * @param parametrosOperacion
	 * @return
	 * @throws CompraVentaDolaresException 
	 */
	private Respuesta<ComprobanteCompraVentaView> impactarOperacion(ParametrosOperacion parametrosOperacion) throws CompraVentaDolaresException{
		
		Respuesta<ComprobanteCompraVentaView> res = crearRespuestaClienteVende(parametrosOperacion);
		if (res.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			obtenerMensajeReintentar(res.getItemsMensajeRespuesta().get(0), false);
		}
		return res;
	}

	/**
	 * Verifica si la sucursal es de banca privada
	 * @param confirmarClienteVenta
	 * @return
	 */
	private static boolean esSucursalBancaPrivada(ConfirmarClienteVentaEntity confirmarClienteVenta) {
		
		IdentificacionCuenta idOrigen = new IdentificacionCuenta(confirmarClienteVenta.getNumeroCuentaOrigen());
		
		return CuentasBancaPrivadaUtil.isCuentaBP(idOrigen.getNroSucursal());
	}
	
	/**
	 * Obtiene los datos necesarios para validar la operación contra RSA
	 * @param parametrosOperacion
	 * @return
	 */
	private VentaUSDDTO obtenerVentaUSDTO(ParametrosOperacion parametrosOperacion, String importeDolarAumCpr) {
		
		VentaUSDDTO ventaDto = new VentaUSDDTO();
		
		ventaDto.setFechaEjecucion(FechaUtils.obtenerFechaActual());
		ventaDto.setFechaAcreditacion(FechaUtils.obtenerFechaActual());
		ventaDto.setImporte(parametrosOperacion.getImporteCredito());
		ventaDto.setImporteUSD(parametrosOperacion.getImporteDebito());
		ventaDto.setMoneda(MonedaEspecieEnum.ARS.getCodigo());
		ventaDto.setMonedaAltair(MonedaEspecieEnum.USD.getCodigo());
		ventaDto.setNroCuenta(TransferenciaUtil.formatearNumeroCuenta(parametrosOperacion.getCuentaOrigen()));
		ventaDto.setSaldoCuentaOrigen(parametrosOperacion.getCuentaOrigen().obtenerSaldoDolares());
		ventaDto.setTipoCuentaEnum(parametrosOperacion.getCuentaOrigen().getTipoCuentaEnum());
		ventaDto.setCbu(parametrosOperacion.getCuentaOrigen().getCbu());
		ventaDto.setTitular(sesionCliente.getCliente().getApellido1() + " " + sesionCliente.getCliente().getNombre());
		ventaDto.setNroCuentaDestino(TransferenciaUtil.formatearNumeroCuenta(parametrosOperacion.getCuentaDestino()));
		
		cargarDatosClaveToken(ventaDto);
		
		cargarLimites(ventaDto, importeDolarAumCpr);
		
		return ventaDto;
	}

	/**
	 * Carga datos para custom facts con cantidad de dias desde ultimo
	 * cambio de clave y/o token
	 * @param ventaDto
	 */
	private void cargarDatosClaveToken(VentaUSDDTO ventaDto) {
    	Respuesta<List<BigDecimal>> antiguedades = clienteManager.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
		if(antiguedades != null && antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
			if(antiguedades.getRespuesta().get(0) != null) {
				ventaDto.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
			}
			if(antiguedades.getRespuesta().get(1) != null) {
				ventaDto.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
			}
		}
    }
	
	/**
	 * Carga datos para custom facts de limites de operatoria
	 * @param limiteOperacionCompraVenta
	 */
	private void cargarLimites(VentaUSDDTO ventaDto, String importeDolarAumCpr) {
		
		BigDecimal importeDolarAcum = BigDecimal.valueOf(0) ; 
		if(importeDolarAumCpr != null && !StringUtils.isEmpty(importeDolarAumCpr)) {
			if(importeDolarAumCpr != null) {
				importeDolarAcum = ISBANStringUtils.stringToBigDecimal(importeDolarAumCpr.substring(0, importeDolarAumCpr.length()-1), 13, 2, false);
				importeDolarAcum = importeDolarAcum.add(new BigDecimal(ventaDto.getImporteUSD().replace(",", ".")));
				if(importeDolarAcum.compareTo(BigDecimal.valueOf(4999)) > 0) { // Supera MAX
					ventaDto.setAcumdiariousd(true);
				}
				ventaDto.setMontoAcumuladoUSD(importeDolarAcum.toString());
			}
		} else {
			ventaDto.setMontoAcumuladoUSD(importeDolarAcum.toString());
		}
	}
	/**
	 * Iniciar contador con dos intentos.
	 *
	 * @param esCompra
	 *            the es compra
	 */
	private void iniciarContador(boolean esCompra) {
		if (esCompra) {
			if (sesionCompraVenta.getContadorCompra() == null) {
				sesionCompraVenta.setContadorCompra(new ContadorIntentos(2));
			}
			sesionCompraVenta.setContadorVenta(null);
		} else {
			if (sesionCompraVenta.getContadorVenta() == null) {
				sesionCompraVenta.setContadorVenta(new ContadorIntentos(2));
			}
			sesionCompraVenta.setContadorCompra(null);
		}
	}

	/**
	 * Crear parametros simulacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param confirmarClienteCompra
	 *            the confirmar cliente compra
	 * @return the parametros simulacion
	 * @throws ServiceException
	 *             the service exception
	 */
	private ParametrosOperacion crearParametrosClienteCompra(Cliente cliente,
			ConfirmarClienteCompraEntity confirmarClienteCompra) throws ServiceException {
		ParametrosOperacion parametrosOperacion = new ParametrosOperacion();
		
		Cuenta cuentaOrigen = new Cuenta();
		Cuenta cuentaDestino = new Cuenta();
		IdentificacionCuenta IdOrigen = new IdentificacionCuenta(confirmarClienteCompra.getNumeroCuentaOrigen());
		IdentificacionCuenta IdDestino = new IdentificacionCuenta(confirmarClienteCompra.getNumeroCuentaDestino());
		if (!CuentasBancaPrivadaUtil.isCuentaBP(IdOrigen.getNroSucursal())) {
			cuentaOrigen = (Cuenta) obtenerCuenta(cliente, confirmarClienteCompra.getNumeroCuentaOrigen());
		} else {
			cuentaOrigen = (Cuenta) cuentaBancaPrivadaBO.buscarCuentaPorId(cliente, IdOrigen);
		}

		if (!CuentasBancaPrivadaUtil.isCuentaBP(IdDestino.getNroSucursal())) {
			cuentaDestino = (Cuenta) obtenerCuenta(cliente, confirmarClienteCompra.getNumeroCuentaDestino());
		} else {
			cuentaDestino = (Cuenta) cuentaBancaPrivadaBO.buscarCuentaPorId(cliente, IdDestino);
		}

		if (cuentaOrigen != null && cuentaDestino != null) {
			parametrosOperacion.setCliente(cliente);
			parametrosOperacion.setCuentaOrigen(cuentaOrigen);
			parametrosOperacion.setCuentaDestino(cuentaDestino);
			parametrosOperacion.setImporteCredito(StringUtils
					.remove(util.quitarSimboloMonedaAImporte(confirmarClienteCompra.getImporteCredito()), '.'));
			parametrosOperacion.setImporteDebito(StringUtils
					.remove(util.quitarSimboloMonedaAImporte(confirmarClienteCompra.getImporteDebito()), '.'));
			parametrosOperacion.setCotizacion(confirmarClienteCompra.getCotizacion());
			parametrosOperacion.setNupTipo(sesionCompraVenta.getTipoDocumento());
			parametrosOperacion.setNupNumDoc(sesionCompraVenta.getNroDocumento());
			parametrosOperacion.setIsDolar(confirmarClienteCompra.getIsDolar());
		}
		return parametrosOperacion;
	}

	/**
	 * Crear parametros.
	 *
	 * @param cliente
	 *            the cliente
	 * @param confirmarClienteVenta
	 *            the confirmar cliente venta
	 * @return the parametros operacion
	 * @throws ServiceException
	 *             the service exception
	 */
	private ParametrosOperacion crearParametrosClienteVende(Cliente cliente,
			ConfirmarClienteVentaEntity confirmarClienteVenta) throws ServiceException {
		ParametrosOperacion parametrosOperacion = new ParametrosOperacion();
		Cuenta cuentaOrigen  = new Cuenta();
		Cuenta cuentaDestino = new Cuenta();
		IdentificacionCuenta IdOrigen = new IdentificacionCuenta(confirmarClienteVenta.getNumeroCuentaOrigen());
		IdentificacionCuenta IdDestino = new IdentificacionCuenta(confirmarClienteVenta.getNumeroCuentaDestino());
		if (!CuentasBancaPrivadaUtil.isCuentaBP(IdOrigen.getNroSucursal())) {
			cuentaOrigen = (Cuenta) obtenerCuenta(cliente, confirmarClienteVenta.getNumeroCuentaOrigen());
		} else {
			cuentaOrigen = (Cuenta) cuentaBancaPrivadaBO.buscarCuentaPorId(cliente, IdOrigen);
		}

		if (!CuentasBancaPrivadaUtil.isCuentaBP(IdDestino.getNroSucursal())) {
			cuentaDestino = (Cuenta) obtenerCuenta(cliente, confirmarClienteVenta.getNumeroCuentaDestino());
		} else {
			cuentaDestino = (Cuenta) cuentaBancaPrivadaBO.buscarCuentaPorId(cliente, IdDestino);
		}

		if (cuentaOrigen != null && cuentaDestino != null) {
			parametrosOperacion.setCliente(cliente);
			parametrosOperacion.setCuentaOrigen(cuentaOrigen);
			parametrosOperacion.setCuentaDestino(cuentaDestino);
			parametrosOperacion.setImporteCredito(StringUtils
					.remove(util.quitarSimboloMonedaAImporte(confirmarClienteVenta.getImporteCredito()), '.'));
			parametrosOperacion.setImporteDebito(StringUtils
					.remove(util.quitarSimboloMonedaAImporte(confirmarClienteVenta.getImporteDebito()), '.'));
			parametrosOperacion.setCotizacion(confirmarClienteVenta.getCotizacion());
			parametrosOperacion.setIsDolar(confirmarClienteVenta.getIsDolar());
			parametrosOperacion.setNupTipo(sesionCompraVenta.getTipoDocumento());
			parametrosOperacion.setNupNumDoc(sesionCompraVenta.getNroDocumento());
		}
		return parametrosOperacion;
	}

	/**
	 * Crear respuesta simulacion compra.
	 *
	 * @param parametrosOperacion
	 *            the parametros operacion
	 * @return the respuesta
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private Respuesta<ComprobanteCompraVentaView> crearRespuestaClienteCompra(ParametrosOperacion parametrosOperacion)
			throws CompraVentaDolaresException {
		try {
			Respuesta<ComprobanteCompraVentaDTO> respuestaComprobanteCompraDTO = operacionClienteCompraBO
					.operacionClienteCompra(parametrosOperacion);
			if (casuistica.esRespuestaOK(respuestaComprobanteCompraDTO.getEstadoRespuesta())) {
				actualizarTurno();
				Estadistica estadistica = armarEstadisticaCompraVenta(parametrosOperacion,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK, respuestaComprobanteCompraDTO, true);
				if (estadistica != null) {
					estadisticaManager.add(estadistica);
				}
				estadisticaManager.add(EstadisticasConstants.COMPRA_DE_DOLARES_CREDITO_TASA_CERO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return crearRespuestaOperacionCompraOK(respuestaComprobanteCompraDTO, parametrosOperacion);
			}
			Estadistica estadistica = armarEstadisticaCompraVenta(parametrosOperacion,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, respuestaComprobanteCompraDTO, true);
			if (estadistica != null) {
				estadisticaManager.add(estadistica);
			}
			return casuistica.crearRespuestaErroneaConDatosExceptionOperacionFeedback(respuestaComprobanteCompraDTO,
					parametrosOperacion.getCuentaOrigen(), CompraVentaStringUtil.OPERACION_COMPRA,
					parametrosOperacion.getImporteCredito());
		} catch (BusinessException e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaErroneaInformacionNoDisponible();
		}
	}

	/**
	 * Actualizar turno.
	 */
	private void actualizarTurno() {
		if (moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.QUEUE_ST_COMPRAVENTA).tienePermisoDeVisibilidad()
				&& QueueSTUtils.verificarHorarioQueue(QueueSTOperations.COMPRAVENTA)) {		
			Respuesta<Void> respuestaActualizarTurno = queueSTBO.actualizarTurno(getCliente().getQueueTurnId(), 
					TurnStatesEnum.TURN_STATUS_FINISHED);
			getCliente().setQueueTurnId(null);
			estadisticaManager.add(EstadisticasConstants.QUEUE_ST_FINAL_COMPRAVENTA, 
					EstadoRespuesta.OK.equals(respuestaActualizarTurno.getEstadoRespuesta()) ?
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK : EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
	}

	/**
	 * Crear respuesta operacion cliente venta.
	 *
	 * @param parametrosOperacion
	 *            the parametros operacion
	 * @return the respuesta
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private Respuesta<ComprobanteCompraVentaView> crearRespuestaClienteVende(ParametrosOperacion parametrosOperacion)
			throws CompraVentaDolaresException {
		try {
			Respuesta<ComprobanteCompraVentaDTO> respuestaVentaDTO = operacionClienteVentaBO
					.operacionClienteVenta(parametrosOperacion);
			if (casuistica.esRespuestaOK(respuestaVentaDTO.getEstadoRespuesta())) {
				actualizarTurno();
				Estadistica estadistica = armarEstadisticaCompraVenta(parametrosOperacion,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK, respuestaVentaDTO, false);
				if (estadistica != null) {
					estadisticaManager.add(estadistica);
				}
				ComprobanteCompraVentaView view = new ComprobanteCompraVentaView(respuestaVentaDTO.getRespuesta());
				view.setMensajeVenta(obtenerMensajeCompraVenta(ISBANStringUtils
						.formatearSaldoConSigno(respuestaVentaDTO.getRespuesta().getImporteDolaresDebitado()), false));
				CompraVentaBuilder builder = new CompraVentaBuilder(sesionCliente.getCliente())
						.setComprobanteDTO(respuestaVentaDTO.getRespuesta()).setParametros(parametrosOperacion);
				sesionParametros.setCompraVentaView(view);
				altaComprobante.altaScompTransferencia(builder);
				return casuistica.crearRespuestaOk(ComprobanteCompraVentaView.class, view);
			}
			Estadistica estadistica = armarEstadisticaCompraVenta(parametrosOperacion,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, respuestaVentaDTO, false);
			if (estadistica != null) {
				estadisticaManager.add(estadistica);
			}
			return casuistica.crearRespuestaErroneaConDatosExceptionOperacionFeedback(respuestaVentaDTO,
					parametrosOperacion.getCuentaOrigen(), "V", parametrosOperacion.getImporteDebito());
		} catch (BusinessException e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaErroneaInformacionNoDisponible();
		}
	}

	/**
	 * Armar estadistica compra venta.
	 *
	 * @param parametrosOperacion
	 *            the parametros operacion
	 * @param resultado
	 *            the resultado
	 * @param respuestaVentaDTO
	 *            the respuesta venta DTO
	 * @param isCompra
	 *            the is compra
	 * @return the estadistica
	 */
	private Estadistica armarEstadisticaCompraVenta(ParametrosOperacion parametrosOperacion, String resultado,
			Respuesta<ComprobanteCompraVentaDTO> respuestaVentaDTO, boolean isCompra) {
		Estadistica estadistica = new Estadistica();
		String codigoEstadistica = getTransactionByTypeAccount(
				parametrosOperacion.getCuentaOrigen().getTipoCuentaEnum(), isCompra);
		estadistica.setCodigoTransaccion(codigoEstadistica);
		estadistica.setCodigoError(resultado);
		String nroCuentaDestino = parametrosOperacion.getCuentaDestino().getNroCuentaProducto();
		estadistica.setNroCtaDestino(parametrosOperacion.getCuentaDestino().getNroSucursal()
				.concat(nroCuentaDestino.substring(nroCuentaDestino.length() - 7)));
		String nroCuentaOrigen = parametrosOperacion.getCuentaOrigen().getNroCuentaProducto();
		estadistica.setNroCtaOrigen(parametrosOperacion.getCuentaOrigen().getNroSucursal()
				.concat(nroCuentaOrigen.substring(nroCuentaDestino.length() - 7)));
		if (EstadoRespuesta.OK.equals(respuestaVentaDTO.getEstadoRespuesta())) {
			estadistica.setNroComprobante(respuestaVentaDTO.getRespuesta().getNumeroComprobante());
		}
		if (isCompra) {
			grabarEstadisticaCompra(parametrosOperacion, estadistica);
		} else {
			grabarEstadisticaVenta(parametrosOperacion, estadistica);
		}

		if (codigoEstadistica != null) {
			return estadistica;
		}
		return null;
	}

	/**
	 * Grabar estadistica venta.
	 *
	 * @param parametrosOperacion
	 *            the parametros operacion
	 * @param estadistica
	 *            the estadistica
	 */
	private void grabarEstadisticaVenta(ParametrosOperacion parametrosOperacion, Estadistica estadistica) {
		if (parametrosOperacion.getIsDolar()) {
			estadistica.setImporte(parametrosOperacion.getImporteDebito().replace(",", "."));
			estadistica.setMoneda(TIPO_USD);
		} else {
			estadistica.setImporte(parametrosOperacion.getImporteCredito().replace(",", "."));
			estadistica.setMoneda(TIPO_PESOS);
		}
	}

	/**
	 * Grabar estadistica compra.
	 *
	 * @param parametrosOperacion
	 *            the parametros operacion
	 * @param estadistica
	 *            the estadistica
	 */
	private void grabarEstadisticaCompra(ParametrosOperacion parametrosOperacion, Estadistica estadistica) {
		if (parametrosOperacion.getIsDolar()) {
			estadistica.setImporte(parametrosOperacion.getImporteCredito().replace(",", "."));
			estadistica.setMoneda(TIPO_USD);
		} else {
			estadistica.setImporte(parametrosOperacion.getImporteDebito().replace(",", "."));
			estadistica.setMoneda(TIPO_PESOS);
		}

	}

	/**
	 * Gets the transaction by type account.
	 *
	 * @param tipoCuentaEnum
	 *            the tipo cuenta enum
	 * @param isCompra
	 *            the is compra
	 * @return the transaction by type account
	 */
	private String getTransactionByTypeAccount(TipoCuenta tipoCuentaEnum, boolean isCompra) {
		String tipoCuenta = null;
		if (!isCompra) {
			if (tipoCuentaEnum.equals(TipoCuenta.CAJA_AHORRO_PESOS)) {
				tipoCuenta = EstadisticasConstants.VENTA_DOLARES_CA_PESOS;
			}
			if (tipoCuentaEnum.equals(TipoCuenta.CUENTA_CORRIENTE_PESOS)) {
				tipoCuenta = EstadisticasConstants.VENTA_DOLARES_CC_PESOS;
			}
			if (tipoCuentaEnum.equals(TipoCuenta.CUENTA_UNICA_PESOS)) {
				tipoCuenta = EstadisticasConstants.VENTA_DOLARES_CU_PESOS;
			}
		} else {
			if (tipoCuentaEnum.equals(TipoCuenta.CAJA_AHORRO_PESOS)) {
				tipoCuenta = EstadisticasConstants.COMPRA_DOLARES_CA_PESOS;
			}
			if (tipoCuentaEnum.equals(TipoCuenta.CUENTA_CORRIENTE_PESOS)) {
				tipoCuenta = EstadisticasConstants.COMPRA_DOLARES_CC_PESOS;
			}
			if (tipoCuentaEnum.equals(TipoCuenta.CUENTA_UNICA_PESOS)) {
				tipoCuenta = EstadisticasConstants.COMPRA_DOLARES_CU_PESOS;
			}
		}
		return tipoCuenta;
	}

	/**
	 * Crear respuesta operacion compra OK.
	 *
	 * @param respuestaComprobanteCompraDTO
	 *            the respuesta comprobante compra DTO
	 * @param parametrosOperacion
	 *            the parametros operacion
	 * @return the respuesta
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private Respuesta<ComprobanteCompraVentaView> crearRespuestaOperacionCompraOK(
			Respuesta<ComprobanteCompraVentaDTO> respuestaComprobanteCompraDTO, ParametrosOperacion parametrosOperacion)
			throws CompraVentaDolaresException {
		ComprobanteCompraVentaView view = new ComprobanteCompraVentaView(respuestaComprobanteCompraDTO.getRespuesta());
		view.setMensajeCompra(obtenerMensajeCompraVenta(ISBANStringUtils.formatearSaldoConSigno(
				respuestaComprobanteCompraDTO.getRespuesta().getImporteDolarAcreditado()), true));
		CompraVentaBuilder builder = new CompraVentaBuilder(sesionCliente.getCliente())
				.setComprobanteDTO(respuestaComprobanteCompraDTO.getRespuesta()).setParametros(parametrosOperacion);
		sesionParametros.setCompraVentaView(view);
		altaComprobante.altaScompTransferencia(builder);
		return casuistica.crearRespuestaOk(ComprobanteCompraVentaView.class, view);
	}

	/**
	 * Obtener mensaje compra.
	 *
	 * @param importe
	 *            the importe
	 * @param esCompra
	 *            the es compra
	 * @return the string
	 */
	protected String obtenerMensajeCompraVenta(String importe, boolean esCompra) {
		String mensaje = mensajeManager.obtenerMensajePorCodigo("1387").getMensaje();
		if (esCompra) {
			mensaje = StringUtils.replace(mensaje, "{0}", CompraVentaStringUtil.OPERACION_COMPRA_DETALLE);
		} else {
			mensaje = StringUtils.replace(mensaje, "{0}", CompraVentaStringUtil.OPERACION_VENTA_DETALLE);
		}
		return StringUtils.replace(mensaje, "{1}", importe);
	}

	/**
	 * Genera la estadistica de la operacion de cliente compra.
	 */
	@Override
	public void generarEstadisticaComprobanteClienteCompra() {
		estadisticaManager.add(EstadisticasConstants.CODIGO_COMPROBANTE_COMPRA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

	}

	/**
	 * Genera la estadistica de la operacion de cliente vende.
	 */
	@Override
	public void generarEstadisticaComprobanteClienteVende() {
		estadisticaManager.add(EstadisticasConstants.CODIGO_COMPROBANTE_VENTA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.manager.
	 * ComprobantesManager#descargarComprobantePDF()
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobantePDF() {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		reporte = reporteBO.obtenerComprobantePDF(sesionParametros.getCompraVentaView());
		String estadistica = generarCodigoDeEstadistica(sesionParametros.getCompraVentaView().getEsCompra());
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/**
	 * Generar codigo de estadistica.
	 *
	 * @param esCompra
	 *            the es compra
	 * @return the string
	 */
	private String generarCodigoDeEstadistica(Boolean esCompra) {
		if (esCompra) {
			return EstadisticasConstants.DESCARGA_PDF_COMPROBANTES_COMPRA_DOLARES;
		} else {
			return EstadisticasConstants.DESCARGA_PDF_COMPROBANTES_VENTA_DOLARES;
		}

	}
	
}
