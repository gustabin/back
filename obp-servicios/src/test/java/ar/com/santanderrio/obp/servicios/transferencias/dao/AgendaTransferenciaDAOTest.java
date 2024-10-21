package ar.com.santanderrio.obp.servicios.transferencias.dao;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.constants.SietePorVeintiCuatroConstant;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda.CNSAgendaDatosResponse;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda.CNSAgendaRegResponse;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda.CNSAgendaResponse;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao.SietePorVenticuatroV1DAO;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro;
import ar.com.santanderrio.obp.servicios.transferencias.dao.impl.AgendaTransferenciaDAOImpl;
import ar.com.santanderrio.obp.servicios.transferencias.entities.ConsultaAgendaTransferencias;
import ar.com.santanderrio.obp.servicios.transferencias.entities.PlazoAcreditacion;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.DatosOrigenTransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.FrecuenciaTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;

/**
 * The Class AgendaTransferenciaDAOTest.
 * 
 * @version 2. Manuel.Vargas
 */
@RunWith(MockitoJUnitRunner.class)
public class AgendaTransferenciaDAOTest {

    /** The agenda transferencia DAO. */
    @InjectMocks
    private AgendaTransferenciaDAOImpl agendaTransferenciaDAO = new AgendaTransferenciaDAOImpl();

    /** The siete por venticuatro Version 1 DAO. */
    @Mock
    private SietePorVenticuatroV1DAO sietePorVenticuatroV1DAO;

    @Mock
    private SesionParametros sesionParametros;

    @Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(agendaTransferenciaDAO, "errorBanelcoCoelsaHabilitado", "1");
    }
    
    @Test
    public void modificacionTransferenciaAgendadaOKTest() throws DAOException {
        String tipoAgendamiento = "AUTOMATICO";
        XMLResponseEntity response = obtenerXMLResponse();
        response.getDATOSRESULTADO().setCodRet("0");
        response.getDATOSRESULTADO().setIdRecibo("20170808123123");
        when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(response);

        TransferenciaAgendadaDTO ransferenciaAgendadaDTO = obtenerTransferenciaDTO();

        TransferenciaDTO request = agendaTransferenciaDAO.ejecutarModificacionDeTransferenciaAgendada(
                ransferenciaAgendadaDTO, tipoAgendamiento, obtenerCliente());

        Assert.assertNotNull(request);
    }

    @Test(expected = DAOException.class)
    public void modificacionTransferenciaAgendadaERRORTest() throws DAOException {
        XMLResponseEntity response = obtenerXMLResponse();
        String tipoAgendamiento = "AUTOMATICO";
        response.getDATOSRESULTADO().setCodRet("1");
        when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(response);

        TransferenciaAgendadaDTO ransferenciaAgendadaDTO = (TransferenciaAgendadaDTO) obtenerTransferenciaDTO();

        agendaTransferenciaDAO.ejecutarModificacionDeTransferenciaAgendada(ransferenciaAgendadaDTO, tipoAgendamiento,
                obtenerCliente());

    }

    @Test
    public void agendamientoTransferenciaRecordatorioRioRioOKTest() throws DAOException {
        XMLResponseEntity response = obtenerXMLResponse();
        response.getDATOSRESULTADO().setCodRet("0");
        when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(response);

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setHaciaOtroBanco(Boolean.FALSE);

        TransferenciaDTO request = agendaTransferenciaDAO
                .ejecutarAgendamientoRecordatorioTransferencia(transferenciaDTO, obtenerCliente());

        Assert.assertNotNull(request);
    }

    @Test(expected = DAOException.class)
    public void agendamientoTransferenciaRecordatorioRioRioERRORTest() throws DAOException {
        XMLResponseEntity response = obtenerXMLResponse();
        response.getDATOSRESULTADO().setCodRet("1");
        when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(response);

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setHaciaOtroBanco(Boolean.FALSE);

        TransferenciaDTO request = agendaTransferenciaDAO
                .ejecutarAgendamientoRecordatorioTransferencia(transferenciaDTO, obtenerCliente());

    }

    @Test
    public void agendamientoTransferenciaRecordatorioOBOkTest() throws DAOException {
        XMLResponseEntity response = obtenerXMLResponse();
        response.getDATOSRESULTADO().setCodRet("0");
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("180.123.123.123");

        when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(response);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setHaciaOtroBanco(Boolean.TRUE);

        TransferenciaDTO request = agendaTransferenciaDAO
                .ejecutarAgendamientoRecordatorioTransferencia(transferenciaDTO, obtenerCliente());

        Assert.assertNotNull(request);
    }

    @Test(expected = DAOException.class)
    public void agendamientoTransferenciaRecordatorioOBErrorTest() throws DAOException {
        XMLResponseEntity response = obtenerXMLResponse();
        response.getDATOSRESULTADO().setCodRet("1");
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("180.123.123.123");

        when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(response);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setHaciaOtroBanco(Boolean.TRUE);

        TransferenciaDTO request = agendaTransferenciaDAO
                .ejecutarAgendamientoRecordatorioTransferencia(transferenciaDTO, obtenerCliente());
    }

    /*
     * AUTOMATICAS
     */

    @Test
    public void agendamientoTransferenciaAutomaticaRioRioOKTest() throws DAOException {
        XMLResponseEntity response = obtenerXMLResponse();
        response.getDATOSRESULTADO().setCodRet("0");
        when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(response);

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setHaciaOtroBanco(Boolean.FALSE);

        TransferenciaDTO request = agendaTransferenciaDAO.ejecutarAgendamientoAutomaticoTransferencia(transferenciaDTO,
                obtenerCliente());

        Assert.assertNotNull(request);
    }

    @Test(expected = DAOException.class)
    public void agendamientoTransferenciaAutomaticaRioRioERRORTest() throws DAOException {
        XMLResponseEntity response = obtenerXMLResponse();
        response.getDATOSRESULTADO().setCodRet("1");
        when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(response);

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setHaciaOtroBanco(Boolean.FALSE);

        TransferenciaDTO request = agendaTransferenciaDAO.ejecutarAgendamientoAutomaticoTransferencia(transferenciaDTO,
                obtenerCliente());

    }

    @Test
    public void agendamientoTransferenciaAutomaticaOBOkTest() throws DAOException {
        XMLResponseEntity response = obtenerXMLResponse();
        response.getDATOSRESULTADO().setCodRet("0");
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("180.123.123.123");

        when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(response);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setHaciaOtroBanco(Boolean.TRUE);

        TransferenciaDTO request = agendaTransferenciaDAO.ejecutarAgendamientoAutomaticoTransferencia(transferenciaDTO,
                obtenerCliente());

        Assert.assertNotNull(request);
    }

    @Test(expected = DAOException.class)
    public void agendamientoTransferenciaAutomaticaOBErrorTest() throws DAOException {
        XMLResponseEntity response = obtenerXMLResponse();
        response.getDATOSRESULTADO().setCodRet("1");
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("180.123.123.123");

        when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(response);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setHaciaOtroBanco(Boolean.TRUE);

        TransferenciaDTO request = agendaTransferenciaDAO.ejecutarAgendamientoAutomaticoTransferencia(transferenciaDTO,
                obtenerCliente());
    }

    public void validarArmadoRequestAgendamientoTransferenciaTest() {
        Cliente cliente = mock(Cliente.class);
        when(cliente.getNup()).thenReturn("00276937");
        when(cliente.getUsuarioRacf()).thenReturn("02GLPE92");
        when(cliente.getPasswordRacf()).thenReturn("@DSI@SOS");
        when(cliente.getFechaNacimiento()).thenReturn("19591005");
        when(cliente.getDni()).thenReturn("00013238861");
        when(cliente.getTipoDocumento()).thenReturn("N");
        // when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(value)

        AgendaTransferenciaDAOImpl agendaTransferenciaDaoImpl = new AgendaTransferenciaDAOImpl();

        // TransferenciaDTO request =
        // agendaTransferenciaDaoImpl.ejecutarAgendamientoRecordatorioTransferencia(obtenerTransferenciaDTO(),
        // cliente);
    }

    private XMLResponseEntity obtenerXMLResponse() {
        XMLResponseEntity xmlResponseEntity = new XMLResponseEntity();
        xmlResponseEntity.setDATOSRESULTADO(new XMLResponseEntity.DATOSRESULTADO());
        xmlResponseEntity.getDATOSRESULTADO().setCodRet("0");
        return xmlResponseEntity;
    }

    // public void validarRequest(){
    // AgendaTransferenciaDAOImpl agendaTransferenciaDaoImpl = new
    // AgendaTransferenciaDAOImpl();
    // XMLRequestEntity requestResultado =
    // agendaTransferenciaDaoImpl.armarRequestRioRioAgendamientoTransferencia(obtenerTransferenciaDTO(),
    // obtenerCliente(), "RECORDATORIO");
    // XMLRequestEntity requestEsperado = obtenerXMLRequest();
    //// Assert.assertEquals(requestEsperado.getCONFIG(),
    // requestResultado.getCONFIG());
    // }

    private XMLRequestEntity obtenerXMLRequest() {
        XMLRequestEntity xmlRequest = new XMLRequestEntity();
        XMLRequestEntity.CONFIG config = new XMLRequestEntity.CONFIG();
        config.setEcoDatosEntrada(SietePorVeintiCuatroConstant.ECO_DATOS_ENTRADA_7X24);
        config.setVersionXML(SietePorVeintiCuatroConstant.VERSION_XML_7X24);
        xmlRequest.setCONFIG(config);

        XMLRequestEntity.DATOSADICIONALES datosAdicionales = new XMLRequestEntity.DATOSADICIONALES();
        XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos datosMensAvisos = new XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos();
        datosMensAvisos.setAnotacionesPersonales("");
        datosMensAvisos.setComentario(null);
        datosMensAvisos.setConcepto("VAR");
        datosMensAvisos.setConceptoAdicional1("");
        datosMensAvisos.setConceptoAdicional2("");
        datosMensAvisos.setConceptoAdicional3("");
        datosMensAvisos.setDescripcionAdicional1("");
        datosMensAvisos.setDescripcionAdicional2("");
        datosMensAvisos.setDescripcionAdicional3("");
        datosMensAvisos.setInfoAdicional("Varios");
        datosMensAvisos.setMailClienteReply("");
        datosMensAvisos.setTitularCredito("Comignaghi Valeriano Paul Tadeo");
        datosMensAvisos.setTitularDebito("VALERIANO PAUL TADEO COMIGNAGHI");
        datosAdicionales.setDatosMensAvisos(datosMensAvisos);
        datosAdicionales.setDatosSueldos(null);
        xmlRequest.setDatosAdicionales(datosAdicionales);

        XMLRequestEntity.DATOSENTRADA datosEntrada = new XMLRequestEntity.DATOSENTRADA();
        XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
        parametros.setCodigoConcepto("VAR");
        parametros.setCotizacionTransferencia("0");
        parametros.setCuentaPropia("N");
        parametros.setDescripcionConcepto("Varios");
        parametros.setImporteCredito("0");
        parametros.setImporteDebito("1231.23");
        parametros.setIndicadorAfectarDisponible("N");
        parametros.setIndicadorLimiteMax("N");
        parametros.setNombreCtaCredito("Comignaghi Valeriano Paul Tadeo");
        parametros.setNroComprobante("0");
        parametros.setNroCtaCredito("0639170");
        parametros.setNroCtaDebito("3663936");
        parametros.setSucCtaCredito("000");
        parametros.setSucCtaDebito("033");
        parametros.setTipoCtaCredito("09");
        parametros.setTipoCtaDebito("09");
        datosEntrada.setParametros(parametros);
        xmlRequest.setDATOSENTRADA(datosEntrada);

        XMLRequestEntity.META meta = new XMLRequestEntity.META();
        XMLRequestEntity.META.Recurrencias recurrencias = new XMLRequestEntity.META.Recurrencias();
        XMLRequestEntity.META.Subcanal subcanal = new XMLRequestEntity.META.Subcanal();
        XMLRequestEntity.META.Usuario usuario = new XMLRequestEntity.META.Usuario();
        XMLRequestEntity.META.Cliente cliente = new XMLRequestEntity.META.Cliente();
        XMLRequestEntity.META.Canal canal = new XMLRequestEntity.META.Canal();
        canal.setCanalId("HTML");
        canal.setCanalTipo("04");
        canal.setCanalVersion("000");
        cliente.setFechaNac("19591005");
        cliente.setIdCliente("00013238861");
        cliente.setNUP("00276937");
        cliente.setTipoId("N");
        cliente.setTipoPersona("I");
        meta.setFechaEjecucion("20171008");
        meta.setIdSesionCnt("        ");
        meta.setIndAuten("0");
        meta.setLogueoAgendaHistorica("S");
        meta.setModoEjecucion("A");
        meta.setNombre("TRANSF_BCO_RIO");
        recurrencias.setFechaBaseRecurrencia("20171008");
        recurrencias.setFrecRecurrencia("M10802");
        recurrencias.setMaxRecurrencia("0");
        recurrencias.setTipoRecurrencia("I");
        subcanal.setSubcanalId("0001");
        subcanal.setSubcanalTipo("99");
        usuario.setUsuarioAtrib("  ");
        usuario.setUsuarioId("02GLPE92");
        usuario.setUsuarioPwd("@DSI@SNA");
        usuario.setUsuarioTipo("03");
        meta.setRecurrencias(recurrencias);
        meta.setSubcanal(subcanal);
        meta.setUsuario(usuario);
        meta.setCliente(cliente);
        meta.setCanal(canal);
        meta.setTipoAgendamiento("I");
        meta.setVersion("110");
        xmlRequest.setMETA(meta);

        return xmlRequest;
    }

    public Cliente obtenerCliente() {
        Cliente cliente = mock(Cliente.class);
        when(cliente.getNup()).thenReturn("00276937");
        when(cliente.getUsuarioRacf()).thenReturn("02GLPE92");
        when(cliente.getPasswordRacf()).thenReturn("@DSI@SNA");
        when(cliente.getFechaNacimiento()).thenReturn("19591005");
        when(cliente.getDni()).thenReturn("00013238861");
        when(cliente.getNombre()).thenReturn("VALERIANO PAUL");
        when(cliente.getApellido1()).thenReturn("TADEO COMIGNAGHI");
        when(cliente.getTipoDocumento()).thenReturn("N");
        return cliente;
    }

    // TODO: Revisar por el refactor
    @Ignore
    public TransferenciaAgendadaDTO obtenerTransferenciaDTO() {
        TransferenciaAgendadaDTO transferenciaDTO = new TransferenciaAgendadaDTO();
        Cuenta cuenta = new Cuenta();
        cuenta.setNroSucursal("0033");
        cuenta.setNroCuentaProducto("0000000003663936");
        cuenta.setTipoCuentaSinUnificar("09");
        cuenta.setTipoCuenta("02");
        cuenta.setNroOrdenFirmante("003");
        cuenta.setCodigoAplicacion("INFI");
        cuenta.setClaseCuenta("N");
        cuenta.setFormaDeOperar("02");
        cuenta.setNroTarjetaCredito("00000000000000000000");
        cuenta.setCodigoPaquete("2101");
        cuenta.setClasePaquete("14");
        cuenta.setSaldoCUPesos("126.60");
        cuenta.setSaldoCUDls("99997859.42");
        cuenta.setSaldoParaConformar("00000000000000+");
        cuenta.setDepositoEfectivo("00000000000000");
        cuenta.setSubproductoAltair("0001");
        cuenta.setMonedaAltair("ARS");
        cuenta.setGrupoAfinidad("000000");
        cuenta.setOficinaAltair("0201");
        cuenta.setContratoAltair("0000007003632381");
        cuenta.setSucursalPaquete("0201");
        cuenta.setNumeroPaquete("000090000131928");
        cuenta.setConvenioPAS(false);
        cuenta.setHabilitadaParaTransferir(false);
        cuenta.setNroSucursal("033");
        cuenta.setNroCuentaProducto("285-34583177/4");
        transferenciaDTO.setCuentaOrigen(cuenta);
        transferenciaDTO.setTipoCuentaDestino(TipoCuenta.fromCodigo(02));
        transferenciaDTO.setTipoTransferencia((byte) 3);
        transferenciaDTO.setImporte(new BigDecimal("1231.23"));
        transferenciaDTO.setMoneda(DivisaEnum.PESO);
        transferenciaDTO.setMarcaLimite("S");
        transferenciaDTO.setPlazoAcreditacion(PlazoAcreditacion.INMEDIATO);
        // transferenciaDTO.setPeriodica("N");
        // transferenciaDTO.setCantidadDias((short) 0);
        transferenciaDTO.setTitular("Comignaghi  Valeriano Paul Tad");
        transferenciaDTO.setNombreReceptor("Comignaghi Valeriano Paul Tadeo");
        transferenciaDTO.setConcepto(ConceptoTransferenciaEnum.VARIOS);
        transferenciaDTO.setInformacionAdicional("Varios");
        transferenciaDTO.setBancoDestino(BancoEnum.SANTANDER_RIO.getDescripcion());
        transferenciaDTO.setAgenda("N");
        transferenciaDTO.setFechaProgramada(new Date("10/08/2017"));
        transferenciaDTO.setFrecuencia(FrecuenciaTransferenciaAgendada.CADA_2_MESES);
        transferenciaDTO.setTipoCuentaDestino(TipoCuenta.CUENTA_UNICA_PESOS);
        IdentificacionCuenta ic = new IdentificacionCuenta();
        ic.setNroCuentaProducto("0639170");
        transferenciaDTO.setNumeroCuentaDestino(ic);
        transferenciaDTO.setConcepto(ConceptoTransferenciaEnum.VARIOS);
        transferenciaDTO.setDescripcionConcepto("Varios");
        transferenciaDTO.setCuit("20-34234234-6");
        transferenciaDTO.setCbuCuenta("0270006910000102550013");

        TransferenciaAgendada transferenciaAgendada = new TransferenciaAgendada();
        transferenciaAgendada.setNroRecurrencia("123");
        transferenciaAgendada.setIdTransaccion("123");
        transferenciaAgendada.setTipoCtaDebito("02");
        transferenciaAgendada.setSucCtaDebito("033");
        transferenciaAgendada.setNroCtaDebito("1231231");
        transferenciaAgendada.setTipoCtaDebito("02");
        transferenciaAgendada.setSucCtaDebito("033");
        transferenciaAgendada.setNroCtaDebito("1221231");
        transferenciaAgendada.setNombreCtaCredito("Nombre cuenta cdto");
        transferenciaAgendada.setCotizacionTransferencia("Cotizacion transferencia");
        transferenciaAgendada.setNroComprobante("123123");
        transferenciaAgendada.setIndicadorAfectarDisponible("indicador Agedar Disponible");
        transferenciaAgendada.setCuentaPropia("N");
        transferenciaAgendada.setTitularDebito("Titular debito");
        transferenciaAgendada.setTitularCredito("Titular credito");
        DatosOrigenTransferenciaAgendadaDTO datosOrigen = new DatosOrigenTransferenciaAgendadaDTO();
        datosOrigen.setTransferenciaAgendada(transferenciaAgendada);
        transferenciaDTO.setDatosOrigen(datosOrigen);

        return transferenciaDTO;
    }

    /**
     * Test get transferencia agendada OK.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetTransferenciaAgendadaOK() throws DAOException {
        ConsultaAgendaTransferencias consultaAgendaTransferencias = mock(ConsultaAgendaTransferencias.class);
        List<CNSAgendaRegResponse> registros = new ArrayList<CNSAgendaRegResponse>();
        Cliente cliente = mock(Cliente.class);
        //
        XMLResponseEntity xmlResponse = mock(XMLResponseEntity.class);
        DATOSRESULTADO dATOSRESULTADO = mock(DATOSRESULTADO.class);
        Registro registro = mock(Registro.class);
        when(dATOSRESULTADO.getCodRet()).thenReturn("0");
        when(dATOSRESULTADO.getSeveridad()).thenReturn("0");
        when(dATOSRESULTADO.getCantidadRegistro()).thenReturn("1");
        when(xmlResponse.getDATOSRESULTADO()).thenReturn(dATOSRESULTADO);
        when(dATOSRESULTADO.getRegistro()).thenReturn(new ArrayList<Registro>());
        // mockeo respuesta de servicio
        CNSAgendaResponse sietePorVeinticuatroResponse = mock(CNSAgendaResponse.class);
        CNSAgendaDatosResponse cnsAgendaDatosResponse = mock(CNSAgendaDatosResponse.class);
        CNSAgendaRegResponse cnsAgendaRegResponse = mock(CNSAgendaRegResponse.class);

        when(cliente.getNup()).thenReturn("00276937");
        when(cliente.getUsuarioRacf()).thenReturn("FREEUSER");
        when(cliente.getPasswordRacf()).thenReturn("FREEUSER");
        when(consultaAgendaTransferencias.getCliente()).thenReturn(cliente);
        when(consultaAgendaTransferencias.getFechaDesde()).thenReturn(new Date());
        when(consultaAgendaTransferencias.getFechaHasta()).thenReturn(new Date());
        when(sietePorVeinticuatroResponse.getCodRet()).thenReturn("0");
        when(sietePorVeinticuatroResponse.getCodRet()).thenReturn("0");
        when(sietePorVeinticuatroResponse.getSeveridad()).thenReturn("0");
        when(sietePorVeinticuatroResponse.getIdOper()).thenReturn("0");
        when(sietePorVeinticuatroResponse.getIdBackend()).thenReturn("0");
        when(sietePorVeinticuatroResponse.getDatos()).thenReturn(cnsAgendaDatosResponse);
        registros.add(cnsAgendaRegResponse);

        when(cnsAgendaDatosResponse.getReg()).thenReturn(registros);
        when(cnsAgendaRegResponse.getTipoAgendamiento()).thenReturn("I");

        when(cnsAgendaRegResponse.getXml()).thenReturn(this.buildXml());
        // when(agendaTransferenciaDAO);
        when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponse);

        List<TransferenciaAgendada> trasferenciaAgendadaList = agendaTransferenciaDAO
                .obtenerTransferenciasAgendadas(consultaAgendaTransferencias, true);

        assertNotNull(trasferenciaAgendadaList);

    }

    /**
     * Obtener agenda transferencias error test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerAgendaTransferenciasErrorTest() throws DAOException {
        try {
            ConsultaAgendaTransferencias consultaAgendaTransferencias = mock(ConsultaAgendaTransferencias.class);
            List<CNSAgendaRegResponse> registros = new ArrayList<CNSAgendaRegResponse>();
            AgendaTransferenciaDAOImpl agendaTransferenciaDAO = new AgendaTransferenciaDAOImpl();
            Cliente cliente = mock(Cliente.class);

            // mockeo respuesta de servicio
            CNSAgendaResponse sietePorVeinticuatroResponse = mock(CNSAgendaResponse.class);
            CNSAgendaDatosResponse cnsAgendaDatosResponse = mock(CNSAgendaDatosResponse.class);
            CNSAgendaRegResponse cnsAgendaRegResponse = mock(CNSAgendaRegResponse.class);

            when(cliente.getNup()).thenReturn("00276937");
            when(cliente.getUsuarioRacf()).thenReturn("FREEUSER");
            when(cliente.getPasswordRacf()).thenReturn("FREEUSER");

            when(consultaAgendaTransferencias.getCliente()).thenReturn(cliente);
            when(consultaAgendaTransferencias.getFechaDesde()).thenReturn(new Date());
            when(consultaAgendaTransferencias.getFechaHasta()).thenReturn(new Date());

            when(sietePorVeinticuatroResponse.getDatos()).thenReturn(cnsAgendaDatosResponse);
            registros.add(cnsAgendaRegResponse);
            when(cnsAgendaDatosResponse.getReg()).thenReturn(registros);
            when(cnsAgendaRegResponse.getTipoAgendamiento()).thenReturn("I");

            when(cnsAgendaRegResponse.getXml()).thenReturn(this.buildXml());

            agendaTransferenciaDAO.obtenerTransferenciasAgendadas(consultaAgendaTransferencias, true);

        } catch (DAOException ex) {
            assertNotNull(ex);
        }

    }

    /**
     * Test get transferencia sin recurrencia.
     */
    // Programada
    @Test
    public void testGetTransferenciaSinRecurrencia() {
        try {
            ConsultaAgendaTransferencias consultaAgendaTransferencias = mock(ConsultaAgendaTransferencias.class);
            List<CNSAgendaRegResponse> registros = new ArrayList<CNSAgendaRegResponse>();
            AgendaTransferenciaDAOImpl agendaTransferenciaDAO = new AgendaTransferenciaDAOImpl();
            Cliente cliente = mock(Cliente.class);

            // mockeo respuesta de servicio
            CNSAgendaResponse sietePorVeinticuatroResponse = mock(CNSAgendaResponse.class);
            CNSAgendaDatosResponse cnsAgendaDatosResponse = mock(CNSAgendaDatosResponse.class);
            CNSAgendaRegResponse cnsAgendaRegResponse = mock(CNSAgendaRegResponse.class);

            when(sietePorVeinticuatroResponse.getCodRet()).thenReturn("0");
            when(cliente.getNup()).thenReturn("00276937");
            when(cliente.getUsuarioRacf()).thenReturn("FREEUSER");
            when(cliente.getPasswordRacf()).thenReturn("FREEUSER");

            when(consultaAgendaTransferencias.getCliente()).thenReturn(cliente);
            when(consultaAgendaTransferencias.getFechaDesde()).thenReturn(new Date());
            when(consultaAgendaTransferencias.getFechaHasta()).thenReturn(new Date());

            when(sietePorVeinticuatroResponse.getDatos()).thenReturn(cnsAgendaDatosResponse);
            registros.add(cnsAgendaRegResponse);
            when(cnsAgendaDatosResponse.getReg()).thenReturn(registros);
            when(cnsAgendaRegResponse.getTipoAgendamiento()).thenReturn("I");

            // registros
            when(cnsAgendaRegResponse.getIdDef()).thenReturn("201605230000004385");
            when(cnsAgendaRegResponse.getTxServicio()).thenReturn("TRANSF_BCO_RIO");
            when(cnsAgendaRegResponse.getTxVersion()).thenReturn("120");
            when(cnsAgendaRegResponse.getCtaOrigTipo()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaOrigSuc()).thenReturn("33");
            when(cnsAgendaRegResponse.getCtaOrigNum()).thenReturn("81995");
            when(cnsAgendaRegResponse.getCtaDestTipo()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaDestSuc()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaDestNum()).thenReturn("3607390");
            when(cnsAgendaRegResponse.getCtaOrigTipoMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaOrigSucMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaOrigNumMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaDestTipoMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaDestSucMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaDestNumMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getTpoDoc()).thenReturn("0");
            when(cnsAgendaRegResponse.getNroDoc()).thenReturn("0");
            when(cnsAgendaRegResponse.getCanal()).thenReturn("04");
            when(cnsAgendaRegResponse.getSubcanal()).thenReturn("99");
            when(cnsAgendaRegResponse.getXml()).thenReturn(this.buildXml());
            // datos de grilla
            when(cnsAgendaRegResponse.getIdDatosRec()).thenReturn("201605230000004437");
            when(cnsAgendaRegResponse.getFechaBaseRec()).thenReturn("20160523123807");
            when(cnsAgendaRegResponse.getFrecuenciaRec()).thenReturn("NR");
            when(cnsAgendaRegResponse.getTipoRec()).thenReturn("E");
            // ---FIN---/
            when(cnsAgendaRegResponse.getMaximaRec()).thenReturn("0");
            when(cnsAgendaRegResponse.getMaximaFV()).thenReturn("0");
            when(cnsAgendaRegResponse.getAccionFV()).thenReturn("1");
            when(cnsAgendaRegResponse.getDiasAvisoPrevio()).thenReturn("1");
            when(cnsAgendaRegResponse.getIdEvento()).thenReturn("201605230000011461");
            // Datos de grilla
            when(cnsAgendaRegResponse.getFechaEjecucion()).thenReturn("20160523123807");
            // --FIN--//
            when(cnsAgendaRegResponse.getFechaBase()).thenReturn("20160523123807");
            when(cnsAgendaRegResponse.getRecurrencia()).thenReturn("0");

            when(cnsAgendaRegResponse.getReintentos()).thenReturn("0");
            when(cnsAgendaRegResponse.getCantFV()).thenReturn("0");
            // datos de grilla
            when(cnsAgendaRegResponse.getTipoAgendamiento()).thenReturn("E");
            when(cnsAgendaRegResponse.getEstado()).thenReturn("P");
            // fin
            when(cnsAgendaRegResponse.getUsuario()).thenReturn("FREEUSER");
            when(cnsAgendaRegResponse.getSistema()).thenReturn("ONLINE");
            // dato grilla
            when(cnsAgendaRegResponse.getAccion()).thenReturn("A");
            // fin
            when(cnsAgendaRegResponse.getFecha()).thenReturn("20160523123807");

            agendaTransferenciaDAO.obtenerTransferenciasAgendadas(consultaAgendaTransferencias, true);
        } catch (DAOException ex) {
            assertNotNull(ex);
        }
    }

    /**
     * Test get transferencia tipo recordatorio.
     */
    @Test
    public void testGetTransferenciaTipoRecordatorio() {
        try {
            ConsultaAgendaTransferencias consultaAgendaTransferencias = mock(ConsultaAgendaTransferencias.class);
            List<CNSAgendaRegResponse> registros = new ArrayList<CNSAgendaRegResponse>();
            AgendaTransferenciaDAOImpl agendaTransferenciaDAO = new AgendaTransferenciaDAOImpl();
            Cliente cliente = mock(Cliente.class);

            // mockeo respuesta de servicio
            CNSAgendaResponse sietePorVeinticuatroResponse = mock(CNSAgendaResponse.class);
            CNSAgendaDatosResponse cnsAgendaDatosResponse = mock(CNSAgendaDatosResponse.class);
            CNSAgendaRegResponse cnsAgendaRegResponse = mock(CNSAgendaRegResponse.class);

            when(sietePorVeinticuatroResponse.getCodRet()).thenReturn("0");
            when(cliente.getNup()).thenReturn("00276937");
            when(cliente.getUsuarioRacf()).thenReturn("FREEUSER");
            when(cliente.getPasswordRacf()).thenReturn("FREEUSER");

            when(consultaAgendaTransferencias.getCliente()).thenReturn(cliente);
            when(consultaAgendaTransferencias.getFechaDesde()).thenReturn(new Date());
            when(consultaAgendaTransferencias.getFechaHasta()).thenReturn(new Date());

            when(sietePorVeinticuatroResponse.getDatos()).thenReturn(cnsAgendaDatosResponse);
            registros.add(cnsAgendaRegResponse);
            when(cnsAgendaDatosResponse.getReg()).thenReturn(registros);
            when(cnsAgendaRegResponse.getTipoAgendamiento()).thenReturn("I");

            // registros
            when(cnsAgendaRegResponse.getIdDef()).thenReturn("201605230000004385");
            when(cnsAgendaRegResponse.getTxServicio()).thenReturn("TRANSF_BCO_RIO");
            when(cnsAgendaRegResponse.getTxVersion()).thenReturn("120");
            when(cnsAgendaRegResponse.getCtaOrigTipo()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaOrigSuc()).thenReturn("33");
            when(cnsAgendaRegResponse.getCtaOrigNum()).thenReturn("81995");
            when(cnsAgendaRegResponse.getCtaDestTipo()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaDestSuc()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaDestNum()).thenReturn("3607390");
            when(cnsAgendaRegResponse.getCtaOrigTipoMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaOrigSucMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaOrigNumMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaDestTipoMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaDestSucMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaDestNumMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getTpoDoc()).thenReturn("0");
            when(cnsAgendaRegResponse.getNroDoc()).thenReturn("0");
            when(cnsAgendaRegResponse.getCanal()).thenReturn("04");
            when(cnsAgendaRegResponse.getSubcanal()).thenReturn("99");
            when(cnsAgendaRegResponse.getXml()).thenReturn(this.buildXml());
            // datos de grilla
            when(cnsAgendaRegResponse.getIdDatosRec()).thenReturn("201605230000004437");
            when(cnsAgendaRegResponse.getFechaBaseRec()).thenReturn("20160523123807");
            when(cnsAgendaRegResponse.getFrecuenciaRec()).thenReturn("M12001");
            when(cnsAgendaRegResponse.getTipoRec()).thenReturn("I");
            // ---FIN---/
            when(cnsAgendaRegResponse.getMaximaRec()).thenReturn("0");
            when(cnsAgendaRegResponse.getMaximaFV()).thenReturn("0");
            when(cnsAgendaRegResponse.getAccionFV()).thenReturn("1");
            when(cnsAgendaRegResponse.getDiasAvisoPrevio()).thenReturn("1");
            when(cnsAgendaRegResponse.getIdEvento()).thenReturn("201605230000011461");
            // Datos de grilla
            when(cnsAgendaRegResponse.getFechaEjecucion()).thenReturn("20160620000000");
            // --FIN--//
            when(cnsAgendaRegResponse.getFechaBase()).thenReturn("20160523123807");
            when(cnsAgendaRegResponse.getRecurrencia()).thenReturn("0");

            when(cnsAgendaRegResponse.getReintentos()).thenReturn("0");
            when(cnsAgendaRegResponse.getCantFV()).thenReturn("0");
            // datos de grilla
            when(cnsAgendaRegResponse.getTipoAgendamiento()).thenReturn("I");
            when(cnsAgendaRegResponse.getEstado()).thenReturn("P");
            // fin
            when(cnsAgendaRegResponse.getUsuario()).thenReturn("FREEUSER");
            when(cnsAgendaRegResponse.getSistema()).thenReturn("ONLINE");
            // dato grilla
            when(cnsAgendaRegResponse.getAccion()).thenReturn("A");
            // fin
            when(cnsAgendaRegResponse.getFecha()).thenReturn("20160523123807");

            agendaTransferenciaDAO.obtenerTransferenciasAgendadas(consultaAgendaTransferencias, true);
        } catch (DAOException ex) {
            assertNotNull(ex);
        }
    }

    /**
     * Test get transferencia con recurrencia.
     */
    // Automatica
    @Test
    public void testGetTransferenciaConRecurrencia() {
        try {
            ConsultaAgendaTransferencias consultaAgendaTransferencias = mock(ConsultaAgendaTransferencias.class);
            List<CNSAgendaRegResponse> registros = new ArrayList<CNSAgendaRegResponse>();
            AgendaTransferenciaDAOImpl agendaTransferenciaDAO = new AgendaTransferenciaDAOImpl();
            Cliente cliente = mock(Cliente.class);

            // mockeo respuesta de servicio
            CNSAgendaResponse sietePorVeinticuatroResponse = mock(CNSAgendaResponse.class);
            CNSAgendaDatosResponse cnsAgendaDatosResponse = mock(CNSAgendaDatosResponse.class);
            CNSAgendaRegResponse cnsAgendaRegResponse = mock(CNSAgendaRegResponse.class);

            when(sietePorVeinticuatroResponse.getCodRet()).thenReturn("0");
            when(cliente.getNup()).thenReturn("00276937");
            when(cliente.getUsuarioRacf()).thenReturn("FREEUSER");
            when(cliente.getPasswordRacf()).thenReturn("FREEUSER");

            when(consultaAgendaTransferencias.getCliente()).thenReturn(cliente);
            when(consultaAgendaTransferencias.getFechaDesde()).thenReturn(new Date());
            when(consultaAgendaTransferencias.getFechaHasta()).thenReturn(new Date());

            when(sietePorVeinticuatroResponse.getDatos()).thenReturn(cnsAgendaDatosResponse);
            registros.add(cnsAgendaRegResponse);
            when(cnsAgendaDatosResponse.getReg()).thenReturn(registros);
            when(cnsAgendaRegResponse.getTipoAgendamiento()).thenReturn("I");

            // registros
            when(cnsAgendaRegResponse.getIdDef()).thenReturn("201605230000004385");
            when(cnsAgendaRegResponse.getTxServicio()).thenReturn("TRANSF_BCO_RIO");
            when(cnsAgendaRegResponse.getTxVersion()).thenReturn("120");
            when(cnsAgendaRegResponse.getCtaOrigTipo()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaOrigSuc()).thenReturn("33");
            when(cnsAgendaRegResponse.getCtaOrigNum()).thenReturn("81995");
            when(cnsAgendaRegResponse.getCtaDestTipo()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaDestSuc()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaDestNum()).thenReturn("3607390");
            when(cnsAgendaRegResponse.getCtaOrigTipoMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaOrigSucMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaOrigNumMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaDestTipoMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaDestSucMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getCtaDestNumMig()).thenReturn("0");
            when(cnsAgendaRegResponse.getTpoDoc()).thenReturn("0");
            when(cnsAgendaRegResponse.getNroDoc()).thenReturn("0");
            when(cnsAgendaRegResponse.getCanal()).thenReturn("04");
            when(cnsAgendaRegResponse.getSubcanal()).thenReturn("99");
            when(cnsAgendaRegResponse.getXml()).thenReturn(this.buildXml());
            // datos de grilla
            when(cnsAgendaRegResponse.getIdDatosRec()).thenReturn("201605230000004437");
            when(cnsAgendaRegResponse.getFechaBaseRec()).thenReturn("20160523123807");
            when(cnsAgendaRegResponse.getFrecuenciaRec()).thenReturn("M12001");
            when(cnsAgendaRegResponse.getTipoRec()).thenReturn("E");
            // ---FIN---/
            when(cnsAgendaRegResponse.getMaximaRec()).thenReturn("0");
            when(cnsAgendaRegResponse.getMaximaFV()).thenReturn("0");
            when(cnsAgendaRegResponse.getAccionFV()).thenReturn("1");
            when(cnsAgendaRegResponse.getDiasAvisoPrevio()).thenReturn("1");
            when(cnsAgendaRegResponse.getIdEvento()).thenReturn("201605230000011461");
            // Datos de grilla
            when(cnsAgendaRegResponse.getFechaEjecucion()).thenReturn("20160523121948");
            // --FIN--//
            when(cnsAgendaRegResponse.getFechaBase()).thenReturn("20160523123807");
            when(cnsAgendaRegResponse.getRecurrencia()).thenReturn("0");

            when(cnsAgendaRegResponse.getReintentos()).thenReturn("0");
            when(cnsAgendaRegResponse.getCantFV()).thenReturn("0");
            // datos de grilla
            when(cnsAgendaRegResponse.getTipoAgendamiento()).thenReturn("E");
            when(cnsAgendaRegResponse.getEstado()).thenReturn("P");
            // fin
            when(cnsAgendaRegResponse.getUsuario()).thenReturn("FREEUSER");
            when(cnsAgendaRegResponse.getSistema()).thenReturn("ONLINE");
            // dato grilla
            when(cnsAgendaRegResponse.getAccion()).thenReturn("A");
            // fin
            when(cnsAgendaRegResponse.getFecha()).thenReturn("20160523123807");

            agendaTransferenciaDAO.obtenerTransferenciasAgendadas(consultaAgendaTransferencias, true);
        } catch (DAOException ex) {
            assertNotNull(ex);
        }
    }

    /**
     * Builds the xml.
     *
     * @return the string
     */
    private String buildXml() {
        StringBuffer str = new StringBuffer();
        str.append("<xml>");
        str.append(
                "<config><servicio>TRANSF_BCO_RIO</servicio><ejecucion modo='I'/><canal tipo='04' id='HTML' version='000'/>");
        str.append(
                "<subcanal tipo='99' id='0001'/></config><datos><NombreCtaCredito>FLAVIANO NATAL RODAS MACHADO</NombreCtaCredito>");
        str.append("</datos></xml>");
        return str.toString();
    }

}
