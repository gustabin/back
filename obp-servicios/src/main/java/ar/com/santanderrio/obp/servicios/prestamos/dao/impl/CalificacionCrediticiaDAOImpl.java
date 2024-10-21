/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.prestamos.dao.CalificacionCrediticiaDAO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CalificacionCrediticiaOutEntity;

/**
 * The Class CalificacionCrediticiaDAOImpl.
 *
 * @author
 */
@Component("calificacionCrediticiaDAO")
public class CalificacionCrediticiaDAOImpl extends IatxBaseDAO implements CalificacionCrediticiaDAO {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CalificacionCrediticiaDAOImpl.class);

    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;

    /** The Constant CODIGO_ERROR_NO_LINEA_CREDITICIA. */
    private static final int CODIGO_ERROR_NO_LINEA_CREDITICIA = 16010007;

	/** The Constant CODIGO_ERROR_OPERACION_INHABILITADA. */
	private static final int CODIGO_ERROR_OPERACION_INHABILITADA = 10099906;

	/** The Constant ERROR_SYSTEM_CCO. */
	private static final String ERROR_SYSTEM_CCO = "CCO";

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /**
     * Consultar iatx para CalificacionCrediticia.
     *
     * @param cuenta
     *            the cuenta
     * @param operacionServicio
     *            the operacion servicio
     * @return entidad de CalificacionCrediticia con la informacion de retorno
     *         de iatx.
     * @throws DAOException
     *             the DAO exception
     */
    @Override
    public CalificacionCrediticiaOutEntity obtenerSituacionCrediticia(Cuenta cuenta, String operacionServicio)
            throws DAOException {
        String servicio = "CNSPMOCACR";
        String version = "130";
        LOGGER.info("Consultar iatx con servicio {} version {} y datos {}.", servicio, version, cuenta);
        IatxRequest iatxRequest = new IatxRequest(servicio, version);
        try {
            IatxRequestData iatxRequestData = generateRequestDate(cuenta, operacionServicio);
            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            if (OK_CODIGO_RETORNO == iatxResponse.getErrorCode()) {
                // manejar respuesta OK
                return processTrama(iatxResponse.getTrama(), CalificacionCrediticiaOutEntity.class);
            } else if (CODIGO_ERROR_NO_LINEA_CREDITICIA == iatxResponse.getErrorCode()) {
                throw new DAOException("Sin linea crediticia disponible.",
                        String.valueOf(CODIGO_ERROR_NO_LINEA_CREDITICIA));
                // throw new
                // DAOException(CodigoMensajeConstantes.LINEA_CREDITICIA_SIN_MONTO_PERMITIDO);
            } else if (CODIGO_ERROR_OPERACION_INHABILITADA == iatxResponse.getErrorCode() && ERROR_SYSTEM_CCO.equals(iatxResponse.getErrorSystem())) {
            	throw new DAOException("Sin linea crediticia disponible.", String.valueOf(CODIGO_ERROR_OPERACION_INHABILITADA));
            }
            // manejar respuesta ERROR
            throw new DAOException(CodigoMensajeConstantes.PRESTAMO_INHABILITADO);
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException();
        }
    }

    /**
     * Genera el objeto IatxRequestData para llamar al servicio iatx.
     *
     * @param cuenta
     *            the cuenta
     * @param operacionServicio
     *            the operacion servicio
     * @return objeto con la estructura de iatx para realizar el request.
     */
    private IatxRequestData generateRequestDate(Cuenta cuenta, String operacionServicio) {

        String sucursal = StringUtils.stripStart(cuenta.getNroSucursal(), "0");
        String sucursalFormateada = StringUtils.leftPad(sucursal, 3, "0");
        String numeroCuenta = StringUtils.stripStart(cuenta.getNroCuentaProducto(), "0");
        String numeroCuentaFormateada = StringUtils.leftPad(numeroCuenta, 7, "0");

        IatxRequestData iatxRequestData = new IatxRequestData(cuenta.getCliente());
        iatxRequestData.addBodyValue(operacionServicio);
        iatxRequestData.addBodyValue(StringUtils.leftPad(cuenta.getTipoCuentaSinUnificar(), 2, '0'));
        iatxRequestData.addBodyValue(sucursalFormateada);
        iatxRequestData.addBodyValue(numeroCuentaFormateada);

        return iatxRequestData;
    }
}