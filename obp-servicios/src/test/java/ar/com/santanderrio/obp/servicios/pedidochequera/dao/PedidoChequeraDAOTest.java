package ar.com.santanderrio.obp.servicios.pedidochequera.dao;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.chequera.dao.PedidoChequeraDAO;
import ar.com.santanderrio.obp.servicios.chequera.dao.impl.PedidoChequeraDAOImpl;
import ar.com.santanderrio.obp.servicios.chequera.entity.PedidoChequeraInEntity;
import ar.com.santanderrio.obp.servicios.chequera.entity.PedidoChequeraOutEntity;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionView;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionViewResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        PedidoChequeraDAOTest.PedidoChequeraDAOTestConfiguration.class })
public class PedidoChequeraDAOTest extends IatxBaseDAOTest {
    
    /** The fondo DAO. */
    @Autowired
    @InjectMocks
    private PedidoChequeraDAO pedidoChequeraDAO;
    
    /** The cliente. */
    private Cliente cliente = new Cliente();
    
    private AbstractCuenta cuenta;;
    
    private PedidoChequeraInEntity pedidoChequeraInEntity ;
    
    /**
     * The Class RescateDAOTestConfiguration.
     */
    @Configuration
    public static class PedidoChequeraDAOTestConfiguration {

        /**
         * PedidoChequera DAO.
         *
         * @return the PedidoChequera DAO
         */
        @Bean
        public PedidoChequeraDAO pedidoChequeraDAO() {
            return new PedidoChequeraDAOImpl();
        }
    }
    
    @Value("classpath:/report/chequera/PedidoChequeraGenerico.jasper")
    private Resource fileJasper;
    
    @Before
    public void init() {
        pedidoChequeraInEntity = new PedidoChequeraInEntity();
        
        pedidoChequeraInEntity.setCantidadChequera("01");
        pedidoChequeraInEntity.setTipoChequera("00");
        pedidoChequeraInEntity.setCliente(cliente);
        
        cuenta = new Cuenta();
        cuenta.setTipoCuenta("02");
        cuenta.setNroCuentaProducto("366393");
        cuenta.setNroSucursal("033");
        
        pedidoChequeraInEntity.setCuenta(cuenta);
    }
    
    @Test
    public void pedidoChequeraConfirmacionTest() throws IatxException, DAOException {
        pedidoChequeraInEntity.setCantidadCheque("25");
        String servicio = "PEDCHR____";
        String version = "120";
        String tramaResponse = "000000000000000000000DHõ0011100000000õ20170718õTRIBUNALES                    õAV. CORRIENTES 1427           õ CAPITAL FEDERAL              õ>";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        Respuesta<PedidoChequeraOutEntity> respuesta = pedidoChequeraDAO.confirmarPedidoChequera(pedidoChequeraInEntity);

        Assert.assertNotNull(respuesta);
    }
    
    @Test
    public void pedidoChequeraConfirmacionErrorTest() throws IatxException, DAOException {
        pedidoChequeraInEntity.setCantidadCheque("8");
        String servicio = "PEDCHR____";
        String version = "120";
        String tramaResponse = "200000000000P04HTML0009900010300CRQJ37  ********00369489000000022017071212252600000000IBF00HF7346 00000000PEDCHR____1200000            00276937    00        00 000000000201707121225180000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ00053001 - FALLO FORMATEO CAMPO                          õ>";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        Respuesta<PedidoChequeraOutEntity> respuesta = pedidoChequeraDAO.confirmarPedidoChequera(pedidoChequeraInEntity);

        Assert.assertNotNull(respuesta);
    }
    
    @Test
    public void pedidoChequeraIATXException() throws IatxException, DAOException {
        String servicio = "PEDCHR____";
        String version = "120";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException("IatxException"));
        
        Respuesta<PedidoChequeraOutEntity> respuesta = pedidoChequeraDAO.confirmarPedidoChequera(pedidoChequeraInEntity);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void generarComprobanteChequeraTest(){
        Reporte reporte = new Reporte();
        ChequeraConfirmacionViewResponse chequeraConfirmacionViewResponse = new ChequeraConfirmacionViewResponse();
        obtenerChequeraConfirmacionViewResponse(chequeraConfirmacionViewResponse);
        
        reporte = pedidoChequeraDAO.generarComprobanteChequera(chequeraConfirmacionViewResponse);
        Assert.assertNotNull(reporte);
    }

    private void obtenerChequeraConfirmacionViewResponse(
            ChequeraConfirmacionViewResponse chequeraConfirmacionViewResponse) {
        chequeraConfirmacionViewResponse.setDomicilioSucursal("domicilio");
        chequeraConfirmacionViewResponse.setFechaHora("21/08/2017");
        chequeraConfirmacionViewResponse.setLocalidadSucursal("localidad");
        chequeraConfirmacionViewResponse.setMensaje("mensaje");
        chequeraConfirmacionViewResponse.setMoneda("pesos");
        chequeraConfirmacionViewResponse.setSucursalEntrega("sucursal");
        
        List<ChequeraConfirmacionView> chequeras = new ArrayList<ChequeraConfirmacionView>();
        ChequeraConfirmacionView chequeraComun = new ChequeraConfirmacionView();
        ChequeraConfirmacionView chequeraPagoDiferido = new ChequeraConfirmacionView();
        
        chequeraComun.setCantidadCheque("50");
        chequeraComun.setCantidadChequera("1");
        chequeraComun.setNumeroComprobante("123");
        chequeraComun.setNumeroCuenta("12");
        chequeraComun.setTipoChequera("00");
        chequeraComun.setPedidoChequeraOk(true);
        
        chequeraPagoDiferido.setCantidadCheque("50");
        chequeraPagoDiferido.setCantidadChequera("1");
        chequeraPagoDiferido.setNumeroComprobante("123");
        chequeraPagoDiferido.setNumeroCuenta("12");
        chequeraPagoDiferido.setTipoChequera("01");
        chequeraPagoDiferido.setPedidoChequeraOk(true);
  
        chequeras.add(chequeraComun);
        chequeras.add(chequeraPagoDiferido);
        chequeraConfirmacionViewResponse.setChequeras(chequeras);
    }
}
