/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioRSADTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.BanelcoOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;

/**
 * @author sabrina.cis
 *
 */
public class ComprobanteAltaDestinatarioViewTest {

    /**
     * Gets the consulta agenda destinatario in entity.
     *
     * @return the consulta agenda destinatario in entity test
     */
    @Test
    public void ConfirmacionAltaDestinatarioView_FEEDBACK_VALIDACION_OK_Test() {
        // Desafio OK
        AutentificacionDTO desafio = obtenerDesafio();
        desafio.setReintentosAgotados(true);
        desafio.setValorNotificarRSA(false);

        // View
        ConfirmacionAltaDestinatarioView view = new ConfirmacionAltaDestinatarioView();
        view.setDesafio(desafio);
        view.setFecha("25/2/2017");
        view.setHora("12:32");
        view.setMensaje("mensaje");
        view.setMensajeEfectivizacion(
                "\u003cp\u003eEl destinatario \u003cb\u003e\"pepe\"\u003c/b\u003e fue añadido a tu \u003cb\u003eagenda de destinatarios\u003c/b\u003e con éxito.\u003c/p\u003e");
        view.setNroComprobante("1234567890");

        // Respuesta
        Respuesta<ConfirmacionAltaDestinatarioView> respuesta = new Respuesta<ConfirmacionAltaDestinatarioView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(view);
        respuesta.setRespuestaVacia(false);

        // Json
        Gson gson = new Gson();
        String json = gson.toJson(respuesta);
        Assert.assertNotNull(json);
        System.out.println(json);

    }

    @Test
    public void ComprobanteAltaDestinatarioView_SOLICITUD_OK_Test() {
        BanelcoOperacionDTO banelcoOperacion = new BanelcoOperacionDTO();
        banelcoOperacion.setDigitos(null);
        banelcoOperacion.setIngresoDigitos(null);
        banelcoOperacion.setMensaje(
                "<p><b>¡Ya casi terminás!</b></p><p>Confirmá tu operación ingresando los últimos 8 dígitos de tu <b>Tarjeta de débito</b> terminada en <b>{0}</b>.</p>");
        banelcoOperacion.setTipoDesafio(TipoDesafioEnum.BANELCO);

        // Desafio Warning
        AutentificacionDTO desafio = obtenerDesafio();
        desafio.setBanelcoOperacion(banelcoOperacion);
        desafio.setCoordenadasOperacion(null);
        desafio.setOperacion(3);
        desafio.setReintentosAgotados(false);
        desafio.setRsaDTO(null);
        desafio.setTipoDesafio(TipoDesafioEnum.BANELCO);
        desafio.setTokenOperacion(null);
        desafio.setReintentosAgotados(false);
        desafio.setValorNotificarRSA(false);

        // View
        ConfirmacionAltaDestinatarioView view = new ConfirmacionAltaDestinatarioView();
        view.setDesafio(desafio);
        view.setFecha("25/2/2017");
        view.setHora(null);
        view.setMensaje(null);
        view.setMensajeEfectivizacion(null);
        view.setNroComprobante(null);

        // Item Mensaje
        List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setDetalleTipoError(null);
        item.setExtra(null);
        item.setMensaje(null);
        item.setTag(null);
        item.setTipoError("DESAFIO");
        itemMensajeRespuesta.add(item);

        // respuesta
        Respuesta<ConfirmacionAltaDestinatarioView> respuesta = new Respuesta<ConfirmacionAltaDestinatarioView>();
        respuesta.setItemMensajeRespuesta(itemMensajeRespuesta);
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuesta.setRespuesta(view);
        respuesta.setRespuestaVacia(false);

        // Json
        Gson gson = new Gson();
        String json = gson.toJson(respuesta);
        Assert.assertNotNull(json);
        System.out.println(json);

    }

    @Test
    public void ComprobanteAltaDestinatarioView_ERROR_DESAFIO_Test() {

        // Item Mensaje
        List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setDetalleTipoError(null);
        item.setExtra(null);
        item.setMensaje("El código ingresado es incorrecto. Intentá nuevamente.");
        item.setTag(null);
        item.setTipoError("ERROR_DESAFIO");
        itemMensajeRespuesta.add(item);

        // respuesta
        Respuesta<ComprobanteAltaDestinatarioView> respuesta = new Respuesta<ComprobanteAltaDestinatarioView>();
        respuesta.setItemMensajeRespuesta(itemMensajeRespuesta);
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuesta.setRespuesta(null);
        respuesta.setRespuestaVacia(true);

        // Json
        Gson gson = new Gson();
        String json = gson.toJson(respuesta);
        Assert.assertNotNull(json);
        System.out.println(json);

    }

    @Test
    public void ComprobanteAltaDestinatarioView_ERROR_DESAFIO_2_Test() {

        // Item Mensaje
        List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setDetalleTipoError(null);
        item.setExtra(null);
        item.setMensaje("El código ingresado es incorrecto. Intentá nuevamente.");
        item.setTag(null);
        item.setTipoError("DEBITO_NUMEROS_EQUIVOCADOS");
        itemMensajeRespuesta.add(item);

        // respuesta
        Respuesta<ComprobanteAltaDestinatarioView> respuesta = new Respuesta<ComprobanteAltaDestinatarioView>();
        respuesta.setItemMensajeRespuesta(itemMensajeRespuesta);
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuesta.setRespuesta(null);
        respuesta.setRespuestaVacia(true);

        // Json
        Gson gson = new Gson();
        String json = gson.toJson(respuesta);
        Assert.assertNotNull(json);
        System.out.println(json);

    }

    @Test
    public void ComprobanteAltaDestinatarioView_SIN_METODO_DESAFIO_Test() {

        // Item Mensaje
        List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setDetalleTipoError(null);
        item.setExtra(null);
        item.setMensaje("Sin metodo de autenticacion.");
        item.setTag(null);
        item.setTipoError("SIN_METODO_DESAFIO");
        itemMensajeRespuesta.add(item);

        // respuesta
        Respuesta<ComprobanteAltaDestinatarioView> respuesta = new Respuesta<ComprobanteAltaDestinatarioView>();
        respuesta.setItemMensajeRespuesta(itemMensajeRespuesta);
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuesta.setRespuesta(null);
        respuesta.setRespuestaVacia(true);

        // Json
        Gson gson = new Gson();
        String json = gson.toJson(respuesta);
        Assert.assertNotNull(json);
        System.out.println(json);

    }

    @Test
    public void ComprobanteAltaDestinatarioView_TOKEN_SOLICITUD_ERROR_GENERICO_Test() {

        // Item Mensaje 1199 - SIN_METODO_DESAFIO - WARNING
        List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setDetalleTipoError(null);
        item.setExtra(null);
        item.setMensaje(
                "<p>¡Lo sentimos!</p><p>Para realizar esta transacción es necesario que tengas un Token de seguridad asociado a tu cuenta.</p>");
        item.setTag("TOKEN");
        item.setTipoError("SIN_METODO_DESAFIO");
        itemMensajeRespuesta.add(item);

        // respuesta
        Respuesta<ComprobanteAltaDestinatarioView> respuesta = new Respuesta<ComprobanteAltaDestinatarioView>();
        respuesta.setItemMensajeRespuesta(itemMensajeRespuesta);
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuesta.setRespuesta(null);
        respuesta.setRespuestaVacia(true);

        // Json
        Gson gson = new Gson();
        String json = gson.toJson(respuesta);
        Assert.assertNotNull(json);
        System.out.println(json);

    }

    private AutentificacionDTO obtenerDesafio() {
        AgendaDestinatarioRSADTO agendaRsaDTO = new AgendaDestinatarioRSADTO(false, "ALIAS123", "072-123456/7",
                TipoAgendaEnum.AGENDA_OTROS_BANCOS, "23-222222-2");

        // desafio banelcoOperacion
        AutentificacionDTO desafio = new AutentificacionDTO();
        desafio.setOperacion(3);
        desafio.setTipoDesafio(TipoDesafioEnum.BANELCO);
        desafio.setRsaDTO(agendaRsaDTO);
        desafio.setValorNotificarRSA(true);
        desafio.setClienteDTO(null);
        desafio.setCoordenadasOperacion(null);
        desafio.setTokenOperacion(null);

        return desafio;
    }

}
