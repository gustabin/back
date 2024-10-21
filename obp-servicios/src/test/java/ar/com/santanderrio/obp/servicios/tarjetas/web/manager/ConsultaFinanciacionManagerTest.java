package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.ConsultaFinanciacionBo;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsultaFinanciacionDetalleDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TagItemMensajeTarjetaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl.ConsultaFinanciacionManagerImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsultaFinanciacionView;

/**
 * The Class ConsultaFinanciacionManagerTest.
 *
 * @author dante.omar.olmedo
 */
@RunWith(MockitoJUnitRunner.class)
public class ConsultaFinanciacionManagerTest {

    /** The consulta financiacion manager impl. */
    @InjectMocks
    private ConsultaFinanciacionManagerImpl consultaFinanciacionManagerImpl = new ConsultaFinanciacionManagerImpl();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The sesion tarjetas. */
    @Mock
    private SesionTarjetas sesionTarjetas;

    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory;

    /** The consulta financiacion bo. */
    @Mock
    private ConsultaFinanciacionBo consultaFinanciacionBo;
    
    @Mock
	private SesionParametros sesionParametros;

    /**
     * Obtener consulta financiacion service exception test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void obtenerConsultaFinanciacionGeneralExceptionTest() throws ServiceException {

        Respuesta<Object> toRet = new Respuesta<Object>();
        toRet.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Mockito.when(respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.CODIGO_ERROR_CONSULTA_FINANCIACION)).thenReturn(toRet);
        Respuesta<ConsultaFinanciacionView> res = consultaFinanciacionManagerImpl
                .obtenerConsultaFinanciacion("12-12-12");

        assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.ERROR);

    }

    /**
     * Obtener consulta financiacion respuesta ok test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerConsultaFinanciacionRespuestaOkTest() throws ServiceException, BusinessException {
        Cliente cliente = new Cliente();
        Respuesta<ConsultaFinanciacionDTO> respuesta = new Respuesta<ConsultaFinanciacionDTO>();
        ConsultaFinanciacionDTO consultaFinanciacion = new ConsultaFinanciacionDTO();
        Respuesta<ConsultaFinanciacionView> toRet = new Respuesta<ConsultaFinanciacionView>();

        respuesta.setRespuesta(consultaFinanciacion);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuestaVacia(true);
        toRet.setEstadoRespuesta(EstadoRespuesta.OK);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(consultaFinanciacionBo.obtenerListaFinanciacion(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn(respuesta);
        when(respuestaFactory.crearRespuestaOk(Matchers.eq(ConsultaFinanciacionView.class),
                Matchers.any(ConsultaFinanciacionView.class))).thenReturn(toRet);
        Respuesta<ConsultaFinanciacionView> res = consultaFinanciacionManagerImpl
                .obtenerConsultaFinanciacion("12-12-12");

        assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Crear estadistica OK business exception test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void crearEstadisticaOKBusinessExceptionTest() throws ServiceException, BusinessException {
        Cliente cliente = new Cliente();
        Respuesta<ConsultaFinanciacionDTO> respuesta = new Respuesta<ConsultaFinanciacionDTO>();
        ConsultaFinanciacionDTO consultaFinanciacion = new ConsultaFinanciacionDTO();
        Respuesta<ConsultaFinanciacionView> toRet = new Respuesta<ConsultaFinanciacionView>();

        respuesta.setRespuesta(consultaFinanciacion);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuestaVacia(true);
        toRet.setEstadoRespuesta(EstadoRespuesta.OK);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(consultaFinanciacionBo.obtenerListaFinanciacion(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn(respuesta);
        when(respuestaFactory.crearRespuestaOk(Matchers.eq(ConsultaFinanciacionView.class),
                Matchers.any(ConsultaFinanciacionView.class))).thenReturn(toRet);
        when(consultaFinanciacionBo.obtenerMarcaDeTarjeta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenThrow(new BusinessException());
        Respuesta<ConsultaFinanciacionView> res = consultaFinanciacionManagerImpl
                .obtenerConsultaFinanciacion("12-12-12");

        assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Obtener consulta financiacion respuesta warning test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerConsultaFinanciacionRespuestaWarningTest() throws ServiceException, BusinessException {
        Cliente cliente = new Cliente();
        Respuesta<ConsultaFinanciacionDTO> respuesta = new Respuesta<ConsultaFinanciacionDTO>();
        ConsultaFinanciacionDTO consultaFinanciacion = new ConsultaFinanciacionDTO();
        Respuesta<Object> toRet = new Respuesta<Object>();

        respuesta.setRespuesta(consultaFinanciacion);
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuesta.setRespuestaVacia(true);
        toRet.setEstadoRespuesta(EstadoRespuesta.WARNING);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(consultaFinanciacionBo.obtenerListaFinanciacion(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn(respuesta);
        when(respuestaFactory.crearRespuestaWarning(TagItemMensajeTarjetaEnum.CONSULTA_FINANCIACION.getDescripcionTag(),
                TipoError.ERROR_SIN_FINANCIACIONES, CodigoMensajeConstantes.CODIGO_ERROR_SIN_FINANCIACIONES))
                        .thenReturn(toRet);

        Respuesta<ConsultaFinanciacionView> res = consultaFinanciacionManagerImpl
                .obtenerConsultaFinanciacion("12-12-12");

        assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.WARNING);
    }

    /**
     * Obtener consulta financiacion respuesta error test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerConsultaFinanciacionRespuestaErrorTest() throws ServiceException, BusinessException {
        Cliente cliente = new Cliente();
        Respuesta<ConsultaFinanciacionDTO> respuesta = new Respuesta<ConsultaFinanciacionDTO>();
        ConsultaFinanciacionDTO consultaFinanciacion = new ConsultaFinanciacionDTO();
        Respuesta<Object> toRet = new Respuesta<Object>();

        respuesta.setRespuesta(consultaFinanciacion);
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuesta.setRespuestaVacia(true);
        toRet.setEstadoRespuesta(EstadoRespuesta.ERROR);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(consultaFinanciacionBo.obtenerListaFinanciacion(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn(respuesta);
        when(respuestaFactory.crearRespuestaError(null, TipoError.ERROR_CARGA_FINANCIACIONES,
                CodigoMensajeConstantes.CODIGO_ERROR_CONSULTA_FINANCIACION)).thenReturn(toRet);

        Respuesta<ConsultaFinanciacionView> res = consultaFinanciacionManagerImpl
                .obtenerConsultaFinanciacion("12-12-12");

        assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    /**
     * Generar respuesta consulta financiacion business exception test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void generarRespuestaConsultaFinanciacionBusinessExceptionTest() throws ServiceException, BusinessException {
        Cliente cliente = new Cliente();
        Respuesta<ConsultaFinanciacionDTO> respuesta = new Respuesta<ConsultaFinanciacionDTO>();
        ConsultaFinanciacionDTO consultaFinanciacion = new ConsultaFinanciacionDTO();
        Respuesta<Object> toRet = new Respuesta<Object>();

        respuesta.setRespuesta(consultaFinanciacion);
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuesta.setRespuestaVacia(true);
        toRet.setEstadoRespuesta(EstadoRespuesta.ERROR);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(consultaFinanciacionBo.obtenerListaFinanciacion(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenThrow(new BusinessException());
        when(respuestaFactory.crearRespuestaError(null, TipoError.ERROR_CARGA_FINANCIACIONES,
                CodigoMensajeConstantes.CODIGO_ERROR_CONSULTA_FINANCIACION)).thenReturn(toRet);
        when(consultaFinanciacionBo.obtenerMarcaDeTarjeta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenThrow(new BusinessException());

        Respuesta<ConsultaFinanciacionView> res = consultaFinanciacionManagerImpl
                .obtenerConsultaFinanciacion("12-12-12");

        assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    /**
     * Gets the sesion tarjetas test.
     *
     * @return the sesion tarjetas test
     */
    @Test
    public void getSesionTarjetasTest() {
        SesionTarjetas res = consultaFinanciacionManagerImpl.getSesionTarjetas();
        assertEquals(res, sesionTarjetas);
    }

    /**
     * Obtener consulta financiacion respuesta estado respuesta error test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void obtenerConsultaFinanciacionRespuestaEstadoRespuestaErrorTest() throws ServiceException {
        Cliente cliente = new Cliente();
        List<ItemMensajeRespuesta> listItemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        Respuesta<List<ConsultaFinanciacionDetalleDTO>> respuesta = new Respuesta<List<ConsultaFinanciacionDetalleDTO>>();

        itemMensajeRespuesta.setMensaje("Mensaje paso por armarRespuestasConMensajes");

        listItemMensajeRespuesta.add(itemMensajeRespuesta);

        respuesta.setItemMensajeRespuesta(listItemMensajeRespuesta);
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuesta.setRespuestaVacia(true);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        // when(consultaFinanciacionService.obtenerListaFinanciacion(Matchers.any(IdentificacionCuenta.class),
        // Matchers.any(Cliente.class))).thenReturn(respuesta);
        //
        // Respuesta<ConsultaFinanciacionView> res =
        // consultaFinanciacionManagerImpl.obtenerConsultaFinanciacion("12-12-12",
        // "Test.inc");
        //
        // assertEquals(res.getItemsMensajeRespuesta().get(0).getMensaje(),"Mensaje
        // paso por armarRespuestasConMensajes");
        // assertEquals(res.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        //
    }

    /**
     * Obtener consulta financiacion respuesta estado respuesta other test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void obtenerConsultaFinanciacionRespuestaEstadoRespuestaOtherTest() throws ServiceException {
        Cliente cliente = new Cliente();
        List<ItemMensajeRespuesta> listItemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        Respuesta<List<ConsultaFinanciacionDetalleDTO>> respuesta = new Respuesta<List<ConsultaFinanciacionDetalleDTO>>();

        listItemMensajeRespuesta.add(itemMensajeRespuesta);

        respuesta.setItemMensajeRespuesta(listItemMensajeRespuesta);
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR_CONCURRENTE);
        respuesta.setRespuestaVacia(true);

        // when(sesionCliente.getCliente()).thenReturn(cliente);
        // when(consultaFinanciacionService.obtenerListaFinanciacion(Matchers.any(IdentificacionCuenta.class),
        // Matchers.any(Cliente.class))).thenReturn(respuesta);
        //
        // Respuesta<ConsultaFinanciacionView> res =
        // consultaFinanciacionManagerImpl.obtenerConsultaFinanciacion("12-12-12",
        // "Test.inc");
        //
        // assertEquals(res.getEstadoRespuesta(), null);
        //
    }
}
