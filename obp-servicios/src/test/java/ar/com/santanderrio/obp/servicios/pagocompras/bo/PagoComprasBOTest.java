package ar.com.santanderrio.obp.servicios.pagocompras.bo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.pagocompras.bo.impl.PagoComprasBOImpl;
import ar.com.santanderrio.obp.servicios.pagocompras.dao.PagoComprasDAO;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasInDTO;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasOutDTO;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.ComprobanteDeudaEntity;
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
 * The Class PagoComprasBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class PagoComprasBOTest {

    /** The pago compras BO. */
    @InjectMocks
    private PagoComprasBOImpl pagoComprasBO;

    /** The pago compras DAO. */
    @Mock
    private PagoComprasDAO pagoComprasDAO;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The detalle cuenta DAO. */
    @Mock
    private DetalleCuentaDAO detalleCuentaDAO;

    /** The cliente. */
    private Cliente cliente = new Cliente();

    /** The in DTO. */
    private PagoComprasInDTO inDTO = new PagoComprasInDTO();

    /** The entity sin deuda. */
    private PagoCompraSinDeudaEntity entitySinDeuda = new PagoCompraSinDeudaEntity();

    /** The pago compras. */
    private NuevoPago pagoCompras = new NuevoPago();

    /** The medio pago. */
    private MedioPago medioPago = new MedioPago();

    /** The deudas entity. */
    private DeudasPagoComprasEntity deudasEntity = new DeudasPagoComprasEntity();

    /** The deuda. */
    private DeudaPagoComprasEntity deuda = new DeudaPagoComprasEntity();

    /** The divisa. */
    private DivisaEnum divisa = DivisaEnum.DOLAR;

    /** The cuenta. */
    private Cuenta cuenta = new Cuenta();

    /** The entity con deuda. */
    private PagoCompraConDeudaEntity entityConDeuda = new PagoCompraConDeudaEntity();

    /** The comprobante entity. */
    private ComprobanteDeudaEntity comprobanteEntity = new ComprobanteDeudaEntity();

    /**
     * Inits the.
     */
    @Before
    public void init() {
        cliente = ClienteMock.infoCliente();

        // Sin deuda
        inDTO.setPid("00123456789");
        entitySinDeuda.setNroComprobantePago("12345678");
        // Con deuda
        pagoCompras.setMonto("100");
        pagoCompras.setFacturaNumero("1234567890123456-789");
        medioPago.setPpdctaIdEmpAcuerdo("345345234562");
        medioPago.setPpdctaCodProdAcuerdo("345");
        medioPago.setPpdctaNroInstaCuerdo("24");
        deudasEntity.setIdClienteTerceros("000004567256745");
        deudasEntity.setCuitClienteTerceros("34534563456");
        deudasEntity.setIndicadorClientePropio("4");
        deudasEntity.setIndicadorExcepcionComision("5");
        deudasEntity.setCondicionIva("6");
        deudasEntity.setTipoIngresosBrutos("6");
        deuda.setTipoComprobanteDeuda("34");
        deuda.setNroComprobanteDeuda("1234567890123456");
        deuda.setNroCuotaDeuda("789");
        deuda.setFechaVencimientoDeuda("20180912");
        deuda.setImportePunitoriosDeuda("00000000002000");
        deuda.setImporteIvaAdicionalInteresesDeuda("00000000000100");
        deuda.setImporteIvaAdicionalInteresesDeuda("00000000000100");
        deudasEntity.getListaDeudas().add(deuda);
        cuenta.setCbu("1234567890123456789012");
        cliente.getCuentas().add(cuenta);
        comprobanteEntity.setNroCprb("1235678");
        comprobanteEntity.setNroCuo("12345");
        entityConDeuda.getComprobantesDeuda().add(comprobanteEntity);
        entityConDeuda.setNroComprobantePago("34345445646545445");
    }

    /**
     * Obtener deudas ok.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerDeudasOk() throws BusinessException, DAOException {
        // Given
        DeudasPagoComprasEntity deudasPagoComprasEntity = new DeudasPagoComprasEntity();
        List<DeudaPagoComprasEntity> deudas = new ArrayList<DeudaPagoComprasEntity>();
        deudasPagoComprasEntity.setListaDeudas(deudas);

        DeudaPagoComprasEntity deuda1 = new DeudaPagoComprasEntity();
        deuda1.setFechaVencimientoDeuda("20181003");

        DeudaPagoComprasEntity deuda2 = new DeudaPagoComprasEntity();
        deuda2.setFechaVencimientoDeuda("20181001");

        DeudaPagoComprasEntity deuda3 = new DeudaPagoComprasEntity();
        deuda3.setFechaVencimientoDeuda("20181005");

        deudas.add(deuda1);
        deudas.add(deuda2);
        deudas.add(deuda3);
        // When
        when(pagoComprasDAO.obtenerListaDeudas(Matchers.any(Cliente.class), Matchers.any(MedioPago.class),
                Matchers.anyString())).thenReturn(deudasPagoComprasEntity);
        // Then
        List<DeudaPagoComprasEntity> respuesta = pagoComprasBO.obtenerDeudas(new Cliente(), new MedioPago(),
                "306586608920");
        // Expected
        assertEquals(3, respuesta.size());
        assertEquals("20181001", respuesta.get(0).getFechaVencimientoDeuda());
    }

    /**
     * Obtener deudas sin deudas.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerDeudasSinDeudas() throws BusinessException, DAOException {
        // Given
        SinDeudasException sinDeudasException = new SinDeudasException();
        // When
        when(pagoComprasDAO.obtenerListaDeudas(Matchers.any(Cliente.class), Matchers.any(MedioPago.class),
                Matchers.anyString())).thenThrow(sinDeudasException);
        // Then
        List<DeudaPagoComprasEntity> respuesta = pagoComprasBO.obtenerDeudas(new Cliente(), new MedioPago(),
                "306586608920");
        // Expected
        assertEquals(0, respuesta.size());
    }

    /**
     * Obtener divisa pesos.
     */
    @Test
    public void obtenerDivisaPesos() {
        MedioPago medioPago = new MedioPago();
        medioPago.setPpdctaMoneda("0");
        DivisaEnum respuesta = pagoComprasBO.obtenerDivisa(medioPago);
        assertEquals(DivisaEnum.PESO, respuesta);
    }

    /**
     * Obtener divisa dolar.
     */
    @Test
    public void obtenerDivisaDolar() {
        MedioPago medioPago = new MedioPago();
        medioPago.setPpdctaMoneda("2");
        DivisaEnum respuesta = pagoComprasBO.obtenerDivisa(medioPago);
        assertEquals(DivisaEnum.DOLAR, respuesta);
    }

    /**
     * Obtener cuentas ok.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCuentasOk() throws BusinessException {
        // Given
        Cliente cliente = ClienteMock.clienteConCuentas();
        // When
        // Then
        List<Cuenta> respuesta = pagoComprasBO.obtenerCuentas(cliente, DivisaEnum.PESO);
        // Expected
        assertEquals(1, respuesta.size());
    }

    /**
     * Realizar adhesion sin adhesion.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void realizarAdhesionSinAdhesion() throws DAOException {
        // Given
        // When
        when(pagoComprasDAO.tieneAdhesion(Matchers.any(Cliente.class), Matchers.any(MedioPago.class)))
                .thenReturn(false);
        // Then
        pagoComprasBO.realizarAdhesion(new Cliente(), new MedioPago(), "306586608920");
        // Expected
        Mockito.verify(pagoComprasDAO, Mockito.times(1)).altaAdhesion(Matchers.any(Cliente.class),
                Matchers.any(MedioPago.class), Matchers.anyString());
    }

    /**
     * Realizar adhesion ya adherido.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void realizarAdhesionYaAdherido() throws DAOException {
        // Given
        // When
        when(pagoComprasDAO.tieneAdhesion(Matchers.any(Cliente.class), Matchers.any(MedioPago.class))).thenReturn(true);
        // Then
        pagoComprasBO.realizarAdhesion(new Cliente(), new MedioPago(), "306586608920");
        // Expected
        Mockito.verify(pagoComprasDAO, Mockito.never()).altaAdhesion(Matchers.any(Cliente.class),
                Matchers.any(MedioPago.class), Matchers.anyString());
    }

    /**
     * Realizar adhesion error consulta adhesion.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void realizarAdhesionErrorConsultaAdhesion() throws DAOException {
        // Given
        DAOException dAOException = new DAOException();
        // When
        when(pagoComprasDAO.tieneAdhesion(Matchers.any(Cliente.class), Matchers.any(MedioPago.class)))
                .thenThrow(dAOException);
        // Then
        pagoComprasBO.realizarAdhesion(new Cliente(), new MedioPago(), "306586608920");
        // Expected
        Mockito.verify(pagoComprasDAO, Mockito.never()).altaAdhesion(Matchers.any(Cliente.class),
                Matchers.any(MedioPago.class), Matchers.anyString());
    }

    /**
     * Ejecutar pago compras sin deuda OK test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoComprasSinDeudaOKTest() throws BusinessException, DAOException {
        // Given
        entitySinDeuda.setTieneError(Boolean.FALSE);

        // When
        Mockito.when(pagoComprasDAO.ejecutarPagoComprasSinDeuda(Matchers.any(Cliente.class),
                Matchers.any(PagoComprasInDTO.class))).thenReturn(entitySinDeuda);

        // Then
        PagoComprasOutDTO dto = pagoComprasBO.ejecutarPagoComprasSinDeuda(cliente, inDTO);

        // Expected
        Assert.assertNotNull(dto);
        Assert.assertEquals("00123456789", dto.getNumeroBoleta());
        Assert.assertEquals("12345678", dto.getComprobante());
        Assert.assertEquals(Boolean.FALSE, dto.getTieneError());
    }

    /**
     * Ejecutar pago compras sin deuda con error test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoComprasSinDeudaConErrorTest() throws BusinessException, DAOException {
        // Given
        entitySinDeuda.setTieneError(Boolean.TRUE);
        entitySinDeuda.setCodigoRetornoExtendido("11262122");

        // When
        Mockito.when(pagoComprasDAO.ejecutarPagoComprasSinDeuda(Matchers.any(Cliente.class),
                Matchers.any(PagoComprasInDTO.class))).thenReturn(entitySinDeuda);

        // Then
        PagoComprasOutDTO dto = pagoComprasBO.ejecutarPagoComprasSinDeuda(cliente, inDTO);

        // Expected
        Assert.assertNotNull(dto);
        Assert.assertEquals(Boolean.TRUE, dto.getTieneError());
        Assert.assertEquals("11262122", dto.getCodError());
    }

    /**
     * Ejecutar pago compras sin deuda Cuenta Alfa No Verificada Nunca Opero.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoComprasSinDeudaCuentaAlfaNoVerificadaNuncaOpero() throws BusinessException, DAOException {
        // Given
        entitySinDeuda.setTieneError(Boolean.TRUE);
        entitySinDeuda.setCodigoRetornoExtendido("10009079");

        // When
        Mockito.when(pagoComprasDAO.ejecutarPagoComprasSinDeuda(Matchers.any(Cliente.class),
                Matchers.any(PagoComprasInDTO.class))).thenReturn(entitySinDeuda);

        // Then
        PagoComprasOutDTO dto = pagoComprasBO.ejecutarPagoComprasSinDeuda(cliente, inDTO);

        // Expected
        Assert.assertNotNull(dto);
        Assert.assertEquals(Boolean.TRUE, dto.getTieneError());
        Assert.assertEquals("10009079", dto.getCodError());
    }

    /**
     * Ejecutar pago compras sin deuda Cuenta Alfa No Verificada.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoComprasSinDeudaCuentaAlfaNoVerificada() throws BusinessException, DAOException {
        // Given
        entitySinDeuda.setTieneError(Boolean.TRUE);
        entitySinDeuda.setCodigoRetornoExtendido("10009080");

        // When
        Mockito.when(pagoComprasDAO.ejecutarPagoComprasSinDeuda(Matchers.any(Cliente.class),
                Matchers.any(PagoComprasInDTO.class))).thenReturn(entitySinDeuda);

        // Then
        PagoComprasOutDTO dto = pagoComprasBO.ejecutarPagoComprasSinDeuda(cliente, inDTO);

        // Expected
        Assert.assertNotNull(dto);
        Assert.assertEquals(Boolean.TRUE, dto.getTieneError());
        Assert.assertEquals("10009080", dto.getCodError());
    }

    /**
     * Ejecutar pago compras sin deuda error timeout test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = BusinessException.class)
    public void ejecutarPagoComprasSinDeudaErrorTimeoutTest() throws BusinessException, DAOException {
        // Given
        DAOException daoException = new DAOException("Error Timeout!");

        // When
        Mockito.when(pagoComprasDAO.ejecutarPagoComprasSinDeuda(Matchers.any(Cliente.class),
                Matchers.any(PagoComprasInDTO.class))).thenThrow(daoException);

        // Then
        PagoComprasOutDTO dto = pagoComprasBO.ejecutarPagoComprasSinDeuda(cliente, inDTO);
    }

    /**
     * Ejecutar pago compras con deuda OK con adhesion test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoComprasConDeudaOKConAdhesionTest() throws BusinessException, DAOException {
        // Given
        entityConDeuda.setTieneError(Boolean.FALSE);
        boolean tieneAdhesion = true;

        // When
        Mockito.when(sesionParametros.getDeudasPagoComprasEntity()).thenReturn(deudasEntity);
        Mockito.when(pagoComprasDAO.ejecutarPagoComprasConDeuda(Matchers.any(Cliente.class),
                Matchers.any(PagoCompraConDeudaInEntity.class))).thenReturn(entityConDeuda);
        Mockito.when(pagoComprasDAO.tieneAdhesion(Matchers.any(Cliente.class), Matchers.any(MedioPago.class)))
                .thenReturn(tieneAdhesion);

        // Then
        PagoComprasOutDTO dto = pagoComprasBO.ejecutarPagoComprasConDeuda(cliente, pagoCompras, medioPago, cuenta,
                divisa);

        // Expected
        Assert.assertNotNull(dto);
        Assert.assertEquals("1235678-12345", dto.getNumeroOrden());
        Assert.assertEquals("34345445646545445", dto.getComprobante());
        Assert.assertEquals(Boolean.FALSE, dto.getTieneError());
    }

    /**
     * Ejecutar pago compras con deuda OK sin adhesion test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoComprasConDeudaOKSinAdhesionTest() throws BusinessException, DAOException {
        // Given
        entityConDeuda.setTieneError(Boolean.FALSE);
        boolean tieneAdhesion = false;

        // When
        Mockito.when(sesionParametros.getDeudasPagoComprasEntity()).thenReturn(deudasEntity);
        Mockito.when(pagoComprasDAO.ejecutarPagoComprasConDeuda(Matchers.any(Cliente.class),
                Matchers.any(PagoCompraConDeudaInEntity.class))).thenReturn(entityConDeuda);
        Mockito.when(pagoComprasDAO.tieneAdhesion(Matchers.any(Cliente.class), Matchers.any(MedioPago.class)))
                .thenReturn(tieneAdhesion);

        // Then
        PagoComprasOutDTO dto = pagoComprasBO.ejecutarPagoComprasConDeuda(cliente, pagoCompras, medioPago, cuenta,
                divisa);

        // Expected
        Assert.assertNotNull(dto);
        Assert.assertEquals("1235678-12345", dto.getNumeroOrden());
        Assert.assertEquals("34345445646545445", dto.getComprobante());
        Assert.assertEquals(Boolean.FALSE, dto.getTieneError());
    }

    /**
     * Ejecutar pago compras con deuda OK adhesion exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoComprasConDeudaOKAdhesionExceptionTest() throws BusinessException, DAOException {
        // Given
        entityConDeuda.setTieneError(Boolean.FALSE);

        // When
        Mockito.when(sesionParametros.getDeudasPagoComprasEntity()).thenReturn(deudasEntity);
        Mockito.when(pagoComprasDAO.ejecutarPagoComprasConDeuda(Matchers.any(Cliente.class),
                Matchers.any(PagoCompraConDeudaInEntity.class))).thenReturn(entityConDeuda);
        Mockito.when(pagoComprasDAO.tieneAdhesion(Matchers.any(Cliente.class), Matchers.any(MedioPago.class)))
                .thenThrow(new DAOException("Error en Adhhesion!"));

        // Then
        PagoComprasOutDTO dto = pagoComprasBO.ejecutarPagoComprasConDeuda(cliente, pagoCompras, medioPago, cuenta,
                divisa);

        // Expected
        Assert.assertNotNull(dto);
        Assert.assertEquals("1235678-12345", dto.getNumeroOrden());
        Assert.assertEquals("34345445646545445", dto.getComprobante());
        Assert.assertEquals(Boolean.FALSE, dto.getTieneError());
    }

    /**
     * Ejecutar pago compras con deuda con error test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoComprasConDeudaConErrorTest() throws BusinessException, DAOException {
        // Given
        entityConDeuda.setTieneError(Boolean.TRUE);
        entityConDeuda.setCodigoRetornoExtendido("11262122");

        // When
        Mockito.when(sesionParametros.getDeudasPagoComprasEntity()).thenReturn(deudasEntity);
        Mockito.when(pagoComprasDAO.ejecutarPagoComprasConDeuda(Matchers.any(Cliente.class),
                Matchers.any(PagoCompraConDeudaInEntity.class))).thenReturn(entityConDeuda);

        // Then
        PagoComprasOutDTO dto = pagoComprasBO.ejecutarPagoComprasConDeuda(cliente, pagoCompras, medioPago, cuenta,
                divisa);

        // Expected
        Assert.assertNotNull(dto);
        Assert.assertEquals(Boolean.TRUE, dto.getTieneError());
        Assert.assertEquals("11262122", dto.getCodError());
    }

    /**
     * Ejecutar pago compras con deuda Cuenta Alfa No Verificada Nunca Opero.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoComprasConDeudaCuentaAlfaNoVerificadaNuncaOpero() throws BusinessException, DAOException {
        // Given
        entityConDeuda.setTieneError(Boolean.TRUE);
        entityConDeuda.setCodigoRetornoExtendido("10009079");

        // When
        Mockito.when(sesionParametros.getDeudasPagoComprasEntity()).thenReturn(deudasEntity);
        Mockito.when(pagoComprasDAO.ejecutarPagoComprasConDeuda(Matchers.any(Cliente.class),
                Matchers.any(PagoCompraConDeudaInEntity.class))).thenReturn(entityConDeuda);

        // Then
        PagoComprasOutDTO dto = pagoComprasBO.ejecutarPagoComprasConDeuda(cliente, pagoCompras, medioPago, cuenta,
                divisa);

        // Expected
        Assert.assertNotNull(dto);
        Assert.assertEquals(Boolean.TRUE, dto.getTieneError());
        Assert.assertEquals("10009079", dto.getCodError());
    }

    /**
     * Ejecutar pago compras con deuda Cuenta Alfa No Verificada.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoComprasConDeudaCuentaAlfaNoVerificada() throws BusinessException, DAOException {
        // Given
        entityConDeuda.setTieneError(Boolean.TRUE);
        entityConDeuda.setCodigoRetornoExtendido("10009080");

        // When
        Mockito.when(sesionParametros.getDeudasPagoComprasEntity()).thenReturn(deudasEntity);
        Mockito.when(pagoComprasDAO.ejecutarPagoComprasConDeuda(Matchers.any(Cliente.class),
                Matchers.any(PagoCompraConDeudaInEntity.class))).thenReturn(entityConDeuda);

        // Then
        PagoComprasOutDTO dto = pagoComprasBO.ejecutarPagoComprasConDeuda(cliente, pagoCompras, medioPago, cuenta,
                divisa);

        // Expected
        Assert.assertNotNull(dto);
        Assert.assertEquals(Boolean.TRUE, dto.getTieneError());
        Assert.assertEquals("10009080", dto.getCodError());
    }


    /**
     * Ejecutar pago compras con deuda error timeout test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = BusinessException.class)
    public void ejecutarPagoComprasConDeudaErrorTimeoutTest() throws BusinessException, DAOException {
        // Given
        DAOException daoException = new DAOException("Error Timeout!");

        // When
        Mockito.when(sesionParametros.getDeudasPagoComprasEntity()).thenReturn(deudasEntity);
        Mockito.when(pagoComprasDAO.ejecutarPagoComprasConDeuda(Matchers.any(Cliente.class),
                Matchers.any(PagoCompraConDeudaInEntity.class))).thenThrow(daoException);

        // Then
        PagoComprasOutDTO dto = pagoComprasBO.ejecutarPagoComprasConDeuda(cliente, pagoCompras, medioPago, cuenta,
                divisa);
    }
}
