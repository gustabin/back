/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GotoLink;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.OfertaComercialEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.OfertasComercialesEntity;

/**
 * The Class OfertasComercialesEntityMock.
 *
 * @author florencia.n.martinez
 */
public class OfertasComercialesEntityMock {

    /**
     * Completar info.
     *
     * @param puntosSuperclub
     *            the puntos superclub
     * @return the ofertas comerciales entity
     */
    public static OfertasComercialesEntity completarInfo(String puntosSuperclub) {
        OfertasComercialesEntity entity = new OfertasComercialesEntity();
        entity.setPuntosSuperclub(puntosSuperclub);
        List<OfertaComercialEntity> ofertas = new ArrayList<OfertaComercialEntity>();
        ofertas.add(completarInfoOferta2());
        ofertas.add(completarInfoOferta());
        entity.setOfertas(ofertas);
        return entity;
    }

    public static OfertaComercialEntity completarInfoOferta() {
        OfertaComercialEntity ofertaComercialEntity = new OfertaComercialEntity();
        ofertaComercialEntity.setOfertaDefault("D");
        ofertaComercialEntity.setIdOfertaRtd("DEFAULT_PROD_GEC");
        ofertaComercialEntity.setIdOfertaInterno("DEFAULT_PROD_GEC");
        ofertaComercialEntity.setTipoOferta("PRODUCTO");
        ofertaComercialEntity.setCategoriaOferta("PRODUCTO_DEFAULT");
        ofertaComercialEntity.setUrl("http://waswcdesa.ar.bsch:8080/images/encabezado.jpg");
        ofertaComercialEntity.setGrupoControl("F");
        ofertaComercialEntity.setTitulo("Bienvenido ");
        ofertaComercialEntity.setOrdenPrioridad("1");
        ofertaComercialEntity.setUbicacionCarrusel("S");
        ofertaComercialEntity.setOrigen("GEC");
        return ofertaComercialEntity;
    }

    public static OfertaComercialEntity completarInfoOferta2() {
        OfertaComercialEntity ofertaComercialEntity = new OfertaComercialEntity();
        ofertaComercialEntity.setOfertaDefault("N");
        ofertaComercialEntity.setIdOfertaRtd("Vida2$SEGURO_DE_VIDA");
        ofertaComercialEntity.setIdOfertaInterno("SEGURO_DE_VIDA           ");
        ofertaComercialEntity.setTipoOferta("PRODUCTO");
        ofertaComercialEntity.setCategoriaOferta("VIDA");
        ofertaComercialEntity.setUrl("http://waswcdesa.ar.bsch:8080/images/Seguro01_desk.jpg");
        ofertaComercialEntity.setLink("gotoCalendarioDePagos();                   ");
        ofertaComercialEntity.setGrupoControl("F");
        ofertaComercialEntity.setPuntosOfertaSc("0");
        ofertaComercialEntity.setPrecioOfertaSc("0");
        ofertaComercialEntity.setTitulo("Seguro de accidentes personales");
        ofertaComercialEntity.setDescripcion(
                "Contratá ahora el seguro de accidentes personales y asegurá el bienestar de tus seres queridos.");
        ofertaComercialEntity.setDescripcionSeccion("");
        ofertaComercialEntity.setSeccionOferta("");
        ofertaComercialEntity.setIndicadorClicSeccion("S");
        ofertaComercialEntity.setOrdenPrioridad("2");
        ofertaComercialEntity.setUbicacionCarrusel("S");
        ofertaComercialEntity.setUbicacionSeccion("");
        ofertaComercialEntity.setOrigen("RTD");
        ofertaComercialEntity.setTipoOfertaSc("N/D");
        ofertaComercialEntity.setTipoProductoLoyalty("0");
        ofertaComercialEntity.setIdLoyalty("N/D");
        return ofertaComercialEntity;
    }

    public static EventosComercialesDTO eventosComercialesDTO() {
        EventosComercialesDTO eventosComercialesDTO = new EventosComercialesDTO();
        eventosComercialesDTO.setJsessionId(
                "JSESSIONID=Es1JeUU_oFtfuevoPMSni5sHVtPZ3o2AB7yuuAjzywz_C8kh8oNA!1286260570; path=/; HttpOnly");
        eventosComercialesDTO.getOfertas().add(ofertaComercialDTO());
        return eventosComercialesDTO;
    }

    public static OfertaComercialDTO ofertaComercialDTO() {
        OfertaComercialDTO ofertaComercialDTO = new OfertaComercialDTO();
        ofertaComercialDTO.setImagenUrl("http://waswcdesa.ar.bsch:8080/images/Seguro01_desk.jpg");
        ofertaComercialDTO.setTitulo("Seguro de accidentes personales");
        ofertaComercialDTO.setDescripcion("Contratá ahora el seguro de accidentes personales y asegurá el bienestar de tus seres queridos.");
        ofertaComercialDTO.setGotoLink(new GotoLink("gotoCalendarioDePagos()"));
        ofertaComercialDTO.setIdRtd("Vida2$SEGURO_DE_VIDA");
        ofertaComercialDTO.setIdInterno("SEGURO_DE_VIDA           ");
        ofertaComercialDTO.setTipoOferta("PRODUCTO");
        ofertaComercialDTO.setCategoriaOferta("VIDA");
        ofertaComercialDTO.setGrupoControl("F");
        ofertaComercialDTO.setUbicacionCarrusel("S");
        ofertaComercialDTO.setUbicacionSeccion("CUENTAS");
        ofertaComercialDTO.setIndicadorClicSeccion("S");
        GotoLink gotoLink = new GotoLink("gotoLink()");
        gotoLink.setParametros("www.santanderrio.com.ar;E");
        ofertaComercialDTO.setGotoLink(gotoLink);
        ofertaComercialDTO.setTitulo("Seguro de accidentes personales");
        ofertaComercialDTO.setDescripcion(null);

        return ofertaComercialDTO;
    }

}
