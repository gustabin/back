package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.bo;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


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
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.bo.impl.ModificacionLimiteDebitoBOImpl;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dao.ModifLimiteDebitoDAO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dto.ConsultaDatosTarjetaDebitoDTO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.ConsultaDatosTarjetaDebitoEntity;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.ModificarLimiteDebitoEntity;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteDescargaCambioLimiteView;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteModificacionLimiteDebitoView;

@RunWith(MockitoJUnitRunner.class)
public class ModificacionLimiteDebitoBOTest {

	@InjectMocks
	private ModificacionLimiteDebitoBOImpl modificacionLimiteDebitoBO;
	
	@Mock
	private ModifLimiteDebitoDAO modifLimiteDebitoDAO;
	
	@InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
	@Mock
    EstadisticaManager estadisticaManager;
	
	@Test
	public void getClaseTarjetaDebitoOk() throws DAOException, BusinessException {	
        String sucursal = new String("000");
        String numTarjeta = new String("4517660025192567    ");
        ConsultaDatosTarjetaDebitoEntity respuestaDAO = new ConsultaDatosTarjetaDebitoEntity();
        respuestaDAO.setTieneError(false);
        respuestaDAO.setCodError(Integer.parseInt("0"));
        respuestaDAO.setClaseTarjetaDebito("8");
        Mockito.when(modifLimiteDebitoDAO.getClaseTarjetaBanelco(Matchers.any(String.class), Matchers.any(String.class), Matchers.any(Cliente.class))).thenReturn(respuestaDAO);
        Respuesta<ConsultaDatosTarjetaDebitoDTO> respuesta = modificacionLimiteDebitoBO.getClaseTarjetaDebito(sucursal, numTarjeta, obtenerCliente());
        Assert.assertNotNull(respuesta);
        Assert.assertNotNull(respuesta.getRespuesta());
        Assert.assertEquals(respuesta.isRespuestaVacia(), false);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
    public void getClaseTarjetaDebitoError() throws DAOException, BusinessException {  
        String sucursal = new String("000");
        String numTarjeta = new String("451766002519256");
        ConsultaDatosTarjetaDebitoEntity respuestaDAO = new ConsultaDatosTarjetaDebitoEntity();
        respuestaDAO.setTieneError(true);
        respuestaDAO.setCodError(Integer.parseInt("20000226"));
        Mockito.when(modifLimiteDebitoDAO.getClaseTarjetaBanelco(Matchers.any(String.class), Matchers.any(String.class), Matchers.any(Cliente.class))).thenReturn(respuestaDAO);
        Respuesta<ConsultaDatosTarjetaDebitoDTO> respuesta = modificacionLimiteDebitoBO.getClaseTarjetaDebito(sucursal, numTarjeta, obtenerCliente());
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertNull(respuesta.getRespuesta());
    }
	
	@Test
	public void getClaseTarjetaDebitoException() throws DAOException, BusinessException {
	    String sucursal = new String("000");
        String numTarjeta = new String("45176600251925");
        given(modifLimiteDebitoDAO.getClaseTarjetaBanelco(Matchers.any(String.class), Matchers.any(String.class), Matchers.any(Cliente.class))).willThrow(new DAOException());
        Respuesta<ConsultaDatosTarjetaDebitoDTO> respuesta = modificacionLimiteDebitoBO.getClaseTarjetaDebito(sucursal, numTarjeta, obtenerCliente());
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
	}
	
	@Test
	public void modificarLimitesExtraccionOK() throws BusinessException, DAOException{
	    ComprobanteModificacionLimiteDebitoView inView = new ComprobanteModificacionLimiteDebitoView();
	    inView.setClaseTarjetaDebito("9");	    
	    String numTarjeta = "4517660025192567    ";
	    
		Respuesta<ResultadoTransaccion> respuestaDAO = new Respuesta<ResultadoTransaccion>();
		respuestaDAO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaDAO.setRespuestaVacia(false);
		Mockito.when(modifLimiteDebitoDAO.modificarLimitesExtraccion(Matchers.any(ModificarLimiteDebitoEntity.class))).thenReturn(respuestaDAO);
		
		Respuesta<ResultadoTransaccion> respuestaBO =  modificacionLimiteDebitoBO.modificarLimitesExtraccion(inView, numTarjeta, obtenerCliente());
		Assert.assertEquals(EstadoRespuesta.OK, respuestaBO.getEstadoRespuesta());
        Assert.assertEquals(respuestaBO.isRespuestaVacia(), false);
	}

	@Test
    public void modificarLimitesExtraccionERROR() throws BusinessException, DAOException{
        ComprobanteModificacionLimiteDebitoView inView = new ComprobanteModificacionLimiteDebitoView();
        inView.setClaseTarjetaDebito("9");      
        String numTarjeta = "";
        
        Respuesta<ResultadoTransaccion> respuestaDAO = new Respuesta<ResultadoTransaccion>();
        respuestaDAO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaDAO.setRespuestaVacia(false);
        Mockito.when(modifLimiteDebitoDAO.modificarLimitesExtraccion(Matchers.any(ModificarLimiteDebitoEntity.class))).thenReturn(respuestaDAO);
        
        Respuesta<ResultadoTransaccion> respuestaBO =  modificacionLimiteDebitoBO.modificarLimitesExtraccion(inView, numTarjeta, obtenerCliente());
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaBO.getEstadoRespuesta());
        Assert.assertEquals(respuestaBO.isRespuestaVacia(), true);
    }
	
	@Test
	public void modificarLimitesExtraccionException() throws DAOException, BusinessException {
        String sucursal = new String("000");
        String numTarjeta = new String("4517660025192567    ");

        given(modifLimiteDebitoDAO.modificarLimitesExtraccion(Matchers.any(ModificarLimiteDebitoEntity.class))).willThrow(new DAOException());
        Respuesta<ConsultaDatosTarjetaDebitoDTO> respuesta = modificacionLimiteDebitoBO.getClaseTarjetaDebito(sucursal, numTarjeta, obtenerCliente());
        Assert.assertEquals(respuesta.isRespuestaVacia(), true);
        Assert.assertNotNull(respuesta.getEstadoRespuesta());
        Assert.assertTrue(EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta()));
        
    }
	
	@Test
    public void comprobanteModifLimitesExtraccionOk() {
	    ComprobanteDescargaCambioLimiteView comprobanteInView = new ComprobanteDescargaCambioLimiteView();
	    Reporte reporteDAO = new Reporte();
        Mockito.when(modifLimiteDebitoDAO.comprobanteModifLimitesExtraccion(Matchers.any(ComprobanteDescargaCambioLimiteView.class))).thenReturn(reporteDAO);
        Respuesta<Reporte> res = modificacionLimiteDebitoBO.comprobanteModifLimitesExtraccion(comprobanteInView);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertNotNull(res.getRespuesta());
    }
	
	 private Cliente obtenerCliente() {
	    	Cliente cliente = new Cliente();
	        cliente.setApellido1("ANSELMI SUAREZ ULA");
	        cliente.setNombre("DANIA MILAGROS");
			return cliente;
	    }
	 
	 private Reporte getRespuestaReporte() {
	    	Reporte reporte = new Reporte();
	    	reporte.setNombre("Comprobante_Modificacion_Limite_Debito_");
	    	reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			return reporte;
	    }
}
