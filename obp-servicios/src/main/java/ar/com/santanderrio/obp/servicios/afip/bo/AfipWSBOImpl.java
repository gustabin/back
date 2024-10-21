package ar.com.santanderrio.obp.servicios.afip.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.afip.dao.AfipWSDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
@Qualifier("afipWSBOImpl")
public class AfipWSBOImpl implements AfipWSBO {

    /** The afip WSDAO. */
    @Autowired
    @Qualifier("afipWSDAOImpl")
    private AfipWSDAOImpl afipWSDAO;

    /** The Constant LOGGER. */
    protected static final Logger LOGGER = LoggerFactory.getLogger(AfipWSBOImpl.class);

    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The respuesta factory. */
    @Autowired
    protected RespuestaFactory respuestaFactory;

    /** The sesion parametros. */
    @Autowired
    protected SesionParametros sesionParametros;

    /** The sesion cliente. */
    @Autowired
    protected SesionCliente sesionCliente;

    /**
     * consultaDeudaProvisional.
     *
     * @return the respuesta
     */
    @Override
    public Respuesta<Boolean> tieneDeudaConAfip() {
        try {
            Boolean response = afipWSDAO.consultaTieneDeuda(sesionCliente.getCliente().getNumeroCUILCUIT());
            LOGGER.info("Consulta de deuda previsional - Respuesta OK");
            return respuestaFactory.crearRespuestaOk(response);
        } catch (DAOException e) {
            LOGGER.error("Error consultando DAO Deuda previsional");
            return this.respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_SERVICIO_AFIP, CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_GENERICO);
        }
    }
}