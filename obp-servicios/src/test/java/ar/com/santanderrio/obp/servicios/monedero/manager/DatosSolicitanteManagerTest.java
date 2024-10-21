package ar.com.santanderrio.obp.servicios.monedero.manager;


import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.combos.service.DatosSelectoresService;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitOutEntity;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.monedero.bo.DatosSolicitanteBO;
import ar.com.santanderrio.obp.servicios.monedero.dao.ConsultaUnidadControlDAO;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteCriterioDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsTransacDTO;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlInEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlOutEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.ObtenerTrxTagMedioDeRecargaEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TagEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TransaccionEntity;
import ar.com.santanderrio.obp.servicios.monedero.service.DatosSolicitanteService;
import ar.com.santanderrio.obp.servicios.monedero.web.manager.impl.DatosSolicitanteManagerImpl;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosAltaCanalesAutomaticosView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosMonederoConMovimientosYSaldoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteCriterioView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteResponseView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitudTagAdicionalView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.manager.TarjetaCreditoAdicionalManager;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.EstadoCivilView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.PaisDeNacimientoView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.SexoView;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.MonederoDTO;

/**
 * The Class DatosSolicitanteManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class DatosSolicitanteManagerTest {

    /** Datos Solicitante Manager. */
    @InjectMocks
    @Spy
    private DatosSolicitanteManagerImpl datosSolicitanteManager = new DatosSolicitanteManagerImpl();
    
    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;
    
    /** The Datos Solicitante BO. */
    @Mock
    private DatosSolicitanteBO datosSolicitanteBO;
    
    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    /** The datos Solicitante Service. */
    @Mock
    private DatosSolicitanteService datosSolicitanteService;
    
    /** The datos Selectores Service. */
    @Mock
    private DatosSelectoresService datosSelectoresService;
    
    /** The Consulta Unidad Control DAO. */
    @Mock
    private ConsultaUnidadControlDAO consultaUnidadControlDAO;
    
    /** The sesion Cliente. */
    @Mock
    private SesionCliente sesionCliente;
    
    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;
    
    /** The tarjeta Credito Adicional Manager. */
    @Mock
    private TarjetaCreditoAdicionalManager tarjetaCreditoAdicionalManager;
    
    @Before
    public void init()  {
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Un mensaje");
        MockitoAnnotations.initMocks(this);
        when(mensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenReturn(mensaje);
    }
    
    /**
     * @throws IllegalAccessException
     * @throws BusinessException 
     */
    @Test
    @Ignore
    public void getDatosDelSolicitanteFueraHorario() throws IllegalAccessException, BusinessException {
        
        FieldUtils.writeField(datosSolicitanteManager, "horaHastaALTA", "8:00", true);
        FieldUtils.writeField(datosSolicitanteManager, "horaDesdeALTA", "8:00", true);
        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        
        Respuesta<Object> respuestaDatosSolicitanteResponseViewError= new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaDatosSolicitanteResponseViewError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaDatosSolicitanteResponseViewError.setItemMensajeRespuesta(itemMensajeRespuestaList);
        
        Cliente cliente = new Cliente();
        cliente.setNup("123456");
        List<Cuenta> cuentasList = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("alias");
        cuenta.setSubproductoAltair("MONE");
        cuenta.setCodigoTitularidad("TI");
        cuenta.setTipoCuenta("07");
        cuenta.setEstadoTarjetaCredito("20");
        cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta.setGrupoAfinidad("33333");

        cuentasList.add(cuenta );
        cliente.setCuentas(cuentasList );

        Respuesta<DatosSolicitanteDTO> out = new Respuesta<DatosSolicitanteDTO>();
        out.setEstadoRespuesta(EstadoRespuesta.OK);
        DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();
        datosSolicitanteDTO.setNup("123456");
        datosSolicitanteDTO.setSexo("H");
        datosSolicitanteDTO.setPaisNacimiento("Argentina");
        datosSolicitanteDTO.setEstadoCivil("Soltero");
        datosSolicitanteDTO.setNacionalidad("Argentina");
        out.setRespuesta(datosSolicitanteDTO);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaDatosSolicitanteResponseViewError);
        Mockito.when(datosSolicitanteService.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioDTO.class), Matchers.any(Cliente.class))).thenReturn(out);

        
        DatosSolicitanteCriterioView datos = new DatosSolicitanteCriterioView();
        Respuesta<DatosSolicitanteResponseView> respuesta = datosSolicitanteManager.getDatosDelSolicitante(datos);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
    }
    
    /**
     * @throws IllegalAccessException
     * @throws BusinessException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getDatosDelSolicitanteOKAdicionalTrue() throws IllegalAccessException, BusinessException {
        
        FieldUtils.writeField(datosSolicitanteManager, "horaHastaALTA", "22:00", true);
        FieldUtils.writeField(datosSolicitanteManager, "horaDesdeALTA", "8:00", true);
        
        Respuesta<DatosSolicitanteDTO> out = new Respuesta<DatosSolicitanteDTO>();
        out.setEstadoRespuesta(EstadoRespuesta.OK);
        DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();
        datosSolicitanteDTO.setNup("123456");
        datosSolicitanteDTO.setSexo("H");
        datosSolicitanteDTO.setPaisNacimiento("Argentina");
        datosSolicitanteDTO.setEstadoCivil("Soltero");
        datosSolicitanteDTO.setNacionalidad("Argentina");
        out.setRespuesta(datosSolicitanteDTO);

        Cliente cliente = new Cliente();
        cliente.setNup("123456");
        List<Cuenta> cuentasList = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("alias");
        cuenta.setSubproductoAltair("MONE");
        cuenta.setCodigoTitularidad("TI");
        cuenta.setProductoAltair("43");
        cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta.setGrupoAfinidad("33333");

        cuentasList.add(cuenta );
        cliente.setCuentas(cuentasList );
        
        List<Opcion> optionList = new ArrayList<Opcion>();
        Opcion option = new Opcion();
        option.setId("1");
        option.setOpcion("400");
        optionList.add(option );
        
        Respuesta<DatosSolicitanteResponseView> respuestaDatosSolicitanteResponseView = new Respuesta<DatosSolicitanteResponseView>();
        respuestaDatosSolicitanteResponseView.setEstadoRespuesta(EstadoRespuesta.OK);
        DatosSolicitanteResponseView datosSolicitanteResponseView = new DatosSolicitanteResponseView();
        DatosSolicitanteView datosSolicitanteView = new DatosSolicitanteView();
        datosSolicitanteResponseView.setDatosSolicitanteView(datosSolicitanteView );
        respuestaDatosSolicitanteResponseView.setRespuesta(datosSolicitanteResponseView);
        
        Mockito.when(datosSolicitanteService.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioDTO.class), Matchers.any(Cliente.class))).thenReturn(out);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(datosSelectoresService.obtenerImportesARecargar()).thenReturn(optionList);
        Mockito.when(datosSelectoresService.obtenerLimitesDeRecargaMensual()).thenReturn(optionList);
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),Matchers.any(DatosSolicitanteResponseView.class))).thenReturn(respuestaDatosSolicitanteResponseView);
        
        DatosSolicitanteCriterioView datos = new DatosSolicitanteCriterioView();
        datos.setAdicional(true);
        Mockito.doReturn(StringUtils.EMPTY).when(datosSolicitanteManager).horarioBancario();        
        Respuesta<DatosSolicitanteResponseView> respuesta = datosSolicitanteManager.getDatosDelSolicitante(datos);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void getDatosDelSolicitanteOKAdicionalfalse() throws IllegalAccessException, BusinessException {
        
        FieldUtils.writeField(datosSolicitanteManager, "horaHastaALTA", "22:00", true);
        FieldUtils.writeField(datosSolicitanteManager, "horaDesdeALTA", "8:00", true);
        
        Respuesta<DatosSolicitanteDTO> out = new Respuesta<DatosSolicitanteDTO>();
        out.setEstadoRespuesta(EstadoRespuesta.OK);
        DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();
        datosSolicitanteDTO.setNup("123456");
        datosSolicitanteDTO.setSexo("M");
        datosSolicitanteDTO.setPaisNacimiento("Argentina");
        datosSolicitanteDTO.setEstadoCivil("Soltero");
        datosSolicitanteDTO.setNacionalidad("Argentina");
        out.setRespuesta(datosSolicitanteDTO);

        Cliente cliente = new Cliente();
        cliente.setNup("123456");
        List<Cuenta> cuentasList = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("alias");
        cuenta.setSubproductoAltair("MONE");
        cuenta.setCodigoTitularidad("TI");
        cuenta.setProductoAltair("43");
        cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta.setGrupoAfinidad("33333");

        cuentasList.add(cuenta );
        cliente.setCuentas(cuentasList );
        
        List<Opcion> optionList = new ArrayList<Opcion>();
        Opcion option = new Opcion();
        option.setId("1");
        option.setOpcion("400");
        optionList.add(option );
        
        Respuesta<DatosSolicitanteResponseView> respuestaDatosSolicitanteResponseView = new Respuesta<DatosSolicitanteResponseView>();
        respuestaDatosSolicitanteResponseView.setEstadoRespuesta(EstadoRespuesta.OK);
        DatosSolicitanteResponseView datosSolicitanteResponseView = new DatosSolicitanteResponseView();
        DatosSolicitanteView datosSolicitanteView = new DatosSolicitanteView();
        datosSolicitanteResponseView.setDatosSolicitanteView(datosSolicitanteView );
        respuestaDatosSolicitanteResponseView.setRespuesta(datosSolicitanteResponseView);
        
        Mockito.when(datosSolicitanteService.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioDTO.class), Matchers.any(Cliente.class))).thenReturn(out);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(datosSelectoresService.obtenerImportesARecargar()).thenReturn(optionList);
        Mockito.when(datosSelectoresService.obtenerLimitesDeRecargaMensual()).thenReturn(optionList);
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),Matchers.any(DatosSolicitanteResponseView.class))).thenReturn(respuestaDatosSolicitanteResponseView);
        Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        Mockito.doReturn(StringUtils.EMPTY).when(datosSolicitanteManager).horarioBancario();        
        
        DatosSolicitanteCriterioView datos = new DatosSolicitanteCriterioView();
        datos.setAdicional(false);
        Respuesta<DatosSolicitanteResponseView> respuesta = datosSolicitanteManager.getDatosDelSolicitante(datos);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void getDatosDelSolicitanteOKBuscarEnPadron() throws IllegalAccessException, BusinessException, DAOException {
        
        FieldUtils.writeField(datosSolicitanteManager, "horaHastaALTA", "22:00", true);
        FieldUtils.writeField(datosSolicitanteManager, "horaDesdeALTA", "8:00", true);
        
        Cliente cliente = new Cliente();
        cliente.setNup("123456");
        List<Cuenta> cuentasList = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("alias");
        cuenta.setSubproductoAltair("MONE");
        cuenta.setCodigoTitularidad("TI");
        cuenta.setProductoAltair("43");
        cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta.setGrupoAfinidad("33333");

        cuentasList.add(cuenta );
        cliente.setCuentas(cuentasList );
        
        List<Opcion> optionList = new ArrayList<Opcion>();
        Opcion option = new Opcion();
        option.setId("1");
        option.setOpcion("400");
        optionList.add(option );
        
        Respuesta<DatosSolicitanteResponseView> respuestaDatosSolicitanteResponseView = new Respuesta<DatosSolicitanteResponseView>();
        respuestaDatosSolicitanteResponseView.setEstadoRespuesta(EstadoRespuesta.OK);
        DatosSolicitanteResponseView datosSolicitanteResponseView = new DatosSolicitanteResponseView();
        DatosSolicitanteView datosSolicitanteView = new DatosSolicitanteView();
        datosSolicitanteResponseView.setDatosSolicitanteView(datosSolicitanteView );
        respuestaDatosSolicitanteResponseView.setRespuesta(datosSolicitanteResponseView);

        Respuesta<ConsultaPadronCuitOutEntity> out = new Respuesta<ConsultaPadronCuitOutEntity>();
        out.setEstadoRespuesta(EstadoRespuesta.OK);
        ConsultaPadronCuitOutEntity consultaPadronCuitOutEntity = new ConsultaPadronCuitOutEntity();
        consultaPadronCuitOutEntity.setAbaNroCuit("123456789");
        consultaPadronCuitOutEntity.setAbaTipoDocumento("N");
        consultaPadronCuitOutEntity.setAbaNroDocumento("35969686");
        consultaPadronCuitOutEntity.setAbaNombre("Jose");
        consultaPadronCuitOutEntity.setAbaApellido("Perez");
        consultaPadronCuitOutEntity.setAbaFechaNacimiento("10/12/1980");
        consultaPadronCuitOutEntity.setAbaSexo("H");
        out.setRespuesta(consultaPadronCuitOutEntity );
        
        List<PaisDeNacimientoView> optionList2 = new ArrayList<PaisDeNacimientoView>();
        List<SexoView> optionList3 = new ArrayList<SexoView>();
        List<EstadoCivilView> optionList5 = new ArrayList<EstadoCivilView>();
        
        Mockito.when(datosSolicitanteService.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioDTO.class), Matchers.any(Cliente.class))).thenReturn(null);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),Matchers.any(DatosSolicitanteResponseView.class))).thenReturn(respuestaDatosSolicitanteResponseView);
        Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        Mockito.when(datosSelectoresService.obtenerImportesARecargar()).thenReturn(optionList);
        Mockito.when(datosSelectoresService.obtenerLimitesDeRecargaMensual()).thenReturn(optionList);
        Mockito.when(datosSolicitanteBO.getDatosPadronBO(Matchers.any(DatosSolicitanteCriterioView.class), Matchers.any(Cliente.class))).thenReturn(out);
        Mockito.when(datosSelectoresService.obtenerPaisesDeNacimiento()).thenReturn(optionList);
        Mockito.when(datosSelectoresService.obtenerSexo()).thenReturn(optionList);
        Mockito.when(datosSelectoresService.obtenerEstadoCivil()).thenReturn(optionList);
        Mockito.when(datosSelectoresService.obtenerNacionalidad()).thenReturn(optionList);
        Mockito.when(tarjetaCreditoAdicionalManager.dtoToViewPaises(Matchers.any(List.class))).thenReturn(optionList2);
        Mockito.when(tarjetaCreditoAdicionalManager.dtoToViewSexo(Matchers.any(List.class))).thenReturn(optionList3);
        Mockito.when(tarjetaCreditoAdicionalManager.dtoToViewEstadoCivil(Matchers.any(List.class))).thenReturn(optionList5);
        Mockito.doReturn(StringUtils.EMPTY).when(datosSolicitanteManager).horarioBancario();        

        DatosSolicitanteCriterioView datos = new DatosSolicitanteCriterioView();
        datos.setAdicional(true);
        Respuesta<DatosSolicitanteResponseView> respuesta = datosSolicitanteManager.getDatosDelSolicitante(datos);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    @Test
    public void getDatosDelSolicitanteAdicionalTrueConBusinessException() throws IllegalAccessException, BusinessException {
        
        FieldUtils.writeField(datosSolicitanteManager, "horaHastaALTA", "22:00", true);
        FieldUtils.writeField(datosSolicitanteManager, "horaDesdeALTA", "8:00", true);        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        
        Cliente cliente = new Cliente();
    	List<Cuenta> cuentasList = new ArrayList<Cuenta>();
    	Cuenta cuenta = new Cuenta();
    	cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
    	cuenta.setGrupoAfinidad("000030");
    	cuenta.setNroSucursal("1234");
		cuentasList.add(cuenta);
		cliente.setCuentas(cuentasList);
        
        Respuesta<Object> respuestaDatosSolicitanteResponseViewError= new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaDatosSolicitanteResponseViewError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaDatosSolicitanteResponseViewError.setItemMensajeRespuesta(itemMensajeRespuestaList);
        
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(datosSolicitanteService.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioDTO.class), Matchers.any(Cliente.class))).thenThrow(new BusinessException());
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaDatosSolicitanteResponseViewError);
        Mockito.doReturn(StringUtils.EMPTY).when(datosSolicitanteManager).horarioBancario();        
        
        DatosSolicitanteCriterioView datos = new DatosSolicitanteCriterioView();
        datos.setAdicional(true);
        Respuesta<DatosSolicitanteResponseView> respuesta = datosSolicitanteManager.getDatosDelSolicitante(datos);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
    }

    @Test
    public void getDatosDelSolicitanteAdicionalFalseConBusinessException() throws IllegalAccessException, BusinessException {
        
        FieldUtils.writeField(datosSolicitanteManager, "horaHastaALTA", "22:00", true);
        FieldUtils.writeField(datosSolicitanteManager, "horaDesdeALTA", "8:00", true);        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        
        Cliente cliente = new Cliente();
    	List<Cuenta> cuentasList = new ArrayList<Cuenta>();
    	Cuenta cuenta = new Cuenta();
    	cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
    	cuenta.setGrupoAfinidad("000030");
    	cuenta.setNroSucursal("1234");
		cuentasList.add(cuenta);
		cliente.setCuentas(cuentasList);
        
        Respuesta<Object> respuestaDatosSolicitanteResponseViewError= new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaDatosSolicitanteResponseViewError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaDatosSolicitanteResponseViewError.setItemMensajeRespuesta(itemMensajeRespuestaList);
        
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(datosSolicitanteService.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioDTO.class), Matchers.any(Cliente.class))).thenThrow(new BusinessException());
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaDatosSolicitanteResponseViewError);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.doReturn(StringUtils.EMPTY).when(datosSolicitanteManager).horarioBancario();        

        DatosSolicitanteCriterioView datos = new DatosSolicitanteCriterioView();
        datos.setAdicional(false);
        Respuesta<DatosSolicitanteResponseView> respuesta = datosSolicitanteManager.getDatosDelSolicitante(datos);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
    }
    
    @Test
    public void getDatosDelSolicitanteAdicionalFalseConDaoException() throws IllegalAccessException, DAOException, BusinessException {
        
        FieldUtils.writeField(datosSolicitanteManager, "horaHastaALTA", "22:00", true);
        FieldUtils.writeField(datosSolicitanteManager, "horaDesdeALTA", "8:00", true);        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        
        Cliente cliente = new Cliente();
    	List<Cuenta> cuentasList = new ArrayList<Cuenta>();
    	Cuenta cuenta = new Cuenta();
    	cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
    	cuenta.setGrupoAfinidad("000030");
    	cuenta.setNroSucursal("1234");
		cuentasList.add(cuenta);
		cliente.setCuentas(cuentasList);
        
        Respuesta<Object> respuestaDatosSolicitanteResponseViewError= new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaDatosSolicitanteResponseViewError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaDatosSolicitanteResponseViewError.setItemMensajeRespuesta(itemMensajeRespuestaList);

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(datosSolicitanteService.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioDTO.class), Matchers.any(Cliente.class))).thenReturn(null);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaDatosSolicitanteResponseViewError);
        Mockito.when(datosSolicitanteBO.getDatosPadronBO(Matchers.any(DatosSolicitanteCriterioView.class), Matchers.any(Cliente.class))).thenThrow(new DAOException());
        Mockito.doReturn(StringUtils.EMPTY).when(datosSolicitanteManager).horarioBancario();        
        
        DatosSolicitanteCriterioView datos = new DatosSolicitanteCriterioView();
        datos.setAdicional(true);
        Respuesta<DatosSolicitanteResponseView> respuesta = datosSolicitanteManager.getDatosDelSolicitante(datos);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
    }
    

    @SuppressWarnings("unchecked")
    @Test
    public void getDatosDelSolicitanteErrorAdicionalTrueCuentaNull() throws IllegalAccessException, BusinessException {
        
        FieldUtils.writeField(datosSolicitanteManager, "horaHastaALTA", "22:00", true);
        FieldUtils.writeField(datosSolicitanteManager, "horaDesdeALTA", "8:00", true);
        
        Respuesta<DatosSolicitanteDTO> out = new Respuesta<DatosSolicitanteDTO>();
        out.setEstadoRespuesta(EstadoRespuesta.OK);
        DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();
        datosSolicitanteDTO.setNup("123456");
        datosSolicitanteDTO.setSexo("M");
        datosSolicitanteDTO.setPaisNacimiento("Argentina");
        datosSolicitanteDTO.setEstadoCivil("Soltero");
        datosSolicitanteDTO.setNacionalidad("Argentina");
        out.setRespuesta(datosSolicitanteDTO);

        Cliente cliente = new Cliente();
        cliente.setNup("123456");
        List<Cuenta> cuentasList = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta.setGrupoAfinidad("33333");
        cuentasList.add(cuenta);
        cliente.setCuentas(cuentasList );
        
        List<Opcion> optionList = new ArrayList<Opcion>();
        Opcion option = new Opcion();
        option.setId("1");
        option.setOpcion("400");
        optionList.add(option );
        
        Respuesta<Object> respuestaDatosSolicitanteResponseViewErrorCuentaNull = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaDatosSolicitanteResponseViewErrorCuentaNull.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaDatosSolicitanteResponseViewErrorCuentaNull.setItemMensajeRespuesta(itemMensajeRespuestaList);
        
        Respuesta<DatosSolicitanteResponseView> respuestaDatosSolicitanteResponseView = new Respuesta<DatosSolicitanteResponseView>();
        respuestaDatosSolicitanteResponseView.setEstadoRespuesta(EstadoRespuesta.OK);
        DatosSolicitanteResponseView datosSolicitanteResponseView = new DatosSolicitanteResponseView();
        DatosSolicitanteView datosSolicitanteView = new DatosSolicitanteView();
        datosSolicitanteResponseView.setDatosSolicitanteView(datosSolicitanteView );
        respuestaDatosSolicitanteResponseView.setRespuesta(datosSolicitanteResponseView);

        Mockito.when(datosSolicitanteService.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioDTO.class), Matchers.any(Cliente.class))).thenReturn(out);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(datosSelectoresService.obtenerImportesARecargar()).thenReturn(optionList);
        Mockito.when(datosSelectoresService.obtenerLimitesDeRecargaMensual()).thenReturn(optionList);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaDatosSolicitanteResponseViewErrorCuentaNull);
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),Matchers.any(DatosSolicitanteResponseView.class))).thenReturn(respuestaDatosSolicitanteResponseView);
        Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        Mockito.doReturn(StringUtils.EMPTY).when(datosSolicitanteManager).horarioBancario();        

        DatosSolicitanteCriterioView datos = new DatosSolicitanteCriterioView();
        datos.setAdicional(true);
        Respuesta<DatosSolicitanteResponseView> respuesta = datosSolicitanteManager.getDatosDelSolicitante(datos);
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");

    }
    
    @Test
    public void consultaMonederoTagWarning(){

        Respuesta<List<MonederoDTO>> outCNSCTAMONE = new Respuesta<List<MonederoDTO>>();
        List<MonederoDTO> MonederoDTOList = new ArrayList<MonederoDTO>();
        outCNSCTAMONE.setRespuesta(MonederoDTOList );
        
        Respuesta<Object> respuestaconsultaMonederoTagWarning= new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Warning Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_WARNING_HARDCODEADO_EN_EL_TEST");
        respuestaconsultaMonederoTagWarning.setEstadoRespuesta(EstadoRespuesta.WARNING);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaconsultaMonederoTagWarning.setItemMensajeRespuesta(itemMensajeRespuestaList);
        
        Mockito.when(datosSolicitanteService.getDatosTarjetaMonedero(Matchers.any(Cliente.class), Matchers.any(String.class))).thenReturn(outCNSCTAMONE);
        Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaconsultaMonederoTagWarning);

        Cliente cliente = new Cliente();
        Respuesta<DatosMonederoConMovimientosYSaldoView> respuesta = datosSolicitanteManager.consultaMonederoTag(cliente );
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_WARNING_HARDCODEADO_EN_EL_TEST");
    }
    
    @Test
    public void consultaMonederoTagOK(){

        Respuesta<List<MonederoDTO>> outCNSCTAMONE = new Respuesta<List<MonederoDTO>>();
        List<MonederoDTO> MonederoDTOList = new ArrayList<MonederoDTO>();
        MonederoDTO monederoDTO = new MonederoDTO();
        monederoDTO.setNombreEmbozado("nombreEmbozado");
        monederoDTO.setNumeroTarjetaTag("556677");
        monederoDTO.setTipoTarjeta("1");
        monederoDTO.setEstadoTarjetaTag("31");
        MonederoDTOList.add(monederoDTO );
        outCNSCTAMONE.setRespuesta(MonederoDTOList);
        
        Respuesta<List<TagEntity>> out = new Respuesta<List<TagEntity>>();
        List<TagEntity> tagEntityList = new ArrayList<TagEntity>();
        TagEntity tagEntity = new TagEntity();
        tagEntity.setCategoria("0");
        tagEntity.setSaldo("50");
        tagEntity.setClteLimiteMensualRecarga("100");
        tagEntity.setClteModuloRecarga("100");
        tagEntity.setEstado("19");
        ObtenerTrxTagMedioDeRecargaEntity medioDeRegarga = new ObtenerTrxTagMedioDeRecargaEntity();
        medioDeRegarga.setIdMarcaTarjeta("VC");
        medioDeRegarga.setUlt4DigitosTarjetas("5566");
        tagEntity.setMedioDeRecarga(medioDeRegarga );
        tagEntity.setTag("5566");
        tagEntityList.add(tagEntity);
        out.setRespuesta(tagEntityList );
        
        Respuesta<List<TransaccionEntity>> respuestaObtenerTransaccionesTagsList = new Respuesta<List<TransaccionEntity>>();
        List<TransaccionEntity> transaccionEntityList = new ArrayList<TransaccionEntity>();
        TransaccionEntity transaccionEntity = new TransaccionEntity();
        transaccionEntity.setLugar("");
        transaccionEntity.setFecha("20170515 15:12:25");
        transaccionEntity.setImporte("50");
        transaccionEntity.setTipo("3290");
        transaccionEntityList.add(transaccionEntity);
        
        respuestaObtenerTransaccionesTagsList.setRespuesta(transaccionEntityList );
        
        Mockito.when(datosSolicitanteService.getDatosTarjetaMonedero(Matchers.any(Cliente.class), Matchers.any(String.class))).thenReturn(outCNSCTAMONE);
        Mockito.when(datosSolicitanteService.obtenerTags(Matchers.any(TagsDTO.class))).thenReturn(out);
        Mockito.when(datosSolicitanteService.obtenerTransaccionesTags(Matchers.any(TagsTransacDTO.class))).thenReturn(respuestaObtenerTransaccionesTagsList);
        
        
        Cliente cliente = new Cliente();
        cliente.setDni("36969858");
        cliente.setTipoDocumento("N");
        List<Cuenta> cuentasList = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setNroTarjetaCredito("5566");
        cuentasList.add(cuenta);
        cliente.setCuentas(cuentasList );
        Respuesta<DatosMonederoConMovimientosYSaldoView> respuesta = datosSolicitanteManager.consultaMonederoTag(cliente);
        
        Assert.assertNotNull(respuesta);
    }
    
    /**
     * @throws IllegalAccessException
     */
    @Test
    public void getDatosSolicitudTagAdicionalOK() throws IllegalAccessException{

    	FieldUtils.writeField(datosSolicitanteManager, "horaHastaALTA", "22:00", true);
        FieldUtils.writeField(datosSolicitanteManager, "horaDesdeALTA", "8:00", true);    	

        List<Opcion> optionList = new ArrayList<Opcion>();
        Opcion option = new Opcion();
        option.setId("1");
        option.setOpcion("400");
        optionList.add(option );
        
        Mensaje msj = new Mensaje();
        msj.setMensaje("mensaje");
        
        Mockito.when(datosSelectoresService.obtenerTiposDeDocumento()).thenReturn(optionList);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_MONEDERO)).thenReturn(msj);
        Mockito.doReturn(StringUtils.EMPTY).when(datosSolicitanteManager).horarioBancario();        

    	Respuesta<DatosSolicitudTagAdicionalView> respuesta = datosSolicitanteManager.getDatosSolicitudTagAdicional();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        Assert.assertEquals("400", respuesta.getRespuesta().getTipoDocumentoView().get(0));
    }
    
    /**
     * @throws IllegalAccessException
     */
    @Test
    @Ignore
    public void getDatosSolicitudTagAdicionalERROR() throws IllegalAccessException{

    	FieldUtils.writeField(datosSolicitanteManager, "horaHastaALTA", "8:00", true);
        FieldUtils.writeField(datosSolicitanteManager, "horaDesdeALTA", "8:00", true);    	

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        Respuesta<DatosSolicitudTagAdicionalView> respuesta = datosSolicitanteManager.getDatosSolicitudTagAdicional();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "FUERA_DE_HORARIO");

    }
    
    /**
     * @throws BusinessException
     * @throws DAOException
     */
    @Test
    public void ejecutarAltaCanalesAutomaticosOK() throws BusinessException, DAOException{

    	Cliente cliente = new Cliente();
    	List<Cuenta> cuentasList = new ArrayList<Cuenta>();
    	Cuenta cuenta = new Cuenta();
    	cuenta.setNroSucursal("1234");
		cuentasList.add(cuenta );
		cliente.setCuentas(cuentasList );
    	
    	Respuesta<DatosSolicitanteDTO> respuestaDatosSolicitanteDTO = new Respuesta<DatosSolicitanteDTO>();
    	DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();
    	respuestaDatosSolicitanteDTO.setRespuesta(datosSolicitanteDTO);
    	
    	ConsultaUnidadControlOutEntity consultaUnidadControlOutEntity = new ConsultaUnidadControlOutEntity();
    	Respuesta<AltaCanalAutomaticoOutEntity> out = new Respuesta<AltaCanalAutomaticoOutEntity>();
    	AltaCanalAutomaticoOutEntity altaCanalAutomaticoOutEntity = new AltaCanalAutomaticoOutEntity();
    	altaCanalAutomaticoOutEntity.setNumeroDelCliente("55666");
		out.setRespuesta(altaCanalAutomaticoOutEntity );
    	
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente );    	
    	Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
    	Mockito.when(datosSolicitanteService.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaDatosSolicitanteDTO);
		Mockito.when(consultaUnidadControlDAO.consultaUC(Matchers.any(ConsultaUnidadControlInEntity.class))).thenReturn(consultaUnidadControlOutEntity);
		Mockito.when(datosSolicitanteService.ejecutarAltaCanalesAutomaticos(Matchers.any(AltaCanalAutomaticoInEntity.class), Matchers.any(Cliente.class))).thenReturn(out);
    	
    	DatosAltaCanalesAutomaticosView datosAltaCanalesAutomaticosView = new DatosAltaCanalesAutomaticosView();
		Respuesta<AltaCanalAutomaticoOutEntity> respuesta = datosSolicitanteManager.ejecutarAltaCanalesAutomaticos(datosAltaCanalesAutomaticosView);
		Assert.assertNotNull(respuesta);
        Assert.assertEquals("55666", respuesta.getRespuesta().getNumeroDelCliente());
		
    }
    
    /**
     * @throws BusinessException
     * @throws DAOException
     */
    @Test
    public void ejecutarAltaCanalesAutomaticosERROR() throws BusinessException, DAOException{

    	Cliente cliente = new Cliente();
    	List<Cuenta> cuentasList = new ArrayList<Cuenta>();
    	Cuenta cuenta = new Cuenta();
    	cuenta.setNroSucursal("1234");
		cuentasList.add(cuenta );
		cliente.setCuentas(cuentasList );
    	
    	Respuesta<DatosSolicitanteDTO> respuestaDatosSolicitanteDTO = new Respuesta<DatosSolicitanteDTO>();
    	DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();
    	respuestaDatosSolicitanteDTO.setRespuesta(datosSolicitanteDTO);
    	
    	ConsultaUnidadControlOutEntity consultaUnidadControlOutEntity = new ConsultaUnidadControlOutEntity();
    	Respuesta<AltaCanalAutomaticoOutEntity> out = new Respuesta<AltaCanalAutomaticoOutEntity>();
    	AltaCanalAutomaticoOutEntity altaCanalAutomaticoOutEntity = new AltaCanalAutomaticoOutEntity();
    	altaCanalAutomaticoOutEntity.setNumeroDelCliente("55666");
		out.setRespuesta(altaCanalAutomaticoOutEntity );
    	
		Respuesta<Object> respuestaejecutarAltaCanalesAutomaticosERRORError= new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaejecutarAltaCanalesAutomaticosERRORError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaejecutarAltaCanalesAutomaticosERRORError.setItemMensajeRespuesta(itemMensajeRespuestaList);
		
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente );    	
    	Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
    	Mockito.when(datosSolicitanteService.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaDatosSolicitanteDTO);
		Mockito.when(consultaUnidadControlDAO.consultaUC(Matchers.any(ConsultaUnidadControlInEntity.class))).thenReturn(consultaUnidadControlOutEntity);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaejecutarAltaCanalesAutomaticosERRORError);
		
    	DatosAltaCanalesAutomaticosView datosAltaCanalesAutomaticosView = new DatosAltaCanalesAutomaticosView();
		Respuesta<AltaCanalAutomaticoOutEntity> respuesta = datosSolicitanteManager.ejecutarAltaCanalesAutomaticos(datosAltaCanalesAutomaticosView);
		Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");		
    }
    
    /**
     * @throws BusinessException
     * @throws DAOException
     */
    @Test
    public void ejecutarAltaCanalesAutomaticosERRORConBusinessException() throws BusinessException, DAOException{

    	Cliente cliente = new Cliente();
    	List<Cuenta> cuentasList = new ArrayList<Cuenta>();
    	Cuenta cuenta = new Cuenta();
    	cuenta.setNroSucursal("1234");
		cuentasList.add(cuenta );
		cliente.setCuentas(cuentasList );
    	
    	Respuesta<DatosSolicitanteDTO> respuestaDatosSolicitanteDTO = new Respuesta<DatosSolicitanteDTO>();
    	DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();
    	respuestaDatosSolicitanteDTO.setRespuesta(datosSolicitanteDTO);
    	
    	ConsultaUnidadControlOutEntity consultaUnidadControlOutEntity = new ConsultaUnidadControlOutEntity();
    	Respuesta<AltaCanalAutomaticoOutEntity> out = new Respuesta<AltaCanalAutomaticoOutEntity>();
    	AltaCanalAutomaticoOutEntity altaCanalAutomaticoOutEntity = new AltaCanalAutomaticoOutEntity();
    	altaCanalAutomaticoOutEntity.setNumeroDelCliente("55666");
		out.setRespuesta(altaCanalAutomaticoOutEntity );
    	
		Respuesta<Object> respuestaejecutarAltaCanalesAutomaticosERRORError= new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaejecutarAltaCanalesAutomaticosERRORError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaejecutarAltaCanalesAutomaticosERRORError.setItemMensajeRespuesta(itemMensajeRespuestaList);
		
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente );    	
    	Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
    	Mockito.when(datosSolicitanteService.getDatosDelSolicitante(Matchers.any(DatosSolicitanteCriterioDTO.class), Matchers.any(Cliente.class))).thenThrow(new BusinessException());
		Mockito.when(consultaUnidadControlDAO.consultaUC(Matchers.any(ConsultaUnidadControlInEntity.class))).thenReturn(consultaUnidadControlOutEntity);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaejecutarAltaCanalesAutomaticosERRORError);
		
    	DatosAltaCanalesAutomaticosView datosAltaCanalesAutomaticosView = new DatosAltaCanalesAutomaticosView();
		Respuesta<AltaCanalAutomaticoOutEntity> respuesta = datosSolicitanteManager.ejecutarAltaCanalesAutomaticos(datosAltaCanalesAutomaticosView);
		Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");		
    }

    /**
     * @throws DAOException
     */
    @SuppressWarnings("unchecked")
	@Test
    public void datosPadronOK() throws DAOException{

    	Respuesta<ConsultaPadronCuitOutEntity> out = new Respuesta<ConsultaPadronCuitOutEntity>();
        out.setEstadoRespuesta(EstadoRespuesta.OK);
        ConsultaPadronCuitOutEntity consultaPadronCuitOutEntity = new ConsultaPadronCuitOutEntity();
        consultaPadronCuitOutEntity.setAbaNroCuit("123456789");
        consultaPadronCuitOutEntity.setAbaTipoDocumento("N");
        consultaPadronCuitOutEntity.setAbaNroDocumento("35969686");
        consultaPadronCuitOutEntity.setAbaNombre("Jose");
        consultaPadronCuitOutEntity.setAbaApellido("Perez");
        consultaPadronCuitOutEntity.setAbaFechaNacimiento("10/12/1980");
        consultaPadronCuitOutEntity.setAbaSexo("H");
        out.setRespuesta(consultaPadronCuitOutEntity);

        Respuesta<DatosSolicitanteResponseView> respuestaDatosSolicitanteResponseView = new Respuesta<DatosSolicitanteResponseView>();
        respuestaDatosSolicitanteResponseView.setEstadoRespuesta(EstadoRespuesta.OK);
        DatosSolicitanteResponseView datosSolicitanteResponseView = new DatosSolicitanteResponseView();
        DatosSolicitanteView datosSolicitanteView = new DatosSolicitanteView();
        datosSolicitanteResponseView.setDatosSolicitanteView(datosSolicitanteView );
        respuestaDatosSolicitanteResponseView.setRespuesta(datosSolicitanteResponseView);
        
        List<Opcion> optionList = new ArrayList<Opcion>();
        Opcion option = new Opcion();
        option.setId("1");
        option.setOpcion("400");
        optionList.add(option);
        
        List<PaisDeNacimientoView> optionList2 = new ArrayList<PaisDeNacimientoView>();
        List<SexoView> optionList3 = new ArrayList<SexoView>();
        List<EstadoCivilView> optionList5 = new ArrayList<EstadoCivilView>();
        
    	Mockito.when(datosSolicitanteBO.getDatosPadronBO(Matchers.any(DatosSolicitanteCriterioView.class), Matchers.any(Cliente.class))).thenReturn(out);
        Mockito.when(datosSelectoresService.obtenerImportesARecargar()).thenReturn(optionList);
        Mockito.when(datosSelectoresService.obtenerLimitesDeRecargaMensual()).thenReturn(optionList);
        Mockito.when(datosSolicitanteBO.getDatosPadronBO(Matchers.any(DatosSolicitanteCriterioView.class), Matchers.any(Cliente.class))).thenReturn(out);
        Mockito.when(datosSelectoresService.obtenerPaisesDeNacimiento()).thenReturn(optionList);
        Mockito.when(datosSelectoresService.obtenerSexo()).thenReturn(optionList);
        Mockito.when(datosSelectoresService.obtenerEstadoCivil()).thenReturn(optionList);
        Mockito.when(datosSelectoresService.obtenerNacionalidad()).thenReturn(optionList);
        Mockito.when(tarjetaCreditoAdicionalManager.dtoToViewPaises(Matchers.any(List.class))).thenReturn(optionList2);
        Mockito.when(tarjetaCreditoAdicionalManager.dtoToViewSexo(Matchers.any(List.class))).thenReturn(optionList3);
        Mockito.when(tarjetaCreditoAdicionalManager.dtoToViewEstadoCivil(Matchers.any(List.class))).thenReturn(optionList5);
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),Matchers.any(DatosSolicitanteResponseView.class))).thenReturn(respuestaDatosSolicitanteResponseView);
    	
    	DatosSolicitanteCriterioView datosSolicitanteCriterioView = new DatosSolicitanteCriterioView();
		Respuesta<DatosSolicitanteResponseView> respuesta = datosSolicitanteManager.datosPadron(datosSolicitanteCriterioView);
		
		Assert.assertNotNull(respuesta);
    	
    }
    
	/**
	 * @throws DAOException
	 */
	@Test
    public void datosPadronERROR() throws DAOException{

    	Respuesta<Object> respuestadatosPadronError= new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestadatosPadronError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestadatosPadronError.setItemMensajeRespuesta(itemMensajeRespuestaList);
        
    	Mockito.when(datosSolicitanteBO.getDatosPadronBO(Matchers.any(DatosSolicitanteCriterioView.class), Matchers.any(Cliente.class))).thenThrow(new DAOException());
    	Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestadatosPadronError);
    	
    	DatosSolicitanteCriterioView datosSolicitanteCriterioView = new DatosSolicitanteCriterioView();
		Respuesta<DatosSolicitanteResponseView> respuesta = datosSolicitanteManager.datosPadron(datosSolicitanteCriterioView);
		
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(respuesta.getItemsMensajeRespuesta().iterator().next().getTipoError(), "TIPO_ERROR_HARDCODEADO_EN_EL_TEST");    	
    }
}
