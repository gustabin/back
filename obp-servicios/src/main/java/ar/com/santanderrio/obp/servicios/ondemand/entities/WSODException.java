/*
 * 
 */
package ar.com.santanderrio.obp.servicios.ondemand.entities;

/**
 * Se mantiene la excepcion para no perder consistencia con el codigo heredado.
 * Se da cuando hay un error con el ws de OnDemand.
 * 
 * @author
 *
 */
public class WSODException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new WSOD exception.
	 *
	 * @param e
	 *            the e
	 */
	public WSODException(Throwable e) {
		super(e);
	}

	/**
	 * Instantiates a new WSOD exception.
	 *
	 * @param s
	 *            the s
	 * @param e
	 *            the e
	 */
	public WSODException(String s, Throwable e) {
		super(s, e);
	}

}