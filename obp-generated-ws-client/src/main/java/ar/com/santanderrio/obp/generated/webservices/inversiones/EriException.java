package ar.com.santanderrio.obp.generated.webservices.inversiones;

import javax.xml.ws.WebFault;

/**
 * The Class EriException.
 *
 * @author sergio.e.goldentair
 */
@WebFault(name = "MESACServiceFault", targetNamespace = "http://schemas.datacontract.org/2004/07/ISBAN.MESAC.ServiceContracts", faultBean = "ar.com.santanderrio.obp.generated.webservices.inversiones.EriFaultBean")
public class EriException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2338985967020579121L;

    /** Bean que bindea el objeto retornado. */
    private final EriFaultBean eriFaultBean;

    /**
     * Instantiates a new eri exception.
     *
     * @param arg0
     *            the arg 0
     * @param arg1
     *            the arg 1
     * @param eriFaultBean
     *            the eri fault bean
     */
    public EriException(String arg0, Throwable arg1, EriFaultBean eriFaultBean) {
        super(arg0, arg1);
        this.eriFaultBean = eriFaultBean;
    }

    /**
     * Instantiates a new eri exception.
     *
     * @param arg0
     *            the arg 0
     * @param eriFaultBean
     *            the eri fault bean
     */
    public EriException(String arg0, EriFaultBean eriFaultBean) {
        super(arg0);
        this.eriFaultBean = eriFaultBean;
    }

    /**
     * Gets the fault info.
     *
     * @return the eriFaultBean
     */
    public EriFaultBean getFaultInfo() {
        return eriFaultBean;
    }
}
