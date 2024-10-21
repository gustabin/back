/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class PrestamoOutEntity.
 *
 * @author florencia.n.martinez
 */
@Record
public class PrestamoOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The ent prest. */
	@Field
	private String entPrest;

	/** The suc prest. */
	@Field
	private String sucPrest;

	/** The num prest. */
	@Field
	private String numPrest;

	/** The divisa. */
	@Field
	private String divisa;

	/** The tasa. */
	@Field
	private String tasa;

	/** The nio. */
	@Field
	private String nio;

	/** The tot gastos pre. */
	@Field
	private String totGastosPre;

	/** The tot comisiones. */
	@Field
	private String totComisiones;

	/** The tot iva. */
	@Field
	private String totIva;

	/** The tot iva adicional. */
	@Field
	private String totIvaAdicional;

	/** The tot resto impu cuota. */
	@Field
	private String totRestoImpu;

	/** The importe abono. */
	@Field
	private String importeAbono;

	/** The importe cargo. */
	@Field
	private String importeCargo;

	/** The tot gastos cuota. */
	@Field
	private String totGastosCuota;

	/** The tot comisiones cuota. */
	@Field
	private String totComisionesCuota;

	/** The tot iva cuota. */
	@Field
	private String totIvaCuota;

	/** The tot resto impu cuota. */
	@Field
	private String totRestoImpuCuota;

	/** The tot seguro cuota. */
	@Field
	private String totSeguroCuota;

	/** The cuota pura. */
	@Field
	private String cuotaPura;

	/** The tot cuota total. */
	@Field
	private String totCuotaTotal;

	/** The cftna. */
	@Field
	private String cftna;

	/** The cftnasimp. */
	@Field
	private String cftnasimp;

	/** The tea. */
	@Field
	private String tea;

	/** The nroExp. */
	@Field
	private String nroExp;
	
	/** The cotizCambio. */
	@Field
	private String cotizCambio;
	
	/** The fechaCotiz. */
	@Field
	private String fechaCotiz;
	
	/** The importeSolicitado. */
	@Field
	private String importeSolicitado;
	
	/** The importeCuotaCTE. */
	@Field
	private String importeCuotaCTE;
	
	/** The importePrimerCuotaPura. */
	@Field
	private String importePrimerCuotaPura;
	
	/** The nro comprobante. */
	private String nroComprobante;

	/** The fecha. */
	private String fecha;
	
	private String fase;

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	/**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
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
	 * Gets the tot cuota total.
	 *
	 * @return the totCuotaTotal
	 */
	public String getTotCuotaTotal() {
		return totCuotaTotal;
	}

	/**
	 * Sets the tot cuota total.
	 *
	 * @param totCuotaTotal
	 *            the totCuotaTotal to set
	 */
	public void setTotCuotaTotal(String totCuotaTotal) {
		this.totCuotaTotal = totCuotaTotal;
	}

	/**
	 * Gets the cuota pura.
	 *
	 * @return the cuotaPura
	 */
	public String getCuotaPura() {
		return cuotaPura;
	}

	/**
	 * Sets the cuota pura.
	 *
	 * @param cuotaPura
	 *            the cuotaPura to set
	 */
	public void setCuotaPura(String cuotaPura) {
		this.cuotaPura = cuotaPura;
	}

	/**
	 * Gets the tot seguro cuota.
	 *
	 * @return the totSeguroCuota
	 */
	public String getTotSeguroCuota() {
		return totSeguroCuota;
	}

	/**
	 * Sets the tot seguro cuota.
	 *
	 * @param totSeguroCuota
	 *            the totSeguroCuota to set
	 */
	public void setTotSeguroCuota(String totSeguroCuota) {
		this.totSeguroCuota = totSeguroCuota;
	}

	/**
	 * Gets the tot iva cuota.
	 *
	 * @return the totIvaCuota
	 */
	public String getTotIvaCuota() {
		return totIvaCuota;
	}

	/**
	 * Sets the tot iva cuota.
	 *
	 * @param totIvaCuota
	 *            the totIvaCuota to set
	 */
	public void setTotIvaCuota(String totIvaCuota) {
		this.totIvaCuota = totIvaCuota;
	}

	/**
	 * Gets the tot resto impu cuota.
	 *
	 * @return the totRestoImpuCuota
	 */
	public String getTotRestoImpuCuota() {
		return totRestoImpuCuota;
	}

	/**
	 * Sets the tot resto impu cuota.
	 *
	 * @param totRestoImpuCuota
	 *            the totRestoImpuCuota to set
	 */
	public void setTotRestoImpuCuota(String totRestoImpuCuota) {
		this.totRestoImpuCuota = totRestoImpuCuota;
	}

	/**
	 * Gets the tasa.
	 *
	 * @return the tasa
	 */
	public String getTasa() {
		return tasa;
	}

	/**
	 * Sets the tasa.
	 *
	 * @param tasa
	 *            the tasa to set
	 */
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigoRetornoExtendido to set
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the ent prest.
	 *
	 * @return the entPrest
	 */
	public String getEntPrest() {
		return entPrest;
	}

	/**
	 * Sets the ent prest.
	 *
	 * @param entPrest
	 *            the entPrest to set
	 */
	public void setEntPrest(String entPrest) {
		this.entPrest = entPrest;
	}

	/**
	 * Gets the suc prest.
	 *
	 * @return the sucPrest
	 */
	public String getSucPrest() {
		return sucPrest;
	}

	/**
	 * Sets the suc prest.
	 *
	 * @param sucPrest
	 *            the sucPrest to set
	 */
	public void setSucPrest(String sucPrest) {
		this.sucPrest = sucPrest;
	}

	/**
	 * Gets the num prest.
	 *
	 * @return the numPrest
	 */
	public String getNumPrest() {
		return numPrest;
	}

	/**
	 * Sets the num prest.
	 *
	 * @param numPrest
	 *            the numPrest to set
	 */
	public void setNumPrest(String numPrest) {
		this.numPrest = numPrest;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the divisa to set
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

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
	 * Gets the tot gastos pre.
	 *
	 * @return the totGastosPre
	 */
	public String getTotGastosPre() {
		return totGastosPre;
	}

	/**
	 * Sets the tot gastos pre.
	 *
	 * @param totGastosPre
	 *            the totGastosPre to set
	 */
	public void setTotGastosPre(String totGastosPre) {
		this.totGastosPre = totGastosPre;
	}

	/**
	 * Gets the tot comisiones.
	 *
	 * @return the totComisiones
	 */
	public String getTotComisiones() {
		return totComisiones;
	}

	/**
	 * Sets the tot comisiones.
	 *
	 * @param totComisiones
	 *            the totComisiones to set
	 */
	public void setTotComisiones(String totComisiones) {
		this.totComisiones = totComisiones;
	}

	/**
	 * Gets the tot iva.
	 *
	 * @return the totIva
	 */
	public String getTotIva() {
		return totIva;
	}

	/**
	 * Sets the tot iva.
	 *
	 * @param totIva
	 *            the totIva to set
	 */
	public void setTotIva(String totIva) {
		this.totIva = totIva;
	}

	/**
	 * Gets the tot iva adicional.
	 *
	 * @return the totIvaAdicional
	 */
	public String getTotIvaAdicional() {
		return totIvaAdicional;
	}

	/**
	 * Sets the tot iva adicional.
	 *
	 * @param totIvaAdicional
	 *            the totIvaAdicional to set
	 */
	public void setTotIvaAdicional(String totIvaAdicional) {
		this.totIvaAdicional = totIvaAdicional;
	}

	/**
	 * Gets the tot resto impu.
	 *
	 * @return the totRestoImpu
	 */
	public String getTotRestoImpu() {
		return totRestoImpu;
	}

	/**
	 * Sets the tot resto impu.
	 *
	 * @param totRestoImpu
	 *            the totRestoImpu to set
	 */
	public void setTotRestoImpu(String totRestoImpu) {
		this.totRestoImpu = totRestoImpu;
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
	 * Gets the tot gastos cuota.
	 *
	 * @return the totGastosCuota
	 */
	public String getTotGastosCuota() {
		return totGastosCuota;
	}

	/**
	 * Sets the tot gastos cuota.
	 *
	 * @param totGastosCuota
	 *            the totGastosCuota to set
	 */
	public void setTotGastosCuota(String totGastosCuota) {
		this.totGastosCuota = totGastosCuota;
	}

	/**
	 * Gets the tot comisiones cuota.
	 *
	 * @return the totComisionesCuota
	 */
	public String getTotComisionesCuota() {
		return totComisionesCuota;
	}

	/**
	 * Sets the tot comisiones cuota.
	 *
	 * @param totComisionesCuota
	 *            the totComisionesCuota to set
	 */
	public void setTotComisionesCuota(String totComisionesCuota) {
		this.totComisionesCuota = totComisionesCuota;
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
	 * Gets the tea.
	 *
	 * @return the tea
	 */
	public String getTea() {
		return tea;
	}

	/**
	 * Sets the tea.
	 *
	 * @param tea
	 *            the tea to set
	 */
	public void setTea(String tea) {
		this.tea = tea;
	}

	/**
	 * Gets the cftnasimp.
	 *
	 * @return the cftnasimp
	 */
	public String getCftnasimp() {
		return cftnasimp;
	}

	/**
	 * Sets the cftnasimp.
	 *
	 * @param cftnasimp
	 *            the cftnasimp to set
	 */
	public void setCftnasimp(String cftnasimp) {
		this.cftnasimp = cftnasimp;
	}

	/**
	 * Gets the nro exp.
	 *
	 * @return the nroExp
	 */
	public String getNroExp() {
		return nroExp;
	}

	/**
	 * Sets the nro exp.
	 *
	 * @param nroExp
	 *            the nroExp to set
	 */
	public void setNroExp(String nroExp) {
		this.nroExp = nroExp;
	}

	/**
	 * Gets the cotiz cambio.
	 *
	 * @return the cotizCambio
	 */
	public String getCotizCambio() {
		return cotizCambio;
	}

	/**
	 * Sets the cotiz cambio.
	 *
	 * @param cotizCambio
	 *            the cotizCambio to set
	 */
	public void setCotizCambio(String cotizCambio) {
		this.cotizCambio = cotizCambio;
	}

	/**
	 * Gets the fecha cotiz.
	 *
	 * @return the fechaCotiz
	 */
	public String getFechaCotiz() {
		return fechaCotiz;
	}

	/**
	 * Sets the fecha cotiz.
	 *
	 * @param fechaCotiz
	 *            the fechaCotiz to set
	 */
	public void setFechaCotiz(String fechaCotiz) {
		this.fechaCotiz = fechaCotiz;
	}


	/**
	 * Gets the importe solicitado.
	 *
	 * @return the importeSolicitado
	 */
	public String getImporteSolicitado() {
		return importeSolicitado;
	}

	/**
	 * Sets the importe solicitado.
	 *
	 * @param importeSolicitado
	 *            the importeSolicitado to set
	 */
	public void setImporteSolicitado(String importeSolicitado) {
		this.importeSolicitado = importeSolicitado;
	}

	/**
	 * Gets the importe cuota CTE.
	 *
	 * @return the importeCuotaCTE
	 */
	public String getImporteCuotaCTE() {
		return importeCuotaCTE;
	}

	/**
	 * Sets the importe cuota CTE.
	 *
	 * @param importeCuotaCTE
	 *            the importeCuotaCTE to set
	 */
	public void setImporteCuotaCTE(String importeCuotaCTE) {
		this.importeCuotaCTE = importeCuotaCTE;
	}

	/**
	 * Gets the importe primer cuota pura.
	 *
	 * @return the importePrimerCuotaPura
	 */
	public String getImportePrimerCuotaPura() {
		return importePrimerCuotaPura;
	}

	/**
	 * Sets the importe primer cuota pura.
	 *
	 * @param importePrimerCuotaPura
	 *            the importePrimerCuotaPura to set
	 */
	public void setImportePrimerCuotaPura(String importePrimerCuotaPura) {
		this.importePrimerCuotaPura = importePrimerCuotaPura;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
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
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigoRetornoExtendido);
		hcb.append(cftna);
		hcb.append(cftnasimp);
		hcb.append(cotizCambio);
		hcb.append(cuotaPura);
		hcb.append(divisa);
		hcb.append(entPrest);
		hcb.append(fechaCotiz);
		hcb.append(headerTrama);
		hcb.append(importeAbono);
		hcb.append(importeCargo);
		hcb.append(importeCuotaCTE);
		hcb.append(importePrimerCuotaPura);
		hcb.append(importeSolicitado);
		hcb.append(nio);
		hcb.append(numPrest);
		hcb.append(nroExp);
		hcb.append(sucPrest);
		hcb.append(tasa);
		hcb.append(tea);
		hcb.append(totComisiones);
		hcb.append(totComisionesCuota);
		hcb.append(totCuotaTotal);
		hcb.append(totGastosCuota);
		hcb.append(totGastosPre);
		hcb.append(totIva);
		hcb.append(totIvaAdicional);
		hcb.append(totIvaCuota);
		hcb.append(totRestoImpu);
		hcb.append(totRestoImpuCuota);
		hcb.append(totSeguroCuota);
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
		PrestamoOutEntity other = (PrestamoOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cftna, other.getCftna());
		eb.append(cftnasimp, other.getCftna());
		eb.append(codigoRetornoExtendido, other.getCodigoRetornoExtendido());
		eb.append(cotizCambio, other.getCotizCambio());
		eb.append(cuotaPura, other.getCuotaPura());
		eb.append(divisa, other.getDivisa());
		eb.append(entPrest, other.getEntPrest());
		eb.append(fechaCotiz, other.getFechaCotiz());
		eb.append(headerTrama, other.getHeaderTrama());
		eb.append(importeAbono, other.getImporteAbono());
		eb.append(importeCargo, other.getImporteCargo());
		eb.append(importeCuotaCTE, other.getImporteCuotaCTE());
		eb.append(importePrimerCuotaPura, other.getImporteCuotaCTE());
		eb.append(importeSolicitado, other.getImporteSolicitado());
		eb.append(nio, other.getNio());
		eb.append(numPrest, other.getNumPrest());
		eb.append(nroExp, other.getNroExp());
		eb.append(sucPrest, other.getSucPrest());
		eb.append(tasa, other.getTasa());
		eb.append(tea, other.getTea());
		eb.append(totComisiones, other.getTotComisiones());
		eb.append(totComisionesCuota, other.getTotComisionesCuota());
		eb.append(totCuotaTotal, other.getTotCuotaTotal());
		eb.append(totGastosCuota, other.getTotGastosCuota());
		eb.append(totGastosPre, other.getTotGastosPre());
		eb.append(totIva, other.getTotIva());
		eb.append(totIvaAdicional, other.getTotIvaAdicional());
		eb.append(totIvaCuota, other.getTotIvaCuota());
		eb.append(totRestoImpu, other.getTotRestoImpu());
		eb.append(totRestoImpuCuota, other.getTotRestoImpuCuota());
		eb.append(totSeguroCuota, other.getTotSeguroCuota());
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
		return new ToStringBuilder(this).append("headerTrama", headerTrama)
				.append("codigoRetornoExtendido", codigoRetornoExtendido).append("entPrest", entPrest)
				.append("sucPrest", sucPrest).append("numPrest", numPrest).append("divisa", divisa).append("tasa", tasa)
				.append("nio", nio).append("totGastosPre", totGastosPre).append("totComisiones", totComisiones)
				.append("totIva", totIva).append("totIvaAdicional", totIvaAdicional)
				.append("totRestoImpu", totRestoImpu).append("importeAbono", importeAbono)
				.append("importeCargo", importeCargo).append("totGastosCuota", totGastosCuota)
				.append("totComisionesCuota", totComisionesCuota).append("totIvaCuota", totIvaCuota)
				.append("totRestoImpuCuota", totRestoImpuCuota).append("totSeguroCuota", totSeguroCuota)
				.append("cuotaPura", cuotaPura).append("totCuotaTotal", totCuotaTotal).append("cftna", cftna)
				.append("cftnasimp", cftnasimp).append("tea", tea).append("nroExp", nroExp)
				.append("cotizCambio", cotizCambio).append("fechaCotiz", fechaCotiz)
				.append("importeSolicitado", importeSolicitado).append("importePrimerCuotaPura", importePrimerCuotaPura)
				.append("importeCuotaCTE", importeCuotaCTE).toString();
	}

}