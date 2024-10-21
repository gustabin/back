/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.mock;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GotoLink;

/**
 * The Class OfertaComercialDTOMock.
 *
 * @author florencia.n.martinez
 */
public class OfertaComercialDTOMock {

    /**
     * Completar info carrusel.
     *
     * @return the oferta comercial DTO
     */
    public static OfertaComercialDTO completarInfoCarrusel() {
        OfertaComercialDTO ofertaDTO = new OfertaComercialDTO();
        ofertaDTO.setCategoriaOferta("CABLE");
        ofertaDTO.setDescripcion("Pagá el Cable");
        ofertaDTO.setGrupoControl("F");
        ofertaDTO.setIdInterno("PAGOS_CABLE");
        ofertaDTO.setIdRtd("CABLE");
        ofertaDTO.setImagenUrl("http://webimages.santanderrio.com.ar/cable.png");
        ofertaDTO.setGotoLink(new GotoLink("gotoCalendarioDePagos()"));
        ofertaDTO.setTipoOferta("PRODUCTO");
        ofertaDTO.setTitulo("");
        ofertaDTO.setUbicacionCarrusel("S");
        ofertaDTO.setUbicacionSeccion("PAGOS");
        return ofertaDTO;
    }

    public static OfertaComercialDTO completarPrestamosCarrusel() {
        OfertaComercialDTO ofertaDTO = new OfertaComercialDTO();
        ofertaDTO.setCategoriaOferta("VIVIENDA");
        ofertaDTO.setDescripcion("Esta es una prueba para ver como se ve el valor 51.416");
        ofertaDTO.setGrupoControl("F");
        ofertaDTO.setIdInterno("PRUEBA OF NUEVA 2");
        ofertaDTO.setIdRtd("VIVIENDA$MOBP_HH2");
        ofertaDTO.setImagenUrl("http://webimages.santanderrio.com.ar/encabezado.jpg");
        ofertaDTO.setGotoLink(new GotoLink("gotoSeguro('ip')"));
        ofertaDTO.setTipoOferta("PRODUCTO");
        ofertaDTO.setTitulo("TITULO");
        ofertaDTO.setUbicacionCarrusel("S");
        ofertaDTO.setUbicacionSeccion("TARJETAS");
        return ofertaDTO;
    }

}
