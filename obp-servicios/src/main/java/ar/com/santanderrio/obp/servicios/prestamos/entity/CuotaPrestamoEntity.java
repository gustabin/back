/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import javax.validation.constraints.Pattern;

import org.beanio.annotation.Field;

/**
 * The Class CuotaPrestamoEntity.
 */
public class CuotaPrestamoEntity {

	/** The feliq rec. */
	@Field()
	private String feliqRec;

	/** The numrec rec. */
	@Field()
	private String numrecRec;

	/** The tasa rec. */
	@Field()
	private String tasaRec;

	/** The cap rec. */
	@Field()
	private String capRec;

	/** The int ord rec. */
	@Field()
	private String intOrdRec;

	/** The comi rec. */
	@Field()
	private String comiRec;

	/** The gas rec. */
	@Field()
	private String gasRec;

	/** The seg rec. */
	@Field()
	private String segRec;

	/** The impt rec. */
	@Field()
	private String imptRec;

	/** The comcobex rec. */
	@Field()
	private String comcobexRec;

	/** The imp mor rec. */
	@Field()
	private String impMorRec;

	/** The imp cps rec. */
	@Field()
	private String impCpsRec;

	/** The imp tot rec. */
	@Field()
	private String impTotRec;

	/** The saldo prev. */
	@Field()
	private String saldoPrev;

	/** The factor index. */
	@Field()
	private String factorIndex;

	/** The imp ajussal. */
	@Field()
	private String impAjussal;

	/** The imp ajuscap. */
	@Field()
	private String impAjuscap;

	/** The imp ajus capmor. */
	@Field()
	private String impAjusCapmor;

	/** The factor capmor. */
	@Field()
	private String factorCapmor;

	/** The coefici index. */
	@Field()
	private String coeficiIndex;

	/** The salteor. */
	@Field()
	private String salteor;

	/** The tae. */
	@Field()
	@Pattern(regexp = "^[0-9]{9}$")
	private String tae;

	/** The cftna. */
	@Field()
	private String cftna;

	/** The cftnasi. */
	@Field()
	private String cftnasi;

	/**
	 * Gets the feliq rec.
	 *
	 * @return the feliq rec
	 */
	public String getFeliqRec() {
		return feliqRec;
	}

	/**
	 * Sets the feliq rec.
	 *
	 * @param feliqRec
	 *            the new feliq rec
	 */
	public void setFeliqRec(String feliqRec) {
		this.feliqRec = feliqRec;
	}

	/**
	 * Gets the numrec rec.
	 *
	 * @return the numrec rec
	 */
	public String getNumrecRec() {
		return numrecRec;
	}

	/**
	 * Sets the numrec rec.
	 *
	 * @param numrecRec
	 *            the new numrec rec
	 */
	public void setNumrecRec(String numrecRec) {
		this.numrecRec = numrecRec;
	}

	/**
	 * Gets the tasa rec.
	 *
	 * @return the tasa rec
	 */
	public String getTasaRec() {
		return tasaRec;
	}

	/**
	 * Sets the tasa rec.
	 *
	 * @param tasaRec
	 *            the new tasa rec
	 */
	public void setTasaRec(String tasaRec) {
		this.tasaRec = tasaRec;
	}

	/**
	 * Gets the cap rec.
	 *
	 * @return the cap rec
	 */
	public String getCapRec() {
		return capRec;
	}

	/**
	 * Sets the cap rec.
	 *
	 * @param capRec
	 *            the new cap rec
	 */
	public void setCapRec(String capRec) {
		this.capRec = capRec;
	}

	/**
	 * Gets the int ord rec.
	 *
	 * @return the int ord rec
	 */
	public String getIntOrdRec() {
		return intOrdRec;
	}

	/**
	 * Sets the int ord rec.
	 *
	 * @param intOrdRec
	 *            the new int ord rec
	 */
	public void setIntOrdRec(String intOrdRec) {
		this.intOrdRec = intOrdRec;
	}

	/**
	 * Gets the comi rec.
	 *
	 * @return the comi rec
	 */
	public String getComiRec() {
		return comiRec;
	}

	/**
	 * Sets the comi rec.
	 *
	 * @param comiRec
	 *            the new comi rec
	 */
	public void setComiRec(String comiRec) {
		this.comiRec = comiRec;
	}

	/**
	 * Gets the gas rec.
	 *
	 * @return the gas rec
	 */
	public String getGasRec() {
		return gasRec;
	}

	/**
	 * Sets the gas rec.
	 *
	 * @param gasRec
	 *            the new gas rec
	 */
	public void setGasRec(String gasRec) {
		this.gasRec = gasRec;
	}

	/**
	 * Gets the seg rec.
	 *
	 * @return the seg rec
	 */
	public String getSegRec() {
		return segRec;
	}

	/**
	 * Sets the seg rec.
	 *
	 * @param segRec
	 *            the new seg rec
	 */
	public void setSegRec(String segRec) {
		this.segRec = segRec;
	}

	/**
	 * Gets the impt rec.
	 *
	 * @return the impt rec
	 */
	public String getImptRec() {
		return imptRec;
	}

	/**
	 * Sets the impt rec.
	 *
	 * @param imptRec
	 *            the new impt rec
	 */
	public void setImptRec(String imptRec) {
		this.imptRec = imptRec;
	}

	/**
	 * Gets the comcobex rec.
	 *
	 * @return the comcobex rec
	 */
	public String getComcobexRec() {
		return comcobexRec;
	}

	/**
	 * Sets the comcobex rec.
	 *
	 * @param comcobexRec
	 *            the new comcobex rec
	 */
	public void setComcobexRec(String comcobexRec) {
		this.comcobexRec = comcobexRec;
	}

	/**
	 * Gets the imp mor rec.
	 *
	 * @return the imp mor rec
	 */
	public String getImpMorRec() {
		return impMorRec;
	}

	/**
	 * Sets the imp mor rec.
	 *
	 * @param impMorRec
	 *            the new imp mor rec
	 */
	public void setImpMorRec(String impMorRec) {
		this.impMorRec = impMorRec;
	}

	/**
	 * Gets the imp cps rec.
	 *
	 * @return the imp cps rec
	 */
	public String getImpCpsRec() {
		return impCpsRec;
	}

	/**
	 * Sets the imp cps rec.
	 *
	 * @param impCpsRec
	 *            the new imp cps rec
	 */
	public void setImpCpsRec(String impCpsRec) {
		this.impCpsRec = impCpsRec;
	}

	/**
	 * Gets the imp tot rec.
	 *
	 * @return the imp tot rec
	 */
	public String getImpTotRec() {
		return impTotRec;
	}

	/**
	 * Sets the imp tot rec.
	 *
	 * @param impTotRec
	 *            the new imp tot rec
	 */
	public void setImpTotRec(String impTotRec) {
		this.impTotRec = impTotRec;
	}

	/**
	 * Gets the saldo prev.
	 *
	 * @return the saldo prev
	 */
	public String getSaldoPrev() {
		return saldoPrev;
	}

	/**
	 * Sets the saldo prev.
	 *
	 * @param saldoPrev
	 *            the new saldo prev
	 */
	public void setSaldoPrev(String saldoPrev) {
		this.saldoPrev = saldoPrev;
	}

	/**
	 * Gets the factor index.
	 *
	 * @return the factor index
	 */
	public String getFactorIndex() {
		return factorIndex;
	}

	/**
	 * Sets the factor index.
	 *
	 * @param factorIndex
	 *            the new factor index
	 */
	public void setFactorIndex(String factorIndex) {
		this.factorIndex = factorIndex;
	}

	/**
	 * Gets the imp ajussal.
	 *
	 * @return the imp ajussal
	 */
	public String getImpAjussal() {
		return impAjussal;
	}

	/**
	 * Sets the imp ajussal.
	 *
	 * @param impAjussal
	 *            the new imp ajussal
	 */
	public void setImpAjussal(String impAjussal) {
		this.impAjussal = impAjussal;
	}

	/**
	 * Gets the imp ajuscap.
	 *
	 * @return the imp ajuscap
	 */
	public String getImpAjuscap() {
		return impAjuscap;
	}

	/**
	 * Sets the imp ajuscap.
	 *
	 * @param impAjuscap
	 *            the new imp ajuscap
	 */
	public void setImpAjuscap(String impAjuscap) {
		this.impAjuscap = impAjuscap;
	}

	/**
	 * Gets the imp ajus capmor.
	 *
	 * @return the imp ajus capmor
	 */
	public String getImpAjusCapmor() {
		return impAjusCapmor;
	}

	/**
	 * Sets the imp ajus capmor.
	 *
	 * @param impAjusCapmor
	 *            the new imp ajus capmor
	 */
	public void setImpAjusCapmor(String impAjusCapmor) {
		this.impAjusCapmor = impAjusCapmor;
	}

	/**
	 * Gets the factor capmor.
	 *
	 * @return the factor capmor
	 */
	public String getFactorCapmor() {
		return factorCapmor;
	}

	/**
	 * Sets the factor capmor.
	 *
	 * @param factorCapmor
	 *            the new factor capmor
	 */
	public void setFactorCapmor(String factorCapmor) {
		this.factorCapmor = factorCapmor;
	}

	/**
	 * Gets the coefici index.
	 *
	 * @return the coefici index
	 */
	public String getCoeficiIndex() {
		return coeficiIndex;
	}

	/**
	 * Sets the coefici index.
	 *
	 * @param coeficiIndex
	 *            the new coefici index
	 */
	public void setCoeficiIndex(String coeficiIndex) {
		this.coeficiIndex = coeficiIndex;
	}

	/**
	 * Gets the salteor.
	 *
	 * @return the salteor
	 */
	public String getSalteor() {
		return salteor;
	}

	/**
	 * Sets the salteor.
	 *
	 * @param salteor
	 *            the new salteor
	 */
	public void setSalteor(String salteor) {
		this.salteor = salteor;
	}

	/**
	 * Gets the tae.
	 *
	 * @return the tae
	 */
	public String getTae() {
		return tae;
	}

	/**
	 * Sets the tae.
	 *
	 * @param tae
	 *            the new tae
	 */
	public void setTae(String tae) {
		this.tae = tae;
	}

	/**
	 * Gets the cftna.
	 *
	 * @return the cftna
	 */
	public String getCftna() {
		return cftna;
	}

	/**
	 * Sets the cftna.
	 *
	 * @param cftna
	 *            the new cftna
	 */
	public void setCftna(String cftna) {
		this.cftna = cftna;
	}

	/**
	 * Gets the cftnasi.
	 *
	 * @return the cftnasi
	 */
	public String getCftnasi() {
		return cftnasi;
	}

	/**
	 * Sets the cftnasi.
	 *
	 * @param cftnasi
	 *            the new cftnasi
	 */
	public void setCftnasi(String cftnasi) {
		this.cftnasi = cftnasi;
	}

}
