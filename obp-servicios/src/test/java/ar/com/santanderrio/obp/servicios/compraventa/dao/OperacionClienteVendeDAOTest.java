/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dao;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaDolaresUtil;
import ar.com.santanderrio.obp.servicios.compraventa.dao.impl.OperacionClienteVendeDAOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionClienteVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionCompraVentaDatosEntrada;

/**
 * The Class OperacionClienteVendeDAOTest.
 *
 * @author pablo.gargaglione
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        OperacionClienteVendeDAOTest.OperacionClienteVendeDAOTestConfiguration.class })
@Ignore
public class OperacionClienteVendeDAOTest extends IatxBaseDAOTest {

    /** The tipo cuenta pesos 01 envio. */
    // "ACAH"
    public final String TIPO_CUENTA_PESOS_01_ENVIO = "01";

    /** The operacion compra. */
    public final String OPERACION_COMPRA = "C";

    /** The operacion cliente vende DAO. */
    @Autowired
    @InjectMocks
    private OperacionClienteVendeDAO operacionClienteVendeDAO;;

    /**
     * The Class OperacionClienteVendeDAOTestConfiguration.
     */
    @Configuration
    public static class OperacionClienteVendeDAOTestConfiguration {

        /**
         * Operacion cliente vende DAO.
         *
         * @return the operacion cliente vende DAO
         */
        @Bean
        public OperacionClienteVendeDAO operacionClienteVendeDAO() {
            return new OperacionClienteVendeDAOImpl();
        }

        /**
         * Compra venta dolares util.
         *
         * @return the compra venta dolares util
         */
        @Bean
        public CompraVentaDolaresUtil compraVentaDolaresUtil() {
            return Mockito.spy(new CompraVentaDolaresUtil());
        }
    }

    /**
     * test de comportamiento de OperacionClienteVende del DAO,
     * OperacionClienteVendeDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionClienteVendeOk() throws IatxException, ServiceException, DAOException {

        // Realiza operaciones de compra (Banco Compra / Cliente Vende) de
        // dólares.
        String cpadolcnlsResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413054900000000IBF42460000013011628CPADOLCNLS1000000            20064145    00        00 013011628201606141305450000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0018200000000õ00720100007003750162õUSDõ000000000000100-õPHXP2S2016-06-1413054587õ00720100007003750162õARSõ000000000001376+õPHXP2T2016-06-1413054588õ00137600000+õ000000000000000+õ6078263Aõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("CPADOLCNLS", "100")))).thenReturn(cpadolcnlsResponse);

        Cliente cliente = obtenerCliente();
        OperacionCompraVentaDatosEntrada operacionCompraVentaDatosEntrada = obtenerOperacionCompraVentaDatosEntrada();
        OperacionClienteVentaEntity operacionClienteVendeEntity = operacionClienteVendeDAO
                .operacionClienteVende(cliente, operacionCompraVentaDatosEntrada, null);
        System.out.println(operacionClienteVendeEntity);
        Assert.assertNotEquals(null, operacionClienteVendeEntity);
        Assert.assertEquals(operacionClienteVendeEntity.getCuentacredito(), "00720100007003750162");
        Assert.assertEquals(operacionClienteVendeEntity.getCuentadebito(), "00720100007003750162");
        Assert.assertEquals(operacionClienteVendeEntity.getNiocredito(), "PHXP2T2016-06-1413054588");
        Assert.assertEquals(operacionClienteVendeEntity.getNiodebito(), "PHXP2S2016-06-1413054587");
        Assert.assertEquals(operacionClienteVendeEntity.getNroBoleto(), "6078263A");
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher("CPADOLCNLS", "100")));
    }

    /**
     * test de comportamiento de OperacionClienteVende del DAO,
     * OperacionClienteVendeDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionClienteVendeError() throws IatxException, ServiceException, DAOException {

        // Realiza operaciones de compra (Banco Compra / Cliente Vende) de
        // dólares.
        String cpadolcnlsResponse = "200000000000P04HTML0009900010300KKQO29  ********00622403000000062016112316480100000000IBF25764000016440587SIMDOLCNLS1000000            00006429    00        00 016440587201611231647560000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810000077õMECõ03õLa operacion solicitada no puede ser realizada debido a que se encuentra fuera del horario de atencion de la sucursal en la que Usted tiene radicada su cuenta.                                                                                               õERROR NO CONTEMõSUCURSAL SIN COTIZACION                                                         õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("CPADOLCNLS", "100")))).thenReturn(cpadolcnlsResponse);

        Cliente cliente = obtenerCliente();
        OperacionCompraVentaDatosEntrada operacionCompraVentaDatosEntrada = obtenerOperacionCompraVentaDatosEntrada();
        OperacionClienteVentaEntity operacionClienteVendeEntity = operacionClienteVendeDAO
                .operacionClienteVende(cliente, operacionCompraVentaDatosEntrada, null);
        System.out.println(operacionClienteVendeEntity);
        Assert.assertNotEquals(null, operacionClienteVendeEntity);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher("CPADOLCNLS", "100")));
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
     * Obtener operacion compra venta datos entrada.
     *
     * @return the operacion compra venta datos entrada
     */
    private OperacionCompraVentaDatosEntrada obtenerOperacionCompraVentaDatosEntrada() {
        OperacionCompraVentaDatosEntrada OperacionCompraVentaDatosEntrada = new OperacionCompraVentaDatosEntrada();
        OperacionCompraVentaDatosEntrada.setAplicacionPesos("ACAH");
        OperacionCompraVentaDatosEntrada.setSucursalctapesos("461");
        OperacionCompraVentaDatosEntrada.setNumeroctapesos("000000000000");
        OperacionCompraVentaDatosEntrada.setAplicacionDolar("ACAH");
        OperacionCompraVentaDatosEntrada.setSucursalCtaDolar("461");
        OperacionCompraVentaDatosEntrada.setNumeroCtaDolar("000000000000");
        OperacionCompraVentaDatosEntrada.setIndtuatesora("t");
        OperacionCompraVentaDatosEntrada.setFechavalor("2016-08-04");
        OperacionCompraVentaDatosEntrada.setConcepdebito("ABCD");
        OperacionCompraVentaDatosEntrada.setConcepcred("ABCD");
        OperacionCompraVentaDatosEntrada.setCodigodebi("ABCD");
        OperacionCompraVentaDatosEntrada.setCodigocred("ABCD");
        OperacionCompraVentaDatosEntrada.setImportedeb("300");
        OperacionCompraVentaDatosEntrada.setImportecred("500");
        OperacionCompraVentaDatosEntrada.setImportecoti("450");
        OperacionCompraVentaDatosEntrada.setIndiccompravta("C");
        OperacionCompraVentaDatosEntrada.setAutorizafip("ABCDEFG");
        OperacionCompraVentaDatosEntrada.setTipocambio("U$S");
        OperacionCompraVentaDatosEntrada.setNumbolecvta("C");
        OperacionCompraVentaDatosEntrada.setCodtributa("S");
        OperacionCompraVentaDatosEntrada.setFecingrpais("20050120");
        OperacionCompraVentaDatosEntrada.setNomapell("Razon Social");
        OperacionCompraVentaDatosEntrada.setPaisemisdoc("001");
        OperacionCompraVentaDatosEntrada.setPaisnac("001");
        OperacionCompraVentaDatosEntrada.setCodigoconcepto("12345");
        OperacionCompraVentaDatosEntrada.setTpodocbcra("L");
        OperacionCompraVentaDatosEntrada.setNrodocbcra("24233456");
        OperacionCompraVentaDatosEntrada.setTiprefafip("ABC");
        OperacionCompraVentaDatosEntrada.setNiocredito("0000AAAA");
        OperacionCompraVentaDatosEntrada.setNiodebito("0000BBBB");
        return OperacionCompraVentaDatosEntrada;
    }

}
