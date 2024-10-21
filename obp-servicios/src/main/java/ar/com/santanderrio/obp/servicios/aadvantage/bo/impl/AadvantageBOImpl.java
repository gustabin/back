/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aadvantage.bo.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.aadvantage.bo.AadvantageBO;
import ar.com.santanderrio.obp.servicios.aadvantage.dao.AadvantageDAO;
import ar.com.santanderrio.obp.servicios.aadvantage.dto.DetallePuntosAadvantageDTO;
import ar.com.santanderrio.obp.servicios.aadvantage.dto.SolicitudMillasAadvantageInDTO;
import ar.com.santanderrio.obp.servicios.aadvantage.dto.SolicitudMillasAadvantageOutDTO;
import ar.com.santanderrio.obp.servicios.aadvantage.entities.SolicitudMillasAadvantageInEntity;
import ar.com.santanderrio.obp.servicios.aadvantage.entities.SolicitudMillasAadvantageOutEntity;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class AadvantageBOImpl.
 */
@Component
public class AadvantageBOImpl implements AadvantageBO {

	/** The aadvantage DAO. */
	@Autowired
	private AadvantageDAO aadvantageDAO;
	
	/** The sesion paramentros. */
	@Autowired
	private SesionParametros sesionParamentros;
	
	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** El numero de registros a solicitar por el SP. */
    @Value("${ADVANTAGE.NROREGISTROS_DEFAULT}")
    private String nroRegistrosDefault;
	
    /** El numero de filas por pagina. */
    @Value("${ADVANTAGE.MESES_XPAGINA}")
    private String mesesXpagina;
    
    
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.aadvantage.bo.AadvantageBO#consultarMillasAadvantage(ar.com.santanderrio.obp.servicios.aadvantage.dto.SolicitudMillasAadvantageInDTO)
	 */
	@Override
	public Respuesta<SolicitudMillasAadvantageOutDTO> consultarMillasAadvantage(SolicitudMillasAadvantageInDTO inDTO) {
		SolicitudMillasAadvantageInEntity inEntity = new SolicitudMillasAadvantageInEntity();
		SolicitudMillasAadvantageOutEntity respuestaDAO = new SolicitudMillasAadvantageOutEntity();
		inEntity.setCliente(inDTO.getCliente());
		inEntity.setCantMeses(Integer.parseInt(nroRegistrosDefault));
		Integer paginaCount = this.determinarContadorDePaginas(inDTO);
		
		SolicitudMillasAadvantageOutDTO respuesta = new SolicitudMillasAadvantageOutDTO();
		
		try {
			respuestaDAO = aadvantageDAO.consultarMillas(inEntity);
			
			if(respuestaDAO.getIsErrorTecnico()) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			} else if (!respuestaDAO.getIsErrorTecnico() && respuestaDAO.getIsSinMillas()) {
				respuesta.setMillas(StringUtils.EMPTY);
			} else {
				List<DetallePuntosAadvantageDTO> listaOrdenadaPorFecha = respuestaDAO.getListaDetalleMillas();
				this.ordenarOperacionesPorFecha(listaOrdenadaPorFecha);
				
				//se determina si hay mas paginas con registros
				Integer filasPagina = Integer.parseInt(mesesXpagina);
				respuesta.setHayMasMillas(listaOrdenadaPorFecha.size() > filasPagina * paginaCount);
				
				//se crea un subset con los registros de la pagina solicitada
				Integer fromIndex = filasPagina * paginaCount - filasPagina;
				Integer toIndex = respuesta.getHayMasMillas() ? filasPagina * paginaCount : listaOrdenadaPorFecha.size();
				respuesta.setListaDetalleMillas(listaOrdenadaPorFecha.subList(fromIndex, toIndex));
				
				respuesta.setMillas(this.calcularTotalMillas(respuestaDAO.getListaDetalleMillas()));
			}
			
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		
		return respuestaFactory.crearRespuestaOk(respuesta);
	}
	
	/**
	 * Determinar contador de paginas.
	 *
	 * @param inDTO
	 *            the in DTO
	 * @return the integer
	 */
	private Integer determinarContadorDePaginas(SolicitudMillasAadvantageInDTO inDTO) {
	
		Integer paginaCount;
		if(inDTO.getMostrarMasMeses() != null && inDTO.getMostrarMasMeses()) {
			paginaCount = sesionParamentros.getContadorPaginaMillasAadvantage() + Integer.valueOf(1);
		}else {
			paginaCount = Integer.valueOf(1);
		}		
		sesionParamentros.setContadorPaginaMillasAadvantage(paginaCount);
		return paginaCount;
	}
	
	/**
	 * Ordenar operaciones por fecha.
	 *
	 * @param listaDetallePuntos
	 *            the lista detalle puntos
	 */
	private void ordenarOperacionesPorFecha(List<DetallePuntosAadvantageDTO> listaDetallePuntos) {
		Collections.sort(listaDetallePuntos, compararFecha);
	}
	
	/** Comparator por fecha. */
    Comparator<DetallePuntosAadvantageDTO> compararFecha = new Comparator<DetallePuntosAadvantageDTO>() {
        public int compare(DetallePuntosAadvantageDTO d1, DetallePuntosAadvantageDTO d2){
            String f1 = d1.getMesAnio();
            String f2 = d2.getMesAnio();
                     
          return compararFechas(f1, f2);
        }
    };

    /**
	 * Comparar fechas.
	 *
	 * @param f1
	 *            the f 1
	 * @param f2
	 *            the f 2
	 * @return the int
	 */
    private int compararFechas(String f1, String f2){
        Integer a1 = getAnio(f1);
        Integer a2 = getAnio(f2);
        
        if(a1.compareTo(a2) != 0){
            return a1.compareTo(a2);
        }else{
            Integer mes1 = getMes(f1);
            Integer mes2 = getMes(f2);
            return mes1.compareTo(mes2);
        }
    }
    
    /**
	 * Gets the anio.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the anio
	 */
    private Integer getAnio(String fecha){
        int indexSpc = fecha.trim().indexOf(" ");
        String anio = fecha.substring(indexSpc +1); //año
        return new Integer(anio);
        
    }
    
    /**
	 * Gets the mes.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the mes
	 */
    private Integer getMes(String fecha){
        int indexSpc = fecha.trim().indexOf(" ");
        String mes = fecha.substring(0,indexSpc).trim();
      
        if("Enero".equalsIgnoreCase(mes)) return 1;
        if("Febrero".equalsIgnoreCase(mes)) return 2;
        if("Marzo".equalsIgnoreCase(mes)) return 3;
        if("Abril".equalsIgnoreCase(mes)) return 4;
        if("Mayo".equalsIgnoreCase(mes)) return 5;
        if("Junio".equalsIgnoreCase(mes)) return 6;
        if("Julio".equalsIgnoreCase(mes)) return 7;
        if("Agosto".equalsIgnoreCase(mes)) return 8;
        if("Septiembre".equalsIgnoreCase(mes)) return 9;
        if("Setiembre".equalsIgnoreCase(mes)) return 9;
        if("Octubre".equalsIgnoreCase(mes)) return 10;
        if("Noviembre".equalsIgnoreCase(mes)) return 11;
        if("Diciembre".equalsIgnoreCase(mes)) return 12;
      
        return 0;
    }
    
    /**
	 * Calcular total millas.
	 *
	 * @param listaMillas
	 *            the lista millas
	 * @return the string
	 */
    private String calcularTotalMillas(List<DetallePuntosAadvantageDTO> listaMillas) {
    	
    	Integer countTotal = 0;
    	for(DetallePuntosAadvantageDTO filaMes : listaMillas) {
    		countTotal += Integer.parseInt(filaMes.getPuntosTotal());
    	}
    	return countTotal.toString();
    }
    
    
    
    /**
	 * Rand between.
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @return the int
	 */
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
    
    
}
