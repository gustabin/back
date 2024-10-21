/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.bo;

import static org.mockito.BDDMockito.given;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.DebitoAutomaticoBOImpl;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.dao.DebitoAutomaticoDAO;

/**
 * Test para {@link DebitoAutomaticoBO}. caso: 9826
 * 
 * @author manuel.vargas
 * @since Aug 30, 2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class DebitoAutomaticoBOTest {

    /** The debito automatico BO. */
    @InjectMocks
    DebitoAutomaticoBOImpl debitoAutomaticoBO;

    /** The estadistica manager. */
    @Mock
    EstadisticaManager estadisticaManager;

    /** The debito automatico DAO. */
    @Mock
    DebitoAutomaticoDAO debitoAutomaticoDAO;

    /** The mensaje DAO. */
    @Mock
    MensajeDAO mensajeDAO;

    /** The resultado. */
    @Mock
    ResultadoTransaccion resultado;

    /** The adhesion debito automatico. */
    private AdhesionDebitoAutomatico adhesionDebitoAutomatico = new AdhesionDebitoAutomatico();

    /** The cliente. */
    private Cliente cliente = new Cliente();

    /** The resultado DAOOK. */
    private ResultadoTransaccion resultadoDAOOK = new ResultadoTransaccion();

    /** The resultado DAOWAR. */
    private ResultadoTransaccion resultadoDAOWAR = new ResultadoTransaccion();

    /** The resultado DAOERR. */
    private ResultadoTransaccion resultadoDAOERR = new ResultadoTransaccion();

    /** The resultado DAOEXC. */
    private ResultadoTransaccion resultadoDAOEXC = new ResultadoTransaccion();

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.adhesionDebitoAutomatico.setCuit("303334440");
        this.adhesionDebitoAutomatico.setNombreServicioEmpresa("SEGUROS");
        this.adhesionDebitoAutomatico.setNroPartidaServicioEmpresa("303334440");
        this.resultadoDAOOK.setEstadoRespuesta(EstadoRespuesta.OK);
        this.resultadoDAOOK.setNumeroComprobante("22220101");
        this.resultadoDAOOK.setFechaTransaccion(new Date());
        this.resultadoDAOWAR.setEstadoRespuesta(EstadoRespuesta.WARNING);
        this.resultadoDAOWAR.setNumeroComprobante(null);
        this.resultadoDAOWAR.setFechaTransaccion(new Date());
        this.resultadoDAOERR.setEstadoRespuesta(EstadoRespuesta.ERROR);
        this.resultadoDAOERR.setNumeroComprobante(null);
        this.resultadoDAOERR.setFechaTransaccion(new Date());
        this.resultadoDAOEXC.setEstadoRespuesta(EstadoRespuesta.ERROR);
        this.resultadoDAOEXC.setNumeroComprobante(null);
        this.resultadoDAOEXC.setFechaTransaccion(null);
        this.resultadoDAOEXC.setMensajeError("Mensaje error");
    }

    /**
     * Test OK ejecución baja debito automatico de servicios adheridos.
     *
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void bajaDebitoAutomaticoTestOK() throws ServiceException, DAOException {
        Mockito.when(this.debitoAutomaticoDAO.bajaAdhesion(Matchers.any(Cliente.class),
                Matchers.any(AdhesionDebitoAutomatico.class))).thenReturn(this.resultadoDAOOK);
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1115");
        mensaje.setMensaje("Baja de Adhesion ADDI");
        respuestaMensaje.setRespuesta(mensaje);
        Mockito.when(this.mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        // test OK
        Respuesta<ResultadoTransaccion> respuestaDAO = debitoAutomaticoBO.bajaAdhesion(this.adhesionDebitoAutomatico,
                this.cliente);
        Assert.assertNotNull(respuestaDAO);
        Assert.assertTrue(EstadoRespuesta.OK.equals(respuestaDAO.getEstadoRespuesta()));
        Assert.assertFalse(respuestaDAO.isRespuestaVacia());
        Assert.assertNotNull(respuestaDAO.getRespuesta());
        Assert.assertEquals("22220101", resultadoDAOOK.getNumeroComprobante());
    }

    /**
     * Test ERROR ejecución baja debito automatico de servicios adheridos.
     * Mensaje 1115
     *
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void bajaDebitoAutomaticoTestERROR() throws ServiceException, DAOException {
        Mockito.when(this.debitoAutomaticoDAO.bajaAdhesion(Matchers.any(Cliente.class),
                Matchers.any(AdhesionDebitoAutomatico.class))).thenReturn(this.resultadoDAOERR);
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1115");
        mensaje.setMensaje("Error en Baja de Adhesion ADDI");
        respuestaMensaje.setRespuesta(mensaje);
        Mockito.when(this.mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        // test no OK
        Respuesta<ResultadoTransaccion> respuestaDAO = debitoAutomaticoBO.bajaAdhesion(this.adhesionDebitoAutomatico,
                this.cliente);
        Assert.assertNotNull(respuestaDAO);
        Assert.assertTrue(EstadoRespuesta.WARNING.equals(respuestaDAO.getEstadoRespuesta()));
        Assert.assertTrue(respuestaDAO.isRespuestaVacia());
    }

    /**
     * Test WARNING ejecución baja debito automatico de servicios adheridos.
     * Mensaje 1117-repeticion
     *
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void bajaDebitoAutomaticoTestWARNING() throws ServiceException, DAOException {
        Mockito.when(this.debitoAutomaticoDAO.bajaAdhesion(Matchers.any(Cliente.class),
                Matchers.any(AdhesionDebitoAutomatico.class))).thenReturn(this.resultadoDAOWAR);
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1117");
        mensaje.setMensaje("Ya se ejecuto Baja de Adhesion ADDI");
        respuestaMensaje.setRespuesta(mensaje);
        Mockito.when(this.mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        // test no OK
        Respuesta<ResultadoTransaccion> respuestaDAO = debitoAutomaticoBO.bajaAdhesion(this.adhesionDebitoAutomatico,
                this.cliente);
        Assert.assertNotNull(respuestaDAO);
        Assert.assertTrue(EstadoRespuesta.ERROR.equals(respuestaDAO.getEstadoRespuesta()));
        Assert.assertTrue(respuestaDAO.isRespuestaVacia());
    }

    /**
     * Test DAO EXCEPTION en ejecución baja debito automatico de servicios
     * adheridos.
     *
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void bajaDebitoAutomaticoTestDAOEXCEPTION() throws ServiceException, DAOException {
        // Respuesta<ResultadoTransaccion> respuestaMockDAO = new
        // Respuesta<ResultadoTransaccion>();
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1115");
        mensaje.setMensaje("Baja de Adhesion ADDI");
        respuestaMensaje.setRespuesta(mensaje);
        Mockito.when(this.mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        given(this.debitoAutomaticoDAO.bajaAdhesion(Matchers.any(Cliente.class),
                Matchers.any(AdhesionDebitoAutomatico.class))).willThrow(new DAOException());
        // test ERROR with Exceptions
        Respuesta<ResultadoTransaccion> respuestaDAO = debitoAutomaticoBO.bajaAdhesion(this.adhesionDebitoAutomatico,
                this.cliente);
        Assert.assertNotNull(respuestaDAO);
        Assert.assertTrue(EstadoRespuesta.WARNING.equals(respuestaDAO.getEstadoRespuesta()));
        Assert.assertTrue(respuestaDAO.isRespuestaVacia());
        Assert.assertNotNull(respuestaDAO.getItemsMensajeRespuesta());
    }
}
