package ar.com.santanderrio.obp.base.exceptions;

// TODO: Auto-generated Javadoc
/**
 * Exception utilizada en las busquedas indices Lucene.
 *
 * @author pablo.martin.gore
 */

public class LuceneSearchException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new lucene search exception.
	 *
	 * @param message
	 *            the message
	 */
	public LuceneSearchException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new lucene search exception.
	 */
	public LuceneSearchException() {
		super();
	}

	/**
	 * Instantiates a new lucene search exception.
	 *
	 * @param e
	 *            the e
	 */
	public LuceneSearchException(Exception e) {
		super(e);
	}

	/**
	 * Instantiates a new lucene search exception.
	 *
	 * @param e
	 *            the e
	 */
	public LuceneSearchException(Throwable e) {
		super(e);
	}

}
