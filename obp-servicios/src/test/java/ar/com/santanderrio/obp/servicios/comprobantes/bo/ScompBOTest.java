package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import ar.com.santanderrio.obp.servicios.comprobantes.dto.*;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.ComprobanteResponse;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.Filtros;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.Comprobante;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteCompraVentaDolar;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCAfip;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCConDeuda;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCSinDeuda;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCVEP;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePagoCompraOk;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteScomp;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTarjRecargOk;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTransfInmRioOB;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTransfInmRioRio;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTrfcci;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTrfcta;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTrfcta7x24;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.CuentaDestino;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.CuentaOrigen;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.DestinoTrfcci;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.DestinoTrfcta;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.OperacionEstado;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.Origen;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.RespuestaScomp;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaOB;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaPMCAfip;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaPMCConDeuda;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaPMCSinDeuda;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaPMCVEP;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaRio;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.ScompBOImpl;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.scomp.dao.ScompDAO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransInmediataOBBuilder;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransInmediataRioBuilder;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConceptoView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

@RunWith(MockitoJUnitRunner.class)
public class ScompBOTest {

    /** The transferencias inmediatas bo. */
    @InjectMocks
    private ScompBO scompBo = new ScompBOImpl();

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    private ScompDAO scompDAO;

    @Mock
    private ScompBOImpl ScompBOImpl;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje DAO. */
    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private MediosPagoBO mediosPagoBO;
    
    @Mock
    private BuscadorEmpresaDAO buscadorMediosPagoDAO;

    @Mock
    Future<ComprobanteResponse> thread;

    @Test
    public void obtenerComprobantesRioOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesRio("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobanteDTOParaTransfInmediatasRioRioTBanco() throws DAOException {
        ComprobanteTransfInmRioRio comprobant = new ComprobanteTransfInmRioRio();
        comprobant.setCanal("04");
        ar.com.santanderrio.obp.servicios.scomp.client.domain.Cliente cliente = new ar.com.santanderrio.obp.servicios.scomp.client.domain.Cliente();
        cliente.setFechaNac("19771108");
        cliente.setNroDoc("00026328189");
        cliente.setNup("02810993");
        cliente.setTpoDoc("N");
        comprobant.setCliente(cliente);
        comprobant.setDescEstado("Aceptada");
        comprobant.setDescMedioPago("");
        comprobant.setEstadoOper(OperacionEstado.A);
        comprobant.setFechaGen("2017-10-04T00:00:00Z");
        comprobant.setFechaOper("2017/10/04T00:00:00Z");
        comprobant.setHoraOper("HoraOper");
        comprobant.setIdMedioPago("");
        comprobant.setSubCanal("99");
        comprobant.setSubTpoComprobante("10");
        comprobant.setTpoComprobante("2");

        TransferenciaRio transferencia = new TransferenciaRio();
        transferencia.setAlias("Juancito");
        transferencia.setNombreDestinatario("Juan Pose");
        transferencia.setImporte("1234,00");
        transferencia.setTipoCuentaOrigen("09");
        transferencia.setSucursalCuentaOrigen("123");
        transferencia.setNumeroCuentaOrigen("0037007");
        transferencia.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        transferencia.setTipoCuentaDestino("00");
        transferencia.setSucursalCuentaDestino("080");
        transferencia.setNumeroCuentaDestino("0691708");
        transferencia.setConcepto("Alquiler");
        transferencia.setDescripcionConcepto("Pago de ALQ");
        transferencia.setPlazoAcreditacion("Inmediata");
        transferencia.setEmailDestinatario("");
        transferencia.setMensaje("");
        transferencia.setNroComprobante("1234567");
        comprobant.setTransferencia(transferencia);

        Assert.assertNotNull(comprobant);
    }

    @Test
    public void obtenerComprobanteDTOParaPagoDeCompras() throws DAOException {

        ComprobanteDTO comprobantePagoCompra = new ComprobanteDTO();
        Date date = new Date();
        comprobantePagoCompra.setNecesitaMoneda(Boolean.FALSE);
        comprobantePagoCompra.setFecha(date);
        comprobantePagoCompra.setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_PUNTUAL);
        comprobantePagoCompra.setImportePesos(ISBANStringUtils.convertirABigDecimal("222,20"));
        IdentificacionCuenta id = new IdentificacionCuenta();
        id.setNroSucursal("0084");
        id.setNroCuentaProducto("800004256287");
        comprobantePagoCompra.setCtaMedioDePagoPesos(id.toString());
        comprobantePagoCompra.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_UNICA);
        comprobantePagoCompra.setDestinatario("CONSULTA =)");
        DetalleComprobantePagoDeComprasDTO detalle = new DetalleComprobantePagoDeComprasDTO();
        detalle.setIdentificacion("30587480359");
        detalle.setNroComprobante("2018082316100036004");
        detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_TRANSFERENCIA.getDetalle());
        comprobantePagoCompra.setDetalle(detalle);

        Assert.assertNotNull(comprobantePagoCompra);
    }

    @Test
	public void obtenerComprobanteDTOParaPagoDeComprasErrorTest() throws DAOException {

		ComprobantePagoCompraOk comprobante = new ComprobantePagoCompraOk();

		comprobante.setCanal(null);
		comprobante.setCliente(null);
		comprobante.setCodProdAcuerdoEmp(null);
		comprobante.setDescCuenta(null);
		comprobante.setEstadoOper(null);
		comprobante.setFechaOper(null);
		comprobante.setIdEmpresa(null);
		comprobante.setIdentificacion(null);
		comprobante.setImporteTotalDebito(null);
		comprobante.setMonedaCtaDeb(null);
		comprobante.setNombreEmpresa(null);
		comprobante.setNombreServicio(null);
		comprobante.setNombreServicio(null);
		comprobante.setNroBoleta(null);
		comprobante.setNroComprobante(null);
		comprobante.setNroCtaDeb(null);
		comprobante.setNroInstanciaAcuerdoEmp(null);
		comprobante.setPagos(null);
		comprobante.setSubCanal(null);
		comprobante.setSubCanal(null);
		comprobante.setSubTpoComprobante(null);
		comprobante.setSucCtaDeb(null);
		comprobante.setTpoComprobante(null);

		ComprobanteDTO comprob = ScompBOImpl.obtenerComprobanteDTOParaPagoDeCompras(comprobante);

		Assert.assertNull(comprob);

	}

	@Test
    public void obtenerComprobantesRioErrorTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setCodRet(1);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesRio("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesRioExceptionTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenThrow(new DAOException());
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesRio("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesRioAsyncOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Future<Respuesta<ComprobantesDTO>> rta = scompBo.obtenerComprobantesRioAsync("213123", transaccion);
        Future<Respuesta<ComprobantesDTO>> rta2 = scompBo.obtenerComprobantesRioAsync("213123123", transaccion);
        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        Respuesta<ComprobantesDTO> respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        try {
            Respuesta<ComprobantesDTO> comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesOtrosBancosOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesOtrosBancos("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesOtrosBancosErrorTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setCodRet(1);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesOtrosBancos("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesOtrosBancosExceptionTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenThrow(new DAOException());
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesOtrosBancos("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesOtrosBancosAsyncOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Future<Respuesta<ComprobantesDTO>> rta = scompBo.obtenerComprobantesOtrosBancosAsync("213123", transaccion);
        Future<Respuesta<ComprobantesDTO>> rta2 = scompBo.obtenerComprobantesOtrosBancosAsync("213123123213",
                transaccion);
        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        Respuesta<ComprobantesDTO> respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        try {
            Respuesta<ComprobantesDTO> comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesTBancoPMCAfipTest() throws DAOException, IllegalAccessException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobantes.getRespuestaScomp().getComprobantes().add(comprobanteScomp);
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        ComprobantePMCAfip comprobanteTransOB = new ComprobantePMCAfip();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobanteScomp.getComprobanteList().add(comprobanteTransOB);

        comprobanteTransOB.setFechaGen("2017-07-07T00:00:00Z");
        comprobanteTransOB.setFechaOper("2017-07-07T00:00:00Z");
        comprobanteTransOB.setHoraOper("14:42:00");
        comprobanteTransOB.setTransferencia(new TransferenciaPMCAfip());
        comprobanteTransOB.getTransferencia().setImporte("99999.00");
        comprobanteTransOB.getTransferencia().setEmpresa("DOMESTICO MENOR 16 HS");
        comprobanteTransOB.getTransferencia().setMoneda("$");
        comprobanteTransOB.getTransferencia().setFechaHoraPago("20170802103200");
        comprobanteTransOB.getTransferencia().setFechaVencimiento("00000000");
        comprobanteTransOB.getTransferencia().setIdentificacion("20301234561");
        comprobanteTransOB.getTransferencia().setIdentificacion2("27218472350");
        comprobanteTransOB.getTransferencia().setPeriodoPago("092017");
        comprobanteTransOB.getTransferencia().setDatosAdicionales("");
        comprobanteTransOB.getTransferencia().setTipoCuentaDebito("09");
        comprobanteTransOB.getTransferencia().setSucursalCuentaDebito("065");
        comprobanteTransOB.getTransferencia().setNumeroCuentaDebito("0691371");
        comprobanteTransOB.getTransferencia().setNumControl("7382");
        comprobanteTransOB.getTransferencia().setNroComprobante("0003864");

        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        MedioPago medioPago = new MedioPago();
        medioPago.setPesIdentificacion("");
        Mockito.when(mediosPagoBO.obtenerPorNombreFantasia(Matchers.anyString())).thenReturn(medioPago);
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesPMCAfip("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesOtrosComprobantesOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesOtrosComprobantes("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesOtrosComprobantesErrorTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setCodRet(1);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesOtrosComprobantes("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesOtrosComprobantesExceptionTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenThrow(new DAOException());
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesOtrosComprobantes("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesOtrosComprobantesAsyncOkTest() throws DAOException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Future<Respuesta<ComprobantesDTO>> rta = scompBo.obtenerComprobantesOtrosComprobantesAsync("213123",
                transaccion);
        Future<Respuesta<ComprobantesDTO>> rta2 = scompBo.obtenerComprobantesOtrosComprobantesAsync("213123123213",
                transaccion);
        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        Respuesta<ComprobantesDTO> respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        try {
            Respuesta<ComprobantesDTO> comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesPagoDeComprasOkTest() throws DAOException, IllegalAccessException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesPagoDeCompras("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesPagoDeComprasErrorTest() throws DAOException, IllegalAccessException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setCodRet(1);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesPagoDeCompras("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesPagoDeComprasExceptionTest() throws DAOException, IllegalAccessException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenThrow(new DAOException());
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesPagoDeCompras("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesPagoDeComprasAsyncOkTest() throws DAOException, IllegalAccessException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Future<Respuesta<ComprobantesDTO>> rta = scompBo.obtenerComprobantesPagoDeComprasAsync("213123", transaccion);
        Future<Respuesta<ComprobantesDTO>> rta2 = scompBo.obtenerComprobantesPagoDeComprasAsync("213123123213",
                transaccion);
        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        Respuesta<ComprobantesDTO> respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        try {
            Respuesta<ComprobantesDTO> comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Async
    private Future<ComprobanteResponse> mockComprobanteResponse(ComprobanteResponse response) {
        return new AsyncResult<ComprobanteResponse>(getComprobanteResponseMock(response));
    }

    private ComprobanteResponse getComprobanteResponseMock(ComprobanteResponse response) {
        return response;
    }

    @Test
    public void obtenerComprobantesTarjetaRecargableOkTest() throws DAOException, IllegalAccessException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);

        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesTarjetaRecargable("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesTarjetaRecargableErrorTest() throws DAOException, IllegalAccessException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setCodRet(1);
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesTarjetaRecargable("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesTarjetaRecargableExceptionTest() throws DAOException, IllegalAccessException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenThrow(new DAOException());
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesTarjetaRecargable("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesTarjetaRecargableAsyncOkTest() throws DAOException, IllegalAccessException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Future<Respuesta<ComprobantesDTO>> rta = scompBo.obtenerComprobantesTarjetaRecargableAsync("213123",
                transaccion);
        Future<Respuesta<ComprobantesDTO>> rta2 = scompBo.obtenerComprobantesTarjetaRecargableAsync("213123123213",
                transaccion);
        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        Respuesta<ComprobantesDTO> respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        try {
            Respuesta<ComprobantesDTO> comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesTBancoOBTest() throws DAOException, IllegalAccessException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobantes.getRespuestaScomp().getComprobantes().add(comprobanteScomp);
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        ComprobanteTransfInmRioOB comprobanteTransOB = new ComprobanteTransfInmRioOB();
        comprobanteScomp.getComprobanteList().add(comprobanteTransOB);

        comprobanteTransOB.setFechaGen("2017-07-07T00:00:00Z");
        comprobanteTransOB.setFechaOper("2017-07-07T00:00:00Z");
        comprobanteTransOB.setHoraOper("14:42:00");
        comprobanteTransOB.setTransferencia(new TransferenciaOB());
        comprobanteTransOB.getTransferencia().setImporte("99999.00");
        comprobanteTransOB.getTransferencia().setTipoCuentaOrigen("09");
        comprobanteTransOB.getTransferencia().setSucursalCuentaOrigen("123");
        comprobanteTransOB.getTransferencia().setNumeroCuentaOrigen("0234567");
        comprobanteTransOB.getTransferencia().setBanco("HSBC");
        comprobanteTransOB.getTransferencia().setCbu("12894718294");
        comprobanteTransOB.getTransferencia().setCuitCuil("20378668817");
        comprobanteTransOB.getTransferencia().setConcepto("Varios");
        comprobanteTransOB.getTransferencia().setDescripcionConcepto("Varios");
        comprobanteTransOB.getTransferencia().setPlazoAcreditacion("Inmediata");
        comprobanteTransOB.getTransferencia().setEmailDestinatario("dan@yopmail.com");
        comprobanteTransOB.getTransferencia().setMensaje("opcional!");
        comprobanteTransOB.getTransferencia().setNroComprobante("0003864");
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesOBTBanco("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesTBancoOBErrorTest() throws DAOException, IllegalAccessException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        comprobantes.setCodRet(1);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesOBTBanco("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesTBancoOBExceptionTest() throws DAOException, IllegalAccessException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenThrow(new DAOException());
        Respuesta<ComprobantesDTO> res = scompBo.obtenerComprobantesOBTBanco("213123", transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesTBancoOBAsyncOkTest() throws DAOException, IllegalAccessException {
        ComprobanteResponse comprobantes = new ComprobanteResponse();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobantes.setRespuestaScomp(new RespuestaScomp());
        comprobantes.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobantes);
        Future<Respuesta<ComprobantesDTO>> rta = scompBo.obtenerComprobantesOBTBancoAsync("213123", transaccion);
        Future<Respuesta<ComprobantesDTO>> rta2 = scompBo.obtenerComprobantesOBTBancoAsync("213123123213", transaccion);
        while (!rta.isDone() && !rta2.isDone()) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {

            }
        }

        Respuesta<ComprobantesDTO> respuesta = null;
        try {
            respuesta = rta.get();
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }
        try {
            Respuesta<ComprobantesDTO> comp2 = rta.get();
            Assert.assertNotNull(comp2);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof DAOException) {

                throw (DAOException) e.getCause();
            }
            throw new DAOException();
        } catch (InterruptedException e) {

        }

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void crearComprobanteConEstadoErrorOk() {
        ComprobanteDTO comprobante = new ComprobanteDTO(true);
        Assert.assertEquals(true, comprobante.getTieneError());
    }

    @Test
    public void crearComprobanteScompRio() throws DAOException, InterruptedException, ExecutionException {
        ComprobanteTrfcta comprobante = new ComprobanteTrfcta();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobante.setFechaOper("20121114");
        Origen origen = new Origen();
        origen.setTipoCuentaOrigen("01");
        origen.setNumeroCuentaOrigen("123456/7");
        origen.setSucursalCuentaOrigen("013");
        comprobante.setOrigen(origen);
        comprobante.setImporteDebito("12,00");
        comprobante.setTitularCtaCredito("titular rio");
        comprobante.setDestino(new DestinoTrfcta());
        comprobante.setNroComprobante("000001234");

        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());

        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);

        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);

        Respuesta<ComprobantesDTO> comprobanteRio = scompBo.obtenerComprobantesRio("2341234", transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA,
                comprobanteRio.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("Titular Rio", comprobanteRio.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRio.getRespuesta().getComprobantes().get(0).getImportePesos());
    }

    @Test
    public void crearComprobanteScompOtrosBancos() throws DAOException, InterruptedException, ExecutionException {
        ComprobanteTrfcci comprobante = new ComprobanteTrfcci();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobante.setFechaOper("20121114");
        comprobante.setHoraOper("00:00:00");
        Origen origen = new Origen();
        origen.setTipoCuentaOrigen("01");
        origen.setNumeroCuentaOrigen("123456/7");
        origen.setSucursalCuentaOrigen("013");
        comprobante.setOrigen(origen);
        comprobante.setImporteDebito("12,00");
        DestinoTrfcci destino = new DestinoTrfcci();
        destino.setTitularCtaDestino("titular otros bancos");
        comprobante.setDestino(destino);
        comprobante.setNroComprobante("000001234");

        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());

        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);

        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);

        Respuesta<ComprobantesDTO> comprobanteOB = scompBo.obtenerComprobantesOtrosBancos("2341234", transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA,
                comprobanteOB.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("Titular Otros Bancos",
                comprobanteOB.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteOB.getRespuesta().getComprobantes().get(0).getImportePesos());
    }

    @Test
    public void crearComprobanteScompRioSietePorVenticuatro()
            throws DAOException, InterruptedException, ExecutionException {
        ComprobanteTrfcta7x24 comprobante = new ComprobanteTrfcta7x24();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobante.setFechaOper("20121114");
        Origen origen = new Origen();
        origen.setTipoCuentaOrigen("03");
        origen.setSucursalCuentaOrigen("013");
        origen.setNumeroCuentaOrigen("123456/7");
        comprobante.setOrigen(origen);
        comprobante.setImporteDebito("12,00");
        DestinoTrfcta destino = new DestinoTrfcta();
        destino.setTitularCtaDestino("destinatario rio7x24");
        destino.setTipoCuentaDestino("03");
        comprobante.setTitularCtaCredito("destinatario rio7x24");
        comprobante.setDestino(destino);
        comprobante.setNroComprobante("000001234");

        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());

        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);

        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = scompBo.obtenerComprobantesOtrosComprobantes("2341234",
                transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("Destinatario Rio7x24",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImporteDolares());
    }

    @Test
    public void crearComprobanteScompTarjetaRecargable()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        ComprobanteTarjRecargOk comprobante = new ComprobanteTarjRecargOk();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobante.setFechaOper("20121114");
        comprobante.setImporteDebitado("12,00");
        comprobante.setNroTarjeta("1234-5678-1234-9754");
        comprobante.setNroCtaDeb("123456/7");
        comprobante.setSucCtaDeb("145");
        comprobante.setTipoCtaDeb("04");

        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());

        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = scompBo.obtenerComprobantesTarjetaRecargable("2341234",
                transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.RECARGA_VISA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("Visa Recargable XXXX-9754",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        Assert.assertEquals(TipoCuenta.CAJA_AHORRO_PESOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
    }

    @Test
    public void crearComprobanteScompPagoCompraPesos()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        ComprobantePagoCompraOk comprobante = new ComprobantePagoCompraOk();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobante.setFechaOper("20121114");
        comprobante.setNroCtaDeb("123456/7");
        comprobante.setSucCtaDeb("145");
        comprobante.setMonedaCtaDeb("$");
        comprobante.setImporteTotalDebito("12.00");
        comprobante.setDescCuenta("Cuenta Unica");
        comprobante.setNombreEmpresa("garbarini");

        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());

        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);

        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = scompBo.obtenerComprobantesPagoDeCompras("2341234",
                transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.PAGO_PUNTUAL,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("garbarini",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(ISBANStringUtils.convertirABigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        Assert.assertEquals(TipoCuenta.CUENTA_UNICA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
    }

    @Test
    public void crearComprobanteScompPagoCompraDolaresCajaAhorro()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        ComprobantePagoCompraOk comprobante = new ComprobantePagoCompraOk();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobante.setFechaOper("20121114");
        comprobante.setNroCtaDeb("123456/7");
        comprobante.setSucCtaDeb("145");
        comprobante.setMonedaCtaDeb("U$s");
        comprobante.setImporteTotalDebito("12.00");
        comprobante.setDescCuenta("Caja de Ahorro");
        comprobante.setNombreEmpresa("garbarini");

        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());

        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = scompBo.obtenerComprobantesPagoDeCompras("2341234",
                transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.PAGO_PUNTUAL,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("garbarini",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(ISBANStringUtils.convertirABigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImporteDolares());
        Assert.assertEquals(TipoCuenta.CAJA_AHORRO_DOLARES,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoDolares());
    }

    @Test
    public void crearComprobanteScompPagoCompraPesosCajaAhorro()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        ComprobantePagoCompraOk comprobante = new ComprobantePagoCompraOk();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobante.setFechaOper("20121114");
        comprobante.setNroCtaDeb("123456/7");
        comprobante.setSucCtaDeb("145");
        comprobante.setMonedaCtaDeb("$");
        comprobante.setImporteTotalDebito("12.00");
        comprobante.setDescCuenta("Caja de Ahorro");
        comprobante.setNombreEmpresa("garbarini");

        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());

        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = scompBo.obtenerComprobantesPagoDeCompras("2341234",
                transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.PAGO_PUNTUAL,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("garbarini",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(ISBANStringUtils.convertirABigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        Assert.assertEquals(TipoCuenta.CAJA_AHORRO_PESOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
    }

    @Test
    public void crearComprobanteScompRioTBanco()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        ComprobanteTransfInmRioRio comprobante = new ComprobanteTransfInmRioRio();
        TransaccionDTO transaccion = new TransaccionDTO();
        TransferenciaRio transferencia = new TransferenciaRio();

        comprobante.setFechaOper("2017/08/16T00:00:00Z");
        comprobante.setHoraOper("09:46:00");
        comprobante.setTransferencia(transferencia);
        transferencia.setTipoCuentaDestino("09");
        transferencia.setImporte("200,00");
        transferencia.setAlias("garbarini");
        transferencia.setNroComprobante("000001234");

        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());

        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = scompBo.obtenerComprobantesRioTBanco("2341234",
                transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("Garbarini",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("200.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        Assert.assertEquals(TipoCuenta.CUENTA_UNICA_PESOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
    }

    @Test
    public void crearComprobanteScompRioTBancoConMailTest()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        ComprobanteTransfInmRioRio comprobante = new ComprobanteTransfInmRioRio();
        TransferenciaRio transferencia = new TransferenciaRio();
        TransaccionDTO transaccion = new TransaccionDTO();

        comprobante.setFechaOper("2017/08/16T00:00:00Z");
        comprobante.setHoraOper("09:46:00");
        comprobante.setTransferencia(transferencia);
        transferencia.setTipoCuentaDestino("09");
        transferencia.setImporte("200,00");
        transferencia.setAlias("");
        transferencia.setNombreDestinatario("Garbarini");
        transferencia.setEmailDestinatario("mail@yopmail.com");
        transferencia.setNroComprobante("000001234");

        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());

        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = scompBo.obtenerComprobantesRioTBanco("2341234",
                transaccion);
        DetalleComprobanteTransferenciaInmediataDTO detalle = (DetalleComprobanteTransferenciaInmediataDTO) comprobanteRioSietePorVenti
                .getRespuesta().getComprobantes().get(0).getDetalle();
        Assert.assertEquals(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("Garbarini",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("200.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        Assert.assertEquals(TipoCuenta.CUENTA_UNICA_PESOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
        Assert.assertEquals("Si", detalle.getAvisoTransferencia());
    }

    @Test
    public void crearComprobanteScompPMCSinDeudaTBanco() throws DAOException, InterruptedException, ExecutionException {
        ComprobantePMCSinDeuda comprobante = new ComprobantePMCSinDeuda();
        TransferenciaPMCSinDeuda transferencia = new TransferenciaPMCSinDeuda();
        TransaccionDTO transaccion = new TransaccionDTO();
        MedioPago medioPago = new MedioPago();

        comprobante.setFechaOper("20121114120402");
        comprobante.setTransferencia(transferencia);
        transferencia.setImporte("2,00");
        transferencia.setTipoCuentaDebito("09");
        transferencia.setSucursalCuentaDebito("09");
        transferencia.setEmpresa("Garbarini");
        transferencia.setFechaHoraPago("20170608104625");

        medioPago.setPesIdentificacion("2123123123");

        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());

        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);

        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);
        Mockito.when(mediosPagoBO.obtenerPorNombreFantasia(Matchers.anyString())).thenReturn(medioPago);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = scompBo.obtenerComprobantesPMCSinDeuda("2341234",
                transaccion);
        Assert.assertEquals("Garbarini",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("2.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImporteDolares());
        Assert.assertEquals(TipoCuenta.CUENTA_UNICA_PESOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
    }

    @Test
    public void crearComprobanteScompPMCConDeudaTBanco()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        ComprobantePMCConDeuda comprobante = new ComprobantePMCConDeuda();
        TransferenciaPMCConDeuda transferencia = new TransferenciaPMCConDeuda();
        TransaccionDTO transaccion = new TransaccionDTO();
        MedioPago medioPago = new MedioPago();

        comprobante.setFechaOper("2017-04-03T00:00:00Z");
        comprobante.setTransferencia(transferencia);
        transferencia.setImporte("2,00");
        transferencia.setTipoCuentaDebito("09");
        transferencia.setSucursalCuentaDebito("09");
        transferencia.setEmpresa("Garbarini");

        medioPago.setPesIdentificacion("2123123123");

        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);
        Set<MedioPago> mediosPago = new HashSet<MedioPago>();
        mediosPago.add(medioPago);
        Mockito.when(buscadorMediosPagoDAO.searchEmpresaByNombreFantasia(Matchers.anyString())).thenReturn(mediosPago);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);
        Mockito.when(mediosPagoBO.obtenerPorNombreFantasia(Matchers.anyString())).thenReturn(medioPago);
        
        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = scompBo.obtenerComprobantesPMCConDeuda("2341234",
                transaccion);
        Assert.assertEquals("Garbarini",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(new BigDecimal("2.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImporteDolares());
        Assert.assertEquals(TipoCuenta.CUENTA_UNICA_PESOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
    }

    @Test
    public void crearComprobanteScompPMCVEPTBanco()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        ComprobantePMCVEP comprobante = new ComprobantePMCVEP();
        TransferenciaPMCVEP transferencia = new TransferenciaPMCVEP();
        MedioPago medioPago = new MedioPago();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobante.setCanal("04");
        comprobante.setSubCanal("99");
        comprobante.setTpoComprobante("2");
        comprobante.setSubTpoComprobante("13");
        comprobante.setEstadoOper(OperacionEstado.A);
        comprobante.setDescEstado("Aceptada");
        comprobante.setFechaOper("2017-04-03T00:00:00Z");
        comprobante.setHoraOper("12:32:00");
        comprobante.setFechaGen("2017-07-28T11:36:10Z");
        comprobante.setTransferencia(transferencia);
        transferencia.setEmpresa("AFIP - PAGO DE IMPUESTOS AFIP (VEP)");
        transferencia.setMoneda("$");
        transferencia.setImporte("540,01");
        transferencia.setFechaHoraPago("20170728123100");
        transferencia.setFechaVencimiento("29072017");
        transferencia.setIdentificacion("21345231237234234");
        transferencia.setIdentificacion2("2031892091213123");
        transferencia.setNumeroVep("000126721490");
        transferencia.setPeriodo("0001");
        transferencia.setAnticipoCuota("091");
        transferencia.setTipoCuentaDebito("09");
        transferencia.setSucursalCuentaDebito("019");
        transferencia.setNumeroCuentaDebito("0693171");
        transferencia.setNumControl("7382");
        transferencia.setNroComprobante("22910219");
        medioPago.setPesIdentificacion("2123123123");

        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());

        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);
        Mockito.when(mediosPagoBO.obtenerPorNombreFantasia(Matchers.anyString())).thenReturn(medioPago);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = scompBo.obtenerComprobantesPMCVEP("2341234",
                transaccion);
        Assert.assertEquals("AFIP - PAGO DE IMPUESTOS AFIP (VEP)",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(ISBANStringUtils.convertirABigDecimal("540,01"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        Assert.assertEquals(TipoCuenta.CUENTA_UNICA_PESOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
    }

    @Test
    public void crearComprobanteScompPagoCompraDolaresCuentaCorriente()
            throws InterruptedException, ExecutionException, DAOException, IllegalAccessException {
        ComprobantePagoCompraOk comprobante = new ComprobantePagoCompraOk();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobante.setFechaOper("20121114");
        comprobante.setNroCtaDeb("123456/7");
        comprobante.setSucCtaDeb("145");
        comprobante.setMonedaCtaDeb("U$s");
        comprobante.setImporteTotalDebito("12.00");
        comprobante.setDescCuenta("Cuenta Corriente");
        comprobante.setNombreEmpresa("garbarini");
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());

        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);

        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = scompBo.obtenerComprobantesPagoDeCompras("2341234",
                transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.PAGO_PUNTUAL,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("garbarini",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(ISBANStringUtils.convertirABigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImporteDolares());
        Assert.assertEquals(TipoCuenta.CUENTA_CORRIENTE_DOLARES,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoDolares());
    }

    @Test
    public void crearComprobanteScompPagoCompraPesosCuentaCorriente()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        ComprobantePagoCompraOk comprobante = new ComprobantePagoCompraOk();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobante.setFechaOper("20121114");
        comprobante.setNroCtaDeb("123456/7");
        comprobante.setSucCtaDeb("145");
        comprobante.setMonedaCtaDeb("$");
        comprobante.setImporteTotalDebito("12.00");
        comprobante.setDescCuenta("Cuenta Corriente");
        comprobante.setNombreEmpresa("garbarini");

        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());

        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);

        Respuesta<ComprobantesDTO> comprobanteRioSietePorVenti = scompBo.obtenerComprobantesPagoDeCompras("2341234",
                transaccion);
        Assert.assertEquals(TipoOperacionComprobanteEnum.PAGO_PUNTUAL,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoOperacion());
        Assert.assertEquals("garbarini",
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getDestinatario());
        Assert.assertEquals(ISBANStringUtils.convertirABigDecimal("12.00"),
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getImportePesos());
        Assert.assertEquals(TipoCuenta.CUENTA_CORRIENTE_PESOS,
                comprobanteRioSietePorVenti.getRespuesta().getComprobantes().get(0).getTipoCtaMedioDePagoPesos());
    }

    @Test
    public void altaScompTransferenciaRioRioOK() throws DAOException {
        TransferenciaView transferenciaView = obtenerTransferenciaView();
        Cliente cliente = obtenerCliente();
        ComprobanteResponse response = new ComprobanteResponse();
        response.setCodRet(0);
        Mockito.when(scompDAO.altaComprobante(Matchers.any(AltaComprobanteRequest.class))).thenReturn(response);
        TransInmediataRioBuilder altaBuilder = new TransInmediataRioBuilder(cliente);
        Respuesta<Void> respuesta = scompBo.altaScompTransferencia(altaBuilder.setTransferenciaView(transferenciaView));
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void altaScompTransferenciaNoRioRio() throws DAOException {
        TransferenciaView transferenciaView = obtenerTransferenciaView();
        transferenciaView.setNroCuentaDestino("");
        transferenciaView.setCuitCuil("20378668817");
        transferenciaView.setImporte("1234,00");
        Cliente cliente = obtenerCliente();
        ComprobanteResponse response = new ComprobanteResponse();
        response.setCodRet(0);

        Mockito.when(scompDAO.altaComprobante(Matchers.any(AltaComprobanteRequest.class))).thenReturn(response);
        TransInmediataOBBuilder altaBuilder = new TransInmediataOBBuilder(cliente);
        Respuesta<Void> respuesta = scompBo.altaScompTransferencia(altaBuilder.setTransferenciaView(transferenciaView));
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void crearComprobanteScompCompraVentaDolares()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        ComprobanteCompraVentaDolar comprobante = new ComprobanteCompraVentaDolar();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobante.setFechaOper("2017-04-03T00:00:00Z");
        comprobante.setHoraOper("00:00:00");
        comprobante.setImporteDebitado(" 100.00");
        comprobante.setImporteAcreditado(" 100.00");
        comprobante.setCotizacionAplicada("1231.22");
        CuentaDestino cuentaDestino = new CuentaDestino();
        CuentaOrigen cuentaOrigen = new CuentaOrigen();
        cuentaOrigen.setTipoCuentaOrigen("09");
        cuentaOrigen.setNumeroCuentaOrigen("32423/6");
        cuentaDestino.setTipoCuentaDestino("10");
        cuentaDestino.setNumeroCuentaDestino("239749/7");
        comprobante.setCuentaDestino(cuentaDestino);
        comprobante.setCuentaOrigen(cuentaOrigen);
        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);

        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);

        Respuesta<ComprobantesDTO> comprobanteCompraVenta = scompBo.obtenerComprobantesCompraVentaDolar("2341234",
                transaccion);

        Assert.assertNotNull(comprobanteCompraVenta.getRespuesta());
    }

    @Test
    public void crearComprobanteScompVentaDolares()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        ComprobanteCompraVentaDolar comprobante = new ComprobanteCompraVentaDolar();
        TransaccionDTO transaccion = new TransaccionDTO();
        comprobante.setFechaOper("2017-04-03T00:00:00Z");
        comprobante.setHoraOper("00:00:00");
        comprobante.setImporteDebitado(" 100.00");
        comprobante.setImporteAcreditado(" 100.00");
        comprobante.setCotizacionAplicada("1231.22");
        CuentaDestino cuentaDestino = new CuentaDestino();
        CuentaOrigen cuentaOrigen = new CuentaOrigen();
        cuentaOrigen.setTipoCuentaOrigen("10");
        cuentaOrigen.setNumeroCuentaOrigen("32423/6");
        cuentaDestino.setTipoCuentaDestino("09");
        cuentaDestino.setNumeroCuentaDestino("239749/7");
        comprobante.setCuentaDestino(cuentaDestino);
        comprobante.setCuentaOrigen(cuentaOrigen);
        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();
        comprobanteResponse.setRespuestaScomp(new RespuestaScomp());
        comprobanteResponse.getRespuestaScomp().setComprobantes(new ArrayList<ComprobanteScomp>());
        FieldUtils.writeDeclaredField(scompBo, "fechaMinima", "01/01/2018", true);
        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        comprobanteScomp.setComprobanteList(new ArrayList<Comprobante>());
        comprobanteScomp.getComprobanteList().add(comprobante);
        comprobanteResponse.getRespuestaScomp().getComprobantes().add(comprobanteScomp);

        Mockito.when(
                scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString()))
                .thenReturn(comprobanteResponse);

        Respuesta<ComprobantesDTO> comprobanteCompraVenta = scompBo.obtenerComprobantesCompraVentaDolar("2341234",
                transaccion);

        Assert.assertNotNull(comprobanteCompraVenta.getRespuesta());
    }

    @Test
    public void cambiarPlazoAcreditacionEnElDiaParaCuentasTerceros() throws DAOException {
        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();

        RespuestaScomp respuestaScomp = new RespuestaScomp();

        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        List<ComprobanteScomp> comprobantes = new ArrayList<ComprobanteScomp>();

        ComprobanteTransfInmRioOB comprobanteTransfInmRioOB = new ComprobanteTransfInmRioOB();
        comprobanteTransfInmRioOB.setTransferencia(new TransferenciaOB());
        comprobanteTransfInmRioOB.setFechaOper("2022/7/26");
        comprobanteTransfInmRioOB.setHoraOper("22:22:22");

        comprobanteScomp.setComprobante(comprobanteTransfInmRioOB);
        comprobantes.add(comprobanteScomp);

        respuestaScomp.setComprobante(comprobantes);
        comprobanteResponse.setRespuestaScomp(respuestaScomp);

        Mockito.when(scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString())).thenReturn(comprobanteResponse);

        Respuesta<ComprobantesDTO> respuesta = scompBo.obtenerComprobantesOBTBanco("", new TransaccionDTO());
        DetalleComprobanteTransferenciaInmediataDTO detalle = (DetalleComprobanteTransferenciaInmediataDTO) respuesta.getRespuesta()
                .getComprobantes().get(0).getDetalle();

        Assert.assertEquals("En el día, según la cuenta de destino", detalle.getPlazoAcreditacion());
    }

    @Test
    public void mantenerPlazoAcreditacionInmediataParaCuentasSantander() throws DAOException {

        ComprobanteResponse comprobanteResponse = new ComprobanteResponse();

        RespuestaScomp respuestaScomp = new RespuestaScomp();

        ComprobanteScomp comprobanteScomp = new ComprobanteScomp();
        List<ComprobanteScomp> comprobantes = new ArrayList<ComprobanteScomp>();

        ComprobanteTrfcta comprobanteTrfcta = new ComprobanteTrfcta();
        comprobanteTrfcta.setFechaOper("2022");
        comprobanteTrfcta.setHoraOper("22:22:22");

        Origen origen = new Origen();
        origen.setTipoCuentaOrigen("0");
        comprobanteTrfcta.setOrigen(origen);
        comprobanteTrfcta.setImporteDebito("10");

        DestinoTrfcta destinoTrfcta = new DestinoTrfcta();
        destinoTrfcta.setNumeroCuentaDestino("0");
        destinoTrfcta.setSucursalCuentaDestino("0");

        comprobanteTrfcta.setDestino(destinoTrfcta);

        comprobanteScomp.setComprobante(comprobanteTrfcta);
        comprobantes.add(comprobanteScomp);

        respuestaScomp.setComprobante(comprobantes);
        comprobanteResponse.setRespuestaScomp(respuestaScomp);

        Mockito.when(scompDAO.listarComprobantes(Matchers.any(Filtros.class), Matchers.anyString(), Matchers.anyString())).thenReturn(comprobanteResponse);

        Respuesta<ComprobantesDTO> respuesta = scompBo.obtenerComprobantesRio("", new TransaccionDTO());
        DetalleComprobanteTransferenciaInmediataRioRioDTO detalle = (DetalleComprobanteTransferenciaInmediataRioRioDTO) respuesta.getRespuesta()
                .getComprobantes().get(0).getDetalle();

        Assert.assertEquals("Inmediata", detalle.getPlazoAcreditacion());
    }

    private TransferenciaView obtenerTransferenciaView() {
        TransferenciaView transferenciaView = new TransferenciaView();
        transferenciaView.setFechaOperacion("27/07/2017 · 14:31");
        transferenciaView.setAliasDestino("Juancito");
        transferenciaView.setTitular("Comignaghi Valeriano Paul Tadeo");
        transferenciaView.setImporte("$ 0,01");
        transferenciaView.setTipoCuentaDescripcion("Cuenta única");
        transferenciaView.setMoneda("peso");
        transferenciaView.setNroCuenta("201-363238/1");
        transferenciaView.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        transferenciaView.setTipoCuentaDestino("Cuenta única");
        transferenciaView.setNroCuentaDestino("000-063917/0");
		transferenciaView.setConcepto(new ConceptoView("Alquiler","8", "VAR", "Alquiler", 0));
        transferenciaView.setDescripcion("Varios");
        transferenciaView.setFechaAcreditacion("Inmediata");
        transferenciaView.setEnviaEmail("No");
        transferenciaView.setMensajeEmail("");
        transferenciaView.setNumeroComprobante("14322435");
        return transferenciaView;
    }

    private Cliente obtenerCliente() {
        Cliente cliente = new Cliente();
        cliente.setNup("1234");
        cliente.setTipoDocumento("1");
        cliente.setDni("0000034876345");
        return cliente;
    }

}
