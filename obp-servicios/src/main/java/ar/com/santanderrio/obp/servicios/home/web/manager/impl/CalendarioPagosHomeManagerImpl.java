/**
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.manager.CalendarioPagosHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeCalendarioPagosView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;

/**
 * The Class CalendarioPagosHomeManagerImpl.
 *
 * @author B039543
 */
@Component
public class CalendarioPagosHomeManagerImpl extends AbstractHomeManager implements CalendarioPagosHomeManager {

    /** The Constant CALENDARIO_DE_PAGOS_TITULO. */
    private static final String CALENDARIO_DE_PAGOS_TITULO = "Pago de Servicios e Impuestos";

    /** The Constant ENCABEZADO. */
    private static final String ENCABEZADO = "Pagos y d\u00E9bitos autom\u00E1ticos";
    
    /** The Constant TEXTO_LINK_CAJA_CALENDARIO. */
    private static final String TEXTO_LINK_CAJA_CALENDARIO= "Consultar y hacer pagos";

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.impl.
     * AbstractHomeManager#obtenerCajas()
     */
    @Override
    protected GrupoCajaHomeView obtenerCajas() {
        List<Caja> cajas = new ArrayList<Caja>();
        Caja caja = getCaja();
        cajas.add(caja);
        GrupoCajaHomeView grupoCajaHomeView = new GrupoCajaHomeView();
        grupoCajaHomeView.setCajas(cajas);
        grupoCajaHomeView.setSinError(Boolean.TRUE);
        return grupoCajaHomeView;
    }

    /**
     * Obtener la caja de calendario de pagos.
     *
     * @return the caja
     */
    private Caja getCaja() {
        CajaHomeCalendarioPagosView caja = new CajaHomeCalendarioPagosView();
        caja.setTitulo(CALENDARIO_DE_PAGOS_TITULO);
        caja.setEncabezado(ENCABEZADO);
        caja.setIsCalendarioPagos(Boolean.TRUE);
        caja.setTextoLink(TEXTO_LINK_CAJA_CALENDARIO);
        return caja;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager#
     * aplicaGrupo()
     */
    @Override
    public Respuesta<Boolean> aplicaGrupo() {
        return respuestaFactory.crearRespuestaOk(Boolean.class, true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager#
     * obtenerAccion()
     */
    @Override
    public AccionController obtenerAccion() {
        return AccionController.IR_HOME_CALENDARIO;
    }

}
