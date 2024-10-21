package ar.com.santanderrio.obp.servicios.pedidochequera.bo;

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
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chequera.bo.impl.PedidoChequeraBOImpl;
import ar.com.santanderrio.obp.servicios.chequera.dao.impl.PedidoChequeraDAOImpl;
import ar.com.santanderrio.obp.servicios.chequera.dto.ChequeraInDTO;
import ar.com.santanderrio.obp.servicios.chequera.dto.ChequeraOutDTO;
import ar.com.santanderrio.obp.servicios.chequera.entity.PedidoChequeraInEntity;
import ar.com.santanderrio.obp.servicios.chequera.entity.PedidoChequeraOutEntity;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionViewResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteActivacionTagMonederoView;

@RunWith(MockitoJUnitRunner.class)
public class PedidoChequeraBOTest {
    
    @Autowired
    @InjectMocks
    private PedidoChequeraBOImpl pedidoChequeraBO = new PedidoChequeraBOImpl();
    
    @Mock
    private PedidoChequeraDAOImpl pedidoChequeraDAO = new PedidoChequeraDAOImpl();
    
    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory;
    
    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Test
    public void generarConfirmacionPedidoChequera() throws IllegalAccessException, DAOException {
        generarChequera("25", "00", "01", "00", true);        
    }
    
    @Test
    public void generarConfirmacionPedidoChequeras() throws IllegalAccessException, DAOException {
        generarChequera("25", "25", "01", "01", true);
    }
    
    @Test
    public void generarConfirmacionPedidoChequerasError() throws IllegalAccessException, DAOException {
        generarChequera(null, null, null, null, true);
    }
    
    @Test
    public void generarConfirmacionPedidoChequerasWarning() throws IllegalAccessException, DAOException {
        generarChequera("-1", null, null, null, true);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void generarComprobante() {
        ComprobanteActivacionTagMonederoView comprobanteActivacionTagMonederoView = new ComprobanteActivacionTagMonederoView();
        comprobanteActivacionTagMonederoView.setComprobante("123467890");
        comprobanteActivacionTagMonederoView.setNroTagMonedero("542379");
        
        Reporte respuestaReporte = new Reporte();
        
        ChequeraConfirmacionViewResponse listaChequeraConfirmacionView = new ChequeraConfirmacionViewResponse();
                
        Mockito.when(pedidoChequeraDAO.generarComprobanteChequera((ChequeraConfirmacionViewResponse) Matchers.any(Object.class))).thenReturn(respuestaReporte);
        
        Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
        respuesta = pedidoChequeraBO.generarComprobanteChequera(listaChequeraConfirmacionView);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    private void generarChequera(String cantidadChequeComun, String cantidadChequePD, String cantidadChequeraComun, 
            String cantidadChequeraPD, boolean clienteOk) throws DAOException {
        Respuesta<List<Respuesta<ChequeraOutDTO>>> respuesta = new Respuesta<List<Respuesta<ChequeraOutDTO>>>();
        Respuesta<PedidoChequeraOutEntity> respuestaOutEntity = new Respuesta<PedidoChequeraOutEntity>();
        PedidoChequeraOutEntity pedidoChequeraOutEntity = getPedidoChequeraOutEntity();
        
        ChequeraInDTO chequeraInDTO = getChequeraInDTO(cantidadChequeComun, cantidadChequePD, cantidadChequeraComun, cantidadChequeraPD, clienteOk);
        
        if(cantidadChequeComun == null) {
            respuestaOutEntity.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuestaOutEntity.setRespuesta(pedidoChequeraOutEntity);
            respuesta = obtenerRespuestaOK(respuestaOutEntity, chequeraInDTO);
            
            Assert.assertNotNull(respuesta);
            Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
            
        } else if(cantidadChequeComun == "-1"){
            respuestaOutEntity.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuestaOutEntity.setRespuesta(pedidoChequeraOutEntity);
            respuesta = obtenerRespuestaError(respuestaOutEntity, chequeraInDTO);
            
            Assert.assertNotNull(respuesta);
            Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        } else {
            respuestaOutEntity.setEstadoRespuesta(EstadoRespuesta.OK);
            respuestaOutEntity.setRespuesta(pedidoChequeraOutEntity);
            respuesta = obtenerRespuestaOK(respuestaOutEntity, chequeraInDTO);
            
            Assert.assertNotNull(respuesta);
            Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        }   
    }

    @SuppressWarnings("unchecked")
    private Respuesta<List<Respuesta<ChequeraOutDTO>>> obtenerRespuestaOK(
            Respuesta<PedidoChequeraOutEntity> respuestaOutEntity, ChequeraInDTO chequeraInDTO) throws DAOException {
        Respuesta<List<Respuesta<ChequeraOutDTO>>> respuesta;
        Mockito.when(pedidoChequeraDAO.confirmarPedidoChequera(Matchers.any(PedidoChequeraInEntity.class))).thenReturn(respuestaOutEntity);
        Respuesta<ChequeraOutDTO> respuestaDTO = new Respuesta<ChequeraOutDTO>();
        respuestaDTO.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ChequeraOutDTO.class))).thenReturn(respuestaDTO);
        Mockito.when(estadisticaManager.add(EstadisticasConstants.PEDIDO_DE_CHEQUERA_COMUN, EstadisticasConstants.CODIGO_ESTADISTICAS_OK)).thenReturn(true);
        
        respuesta = pedidoChequeraBO.confirmarPedidoChequera(chequeraInDTO);
        return respuesta;
    }
    
    private Respuesta<List<Respuesta<ChequeraOutDTO>>> obtenerRespuestaError(
            Respuesta<PedidoChequeraOutEntity> respuestaOutEntity, ChequeraInDTO chequeraInDTO) throws DAOException {
        Respuesta<List<Respuesta<ChequeraOutDTO>>> respuesta;
        Mockito.when(pedidoChequeraDAO.confirmarPedidoChequera(Matchers.any(PedidoChequeraInEntity.class))).thenReturn(respuestaOutEntity);
        Respuesta<ChequeraOutDTO> respuestaDTO = new Respuesta<ChequeraOutDTO>();
        respuestaDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(ChequeraOutDTO.class))).thenReturn(respuestaDTO);
        Mockito.when(estadisticaManager.add(EstadisticasConstants.PEDIDO_DE_CHEQUERA_COMUN, EstadisticasConstants.CODIGO_ESTADISTICAS_OK)).thenReturn(true);
        
        respuesta = pedidoChequeraBO.confirmarPedidoChequera(chequeraInDTO);
        return respuesta;
    }

    private ChequeraInDTO getChequeraInDTO(String cantidadChequeComun, String cantidadChequePD, String cantidadChequeraComun, 
            String cantidadChequeraPD, boolean clienteOk) {
        ChequeraInDTO chequeraInDTO = new ChequeraInDTO();
        chequeraInDTO.setCantidadChequeComun(cantidadChequeComun);
        chequeraInDTO.setCantidadChequePagoDiferido(cantidadChequePD);
        chequeraInDTO.setCantidadChequeraComun(cantidadChequeraComun);
        chequeraInDTO.setCantidadChequeraPagoDiferido(cantidadChequeraPD);
        chequeraInDTO.setCliente(clienteOk ? getCliente() : getCliente());
        chequeraInDTO.setCuenta("033-366393/6");
        chequeraInDTO.setPedidoChequeraComun(true);
        chequeraInDTO.setPedidoChequeraPagoDiferido(true);
        return chequeraInDTO;
    }

    private PedidoChequeraOutEntity getPedidoChequeraOutEntity() {
        PedidoChequeraOutEntity pedidoChequeraOutEntity = new PedidoChequeraOutEntity();
        pedidoChequeraOutEntity.setCantidadChequera("01");
        pedidoChequeraOutEntity.setCantidadCheques("25");
        pedidoChequeraOutEntity.setDomicilioSucursal("Rivadavia 1424");
        pedidoChequeraOutEntity.setLocalidadSucursal("CABA");
        pedidoChequeraOutEntity.setSucursalEntrega("FLORES");
        pedidoChequeraOutEntity.setNumeroComprobante("0123456789");
        pedidoChequeraOutEntity.setFechaTransaccion("12/12/2017");
        return pedidoChequeraOutEntity;
    }
    
    private Cliente getCliente() {
        Cliente cliente = new Cliente();
        cliente.setApellido1("MILANDO");
        cliente.setNombre("CONSTANCIO PERCY");
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setNroSucursal("033");
        cuenta.setNroCuentaProducto("3663936");
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);
        return cliente;
    }
}
