package ar.com.santanderrio.obp.servicios.microfrontend.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ar.com.santanderrio.obp.base.dao.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import org.springframework.stereotype.Component;

@Component
public class MicrofrontendsBOImpl implements MicrofrontendsBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MicrofrontendsBOImpl.class);

    /** The file path. */
    @Autowired
    private ArchivoDeRecursosDAO archivoResource;

    @Override
    public Boolean consultaVisibilidadPorNup(String nup, String microFrontName, Boolean errValue) {
        String nombreArchivo = consultaNombreArchivoMicroFront(microFrontName);
        Boolean existeNup=false;
        try {
            for (String lineaTexto : archivoResource.leerArchivo(nombreArchivo)) {
                if(lineaTexto.indexOf(nup)>-1) {
                    existeNup=true;
                    break;
                }
            }
        }
        catch(DAOException e)
        {
            LOGGER.info("No se puede cargar el archivo {},", nombreArchivo, e);
            return errValue;
        }
        return existeNup;
    }

    private static final String consultaNombreArchivoMicroFront(String microFrontName)
    {
        String cap = microFrontName.substring(0, 1).toUpperCase() + microFrontName.substring(1);
        return "ListadoNups" + cap + ".txt";
    }
}
