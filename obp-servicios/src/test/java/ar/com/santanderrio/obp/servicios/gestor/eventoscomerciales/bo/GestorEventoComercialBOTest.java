package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo;

import static org.mockito.Mockito.when;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo.impl.GestorEventoComercialBOImpl;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.GestorEventoComercialDAO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChancesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.ChanceGrillaOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.ChanceOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.FinalizarPromesaEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GestionEventoComercialOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.NotificacionOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.mock.GestionEventoComercialOutEntityMock;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.mock.OfertaComercialDTOMock;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.ChanceHistorialView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.FinalizarPromesaPagoInView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosDeDomicilioDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class GestorEventoComercialBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class GestorEventoComercialBOTest {

    /** The gestor evento comercial BO. */
    @InjectMocks
    private GestorEventoComercialBO gestorEventoComercialBO = new GestorEventoComercialBOImpl();

    /** The gestor evento comercial DAO. */
    @Mock
    private GestorEventoComercialDAO gestorEventoComercialDAO;
    
    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;
    
    
    @Mock
	private SesionParametros sessionParametros;
    
    
    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private ContadorIntentos contadorIntentos;
    

    /**
     * Cuando obtener cantidad notificaciones obtengo notificaciones out entity.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void cuandoObtenerCantidadNotificacionesObtengoNotificacionesOutEntity()
            throws DAOException, BusinessException {
        GestionEventoComercialOutEntity notificacionesOutEntity = generarCantidadNotificacionesMock();
        Cliente cliente = ClienteMock.infoCliente();

        Mockito.when(gestorEventoComercialDAO.obtenerCantidadNotificaciones(cliente))
                .thenReturn(notificacionesOutEntity);

        GestionEventoComercialOutEntity notificacion = gestorEventoComercialBO.obtenerCantidadNotificaciones(cliente);
        Assert.assertEquals("Error: no tiene notificaciones", notificacion.getTotalNotif(), "1");
    }

    /**
     * Verifica que el nup no sea null.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void cuandoObtenerNotificacionesConNupNullDevuelveException() throws DAOException, BusinessException {
        GestionEventoComercialOutEntity notificacionesOutEntity = this.generarCantidadNotificacionesMock();
        notificacionesOutEntity.setNup(null);

        ejecutaValidacionCantidadNotificaciones(notificacionesOutEntity);
    }

    /**
     * Se espera excepcion si el nup no es numerico.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void cuandoObtenerNotificacionesConNupFormatoInvalidoDevuelveException()
            throws DAOException, BusinessException {
        GestionEventoComercialOutEntity notificacionesOutEntity = this.generarCantidadNotificacionesMock();
        notificacionesOutEntity.setNup("alkdfjaskl");

        ejecutaValidacionCantidadNotificaciones(notificacionesOutEntity);
    }

    /**
     * Se espera excepcion si el nup no es numerico.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void cuandoObtenerNotificacionesConNupNegativoDevuelveException() throws DAOException, BusinessException {
        GestionEventoComercialOutEntity notificacionesOutEntity = this.generarCantidadNotificacionesMock();
        notificacionesOutEntity.setNup("-1");

        ejecutaValidacionCantidadNotificaciones(notificacionesOutEntity);
    }

    /**
     * Verifica que el CantNotifSinLeer no sea null.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void cuandoObtenerNotificacionesConCantNotifSinLeerNullDevuelveException()
            throws DAOException, BusinessException {
        GestionEventoComercialOutEntity notificacionesOutEntity = this.generarCantidadNotificacionesMock();
        notificacionesOutEntity.setCantNotifSinLeer(null);

        ejecutaValidacionCantidadNotificaciones(notificacionesOutEntity);
    }

    /**
     * Se espera excepcion si el CantNotifSinLeer no es numerico.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void cuandoObtenerNotificacionesCantNotifSinLeerFormatoInvalidoDevuelveException()
            throws DAOException, BusinessException {
        GestionEventoComercialOutEntity notificacionesOutEntity = this.generarCantidadNotificacionesMock();
        notificacionesOutEntity.setCantNotifSinLeer("alkdfjaskl");

        ejecutaValidacionCantidadNotificaciones(notificacionesOutEntity);
    }

    /**
     * Se espera excepcion si el CantNotifSinLeer no es numerico.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void cuandoObtenerNotificacionesCantNotifSinLeerNegativoDevuelveException()
            throws DAOException, BusinessException {
        GestionEventoComercialOutEntity notificacionesOutEntity = this.generarCantidadNotificacionesMock();
        notificacionesOutEntity.setCantNotifSinLeer("-1");

        ejecutaValidacionCantidadNotificaciones(notificacionesOutEntity);
    }

    /**
     * Verifica que el TotalNotif no sea null.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void cuandoObtenerNotificacionesConTotalNotifNullDevuelveException() throws DAOException, BusinessException {
        GestionEventoComercialOutEntity notificacionesOutEntity = this.generarCantidadNotificacionesMock();
        notificacionesOutEntity.setTotalNotif(null);

        ejecutaValidacionCantidadNotificaciones(notificacionesOutEntity);
    }

    /**
     * Se espera excepcion si el TotalNotif no es numerico.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void cuandoObtenerNotificacionesTotalNotifFormatoInvalidoDevuelveException()
            throws DAOException, BusinessException {
        GestionEventoComercialOutEntity notificacionesOutEntity = this.generarCantidadNotificacionesMock();
        notificacionesOutEntity.setTotalNotif("alkdfjaskl");

        ejecutaValidacionCantidadNotificaciones(notificacionesOutEntity);
    }

    /**
     * Se espera excepcion si el TotalNotif no es numerico.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void cuandoObtenerNotificacionesTotalNotifNegativoDevuelveException()
            throws DAOException, BusinessException {
        GestionEventoComercialOutEntity notificacionesOutEntity = this.generarCantidadNotificacionesMock();
        notificacionesOutEntity.setTotalNotif("-1");

        ejecutaValidacionCantidadNotificaciones(notificacionesOutEntity);
    }

    /**
     * Se espera excepcion si el codigoError es null.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void cuandoObtenerNotificacionesCodigoErrorDevuelveException() throws DAOException, BusinessException {
        GestionEventoComercialOutEntity notificacionesOutEntity = this.generarCantidadNotificacionesMock();
        notificacionesOutEntity.setCodigoError(null);

        ejecutaValidacionCantidadNotificaciones(notificacionesOutEntity);
    }

    /**
     * Se espera excepcion si el codigoError es distinto a ERROR000.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void cuandoObtenerNotificacionesCodigoErrorDistintoA_ERROR000_DevuelveException()
            throws DAOException, BusinessException {
        GestionEventoComercialOutEntity notificacionesOutEntity = this.generarCantidadNotificacionesMock();
        notificacionesOutEntity.setCodigoError("ERRORAlgo! ! !");

        ejecutaValidacionCantidadNotificaciones(notificacionesOutEntity);
    }

    /**
     * Cuando obtengo cantidad notificaciones obtengo dao exception.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void cuandoObtengoCantidadNotificacionesObtengoDaoException() throws DAOException, BusinessException {
        Cliente cliente = ClienteMock.infoCliente();

        Mockito.when(gestorEventoComercialDAO.obtenerCantidadNotificaciones(cliente)).thenThrow(new DAOException());

        gestorEventoComercialBO.obtenerCantidadNotificaciones(cliente);

    }

    /**
     * obtengo la lista de notificaciones.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void cuandoObtengoNotificacionesEsperoNotificacionOutEntity() throws DAOException, BusinessException {
        Cliente cliente = ClienteMock.infoCliente();
        Mockito.when(gestorEventoComercialDAO.obtenerNotificaciones(cliente))
                .thenReturn(this.generarNotificacionesMock());

        GestionEventoComercialOutEntity notificaciones = gestorEventoComercialBO.obtenerNotificaciones(cliente);
        Assert.assertEquals("Error: se esperan 2 notificaciones", 2, notificaciones.getNotificaciones().size());
    }
    
    /**
     * Obtener notificaciones con goto landing mas goto link test.
     *
     * @throws DAOException the DAO exception
     * @throws BusinessException the business exception
     */
    @Test
    public void obtenerNotificacionesConGotoLandingMasGotoLinkTest() throws DAOException, BusinessException {
//        Given
        Cliente cliente = ClienteMock.infoCliente();
        GestionEventoComercialOutEntity notificacionesOutEntity = GestionEventoComercialOutEntityMock.completarInfoGotoLandingMasGotoLinkNotificaciones();
        
//        When
        Mockito.when(gestorEventoComercialDAO.obtenerNotificaciones(cliente))
                .thenReturn(notificacionesOutEntity);

//        Then
        GestionEventoComercialOutEntity notificaciones = gestorEventoComercialBO.obtenerNotificaciones(cliente);
        
//        Expected
        Assert.assertEquals("Error: se esperan 2 notificaciones", 1, notificaciones.getNotificaciones().size());
    }

    /**
     * obtengo la lista de notificaciones.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void cuandoObtengoNotificacionesEsperoDaoException() throws DAOException, BusinessException {
        Cliente cliente = ClienteMock.infoCliente();
        Mockito.when(gestorEventoComercialDAO.obtenerNotificaciones(cliente)).thenThrow(new DAOException());

        gestorEventoComercialBO.obtenerNotificaciones(cliente);
    }

    /**
     * Generar notificaciones mock.
     *
     * @return the gestion evento comercial out entity
     */
    private GestionEventoComercialOutEntity generarNotificacionesMock() {
        GestionEventoComercialOutEntity notificacionesOutEntity = new GestionEventoComercialOutEntity();
        notificacionesOutEntity.setNup("00129");
        notificacionesOutEntity.setCantNotifSinLeer("2");
        notificacionesOutEntity.setTotalNotif("2");
        notificacionesOutEntity.setCodigoError("ERROR000");
        notificacionesOutEntity.setDescripcionError("Una descripcion");

        List<NotificacionOutEntity> notificaciones = new ArrayList<NotificacionOutEntity>();
        NotificacionOutEntity notificacion1 = new NotificacionOutEntity();
        notificacion1.setIdNotificacion("12345");
        notificacion1.setNup("00001");
        notificacion1.setCodigo("10");
        notificacion1.setSubCodigo("20");
        notificacion1.setTitulo("Titulo 1");
        notificacion1.setMensajeAmigable("Ammigable 1");
        notificacion1.setIndicaLectura("S");
        notificacion1.setIndicaInactivable("S");
        notificacion1.setFechaAlta("20171o10 17:45");

        NotificacionOutEntity notificacion2 = new NotificacionOutEntity();
        notificacion2.setIdNotificacion("6789");
        notificacion2.setNup("00002");
        notificacion2.setCodigo("20");
        notificacion2.setSubCodigo("30");
        notificacion2.setTitulo("Titulo 2");
        notificacion2.setMensajeAmigable("Ammigable 2");
        notificacion2.setIndicaLectura("S");
        notificacion2.setIndicaInactivable("S");
        notificacion2.setFechaAlta("20171o10 17:45");

        notificaciones.add(notificacion1);
        notificaciones.add(notificacion2);

        notificacionesOutEntity.setNotificaciones(notificaciones);

        return notificacionesOutEntity;
    }

    // inicio metodos privados
    /**
     * Ejecuta validacion cantidad notificaciones.
     *
     * @param notificacionesOutEntity
     *            the notificaciones out entity
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    // -----------------------------------------------------------------------------------
    private void ejecutaValidacionCantidadNotificaciones(GestionEventoComercialOutEntity notificacionesOutEntity)
            throws DAOException, BusinessException {
        Cliente cliente = ClienteMock.infoCliente();

        Mockito.when(gestorEventoComercialDAO.obtenerCantidadNotificaciones(cliente))
                .thenReturn(notificacionesOutEntity);

        gestorEventoComercialBO.obtenerCantidadNotificaciones(cliente);
    }

    /**
     * Generar cantidad notificaciones mock.
     *
     * @return the gestion evento comercial out entity
     */
    private GestionEventoComercialOutEntity generarCantidadNotificacionesMock() {
        GestionEventoComercialOutEntity notificacionesOutEntity = new GestionEventoComercialOutEntity();
        notificacionesOutEntity.setNup("00129");
        notificacionesOutEntity.setCantNotifSinLeer("1");
        notificacionesOutEntity.setTotalNotif("1");
        notificacionesOutEntity.setCodigoError("ERROR000");
        notificacionesOutEntity.setDescripcionError("Una descripcion");
        return notificacionesOutEntity;
    }
    
    /**
     * Mapea a true cuando no tiene errores
     * @throws DAOException
     */
    @Test
    public void cuandoActualizoNotificacionesObtengoTrue() throws DAOException {
        Cliente cliente = ClienteMock.infoCliente();

        GestionEventoComercialOutEntity respuesta = new GestionEventoComercialOutEntity();
        respuesta.setCodigoError("ERROR000");
        Mockito.when(gestorEventoComercialDAO.actualizarNotificaciones(cliente))
                .thenReturn(respuesta);
        
        Assert.assertTrue(gestorEventoComercialBO.actualizarNotificaciones(cliente));
    }
    
    /**
     * Mapea a false en el caso de un DaoException
     * @throws DAOException
     */
    @Test
    public void cuandoActualizoNotificacionesObtengoFalse() throws DAOException {
        Cliente cliente = ClienteMock.infoCliente();

        Mockito.when(gestorEventoComercialDAO.actualizarNotificaciones(cliente))
                .thenThrow(new DAOException());
        
        Assert.assertFalse(gestorEventoComercialBO.actualizarNotificaciones(cliente));
    }

    /**
     * Tiene interes en notificacion OK.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void tieneInteresEnNotificacionOK() throws DAOException {
        // Given
        Cliente cliente = ClienteMock.infoCliente();
        String idNotificacion = "12343455";
        GestionEventoComercialOutEntity response = new GestionEventoComercialOutEntity();
        response.setCodigoError("error000");

        // When
        Mockito.when(gestorEventoComercialDAO.informarInteresEnNotificacion(Matchers.any(Cliente.class),
                Matchers.anyString())).thenReturn(response);

        // Then
        Boolean tieneInteresEnNotificacion = gestorEventoComercialBO.informarInteresEnNotificacion(cliente,
                idNotificacion);

        // Expected
        Assert.assertEquals(Boolean.TRUE, tieneInteresEnNotificacion);
    }

    /**
     * Tiene interes en notificacion cod error distinto AOK.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void tieneInteresEnNotificacionCodErrorDistintoAOK() throws DAOException {
        // Given
        Cliente cliente = ClienteMock.infoCliente();
        String idNotificacion = "12343455";
        GestionEventoComercialOutEntity response = new GestionEventoComercialOutEntity();
        response.setCodigoError("error650");

        // When
        Mockito.when(gestorEventoComercialDAO.informarInteresEnNotificacion(Matchers.any(Cliente.class),
                Matchers.anyString())).thenReturn(response);

        // Then
        Boolean tieneInteresEnNotificacion = gestorEventoComercialBO.informarInteresEnNotificacion(cliente,
                idNotificacion);

        // Expected
        Assert.assertEquals(Boolean.FALSE, tieneInteresEnNotificacion);
    }

    /**
     * Tiene interes en notificacion DAO exception.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void tieneInteresEnNotificacionDAOException() throws DAOException {
        // Given
        Cliente cliente = ClienteMock.infoCliente();
        String idNotificacion = "12343455";

        // When
        Mockito.when(gestorEventoComercialDAO.informarInteresEnNotificacion(Matchers.any(Cliente.class),
                Matchers.anyString())).thenThrow(new DAOException("DAO Exception."));

        // Then
        Boolean tieneInteresEnNotificacion = gestorEventoComercialBO.informarInteresEnNotificacion(cliente,
                idNotificacion);

        // Expected
        Assert.assertEquals(Boolean.FALSE, tieneInteresEnNotificacion);
    }

    /**
     * Informar interes oferta.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void informarFeedbackOferta() throws DAOException {
        // Given
        Cliente cliente = new Cliente();
        OfertaComercialDTO ofertaComercialDTO = new OfertaComercialDTO();
        String seccion = "TARJETAS";
        String accion = "CLICKED";

        // When
        Mockito.when(gestorEventoComercialDAO.informarFeedbackOferta(Matchers.any(Cliente.class), Matchers.anyString(),
                Matchers.any(OfertaComercialDTO.class), Matchers.anyString())).thenReturn(true);

        // Then
        Boolean tieneInteresEnNotificacion = gestorEventoComercialBO.informarFeedbackOferta(cliente, seccion,
                ofertaComercialDTO, accion);

        // Expected
        Assert.assertTrue(tieneInteresEnNotificacion);
    }

    /**
     * Informar interes oferta DAO exception.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void informarFeedbackOfertaDAOException() throws DAOException {
        // Given
        Cliente cliente = new Cliente();
        OfertaComercialDTO ofertaComercialDTO = new OfertaComercialDTO();
        String seccion = "TARJETAS";
        String accion = "CLICKED";

        // When
        Mockito.when(gestorEventoComercialDAO.informarFeedbackOferta(Matchers.any(Cliente.class), Matchers.anyString(),
                Matchers.any(OfertaComercialDTO.class), Matchers.anyString())).thenThrow(new DAOException("DAO Exception."));

        // Then
        Boolean tieneInteresEnNotificacion = gestorEventoComercialBO.informarFeedbackOferta(cliente, seccion,
                ofertaComercialDTO, accion);

        // Expected
        Assert.assertFalse(tieneInteresEnNotificacion);
    }
    
    /**
     * Informar gestion AC. OK.
     * 
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void informarGestionACTestOK() throws DAOException {
    	Cliente cliente = new Cliente();
    	DatosDeDomicilioDTO domicilioDeReemplazo = new DatosDeDomicilioDTO(); 
    	OfertaComercialDTO ofertaComercialDTO = new OfertaComercialDTO();
    	EventosComercialesDTO eventosComercialesDTO = new EventosComercialesDTO();
    	
    	Mockito.when(sesionParametros.getOfertaRecambioChip()).thenReturn(ofertaComercialDTO);
    	Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(eventosComercialesDTO);
    	
    	Mockito.when(gestorEventoComercialDAO.informarGestionAC(Matchers.any(Cliente.class),
                 Matchers.any(DatosDeDomicilioDTO.class), Matchers.any(OfertaComercialDTO.class)))
                 .thenReturn(Boolean.TRUE);

    	Boolean respuesta = gestorEventoComercialBO.informarGestionAC(cliente, domicilioDeReemplazo);
    	
    	Assert.assertEquals(Boolean.TRUE, respuesta);
    }
    
    /**
     * Informar gestion AC. Error en DAO Lanza un DAO Exception.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void informarGestionACTestErrorDAO() throws DAOException {
        // Given
    	Cliente cliente = new Cliente();
    	DatosDeDomicilioDTO domicilioDeReemplazo = new DatosDeDomicilioDTO(); 
    	OfertaComercialDTO ofertaComercialDTO = new OfertaComercialDTO();
    	EventosComercialesDTO eventosComercialesDTO = new EventosComercialesDTO();
    	
    	// When
    	Mockito.when(sesionParametros.getOfertaRecambioChip()).thenReturn(ofertaComercialDTO);
    	Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(eventosComercialesDTO);
        Mockito.when(gestorEventoComercialDAO.informarGestionAC(Matchers.any(Cliente.class),
                Matchers.any(DatosDeDomicilioDTO.class), Matchers.any(OfertaComercialDTO.class)))
        		.thenThrow(new DAOException("DAO Exception."));

        // Then
        Boolean respuesta = gestorEventoComercialBO.informarGestionAC(cliente, domicilioDeReemplazo);

        // Expected
        Assert.assertEquals(Boolean.FALSE, respuesta);
    }
    
    /**
     * obtenerPremiaciones OK.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerPremiacionesTestOK() throws DAOException {
    	//Datos Cliente
    	Cliente cliente = ClienteMock.infoCliente();
    	cliente.setNup("00276937");
    	//Datos ChanceOutEntity
    	ChanceOutEntity chanceOutEntity = new ChanceOutEntity();
    	chanceOutEntity.setTotal_premiaciones("2");
    	chanceOutEntity.setCodigoError("ERROR000");
    	chanceOutEntity.setNup("00276937");
    	List<ChanceGrillaOutEntity> listGrillaOut = new ArrayList<ChanceGrillaOutEntity>();
    	ChanceGrillaOutEntity grillaOut = new ChanceGrillaOutEntity();
    	grillaOut.setChances_acreditacion(20);
    	grillaOut.setChances_td(40);
    	grillaOut.setChances_visa(0);
    	grillaOut.setChances_amex(20);
    	grillaOut.setTotal(80);
    	
    	Date dateFechaAct = new Date();
    	SimpleDateFormat formato = new SimpleDateFormat("yyyyMM");
    	String fechaAct = formato.format(dateFechaAct);
    	grillaOut.setPeriodo(fechaAct);
    	
    	listGrillaOut.add(grillaOut);
    	grillaOut = new ChanceGrillaOutEntity();
    	grillaOut.setChances_acreditacion(10);
    	grillaOut.setChances_td(20);
    	grillaOut.setChances_visa(20);
    	grillaOut.setChances_amex(0);
    	grillaOut.setTotal(50);
    	grillaOut.setPeriodo("201906");
    	listGrillaOut.add(grillaOut);
    	chanceOutEntity.setChancesGrillaOutEntity(listGrillaOut);
    	
    	//cuando. reemplazo
    	Mockito.when(gestorEventoComercialDAO.obtenerPremiaciones(Matchers.any(String.class), 
    			Matchers.any(Cliente.class))).thenReturn(chanceOutEntity);
    	
    	Respuesta<ChancesDTO> respuestaDTO = gestorEventoComercialBO.obtenerPremiaciones("MOBILE", cliente, "20190705");
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
    }
    
    /**
     * obtenerPremiaciones ERROR. el nivel DAO lanza un DAO Exception. 
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerPremiacionesTestErrorDAOException() throws DAOException {
    	//Datos Cliente
    	Cliente cliente = ClienteMock.infoCliente();
    	cliente.setNup("00276937");
    	//Datos mensaje
    	Mensaje mensaje = MensajeMock.completarInfoMensaje("1897",
    			"<p>No hay información para mostrar.</p><p>Ocurrió un error en nuestros servicios. Por favor, volvé a ingresar más tarde.</p>");
    	
    	//cuando. reemplazo
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    	Mockito.when(gestorEventoComercialDAO.obtenerPremiaciones(Matchers.any(String.class), 
    			Matchers.any(Cliente.class))).thenThrow(new DAOException("DAO Exception."));
    	
    	Respuesta<ChancesDTO> respuestaDTO = gestorEventoComercialBO.obtenerPremiaciones("MOBILE", cliente, "20190705");
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
    }
    
    /**
     * obtenerPremiaciones ERROR. el nivel DAO nos da un respuesta con error. 
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerPremiacionesTestErrorRespuestaDAO() throws DAOException {
    	//Datos Cliente
    	Cliente cliente = ClienteMock.infoCliente();
    	cliente.setNup("00276937");
    	//Datos chanceOutEntity
    	ChanceOutEntity chanceOutEntity = new ChanceOutEntity();
    	chanceOutEntity.setCodigoError("eror000");
    	
    	//Datos mensaje
    	Mensaje mensaje = MensajeMock.completarInfoMensaje("1897",
    			"<p>No hay información para mostrar.</p><p>Ocurrió un error en nuestros servicios. Por favor, volvé a ingresar más tarde.</p>");
    	
    	//cuando. reemplazo
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    	Mockito.when(gestorEventoComercialDAO.obtenerPremiaciones(Matchers.any(String.class), 
    			Matchers.any(Cliente.class))).thenReturn(chanceOutEntity);
    	
    	Respuesta<ChancesDTO> respuestaDTO = gestorEventoComercialBO.obtenerPremiaciones("MOBILE", cliente, "20190705");
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
    }
    
    /**
     * obtenerPremiaciones Warning. Lista de chances Vacia. 
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerPremiacionesTestErrorListaVacia() throws DAOException {
    	//Datos Cliente
    	Cliente cliente = ClienteMock.infoCliente();
    	cliente.setNup("00276937");
    	//Datos ChanceOutEntity
    	ChanceOutEntity chanceOutEntity = new ChanceOutEntity();
    	chanceOutEntity.setTotal_premiaciones("2");
    	chanceOutEntity.setCodigoError("ERROR000");
    	chanceOutEntity.setNup("00276937");
    	chanceOutEntity.setChancesGrillaOutEntity(new ArrayList<ChanceGrillaOutEntity>());
    	
    	//Datos mensaje
    	Mensaje mensaje = MensajeMock.completarInfoMensaje("1898",
    			"Con la acreditación de tu sueldo y las compras que realices con tus tarjetas Santander Río crédito y débito comenzá a sumar chances para el sorteo de $ 30.000 por mes.");
    	
    	//cuando. reemplazo
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    	Mockito.when(gestorEventoComercialDAO.obtenerPremiaciones(Matchers.any(String.class), 
    			Matchers.any(Cliente.class))).thenReturn(chanceOutEntity);
    	
    	Respuesta<ChancesDTO> respuestaDTO = gestorEventoComercialBO.obtenerPremiaciones("MOBILE", cliente, "20190705");
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.WARNING, respuestaDTO.getEstadoRespuesta());
    }
    
    /**
     * obtenerCotasMaximaYMininaTestOK OK. 
     * 
     */
    @Test
    public void obtenerCotasMaximaYMininaTestOK() throws DAOException {
    	//Datos Cliente
    	Cliente cliente = ClienteMock.infoCliente();
    	cliente.setNup("00276937");
    	//Datos ChanceOutEntity
    	ChanceOutEntity chanceOutEntity = new ChanceOutEntity();
    	chanceOutEntity.setTotal_premiaciones("2");
    	chanceOutEntity.setCodigoError("ERROR000");
    	chanceOutEntity.setNup("00276937");
    	List<ChanceGrillaOutEntity> listGrillaOut = new ArrayList<ChanceGrillaOutEntity>();
    	ChanceGrillaOutEntity grillaOut = new ChanceGrillaOutEntity();
    	grillaOut.setChances_acreditacion(20);
    	grillaOut.setChances_td(40);
    	grillaOut.setChances_visa(0);
    	grillaOut.setChances_amex(20);
    	grillaOut.setTotal(80);
    	grillaOut.setPeriodo("201907");
    	listGrillaOut.add(grillaOut);
    	grillaOut = new ChanceGrillaOutEntity();
    	grillaOut.setChances_acreditacion(10);
    	grillaOut.setChances_td(20);
    	grillaOut.setChances_visa(20);
    	grillaOut.setChances_amex(0);
    	grillaOut.setTotal(50);
    	grillaOut.setPeriodo("201906");
    	listGrillaOut.add(grillaOut);
    	chanceOutEntity.setChancesGrillaOutEntity(listGrillaOut);
    	
    	//cuando. reemplazo
    	Mockito.when(gestorEventoComercialDAO.obtenerPremiaciones(Matchers.any(String.class), 
    			Matchers.any(Cliente.class))).thenReturn(chanceOutEntity);
    	
    	Respuesta<ChanceHistorialView> respuestaView = gestorEventoComercialBO.obtenerCotasMaximaYMinina("MOBILE", cliente);
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
    }
    
    /**
     * obtenerPremiaciones Error. Lista de chances Vacia. 
     *
     */
    @Test
    public void obtenerCotasMaximaYMininaTestErrorListaVacia() throws DAOException {
    	//Datos Cliente
    	Cliente cliente = ClienteMock.infoCliente();
    	cliente.setNup("00276937");
    	//Datos ChanceOutEntity
    	ChanceOutEntity chanceOutEntity = new ChanceOutEntity();
    	chanceOutEntity.setTotal_premiaciones("0");
    	chanceOutEntity.setCodigoError("ERROR000");
    	chanceOutEntity.setNup("00276937");
    	chanceOutEntity.setChancesGrillaOutEntity(new ArrayList<ChanceGrillaOutEntity>());
    	//Datos mensaje
    	Mensaje mensaje = MensajeMock.completarInfoMensaje(CodigoMensajeConstantes.WARNING_SIN_CHANCES,
    			"Con la acreditación de tu sueldo y las compras que realices con tus tarjetas Santander Río crédito y débito comenzá a sumar chances para el sorteo de $ 30.000 por mes.");
    	
    	
    	//cuando. reemplazo
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    	Mockito.when(gestorEventoComercialDAO.obtenerPremiaciones(Matchers.any(String.class), 
    			Matchers.any(Cliente.class))).thenReturn(chanceOutEntity);
    	
    	Respuesta<ChanceHistorialView> respuestaView = gestorEventoComercialBO.obtenerCotasMaximaYMinina("MOBILE", cliente);
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.WARNING, respuestaView.getEstadoRespuesta());
    }
    
    /**
     * obtenerPremiaciones Error. DAOException. 
     *
     */
    @Test
    public void obtenerCotasMaximaYMininaTestErrorDAOException() throws DAOException {
    	//Datos Cliente
    	Cliente cliente = ClienteMock.infoCliente();
    	cliente.setNup("00276937");
    	//Datos ChanceOutEntity
    	ChanceOutEntity chanceOutEntity = new ChanceOutEntity();
    	chanceOutEntity.setTotal_premiaciones("0");
    	chanceOutEntity.setCodigoError("ERROR000");
    	chanceOutEntity.setNup("00276937");
    	chanceOutEntity.setChancesGrillaOutEntity(new ArrayList<ChanceGrillaOutEntity>());
    	//Datos mensaje
    	Mensaje mensaje = MensajeMock.completarInfoMensaje("1897",
    			"<p>No hay información para mostrar.</p><p>Ocurrió un error en nuestros servicios. Por favor, volvé a ingresar más tarde.</p>");
    	
    	//cuando. reemplazo
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    	Mockito.when(gestorEventoComercialDAO.obtenerPremiaciones(Matchers.any(String.class), 
    			Matchers.any(Cliente.class))).thenThrow(new DAOException("DAO Exception."));
    	
    	Respuesta<ChanceHistorialView> respuestaView = gestorEventoComercialBO.obtenerCotasMaximaYMinina("MOBILE", cliente);
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuestaView.getEstadoRespuesta());
    }
    
     
	@Test
	public void finalizarPromesaPagoTestOk() {

		Cliente cliente = ClienteMock.infoCliente();
		cliente.setNup("00276937");
		
		FinalizarPromesaPagoInView finalizarPromesaPagoInView = new FinalizarPromesaPagoInView();
		finalizarPromesaPagoInView.setFechaPromesa("10/09/2019");
		finalizarPromesaPagoInView.setImportePromesa("$ 1234.22");
		
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		Mensaje mensajeMock = new Mensaje();
		mensajeMock.setMensaje("Mensaje test");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);
		Respuesta<FinalizarPromesaEntity> rtaFactory = new Respuesta<FinalizarPromesaEntity>();
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(FinalizarPromesaEntity.class))).thenReturn(rtaFactory);
		Respuesta<FinalizarPromesaEntity> resultadoBO = gestorEventoComercialBO.finalizarPromesaPago(cliente, finalizarPromesaPagoInView);
		Assert.assertEquals(resultadoBO.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
    
    
	@Test
	public void finalizarPromesaPagoTestError() throws DAOException {

		Cliente cliente = ClienteMock.infoCliente();
		cliente.setNup("00276937");
		
		FinalizarPromesaPagoInView finalizarPromesaPagoInView = new FinalizarPromesaPagoInView();
		finalizarPromesaPagoInView.setFechaPromesa("10/09/2019");
		finalizarPromesaPagoInView.setImportePromesa("$ 1234.22");
		
		ContadorIntentos contadorIntentos = new ContadorIntentos();
		contadorIntentos.permiteReintentar();
		when(sessionParametros.getContador()).thenReturn(contadorIntentos);
		Mensaje mensajeMock = new Mensaje();
		mensajeMock.setMensaje("Mensaje test");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);
		Mockito.when(gestorEventoComercialDAO.finalizarPromesaPago(Matchers.any(Cliente.class), Matchers.any(FinalizarPromesaPagoInView.class)))
				.thenThrow(new DAOException());

		Respuesta<Object> responseFactoryError = new Respuesta<Object>();
		responseFactoryError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		responseFactoryError.setRespuestaVacia(true);
		Mockito.when(
				respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(Matchers.anyString(), Matchers.anyString()))
				.thenReturn(responseFactoryError);
		Respuesta<FinalizarPromesaEntity> resultadoBO = gestorEventoComercialBO.finalizarPromesaPago(cliente,  finalizarPromesaPagoInView);
		Assert.assertNotNull(resultadoBO);
		Assert.assertEquals(EstadoRespuesta.ERROR, resultadoBO.getEstadoRespuesta());

	}
    
    
    
    
}
