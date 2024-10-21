/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.afip.bo.AfipWSBOImpl;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoTasaCeroConfigEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.TarjetaPrestamoTasaCero;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoTasaCeroBO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConfirmarPrestamoTasaCeroInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoTasaCeroView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ConfirmacionPrestamoTasaCeroView;
import ar.com.santanderrio.obp.servicios.prestamos.view.DatosPrestamoTasaCeroView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoTasaCeroManager;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class PrestamoTasaCeroManagerImpl.
 */
@Component
public class PrestamoTasaCeroManagerImpl implements PrestamoTasaCeroManager {

    /** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoTasaCeroManagerImpl.class);

	/** The Constant NRO_TARJETA_LEN. */
	private static final int NRO_TARJETA_LEN = 20;
	
	/** The Constant TIPO_SOLICITUD_ALTA_TP. */
	private static final String TIPO_SOLICITUD_ALTA_TP = "TP";
	
	/** The Constant TIPO_SOLICITUD_ALTA. */
	private static final String TIPO_SOLICITUD_ALTA = "P";
	
	/** The Constant TIPO_OPERACION_ALTA. */
	private static final String TIPO_OPERACION_ALTA = "A";
	
	/** The Constant TARJETA_EN_BLANCO. */
	private static final String TARJETA_EN_BLANCO = "0000000000000000";
	
	/** The Constant PRESTAMO_TASA_CERO_CULTURA. */
    private static final String PRESTAMO_TASA_CERO_CULTURA = "C";	

	/** The prestamo tasa cero BO. */
	@Autowired
	private PrestamoTasaCeroBO prestamoTasaCeroBO;

	@Autowired
	@Qualifier("afipWSBOImpl")
	private AfipWSBOImpl afipWSBOImpl;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;
	
	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The legal BO. */
	@Autowired
	protected LegalBO legalBO;
	
    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

	/**
	 * Inicio prestamos tasa cero.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<DatosPrestamoTasaCeroView> inicioPrestamosTasaCero() {
		LOGGER.info("consulto estado de deuda previsional");
		Respuesta<Boolean> respTieneDeuda = afipWSBOImpl.tieneDeudaConAfip();
		if (!EstadoRespuesta.OK.equals(respTieneDeuda.getEstadoRespuesta())) {
			LOGGER.error("Error en los servicios de afip");
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SERVICIO_AFIP, CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_GENERICO);
		}

		boolean tieneDeuda = respTieneDeuda.getRespuesta();

		LOGGER.info("estado de deuda: {}", tieneDeuda);
		if (tieneDeuda) {
			LOGGER.error("La persona tiene deuda previsional, no puede solicitar un tasa cero");
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_DEUDA_PREVISIONAL, CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_GENERICO);
		}

		Cliente cliente = sesionCliente.getCliente();
		String email = sesionParametros.getCredencialesMya().getEmail();
		String telMya = sesionParametros.getCredencialesMya().getCodigoArea() != null ? sesionParametros.getCredencialesMya().getCodigoArea() + "-" : "";
		telMya = sesionParametros.getCredencialesMya().getCelular() != null ? telMya + sesionParametros.getCredencialesMya().getCelular() : "";
		List<Cuenta> tarjetas = cliente.getCuentasTarjetaCredito();
		boolean tieneTrjCred = !tarjetas.isEmpty();
		Respuesta<DatosPrestamoTasaCeroView> respuesta = new Respuesta<DatosPrestamoTasaCeroView>();
		DatosPrestamoTasaCeroView viewRsp = new DatosPrestamoTasaCeroView();		    	

		try {
			Respuesta<PrestamoTasaCeroConfigEntity> rsp = prestamoTasaCeroBO.inicioPrestamosTasaCero(sesionCliente);
						
	    	if (EstadoRespuesta.OK.equals(rsp.getEstadoRespuesta()) && (rsp.getRespuesta() != null) && (rsp.getRespuesta().getHabilitadoPrestamo())){
	    		viewRsp.setImporteSolicitado(ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(rsp.getRespuesta().getImporte_solicitado())));
	    		if ("".equals(rsp.getRespuesta().getEmail())) {
	    			viewRsp.setMail(String.valueOf(rsp.getRespuesta().getEmail()));
	    		} else {
	    			viewRsp.setMail(String.valueOf(email));	
	    		}	    		
	    		viewRsp.setCelular(String.valueOf(telMya));
	    		viewRsp.setHabilitadoPrestamo(String.valueOf(rsp.getRespuesta().getHabilitadoPrestamo()));

	    		if (tieneTrjCred) {
	    			List<TarjetaPrestamoTasaCero> tarjetasPrestamoTasaCeroFormateadas = new ArrayList<TarjetaPrestamoTasaCero>();
	    			for (Cuenta tarjeta : tarjetas) {
	    				tarjetasPrestamoTasaCeroFormateadas.add(formatearTarjetas(tarjeta));	
	    			}
	    			viewRsp.setCards(tarjetasPrestamoTasaCeroFormateadas);
	    		}

	    		if (TARJETA_EN_BLANCO.equals(rsp.getRespuesta().getTarjeta())) {
	    			viewRsp.setTarjeta("");
	    			if (tieneTrjCred) {
	    				viewRsp.setEmitirTarjeta(false);
	    			}else {
	    				viewRsp.setEmitirTarjeta(true);
	    			}	    			
	    			
	    		} else {
		    		if (tieneTrjCred) {	    			
		    			viewRsp.setEmitirTarjeta(false);
		    			if (isTarjetaValida(rsp.getRespuesta().getTarjeta())) {
		    				viewRsp.setTarjeta(rsp.getRespuesta().getTarjeta());
		    			} else {
		    				viewRsp.setTarjeta("");		    				
		    			}
		    		} else {
		    			viewRsp.setEmitirTarjeta(true);
		    			viewRsp.setTarjeta("");		    			
		    		}
	    		}
	    		viewRsp.setTipo(rsp.getRespuesta().getTipo());
	        	respuesta = respuestaFactory.crearRespuestaOk(viewRsp);
	        	String estadisticaPrestamo = EstadisticasConstants.INICIO_PRESTAMO_TASA_CERO;
	        	if(PRESTAMO_TASA_CERO_CULTURA.equals(rsp.getRespuesta().getTipo())) {
	        	    estadisticaPrestamo = EstadisticasConstants.INICIO_PRESTAMO_TASA_CERO_CULTURA;
	        	}
	        	estadisticaManager.add(estadisticaPrestamo, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	    	} else if (EstadoRespuesta.ERROR.equals(rsp.getEstadoRespuesta())) {
	    		if (TipoError.ERROR_INICIO_PRESTAMOS.getDescripcion().equals(rsp.getItemsMensajeRespuesta().get(0).getTipoError()) ||
	    				"PRESTAMO_NO_DISPONIBLE".equals(rsp.getItemsMensajeRespuesta().get(0).getTipoError())) {
	    			LOGGER.error("ERROR_INICIO_PRESTAMOS significa que el cliente tiene un prestamo activo. PRESTAMO_NO_DISPONIBLE significa que"
	    					+ "no puede sacar prestamo a tasa cero normal.");
	    			return respuestaFactory.crearRespuestaError(DatosPrestamoTasaCeroView.class, rsp.getItemsMensajeRespuesta());
	    		}
	            respuesta = respuestaFactory.crearRespuestaError(DatosPrestamoTasaCeroView.class, "", "");	            		
	            estadisticaManager.add(EstadisticasConstants.INICIO_PRESTAMO_TASA_CERO,
	                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	        }

		} catch (DAOException e) {
            respuesta = respuestaFactory.crearRespuestaError(DatosPrestamoTasaCeroView.class, "", "");	            		
            estadisticaManager.add(EstadisticasConstants.INICIO_PRESTAMO_TASA_CERO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuesta;
	}

	/**
	 * Solicitar prestamos tasa cero.
	 *
	 * @param view the view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ComprobantePrestamoTasaCeroView> solicitarPrestamosTasaCero(ConfirmacionPrestamoTasaCeroView view) {
		ConfirmarPrestamoTasaCeroInDTO dto = buildConfirmarPrestamoSueldosInDTO(view);
		Respuesta<ComprobantePrestamoTasaCeroView> respuesta = new Respuesta<ComprobantePrestamoTasaCeroView>();
		ComprobantePrestamoTasaCeroView viewRsp = new ComprobantePrestamoTasaCeroView();	
		String estadisticaTyC = EstadisticasConstants.ACEPTA_TYC_PRESTAMO_TASA_CERO;
		String estadisticaConfirmaPrestamo = EstadisticasConstants.CONFIRMACION_PRESTAMO_TASA_CERO;
		if(PRESTAMO_TASA_CERO_CULTURA.equals(view.getTipo())) {
		    estadisticaTyC = EstadisticasConstants.ACEPTA_TYC_PRESTAMO_TASA_CERO_CULTURA;
		    estadisticaConfirmaPrestamo = EstadisticasConstants.CONFIRMACION_PRESTAMO_TASA_CERO_CULTURA;
		}
		estadisticaManager.add(estadisticaTyC,EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		try {
		    
			boolean rsp = prestamoTasaCeroBO.solicitarPrestamosTasaCero(dto, sesionCliente);
			if (rsp == true) {
				viewRsp.setPrestamoTasaCeroInsertado(Boolean.TRUE);				
				respuesta = respuestaFactory.crearRespuestaOk(viewRsp);
	        	estadisticaManager.add(estadisticaConfirmaPrestamo,
	                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	    	} else {
	            respuesta = respuestaFactory.crearRespuestaError(ComprobantePrestamoTasaCeroView.class, "", "");	            		
	            estadisticaManager.add(estadisticaConfirmaPrestamo,
	                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	        }
		} catch (DAOException e) {
            respuesta = respuestaFactory.crearRespuestaError(ComprobantePrestamoTasaCeroView.class, "", "");	            		
            estadisticaManager.add(estadisticaConfirmaPrestamo,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuesta;
	}

    /**
     * Builds the confirmar prestamo sueldos in DTO.
     *
     * @param view the view
     * @return the confirmar prestamo tasa cero in DTO
     */
    private ConfirmarPrestamoTasaCeroInDTO buildConfirmarPrestamoSueldosInDTO(ConfirmacionPrestamoTasaCeroView view) {
    	Cliente cliente = sesionCliente.getCliente();
    	
    	ConfirmarPrestamoTasaCeroInDTO confirmarPrestamoTasaCeroInDTO = new ConfirmarPrestamoTasaCeroInDTO();
        confirmarPrestamoTasaCeroInDTO.setCelular(view.getCelular());
        confirmarPrestamoTasaCeroInDTO.setEmail(view.getMail());
        if (view.getEmitirTarjeta()) {
        	confirmarPrestamoTasaCeroInDTO.setTarjeta(TARJETA_EN_BLANCO);
        	confirmarPrestamoTasaCeroInDTO.setTipoSolicitud(TIPO_SOLICITUD_ALTA_TP);
        } else {
        	String trjConfirmacion = "";
        	if ("".equals(view.getTarjeta())) {
        		trjConfirmacion = obtenerTarjetaConfirmacionPrestamo(cliente, view.getTarjetaId());
        	}else {
        		trjConfirmacion = view.getTarjeta();
        	}
        	confirmarPrestamoTasaCeroInDTO.setTipoSolicitud(TIPO_SOLICITUD_ALTA);
        	confirmarPrestamoTasaCeroInDTO.setTarjeta(trjConfirmacion);
        }
        confirmarPrestamoTasaCeroInDTO.setTipoOperacion(TIPO_OPERACION_ALTA);
        return confirmarPrestamoTasaCeroInDTO;
    }

    /**
     * Checks if is tarjeta valida.
     *
     * @param nroTarjeta the nro tarjeta
     * @return true, if is tarjeta valida
     */
    private boolean isTarjetaValida(String nroTarjeta) {    	
    	String nro = StringUtils.leftPad(String.valueOf(Long.parseLong(nroTarjeta)), NRO_TARJETA_LEN, "0");
    	Cuenta tarjetaElegida = sesionCliente.getCliente().getTarjetaDesdeNroTarjeta(nro);
    	if (tarjetaElegida != null && !Cuenta.TIPOCTA_BANELCO.equals(tarjetaElegida.getTipoCuenta())) {
    		return true;
    	} else {
    		return false;
    	}
    }

    /**
     * Formatear tarjetas.
     *
     * @param tarjeta the tarjeta
     * @return the tarjeta prestamo tasa cero
     */
    private TarjetaPrestamoTasaCero formatearTarjetas(Cuenta tarjeta) {
        return new TarjetaPrestamoTasaCero(tarjeta.getId().toString(),
        		TarjetaBOUtils.formatearNumeroTarjeta(tarjeta.getNroTarjetaCredito(),TarjetaUtils.getMarca(tarjeta)),
                tarjeta.getAlias());
    }

    /**
     * Obtener tarjeta confirmacion prestamo.
     *
     * @param cliente the cliente
     * @param tarjetaElegida the tarjeta elegida
     * @return the string
     */
    private String obtenerTarjetaConfirmacionPrestamo(Cliente cliente, String tarjetaElegida) {
    	String nroTarjeta = "";
		List<Cuenta> tarjetas = cliente.getCuentasTarjetaCredito();
		for (Cuenta trj : tarjetas) {
			TarjetaPrestamoTasaCero tarjetaPrestamoTasaCero =  formatearTarjetas(trj);
			if (tarjetaElegida.equals(tarjetaPrestamoTasaCero.getIdCuenta())) {
				nroTarjeta = ISBANStringUtils.eliminarCeros(trj.getNroTarjetaCredito());
				break;
			}
		}
		return nroTarjeta;
    }

}
