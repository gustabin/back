package ar.com.santanderrio.obp.servicios.tarjetas.dao;

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
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.impl.TarjetaDAOImpl;
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
public class TarjetaDAOTest {

    /** The tarjeta DAO. */
    @InjectMocks
    private TarjetaDAO tarjetaDAO = new TarjetaDAOImpl();

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
        ResponseEntity<String> respuestaWs = obtenerRespuestaWSResumenCuenta();

        Mockito.when(visaAmexWS.obtenerRespuestaVisaAmexWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(respuestaWs);
        FieldUtils.writeDeclaredField(tarjetaDAO, "appEncoding", "UTF-8", true);

        RetornoTarjetasEntity respuesta = tarjetaDAO.obtenerTarjetasDeVisaWS(CuentaMock.completarInfoCuenta(),
                OperacionTarjetaWSEnum.RESUMENCUENTA,new Cliente());

        Assert.assertNotNull(respuesta);
        Assert.assertFalse(respuesta.getError());
        Assert.assertEquals("DOE/JOHN D ",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getDatos().getHabiente());
        Assert.assertEquals("0",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getDatos().getCategoria());
        Assert.assertEquals("12345678",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getDatos().getDocumento());
        Assert.assertEquals("1234123412349324",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getDatos().getTarjetaActiva());
        Assert.assertEquals("proximo", respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument()
                .getSaldoEnCuenta().getFechas().getFechas().get(0).getDescripcion());
        Assert.assertEquals("minimo", respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument()
                .getSaldoEnCuenta().getPagos().getPagos().get(0).getDescripcion());
        Assert.assertEquals("compra", respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument()
                .getSaldoEnCuenta().getLimites().getLimites().get(0).getDescripcion());
        Assert.assertEquals("ultima", respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument()
                .getSaldoEnCuenta().getSaldos().getSaldos().get(0).getDescripcion());
    }

    /**
     * Visa autorizaciones OK test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void visaAutorizacionesOKTest() throws DAOException, IllegalAccessException {
        ResponseEntity<String> respuestaWs = obtenerRespuestaWSAutorizaciones();
        Mockito.when(visaAmexWS.obtenerRespuestaVisaAmexWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(respuestaWs);
        FieldUtils.writeDeclaredField(tarjetaDAO, "appEncoding", "UTF-8", true);

        RetornoTarjetasEntity respuesta = tarjetaDAO.obtenerTarjetasDeVisaWS(CuentaMock.completarInfoCuenta(),
                OperacionTarjetaWSEnum.AUTORIZACIONES,new Cliente());

        Assert.assertNotNull(respuesta);
        Assert.assertFalse(respuesta.getError());
        Assert.assertEquals(" ESCUDERO SYRIANI ",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getDatos().getHabiente());
        Assert.assertEquals("0",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getDatos().getCategoria());
        Assert.assertEquals("24422451",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getDatos().getDocumento());
        Assert.assertEquals("4509950146886867",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getDatos().getTarjetaActiva());
        Assert.assertEquals("3.459,21", respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument()
                .getAutorizaciones().getTotales().getPesos());
        Assert.assertEquals("0,00", respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument()
                .getAutorizaciones().getTotales().getDolares());
        Assert.assertEquals("XXXX XXXX XXXX6867", respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument()
                .getAutorizaciones().getConsumoPendienteConfirmacionList().get(0).getCodigoTarjeta());
        Assert.assertEquals("0,00", respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument()
                .getAutorizaciones().getConsumoPendienteConfirmacionList().get(0).getDolares());
        Assert.assertEquals("2.740,00", respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument()
                .getAutorizaciones().getConsumoPendienteConfirmacionList().get(0).getPesos());
        Assert.assertEquals("03160",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getAutorizaciones()
                        .getConsumoPendienteConfirmacionList().get(0).getAutorizacionList().get(0).getCodigo());
        Assert.assertEquals("CONS. $     ",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getAutorizaciones()
                        .getConsumoPendienteConfirmacionList().get(0).getAutorizacionList().get(0).getDescripcion());
        Assert.assertEquals("0014866776",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getAutorizaciones()
                        .getConsumoPendienteConfirmacionList().get(0).getAutorizacionList().get(0).getEstablecimiento()
                        .getCodigo());
        Assert.assertEquals("PAULA CAHEN D'ANVERS",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getAutorizaciones()
                        .getConsumoPendienteConfirmacionList().get(0).getAutorizacionList().get(0).getEstablecimiento()
                        .getDescripcion());
        Assert.assertEquals("12/03/2016",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getAutorizaciones()
                        .getConsumoPendienteConfirmacionList().get(0).getAutorizacionList().get(0).getFecha());
        Assert.assertEquals("1.960,00",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getAutorizaciones()
                        .getConsumoPendienteConfirmacionList().get(0).getAutorizacionList().get(0).getImporte()
                        .getValor());
        Assert.assertEquals("pesos",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getAutorizaciones()
                        .getConsumoPendienteConfirmacionList().get(0).getAutorizacionList().get(0).getMoneda());
        Assert.assertEquals("C",
                respuesta.getTarjetas().getTarjetaList().get(0).getTarjetaDocument().getAutorizaciones()
                        .getConsumoPendienteConfirmacionList().get(0).getAutorizacionList().get(0).getTipo());
    }

    /**
     * Obtener respuesta WS autorizaciones.
     *
     * @return the response entity
     */
    private ResponseEntity<String> obtenerRespuestaWSAutorizaciones() {
        String body = ("<tarjetas><tarjeta><document sessionID=\"qUwoIm0S_GTdOvP2BFXvOIYc\">"
                + "<datos id=\"73420616\"><affinityGroup>Grupo_Afinidad</affinityGroup>"
                + "<apellido> ESCUDERO SYRIANI </apellido><categoria>0</categoria>"
                + "<codTipoTarjeta>CHIP EMV C/CONTAC</codTipoTarjeta>"
                + "<codigoSucursal>100</codigoSucursal><cuenta>337086339</cuenta>"
                + "<fechaDesde>01/10/2014</fechaDesde><fechaNacimiento>14/04/1975</fechaNacimiento>"
                + "<habiente> ESCUDERO SYRIANI </habiente><nombre>MARIANA</nombre>"
                + "<documento>24422451</documento><producto>Platinum</producto>"
                + "<tarjetaActiva>4509950146886867</tarjetaActiva>" + "<tarjetaProdu>CR0702</tarjetaProdu><telefono/>"
                + "<tipoDocumento codigo=\"1\">DNI</tipoDocumento>"
                + "<tipoTarjetaDetalle>CHIP EMV C/CONTAC</tipoTarjetaDetalle>"
                + "<titular> ESCUDERO SYRIANI </titular><vencimiento>2209</vencimiento>"
                + "<website/></datos><autorizaciones><totales>" + "<dolares>0,00</dolares><pesos>3.459,21</pesos>"
                + "</totales><tarjeta codigoTarjeta=\"XXXX XXXX XXXX6867\">"
                + "<dolares>0,00</dolares><pesos>2.740,00</pesos>" + "<autorizacion>"
                + "<establecimiento codigo=\"0014866776\">PAULA CAHEN D&apos;ANVERS</establecimiento>"
                + "<codigo>03160</codigo><descripcion>CONS. $     </descripcion>"
                + "<fecha>12/03/2016</fecha><importe>1.960,00</importe>" + "<internacional/><moneda>pesos</moneda>"
                + "<tipo>C</tipo>" + "</autorizacion>" + "<autorizacion>"
                + "<establecimiento codigo=\"0013620026\">PORTSAID</establecimiento>"
                + "<codigo>02469</codigo><descripcion>CONS. $     </descripcion>"
                + "<fecha>13/03/2016</fecha><importe>780,00</importe>" + "<internacional/><moneda>pesos</moneda>"
                + "<tipo>C</tipo></autorizacion></tarjeta>"
                + "<tarjeta codigoTarjeta=\"XXXX XXXX XXXX3821\"><dolares>0,00</dolares>"
                + "<pesos>108,20</pesos><autorizacion>"
                + "<establecimiento codigo=\"0019551449\">CHANGOMAS EXPRESS ALBERDI</establecimiento>"
                + "<codigo>03660</codigo><descripcion>CONS. $     </descripcion>"
                + "<fecha>11/03/2016</fecha><importe>108,20</importe>" + "<internacional/><moneda>pesos</moneda>"
                + "<tipo>B</tipo></autorizacion></tarjeta>" + "</autorizaciones></document></tarjeta><tarjeta>"
                + "<document sessionID=\"qUwoIm0S_GTdOvP2BFXvOIYc\"><datos id=\"73420616\">"
                + "<affinityGroup>Grupo_Afinidad</affinityGroup>"
                + "<apellido> ESCUDERO SYRIANI </apellido><categoria>1</categoria>"
                + "<codTipoTarjeta>CHIP EMV C/CONTAC</codTipoTarjeta>"
                + "<codigoSucursal>100</codigoSucursal><cuenta>337086339</cuenta>"
                + "<fechaDesde>02/10/2014</fechaDesde><fechaNacimiento>28/05/1943</fechaNacimiento>"
                + "<habiente> ESCUDERO SYRIANI </habiente><nombre>MARIANA</nombre>"
                + "<documento>24422451</documento><producto>Platinum</producto>"
                + "<tarjetaActiva>4509950146883821</tarjetaActiva>" + "<tarjetaProdu>CR0702</tarjetaProdu>"
                + "<telefono/>" + "<tipoDocumento codigo=\"1\">DNI</tipoDocumento>"
                + "<tipoTarjetaDetalle>CHIP EMV C/CONTAC</tipoTarjetaDetalle>" + "<titular> ESCUDERO SYRIANI </titular>"
                + "<vencimiento>2209</vencimiento>" + "<website/>" + "		</datos>" + "<autorizaciones>"
                + "			<totales>" + "<dolares>0,00</dolares>" + "<pesos>3.459,21</pesos>" + "	</totales>"
                + "<tarjeta codigoTarjeta=\"XXXX XXXX XXXX6867\">" + "				<dolares>0,00</dolares>"
                + "<pesos>2.740,00</pesos>" + "				<autorizacion>"
                + "<establecimiento codigo=\"0014866776\">PAULA CAHEN D&apos;ANVERS</establecimiento>"
                + "<codigo>03160</codigo>" + "		<descripcion>CONS. $     </descripcion>"
                + "<fecha>12/03/2016</fecha>" + "		<importe>1.960,00</importe>" + "		<internacional/>"
                + "<moneda>pesos</moneda>" + "		<tipo>C</tipo>" + "	</autorizacion>" + "</tarjeta>"
                + "<tarjeta codigoTarjeta=\"XXXX XXXX XXXX3821\">" + "<dolares>0,00</dolares>" + "<pesos>719,21</pesos>"
                + "<autorizacion>"
                + "<establecimiento codigo=\"0019551449\">CHANGOMAS EXPRESS ALBERDI</establecimiento>"
                + "<codigo>03660</codigo>" + "<descripcion>CONS. $     </descripcion>" + "<fecha>11/03/2016</fecha>"
                + "<importe>108,20</importe>" + "<internacional/>"
                + "<moneda>pesos</moneda><tipo>B</tipo></autorizacion>"
                + "</tarjeta></autorizaciones></document></tarjeta>" + "</tarjetas>");

        ResponseEntity<String> respuestaWs = new ResponseEntity<String>(body, null, HttpStatus.OK);
        return respuestaWs;
    }

    /**
     * Setea la trama a probar.
     *
     * @return the respuesta ws
     */
    private ResponseEntity<String> obtenerRespuestaWSResumenCuenta() {
        String body = ("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?><tarjetas><tarjeta><document sessionID=\"f2s0lSvuWMSHwnzJrbtlciLE(wKgBKRDN)\">"
                + "<datos id=\"3839135\"><affinityGroup>9240</affinityGroup><apellido>DOE</apellido><categoria>0</categoria><codigoSucursal>048</codigoSucursal>"
                + "<cuenta>12345678</cuenta><fechaDesde>01/11/2001</fechaDesde><fechaNacimiento>04/06/1968</fechaNacimiento><habiente>DOE/JOHN D </habiente>"
                + "<nombre>JOHN</nombre><documento>12345678</documento><producto>Classic Internacional</producto><tarjetaActiva>1234123412349324</tarjetaActiva>"
                + "<telefono/><tipoDocumento codigo=\"1\">DNI</tipoDocumento><tipoTarjetaDetalle>Internacional</tipoTarjetaDetalle>"
                + "<titular>DOE/JOHN D </titular><website/></datos><saldoenCuenta limitesUnificados=\"S\"><fechas><fecha descripcion=\"proximo\">"
                + "<cierre>10/07/2003</cierre><vencimiento>21/07/2003</vencimiento></fecha><fecha descripcion=\"ultimo\"><cierre>05/06/2003</cierre>"
                + "<vencimiento>17/06/2003</vencimiento></fecha><fecha descripcion=\"anterior\"><cierre>08/05/2003</cierre><vencimiento>19/05/2003</vencimiento>"
                + "</fecha></fechas><pagos><pago descripcion=\"minimo\"><pesos>722,05</pesos><dolares>0,00</dolares></pago></pagos>"
                + "<limites>" + "<limite descripcion=\"compra\">" + "<pesos>2.100,00</pesos>" + "</limite>"
                + "<limite descripcion=\"compracuotas\">" + "	<pesos>2.100,00</pesos>" + "</limite>"
                + "<limite descripcion=\"compradisp\">" + "	<pesos>2.100,00</pesos>" + "</limite>"
                + "<limite descripcion=\"compracuotasdisp\">" + "<pesos>2.100,00</pesos>" + "</limite>"
                + "<limite descripcion=\"compadeldisp\">" + "<pesos>210,00</pesos>" + "</limite>"
                + "<limite descripcion=\"financiacion\">" + "<pesos>1.680,00</pesos>" + "</limite>"
                + "<limite descripcion=\"adelantos\">" + "<pesos>210,00</pesos>" + "</limite>" + "</limites>"
                + "<saldos>" + "<saldo descripcion=\"ultima\">" + "<pesos>825,37</pesos>" + "<dolares>0,00</dolares>"
                + "</saldo>" + "<saldo descripcion=\"anterior\">" + "<pesos>903,87</pesos>" + "<dolares>0,00</dolares>"
                + "					</saldo>" + "			</saldos>" + "		<tasas>"
                + "		<tasa descripcion=\"anual\">" + "		<pesos>46,0000</pesos>"
                + "		<dolares>33,8000</dolares>" + "</tasa>" + "<tasa descripcion=\"mensual\">"
                + "	<pesos>3,7800</pesos>" + "	<dolares>2,7800</dolares>" + "	</tasa>" + "	</tasas>"
                + "</saldoenCuenta>" + "</document>" + "</tarjeta>" + "</tarjetas>");

        ResponseEntity<String> respuestaWs = new ResponseEntity<String>(body, null, HttpStatus.OK);
        return respuestaWs;
    }
}