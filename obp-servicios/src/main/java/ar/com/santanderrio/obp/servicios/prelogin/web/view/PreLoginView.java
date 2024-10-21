package ar.com.santanderrio.obp.servicios.prelogin.web.view;

/**
 * The Class PreLoginView.
 */
public class PreLoginView {

	/** The w id. */
	private String wId;

	/** The w T. */
	private String wT;

	/** The n. */
	private String n;

	/**
	 * Instantiates a new pre login view.
	 *
	 * @param wId the w id
	 * @param wT the w T
	 * @param n the n
	 */
	public PreLoginView(String wId, String wT, String n) {
		super();
		this.wId = wId;
		this.wT = wT;
		this.n = n;
	}

	/**
	 * Instantiates a new pre login view.
	 */
	public PreLoginView() {
		super();
	}

	/**
	 * Gets the w id.
	 *
	 * @return the w id
	 */
	public String getwId() {
		return wId;
	}

	/**
	 * Sets the w id.
	 *
	 * @param id the new w id
	 */
	public void setwId(String id) {
		this.wId = id;
	}

	/**
	 * Gets the w T.
	 *
	 * @return the w T
	 */
	public String getwT() {
		return wT;
	}

	/**
	 * Sets the w T.
	 *
	 * @param waitMessage the new w T
	 */
	public void setwT(String waitMessage) {
		this.wT = waitMessage;
	}

	/**
	 * Gets the n.
	 *
	 * @return the n
	 */
	public String getN() {
		return n;
	}

	/**
	 * Sets the n.
	 *
	 * @param n the new n
	 */
	public void setN(String n) {
		this.n = n;
	}

}
