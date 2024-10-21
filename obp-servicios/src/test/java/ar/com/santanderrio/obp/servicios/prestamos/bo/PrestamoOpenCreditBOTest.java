package ar.com.santanderrio.obp.servicios.prestamos.bo;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.dao.DeudaPrestamoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.prestamos.bo.impl.PrestamoOpenCreditBOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoOpenCreditDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConsultaPagosMinimosOpenCreditInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.DetallePagosMinimosOpenCreditDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoOpenCreditDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoOpenCreditInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ConsultaPrestamoOpenCreditInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ConsultaPrestamoOpenCreditOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ReportePagosOpenCreditInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.mock.PrestamoOpenCreditObjectsMock;


/**
 * PrestamoOpenCredit BO Test
 * @author Silvina_Luque
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PrestamoOpenCreditBOTest {
    
    /** prestamoOpenCreditBO */
    @InjectMocks
    private PrestamoOpenCreditBO prestamoOpenCreditBO = new PrestamoOpenCreditBOImpl();  
    
    /** deudaPrestamoDAO */
    @Mock
    private DeudaPrestamoDAO deudaPrestamoDAO;
    
    /** prestamoOpenCreditDAO */
    @Mock
    private PrestamoOpenCreditDAO prestamoOpenCreditDAO;  
    
    /** respuestaFactory */
    @Mock
    private MensajeBO mensajeBO;
    
    /** reporte dao */
    @Mock
    private ReporteDAO reporteDAO;
    
    
    /** MensajeBO */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    /**mensaje */
    private Mensaje mensaje = new Mensaje();
    
    
    /**
     * Inits.
     */
    @Before
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        mensaje.setMensaje("Mensaje");
    }
    
    
    /**
     * obtenerPrestamosOpenCreditTest 
     */
    @Test
    public void obtenerPrestamosOpenCreditTest() throws DAOException{
        PrestamoOpenCreditInDTO prestamoOpenCreditInDTO = new PrestamoOpenCreditInDTO();
        prestamoOpenCreditInDTO.setCliente(PrestamoOpenCreditObjectsMock.obtenerClienteConCuentaOpenCredit());
        Prestamo resultadoDaoDeudaPrestamo = PrestamoOpenCreditObjectsMock.obtenerResultadoDaoDeudaPrestamo();
        ConsultaPrestamoOpenCreditOutEntity resultadoDaoOpenCredit = PrestamoOpenCreditObjectsMock.obtenerConsultaPrestamoOpenCreditOutEntity();
        when(deudaPrestamoDAO.consultarDeudaDePrestamoOpenCredit(Matchers.any(ConsultaPrestamoOpenCreditInEntity.class))).thenReturn(resultadoDaoDeudaPrestamo);
        when(prestamoOpenCreditDAO.consultarPrestamo(Matchers.any(ConsultaPrestamoOpenCreditInEntity.class))).thenReturn(resultadoDaoOpenCredit);
        Respuesta<List<PrestamoOpenCreditDTO>> respuesta = prestamoOpenCreditBO.obtenerPrestamosOpenCredit(prestamoOpenCreditInDTO);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    
    /**
     * obtenerPrestamosOpenCreditErrorTest 
     */
    @Test
    public void obtenerPrestamosOpenCreditErrorTest() throws DAOException{
        PrestamoOpenCreditInDTO prestamoOpenCreditInDTO = new PrestamoOpenCreditInDTO();
        prestamoOpenCreditInDTO.setCliente(PrestamoOpenCreditObjectsMock.obtenerClienteConCuentaOpenCredit());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(deudaPrestamoDAO.consultarDeudaDePrestamoOpenCredit(Matchers.any(ConsultaPrestamoOpenCreditInEntity.class))).thenThrow(new DAOException());
        Respuesta<List<PrestamoOpenCreditDTO>> respuesta = prestamoOpenCreditBO.obtenerPrestamosOpenCredit(prestamoOpenCreditInDTO);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    /**
     * obtenerPrestamosOpenCreditErrorRuntimeTest 
     */
    @Test
    public void obtenerPrestamosOpenCreditRuntimeErrorTest() throws DAOException{
        PrestamoOpenCreditInDTO prestamoOpenCreditInDTO = new PrestamoOpenCreditInDTO();
        prestamoOpenCreditInDTO.setCliente(PrestamoOpenCreditObjectsMock.obtenerClienteConCuentaOpenCredit());
        Prestamo resultadoDaoDeudaPrestamo = PrestamoOpenCreditObjectsMock.obtenerResultadoDaoDeudaPrestamo();
        ConsultaPrestamoOpenCreditOutEntity resultadoDaoOpenCredit = PrestamoOpenCreditObjectsMock.obtenerConsultaPrestamoOpenCreditOutEntity();
        resultadoDaoOpenCredit.setFechasInicio("2017");
        when(deudaPrestamoDAO.consultarDeudaDePrestamoOpenCredit(Matchers.any(ConsultaPrestamoOpenCreditInEntity.class))).thenReturn(resultadoDaoDeudaPrestamo);
        when(prestamoOpenCreditDAO.consultarPrestamo(Matchers.any(ConsultaPrestamoOpenCreditInEntity.class))).thenReturn(resultadoDaoOpenCredit);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<List<PrestamoOpenCreditDTO>> respuesta = prestamoOpenCreditBO.obtenerPrestamosOpenCredit(prestamoOpenCreditInDTO);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    /**
     * obtenerDetallePagosMinimosTest 
     */
    @Test
    public void obtenerDetallePagosMinimosTest() throws DAOException{
        ConsultaPagosMinimosOpenCreditInDTO consultaPagosMinimosOpenCreditInDTO = PrestamoOpenCreditObjectsMock.obtenerConsultaPagosMinimosOpenCreditInDTO();
        ConsultaCuotaPagaOutEntity consultaCuotaPagaOutEntity = PrestamoOpenCreditObjectsMock.obtenerConsultaCuotaPagaOutEntity();
        when(prestamoOpenCreditDAO.obtenerCuotasPagasPrestamo(Matchers.any(Cliente.class), Matchers.any(ConsultaCuotaPagaInEntity.class))).thenReturn(consultaCuotaPagaOutEntity);
        Respuesta<DetallePagosMinimosOpenCreditDTO> respuesta = prestamoOpenCreditBO.obtenerDetallePagosMinimos(consultaPagosMinimosOpenCreditInDTO);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    

    
    /**
     * obtenerDetallePagosMinimosTest 
     */
    @Test
    public void obtenerDetallePagosMinimosErrorTest() throws DAOException{
        ConsultaPagosMinimosOpenCreditInDTO consultaPagosMinimosOpenCreditInDTO = PrestamoOpenCreditObjectsMock.obtenerConsultaPagosMinimosOpenCreditInDTO();
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(prestamoOpenCreditDAO.obtenerCuotasPagasPrestamo(Matchers.any(Cliente.class), Matchers.any(ConsultaCuotaPagaInEntity.class))).thenThrow(new DAOException());
        Respuesta<DetallePagosMinimosOpenCreditDTO> respuesta = prestamoOpenCreditBO.obtenerDetallePagosMinimos(consultaPagosMinimosOpenCreditInDTO);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    
    /**
     * exportarHistorialPagosMinimosTest
     */
    @Test
    public void exportarHistorialPagosMinimosTest() throws DAOException{
        Respuesta<Reporte> respuestaDao = new Respuesta<Reporte>();
        respuestaDao.setEstadoRespuesta(EstadoRespuesta.OK);
        when(reporteDAO.obtenerReporte(Matchers.any(ReportePagosOpenCreditInEntity.class), Matchers.anyString(), Matchers.any(Cliente.class), Matchers.any(Boolean.class))).thenReturn(respuestaDao);
        Respuesta<Reporte> respuesta = prestamoOpenCreditBO.exportarHistorialPagosMinimos(PrestamoOpenCreditObjectsMock.obtenerReportePagosMinimosOpenCreditInView(), PrestamoOpenCreditObjectsMock.obtenerClienteConCuentaOpenCredit());
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
}
