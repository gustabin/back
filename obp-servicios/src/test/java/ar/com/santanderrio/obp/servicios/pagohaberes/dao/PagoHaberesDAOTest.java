package ar.com.santanderrio.obp.servicios.pagohaberes.dao;

import static org.mockito.Mockito.when;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaOutCBUEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.clientes.service.ClienteService;
import ar.com.santanderrio.obp.servicios.comun.CuentaInvalidaException;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagohaberes.dao.impl.PagoHaberesDAOImpl;
import ar.com.santanderrio.obp.servicios.pagohaberes.exceptions.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.DatosDestinatarioView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoInformadoView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidacionesPagoPorCBUView;
import ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.RequestCNSTITCBU;
import ar.com.santanderrio.obp.servicios.transferencias.exception.DestinatarioNoVerificadoException;

/**
 * The Class TransferenciaDAOTest.
 *
 * @author Manuel Vargas : B041299
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class PagoHaberesDAOTest extends IatxBaseDAOTest {
    
    @Mock
    SesionCliente sesionCliente;

    @InjectMocks
    private PagoHaberesDAOImpl pagoHaberesDAOImpl;
    
    @Mock
    private TransferenciaDAO mockTransferenciaDAOImpl;
    
    @Mock
    private LegalDAO legalDAO;
    
    @Mock
    private ClienteService clienteService;

    @Mock
    MensajeManager mensajeManager;
    
    @Mock
    private IatxComm iatxComm;

    /** The CNSTITCB U 110 response OK. */
    private String CNSTITCBU_110_ResponseOK = "200000000000P04HTML0009900010300MRQT37  ********00170890000000092017051809401400000000IBF21602000000000000CNSTITCBU_1100000            00276937    00        00 000000000201705180940030000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0018900000000õQAPEREZ QALILIANA J  /DEL BUENO GABRIELA   /                    õ27181388167õ27231462517õ           õBANCO ITAU            õ11õ04391813013                 õ01õ11õIBAYõ0000õ0000000õ";
    
    /** The CNSCTATITResponse U 110 response OK. */
    private String CNSCTATIT_110_ResponseOK = "200000000000P04HTML0009900010300MRQT37  ********00170890000000202017051809462500000000IBF21647000000000000CNSCTATIT_1100000            00276937    00        00 009407765201705180946130000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006400000000õMACHADO BRONSTEI  FLAVIANO NATõL õ20047409889õ00743315õ";
    
    /** The PAGHABCCI_ U 100 response ERROR. */
    private String PAGHABCCI_100_ResponseERROR = "200000000000P04HTML0009900010302QLPO92  ********00170893000000062017051810003600000000IBF22073000000010299PAGHABCCI_1000000            02615492    00        00 000000000201705181000250000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0004800000036õ299õ9990õ00010299õ00000060000õ20170519õ";
    
    /** The servicio cnsctatit. */
    private String servicioCnsctatit = "CNSCTATIT_";

    /** The version Cnsctatit . */
    private String versionCnsctatit = "110";
    
    /** The CNSTITCB uname. */
    private String CNSTITCBUname = "CNSTITCBU";
    
    /** The CNSTITCB uversion. */
    private String CNSTITCBUversion = "110";
    
    /** The servicio Paghabcci. */
    private String servicioPaghabcci = "PAGHABCCI_";

    /** The version Paghabcci. */
    private String versionPaghabcci = "100";
    
    private static final int CODIGO_RETORNO_10000122 = 10000122;
    
    /** The cliente. */
    private Cliente cliente;

    /** The cuenta. */
    private Cuenta cuenta;

    /**
     * Inits the.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Before
    public void init() throws ServiceException {
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
        cuenta.setTipoCuenta("00");
        cuenta.setNroSucursal("000");
        cuenta.setNroCuentaProducto("0000000");
        cuenta.setNroOrdenFirmante("01");
    }

    /**
     * Test validar que el cliente existe en OBP. Mocks iatxSender con CNSCTATIT_
     * version 110
     * 
     * @throws DAOException
     * @throws CuentaInvalidaException
     * @throws IatxException 
     */
    @Test
    public void testIsClienteValidoOK() throws DAOException, CuentaInvalidaException, IatxException{
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicioCnsctatit, versionCnsctatit))))
                .thenReturn(CNSCTATIT_110_ResponseOK);
        
        PagoInformadoView pagoInformadoView = new PagoInformadoView();
        pagoInformadoView.setTipoCuentaDestino("CCP");
        pagoInformadoView.setCuentaDestino("123-123456/7");
        
        IatxResponse response = new IatxResponse();
        response.setEstadoRespuesta(EstadoRespuesta.OK);
        Vector<String> vector = buildVectorIsClienteValido();
        response.setTrama(CNSCTATIT_110_ResponseOK);
        response.setIatxBody(vector);
        response.setErrorCode(00000000);
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(response);

        PagoInformadoView pagoInformadoResult = this.pagoHaberesDAOImpl.isClienteValido(cliente, pagoInformadoView);
        Assert.assertNotNull(pagoInformadoResult);
        
    }
    
    /**
     * * Test para validar que el CBU sea valido. Mocks iatxSender con CNSCTATIT_
     * version 110
     * 
     * @throws IatxException
     * @throws DestinatarioNoVerificadoException 
     * @throws DAOException 
     */
    @Ignore
    @Test
    public void testValidarCbuOK() throws IatxException, DestinatarioNoVerificadoException, DAOException{
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(CNSTITCBUname, CNSTITCBUversion))))
                .thenReturn(CNSTITCBU_110_ResponseOK);
        
        ValidacionesPagoPorCBUView validarCBUView = new ValidacionesPagoPorCBUView();
        validarCBUView.setNumeroCBUDestino("2590004220043918130135");
        validarCBUView.setDireccionIP("180166094114");
        validarCBUView.setCuentaOrigen("000000639170");
        validarCBUView.setTarjetaBanelco("4517660021778823");
        validarCBUView.setTipoCuentaOrigen("CUP");
        
        IatxResponse response = new IatxResponse();
        response.setEstadoRespuesta(EstadoRespuesta.OK);
        Vector<String> vector = buildVectorIsCBUValido();
        response.setTrama(CNSTITCBU_110_ResponseOK);
        response.setIatxBody(vector);
        response.setErrorCode(00000036);
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(response);
        when(mockTransferenciaDAOImpl.conexionCNSTITICBU(cliente, getCNSTIT())).thenReturn(response);
        
        ValidacionCuentaOutCBUEntity validacionCuentaOutCBUEntity = this.pagoHaberesDAOImpl.validarCBU(cliente, validarCBUView);
        
        Assert.assertNotNull(validacionCuentaOutCBUEntity);
    }
    
	/**
     * Test que intenta pagar por cbu pero falla por error en la trama de 10000036
     * 
     * @throws IatxException
     * @throws DAOException 
     * @throws SaldoInsuficienteException 
     */
    @Test(expected=SaldoInsuficienteException.class)
    public void testPagoHaberesCBUError() throws IatxException, SaldoInsuficienteException, DAOException{
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicioPaghabcci, versionPaghabcci))))
                .thenReturn(PAGHABCCI_100_ResponseERROR);
        
        DatosDestinatarioView datosDestinatarioView = new DatosDestinatarioView();
        datosDestinatarioView.setTipoCuentaOrigen("09");
        datosDestinatarioView.setCuentaOrigen("123456789012");
        datosDestinatarioView.setImporte("00000010000");
        datosDestinatarioView.setConcepto("CONCEPTO");
        datosDestinatarioView.setCuilCuitDestinatario("12345678901");
        datosDestinatarioView.setNumeroCBUDestino("00088000006391704");
        datosDestinatarioView.setBancoDestinatario("BANCO SANTANDER");
        datosDestinatarioView.setNombreDestinatario("ZIRUFFO RUBEN OSCAR");
        
        IatxResponse response = new IatxResponse();
        response.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Vector<String> vector = buildVectorPagoHaberes();
        response.setTrama(PAGHABCCI_100_ResponseERROR);
        response.setIatxBody(vector);
        response.setErrorCode(10000122);
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(response);
        
        IatxResponse iatxResponse = this.pagoHaberesDAOImpl.pagoHaberesCBU(cliente, datosDestinatarioView, cuenta);
        
        int codeError = iatxResponse.getErrorCode();
        Assert.assertEquals(codeError, CODIGO_RETORNO_10000122);
    }
    
    private Vector<String> buildVectorIsClienteValido() {
        Vector<String> vector = new Vector<String>();
        vector.add("00000000");
        vector.add("MACHADO BRONSTEI  FLAVIANO NAT");
        vector.add("L");
        vector.add("20047409889");
        vector.add("00743315");
        return vector;
    }

    private Vector<String> buildVectorIsCBUValido() {
        Vector<String> vector = new Vector<String>();
        vector.add("00000000");
        vector.add(" ");
        vector.add(" ");
        vector.add(" ");
        vector.add(" ");
        vector.add(" ");
        vector.add(" ");
        vector.add(" ");
        vector.add(" ");
        vector.add(" ");
        vector.add(" ");
        vector.add(" ");
        vector.add(" ");
        return vector;
    }
    
    private Vector<String> buildVectorPagoHaberes() {
        Vector<String> vector = new Vector<String>();
        vector.add("10000122");
        vector.add("ABC");
        vector.add("10");
        return vector;
    }
    
    private RequestCNSTITCBU getCNSTIT() {
    	RequestCNSTITCBU requestCNSTITCBU = new RequestCNSTITCBU();
    	requestCNSTITCBU.setTipoCuenta("00");
    	requestCNSTITCBU.setNroSucursal("312");
    	requestCNSTITCBU.setNroCuenta("0053479");
    	requestCNSTITCBU.setCbuDestino("2590004220043918130135");
    	requestCNSTITCBU.setNroTarjeta("004517660095844204");
    	requestCNSTITCBU.setDireccionIP("192.168.56.1");
        return requestCNSTITCBU;
	}
}
