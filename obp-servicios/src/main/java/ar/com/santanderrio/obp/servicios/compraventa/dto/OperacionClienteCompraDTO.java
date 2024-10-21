/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class OperacionClienteCompraDTO.
 *
 * @author sabrina.cis
 */
public class OperacionClienteCompraDTO {

	/** The ccccarg. */
	private String ccccarg;

	/** The divcarg. */
	private String divcarg;

	/** The codcarg. */
	private String codcarg;

	/** The concarg. */
	private String concarg;

	/** The impcarg. */
	private String impcarg;

	/** The cccabon. */
	private String cccabon;

	/** The divabon. */
	private String divabon;

	/** The codabon. */
	private String codabon;

	/** The conabon. */
	private String conabon;

	/** The impabon. */
	private String impabon;

	/** The tipcamb. */
	private String tipcamb;

	/** The impcoti. */
	private String impcoti;

	/** The fechval. */
	private String fechval;

	/** The sdodcar. */
	private String sdodcar;

	/** The sdocarb. */
	private String sdocarb;

	/** The nromcar. */
	private String nromcar;

	/** The sdodabo. */
	private String sdodabo;

	/** The sdoabob. */
	private String sdoabob;;

	/** The nromabo. */
	private String nromabo;

	/** The centrl. */
	private String centrl;

	/** The canal. */
	private String canal;

	/** The implmpu. */
	private String implmpu;

	/** The concimp. */
	private String concimp;

	/** The impuest. */
	private String impuest;

	/** The regimen. */
	private String regimen;

	/** The codalta. */
	private String codalta;

	/** The nomclie. */
	private String nomclie;

	/** The ppriape. */
	private String ppriape;

	/** The psegape. */
	private String psegape;

	/** The tipoid. */
	private String tipoid;

	/** The numiden. */
	private String numiden;

	/** The numbole. */
	private String numbole;

	/** The porimpu. */
	private String porimpu;

	/** The totcarg. */
	private String totcarg;

	/** The niocuentas. */
	private String niocuentas;

	/** The fecha. */
	private String fecha;

	/** The hora. */
	private String hora;

	/** The numero operacion. */
	private String nroOperacion;

	/** The cod error. */
	private Integer codError;

	/**
	 * Instantiates a new operacion cliente compra DTO.
	 */
	public OperacionClienteCompraDTO() {
		super();
	}

	/**
	 * Instantiates a new operacion cliente compra DTO.
	 *
	 * @param codError
	 *            the cod error
	 */
	public OperacionClienteCompraDTO(Integer codError) {
		super();
		this.codError = codError;
	}

	/**
	 * Gets the ccccarg.
	 *
	 * @return the ccccarg
	 */
	public String getCcccarg() {
		return ccccarg;
	}

	/**
	 * Sets the ccccarg.
	 *
	 * @param ccccarg
	 *            the ccccarg to set
	 */
	public void setCcccarg(String ccccarg) {
		this.ccccarg = ccccarg;
	}

	/**
	 * Gets the divcarg.
	 *
	 * @return the divcarg
	 */
	public String getDivcarg() {
		return divcarg;
	}

	/**
	 * Sets the divcarg.
	 *
	 * @param divcarg
	 *            the divcarg to set
	 */
	public void setDivcarg(String divcarg) {
		this.divcarg = divcarg;
	}

	/**
	 * Gets the codcarg.
	 *
	 * @return the codcarg
	 */
	public String getCodcarg() {
		return codcarg;
	}

	/**
	 * Sets the codcarg.
	 *
	 * @param codcarg
	 *            the codcarg to set
	 */
	public void setCodcarg(String codcarg) {
		this.codcarg = codcarg;
	}

	/**
	 * Gets the concarg.
	 *
	 * @return the concarg
	 */
	public String getConcarg() {
		return concarg;
	}

	/**
	 * Sets the concarg.
	 *
	 * @param concarg
	 *            the concarg to set
	 */
	public void setConcarg(String concarg) {
		this.concarg = concarg;
	}

	/**
	 * Gets the impcarg.
	 *
	 * @return the impcarg
	 */
	public String getImpcarg() {
		return impcarg;
	}

	/**
	 * Sets the impcarg.
	 *
	 * @param impcarg
	 *            the impcarg to set
	 */
	public void setImpcarg(String impcarg) {
		this.impcarg = impcarg;
	}

	/**
	 * Gets the cccabon.
	 *
	 * @return the cccabon
	 */
	public String getCccabon() {
		return cccabon;
	}

	/**
	 * Sets the cccabon.
	 *
	 * @param cccabon
	 *            the cccabon to set
	 */
	public void setCccabon(String cccabon) {
		this.cccabon = cccabon;
	}

	/**
	 * Gets the divabon.
	 *
	 * @return the divabon
	 */
	public String getDivabon() {
		return divabon;
	}

	/**
	 * Sets the divabon.
	 *
	 * @param divabon
	 *            the divabon to set
	 */
	public void setDivabon(String divabon) {
		this.divabon = divabon;
	}

	/**
	 * Gets the codabon.
	 *
	 * @return the codabon
	 */
	public String getCodabon() {
		return codabon;
	}

	/**
	 * Sets the codabon.
	 *
	 * @param codabon
	 *            the codabon to set
	 */
	public void setCodabon(String codabon) {
		this.codabon = codabon;
	}

	/**
	 * Gets the conabon.
	 *
	 * @return the conabon
	 */
	public String getConabon() {
		return conabon;
	}

	/**
	 * Sets the conabon.
	 *
	 * @param conabon
	 *            the conabon to set
	 */
	public void setConabon(String conabon) {
		this.conabon = conabon;
	}

	/**
	 * Gets the impabon.
	 *
	 * @return the impabon
	 */
	public String getImpabon() {
		return impabon;
	}

	/**
	 * Sets the impabon.
	 *
	 * @param impabon
	 *            the impabon to set
	 */
	public void setImpabon(String impabon) {
		this.impabon = impabon;
	}

	/**
	 * Gets the tipcamb.
	 *
	 * @return the tipcamb
	 */
	public String getTipcamb() {
		return tipcamb;
	}

	/**
	 * Sets the tipcamb.
	 *
	 * @param tipcamb
	 *            the tipcamb to set
	 */
	public void setTipcamb(String tipcamb) {
		this.tipcamb = tipcamb;
	}

	/**
	 * Gets the impcoti.
	 *
	 * @return the impcoti
	 */
	public String getImpcoti() {
		return impcoti;
	}

	/**
	 * Sets the impcoti.
	 *
	 * @param impcoti
	 *            the impcoti to set
	 */
	public void setImpcoti(String impcoti) {
		this.impcoti = impcoti;
	}

	/**
	 * Gets the fechval.
	 *
	 * @return the fechval
	 */
	public String getFechval() {
		return fechval;
	}

	/**
	 * Sets the fechval.
	 *
	 * @param fechval
	 *            the fechval to set
	 */
	public void setFechval(String fechval) {
		this.fechval = fechval;
	}

	/**
	 * Gets the sdodcar.
	 *
	 * @return the sdodcar
	 */
	public String getSdodcar() {
		return sdodcar;
	}

	/**
	 * Sets the sdodcar.
	 *
	 * @param sdodcar
	 *            the sdodcar to set
	 */
	public void setSdodcar(String sdodcar) {
		this.sdodcar = sdodcar;
	}

	/**
	 * Gets the sdocarb.
	 *
	 * @return the sdocarb
	 */
	public String getSdocarb() {
		return sdocarb;
	}

	/**
	 * Sets the sdocarb.
	 *
	 * @param sdocarb
	 *            the sdocarb to set
	 */
	public void setSdocarb(String sdocarb) {
		this.sdocarb = sdocarb;
	}

	/**
	 * Gets the nromcar.
	 *
	 * @return the nromcar
	 */
	public String getNromcar() {
		return nromcar;
	}

	/**
	 * Sets the nromcar.
	 *
	 * @param nromcar
	 *            the nromcar to set
	 */
	public void setNromcar(String nromcar) {
		this.nromcar = nromcar;
	}

	/**
	 * Gets the sdodabo.
	 *
	 * @return the sdodabo
	 */
	public String getSdodabo() {
		return sdodabo;
	}

	/**
	 * Sets the sdodabo.
	 *
	 * @param sdodabo
	 *            the sdodabo to set
	 */
	public void setSdodabo(String sdodabo) {
		this.sdodabo = sdodabo;
	}

	/**
	 * Gets the sdoabob.
	 *
	 * @return the sdoabob
	 */
	public String getSdoabob() {
		return sdoabob;
	}

	/**
	 * Sets the sdoabob.
	 *
	 * @param sdoabob
	 *            the sdoabob to set
	 */
	public void setSdoabob(String sdoabob) {
		this.sdoabob = sdoabob;
	}

	/**
	 * Gets the nromabo.
	 *
	 * @return the nromabo
	 */
	public String getNromabo() {
		return nromabo;
	}

	/**
	 * Sets the nromabo.
	 *
	 * @param nromabo
	 *            the nromabo to set
	 */
	public void setNromabo(String nromabo) {
		this.nromabo = nromabo;
	}

	/**
	 * Gets the centrl.
	 *
	 * @return the centrl
	 */
	public String getCentrl() {
		return centrl;
	}

	/**
	 * Sets the centrl.
	 *
	 * @param centrl
	 *            the centrl to set
	 */
	public void setCentrl(String centrl) {
		this.centrl = centrl;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the canal to set
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the implmpu.
	 *
	 * @return the implmpu
	 */
	public String getImplmpu() {
		return implmpu;
	}

	/**
	 * Sets the implmpu.
	 *
	 * @param implmpu
	 *            the implmpu to set
	 */
	public void setImplmpu(String implmpu) {
		this.implmpu = implmpu;
	}

	/**
	 * Gets the concimp.
	 *
	 * @return the concimp
	 */
	public String getConcimp() {
		return concimp;
	}

	/**
	 * Sets the concimp.
	 *
	 * @param concimp
	 *            the concimp to set
	 */
	public void setConcimp(String concimp) {
		this.concimp = concimp;
	}

	/**
	 * Gets the impuest.
	 *
	 * @return the impuest
	 */
	public String getImpuest() {
		return impuest;
	}

	/**
	 * Sets the impuest.
	 *
	 * @param impuest
	 *            the impuest to set
	 */
	public void setImpuest(String impuest) {
		this.impuest = impuest;
	}

	/**
	 * Gets the regimen.
	 *
	 * @return the regimen
	 */
	public String getRegimen() {
		return regimen;
	}

	/**
	 * Sets the regimen.
	 *
	 * @param regimen
	 *            the regimen to set
	 */
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}

	/**
	 * Gets the codalta.
	 *
	 * @return the codalta
	 */
	public String getCodalta() {
		return codalta;
	}

	/**
	 * Sets the codalta.
	 *
	 * @param codalta
	 *            the codalta to set
	 */
	public void setCodalta(String codalta) {
		this.codalta = codalta;
	}

	/**
	 * Gets the nomclie.
	 *
	 * @return the nomclie
	 */
	public String getNomclie() {
		return nomclie;
	}

	/**
	 * Sets the nomclie.
	 *
	 * @param nomclie
	 *            the nomclie to set
	 */
	public void setNomclie(String nomclie) {
		this.nomclie = nomclie;
	}

	/**
	 * Gets the ppriape.
	 *
	 * @return the ppriape
	 */
	public String getPpriape() {
		return ppriape;
	}

	/**
	 * Sets the ppriape.
	 *
	 * @param ppriape
	 *            the ppriape to set
	 */
	public void setPpriape(String ppriape) {
		this.ppriape = ppriape;
	}

	/**
	 * Gets the psegape.
	 *
	 * @return the psegape
	 */
	public String getPsegape() {
		return psegape;
	}

	/**
	 * Sets the psegape.
	 *
	 * @param psegape
	 *            the psegape to set
	 */
	public void setPsegape(String psegape) {
		this.psegape = psegape;
	}

	/**
	 * Gets the tipoid.
	 *
	 * @return the tipoid
	 */
	public String getTipoid() {
		return tipoid;
	}

	/**
	 * Sets the tipoid.
	 *
	 * @param tipoid
	 *            the tipoid to set
	 */
	public void setTipoid(String tipoid) {
		this.tipoid = tipoid;
	}

	/**
	 * Gets the numiden.
	 *
	 * @return the numiden
	 */
	public String getNumiden() {
		return numiden;
	}

	/**
	 * Sets the numiden.
	 *
	 * @param numiden
	 *            the numiden to set
	 */
	public void setNumiden(String numiden) {
		this.numiden = numiden;
	}

	/**
	 * Gets the numbole.
	 *
	 * @return the numbole
	 */
	public String getNumbole() {
		return numbole;
	}

	/**
	 * Sets the numbole.
	 *
	 * @param numbole
	 *            the numbole to set
	 */
	public void setNumbole(String numbole) {
		this.numbole = numbole;
	}

	/**
	 * Gets the porimpu.
	 *
	 * @return the porimpu
	 */
	public String getPorimpu() {
		return porimpu;
	}

	/**
	 * Sets the porimpu.
	 *
	 * @param porimpu
	 *            the porimpu to set
	 */
	public void setPorimpu(String porimpu) {
		this.porimpu = porimpu;
	}

	/**
	 * Gets the totcarg.
	 *
	 * @return the totcarg
	 */
	public String getTotcarg() {
		return totcarg;
	}

	/**
	 * Sets the totcarg.
	 *
	 * @param totcarg
	 *            the totcarg to set
	 */
	public void setTotcarg(String totcarg) {
		this.totcarg = totcarg;
	}

	/**
	 * Gets the niocuentas.
	 *
	 * @return the niocuentas
	 */
	public String getNiocuentas() {
		return niocuentas;
	}

	/**
	 * Sets the niocuentas.
	 *
	 * @param niocuentas
	 *            the niocuentas to set
	 */
	public void setNiocuentas(String niocuentas) {
		this.niocuentas = niocuentas;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the new hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Gets the nro operacion.
	 *
	 * @return the nro operacion
	 */
	public String getNroOperacion() {
		return nroOperacion;
	}

	/**
	 * Sets the nro operacion.
	 *
	 * @param nroOperacion
	 *            the new nro operacion
	 */
	public void setNroOperacion(String nroOperacion) {
		this.nroOperacion = nroOperacion;
	}

	/**
	 * Gets the cod error.
	 *
	 * @return the cod error
	 */
	public Integer getCodError() {
		return codError;
	}

	/**
	 * Sets the cod error.
	 *
	 * @param codError
	 *            the new cod error
	 */
	public void setCodError(Integer codError) {
		this.codError = codError;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(ccccarg);
		hcb.append(divcarg);
		hcb.append(codcarg);
		hcb.append(concarg);
		hcb.append(conabon);
		hcb.append(impabon);
		hcb.append(tipcamb);
		hcb.append(impcoti);
		hcb.append(fechval);
		hcb.append(sdodcar);
		hcb.append(sdocarb);
		hcb.append(centrl);
		hcb.append(canal);
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		OperacionClienteCompraDTO other = (OperacionClienteCompraDTO) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(ccccarg, other.getCcccarg());
		eb.append(divcarg, other.getDivcarg());
		eb.append(codcarg, other.getCodcarg());
		eb.append(concarg, other.getConcarg());
		eb.append(conabon, other.getConabon());
		eb.append(impabon, other.getImpabon());
		eb.append(tipcamb, other.getTipcamb());
		eb.append(impcoti, other.getImpcoti());
		eb.append(fechval, other.getFechval());
		eb.append(sdodcar, other.getSdodcar());
		eb.append(sdocarb, other.getSdocarb());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OperacionClienteCompraDTO [ccccarg=" + ccccarg + ", divcarg=" + divcarg + ", codcarg=" + codcarg
				+ ", concarg=" + concarg + ", impcarg=" + impcarg + ", cccabon=" + cccabon + ", divabon=" + divabon
				+ ", codabon=" + codabon + ", conabon=" + conabon + ", impabon=" + impabon + ", tipcamb=" + tipcamb
				+ ", impcoti=" + impcoti + ", fechval=" + fechval + ", sdodcar=" + sdodcar + ", sdocarb=" + sdocarb
				+ ", nromcar=" + nromcar + ", sdodabo=" + sdodabo + ", sdoabob=" + sdoabob + ", nromabo=" + nromabo
				+ ", centrl=" + centrl + ", canal=" + canal + ", implmpu=" + implmpu + ", concimp=" + concimp
				+ ", impuest=" + impuest + ", regimen=" + regimen + ", codalta=" + codalta + ", nomclie=" + nomclie
				+ ", ppriape=" + ppriape + ", psegape=" + psegape + ", tipoid=" + tipoid + ", numiden=" + numiden
				+ ", numbole=" + numbole + ", porimpu=" + porimpu + ", totcarg=" + totcarg + ", niocuentas="
				+ niocuentas + ", fecha=" + fecha + ", hora=" + hora + ", nroOperacion=" + nroOperacion + ", codError="
				+ codError + "]";
	}

}
