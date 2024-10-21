package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.ComprobantesPagoMisCuentasBOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ComprobantesPagoMisCuentasDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobantePMCEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaComprobanteInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaComprobanteOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

@RunWith(MockitoJUnitRunner.class)
public class ComprobantesPagoMisCuentasBOTest {

    @InjectMocks
    private ComprobantesPagoMisCuentasBO comprobante = new ComprobantesPagoMisCuentasBOImpl();

    @Mock
    private ComprobantesPagoMisCuentasDAO comprobantesPagoMisCuentasDAO;

    @Mock
    private MediosPagoBO medioPagoBO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    @Test
    public void obtenerComprobantesPMCAsyncTest()
            throws DAOException, IllegalAccessException, InterruptedException, ExecutionException {
        Cliente cliente = new Cliente();
        ConsultaComprobanteOutEntity comprobanteRes = new ConsultaComprobanteOutEntity();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        TransaccionDTO transaccion = new TransaccionDTO();
        transaccion.setImporteDesde(new BigDecimal(300));
        transaccion.setImporteHasta(new BigDecimal(300));
        comprobanteRes.setCodigoRetornoExtendido("00000000000");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cliente.setCuentas(cuentas);
        cliente.getCuentas().add(cuenta);
        
        Cuenta cuentaBanelco = new Cuenta();
        cuentaBanelco.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuentaBanelco.setCodigoAplicacion("ABAE");
        cliente.getCuentas().add(cuentaBanelco);
        
        FieldUtils.writeDeclaredField(comprobante, "diasFechaDesde", "150", true);
        Mockito.when(comprobantesPagoMisCuentasDAO.consultar(Matchers.any(ConsultaComprobanteInEntity.class)))
                .thenReturn(comprobanteRes);

        Future<Respuesta<ComprobantesDTO>> res = comprobante.obtenerComprobantesPMCAsync(cliente, transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.get().getEstadoRespuesta());

    }

    @Test
    public void obtenerComprobantesPagoMisCuentasException()
            throws IllegalAccessException, DAOException, InterruptedException, ExecutionException {
        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();

        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cliente.setCuentas(cuentas);
        cliente.getCuentas().add(cuenta);
        FieldUtils.writeDeclaredField(comprobante, "diasFechaDesde", "150", true);
        Mockito.when(comprobantesPagoMisCuentasDAO.consultar(Matchers.any(ConsultaComprobanteInEntity.class)))
                .thenThrow(new DAOException());

        Future<Respuesta<ComprobantesDTO>> res = comprobante.obtenerComprobantesPMCAsync(cliente, new TransaccionDTO());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.get().getEstadoRespuesta());
    }

    @Test
    public void obtenerComprobantesPMCAsyncConComprobantesTest()
            throws DAOException, IllegalAccessException, InterruptedException, ExecutionException, BusinessException {
        Cliente cliente = new Cliente();
        ConsultaComprobanteOutEntity comprobanteRes = new ConsultaComprobanteOutEntity();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        List<ComprobantePMCEntity> destinatarios = new ArrayList<ComprobantePMCEntity>();
        ComprobantePMCEntity comprobantePMC = new ComprobantePMCEntity();
        Respuesta<MedioPago> respuestaMP = new Respuesta<MedioPago>();
        MedioPago medioPago = new MedioPago();
        
        comprobantePMC.setMoneda("0");
        comprobantePMC.setImporte("1200");
        comprobantePMC.setFechaPago("20171214");
        comprobantePMC.setTipoCuenta("09");
        comprobantePMC.setNroTransaccion("12");
        comprobanteRes.setDestinatarios(destinatarios );
        destinatarios.add(comprobantePMC);
        comprobanteRes.setCodigoRetornoExtendido("00000000000");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cliente.setCuentas(cuentas);
        cliente.getCuentas().add(cuenta);
        
        Cuenta cuentaBanelco = new Cuenta();
        cuentaBanelco.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuentaBanelco.setCodigoAplicacion("ABAE");
        cliente.getCuentas().add(cuentaBanelco);
        
        respuestaMP.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaMP.setRespuesta(medioPago);
        medioPago.setNombreFantasia("final fantasy");
        
        Mockito.when(medioPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respuestaMP );
        FieldUtils.writeDeclaredField(comprobante, "diasFechaDesde", "150", true);
        Mockito.when(comprobantesPagoMisCuentasDAO.consultar(Matchers.any(ConsultaComprobanteInEntity.class)))
                .thenReturn(comprobanteRes);

        Future<Respuesta<ComprobantesDTO>> res = comprobante.obtenerComprobantesPMCAsync(cliente, new TransaccionDTO());
        Assert.assertEquals(EstadoRespuesta.OK, res.get().getEstadoRespuesta());

    }
}
