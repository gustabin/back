package ar.com.santanderrio.obp.servicios.home.web.view;

/**
 * The Class MicroFEView.
 */
public class MicroFEView {

    /** The action. */
    private String action;

    /** The path. */
    private String path;

    /**
     * Instantiates a new micro FE view.
     *
     * @param action the action
     * @param path the path
     */
    public MicroFEView(String action, String path) {
        this.action = action;
        this.path = path;
    }

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the action.
	 *
	 * @param action the new action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

}
