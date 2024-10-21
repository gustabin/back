/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.bo;

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
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.impl.OperacionFueraHorarioSucursalException;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.dao.ConsultaInhabilitadosDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosOutEntity;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.monedero.bo.impl.AltaTagMonederoBOImpl;
import ar.com.santanderrio.obp.servicios.monedero.dao.AltaTagMonederoDAO;
import ar.com.santanderrio.obp.servicios.monedero.dao.ConsultaUnidadControlDAO;
import ar.com.santanderrio.obp.servicios.monedero.dao.MonederoActivacionDAO;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlInEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlOutEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.DatosAltaTagMonederoEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.MonederoActivacionInEntities;
import ar.com.santanderrio.obp.servicios.monedero.exception.ErrorAltaSolicitudException;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteActivacionTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteAltaTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosParaActivacionView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteConfirmadoView;

/**
 * The Class AltaTagMonederoBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class AltaTagMonederoBOTest {

    /** The Alta Tag Monedero BO. */
	@InjectMocks
    private AltaTagMonederoBO altaTagMonederoBO = new AltaTagMonederoBOImpl();
	
    /** The alta tag monedero DAO. */
    @Mock
    private AltaTagMonederoDAO altaTagMonederoDAO;
    
    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;
    
    /** The estadistica manager. */
	@Mock
	private EstadisticaManager estadisticaManager;
	
    /** The consulta unidad control DAO. */
    @Mock
    private ConsultaUnidadControlDAO consultaUnidadControlDAO;
    
    /** The consulta inhabilitados DAO. */
    @Mock
    private ConsultaInhabilitadosDAO consultaInhabilitadosDAO;
    
    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    /** The monedero activacion DAO. */
    @Mock
    private MonederoActivacionDAO monederoActivacionDAO;
    
	/**
	 * Es persona habilitada.
	 * @throws DAOException 
	 */
	@Test
	public void esPersonaHabilitada() throws DAOException {
		Cliente cliente = new Cliente();
		
		ConsultaInhabilitadosInEntity in = new ConsultaInhabilitadosInEntity();
		in.setApellido("fulanito");
		in.setCliente(cliente);
		in.setFechaNac("01/01/1979");
		in.setNombre("cosme");
		in.setNroDoc("12345678");
		in.setSexo("M");
		in.setTipoDoc("N");
		
		
		ConsultaInhabilitadosOutEntity out = new ConsultaInhabilitadosOutEntity();
		
		out.setCodigoRetornoExtendido("RETURN OK");
		Mockito.when(consultaInhabilitadosDAO.consultaInhabilitados(Matchers.any(ConsultaInhabilitadosInEntity.class))).thenReturn(out);
		
		ConsultaInhabilitadosOutEntity respuesta = altaTagMonederoBO.esPersonaHabilitada(in);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals("RETURN OK", respuesta.getCodigoRetornoExtendido());
    }
	
	@Test
	public void esPersonaHabilitadaDAOException() throws DAOException{
		Cliente cliente = new Cliente();
		
		ConsultaInhabilitadosInEntity in = new ConsultaInhabilitadosInEntity();
		in.setApellido("fulanito");
		in.setCliente(cliente);
		in.setFechaNac("01/01/1979");
		in.setNombre("cosme");
		in.setNroDoc("12345678");
		in.setSexo("M");
		in.setTipoDoc("N");
		
		
		Mockito.when(consultaInhabilitadosDAO.consultaInhabilitados(Matchers.any(ConsultaInhabilitadosInEntity.class))).thenThrow(new DAOException());
		
		ConsultaInhabilitadosOutEntity respuesta = altaTagMonederoBO.esPersonaHabilitada(in);
        Assert.assertNotNull(respuesta);
    }
	
    /**
     * Obtener datos sucursal.
     */
    @Test
    public void obtenerDatosSucursal() {
    	Cliente cliente = new Cliente();
    	
    	ConsultaUnidadControlInEntity in = new ConsultaUnidadControlInEntity();
    	in.setCliente(cliente);
    	in.setNroDocumento("12345678");
    	in.setNup("12345");
    	in.setTipoDocumento("N");
    	
    	ConsultaUnidadControlOutEntity out = new ConsultaUnidadControlOutEntity();
    	when(altaTagMonederoBO.obtenerDatosSucursal(in)).thenReturn(out);
    }
    
    @Test
    public void obtenerDatosSucursalConDaoException() throws DAOException {
    	Cliente cliente = new Cliente();
    	
    	ConsultaUnidadControlInEntity in = new ConsultaUnidadControlInEntity();
    	in.setCliente(cliente);
    	in.setNroDocumento("12345678");
    	in.setNup("12345");
    	in.setTipoDocumento("N");
    	
    	Mockito.when(consultaUnidadControlDAO.consultaUC(Matchers.any(ConsultaUnidadControlInEntity.class))).thenThrow(new DAOException());
    	
    	ConsultaUnidadControlOutEntity respuesta = altaTagMonederoBO.obtenerDatosSucursal(in);
    	Assert.assertNotNull(respuesta);
    }
    
	@SuppressWarnings("unchecked")
	@Test
	public void ejecutarAltaTagMonederoOk() throws BusinessException {
    	
    	
    	ComprobanteAltaTagMonederoView out = new ComprobanteAltaTagMonederoView();
    	out.setComprobante("1234567890");
    	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
    	out.setFechaHora(dateFormatter.format(new Date()));
    	
    	DatosSolicitanteConfirmadoView datosSolicitanteConfirmadoView = new DatosSolicitanteConfirmadoView();
    	datosSolicitanteConfirmadoView.setAdicional(false);
    	datosSolicitanteConfirmadoView.setApellido("Gomez");
    	datosSolicitanteConfirmadoView.setNombre("Carlos");
    	out.setDatosSolicitanteConfirmadoView(datosSolicitanteConfirmadoView);
    	
    	DatosAltaTagMonederoEntity  datosAltaTagMonedero  = getDatosAltaTagMonedero();
    	Cliente cliente = getCliente();
    	
    	Respuesta<ComprobanteAltaTagMonederoView> respuestaComprobanteAltaTagMonederoView= new Respuesta<ComprobanteAltaTagMonederoView>();
    	respuestaComprobanteAltaTagMonederoView.setEstadoRespuesta(EstadoRespuesta.OK);
    	respuestaComprobanteAltaTagMonederoView.setRespuesta(out);
    	
    	Mockito.when(altaTagMonederoDAO.ejecutarAltaTagMonedero(Matchers.any(DatosAltaTagMonederoEntity.class), Matchers.any(Cliente.class))).thenReturn(out);
    	Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
    	Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),Matchers.any(ComprobanteAltaTagMonederoView.class))).thenReturn(respuestaComprobanteAltaTagMonederoView);

    	Respuesta<ComprobanteAltaTagMonederoView> respuesta = altaTagMonederoBO.ejecutarAltaTagMonedero(datosAltaTagMonedero, cliente);
    	
    	Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        Assert.assertEquals("1234567890", respuesta.getRespuesta().getComprobante());
		
    }
    
	@Test
	public void ejecutarAltaTagMonederoErrorConDAOException() throws BusinessException {
    	
    	DatosAltaTagMonederoEntity  datosAltaTagMonedero  = getDatosAltaTagMonedero();
    	Cliente cliente = getCliente();
    	
    	Respuesta<Object> respuestaAltaTagMonederoConException = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaAltaTagMonederoConException.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaAltaTagMonederoConException.setItemMensajeRespuesta(itemMensajeRespuestaList);

    	BusinessException e = new BusinessException();
    	e.initCause(new DAOException());
    	
    	Mockito.when(altaTagMonederoDAO.ejecutarAltaTagMonedero(Matchers.any(DatosAltaTagMonederoEntity.class), Matchers.any(Cliente.class))).thenThrow(e);
    	Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaAltaTagMonederoConException);
    	
    	Respuesta<ComprobanteAltaTagMonederoView> respuesta = altaTagMonederoBO.ejecutarAltaTagMonedero(datosAltaTagMonedero, cliente);
    	
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
		
    }
	
	@Test
	public void ejecutarAltaTagMonederoErrorConErrorAltaSolicitudException() throws BusinessException {
    	
    	DatosAltaTagMonederoEntity  datosAltaTagMonedero  = getDatosAltaTagMonedero();
    	Cliente cliente = getCliente();
    	
    	Respuesta<Object> respuestaAltaTagMonederoConException = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaAltaTagMonederoConException.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaAltaTagMonederoConException.setItemMensajeRespuesta(itemMensajeRespuestaList);

    	BusinessException e = new BusinessException();
    	e.initCause(new ErrorAltaSolicitudException());
    	
    	Mockito.when(altaTagMonederoDAO.ejecutarAltaTagMonedero(Matchers.any(DatosAltaTagMonederoEntity.class), Matchers.any(Cliente.class))).thenThrow(e);
    	Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaAltaTagMonederoConException);
    	
    	Respuesta<ComprobanteAltaTagMonederoView> respuesta = altaTagMonederoBO.ejecutarAltaTagMonedero(datosAltaTagMonedero, cliente);
    	
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
		
    }
	
	@Test
	public void ejecutarAltaTagMonederoErrorConOperacionFueraHorarioSucursalException() throws BusinessException {
    	
    	DatosAltaTagMonederoEntity  datosAltaTagMonedero  = getDatosAltaTagMonedero();
    	Cliente cliente = getCliente();
    	
    	Respuesta<Object> respuestaAltaTagMonederoConException = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaAltaTagMonederoConException.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaAltaTagMonederoConException.setItemMensajeRespuesta(itemMensajeRespuestaList);

    	BusinessException e = new BusinessException();
    	e.initCause(new OperacionFueraHorarioSucursalException());
    	
    	Mockito.when(altaTagMonederoDAO.ejecutarAltaTagMonedero(Matchers.any(DatosAltaTagMonederoEntity.class), Matchers.any(Cliente.class))).thenThrow(e);
    	Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaAltaTagMonederoConException);
    	
    	Respuesta<ComprobanteAltaTagMonederoView> respuesta = altaTagMonederoBO.ejecutarAltaTagMonedero(datosAltaTagMonedero, cliente);
    	
    	Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
		
    }
	
	
	@Test
    public void generarComprobanteActivacionTagMonedero() {
    	
    	Reporte out = new Reporte();
    	Mockito.when(altaTagMonederoDAO.generarComprobanteActivacionTagMonedero(Matchers.any(ComprobanteActivacionTagMonederoView.class))).thenReturn(out);
    	
    	ComprobanteActivacionTagMonederoView in = new ComprobanteActivacionTagMonederoView();
		Respuesta<Reporte> respuesta = altaTagMonederoBO.generarComprobanteActivacionTagMonedero(in);
		Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
	
	@Test
    public void generarComprobanteAltaTagMonedero() {
    	
    	Reporte out = new Reporte();
    	Mockito.when(altaTagMonederoDAO.generarComprobanteAltaTagMonedero(Matchers.any(ComprobanteAltaTagMonederoView.class))).thenReturn(out);
    	
    	ComprobanteAltaTagMonederoView in = new ComprobanteAltaTagMonederoView();
		Respuesta<Reporte> respuesta = altaTagMonederoBO.generarComprobanteAltaTagMonedero(in);
		Assert.assertNotNull(respuesta);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
	
	
    
	@Test
    public void activarMonederoTag() throws DAOException {
    	
    	Cuenta cuenta = new Cuenta();
    	cuenta.setNroSucursal("sucursal");
    	cuenta.setNroTarjetaCredito("tarjetaCredito");
    	
    	Respuesta<ResultadoTransaccion> monederoActivacionOutEntities = new Respuesta<ResultadoTransaccion>();
    	monederoActivacionOutEntities.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	Mockito.when(cuentaBO.obtenerCuentaPorId(Matchers.any(Cliente.class), Matchers.any(String.class))).thenReturn(cuenta);
    	Mockito.when(monederoActivacionDAO.activar(Matchers.any(MonederoActivacionInEntities.class))).thenReturn(monederoActivacionOutEntities);
    	
    	DatosParaActivacionView datosParaActivacionView = new DatosParaActivacionView();
    	datosParaActivacionView.setIdCuentaSeleccionada("1");
    	Cliente cliente = getCliente();
    	
		Respuesta<ResultadoTransaccion> respuesta = altaTagMonederoBO.activarMonederoTag(datosParaActivacionView, cliente);
    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
	@Test
    public void activarMonederoTagConDAOException() throws DAOException {
    	
    	Cuenta cuenta = new Cuenta();
    	cuenta.setNroSucursal("sucursal");
    	cuenta.setNroTarjetaCredito("tarjetaCredito");
    	
    	Respuesta<Object> respuestaActivarMonederoTagConException = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaActivarMonederoTagConException.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaActivarMonederoTagConException.setItemMensajeRespuesta(itemMensajeRespuestaList);
    	
    	
    	Mockito.when(cuentaBO.obtenerCuentaPorId(Matchers.any(Cliente.class), Matchers.any(String.class))).thenReturn(cuenta);
    	Mockito.when(monederoActivacionDAO.activar(Matchers.any(MonederoActivacionInEntities.class))).thenThrow(new DAOException());
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaActivarMonederoTagConException);
    	
    	DatosParaActivacionView datosParaActivacionView = new DatosParaActivacionView();
    	datosParaActivacionView.setIdCuentaSeleccionada("1");
    	Cliente cliente = getCliente();
    	
		Respuesta<ResultadoTransaccion> respuesta = altaTagMonederoBO.activarMonederoTag(datosParaActivacionView, cliente);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
    }
    
    private Cliente getCliente() {
    	Cliente cliente = new Cliente();
        cliente.setApellido1("Lopez");
        cliente.setNombre("Fernando");
        cliente.setNumeroCUILCUIT("202345676792");
		return cliente;
    }
    
    private DatosAltaTagMonederoEntity getDatosAltaTagMonedero() {
    	DatosAltaTagMonederoEntity datosAltaTagMonedero = new DatosAltaTagMonederoEntity();
    	
    	datosAltaTagMonedero.setApellidoEmbozado("");
		datosAltaTagMonedero.setNombreEmbozado("");
		datosAltaTagMonedero.setCuentaPesosSeleccionada(null);
		datosAltaTagMonedero.setMarcaEmisoraTarjetaOrigenPrincipal("");
		datosAltaTagMonedero.setNroTarjetaOrigenRecargasPrincipal("");
		datosAltaTagMonedero.setMarcaEmisoraTarjetaOrigenSecundaria("");
		datosAltaTagMonedero.setNroTarjetaOrigenRecargasSecundaria("");
		datosAltaTagMonedero.setNup("");
		datosAltaTagMonedero.setImporteARecargar("");
		datosAltaTagMonedero.setLimiteMensualRecarga("");
		datosAltaTagMonedero.setFechaNacimiento("");
		datosAltaTagMonedero.setPaisDeNacimiento("");
		datosAltaTagMonedero.setSexo("");
		datosAltaTagMonedero.setEstadoCivil("");
		datosAltaTagMonedero.setNacionalidad("");
		datosAltaTagMonedero.setCalle("");
		datosAltaTagMonedero.setNro("");
		datosAltaTagMonedero.setPiso("");
		datosAltaTagMonedero.setDepto("");
		datosAltaTagMonedero.setCp("");
		datosAltaTagMonedero.setLocalidad("");
		datosAltaTagMonedero.setProvincia("");
		datosAltaTagMonedero.setApellidoEmbozadoAdicional("");
		datosAltaTagMonedero.setNombreEmbozadoAdicional("");
		datosAltaTagMonedero.setNupAdicional("");
		return datosAltaTagMonedero;
    }    
}
