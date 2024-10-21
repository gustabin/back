/**
* 
*/
package ar.com.santanderrio.obp.servicios.compraventa.web.manager.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.SimulacionClienteCompraBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.SimulacionClienteVendeBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.dto.SimulacionCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosSimulacion;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ContinuarCompraVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.ConfiguracionCompraVentaManager;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.SimulacionCompraVentaManager;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.SimulacionCompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosRespuestaHabilitaCompraVentaUSDEntity;

/**
 * The Class SimulacionCompraVentaManagerImpl.
 *
 * @author sabrina.cis
 */
@Component
public class SimulacionCompraVentaManagerImpl extends CompraVentaDolaresManagerImpl
		implements SimulacionCompraVentaManager {
	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SimulacionCompraVentaManagerImpl.class);
	
	   /** The Constant MSJ_INFO_GUARDANDO_HASH_EN_SESION. */
    private static final String MSJ_INFO_GUARDANDO_HASH_EN_SESION = "Se guarda el hash en sesion.";
    
    /** The Constant MSJ_INFO_CREANDO_HASH_ATRIBUTOS. */
    private static final String MSJ_INFO_CREANDO_HASH_ATRIBUTOS = "Creando hash de los atributos...";

	/** The simulacion cliente compra BO. */
	@Autowired
	private SimulacionClienteCompraBO simulacionClienteCompraBO;

	/** The simulacion cliente vende BO. */
	@Autowired
	private SimulacionClienteVendeBO simulacionClienteVendeBO;
	
	/** The cuenta banca privada BO. */
	@Autowired
	private CuentasBancaPrivadaBO cuentaBancaPrivadaBO;
	
	/** configuracion compra venta Manager */
	@Autowired
	private ConfiguracionCompraVentaManager configuracionCompraVentaManager;
	
	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;
	
	@Autowired
	private EstadisticaManager estadisticaManager;
	
	private static final String COMPRA_DOLARES = "C";
	

	/**
	 * Continuar del paso uno para la compra de dolares. Simulacion cliente
	 * compra, banco vende.
	 *
	 * @param entity
	 *            the continuar compra venta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<SimulacionCompraVentaDolarView> continuarCompraDolares(ContinuarCompraVentaEntity entity) {
        try {
        	Respuesta<DatosRespuestaHabilitaCompraVentaUSDEntity> respuesta = configuracionCompraVentaManager.cnsHabilitadoCompraVtaUSD(sesionCliente.getCliente().getNup());
        	if(EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta()) && 
        			respuesta.getRespuesta().getDatos().getCodigo() == 0) {
	            ParametrosSimulacion parametrosSimulacion = crearParametrosSimulacionCompraVenta(getCliente(), entity);
	            Respuesta<SimulacionCompraVentaDolarView> view = crearRespuestaSimulacionCompra(parametrosSimulacion);
	            if(!entity.getIsDolar() && EstadoRespuesta.OK.equals(view.getEstadoRespuesta())) {
	                String importeSinFormatear = view.getRespuesta().getImporteDebitarPesos().replaceAll("\\.", "");
	                importeSinFormatear = importeSinFormatear.replace(",", ".");
	                entity.setImporte(Double.valueOf(importeSinFormatear));
	            }
	            cargarHashEnSesion(entity);
	            guardarNumerosCuentaEnSesionCompraVenta(view.getEstadoRespuesta(), entity.getNumeroCuentaOrigen(),
	                    entity.getNumeroCuentaDestino());
	            return view;
        	} else {
	    		estadisticaManager.add(EstadisticasConstants.SIMULACION_COMPRA_DOLARES,
	    				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        		return casuistica.crearRespuestaErrorMensajePersonalizado(respuesta.getRespuesta().getDatos().getLeyenda());
        	}
        } catch (Exception e) {
        	estadisticaManager.add(EstadisticasConstants.SIMULACION_COMPRA_DOLARES,
    				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
            return casuistica.crearRespuestaErroneaInformacionNoDisponible();
        }
	}

	/**
	 * Cargar hash en sesion.
	 *
	 * @param entity
	 *            the entity
	 */
	private void cargarHashEnSesion(ContinuarCompraVentaEntity entity) {
	    String hashView = HashUtils.obtenerHash(crearMapaDeCompraVentaEntity(entity));
        LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
        sesion.setValidacionHash(hashView);
    }

    /**
	 * Crear mapa de compra venta entity.
	 *
	 * @param entity
	 *            the entity
	 * @return the map
	 */
    private Map<String, String> crearMapaDeCompraVentaEntity(ContinuarCompraVentaEntity entity) {
        LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
        Map<String, String> mapaAtributos = new HashMap<String, String>();
        mapaAtributos.put("cotizacion", entity.getCotizacion());
        mapaAtributos.put("numeroCuentaDestino", entity.getNumeroCuentaDestino());
        mapaAtributos.put("numeroCuentaOrigen", entity.getNumeroCuentaOrigen());
        mapaAtributos.put("importe", ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(entity.getImporte())));
        mapaAtributos.put("isDolar", String.valueOf(entity.getIsDolar()));
        LOGGER.info("String mapa vista: " + mapaAtributos.toString());
        return mapaAtributos;
    }

    /**
	 * Continuar del paso uno para la venta de dolares.
	 *
	 * @param continuarCompraVenta
	 *            the continuar compra venta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<SimulacionCompraVentaDolarView> continuarVentaDolares(
			ContinuarCompraVentaEntity continuarCompraVenta) {
	    cargarHashEnSesion(continuarCompraVenta);
		try {
			ParametrosSimulacion parametrosSimulacion = crearParametrosSimulacionCompraVenta(getCliente(),
					continuarCompraVenta);
			Respuesta<SimulacionCompraVentaDolarView> view = crearRespuestaSimulacionVenta(parametrosSimulacion);
			guardarNumerosCuentaEnSesionCompraVenta(view.getEstadoRespuesta(),
					continuarCompraVenta.getNumeroCuentaOrigen(), continuarCompraVenta.getNumeroCuentaDestino());
			return view;
		} catch (Exception e) {
			estadisticaManager.add(EstadisticasConstants.SIMULACION_VENTA_DOLARES,
    				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaErroneaInformacionNoDisponible();
		}
	}

	/**
	 * Crear parametros simulacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param continuarCompraVenta
	 *            the continuar compra venta
	 * @return the parametros simulacion
	 * @throws ServiceException
	 *             the service exception
	 */
	private ParametrosSimulacion crearParametrosSimulacionCompraVenta(Cliente cliente,
			ContinuarCompraVentaEntity continuarCompraVenta) throws ServiceException {
		ParametrosSimulacion parametrosSimulacion = new ParametrosSimulacion();
		Cuenta cuentaOrigen = new Cuenta();
		Cuenta cuentaDestino = new Cuenta();
		IdentificacionCuenta IdOrigen = new IdentificacionCuenta(continuarCompraVenta.getNumeroCuentaOrigen());
		IdentificacionCuenta IdDestino = new IdentificacionCuenta(continuarCompraVenta.getNumeroCuentaDestino());

		if (!CuentasBancaPrivadaUtil.isCuentaBP(IdOrigen.getNroSucursal())) {
			cuentaOrigen = obtenerCuenta(cliente, continuarCompraVenta.getNumeroCuentaOrigen());
		} else {
			cuentaOrigen = (Cuenta) cuentaBancaPrivadaBO.buscarCuentaPorId(cliente, IdOrigen);
		}
		if (!CuentasBancaPrivadaUtil.isCuentaBP(IdDestino.getNroSucursal())) {
			cuentaDestino = obtenerCuenta(cliente, continuarCompraVenta.getNumeroCuentaDestino());
		} else {
			cuentaDestino = (Cuenta) cuentaBancaPrivadaBO.buscarCuentaPorId(cliente, IdDestino);
		}

		if (cuentaOrigen != null && cuentaDestino != null) {
			parametrosSimulacion.setCliente(cliente);
			parametrosSimulacion
					.setImporte(util.quitarSimboloMonedaAImporte(String.valueOf(continuarCompraVenta.getImporte())));
			parametrosSimulacion.setIsDolar(continuarCompraVenta.getIsDolar());
			parametrosSimulacion.setCotizacion(continuarCompraVenta.getCotizacion());
			parametrosSimulacion.setCuentaOrigen(cuentaOrigen);
			parametrosSimulacion.setCuentaDestino(cuentaDestino);
			parametrosSimulacion.setNumeroCuentaDestino(continuarCompraVenta.getNumeroCuentaDestino());
			parametrosSimulacion.setNumeroCuentaOrigen(continuarCompraVenta.getNumeroCuentaOrigen());
			parametrosSimulacion.setNupTipo(sesionCompraVenta.getTipoDocumento());
			parametrosSimulacion.setNupNumDoc(sesionCompraVenta.getNroDocumento());
		}
		return parametrosSimulacion;
	}

	/**
	 * Crear respuesta simulacion compra.
	 *
	 * @param parametrosSimulacion
	 *            the parametros simulacion
	 * @return the respuesta
	 */
	private Respuesta<SimulacionCompraVentaDolarView> crearRespuestaSimulacionCompra(
			ParametrosSimulacion parametrosSimulacion) {
		try {
			Respuesta<SimulacionCompraVentaDTO> respuestaCompraDTO = simulacionClienteCompraBO
					.obtenerSimulacionClienteCompra(parametrosSimulacion);
			if (EstadoRespuesta.ERROR.equals(respuestaCompraDTO.getEstadoRespuesta()) &&
					(TipoError.ERROR_INHABILITADO_BCRA_AMEC_21.getDescripcion().equals(respuestaCompraDTO.getItemsMensajeRespuesta().get(0).getTipoError())
							|| TipoError.ERROR_INHABILITADO_BCRA_AMEC_42.getDescripcion().equals(respuestaCompraDTO.getItemsMensajeRespuesta().get(0).getTipoError()))) {
					String mensajeError = respuestaCompraDTO.getItemsMensajeRespuesta().get(0).getMensaje();
					respuestaCompraDTO.getItemsMensajeRespuesta().get(0).setMensaje(MessageFormat.format(mensajeError, "comprar"));
				}
			return crearRespuestaSimulacion(respuestaCompraDTO, parametrosSimulacion.getNumeroCuentaOrigen(), "C");
		} catch (BusinessException e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaErroneaInformacionNoDisponible();
		} catch (CompraVentaDolaresException e) {
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaErroneaInformacionNoDisponible();
		}
	}

	/**
	 * Verifica que cliente tiene un documento del tipo CUIT, CUIL o CDI. Luego
	 * genera la respuesta con el lalmado al servicio de simulacion. En caso que
	 * la doumentacion del cliente no sea valida se genera un mensaje de error
	 * 1085
	 *
	 * @param parametrosSimulacion
	 *            the parametros simulacion
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private Respuesta<SimulacionCompraVentaDolarView> crearRespuestaSimulacionVenta(
			ParametrosSimulacion parametrosSimulacion) throws BusinessException, CompraVentaDolaresException {
		try {
			Respuesta<SimulacionCompraVentaDTO> respuestaCompraVentaDolarView = simulacionClienteVendeBO
					.obtenerSimulacionClienteVende(parametrosSimulacion);
			if (EstadoRespuesta.ERROR.equals(respuestaCompraVentaDolarView.getEstadoRespuesta()) && 
					(TipoError.ERROR_INHABILITADO_BCRA_AMEC_21.getDescripcion().equals(respuestaCompraVentaDolarView.getItemsMensajeRespuesta().get(0).getTipoError())
							|| TipoError.ERROR_INHABILITADO_BCRA_AMEC_42.getDescripcion().equals(respuestaCompraVentaDolarView.getItemsMensajeRespuesta().get(0).getTipoError()))) {
				String mensajeError = respuestaCompraVentaDolarView.getItemsMensajeRespuesta().get(0).getMensaje();
				respuestaCompraVentaDolarView.getItemsMensajeRespuesta().get(0).setMensaje(MessageFormat.format(mensajeError, "vender"));
			}
			return crearRespuestaSimulacion(respuestaCompraVentaDolarView, parametrosSimulacion.getNumeroCuentaOrigen(),
					"V");
		} catch (Exception e) {
			estadisticaManager.add(EstadisticasConstants.SIMULACION_VENTA_DOLARES,
    				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			LOGGER.info(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaErroneaInformacionNoDisponible();
		}
	}

	/**
	 * Crear respuesta simulacion.
	 *
	 * @param rtaDto
	 *            the respuesta compra venta dolar view
	 * @param nroCuentaOrigen
	 *            the nro cuenta origen
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the respuesta
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private Respuesta<SimulacionCompraVentaDolarView> crearRespuestaSimulacion(
			Respuesta<SimulacionCompraVentaDTO> rtaDto, String nroCuentaOrigen, String tipoOperacion)
			throws CompraVentaDolaresException {
		if (casuistica.esRespuestaOK(rtaDto.getEstadoRespuesta())) {
			sesionCompraVenta.setTipoDocumento(rtaDto.getRespuesta().getNupTipo());
			sesionCompraVenta.setNroDocumento(rtaDto.getRespuesta().getNupNumDoc());
			if (COMPRA_DOLARES.equals(tipoOperacion)) {
				estadisticaManager.add(EstadisticasConstants.SIMULACION_COMPRA_DOLARES,
    			EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				estadisticaManager.add(EstadisticasConstants.SIMULACION_VENTA_DOLARES,
		    	EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}
			return casuistica.crearRespuestaOk(SimulacionCompraVentaDolarView.class,
					new SimulacionCompraVentaDolarView(rtaDto.getRespuesta()));
		}
		if (COMPRA_DOLARES.equals(tipoOperacion)) {
			estadisticaManager.add(EstadisticasConstants.SIMULACION_COMPRA_DOLARES,
			EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else {
			estadisticaManager.add(EstadisticasConstants.SIMULACION_VENTA_DOLARES,
	    	EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return casuistica.crearRespuestaErroneaConDatosException(rtaDto, nroCuentaOrigen, tipoOperacion);
	}

}