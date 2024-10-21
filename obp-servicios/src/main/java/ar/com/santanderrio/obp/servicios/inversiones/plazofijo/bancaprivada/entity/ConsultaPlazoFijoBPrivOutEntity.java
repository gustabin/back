/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bancaprivada.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto utilizado para devolver datos del DAO.
 *
 * @author juan.pablo.picate
 */
public class ConsultaPlazoFijoBPrivOutEntity {

	/** the num orden. */
	private String numOrden;

	/** the cod sist. */
	private String codSist;

	/** the tipo ord. */
	private String tipoOrd;

	/** the moneda liq. */
	private String monedaLiq;

	/** the fecha orden. */
	private String fechaOrden;

	/** the fecha liq. */
	private String fechaLiq;

	/** the capital. */
	private String capital;

	/** the hora emision. */
	private String horaEmision;

	/** the tna. */
	private String tna;

	/** the total a cobrar vencimiento. */
	private String totalACobrarVencimiento;

	/** the impuestos. */
	private String impuestos;

	/** the cod certificado. */
	private String codCertificado;

	/** the certificado. */
	private String certificado;

	/** the suc radicacion. */
	private String sucRadicacion;

	/** the codigo accion vencimiento. */
	private String codigoAccionVencimiento;

	/** the accion vencimiento. */
	private String accionVencimiento;

	/** the plazo minimo precancelacion. */
	private String plazoMinimoPrecancelacion;

	/** the porcentaje penalizacion. */
	private String porcentajePenalizacion;

	/** the plazo. */
	private String plazo;

	/** the interes. */
	private String interes;

	/** the interes devengamiento especial. */
	private String interesDevengamientoEspecial;

	/** the cdsecidx. */
	private String cdsecidx;

	/** the nbsecidx. */
	private String nbsecidx;

	/** the fecha operacion. */
	private String fechaOperacion;

	/** the imintbon. */
	private String imintbon;

	/** the pccotori. */
	private String pccotori;

	/** the cdcondic. */
	private String cdcondic;

	/** the cdproduc. */
	private String cdproduc;

	/** the devengado. */
	private String devengado;

	/** the fecha devengado. */
	private String fechaDevengado;

	/** the ajuste. */
	private String ajuste;

	/** the int var deveng diva. */
	private String intVarDevengDiva;

	/** the int men var deveng diva. */
	private String intMenVarDevengDiva;

	/**
	 * Gets the num orden.
	 *
	 * @return the numOrden
	 */
	public String getNumOrden() {
		return numOrden;
	}

	/**
	 * Sets the num orden.
	 *
	 * @param numOrden
	 *            the numOrden to set
	 */
	public void setNumOrden(String numOrden) {
		this.numOrden = numOrden;
	}

	/**
	 * Gets the cod sist.
	 *
	 * @return the codSist
	 */
	public String getCodSist() {
		return codSist;
	}

	/**
	 * Sets the cod sist.
	 *
	 * @param codSist
	 *            the codSist to set
	 */
	public void setCodSist(String codSist) {
		this.codSist = codSist;
	}

	/**
	 * Gets the tipo ord.
	 *
	 * @return the tipoOrd
	 */
	public String getTipoOrd() {
		return tipoOrd;
	}

	/**
	 * Sets the tipo ord.
	 *
	 * @param tipoOrd
	 *            the tipoOrd to set
	 */
	public void setTipoOrd(String tipoOrd) {
		this.tipoOrd = tipoOrd;
	}

	/**
	 * Gets the moneda liq.
	 *
	 * @return the monedaLiq
	 */
	public String getMonedaLiq() {
		return monedaLiq;
	}

	/**
	 * Sets the moneda liq.
	 *
	 * @param monedaLiq
	 *            the monedaLiq to set
	 */
	public void setMonedaLiq(String monedaLiq) {
		this.monedaLiq = monedaLiq;
	}

	/**
	 * Gets the fecha orden.
	 *
	 * @return the fechaOrden
	 */
	public String getFechaOrden() {
		return fechaOrden;
	}

	/**
	 * Sets the fecha orden.
	 *
	 * @param fechaOrden
	 *            the fechaOrden to set
	 */
	public void setFechaOrden(String fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	/**
	 * Gets the fecha liq.
	 *
	 * @return the fechaLiq
	 */
	public String getFechaLiq() {
		return fechaLiq;
	}

	/**
	 * Sets the fecha liq.
	 *
	 * @param fechaLiq
	 *            the fechaLiq to set
	 */
	public void setFechaLiq(String fechaLiq) {
		this.fechaLiq = fechaLiq;
	}

	/**
	 * Gets the capital.
	 *
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}

	/**
	 * Sets the capital.
	 *
	 * @param capital
	 *            the capital to set
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * Gets the hora emision.
	 *
	 * @return the horaEmision
	 */
	public String getHoraEmision() {
		return horaEmision;
	}

	/**
	 * Sets the hora emision.
	 *
	 * @param horaEmision
	 *            the horaEmision to set
	 */
	public void setHoraEmision(String horaEmision) {
		this.horaEmision = horaEmision;
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
	 * Gets the total A cobrar vencimiento.
	 *
	 * @return the totalACobrarVencimiento
	 */
	public String getTotalACobrarVencimiento() {
		return totalACobrarVencimiento;
	}

	/**
	 * Sets the total A cobrar vencimiento.
	 *
	 * @param totalACobrarVencimiento
	 *            the totalACobrarVencimiento to set
	 */
	public void setTotalACobrarVencimiento(String totalACobrarVencimiento) {
		this.totalACobrarVencimiento = totalACobrarVencimiento;
	}

	/**
	 * Gets the impuestos.
	 *
	 * @return the impuestos
	 */
	public String getImpuestos() {
		return impuestos;
	}

	/**
	 * Sets the impuestos.
	 *
	 * @param impuestos
	 *            the impuestos to set
	 */
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * Gets the cod certificado.
	 *
	 * @return the codCertificado
	 */
	public String getCodCertificado() {
		return codCertificado;
	}

	/**
	 * Sets the cod certificado.
	 *
	 * @param codCertificado
	 *            the codCertificado to set
	 */
	public void setCodCertificado(String codCertificado) {
		this.codCertificado = codCertificado;
	}

	/**
	 * Gets the certificado.
	 *
	 * @return the certificado
	 */
	public String getCertificado() {
		return certificado;
	}

	/**
	 * Sets the certificado.
	 *
	 * @param certificado
	 *            the certificado to set
	 */
	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	/**
	 * Gets the suc radicacion.
	 *
	 * @return the sucRadicacion
	 */
	public String getSucRadicacion() {
		return sucRadicacion;
	}

	/**
	 * Sets the suc radicacion.
	 *
	 * @param sucRadicacion
	 *            the sucRadicacion to set
	 */
	public void setSucRadicacion(String sucRadicacion) {
		this.sucRadicacion = sucRadicacion;
	}

	/**
	 * Gets the codigo accion vencimiento.
	 *
	 * @return the codigoAccionVencimiento
	 */
	public String getCodigoAccionVencimiento() {
		return codigoAccionVencimiento;
	}

	/**
	 * Sets the codigo accion vencimiento.
	 *
	 * @param codigoAccionVencimiento
	 *            the codigoAccionVencimiento to set
	 */
	public void setCodigoAccionVencimiento(String codigoAccionVencimiento) {
		this.codigoAccionVencimiento = codigoAccionVencimiento;
	}

	/**
	 * Gets the accion vencimiento.
	 *
	 * @return the accionVencimiento
	 */
	public String getAccionVencimiento() {
		return accionVencimiento;
	}

	/**
	 * Sets the accion vencimiento.
	 *
	 * @param accionVencimiento
	 *            the accionVencimiento to set
	 */
	public void setAccionVencimiento(String accionVencimiento) {
		this.accionVencimiento = accionVencimiento;
	}

	/**
	 * Gets the plazo minimo precancelacion.
	 *
	 * @return the plazoMinimoPrecancelacion
	 */
	public String getPlazoMinimoPrecancelacion() {
		return plazoMinimoPrecancelacion;
	}

	/**
	 * Sets the plazo minimo precancelacion.
	 *
	 * @param plazoMinimoPrecancelacion
	 *            the plazoMinimoPrecancelacion to set
	 */
	public void setPlazoMinimoPrecancelacion(String plazoMinimoPrecancelacion) {
		this.plazoMinimoPrecancelacion = plazoMinimoPrecancelacion;
	}

	/**
	 * Gets the porcentaje penalizacion.
	 *
	 * @return the porcentajePenalizacion
	 */
	public String getPorcentajePenalizacion() {
		return porcentajePenalizacion;
	}

	/**
	 * Sets the porcentaje penalizacion.
	 *
	 * @param porcentajePenalizacion
	 *            the porcentajePenalizacion to set
	 */
	public void setPorcentajePenalizacion(String porcentajePenalizacion) {
		this.porcentajePenalizacion = porcentajePenalizacion;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the interes.
	 *
	 * @return the interes
	 */
	public String getInteres() {
		return interes;
	}

	/**
	 * Sets the interes.
	 *
	 * @param interes
	 *            the interes to set
	 */
	public void setInteres(String interes) {
		this.interes = interes;
	}

	/**
	 * Gets the interes devengamiento especial.
	 *
	 * @return the interesDevengamientoEspecial
	 */
	public String getInteresDevengamientoEspecial() {
		return interesDevengamientoEspecial;
	}

	/**
	 * Sets the interes devengamiento especial.
	 *
	 * @param interesDevengamientoEspecial
	 *            the interesDevengamientoEspecial to set
	 */
	public void setInteresDevengamientoEspecial(String interesDevengamientoEspecial) {
		this.interesDevengamientoEspecial = interesDevengamientoEspecial;
	}

	/**
	 * Gets the cdsecidx.
	 *
	 * @return the cdsecidx
	 */
	public String getCdsecidx() {
		return cdsecidx;
	}

	/**
	 * Sets the cdsecidx.
	 *
	 * @param cdsecidx
	 *            the cdsecidx to set
	 */
	public void setCdsecidx(String cdsecidx) {
		this.cdsecidx = cdsecidx;
	}

	/**
	 * Gets the nbsecidx.
	 *
	 * @return the nbsecidx
	 */
	public String getNbsecidx() {
		return nbsecidx;
	}

	/**
	 * Sets the nbsecidx.
	 *
	 * @param nbsecidx
	 *            the nbsecidx to set
	 */
	public void setNbsecidx(String nbsecidx) {
		this.nbsecidx = nbsecidx;
	}

	/**
	 * Gets the fecha operacion.
	 *
	 * @return the fechaOperacion
	 */
	public String getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * Sets the fecha operacion.
	 *
	 * @param fechaOperacion
	 *            the fechaOperacion to set
	 */
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	/**
	 * Gets the imintbon.
	 *
	 * @return the imintbon
	 */
	public String getImintbon() {
		return imintbon;
	}

	/**
	 * Sets the imintbon.
	 *
	 * @param imintbon
	 *            the imintbon to set
	 */
	public void setImintbon(String imintbon) {
		this.imintbon = imintbon;
	}

	/**
	 * Gets the pccotori.
	 *
	 * @return the pccotori
	 */
	public String getPccotori() {
		return pccotori;
	}

	/**
	 * Sets the pccotori.
	 *
	 * @param pccotori
	 *            the pccotori to set
	 */
	public void setPccotori(String pccotori) {
		this.pccotori = pccotori;
	}

	/**
	 * Gets the cdcondic.
	 *
	 * @return the cdcondic
	 */
	public String getCdcondic() {
		return cdcondic;
	}

	/**
	 * Sets the cdcondic.
	 *
	 * @param cdcondic
	 *            the cdcondic to set
	 */
	public void setCdcondic(String cdcondic) {
		this.cdcondic = cdcondic;
	}

	/**
	 * Gets the cdproduc.
	 *
	 * @return the cdproduc
	 */
	public String getCdproduc() {
		return cdproduc;
	}

	/**
	 * Sets the cdproduc.
	 *
	 * @param cdproduc
	 *            the cdproduc to set
	 */
	public void setCdproduc(String cdproduc) {
		this.cdproduc = cdproduc;
	}

	/**
	 * Gets the devengado.
	 *
	 * @return the devengado
	 */
	public String getDevengado() {
		return devengado;
	}

	/**
	 * Sets the devengado.
	 *
	 * @param devengado
	 *            the devengado to set
	 */
	public void setDevengado(String devengado) {
		this.devengado = devengado;
	}

	/**
	 * Gets the fecha devengado.
	 *
	 * @return the fechaDevengado
	 */
	public String getFechaDevengado() {
		return fechaDevengado;
	}

	/**
	 * Sets the fecha devengado.
	 *
	 * @param fechaDevengado
	 *            the fechaDevengado to set
	 */
	public void setFechaDevengado(String fechaDevengado) {
		this.fechaDevengado = fechaDevengado;
	}

	/**
	 * Gets the ajuste.
	 *
	 * @return the ajuste
	 */
	public String getAjuste() {
		return ajuste;
	}

	/**
	 * Sets the ajuste.
	 *
	 * @param ajuste
	 *            the ajuste to set
	 */
	public void setAjuste(String ajuste) {
		this.ajuste = ajuste;
	}

	/**
	 * Gets the int var deveng diva.
	 *
	 * @return the intVarDevengDiva
	 */
	public String getIntVarDevengDiva() {
		return intVarDevengDiva;
	}

	/**
	 * Sets the int var deveng diva.
	 *
	 * @param intVarDevengDiva
	 *            the intVarDevengDiva to set
	 */
	public void setIntVarDevengDiva(String intVarDevengDiva) {
		this.intVarDevengDiva = intVarDevengDiva;
	}

	/**
	 * Gets the int men var deveng diva.
	 *
	 * @return the intMenVarDevengDiva
	 */
	public String getIntMenVarDevengDiva() {
		return intMenVarDevengDiva;
	}

	/**
	 * Sets the int men var deveng diva.
	 *
	 * @param intMenVarDevengDiva
	 *            the intMenVarDevengDiva to set
	 */
	public void setIntMenVarDevengDiva(String intMenVarDevengDiva) {
		this.intMenVarDevengDiva = intMenVarDevengDiva;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("certificado", certificado).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		ConsultaPlazoFijoBPrivOutEntity other = (ConsultaPlazoFijoBPrivOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder().append(certificado, other.certificado);
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(certificado).toHashCode();
	}

}
