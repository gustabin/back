package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoPermitidoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoOutEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class PrestamoPermitidoDAOImpl.
 *
 */
@Component
public class PrestamoPermitidoDAOImpl extends IatxBaseDAO implements PrestamoPermitidoDAO {
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoPermitidoDAOImpl.class);

    /**
     * The Constant OK_CODIGO_RETORNO.
     */
    private static final int OK_CODIGO_RETORNO = 0;

    /**
     * The Constant MONEDA_CONSULTA.
     */
    private static final String MONEDA_CONSULTA = "ARS";

    /**
     * The Constant DESTINO_FONDOS.
     */
    private static final String DESTINO_FONDOS = "99000";

    public static final String TIPO_PREACORDADO = "PREACORDADO    ";
    public static final String TIPO_PREAPROBADO = "PREAPROBADO    ";

    private static final String CUENTA_PAQUETE_VACIA = "000000000000";

    /**
     * The iatx comm.
     */
    @Autowired
    private IatxComm iatxComm;


    @Override
    public PrestamoPermitidoOutEntity consultarPrestamosPermitidosPreacordados(PrestamoPermitidoInEntity entity)
            throws DAOException {
        return consultarPrestamosPermitidos(entity, TIPO_PREACORDADO);
    }
    @Override
    public PrestamoPermitidoOutEntity consultarPrestamosPermitidosPreaprobados(PrestamoPermitidoInEntity entity)
            throws DAOException {
        return consultarPrestamosPermitidos(entity, TIPO_PREAPROBADO);
    }

    /**
     * Consultar iatx para Transferencia.
     *
     * @param entity the entity
     * @return entidad con la informacion de retorno de iatx de Transferencia
     * @throws DAOException the DAO exception
     */
    private PrestamoPermitidoOutEntity consultarPrestamosPermitidos(PrestamoPermitidoInEntity entity, String tipoPrestamo) throws DAOException {
        String servicio = "CNSPMOPERM";
        String version = "120";
        IatxRequest iatxRequest = new IatxRequest(servicio, version);
        try {
            IatxRequestData iatxRequestData = generateRequestData(entity);
            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            int errorCode = iatxResponse.getErrorCode();
            if (OK_CODIGO_RETORNO == errorCode) {

                PrestamoPermitidoOutEntity prestamoPermitidoOut = processTrama(iatxResponse.getTrama(), PrestamoPermitidoOutEntity.class);
                return filtrarPreacordados(prestamoPermitidoOut, tipoPrestamo);
            } else {
                // manejar respuesta ERROR
                throw new DAOException("Error al consultar Prestamos Permitidos", String.valueOf(errorCode));
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        } catch (Exception e) {
            if (e.getClass().equals(DAOException.class)) {
                LOGGER.error("Error consultando prestamos para la cuenta ", entity.getCuenta().getNroCuentaProducto());
                throw (DAOException) e;
            } else {
                LOGGER.error(e.getMessage(), e);
            }
            throw new DAOException(e);
        }
    }


    /**
     * Genera el objeto IatxRequestData para llamar al servicio iatx.
     *
     * @param entity the entity
     * @return the iatx request data
     */
    private IatxRequestData generateRequestData(PrestamoPermitidoInEntity entity) {
        Cuenta cuenta = entity.getCuenta();
        String sucursal = StringUtils.stripStart(cuenta.getNroSucursal(), "0");
        String sucursalFormateada = StringUtils.leftPad(sucursal, 4, "0");
        String numeroCuenta = StringUtils.stripStart(cuenta.getNroCuentaProducto(), "0");
        String numeroCuentaFormateada = StringUtils.leftPad(numeroCuenta, 12, "0");
        String sucursalPaquete = StringUtils.stripStart(cuenta.getSucursalPaquete(), "0");
        String sucursalPaqueteFormateada = StringUtils.leftPad(sucursalPaquete, 4, "0");
        String numeroPaquete = StringUtils.stripStart(cuenta.getNumeroPaquete(), "0");
        String numeroPaqueteFormateada = StringUtils.leftPad(numeroPaquete, 12, "0");
        IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
        iatxRequestData.addBodyValue(sucursalFormateada);
        iatxRequestData.addBodyValue(numeroCuentaFormateada);
        iatxRequestData.addBodyValue(MONEDA_CONSULTA);
        if(CUENTA_PAQUETE_VACIA.equals(numeroPaqueteFormateada) ){
            iatxRequestData.addBodyValue(StringUtils.leftPad("", 4, " "));
            iatxRequestData.addBodyValue(StringUtils.leftPad("", 12, " "));
        }else{
            iatxRequestData.addBodyValue(sucursalPaqueteFormateada);
            iatxRequestData.addBodyValue(numeroPaqueteFormateada);
        }
        iatxRequestData.addBodyValue(DESTINO_FONDOS);
        return iatxRequestData;
    }

    /**
     * Filtra la lista de prestamos permitodos segun el tipo
     */
    private PrestamoPermitidoOutEntity filtrarPreacordados(PrestamoPermitidoOutEntity prestamoPermitidoOut, String tipoPrestamo) {
        List<PrestamoPermitidoEntity> listaPrestamosPrecordados = new ArrayList<PrestamoPermitidoEntity>();
        for (PrestamoPermitidoEntity prestamo : prestamoPermitidoOut.getListaResult()) {
            if (tipoPrestamo.equals(prestamo.getTipoOferta())) {
                listaPrestamosPrecordados.add(prestamo);
            }
        }
        prestamoPermitidoOut.setListaResult(listaPrestamosPrecordados);
        prestamoPermitidoOut.setCantidadOcurrencias((long) listaPrestamosPrecordados.size());
        return prestamoPermitidoOut;
    }

}
