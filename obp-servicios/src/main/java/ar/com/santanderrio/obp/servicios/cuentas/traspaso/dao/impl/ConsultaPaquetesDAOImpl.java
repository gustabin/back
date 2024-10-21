/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.impl;


import ar.com.santanderrio.obp.servicios.api.accounts.PackagesApi;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.PackagesEntity;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.PackagesResponseEntity;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.PaqueteEntity;
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
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidationEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.ConsultaPaquetesDAO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.InformacionCuentaPaqueteInEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ConsultaPaquetesDAOImpl.
 */
@Component
public class ConsultaPaquetesDAOImpl extends IatxBaseDAO implements ConsultaPaquetesDAO {

    /** The Constant VERSION_CNSPAQCNLS. */
    private static final String VERSION_CNSPAQCNLS = "100";

    /** The Constant SERVICIO_CNSPAQCNLS. */
    private static final String SERVICIO_CNSPAQCNLS = "CNSPAQCNLS";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaPaquetesDAOImpl.class);

    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;
    private static final String PAQUETE_INDIVIDUO = "1";
    private static final String PAQUETE_EMPRESA = "2";

    private static final String PAQUETE_ESTADO_ACTIVO = "A";
    private static final String PAQUETE_ESTADO_CANCEL = "C";
    private static final String PAQUETE_ESTADO_PRECANCEL = "P";
    private static final String PAQUETE_ESTADO_MIGRATE = "R";
    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    @Autowired
    private PackagesApi accountsApi;
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.
     * ConsultaPaquetesDAO#consultar(ar.com.santanderrio.obp.servicios.cuentas.
     * traspaso.entity.ConsultaPaquetesInEntity)
     */
    @Override
    @Cacheable(cacheNames = CacheConstants.Names.CACHE_NUP_CNSPAQCNLS, key = "#consultaPaquetesInEntity.cliente.nup")
    public ConsultaPaquetesOutEntity consultar(ConsultaPaquetesInEntity consultaPaquetesInEntity) throws DAOException {
        ConsultaPaquetesOutEntity consultaPaquetesOutEntity = new ConsultaPaquetesOutEntity();

        if (!ValidationEntity.validate(consultaPaquetesInEntity)) {
            consultaPaquetesOutEntity.setCodigoRetornoExtendido("0001");
            return consultaPaquetesOutEntity;
        }
        try {
            consultaPaquetesOutEntity = ejecucionConsulta(consultaPaquetesInEntity);
            if (consultaPaquetesOutEntity == null) {
                throw new DAOException("Error de servicio");
            }
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return consultaPaquetesOutEntity;

    }

    /**
     * Ejecucion consulta.
     *
     * @param consultaPaquetesInEntity
     *            the consulta paquetes in entity
     * @return the consulta paquetes out entity
     * @throws DAOException
     *             the DAO exception
     */
    private ConsultaPaquetesOutEntity ejecucionConsulta(ConsultaPaquetesInEntity consultaPaquetesInEntity)
            throws DAOException {
        String servicio = SERVICIO_CNSPAQCNLS;
        String version = VERSION_CNSPAQCNLS;
        boolean llamaIatx=false;
        IatxRequest iatxRequest = new IatxRequest(servicio, version);
        ConsultaPaquetesOutEntity consultaPaquetesOutEntity = null;
        //obtener las cuentas a filtrar de consultaPaquetesInEntity
        //llamar a la api
        try {
            PackagesResponseEntity cuentasApi = accountsApi.getPackagesByNup(consultaPaquetesInEntity.getCliente().getNup());
            LOGGER.info("ML respuesta api." + cuentasApi.toString());
            //verificar cuenta api vs cta iatx
            //armar salida paquetes
            consultaPaquetesOutEntity = generateResponseApiPackages(cuentasApi);
        } catch (ApiException e) {
            LOGGER.info("ML error al consultar la api de paquetes, se llama iatx");
            llamaIatx = true;
        }

        if (Boolean.TRUE.equals(llamaIatx)) {
            try {
                IatxRequestData iatxRequestData = generateRequestDataCNSPAQCNLS(consultaPaquetesInEntity);

                iatxRequest.setData(iatxRequestData);
                IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
                int errorCode = iatxResponse.getErrorCode();
                if (OK_CODIGO_RETORNO == errorCode) {
                    consultaPaquetesOutEntity = processTrama(iatxResponse.getTrama(), ConsultaPaquetesOutEntity.class);
                }
            } catch (IatxException e) {
                LOGGER.error(e.getMessage(), e);
                throw new DAOException(e);
            }
        }

        return consultaPaquetesOutEntity;
    }

    /**
     * Generate request data CNSPAQCNLS.
     *
     * @param consultaPaquetesInEntity
     *            the consulta paquetes in entity
     * @return the iatx request data
     */
    private IatxRequestData generateRequestDataCNSPAQCNLS(ConsultaPaquetesInEntity consultaPaquetesInEntity) {
        IatxRequestData iatxRequestData = new IatxRequestData(consultaPaquetesInEntity.getCliente());
        if (consultaPaquetesInEntity.getCuentasFiltradas() == null) {
            iatxRequestData.addBodyValue(consultaPaquetesInEntity.getCantidadDeCuentas());
            iatxRequestData.addBodyValue(consultaPaquetesInEntity.getTipoCuenta());
            iatxRequestData.addBodyValue(consultaPaquetesInEntity.getSucursalCuenta());
            iatxRequestData.addBodyValue(consultaPaquetesInEntity.getNumeroCuenta());
        }else{
            iatxRequestData.addBodyValue(consultaPaquetesInEntity.getCantidadDeCuentas());
            for(InformacionCuentaPaqueteInEntity cuenta : consultaPaquetesInEntity.getCuentasFiltradas()){
                iatxRequestData.addBodyValue(cuenta.getTipoCuenta());
                iatxRequestData.addBodyValue(cuenta.getSucursalCuenta());
                iatxRequestData.addBodyValue(cuenta.getNumeroCuenta());
            }
        }
        return iatxRequestData;
    }
    private ConsultaPaquetesOutEntity generateResponseApiPackages(PackagesResponseEntity packages){
        //salida iatx
        LOGGER.info("ML entre generateResponseApiPackages");
        ConsultaPaquetesOutEntity consultaPaquetesOutEntity = new ConsultaPaquetesOutEntity();
        // lista de paquetes iatx
        List<PaqueteEntity> packageIatx = new ArrayList<PaqueteEntity>();

        for (int ind=0; ind < packages.getPackages().size();ind++) {
            PaqueteEntity packageUnit=new PaqueteEntity();
            //tipo de cuenta
            packageUnit.setTipoCuenta(getPackageType(packages.getPackages().get(ind), "type"));
            packageUnit.setEntidad("0072");
            packageUnit.setSucursalCuenta(getCharFromString(packages.getPackages().get(ind).getBranchCode(), 1, 4));
            packageUnit.setNumeroCuenta(getCharFromString(packages.getPackages().get(ind).getMainAccountId(),5,12));
            packageUnit.setDivisa(getPackageType(packages.getPackages().get(ind), "currency"));
            packageUnit.setProducto(getCharFromString(packages.getPackages().get(ind).getMainAccountId(),1,3));
            packageUnit.setSubProducto("0001");
            packageUnit.setEntidadPaquete("0072");
            packageUnit.setCentroAltairPaquete(packages.getPackages().get(ind).getBranchCode());
            packageUnit.setNumeroCuentaAltair(packages.getPackages().get(ind).getMainAccountId());
            packageUnit.setPaquete(packages.getPackages().get(ind).getId());
            packageUnit.setProductoPaquete(packages.getPackages().get(ind).getProductCode());
            packageUnit.setSubProductoPaquete(packages.getPackages().get(ind).getSubProductCode());

            if (Boolean.TRUE.equals(packages.getPackages().get(ind).getIsCompanyPackage())){
                packageUnit.setIndicadorPaquete(PAQUETE_EMPRESA);
            }else {
                packageUnit.setIndicadorPaquete(PAQUETE_INDIVIDUO);
            }

            packageUnit.setIndicadorEstadoPaquete(getStatus(packages.getPackages().get(ind).getStatus()));

            packageUnit.setSucursal(getCharFromString(packages.getPackages().get(ind).getBranchCode(), 1, 4));

            packageIatx.add(packageUnit);
        }

        //retorna la lista de paquetes parseada
        consultaPaquetesOutEntity.setPaquetes(packageIatx);
        consultaPaquetesOutEntity.setCantidadCuentas(new Long(packages.getPackages().size()));
        if (packageIatx.size() > 0) {
            consultaPaquetesOutEntity.setCodigoRetornoExtendido("0");
        }else{
            consultaPaquetesOutEntity.setCodigoRetornoExtendido("1");
        }
        return consultaPaquetesOutEntity;
    }
    private String getPackageType(PackagesEntity packages, String data){
        String accountType = null;
        String currencyType = null;

        if (getCharFromString(packages.getMainAccountId(),1, 3).equals("07")) {
            accountType = "09";
            currencyType = "ARS";
        } else if (getCharFromString(packages.getMainAccountId(),1, 3).equals("05")) {
            accountType = "00";
            currencyType = "ARS";
        } else if (getCharFromString(packages.getMainAccountId(),1, 3).equals("06")) {
            accountType = "03";
            currencyType = "USD";
        } else if (getCharFromString(packages.getMainAccountId(),1, 3).equals("02")) {
            accountType = "01";
            currencyType = "ARS";
        } else if (getCharFromString(packages.getMainAccountId(),1, 3).equals("03")) {
            accountType = "04";
            currencyType = "USD";
        }

        if (data.equals("currency")){
            return currencyType;
        }else{
            return  accountType;
        }

    }
    private String getCharFromString(String type, int firstChar, int endChar){
        return type.substring(firstChar,endChar);
    }
    private String getStatus(String status){
        String statusOut=null;
        if ((status).equalsIgnoreCase("ACTIVE")){
            statusOut= PAQUETE_ESTADO_ACTIVO;
        }
        if ((status).equalsIgnoreCase("CANCELLED")){
            statusOut =PAQUETE_ESTADO_CANCEL;
        }
        if ((status).equalsIgnoreCase("MIGRATED")){
            statusOut =PAQUETE_ESTADO_MIGRATE;
        }

        if ((status).equalsIgnoreCase("PRECANCELLED")){
            statusOut= PAQUETE_ESTADO_PRECANCEL;
        }

        return statusOut;
    }

}
