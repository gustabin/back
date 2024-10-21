/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * SIMCPVTACN 110. Entity Simulación de venta (Banco Vende / Cliente Compra) de
 * dólares
 * 
 * @author sabrina.cis
 *
 */
public class SimulacionClienteCompraEntity {

	/** The cccarg. */
	private String cccarg;

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
	private String sdoabob;

	/** The nromabo. */
	private String nromabo;

	/** The centori. */
	private String centori;

	/** The canal. */
	private String canal;

	/** The impimpu. */
	private String impimpu;

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

	/** The num bole. */
	private String numBole;

	/** The por impu. */
	private String porImpu;

	/** The tot carg. */
	private String totCarg;
	
    private String importeImpuesto2;

    private String conceptoImpuesto2;

    private String impuesto2;

    private String regimenImpositivo2;

    private String porcentajeImpuesto2;

	private String impuestoBienes;

	/** The cod error. */
	private Integer codError;
	
	/** The mensaje error. */
	private String mensajeError;

	/** The tiene error. */
	private Boolean tieneError = Boolean.FALSE;

	/**
	 * Instantiates a new simulacion cliente compra DTO.
	 */
	public SimulacionClienteCompraEntity() {
		super();
	}

	/**
	 * Instantiates a new simulacion cliente compra DTO.
	 *
	 * @param codError
	 *            the cod error
	 */
	public SimulacionClienteCompraEntity(Integer codError) {
		super();
		this.setCodError(codError);
		this.setTieneError(true);
	}

	public SimulacionClienteCompraEntity(int errorCode, String mensajeError) {
		super();
		this.setCodError(errorCode);
		this.setMensajeError(mensajeError);
		this.setTieneError(true);
	}

	/**
	 * Gets the cccarg.
	 *
	 * @return the cccarg
	 */
	public String getCccarg() {
		return cccarg;
	}

	/**
	 * Sets the cccarg.
	 *
	 * @param cccarg
	 *            the cccarg to set
	 */
	public void setCccarg(String cccarg) {
		this.cccarg = cccarg;
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
	 * Gets the centori.
	 *
	 * @return the centori
	 */
	public String getCentori() {
		return centori;
	}

	/**
	 * Sets the centori.
	 *
	 * @param centori
	 *            the centori to set
	 */
	public void setCentori(String centori) {
		this.centori = centori;
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
	 * Gets the impimpu.
	 *
	 * @return the impimpu
	 */
	public String getImpimpu() {
		return impimpu;
	}

	/**
	 * Sets the impimpu.
	 *
	 * @param impimpu
	 *            the impimpu to set
	 */
	public void setImpimpu(String impimpu) {
		this.impimpu = impimpu;
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
	 * Gets the num bole.
	 *
	 * @return the numBole
	 */
	public String getNumBole() {
		return numBole;
	}

	/**
	 * Sets the num bole.
	 *
	 * @param numBole
	 *            the numBole to set
	 */
	public void setNumBole(String numBole) {
		this.numBole = numBole;
	}

	/**
	 * Gets the por impu.
	 *
	 * @return the porImpu
	 */
	public String getPorImpu() {
		return porImpu;
	}

	/**
	 * Sets the por impu.
	 *
	 * @param porImpu
	 *            the porImpu to set
	 */
	public void setPorImpu(String porImpu) {
		this.porImpu = porImpu;
	}

	/**
	 * Gets the tot carg.
	 *
	 * @return the totCarg
	 */
	public String getTotCarg() {
		return totCarg;
	}

	/**
	 * Sets the tot carg.
	 *
	 * @param totCarg
	 *            the totCarg to set
	 */
	public void setTotCarg(String totCarg) {
		this.totCarg = totCarg;
	}

	/**
     * @return the importeImpuesto2
     */
    public String getImporteImpuesto2() {
        return importeImpuesto2;
    }

    /**
     * @param importeImpuesto2 the importeImpuesto2 to set
     */
    public void setImporteImpuesto2(String importeImpuesto2) {
        this.importeImpuesto2 = importeImpuesto2;
    }

    /**
     * @return the conceptoImpuesto2
     */
    public String getConceptoImpuesto2() {
        return conceptoImpuesto2;
    }

    /**
     * @param conceptoImpuesto2 the conceptoImpuesto2 to set
     */
    public void setConceptoImpuesto2(String conceptoImpuesto2) {
        this.conceptoImpuesto2 = conceptoImpuesto2;
    }

    /**
     * @return the impuesto2
     */
    public String getImpuesto2() {
        return impuesto2;
    }

    /**
     * @param impuesto2 the impuesto2 to set
     */
    public void setImpuesto2(String impuesto2) {
        this.impuesto2 = impuesto2;
    }

    /**
     * @return the regimenImpositivo2
     */
    public String getRegimenImpositivo2() {
        return regimenImpositivo2;
    }

    /**
     * @param regimenImpositivo2 the regimenImpositivo2 to set
     */
    public void setRegimenImpositivo2(String regimenImpositivo2) {
        this.regimenImpositivo2 = regimenImpositivo2;
    }

    /**
     * @return the porcentajeImpuesto2
     */
    public String getPorcentajeImpuesto2() {
        return porcentajeImpuesto2;
    }

    /**
     * @param porcentajeImpuesto2 the porcentajeImpuesto2 to set
     */
    public void setPorcentajeImpuesto2(String porcentajeImpuesto2) {
        this.porcentajeImpuesto2 = porcentajeImpuesto2;
    }

	public String getImpuestoBienes() {
		return impuestoBienes;
	}

	public void setImpuestoBienes(String impuestoBienes) {
		this.impuestoBienes = impuestoBienes;
	}

    /**
	 * Gets the cod error.
	 *
	 * @return the codError
	 */
	public Integer getCodError() {
		return codError;
	}

	/**
	 * Sets the cod error.
	 *
	 * @param codError
	 *            the codError to set
	 */
	public void setCodError(Integer codError) {
		this.codError = codError;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cccarg);
		hcb.append(codError);
		hcb.append(divcarg);
		hcb.append(codcarg);
		hcb.append(concarg);
		hcb.append(impcarg);
		hcb.append(conabon);
		hcb.append(divabon);
		hcb.append(impabon);
		hcb.append(tipcamb);
		hcb.append(impcoti);
		hcb.append(fechval);
		hcb.append(sdodcar);
		hcb.append(sdocarb);
		hcb.append(nromcar);
		hcb.append(sdodabo);
		hcb.append(sdoabob);
		hcb.append(nromabo);
		hcb.append(centori);
		hcb.append(canal);
		hcb.append(impimpu);
		hcb.append(concimp);
		hcb.append(impuest);
		hcb.append(regimen);
		hcb.append(codalta);
		hcb.append(nomclie);
		hcb.append(ppriape);
		hcb.append(psegape);
		hcb.append(tipoid);
		hcb.append(numiden);
		hcb.append(numBole);
		hcb.append(porImpu);
		hcb.append(totCarg);
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
		SimulacionClienteCompraEntity other = (SimulacionClienteCompraEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cccarg, other.getCccarg());
		eb.append(divcarg, other.getDivcarg());
		eb.append(codcarg, other.getCodcarg());
		eb.append(concarg, other.getConcarg());
		eb.append(impcarg, other.getImpcarg());
		eb.append(cccabon, other.getCccabon());
		eb.append(divabon, other.getDivabon());
		eb.append(codabon, other.getCodabon());
		eb.append(conabon, other.getConabon());
		eb.append(impabon, other.getImpabon());
		eb.append(tipcamb, other.getTipcamb());
		eb.append(impcoti, other.getImpcoti());
		eb.append(fechval, other.getFechval());
		eb.append(sdodcar, other.getSdodcar());
		eb.append(nromabo, other.getNromabo());
		eb.append(centori, other.getCentori());
		eb.append(canal, other.getCanal());
		eb.append(impimpu, other.getImpimpu());
		eb.append(concimp, other.getConcimp());
		eb.append(impuest, other.getImpimpu());
		eb.append(regimen, other.getRegimen());
		eb.append(codalta, other.getCodalta());
		eb.append(nomclie, other.getNomclie());
		eb.append(psegape, other.getPsegape());
		eb.append(tipoid, other.getTipoid());
		eb.append(numiden, other.getNumiden());
		eb.append(numBole, other.getNumBole());
		eb.append(porImpu, other.getPorImpu());
		eb.append(totCarg, other.getTotCarg());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("cccarg", cccarg).append("divcarg", divcarg).append("codcarg", codcarg)
				.append("concarg", concarg).append("impcarg", impcarg).append("cccabon", cccabon)
				.append("divabon", divabon).append("codabon", codabon).append("conabon", conabon)
				.append("impabon", impabon).append("tipcamb", tipcamb).append("impcoti", impcoti)
				.append("fechval", fechval).append("sdodcar", sdodcar).append("sdocarb", sdocarb)
				.append("nromcar", nromcar).append("sdodabo", sdodabo).append("sdoabob", sdoabob)
				.append("nromabo", nromabo).append("centori", centori).append("canal", canal).append("impimpu", impimpu)
				.append("concimp", concimp).append("impuest", impuest).append("regimen", regimen)
				.append("codalta", codalta).append("nomclie", nomclie).append("ppriape", ppriape)
				.append("psegape", psegape).append("tipoid", tipoid).append("numiden", numiden)
				.append("numBole", numBole).append("porImpu", porImpu).append("totCarg", totCarg)
				.append("codError", codError).toString();
	}

	/**
	 * Gets the tiene error.
	 *
	 * @return the tiene error
	 */
	public Boolean getTieneError() {
		return tieneError;
	}

	/**
	 * Sets the tiene error.
	 *
	 * @param tieneError
	 *            the new tiene error
	 */
	public void setTieneError(Boolean tieneError) {
		this.tieneError = tieneError;
	}

}