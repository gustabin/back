package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class OfertasFinanciacionOutView.
 */
public class OfertasFinanciacionOutView {

	/** The ofertas. */
	private List<OfertaFinanciacionView> ofertas = new ArrayList<OfertaFinanciacionView>();

	/** The tiene cuenta. */
	private boolean tieneCuenta;

	/** The default mail. */
	private String defaultMail;

	/** The legal. */
	private String legal;

	/** The mas info. */
	private String masInfo;

	private String ayuda;

	private String tyc;

	private String bullets;

	private String titulo;

	private RefinanciacionType type;

	private MensajeConfirmacion msjConfirmacion;

	/**
	 * Gets the ofertas.
	 *
	 * @return the ofertas
	 */
	public List<OfertaFinanciacionView> getOfertas() {
		return ofertas;
	}

	/**
	 * Sets the ofertas.
	 *
	 * @param ofertas the new ofertas
	 */
	public void setOfertas(List<OfertaFinanciacionView> ofertas) {
		this.ofertas = ofertas;
	}

	/**
	 * Checks if is tiene cuenta.
	 *
	 * @return true, if is tiene cuenta
	 */
	public boolean isTieneCuenta() {
		return tieneCuenta;
	}

	/**
	 * Sets the tiene cuenta.
	 *
	 * @param tieneCuenta the new tiene cuenta
	 */
	public void setTieneCuenta(boolean tieneCuenta) {
		this.tieneCuenta = tieneCuenta;
	}

	/**
	 * Gets the default mail.
	 *
	 * @return the default mail
	 */
	public String getDefaultMail() {
		return defaultMail;
	}

	/**
	 * Sets the default mail.
	 *
	 * @param defaultMail the new default mail
	 */
	public void setDefaultMail(String defaultMail) {
		this.defaultMail = defaultMail;
	}

	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal the new legal
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

	/**
	 * Gets the mas info.
	 *
	 * @return the mas info
	 */
	public String getMasInfo() {
		return masInfo;
	}

	/**
	 * Sets the mas info.
	 *
	 * @param masInfo the new mas info
	 */
	public void setMasInfo(String masInfo) {
		this.masInfo = masInfo;
	}


	/**
	 * Gets the ayuda.
	 *
	 * @return the ayuda
	 */
	public String getAyuda() {
		return ayuda;
	}

	/**
	 * Sets the ayuda.
	 *
	 * @param ayuda the new ayuda
	 */
	public void setAyuda(String ayuda) {
		this.ayuda = ayuda;
	}

	public String getTyc() {
		return tyc;
	}

	public void setTyc(String tyc) {
		this.tyc = tyc;
	}

	public String getBullets() {
		return bullets;
	}

	public void setBullets(String bullets) {
		this.bullets = bullets;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public RefinanciacionType getType() {
		return type;
	}

	public void setType(RefinanciacionType type) {
		this.type = type;
	}

	public MensajeConfirmacion getMsjConfirmacion() {
		return msjConfirmacion;
	}
	public void setMsjConfirmacion(MensajeConfirmacion msjConfirmacion) {
		this.msjConfirmacion = msjConfirmacion;
	}
}
