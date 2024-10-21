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
import ar.com.santanderrio.obp.servicios.compraventa.dao.impl.OperacionClienteCompraDAOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionClienteCompraEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionCompraVentaDatosEntrada;

/**
 * The Class OperacionClienteCompraDAOTest.
 *
 * @author pablo.gargaglione
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        OperacionClienteCompraDAOTest.OperacionClienteCompraDAOTestConfiguration.class })
@Ignore
public class OperacionClienteCompraDAOTest extends IatxBaseDAOTest {

    /** The tipo cuenta pesos 01 envio. */
    // "ACAH"
    public final String TIPO_CUENTA_PESOS_01_ENVIO = "01";

    /** The operacion compra. */
    public final String OPERACION_COMPRA = "C";

    /** The operacion cliente compra DAO. */
    @Autowired
    @InjectMocks
    private OperacionClienteCompraDAO operacionClienteCompraDAO;;

    /**
     * The Class OperacionClienteCompraDAOTestConfiguration.
     */
    @Configuration
    public static class OperacionClienteCompraDAOTestConfiguration {

        /**
         * Operacion cliente compra DAO.
         *
         * @return the operacion cliente compra DAO
         */
        @Bean
        public OperacionClienteCompraDAO operacionClienteCompraDAO() {
            return new OperacionClienteCompraDAOImpl();
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
     * test de comportamiento de OperacionClienteCompra del DAO,
     * OperacionClienteCompraDAO.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionClienteCompraOk() throws IatxException, ServiceException, DAOException {
        // Realiza operaciones de venta (Banco Vende / Cliente Compra) de
        // dólares.
        String cpvtadolcnResponse = "200000000000P70HTML00000000003FREEUSER  ********00000001000000002016061413051500000000IBF48339000013007470CPVTADOLCN1100000            20064145    00        00 013007470201606141305110000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0043600000000õ00720100007003750162õARSõ2312õ    õ000000000001405+õ00720100007003750162õUSDõ2254õ    õ000000000000100+õ   õ000000000140500+õ2016-06-14õ000000000023596+õ000000000000000+õ000000010õ000000000000100+õ000000000023596+õ000000002õ8554õ70õ000000000000000+õ    õ    õ   õ    õFRANCO                                  õVITA              õ                    õN  õ00030219572õ6078244Aõ        õ000000000001405+õ43XP2C2016-06-1413051170õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("CPVTADOLCN", "110")))).thenReturn(cpvtadolcnResponse);

        Cliente cliente = obtenerCliente();
        OperacionCompraVentaDatosEntrada operacionCompraVentaDatosEntrada = obtenerOperacionCompraVentaDatosEntrada();
        OperacionClienteCompraEntity operacionClienteCompraEntity = operacionClienteCompraDAO
                .operacionClienteCompra(cliente, operacionCompraVentaDatosEntrada, null);
        System.out.println(operacionClienteCompraEntity);
        Assert.assertNotEquals(null, operacionClienteCompraEntity);
        Assert.assertEquals(operacionClienteCompraEntity.getCccabon(), "00720100007003750162");
        Assert.assertEquals(operacionClienteCompraEntity.getCcccarg(), "00720100007003750162");
        Assert.assertEquals(operacionClienteCompraEntity.getCanal(), "70");
        Assert.assertEquals(operacionClienteCompraEntity.getCentrl(), "8554");
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher("CPVTADOLCN", "110")));
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
