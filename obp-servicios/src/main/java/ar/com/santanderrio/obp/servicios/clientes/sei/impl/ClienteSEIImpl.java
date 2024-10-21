/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.sei.impl;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.sei.ClienteSEI;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClaveUsuarioManager;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioClaveUsuarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioUsuarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.MarcaAPNHResponse;

/**
 * The Class ClienteSEIImpl.
 */
@Component
public class ClienteSEIImpl implements ClienteSEI {

    /** The cliente manager. */
    @Autowired
    private ClienteManager clienteManager;

    /** The clave usuario manager. */
    @Autowired
    private ClaveUsuarioManager claveUsuarioManager;

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.clientes.sei.ClienteSEI#getMarcaAPNH()
     */
    @Override
    public Respuesta<MarcaAPNHResponse> getMarcaAPNH() {
        return clienteManager.getMarcaAPNH();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.monedero.sei.MonederoSEI#
     * getDatosSolicitudTagAdicional()
     */
    @Override
    public Respuesta<CambioUsuarioView> cambioUsuario(MessageContext mc, CambioUsuarioView cambioUsuarioView) {
        return clienteManager.cambioUsuario(mc.getHttpServletRequest(), cambioUsuarioView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clientes.sei.ClienteSEI#
     * cambioClaveUsuario(org.apache.cxf.jaxrs.ext.MessageContext,
     * ar.com.santanderrio.obp.servicios.clientes.web.view.
     * CambioClaveUsuarioView)
     */
    @Override
    public Respuesta<CambioClaveUsuarioView> cambioClaveUsuario(MessageContext mc,
            CambioClaveUsuarioView cambioClaveUsuarioView) {
        return claveUsuarioManager.cambioUsuarioClave(mc.getHttpServletRequest(), cambioClaveUsuarioView);
    }

    @Override
    public Respuesta<Void> getEstadoCensoEconomico() {
        return clienteManager.getEstadoCensoEconomico();
    }
}
