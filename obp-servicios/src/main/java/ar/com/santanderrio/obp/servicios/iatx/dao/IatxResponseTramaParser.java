/*
 * 
 */
package ar.com.santanderrio.obp.servicios.iatx.dao;

/**
 * The Class IatxResponseTramaParser.
 */
public class IatxResponseTramaParser {

	/** The iatx response. */
	private IatxResponse iatxResponse;

	/**
	 * Instantiates a new iatx response trama parser.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 */
	public IatxResponseTramaParser(IatxResponse iatxResponse) {
		this.iatxResponse = iatxResponse;
	}

	/**
	 * Gets the trama.
	 *
	 * @return the trama
	 */
	protected String getTrama() {
		return iatxResponse.getTrama();
	}

}
