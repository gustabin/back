/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.CasuisticaErrorTarjetasBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumoTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumosView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.LineaDetalleConsumoTarjetaView;

/**
 * The Class CasuisticaErrorTarjetasBOTest.
 *
 * @author sabrina.cis
 */
@RunWith(MockitoJUnitRunner.class)
public class CasuisticaErrorTarjetasBOTest {

    /** The casuistica. */
    @InjectMocks
    private CasuisticaErrorTarjetasBO casuistica = new CasuisticaErrorTarjetasBOImpl();

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    MensajeDAO mensajeDAO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje. */
    Mensaje mensaje = new Mensaje();

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mensaje.setMensaje("Mensaje");
    }

    /**
     * Es respuesta OK.
     *
     */
    @Test
    public void esRespuestaOK() {
        EstadoRespuesta estadoRespuesta = EstadoRespuesta.OK;
        Boolean res = casuistica.esRespuestaOK(estadoRespuesta);
        Assert.assertEquals(res, true);
    }

    /**
     * Es respuesta warning.
     *
     */
    @Test
    public void esRespuestaWarning() {
        EstadoRespuesta estadoRespuesta = EstadoRespuesta.WARNING;
        Boolean res = casuistica.esRespuestaWarning(estadoRespuesta);
        Assert.assertEquals(res, true);
    }

    /**
     * Es respuesta error.
     *
     */
    @Test
    public void esRespuestaError() {
        EstadoRespuesta estadoRespuesta = EstadoRespuesta.ERROR;
        Boolean res = casuistica.esRespuestaError(estadoRespuesta);
        Assert.assertEquals(res, true);
    }

    /**
     * Crear respuesta ok.
     *
     */
    @Test
    public void crearRespuestaOk() {
        ConsumosView view = obtenerConsumosView();
        view.setEstadoRespuestaConsumosPendientes(EstadoRespuesta.OK);
        view.setMuestraTarjetasConCabecera(true);
        Respuesta<ConsumosView> rta = new Respuesta<ConsumosView>();
        rta.setEstadoRespuesta(EstadoRespuesta.OK);
        rta.setRespuesta(view);
        rta.setRespuestaVacia(false);

        Mockito.when(respuestaFactory.crearRespuestaOk(ConsumosView.class, view)).thenReturn(rta);

        Respuesta<ConsumosView> respuesta = casuistica.crearRespuestaOk(ConsumosView.class, view);
        Assert.assertEquals(respuesta, rta);

        generarJson(respuesta, "-- Grilla OK --");
    }

    /**
     * Generar json.
     *
     * @param respuesta
     *            the respuesta
     * @param titulo
     *            the titulo
     */
    private void generarJson(Respuesta<ConsumosView> respuesta, String titulo) {
        Gson gson = new Gson();
        String json = gson.toJson(respuesta);
        Assert.assertNotNull(json);
        System.out.println(titulo);
        System.out.println(json);
        System.out.println(" ");
    }

    /**
     * Obtener consumos view.
     *
     * @return the consumos view
     */
    private ConsumosView obtenerConsumosView() {
        ConsumosView res = new ConsumosView();
        List<ConsumoTarjetaView> consumosTarjetas = new ArrayList<ConsumoTarjetaView>();
        ConsumoTarjetaView consumoTarjetaView = obtenerConsumoTarjetaView();
        consumosTarjetas.add(consumoTarjetaView);
        res.setConsumosTarjetas(consumosTarjetas);
        res.setEstadoRespuestaConsumos(EstadoRespuesta.OK);
        res.setEstadoRespuestaConsumosPendientes(EstadoRespuesta.OK);
        res.setMuestraTarjetasConCabecera(true);
        return res;
    }

    /**
     * Obtener consumo tarjeta view.
     *
     * @return the consumo tarjeta view
     */
    private ConsumoTarjetaView obtenerConsumoTarjetaView() {
        ConsumoTarjetaView res = new ConsumoTarjetaView();
        res.setConsumoDolares(new BigDecimal(1200));
        res.setConsumoPesos(new BigDecimal(1200));
        res.setHasConsumoDolaresCero(false);
        res.setHasConsumoPesosCero(false);
        res.setHasError(false);
        res.setIsAdicional(false);
        res.setIsTitular(true);
        res.setLineas(obtenerLineas());
        res.setMarca("VISA");
        res.setNombreAdicional("Marcos");
        res.setNumero("221-579806/6");
        return res;
    }

    /**
     * Obtener lineas.
     *
     * @return the list
     */
    private List<LineaDetalleConsumoTarjetaView> obtenerLineas() {
        List<LineaDetalleConsumoTarjetaView> lineas = new ArrayList<LineaDetalleConsumoTarjetaView>();
        LineaDetalleConsumoTarjetaView linea1 = new LineaDetalleConsumoTarjetaView();
        linea1.setCodigoEstablecimiento("2588");
        linea1.setConsumoDolares(false);
        linea1.setConsumoPesos(true);
        linea1.setCuota("6");
        linea1.setCuotasCanceladas(4);
        linea1.setCuotasTotales(12);
        linea1.setDescripcion("Colombraro");
        linea1.setFecha(new Date());
        linea1.setImporteDolares(null);
        linea1.setImportePesos(new BigDecimal(1000));
        linea1.setComprobante("0000001234");
        linea1.setNroReferencia("0000001234");
        linea1.setNroTarjeta("221-579806/6");
        linea1.setTieneCuota(true);
        lineas.add(linea1);

        LineaDetalleConsumoTarjetaView linea2 = new LineaDetalleConsumoTarjetaView();
        linea2.setCodigoEstablecimiento("2567");
        linea2.setConsumoDolares(false);
        linea2.setConsumoPesos(true);
        linea2.setCuota("3");
        linea2.setCuotasCanceladas(4);
        linea2.setCuotasTotales(7);
        linea2.setDescripcion("Prune");
        linea2.setFecha(new Date());
        linea2.setImporteDolares(null);
        linea2.setImportePesos(new BigDecimal(2000));
        linea2.setComprobante("0000001234");
        linea2.setNroReferencia("0000001234");
        linea2.setNroTarjeta("221-579806/6");
        linea2.setTieneCuota(true);
        lineas.add(linea2);
        return lineas;
    }

}
