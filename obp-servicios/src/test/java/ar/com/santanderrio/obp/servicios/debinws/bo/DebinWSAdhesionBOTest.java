package ar.com.santanderrio.obp.servicios.debinws.bo;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import ar.com.santanderrio.obp.generated.webservices.debin.Error;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestAdhesion;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConsulta;
import ar.com.santanderrio.obp.generated.webservices.debin.Response;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseVendedor;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.debinws.bo.impl.DebinWSAdhesionBOImpl;
import ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSAdhesionDAO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CuentaAdhesionDTO;
import ar.com.santanderrio.obp.servicios.debinws.entities.ComprobanteAdhesionEntity;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultarAdhesionDebinesView;
import ar.com.santanderrio.obp.servicios.debinws.view.GestionarAdhesionDebinesView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class DebinWSAdhesionBOTest {
	
	@InjectMocks
    private DebinWSAdhesionBO debinWSAdhesionBO = new DebinWSAdhesionBOImpl();

    @Mock
    private DebinWSAdhesionDAO debinWSAdhesionDAO;

    @Mock
    private SesionParametros sesionParametros;

    @Mock
    private SesionCliente sesionCliente;

    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private MensajeManager mensajeManager;


    @Mock
    private MensajeDAO mensajeDAO;

    @InjectMocks
    @Spy
    private EstadisticaManager estadisticaManager = new EstadisticaManagerImpl();

    @Mock
    private EstadisticaBO estadisticaBO;

    
    Mensaje mensaje = new Mensaje();

    /** The cliente. */
    private Cliente cliente = new Cliente();
    
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
        MockitoAnnotations.initMocks(this);
        cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("0720033520000000819954");
        cuenta.setAlias("asdasdasd");
        cuenta.setNroSucursal("02");
        cuenta.setTipoCuenta("02");
        cuenta.setNroCuentaProducto("3423423423432");
        Cuenta cuentaPesos = new Cuenta();
        cuentaPesos.setCbu("0720033520000000819955");
        cuentaPesos.setAlias("Cuenta Pesos");
        cuentaPesos.setNroSucursal("02");
        cuentaPesos.setTipoCuenta("01");
        cuentaPesos.setNroCuentaProducto("3425523423432");
        Cuenta cuentaDolares = new Cuenta();
        cuentaDolares.setCbu("0720033520000000819956");
        cuentaDolares.setAlias("Cuenta Dolares");
        cuentaDolares.setNroSucursal("02");
        cuentaDolares.setTipoCuenta("03");
        cuentaDolares.setNroCuentaProducto("3427723423432");
        cuentas.add(cuenta);
        cuentas.add(cuentaPesos);
        cuentas.add(cuentaDolares);
        cliente.setCuentas(cuentas );
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("Silvina");
        cliente.setApellido1("Luque");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);
        cliente.setNup("123456789");
        cliente.setNumeroCUILCUIT("20382852150");
        cliente.setDni("38285215");
        cliente.setTipoDocumento("N");
    }
    
    @Test
    public void buscarCuentasParaAdhesionOK() throws DAOException{
    	ResponseVendedor responseConsulta = new ResponseVendedor();
    	
    	when(debinWSAdhesionDAO.consultaVendedor(Matchers.any(RequestConsulta.class))).thenReturn(responseConsulta);
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	
    	Respuesta<ConsultarAdhesionDebinesView> respuesta = debinWSAdhesionBO.buscarCuentasParaAdhesionDebines();
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void buscarCuentasParaAdhesionError() throws DAOException{
    	ResponseVendedor responseConsulta = new ResponseVendedor();
    	Error error = new Error();
    	
    	responseConsulta.setError(error);
    	
    	when(debinWSAdhesionDAO.consultaVendedor(Matchers.any(RequestConsulta.class))).thenReturn(responseConsulta);
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);

    	
    	Respuesta<ConsultarAdhesionDebinesView> respuesta = debinWSAdhesionBO.buscarCuentasParaAdhesionDebines();
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void gestionarAdhesionDebinesOK() throws DAOException{
    	
    	List<CuentaAdhesionDTO> listCuentasAdhesion = new ArrayList<CuentaAdhesionDTO>();
    	
    	CuentaAdhesionDTO cuentaAdherida = new CuentaAdhesionDTO();
    	cuentaAdherida.setAdherida(true);
    	CuentaAdhesionDTO cuentaNoAdherida = new CuentaAdhesionDTO();

    	listCuentasAdhesion.add(cuentaNoAdherida);
    	listCuentasAdhesion.add(cuentaAdherida);
    	
    	Response respuestaDAO = new Response();
    	respuestaDAO.setEstado("OK");
    	Error error = new Error();
    	error.setCodigo("00");
    	respuestaDAO.setError(error);
    	
    	GestionarAdhesionDebinesView gestionAdhesionInDTO = new GestionarAdhesionDebinesView();
    	gestionAdhesionInDTO.setCuentasCambiadas(listCuentasAdhesion);
    	
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(debinWSAdhesionDAO.bajaCuentaVendedor(Matchers.any(RequestAdhesion.class))).thenReturn(respuestaDAO);
    	when(debinWSAdhesionDAO.adhesionVendedor(Matchers.any(RequestAdhesion.class))).thenReturn(respuestaDAO);
    	when(mensajeManager.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    	
    	Respuesta<GestionarAdhesionDebinesView> respuesta = debinWSAdhesionBO.gestionarAdhesionDebines(gestionAdhesionInDTO);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void gestionarAdhesionDebinesError() throws DAOException{
    	
    	List<CuentaAdhesionDTO> listCuentasAdhesion = new ArrayList<CuentaAdhesionDTO>();
    	
    	CuentaAdhesionDTO cuentaAdherida = new CuentaAdhesionDTO();
    	cuentaAdherida.setAdherida(true);
    	listCuentasAdhesion.add(cuentaAdherida);
    	
    	Response respuestaDAO = new Response();
    	respuestaDAO.setEstado("OK");
    	Error error = new Error();
    	error.setCodigo("09");
    	respuestaDAO.setError(error);
    	
    	GestionarAdhesionDebinesView gestionAdhesionInDTO = new GestionarAdhesionDebinesView();
    	gestionAdhesionInDTO.setCuentasCambiadas(listCuentasAdhesion);
    	
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(debinWSAdhesionDAO.adhesionVendedor(Matchers.any(RequestAdhesion.class))).thenReturn(respuestaDAO);
    	when(mensajeManager.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    	
    	Respuesta<GestionarAdhesionDebinesView> respuesta = debinWSAdhesionBO.gestionarAdhesionDebines(gestionAdhesionInDTO);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void gestionarAdhesionDebinesErrorTimeout() throws DAOException{
    	
    	List<CuentaAdhesionDTO> listCuentasAdhesion = new ArrayList<CuentaAdhesionDTO>();
    	
    	CuentaAdhesionDTO cuentaAdherida = new CuentaAdhesionDTO();
    	cuentaAdherida.setAdherida(true);
    	CuentaAdhesionDTO cuentaNoAdherida = new CuentaAdhesionDTO();
    	
    	listCuentasAdhesion.add(cuentaNoAdherida);
    	listCuentasAdhesion.add(cuentaAdherida);
    	
    	DAOException e = new DAOException();
    	e.setErrorCode("099");
    	Response respuestaDAO = new Response();
    	respuestaDAO.setEstado("OK");
    	Error error = new Error();
    	error.setCodigo("09");
    	respuestaDAO.setError(error);
    	
    	GestionarAdhesionDebinesView gestionAdhesionInDTO = new GestionarAdhesionDebinesView();
    	gestionAdhesionInDTO.setCuentasCambiadas(listCuentasAdhesion);
    	
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(debinWSAdhesionDAO.bajaCuentaVendedor(Matchers.any(RequestAdhesion.class))).thenReturn(respuestaDAO);
    	when(debinWSAdhesionDAO.adhesionVendedor(Matchers.any(RequestAdhesion.class))).thenThrow(e);
    	
    	when(mensajeManager.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    	
    	Respuesta<GestionarAdhesionDebinesView> respuesta = debinWSAdhesionBO.gestionarAdhesionDebines(gestionAdhesionInDTO);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void gestionarAdhesionDebinesErrorGenerico() throws DAOException{
    	
    	List<CuentaAdhesionDTO> listCuentasAdhesion = new ArrayList<CuentaAdhesionDTO>();
    	
    	CuentaAdhesionDTO cuentaAdherida = new CuentaAdhesionDTO();
    	cuentaAdherida.setAdherida(true);
   
    	listCuentasAdhesion.add(cuentaAdherida);
    	
    	
    	GestionarAdhesionDebinesView gestionAdhesionInDTO = new GestionarAdhesionDebinesView();
    	gestionAdhesionInDTO.setCuentasCambiadas(listCuentasAdhesion);
    	
    	when(sesionCliente.getCliente()).thenReturn(cliente);
    	when(debinWSAdhesionDAO.adhesionVendedor(Matchers.any(RequestAdhesion.class))).thenThrow(new DAOException());
    	when(mensajeManager.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    	
    	Respuesta<GestionarAdhesionDebinesView> respuesta = debinWSAdhesionBO.gestionarAdhesionDebines(gestionAdhesionInDTO);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    @Test
    public void descargarComprobanteOK() throws DAOException{
    	GestionarAdhesionDebinesView gestionDebinView = new GestionarAdhesionDebinesView();
    	gestionDebinView.setTituloComprobante("Titulo");
    	gestionDebinView.setSubtituloComprobante("Subtitulo");
    	gestionDebinView.setNumeroComprobante("12345678");
    	
    	List<CuentaAdhesionDTO> listCuentasAdhesion = new ArrayList<CuentaAdhesionDTO>();
    	
    	CuentaAdhesionDTO cuentaAdherida = new CuentaAdhesionDTO();
    	cuentaAdherida.setAdherida(true);
    	cuentaAdherida.setCuentaNumero("12345678");
    	cuentaAdherida.setCuentaAlias("Alias");
    	cuentaAdherida.setLabelCuenta("Cuenta");
    	cuentaAdherida.setCuentaDescripcion("Cuenta Descripcion");
    	CuentaAdhesionDTO cuentaNoAdherida = new CuentaAdhesionDTO();
    	cuentaNoAdherida.setCuentaNumero("87654321");
    	cuentaNoAdherida.setCuentaAlias("Test");
    	cuentaNoAdherida.setLabelCuenta("Cuenta");
    	cuentaNoAdherida.setCuentaDescripcion("Descripcion Cuenta");
    	
    	listCuentasAdhesion.add(cuentaNoAdherida);
    	listCuentasAdhesion.add(cuentaAdherida);
    	
    	gestionDebinView.setCuentasCambiadas(listCuentasAdhesion);
    	
    	when(sesionParametros.getGestionarAdhesionDebinesView()).thenReturn(gestionDebinView);
    	when(debinWSAdhesionDAO.generarComprobante(Matchers.any(ComprobanteAdhesionEntity.class))).thenReturn(new Reporte());
    	
    	Respuesta<Reporte> respuesta = debinWSAdhesionBO.descargarComprobanteAdhesion();
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
}
