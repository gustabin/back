/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.mock;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GestionEventoComercialOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GotoLink;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.NotificacionOutEntity;

/**
 * The Class GestionEventoComercialOutEntityMock.
 *
 * @author florencia.n.martinez
 */
public class GestionEventoComercialOutEntityMock {

    /**
     * Completar info cod error.
     *
     * @param codigoError
     *            the codigo error
     * @return the gestion evento comercial out entity
     */
    public static GestionEventoComercialOutEntity completarInfoCodError(String codigoError) {
        GestionEventoComercialOutEntity entity = new GestionEventoComercialOutEntity();
        entity.setCodigoError(codigoError);
        return entity;
    }

    /**
     * Completar info goto landing mas goto link notificaciones.
     *
     * @return the gestion evento comercial out entity
     */
    public static GestionEventoComercialOutEntity completarInfoGotoLandingMasGotoLinkNotificaciones() {
        GestionEventoComercialOutEntity entity = new GestionEventoComercialOutEntity();
        entity.setCantNotifSinLeer("0");
        entity.setTotalNotif("1");
        entity.setNotificaciones(obtenerListaNot());
        entity.setNup("00276937");
        entity.setCodigoError("ERROR000");
        entity.setDescripcionError("");
        return entity;
    }

    /**
     * Obtener lista not.
     *
     * @return the list
     */
    private static List<NotificacionOutEntity> obtenerListaNot() {
        List<NotificacionOutEntity> notificaciones = new ArrayList<NotificacionOutEntity>();
        NotificacionOutEntity notificacion = new NotificacionOutEntity();
        notificacion.setIdNotificacion("12326906");
        notificacion.setNup("00276937");
        notificacion.setCodigo("A00003");
        notificacion.setSubCodigo("1");
        notificacion.setTitulo("Cheque rechazado");
        notificacion.setMensajeAmigable(
                "El cheque por <strong>$16.230,00</strong> depositado en tu cuenta <strong>XXXX-XXX3937</strong> fue rechazado. Por favor, acercate a retirarlo en tu sucursal. Estos son tus Ãºltimos movimientos.");
        notificacion.setIndicaLectura("S");
        notificacion.setIndicaInactivable("N");
        notificacion.setUrl("");
        notificacion.setLink("gotoLanding(Titulo web content;www.santanderrio.com.ar;gotoLink(www.sube.com.ar;E))");
        notificacion.setGotoLink(new GotoLink(notificacion.getLink()));
        notificacion.setFechaAlta("2018-02-22");
        notificaciones.add(notificacion);
        return notificaciones;
    }

}
