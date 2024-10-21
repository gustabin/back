/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.dao;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.util.ReflectionTestUtils;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.SessionUsuarioData;
import ar.com.santanderrio.obp.base.iatx.helpers.IatxSender;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.clientes.service.ClienteService;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.transferencias.dao.impl.TransferenciaDAOImpl;
import ar.com.santanderrio.obp.servicios.transferencias.dao.impl.TransferenciaModtrfe;
import ar.com.santanderrio.obp.servicios.transferencias.entities.CaracteristicasCuentaCredito;
import ar.com.santanderrio.obp.servicios.transferencias.entities.IndicadorFuncion;
import ar.com.santanderrio.obp.servicios.transferencias.entities.PlazoAcreditacion;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TipoCuentaBanelco;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaOrigenSinBanelcoException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.DestinatarioNoVerificadoException;

/**
 * The Class TransferenciaDAOTest.
 *
 * @author Manuel Vargas : B041299
 */
@RunWith(MockitoJUnitRunner.class)
public class TransferenciaDAOTest{

    /** The sesion cliente. */
    @Mock
    SesionCliente sesionCliente;
    
    /** The transferencia DAO. */
    @InjectMocks
    private TransferenciaDAOImpl transferenciaDAO;

    /** The cliente service. */
    @Mock
    private ClienteService clienteService;

    /** The mensaje manager. */
    @Mock
    MensajeManager mensajeManager;
    
    @Mock
    private IatxSender iatxSender;
    
    @Mock
    private IatxComm iatxComm;
    
    @Mock
	private SesionParametros sesionParametros;
    
    /** The TRFCT A 170 response OK. */
    // Responses de servicios:
    private String TRFCTA_170_ResponseOK = "200000000000P04HTML0009900010301193074  ********00086151000000892016052609295700000000IBF17347000000000000BAJDDIADHE1000000            01193074    00        00 009272077201605260929540000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0007700000000õ02õ127õ03628302õ00010439002õHARDOY  LILIANA ESTER         õ19520519õ";
    
    private String TRFCTA_170_ResponseOk2 = "200000000000P04HTML0009900010302GLPE92  ********00678615000000062017062113492300000000IBF006VZ000000002805TRFCCI____1300000            02615492    00        00 000002805201706211349170000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHö0004800000000ö805ö9990ö00002805ö00000000300ö20170621ö";
    
    /** The TRFCT A 170 resp E R 100000058 saldo insuficiente. */
    private String TRFCTA_170_Resp_ER_100000058_SaldoInsuficiente = "200000000000P04HTML0009900010300MRQT37  ********00252689000000192016091411292400000000IBF24319000000000000TRFCCI____1300000            00276937    00        00 000000000201609141129190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810000058õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õSALDO INSUFICIENTE                                                              õ";

    /** The TRFCT A 170 resp E R 100000515 saldo insuficiente. */
    private String TRFCTA_170_Resp_ER_100000515_SaldoInsuficiente = "200000000000P04HTML0009900010300MRQT37  ********00252689000000192016091411292400000000IBF24319000000000000TRFCCI____1300000            00276937    00        00 000000000201609141129190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810000515õABGõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õSALDO INSUFICIENTE                                                              õ";

    /** The TRFCT A 170 resp E R 100002122 saldo insuficiente. */
    private String TRFCTA_170_Resp_ER_100002122_SaldoInsuficiente = "200000000000P04HTML0009900010300MRQT37  ********00252689000000192016091411292400000000IBF24319000000000000TRFCCI____1300000            00276937    00        00 000000000201609141129190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810000122õABGõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õSALDO INSUFICIENTE                                                              õ";

    /** The TRFCT A 170 resp E R 10002065 cta bloqueada. */
    private String TRFCTA_170_Resp_ER_10002065_CtaBloqueada = "200000000000P04HTML0009900010302QLPO92  ********00252714000000122016091412054200000000IBF26341000012025419TRFCTA____1700000            02615492    00        00 012025419201609141205380000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0033810002065õABGõ03õNo es posible realizar debitos o acreditaciones en la cuenta seleccionada. Por favor comuniquese con su sucursal.                                                                                                                                             õCta. Bloqueada õBGE2065 - CTA BLOQ.MOTIVO 22-LEY 27260. ART. 4 AUTõ";

    /** The TRFCC I 130 resp E R 10000058 saldo insuficiente. */
    // 10000058 y 10000059
    private String TRFCCI_130_Resp_ER_10000058_SaldoInsuficiente = "200000000000P04HTML0009900010300RONN15  ********00683641000000402016090616263500000000IBF26826000000000000TRFCCI____1300000            00743315    00        00 000000000201609061626310000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810000058õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õSALDO INSUFICIENTE                                                              õ";
    // Error. Excede límite diario. Usuario H2: 13238861. Error ZKE0004. US
    /** The TRFCT A 170 resp E R 10000004 EX lim diario. */
    // 19831.
    private String TRFCTA_170_Resp_ER_10000004_EXLimDiario = "200000000000P04HTML0009900010302QLPO92  ********00360085000000382016090614542800000000IBF21762000014529573TRFCTA____1700000            02615492    00        00 014529573201609061454240000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0033210000004õAZKõ03õLa operacion solicitada no puede ser realizada debido a que supera el limite maximo diario por canales.                                                                                                                                                       õExc. limite diaõZKE0004 - SE EXCEDE. DISPONIBLE : SUPERA LIMõ";

    /** The CNSTITCB U 110 response OK. */
    // Consulta
    private String CNSTITCBU_110_ResponseOK = "200000000000P04HTML0009900010340423049  ********00776493000000102016030112234000000000IBF18016000000000000CNSTITCBU_1100000            40423049    00        00 012204867201603011224100000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0018900000000õCASTILLO MARIANO ELBIO                                          õ20246461164õ           õ           õBANCO DE GALICIA      õ12õ402570440414                õ01õ11õGLCAõ0000õ0000000õ";
    // respuesta del servicio TRFCTA_ 170 o TRFCCI 130. la cuenta Origen está
    /** The TRFCT A 170 resp E R 0000117 cta orig inhabilitada. */
    // inhabilitada por no ser usada en 180 días.
    private String TRFCTA_170_Resp_ER_0000117_CtaOrigInhabilitada = "200000000000P04HTML0009900010300SRMR42  ********00360099000000082016090715050300000000IBF22463000015030556TRFCTA____1700000            00872742    00        00 015030556201609071504580000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0033410000117õAZBõ03õLa cuenta supera 180 dias sin operar.                                                                                                                                                                                                                         õ180 DIAS S/OPERõZBE0117 - LA CUENTA SUPERA 180 DIAS SIN OPERARõ";
    // respuesta del servicio TRFCTA_ 170 o TRFCCI 130. la cuenta Destino está
    /** The TRFCT A 170 resp E R 0000117 cta dest inhabilitada. */
    // inhabilitada por no ser usada en 180 días.
    private String TRFCTA_170_Resp_ER_0000117_CtaDestInhabilitada = "200000000000P04HTML0009900010300RONN15  ********00508269000000192016090815021300000000IBF21208000015032362TRFCTA____1700000            00743315    00        00 015032362201609081502080000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0033410000117õAZBõ03õLa cuenta supera 180 dias sin operar.                                                                                                                                                                                                                         õ180 DIAS S/OPERõZBE0117 - LA CUENTA SUPERA 180 DIAS SIN OPERARõ";

    /** The TRFCC I 130 resp E R 10000004 lim max. */
    // importe ingresado supera el límite máximo permitido.
    private String TRFCCI_130_Resp_ER_10000004_LimMax = "200000000000P04HTML0009900010300RONN15  ********00683655000000192016090911161900000000IBF25171000000000000TRFCCI____1300000            00743315    00        00 000000000201609091116150000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036510000004õAZKõ03õLa operacion solicitada no puede ser realizada debido a que supera el limite maximo diario por canales.                                                                                                                                                       õExc. limite diaõZKE0004-SE EXCEDE. DISPONIBLE : SUPERA                                       õ";

    /** The TRFCT A 170 response OK. */
    private String CNSMODTRFE_140_ResponseOK = "200000000000Q04HTML0009900010302QLPO92  XXXXXXXX00099190000000122017011816364800000000        00000000CNSMODTRFE1100000            02615492    00        00  IN999026154921959100500000        0000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0001500õ000õ0000000õ";

    /** The TRFCT aname. */
    // Servicios
    private String TRFCTAname = "TRFCTA____";

    /** The TRFCT aver. */
    private String TRFCTAver = "170";

    /** The TRFCC iname. */
    private String TRFCCIname = "TRFCCI____";

    /** The TRFCC iver. */
    private String TRFCCIver = "130";

    /** The CNSTITCB uname. */
    private String CNSTITCBUname = "CNSTITCBU";

    /** The CNSTITCB uversion. */
    private String CNSTITCBUversion = "110";

    /** The cliente. */
    private Cliente cliente = new Cliente();

    /** The cuenta. */
    private Cuenta cuenta = new Cuenta();

    /** The resultado T xdeseado. */
    private TransferenciaDTO resultadoTXdeseado = new TransferenciaDTO();
    
    /** The registro de sesion. */
    private RegistroSesion rs;
    
    /**
     * Inits the.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Before
    public void init() throws ServiceException {
    	
    	rs = new RegistroSesion();
    	rs.setIp("0:0:0:0:0:0:0:1");
    	
        /*
         * // cuentas respuestaCuentasViewOk = new Respuesta<CuentasView>();
         * CuentasView cuentasView = new CuentasView(); List<CuentaView>
         * listCuentasView = new ArrayList<CuentaView>(); CuentaView cuentaView1
         * = new CuentaView(); CuentaView cuentaView2 = new CuentaView();
         * cuentaView1.setIsCerrada(false); listCuentasView.add(cuentaView1);
         * cuentaView2.setIsCerrada(false); listCuentasView.add(cuentaView2);
         * cuentasView.setCuentas(listCuentasView);
         * respuestaCuentasViewOk.setEstadoRespuesta(EstadoRespuesta.OK);
         * respuestaCuentasViewOk.setRespuesta(cuentasView);
         */

        cliente = new Cliente();
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("CONSTANCIO PERCY");
        cliente.setApellido1("MILANDO");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);

        cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
        cuenta.setSucursalPaquete("0033");
        cuenta.setNroCuentaProducto("000000081995");
        cuenta.setNroOrdenFirmante("01");
        resultadoTXdeseado = new TransferenciaDTO();
        resultadoTXdeseado.setCuentaOrigen(cuenta);
        resultadoTXdeseado.setTipoTransferencia((byte) 3);
        resultadoTXdeseado.setImporte(new BigDecimal(22000));
        resultadoTXdeseado.setMoneda(DivisaEnum.PESO);
        resultadoTXdeseado.setInformacionDiscrecional("                    ");
//        resultadoTXdeseado.setIdentificacionBeneficiario("20101340008");
        resultadoTXdeseado.setMarcaLimite("S");
        resultadoTXdeseado.setCaracteristicasCuentaCredito(CaracteristicasCuentaCredito.INMEDIATO);
        resultadoTXdeseado.setPlazoAcreditacion(PlazoAcreditacion.INMEDIATO);
//        resultadoTXdeseado.setPeriodica("N");
//        resultadoTXdeseado.setCantidadDias((short) 000);
        resultadoTXdeseado.setIp("180166225074");
        resultadoTXdeseado.setTitular("LOPEZ LLOVET JORGE");
        resultadoTXdeseado.setCuit("20101340008");
        resultadoTXdeseado.setCuit2("           ");
        resultadoTXdeseado.setCuit3("           ");
        resultadoTXdeseado.setCuentaDestinoBanelco("440273450026");
        resultadoTXdeseado.setTipoDeCuentaToBanelco(TipoCuentaBanelco.TIPO_01);
        resultadoTXdeseado.setTipoDeCuentaFromBanelco(TipoCuentaBanelco.TIPO_11);
        resultadoTXdeseado.setEmail("");
        resultadoTXdeseado.setComentario("");
        resultadoTXdeseado.setConceptoAdicional(null);
        resultadoTXdeseado.setDescripcionAdicional(null);
        resultadoTXdeseado.setConceptoAdicional2(null);
        resultadoTXdeseado.setDescripcionAdicional2(null);
        resultadoTXdeseado.setConceptoAdicional3(null);
        resultadoTXdeseado.setDescripcionAdicional3(null);
        resultadoTXdeseado.setConcepto(ConceptoTransferenciaEnum.VARIOS);
        resultadoTXdeseado.setInformacionAdicional("Varios");
        resultadoTXdeseado.setCbuCuenta("0070002330004402734562");
        resultadoTXdeseado.setFiid("0000");
        resultadoTXdeseado.setUser("0000000");
        resultadoTXdeseado.setBancoReceptor("GLCA");
        resultadoTXdeseado.setCodigoConcepto("7");
        resultadoTXdeseado.setBancoDestino("BANCO DE GALICIA");
        resultadoTXdeseado.setAgenda("N");
        
        ReflectionTestUtils.setField(transferenciaDAO, "errorBanelcoCoelsaHabilitado", "1");

    }

    /**
     * The Class TransferenciaDAOTestConfiguration.
     */
    @Configuration
    public static class TransferenciaDAOTestConfiguration {

        /**
         * Transferencia DAO.
         *
         * @return the transferencia DAO
         */
        @Bean
        public TransferenciaDAO transferenciaDAO() {
            return new TransferenciaDAOImpl();
        }
    }

    /**
     * Test is cliente actualizado para transferir.
     */
    @Test
    @Ignore
    public void testIsClienteActualizadoParaTransferir() {

    }

    /**
     * Test validar origen destino transferencia. Mocks iatSender con CNSTITCBU
     * version 110.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     * @throws DestinatarioNoVerificadoException
     *             the destinatario no verificado exception
     */
    //TODO: corregir sacar ignore
    @Test
    @Ignore
    public void testValidarOrigenDestinoTransferencia()
            throws DAOException, IatxException, DestinatarioNoVerificadoException {
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(CNSTITCBUname, CNSTITCBUversion))))
                .thenReturn(CNSTITCBU_110_ResponseOK);
//        TransferenciaDTO resultadoTX = this.transferenciaDAO.validarOrigenDestinoTransferencia(cliente,
//                resultadoTXdeseado);
//        Assert.assertNotNull(resultadoTX);
//        Assert.assertEquals(resultadoTX, resultadoTXdeseado);
    }

    /**
     * Realizar transferencia 2.
     *
     * @throws DAOException the DAO exception
     * @throws IatxException the iatx exception
     * @throws CuentaOrigenSinBanelcoException 
     */
    @Test
    public void realizarTransferencia2() throws DAOException, IatxException, CuentaOrigenSinBanelcoException{
    	Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(TRFCCIname, TRFCCIver))))
        	.thenReturn(TRFCTA_170_ResponseOk2);
    	
    	Mockito.when(iatxComm.exec(Matchers.any(IatxRequest.class)))
    		.thenReturn(crearIatxResponse());
    	Mockito.when(sesionCliente.obtenerIpV4SinPuntos())
    		.thenReturn("000000000000");
    	
    	ApplicationContext context = Mockito.mock(ApplicationContext.class);
    	SessionUsuarioData sesionUsuarioData = new SessionUsuarioData();
    	sesionUsuarioData.setIatxNroReqSessionName("5");
    	sesionUsuarioData.setIatxSessionUserName("371769");
    	ContextHolder.setContext(context);
    	Mockito.when(context.getBean(SessionUsuarioData.class))
    		.thenReturn(sesionUsuarioData);
    	
    	SimpleDateFormat dateParser = new SimpleDateFormat("yyyyMMddHHmmss");
    	TransferenciaDTO resultadoTXdeseado = new TransferenciaDTO();
        resultadoTXdeseado = crearResultadoTX(resultadoTXdeseado);
//        resultadoTXdeseado.setNroComprobante("11302889");
        try {
			resultadoTXdeseado.setFechaDeOperacion(dateParser.parse("20170628113137"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        TransferenciaDTO resultadoTX = this.transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(cliente, resultadoTXdeseado,
                IndicadorFuncion.LIQUIDACION);
        
        Assert.assertEquals(resultadoTXdeseado, resultadoTX);
    }
    
    
    /**
     * Test realizar transferencia. Mock de iatSender.send. Varios casos: iatx
     * service TRFCTA____170:
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     * @throws CuentaOrigenSinBanelcoException 
     */
	@Test
	@Ignore
    public void testRealizarTransferencia() throws DAOException, IatxException, CuentaOrigenSinBanelcoException {
    	
    	ApplicationContext context = Mockito.mock(ApplicationContext.class);
    	SessionUsuarioData sesionUsuarioData = new SessionUsuarioData();
    	sesionUsuarioData.setIatxNroReqSessionName("5");
    	sesionUsuarioData.setIatxSessionUserName("371769");
    	ContextHolder.setContext(context);
    	Mockito.when(context.getBean(SessionUsuarioData.class))
    		.thenReturn(sesionUsuarioData);
		
		
    	Mockito.when(sesionParametros.getRegistroSession()).thenReturn(rs);
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(TRFCCIname, TRFCCIver))))
                .thenReturn(TRFCTA_170_ResponseOk2);
        IatxResponse response = new IatxResponse();
        response.setEstadoRespuesta(EstadoRespuesta.OK);
		when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(response );
        //
        TransferenciaDTO resultadoTXdeseado = new TransferenciaDTO();
        resultadoTXdeseado = crearResultadoTX(resultadoTXdeseado);
        
        TransferenciaDTO resultadoTX = this.transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(cliente, resultadoTXdeseado,
                IndicadorFuncion.LIQUIDACION);
        //
        Assert.assertNotNull(resultadoTX);
        Assert.assertEquals(resultadoTX, resultadoTXdeseado);
    }
    
    
    public IatxResponse crearIatxResponse(){
    	IatxResponse iatxResponse = new IatxResponse();
    	iatxResponse.setErrorCode(0);
    	iatxResponse.setErrorMessage("");
    	iatxResponse.setErrorSystem("");
    	iatxResponse.setEstadoRespuesta(EstadoRespuesta.OK);
    	iatxResponse.setFecha("20170628");
    	iatxResponse.setFechaHoraReq("20170628113137");
    	iatxResponse.setHora("11:31:28");
    	iatxResponse.setNroComprobante("11302889");
    	iatxResponse.setSesionUsuario("00371770");
    	iatxResponse.setWarningOk(false);
    	return iatxResponse;
    }

    public TransferenciaDTO crearTransferenciaMock(){
    	TransferenciaDTO transferencia = new TransferenciaDTO();
    	transferencia.setTipoDeCuentaFromBanelco(TipoCuentaBanelco.TIPO_02);
    	transferencia.setTipoDeCuentaToBanelco(TipoCuentaBanelco.TIPO_02);
    	return transferencia;
    }
    /**
     * Test errores al realizar transferencia. Mock del metodo iatSender.send().
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     * @throws CuentaOrigenSinBanelcoException 
     * @see US 19831 - DTF Nueva transferencia - Casuisticas alternativas. iatx
     *      service TRFCTA____170, varios casos: 100000058 saldo insuficiente.
     *      Message set case: CODIGO_ERROR_SALDO_INSUFICIENTE_2. 10002065 cuenta
     *      bloqueda. Message set case: . 100000515 Saldo Insuficiente en cuenta
     *      origen. 100002122 Saldo Insuficiente en cuenta origen.
     */
    @Test
    @Ignore
    public void testRealizarTransferenciasConError() throws DAOException, IatxException, CuentaOrigenSinBanelcoException {
        //
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(TRFCCIname, TRFCCIver))))
                .thenReturn(TRFCTA_170_Resp_ER_100000058_SaldoInsuficiente);
        TransferenciaDTO resultadoTXdeseado = new TransferenciaDTO();
        TransferenciaDTO resultadoTX = this.transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(cliente,
                crearResultadoTX(resultadoTXdeseado), IndicadorFuncion.LIQUIDACION);
        Assert.assertNotNull(resultadoTX);
        Assert.assertEquals(resultadoTX, resultadoTXdeseado);
        //
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(TRFCCIname, TRFCCIver))))
                .thenReturn(TRFCTA_170_Resp_ER_100000515_SaldoInsuficiente);
        resultadoTXdeseado = new TransferenciaDTO();
        resultadoTX = this.transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(cliente, crearResultadoTX(resultadoTXdeseado),
                IndicadorFuncion.LIQUIDACION);
        Assert.assertNotNull(resultadoTX);
        Assert.assertEquals(resultadoTX, resultadoTXdeseado);
        //
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(TRFCCIname, TRFCCIver))))
                .thenReturn(TRFCTA_170_Resp_ER_100002122_SaldoInsuficiente);
        resultadoTXdeseado = new TransferenciaDTO();
        resultadoTX = this.transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(cliente, crearResultadoTX(resultadoTXdeseado),
                IndicadorFuncion.LIQUIDACION);
        Assert.assertNotNull(resultadoTX);
        Assert.assertEquals(resultadoTX, resultadoTXdeseado);
        //
        // when(iatxSender.send(Matchers.argThat(new IatxMatcher(TRFCCIname,
        // TRFCCIver)))).thenReturn(TRFCTA_170_Resp_ER_10002065_CtaBloqueada);
        // resultadoTXdeseado = new Transferencia();
        // resultadoTX = this.transferenciaDAO.realizarTransferencia(cliente,
        // crearResultadoTX(resultadoTXdeseado), IndicadorFuncion.LIQUIDACION);
        // Assert.assertNotNull(resultadoTX);
        // Assert.assertEquals(resultadoTX, resultadoTXdeseado);
        //
    }

    /**
     * Realizar transferencia rio test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws CuentaOrigenSinBanelcoException 
     */
    @Ignore
    @Test
    public void realizarTransferenciaRioTest() throws IatxException, DAOException, CuentaOrigenSinBanelcoException {

        TransferenciaDTO transferencia = new TransferenciaDTO();
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        transferencia.setCuentaOrigen(cuentaOrigen);
        BigDecimal importe = new BigDecimal(32);
        transferencia.setImporte(importe);
        Cliente cliente = new Cliente();
        Segmento segmento = new Segmento();
        segmento.setUniversidad(false);
        cliente.setSegmento(segmento);
        transferencia.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);

        String transferenciaResponseOk = "200000000000P04HTML0009900010302QLPO92  ********00360085000000122016090614434000000000IBF21122000014429036TRFCTA____1700000            02615492    00        00 014429036201609061443350000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0035710002122õABGõ03õEl saldo de la cuenta debito es insuficiente para realizar la transaccion.                                                                                                                                                                                    õDisp.insuficienõBGE2122 - DISPONIBLE INSUF. EN CTA. AHORRO             1.810,95  AUT.õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("TRFCTA____", "170"))))
                .thenReturn(transferenciaResponseOk);
        TransferenciaDTO resultado = transferenciaDAO.realizarTransferenciaInmediataRioRio(cliente, transferencia);
        Assert.assertNotNull(resultado);

    }

    /**
     * Crear resultado TX.
     *
     * @param resultadoTXdeseado
     *            the resultado T xdeseado
     * @return the transferencia
     */
    private TransferenciaDTO crearResultadoTX(TransferenciaDTO resultadoTXdeseado) {
        cuenta = new Cuenta();
        cuenta.setTipoCuenta("0");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
        cuenta.setSucursalPaquete("0033");
        cuenta.setNroCuentaProducto("000000081995");
        cuenta.setNroOrdenFirmante("01");
        resultadoTXdeseado.setCuentaOrigen(cuenta);
        resultadoTXdeseado.setTipoTransferencia((byte) 3);
        resultadoTXdeseado.setImporte(new BigDecimal(22000));
        resultadoTXdeseado.setMoneda(DivisaEnum.PESO);
        resultadoTXdeseado.setInformacionDiscrecional("                    ");
        resultadoTXdeseado.setMarcaLimite("S");
        resultadoTXdeseado.setCaracteristicasCuentaCredito(CaracteristicasCuentaCredito.INMEDIATO);
        resultadoTXdeseado.setPlazoAcreditacion(PlazoAcreditacion.INMEDIATO);
//        resultadoTXdeseado.setPeriodica("N");
//        resultadoTXdeseado.setCantidadDias((short) 000);
        resultadoTXdeseado.setIp("180166225074");
        resultadoTXdeseado.setTitular("LOPEZ LLOVET JORGE");
        resultadoTXdeseado.setCuit("20101340008");
        resultadoTXdeseado.setCuit2("           ");
        resultadoTXdeseado.setCuit3("           ");
        resultadoTXdeseado.setCuentaDestinoBanelco("440273450026");
        resultadoTXdeseado.setTipoDeCuentaToBanelco(TipoCuentaBanelco.TIPO_01);
        resultadoTXdeseado.setTipoDeCuentaFromBanelco(TipoCuentaBanelco.TIPO_11);
        resultadoTXdeseado.setEmail("");
        resultadoTXdeseado.setComentario("");
        resultadoTXdeseado.setConceptoAdicional(null);
        resultadoTXdeseado.setDescripcionAdicional(null);
        resultadoTXdeseado.setConceptoAdicional2(null);
        resultadoTXdeseado.setDescripcionAdicional2(null);
        resultadoTXdeseado.setConceptoAdicional3(null);
        resultadoTXdeseado.setDescripcionAdicional3(null);
        resultadoTXdeseado.setConcepto(ConceptoTransferenciaEnum.VARIOS);
        resultadoTXdeseado.setInformacionAdicional("Varios");
        resultadoTXdeseado.setCbuCuenta("0070002330004402734562");
        resultadoTXdeseado.setFiid("0000");
        resultadoTXdeseado.setUser("0000000");
        resultadoTXdeseado.setBancoReceptor("GLCA");
        resultadoTXdeseado.setCodigoConcepto("7");
        resultadoTXdeseado.setBancoDestino("BANCO DE GALICIA");
        resultadoTXdeseado.setAgenda("N");
        resultadoTXdeseado.setHaciaOtroBanco(false);
        return resultadoTXdeseado;
    }

    /**
     * Test generar comprobante transferencia.
     */
    @Test
    @Ignore
    public void testGenerarComprobanteTransferencia() {
    }

    /**
     * Obtener cliente.
     *
     * @return the cliente
     * @throws ServiceException
     *             the service exception
     */
    private Cliente obtenerCliente() throws ServiceException {
        CredencialCliente credenciales = new CredencialCliente();
        credenciales.setIp("0:0:0:0:0:0:0:1");
        Respuesta<ResumenCliente> resumenCliente = clienteService.validarCredenciales(credenciales);
        Cliente cliente = new Cliente();
        Segmento segmento = new Segmento();

        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);

        cliente.setNombre("CONSTANCIO PERCY");
        cliente.setApellido1("MILANDO");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);
        return cliente;
    }

    /**
     * Obtener tarjeta.
     *
     * @param cliente
     *            the cliente
     * @return the cuenta
     */
    private Cuenta obtenerTarjeta(Cliente cliente) {
        List<Cuenta> cuentas = cliente.getCuentas();
        for (Cuenta cuenta : cuentas) {
            String tipoCuenta = cuenta.getTipoCuenta();
            if ("06".equals(tipoCuenta) || "07".equals(tipoCuenta) || "42".equals(tipoCuenta)) {
                return cuenta;
            }
        }
        return null;
    }
    
    /**
     * Test validar origen destino transferencia. Mocks iatSender con CNSTITCBU
     * version 110.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IatxException
     *             the iatx exception
     * @throws DestinatarioNoVerificadoException
     *             the destinatario no verificado exception
     */
    @Test
    public void testIsClienteAdheridoAServicioPagoHaberesOK()
            throws DAOException, IatxException, DestinatarioNoVerificadoException {
    	ApplicationContext context = Mockito.mock(ApplicationContext.class);
    	SessionUsuarioData sesionUsuarioData = new SessionUsuarioData();
    	sesionUsuarioData.setIatxNroReqSessionName("5");
    	sesionUsuarioData.setIatxSessionUserName("371769");
    	ContextHolder.setContext(context);
    	Mockito.when(context.getBean(SessionUsuarioData.class))
    		.thenReturn(sesionUsuarioData);
    	
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSMODTRFE", "110"))))
                .thenReturn(CNSMODTRFE_140_ResponseOK);

        IatxResponse response = new IatxResponse();
        response.setEstadoRespuesta(EstadoRespuesta.OK);
        Vector<String> vector = buildVectorIsClienteAdherido();
        response.setTrama(CNSMODTRFE_140_ResponseOK);
        response.setIatxBody(vector);
        response.setErrorCode(00000000);
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(response);

        TransferenciaModtrfe transferenciaModtrfe = transferenciaDAO.ejecutarModTrfe(cliente);
        Assert.assertEquals(transferenciaModtrfe.getIndicadorAdhesionBee(), "S");
    }
    
    private Vector<String> buildVectorIsClienteAdherido() {
        Vector<String> vector = new Vector<String>();
        vector.add("00000000");
        vector.add("A");
        vector.add("0001");
        vector.add("03");
        vector.add("S");
        vector.add("02");
        return vector;
    }
}
