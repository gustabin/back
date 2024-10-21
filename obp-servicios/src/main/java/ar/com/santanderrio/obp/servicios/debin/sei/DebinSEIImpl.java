/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debin.sei;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.debin.entities.DebinView;
import ar.com.santanderrio.obp.servicios.debin.manager.DebinTokenManager;

/**
 * The Class DebinSEIImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("debinSEI")
public class DebinSEIImpl implements DebinSEI {
    
    /** The debin token manager. */
    @Autowired
    private DebinTokenManager debinTokenManager;

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.debin.sei.DebinSEI#saltoDebin(ar.com.
     * santanderrio.obp.servicios.debin.entities.DebinView,
     * org.apache.cxf.jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<DebinView> saltoDebin(DebinView debinView, MessageContext mc) {
        return debinTokenManager.obtenerTokenEncriptado(debinView, mc);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.debin.sei.DebinSEI#habilitado()
     */
    @Override
    public Respuesta<Boolean> habilitado() {
        return debinTokenManager.habilitado();
    }

}
