package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.DetallePMCBOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ComprobantesPagoMisCuentasDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaComprobanteInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaDetallePMCOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.DetallePMCEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobantePagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoDetalleComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * The Class DetallePMCBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetallePMCBOTest {

    /** The detalle BO. */
    @InjectMocks
    private DetallePMCBO detalleBO = new DetallePMCBOImpl();

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje DAO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The comprobante pago mis cuentas dao. */
    @Mock
    private ComprobantesPagoMisCuentasDAO comprobantePagoMisCuentasDao;

    /** The medio pago BO. */
    @Mock
    private MediosPagoBO medioPagoBO;

    /** The mensaje. */
    Mensaje mensaje = new Mensaje();

    /** The cliente. */
    Cliente cliente = new Cliente();

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
        mensaje.setCodigo("1551");
        mensaje.setTag("TAG");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    }

    /**
     * Obtener detalle comprobantes PMC tipo pago 1 OK.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerDetalleComprobantesPMCTipoPago1OK() throws DAOException, BusinessException {
        // Given
        MedioPago resMedioPago = obtenerMedioDePago("N", 1, "F", "1");
        DetallePMCEntity detalle1 = obtenerDetallePMCEntity("1144111618         ", "04062017", "1021", "ARS",
                "000000000000", "1", "1234", "00000004    ", "00000", "00000000");
        DetallePMCEntity detalle2 = obtenerDetallePMCEntity("1144111618         ", "04062017", "1021", "ARS",
                "0000000123567", "1", "1234", "00000004    ", "21837891273", "00000102");

        List<DetallePMCEntity> destinatarios = new ArrayList<DetallePMCEntity>();
        destinatarios.add(detalle1);
        destinatarios.add(detalle2);

        ConsultaDetallePMCOutEntity detallePMC = new ConsultaDetallePMCOutEntity();
        detallePMC.setCodigoRetornoExtendido("00000000");
        detallePMC.setDestinatarios(destinatarios);
        // When
        Mockito.when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(resMedioPago);
        Mockito.when(comprobantePagoMisCuentasDao.consultarDetalle(Matchers.any(ConsultaComprobanteInEntity.class)))
                .thenReturn(detallePMC);
        // Then
        Respuesta<DetalleComprobanteDTO> res = detalleBO.obtenerDetallePMC("ASDF",
                "1144111618         201706041021ARS000000012356700000102891273", "Test", cliente);
        // Expected
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener detalle comprobantes PMC tipo pago 2 OK.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerDetalleComprobantesPMCTipoPago2OK() throws DAOException, BusinessException {
        MedioPago resMedioPago = obtenerMedioDePago("S", 2, "F", "1");
        Mockito.when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(resMedioPago);

        DetallePMCEntity detalle1 = obtenerDetallePMCEntity("1144111618         ", "04062017", "1021", "ARS",
                "000000000000", "1", "1234", "00000004    ", "00000", "00000000");

        List<DetallePMCEntity> destinatarios = new ArrayList<DetallePMCEntity>();
        destinatarios.add(detalle1);

        ConsultaDetallePMCOutEntity detallePMC = new ConsultaDetallePMCOutEntity();
        detallePMC.setCodigoRetornoExtendido("00000000");
        detallePMC.setDestinatarios(destinatarios);

        Mockito.when(comprobantePagoMisCuentasDao.consultarDetalle(Matchers.any(ConsultaComprobanteInEntity.class)))
                .thenReturn(detallePMC);

        Respuesta<DetalleComprobanteDTO> res = detalleBO.obtenerDetallePMC("ASDF",
                "1144111618         201706041021ARS000000012356700000102891273", "Test", cliente);
        Assert.assertNotNull(res);
    }

    /**
     * Obtener detalle comprobantes PMC tipo pago 3 OK.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerDetalleComprobantesPMCTipoPago3OK() throws DAOException, BusinessException {
        MedioPago resMedioPago = obtenerMedioDePago("S", 3, "F", "1");
        Mockito.when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(resMedioPago);

        DetallePMCEntity detalle1 = obtenerDetallePMCEntity("1144111618         ", "04062017", "1021", "USR",
                "000000000000", "1", "1234", "00000004    ", "00000", "00000000");
        DetallePMCEntity detalle2 = obtenerDetallePMCEntity("1144111618         ", "04062017", "1021", "ARS",
                "0000000123567", "1", "1234", "00000004    ", "21837891273", "00000102");

        List<DetallePMCEntity> destinatarios = new ArrayList<DetallePMCEntity>();
        destinatarios.add(detalle1);
        destinatarios.add(detalle2);

        ConsultaDetallePMCOutEntity detallePMC = new ConsultaDetallePMCOutEntity();
        detallePMC.setCodigoRetornoExtendido("00000000");
        detallePMC.setDestinatarios(destinatarios);

        Mockito.when(comprobantePagoMisCuentasDao.consultarDetalle(Matchers.any(ConsultaComprobanteInEntity.class)))
                .thenReturn(detallePMC);

        Respuesta<DetalleComprobanteDTO> res = detalleBO.obtenerDetallePMC("ASDF",
                "1144111618         201706041021ARS000000012356700000102891273", "Test", cliente);
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Es codigo retorno extendido no OK.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void esCodigoRetornoExtendidoNoOK() throws DAOException, BusinessException {
        MedioPago resMedioPago = obtenerMedioDePago("S", 2, "F", "1");
        Mockito.when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(resMedioPago);

        DetallePMCEntity detalle1 = obtenerDetallePMCEntity("1144111618         ", "04062017", "1021", "ARS",
                "000000000000", "1", "1234", "00000004    ", "00000", "00000000");

        List<DetallePMCEntity> destinatarios = new ArrayList<DetallePMCEntity>();
        destinatarios.add(detalle1);

        ConsultaDetallePMCOutEntity detallePMC = new ConsultaDetallePMCOutEntity();
        detallePMC.setCodigoRetornoExtendido("10000065");
        detallePMC.setDestinatarios(destinatarios);

        Mockito.when(comprobantePagoMisCuentasDao.consultarDetalle(Matchers.any(ConsultaComprobanteInEntity.class)))
                .thenReturn(detallePMC);

        Respuesta<DetalleComprobanteDTO> res = detalleBO.obtenerDetallePMC("ASDF",
                "1144111618         201706041021ARS000000012356700000102891273", "Test", cliente);
        Assert.assertNotNull(res);
    }

    /**
     * Obtener detalle comprobantes PMC error DAO exception.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerDetalleComprobantesPMCErrorDAOException() throws DAOException, BusinessException {
        MedioPago resMedioPago = obtenerMedioDePago("S", 2, "F", "1");
        Mockito.when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(resMedioPago);
        Mockito.when(comprobantePagoMisCuentasDao.consultarDetalle(Matchers.any(ConsultaComprobanteInEntity.class)))
                .thenThrow(new DAOException());
        Respuesta<DetalleComprobanteDTO> res = detalleBO.obtenerDetallePMC("", "", "", new Cliente());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener detalle PMC entity.
     *
     * @param idClienteEmpresa
     *            the id cliente empresa
     * @param fechaPago
     *            the fecha pago
     * @param horaPago
     *            the hora pago
     * @param moneda
     *            the moneda
     * @param importe
     *            the importe
     * @param tipoCuenta
     *            the tipo cuenta
     * @param sucursal
     *            the sucursal
     * @param nroCuenta
     *            the nroCuenta
     * @param nroSecuencia
     *            the nro secuencia
     * @param controlTicket
     *            the control ticket
     * @return the detalle PMC entity
     */
    private DetallePMCEntity obtenerDetallePMCEntity(String idClienteEmpresa, String fechaPago, String horaPago,
            String moneda, String importe, String tipoCuenta, String sucursal, String nroCuenta, String nroSecuencia,
            String controlTicket) {
        DetallePMCEntity detalle = new DetallePMCEntity();
        detalle.setIdClienteEmpresa(idClienteEmpresa);
        detalle.setFechaPago(fechaPago);
        detalle.setHoraPago(horaPago);
        detalle.setMoneda(moneda);
        detalle.setImporte(importe);
        detalle.setTipoCuenta(tipoCuenta);
        detalle.setSucursal(sucursal);
        detalle.setNroCuenta(nroCuenta);
        detalle.setNroSecuencia(nroSecuencia);
        detalle.setControlTicket(controlTicket);
        detalle.setDescripcionRecibo("8927349832      ");
        return detalle;
    }

    /**
     * Obtener medio de pago.
     *
     * @param pesPrepago
     *            the pes prepago
     * @param tipoPago
     *            the tipo pago
     * @param tipoEmpresa
     *            the tipo empresa
     * @param datosAdicionales
     *            the datos adicionales
     * @return the respuesta
     */
    private MedioPago obtenerMedioDePago(String pesPrepago, Integer tipoPago, String tipoEmpresa,
            String datosAdicionales) {
        MedioPago medioPago = new MedioPago();
        medioPago.setPesPrepago(pesPrepago);
        medioPago.setTipoPago(tipoPago);
        medioPago.setTipoEmpresa(tipoEmpresa);
        medioPago.setDatosAdicionales(datosAdicionales);
        medioPago.setPescodigorubro("DOJU");
        return medioPago;
    }

    @Test
    public void detalleComprobantePMCDTOToStringTest() throws ParseException {
        DetalleComprobantePagoMisCuentasDTO dto = new DetalleComprobantePagoMisCuentasDTO();
        dto.setAnotaciones("prueba");
        Date fecha = ISBANStringUtils.parsearFechaConAnio("07/05/2017");
        dto.setFechaDePago(fecha);
        dto.setEmpresa("prueba");
        dto.setNroComprobante("prueba");
        dto.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PMC_AFIP);
        String stringDTO = dto.toString();
        Assert.assertEquals(
                "DetalleComprobantePagoMisCuentasDTO [tipoPMC=null, empresa= prueba, labelDinamico= null, horaDePago= null, importe= null, nroControl= null, nroMedioDePago= null, tipoMedioDePago= null, cuitVEP= null, fechaVencimiento= null, codigoValidacion= null, transaccion= null, leyendaEmpresa= null, moneda= null, leyendaFactura= null]",
                stringDTO);

    }

    @Test
    public void detalleComprobantePMCDTOEqualsTest() throws ParseException {
        DetalleComprobantePagoMisCuentasDTO dto1 = new DetalleComprobantePagoMisCuentasDTO();
        DetalleComprobantePagoMisCuentasDTO dto2 = new DetalleComprobantePagoMisCuentasDTO();
        dto1.setAnotaciones("prueba");
        Date fecha = ISBANStringUtils.parsearFechaConAnio("07/05/2017");
        dto1.setFechaDePago(fecha);
        dto1.setNroComprobante("prueba");
        dto1.setIdClienteEmpresa("prueba");
        dto2.setIdClienteEmpresa("prueba");
        dto2.setAnotaciones("prueba");
        dto2.setFechaDePago(fecha);
        dto2.setNroComprobante("prueba");
        dto2.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PMC_AFIP);
        Assert.assertTrue(dto1.equals(dto2));

    }

    @Test
    public void detalleComprobanteAFIPTipoC() throws BusinessException, DAOException {
        MedioPago resMedioPago = obtenerMedioDePago("S", 2, "F", "C");
        Mockito.when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(resMedioPago);

        DetallePMCEntity detalle1 = obtenerDetallePMCEntity("1144111618         ", "04062017", "1021", "ARS",
                "000000000000", "1", "1234", "00000004    ", "00000", "00000000");
        DetallePMCEntity detalle2 = obtenerDetallePMCEntity("1144111618         ", "04062017", "1021", "ARS",
                "0000000123567", "1", "1234", "00000004    ", "21837891273", "00000102");

        List<DetallePMCEntity> destinatarios = new ArrayList<DetallePMCEntity>();
        destinatarios.add(detalle1);
        destinatarios.add(detalle2);

        ConsultaDetallePMCOutEntity detallePMC = new ConsultaDetallePMCOutEntity();
        detallePMC.setCodigoRetornoExtendido("00000000");
        detallePMC.setDestinatarios(destinatarios);

        Mockito.when(comprobantePagoMisCuentasDao.consultarDetalle(Matchers.any(ConsultaComprobanteInEntity.class)))
                .thenReturn(detallePMC);

        Respuesta<DetalleComprobanteDTO> res = detalleBO.obtenerDetallePMC("ASDF",
                "1144111618         201706041021ARS000000012356700000102891273", "Test", cliente);
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void detalleComprobanteAFIPDatosAdicionalesEnBlanco() throws BusinessException, DAOException {
        MedioPago resMedioPago = obtenerMedioDePago("S", 2, "F", "");
        Mockito.when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(resMedioPago);

        DetallePMCEntity detalle1 = obtenerDetallePMCEntity("1144111618         ", "04062017", "1021", "ARS",
                "000000000000", "1", "1234", "00000004    ", "00000", "00000000");
        DetallePMCEntity detalle2 = obtenerDetallePMCEntity("1144111618         ", "04062017", "1021", "ARS",
                "0000000123567", "1", "1234", "00000004    ", "21837891273", "00000102");

        List<DetallePMCEntity> destinatarios = new ArrayList<DetallePMCEntity>();
        destinatarios.add(detalle1);
        destinatarios.add(detalle2);

        ConsultaDetallePMCOutEntity detallePMC = new ConsultaDetallePMCOutEntity();
        detallePMC.setCodigoRetornoExtendido("00000000");
        detallePMC.setDestinatarios(destinatarios);

        Mockito.when(comprobantePagoMisCuentasDao.consultarDetalle(Matchers.any(ConsultaComprobanteInEntity.class)))
                .thenReturn(detallePMC);

        Respuesta<DetalleComprobanteDTO> res = detalleBO.obtenerDetallePMC("ASDF",
                "1144111618         201706041021ARS000000012356700000102891273", "Test", cliente);
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

    }

    @Test
    public void detalleComprobanteAFIPTipoEmpresaM() throws BusinessException, DAOException {
        MedioPago resMedioPago = obtenerMedioDePago("S", 2, "M", "");
        Mockito.when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(resMedioPago);

        DetallePMCEntity detalle1 = obtenerDetallePMCEntity("1144111618         ", "04062017", "1021", "ARS",
                "000000000000", "1", "1234", "00000004    ", "00000", "00000000");
        DetallePMCEntity detalle2 = obtenerDetallePMCEntity("1144111618         ", "04062017", "1021", "ARS",
                "0000000123567", "1", "1234", "00000004    ", "21837891273", "00000102");

        List<DetallePMCEntity> destinatarios = new ArrayList<DetallePMCEntity>();
        destinatarios.add(detalle1);
        destinatarios.add(detalle2);

        ConsultaDetallePMCOutEntity detallePMC = new ConsultaDetallePMCOutEntity();
        detallePMC.setCodigoRetornoExtendido("00000000");
        detallePMC.setDestinatarios(destinatarios);

        Mockito.when(comprobantePagoMisCuentasDao.consultarDetalle(Matchers.any(ConsultaComprobanteInEntity.class)))
                .thenReturn(detallePMC);

        Respuesta<DetalleComprobanteDTO> res = detalleBO.obtenerDetallePMC("ASDF",
                "1144111618         201706041021ARS000000012356700000102891273", "Test", cliente);
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

    }

    @Test
    public void obtenerDetalleComprobantesPMCTipoPago1ConTarjetaOK() throws DAOException, BusinessException {
        // Given
        MedioPago resMedioPago = obtenerMedioDePago("N", 1, "F", "1");
        DetallePMCEntity detalle1 = obtenerDetallePMCEntity("28802802           ", "14062018", "1616", "ARS",
                "00000000001200", "31", "6002", "00000004    ", "763818245059", "6953");

        List<DetallePMCEntity> destinatarios = new ArrayList<DetallePMCEntity>();
        destinatarios.add(detalle1);

        ConsultaDetallePMCOutEntity detallePMC = new ConsultaDetallePMCOutEntity();
        detallePMC.setCodigoRetornoExtendido("00000000");
        detallePMC.setDestinatarios(destinatarios);
        // When
        Mockito.when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(resMedioPago);
        Mockito.when(comprobantePagoMisCuentasDao.consultarDetalle(Matchers.any(ConsultaComprobanteInEntity.class)))
                .thenReturn(detallePMC);
        // Then
        Respuesta<DetalleComprobanteDTO> res = detalleBO.obtenerDetallePMC("ASDF",
                "28802802           201806141616ARS000000000012006953245059", "Test", cliente);
        // Expected
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }
    
    
    @Test
    public void obtenerComprobantesPorEmpresas() throws DAOException, BusinessException {
        // Given
        MedioPago resMedioPago = obtenerMedioDePago("N", 1, "F", "1");
        DetallePMCEntity detalle1 = obtenerDetallePMCEntity("28802802           ", "14062018", "1616", "ARS",
                "00000000001200", "31", "6002", "00000004    ", "763818245059", "6953");
        DetallePMCEntity detalle2 = obtenerDetallePMCEntity("1144111618         ", "04062017", "1021", "ARS",
                "0000000123567", "1", "1234", "00000004    ", "21837891273", "00000102");

        List<DetallePMCEntity> destinatarios = new ArrayList<DetallePMCEntity>();
        destinatarios.add(detalle1);
        destinatarios.add(detalle2);

        ConsultaDetallePMCOutEntity detallePMC = new ConsultaDetallePMCOutEntity();
        detallePMC.setCodigoRetornoExtendido("00000000");
        detallePMC.setDestinatarios(destinatarios);
        // When
        Mockito.when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(resMedioPago);
        Mockito.when(comprobantePagoMisCuentasDao.consultarDetalle(Matchers.any(ConsultaComprobanteInEntity.class)))
                .thenReturn(detallePMC);
        // Then
        Respuesta<ComprobantesDTO> res = detalleBO.obtenerComprobantesPorEmpresas(new TransaccionDTO(), cliente);
        // Expected
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

}
