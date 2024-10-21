package ar.com.santanderrio.obp.servicios.pagocompras.dao;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.pagocompras.dao.impl.PagoComprasDAOImpl;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasInDTO;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudaPagoComprasEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudasPagoComprasEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoCompraConDeudaEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoCompraConDeudaInEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoCompraSinDeudaEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.exception.SinDeudasException;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class PagoComprasDAOTest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        PagoComprasDAOTest.PagoComprasDAOTestConfiguration.class })
public class PagoComprasDAOTest extends IatxBaseDAOTest {

    /** The pago compras DAO. */
    @Autowired
    private PagoComprasDAO pagoComprasDAO;
    
    /** The cliente. */
    Cliente cliente = new Cliente();
    
    /** The pago compras. */
    NuevoPago pagoCompras = new NuevoPago();
    
    /** The medio pago. */
    MedioPago medioPago = new MedioPago();
    
    /** The deudas. */
    DeudasPagoComprasEntity deudas = new DeudasPagoComprasEntity();
    
    /** The deuda. */
    DeudaPagoComprasEntity deuda = new DeudaPagoComprasEntity();
    
    /** The in entity. */
    PagoCompraConDeudaInEntity inEntity = new PagoCompraConDeudaInEntity();

    /** The cuenta. */
    Cuenta cuenta = new Cuenta();
    
    /** The in DTO. */
    PagoComprasInDTO inDTO = new PagoComprasInDTO();
    
    /**
     * The Class PagoComprasDAOTestConfiguration.
     */
    @Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.pagocompras.dao" })
    public static class PagoComprasDAOTestConfiguration {
        
        /**
         * Pago compras DAO.
         *
         * @return the pago compras DAO
         */
        @Bean
        PagoComprasDAO pagoComprasDAO() {
            return new PagoComprasDAOImpl();
        }

        /**
         * Alianzas DAO.
         *
         * @return the alianzas DAO
         */
        @Bean
        public AlianzasDAO alianzasDAO() {
            return Mockito.mock(AlianzasDAO.class);
        }
    }
    
    /**
     * Inits the.
     */
    @Before
    public void init() {
        cliente = ClienteMock.infoCliente();
        pagoCompras = new NuevoPago();
        pagoCompras.setMonto("100");
        pagoCompras.setFacturaNumero("000000123456789-0012");
        medioPago = new MedioPago();
        medioPago.setPpdctaIdEmpAcuerdo("123456789210");
        medioPago.setPpdctaCodProdAcuerdo("123");
        medioPago.setPpdctaNroInstaCuerdo("02");
        deudas.setIdClienteTerceros("123456789012345");
        deudas.setCuitClienteTerceros("12345678901");
        deudas.setIndicadorExcepcionComision("1");
        deudas.setIndicadorClientePropio("2");
        deudas.setCondicionIva("3");
        deudas.setTipoIngresosBrutos("4");
        deudas.setCantidadComprobanteDeuda(Integer.valueOf("01"));
        deuda.setTipoComprobanteDeuda("01");
        deuda.setNroComprobanteDeuda("000000123456789");
        deuda.setNroCuotaDeuda("0012");
        deuda.setFechaVencimientoDeuda("20180915");
        deuda.setImportePunitoriosDeuda("00000000002000");
        deuda.setImporteIvaInteresesDeuda("00000000002000");
        deuda.setImporteIvaAdicionalInteresesDeuda("00000000000100");
        deuda.setTexto1("Texto 1");
        deuda.setTexto2("Texto 2");
        deuda.setTexto3("Texto 3");
        deuda.setTexto4("Texto 4");
        deuda.setTexto5("Texto 5");
        deudas.getListaDeudas().add(deuda);
        cuenta.setCbu("1234567890123456789012");
        cliente.getCuentas().add(cuenta);
        inEntity = new PagoCompraConDeudaInEntity(pagoCompras, medioPago, deudas,
                DivisaEnum.PESO, cuenta);
        inDTO.setIdEmpresa("123456789012");
        inDTO.setCodProductoAcuerdoEmpresa("123");
        inDTO.setNroInstanciaAcuerdoEmpresa("12");
        inDTO.setPid("1234567890");
        inDTO.setMonto("00000000002000");
        inDTO.setFormaPagoDeuda("12");
        inDTO.setSucursalCuenta("023");
        inDTO.setTipoCuenta("1");
        inDTO.setNroCuentaGenerico("000012345678");
        inDTO.setNumeroIdentificacion("     3rergdfgdf");
    }

    /**
     * Obtener lista deudas OK.
     *
     * @throws DAOException the DAO exception
     * @throws IatxException the iatx exception
     */
    @Test
    public void obtenerListaDeudasOK() throws DAOException, IatxException {
        // Given
        String servicio = "CNSDEUCLTT";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010300KTNK98  ********00085470000000132018073108323400000000IBF2000E000000000000CNSDEUCLTT1100000            00093098    00        00 008308788201807310832150000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0543400000000õ306586608920õNORDELTA S.A. (BARRIO LOS CASTõ001õ02õEõ000033709342369õMARNIDA SRL                   õ33709342369õ7õ9õNõNõSõ20õOPõ   õ                                        õYACHT  257CUOTAõ0016õ20181003õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0259    õTC: 17.5000    õBO257   /CUO 16õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0017õ20181103õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0260    õTC: 17.5000    õBO257   /CUO 17õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0018õ20181203õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0261    õTC: 17.5000    õBO257   /CUO 18õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0019õ20190103õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0262    õTC: 17.5000    õBO257   /CUO 19õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0020õ20190203õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0263    õTC: 17.5000    õBO257   /CUO 20õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0021õ20190303õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0264    õTC: 17.5000    õBO257   /CUO 21õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0022õ20190403õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0265    õTC: 17.5000    õBO257   /CUO 22õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0023õ20190503õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0266    õTC: 17.5000    õBO257   /CUO 23õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0024õ20190603õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0267    õTC: 17.5000    õBO257   /CUO 24õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0025õ20190703õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0268    õTC: 17.5000    õBO257   /CUO 25õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0026õ20190803õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0269    õTC: 17.5000    õBO257   /CUO 26õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0027õ20190903õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0270    õTC: 17.5000    õBO257   /CUO 27õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0028õ20191003õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0271    õTC: 17.5000    õBO257   /CUO 28õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0029õ20191103õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0272    õTC: 17.5000    õBO257   /CUO 29õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0030õ20191203õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0273    õTC: 17.5000    õBO257   /CUO 30õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0031õ20200103õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0274    õTC: 17.5000    õBO257   /CUO 31õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0032õ20200203õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0275    õTC: 17.5000    õBO257   /CUO 32õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0033õ20200303õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0276    õTC: 17.5000    õBO257   /CUO 33õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0034õ20200403õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0277    õTC: 17.5000    õBO257   /CUO 34õ               õ               õ1õOPõ   õ                                        õYACHT  257CUOTAõ0035õ20200503õ          õ00000000õ00000000000000õ00000009005500õ00000000000000õ00000000000000õ00000000000000õ000000õ001/00927/0278    õTC: 17.5000    õBO257   /CUO 35õ               õ               õ1õ";

        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        // Then
        DeudasPagoComprasEntity respuesta = pagoComprasDAO.obtenerListaDeudas(new Cliente(), new MedioPago(),
                "306586608920");
        // Expected
        Assert.assertEquals("00000000", respuesta.getCodigoRetornoExtendido());
        Assert.assertEquals(20, respuesta.getListaDeudas().size());
    }

    /**
     * Obtener lista deudas sin deudas.
     *
     * @throws DAOException the DAO exception
     * @throws IatxException the iatx exception
     */
    @Test(expected = SinDeudasException.class)
    public void obtenerListaDeudasSinDeudas() throws DAOException, IatxException {
        // Given
        String servicio = "CNSDEUCLTT";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010300KTNK98  ********00010885000000182018082210172400000000IBF2009M000000000000CNSDEUCLTT1100000            00093098    00        00 010111652201808221017030000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036510110001õRCEõ03õNo existe deuda para la empresa seleccionada.                                                                                                                                                                                                                 õSIN DATOS      õNO HAY DATOS PARA CONSULTA SOLICITADA                                        õ";

        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        // Then
        pagoComprasDAO.obtenerListaDeudas(new Cliente(), new MedioPago(), "306586608920");
        // Expected
    }

    /**
     * Tiene adhesion true.
     *
     * @throws DAOException the DAO exception
     * @throws IatxException the iatx exception
     */
    @Test
    public void tieneAdhesionTrue() throws DAOException, IatxException {
        // Given
        String servicio = "CNSADHEPIR";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010300CRQJ37  ********00801310000000142018062214221400000000IBF00T87000000000000CNSADHEPIR1000000            00276937    00        00 014230306201806221421550000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0019100000000õSõ307013071150õ004õ01õDESPEGAR.COM.AR S.A.          õ        õ                      õ001õ00276937õ95220108              õ                                                            õ";

        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        // Then
        boolean respuesta = pagoComprasDAO.tieneAdhesion(new Cliente(), new MedioPago());
        // Expected
        Assert.assertEquals(true, respuesta);
    }

    /**
     * Tiene adhesion false.
     *
     * @throws DAOException the DAO exception
     * @throws IatxException the iatx exception
     */
    @Test
    public void tieneAdhesionFalse() throws DAOException, IatxException {
        // Given
        String servicio = "CNSADHEPIR";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010300CRQJ37  ********00743163000000142018070515402500000000IBF01CYR000000000000CNSADHEPIR1000000            00276937    00        00 015492374201807051540060000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810110001õRCEõ03õNo existe deuda para la empresa seleccionada.                                                                                                                                                                                                                 õSIN DATOS      õ011NO HAY DATOS PARA CONSULTA SOLICITADA                                        õ";

        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        // Then
        boolean respuesta = pagoComprasDAO.tieneAdhesion(new Cliente(), new MedioPago());
        // Expected
        Assert.assertEquals(false, respuesta);
    }

    /**
     * Tiene adhesion error iatx.
     *
     * @throws DAOException the DAO exception
     * @throws IatxException the iatx exception
     */
    @Test(expected = DAOException.class)
    public void tieneAdhesionErrorIatx() throws DAOException, IatxException {
        // Given
        String servicio = "CNSADHEPIR";
        String version = "100";
        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException());
        // Then
        pagoComprasDAO.tieneAdhesion(new Cliente(), new MedioPago());
        // Expected
    }

    /**
     * Tiene adhesion codigo error.
     *
     * @throws DAOException the DAO exception
     * @throws IatxException the iatx exception
     */
    @Test(expected = DAOException.class)
    public void tieneAdhesionCodigoError() throws DAOException, IatxException {
        // Given
        String servicio = "CNSADHEPIR";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010300CRQJ37  ********00743163000000142018070515402500000000IBF01CYR000000000000CNSADHEPIR1000000            00276937    00        00 015492374201807051540060000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810000000";

        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        // Then
        pagoComprasDAO.tieneAdhesion(new Cliente(), new MedioPago());
        // Expected
    }

    /**
     * Alta adhesion ok.
     *
     * @throws DAOException the DAO exception
     * @throws IatxException the iatx exception
     */
    @Test
    public void altaAdhesionOk() throws DAOException, IatxException {
        // Given
        String servicio = "ACTADHEPIR";
        String version = "100";
        String tramaResponse = "200000000000Q04HTML0009900010300CRQJ37  ********00743163000000162018070515405000000000        00000000ACTADHEPIR1000000            00276937    00        00  IN000215871831970042000000        0000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0005701õ307030885340õ001õ01õ00276937õ969771721õnueva adhesionõ";

        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        // Then
        pagoComprasDAO.altaAdhesion(new Cliente(), new MedioPago(), "306586608920");
        // Expected
    }

    /**
     * Ejecutar pago compras con deuda OK test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     */
    @Test
    public void ejecutarPagoComprasConDeudaOKTest() throws DAOException, IatxException {
        // Given
        String tramaResponse = "200000000000P04HTML0009900010300KTNK98  ********00042640000000182018071210335500000000IBF200FH000000000000PAGSICEFUL1000000            00093098    00        00 010321706201807121033380000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0339000000000õ2018071210330005004õYPF SOCIEDAD ANONIMA          õ000000126763631       õ20101317839õNõNõ000õ00000000000000õ00000000150234õ7õ9õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00õ0õ                               õ00000000000000õ01õ11õ0õ0054000720084700088000042562872õ00000000150234õ00000000õ õ00õ0õ                               õ00000000000000õ00000000õ õ00õ0õ                               õ00000000000000õ00000000õ õ00õ0õ                               õ00000000000000õ00000000õ õ00õ0õ                               õ00000000000000õ00000000õ õ00õ0õ                               õ00000000000000õ00000000õ õ00õ0õ                               õ00000000000000õ00000000õ õ00õ0õ                               õ00000000000000õ00000000õ õ00õ0õ                               õ00000000000000õ00000000õ õ00õ0õ                               õ00000000000000õ00000000õ õ00õ0õ                               õ00000000000000õ00000000õ õ00õ0õ                               õ00000000000000õ00000000õ õ00õ0õ                               õ00000000000000õ00000000õ õ00õ0õ                               õ00000000000000õ00000000õ õ00õ0õ                               õ00000000000000õ00000000õ õ01õFCõ2086B00003316                  õ0000õ20171021õ000000õ00000000150234õ00000000000000õ00000000000000õ00000000000000õ00000000150234õ00000000000000õ  õ                               õ    õ00000000õ000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ  õ                               õ    õ00000000õ000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ  õ                               õ    õ00000000õ000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ  õ                               õ    õ00000000õ000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ  õ                               õ    õ00000000õ000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ  õ                               õ    õ00000000õ000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ  õ                               õ    õ00000000õ000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ  õ                               õ    õ00000000õ000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ  õ                               õ    õ00000000õ000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ  õ                               õ    õ00000000õ000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ  õ                               õ    õ00000000õ000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ  õ                               õ    õ00000000õ000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ  õ                               õ    õ00000000õ000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ  õ                               õ    õ00000000õ000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ00000000000000õ";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGSICEFUL", "100")))).thenReturn(tramaResponse);

        // Then
        PagoCompraConDeudaEntity entity = pagoComprasDAO.ejecutarPagoComprasConDeuda(cliente, inEntity);

        // Expected
        Assert.assertNotNull(entity);
        Assert.assertEquals("2018071210330005004", entity.getNroComprobantePago());
        Assert.assertEquals("YPF SOCIEDAD ANONIMA          ", entity.getNombreEmpresa());
    }

    /**
     * Ejecutar pago compras con deuda error moneda incorrecta test.
     *
     * @throws DAOException the DAO exception
     * @throws IatxException the iatx exception
     */
    @Test
    public void ejecutarPagoComprasConDeudaErrorMonedaIncorrectaTest() throws DAOException, IatxException {
        // Given
        String tramaResponse = "200000000000P04HTML0009900010300KTNK98  ********00042657000000182018071914474000000000IBF201YS000000000000PAGSICEFUL1000000            00093098    00        00 014437401201807191447200000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810740127õRCEõ03õLa forma de pago elegida no está habilitada por la empresa.                                                                                                                                                                                                   õFORM.PGO.NO HA õFORMA DE PAGO NO HABILITADA POR LA EMPRESA                                      õ";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGSICEFUL", "100")))).thenReturn(tramaResponse);

        // Then
        PagoCompraConDeudaEntity entity = pagoComprasDAO.ejecutarPagoComprasConDeuda(cliente, inEntity);

        // Expected
        Assert.assertNotNull(entity);
        Assert.assertEquals("10740127", entity.getCodigoRetornoExtendido());
        Assert.assertEquals(Boolean.TRUE, entity.getTieneError());
    }
    
    /**
     * Ejecutar pago compras con deuda error timeout test.
     *
     * @throws DAOException the DAO exception
     * @throws IatxException the iatx exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void ejecutarPagoComprasConDeudaErrorTimeoutTest() throws DAOException, IatxException {
        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGSICEFUL", "100")))).thenThrow(new IatxException("Error Timeout!"));

        // Then
        PagoCompraConDeudaEntity entity = pagoComprasDAO.ejecutarPagoComprasConDeuda(cliente, inEntity);

    }
    
    /**
     * Ejecutar pago compras sin deuda OK test.
     *
     * @throws DAOException the DAO exception
     * @throws IatxException the iatx exception
     */
    @Test
    public void ejecutarPagoComprasSinDeudaOKTest() throws DAOException, IatxException {
        // Given
        String tramaResponse = "200000000000P04HTML0009900010300CRQJ37  ********00801307000000162018062117143700000000IBF01XQC000000000000PAGDEURECB1300000            00276937    00        00 017157330201806211714190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0021300000000õ2018062117140035004õEL MUNDO DEL JUGUETE S.A.     õ000õ00000000000000õ00000000012200õ01õ11õ072õ00000õ00000000õ201õ1õ000003243370õ000000012200õ00000000õ õ01õCRõ000000095220108õ0000õ20180621õ00000000012200õ";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGDEURECB", "130")))).thenReturn(tramaResponse);

        // Then
        PagoCompraSinDeudaEntity entity = pagoComprasDAO.ejecutarPagoComprasSinDeuda(cliente, inDTO);

        // Expected
        Assert.assertNotNull(entity);
        Assert.assertEquals("2018062117140035004", entity.getNroComprobantePago());
        Assert.assertEquals("EL MUNDO DEL JUGUETE S.A.     ", entity.getNombreEmpresa());
    }
    
    /**
     * Ejecutar pago compras sin deuda error moneda incorrecta test.
     *
     * @throws DAOException the DAO exception
     * @throws IatxException the iatx exception
     */
    @Test
    public void ejecutarPagoComprasSinDeudaErrorMonedaIncorrectaTest() throws DAOException, IatxException {
        // Given
        String tramaResponse = "200000000000P04HTML0009900010300KTNK98  ********00057542000000162018071911355200000000IBF200UQ000000000000PAGDEURECB1300000            00093098    00        00 000000000201807191135320000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036510740127õRCEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õFORMA DE PAGO INVALIDA                                                       õ";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGDEURECB", "130")))).thenReturn(tramaResponse);

        // Then
        PagoCompraSinDeudaEntity entity = pagoComprasDAO.ejecutarPagoComprasSinDeuda(cliente, inDTO);

        // Expected
        Assert.assertNotNull(entity);
        Assert.assertEquals("10740127", entity.getCodigoRetornoExtendido());
        Assert.assertEquals(Boolean.TRUE, entity.getTieneError());
    }
    
    /**
     * Ejecutar pago compras sin deuda error timeout test.
     *
     * @throws DAOException the DAO exception
     * @throws IatxException the iatx exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void ejecutarPagoComprasSinDeudaErrorTimeoutTest() throws DAOException, IatxException {
        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGDEURECB", "130")))).thenThrow(new IatxException("Error Timeout!"));

        // Then
        PagoCompraSinDeudaEntity entity = pagoComprasDAO.ejecutarPagoComprasSinDeuda(cliente, inDTO);
    }
}