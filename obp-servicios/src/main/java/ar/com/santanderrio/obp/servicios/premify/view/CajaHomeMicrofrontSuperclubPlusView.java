package ar.com.santanderrio.obp.servicios.premify.view;

import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeMicrofrontView;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CajaHomeMicrofrontSuperclubPlusView extends CajaHomeMicrofrontView {

    @JsonProperty("notificaciones")
    private NotificacionView notificaciones;

    public CajaHomeMicrofrontSuperclubPlusView(String titulo, String encabezado, String descripcion, String nombreMicrofront, String urlMicrofront) {
        super(titulo, encabezado, descripcion, nombreMicrofront, urlMicrofront);
    }

    public NotificacionView getNotificacionView() {
        return this.notificaciones;
    }

    public void setNotificacionView(NotificacionView notificacionView) {
        this.notificaciones = notificacionView;
    }
}
