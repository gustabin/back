package ar.com.santanderrio.obp.servicios.comun.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.dao.ConsultaPadronOCuitDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronesCuitOutEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.PadronABAOutEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.PadronBCRAOutEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.PadronOutEntity;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;

@Component
public class ConsultaPadronCuitBOImpl implements ConsultaPadronCuitBO {
    
    /** The consulta padron cuit DAO. */
    @Autowired
    private ConsultaPadronOCuitDAO consultaPadronOCuitDAO;
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaPadronCuitBOImpl.class);
    

    @Override
    public PadronOutEntity consultaPadron(ConsultaPadronCuitInEntity entity) throws DAOException {
        
        try {
            ConsultaPadronesCuitOutEntity padrones = consultaPadronOCuitDAO.consultaPadrones(entity);
            if(padrones.getCantidadABA() == 0 && padrones.getCantidadBcra() == 0) {
                LOGGER.error("No se encontraron datos en los padrones para {}",entity.getCliente().getDni());
                return null;
            }
            // busco en el padron del ABA. Si solo hay uno, lo retorno.
            if (padrones.getCantidadABA()== 1) {
                return padrones.getPadronABA().get(0);
            }
            for(PadronABAOutEntity cuit : padrones.getPadronABA()) {
                if (coincidenFechas(cuit.getAbaFechaNacimiento(), entity.getCliente().getFechaNacimiento())) {
                    return cuit;
                }
            }
            // busco en el padron del BCRA. Si solo hay uno, lo retorno.
            if (padrones.getCantidadBcra()== 1) {
                return padrones.getPadronBCRA().get(0);
            }
            for (PadronBCRAOutEntity cuit : padrones.getPadronBCRA()) {
                int apellidoSize = (entity.getCliente().getApellido1().trim().length() >= 6) 
                        ? 6: entity.getCliente().getApellido1().trim().length();
                String apellido = entity.getCliente().getApellido1().trim().substring(0, apellidoSize);
                int nombreSize = (entity.getCliente().getNombre().trim().length() >= 5) 
                        ? 5: entity.getCliente().getNombre().trim().length();
                String nombre = entity.getCliente().getNombre().trim().substring(0, nombreSize);
                if (cuit.getBcraApellidoNombre1().contains(apellido)&&
                        cuit.getBcraApellidoNombre1().contains(nombre)) {
                    return cuit;
                }
            }
        } catch (DAOException e) {
            LOGGER.error("Ocurrio un error");
            throw e;
        }
        LOGGER.error("No se encontraron coincidencias en los padrones para {}",entity.getCliente().getDni());
        return null;
    }

    private boolean coincidenFechas(String abaFechaNacimiento, String fechaNacimiento) {
        return fechaNacimiento.equals(FechaUtils.modificarFormatoFechas(abaFechaNacimiento, "dd/MM/yyyy", "yyyyMMdd"));
    }

}
