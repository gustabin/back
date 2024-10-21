package ar.com.santanderrio.obp.servicios.monedero.bo;

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

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.impl.NoSePuedeRealizarLaOperacionException;
import ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.ConsultaPadronOCuitDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.DatosSolicitanteDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitOutEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteCriterioEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.monedero.bo.impl.DatosSolicitanteBOImpl;
import ar.com.santanderrio.obp.servicios.monedero.dao.MonederoWebDAO;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteCriterioDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsTransacDTO;
import ar.com.santanderrio.obp.servicios.monedero.entities.TagEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TransaccionEntity;
import ar.com.santanderrio.obp.servicios.monedero.exception.OperacionFueraHorarioSucursalException;
import ar.com.santanderrio.obp.servicios.monedero.exception.SinAccesoALaInformacionException;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteCriterioView;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.MonederoDTO;

/**
 * The Class DatosSolicitanteBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class DatosSolicitanteBOTest {

    /** Datos Solicitante BO. */
	@InjectMocks
    private DatosSolicitanteBO datosSolicitanteBO = new DatosSolicitanteBOImpl();
	
	/** The datos Solicitante DAO. */
    @Mock
    private DatosSolicitanteDAO datosSolicitanteDAO;
    
    /** The Datos Selectores DAO. */
    @Mock
    private DatosSelectoresDAO datosSelectoresDAO;
    
    /** The Monedero Web DAO. */
    @Mock
    private MonederoWebDAO monederoWebDAO;
    
    /** The Consulta Padron O Cuit DAO. */
    @Mock
    private ConsultaPadronOCuitDAO consultaPadronOCuitDAO;
    
    /** The estadistica manager. */
	@Mock
	private EstadisticaManager estadisticaManager;
	
	/** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
	
	/**
	 * @throws DAOException
	 * @throws SinAccesoALaInformacionException
	 * @throws OperacionFueraHorarioSucursalException
	 * @throws BusinessException
	 */
	@Test
	public void getDatosDelSolicitanteWarning() throws DAOException, SinAccesoALaInformacionException, OperacionFueraHorarioSucursalException, BusinessException {
		
		DatosSolicitanteEntity out = new DatosSolicitanteEntity();
		out.setFechaNacimiento("00000000");
		
		Respuesta<Object> respuestaDatosDelSolicitanteWarning = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Warning Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_WARNING_HARDCODEADO_EN_EL_TEST");
        respuestaDatosDelSolicitanteWarning.setEstadoRespuesta(EstadoRespuesta.WARNING);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaDatosDelSolicitanteWarning.setItemMensajeRespuesta(itemMensajeRespuestaList);
        
		Mockito.when(datosSolicitanteDAO.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioEntity.class), Matchers.any(Cliente.class))).thenReturn(out);
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
    	Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaDatosDelSolicitanteWarning);
		
        DatosSolicitanteCriterioDTO datosSolicitante = new DatosSolicitanteCriterioDTO();
        datosSolicitante.setAdicional(true);
        datosSolicitante.setDoc("doc");
        datosSolicitante.setDocTipo("docTipo");
        datosSolicitante.setFechaNacimiento("01/03/1991");
        datosSolicitante.setNup("nup");

        Cliente cliente = new Cliente();
		Respuesta<DatosSolicitanteDTO> respuesta = datosSolicitanteBO.getDatosDelSolicitante(datosSolicitante , cliente);
        
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_WARNING_HARDCODEADO_EN_EL_TEST");
    }
	
	/**
	 * @throws DAOException
	 * @throws SinAccesoALaInformacionException
	 * @throws OperacionFueraHorarioSucursalException
	 * @throws BusinessException
	 */
	@Test
	public void getDatosDelSolicitanteConErrorMismoNup() throws DAOException, SinAccesoALaInformacionException, OperacionFueraHorarioSucursalException, BusinessException {
		
		DatosSolicitanteEntity out = new DatosSolicitanteEntity();
		out.setFechaNacimiento("01031991");
		out.setNup("999");
		
		Respuesta<Object> respuestaDatosDelSolicitanteWarning = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaDatosDelSolicitanteWarning.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaDatosDelSolicitanteWarning.setItemMensajeRespuesta(itemMensajeRespuestaList);
        
		Mockito.when(datosSolicitanteDAO.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioEntity.class), Matchers.any(Cliente.class))).thenReturn(out);
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
    	Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaDatosDelSolicitanteWarning);
		
        DatosSolicitanteCriterioDTO datosSolicitante = new DatosSolicitanteCriterioDTO();
        datosSolicitante.setAdicional(true);
        datosSolicitante.setDoc("doc");
        datosSolicitante.setDocTipo("docTipo");
        datosSolicitante.setFechaNacimiento("01/03/1991");
        datosSolicitante.setNup("nup");

        Cliente cliente = new Cliente();
        cliente.setNup("999");
		Respuesta<DatosSolicitanteDTO> respuesta = datosSolicitanteBO.getDatosDelSolicitante(datosSolicitante , cliente);
        
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
    }
	
	/**
	 * @throws DAOException
	 * @throws SinAccesoALaInformacionException
	 * @throws OperacionFueraHorarioSucursalException
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void getDatosDelSolicitanteOK() throws DAOException, SinAccesoALaInformacionException, OperacionFueraHorarioSucursalException, BusinessException {
		
		DatosSolicitanteEntity out = new DatosSolicitanteEntity();
		out.setFechaNacimiento("01031991");
		out.setNup("999");
		out.setDocTipo("N");
		out.setAceptoTerminosCondiciones(true);
		out.setApellido("Fernandez");
		out.setCalle("calle");
		out.setCalleNro("123");
		out.setCp("1660");
		out.setDoc("13659659");
		out.setEstadoCivil("s");
		out.setPaisNacimiento("españa");
		out.setLocalidad("Munro");
		
        DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();;
        Respuesta<DatosSolicitanteDTO> respuestaDatosSolicitanteDTO = new Respuesta<DatosSolicitanteDTO>();
        respuestaDatosSolicitanteDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaDatosSolicitanteDTO.setRespuesta(datosSolicitanteDTO );
        
		Mockito.when(datosSolicitanteDAO.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioEntity.class), Matchers.any(Cliente.class))).thenReturn(out);
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),Matchers.any(DatosSolicitanteDTO.class))).thenReturn(respuestaDatosSolicitanteDTO);
    	Mockito.when(datosSelectoresDAO.obtenerOpcionDescripcion(Matchers.anyString(), Matchers.anyString())).thenReturn("Argentino2");
    	
    	
        DatosSolicitanteCriterioDTO datosSolicitante = new DatosSolicitanteCriterioDTO();
        datosSolicitante.setAdicional(true);
        datosSolicitante.setDoc("doc");
        datosSolicitante.setDocTipo("docTipo");
        datosSolicitante.setFechaNacimiento("01/03/1991");
        datosSolicitante.setNup("nup");

        Cliente cliente = new Cliente();
        cliente.setNup("123");
		Respuesta<DatosSolicitanteDTO> respuesta = datosSolicitanteBO.getDatosDelSolicitante(datosSolicitante , cliente);
        
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
	
	/**
	 * @throws DAOException
	 * @throws SinAccesoALaInformacionException
	 * @throws OperacionFueraHorarioSucursalException
	 * @throws BusinessException
	 */
	@Test
	public void getDatosDelSolicitanteErrorConException() throws DAOException, SinAccesoALaInformacionException, OperacionFueraHorarioSucursalException, BusinessException {
		
		DatosSolicitanteEntity out = new DatosSolicitanteEntity();
		out.setFechaNacimiento("00000000");
		
		Respuesta<Object> respuestaDatosDelSolicitanteWarning = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Warning Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_WARNING_HARDCODEADO_EN_EL_TEST");
        respuestaDatosDelSolicitanteWarning.setEstadoRespuesta(EstadoRespuesta.WARNING);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaDatosDelSolicitanteWarning.setItemMensajeRespuesta(itemMensajeRespuestaList);
        
		Mockito.when(datosSolicitanteDAO.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioEntity.class), Matchers.any(Cliente.class))).thenThrow(new DAOException());
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
    	Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaDatosDelSolicitanteWarning);
		
        DatosSolicitanteCriterioDTO datosSolicitante = new DatosSolicitanteCriterioDTO();
        datosSolicitante.setAdicional(true);
        datosSolicitante.setDoc("doc");
        datosSolicitante.setDocTipo("docTipo");
        datosSolicitante.setFechaNacimiento("01/03/1991");
        datosSolicitante.setNup("nup");

        Cliente cliente = new Cliente();
		Respuesta<DatosSolicitanteDTO> respuesta = datosSolicitanteBO.getDatosDelSolicitante(datosSolicitante , cliente);
        
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_WARNING_HARDCODEADO_EN_EL_TEST");
    }
	
	@SuppressWarnings("unchecked")
	@Test
	public void getDatosDelSolicitanteOKAdicionalFalse() throws DAOException, SinAccesoALaInformacionException, OperacionFueraHorarioSucursalException, BusinessException {
		
		DatosSolicitanteEntity out = new DatosSolicitanteEntity();
		out.setFechaNacimiento("01031991");
		out.setNup("999");
		out.setDocTipo("N");
		out.setAceptoTerminosCondiciones(true);
		out.setApellido("Fernandez");
		out.setCalle("calle");
		out.setCalleNro("123");
		out.setCp("1660");
		out.setDoc("13659659");
		out.setEstadoCivil("s");
		out.setPaisNacimiento("españa");
		out.setLocalidad("Munro");
		
        DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();;
        Respuesta<DatosSolicitanteDTO> respuestaDatosSolicitanteDTO = new Respuesta<DatosSolicitanteDTO>();
        respuestaDatosSolicitanteDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaDatosSolicitanteDTO.setRespuesta(datosSolicitanteDTO );
        
		Mockito.when(datosSolicitanteDAO.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioEntity.class), Matchers.any(Cliente.class))).thenReturn(out);
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),Matchers.any(DatosSolicitanteDTO.class))).thenReturn(respuestaDatosSolicitanteDTO);
    	Mockito.when(datosSelectoresDAO.obtenerOpcionDescripcion(Matchers.anyString(), Matchers.anyString())).thenReturn("Argentino2");
    	
    	
        DatosSolicitanteCriterioDTO datosSolicitante = new DatosSolicitanteCriterioDTO();
        datosSolicitante.setAdicional(false);
        datosSolicitante.setDoc("doc");
        datosSolicitante.setDocTipo("docTipo");
        datosSolicitante.setFechaNacimiento("01/03/1991");
        datosSolicitante.setNup("nup");

        Cliente cliente = new Cliente();
        cliente.setNup("123");
		Respuesta<DatosSolicitanteDTO> respuesta = datosSolicitanteBO.getDatosDelSolicitante(datosSolicitante , cliente);
        
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
	
	/**
	 * @throws DAOException
	 * @throws SinAccesoALaInformacionException
	 * @throws OperacionFueraHorarioSucursalException
	 * @throws BusinessException
	 */
	@Test
	public void getDatosDelSolicitanteNull() throws DAOException, SinAccesoALaInformacionException, OperacionFueraHorarioSucursalException, BusinessException {
		
		Mockito.when(datosSolicitanteDAO.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioEntity.class), Matchers.any(Cliente.class))).thenReturn(null);
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
		
        DatosSolicitanteCriterioDTO datosSolicitante = new DatosSolicitanteCriterioDTO();
        datosSolicitante.setAdicional(true);
        datosSolicitante.setDoc("doc");
        datosSolicitante.setDocTipo("docTipo");
        datosSolicitante.setFechaNacimiento("01/03/1991");
        datosSolicitante.setNup("nup");

        Cliente cliente = new Cliente();
		Respuesta<DatosSolicitanteDTO> respuesta = datosSolicitanteBO.getDatosDelSolicitante(datosSolicitante , cliente);
        
		Assert.assertNull(respuesta);
    }
	
	/**
	 * @throws OperacionFueraHorarioSucursalException
	 * @throws DAOException
	 * @throws NoSePuedeRealizarLaOperacionException
	 */
	@Test
	public void getDatosTarjetaMonederoWarning() throws OperacionFueraHorarioSucursalException, DAOException, NoSePuedeRealizarLaOperacionException  {
		
		Respuesta<Object> respuestaDatosTarjetaMonederoWarning = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Warning Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_WARNING_HARDCODEADO_EN_EL_TEST");
        respuestaDatosTarjetaMonederoWarning.setEstadoRespuesta(EstadoRespuesta.WARNING);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaDatosTarjetaMonederoWarning.setItemMensajeRespuesta(itemMensajeRespuestaList);
        
		Mockito.when(datosSolicitanteDAO.getDatosTarjetaMonedero(Matchers.any(Cliente.class), Matchers.any(String.class))).thenReturn(null);
		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaDatosTarjetaMonederoWarning);

        Cliente cliente = new Cliente();
        Respuesta<List<MonederoDTO>> respuesta = datosSolicitanteBO.getDatosTarjetaMonedero(cliente , "tipoDeConsulta");
        
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_WARNING_HARDCODEADO_EN_EL_TEST");

    }
	
	/**
	 * @throws OperacionFueraHorarioSucursalException
	 * @throws DAOException
	 * @throws NoSePuedeRealizarLaOperacionException
	 */
	@Test
	public void getDatosTarjetaMonederoOK() throws OperacionFueraHorarioSucursalException, DAOException, NoSePuedeRealizarLaOperacionException  {
		
		List<MonederoDTO> out = new ArrayList<MonederoDTO>();
		MonederoDTO monederoDTO = new MonederoDTO();
		monederoDTO.setNombreEmbozado("nombreEmbozado");
		out.add(monederoDTO);
		
		Mockito.when(datosSolicitanteDAO.getDatosTarjetaMonedero(Matchers.any(Cliente.class), Matchers.any(String.class))).thenReturn(out);

        Cliente cliente = new Cliente();
        Respuesta<List<MonederoDTO>> respuesta = datosSolicitanteBO.getDatosTarjetaMonedero(cliente , "tipoDeConsulta");
        
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		Assert.assertEquals("nombreEmbozado", respuesta.getRespuesta().get(0).getNombreEmbozado());
    }

	/**
	 * @throws NoSePuedeRealizarLaOperacionException 
	 * @throws DAOException 
	 * @throws OperacionFueraHorarioSucursalException 
	 * 
	 */
	@Test
	public void getDatosTarjetaMonederoErrorConOperacionFueraHorarioSucursalException() throws OperacionFueraHorarioSucursalException, DAOException, NoSePuedeRealizarLaOperacionException {
		
		
		Respuesta<Object> respuestaatosTarjetaMonederoError = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaatosTarjetaMonederoError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaatosTarjetaMonederoError.setItemMensajeRespuesta(itemMensajeRespuestaList);
		
		Mockito.when(datosSolicitanteDAO.getDatosTarjetaMonedero(Matchers.any(Cliente.class), Matchers.any(String.class))).thenThrow(new OperacionFueraHorarioSucursalException());
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaatosTarjetaMonederoError);

        Cliente cliente = new Cliente();
        Respuesta<List<MonederoDTO>> respuesta = datosSolicitanteBO.getDatosTarjetaMonedero(cliente , "tipoDeConsulta");
        
        Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");		
    }
	
	/**
	 * @throws OperacionFueraHorarioSucursalException
	 * @throws DAOException
	 * @throws NoSePuedeRealizarLaOperacionException
	 */
	@Test
	public void getDatosTarjetaMonederoErrorConDaoException() throws OperacionFueraHorarioSucursalException, DAOException, NoSePuedeRealizarLaOperacionException {
		
		
		Respuesta<Object> respuestaatosTarjetaMonederoError = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaatosTarjetaMonederoError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaatosTarjetaMonederoError.setItemMensajeRespuesta(itemMensajeRespuestaList);
		
		DAOException e = new DAOException("TIME_OUT");
		Mockito.when(datosSolicitanteDAO.getDatosTarjetaMonedero(Matchers.any(Cliente.class), Matchers.any(String.class))).thenThrow(e);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.anyString(), Matchers.anyString())).thenReturn(respuestaatosTarjetaMonederoError);

        Cliente cliente = new Cliente();
        Respuesta<List<MonederoDTO>> respuesta = datosSolicitanteBO.getDatosTarjetaMonedero(cliente , "tipoDeConsulta");
        
        Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");		
    }
	
	/**
	 * @throws DAOException
	 */
	@Test
	public void obtenerTagsErrorConDaoException() throws DAOException{
		
		
		Respuesta<Object> respuestaatosTarjetaMonederoError = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaatosTarjetaMonederoError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaatosTarjetaMonederoError.setItemMensajeRespuesta(itemMensajeRespuestaList);
		
		DAOException e = new DAOException();
		Mockito.when(monederoWebDAO.obtenerTags(Matchers.any(TagsDTO.class))).thenThrow(e);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaatosTarjetaMonederoError);

        Respuesta<List<TagEntity>> respuesta = datosSolicitanteBO.obtenerTags(new TagsDTO());
        
        Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");		
    }
	
	/**
	 * @throws DAOException
	 */
	@Test
	public void obtenerTagsErroOK() throws DAOException{
		
		List<TagEntity> out = new ArrayList<TagEntity>();
		TagEntity tagEntity = new TagEntity();
		tagEntity.setApellido("apellido");
		out.add(tagEntity);
		
		Mockito.when(monederoWebDAO.obtenerTags(Matchers.any(TagsDTO.class))).thenReturn(out);

        Respuesta<List<TagEntity>> respuesta = datosSolicitanteBO.obtenerTags(new TagsDTO());
        
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		Assert.assertEquals("apellido", respuesta.getRespuesta().get(0).getApellido());		
    }

	/**
	 * @throws DAOException
	 */
	@Test
	public void obtenerTransaccionesTagsErrorConDaoException() throws DAOException{
		
		
		Respuesta<Object> respuestaatosTarjetaMonederoError = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Warning Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_WARNING_HARDCODEADO_EN_EL_TEST");
        respuestaatosTarjetaMonederoError.setEstadoRespuesta(EstadoRespuesta.WARNING);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaatosTarjetaMonederoError.setItemMensajeRespuesta(itemMensajeRespuestaList);
		
		DAOException e = new DAOException();
		Mockito.when(monederoWebDAO.obtenerTransaccionesTag(Matchers.any(TagsTransacDTO.class))).thenThrow(e);
		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaatosTarjetaMonederoError);

		Respuesta<List<TransaccionEntity>> respuesta = datosSolicitanteBO.obtenerTransaccionesTags(new TagsTransacDTO());
        
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_WARNING_HARDCODEADO_EN_EL_TEST");		
    }
	
	/**
	 * @throws DAOException
	 */
	@Test
	public void obtenerTransaccionesTagsOK() throws DAOException{
		
		List<TransaccionEntity> out = new ArrayList<TransaccionEntity>();
		TransaccionEntity transaccionEntity = new TransaccionEntity();
		transaccionEntity.setEstado("estado");
		out.add(transaccionEntity);
		
		Mockito.when(monederoWebDAO.obtenerTransaccionesTag(Matchers.any(TagsTransacDTO.class))).thenReturn(out);

        Respuesta<List<TransaccionEntity>> respuesta = datosSolicitanteBO.obtenerTransaccionesTags(new TagsTransacDTO());
        
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		Assert.assertEquals("estado", respuesta.getRespuesta().get(0).getEstado());		
    }
	
	@SuppressWarnings("unchecked")
	@Test
	public void getDatosPadronBOOK() throws DAOException{
		
		
		List<ConsultaPadronCuitOutEntity> out = new ArrayList<ConsultaPadronCuitOutEntity>();
		ConsultaPadronCuitOutEntity consultaPadronCuitOutEntity = new ConsultaPadronCuitOutEntity();
		consultaPadronCuitOutEntity.setAbaFechaNacimiento("01/06/1880");
		out.add(consultaPadronCuitOutEntity);

		Respuesta<ConsultaPadronCuitOutEntity> respuestaDatosPadronBO = new Respuesta<ConsultaPadronCuitOutEntity>();
		respuestaDatosPadronBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Mockito.when(consultaPadronOCuitDAO.consultaPadronRetornandoListaCoincidencias(Matchers.any(ConsultaPadronCuitInEntity.class))).thenReturn(out);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),Matchers.any(ConsultaPadronCuitOutEntity.class))).thenReturn(respuestaDatosPadronBO);
		

		DatosSolicitanteCriterioView datosSolicitante = new DatosSolicitanteCriterioView();
		datosSolicitante.setDocTipo("DNI");
		datosSolicitante.setDoc("35334983");
		datosSolicitante.setFechaNacimiento("01/06/1880");
        Cliente cliente = new Cliente();
		Respuesta<ConsultaPadronCuitOutEntity> respuesta = datosSolicitanteBO.getDatosPadronBO(datosSolicitante, cliente);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
	
	@SuppressWarnings("unchecked")
	@Test
	public void getDatosPadronBOOKTipoDocN() throws DAOException{
		
		
		List<ConsultaPadronCuitOutEntity> out = new ArrayList<ConsultaPadronCuitOutEntity>();
		ConsultaPadronCuitOutEntity consultaPadronCuitOutEntity = new ConsultaPadronCuitOutEntity();
		consultaPadronCuitOutEntity.setAbaFechaNacimiento("01/06/1880");
		out.add(consultaPadronCuitOutEntity);
		
		ConsultaPadronCuitOutEntity consultaPadronCuitOutEntity2 = new ConsultaPadronCuitOutEntity();
		consultaPadronCuitOutEntity2.setAbaFechaNacimiento("07/07/1890");
		out.add(consultaPadronCuitOutEntity2);

		Respuesta<ConsultaPadronCuitOutEntity> respuestaDatosPadronBO = new Respuesta<ConsultaPadronCuitOutEntity>();
		respuestaDatosPadronBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Mockito.when(consultaPadronOCuitDAO.consultaPadronRetornandoListaCoincidencias(Matchers.any(ConsultaPadronCuitInEntity.class))).thenReturn(out);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),Matchers.any(ConsultaPadronCuitOutEntity.class))).thenReturn(respuestaDatosPadronBO);
		

		DatosSolicitanteCriterioView datosSolicitante = new DatosSolicitanteCriterioView();
		datosSolicitante.setDocTipo("N");
		datosSolicitante.setDoc("35334983");
		datosSolicitante.setFechaNacimiento("01/06/1880");
        Cliente cliente = new Cliente();
		Respuesta<ConsultaPadronCuitOutEntity> respuesta = datosSolicitanteBO.getDatosPadronBO(datosSolicitante, cliente);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
	
	@SuppressWarnings("unchecked")
	@Test
	public void getDatosPadronBOOKSinDocTipo() throws DAOException{
		
		
		List<ConsultaPadronCuitOutEntity> out = new ArrayList<ConsultaPadronCuitOutEntity>();
		ConsultaPadronCuitOutEntity consultaPadronCuitOutEntity = new ConsultaPadronCuitOutEntity();
		consultaPadronCuitOutEntity.setAbaFechaNacimiento("01/06/1880");
		out.add(consultaPadronCuitOutEntity);

		Respuesta<ConsultaPadronCuitOutEntity> respuestaDatosPadronBO = new Respuesta<ConsultaPadronCuitOutEntity>();
		respuestaDatosPadronBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Mockito.when(consultaPadronOCuitDAO.consultaPadronRetornandoListaCoincidencias(Matchers.any(ConsultaPadronCuitInEntity.class))).thenReturn(out);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),Matchers.any(ConsultaPadronCuitOutEntity.class))).thenReturn(respuestaDatosPadronBO);
		

		DatosSolicitanteCriterioView datosSolicitante = new DatosSolicitanteCriterioView();
		datosSolicitante.setDocTipo("");
		datosSolicitante.setDoc("35334983");
		datosSolicitante.setFechaNacimiento("01/06/1880");
        Cliente cliente = new Cliente();
		Respuesta<ConsultaPadronCuitOutEntity> respuesta = datosSolicitanteBO.getDatosPadronBO(datosSolicitante, cliente);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
	
	@Test
	public void getDatosPadronBOWarning() throws DAOException{
		
		
		List<ConsultaPadronCuitOutEntity> out = new ArrayList<ConsultaPadronCuitOutEntity>();

		Respuesta<Object> respuestaDatoPadronBOWarning = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Warning Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_WARNING_HARDCODEADO_EN_EL_TEST");
        respuestaDatoPadronBOWarning.setEstadoRespuesta(EstadoRespuesta.WARNING);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaDatoPadronBOWarning.setItemMensajeRespuesta(itemMensajeRespuestaList);
        
		Mockito.when(consultaPadronOCuitDAO.consultaPadronRetornandoListaCoincidencias(Matchers.any(ConsultaPadronCuitInEntity.class))).thenReturn(out);
		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaDatoPadronBOWarning);

		DatosSolicitanteCriterioView datosSolicitante = new DatosSolicitanteCriterioView();
		datosSolicitante.setDocTipo("DNI");
		datosSolicitante.setDoc("35334983");
		datosSolicitante.setFechaNacimiento("01/06/1880");
        Cliente cliente = new Cliente();
		Respuesta<ConsultaPadronCuitOutEntity> respuesta = datosSolicitanteBO.getDatosPadronBO(datosSolicitante, cliente);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_WARNING_HARDCODEADO_EN_EL_TEST");
    }
	
	@Test
	public void getDatosPadronBOWarningSinDocTipo() throws DAOException{
		
		
		List<ConsultaPadronCuitOutEntity> out = new ArrayList<ConsultaPadronCuitOutEntity>();

		Respuesta<Object> respuestaDatoPadronBOWarning = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Warning Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_WARNING_HARDCODEADO_EN_EL_TEST");
        respuestaDatoPadronBOWarning.setEstadoRespuesta(EstadoRespuesta.WARNING);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaDatoPadronBOWarning.setItemMensajeRespuesta(itemMensajeRespuestaList);
        
		Mockito.when(consultaPadronOCuitDAO.consultaPadronRetornandoListaCoincidencias(Matchers.any(ConsultaPadronCuitInEntity.class))).thenReturn(out);
		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaDatoPadronBOWarning);

		DatosSolicitanteCriterioView datosSolicitante = new DatosSolicitanteCriterioView();
		datosSolicitante.setDocTipo("");
		datosSolicitante.setDoc("35334983");
		datosSolicitante.setFechaNacimiento("01/06/1880");
        Cliente cliente = new Cliente();
		Respuesta<ConsultaPadronCuitOutEntity> respuesta = datosSolicitanteBO.getDatosPadronBO(datosSolicitante, cliente);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_WARNING_HARDCODEADO_EN_EL_TEST");
    }
}
