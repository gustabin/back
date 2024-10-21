package ar.com.santanderrio.obp.servicios.echeq.view;

public class ConfirmarOperacionECheqOutView {

	/** The mensaje feedback. */
	private String mensajeFeedback;
	
	
	public ConfirmarOperacionECheqOutView() {
		super();
	}

	public ConfirmarOperacionECheqOutView(String mensajeFeedback) {
		super();
		this.mensajeFeedback = mensajeFeedback;
	}

	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}
	
	
	
}
