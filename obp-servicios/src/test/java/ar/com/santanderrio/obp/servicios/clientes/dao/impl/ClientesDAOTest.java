package ar.com.santanderrio.obp.servicios.clientes.dao.impl;

import static org.mockito.Mockito.when;

import java.util.Vector;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcherRellamado;
import ar.com.santanderrio.obp.servicios.clientes.dao.ClienteDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteConSaldoResponse;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.dao.CuentaTituloDAO;



/**
 * The Class ClientesDAOTest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {})
public class ClientesDAOTest extends IatxBaseDAOTest {

    /** The cliente DAO. */
    @InjectMocks
    private ClienteDAO clienteDAO = new ClienteDAOImpl();

    /** The iatx comm. */
    @Mock
    private IatxComm iatxComm;
    
    /** The iatx comm. */
    @Mock
    private CuentaTituloDAO cuentaTituloDAO;

    /**
     * Test get cliente OK.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void testGetClienteOK() throws IatxException, IllegalAccessException {
        IatxResponse response = new IatxResponse();
        response.setEstadoRespuesta(EstadoRespuesta.OK);
        Vector<String> vector = buildVector("20010080",
                " Disculpe, no es posible realizar la transaccion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                   ");
        response.setIatxBody(vector);
        response.setErrorCode(20010080);

        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(response);
        ResumenCliente resumenCliente = new ResumenCliente();
        resumenCliente.setFechaNacimiento("20050412");
        FieldUtils.writeField(clienteDAO, "prefijoIdecltsdo", "IDECLTSDO", true);
        FieldUtils.writeField(clienteDAO, "version180", "180", true);
        ClienteConSaldoResponse respuesta = clienteDAO.obtenerCliente(resumenCliente);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * Test get cliente warning 10071.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void testGetClienteWarning_10071() throws IatxException, IllegalAccessException {
        IatxResponse response = new IatxResponse();
        response.setEstadoRespuesta(EstadoRespuesta.WARNING);

        Vector<String> vector = buildVector("20010071",
                " PROBLEMAS EN TARJETA: MASTERCARD/VISA                                                                                                                                                                                                                         ");
        response.setIatxBody(vector);
        response.setErrorCode(20010071);

        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(response);
        ResumenCliente resumenCliente = new ResumenCliente();
        resumenCliente.setFechaNacimiento("20050412");
        FieldUtils.writeField(clienteDAO, "prefijoIdecltsdo", "IDECLTSDO", true);
        FieldUtils.writeField(clienteDAO, "version180", "180", true);
        ClienteConSaldoResponse respuesta = clienteDAO.obtenerCliente(resumenCliente);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        Assert.assertEquals(respuesta.getCodigoRespuesta(), new Integer(20010071));
    }

    /**
     * Test get cliente warning 10075.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void testGetClienteWarning_10075() throws IatxException, IllegalAccessException {
        String respIdecltsdo = "200000000000P04HTML0009900010302QLQQ47  ********00036307000000012016110815100700000000IBF22141000000000000IDECLTSDO_1800000            02616647    00        00 015159988201611081510020000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHÃµ0986720010080ÃµCNLÃµ03ÃµDisculpe, no es posible realizar la transaccion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                   ÃµGrabo log cnl24ÃµPROBLEMAS EN EL PROGRAMA DCNL024 - SE GENERO EL LOG                             ÃµNÃµ00021767058ÃµVITOLDO SOFANOR ALAIN                   ÃµCONQUISTA           Ãµ                    Ãµ19710305ÃµIÃµ02616647Ãµ        Ãµ00000000Ãµ045Ãµ09Ãµ0066Ãµ0000000001020667Ãµ001ÃµINFIÃµPÃµNÃµ02Ãµ00000000000000000001Ãµ000000Ãµ0066Ãµ0000007001020667ÃµN ÃµTIÃµ0350Ãµ14Ãµ0066Ãµ000090000001432Ãµ00000083076506+Ãµ00000000000000+Ãµ00000000118400Ãµ0720066388000010206672Ãµ07Ãµ0001ÃµARSÃµ09Ãµ0126Ãµ0000000003655016Ãµ001ÃµINFIÃµSÃµNÃµ02Ãµ00000000000000000000Ãµ000000Ãµ0126Ãµ0000007003655016Ãµ  ÃµTIÃµ2176Ãµ14Ãµ0126Ãµ000090000237503Ãµ00000000596409-Ãµ00000000000000+Ãµ00000000000000Ãµ0720126088000036550168Ãµ07Ãµ0001ÃµARSÃµ10Ãµ0066Ãµ0000000001020667Ãµ001ÃµINFIÃµPÃµNÃµ02Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000007001020667ÃµN ÃµTIÃµ0350Ãµ14Ãµ0066Ãµ000090000001432Ãµ00000100758755+Ãµ00000000000000+Ãµ00000000001757Ãµ0720066388000010206672Ãµ07Ãµ0001ÃµUSDÃµ10Ãµ0126Ãµ0000000003655016Ãµ001ÃµINFIÃµSÃµNÃµ02Ãµ00000000000000000000Ãµ000000Ãµ0126Ãµ0000007003655016Ãµ  ÃµTIÃµ2176Ãµ14Ãµ0126Ãµ000090000237503Ãµ00000009147789+Ãµ00000000000000+Ãµ00000000000000Ãµ0720126088000036550168Ãµ07Ãµ0001ÃµUSDÃµ09Ãµ0000Ãµ0000000004043627Ãµ003ÃµINFIÃµSÃµNÃµ02Ãµ00000000000000000000Ãµ000000Ãµ0000Ãµ0000007004043627ÃµN ÃµCTÃµ0355Ãµ14Ãµ0000Ãµ000090009675036Ãµ00000017500000+Ãµ00000000000000+Ãµ00000000000000Ãµ0720000788000040436272Ãµ07Ãµ0001ÃµARSÃµ10Ãµ0000Ãµ0000000004043627Ãµ003ÃµINFIÃµSÃµNÃµ02Ãµ00000000000000000000Ãµ000000Ãµ0000Ãµ0000007004043627ÃµN ÃµCTÃµ0355Ãµ14Ãµ0000Ãµ000090009675036Ãµ00000017000000+Ãµ00000000000000+Ãµ00000000000000Ãµ0720000788000040436272Ãµ07Ãµ0001ÃµUSDÃµ00Ãµ0000Ãµ0000000004042723Ãµ001ÃµACTEÃµSÃµNÃµ01Ãµ00000000000000000000Ãµ000000Ãµ0000Ãµ0000005004042723ÃµN ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000097813616+Ãµ00000000000000+Ãµ00000000000000Ãµ0720000720000040427236Ãµ05Ãµ0001ÃµARSÃµ01Ãµ0000Ãµ0000000004042716Ãµ001ÃµACAHÃµSÃµNÃµ01Ãµ00000000000000000000Ãµ000000Ãµ0000Ãµ0000002004042716ÃµN ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000009587768+Ãµ00000000000000+Ãµ00000000000000Ãµ0720000730000040427165Ãµ02Ãµ0001ÃµARSÃµ05Ãµ0126Ãµ0000000000000000Ãµ001ÃµABAEÃµSÃµEÃµ99Ãµ00004517660038454921Ãµ000031Ãµ0066Ãµ0000000001020667Ãµ01ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0000000000000000000000Ãµ81Ãµ0012Ãµ   Ãµ07Ãµ0066Ãµ0000000014343591Ãµ004ÃµAVINÃµPÃµNÃµ99Ãµ00004509800038195153Ãµ000000Ãµ0066Ãµ0000300014343591Ãµ20ÃµADÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000342655+Ãµ00000000000000+Ãµ00000002100000Ãµ1105200403010210001000Ãµ40ÃµNAC Ãµ   Ãµ07Ãµ0066Ãµ0000000158667489Ãµ001ÃµAVISÃµSÃµHÃµ99Ãµ00004660570007674653Ãµ009743Ãµ0066Ãµ0000000158667489Ãµ20ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000254301+Ãµ00000000069250+Ãµ00000001054800Ãµ1304200302910105489100Ãµ40ÃµPLATÃµ   Ãµ08Ãµ0000Ãµ0000000003126509Ãµ001ÃµATITÃµPÃµ Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0000Ãµ0000000000000000Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0000000000000000000000Ãµ60Ãµ0000Ãµ   Ãµ08Ãµ0000Ãµ0000000009106624Ãµ001ÃµATITÃµSÃµ Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0000Ãµ0000000000000000Ãµ00ÃµTIÃµ0350Ãµ14Ãµ0066Ãµ000090000001432Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0000000000000000000000Ãµ60Ãµ0000Ãµ   Ãµ08Ãµ0000Ãµ0000000016063194Ãµ001ÃµATITÃµSÃµ Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0000Ãµ0000000000000000Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0000000000000000000000Ãµ60Ãµ0000Ãµ   Ãµ08Ãµ0000Ãµ0000000025360961Ãµ003ÃµATITÃµSÃµ Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0000Ãµ0000000000000000Ãµ00ÃµCTÃµ0355Ãµ14Ãµ0000Ãµ000090009675036Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0000000000000000000000Ãµ60Ãµ0000Ãµ   Ãµ08Ãµ0000Ãµ0000000090187393Ãµ002ÃµATITÃµSÃµ Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0000Ãµ0000000000000000Ãµ00ÃµCTÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0000000000000000000000Ãµ60Ãµ0000Ãµ   Ãµ30Ãµ0066Ãµ0000035100616033Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035100616033Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035100616194Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035100616194Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0017ÃµARSÃµ30Ãµ0066Ãµ0000035100616231Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035100616231Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0017ÃµARSÃµ30Ãµ0066Ãµ0000035100616255Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035100616255Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0017ÃµARSÃµ30Ãµ0066Ãµ0000035100616330Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035100616330Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035100616590Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035100616590Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035100616712Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035100616712Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035100616736Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035100616736Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035100616750Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035100616750Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035190646811Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190646811Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035190646859Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190646859Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035190647098Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647098Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035190647111Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647111Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035190647159Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647159Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035190647210Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647210Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035190647258Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647258Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035190647272Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647272Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035190647296Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647296Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035190647357Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647357Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035190647555Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647555Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0016ÃµARSÃµ30Ãµ0066Ãµ0000035190647654Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647654Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0046ÃµARSÃµ30Ãµ0066Ãµ0000035190647692Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647692Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0046ÃµARSÃµ30Ãµ0066Ãµ0000035190647906Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647906Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0046ÃµARSÃµ30Ãµ0066Ãµ0000035190647920Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647920Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0046ÃµARSÃµ30Ãµ0066Ãµ0000035190647944Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647944Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0046ÃµARSÃµ30Ãµ0066Ãµ0000035190647968Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647968Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0046ÃµARSÃµ30Ãµ0066Ãµ0000035190647982Ãµ001ÃµAPTMÃµSÃµ4Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000035190647982Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0072020661020667000000Ãµ35Ãµ0046ÃµARSÃµ30Ãµ0066Ãµ0000037100096336Ãµ001ÃµAPTMÃµSÃµ8Ãµ99Ãµ00000000000000000000Ãµ000000Ãµ0066Ãµ0000037100096336Ãµ00ÃµTIÃµ0000Ãµ00Ãµ0000Ãµ000000000000000Ãµ00000000000000 Ãµ00000000000000 Ãµ00000000000000Ãµ0066020661020667000000Ãµ37Ãµ0075ÃµARSÃµ42Ãµ0066Ãµ0000000008336771Ãµ001ÃµAEXPÃµPÃµHÃµ99Ãµ00003777920000206900Ãµ009744Ãµ0066Ãµ0000000008336771Ãµ20ÃµTIÃµ0350Ãµ14Ãµ0066Ãµ000090000001432Ãµ00000000462189+Ãµ00000000485789+Ãµ00000001054800Ãµ1107140608230105489000Ãµ42ÃµPLATÃµ   Ãµ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcherRellamado("IDECLTSDO", "180", "000"))))
                .thenReturn(respIdecltsdo);
        IatxResponse response = new IatxResponse();
        response.setEstadoRespuesta(EstadoRespuesta.WARNING);
        Vector<String> vector = buildVector("20010075",
                " PROBLEMAS EN TARJETA: MASTERCARD/VISA                                                                                                                                                                                                                         ");
        response.setTrama(respIdecltsdo);
        response.setIatxBody(vector);
        response.setErrorCode(20010075);
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(response);
        ResumenCliente resumenCliente = new ResumenCliente();
        resumenCliente.setFechaNacimiento("20051201");
        FieldUtils.writeField(clienteDAO, "prefijoIdecltsdo", "IDECLTSDO", true);
        FieldUtils.writeField(clienteDAO, "version180", "180", true);
        ClienteConSaldoResponse respuesta = clienteDAO.obtenerCliente(resumenCliente);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        Assert.assertEquals(respuesta.getCodigoRespuesta(), new Integer(20010075));
    }

    /**
     * Builds the vector.
     *
     * @param errorCode
     *            the error code
     * @param leyenda
     *            the leyenda
     * @return the vector
     */
    private Vector<String> buildVector(String errorCode, String leyenda) {
        Vector<String> vector = new Vector<String>();
        vector.add(errorCode);
        vector.add("CNL");
        vector.add("03");
        vector.add(leyenda);
        vector.add(" VITOLDO SOFANOR ALAIN                   ");
        vector.add(" CONQUISTA           ");
        vector.add(" N");
        vector.add(" 00021767058");
        vector.add(" VITOLDO SOFANOR ALAIN                   ");
        vector.add(" CONQUISTA           ");
        vector.add("                     ");
        vector.add("0");
        vector.add(" I");
        vector.add("02616647");
        return vector;
    }

}
