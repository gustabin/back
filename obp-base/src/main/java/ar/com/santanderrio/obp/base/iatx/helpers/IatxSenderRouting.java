package ar.com.santanderrio.obp.base.iatx.helpers;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class IatxSenderRouting.
 */
public class IatxSenderRouting implements IatxSender {

    /** The servicios en demo. */
    @Value("#{'${SERVICIOS.CICS.EN.DEMO}'.split(',')}") 
    private List<String> serviciosEnDemo;
    
    /** The iatx sender CICS impl. */
    private IatxSender iatxSenderCICSImpl;
    
    /** The iatx sender mock impl. */
    private IatxSender iatxSenderMockImpl;
    
    /**
     * Instantiates a new iatx sender routing.
     *
     * @param iatxSenderCICSImpl the iatx sender CICS impl
     * @param iatxSenderMockImpl the iatx sender mock impl
     */
    public IatxSenderRouting(IatxSender iatxSenderCICSImpl, IatxSender iatxSenderMockImpl) {
        this.iatxSenderCICSImpl = iatxSenderCICSImpl;
        this.iatxSenderMockImpl = iatxSenderMockImpl;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.base.iatx.helpers.IatxSender#send(ar.com.santanderrio.obp.base.iatx.entities.IatxRequest)
     */
    @Override
    public String send(IatxRequest iatxRequest) throws IatxException {
        String nombreServicio = iatxRequest.getNombreServicio();
        
        if (this.serviciosEnDemo.contains(nombreServicio)) {
            return this.iatxSenderMockImpl.send(iatxRequest);
        }
        
        return this.iatxSenderCICSImpl.send(iatxRequest);
        
    }

}
