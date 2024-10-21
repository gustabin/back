package ar.com.santanderrio.obp.servicios.comprobantes.dao;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaComprobanteInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaComprobanteOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaDetallePMCOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.DetallePMCEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.impl.ComprobantesPagoMisCuentasDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class ComprobantesPagoMisCuentasDAOTest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ComprobantesPagoMisCuentasDAOTest.CNSPESPAGCDAOITConfiguration.class })
public class ComprobantesPagoMisCuentasDAOTest extends IatxBaseDAOTest {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ComprobantesPagoMisCuentasDAOTest.class);

    private static final String ERROR = "Error";
    
    /** The service DAO. */
    @Autowired
    @InjectMocks
    private ComprobantesPagoMisCuentasDAO serviceDAO;

    /**
     * The Class CNSPESPAGCDAOITConfiguration.
     */
    @Configuration
    public static class CNSPESPAGCDAOITConfiguration {

        /**
         * Service DAO.
         *
         * @return the comprobantes pago mis cuentas DAO
         */
        @Bean
        public ComprobantesPagoMisCuentasDAO serviceDAO() {
            return new ComprobantesPagoMisCuentasDAOImpl();
        }

    }

    /**
     * Consulta test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test
    public void consultaTest() throws DAOException, IatxException {
        ConsultaComprobanteInEntity entity = new ConsultaComprobanteInEntity();
        entity.setCliente(obtenerCliente());
        entity.setFechaDesde("");
        entity.setFechaHasta("");
        entity.setNroCuenta("");
        entity.setNroOrdenFirmante("");
        entity.setNroTarjetaBanelco("");
        entity.setSucursalCuenta("");
        entity.setTipoCuenta("");

        String cnscotcnResponse = "200000000000P04HTML0009900010320064145  ********00559753000000122017050200554000000000IBF31020000000000000CNSPESPAGC1000000N           20064145    00        00 000590370201705020055330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0668900000000õ00047õPLPSõ7593061701         õ20161103õ1117õ20161110õB5514-67373896      õ0õ00000000023104õ09õ0100õ000003750162õ5852õ454889õ0õ4517660118184661    õVIBSõ4546590903356130   õ20161109õ2009õ20161109õSU SALDO EN PESOS   õ0õ00000000400000õ09õ0100õ000003750162õ4512õ038935õ0õ4517660118184661    õBHVIõ4304970007250763   õ20161109õ2010õ20161109õSU SALDO EN PESOS   õ0õ00000000400000õ09õ0100õ000003750162õ8400õ790595õ0õ4517660118184661    õCTISõ20996919346        õ20161111õ1832õ20161111õ                    õ0õ00000000029162õ09õ0100õ000003750162õ8412õ430762õ0õ4517660118184661    õMTAJõ20302195723102016  õ20161114õ1318õ20161114õ201610              õ0õ00000000000100õ09õ0100õ000003750162õ6870õ571886õ0õ4517660118184661    õVIFNõ4540730007947493   õ20161116õ1115õ20161116õ                    õ0õ00000000000200õ09õ0100õ000003750162õ5115õ386127õ0õ4517660118184661    õBHVIõ4304970007250763   õ20161116õ1116õ20161116õ                    õ0õ00000000000200õ09õ0100õ000003750162õ9232õ862964õ0õ4517660118184661    õBHVIõ4304970007250763   õ20161117õ1700õ20161117õ                    õ0õ00000000000200õ09õ0100õ000003750162õ3852õ455438õ0õ4517660118184661    õREPEõ1159295358         õ20161125õ2249õ20161125õ0000001206618660    õ0õ00000000005000õ09õ0100õ000003750162õ3499õ517775õ0õ4517660118184661    õMVCMõ0100910687623      õ20161126õ1412õ20161206õ                    õ0õ00000000040000õ09õ0100õ000003750162õ8091õ202804õ0õ4517660118184661    õREPEõ1159295358         õ20161127õ2125õ20161127õ0000001207429886    õ0õ00000000005000õ09õ0100õ000003750162õ6003õ509008õ0õ4517660118184661    õREPEõ1159295358         õ20161129õ1206õ20161129õ0000001208064003    õ0õ00000000005000õ09õ0100õ000003750162õ8013õ224971õ0õ4517660118184661    õREPEõ1159295358         õ20161207õ1849õ20161207õ0000001213121704    õ0õ00000000005000õ09õ0100õ000003750162õ6854õ825119õ0õ4517660118184661    õVIFNõ4540730007947493   õ20161212õ1041õ20161212õ                    õ0õ00000000800000õ09õ0100õ000003750162õ2965õ082312õ0õ4517660118184661    õVIRBõ4258210001332432   õ20161212õ1041õ20161212õ                    õ0õ00000000800000õ09õ0100õ000003750162õ2965õ261366õ0õ4517660118184661    õPLPSõ7593061701         õ20161219õ1433õ20161219õ                    õ0õ00000000023606õ01õ0100õ000003760330õ3805õ962319õ0õ4517660118184661    õBHVIõ4304970007250763   õ20161219õ1437õ20161219õ                    õ0õ00000000600000õ09õ0100õ000003750162õ3154õ687892õ0õ4517660118184661    õVIBSõ4546590903356130   õ20161219õ1437õ20161219õ                    õ0õ00000000800000õ09õ0100õ000003750162õ3154õ898986õ0õ4517660118184661    õDTVPõ057000767256977000 õ20161225õ1344õ20161225õ                    õ0õ00000000015000õ09õ0100õ000003750162õ1594õ239054õ0õ4517660118184661    õVIFNõ4540730007947493   õ20170104õ1429õ20170104õSU SALDO EN PESOS   õ0õ00000000800000õ09õ0100õ000003750162õ7217õ237987õ0õ4517660118184661    õVIRBõ4258210001332432   õ20170104õ1429õ20170104õ                    õ0õ00000000800000õ09õ0100õ000003750162õ7217õ359011õ0õ4517660118184661    õVIRIõ4509950155938479   õ20170104õ1429õ20170104õ                    õ0õ00000001000000õ09õ0100õ000003750162õ7217õ548822õ0õ4517660118184661    õPLPSõ7593061701         õ20170104õ1439õ20170110õB5514-76361983      õ0õ00000000023185õ09õ0100õ000003750162õ3621õ480741õ0õ4517660118184661    õBHVIõ4304970007250763   õ20170104õ1439õ20170111õSU SALDO EN PESOS   õ0õ00000000550000õ09õ0100õ000003750162õ2602õ579886õ0õ4517660118184661    õVIBSõ4546590903356130   õ20170104õ1439õ20170111õSU SALDO EN PESOS   õ0õ00000000500000õ09õ0100õ000003750162õ2602õ767586õ0õ4517660118184661    õOSDEõ60061361109701     õ20170107õ0023õ20170112õ6011611614284750    õ0õ00000000032040õ09õ0100õ000003750162õ9329õ490457õ0õ4517660118184661    õREPEõ1159295358         õ20170201õ0716õ20170201õ00000000001243906634õ0õ00000000010000õ09õ0100õ000003750162õ2065õ256611õ0õ4517660118184661    õVIFNõ4540730007947493   õ20170201õ2220õ20170201õSU SALDO EN PESOS   õ0õ00000000800000õ09õ0100õ000003750162õ0432õ841790õ0õ4517660118184661    õVIRBõ4258210001332432   õ20170201õ2220õ20170201õSU SALDO EN PESOS   õ0õ00000000162372õ09õ0100õ000003750162õ1040õ129920õ0õ4517660118184661    õVIRIõ4509950155938479   õ20170201õ2225õ20170201õ                    õ0õ00000001000000õ09õ0100õ000003750162õ3524õ172063õ0õ4517660118184661    õBHVIõ4304970007250763   õ20170207õ2319õ20170207õSU SALDO EN PESOS   õ0õ00000000360000õ09õ0100õ000003750162õ8935õ605890õ0õ4517660118184661    õREPEõ1159295358         õ20170211õ0906õ20170211õ00000000001249723617õ0õ00000000010000õ09õ0100õ000003750162õ0303õ752976õ0õ4517660118184661    õREPEõ1159295358         õ20170307õ2209õ20170307õ00000000001262101728õ0õ00000000010000õ09õ0100õ000003750162õ0645õ731341õ0õ4517660118184661    õBHVIõ4304970007250763   õ20170313õ2102õ20170314õSU SALDO EN PESOS   õ0õ00000000510000õ09õ0100õ000003750162õ6863õ856941õ0õ4517660118184661    õVIBSõ4546590903356130   õ20170313õ2102õ20170315õSU SALDO EN PESOS   õ0õ00000000600000õ09õ0100õ000003750162õ6863õ115374õ0õ4517660118184661    õVIFNõ4540730007947493   õ20170313õ2102õ20170313õ                    õ0õ00000000800000õ09õ0100õ000003750162õ6863õ426473õ0õ4517660118184661    õVIRBõ4258210001332432   õ20170313õ2102õ20170313õ                    õ0õ00000000043395õ09õ0100õ000003750162õ1912õ843347õ0õ4517660118184661    õVIRIõ4509950155938479   õ20170313õ2102õ20170313õ                    õ0õ00000001000000õ09õ0100õ000003750162õ6863õ985693õ0õ4517660118184661    õSEA õ2030219572330219572õ20170328õ1605õ20170422õ000294827474        õ0õ00000000000001õ09õ0100õ000003750162õ8081õ362816õ0õ4517660118184661    õSEA õ2030219572330219572õ20170329õ0927õ20170423õ000294915785        õ0õ00000000000030õ01õ0100õ000003760330õ7420õ476193õ0õ4517660118184661    õVIFNõ4540730007947493   õ20170417õ1119õ20170417õ                    õ0õ00000000500000õ09õ0100õ000003750162õ3445õ997416õ0õ4517660118184661    õVIRBõ4258210001332432   õ20170417õ1119õ20170417õ                    õ0õ00000000257500õ09õ0100õ000003750162õ7665õ427161õ0õ4517660118184661    õBHVIõ4304970007250763   õ20170417õ1119õ20170417õ                    õ0õ00000001100000õ09õ0100õ000003750162õ3445õ062173õ0õ4517660118184661    õVIBSõ4546590903356130   õ20170417õ1119õ20170417õ                    õ0õ00000000500000õ09õ0100õ000003750162õ3445õ232246õ0õ4517660118184661    õVIRIõ4509950155938479   õ20170417õ1119õ20170417õ                    õ0õ00000000900000õ09õ0100õ000003750162õ3445õ806077õ0õ4517660118184661    õREMOõ3487614357         õ20170417õ1618õ20170417õZLBNPPOM01          õ0õ00000000002000õ09õ0100õ000003750162õ2152õ175239õ0õ4517660118184661    õREMOõ3487614357         õ20170417õ1622õ20170417õ3IBNPPPN31          õ0õ00000000003000õ09õ0100õ000003750162õ7205õ787786õ0õ4517660118184661    õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSPESPAGC", "100"))))
                .thenReturn(cnscotcnResponse);

        ConsultaComprobanteOutEntity res = serviceDAO.consultar(entity);
        Assert.assertNotNull(res);
    }

    /**
     * Consulta error test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test
    public void consultaErrorTest() throws DAOException, IatxException {
        ConsultaComprobanteInEntity entity = new ConsultaComprobanteInEntity();
        entity.setCliente(obtenerCliente());
        entity.setFechaDesde("");
        entity.setFechaHasta("");
        entity.setNroCuenta("");
        entity.setNroOrdenFirmante("");
        entity.setNroTarjetaBanelco("");
        entity.setSucursalCuenta("");
        entity.setTipoCuenta("");

        String cnscotcnResponse = "200000000000P04HTML0009900010320064145  ********00559753000000122017050200554000000000IBF31020000000000000CNSPESPAGC1000000N           20064145    00        00 000590370201705020055330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0668900000010õ000õ01õ7593061701          7593061701          7593061701          7593061701          õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSPESPAGC", "100"))))
                .thenReturn(cnscotcnResponse);

        ConsultaComprobanteOutEntity res = serviceDAO.consultar(entity);
        Assert.assertEquals("10", res.getCodigoRetornoExtendido());
    }

    /**
     * Consulta exception test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test(expected = DAOException.class)
    public void consultaExceptionTest() throws DAOException, IatxException {
        ConsultaComprobanteInEntity entity = new ConsultaComprobanteInEntity();
        entity.setCliente(obtenerCliente());
        entity.setFechaDesde("");
        entity.setFechaHasta("");
        entity.setNroCuenta("");
        entity.setNroOrdenFirmante("");
        entity.setNroTarjetaBanelco("");
        entity.setSucursalCuenta("");
        entity.setTipoCuenta("");

        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSPESPAGC", "100"))))
                .thenThrow(new IatxException());
        ConsultaComprobanteOutEntity res = serviceDAO.consultar(entity);
        Assert.assertEquals("10", res.getCodigoRetornoExtendido());
    }

    /**
     * Consulta async test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test
    public void consultaAsyncTest() throws DAOException, IatxException {
        ConsultaComprobanteInEntity entity = new ConsultaComprobanteInEntity();
        entity.setCliente(obtenerCliente());
        entity.setFechaDesde("");
        entity.setFechaHasta("");
        entity.setNroCuenta("");
        entity.setNroOrdenFirmante("");
        entity.setNroTarjetaBanelco("");
        entity.setSucursalCuenta("");
        entity.setTipoCuenta("");

        String cnscotcnResponse = "200000000000P04HTML0009900010320064145  ********00559753000000122017050200554000000000IBF31020000000000000CNSPESPAGC1000000N           20064145    00        00 000590370201705020055330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0668900000000õ00047õPLPSõ7593061701         õ20161103õ1117õ20161110õB5514-67373896      õ0õ00000000023104õ09õ0100õ000003750162õ5852õ454889õ0õ4517660118184661    õVIBSõ4546590903356130   õ20161109õ2009õ20161109õSU SALDO EN PESOS   õ0õ00000000400000õ09õ0100õ000003750162õ4512õ038935õ0õ4517660118184661    õBHVIõ4304970007250763   õ20161109õ2010õ20161109õSU SALDO EN PESOS   õ0õ00000000400000õ09õ0100õ000003750162õ8400õ790595õ0õ4517660118184661    õCTISõ20996919346        õ20161111õ1832õ20161111õ                    õ0õ00000000029162õ09õ0100õ000003750162õ8412õ430762õ0õ4517660118184661    õMTAJõ20302195723102016  õ20161114õ1318õ20161114õ201610              õ0õ00000000000100õ09õ0100õ000003750162õ6870õ571886õ0õ4517660118184661    õVIFNõ4540730007947493   õ20161116õ1115õ20161116õ                    õ0õ00000000000200õ09õ0100õ000003750162õ5115õ386127õ0õ4517660118184661    õBHVIõ4304970007250763   õ20161116õ1116õ20161116õ                    õ0õ00000000000200õ09õ0100õ000003750162õ9232õ862964õ0õ4517660118184661    õBHVIõ4304970007250763   õ20161117õ1700õ20161117õ                    õ0õ00000000000200õ09õ0100õ000003750162õ3852õ455438õ0õ4517660118184661    õREPEõ1159295358         õ20161125õ2249õ20161125õ0000001206618660    õ0õ00000000005000õ09õ0100õ000003750162õ3499õ517775õ0õ4517660118184661    õMVCMõ0100910687623      õ20161126õ1412õ20161206õ                    õ0õ00000000040000õ09õ0100õ000003750162õ8091õ202804õ0õ4517660118184661    õREPEõ1159295358         õ20161127õ2125õ20161127õ0000001207429886    õ0õ00000000005000õ09õ0100õ000003750162õ6003õ509008õ0õ4517660118184661    õREPEõ1159295358         õ20161129õ1206õ20161129õ0000001208064003    õ0õ00000000005000õ09õ0100õ000003750162õ8013õ224971õ0õ4517660118184661    õREPEõ1159295358         õ20161207õ1849õ20161207õ0000001213121704    õ0õ00000000005000õ09õ0100õ000003750162õ6854õ825119õ0õ4517660118184661    õVIFNõ4540730007947493   õ20161212õ1041õ20161212õ                    õ0õ00000000800000õ09õ0100õ000003750162õ2965õ082312õ0õ4517660118184661    õVIRBõ4258210001332432   õ20161212õ1041õ20161212õ                    õ0õ00000000800000õ09õ0100õ000003750162õ2965õ261366õ0õ4517660118184661    õPLPSõ7593061701         õ20161219õ1433õ20161219õ                    õ0õ00000000023606õ01õ0100õ000003760330õ3805õ962319õ0õ4517660118184661    õBHVIõ4304970007250763   õ20161219õ1437õ20161219õ                    õ0õ00000000600000õ09õ0100õ000003750162õ3154õ687892õ0õ4517660118184661    õVIBSõ4546590903356130   õ20161219õ1437õ20161219õ                    õ0õ00000000800000õ09õ0100õ000003750162õ3154õ898986õ0õ4517660118184661    õDTVPõ057000767256977000 õ20161225õ1344õ20161225õ                    õ0õ00000000015000õ09õ0100õ000003750162õ1594õ239054õ0õ4517660118184661    õVIFNõ4540730007947493   õ20170104õ1429õ20170104õSU SALDO EN PESOS   õ0õ00000000800000õ09õ0100õ000003750162õ7217õ237987õ0õ4517660118184661    õVIRBõ4258210001332432   õ20170104õ1429õ20170104õ                    õ0õ00000000800000õ09õ0100õ000003750162õ7217õ359011õ0õ4517660118184661    õVIRIõ4509950155938479   õ20170104õ1429õ20170104õ                    õ0õ00000001000000õ09õ0100õ000003750162õ7217õ548822õ0õ4517660118184661    õPLPSõ7593061701         õ20170104õ1439õ20170110õB5514-76361983      õ0õ00000000023185õ09õ0100õ000003750162õ3621õ480741õ0õ4517660118184661    õBHVIõ4304970007250763   õ20170104õ1439õ20170111õSU SALDO EN PESOS   õ0õ00000000550000õ09õ0100õ000003750162õ2602õ579886õ0õ4517660118184661    õVIBSõ4546590903356130   õ20170104õ1439õ20170111õSU SALDO EN PESOS   õ0õ00000000500000õ09õ0100õ000003750162õ2602õ767586õ0õ4517660118184661    õOSDEõ60061361109701     õ20170107õ0023õ20170112õ6011611614284750    õ0õ00000000032040õ09õ0100õ000003750162õ9329õ490457õ0õ4517660118184661    õREPEõ1159295358         õ20170201õ0716õ20170201õ00000000001243906634õ0õ00000000010000õ09õ0100õ000003750162õ2065õ256611õ0õ4517660118184661    õVIFNõ4540730007947493   õ20170201õ2220õ20170201õSU SALDO EN PESOS   õ0õ00000000800000õ09õ0100õ000003750162õ0432õ841790õ0õ4517660118184661    õVIRBõ4258210001332432   õ20170201õ2220õ20170201õSU SALDO EN PESOS   õ0õ00000000162372õ09õ0100õ000003750162õ1040õ129920õ0õ4517660118184661    õVIRIõ4509950155938479   õ20170201õ2225õ20170201õ                    õ0õ00000001000000õ09õ0100õ000003750162õ3524õ172063õ0õ4517660118184661    õBHVIõ4304970007250763   õ20170207õ2319õ20170207õSU SALDO EN PESOS   õ0õ00000000360000õ09õ0100õ000003750162õ8935õ605890õ0õ4517660118184661    õREPEõ1159295358         õ20170211õ0906õ20170211õ00000000001249723617õ0õ00000000010000õ09õ0100õ000003750162õ0303õ752976õ0õ4517660118184661    õREPEõ1159295358         õ20170307õ2209õ20170307õ00000000001262101728õ0õ00000000010000õ09õ0100õ000003750162õ0645õ731341õ0õ4517660118184661    õBHVIõ4304970007250763   õ20170313õ2102õ20170314õSU SALDO EN PESOS   õ0õ00000000510000õ09õ0100õ000003750162õ6863õ856941õ0õ4517660118184661    õVIBSõ4546590903356130   õ20170313õ2102õ20170315õSU SALDO EN PESOS   õ0õ00000000600000õ09õ0100õ000003750162õ6863õ115374õ0õ4517660118184661    õVIFNõ4540730007947493   õ20170313õ2102õ20170313õ                    õ0õ00000000800000õ09õ0100õ000003750162õ6863õ426473õ0õ4517660118184661    õVIRBõ4258210001332432   õ20170313õ2102õ20170313õ                    õ0õ00000000043395õ09õ0100õ000003750162õ1912õ843347õ0õ4517660118184661    õVIRIõ4509950155938479   õ20170313õ2102õ20170313õ                    õ0õ00000001000000õ09õ0100õ000003750162õ6863õ985693õ0õ4517660118184661    õSEA õ2030219572330219572õ20170328õ1605õ20170422õ000294827474        õ0õ00000000000001õ09õ0100õ000003750162õ8081õ362816õ0õ4517660118184661    õSEA õ2030219572330219572õ20170329õ0927õ20170423õ000294915785        õ0õ00000000000030õ01õ0100õ000003760330õ7420õ476193õ0õ4517660118184661    õVIFNõ4540730007947493   õ20170417õ1119õ20170417õ                    õ0õ00000000500000õ09õ0100õ000003750162õ3445õ997416õ0õ4517660118184661    õVIRBõ4258210001332432   õ20170417õ1119õ20170417õ                    õ0õ00000000257500õ09õ0100õ000003750162õ7665õ427161õ0õ4517660118184661    õBHVIõ4304970007250763   õ20170417õ1119õ20170417õ                    õ0õ00000001100000õ09õ0100õ000003750162õ3445õ062173õ0õ4517660118184661    õVIBSõ4546590903356130   õ20170417õ1119õ20170417õ                    õ0õ00000000500000õ09õ0100õ000003750162õ3445õ232246õ0õ4517660118184661    õVIRIõ4509950155938479   õ20170417õ1119õ20170417õ                    õ0õ00000000900000õ09õ0100õ000003750162õ3445õ806077õ0õ4517660118184661    õREMOõ3487614357         õ20170417õ1618õ20170417õZLBNPPOM01          õ0õ00000000002000õ09õ0100õ000003750162õ2152õ175239õ0õ4517660118184661    õREMOõ3487614357         õ20170417õ1622õ20170417õ3IBNPPPN31          õ0õ00000000003000õ09õ0100õ000003750162õ7205õ787786õ0õ4517660118184661    õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSPESPAGC", "100"))))
                .thenReturn(cnscotcnResponse);

        Future<ConsultaComprobanteOutEntity> rta = serviceDAO.consultarAsync(entity);
        Future<ConsultaComprobanteOutEntity> rta2 = serviceDAO.consultarAsync(entity);

        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                LOGGER.error(ERROR, e);
            }
        }

        ConsultaComprobanteOutEntity respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {
            LOGGER.error(ERROR, e);
        }
        try {
            ConsultaComprobanteOutEntity comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {
            LOGGER.error(ERROR, e);
        }
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener cliente.
     *
     * @return the cliente
     */
    private Cliente obtenerCliente() {
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        Cliente c1 = new Cliente();
        c1.setNombre("CONSTANCIO PERCY");
        c1.setApellido1("MILANDO");
        c1.setApellido2("M");
        c1.setSegmento(segmento);
        return c1;
    }

    /**
     * Consulta detalle test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test
    public void consultaDetalleTest() throws DAOException, IatxException {
        ConsultaComprobanteInEntity entity = new ConsultaComprobanteInEntity();
        entity.setCliente(obtenerCliente());
        entity.setFechaDesde("");
        entity.setFechaHasta("");
        entity.setNroCuenta("");
        entity.setNroOrdenFirmante("");
        entity.setNroTarjetaBanelco("");
        entity.setSucursalCuenta("");
        entity.setTipoCuenta("");

        String cnscotcnResponse = "200000000000P04HTML0009900010380335191  ********00480675000000172017052612133600000000IBF31495000000000000CNSPESPAGE1200000            80335191    00        00 012134726201705261213280000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0377800000000õCOMPROBANTE VALIDO DE PAGO. CONSERVELO  õ028õ1160002010         õ19052017õ1024õARSõ00000000003000õ00õ0100õ000003733527õ960279232794õ1112õ 1134621840                             õ1160002010         õ17052017õ0937õARSõ00000000003000õ00õ0100õ000003733527õ784656332139õ4172õ 1133533314                             õ1160002010         õ14052017õ1546õARSõ00000000002000õ00õ0100õ000003733527õ547568823207õ0956õ 1132044397                             õ1160002010         õ11052017õ0751õARSõ00000000003000õ00õ0100õ000003733527õ259908040456õ2551õ 1129985732                             õ1160002010         õ22032017õ0733õARSõ00000000002000õ00õ0100õ000003733527õ938814874503õ3301õ 1099903425                             õ1144752634         õ19032017õ2258õARSõ00000000003000õ00õ0100õ000003733527õ735114915012õ4202õ 1098538813                             õ1144752634         õ10032017õ1939õARSõ00000000003000õ00õ0100õ000003733527õ945589816015õ1398õ 1093542508                             õ1144752634         õ10022017õ1249õARSõ00000000003000õ00õ0100õ000003733527õ501793084355õ3049õ 1078844917                             õ1160002010         õ27012017õ0930õARSõ00000000002000õ00õ0100õ000003733527õ280237923747õ7213õ 1071285939                             õ1160002010         õ18012017õ1849õARSõ00000000002000õ00õ0100õ000003733527õ536197235870õ4396õ 1067128076                             õ1144752634         õ16122016õ2019õARSõ00000000005000õ00õ0100õ000003733527õ690344533947õ6166õ 1050414598                             õ1160002010         õ21092016õ0908õARSõ00000000002000õ00õ0100õ000003733527õ219688968153õ7833õ 1008995862                             õ1160002010         õ18082016õ1636õARSõ00000000002000õ00õ0100õ000003733527õ308964714166õ4562õ 992924450                              õ1160002010         õ16082016õ1456õARSõ00000000002000õ00õ0100õ000003733527õ130195030920õ5010õ 991829898                              õ1160002010         õ19072016õ0835õARSõ00000000003000õ00õ0100õ000003733527õ688114941890õ4753õ 977909205                              õ1144752634         õ16062016õ1007õARSõ00000000005000õ00õ0100õ000003733527õ842455253420õ0130õ 962833706                              õ1160002010         õ07062016õ1017õARSõ00000000005000õ00õ0100õ000003733527õ065463420003õ0752õ 958532541                              õ1144752634         õ07062016õ1014õARSõ00000000005000õ00õ0100õ000003733527õ065251118267õ3012õ 958529998                              õ1160002010         õ21042016õ1729õARSõ00000000003000õ00õ0100õ000003733527õ030542246766õ8231õ 937153859                              õ1160002010         õ20042016õ1014õARSõ00000000002000õ00õ0100õ000003733527õ918050362620õ5656õ 936475852                              õ1144752634         õ06032016õ1450õARSõ00000000010000õ00õ0100õ000003733527õ046656038741õ1510õ915723742                               õ1144752634         õ03032016õ1347õARSõ00000000005000õ00õ0100õ000003733527õ783661874239õ8134õ914192082                               õ1160002010         õ19022016õ0930õARSõ00000000005000õ00õ0100õ000003733527õ645019792088õ4030õ908122544                               õ1160002010         õ15022016õ0943õARSõ00000000002000õ00õ0100õ000003733527õ300221494934õ6631õ906214921                               õ1160002010         õ10022016õ0922õARSõ00000000002000õ00õ0100õ000003733527õ866952643619õ5544õ903953406                               õ1160002010         õ06022016õ2052õARSõ00000000002000õ00õ0100õ000003733527õ562774474329õ7210õ902944746                               õ1160002010         õ18122015õ0801õARSõ00000000004000õ00õ0100õ000003733527õ196464104316õ2185õ880096078                               õ1160002010         õ13122015õ2110õARSõ00000000003000õ00õ0100õ000003733527õ811812169336õ9113õ878010914                               õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSPESPAGE", "120"))))
                .thenReturn(cnscotcnResponse);

        ConsultaDetallePMCOutEntity res = serviceDAO.consultarDetalle(entity);
        Assert.assertNotNull(res);
    }

    /**
     * Consulta detalle error test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test
    public void consultaDetalleErrorTest() throws DAOException, IatxException {
        ConsultaComprobanteInEntity entity = new ConsultaComprobanteInEntity();
        entity.setCliente(obtenerCliente());
        entity.setFechaDesde("");
        entity.setFechaHasta("");
        entity.setNroCuenta("");
        entity.setNroOrdenFirmante("");
        entity.setNroTarjetaBanelco("");
        entity.setSucursalCuenta("");
        entity.setTipoCuenta("");

        String cnscotcnResponse = "200000000000P04HTML0009900010380335191  ********00480675000000172017052612133600000000IBF31495000000000000CNSPESPAGE1200000            80335191    00        00 012134726201705261213280000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0668900000010õ000õ01õ7593061701          7593061701          7593061701          7593061701          õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSPESPAGE", "120"))))
                .thenReturn(cnscotcnResponse);

        ConsultaDetallePMCOutEntity res = serviceDAO.consultarDetalle(entity);
        Assert.assertEquals("10", res.getCodigoRetornoExtendido());
    }

    /**
     * Consulta detalle exception test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test(expected = DAOException.class)
    public void consultaDetalleExceptionTest() throws DAOException, IatxException {
        ConsultaComprobanteInEntity entity = new ConsultaComprobanteInEntity();
        entity.setCliente(obtenerCliente());
        entity.setFechaDesde("");
        entity.setFechaHasta("");
        entity.setNroCuenta("");
        entity.setNroOrdenFirmante("");
        entity.setNroTarjetaBanelco("");
        entity.setSucursalCuenta("");
        entity.setTipoCuenta("");

        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSPESPAGE", "120"))))
                .thenThrow(new IatxException());

        ConsultaDetallePMCOutEntity res = serviceDAO.consultarDetalle(entity);
        Assert.assertNotNull(res);
    }

    /**
     * Obtener out entity async test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test
    public void obtenerOutEntityAsyncTest() throws DAOException, IatxException {

        Future<ConsultaComprobanteOutEntity> rta = serviceDAO.obtenerOutEntityErrorAsync();
        Future<ConsultaComprobanteOutEntity> rta2 = serviceDAO.obtenerOutEntityErrorAsync();

        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                LOGGER.error(ERROR, e);
            }
        }

        ConsultaComprobanteOutEntity respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {
            LOGGER.error(ERROR, e);
        }
        try {
            ConsultaComprobanteOutEntity comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {
            LOGGER.error(ERROR, e);
        }
        Assert.assertNotNull(respuesta);
    }

    /**
     * Consulta detalle error generico test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test
    public void consultaDetalleErrorGenericoTest() throws IatxException   {
        ConsultaComprobanteInEntity entity = new ConsultaComprobanteInEntity();
        entity.setCliente(obtenerCliente());
        entity.setFechaDesde("");
        entity.setFechaHasta("");
        entity.setNroCuenta("");
        entity.setNroOrdenFirmante("");
        entity.setNroTarjetaBanelco("");
        entity.setSucursalCuenta("");
        entity.setTipoCuenta("");

        String cnscotcnResponse = "200000000000P04HTML0009900010380335191  ********00480675000000202017052612135300000000IBF27817000000000000CNSPESPAGE1200000            80335191    00        00 012140087201705261213450000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0085200000000õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ006õ00100300390560     õ05072017õ1717õARSõ00000000001310õ00õ0100õ000003733527õ412519954400õ841 õPAGO FACTURA 10758673472 VTO. 26/04/17  õ0001920525        @õ22022017õ0909õARSõ00000000033572õ00õ0100õ000003733527õ519984770794õ5246õPAGO FACTURA 10755714836 VTO. 22/02/17  õ0001920525         õ22122016õ0837õARSõ00000000033572õ00õ0100õ000003733527õ392140185704õ4154õPAGO FACTURA 10752309266 VTO. 22/12/16  õ0001920525         õ25102016õ0844õARSõ00000000033572õ00õ0100õ000003733527õ643108957282õ4261õPAGO FACTURA 10748736883 VTO. 25/10/16  õ0001920525         õ25082016õ0840õARSõ00000000033572õ00õ0100õ000003733527õ062217236289õ6251õPAGO FACTURA 10745444840 VTO. 25/08/16  õ0001920525         õ29062016õ0949õARSõ00000000033572õ00õ0100õ000003733527õ964584393953õ5730õPAGO FACTURA 10742276284 VTO. 29/06/16  õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSPESPAGE", "120"))))
                .thenReturn(cnscotcnResponse);
 
        ConsultaDetallePMCOutEntity res= null;
        try {
            res = serviceDAO.consultarDetalle(entity);
        } catch (DAOException e) {            
            LOGGER.error(ERROR, e);
        }
        Assert.assertNotNull(res);
    }
    
    /**
     * Consulta detalle error generico test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test
    public void consultaDetallePMCConDeudaTest() throws IatxException   {
        ConsultaComprobanteInEntity entity = new ConsultaComprobanteInEntity();
        entity.setCliente(obtenerCliente());
        entity.setFechaDesde("");
        entity.setFechaHasta("");
        entity.setNroCuenta("");
        entity.setNroOrdenFirmante("");
        entity.setNroTarjetaBanelco("");
        entity.setSucursalCuenta("");
        entity.setTipoCuenta("");

        String cnscotcnResponse = "200000000000P04HTML0009900010380335191  ********00480675000000202017052612135300000000IBF27817000000000000CNSPESPAGE1200000            80335191    00        00 012140087201705261213450000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0085200000000õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ006õ00100300390560     õ05072017õ1717õARSõ00000000001310õ00õ0100õ000003733527õ412519954400õ841 õPAGO FACTURA 10758673472 VTO. 26/04/17  õ0001920525         õ22022017õ0909õARSõ00000000033572õ00õ0100õ000003733527õ519984770794õ5246õPAGO FACTURA 10755714836 VTO. 22/02/17  õ0001920525         õ22122016õ0837õARSõ00000000033572õ00õ0100õ000003733527õ392140185704õ4154õPAGO FACTURA 10752309266 VTO. 22/12/16  õ0001920525         õ25102016õ0844õARSõ00000000033572õ00õ0100õ000003733527õ643108957282õ4261õPAGO FACTURA 10748736883 VTO. 25/10/16  õ0001920525         õ25082016õ0840õARSõ00000000033572õ00õ0100õ000003733527õ062217236289õ6251õPAGO FACTURA 10745444840 VTO. 25/08/16  õ0001920525         õ29062016õ0949õARSõ00000000033572õ00õ0100õ000003733527õ964584393953õ5730õPAGO FACTURA 10742276284 VTO. 29/06/16  õ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSPESPAGE", "120"))))
                .thenReturn(cnscotcnResponse);
 
        ConsultaDetallePMCOutEntity res= null;
        try {
            res = serviceDAO.consultarDetalle(entity);
        } catch (DAOException e) {            
            LOGGER.error(ERROR, e);
        }
        Assert.assertNotNull(res);
    }
    
    /**
     * Consulta detalle error generico test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test
    public void consultaDetalleErrorValidacionPMCConDeudaTest() throws IatxException   {
        ConsultaComprobanteInEntity entity = new ConsultaComprobanteInEntity();
        entity.setCliente(obtenerCliente());
        entity.setFechaDesde("");
        entity.setFechaHasta("");
        entity.setNroCuenta("");
        entity.setNroOrdenFirmante("");
        entity.setNroTarjetaBanelco("");
        entity.setSucursalCuenta("");
        entity.setTipoCuenta("");

        String cnscotcnResponse = "200000000000P04HTML0009900010380335191  ********00480675000000202017052612135300000000IBF27817000000000000CNSPESPAGE1200000            80335191    00        00 012140087201705261213450000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0085200000000õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ006õ00100300390560     õ05072017õ1717õARSõ00000000001310õ00õ0100õ000003733527õ412519954400õ841 õPAGO FACTURA 10758673472 VTO. 26/04/17  õ0001920525         õ22022017õ0909õARSõ00000000033572õ00õ0100õ000003733527õ519984770794õ5246õPAGO FACTURA 10755714836 VTO. 22/02/17  õ0001920525         õ22122016õ0837õARSõ00000000033572õ00õ0100õ000003733527õ392140185704õ4154õPAGO FACTURA 10752309266 VTO. 22/12/16  õ0001920525         õ25102016õ0844õARSõ00000000033572õ00õ0100õ000003733527õ643108957282õ4261õPAGO FACTURA 10748736883 VTO. 25/10/16  õ0001920525         õ25082016õ0840õARSõ00000000033572õ00õ0100õ000003733527õ062217236289õ6251õPAGO FACTURA 10745444840 VTO. 25/08/16  õ0001920525         õ29062016õ0949õARSõ00000000033572õ00õ0100õ000003733527õ964584393953õ5730õPAGO FACTURA 10742276284 VTO. 29/06/16  TESTõ";
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSPESPAGE", "120"))))
                .thenReturn(cnscotcnResponse);
 
        ConsultaDetallePMCOutEntity res= null;
        try {
            res = serviceDAO.consultarDetalle(entity);
        } catch (DAOException e) {            
            LOGGER.error(ERROR, e);
        }
        Assert.assertNull(res);
    }
    
    /**
     * test de rellamada (buffering en realidad)
     * @throws DAOException
     * @throws IatxException
     */
    @Test
    public void consultaDetalleRellamadaTest() throws DAOException, IatxException {

        ConsultaComprobanteInEntity entity = new ConsultaComprobanteInEntity();
        entity.setCliente(obtenerCliente());
        entity.setFechaDesde("");
        entity.setFechaHasta("");
        entity.setNroCuenta("");
        entity.setNroOrdenFirmante("");
        entity.setNroTarjetaBanelco("");
        entity.setSucursalCuenta("");
        entity.setTipoCuenta("");
        String cnscotcnResponse = "200000000100P04HTML0009900010303348599  ********00633763000000122018103012490100000000IBF1KR5H000000000000CNSPESPAGE1200000            03348599    00        00 012401776201810301248380000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ1907300000000õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ181õ107808566          õ02102018õ0925õARSõ00000000094775õ31õ1640õ00694404    õ243116717504õ0206õ                                        õ103851599          õ19092018õ1814õARSõ00000000025978õ31õ1640õ00649004    õ151676696872õ7966õ                                        õ107808566          õ11092018õ0910õARSõ00000000120433õ31õ1640õ01557304    õ427802226082õ3545õ                                        õ107808566          õ30082018õ1452õARSõ00000000120433õ31õ1640õ00847204    õ411532649555õ4856õ                                        õ103851599          õ30082018õ1452õARSõ00000000025978õ31õ1640õ00881604    õ411531665333õ4251õ                                        õ103851599          õ03082018õ1236õARSõ00000000020001õ31õ1640õ00506304    õ070596587804õ5540õ                                        õ107808566          õ03072018õ1017õARSõ00000000054188õ31õ1640õ00428604    õ383856425638õ2202õ                                        õ103851599          õ22062018õ1252õARSõ00000000020001õ31õ1640õ00543004    õ442739080951õ0002õ                                        õ107808566          õ14062018õ0827õARSõ00000000054188õ31õ1640õ00371104    õ735659982885õ2445õ                                        õ103851599          õ06062018õ1556õARSõ00000000019933õ00õ0100õ000003561278õ071394529261õ9872õ                                        õ107808566          õ15052018õ1106õARSõ00000000026492õ31õ1640õ00909104    õ153165366088õ2002õ                                        õ103851599          õ03052018õ0850õARSõ00000000019933õ31õ1640õ00274404    õ108259525273õ7254õ                                        õ107808566          õ23042018õ1303õARSõ00000000026492õ00õ0100õ000003561278õ259420719308õ5842õ                                        õ103851599          õ26032018õ0909õARSõ00000000018427õ31õ1640õ00336004    õ826156929974õ2472õ                                        õ107808566          õ27022018õ1509õARSõ00000000023777õ31õ1640õ00264804    õ514972677945õ6286õ                                        õ103851599          õ27022018õ1509õARSõ00000000018427õ31õ1640õ00269604    õ514972303664õ3141õ                                        õ107808566          õ16022018õ1915õARSõ00000000023777õ00õ0100õ000003561278õ579306681822õ3363õ                                        õ103851599          õ01022018õ0942õARSõ00000000016802õ31õ1640õ00251604    õ248937377106õ2670õ                                        õ107808566          õ18012018õ1409õARSõ00000000029395õ00õ0100õ000003561278õ055372409806õ9526õ                                        õ103851599          õ05012018õ0836õARSõ00000000016802õ00õ0100õ000003561278õ912183273332õ9344õ                                        õ107808566          õ18122017õ0828õARSõ00000000029395õ31õ1640õ00685404    õ356513834292õ5100õ                                        õ103851599          õ04122017õ1017õARSõ00000000005362õ31õ1640õ00660604    õ153462149661õ4392õ                                        õ107808566          õ02112017õ0855õARSõ00000000011803õ31õ1640õ00584404    õ383748972725õ7341õ                                        õ103851599          õ29102017õ1430õARSõ00000000005362õ31õ1640õ00820404    õ058216503956õ3027õ                                        õ107808566          õ11102017õ0839õARSõ00000000011803õ00õ0100õ000003561278õ481980796400õ0879õ                                        õ103851599          õ02102017õ1054õARSõ00000000010759õ31õ1640õ00594004    õ712470385626õ4909õ                                        õ107808566          õ04092017õ1554õARSõ00000000044951õ31õ1640õ00515304    õ311293538614õ0435õ                                        õ103851599          õ04092017õ1553õARSõ00000000010759õ31õ1640õ00552804    õ311221855454õ8651õ                                        õ107808566          õ11082017õ0904õARSõ00000000044951õ31õ1640õ00583104    õ213055159035õ4834õ                                        õ103851599          õ28072017õ0852õARSõ00000000012539õ31õ1640õ00433104    õ002760677358õ3494õ                                        õ103851599          õ29062017õ0844õARSõ00000000012539õ00õ0100õ000003561278õ496654108538õ0217õ                                        õ107808566          õ29062017õ0843õARSõ00000000020283õ00õ0100õ000003561278õ496621314323õ1495õ                                        õ103851599          õ27062017õ0911õARSõ00000000010146õ00õ0100õ000003561278õ325472869735õ3915õ                                        õ107808566          õ21062017õ0900õARSõ00000000020283õ00õ0100õ000003561278õ806430463373õ1170õ                                        õ107808566          õ30052017õ0845õARSõ00000000012008õ00õ0100õ000003561278õ904731436733õ2022õ                                        õ103851599          õ30052017õ0845õARSõ00000000010146õ00õ0100õ000003561278õ904730486711õ3341õ                                        õ107808566          õ28042017õ0839õARSõ00000000012008õ00õ0100õ000003561278õ139567044588õ8019õ                                        õ107808566          õ28042017õ0838õARSõ00000000008994õ00õ0100õ000003561278õ139532779770õ5040õ                                        õ103851599          õ28042017õ0838õARSõ00000000007804õ00õ0100õ000003561278õ139491524335õ5811õ                                        õ103851599          õ30032017õ1109õARSõ00000000007804õ00õ0100õ000003561278õ642945555531õ9345õ                                        õ107808566          õ30032017õ1103õARSõ00000000008994õ00õ0100õ000003561278õ642624541526õ2413õ                                        õ103851599          õ01032017õ1149õARSõ00000000002055õ00õ0100õ000003561278õ139755788319õ6042õ                                        õ107808566          õ01032017õ1148õARSõ00000000010345õ00õ0100õ000003561278õ139728831242õ6531õ                                        õ103851599          õ15022017õ0858õARSõ00000000002055õ00õ0100õ000003561278õ919907229734õ2743õ                                        õ107808566          õ15022017õ0858õARSõ00000000010345õ00õ0100õ000003561278õ919907043398õ2616õ                                        õ107808566          õ24012017õ0919õARSõ00000000010420õ00õ0100õ000003561278õ020390714334õ5664õ                                        õ107808566          õ06012017õ0844õARSõ00000000010420õ00õ0100õ000003561278õ463086837876õ2314õ                                        õ103851599          õ27072016õ0834õARSõ00000000013443õ00õ0100õ000003561278õ379258788080õ3033õ                                        õ103851599          õ04072016õ1202õARSõ00000000013443õ00õ0100õ000003561278õ404565259285õ9351õ                                        õ107808566          õ08062016õ1848õARSõ00000000043837õ00õ0100õ000003561278õ182493499998õ4173õ                                        õ103851599          õ28042016õ1419õARSõ00000000005795õ00õ0100õ000003561278õ623953097754õ5400õ                                        õ107808566          õ19042016õ0820õARSõ00000000007156õ00õ0100õ000003561278õ824845340738õ1602õ                                        õ103851599          õ09032016õ0841õARSõ00000000005963õ00õ0100õ000003561278õ283714886409õ9726õ                                        õ107808566          õ26022016õ0835õARSõ00000000007906õ00õ0100õ000003561278õ246519985385õ8074õ                                        õ103851599          õ31122015õ1048õARSõ00000000006618õ00õ0100õ000003561278õ329703450970õ3942õ                                        õ107808566          õ10122015õ0925õARSõ00000000011681õ00õ0100õ000003561278õ510357393395õ3919õ                                        õ103851599          õ02112015õ0811õARSõ00000000006607õ00õ0100õ000003561278õ222660026676õ2330õ                                        õ107808566          õ14102015õ0833õARSõ00000000016492õ00õ0100õ000003561278õ582390610466õ3029õ                                        õ103851599          õ01092015õ0838õARSõ00000000002841õ00õ0100õ000003561278õ867527118435õ0314õ                                        õ107808566          õ13082015õ0849õARSõ00000000019956õ00õ0100õ000003561278õ226577401830õ3314õ                                        õ103851599          õ01072015õ1053õARSõ00000000002276õ00õ0100õ000003561278õ518826540858õ2014õ                                        õ107808566          õ29062015õ0720õARSõ00000000008234õ00õ0100õ000003561278õ333205241201õ1101õ                                        õ103851599          õ30042015õ0826õARSõ00000000004043õ00õ0100õ000003561278õ153209694775õ6521õ                                        õ107808566          õ20042015õ0830õARSõ00000000005585õ00õ0100õ000003561278õ289423012026õ2659õ                                        õ103851599          õ02032015õ0833õARSõ00000000002228õ00õ0100õ000003561278õ056014878415õ3300õ                                        õ107808566          õ13022015õ1028õARSõ00000000006468õ00õ0100õ000003561278õ594132006421õ2530õ                                        õ103851599          õ02012015õ0813õARSõ00000000002303õ00õ0100õ000003561278õ957194643905õ7797õ                                        õ107808566          õ05122014õ1159õARSõ00000000007499õ00õ0100õ000003561278õ551599955319õ5223õ                                        õ103851599          õ30102014õ0918õARSõ00000000002808õ00õ0100õ000003561278õ431491329951õ4971õ                                        õ107808566          õ08102014õ1031õARSõ00000000004106õ00õ0100õ000003561278õ535072575604õ1493õ                                        õ103851599          õ29082014õ0859õARSõ00000000006973õ00õ0100õ000003561278õ073585520184õ5134õ                                        õ107808566          õ08082014õ1740õARSõ00000000005936õ00õ0100õ000003561278õ290400966479õ3940õ                                        õ103851599          õ01072014õ0949õARSõ00000000003563õ00õ0100õ000003561278õ8969        õ4370õ                                        õ107808566          õ16062014õ1047õARSõ00000000003208õ00õ0100õ000003561278õ686420024437õ3343õ                                        õ103851599          õ19052014õ1024õARSõ00000000003060õ00õ0100õ000003561278õ265849132509õ3497õ                                        õ107808566          õ08042014õ1623õARSõ00000000002621õ00õ0100õ000003561278õ745004747028õ0135õ                                        õ103851599          õ28022014õ0947õARSõ00000000002361õ00õ0100õ000003561278õ351628856447õ8502õ                                        õ107808566          õ12022014õ1036õARSõ00000000002564õ00õ0100õ000003561278õ972178538698õ0721õ                                        õ103851599          õ02012014õ0925õARSõ00000000002508õ00õ0100õ000003561278õ425534405944õ2403õ                                        õ107808566          õ12122013õ1202õARSõ00000000003255õ00õ0100õ000003561278õ620542432599õ4920õ                                        õ103851599          õ31102013õ1418õARSõ00000000003696õ00õ0100õ000003561278õ999927397873õ9115õ                                        õ30007233285        õ13102013õ1822õARSõ00000000024971õ00õ0100õ000003561278õ459372831680õ0129õ                                        õ107808566          õ10102013õ1023õARSõ00000000005007õ00õ0100õ000003561278õ171395255828õ5012õ                                        õ103851599          õ29082013õ0845õARSõ00000000002277õ00õ0100õ000003561278õ536754454066õ3800õ                                        õ107808566          õ09082013õ1557õARSõ00000000004407õ00õ0100õ000003561278õ834676828099õ5013õ                                        õ103851599          õ02072013õ0903õARSõ00000000003142õ00õ0100õ000003561278õ526617750632õ8540õ                                        õ107808566          õ06062013õ1324õARSõ00000000003652õ00õ0100õ000003561278õ295888683726õ3707õ                                        õ103851599          õ29042013õ1219õARSõ00000000002631õ00õ0100õ000003561278õ008768235896õ2240õ                                        õ107808566          õ08042013õ1407õARSõ00000000002637õ00õ0100õ000003561278õ200870592210õ0021õ                                        õ103851599          õ04032013õ1035õARSõ00000000002401õ00õ0100õ000003561278õ361210265200õ3202õ                                        õ107808566          õ13022013õ0929õARSõ00000000002558õ00õ0100õ000003561278õ518586155789õ9904õ                                        õ103925924          õ02012013õ0916õARSõ00000000003716õ00õ0100õ000003561278õ888999065797õ1315õ                                        õ103851599          õ26122012õ0952õARSõ00000000002517õ00õ0100õ000003561278õ286355452445õ9130õ                                        õ107808566          õ07122012õ0939õARSõ00000000002276õ00õ0100õ000003561278õ643976998272õ0501õ                                        õ103925924          õ23102012õ0933õARSõ00000000003580õ00õ0100õ000003561278õ755623343909õ1407õ                                        õ103851599          õ22102012õ0758õARSõ00000000004376õ00õ0100õ000003561278õ663508008562õ2554õ                                        õ107808566          õ12102012õ1405õARSõ00000000003394õ00õ0100õ000003561278õ821525892505õ3177õ                                        õ103925924          õ03092012õ1554õARSõ00000000004588õ00õ0100õ000003561278õ458497444585õ1405õ                                        õ103851599          õ03092012õ0735õARSõ00000000003509õ00õ0100õ000003561278õ428541698621õ1423õ                                        õ107808566          õ17082012õ1311õARSõ00000000003828õ00õ0100õ000003561278õ979905195192õ9749õ                                        õ103925924          õ10072012õ0853õARSõ00000000003329õ00õ0100õ000003561278õ681192959919õ2534õ                                        õ103851599          õ05072012õ1448õARSõ00000000002515õ00õ0100õ000003561278õ270530570818õ0246õ                                        õ107808566          õ15062012õ0725õARSõ00000000002509õ00õ0100õ000003561278õ515937819142õ5719õ                                        õ103925924          õ15052012õ1547õARSõ00000000002724õ00õ0100õ000003561278õ9642        õ2532õ                                        õ103851599          õ27042012õ1449õARSõ00000000002075õ00õ0100õ000003561278õ308987789752õ1244õ                                        õ107808566          õ11042012õ1357õARSõ00000000001686õ00õ0100õ000003561278õ923479546701õ4252õ                                        õ103851599          õ03032012õ1750õARSõ00000000001855õ00õ0100õ000003561278õ567857363388õ4203õ                                        õ107808566          õ10022012õ1648õARSõ00000000001926õ00õ0100õ000003561278õ663319954099õ1930õ                                        õ103851599          õ09012012õ0907õARSõ00000000002063õ00õ0100õ000003561278õ870839664668õ9404õ                                        õ107808566          õ07122011õ1300õARSõ00000000002891õ00õ0100õ000003561278õ033629471954õ6800õ                                        õ103851599          õ27102011õ1742õARSõ00000000002800õ00õ0100õ000003561278õ508131911924õ2902õ                                        õ107808566          õ14102011õ0852õARSõ00000000003806õ00õ0100õ000003561278õ353136647804õ2667õ                                        õ103851599          õ29082011õ0901õARSõ00000000003502õ00õ0100õ000003561278õ379307414088õ1414õ                                        õ107808566          õ15082011õ1436õARSõ00000000005162õ00õ0100õ000003561278õ189787103124õ7190õ                                        õ103851599          õ30062011õ0901õARSõ00000000002812õ00õ0100õ000003561278õ195296714684õ1503õ                                        õ107808566          õ09062011õ0942õARSõ00000000002381õ00õ0100õ000003561278õ383378572823õ2108õ                                        õ103851599          õ04052011õ1509õARSõ00000000002025õ00õ0100õ000003561278õ292576098201õ1530õ                                        õ107808566          õ15042011õ0809õARSõ00000000001761õ00õ0100õ000003561278õ625760231266õ6349õ                                        õ103851599          õ22022011õ1032õARSõ00000000001885õ00õ0100õ000003561278õ141531555829õ4005õ                                        õ103851599          õ22122010õ1353õARSõ00000000001966õ00õ0100õ000003561278õ796793863782õ4210õ                                        õ103851599          õ29102010õ1156õARSõ00000000002720õ00õ0100õ000003561278õ124165675087õ6157õ                                        õ103851599          õ02092010õ1425õARSõ00000000003544õ00õ0100õ000003561278õ208337272687õ0226õ                                        õ103851599          õ29062010õ1816õARSõ00000000002118õ00õ0100õ000003561278õ606199640908õ1523õ                                        õ103851599          õ30042010õ0946õARSõ00000000002209õ00õ0100õ000003561278õ391596837284õ3011õ                                        õ103851599          õ26022010õ0856õARSõ00000000001641õ00õ0100õ000003561278õ945367442905õ0969õ                                        õ30002669236        õ30122009õ1554õARSõ00000000001793õ00õ0100õ000003561278õ959255981814õ2170õ                                        õ30002669236        õ29102009õ1115õARSõ00000000000828õ00õ0100õ000003561278õ585756801222õ8508õ                                        õ107808566          õ29042009õ0851õARSõ00000000002288õ00õ0100õ000003561278õ765916705352õ6052õ                                        õ107808566          õ26022009õ0849õARSõ00000000002239õ00õ0100õ000003561278õ408973110967õ6841õ                                        õ30002669236        õ24122008õ0839õARSõ00000000003637õ00õ0100õ000003561278õ878776863573õ6285õ                                        õ107808566          õ22122008õ0846õARSõ00000000002272õ00õ0100õ000003561278õ706414748304õ9202õ                                        õ107808566          õ31102008õ1225õARSõ00000000001480õ00õ0100õ000003561278õ226741512800õ2062õ                                        õ30002669236        õ27102008õ1147õARSõ00000000003880õ00õ0100õ000003561278õ878846365900õ6503õ                                        õ30002669236        õ27082008õ1751õARSõ00000000001935õ00õ0100õ000003561278õ630282525800õ5185õ                                        õ107808566          õ13082008õ0941õARSõ00000000004860õ00õ0100õ000003561278õ391262059800õ7230õ                                        õ30002669236        õ30062008õ0904õARSõ00000000001826õ00õ0100õ000003561278õ587478428600õ2906õ                                        õ107808566          õ10062008õ0902õARSõ00000000002728õ00õ0100õ000003561278õ859362675000õ2242õ                                        õ30003632625        õ30042008õ0856õARSõ00000000004441õ00õ0100õ000003561278õ316616988600õ1592õ                                        õ30002669236        õ30042008õ0856õARSõ00000000001702õ00õ0100õ000003561278õ316616471400õ5051õ                                        õ30002669236        õ29022008õ0928õARSõ00000000001950õ00õ0100õ000003561278õ048113102800õ0314õ                                        õ107808566          õ18022008õ0932õARSõ00000000002630õ00õ0100õ000003561278õ097925085900õ1886õ                                        õ30003632625        õ18022008õ0932õARSõ00000000002084õ00õ0100õ000003561278õ097924523400õ7919õ                                        õ30003632625        õ21122007õ1702õARSõ00000000001272õ00õ0100õ000003561278õ027341844600õ4173õ                                        õ";
        String cnscotcnResponse2 = "200000000000P04HTML0009900010303348599  ********00633763000000132018103012490100000000IBF1KR5H000000000000CNSPESPAGE1200000            03348599    00        00 000000000201810301248380000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ05054107808566          õ10122007õ1153õARSõ00000000002543õ00õ0100õ000003561278õ058429988800õ4412õ                                        õ107808566          õ02112007õ1053õARSõ00000000006394õ00õ0100õ000003561278õ771614071000õ4156õ                                        õ30003632625        õ31102007õ1007õARSõ00000000006823õ00õ0100õ000003561278õ596063132600õ9862õ                                        õ107808566          õ03092007õ0952õARSõ00000000008430õ00õ0100õ000003561278õ583979937000õ5510õ                                        õ30003632625        õ30082007õ1801õARSõ00000000013716õ00õ0100õ000003561278õ267704907200õ1484õ                                        õ30003632625        õ29062007õ0936õARSõ00000000007761õ00õ0100õ000003561278õ880596052000õ5604õ                                        õ107808566          õ19062007õ1559õARSõ00000000004550õ00õ0100õ000003561278õ039573658100õ1850õ                                        õ107808566          õ30042007õ0913õARSõ00000000002279õ00õ0100õ000003561278õ695229655400õ1135õ                                        õ30003632625        õ30042007õ0912õARSõ00000000001689õ00õ0100õ000003561278õ695156540000õ2337õ                                        õ30003632625        õ27022007õ1549õARSõ00000000001684õ00õ0100õ000003561278õ362160198100õ3244õ                                        õ107808566          õ14022007õ0922õARSõ00000000002678õ00õ0100õ000003561278õ215751327100õ4435õ                                        õ30003632625        õ26122006õ0922õARSõ00000000001840õ00õ0100õ000003561278õ895763788200õ5381õ                                        õ107808566          õ26122006õ0921õARSõ00000000002803õ00õ0100õ000003561278õ895717500300õ0023õ                                        õ30003632625        õ30102006õ1843õARSõ00000000007008õ00õ0100õ000003561278õ004610133500õ5506õ                                        õ107808566          õ17102006õ0910õARSõ00000000007999õ00õ0100õ000003561278õ847001265000õ6554õ                                        õ30003632625        õ29082006õ1641õARSõ00000000007701õ00õ0100õ000003561278õ640512360100õ4142õ                                        õ107808566          õ10082006õ0914õARSõ00000000009338õ00õ0100õ000003561278õ972047285900õ4124õ                                        õ30003632625        õ30062006õ0920õARSõ00000000004282õ00õ0100õ000003561278õ430000939700õ1755õ                                        õ107808566          õ08062006õ0923õARSõ00000000005200õ00õ0100õ000003561278õ529410167900õ8556õ                                        õ30003632625        õ28042006õ1453õARSõ00000000001687õ00õ0100õ000003561278õ006780004100õ6971õ                                        õ107808566          õ06042006õ1658õARSõ00000000003115õ00õ0100õ000003561278õ113508935000õ2282õ                                        õ30003632625        õ27022006õ0859õARSõ00000000002211õ00õ0100õ000003561278õ801575997600õ2587õ                                        õ107808566          õ13022006õ1019õARSõ00000000002797õ00õ0100õ000003561278õ596799468600õ1312õ                                        õ30002600952        õ23122005õ0847õARSõ00000000001704õ00õ0100õ000003561278õ098424374700õ7124õ                                        õ107808566          õ07122005õ1123õARSõ00000000003235õ00õ0100õ000003561278õ725395934100õ6325õ                                        õ107808566          õ06102005õ1242õARSõ00000000005406õ00õ0100õ000003561278õ373347628100õ6494õ                                        õ107808566          õ08082005õ0923õARSõ00000000005975õ00õ0100õ000003561278õ263838789700õ8541õ                                        õ107808566          õ06062005õ1131õARSõ00000000004464õ00õ0100õ000003561278õ828311777300õ3944õ                                        õ107808566          õ07042005õ1726õARSõ00000000002637õ00õ0100õ000003561278õ665618364200õ1509õ                                        õ107808566          õ07022005õ0843õARSõ00000000002564õ00õ0100õ000003561278õ536589813500õ7115õ                                        õ107808566          õ06122004õ1339õARSõ00000000002538õ00õ0100õ000003561278õ111141639000õ2912õ                                        õ107808566          õ06102004õ0857õARSõ00000000003656õ00õ0100õ000003561278õ823878359000õ8840õ                                        õ107808566          õ06082004õ0920õARSõ00000000004921õ00õ0100õ000003561278õ554852958300õ1723õ                                        õ107808566          õ09062004õ0907õARSõ00000000004213õ00õ0100õ000003561278õ542825102100õ1783õ                                        õ107808566          õ02042004õ1417õARSõ00000000002982õ00õ0100õ000003561278õ686252356700õ6125õ                                        õ107808566          õ09022004õ0907õARSõ00000000003134õ00õ0100õ000003561278õ088462131800õ3418õ                                        õ107808566          õ09122003õ0850õARSõ00000000003785õ00õ0100õ000003561278õ730645175500õ5413õ                                        õ107808566          õ06122001õ1414õARSõ00000000004925õ00õ0100õ000003561278õ5051        õ3171õ                                        õ"; 
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSPESPAGE", "120"))))
                .thenReturn(cnscotcnResponse).thenReturn(cnscotcnResponse2);

        ConsultaDetallePMCOutEntity res = serviceDAO.consultarDetalle(entity);
        for (DetallePMCEntity detalle : res.getDestinatarios()) {
            System.out.println(detalle.getFechaPago());
            System.out.println(ISBANStringUtils.formatearFecha(detalle.getFechaPago(),
                    ISBANStringUtils.FORMATO_FECHA_SIN_BARRAS));
        }

        Assert.assertEquals("00000000", res.getCodigoRetornoExtendido());

    }
}
