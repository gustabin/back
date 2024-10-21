package ar.com.santanderrio.obp.servicios.referidos.bo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.referidos.dao.ReferidosDAO;

@Component("ReferidosBO")
public class ReferidosBOImpl implements ReferidosBO {

	@Autowired
	private ReferidosDAO referidosDAO;
	
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	@Autowired
	private SesionCliente sesionCliente;
	
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReferidosBOImpl.class);
	
	
	@Override
	public Respuesta<String> obtenerTokenReferidos(String nup) throws Exception{
		String token = referidosDAO.obtenerTokenReferidos(nup);
		return respuestaFactory.crearRespuestaOk(token);
	}
	
	@Override
    public boolean tieneNupEnListado() {
    	boolean tieneNupEnLista = Boolean.FALSE;
    	
    	List<String> listaNups = new ArrayList<String>();
    	try {
    		//Se obtiene lista de nups con clientes a los que se les dio de baja la tarjeta de coordenadas
    		listaNups = referidosDAO.obtenerNups("listadoNupsReferidos.txt");
    	} catch (DAOException e) {
    		LOGGER.info("Error al obtener listado de nups de baja de coordenadas");
    	}
    	for (String nup : listaNups) {
    		if (nup.equals(sesionCliente.getCliente().getNup())) {
    			tieneNupEnLista = Boolean.TRUE;
    			break;
    		}   
    	}
    	
    	return tieneNupEnLista;
    }
    
}
