/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prelogin.sei;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.prelogin.manager.PreLoginManger;
import ar.com.santanderrio.obp.servicios.prelogin.web.view.PreLoginView;

/**
 * The Class PreLoginSEIImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("preLoginSEI")
public class PreLoginSEIImpl implements PreLoginSEI {

    /** The pre login manger. */
    @Autowired
    private PreLoginManger preLoginManger;

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.prelogin.PreLoginSEI#init(org.apache.cxf.
     * jaxrs.ext.MessageContext)
     */
    @Override
    public Respuesta<String> init(MessageContext mc) {
        return preLoginManger.obtenerEncPub();
    }

    @Override
    public Respuesta<PreLoginView> preLogin(String datoEntrada) {
        return preLoginManger.preLogin(datoEntrada);
    }

}
