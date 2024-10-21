/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GotoLink;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.NotificacionOutEntity;

/**
 * The Class NotificacionComercialViewTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class NotificacionComercialViewTest {

    /**
     * Obtener notificacion comercial view test.
     */
    @Test
    public void obtenerNotificacionComercialViewTest() {
        // Given
        NotificacionOutEntity notificacion = new NotificacionOutEntity();
        notificacion.setIdNotificacion("12326906");
        notificacion.setTitulo("Cheque rechazado");
        notificacion.setMensajeAmigable(
                "El cheque por <strong>$16.230,00</strong> depositado en tu cuenta <strong>XXXX-XXX3937</strong> fue rechazado. Por favor, acercate a retirarlo en tu sucursal. Estos son tus Ãºltimos movimientos.");
        notificacion.setGotoLink(new GotoLink("gotoLink(https://santanderrio.andreani.com;E)"));
        notificacion.setUrl("");
        notificacion.setIndicaLectura("S");
        notificacion.setFechaAlta("2018-02-22");

        // When
        NotificacionComercialView notif = new NotificacionComercialView(notificacion, null);

        // Then
        Assert.assertEquals("Conocé más", notif.getBoton());
        Assert.assertEquals("https://santanderrio.andreani.com", notif.getContenidoLink().getUrl());
        Assert.assertEquals("E", notif.getContenidoLink().getTipoAcceso());
    }
}
