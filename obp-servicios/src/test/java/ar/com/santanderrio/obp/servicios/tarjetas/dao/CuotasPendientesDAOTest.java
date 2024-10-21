package ar.com.santanderrio.obp.servicios.tarjetas.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
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
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.CuotasPendientesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DetalleCuotaPendienteEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaCuotasPendientesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaDocumentEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.Totales;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.impl.TarjetaDAOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.impl.VisaAmexRequest;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.impl.VisaAmexWS;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;

/**
 * The Class CuotasPendientesDAOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuotasPendientesDAOTest {

    /** The cuotas pendientes DAO. */
    @InjectMocks
    private TarjetaDAO cuotasPendientesDAO = new TarjetaDAOImpl();

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
     * Consultar cuotas pendientes test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void consultarCuotasPendientesTest() throws DAOException, IllegalAccessException {
        ResponseEntity<String> respuestaWs = getRespuestaWs();
        Mockito.when(visaAmexWS.obtenerRespuestaVisaAmexWS(Matchers.any(Cuenta.class),
                Matchers.any(OperacionTarjetaWSEnum.class),Matchers.any(Cliente.class))).thenReturn(respuestaWs);
        FieldUtils.writeDeclaredField(cuotasPendientesDAO, "appEncoding", "UTF-8", true);
        RetornoTarjetasEntity respuesta = cuotasPendientesDAO.obtenerTarjetasDeVisaWS(CuentaMock.completarInfoCuenta(),
                OperacionTarjetaWSEnum.CUOTASPENDIENTES, new Cliente());
        assertNotNull(respuesta);

        // del servicio
        List<TarjetaEntity> tarjetaListWs = respuesta.getTarjetas().getTarjetaList();
        TarjetaEntity tarjetaWs = tarjetaListWs.get(0);
        TarjetaDocumentEntity tarjetaDocumentWs = tarjetaWs.getTarjetaDocument();
        CuotasPendientesEntity cuotasPendientesWs = tarjetaDocumentWs.getCuotasPendientes();
        List<TarjetaCuotasPendientesEntity> tarjetasCuotasPendientesWs = cuotasPendientesWs.getTarjetaList();
        TarjetaCuotasPendientesEntity tarjetaCuotasPendientesDTOWs = tarjetasCuotasPendientesWs.get(0);
        List<DetalleCuotaPendienteEntity> coleccionDetalleCuotaPendienteWs = tarjetaCuotasPendientesDTOWs
                .getDetalleCuotaPendienteList();
        DetalleCuotaPendienteEntity detalleCuotaPendienteWs = coleccionDetalleCuotaPendienteWs.get(0);
        // pojos
        List<TarjetaCuotasPendientesEntity> tarjetasCuotasPendientes = getCuotasPendientes().getTarjetaList();
        TarjetaCuotasPendientesEntity tarjetaCuotasPendientes = tarjetasCuotasPendientes.get(0);
        List<DetalleCuotaPendienteEntity> coleccionDetalleCuotaPendiente = tarjetaCuotasPendientes
                .getDetalleCuotaPendienteList();
        DetalleCuotaPendienteEntity detalleCuotaPendiente = coleccionDetalleCuotaPendiente.get(0);

        assertEquals(detalleCuotaPendienteWs.getComprobante().trim(), detalleCuotaPendiente.getComprobante());
        assertEquals(detalleCuotaPendienteWs.getCuotas().trim(), detalleCuotaPendiente.getCuotas());
        assertEquals(detalleCuotaPendienteWs.getCuotasPendientes().trim(), detalleCuotaPendiente.getCuotasPendientes());
        assertEquals(detalleCuotaPendienteWs.getEstablecimiento().trim(), detalleCuotaPendiente.getEstablecimiento());
        assertEquals(detalleCuotaPendienteWs.getFecha().trim(), detalleCuotaPendiente.getFecha());
        assertEquals(detalleCuotaPendienteWs.getImporte().trim(), detalleCuotaPendiente.getImporte());
        assertEquals(detalleCuotaPendienteWs.getMoneda().trim(), detalleCuotaPendiente.getMoneda());
    }

    /**
     * Gets the detalle cuota pendiente 1.
     *
     * @return the detalle cuota pendiente 1
     */
    private DetalleCuotaPendienteEntity getDetalleCuotaPendiente_1() {
        DetalleCuotaPendienteEntity detalleCuotaPendiente = new DetalleCuotaPendienteEntity();
        detalleCuotaPendiente.setComprobante("23431780");
        detalleCuotaPendiente.setCuotas("12");
        detalleCuotaPendiente.setCuotasPendientes("02");
        detalleCuotaPendiente.setEstablecimiento("WWW.GARBARINO.COM.AR");
        detalleCuotaPendiente.setFecha("07/07/2015");
        detalleCuotaPendiente.setImporte("316,32");
        detalleCuotaPendiente.setMoneda("pesos");
        return detalleCuotaPendiente;
    }

    /**
     * Gets the detalle cuota pendiente 2.
     *
     * @return the detalle cuota pendiente 2
     */
    private DetalleCuotaPendienteEntity getDetalleCuotaPendiente_2() {
        DetalleCuotaPendienteEntity detalleCuotaPendiente = new DetalleCuotaPendienteEntity();
        detalleCuotaPendiente.setComprobante("04818149");
        detalleCuotaPendiente.setCuotas("12");
        detalleCuotaPendiente.setCuotasPendientes("03");
        detalleCuotaPendiente.setEstablecimiento("GIFTCOLLECTIONSRL");
        detalleCuotaPendiente.setFecha("09/08/2015");
        detalleCuotaPendiente.setImporte("2.122,50");
        detalleCuotaPendiente.setMoneda("pesos");
        return detalleCuotaPendiente;
    }

    /**
     * Gets the detalle cuota pendiente 3.
     *
     * @return the detalle cuota pendiente 3
     */
    private DetalleCuotaPendienteEntity getDetalleCuotaPendiente_3() {
        DetalleCuotaPendienteEntity detalleCuotaPendiente = new DetalleCuotaPendienteEntity();
        detalleCuotaPendiente.setComprobante("19209250");
        detalleCuotaPendiente.setCuotas("12");
        detalleCuotaPendiente.setCuotasPendientes("09");
        detalleCuotaPendiente.setEstablecimiento("WWW.DESPEGAR.COM");
        detalleCuotaPendiente.setFecha("23/01/2016");
        detalleCuotaPendiente.setImporte("8.375,22");
        detalleCuotaPendiente.setMoneda("pesos");
        return detalleCuotaPendiente;
    }

    /**
     * Gets the tarjeta 1.
     *
     * @return the tarjeta 1
     */
    private TarjetaCuotasPendientesEntity getTarjeta_1() {
        TarjetaCuotasPendientesEntity tarjeta = new TarjetaCuotasPendientesEntity();
        tarjeta.setDolares("0,00");
        tarjeta.setPesos("10.814,04");
        List<DetalleCuotaPendienteEntity> detalleCuotaPendienteList = new ArrayList<DetalleCuotaPendienteEntity>();
        detalleCuotaPendienteList.add(getDetalleCuotaPendiente_1());
        detalleCuotaPendienteList.add(getDetalleCuotaPendiente_2());
        detalleCuotaPendienteList.add(getDetalleCuotaPendiente_3());
        tarjeta.setDetalleCuotaPendienteList(detalleCuotaPendienteList);
        return tarjeta;

    }

    /**
     * Gets the detalle cuota pendiente 4.
     *
     * @return the detalle cuota pendiente 4
     */
    private DetalleCuotaPendienteEntity getDetalleCuotaPendiente_4() {
        DetalleCuotaPendienteEntity detalleCuotaPendiente = new DetalleCuotaPendienteEntity();
        detalleCuotaPendiente.setComprobante("47913566");
        detalleCuotaPendiente.setCuotas("12");
        detalleCuotaPendiente.setCuotasPendientes("04");
        detalleCuotaPendiente.setEstablecimiento("VINCENT");
        detalleCuotaPendiente.setFecha("17/09/2015");
        detalleCuotaPendiente.setImporte("866,32");
        detalleCuotaPendiente.setMoneda("pesos");
        return detalleCuotaPendiente;
    }

    /**
     * Gets the tarjeta 2.
     *
     * @return the tarjeta 2
     */
    private TarjetaCuotasPendientesEntity getTarjeta_2() {
        TarjetaCuotasPendientesEntity tarjeta = new TarjetaCuotasPendientesEntity();
        tarjeta.setDolares("0,00");
        tarjeta.setPesos("866,32");
        List<DetalleCuotaPendienteEntity> detalleCuotaPendienteList = new ArrayList<DetalleCuotaPendienteEntity>();
        detalleCuotaPendienteList.add(getDetalleCuotaPendiente_4());
        tarjeta.setDetalleCuotaPendienteList(detalleCuotaPendienteList);
        return tarjeta;
    }

    /**
     * Gets the totales.
     *
     * @return the totales
     */
    private Totales getTotales() {
        Totales totales = new Totales();
        totales.setPesos("11.680,36");
        totales.setDolares("0,00");
        return totales;
    }

    /**
     * Gets the cuotas pendientes.
     *
     * @return the cuotas pendientes
     */
    private CuotasPendientesEntity getCuotasPendientes() {
        List<TarjetaCuotasPendientesEntity> tarjetaList = new ArrayList<TarjetaCuotasPendientesEntity>();
        tarjetaList.add(getTarjeta_1());
        tarjetaList.add(getTarjeta_2());
        CuotasPendientesEntity cuotasPendientes = new CuotasPendientesEntity();
        cuotasPendientes.setTotales(getTotales());
        cuotasPendientes.setTarjetaList(tarjetaList);
        return cuotasPendientes;
    }

    /**
     * Setea la trama a probar.
     *
     * @return the respuesta ws
     */
    private ResponseEntity<String> getRespuestaWs() {
        String body = ("<tarjetas>" + "<tarjeta> <document sessionID=\"bG3nsDYWXIn15Z8Q5KBnlt53\"> "
                + "<datos id=\"112476375\">" + "<affinityGroup>Grupo_Afinidad</affinityGroup>"
                + "<apellido>SPORLEDER</apellido>" + "<categoria>0</categoria>"
                + "<codTipoTarjeta>CHIP EMV C/CONTAC</codTipoTarjeta>" + "<codigoSucursal>201</codigoSucursal>"
                + "<cuenta>413034030</cuenta>" + "<fechaDesde>04/05/2015</fechaDesde>"
                + "<fechaNacimiento>18/11/1984</fechaNacimiento>" + "<habiente>SPORLEDER/BELEN</habiente>"
                + "<nombre>BELEN</nombre>" + "<documento>31303514</documento>" + "<producto>Gold</producto>"
                + "<tarjetaActiva>4509950140722392</tarjetaActiva>" + "<tarjetaProdu>CR0202</tarjetaProdu>"
                + "<telefono>8060529</telefono>" + "<tipoDocumento codigo=\"1\">DNI</tipoDocumento>"
                + "<tipoTarjetaDetalle>CHIP EMV C/CONTAC</tipoTarjetaDetalle>" + "<titular>SPORLEDER/BELEN</titular>"
                + "<vencimiento>1608</vencimiento>" + "<website/>" + "</datos>" + "<CuotasPendientes>" + "<totales>"
                + "<dolares>0,00</dolares>" + "<pesos>11.680,36</pesos>" + "</totales>"
                + "<tarjeta codigoTarjeta=\"XXXX XXXX XXXX2392\">" + "<dolares>0,00</dolares>"
                + "<pesos>10.814,04</pesos>" + "<cuota>" + "<comprobante>23431780</comprobante>" + "<cuotas>12</cuotas>"
                + "<cuotasPendientes>02</cuotasPendientes>"
                + "<establecimiento>WWW.GARBARINO.COM.AR               </establecimiento>" + "<fecha>07/07/2015</fecha>"
                + "<importe>316,32</importe>" + "<moneda>pesos</moneda>" + "</cuota>" + "<cuota>"
                + "<comprobante>04818149</comprobante>" + "<cuotas>12</cuotas>"
                + "<cuotasPendientes>03</cuotasPendientes>"
                + "<establecimiento>GIFT COLLECTION SRL                </establecimiento>" + "<fecha>09/08/2015</fecha>"
                + "<importe>2.122,50</importe>" + "<moneda>pesos</moneda>" + "</cuota>" + "<cuota>"
                + "<comprobante>19209250</comprobante>" + "<cuotas>12</cuotas>"
                + "<cuotasPendientes>09</cuotasPendientes>"
                + "<establecimiento>WWW.DESPEGAR.COM                   </establecimiento>" + "<fecha>23/01/2016</fecha>"
                + "<importe>8.375,22</importe>" + "<moneda>pesos</moneda>" + "</cuota>" + "</tarjeta>"
                + "<tarjeta codigoTarjeta=\"XXXX XXXX XXXX2400\">" + "<dolares>0,00</dolares>" + "<pesos>866,32</pesos>"
                + "<cuota>" + "<comprobante>47913566</comprobante>" + "<cuotas>12</cuotas>"
                + "<cuotasPendientes>04</cuotasPendientes>"
                + "<establecimiento>VINCENT                            </establecimiento>" + "<fecha>17/09/2015</fecha>"
                + "<importe>866,32</importe>" + "<moneda>pesos</moneda>" + "</cuota>" + "</tarjeta>"
                + "</CuotasPendientes>" + "</document>" + "</tarjeta>" + "<tarjeta>"
                + "<document sessionID=\"bG3nsDYWXIn15Z8Q5KBnlt53\">" + "<datos id=\"112476375\">"
                + "<affinityGroup>Grupo_Afinidad</affinityGroup>" + "<apellido>SPORLEDER</apellido>"
                + "<categoria>1</categoria>" + "<codTipoTarjeta>CHIP EMV C/CONTAC</codTipoTarjeta>"
                + "<codigoSucursal>201</codigoSucursal>" + "<cuenta>413034030</cuenta>"
                + "<fechaDesde>04/05/2015</fechaDesde>" + "<fechaNacimiento>11/06/1958</fechaNacimiento>"
                + "<habiente>LOSADA/ANA MARIA</habiente>" + "<nombre>BELEN</nombre>" + "<documento>31303514</documento>"
                + "<producto>Gold</producto>" + "<tarjetaActiva>4509950140722400</tarjetaActiva>"
                + "<tarjetaProdu>CR0202</tarjetaProdu>" + "<telefono>8060529</telefono>"
                + "<tipoDocumento codigo=\"1\">DNI</tipoDocumento>"
                + "<tipoTarjetaDetalle>CHIP EMV C/CONTAC</tipoTarjetaDetalle>" + "<titular>LOSADA/ANA MARIA</titular>"
                + "<vencimiento>1608</vencimiento>" + "<website/>" + "</datos>" + "<CuotasPendientes>" + "<totales>"
                + "<dolares>0,00</dolares>" + "<pesos>866,32</pesos>" + "</totales>"
                + "<tarjeta codigoTarjeta=\"XXXX XXXX XXXX2400\">" + "<dolares>0,00</dolares>" + "<pesos>866,32</pesos>"
                + "<cuota>" + "<comprobante>47913566</comprobante>" + "<cuotas>12</cuotas>"
                + "<cuotasPendientes>04</cuotasPendientes>"
                + "<establecimiento>VINCENT                            </establecimiento>" + "<fecha>17/09/2015</fecha>"
                + "<importe>866,32</importe>" + "<moneda>pesos</moneda>" + "</cuota>" + "</tarjeta>"
                + "</CuotasPendientes>" + "</document>" + "</tarjeta>" + "</tarjetas>");

        ResponseEntity<String> respuestaWs = new ResponseEntity<String>(body, null, HttpStatus.OK);
        return respuestaWs;
    }

}