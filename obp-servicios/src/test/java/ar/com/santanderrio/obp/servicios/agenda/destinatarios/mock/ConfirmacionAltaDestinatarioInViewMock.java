package ar.com.santanderrio.obp.servicios.agenda.destinatarios.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.Desafio;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.TokenDesafio;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.TokenOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Class ConfirmacionAltaDestinatarioInViewMock.
 *
 * @author florencia.n.martinez
 */
public final class ConfirmacionAltaDestinatarioInViewMock {
    
    private ConfirmacionAltaDestinatarioInViewMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtener token operacion DTO.
     *
     * @return the token operacion DTO
     */
    public static TokenOperacionDTO obtenerTokenOperacionDTO() {
        TokenOperacionDTO tokenOperacionDTO = new TokenOperacionDTO();
        tokenOperacionDTO.setTipoDesafio(TipoDesafioEnum.TOKEN);
        tokenOperacionDTO.setMensaje(
                "<p><b>¡Ya casi terminás!</b></p><p>Confirmá tu operación ingresando los 6 dígitos de seguridad.</p>");
        return tokenOperacionDTO;
    }

    /**
     * Obtener sesion parametros desafio.
     *
     * @return the desafio
     */
    public static Desafio<AutentificacionDTO> obtenerSesionParametrosDesafio(Boolean desafioEnSesion) {
        if (desafioEnSesion) {
            TokenDesafio desafio = new TokenDesafio();
            desafio.setAutentificacionDTO(new AutentificacionDTO());
            return desafio;
        }
        return null;
    }

    /**
     * Obtiene la entidad confirmacion alta destinatario in view.
     *
     * @return the confirmacion alta destinatario in view
     */
    public static ConfirmacionAltaDestinatarioView obtenerConfirmacionAltaDestinatarioInView() {
        ConfirmacionAltaDestinatarioView view = new ConfirmacionAltaDestinatarioView();
        view.setIsCuit(false);
        view.setBancoDestino(BancoEnum.SANTANDER_RIO.getDescripcion());
        view.setCbu("478483463873");
        view.setAlias("EsteEsMiAlias");
        view.setNumeroDocumento("30173299881");
        view.setReferencia("Contador");
        view.setTitular("MUÑOZ, CÉSAR ALBERTO.");
        view.setDireccionCorreo("cesar@gmail.com");
        view.setNroCuenta("027-123456/7");
        view.setIdTipoCuenta("02");
        view.setDesafio(new AutentificacionDTO());
        return view;
    }

    /**
     * Obtiene la entidad confirmacion alta destinatario in view.
     *
     * @return the confirmacion alta destinatario in view
     */
    public static ConfirmacionAltaDestinatarioView obtenerConfirmacionAltaDestinatarioOtrosBancosInView() {
        ConfirmacionAltaDestinatarioView datosConfirmados = new ConfirmacionAltaDestinatarioView();
        datosConfirmados.setIsCuit(false);
        datosConfirmados.setNumeroDocumento("30173299881");
        datosConfirmados.setReferencia("Contador");
        datosConfirmados.setTitular("MUÑOZ, CÉSAR ALBERTO.");
        datosConfirmados.setDireccionCorreo("cesar@gmail.com");
        datosConfirmados.setCbu("0123456789012345678901");
        return datosConfirmados;
    }

    /**
     * Obtiene el cliente.
     *
     * @return the cliente
     */
    public static Cliente obtenerCliente() {
        Cliente cliente = new Cliente();
        cliente.setNup("233223");
        cliente.setApellido1("Muñoz");
        cliente.setNombre("Cesar Alberto");
        cliente.setNumeroCUILCUIT("30173299881");
        ;
        return cliente;
    }

    /**
     * Obtiene la confirmacion alta destinatario in view con campos completos
     * con espacios.
     *
     * @return the confirmacion alta destinatario in view
     */
    public static ConfirmacionAltaDestinatarioView obtenerConfirmacionAltaDestinatarioView() {
        ConfirmacionAltaDestinatarioView datosConfirmados = new ConfirmacionAltaDestinatarioView();
        datosConfirmados.setIsCuit(false);
        datosConfirmados.setNumeroDocumento("20-13238861-0");
        datosConfirmados.setReferencia("Contador" + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 22));
        datosConfirmados.setTitular("MUÑOZ, CÉSAR ALBERTO." + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 43));
        datosConfirmados
                .setDireccionCorreo("cesar@gmail.com" + StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, 85));
        datosConfirmados.setNroCuenta("0027-00000123456/7");
        datosConfirmados.setIdTipoCuenta("02");
        return datosConfirmados;
    }


    /**
     * Obtener respuesta fin desafio OK.
     *
     * @return the respuesta
     */
    public static Respuesta<ConfirmacionAltaDestinatarioView> obtenerRespuesta_Desafio_Token_OK() {

        // View
        ConfirmacionAltaDestinatarioView view = new ConfirmacionAltaDestinatarioView();
        view.setIsCuit(false);
        view.setDireccionCorreo("cesar@gmail.com");
        view.setIdTipoCuenta("02");
        view.setNroCuenta("000-063917/0");
        view.setNumeroDocumento("20-13238861-0");
        view.setReferencia("Referencia Test");
        view.setTitular("COMIGNAGHI VALERIANO PAUL TAD");
        view.setCbu("0720370988000035087922");
        view.setDesafio(new AutentificacionDTO());
        view.setMensajeEfectivizacion(
                "<p><b>¡Ya casi terminás!</b></p><p>Confirmá tu operación ingresando los 6 dígitos de seguridad.</p>");

        // Respuesta
        Respuesta<ConfirmacionAltaDestinatarioView> respuesta = new Respuesta<ConfirmacionAltaDestinatarioView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuesta.setRespuesta(view);
        respuesta.setRespuestaVacia(false);
        return respuesta;
    }

    public static <T> Respuesta<T> crearRespuesta(Class<T> respuestaClass, T data, EstadoRespuesta estado,
            Boolean vacia, List<ItemMensajeRespuesta> itemMensajes) {
        Respuesta<T> rta = new Respuesta<T>();
        rta.setRespuesta(data);
        rta.setEstadoRespuesta(estado);
        rta.setRespuestaVacia(vacia);
        rta.setItemMensajeRespuesta(itemMensajes);
        return rta;
    };

    public static List<ItemMensajeRespuesta> crearItemMensajeRespuesta(String tag, TipoError tipoError,
            String mensaje) {
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(tipoError.getDescripcion());
        item.setTag(tag);
        item.setMensaje(mensaje);
        List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        items.add(item);
        return items;
    }

    /**
     * Obtener respuesta fin desafio OK.
     *
     * @return the respuesta
     */
    public static Respuesta<ComprobanteAltaDestinatarioDTO> obtenerRespuestaComprobanteAltaDestinatarioDTO() {
        ComprobanteAltaDestinatarioDTO dto = new ComprobanteAltaDestinatarioDTO();
        dto.setFecha(new Date());
        dto.setHora("12:34");
        dto.setNroComprobante("00000001");
        dto.setMensajeEfectivizacion(
                "<p>El destinatario <b>\"Referencia Test\"</b> fue añadido a tu <b>agenda de destinatarios</b> con éxito.</p>");
        return crearRespuesta(ComprobanteAltaDestinatarioDTO.class, dto, EstadoRespuesta.OK, false, null);
    }
    
    /**
     * Obtiene el registro de sesion.
     *
     * @return the registro sesion
     */
    public static  RegistroSesion obtenerRegistroSesion() {
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("123456789012345");
        return registroSesion;
    }

}