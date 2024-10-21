/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monitoreo.sei;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.monitoreo.manager.MonitoreoManager;

/**
 * The Class MonitoreoSEIImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("monitoreoSEI")
public class MonitoreoSEIImpl implements MonitoreoSEI {
    
    /** The monitoreo manager. */
    @Autowired
    private MonitoreoManager monitoreoManager;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.monitoreo.sei.MonitoreoSEI#monitor()
     */
    @Override
    public Response monitor() {
        return Response.status(Response.Status.OK).entity(monitoreoManager.obtenerHTML())
                .header("Content-Type", MediaType.TEXT_HTML).build();
    }

}
