package ar.com.santanderrio.obp.servicios.citi.dao;

import static org.mockito.Mockito.when;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.citi.dao.impl.CuentasCitiDAOImpl;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentasCitiInEntity;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentasCitiOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class CuentasCitiDAOTest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        CuentasCitiDAOTest.CuentasCitiDAOTestConfiguration.class})
public class CuentasCitiDAOTest extends IatxBaseDAOTest {

    /** The Constant SERVICIO_CNSCTACONV. */
    private static final String SERVICIO_CNSCTACONV = "CNSCTACONV";

    /** The Constant VERSION_100. */
    private static final String VERSION_100 = "100";

    /** The Constant TRAMA_RESPONSE_PESOS. */
    private static final String TRAMA_RESPONSE_PESOS = "200000000000P04HTML0009900010371274268  ********00843465000000092017091215053400000000IBF11ZG8000000000000CNSCTACONV1000000            71274268    00        00 015007251201709121505230000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0015000000000õ0072õ0769õ007003759305õARSõACTEõ769õ000003759305õ0720769588000037593054õ07õ0001õ1õ90õ0353õSõ1õ0220274624õ0013õARSõ0167777100002202746241õ1õAõ";

    /** The Constant MONEDA_PESOS. */
    private static final String MONEDA_PESOS = "peso";
    
    /** The Constant MONEDA_DOLAR. */
    private static final String MONEDA_DOLAR = "dolar";

    /** The Constant MENSAJE_ERROR. */
    private static final String MENSAJE_ERROR = "Error de Iatx.";

    /** The Constant SERVICIO_CNINTERVI. */
    private static final String SERVICIO_CNINTERVI = "CNINTERVI";

    /** The Constant CONSULTA_CUENTA_ACCESO. */
    private static final String CONSULTA_CUENTA_ACCESO = StringUtils.EMPTY;

    /** The Constant CONSULTA_CUENTA_CITI. */
    private static final String CONSULTA_CUENTA_CITI = "000123";

    /** The Constant CONSULTA_CUENTA_ALTAIR. */
    private static final String CONSULTA_CUENTA_ALTAIR = "000123";

    /** The Constant CONSULTA_CUENTA_CITI_DIVISA. */
    private static final String CONSULTA_CUENTA_CITI_DIVISA = "PESOS";

    /** The Constant CONSULTA_CUENTA_CITI_APLICACION. */
    private static final String CONSULTA_CUENTA_CITI_APLICACION = "OBP";

    /** The Constant CONSULTA_CUENTA_CITI_SUCURSAL. */
    private static final String CONSULTA_CUENTA_CITI_SUCURSAL = "001";

    /** The Constant CONSULTA_CUENTA_CITI_CUENTA_RIO. */
    private static final String CONSULTA_CUENTA_CITI_CUENTA_RIO = "000123";

    /** The Constant CONSULTA_CUENTA_CITI_CBU_CUENTA_CITI. */
    private static final String CONSULTA_CUENTA_CITI_CBU_CUENTA_CITI = "00112233";

    /** The prestamo cuota paga DAO. */
    @Autowired
    private CuentasCitiDAOImpl cuentasCitiDAO;

    /**
     * The Class PrestamoCuotaPagaDAOTestConfiguration.
     */
    @Configuration
    public static class CuentasCitiDAOTestConfiguration {

        /**
         * Prestamo cuota paga DAO.
         *
         * @return the prestamo cuota paga DAO
         */
        @Bean
        public CuentasCitiDAO cuentasCitiDAO() {
            return new CuentasCitiDAOImpl();
        }
    }

    /**
     * Probar cuentas citi.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void probarCuentasCiti() throws IatxException, DAOException {
        String servicio = SERVICIO_CNSCTACONV;
        String version = VERSION_100;

        CuentasCitiInEntity consultaTraspasoManualInEntity = crearCuentasCitiInEntity();
        String tramaResponsePesos = TRAMA_RESPONSE_PESOS;
        when(iatxSender
                .send(Matchers.argThat(new IatxMatcher(servicio, version))))
                        .thenReturn(tramaResponsePesos);
        CuentasCitiOutEntity outEntity = cuentasCitiDAO
                .ejecutarConsultaCuentaCity(consultaTraspasoManualInEntity,
                        MONEDA_PESOS);
        Assert.assertNotNull(outEntity.getCodProducto());
    }

    /**
     * Crear cuentas citi in entity.
     *
     * @return the cuentas citi in entity
     */
    private CuentasCitiInEntity crearCuentasCitiInEntity() {
        CuentasCitiInEntity consultaTraspasoManualInEntity = new CuentasCitiInEntity();
        consultaTraspasoManualInEntity.setCliente(new Cliente());
        consultaTraspasoManualInEntity.setAcceso(CONSULTA_CUENTA_ACCESO);
        consultaTraspasoManualInEntity.setCuentaCiti(CONSULTA_CUENTA_CITI);
        consultaTraspasoManualInEntity.setCuentaAltair(CONSULTA_CUENTA_ALTAIR);
        consultaTraspasoManualInEntity.setDivisa(CONSULTA_CUENTA_CITI_DIVISA);
        consultaTraspasoManualInEntity
                .setAplicacion(CONSULTA_CUENTA_CITI_APLICACION);
        consultaTraspasoManualInEntity
                .setSucursal(CONSULTA_CUENTA_CITI_SUCURSAL);
        consultaTraspasoManualInEntity
                .setCuentaRio(CONSULTA_CUENTA_CITI_CUENTA_RIO);
        consultaTraspasoManualInEntity
                .setCbuCuentaCiti(CONSULTA_CUENTA_CITI_CBU_CUENTA_CITI);
        return consultaTraspasoManualInEntity;
    }

}
