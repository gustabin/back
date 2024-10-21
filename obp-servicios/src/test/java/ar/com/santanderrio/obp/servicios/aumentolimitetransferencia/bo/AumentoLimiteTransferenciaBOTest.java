package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.bo;

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
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.bo.impl.AumentoLimiteTransferenciaBOImpl;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dao.AumentoLimiteTransferenciaDAO;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto.AumentoLimiteTransferenciaInDTO;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto.AumentoLimiteTransferenciaOutDTO;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.entities.AumentoLimiteTransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.entities.AumentoLimiteTransferenciaOutEntity;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.DatosComprobanteAumentoLimiteTransferencia;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;


@RunWith(MockitoJUnitRunner.class)
public class AumentoLimiteTransferenciaBOTest {

    @InjectMocks
    private AumentoLimiteTransferenciaBOImpl aumentoLimiteTransferenciaBO;
    
    @Mock
    private AumentoLimiteTransferenciaDAO aumentoLimiteTransferenciaDAO;
    
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Before
    public void init() {
        aumentoLimiteTransferenciaBO.setCanal("1");
        aumentoLimiteTransferenciaBO.setCanalId("1");
        aumentoLimiteTransferenciaBO.setCodAsociacion("1");
        aumentoLimiteTransferenciaBO.setCodigoCanal("1");
        aumentoLimiteTransferenciaBO.setCodigoCanalId("1");
        aumentoLimiteTransferenciaBO.setCodigoProductoId("1");
        aumentoLimiteTransferenciaBO.setCodigoSubcanal("1");
        aumentoLimiteTransferenciaBO.setCodigoSubcanalId("1");
        aumentoLimiteTransferenciaBO.setCodigoSubproductoId("1");
        aumentoLimiteTransferenciaBO.setCodSector("1");
        aumentoLimiteTransferenciaBO.setCodUser("1");
        aumentoLimiteTransferenciaBO.setComentario("1");
        aumentoLimiteTransferenciaBO.setCuentaDestionId("1");
        aumentoLimiteTransferenciaBO.setCuentaOrigenId("2963");
        aumentoLimiteTransferenciaBO.setDescripcionProductoId("1");
        aumentoLimiteTransferenciaBO.setFechaOperacionId("1");
        aumentoLimiteTransferenciaBO.setImporteId("1");
        aumentoLimiteTransferenciaBO.setMedioIngreso("1");
        aumentoLimiteTransferenciaBO.setMonedaId("1");
        aumentoLimiteTransferenciaBO.setMonedaOrigenId("1");
        aumentoLimiteTransferenciaBO.setSucursalOrigenId("2962");
        aumentoLimiteTransferenciaBO.setTipoDestinoId("1");
        aumentoLimiteTransferenciaBO.setTipoOperacionId("1");
        aumentoLimiteTransferenciaBO.setTipoPersona("1");
        aumentoLimiteTransferenciaBO.setTitularDestinoId("1");
    }
    
    @Test
    public void altaSolicitudAumentoLimiteTransferenciaOKTest() throws DAOException {
        
        Respuesta<AumentoLimiteTransferenciaOutEntity> respuestaDAO = new Respuesta<AumentoLimiteTransferenciaOutEntity>();
        respuestaDAO.setEstadoRespuesta(EstadoRespuesta.OK);
        AumentoLimiteTransferenciaOutEntity daoOutEntity = new AumentoLimiteTransferenciaOutEntity();
        daoOutEntity.setNroGestion("98765289");
        respuestaDAO.setRespuesta(daoOutEntity);
        
        Mockito.when(aumentoLimiteTransferenciaDAO.altaSolicitudAumentoLimiteTransferencia(Matchers.any(AumentoLimiteTransferenciaInEntity.class))).thenReturn(respuestaDAO);
        
        AumentoLimiteTransferenciaInDTO inDto = obtenerInDto();
        Respuesta<AumentoLimiteTransferenciaOutDTO> respuestaFinal = aumentoLimiteTransferenciaBO.altaSolicitudAumentoLimiteTransferencia(inDto);
        Assert.assertNotNull(respuestaFinal);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());
        Assert.assertNotNull(respuestaFinal.getRespuesta());
        Assert.assertEquals("98765289", respuestaFinal.getRespuesta().getNroGestion());
    }
    
    @Test
    public void generarComprobanteAumentoLimiteTransferenciaOKTest() {
        DatosComprobanteAumentoLimiteTransferencia datosComprobante = new DatosComprobanteAumentoLimiteTransferencia();
        Reporte reporteDAO = new Reporte();
        Mockito.when(aumentoLimiteTransferenciaDAO.generarComprobanteAumentoLimiteTransferencia(Matchers.any(DatosComprobanteAumentoLimiteTransferencia.class))).thenReturn(reporteDAO);
        Respuesta<Reporte> res = aumentoLimiteTransferenciaBO.generarComprobanteAumentoLimiteTransferencia(datosComprobante);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertNotNull(res.getRespuesta());
    }
    
    private AumentoLimiteTransferenciaInDTO obtenerInDto() {
        AumentoLimiteTransferenciaInDTO inDto = new AumentoLimiteTransferenciaInDTO();
        inDto.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        Cliente cliente = new Cliente();
        cliente.setNup("12312312");
        cliente.setApellido2("Apellido2");
        cliente.setApellido1("Apellido1");
        cliente.setNombre("Nombre");
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000003632381");
        cuenta.setNroSucursal("2345");
        cuenta.setContratoAltair("201-363238/1");
        cuenta.setTipoCuenta("Cuenta Única");
        cuenta.setMonedaAltair("ARS");
        cuenta.setProductoAltair("201-363238/1");
        cuenta.setSubproductoAltair("201-363238/1");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);
        inDto.setNroCuenta("201-363238/1");
        inDto.setCliente(cliente);
        inDto.setFechaEjecucion("12/12/2017");
        inDto.setRioRio(false);
        inDto.setCuentaPropia(true);
        inDto.setImporte("400000");
        inDto.setMoneda("peso");
        inDto.setTipoCuentaDescripcion("Cuenta Única");
        inDto.setTitular("Nombre Apellido1 Apellido2");
        inDto.setNroCuentaDestino("400-872651/1");
        
        return inDto;
    }
    
}
