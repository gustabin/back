package ar.com.santanderrio.obp.servicios.perfil.bo;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.combos.dao.DatosSelectoresDAO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.merlin.dao.MerlinDAO;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DatosMerlinInEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DudaEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.ResultadoMerlinEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.exception.MerlinError1Exception;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.bo.impl.CambioDomicilioBOImpl;
import ar.com.santanderrio.obp.servicios.perfil.dao.CambioDomicilioDAO;
import ar.com.santanderrio.obp.servicios.perfil.dto.CambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.DatosDomTelOutDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ModificacionCambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ProductoDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ResultadoModificacionDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.entities.CambioDomicilioInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.CambioDomicilioOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosDomicilioInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosDomicilioOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosTelefonoInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDatosTelefonoOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDomiciliosInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDomiciliosOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosDomicilioEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosTelefonoEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DomicilioEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ProductoEntity;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;

@RunWith(MockitoJUnitRunner.class)
public class CambioDomicilioBOTest {

    @InjectMocks
    private CambioDomicilioBO cambioDomicilioBO = new CambioDomicilioBOImpl();

    @Mock
    private CambioDomicilioDAO cambioDomicilioDAO;

    @Mock
    private MerlinDAO merlinDAO;

    @Mock
    private DatosSelectoresDAO datosSelectoresDAO;

    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private MensajeDAO mensajeDAO;

    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    private SesionCliente sesionCliente;

    @Mock
    private SesionParametros sesionParametros;
    
    @Mock
    private EstadisticaManager estadisticaManager;

    Mensaje mensaje = new Mensaje();
    List<CambioDomicilioDTO> lCdDTO = new ArrayList<CambioDomicilioDTO>();
    ModificacionCambioDomicilioDTO domicilioModif = new ModificacionCambioDomicilioDTO();
    CambioDomicilioView cdView = new CambioDomicilioView();
    ConsultaDatosDomicilioOutEntity cDatosDomicilio = new ConsultaDatosDomicilioOutEntity();
    DatosDomicilioEntity datosDom = new DatosDomicilioEntity();
    DatosTelefonoEntity datosTel = new DatosTelefonoEntity();
    ConsultaDatosTelefonoOutEntity cDatosTelefono = new ConsultaDatosTelefonoOutEntity();

    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");

        List<DatosDomicilioEntity> listaDomiciliosEntity = new ArrayList<DatosDomicilioEntity>();
        datosDom.setSecuencia("001");
        datosDom.setTipoDomicilio("PRI");
        datosDom.setTipoVia("");
        datosDom.setTipoConstruccion("");
        datosDom.setTipoNucleoUrbano("");
        datosDom.setObservaciones2("");
        datosDom.setSucursalCasilla("");
        datosDom.setDescLocalidad("LOCALIDAD");
        datosDom.setComuna("");
        datosDom.setRutaCartero("");
        datosDom.setCodigoPais("01");
        datosDom.setTituDomicilio("");
        datosDom.setFechaVerificacion("");
        datosDom.setUsuarioAlta("");
        datosDom.setFechaAltaRegistro("");
        datosDom.setUsuarioUltimaMod("");
        datosDom.setNroTerminalUltMod("");
        datosDom.setSucursalUltMod("");
        datosDom.setTimestampUltMod("");
        listaDomiciliosEntity.add(datosDom);
        cDatosDomicilio.setListaDatosDomicilio(listaDomiciliosEntity);

        List<DatosTelefonoEntity> listaTelefonosEntity = new ArrayList<DatosTelefonoEntity>();
        datosTel.setSecuenciaTelefono("001");
        datosTel.setTipoTelefono("1");
        datosTel.setClaseTelefono("3");
        datosTel.setNroInterno("");
        datosTel.setObservacionesSemaforo("");
        datosTel.setTimestamp("");
        listaTelefonosEntity.add(datosTel);
        cDatosTelefono.setListaTelefonos(listaTelefonosEntity);

        // Configuracion lista domicilios en sesion
        CambioDomicilioDTO cdDTO = new CambioDomicilioDTO();
        cdDTO.setDomicilioId("99999999");
        cdDTO.setApt("1234");
        cdDTO.setCalle("Calle");
        cdDTO.setCodigoPostal("1234");
        cdDTO.setComuna("");
        cdDTO.setPiso("1");
        cdDTO.setDepartamento("A");
        cdDTO.setLocalidad("Avellaneda");
        cdDTO.setMarcaDomErroneo("");
        cdDTO.setObservaciones(
                "                            14  B                                                                198");
        cdDTO.setPais("02");
        cdDTO.setSecuenciaDomicilio("001");
        cdDTO.setSucursal("");
        cdDTO.setProvincia("08");
        cdDTO.setTelefono("34567");
        cdDTO.setPrefijo("011");
        cdDTO.setCaracteristica("22");
        cdDTO.setNumeroTelefono("344543634543");
        cdDTO.setTipoDomicilio("PRI");
        cdDTO.setDescProvincia("Buenos Aires");
        cdDTO.setDescPais("Argentina");
        cdDTO.setListaProductos(new ArrayList<ProductoDTO>());
        lCdDTO.add(cdDTO);

        // Configuracion view
        cdView.setDomicilioId("99999999");
        cdView.setApt("1234");
        cdView.setCalle("Calle");
        cdView.setCaracteristica("22");
        cdView.setPrefijo("011");
        cdView.setNumeroTelefono("344543634543");
        cdView.setTelefono("344543634543");
        cdView.setLocalidad("Avellaneda");
        cdView.setDescProvincia("Buenos Aires");
        cdView.setProvincia("02");
        cdView.setCodigoPostal("1234");
        cdView.setDepartamento("A");
        cdView.setPiso("2");
        cdView.setDescPais("Argentina");
        cdView.setTipoDomicilio("PRI");
        cdView.setNormalizar(false);

        // Configuracion domicilio modificado de sesion
        domicilioModif.setSecuenciaDomicilio("001");
        domicilioModif.setDomicilioId("99999999");
        DatosDomTelOutDTO datosDomTelDTO = new DatosDomTelOutDTO();
        datosDomTelDTO.setSecuenciaDomicilio("");
        datosDomTelDTO.setTipoDomicilio("");
        datosDomTelDTO.setTipoVia("");
        datosDomTelDTO.setTipoConstruccion("");
        datosDomTelDTO.setTipoNucleoUrbano("");
        datosDomTelDTO.setObservaciones2("");
        datosDomTelDTO.setSucursalCasilla("");
        datosDomTelDTO.setLocalidad("");
        datosDomTelDTO.setComuna("");
        datosDomTelDTO.setRutaCartero("");
        datosDomTelDTO.setCodigoPais("");
        datosDomTelDTO.setTituDomicilio("");
        datosDomTelDTO.setFechaVerificacion("");
        datosDomTelDTO.setMotivoDevolucion("");
        datosDomTelDTO.setUsuarioAlta("");
        datosDomTelDTO.setFechaAltaRegistro("");
        datosDomTelDTO.setUsuarioUltimaMod("");
        datosDomTelDTO.setNroTerminalUltMod("");
        datosDomTelDTO.setSucursalUltMod("");
        datosDomTelDTO.setTimestampUltMod("");
        datosDomTelDTO.setSecuenciaTelefono("");
        datosDomTelDTO.setTipoTelefono("");
        datosDomTelDTO.setClaseTelefono("");
        datosDomTelDTO.setNroInterno("");
        datosDomTelDTO.setObservacionesSemaforo("");
        datosDomTelDTO.setTimestamp("");
        domicilioModif.setDatosAdicionales(datosDomTelDTO);
        
    }

    @Test
    public void consultarDomiciliosRegistradosTest() throws DAOException, IllegalAccessException {
        Respuesta<ConsultaDomiciliosOutEntity> rDom = new Respuesta<ConsultaDomiciliosOutEntity>();
        ConsultaDomiciliosOutEntity outDAO = new ConsultaDomiciliosOutEntity();
        List<DomicilioEntity> listDomEntity = new ArrayList<DomicilioEntity>();
        DomicilioEntity domEntity = new DomicilioEntity();
        domEntity.setApt("123");
        domEntity.setCalle("calle");
        domEntity.setCodigoPostal("1234");
        domEntity.setComuna("");
        domEntity.setLocalidad("localidad");
        domEntity.setMarcaDomErroneo("");
        domEntity.setObservaciones("                                                                                ");
        domEntity.setPais("argentina");
        domEntity.setSecuenciaDomicilio("001");
        domEntity.setSucursal("2");
        domEntity.setProvincia("02");
        domEntity.setTelefono("1234567823423423423423423342342342349133334");
        domEntity.setTipoDomicilio("");
        listDomEntity.add(domEntity);

        outDAO.setListaDomicilios(listDomEntity);
        outDAO.setCantidadDomiciliosInformados(1L);
        outDAO.setCantidadProductos(0L);
        outDAO.setCantidadTotalDomicilios("3");
        outDAO.setCodigoRetorno("00000000");
        outDAO.setHayMasDatos("S");
        outDAO.setHeaderTrama("");
        List<ProductoEntity> listaproductos = new ArrayList<ProductoEntity>();
        outDAO.setListaproductos(listaproductos);
        outDAO.setNombreApellido("nombreApellido");
        outDAO.setNumeroCuil("312321312312");
        outDAO.setNup("3312312312");
        rDom.setRespuesta(outDAO);
        rDom.setEstadoRespuesta(EstadoRespuesta.OK);

        Cliente cliente = new Cliente();
        ConsultaDomiciliosInEntity consultaDomiciliosInEntity = new ConsultaDomiciliosInEntity();
        consultaDomiciliosInEntity.setCliente(cliente);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cambioDomicilioDAO.consultaDomiciliosRegistrados(Matchers.any(ConsultaDomiciliosInEntity.class)))
                .thenReturn(rDom);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<List<CambioDomicilioDTO>> respuesta = cambioDomicilioBO.consultarDomiciliosRegistrados();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

    }
    
    @Test
    public void consultarDomiciliosRegistradosErrorTest() throws DAOException {

        Cliente cliente = new Cliente();
        ConsultaDomiciliosInEntity consultaDomiciliosInEntity = new ConsultaDomiciliosInEntity();
        consultaDomiciliosInEntity.setCliente(cliente);
        when(sesionCliente.getCliente()).thenReturn(cliente);

        Mockito.when(cambioDomicilioDAO.consultaDomiciliosRegistrados(Matchers.any(ConsultaDomiciliosInEntity.class)))
                .thenThrow(new DAOException("Servicio falló."));

        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<List<CambioDomicilioDTO>> respuesta = cambioDomicilioBO.consultarDomiciliosRegistrados();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

    }

    @Test
    public void obtenerInfoAdicionalDomTelIdInvalidoTest() throws DAOException {
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<DatosDomTelOutDTO> respuesta = cambioDomicilioBO.obtenerInfoAdicionalDomTel("");
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

    }

    @Test
    public void obtenerInfoAdicionalDomTelTest() throws DAOException {
        Cliente cliente = new Cliente();
        when(sesionParametros.getDomiciliosCliente()).thenReturn(lCdDTO);
        when(sesionParametros.getDomicilioModificado()).thenReturn(domicilioModif);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(cambioDomicilioDAO.consultaDatosDomicilio(Matchers.any(ConsultaDatosDomicilioInEntity.class)))
                .thenReturn(cDatosDomicilio);
        when(cambioDomicilioDAO.consultaDatosTelefono(Matchers.any(ConsultaDatosTelefonoInEntity.class)))
                .thenReturn(cDatosTelefono);
        Respuesta<DatosDomTelOutDTO> respuesta = cambioDomicilioBO.obtenerInfoAdicionalDomTel("99999999");
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    @Test
    public void guardarCambioDomicilioError() throws DAOException {
        CambioDomicilioView cambioDomicilioView = new CambioDomicilioView();
        CambioDomicilioOutEntity cambioDomicilioOutEntity = new CambioDomicilioOutEntity();
        when(cambioDomicilioDAO.guardarCambioDomicilio(Matchers.any(CambioDomicilioInEntity.class)))
                .thenReturn(cambioDomicilioOutEntity);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ResultadoModificacionDomicilioDTO> respuesta = cambioDomicilioBO
                .guardarCambioDomicilio(cambioDomicilioView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Ignore
    @Test
    public void guardarCambioDomicilioOK() throws DAOException {

        CambioDomicilioOutEntity cambioDomicilioOutEntity = new CambioDomicilioOutEntity();
        when(sesionParametros.getDomiciliosCliente()).thenReturn(lCdDTO);
        when(sesionParametros.getDomicilioModificado()).thenReturn(domicilioModif);
        when(cambioDomicilioDAO.guardarCambioDomicilio(Matchers.any(CambioDomicilioInEntity.class)))
                .thenReturn(cambioDomicilioOutEntity);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ResultadoModificacionDomicilioDTO> respuesta = cambioDomicilioBO.guardarCambioDomicilio(cdView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    @Ignore
    @Test
    public void normalizarDomicilioTestEror() throws DAOException, MerlinError1Exception {
        ResultadoMerlinEntity resultadoMerlinEntity = new ResultadoMerlinEntity();
        List<DudaEntity> listaDudas = new ArrayList<DudaEntity>();
        resultadoMerlinEntity.setDudas(listaDudas);
        Cliente cliente = new Cliente();
        when(sesionParametros.getDomicilioModificado()).thenReturn(domicilioModif);
        when(sesionCliente.getCliente()).thenReturn(cliente);

        Mockito.when(merlinDAO.getResultadoMerlin(Matchers.any(DatosMerlinInEntity.class)))
                .thenThrow(new DAOException("Servicio falló."));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<List<CambioDomicilioDTO>> respuesta = cambioDomicilioBO.normalizarDomicilio(cdView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    @Ignore
    @Test
    public void normalizarDomicilioTest() throws DAOException, MerlinError1Exception {
        ResultadoMerlinEntity resultadoMerlinEntity = new ResultadoMerlinEntity();
        List<DudaEntity> listaDudas = new ArrayList<DudaEntity>();
        resultadoMerlinEntity.setDudas(listaDudas);
        resultadoMerlinEntity.setCpa("");
        resultadoMerlinEntity.setProvincia("");
        resultadoMerlinEntity.setProvincia("");
        resultadoMerlinEntity.setNombreCalle("LA CALLE");
        resultadoMerlinEntity.setDepartamento("");
        resultadoMerlinEntity.setPiso("");
        resultadoMerlinEntity.setLocalidad("");
        resultadoMerlinEntity.setNumeroBloque("");
        Cliente cliente = new Cliente();
        when(sesionParametros.getDomicilioModificado()).thenReturn(domicilioModif);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(datosSelectoresDAO.obtenerIdOpcionPorDescripcion(Matchers.anyString(), Matchers.anyString()))
                .thenReturn("02");
        when(merlinDAO.getResultadoMerlin(Matchers.any(DatosMerlinInEntity.class))).thenReturn(resultadoMerlinEntity);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<List<CambioDomicilioDTO>> respuesta = cambioDomicilioBO.normalizarDomicilio(cdView);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @Test
    public void descargarComprobanteTest() throws DAOException {
        Reporte reporte = new Reporte();
        DatosComprobanteEntity datos = new DatosComprobanteEntity();
        Cliente cliente = new Cliente();
        when(sesionParametros.getDomicilioModificado()).thenReturn(domicilioModif);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cambioDomicilioDAO.descargarComprobante(Matchers.any(DatosComprobanteEntity.class))).thenReturn(reporte);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<Reporte> respuesta = cambioDomicilioBO.descargarComprobante(datos);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    @Test
    public void descargarComprobanteErrorTest() throws DAOException {
        DatosComprobanteEntity datos = new DatosComprobanteEntity();
        Cliente cliente = new Cliente();
        when(sesionParametros.getDomicilioModificado()).thenReturn(domicilioModif);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cambioDomicilioDAO.descargarComprobante(Matchers.any(DatosComprobanteEntity.class)))
                .thenThrow(new ISBANRuntimeException("Error"));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<Reporte> respuesta = cambioDomicilioBO.descargarComprobante(datos);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

    }
    
    /**
     * consultar domicilio principal laboral: OK
     * 
     * throws DAOException
     */
    @Test
    public void consultarDomicilioPrincipalLaboralTestOK() throws DAOException {
    	
    	//datos de Domi
    	DomicilioEntity domEntity = new DomicilioEntity();
        domEntity.setApt("123");
        domEntity.setCalle("calle");
        domEntity.setCodigoPostal("1234");
        domEntity.setComuna("");
        domEntity.setLocalidad("localidad");
        domEntity.setMarcaDomErroneo("");
        domEntity.setObservaciones("                                                                                ");
        domEntity.setPais("argentina");
        domEntity.setSecuenciaDomicilio("001");
        domEntity.setSucursal("2");
        domEntity.setProvincia("02");
        domEntity.setTelefono("1234567823423423423423423342342342349133334");
        domEntity.setTipoDomicilio("");
        
      //lista Domicilio Entity
    	List<DomicilioEntity> listDomEntity = new ArrayList<DomicilioEntity>();
        listDomEntity.add(domEntity);
        
      //datos de Consulta domicilio out entity
    	ConsultaDomiciliosOutEntity outDAO = new ConsultaDomiciliosOutEntity();
    	outDAO.setListaDomicilios(listDomEntity);
        outDAO.setCantidadDomiciliosInformados(1L);
        outDAO.setCantidadProductos(0L);
        outDAO.setCantidadTotalDomicilios("3");
        outDAO.setCodigoRetorno("00000000");
        outDAO.setHayMasDatos("S");
        outDAO.setHeaderTrama("");
        List<ProductoEntity> listaproductos = new ArrayList<ProductoEntity>();
        outDAO.setListaproductos(listaproductos);
        outDAO.setNombreApellido("nombreApellido");
        outDAO.setNumeroCuil("312321312312");
        outDAO.setNup("3312312312");
        
        Respuesta<List<CambioDomicilioDTO>> rtaDom = new Respuesta<List<CambioDomicilioDTO>>();

        //datos para respuesta Consulta domicilio out entity.
        Respuesta<ConsultaDomiciliosOutEntity> resConsultaDomiciliosOutEntity = new Respuesta<ConsultaDomiciliosOutEntity>();
        resConsultaDomiciliosOutEntity.setRespuesta(outDAO);
    	resConsultaDomiciliosOutEntity.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	when(cambioDomicilioDAO.consultaDomiciliosRegistrados(Matchers.any(ConsultaDomiciliosInEntity.class))).thenReturn(resConsultaDomiciliosOutEntity);
    	
    	rtaDom = cambioDomicilioBO.consultarDomiciliosRegistrados();
    	Assert.assertEquals(rtaDom.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    /**
     * consultar domicilio principal laboral: Error no se encontro domicilio
     * 
     * throws DAOException
     */
    @Test
    public void consultarDomicilioPrincipalLaboralTestErrorNoDom() throws DAOException {
    	
      //datos de Consulta domicilio out entity
    	ConsultaDomiciliosOutEntity outDAO = new ConsultaDomiciliosOutEntity();
    	outDAO.setListaDomicilios(null);
        outDAO.setCantidadDomiciliosInformados(1L);
        outDAO.setCantidadProductos(0L);
        outDAO.setCantidadTotalDomicilios("3");
        outDAO.setCodigoRetorno("00000000");
        outDAO.setHayMasDatos("S");
        outDAO.setHeaderTrama("");
        List<ProductoEntity> listaproductos = new ArrayList<ProductoEntity>();
        outDAO.setListaproductos(listaproductos);
        outDAO.setNombreApellido("nombreApellido");
        outDAO.setNumeroCuil("312321312312");
        outDAO.setNup("3312312312");
        
        Respuesta<List<CambioDomicilioDTO>> rtaDom = new Respuesta<List<CambioDomicilioDTO>>();

        //datos para respuesta Consulta domicilio out entity.
        Respuesta<ConsultaDomiciliosOutEntity> resConsultaDomiciliosOutEntity = new Respuesta<ConsultaDomiciliosOutEntity>();
        resConsultaDomiciliosOutEntity.setRespuesta(outDAO);
    	resConsultaDomiciliosOutEntity.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	when(cambioDomicilioDAO.consultaDomiciliosRegistrados(Matchers.any(ConsultaDomiciliosInEntity.class))).thenReturn(resConsultaDomiciliosOutEntity);
    	
    	rtaDom = cambioDomicilioBO.consultarDomiciliosRegistrados();
    	Assert.assertEquals(rtaDom.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    /**
     * consultar domicilio principal laboral: ERROR Respuesta consulta domicilio out entity tiene un tipo de error: "ERROR"
     * 
     * throws DAOException
     */
    @Test
    public void consultarDomicilioPrincipalLaboralTestErrorDomicilio() throws DAOException {
    	Respuesta<List<CambioDomicilioDTO>> rtaDom = new Respuesta<List<CambioDomicilioDTO>>();
    	Respuesta<ConsultaDomiciliosOutEntity> consultaDomiciliosOutEntity = new Respuesta<ConsultaDomiciliosOutEntity>();
    	consultaDomiciliosOutEntity.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	
    	when(cambioDomicilioDAO.consultaDomiciliosRegistrados(Matchers.any(ConsultaDomiciliosInEntity.class))).thenReturn(consultaDomiciliosOutEntity);
    	
    	rtaDom = cambioDomicilioBO.consultarDomiciliosRegistrados();
    	
    	Assert.assertEquals(rtaDom.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    /**
     * consultar domicilio principal laboral: ERROR lanza una DAOException
     * 
     * throws DAOException
     */
    @Test
    public void consultarDomicilioPrincipalLaboralTestErrorDAO() throws DAOException {
    	Respuesta<List<CambioDomicilioDTO>> rtaDom = new Respuesta<List<CambioDomicilioDTO>>();
    	
    	when(cambioDomicilioDAO.consultaDomiciliosRegistrados(Matchers.any(ConsultaDomiciliosInEntity.class))).thenThrow(new DAOException("DAO Exception."));
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	rtaDom = cambioDomicilioBO.consultarDomiciliosRegistrados();
    	
    	Assert.assertEquals(rtaDom.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
}
