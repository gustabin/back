/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.util.CollectionUtils;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GestionEventoComercialOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.NotificacionOutEntity;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * The Class NotificacionesComercialesView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class NotificacionesComercialesView {

    /** The sin leer. */
    private Integer sinLeer;

    /** The total. */
    private Integer total;

    /** The notificaciones. */
    private List<NotificacionComercialView> notificaciones;
    
	private static final String GOTO_SEGUROS_COTIZACION = "gotoSeguroCotizarSolicitar()";
	
	private static final String GOTO_LANDING = "gotoLanding()";


    /**
     * Instantiates a new notificaciones comerciales view.
     *
     * @param notificaciones
     *            the notificaciones
     */
    public NotificacionesComercialesView(GestionEventoComercialOutEntity notificaciones, TokenView seguros, Cliente cliente) {
        this.sinLeer = Integer.parseInt(notificaciones.getCantNotifSinLeer());
        this.total = Integer.parseInt(notificaciones.getTotalNotif());

        if (!CollectionUtils.isEmpty(notificaciones.getNotificaciones())) {
            this.notificaciones = new ArrayList<NotificacionComercialView>();
            for (NotificacionOutEntity notificacion : notificaciones.getNotificaciones()) {
            	NotificacionComercialView notificacionView = new NotificacionComercialView(notificacion, cliente);
           	
            	if (GOTO_SEGUROS_COTIZACION.equals(notificacionView.getLink())) {
            		notificacionView.getContenidoLink().setDataUrl(armarURLCotizacion(seguros.getUrl()));
            		notificacionView.getContenidoLink().setTipoAcceso(seguros.getTokenFirmado());
            		notificacionView.getContenidoLink().setUrl(null);
            	} else if (GOTO_LANDING.equals(notificacionView.getLink()) && GOTO_SEGUROS_COTIZACION.equals(notificacionView.getContenido().getLink())) {
            		notificacionView.getContenido().setDataUrl(armarURLCotizacion(seguros.getUrl()));
            		notificacionView.getContenido().setTipoAcceso(seguros.getTokenFirmado());	
            	}
            	
            	this.notificaciones.add(notificacionView);
            }
        }
    }

    private String armarURLCotizacion(String url) {
    	
    	String urlFinal = "proteccion?jwt=";
    	String[] urlPorPartes = url.split("#!/");

    	return urlPorPartes[0] + "#!/" + urlFinal;
    }
    
    /**
     * Gets the sin leer.
     *
     * @return the sin leer
     */
    public Integer getSinLeer() {
        return sinLeer;
    }

    /**
     * Sets the sin leer.
     *
     * @param sinLeer
     *            the new sin leer
     */
    public void setSinLeer(Integer sinLeer) {
        this.sinLeer = sinLeer;
    }

    /**
     * Gets the total.
     *
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * Sets the total.
     *
     * @param total
     *            the new total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * Gets the notificaciones.
     *
     * @return the notificaciones
     */
    public List<NotificacionComercialView> getNotificaciones() {
        return notificaciones;
    }

    /**
     * Sets the notificaciones.
     *
     * @param notificaciones
     *            the new notificaciones
     */
    public void setNotificaciones(List<NotificacionComercialView> notificaciones) {
        this.notificaciones = notificaciones;
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(sinLeer);
        hcb.append(total);
        return hcb.toHashCode();
    }

    /**
     * Equals.
     *
     * @param obj
     *            the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NotificacionesComercialesView other = (NotificacionesComercialesView) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(sinLeer, other.getSinLeer());
        eb.append(total, other.getTotal());
        return eb.isEquals();
    }

    /**
     * ToString.
     *
     * @return the string
     */
    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this).append("sinLeer", sinLeer).append("total", total)
                .append("notificaciones", notificaciones).toString();
    }

}
