/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.MovimientosBO;
import ar.com.santanderrio.obp.servicios.cuentas.dao.UltimosMovimientosDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Movimiento;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoConsultaMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.UltimosMovimientosDTO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class MovimientosBOImpl.
 */
@Component
public class MovimientosBOImpl implements MovimientosBO {
    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The ultimos movimientos dao. */
    @Autowired
    private UltimosMovimientosDAO ultimosMovimientosDAO;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MovimientosBOImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.cuentas.bo.UltimosMovimientosBO#
     * obtenerUltimosMovimientos(ar.com.santanderrio.obp.cuentas.entities.
     * ConsultaUltimosMovimientos)
     */
    @Override
    @Cacheable(value = CacheConstants.Names.CACHE_ULTIMOS_MOVIMIENTOS, key = "#filtroMovimientos.cuenta.cliente.nup")
    public Respuesta<UltimosMovimientosDTO> obtenerMovimientos(ConsultaUltimosMovimientos filtroMovimientos) {
        
        return obtenerMovimientosSinCache(filtroMovimientos);
    }
    
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.MovimientosBO#obtenerMovimientosSinCache(ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos)
     */
    @Override
    public Respuesta<UltimosMovimientosDTO> obtenerMovimientosSinCache(ConsultaUltimosMovimientos filtroMovimientos) {
        
        UltimosMovimientosDTO movimientosDTO = new UltimosMovimientosDTO();

        movimientosDTO.setMovimientos(new ArrayList<Movimiento>());

        Boolean sinMovimientosDelDia = Boolean.FALSE;

        Respuesta<UltimosMovimientosDTO> respuestaMovimientosDelDiaDTO = null;

        boolean consultoMovimientosDelDia = consultoMovimientosDelDia(filtroMovimientos);
        boolean consultoMovimientosConsolidados = consultoMovimientosConsolidados(filtroMovimientos);

        if (consultoMovimientosDelDia) {
            Boolean soloDelDia = !consultoMovimientosConsolidados;
            
            filtroMovimientos.setTipoConsulta(TipoConsultaMovimientos.MOVIMIENTOS_DEL_DIA);
            ConsultaUltimosMovimientos filtroMovimientosFechaDia = setearFechaDelDia(filtroMovimientos);
            respuestaMovimientosDelDiaDTO = consultarMovimientos(filtroMovimientosFechaDia);

            if (EstadoRespuesta.OK.equals(respuestaMovimientosDelDiaDTO.getEstadoRespuesta())) {
                ordenarMovimientos(respuestaMovimientosDelDiaDTO.getRespuesta());
                movimientosDTO.setMovimientos(respuestaMovimientosDelDiaDTO.getRespuesta().getMovimientos());
                if (soloDelDia) {
                    return respuestaFactory.crearRespuestaOk(respuestaMovimientosDelDiaDTO.getRespuesta());
                }
            }
            if (EstadoRespuesta.WARNING.equals(respuestaMovimientosDelDiaDTO.getEstadoRespuesta())) {
                sinMovimientosDelDia = Boolean.TRUE;
                if (soloDelDia) {
                    return respuestaFactory.crearRespuestaWarning(movimientosDTO, StringUtils.EMPTY,
                            TipoError.SIN_MOVIMIENTOS, StringUtils.EMPTY);
                }
            }

            if (EstadoRespuesta.ERROR.equals(respuestaMovimientosDelDiaDTO.getEstadoRespuesta())) {

                if (soloDelDia) {
                    return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.SIN_MOVIMIENTOS,
                            StringUtils.EMPTY);
                }
                respuestaMovimientosDelDiaDTO = respuestaFactory.crearRespuestaWarning(
                        respuestaMovimientosDelDiaDTO.getRespuesta(), StringUtils.EMPTY,
                        TipoError.ERROR_PARCIAL_GRILLA_MOVIMIENTOS_DIA,
                        CodigoMensajeConstantes.ERROR_GRILLA_MOVIMIENTOS_DIA);
            }
        }

        Boolean sinMovimientosConsolidados = Boolean.FALSE;
        Respuesta<UltimosMovimientosDTO> respuestaMovimientosConsolidadosDTO = null;
        
        if (consultoMovimientosConsolidados) {
            Boolean soloConsolidados = !consultoMovimientosDelDia;
            
            filtroMovimientos.setTipoConsulta(TipoConsultaMovimientos.MOVIMIENTO_CONSOLIDADOS);
            respuestaMovimientosConsolidadosDTO = consultarMovimientos(filtroMovimientos);

            if (EstadoRespuesta.OK.equals(respuestaMovimientosConsolidadosDTO.getEstadoRespuesta())
                    && movimientosIsNotEmpty(respuestaMovimientosConsolidadosDTO.getRespuesta())) {
                if (soloConsolidados) {
                    return respuestaFactory.crearRespuestaOk(respuestaMovimientosConsolidadosDTO.getRespuesta());
                }
                movimientosDTO.getMovimientos()
                        .addAll(respuestaMovimientosConsolidadosDTO.getRespuesta().getMovimientos());
            }
            if (EstadoRespuesta.WARNING.equals(respuestaMovimientosConsolidadosDTO.getEstadoRespuesta())) {
                sinMovimientosConsolidados = Boolean.TRUE;
                if (soloConsolidados) {
                    return respuestaFactory.crearRespuestaWarning(movimientosDTO, StringUtils.EMPTY,
                            TipoError.SIN_MOVIMIENTOS, StringUtils.EMPTY);
                }
            }

            if (EstadoRespuesta.WARNING.equals(respuestaMovimientosConsolidadosDTO.getEstadoRespuesta()) &&
            	sesionParametros.getBusquedaDefaultCincuentaYTresDiasRTL()) {
            		return respuestaFactory.crearRespuestaWarning(movimientosDTO, StringUtils.EMPTY,
                            TipoError.SIN_MOVIMIENTOS, StringUtils.EMPTY);
            }
            
            if (EstadoRespuesta.ERROR.equals(respuestaMovimientosConsolidadosDTO.getEstadoRespuesta())) {

                if (soloConsolidados) {
                    return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.SIN_MOVIMIENTOS,
                            StringUtils.EMPTY);
                }
                respuestaMovimientosConsolidadosDTO = respuestaFactory.crearRespuestaWarning(
                        respuestaMovimientosConsolidadosDTO.getRespuesta(), StringUtils.EMPTY,
                        TipoError.ERROR_PARCIAL_GRILLA_MOVIMIENTOS,
                        CodigoMensajeConstantes.ERROR_GRILLA_MOVIMIENTOS_CONSOLIDADOS);
            }
        }
        if (sinMovimientosDelDia && sinMovimientosConsolidados) {
            return respuestaFactory.crearRespuestaWarning(movimientosDTO, StringUtils.EMPTY,
                    TipoError.SIN_MOVIMIENTOS, StringUtils.EMPTY);
        }
        
        if (EstadoRespuesta.WARNING.equals(respuestaMovimientosDelDiaDTO.getEstadoRespuesta()) 
                && EstadoRespuesta.WARNING.equals(respuestaMovimientosConsolidadosDTO.getEstadoRespuesta())) {
            
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.SIN_MOVIMIENTOS,
                    StringUtils.EMPTY);
        }
        Respuesta<UltimosMovimientosDTO> respuesta = null;
        if (EstadoRespuesta.WARNING.equals(respuestaMovimientosDelDiaDTO.getEstadoRespuesta())) {
            
            if(sinMovimientosDelDia) {
                respuesta = respuestaMovimientosConsolidadosDTO;
            } else {
                respuesta = respuestaMovimientosDelDiaDTO;
            }
        }
        
        if (EstadoRespuesta.WARNING.equals(respuestaMovimientosConsolidadosDTO.getEstadoRespuesta())) {
            
            if(sinMovimientosConsolidados) {
                respuesta = respuestaMovimientosDelDiaDTO;
            } else {
                respuesta = respuestaMovimientosConsolidadosDTO;
            }
        }
        if (EstadoRespuesta.OK.equals(respuestaMovimientosConsolidadosDTO.getEstadoRespuesta())
                && EstadoRespuesta.OK.equals(respuestaMovimientosDelDiaDTO.getEstadoRespuesta())) {
            
            respuesta = respuestaFactory.crearRespuestaOk(movimientosDTO);
        }

        respuesta.setRespuesta(movimientosDTO);

        return respuesta;
    }
    
    private ConsultaUltimosMovimientos setearFechaDelDia (ConsultaUltimosMovimientos filtroMovimientos) {
    	
    	ConsultaUltimosMovimientos filtroMovimientosDelDia = new ConsultaUltimosMovimientos();
		try {
			filtroMovimientosDelDia = (ConsultaUltimosMovimientos) BeanUtils.cloneBean(filtroMovimientos);
			
	    	Date fechaBase = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaBase);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			fechaBase = cal.getTime();
	    	
	    	filtroMovimientosDelDia.setFechaDesde(fechaBase);
	    		    	
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return filtroMovimientosDelDia;
    	
    }

    /**
	 * Movimientos is not empty.
	 *
	 * @param movimientosDTO
	 *            the movimientos DTO
	 * @return true, if successful
	 */
    private boolean movimientosIsNotEmpty(UltimosMovimientosDTO movimientosDTO) {
        
        return movimientosDTO != null 
                && !CollectionUtils.isEmpty(movimientosDTO.getMovimientos());
    }

    /**
	 * Consultar movimientos.
	 *
	 * @param filtroMovimientos
	 *            the filtro movimientos
	 * @return the respuesta
	 */
    private Respuesta<UltimosMovimientosDTO> consultarMovimientos(ConsultaUltimosMovimientos filtroMovimientos) {

        Respuesta<UltimosMovimientosDTO> respuesta = null;
        UltimosMovimientosDTO movimientosDTO = null;
        try {
            movimientosDTO = ultimosMovimientosDAO.consultarExtractoUltimosMovimientos(filtroMovimientos);

            if (movimientosDTO == null ||  movimientosDTO.getMovimientos() == null) {
                respuesta = respuestaFactory.crearRespuestaWarning(movimientosDTO, StringUtils.EMPTY,
                        TipoError.SIN_MOVIMIENTOS, StringUtils.EMPTY);
            } else {
                respuesta = respuestaFactory.crearRespuestaOk(movimientosDTO);
            }
        } catch (DAOException e) {
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.SIN_MOVIMIENTOS,
                    StringUtils.EMPTY);
        }
        return respuesta;
    }

    /**
	 * Ordenar movimientos.
	 *
	 * @param movimientosDTO
	 *            the movimientos DTO
	 */
    private void ordenarMovimientos(UltimosMovimientosDTO movimientosDTO) {

        if (movimientosIsNotEmpty(movimientosDTO)) {

            Collections.sort(movimientosDTO.getMovimientos(), new Comparator<Movimiento>() {
                @Override
                public int compare(Movimiento movimiento1, Movimiento movimiento2) {
                    int resultado = movimiento2.getFechaMovimiento().compareTo(movimiento1.getFechaMovimiento());
                	if (resultado != 0) {
                		return resultado;
                	}
                    resultado = movimiento2.getHoraMovimiento().compareTo(movimiento1.getHoraMovimiento());
                	return resultado;
                }
            });
        }
    }

    /**
     * Consulto movimientos del dia.
     *
     * @param filtroMovimientos
     *            the filtro movimientos
     * @return true, if successful
     */
    private boolean consultoMovimientosDelDia(ConsultaUltimosMovimientos filtroMovimientos) {
        Date fechaHoy = new Date();
        return DateUtils.isSameDay(fechaHoy, filtroMovimientos.getFechaHasta());
    }

    /**
     * Consulto movimientos del dia.
     *
     * @param filtroMovimientos
     *            the filtro movimientos
     * @return true, if successful
     */
    private boolean consultoMovimientosConsolidados(ConsultaUltimosMovimientos filtroMovimientos) {

        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date today = c.getTime();
        return filtroMovimientos.getFechaDesde().before(today);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.bo.MovimientosBO#limpiarCache(
     * ar.com.santanderrio.obp.servicios.cuentas.entities.
     * ConsultaUltimosMovimientos)
     */
    @Override
    @CacheEvict(value = CacheConstants.Names.CACHE_ULTIMOS_MOVIMIENTOS, key = "#filtroMovimientos.cuenta.cliente.nup")
    public void limpiarCache(ConsultaUltimosMovimientos filtroMovimientos) {
        LOGGER.info("Se limpia la cache: {}", filtroMovimientos.getCuenta().getCliente().getNup());
    }
    
}
