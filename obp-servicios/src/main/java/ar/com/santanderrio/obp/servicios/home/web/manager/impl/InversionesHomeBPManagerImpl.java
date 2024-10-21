/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.manager.InversionesHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeInversionesView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.InicioInversionesViewRequest;

/**
 * The Class InversionesHomeBPManagerImpl.
 */
@Component("inversionesHomeBPManagerImpl")
public class InversionesHomeBPManagerImpl extends AbstractHomeManager implements InversionesHomeManager {

    /** The Constant ENCABEZADO_CAJA_INVERSIONES. */
    private static final String ENCABEZADO_CAJA_INVERSIONES_BANCA_PRIVADA = "Tenencia Consolidada";

    /** The Constant ICONO_CAJA_INVERSIONES. */
    private static final String ICONO_CAJA_INVERSIONES = "home_cards_fondos_inversiones";

    /** The Constant TITULO_CAJA_INVERSIONES. */
    private static final String TITULO_CAJA_INVERSIONES = "Inversiones Banca Privada";

    /** The Constant TOTAL_TENENCIAS. */
    private static final String TOTAL_TENENCIAS = "TOD";

    /** The Constant CAJA_PRIVADA. */
    private static final String CAJA_PRIVADA = "BP";
    
    /** The Constant TEXTO_LINK_CAJA_INVERSIONES. */
    private static final String TEXTO_LINK_CAJA_INVERSIONES= "Ver tenencia";

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /**
     * SI EL CLIENTE NO TIENE CUENTAS DE BANCA PRIVADA NO MOSTRAR CAJA {@inheritDoc}
     */
    @Override
    public Respuesta<Boolean> aplicaGrupo() {
           return respuestaFactory.crearRespuestaOk(Boolean.class, Boolean.FALSE);
    }


    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager#
     * obtenerAccion()
     */
    @Override
    public AccionController obtenerAccion() {
        return AccionController.IR_HOME_INVERSIONES_BP;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.home.web.manager.InversionesHomeManager
     * #obtenerTenencias()
     */
    @Override
    public Respuesta<CajaHomeInversionesView> obtenerTenencias() {
        return null;
    }

    /**
     * Tengo una sola caja para inversiones, de momento...
     *
     * @return the list
     */
    @Override
    protected GrupoCajaHomeView obtenerCajas() {
        List<Caja> cajas = new ArrayList<Caja>();
        cajas.add(getCajaBPriv());
        GrupoCajaHomeView grupoCajaHomeView = new GrupoCajaHomeView();
        grupoCajaHomeView.setCajas(cajas);
        grupoCajaHomeView.setSinError(Boolean.TRUE);
        return grupoCajaHomeView;
    }

    /**
     * Gets the caja banca privada.
     *
     * @return the caja
     **/
    private Caja getCajaBPriv() {
        CajaHomeInversionesView caja = new CajaHomeInversionesView();
        caja.setIsInversiones(true);
        caja.setEncabezado(WordUtils.capitalizeFully(TITULO_CAJA_INVERSIONES));
        caja.setIcono(ICONO_CAJA_INVERSIONES);
        caja.setTitulo(ENCABEZADO_CAJA_INVERSIONES_BANCA_PRIVADA);
        caja.setBanca(CAJA_PRIVADA);
        caja.setTextoLink(TEXTO_LINK_CAJA_INVERSIONES);
        InicioInversionesViewRequest request = new InicioInversionesViewRequest();
        request.setTipoDeOperacion(TOTAL_TENENCIAS);
        return caja;
    }

}
