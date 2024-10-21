package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.ReporteComprobantePDFBOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ReporteComprobantePDFDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.ConfirmacionRecargaView;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobantePagoTarjeta;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConceptoView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

@RunWith(MockitoJUnitRunner.class)
public class ReporteComprobantePDFBOTest {

    @InjectMocks
    private ReporteComprobantePDFBO reporteComprobantePDFBO = new ReporteComprobantePDFBOImpl();

    @Mock
    private ReporteComprobantePDFDAO reporteComprobantePDFDAO;

    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    /** The medios pago BO. */
    @Mock
    private MediosPagoBO mediosPagoBO ;
    
    

    @Test
    public void obtenerComprobantePDFFlujoTransferenciaInmediataTest() throws DAOException {
        TransferenciaView transferenciaView = obtenerTransferenciaView();
        Mockito.when(reporteComprobantePDFDAO.obtenerReporteComprobantePDF(Matchers.any(DetalleComprobanteView.class)))
                .thenReturn(obtenerReporte());
        Respuesta<Reporte> respuesta = reporteComprobantePDFBO.obtenerComprobantePDF(transferenciaView);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void obtenerComprobantePDFFlujoTransferenciaProgramadaTest() throws DAOException {
        TransferenciaView transferenciaView = obtenerTransferenciaView();
        transferenciaView.setInmediata(false);
        transferenciaView.setAliasDestino("ALIAS");
        transferenciaView.setCbu("1329571295381723");
        transferenciaView.setImporte("213,12");
        transferenciaView.setMoneda("peso");
        Mockito.when(reporteComprobantePDFDAO.obtenerReporteComprobantePDF(Matchers.any(DetalleComprobanteView.class)))
                .thenReturn(obtenerReporte());
        Respuesta<Reporte> respuesta = reporteComprobantePDFBO.obtenerComprobantePDF(transferenciaView);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void obtenerComprobantePDFFlujoComprobantesTest() throws DAOException {
        DetalleComprobanteView transferenciaView = obtenerDetalleTransferenciaView();
        Mockito.when(reporteComprobantePDFDAO.obtenerReporteComprobantePDF(Matchers.any(DetalleComprobanteView.class)))
                .thenReturn(obtenerReporte());
        Respuesta<Reporte> respuesta = reporteComprobantePDFBO.obtenerComprobantePDF(transferenciaView);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void obtenerComprobantePDFFlujoComprobantesExceptionTest() throws DAOException {
        DetalleComprobanteView transferenciaView = obtenerDetalleTransferenciaView();
        Mockito.when(reporteComprobantePDFDAO.obtenerReporteComprobantePDF(Matchers.any(DetalleComprobanteView.class)))
                .thenThrow(new DAOException());
        Respuesta<Reporte> respuesta = reporteComprobantePDFBO.obtenerComprobantePDF(transferenciaView);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(TipoError.ERROR_DESCARGA_COMPROBANTE.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    private Reporte obtenerReporte() {
        Reporte reporte = new Reporte();
        reporte.setNombre("Comprobante de transferencia.pdf");
        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        return reporte;
    }

    private TransferenciaView obtenerTransferenciaView() {
        TransferenciaView view = new TransferenciaView();
        view.setInmediata(true);
        view.setImporte("$ 123,00");
        view.setNroCuenta("000-063917/0");
        view.setTipoCuentaDescripcion("Cuenta única");
        view.setNroCuentaDestino("201-363238/1");
        view.setTipoCuentaDestinoDescripcion("Cuenta única");
        view.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        view.setIsRioRio(true);
        view.setTitular("Comignaghi Valeriano Paul Tadeo");
        view.setFechaEjecucion("21/09/2017");
        view.setFechaAcreditacion("Inmediata");
        view.setConcepto(new ConceptoView("Alquiler","8", "VAR", "Alquiler", 0));
        view.setDescripcion("Varios");
        view.setEnviaEmail("No");
        view.setNumeroComprobante("15226211");
        view.setEmail("");
        view.setMensajeEmail("");
        view.setFechaOperacion("21/09/2017 · 15:24");
        view.setIsRioRio(false);
        return view;
    }

    private DetalleComprobanteView obtenerDetalleTransferenciaView() {
        DetalleComprobanteView view = new DetalleComprobanteView();
        view.setCtaMedioDePagoDolares("206778/4");
        view.setCuit("618246194672846");
        view.setDestinatario("Hernando Flores");
        view.setEfectuada(true);
        view.setEmpresa("Movistar");
        view.setEstado("Aceptada");
        view.setFecha("10/2/2017");
        view.setFechaOperacion("10/2/2017");
        view.setId("123141224");
        view.setIdComprobante("11235123A");
        view.setIdentificacion("20378668817");
        view.setIdSesion("1234897");
        return view;
    }

    @Test
    public void obtenerComprobantePDFPagoTarjetaCreditoView() {
        PagoTarjetaCreditoView view = new PagoTarjetaCreditoView();
        ComprobantePagoTarjeta comprobante = new ComprobantePagoTarjeta();
        List<Cuenta> listaCuenta = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        
        view.setTipoTarjeta(" C ");
        view.setCbuDolares("234691237468");
        view.setCbuPesos("234691237464");
        cuenta.setNroCuentaProducto("12346192366");
        cuenta.setNroSucursal("012");
        listaCuenta.add(cuenta);
        listaCuenta.get(0).setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        Respuesta<Reporte> respuesta = reporteComprobantePDFBO.obtenerComprobantePDF(view,
                TipoOperacionComprobanteEnum.COMPRA_DOLARES, listaCuenta, comprobante);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    
    @Test
    public void obtenerComprobantePDFComprobanteCompraVentaView(){
        ComprobanteCompraVentaView compraVenta = new ComprobanteCompraVentaView();
        Respuesta<Reporte> respuesta = reporteComprobantePDFBO.obtenerComprobantePDF(compraVenta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    @Test
    public void obtenerComprobantePDFComprobanteConfirmacionRecargaView(){
        ConfirmacionRecargaView recarga = new ConfirmacionRecargaView();
        recarga.setMontoId("12");
        Mockito.when(mediosPagoBO.obtenerPorCodigo(Matchers.anyString())).thenReturn(obtenerMedioPago());
        Respuesta<Reporte> respuesta = reporteComprobantePDFBO.obtenerComprobantePDF(recarga);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    /**
     * Obtener medio pago.
     *
     * @return the medio pago
     */
    private MedioPago obtenerMedioPago() {
        MedioPago medioPago = new MedioPago();
        medioPago.setPesIdentificacion("REPE");
        medioPago.setNombreFantasia("DIREC TV ");
        return medioPago;
    }

}
