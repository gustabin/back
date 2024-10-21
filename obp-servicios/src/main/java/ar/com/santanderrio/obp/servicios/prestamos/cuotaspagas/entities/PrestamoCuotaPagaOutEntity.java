/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.beanio.annotation.Field;

/**
 * The Class PrestamoCuotaPagaOutEntity.
 *
 * @author florencia.n.martinez
 */
public class PrestamoCuotaPagaOutEntity {

	/** The nio. */
	@Field
	private String nio;

	/** The cod evento. */
	@Field
	private String codEvento;

	/** The descr evento. */
	@Field
	private String descrEvento;

	/** The feoper. */
	@Field
	private String feoper;

	/** The feconta. */
	@Field
	private String feconta;

	/** The fevalor. */
	@Field
	private String fevalor;

	/** The feliq. */
	@Field
	private String feliq;

	/** The indretro. */
	@Field
	private String indretro;

	/** The fecretro. */
	@Field
	private String fecretro;

	/** The ofioper. */
	@Field
	private String ofioper;

	/** The divisa mov. */
	@Field
	private String divisaMov;

	/** The saldoant. */
	@Field
	private String saldoant;

	/** The saldopost. */
	@Field
	private String saldopost;

	/** The tabla. */
	@Field
	private String tabla;

	/** The divisa pago. */
	@Field
	private String divisaPago;

	/** The importe abono. */
	@Field
	private String importeAbono;

	/** The forma pago abono. */
	@Field
	private String formaPagoAbono;

	/** The descr FP abono. */
	@Field
	private String descrFPAbono;

	/** The importe cargo. */
	@Field
	private String importeCargo;

	/** The forma pago cargo. */
	@Field
	private String formaPagoCargo;

	/** The descr FP cargo. */
	@Field
	private String descrFPCargo;

	/** The operac. */
	@Field
	private String operac;

	/** The imp ajussal. */
	@Field
	private String impAjussal;

	/** The factor index. */
	@Field
	private String factorIndex;

	/** The numrec. */
	@Field
	private String numrec;

	/** The capinire. */
	@Field
	private String capinire;

	/** The intinire. */
	@Field
	private String intinire;

	/** The seginire. */
	@Field
	private String seginire;

	/** The impinire. */
	@Field
	private String impinire;

	/** The cominire. */
	@Field
	private String cominire;

	/** The gasinire. */
	@Field
	private String gasinire;

	/** The tna. */
	@Field
	private String tna;

	/** The tae. */
	@Field
	private String tae;

	/** The cftna. */
	@Field
	private String cftna;

	/** The cftnasi. */
	@Field
	private String cftnasi;

	/** The userid. */
	@Field
	private String userid;

	/**
	 * Gets the nio.
	 *
	 * @return the nio
	 */
	public String getNio() {
		return nio;
	}

	/**
	 * Sets the nio.
	 *
	 * @param nio
	 *            the nio to set
	 */
	public void setNio(String nio) {
		this.nio = nio;
	}

	/**
	 * Gets the cod evento.
	 *
	 * @return the codEvento
	 */
	public String getCodEvento() {
		return codEvento;
	}

	/**
	 * Sets the cod evento.
	 *
	 * @param codEvento
	 *            the codEvento to set
	 */
	public void setCodEvento(String codEvento) {
		this.codEvento = codEvento;
	}

	/**
	 * Gets the descr evento.
	 *
	 * @return the descrEvento
	 */
	public String getDescrEvento() {
		return descrEvento;
	}

	/**
	 * Sets the descr evento.
	 *
	 * @param descrEvento
	 *            the descrEvento to set
	 */
	public void setDescrEvento(String descrEvento) {
		this.descrEvento = descrEvento;
	}

	/**
	 * Gets the feoper.
	 *
	 * @return the feoper
	 */
	public String getFeoper() {
		return feoper;
	}

	/**
	 * Sets the feoper.
	 *
	 * @param feoper
	 *            the feoper to set
	 */
	public void setFeoper(String feoper) {
		this.feoper = feoper;
	}

	/**
	 * Gets the feconta.
	 *
	 * @return the feconta
	 */
	public String getFeconta() {
		return feconta;
	}

	/**
	 * Sets the feconta.
	 *
	 * @param feconta
	 *            the feconta to set
	 */
	public void setFeconta(String feconta) {
		this.feconta = feconta;
	}

	/**
	 * Gets the fevalor.
	 *
	 * @return the fevalor
	 */
	public String getFevalor() {
		return fevalor;
	}

	/**
	 * Sets the fevalor.
	 *
	 * @param fevalor
	 *            the fevalor to set
	 */
	public void setFevalor(String fevalor) {
		this.fevalor = fevalor;
	}

	/**
	 * Gets the feliq.
	 *
	 * @return the feliq
	 */
	public String getFeliq() {
		return feliq;
	}

	/**
	 * Sets the feliq.
	 *
	 * @param feliq
	 *            the feliq to set
	 */
	public void setFeliq(String feliq) {
		this.feliq = feliq;
	}

	/**
	 * Gets the indretro.
	 *
	 * @return the indretro
	 */
	public String getIndretro() {
		return indretro;
	}

	/**
	 * Sets the indretro.
	 *
	 * @param indretro
	 *            the indretro to set
	 */
	public void setIndretro(String indretro) {
		this.indretro = indretro;
	}

	/**
	 * Gets the fecretro.
	 *
	 * @return the fecretro
	 */
	public String getFecretro() {
		return fecretro;
	}

	/**
	 * Sets the fecretro.
	 *
	 * @param fecretro
	 *            the fecretro to set
	 */
	public void setFecretro(String fecretro) {
		this.fecretro = fecretro;
	}

	/**
	 * Gets the ofioper.
	 *
	 * @return the ofioper
	 */
	public String getOfioper() {
		return ofioper;
	}

	/**
	 * Sets the ofioper.
	 *
	 * @param ofioper
	 *            the ofioper to set
	 */
	public void setOfioper(String ofioper) {
		this.ofioper = ofioper;
	}

	/**
	 * Gets the divisa mov.
	 *
	 * @return the divisaMov
	 */
	public String getDivisaMov() {
		return divisaMov;
	}

	/**
	 * Sets the divisa mov.
	 *
	 * @param divisaMov
	 *            the divisaMov to set
	 */
	public void setDivisaMov(String divisaMov) {
		this.divisaMov = divisaMov;
	}

	/**
	 * Gets the saldoant.
	 *
	 * @return the saldoant
	 */
	public String getSaldoant() {
		return saldoant;
	}

	/**
	 * Sets the saldoant.
	 *
	 * @param saldoant
	 *            the saldoant to set
	 */
	public void setSaldoant(String saldoant) {
		this.saldoant = saldoant;
	}

	/**
	 * Gets the saldopost.
	 *
	 * @return the saldopost
	 */
	public String getSaldopost() {
		return saldopost;
	}

	/**
	 * Sets the saldopost.
	 *
	 * @param saldopost
	 *            the saldopost to set
	 */
	public void setSaldopost(String saldopost) {
		this.saldopost = saldopost;
	}

	/**
	 * Gets the tabla.
	 *
	 * @return the tabla
	 */
	public String getTabla() {
		return tabla;
	}

	/**
	 * Sets the tabla.
	 *
	 * @param tabla
	 *            the tabla to set
	 */
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	/**
	 * Gets the divisa pago.
	 *
	 * @return the divisaPago
	 */
	public String getDivisaPago() {
		return divisaPago;
	}

	/**
	 * Sets the divisa pago.
	 *
	 * @param divisaPago
	 *            the divisaPago to set
	 */
	public void setDivisaPago(String divisaPago) {
		this.divisaPago = divisaPago;
	}

	/**
	 * Gets the importe abono.
	 *
	 * @return the importeAbono
	 */
	public String getImporteAbono() {
		return importeAbono;
	}

	/**
	 * Sets the importe abono.
	 *
	 * @param importeAbono
	 *            the importeAbono to set
	 */
	public void setImporteAbono(String importeAbono) {
		this.importeAbono = importeAbono;
	}

	/**
	 * Gets the forma pago abono.
	 *
	 * @return the formaPagoAbono
	 */
	public String getFormaPagoAbono() {
		return formaPagoAbono;
	}

	/**
	 * Sets the forma pago abono.
	 *
	 * @param formaPagoAbono
	 *            the formaPagoAbono to set
	 */
	public void setFormaPagoAbono(String formaPagoAbono) {
		this.formaPagoAbono = formaPagoAbono;
	}

	/**
	 * Gets the descr FP abono.
	 *
	 * @return the descrFPAbono
	 */
	public String getDescrFPAbono() {
		return descrFPAbono;
	}

	/**
	 * Sets the descr FP abono.
	 *
	 * @param descrFPAbono
	 *            the descrFPAbono to set
	 */
	public void setDescrFPAbono(String descrFPAbono) {
		this.descrFPAbono = descrFPAbono;
	}

	/**
	 * Gets the importe cargo.
	 *
	 * @return the importeCargo
	 */
	public String getImporteCargo() {
		return importeCargo;
	}

	/**
	 * Sets the importe cargo.
	 *
	 * @param importeCargo
	 *            the importeCargo to set
	 */
	public void setImporteCargo(String importeCargo) {
		this.importeCargo = importeCargo;
	}

	/**
	 * Gets the forma pago cargo.
	 *
	 * @return the formaPagoCargo
	 */
	public String getFormaPagoCargo() {
		return formaPagoCargo;
	}

	/**
	 * Sets the forma pago cargo.
	 *
	 * @param formaPagoCargo
	 *            the formaPagoCargo to set
	 */
	public void setFormaPagoCargo(String formaPagoCargo) {
		this.formaPagoCargo = formaPagoCargo;
	}

	/**
	 * Gets the descr FP cargo.
	 *
	 * @return the descrFPCargo
	 */
	public String getDescrFPCargo() {
		return descrFPCargo;
	}

	/**
	 * Sets the descr FP cargo.
	 *
	 * @param descrFPCargo
	 *            the descrFPCargo to set
	 */
	public void setDescrFPCargo(String descrFPCargo) {
		this.descrFPCargo = descrFPCargo;
	}

	/**
	 * Gets the operac.
	 *
	 * @return the operac
	 */
	public String getOperac() {
		return operac;
	}

	/**
	 * Sets the operac.
	 *
	 * @param operac
	 *            the operac to set
	 */
	public void setOperac(String operac) {
		this.operac = operac;
	}

	/**
	 * Gets the imp ajussal.
	 *
	 * @return the impAjussal
	 */
	public String getImpAjussal() {
		return impAjussal;
	}

	/**
	 * Sets the imp ajussal.
	 *
	 * @param impAjussal
	 *            the impAjussal to set
	 */
	public void setImpAjussal(String impAjussal) {
		this.impAjussal = impAjussal;
	}

	/**
	 * Gets the factor index.
	 *
	 * @return the factorIndex
	 */
	public String getFactorIndex() {
		return factorIndex;
	}

	/**
	 * Sets the factor index.
	 *
	 * @param factorIndex
	 *            the factorIndex to set
	 */
	public void setFactorIndex(String factorIndex) {
		this.factorIndex = factorIndex;
	}

	/**
	 * Gets the numrec.
	 *
	 * @return the numrec
	 */
	public String getNumrec() {
		return numrec;
	}

	/**
	 * Sets the numrec.
	 *
	 * @param numrec
	 *            the numrec to set
	 */
	public void setNumrec(String numrec) {
		this.numrec = numrec;
	}

	/**
	 * Gets the capinire.
	 *
	 * @return the capinire
	 */
	public String getCapinire() {
		return capinire;
	}

	/**
	 * Sets the capinire.
	 *
	 * @param capinire
	 *            the capinire to set
	 */
	public void setCapinire(String capinire) {
		this.capinire = capinire;
	}

	/**
	 * Gets the intinire.
	 *
	 * @return the intinire
	 */
	public String getIntinire() {
		return intinire;
	}

	/**
	 * Sets the intinire.
	 *
	 * @param intinire
	 *            the intinire to set
	 */
	public void setIntinire(String intinire) {
		this.intinire = intinire;
	}

	/**
	 * Gets the seginire.
	 *
	 * @return the seginire
	 */
	public String getSeginire() {
		return seginire;
	}

	/**
	 * Sets the seginire.
	 *
	 * @param seginire
	 *            the seginire to set
	 */
	public void setSeginire(String seginire) {
		this.seginire = seginire;
	}

	/**
	 * Gets the impinire.
	 *
	 * @return the impinire
	 */
	public String getImpinire() {
		return impinire;
	}

	/**
	 * Sets the impinire.
	 *
	 * @param impinire
	 *            the impinire to set
	 */
	public void setImpinire(String impinire) {
		this.impinire = impinire;
	}

	/**
	 * Gets the cominire.
	 *
	 * @return the cominire
	 */
	public String getCominire() {
		return cominire;
	}

	/**
	 * Sets the cominire.
	 *
	 * @param cominire
	 *            the cominire to set
	 */
	public void setCominire(String cominire) {
		this.cominire = cominire;
	}

	/**
	 * Gets the gasinire.
	 *
	 * @return the gasinire
	 */
	public String getGasinire() {
		return gasinire;
	}

	/**
	 * Sets the gasinire.
	 *
	 * @param gasinire
	 *            the gasinire to set
	 */
	public void setGasinire(String gasinire) {
		this.gasinire = gasinire;
	}

	/**
	 * Gets the tna.
	 *
	 * @return the tna
	 */
	public String getTna() {
		return tna;
	}

	/**
	 * Sets the tna.
	 *
	 * @param tna
	 *            the tna to set
	 */
	public void setTna(String tna) {
		this.tna = tna;
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
	 *            the tae to set
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
	 *            the cftna to set
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
	 *            the cftnasi to set
	 */
	public void setCftnasi(String cftnasi) {
		this.cftnasi = cftnasi;
	}

	/**
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * Sets the userid.
	 *
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(capinire);
		hcb.append(cftna);
		hcb.append(cftnasi);
		hcb.append(cominire);
		hcb.append(descrEvento);
		hcb.append(descrFPAbono);
		hcb.append(descrFPCargo);
		hcb.append(divisaMov);
		hcb.append(divisaPago);
		hcb.append(factorIndex);
		hcb.append(feconta);
		hcb.append(fecretro);
		hcb.append(feliq);
		hcb.append(feoper);
		hcb.append(fevalor);
		hcb.append(formaPagoAbono);
		hcb.append(formaPagoCargo);
		hcb.append(gasinire);
		hcb.append(impAjussal);
		hcb.append(impinire);
		hcb.append(importeAbono);
		hcb.append(importeCargo);
		hcb.append(indretro);
		hcb.append(intinire);
		hcb.append(nio);
		hcb.append(numrec);
		hcb.append(ofioper);
		hcb.append(operac);
		hcb.append(saldoant);
		hcb.append(saldopost);
		hcb.append(seginire);
		hcb.append(tabla);
		hcb.append(tae);
		hcb.append(tna);
		hcb.append(userid);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrestamoCuotaPagaOutEntity other = (PrestamoCuotaPagaOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(capinire, other.getCapinire());
		eb.append(cftna, other.getCftna());
		eb.append(cftnasi, other.getCftnasi());
		eb.append(cominire, other.getCominire());
		eb.append(descrEvento, other.getDescrEvento());
		eb.append(descrFPAbono, other.getDescrFPAbono());
		eb.append(descrFPCargo, other.getDescrFPCargo());
		eb.append(divisaMov, other.getDivisaMov());
		eb.append(divisaPago, other.getDivisaPago());
		eb.append(factorIndex, other.getFactorIndex());
		eb.append(feconta, other.getFeconta());
		eb.append(fecretro, other.getFecretro());
		eb.append(feliq, other.getFeliq());
		eb.append(feoper, other.getFeoper());
		eb.append(fevalor, other.getFevalor());
		eb.append(formaPagoAbono, other.getFormaPagoAbono());
		eb.append(formaPagoCargo, other.getFormaPagoCargo());
		eb.append(gasinire, other.getGasinire());
		eb.append(impAjussal, other.getImpAjussal());
		eb.append(impinire, other.getImpinire());
		eb.append(importeAbono, other.getImporteAbono());
		eb.append(importeCargo, other.getImporteCargo());
		eb.append(indretro, other.getIndretro());
		eb.append(intinire, other.getIntinire());
		eb.append(nio, other.getNio());
		eb.append(numrec, other.getNumrec());
		eb.append(ofioper, other.getOfioper());
		eb.append(operac, other.getOperac());
		eb.append(saldoant, other.getSaldoant());
		eb.append(saldopost, other.getSaldopost());
		eb.append(seginire, other.getSeginire());
		eb.append(tabla, other.getTabla());
		eb.append(tae, other.getTae());
		eb.append(tna, other.getTna());
		eb.append(userid, other.getUserid());
		return eb.isEquals();
	}

	/**
	 * TpoString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("nio", nio).append("codEvento", codEvento)
				.append("descrEvento", descrEvento).append("feoper", feoper).append("feconta", feconta)
				.append("fevalor", fevalor).append("feliq", feliq).append("indretro", indretro)
				.append("fecretro", fecretro).append("ofioper", ofioper).append("divisaMov", divisaMov)
				.append("saldoant", saldoant).append("saldopost", saldopost).append("tabla", tabla)
				.append("divisaPago", divisaPago).append("importeAbono", importeAbono)
				.append("formaPagoAbono", formaPagoAbono).append("descrFPAbono", descrFPAbono)
				.append("importeCargo", importeCargo).append("formaPagoCargo", formaPagoCargo)
				.append("descrFPCargo", descrFPCargo).append("operac", operac).append("impAjussal", impAjussal)
				.append("factorIndex", factorIndex).append("numrec", numrec).append("capinire", capinire)
				.append("intinire", intinire).append("seginire", seginire).append("impinire", impinire)
				.append("cominire", cominire).append("gasinire", gasinire).append("tna", tna).append("tae", tae)
				.append("cftna", cftna).append("cftnasi", cftnasi).append("userid", userid).toString();
	}

}
