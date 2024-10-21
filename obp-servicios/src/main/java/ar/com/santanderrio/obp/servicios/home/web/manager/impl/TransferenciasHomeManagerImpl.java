/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.manager.TransferenciasHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeTransferenciasView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;

/**
 * The Class TransferenciasHomeManagerImpl.
 */
@Component
public class TransferenciasHomeManagerImpl extends AbstractHomeManager implements TransferenciasHomeManager {
    
    /** The Constant TRANSFERENCIAS_TITULO. */
    private static final String TRANSFERENCIAS_TITULO = "Transferencias";

    /** The Constant ENCABEZADO. */
    private static final String ENCABEZADO = "TRANSACCIONES";
    
    /** The Constant TEXTO_LINK_CAJA_CALENDARIO. */
    private static final String TEXTO_LINK_CAJA_CALENDARIO= "Ir a transferencias";
    
    /** The cuenta BO. */
    @Autowired
    private CuentaBO cuentaBO;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager#aplicaGrupo()
     */
    @Override
    public Respuesta<Boolean> aplicaGrupo() {
        Cliente cliente = sesionCliente.getCliente();
        Boolean aplicaGrupo = cuentaBO.hasCuentasMonetariasActivas(cliente);
        return respuestaFactory.crearRespuestaOk(Boolean.class, aplicaGrupo);
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.GrupoHomeManager#obtenerAccion()
     */
    @Override
    public AccionController obtenerAccion() {
        return AccionController.IR_HOME_TRANFERENCIAS;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.home.web.manager.impl.AbstractHomeManager#obtenerCajas()
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
	 * Gets the caja.
	 *
	 * @return the caja
	 */
    private Caja getCaja() {
        CajaHomeTransferenciasView caja = new CajaHomeTransferenciasView();
        caja.setTitulo(TRANSFERENCIAS_TITULO);
        caja.setEncabezado(ENCABEZADO);
        caja.setIsTransferencias(Boolean.TRUE);
        caja.setTextoLink(TEXTO_LINK_CAJA_CALENDARIO);
        return caja;
    }

}
