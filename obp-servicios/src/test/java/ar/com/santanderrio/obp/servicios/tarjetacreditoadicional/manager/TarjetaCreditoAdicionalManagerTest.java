package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DatosMerlinInEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.exception.MerlinWarningException;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.login.manager.MyaManager;
import ar.com.santanderrio.obp.servicios.monedero.dao.ConsultaUnidadControlDAO;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlInEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlOutEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.DatosSolicitanteTarjetaAdicionalBO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.TarjetaCreditoAdicionalBO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.impl.EdadIncorrectaException;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ComprobanteAltaTarjCredAdicionalDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosSolicitanteCriterioDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosSolicitanteDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DomicilioDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DomiciliosDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.TarjetaAdicionalSolicitadaDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.TarjetaCandidataDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.manager.TarjetaCreditoAdicionalManager;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.manager.impl.TarjetaCreditoAdicionalManagerImpl;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosConfirmadosDelSolicitanteView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosSolicitanteCriterioView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosSolicitanteView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DomiciliosView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.SolicitudTarjetaCreditoAdicionalView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.TarjetaCandidataView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

@RunWith(MockitoJUnitRunner.class)
public class TarjetaCreditoAdicionalManagerTest {

    @InjectMocks
    private TarjetaCreditoAdicionalManager tarjetaCreditoAdicionalManager = new TarjetaCreditoAdicionalManagerImpl();

    @Mock
    private TarjetaCreditoAdicionalBO tarjetaCreditoAdicionalBO;

    @Mock
    private DatosSolicitanteTarjetaAdicionalBO datosSolicitanteBO;

    @Mock
    private MyaManager myaManager;

    @Mock
    private DatosSelectoresBO datosSelectoresBO;

    @Mock
    private AutentificacionManager autentificacionManager;

    @Mock
    private ConsultaUnidadControlDAO consultaUnidadControlDAO;

    @Mock
    private SesionParametros sesionParametros;

    @Mock
    private SesionCliente sesionCliente;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private EstadisticaManager estadisticaManager;

    @Mock
    private DesafioOperacionRSA<DatosConfirmadosDelSolicitanteView> desafioOperacionRSA;

    @Before
    public void init() throws IllegalAccessException {
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje");
        mensaje.setCodigo("0000");
        mensaje.setTag("Tag");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        FieldUtils.writeDeclaredField(tarjetaCreditoAdicionalManager, "valorDesafioTrjAdicional", 1, true);
    }

    @Test
    public void getDatosInicialesOk() {
        // Given
        Respuesta<List<TarjetaCandidataDTO>> tarjetaCandidata = new Respuesta<List<TarjetaCandidataDTO>>();
        tarjetaCandidata.setEstadoRespuesta(EstadoRespuesta.OK);
        List<TarjetaCandidataDTO> tarjetas = new ArrayList<TarjetaCandidataDTO>();
        tarjetaCandidata.setRespuesta(tarjetas);
        TarjetaCandidataDTO tarjeta = new TarjetaCandidataDTO();
        tarjeta.setCuentaId("509741688630061572");
        tarjeta.setLimiteDeCompra("500");
        tarjeta.setMoneda("dolar");
        tarjeta.setNroTarjetaConFormato("XXXX-7035");
        tarjeta.setTipoCuenta("07");
        tarjeta.setTipoCuentaDescripcion("VISA");
        tarjetas.add(tarjeta);
        // When
        when(tarjetaCreditoAdicionalBO.getTarjetasCandidatas()).thenReturn(tarjetaCandidata);
        // Then
        Respuesta<SolicitudTarjetaCreditoAdicionalView> respuesta = tarjetaCreditoAdicionalManager.getDatosIniciales();
        // Expected
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertEquals(1, respuesta.getRespuesta().getTarjetasCandidatas().size());
    }

    @Test
    public void getDatosInicialesError() {
        // Given
        Respuesta<List<TarjetaCandidataDTO>> tarjetaCandidata = new Respuesta<List<TarjetaCandidataDTO>>();
        tarjetaCandidata.setEstadoRespuesta(EstadoRespuesta.ERROR);
        // When
        when(tarjetaCreditoAdicionalBO.getTarjetasCandidatas()).thenReturn(tarjetaCandidata);
        // Then
        Respuesta<SolicitudTarjetaCreditoAdicionalView> respuesta = tarjetaCreditoAdicionalManager.getDatosIniciales();
        // Expected
        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void getDatosDelSolicitanteOkAltair() throws EdadIncorrectaException {
        // Given
        DatosSolicitanteCriterioView datosSolicitanteCriterioView = new DatosSolicitanteCriterioView();
        datosSolicitanteCriterioView.setDoc("21072349");
        datosSolicitanteCriterioView.setDocTipo("N ");
        datosSolicitanteCriterioView.setFechaNacimiento("31/08/1800");
        Respuesta<DatosSolicitanteDTO> datosSolicitanteAltair = new Respuesta<DatosSolicitanteDTO>();
        datosSolicitanteAltair.setEstadoRespuesta(EstadoRespuesta.OK);
        DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();
        datosSolicitanteDTO.setApellido("BLICKSTEIN GRINER");
        datosSolicitanteDTO.setCalle("EL VERONES                                        ");
        datosSolicitanteDTO.setCalleNro("04053     ");
        datosSolicitanteDTO.setCp("5016");
        datosSolicitanteDTO.setDepto("   ");
        datosSolicitanteDTO.setDocumentoNro("21072349");
        datosSolicitanteDTO.setDocumentoTipo("N ");
        datosSolicitanteDTO.setEstadoCivil("Casado");
        datosSolicitanteDTO.setFechaNacimiento("31081800");
        datosSolicitanteDTO.setIdNacionalidad("080");
        datosSolicitanteDTO.setIdPaisNacimiento("080");
        datosSolicitanteDTO.setLocalidad("CORDOBA                       ");
        datosSolicitanteDTO.setNacionalidad("Argentina");
        datosSolicitanteDTO.setNombre("ALBA KATERINA");
        datosSolicitanteDTO.setNup("00064530");
        datosSolicitanteDTO.setPaisNacimiento("Argentina");
        datosSolicitanteDTO.setPiso("  ");
        datosSolicitanteDTO.setSexo("Masculino");
        datosSolicitanteDTO.setTelefono("0523");
        datosSolicitanteAltair.setRespuesta(datosSolicitanteDTO);

        Cliente cliente = new Cliente();
        cliente.setNup("00352885");

        Respuesta<CredencialesMya> estadoCliente = new Respuesta<CredencialesMya>();
        estadoCliente.setEstadoRespuesta(EstadoRespuesta.OK);
        estadoCliente.setRespuesta(new CredencialesMya());

        // When
        when(tarjetaCreditoAdicionalBO.getDatosDelSolicitanteAltair(Matchers.any(DatosSolicitanteCriterioDTO.class),
                Matchers.any(Cliente.class))).thenReturn(datosSolicitanteAltair);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(myaManager.obtenerEstadoMya(Matchers.any(Cliente.class))).thenReturn(estadoCliente);
        when(datosSelectoresBO.obtenerProvincias()).thenReturn(new ArrayList<Opcion>());
        // Then
        Respuesta<DatosSolicitanteView> respuesta = tarjetaCreditoAdicionalManager
                .getDatosDelSolicitante(datosSolicitanteCriterioView);
        // Expected
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertEquals(false, respuesta.getRespuesta().getIsPadron());
    }

    @Test
    public void getDatosDelSolicitanteOkPadron() throws EdadIncorrectaException, DAOException {
        // Given
        DatosSolicitanteCriterioView datosSolicitanteCriterioView = new DatosSolicitanteCriterioView();
        datosSolicitanteCriterioView.setDoc("21072349");
        datosSolicitanteCriterioView.setDocTipo("N ");
        datosSolicitanteCriterioView.setFechaNacimiento("31/08/1800");
        Respuesta<DatosSolicitanteDTO> datosSolicitanteAltair = new Respuesta<DatosSolicitanteDTO>();
        datosSolicitanteAltair.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Respuesta<DatosSolicitanteDTO> datosSolicitantePadron = new Respuesta<DatosSolicitanteDTO>();
        datosSolicitantePadron.setEstadoRespuesta(EstadoRespuesta.OK);
        DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();
        datosSolicitanteDTO.setApellido("BLICKSTEIN GRINER");
        datosSolicitanteDTO.setDocumentoNro("21072349");
        datosSolicitanteDTO.setDocumentoTipo("N ");
        datosSolicitanteDTO.setFechaNacimiento("31081800");
        datosSolicitanteDTO.setNombre("ALBA KATERINA");
        datosSolicitanteDTO.setSexo("Masculino");
        datosSolicitantePadron.setRespuesta(datosSolicitanteDTO);

        Cliente cliente = new Cliente();
        cliente.setNup("00352885");

        Respuesta<CredencialesMya> estadoCliente = new Respuesta<CredencialesMya>();
        estadoCliente.setEstadoRespuesta(EstadoRespuesta.OK);
        estadoCliente.setRespuesta(new CredencialesMya());

        // When
        when(tarjetaCreditoAdicionalBO.getDatosDelSolicitanteAltair(Matchers.any(DatosSolicitanteCriterioDTO.class),
                Matchers.any(Cliente.class))).thenReturn(datosSolicitanteAltair);
        when(datosSolicitanteBO.getDatosPadronBO(Matchers.any(DatosSolicitanteCriterioDTO.class),
                Matchers.any(Cliente.class))).thenReturn(datosSolicitantePadron);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(myaManager.obtenerEstadoMya(Matchers.any(Cliente.class))).thenReturn(estadoCliente);
        when(datosSelectoresBO.obtenerProvincias()).thenReturn(new ArrayList<Opcion>());
        // Then
        Respuesta<DatosSolicitanteView> respuesta = tarjetaCreditoAdicionalManager
                .getDatosDelSolicitante(datosSolicitanteCriterioView);
        // Expected
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertEquals(true, respuesta.getRespuesta().getIsPadron());
    }

    @Test
    public void getResultadoMerlinOk() throws MerlinWarningException, BusinessException {
        // Given
        DatosMerlinInEntity datosDeDomicilio = new DatosMerlinInEntity();
        DomiciliosDTO domiciliosDTO = new DomiciliosDTO();
        domiciliosDTO.setEsPopUp(false);
        List<DomicilioDTO> domiciliosList = new ArrayList<DomicilioDTO>();
        DomicilioDTO domicilioDTO = new DomicilioDTO();
        domicilioDTO.setCodPostal("C1136AAF");
        domicilioDTO.setCodProv("01");
        domicilioDTO.setDepartamento("A");
        domicilioDTO.setDescLocalidad("CAPITAL");
        domicilioDTO.setDescProv("Capital Federal");
        domicilioDTO.setNombreCalle("SAN JOSE");
        domicilioDTO.setNumeroBloque("1452");
        domicilioDTO.setPiso("5");
        domiciliosList.add(domicilioDTO);
        domiciliosDTO.setDomiciliosDTO(domiciliosList);
        // When
        when(tarjetaCreditoAdicionalBO.getResultadoMerlin(Matchers.any(Cliente.class),
                Matchers.any(DatosMerlinInEntity.class))).thenReturn(domiciliosDTO);
        // Then
        Respuesta<DomiciliosView> respuesta = tarjetaCreditoAdicionalManager.getResultadoMerlin(datosDeDomicilio);
        // Expected
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertEquals(false, respuesta.getRespuesta().isEsPopUp());
        assertEquals(1, respuesta.getRespuesta().getDomiciliosView().size());
    }

    @Test
    public void getResultadoMerlinOkPopUp() throws MerlinWarningException, BusinessException {
        // Given
        DatosMerlinInEntity datosDeDomicilio = new DatosMerlinInEntity();
        DomiciliosDTO domiciliosDTO = new DomiciliosDTO();
        domiciliosDTO.setEsPopUp(true);
        List<DomicilioDTO> domiciliosList = new ArrayList<DomicilioDTO>();
        DomicilioDTO domicilioDTO = new DomicilioDTO();
        domicilioDTO.setCodPostal("C1136AAF");
        domicilioDTO.setCodProv("01");
        domicilioDTO.setDepartamento("A");
        domicilioDTO.setDescLocalidad("CAPITAL");
        domicilioDTO.setDescProv("Capital Federal");
        domicilioDTO.setNombreCalle("SAN JOSE");
        domicilioDTO.setNumeroBloque("1452");
        domicilioDTO.setPiso("5");
        domiciliosList.add(domicilioDTO);

        DomicilioDTO domicilioDTO2 = new DomicilioDTO();
        domicilioDTO2.setCodPostal("C1424ABO");
        domicilioDTO2.setCodProv("01");
        domicilioDTO2.setDepartamento("A");
        domicilioDTO2.setDescLocalidad("CAPITAL");
        domicilioDTO2.setDescProv("Capital Federal");
        domicilioDTO2.setNombreCalle("AV JOSE M MORENO");
        domicilioDTO2.setNumeroBloque("1452");
        domicilioDTO2.setPiso("5");
        domiciliosList.add(domicilioDTO2);

        domiciliosDTO.setDomiciliosDTO(domiciliosList);
        // When
        when(tarjetaCreditoAdicionalBO.getResultadoMerlin(Matchers.any(Cliente.class),
                Matchers.any(DatosMerlinInEntity.class))).thenReturn(domiciliosDTO);
        // Then
        Respuesta<DomiciliosView> respuesta = tarjetaCreditoAdicionalManager.getResultadoMerlin(datosDeDomicilio);
        // Expected
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertEquals(true, respuesta.getRespuesta().isEsPopUp());
        assertEquals(2, respuesta.getRespuesta().getDomiciliosView().size());
    }

    @Test
    public void altaTarjetaCreditoAdicionalOkConNup() throws DAOException {
        // Given
        DatosConfirmadosDelSolicitanteView datosSolicitante = mockDatosConfirmadosDelSolicitanteView();
        Respuesta<AutentificacionDTO> rsa = new Respuesta<AutentificacionDTO>();
        rsa.setEstadoRespuesta(EstadoRespuesta.OK);
        Cliente cliente = ClienteMock.completarInfoCliente();
        Respuesta<ComprobanteAltaTarjCredAdicionalDTO> respuestaAltaTarjeta = new Respuesta<ComprobanteAltaTarjCredAdicionalDTO>();
        respuestaAltaTarjeta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAltaTarjeta.setRespuesta(mockComprobanteAltaTarjCredAdicionalDTO());
        // When
        when(sesionParametros.getDesafioEnCurso()).thenReturn(null);
        when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(rsa);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(consultaUnidadControlDAO.consultaUC(Matchers.any(ConsultaUnidadControlInEntity.class)))
                .thenReturn(new ConsultaUnidadControlOutEntity());
        when(tarjetaCreditoAdicionalBO.altaTarjetaCreditoAdicional(
                Matchers.any(DatosConfirmadosDelSolicitanteView.class), Matchers.any(Cliente.class)))
                        .thenReturn(respuestaAltaTarjeta);
        Respuesta<DatosConfirmadosDelSolicitanteView> respuestaRSA = new Respuesta<DatosConfirmadosDelSolicitanteView>();
        respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
        when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(DatosConfirmadosDelSolicitanteView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
        // Then
        Respuesta<DatosConfirmadosDelSolicitanteView> respuesta = tarjetaCreditoAdicionalManager
                .altaTarjetaCreditoAdicional(datosSolicitante);
        // Expected
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @Test
    public void altaTarjetaCreditoAdicionalOkAltaDeNup() throws DAOException {
        // Given
        DatosConfirmadosDelSolicitanteView datosSolicitante = mockDatosConfirmadosDelSolicitanteView();
        datosSolicitante.setNup(null);
        Respuesta<AutentificacionDTO> rsa = new Respuesta<AutentificacionDTO>();
        rsa.setEstadoRespuesta(EstadoRespuesta.OK);
        Cliente cliente = ClienteMock.completarInfoCliente();
        Respuesta<ComprobanteAltaTarjCredAdicionalDTO> respuestaAltaTarjeta = new Respuesta<ComprobanteAltaTarjCredAdicionalDTO>();
        respuestaAltaTarjeta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAltaTarjeta.setRespuesta(mockComprobanteAltaTarjCredAdicionalDTO());
        Respuesta<AltaCanalAutomaticoOutEntity> altaCanalAutomaticoRespuesta = new Respuesta<AltaCanalAutomaticoOutEntity>();
        altaCanalAutomaticoRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        AltaCanalAutomaticoOutEntity altaCanalAutomaticoOutEntity = new AltaCanalAutomaticoOutEntity();
        altaCanalAutomaticoOutEntity.setNumeroDelCliente("00064530");
        altaCanalAutomaticoRespuesta.setRespuesta(altaCanalAutomaticoOutEntity);
        // When
        when(sesionParametros.getDesafioEnCurso()).thenReturn(null);
        when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(rsa);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(consultaUnidadControlDAO.consultaUC(Matchers.any(ConsultaUnidadControlInEntity.class)))
                .thenReturn(new ConsultaUnidadControlOutEntity());
        when(tarjetaCreditoAdicionalBO.altaTarjetaCreditoAdicional(
                Matchers.any(DatosConfirmadosDelSolicitanteView.class), Matchers.any(Cliente.class)))
                        .thenReturn(respuestaAltaTarjeta);
        when(datosSolicitanteBO.ejecutarAltaCanalesAutomaticos(Matchers.any(AltaCanalAutomaticoInEntity.class),
                Matchers.any(Cliente.class))).thenReturn(altaCanalAutomaticoRespuesta);

        Respuesta<DatosConfirmadosDelSolicitanteView> respuestaRSA = new Respuesta<DatosConfirmadosDelSolicitanteView>();
        respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
        when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(DatosConfirmadosDelSolicitanteView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);

        // Then
        Respuesta<DatosConfirmadosDelSolicitanteView> respuesta = tarjetaCreditoAdicionalManager
                .altaTarjetaCreditoAdicional(datosSolicitante);
        // Expected
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    private DatosConfirmadosDelSolicitanteView mockDatosConfirmadosDelSolicitanteView() {
        DatosConfirmadosDelSolicitanteView datosSolicitante = new DatosConfirmadosDelSolicitanteView();
        datosSolicitante.setNup("00064530");
        datosSolicitante.setDocumentoTipo("DNI");
        datosSolicitante.setDocumentoNro("18.654.377");
        datosSolicitante.setApellido("LASTFOIGEL");
        datosSolicitante.setNombre("MARCIO ELISANDRO");
        datosSolicitante.setFechaNacimiento("29/07/1964");
        datosSolicitante.setCuit("20186543778");
        datosSolicitante.setPaisNacimiento("080");
        datosSolicitante.setSexo("Masculino");
        datosSolicitante.setEstadoCivil("Soltero");
        datosSolicitante.setNacionalidad("080");
        datosSolicitante.setCalle("SAN JOSE");
        datosSolicitante.setNro("1452");
        datosSolicitante.setPiso("5");
        datosSolicitante.setDepto("A");
        datosSolicitante.setProvincia("01");
        datosSolicitante.setCp("C1136AAF");
        datosSolicitante.setLocalidad("CAPITAL");
        datosSolicitante.setTipoCuitCuil("CUIL");

        List<TarjetaCandidataView> tarjetas = new ArrayList<TarjetaCandidataView>();
        TarjetaCandidataView tarjeta = new TarjetaCandidataView();
        tarjeta.setTipoCuenta("07");
        tarjeta.setTipoCuentaDescripcion("VISA");
        tarjeta.setNroTarjetaConFormato("XXXX-7035");
        tarjeta.setPorcentajeLimiteDeCompra("17");
        tarjeta.setLimiteDeCompra("500");
        tarjeta.setCuentaId("1497085897379186352");
        tarjeta.setMoneda("dolar");
        tarjetas.add(tarjeta);
        datosSolicitante.setTarjetas(tarjetas);
        return datosSolicitante;
    }

    private TarjetaAdicionalSolicitadaDTO mockTarjetaAdicionalSolicitadaDTO() {
        TarjetaAdicionalSolicitadaDTO tarjetaAdicionalSolicitadaDTO = new TarjetaAdicionalSolicitadaDTO();
        tarjetaAdicionalSolicitadaDTO.setTipoCuenta("Tarjeta de crédito Santander VISA");
        tarjetaAdicionalSolicitadaDTO.setNroTarjetaConFormato("XXXX-7035");
        tarjetaAdicionalSolicitadaDTO.setCuenta("33850139/2");
        tarjetaAdicionalSolicitadaDTO.setPorcentajeLimiteDeCompra("17");
        tarjetaAdicionalSolicitadaDTO.setSolicitudNro("056848");
        tarjetaAdicionalSolicitadaDTO.setConError(false);
        tarjetaAdicionalSolicitadaDTO.setNombreTarjeta("Cuenta VISA");
        return tarjetaAdicionalSolicitadaDTO;
    }

    private ComprobanteAltaTarjCredAdicionalDTO mockComprobanteAltaTarjCredAdicionalDTO() {
        ComprobanteAltaTarjCredAdicionalDTO comprobanteAltaTarjCredAdicionalDTO = new ComprobanteAltaTarjCredAdicionalDTO();
        comprobanteAltaTarjCredAdicionalDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        comprobanteAltaTarjCredAdicionalDTO.setApellido("LASTFOIGEL");
        comprobanteAltaTarjCredAdicionalDTO.setNombre("MARCIO ELISANDRO");
        comprobanteAltaTarjCredAdicionalDTO.setDniAdicional("18.654.377");
        comprobanteAltaTarjCredAdicionalDTO.setFechaHora("19-07-2018 10:27");
        comprobanteAltaTarjCredAdicionalDTO.setLegal("Conserve este ticket como comprobante S.E.U.O.");
        List<TarjetaAdicionalSolicitadaDTO> tarjetasSolicitadas = new ArrayList<TarjetaAdicionalSolicitadaDTO>();
        tarjetasSolicitadas.add(mockTarjetaAdicionalSolicitadaDTO());
        comprobanteAltaTarjCredAdicionalDTO.setTarjetasAdicionalesSolicitadas(tarjetasSolicitadas);
        return comprobanteAltaTarjCredAdicionalDTO;
    }

}
