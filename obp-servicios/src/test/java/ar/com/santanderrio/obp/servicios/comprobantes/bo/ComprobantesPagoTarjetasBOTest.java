package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.ArrayList;
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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.ComprobantesPagoTarjetasBOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ComprobantesPagoTarjetaDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteVisaAmexIATXInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteVisaAmexIATXOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteVisaIATXEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresaAdheridaEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresasAdheridasDebitoAutoOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class ComprobantesPagoTarjetasBOTest {

    @InjectMocks
    private ComprobantesPagoTarjetasBO comprobantes = new ComprobantesPagoTarjetasBOImpl();

    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje DAO. */
    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private ComprobantesPagoTarjetaDAO comprobantesPagoTarjetaDAO;

    @Mock
    Future<ComprobanteVisaAmexIATXOutEntity> thread;

    @Test
    public void obtenerComprobantesDebitoAutomaticoEnCuentaAsyncTest()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        EmpresasAdheridasDebitoAutoOutEntity empresaAdherida = new EmpresasAdheridasDebitoAutoOutEntity();
        EmpresaAdheridaEntity empresa = new EmpresaAdheridaEntity();
        ComprobanteVisaAmexIATXOutEntity comprobanteDebitoAutomaticoOutEntity = new ComprobanteVisaAmexIATXOutEntity();

        empresaAdherida.setCodigoRetornoExtendido("0");
        empresaAdherida.setEmpresas(new ArrayList<EmpresaAdheridaEntity>());
        empresaAdherida.getEmpresas().add(empresa);

        comprobanteDebitoAutomaticoOutEntity.setCodigoRetornoExtendido("0");
        comprobanteDebitoAutomaticoOutEntity.setComprobantes(new ArrayList<ComprobanteVisaIATXEntity>());
        comprobanteDebitoAutomaticoOutEntity.getComprobantes().add(new ComprobanteVisaIATXEntity());

        Mockito.when(comprobantesPagoTarjetaDAO.consultarAsync(Matchers.any(ComprobanteVisaAmexIATXInEntity.class)))
                .thenReturn(thread);
        Mockito.when(thread.isDone()).thenReturn(true);
        Mockito.when(thread.get()).thenReturn(comprobanteDebitoAutomaticoOutEntity);
        FieldUtils.writeDeclaredField(comprobantes, "diasFechaDesde", "180", true);

        Future<Respuesta<ComprobantesDTO>> res = comprobantes.obtenerComprobantesPagoTarjetasAsync(obtenerCliente(), new TransaccionDTO(), Boolean.FALSE);
        Assert.assertEquals(EstadoRespuesta.OK, res.get().getEstadoRespuesta());

    }
    
    @Test
    public void obtenerComprobantesDebitoAutomaticoEnCuentaOkTest()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        EmpresasAdheridasDebitoAutoOutEntity empresaAdherida = new EmpresasAdheridasDebitoAutoOutEntity();
        EmpresaAdheridaEntity empresa = new EmpresaAdheridaEntity();
        ComprobanteVisaAmexIATXOutEntity comprobanteDebitoAutomaticoOutEntity = new ComprobanteVisaAmexIATXOutEntity();

        empresaAdherida.setCodigoRetornoExtendido("0");
        empresaAdherida.setEmpresas(new ArrayList<EmpresaAdheridaEntity>());
        empresaAdherida.getEmpresas().add(empresa);

        ComprobanteVisaIATXEntity visaIatxEntity = new ComprobanteVisaIATXEntity();
        visaIatxEntity.setEstado("A");
        visaIatxEntity.setFechaPagoDebito("20170922");
        visaIatxEntity.setFechaEstado("20170922");
        visaIatxEntity.setImporteDolares("20000");
        visaIatxEntity.setImportePagadoDebitado("30000");
        visaIatxEntity.setMedioDePago("09");
        visaIatxEntity.setNroComprobante("12827");
        visaIatxEntity.setOrigenMov("");
        visaIatxEntity.setTipoMoneda("02");
        
        comprobanteDebitoAutomaticoOutEntity.setCodigoRetornoExtendido("0");
        comprobanteDebitoAutomaticoOutEntity.setComprobantes(new ArrayList<ComprobanteVisaIATXEntity>());
        comprobanteDebitoAutomaticoOutEntity.getComprobantes().add(visaIatxEntity );

        Mockito.when(comprobantesPagoTarjetaDAO.consultarAsync(Matchers.any(ComprobanteVisaAmexIATXInEntity.class)))
                .thenReturn(thread);
        Mockito.when(thread.isDone()).thenReturn(true);
        Mockito.when(thread.get()).thenReturn(comprobanteDebitoAutomaticoOutEntity);
        FieldUtils.writeDeclaredField(comprobantes, "diasFechaDesde", "180", true);

        Future<Respuesta<ComprobantesDTO>> res = comprobantes.obtenerComprobantesPagoTarjetasAsync(obtenerCliente(), new TransaccionDTO(), Boolean.FALSE);
        Assert.assertEquals(EstadoRespuesta.OK, res.get().getEstadoRespuesta());

    }

    @Test
    public void obtenerComprobantesDebitoAutomaticoEnCuentaAsyncWaitTest()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {

        Mockito.when(comprobantesPagoTarjetaDAO.consultarAsync(Matchers.any(ComprobanteVisaAmexIATXInEntity.class)))
                .thenReturn(thread);

        Mockito.when(thread.isDone()).then(new Answer<Boolean>() {
            boolean esPrimerLlamado = true;

            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                if (esPrimerLlamado) {
                    esPrimerLlamado = false;
                    return false;
                }
                return true;
            }
        });
        Mockito.when(thread.get()).thenThrow(new InterruptedException());
        FieldUtils.writeDeclaredField(comprobantes, "diasFechaDesde", "180", true);

        Future<Respuesta<ComprobantesDTO>> res = comprobantes.obtenerComprobantesPagoTarjetasAsync(obtenerCliente(), new TransaccionDTO(), Boolean.FALSE);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.get().getEstadoRespuesta());

    }
    
    @Test
    public void obtenerComprobantesDebitoAutomaticoEnCuentaExecutionExceptionTest()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {

        Mockito.when(comprobantesPagoTarjetaDAO.consultarAsync(Matchers.any(ComprobanteVisaAmexIATXInEntity.class)))
                .thenReturn(thread);

        Mockito.when(thread.isDone()).then(new Answer<Boolean>() {
            boolean esPrimerLlamado = true;

            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                if (esPrimerLlamado) {
                    esPrimerLlamado = false;
                    return false;
                }
                return true;
            }
        });
        Mockito.when(thread.get()).thenThrow(new ExecutionException(new DAOException()));
        FieldUtils.writeDeclaredField(comprobantes, "diasFechaDesde", "180", true);

        Future<Respuesta<ComprobantesDTO>> res = comprobantes.obtenerComprobantesPagoTarjetasAsync(obtenerCliente(), new TransaccionDTO(), Boolean.FALSE);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.get().getEstadoRespuesta());

    }

    @Test
    public void obtenerComprobantesDebitoAutomaticoEnCuentaDAOExceptionTest()
            throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        EmpresasAdheridasDebitoAutoOutEntity empresaAdherida = new EmpresasAdheridasDebitoAutoOutEntity();
        EmpresaAdheridaEntity empresa = new EmpresaAdheridaEntity();
        ComprobanteVisaAmexIATXOutEntity comprobanteDebitoAutomaticoOutEntity = new ComprobanteVisaAmexIATXOutEntity();

        empresaAdherida.setCodigoRetornoExtendido("0");
        empresaAdherida.setEmpresas(new ArrayList<EmpresaAdheridaEntity>());
        empresaAdherida.getEmpresas().add(empresa);

        comprobanteDebitoAutomaticoOutEntity.setCodigoRetornoExtendido("1");
        comprobanteDebitoAutomaticoOutEntity.setComprobantes(new ArrayList<ComprobanteVisaIATXEntity>());
        comprobanteDebitoAutomaticoOutEntity.getComprobantes().add(new ComprobanteVisaIATXEntity());

        Mockito.when(comprobantesPagoTarjetaDAO.consultarAsync(Matchers.any(ComprobanteVisaAmexIATXInEntity.class)))
                .thenThrow(new DAOException());
        Mockito.when(comprobantesPagoTarjetaDAO.obtenerOutEntityErrorAsync()).thenReturn(thread);
        Mockito.when(thread.isDone()).thenReturn(true);
        Mockito.when(thread.get()).thenReturn(comprobanteDebitoAutomaticoOutEntity);
        FieldUtils.writeDeclaredField(comprobantes, "diasFechaDesde", "180", true);

        Future<Respuesta<ComprobantesDTO>> res = comprobantes.obtenerComprobantesPagoTarjetasAsync(obtenerCliente(), new TransaccionDTO(), Boolean.FALSE);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.get().getEstadoRespuesta());

    }

    @Test
    public void obtenerComprobantesPagoTarjetasVacio() {
        Cliente cliente = obtenerCliente();
        cliente.setCuentas(new ArrayList<Cuenta>());

        Respuesta<ComprobantesDTO> res = comprobantes.obtenerComprobantesPagoTarjetas(cliente, new TransaccionDTO(), Boolean.FALSE);
        Assert.assertTrue(res.getRespuesta().getComprobantes().isEmpty());
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    private Cliente obtenerCliente() {
        Cliente cliente = new Cliente();
        cliente.setFechaNacimiento("201111112");
        cliente.setDni("38677554");
        cliente.setNup("02372381723");
        cliente.setApellido1("Gomez");
        cliente.setApellido2("Ortiz");
        cliente.setNombre("Gabriel");
        cliente.setTipoDocumento("I");
        cliente.setPasswordRacf("@Isdnas98");
        cliente.setUsuarioRacf("@aiskw23");
        cliente.setCuentas(new ArrayList<Cuenta>());
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta.setEstadoTarjetaCredito("4");
        cuenta.setNroCuentaProducto("2");
        cuenta.setCodigoTitularidad("TI");
        cliente.getCuentas().add(cuenta);
        return cliente;
    }
}
