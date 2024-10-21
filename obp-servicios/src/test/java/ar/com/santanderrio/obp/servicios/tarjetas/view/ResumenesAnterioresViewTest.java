package ar.com.santanderrio.obp.servicios.tarjetas.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ResumenAnterior;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ResumenesAnterioresView;

/**
 * The Class ResumenesAnterioresViewTest.
 */
public class ResumenesAnterioresViewTest {

    /**
     * Crear respuesta ok resumenes anteriores.
     */
    @Test
    public void crearRespuestaOkResumenesAnteriores() {

        ResumenesAnterioresView resumenesAnterioresView = new ResumenesAnterioresView();
        resumenesAnterioresView.setMarca("VISA");
        resumenesAnterioresView.setNumero("XXXX-8569");

        ResumenAnterior resumen = new ResumenAnterior();
        resumen.setFecha("10 de Febrero del 2016");

        ResumenAnterior resumen2 = new ResumenAnterior();
        resumen2.setFecha("08 de Marzo del 2016");

        ResumenAnterior resumen3 = new ResumenAnterior();
        resumen3.setFecha("12 de Abril del 2016");

        ResumenAnterior resumen4 = new ResumenAnterior();
        resumen4.setFecha("09 de Mayo del 2016");

        ResumenAnterior resumen5 = new ResumenAnterior();
        resumen5.setFecha("10 de Junio del 2016");

        ResumenAnterior resumen6 = new ResumenAnterior();
        resumen6.setFecha("08 de Julio del 2016");

        List<ResumenAnterior> resumenes = new ArrayList<ResumenAnterior>();
        resumenes.add(resumen);
        resumenes.add(resumen2);
        resumenes.add(resumen3);
        resumenes.add(resumen4);
        resumenes.add(resumen5);
        resumenes.add(resumen6);

        resumenesAnterioresView.setResumenes(resumenes);

        Respuesta<ResumenesAnterioresView> respuesta = new Respuesta<ResumenesAnterioresView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuestaVacia(false);
        respuesta.setRespuesta(resumenesAnterioresView);

        Gson gson = new Gson();
        String json = gson.toJson(respuesta);
        Assert.assertNotNull(json);
        System.out.println(json);

    }

    /**
     * Crear respuesta error sin resumenes disponibles.
     */
    @Test
    public void crearRespuestaErrorSinResumenesDisponibles() {
        ResumenesAnterioresView resumenes = new ResumenesAnterioresView();
        resumenes.setMarca("VISA");
        resumenes.setNumero("XXXX - 8569");

        Respuesta<ResumenesAnterioresView> respuesta = new Respuesta<ResumenesAnterioresView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuesta.setRespuesta(resumenes);

        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(TipoError.SIN_RESUMENES.getDescripcion());
        item.setMensaje("Aún no tenes resúmenes");

        respuesta.add(item);

        Gson gson = new Gson();
        String json = gson.toJson(respuesta);
        Assert.assertNotNull(json);
        System.out.println(json);

    }

    /**
     * Crear respuesta error generico.
     */
    @Test
    public void crearRespuestaErrorGenerico() {
        Respuesta<ResumenesAnterioresView> respuesta = new Respuesta<ResumenesAnterioresView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);

        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        item.setMensaje(
                "Lo sentimos. Hubo un error en la carga de tus resúmenes. Por favor, intentalo en unos minutos");

        respuesta.add(item);

        Gson gson = new Gson();
        String json = gson.toJson(respuesta);
        Assert.assertNotNull(json);
        System.out.println(json);

    }

}
