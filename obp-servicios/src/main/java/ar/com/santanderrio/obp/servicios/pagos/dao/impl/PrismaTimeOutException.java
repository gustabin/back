/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Class PrismaTimeOutException.
 */
public class PrismaTimeOutException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	
	/**
	 * Instantiates a new prisma time out exception.
	 */
	public PrismaTimeOutException(){
		super();
	}
	
	/**
	 * Instantiates a new prisma time out exception.
	 *
	 * @param cause the cause
	 */
	public PrismaTimeOutException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new prisma time out exception.
	 *
	 * @param msg the msg
	 */
	public PrismaTimeOutException(String msg) {
		super(msg);
	}


}

