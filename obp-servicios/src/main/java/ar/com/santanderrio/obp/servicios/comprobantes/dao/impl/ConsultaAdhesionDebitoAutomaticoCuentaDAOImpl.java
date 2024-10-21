/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ConsultaAdhesionDebitoAutomaticoCuentaDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresasAdheridasDebitoAutoInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresasAdheridasDebitoAutoOutEntity;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * La clase CNSDDIADHGDAOImpl.
 * 
 * @author dante.omar.olmedo
 *
 */
@Component
public class ConsultaAdhesionDebitoAutomaticoCuentaDAOImpl extends IatxBaseDAO
        implements ConsultaAdhesionDebitoAutomaticoCuentaDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaAdhesionDebitoAutomaticoCuentaDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;
	
	/**
	 * Indicador FIN_DATOS
	 */
	private static final String FIN_DATOS = "N";
	
	/**
	 * Nombre del servicio
	 */
	private static final String SERVICIO = "CNSDDIADHG";
	
	/**
	 * Version del servicio
	 */
	private static final String VERSION = "120";

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.dao.
	 * ConsultaAdhesionDebitoAutomaticoCuentaDAO#consultar(ar.com.santanderrio.
	 * obp.servicios.comprobantes.dao.entity. EmpresasAdheridasDebitoAutoInEntity)
	 */
	@Cacheable(cacheNames = CacheConstants.Names.CACHE_CNS_DDIADHG, key = "#inEntity.cliente.nup")
	@Override
	public EmpresasAdheridasDebitoAutoOutEntity consultar(EmpresasAdheridasDebitoAutoInEntity inEntity)
	        throws DAOException {
		
		EmpresasAdheridasDebitoAutoOutEntity outEntity = new EmpresasAdheridasDebitoAutoOutEntity();

		LOGGER.info("Se cachea por nup {} flujo {}.", inEntity.getCliente().getNup(), SERVICIO);
		
		try {
			
			IatxRequest iatxRequest = new IatxRequest(SERVICIO, VERSION);
			inEntity.setBloqueRellamada("");
			iatxRequest.setData(getIATXRequestData(inEntity));
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			
			if (OK_CODIGO_RETORNO == errorCode) {
				
				outEntity = processTrama(iatxResponse.getTrama(), EmpresasAdheridasDebitoAutoOutEntity.class);
				
				outEntity = checkearRellamada(outEntity, inEntity);
				
			} else {
				
				LOGGER.error("Codigo de error no esperado de iatx en servicio" + SERVICIO + VERSION);
				outEntity.setCodigoRetornoExtendido(String.valueOf(iatxResponse.getErrorCode()));
				
			}
		} catch (IatxException e) {
			
			LOGGER.error("Error al consultar las adhesiones a debito automatico para el cliente {}", inEntity.getCliente().getNup(), e);
			throw new DAOException();
			
		}
		
		return outEntity;
	}
	
	/**
	 * Obtenemos el request para llamar al servicio IATX CNSDDIADHG
	 * @param inEntity
	 * @return
	 */
	private IatxRequestData getIATXRequestData(EmpresasAdheridasDebitoAutoInEntity inEntity) {
		
		IatxRequestData iatxRequestData = new IatxRequestData(inEntity.getCliente());
		// A30
		//iatxRequestData.addBodyValue(inEntity.getNombreApellido().length() < 30 ? ISBANStringUtils.fillStr(inEntity.getNombreApellido(), 30) : inEntity.getNombreApellido().substring(0, 30));
		iatxRequestData.addBodyValue(ISBANStringUtils.normalizarCampoAlfanumericoIatx(inEntity.getNombreApellido(), 30));
		// A100
		iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(inEntity.getBloqueRellamada(), 100));

		return iatxRequestData;
	}
	
	/**
	 * Checkeamos rellamada en servicio IATX CNSDDIADHG
	 * @param outEntity
	 * @param inEntity
	 * @return
	 * @throws IatxException 
	 */
	private EmpresasAdheridasDebitoAutoOutEntity checkearRellamada(EmpresasAdheridasDebitoAutoOutEntity outEntity, EmpresasAdheridasDebitoAutoInEntity inEntity) throws IatxException {
		
		if(FIN_DATOS.equalsIgnoreCase(outEntity.getFinDatos())) {
			
			inEntity.setBloqueRellamada(outEntity.getBloqueRellamada()); // obtenemos de la salida anterior, el bloque con datos para rellamar servicio
			IatxRequest iatxRequest = new IatxRequest(SERVICIO, VERSION);
			iatxRequest.setData(getIATXRequestData(inEntity));
			
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			
			if (OK_CODIGO_RETORNO == errorCode) {
				
				EmpresasAdheridasDebitoAutoOutEntity outEntityRellamada = new EmpresasAdheridasDebitoAutoOutEntity();
				outEntityRellamada = processTrama(iatxResponse.getTrama(), EmpresasAdheridasDebitoAutoOutEntity.class);
				outEntity.getEmpresas().addAll(outEntityRellamada.getEmpresas());
				outEntity.setBloqueRellamada(outEntityRellamada.getBloqueRellamada());
				outEntity.setFinDatos(outEntityRellamada.getFinDatos());
				checkearRellamada(outEntity, inEntity);
				
			} else {
				
				LOGGER.error("Codigo de error no esperado de iatx en servicio " + SERVICIO + VERSION);
				outEntity.setCodigoRetornoExtendido(String.valueOf(iatxResponse.getErrorCode()));
				
			}
		}
		
		return outEntity;
	}

}
