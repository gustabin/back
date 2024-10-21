/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * The Class OfertasComercialesView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class OfertasComercialesView {
	 
    /** The tiempo autodesplazamiento. */
    private Integer tiempoAutodesplazamiento;

    /** The ofertas. */
    private List<OfertaComercialView> ofertas = new ArrayList<OfertaComercialView>();

    /** The mostrar encuesta. */
    private Boolean mostrarEncuesta = false;

	private static final String GOTO_SEGUROS_COTIZACION = "gotoSeguroCotizarSolicitar()";
	
	private static final String GOTO_LANDING = "gotoLanding()";

    private static final String  MOBP_14288867_1_690 = "MOBP_14288867_1_690";
    private static final String  MOBP_14288867_2_690 = "MOBP_14288867_2_690";
    private static final String  MOBP_14288867_3_690 = "MOBP_14288867_3_690";

    private Boolean mostrarAceptacionDuo = false;

    /**
     * Cargar ofertas carrusel.
     *
     * @param eventosComercialesDTO
     *            the eventos comerciales DTO
     * @param cliente
     */
    public void cargarOfertasCarrusel(EventosComercialesDTO eventosComercialesDTO, TokenView seguros, Cliente cliente) {
    	for (OfertaComercialDTO ofertaComercialDTO : eventosComercialesDTO.getOfertas()) {
            if (ofertaComercialDTO.perteneceACarrusel()) {
            	
            	OfertaComercialView oferta = new OfertaComercialView(ofertaComercialDTO, true);
            	
            	if (GOTO_SEGUROS_COTIZACION.equals(oferta.getLink())) {
            		oferta.getContenidoLink().setDataUrl(armarURLCotizacion(seguros.getUrl()));
            		oferta.getContenidoLink().setTipoAcceso(seguros.getTokenFirmado());
            		oferta.getContenidoLink().setUrl(null);
            	} else if (GOTO_LANDING.equals(oferta.getLink()) && GOTO_SEGUROS_COTIZACION.equals(oferta.getContenido().getLink())) {
            		oferta.getContenido().setDataUrl(armarURLCotizacion(seguros.getUrl()));
            		oferta.getContenido().setTipoAcceso(seguros.getTokenFirmado());	
            	}

            	cliente.setTipoOfertaRefinanciacion(obtenerTipoDeOfertaRefinanciacion(oferta));
            	this.ofertas.add(oferta);
            }
        }
    }

    private String obtenerTipoDeOfertaRefinanciacion(OfertaComercialView oferta) {
        if(oferta.getId() != null && oferta.getId().indexOf(MOBP_14288867_1_690) > -1){
            return Cliente.EARLY_IRREGULAR;
        }
        if(oferta.getId() != null && oferta.getId().indexOf(MOBP_14288867_2_690) > -1){
            return Cliente.EARLY_CURRENT;
        }
        if(oferta.getId() != null && oferta.getId().indexOf(MOBP_14288867_3_690) > -1){
            return Cliente.CUOTAPHONE;
        }
        return null;
    }

    /**
     * Cargar ofertas seccion.
     *
     * @param eventosComercialesDTO
     *            the eventos comerciales DTO
     */
    public void cargarOfertasSeccion(EventosComercialesDTO eventosComercialesDTO, TokenView seguros) {
    	for (OfertaComercialDTO ofertaComercialDTO : eventosComercialesDTO.getOfertas()) {
            if (StringUtils.isNotBlank(ofertaComercialDTO.getUbicacionSeccion()) && !ofertaComercialDTO.getCerrado()) {
            	
            	OfertaComercialView oferta = new OfertaComercialView(ofertaComercialDTO, false);
            	
            	if (GOTO_SEGUROS_COTIZACION.equals(oferta.getLink())) {
            		
            		oferta.getContenidoLink().setDataUrl(armarURLCotizacion(seguros.getUrl()));
            		oferta.getContenidoLink().setTipoAcceso(seguros.getTokenFirmado());
            		oferta.getContenidoLink().setUrl(null);
            	} else if (GOTO_LANDING.equals(oferta.getLink()) && GOTO_SEGUROS_COTIZACION.equals(oferta.getContenido().getLink())) {
            		oferta.getContenido().setDataUrl(armarURLCotizacion(seguros.getUrl()));
            		oferta.getContenido().setTipoAcceso(seguros.getTokenFirmado());	
            	}
            	
                 this.ofertas.add(oferta);
            }
        }
    }
    
    
    private String armarURLCotizacion(String url) {
    	    	    	
    	String urlFinal = "proteccion?jwt=";
    	String[] urlPorPartes = url.split("#!/");

    	return urlPorPartes[0] + "#!/" + urlFinal;
    }

    /**
     * Gets the tiempo autodesplazamiento.
     *
     * @return the tiempo autodesplazamiento
     */
    public Integer getTiempoAutodesplazamiento() {
        return tiempoAutodesplazamiento;
    }

    /**
     * Sets the tiempo autodesplazamiento.
     *
     * @param tiempoAutodesplazamiento
     *            the new tiempo autodesplazamiento
     */
    public void setTiempoAutodesplazamiento(Integer tiempoAutodesplazamiento) {
        this.tiempoAutodesplazamiento = tiempoAutodesplazamiento;
    }

    /**
     * Gets the ofertas.
     *
     * @return the ofertas
     */
    public List<OfertaComercialView> getOfertas() {
        return ofertas;
    }

    /**
     * Sets the ofertas.
     *
     * @param ofertas
     *            the new ofertas
     */
    public void setOfertas(List<OfertaComercialView> ofertas) {
        this.ofertas = ofertas;
    }

    /**
     * Gets the mostrar encuesta.
     *
     * @return the mostrar encuesta
     */
    public Boolean getMostrarEncuesta() {
        return mostrarEncuesta;
    }

    /**
     * Sets the mostrar encuesta.
     *
     * @param mostrarEncuesta
     *            the new mostrar encuesta.
     */
    public void setMostrarEncuesta(Boolean mostrarEncuesta) {
        this.mostrarEncuesta = mostrarEncuesta;
    }

    public Boolean getMostrarAceptacionDuo() {
        return mostrarAceptacionDuo;
    }

    public void setMostrarAceptacionDuo(Boolean mostrarAceptacionDuo) {
        this.mostrarAceptacionDuo = mostrarAceptacionDuo;
    }
    
    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(tiempoAutodesplazamiento);
        hcb.append(mostrarEncuesta);
        hcb.append(mostrarAceptacionDuo);
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
        OfertasComercialesView other = (OfertasComercialesView) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(tiempoAutodesplazamiento, other.getTiempoAutodesplazamiento());
        eb.append(mostrarEncuesta, other.getMostrarEncuesta());
        eb.append(mostrarAceptacionDuo, other.getMostrarAceptacionDuo());
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
        return new ToStringBuilder(this).append("tiempoAutodesplazamiento", tiempoAutodesplazamiento)
                .append("ofertas", ofertas)
                .append("mostrarEncuesta").toString();
    }
    
}
