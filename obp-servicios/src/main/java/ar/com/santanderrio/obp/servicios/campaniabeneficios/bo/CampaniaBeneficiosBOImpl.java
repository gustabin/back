package ar.com.santanderrio.obp.servicios.campaniabeneficios.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.dao.CampaniaBeneficiosDAO;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.dao.ConsultaCampaniaBeneficiosDAO;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.dto.SuscCampaniaBeneficiosInDTO;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.entities.RepuestaSPSuscCampaniaBeneficiosEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.GestorEventoComercialDAO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;

/**
 * The Class CampaniaBeneficiosBOImpl.
 */
@Component
public class CampaniaBeneficiosBOImpl implements CampaniaBeneficiosBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CampaniaBeneficiosBOImpl.class);

    /** The campania Beneficios DAO. */
    @Autowired
    private CampaniaBeneficiosDAO campaniaBeneficiosDAO;

    /** The consuta campania Beneficios DAO. */
    @Autowired
    private ConsultaCampaniaBeneficiosDAO consultaCampaniaBeneficiosDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;
    
    /** The gestorEventoComercialDAO. */
    @Autowired
    private GestorEventoComercialDAO gestorEventoComercialDAO;

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.campaniabeneficios.bo.CampaniaBeneficiosBO#
     * suscripcionCampaniaBeneficios(ar.com.santanderrio.obp.servicios.
     * campaniabeneficios.dto.SuscCampaniaBeneficiosInDTO)
     */
    @Override
    public Respuesta<RepuestaSPSuscCampaniaBeneficiosEntity> suscripcionCampaniaBeneficios(
            SuscCampaniaBeneficiosInDTO inDTO) {
        try {
            RepuestaSPSuscCampaniaBeneficiosEntity repuestaSPSuscCampaniaBeneficiosEntity = campaniaBeneficiosDAO
                    .suscribirCampaniaBeneficios(inDTO);
            return respuestaFactory.crearRespuestaOk(repuestaSPSuscCampaniaBeneficiosEntity);
        } catch (DAOException e) {
            LOGGER.error("Error suscribiendo al beneficio con los siguientes datos {}", inDTO, e);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.campaniabeneficios.bo.CampaniaBeneficiosBO#
     * consSuscripcionCampaniaBeneficios(ar.com.santanderrio.obp.servicios.
     * campaniabeneficios.dto.SuscCampaniaBeneficiosInDTO)
     */
    @Override
    public Respuesta<RepuestaSPSuscCampaniaBeneficiosEntity> consSuscripcionCampaniaBeneficios(
            SuscCampaniaBeneficiosInDTO inDTO) {
        try {
            RepuestaSPSuscCampaniaBeneficiosEntity repuestaSPSuscCampaniaBeneficiosEntity = consultaCampaniaBeneficiosDAO
                    .consultaCampaniaBeneficiosDAO(inDTO);
            return respuestaFactory.crearRespuestaOk(repuestaSPSuscCampaniaBeneficiosEntity);
        } catch (DAOException e) {
            LOGGER.error("Error Consultando suscripcion a beneficio con los siguientes datos {}", inDTO, e);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        }
    }

	/**
	 * Informar gestion AC.
	 *
	 * @param cliente the cliente
	 * @param ofertaComercialDTO the oferta comercial DTO
	 * @return the boolean
	 */
	@Override
	public Boolean informarGestionAC(Cliente cliente, OfertaComercialDTO ofertaComercialDTO) {
		LOGGER.debug("Se informara la gestion AC");
		try {
			return gestorEventoComercialDAO.informarGestionAC(cliente, ofertaComercialDTO);
		} catch (DAOException e) {
            LOGGER.error("Error al informar AC.", e);
            return false;
        }
       
	}
}
