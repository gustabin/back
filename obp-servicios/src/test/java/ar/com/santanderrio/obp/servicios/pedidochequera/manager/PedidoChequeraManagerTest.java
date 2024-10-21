package ar.com.santanderrio.obp.servicios.pedidochequera.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chequera.bo.impl.PedidoChequeraBOImpl;
import ar.com.santanderrio.obp.servicios.chequera.dto.ChequeraInDTO;
import ar.com.santanderrio.obp.servicios.chequera.dto.ChequeraOutDTO;
import ar.com.santanderrio.obp.servicios.chequera.manager.impl.PedidoChequeraManagerImpl;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionInView;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionViewResponse;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraViewResponse;
import ar.com.santanderrio.obp.servicios.chequera.view.CuentaView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.combos.service.impl.DatosSelectoresServiceImpl;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaDetalleCuentaOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;


@RunWith(MockitoJUnitRunner.class)
public class PedidoChequeraManagerTest {
    
    @Autowired
    @InjectMocks
    private PedidoChequeraManagerImpl pedidoChequeraManager = new PedidoChequeraManagerImpl();
    
    @Mock
    private PedidoChequeraBOImpl pedidoChequeraBO;
    
    @Mock
    private DatosSelectoresServiceImpl datosSelectoresService = new DatosSelectoresServiceImpl();;
    
    @Mock
    private SesionCliente sesionCliente = new SesionCliente();
    
    @Mock
    private RespuestaFactory respuestaFactory;
    
    @Mock
    private CuentaBO cuentaBO;
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void getDatosChequera() throws DAOException {
        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta.setNroSucursal("033");
        cuenta.setNroCuentaProducto("78423709432");
        cuentas.add(cuenta);
        cliente.setApellido1("apellido");
        cliente.setCuentas(cuentas);
        
        List<Opcion> opciones = new ArrayList<Opcion>();
        Opcion opcion = new Opcion();
        opcion.setId("1");
        opcion.setOpcion("25");
        opciones.add(opcion);
        
        ConsultaDetalleCuentaOutEntity detalleCuenta = new ConsultaDetalleCuentaOutEntity();
        detalleCuenta.setIndSobregiro("S");
        
        Respuesta<ChequeraViewResponse> respuesta = new Respuesta<ChequeraViewResponse>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(datosSelectoresService.obtenerCantidadChequesComun()).thenReturn(opciones);
        Mockito.when(datosSelectoresService.obtenerCantidadChequeraPagoDiferido()).thenReturn(opciones);
        Mockito.when(datosSelectoresService.obtenerCantidadChequesPagoDiferido()).thenReturn(opciones);
        Mockito.when(datosSelectoresService.obtenerCantidadChequeraComun()).thenReturn(opciones);
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ChequeraViewResponse.class))).thenReturn(respuesta);
        Mockito.when(cuentaBO.obtenerDetalleCuenta(Matchers.any(Cuenta.class))).thenReturn(detalleCuenta);
        
        
        respuesta = pedidoChequeraManager.getDatosChequera();
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void confirmarPedidoChequera() {
        Respuesta<ChequeraConfirmacionViewResponse> respuesta = new Respuesta<ChequeraConfirmacionViewResponse>();
        Cliente cliente = new Cliente();
        cliente.setApellido1("apellido");
        ChequeraConfirmacionInView chequeraInView = obtenerChequeraInView();
        
        Respuesta<List<Respuesta<ChequeraOutDTO>>> respuestaDTO = obtenerRespuestaDTO(EstadoRespuesta.OK);
        
        Mockito.when(pedidoChequeraBO.confirmarPedidoChequera(Matchers.any(ChequeraInDTO.class))).thenReturn(respuestaDTO);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        respuesta = pedidoChequeraManager.confirmarPedidoChequera(chequeraInView);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    private ChequeraConfirmacionInView obtenerChequeraInView() {
        ChequeraConfirmacionInView chequeraInView = new ChequeraConfirmacionInView();
        CuentaView cuentaSeleccionada = new CuentaView();
        cuentaSeleccionada.setNumero("033-123434/4");
        chequeraInView.setCuentaSeleccionada(cuentaSeleccionada);
        chequeraInView.setCantidadChequeraComun("1");
        chequeraInView.setCantidadChequesComun("1");
        return chequeraInView;
    }

    private Respuesta<List<Respuesta<ChequeraOutDTO>>> obtenerRespuestaDTO(EstadoRespuesta estadoRespuesta) {    
        
        Respuesta<List<Respuesta<ChequeraOutDTO>>> respuestaDTO = new Respuesta<List<Respuesta<ChequeraOutDTO>>>();
        
        Respuesta<ChequeraOutDTO> respuestaOutDTO = new Respuesta<ChequeraOutDTO>();
        ChequeraOutDTO chequeraOutDTO = new ChequeraOutDTO();
        chequeraOutDTO.setCantidadCheque("1");
        chequeraOutDTO.setSucursalEntrega("sucursal");
        chequeraOutDTO.setLocalidadSucursal("localidad");
        chequeraOutDTO.setDomicilioSucursal("domicilio");
        respuestaOutDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaOutDTO.setRespuesta(chequeraOutDTO);
        
        
        Respuesta<ChequeraOutDTO> respuestaOutDTOBis = new Respuesta<ChequeraOutDTO>();
        ChequeraOutDTO chequeraOutDTOBis = new ChequeraOutDTO();
        chequeraOutDTOBis.setCantidadCheque("1");
        respuestaOutDTOBis.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaOutDTOBis.setRespuesta(chequeraOutDTO);
        
        List<Respuesta<ChequeraOutDTO>> lista = new ArrayList<Respuesta<ChequeraOutDTO>>();
        lista.add(respuestaOutDTO);
        lista.add(respuestaOutDTOBis);
        
        respuestaDTO.setRespuesta(lista);
        respuestaDTO.setEstadoRespuesta(estadoRespuesta);
        return respuestaDTO;
    }

    @Test
    public void confirmarPedidoChequeraWarning() {
        Respuesta<ChequeraConfirmacionViewResponse> respuesta = new Respuesta<ChequeraConfirmacionViewResponse>();
        Cliente cliente = new Cliente();
        cliente.setApellido1("apellido");
        ChequeraConfirmacionInView chequeraInView = obtenerChequeraInView();
        
        Respuesta<List<Respuesta<ChequeraOutDTO>>> respuestaDTO = obtenerRespuestaDTO(EstadoRespuesta.WARNING);
        
        Mockito.when(pedidoChequeraBO.confirmarPedidoChequera(Matchers.any(ChequeraInDTO.class))).thenReturn(respuestaDTO);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        respuesta = pedidoChequeraManager.confirmarPedidoChequera(chequeraInView);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void confirmarPedidoChequeraError() {
        Respuesta<ChequeraConfirmacionViewResponse> respuesta = new Respuesta<ChequeraConfirmacionViewResponse>();
        Cliente cliente = new Cliente();
        cliente.setApellido1("apellido");
        ChequeraConfirmacionInView chequeraInView = obtenerChequeraInView();
        
        Respuesta<List<Respuesta<ChequeraOutDTO>>> respuestaDTO = obtenerRespuestaDTO(EstadoRespuesta.ERROR);
        
        Mockito.when(pedidoChequeraBO.confirmarPedidoChequera(Matchers.any(ChequeraInDTO.class))).thenReturn(respuestaDTO);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        respuesta = pedidoChequeraManager.confirmarPedidoChequera(chequeraInView);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

}
