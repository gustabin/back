/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.CoordinadorComprobantesBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.DetallePMCBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CuitDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CuitEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteScompAfipDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteScompConDeudaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteScompSinDeudaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteScompVepDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaInmediataDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaInmediataOtrosBancosDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteTransferenciaProgramadaRioRioDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoDetalleComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.web.manager.impl.ComprobantesManagerImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCAfipView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCVEPView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;

/**
 * The Class DetalleBuilderFactoryMethodTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetalleBuilderViewTest {

    /** The comprobantes BO. */
    @InjectMocks
    private ComprobantesManager comprobantesManager = new ComprobantesManagerImpl();

    /** The comprobantes BO. */
    @Mock
    private CoordinadorComprobantesBO coordinadorBO;

    /** The detalle PMC BO. */
    @Mock
    private DetallePMCBO detallePMCBO;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

    /** The registro sesion. */
    @Mock
    private RegistroSesion registroSesion;

    /** The reporte BO. */
    @Mock
    private ReporteComprobantePDFBO reporteBO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**
     * Crear detalle DTO builder PM C con deuda test.
     */
    @Test
    public void crearDetalleDTOBuilder_SCOMP_PMC_Con_Deuda_Test() {

        ComprobanteDTO comprobanteDTO = obtenerComprobante(TipoOperacionComprobanteEnum.PAGO_SERVICIOS);
        comprobanteDTO.setDetalle(obtenerDetalleSCOMP_PMC_Con_DeudaDTO());
        DetalleComprobanteView view = comprobanteDTO.getDetalle().getView(comprobanteDTO);
        Assert.assertNotNull(view);
    }

    /**
     * Obtener detalle SCOM P PM C con deuda DTO.
     *
     * @return the detalle comprobante DTO
     */
    private DetalleComprobanteDTO obtenerDetalleSCOMP_PMC_Con_DeudaDTO() {
        DetalleComprobanteScompConDeudaDTO detalle = new DetalleComprobanteScompConDeudaDTO();
        completarDetalleComprobanteDTO(detalle);
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_PMC_CON_DEUDA);
        detalle.setIdentificacion("Identificacion");
        detalle.setEmpresa("Empresa");
        detalle.setFechaDePago(new GregorianCalendar(2017, 4, 1).getTime());
        detalle.setNroCuentaOrigen("NroCuentaOrigen");
        detalle.setCodigoValidacion("CodigoValidacion");
        detalle.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalle.setMoneda("$");
        detalle.setImporte(new BigDecimal(10));
        detalle.setNroControl("NroControl");
        List<String> leyenda = new ArrayList<String>();
        leyenda.addAll(Arrays.asList("A", "A", "A"));
        detalle.setLeyendaFactura(leyenda);
        detalle.setLeyendaEmpresa("LeyendaEmpresa");
        CuitDTO cuit = new CuitDTO();
        cuit.setNumero("Numero");
        cuit.setTipo(CuitEnum.CUIT_EMPLEADO);
        detalle.setCuit(cuit);
        detalle.setNroComprobante("");
        return detalle;
    }

    /**
     * Crear detalle DTO builder PM C con deuda test.
     */
    @Test
    public void crearDetalleDTOBuilder_SCOMP_PMC_Sin_Deuda_Test() {
        ComprobanteDTO comprobanteDTO = obtenerComprobante(TipoOperacionComprobanteEnum.PAGO_SERVICIOS);
        comprobanteDTO.setDetalle(obtenerDetalleSCOMP_PMC_Sin_DeudaDTO());
        DetalleComprobanteView view = comprobanteDTO.getDetalle().getView(comprobanteDTO);
        Assert.assertNotNull(view);
    }

    /**
     * Obtener detalle SCOM P PM C sin deuda DTO.
     *
     * @return the detalle comprobante DTO
     */
    private DetalleComprobanteDTO obtenerDetalleSCOMP_PMC_Sin_DeudaDTO() {
        DetalleComprobanteScompSinDeudaDTO detalle = new DetalleComprobanteScompSinDeudaDTO();
        completarDetalleComprobanteDTO(detalle);
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_PMC_SIN_DEUDA);
        detalle.setIdentificacion("Identificacion");
        detalle.setEmpresa("Empresa");
        detalle.setFechaDePago(new GregorianCalendar(2017, 4, 1).getTime());
        detalle.setNroCuentaOrigen("NroCuentaOrigen");
        detalle.setCodigoValidacion("CodigoValidacion");
        detalle.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalle.setMoneda("$");
        detalle.setImporte(new BigDecimal(10));
        detalle.setNroControl("NroControl");
        List<String> leyenda = new ArrayList<String>();
        leyenda.addAll(Arrays.asList("A", "A", "A"));
        detalle.setLeyendaFactura(leyenda);
        detalle.setLeyendaEmpresa("LeyendaEmpresa");
        CuitDTO cuit = new CuitDTO();
        cuit.setNumero("Numero");
        cuit.setTipo(CuitEnum.CUIT_EMPLEADO);
        detalle.setCuit(cuit);
        detalle.setNroComprobante("");
        return detalle;
    }

    /**
     * Crear detalle DTO builder PM C con deuda test.
     */
    @Test
    public void crearDetalleDTOBuilder_SCOMP_PMC_Afip_Test() {

        ComprobanteDTO comprobanteDTO = obtenerComprobante(TipoOperacionComprobanteEnum.PAGO_SERVICIOS);
        comprobanteDTO.setDetalle(obtenerDetalleSCOMP_PMC_AfipDTO());
        DetalleComprobantePMCAfipView view = (DetalleComprobantePMCAfipView) comprobanteDTO.getDetalle()
                .getView(comprobanteDTO);
        Assert.assertNotNull(view);
    }

    /**
     * Obtener detalle SCOM P PM C afip DTO.
     *
     * @return the detalle comprobante DTO
     */
    private DetalleComprobanteDTO obtenerDetalleSCOMP_PMC_AfipDTO() {
        DetalleComprobanteScompAfipDTO detalle = new DetalleComprobanteScompAfipDTO();
        completarDetalleComprobanteDTO(detalle);
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_PMC_AFIP);
        detalle.setIdentificacion("Identificacion");
        detalle.setEmpresa("Empresa");
        detalle.setFechaDePago(new GregorianCalendar(2017, 4, 1).getTime());
        detalle.setNroCuentaOrigen("NroCuentaOrigen");
        detalle.setCodigoValidacion("CodigoValidacion");
        detalle.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalle.setMoneda("$");
        detalle.setImporte(new BigDecimal(10));
        detalle.setNroControl("NroControl");
        List<String> leyenda = new ArrayList<String>();
        leyenda.addAll(Arrays.asList("A", "A", "A"));
        detalle.setLeyendaFactura(leyenda);
        detalle.setLeyendaEmpresa("LeyendaEmpresa");
        CuitDTO cuit = new CuitDTO();
        cuit.setNumero("Numero");
        cuit.setTipo(CuitEnum.CUIT_EMPLEADO);
        detalle.setCuit(cuit);
        detalle.setCuitVEP(cuit);
        detalle.setNroComprobante("");
        return detalle;
    }

    /**
     * Crear detalle DTO builder PM C con deuda test.
     */
    @Test
    public void crearDetalleDTOBuilder_SCOMP_PMC_Vep_Test() {
        ComprobanteDTO comprobanteDTO = obtenerComprobante(TipoOperacionComprobanteEnum.PAGO_SERVICIOS);
        comprobanteDTO.setDetalle(obtenerDetalleSCOMP_PMC_VepDTO());
        DetalleComprobantePMCVEPView view = (DetalleComprobantePMCVEPView) comprobanteDTO.getDetalle()
                .getView(comprobanteDTO);
        Assert.assertNotNull(view);
    }

    /**
     * Obtener detalle SCOM P PM C vep DTO.
     *
     * @return the detalle comprobante DTO
     */
    private DetalleComprobanteDTO obtenerDetalleSCOMP_PMC_VepDTO() {
        DetalleComprobanteScompVepDTO detalle = new DetalleComprobanteScompVepDTO();
        completarDetalleComprobanteDTO(detalle);
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_PMC_VEP);
        detalle.setIdentificacion("Identificacion");
        detalle.setEmpresa("Empresa");
        detalle.setFechaDePago(new GregorianCalendar(2017, 4, 1).getTime());
        detalle.setFechaVencimiento(new GregorianCalendar(2017, 4, 1).getTime());
        detalle.setNroCuentaOrigen("NroCuentaOrigen");
        detalle.setCodigoValidacion("CodigoValidacion");
        detalle.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalle.setMoneda("$");
        detalle.setImporte(new BigDecimal(10));
        detalle.setNroControl("NroControl");
        List<String> leyenda = new ArrayList<String>();
        leyenda.addAll(Arrays.asList("A", "A", "A"));
        detalle.setLeyendaFactura(leyenda);
        detalle.setLeyendaEmpresa("LeyendaEmpresa");
        CuitDTO cuit = new CuitDTO();
        cuit.setNumero("Numero");
        cuit.setTipo(CuitEnum.CUIT_EMPLEADO);
        detalle.setCuit(cuit);
        detalle.setCuitVEP(cuit);
        detalle.setNroComprobante("");
        return detalle;
    }

    /**
     * Crear detalle DTO builder scomp inmediatas test.
     */
    @Test
    public void crearDetalleDTOBuilder_Scomp_Inmediatas_Rio_Test() {
        ComprobanteDTO comprobanteDTO = obtenerComprobante(TipoOperacionComprobanteEnum.SCOMP_PAGO_SERVICIOS);
        comprobanteDTO.setDetalle(obtenerDetalleSCOMP_Inmediata_RioDTO());
        DetalleComprobanteView view = comprobanteDTO.getDetalle().getView(comprobanteDTO);
        Assert.assertNotNull(view);
    }

    /**
     * Obtener detalle SCOM P inmediata rio DTO.
     *
     * @return the detalle comprobante DTO
     */
    private DetalleComprobanteDTO obtenerDetalleSCOMP_Inmediata_RioDTO() {
        DetalleComprobanteTransferenciaInmediataDTO detalle = new DetalleComprobanteTransferenciaInmediataDTO();
        completarDetalleComprobanteDTO(detalle);
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO);
        return detalle;
    }

    /**
     * Crear detalle DTO builder scomp inmediatas test.
     */
    @Test
    public void crearDetalleDTOBuilder_Scomp_Inmediatas_Otros_Bancos_Test() {

        ComprobanteDTO comprobanteDTO = obtenerComprobante(TipoOperacionComprobanteEnum.SCOMP_PAGO_SERVICIOS);
        comprobanteDTO.setDetalle(obtenerDetalleSCOMP_Inmediata_OTDTO());
        CuitDTO cuit = new CuitDTO();
        cuit.setDigitoVerificador("");
        cuit.setNumero("");
        cuit.setTipo(CuitEnum.CUIT);
        comprobanteDTO.getDetalle().setCuit(cuit);
        DetalleComprobanteView view = comprobanteDTO.getDetalle().getView(comprobanteDTO);
        Assert.assertNotNull(view);
    }

    /**
     * Obtener detalle SCOM P inmediata OTDTO.
     *
     * @return the detalle comprobante DTO
     */
    private DetalleComprobanteDTO obtenerDetalleSCOMP_Inmediata_OTDTO() {
        DetalleComprobanteTransferenciaInmediataOtrosBancosDTO detalle = new DetalleComprobanteTransferenciaInmediataOtrosBancosDTO();
        completarDetalleComprobanteDTO(detalle);
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.SCOMP_TRANSFERENCIA_INMEDIATA_OTROS_BANCOS);
        return detalle;
    }

    /**
     * Crear detalle DTO builder pago tarjeta test.
     */
    @Test
    public void crearDetalleDTO_transferencia_programada_rio_rio_Test() {
        ComprobanteDTO comprobanteDTO = obtenerComprobante(TipoOperacionComprobanteEnum.PAGO_SERVICIOS);
        comprobanteDTO.setDetalle(obtenerDetalle_transferenciaProgramadaRioRioDTO());
        DetalleComprobanteView view = comprobanteDTO.getDetalle().getView(comprobanteDTO);
        Assert.assertNotNull(view);
    }

    /**
     * Obtener detalle transferencia programada rio rio DTO.
     *
     * @return the detalle comprobante DTO
     */
    private DetalleComprobanteDTO obtenerDetalle_transferenciaProgramadaRioRioDTO() {
        DetalleComprobanteTransferenciaProgramadaRioRioDTO detalle = new DetalleComprobanteTransferenciaProgramadaRioRioDTO();
        completarDetalleComprobanteDTO(detalle);
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_RIO_RIO);
        detalle.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        detalle.setTipoCuentaDestino(TipoCuenta.CUENTA_UNICA);
        return detalle;
    }

    /**
     * Crear detalle DTO builder debitos automaticos en cuenta test.
     */
    @Test
    public void crearDetalleDTO_transferencia_programada_ot_Test() {
        ComprobanteDTO comprobanteDTO = obtenerComprobante(TipoOperacionComprobanteEnum.PAGO_SERVICIOS);
        comprobanteDTO.setDetalle(obtenerDetalle_transferenciaProgramadaOBDTO());
        DetalleComprobanteView view = comprobanteDTO.getDetalle().getView(comprobanteDTO);
        Assert.assertNotNull(view);
    }

    /**
     * Obtener detalle transferencia programada OBDTO.
     *
     * @return Tthe detalle comprobante DTO
     */
    private DetalleComprobanteDTO obtenerDetalle_transferenciaProgramadaOBDTO() {
        DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO detalle = new DetalleComprobanteTransferenciaProgramadaOtrosBancosDTO();
        completarDetalleComprobanteDTO(detalle);
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.TRANSFERENCIA_PROGRAMADA_OTROS_BANCOS);
        return detalle;
    }

    /**
     * Obtener comprobante.
     *
     * @param pagoServicios
     *            the pago servicios
     * @return the comprobante DTO
     */
    private ComprobanteDTO obtenerComprobante(TipoOperacionComprobanteEnum pagoServicios) {
        ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
        comprobanteDTO.setFecha(new GregorianCalendar(2017, 9, 1).getTime());
        comprobanteDTO.setTipoOperacion(pagoServicios);
        comprobanteDTO.setCtaMedioDePagoPesos("000-063917/0");
        comprobanteDTO.setTipoCtaMedioDePagoPesos(TipoCuenta.CUENTA_UNICA);
        comprobanteDTO.setDestinatario("Destinatario");
        comprobanteDTO.setImporteDolares(null);
        comprobanteDTO.setImportePesos(new BigDecimal(1200));
        return comprobanteDTO;
    }

    /**
     * Completar detalle comprobante DTO.
     *
     * @param detalle
     *            the detalle
     */
    private void completarDetalleComprobanteDTO(DetalleComprobanteDTO detalle) {
        detalle.setIdentificacion("Identificacion");
        detalle.setFechaDePago(new GregorianCalendar(2017, 4, 1).getTime());
        detalle.setNroCuentaOrigen("NroCuentaOrigen");
        detalle.setTipoCuentaOrigen(TipoCuenta.CUENTA_UNICA);
        List<String> leyenda = new ArrayList<String>();
        leyenda.addAll(Arrays.asList("A", "A", "A"));
        CuitDTO cuit = new CuitDTO();
        cuit.setNumero("Numero");
        cuit.setTipo(CuitEnum.CUIT_EMPLEADO);
        detalle.setCuit(cuit);
    }

    /**
     * Obtener medio pago view.
     *
     * @return the medio pago view
     */
    public MedioPagoView obtenerMedioPagoView() {
        MedioPagoView medioPagoView = new MedioPagoView();
        medioPagoView.setCodigoPagoElectronico("1");
        medioPagoView.setTipoEmpresa("pepe");
        return medioPagoView;
    }

}
