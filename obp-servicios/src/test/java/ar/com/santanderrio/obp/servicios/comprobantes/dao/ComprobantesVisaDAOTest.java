package ar.com.santanderrio.obp.servicios.comprobantes.dao;

import java.util.Date;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.impl.ComprobantesVisaDAOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.impl.VisaAmexRequest;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.impl.VisaAmexWS;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;

/**
 * The Class TarjetaDAOTest.
 * 
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class ComprobantesVisaDAOTest {

    /** The tarjeta DAO. */
    @InjectMocks
    private ComprobantesVisaDAO comprobantesVisaDAO = new ComprobantesVisaDAOImpl();

    /** The visa amex request. */
    @Mock
    private VisaAmexRequest visaAmexRequest;

    /** The rest template. */
    @Mock
    private RestTemplate restTemplate;

    /** The visa amex WS. */
    @Mock
    VisaAmexWS visaAmexWS;

    /**
     * Visa resumen cuenta OK test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void visaResumenCuentaOKTest() throws DAOException, IllegalAccessException {
        ResponseEntity<String> respuestaWs = obtenerRespuestaWSInformesDebitosAutomaticos();
        TransaccionDTO transaccion = new TransaccionDTO();
        transaccion.setFechaDesde(new Date());
        transaccion.setFechaHasta(new Date());

        Mockito.when(visaAmexWS.obtenerRespuestaVisaAmexDebitosWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(TransaccionDTO.class),Matchers.any(Cliente.class))).thenReturn(respuestaWs);
        FieldUtils.writeDeclaredField(comprobantesVisaDAO, "appEncoding", "UTF-8", true);

        RetornoTarjetasEntity respuesta = comprobantesVisaDAO.obtenerInformesDebitosAutomaticosWS(
                CuentaMock.completarInfoCuenta(), OperacionTarjetaWSEnum.INFORMEDEBITOSAUTOMATICOS, transaccion, new Cliente());

        Assert.assertNotNull(respuesta);

    }
    
    @Test(expected=DAOException.class)
    public void visaResumenCuentaErrorTest() throws DAOException, IllegalAccessException {
        HttpStatus statusCode = HttpStatus.BAD_GATEWAY;
        ResponseEntity<String> respuestaWs = new ResponseEntity<String>(statusCode );
        TransaccionDTO transaccion = new TransaccionDTO();
        transaccion.setFechaDesde(new Date());
        transaccion.setFechaHasta(new Date());

        Mockito.when(visaAmexWS.obtenerRespuestaVisaAmexDebitosWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(TransaccionDTO.class),Matchers.any(Cliente.class))).thenReturn(respuestaWs);
        FieldUtils.writeDeclaredField(comprobantesVisaDAO, "appEncoding", "UTF-8", true);

        RetornoTarjetasEntity respuesta = comprobantesVisaDAO.obtenerInformesDebitosAutomaticosWS(
                CuentaMock.completarInfoCuenta(), OperacionTarjetaWSEnum.INFORMEDEBITOSAUTOMATICOS, transaccion, new Cliente());

        Assert.assertNotNull(respuesta);

    }
    
    @Test
    public void visaResumenCuentaOKSinComprobantesTest() throws DAOException, IllegalAccessException {
        ResponseEntity<String> respuestaWs = new ResponseEntity<String>("", null, HttpStatus.OK);
        
        TransaccionDTO transaccion = new TransaccionDTO();
        transaccion.setFechaDesde(new Date());
        transaccion.setFechaHasta(new Date());

        Mockito.when(visaAmexWS.obtenerRespuestaVisaAmexDebitosWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(TransaccionDTO.class),Matchers.any(Cliente.class))).thenReturn(respuestaWs);
        FieldUtils.writeDeclaredField(comprobantesVisaDAO, "appEncoding", "UTF-8", true);

        RetornoTarjetasEntity respuesta = comprobantesVisaDAO.obtenerInformesDebitosAutomaticosWS(
                CuentaMock.completarInfoCuenta(), OperacionTarjetaWSEnum.INFORMEDEBITOSAUTOMATICOS, transaccion, new Cliente());

        Assert.assertNotNull(respuesta);

    }
    
    @Test(expected=DAOException.class)
    public void visaResumenCuentaOKConTarjetasTest() throws DAOException, IllegalAccessException {
        ResponseEntity<String> respuestaWs = new ResponseEntity<String>("2345234", null, HttpStatus.OK);
        
        TransaccionDTO transaccion = new TransaccionDTO();
        transaccion.setFechaDesde(new Date());
        transaccion.setFechaHasta(new Date());

        Mockito.when(visaAmexWS.obtenerRespuestaVisaAmexDebitosWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(TransaccionDTO.class),Matchers.any(Cliente.class))).thenReturn(respuestaWs);
        FieldUtils.writeDeclaredField(comprobantesVisaDAO, "appEncoding", "UTF-8", true);

        RetornoTarjetasEntity respuesta = comprobantesVisaDAO.obtenerInformesDebitosAutomaticosWS(
                CuentaMock.completarInfoCuenta(), OperacionTarjetaWSEnum.INFORMEDEBITOSAUTOMATICOS, transaccion, new Cliente());
        Assert.assertTrue(respuesta.getError());
        Assert.assertNotNull(respuesta);

    }

    /**
     * Setea la trama a probar.
     *
     * @return the respuesta ws
     */
    private ResponseEntity<String> obtenerRespuestaWSInformesDebitosAutomaticos() {
        String body = ("<tarjetas>    <tarjeta>  <document sessionID=\"cHzGGAxpFsNXTdgYBLmjm129\">"
                + "  <datos id=\"28570704\">" + " <affinityGroup>0</affinityGroup>" + "<apellido>MACERO</apellido>"
                + "<categoria>0</categoria>" + "<codTipoTarjeta>Gold</codTipoTarjeta>"
                + "<codigoSucursal>100</codigoSucursal>" + "<cuenta>722764282</cuenta>"
                + "<fechaDesde>22/06/2016</fechaDesde>" + "<fechaNacimiento>17/04/1983</fechaNacimiento>"
                + "<habiente>MACERO/MARTIN</habiente>" + "<nombre>MARTIN</nombre>" + "<documento>30219572</documento>"
                + "<producto>Gold</producto>" + "<tarjetaActiva>4509950155938479</tarjetaActiva>"
                + "<tarjetaProdu>CR0202</tarjetaProdu>" + "<telefono/>"
                + "<tipoDocumento codigo=\"1\">DNI</tipoDocumento>" + "<tipoTarjetaDetalle>Gold</tipoTarjetaDetalle>"
                + "<titular>MACERO/MARTIN</titular>" + "<vencimiento>1906</vencimiento>" + "<website/>"
                + "<email descripcion=\"personal\">" + "   <nombre>martinmacero</nombre>"
                + " <dominio>gmail.com</dominio>" + "</email>" + "   </datos>"
                + "<informeDebitosAutomaticos desde=\"01-11-16\" hasta=\"30-04-17\">" + " <consumoPromedio>"
                + "     <dolares>0,00</dolares>" + "  <pesos>1.345,17</pesos>" + "</consumoPromedio>" + "<totales>"
                + " <dolares>0,00</dolares>" + " <pesos>8.071,00</pesos>" + "</totales>"
                + "<debitosTarjeta nombreTarjeta=\"MACERO/MARTIN\">" + " <totales>" + "      <pesos>8.071,00</pesos>"
                + "   <dolares>0,00</dolares>" + "</totales>" + "<debito>" + " <establecimiento>"
                + "  <rubro>SERV DE TELEVISION POR CABLE</rubro>"
                + " <codEstablecimiento>0009734005</codEstablecimiento>" + " </establecimiento>"
                + "    <fecha>31-10-2016</fecha>" + " <descripcion>DIREC TV DEB AUT    000000073290845</descripcion>"
                + " <importe>" + "  <pesos>1.196,00</pesos>" + " <dolares>0,00</dolares>" + "</importe>" + " </debito>"
                + "<debito>" + "  <establecimiento>" + "   <rubro>SERV DE TELEVISION POR CABLE</rubro>"
                + "<codEstablecimiento>0009734005</codEstablecimiento>" + "  </establecimiento>"
                + "<fecha>02-12-2016</fecha>" + "<descripcion>DIREC TV DEB AUT    000000073290845</descripcion>"
                + "<importe>" + " <pesos>1.375,00</pesos>" + " <dolares>0,00</dolares>" + "</importe>" + "</debito>"
                + " <debito>" + "  <establecimiento>" + "   <rubro>SERV DE TELEVISION POR CABLE</rubro>"
                + " <codEstablecimiento>0009734005</codEstablecimiento>" + " </establecimiento>"
                + " <fecha>30-12-2016</fecha>" + " <descripcion>DIREC TV DEB AUT    000000073290845</descripcion>"
                + " <importe>" + "   <pesos>1.375,00</pesos>" + " <dolares>0,00</dolares>" + " </importe>"
                + " </debito>" + " <debito>" + "   <establecimiento>"
                + "    <rubro>SERV DE TELEVISION POR CABLE</rubro>"
                + " <codEstablecimiento>0009734005</codEstablecimiento>" + " </establecimiento>"
                + " <fecha>02-02-2017</fecha>" + " <descripcion>DIREC TV DEB AUT    000000073290845</descripcion>"
                + " <importe>" + "   <pesos>1.375,00</pesos>" + " <dolares>0,00</dolares>" + " </importe>"
                + " </debito>" + " <debito>" + "   <establecimiento>"
                + "    <rubro>SERV DE TELEVISION POR CABLE</rubro>"
                + "  <codEstablecimiento>0009734005</codEstablecimiento>" + " </establecimiento>"
                + " <fecha>03-03-2017</fecha>" + " <descripcion>DIREC TV DEB AUT    000000073290845</descripcion>"
                + " <importe>" + "  <pesos>1.375,00</pesos>" + " <dolares>0,00</dolares>" + " </importe>" + " </debito>"
                + " <debito>" + "    <establecimiento>" + "     <rubro>SERV DE TELEVISION POR CABLE</rubro>"
                + "  <codEstablecimiento>0009734005</codEstablecimiento>" + "</establecimiento>"
                + "<fecha>31-03-2017</fecha>" + " <descripcion>DIREC TV DEB AUT    000000073290845</descripcion>"
                + " <importe>" + "   <pesos>1.375,00</pesos>" + " <dolares>0,00</dolares>" + " </importe>" + "</debito>"
                + " </debitosTarjeta>" + " </informeDebitosAutomaticos>" + "</document>" + " </tarjeta>" + " <tarjeta>"
                + "   <error sessionID=\"cHzGGAxpFsNXTdgYBLmjm129\">" + "    <codigo>112501</codigo>" + " </error>"
                + " </tarjeta>" + "</tarjetas>");

        ResponseEntity<String> respuestaWs = new ResponseEntity<String>(body, null, HttpStatus.OK);
        return respuestaWs;
    }
}