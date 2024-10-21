/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.exceptions;


/**
 * The Class DebinCBUInvalidoDAOException.
 */
public class DebinCBUInvalidoDAOException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The error code service. */
    private int errorCode;

    /**
	 * Instantiates a new DebinCBUInvalidoDAOException.
	 *
	 * @param errorCode
	 *            the error code
	 */
    public DebinCBUInvalidoDAOException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    /**
     * Default constructor.
     */
    public DebinCBUInvalidoDAOException() {
    }

    /**
	 * Gets the error code.
	 *
	 * @return the errorCode
	 */
    public int getErrorCode() {
        return errorCode;
    }

    /**
	 * Sets the error code.
	 *
	 * @param errorCode
	 *            the errorCode to set
	 */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


}
