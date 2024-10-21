/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sucursales.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.SucursalDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * @author sergio.e.goldentair
 *
 */
@Repository("sucursalDAO")
public class SucursalDAOImpl extends IatxBaseDAO implements SucursalDAO {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SucursalDAOImpl.class);

    /** El servicio CNSDATOSUC. */
    private static final String CNSDATOSUC = "CNSDATOSUC";

    /** El servicio CNSDATOSUC version 100. */
    private static final String CNSDATOSUC_VERSION = "100";

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.sucursales.dao.impl.SucursalDAO#
     * cnsSucursales(ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente)
     */
    @Override
    public SucursalOutEntity cnsSucursales(ResumenCliente resumenCliente) throws DAOException {
        SucursalOutEntity salida = new SucursalOutEntity();
        String ofiRellamada = "0000";
        do {
            try {
                IatxRequest request = new IatxRequest(CNSDATOSUC, CNSDATOSUC_VERSION);
                IatxRequestData requestData = new IatxRequestData(resumenCliente, false);
                requestData.addBodyValue("2");
                requestData.addBodyValue("0072");
                requestData.addBodyValue("0000");
                requestData.addBodyValue(ofiRellamada);

                request.setData(requestData);
                IatxResponse iatxResponse = iatxComm.exec(request);

                Integer codigoRetorno = iatxResponse.getErrorCode();

                if (Integer.valueOf(0).equals(codigoRetorno)) {
                    SucursalOutEntity sucursalOutEntity = processTrama(iatxResponse.getTrama(),
                            SucursalOutEntity.class);
                    ofiRellamada = sucursalOutEntity.getOficinaRellamada();
                    salida.getSucursales().addAll(sucursalOutEntity.getSucursales());
                } else {
                    String mensajeErrorIatx = iatxResponse.getErrorMessage();
                    LOGGER.info("Error de iatx {}", mensajeErrorIatx);
                }
            } catch (IatxException e) {
                LOGGER.error("No se han podido obtener las sucursales por error en iatx.", e);
                throw new DAOException(e);
            }
        } while (!"0000".equals(ofiRellamada));
        return salida;
    }

}
