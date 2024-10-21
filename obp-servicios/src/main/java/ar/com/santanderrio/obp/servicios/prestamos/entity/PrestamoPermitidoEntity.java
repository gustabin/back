/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import org.beanio.annotation.Field;

/**
 * The Class PrestamoPermitidoEntity.
 *
 * @author B039543
 */
public class PrestamoPermitidoEntity {

	/** The corresponde empleado. */
	private final String correspondeEmpleado = "S";
	/** The es UVA. */
	private final String esUVA = "S";
	
	/** 1. COD-PRODUCTO-BG A 02 Código de producto de BG (Activas Altair) */
	@Field
	private String codProductoBg;
	/** 2. COD-SUBP-BG A 04 Código de subproducto de BG (Activas Altair) */
	@Field
	private String codSubpBg;
	/**
	 * 3. MAR-PLAN-SUELDO-BG A 01 Marca que identifica si la cta pertenece a
	 * plan sueldo BG
	 */
	@Field
	private String marPlanSueldoBg;
	/** 4. COD-SUSCRIPTOR-BG A 11 Código de suscriptor de BG */
	@Field
	private String codSuscriptorBg;
	/** 5. MARCA-EMPL A 01 Marca de empleado de PE */
	@Field
	private String marcaEmpl;
	/** 6. DEST-FONDOS-COMER-O A 05 Código de destino de fondos comerciales */
	@Field
	private String destFondosComerO;
	/** 7. COD-DIVISA-O A 03 Se recibe ARS o USD */
	@Field
	private String codDivisaO;
	/** 8. TPO-TASA A 01 Tipo de tasa */
	@Field
	private String tpoTasa;
	/** 2. MIN-CANT-CUOTAS N 04 Mínima cantidad de cuotas permitidas */
	@Field
	private String minCantCuotas;
	/** 3. MAX-CANT-CUOTAS N 04 Máxima cantidad de cuotas permitidas */
	@Field
	private String maxCantCuotas;
	/** 4. MIN-IMP-PREST N 13,4 Mínimo importe otorgable */
	@Field
	private String minImpPrest;
	/** 5. MAX-IMP-PREST N 13,4 Máximo importe otorgable */
	@Field
	private String maxImpPrest;
	/** 6. ENTIDAD-UG A 04 Entidad de producto de UG (formato Altair) */
	@Field
	private String entidadUg;
	/** 7. COD-PRODUCTO-UG A 02 Código de producto de UG (Activas Altair) */
	@Field
	private String codProductoUg;
	/** 8. COD-SUBP-UG A 04 Código de subproducto de UG (Activas Altair) */
	@Field
	private String codSubpUg;
	/** 9. DEST-FONDOS-UG A 05 Destino de fondos sw UG (Activa Altair) */
	@Field
	private String destFondosUg;
	/**
	 * 10. CLAUSULA-REV-UG A 03 Código de cláusula de revisión de UG (Activa
	 * Altair)
	 */
	@Field
	private String clausulaRevUg;
	/**
	 * 11. COD-TASA-UG A 04 Código de tasa (coeficiente) de UG (Activa Altair)
	 */
	@Field
	private String codTasaUg;
	/**
	 * 12. VALOR-TASA N 3,6 Tasa (TNA) determinada para el producto préstamo
	 * (Activa Altair)
	 */
	@Field
	private String valorTasa;

	/**
	 * IND-LINEA-UVA A 1 Indicador de oferta  de una línea en uva
	 */
	@Field
	private String indLineaUVA;

	/**
	 * IND-INDEXACION A01 Indicador de Producto con Indexacion
	 */
	@Field
	private String indIndexacion;

	/**
	 * COEFICI-INDEXACION A04 Coeficientde de Indexacion
	 */
	@Field
	private String coeIndexacion;

	/**
	 * COEFICI-VISULIZACION A04 Coeficientde de Visualización
	 */
	@Field
	private String coeVisualizacion;

	/** 13. TPO-POLIZA-DS A 03 Tipo de póliza de Seguros Altair */
	@Field
	private String tpoPolizaDs;
	/** 14. TPO-RIESGO-DS A 03 Tipo de riesgo de Seguros Altair */
	@Field
	private String tpoRiesgoDs;
	/**
	 * 15. ENT-CUENTA-PROVE A 04 Entidad de la cuenta del proveedor formato
	 * Altair
	 */
	@Field
	private String entCuentaProve;
	/**
	 * 16. SUC-CUENTA-PROVE A 04 Sucursal de la cuenta del proveedor formato
	 * Altair
	 */
	@Field
	private String sucCuentaProve;
	/**
	 * 17. NRO-CUENTA-PROVE N 12 Número de cuenta del proveedor formato Altair
	 */
	@Field
	private String nroCuentaProve;

	/** 18. DIVISA-CTA-PROVE A 3 Divisa cuenta del proveedor */
	@Field
	private String divisaCtaProve;

	/** TIPO-OFRETA A15 Descripcion de la Oferta: Preacordado/Preaprobado  */
	@Field
	private String tipoOferta;
	
	/**
	 * Gets the cod producto bg.
	 *
	 * @return the codProductoBg
	 */
	public String getCodProductoBg() {
		return codProductoBg;
	}

	/**
	 * Sets the cod producto bg.
	 *
	 * @param codProductoBg
	 *            the codProductoBg to set
	 */
	public void setCodProductoBg(String codProductoBg) {
		this.codProductoBg = codProductoBg;
	}

	/**
	 * Gets the cod subp bg.
	 *
	 * @return the codSubpBg
	 */
	public String getCodSubpBg() {
		return codSubpBg;
	}

	/**
	 * Sets the cod subp bg.
	 *
	 * @param codSubpBg
	 *            the codSubpBg to set
	 */
	public void setCodSubpBg(String codSubpBg) {
		this.codSubpBg = codSubpBg;
	}

	/**
	 * Gets the mar plan sueldo bg.
	 *
	 * @return the marPlanSueldoBg
	 */
	public String getMarPlanSueldoBg() {
		return marPlanSueldoBg;
	}

	/**
	 * Sets the mar plan sueldo bg.
	 *
	 * @param marPlanSueldoBg
	 *            the marPlanSueldoBg to set
	 */
	public void setMarPlanSueldoBg(String marPlanSueldoBg) {
		this.marPlanSueldoBg = marPlanSueldoBg;
	}

	/**
	 * Gets the cod suscriptor bg.
	 *
	 * @return the codSuscriptorBg
	 */
	public String getCodSuscriptorBg() {
		return codSuscriptorBg;
	}

	/**
	 * Sets the cod suscriptor bg.
	 *
	 * @param codSuscriptorBg
	 *            the codSuscriptorBg to set
	 */
	public void setCodSuscriptorBg(String codSuscriptorBg) {
		this.codSuscriptorBg = codSuscriptorBg;
	}

	/**
	 * Gets the marca empl.
	 *
	 * @return the marcaEmpl
	 */
	public String getMarcaEmpl() {
		return marcaEmpl;
	}

	/**
	 * Sets the marca empl.
	 *
	 * @param marcaEmpl
	 *            the marcaEmpl to set
	 */
	public void setMarcaEmpl(String marcaEmpl) {
		this.marcaEmpl = marcaEmpl;
	}

	/**
	 * Gets the dest fondos comer O.
	 *
	 * @return the destFondosComerO
	 */
	public String getDestFondosComerO() {
		return destFondosComerO;
	}

	/**
	 * Sets the dest fondos comer O.
	 *
	 * @param destFondosComerO
	 *            the destFondosComerO to set
	 */
	public void setDestFondosComerO(String destFondosComerO) {
		this.destFondosComerO = destFondosComerO;
	}

	/**
	 * Gets the cod divisa O.
	 *
	 * @return the codDivisaO
	 */
	public String getCodDivisaO() {
		return codDivisaO;
	}

	/**
	 * Sets the cod divisa O.
	 *
	 * @param codDivisaO
	 *            the codDivisaO to set
	 */
	public void setCodDivisaO(String codDivisaO) {
		this.codDivisaO = codDivisaO;
	}

	/**
	 * Gets the tpo tasa.
	 *
	 * @return the tpoTasa
	 */
	public String getTpoTasa() {
		return tpoTasa;
	}

	/**
	 * Sets the tpo tasa.
	 *
	 * @param tpoTasa
	 *            the tpoTasa to set
	 */
	public void setTpoTasa(String tpoTasa) {
		this.tpoTasa = tpoTasa;
	}

	/**
	 * Gets the min cant cuotas.
	 *
	 * @return the minCantCuotas
	 */
	public String getMinCantCuotas() {
		return minCantCuotas;
	}

	/**
	 * Sets the min cant cuotas.
	 *
	 * @param minCantCuotas
	 *            the minCantCuotas to set
	 */
	public void setMinCantCuotas(String minCantCuotas) {
		this.minCantCuotas = minCantCuotas;
	}

	/**
	 * Gets the max cant cuotas.
	 *
	 * @return the maxCantCuotas
	 */
	public String getMaxCantCuotas() {
		return maxCantCuotas;
	}

	/**
	 * Sets the max cant cuotas.
	 *
	 * @param maxCantCuotas
	 *            the maxCantCuotas to set
	 */
	public void setMaxCantCuotas(String maxCantCuotas) {
		this.maxCantCuotas = maxCantCuotas;
	}

	/**
	 * Gets the min imp prest.
	 *
	 * @return the minImpPrest
	 */
	public String getMinImpPrest() {
		return minImpPrest;
	}

	/**
	 * Sets the min imp prest.
	 *
	 * @param minImpPrest
	 *            the minImpPrest to set
	 */
	public void setMinImpPrest(String minImpPrest) {
		this.minImpPrest = minImpPrest;
	}

	/**
	 * Gets the max imp prest.
	 *
	 * @return the maxImpPrest
	 */
	public String getMaxImpPrest() {
		return maxImpPrest;
	}

	/**
	 * Sets the max imp prest.
	 *
	 * @param maxImpPrest
	 *            the maxImpPrest to set
	 */
	public void setMaxImpPrest(String maxImpPrest) {
		this.maxImpPrest = maxImpPrest;
	}

	/**
	 * Gets the entidad ug.
	 *
	 * @return the entidadUg
	 */
	public String getEntidadUg() {
		return entidadUg;
	}

	/**
	 * Sets the entidad ug.
	 *
	 * @param entidadUg
	 *            the entidadUg to set
	 */
	public void setEntidadUg(String entidadUg) {
		this.entidadUg = entidadUg;
	}

	/**
	 * Gets the cod producto ug.
	 *
	 * @return the codProductoUg
	 */
	public String getCodProductoUg() {
		return codProductoUg;
	}

	/**
	 * Sets the cod producto ug.
	 *
	 * @param codProductoUg
	 *            the codProductoUg to set
	 */
	public void setCodProductoUg(String codProductoUg) {
		this.codProductoUg = codProductoUg;
	}

	/**
	 * Gets the cod subp ug.
	 *
	 * @return the codSubpUg
	 */
	public String getCodSubpUg() {
		return codSubpUg;
	}

	/**
	 * Sets the cod subp ug.
	 *
	 * @param codSubpUg
	 *            the codSubpUg to set
	 */
	public void setCodSubpUg(String codSubpUg) {
		this.codSubpUg = codSubpUg;
	}

	/**
	 * Gets the dest fondos ug.
	 *
	 * @return the destFondosUg
	 */
	public String getDestFondosUg() {
		return destFondosUg;
	}

	/**
	 * Sets the dest fondos ug.
	 *
	 * @param destFondosUg
	 *            the destFondosUg to set
	 */
	public void setDestFondosUg(String destFondosUg) {
		this.destFondosUg = destFondosUg;
	}

	/**
	 * Gets the clausula rev ug.
	 *
	 * @return the clausulaRevUg
	 */
	public String getClausulaRevUg() {
		return clausulaRevUg;
	}

	/**
	 * Sets the clausula rev ug.
	 *
	 * @param clausulaRevUg
	 *            the clausulaRevUg to set
	 */
	public void setClausulaRevUg(String clausulaRevUg) {
		this.clausulaRevUg = clausulaRevUg;
	}

	/**
	 * Gets the cod tasa ug.
	 *
	 * @return the codTasaUg
	 */
	public String getCodTasaUg() {
		return codTasaUg;
	}

	/**
	 * Sets the cod tasa ug.
	 *
	 * @param codTasaUg
	 *            the codTasaUg to set
	 */
	public void setCodTasaUg(String codTasaUg) {
		this.codTasaUg = codTasaUg;
	}

	/**
	 * Gets the valor tasa.
	 *
	 * @return the valorTasa
	 */
	public String getValorTasa() {
		return valorTasa;
	}

	/**
	 * Sets the valor tasa.
	 *
	 * @param valorTasa
	 *            the valorTasa to set
	 */
	public void setValorTasa(String valorTasa) {
		this.valorTasa = valorTasa;
	}

	/**
	 * Gets the tpo poliza ds.
	 *
	 * @return the tpoPolizaDs
	 */
	public String getTpoPolizaDs() {
		return tpoPolizaDs;
	}

	/**
	 * Sets the tpo poliza ds.
	 *
	 * @param tpoPolizaDs
	 *            the tpoPolizaDs to set
	 */
	public void setTpoPolizaDs(String tpoPolizaDs) {
		this.tpoPolizaDs = tpoPolizaDs;
	}

	/**
	 * Gets the tpo riesgo ds.
	 *
	 * @return the tpoRiesgoDs
	 */
	public String getTpoRiesgoDs() {
		return tpoRiesgoDs;
	}

	/**
	 * Sets the tpo riesgo ds.
	 *
	 * @param tpoRiesgoDs
	 *            the tpoRiesgoDs to set
	 */
	public void setTpoRiesgoDs(String tpoRiesgoDs) {
		this.tpoRiesgoDs = tpoRiesgoDs;
	}

	/**
	 * Gets the ent cuenta prove.
	 *
	 * @return the entCuentaProve
	 */
	public String getEntCuentaProve() {
		return entCuentaProve;
	}

	/**
	 * Sets the ent cuenta prove.
	 *
	 * @param entCuentaProve
	 *            the entCuentaProve to set
	 */
	public void setEntCuentaProve(String entCuentaProve) {
		this.entCuentaProve = entCuentaProve;
	}

	/**
	 * Gets the suc cuenta prove.
	 *
	 * @return the sucCuentaProve
	 */
	public String getSucCuentaProve() {
		return sucCuentaProve;
	}

	/**
	 * Sets the suc cuenta prove.
	 *
	 * @param sucCuentaProve
	 *            the sucCuentaProve to set
	 */
	public void setSucCuentaProve(String sucCuentaProve) {
		this.sucCuentaProve = sucCuentaProve;
	}

	/**
	 * Gets the nro cuenta prove.
	 *
	 * @return the nroCuentaProve
	 */
	public String getNroCuentaProve() {
		return nroCuentaProve;
	}

	/**
	 * Sets the nro cuenta prove.
	 *
	 * @param nroCuentaProve
	 *            the nroCuentaProve to set
	 */
	public void setNroCuentaProve(String nroCuentaProve) {
		this.nroCuentaProve = nroCuentaProve;
	}

	/**
	 * Gets the divisa cta prove.
	 *
	 * @return the divisaCtaProve
	 */
	public String getDivisaCtaProve() {
		return divisaCtaProve;
	}

	/**
	 * Sets the divisa cta prove.
	 *
	 * @param divisaCtaProve
	 *            the divisaCtaProve to set
	 */
	public void setDivisaCtaProve(String divisaCtaProve) {
		this.divisaCtaProve = divisaCtaProve;
	}

	/**
	 * Gets the ind linea UVA.
	 *
	 * @return the indLineaUVA
	 */
	public String getIndLineaUVA() {
		return indLineaUVA;
	}

	/**
	 * Sets the ind linea UVA.
	 *
	 * @param indLineaUVA
	 *            the indLineaUVA to set
	 */
	public void setIndLineaUVA(String indLineaUVA) {
		this.indLineaUVA = indLineaUVA;
	}

	/**
	 * Es empleado.
	 *
	 * @return true, if successful
	 */
	public boolean esEmpleado() {
		return this.getMarcaEmpl().equals(this.correspondeEmpleado);
	}

	/**
	 * Es UVA.
	 *
	 * @return true, if successful
	 */
	public boolean esUVA() {
		return this.getIndLineaUVA().equals(this.esUVA);
	}

	public String getIndIndexacion() {
		return indIndexacion;
	}

	public void setIndIndexacion(String indIndexacion) {
		this.indIndexacion = indIndexacion;
	}

	public String getCoeIndexacion() {
		return coeIndexacion;
	}

	public void setCoeIndexacion(String coeIndexacion) {
		this.coeIndexacion = coeIndexacion;
	}

	public String getCoeVisualizacion() {
		return coeVisualizacion;
	}

	public void setCoeVisualizacion(String coeVisualizacion) {
		this.coeVisualizacion = coeVisualizacion;
	}

	public String getTipoOferta() {
		return tipoOferta;
	}

	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PrestamoPermitidoEntity)) return false;

		PrestamoPermitidoEntity that = (PrestamoPermitidoEntity) o;

		if (getCodDivisaO() != null ? !getCodDivisaO().equals(that.getCodDivisaO()) : that.getCodDivisaO() != null)
			return false;
		if (getTpoTasa() != null ? !getTpoTasa().equals(that.getTpoTasa()) : that.getTpoTasa() != null) return false;
		if (getMinCantCuotas() != null ? !getMinCantCuotas().equals(that.getMinCantCuotas()) : that.getMinCantCuotas() != null)
			return false;
		if (getMaxCantCuotas() != null ? !getMaxCantCuotas().equals(that.getMaxCantCuotas()) : that.getMaxCantCuotas() != null)
			return false;
		if (getMinImpPrest() != null ? !getMinImpPrest().equals(that.getMinImpPrest()) : that.getMinImpPrest() != null)
			return false;
		if (getMaxImpPrest() != null ? !getMaxImpPrest().equals(that.getMaxImpPrest()) : that.getMaxImpPrest() != null)
			return false;
		if (getEntidadUg() != null ? !getEntidadUg().equals(that.getEntidadUg()) : that.getEntidadUg() != null)
			return false;
		if (getCodProductoUg() != null ? !getCodProductoUg().equals(that.getCodProductoUg()) : that.getCodProductoUg() != null)
			return false;
		if (getCodSubpUg() != null ? !getCodSubpUg().equals(that.getCodSubpUg()) : that.getCodSubpUg() != null)
			return false;
		return getTipoOferta() != null ? getTipoOferta().equals(that.getTipoOferta()) : that.getTipoOferta() == null;
	}

	@Override
	public int hashCode() {
		int result = getCodDivisaO() != null ? getCodDivisaO().hashCode() : 0;
		result = 31 * result + (getTpoTasa() != null ? getTpoTasa().hashCode() : 0);
		result = 31 * result + (getMinCantCuotas() != null ? getMinCantCuotas().hashCode() : 0);
		result = 31 * result + (getMaxCantCuotas() != null ? getMaxCantCuotas().hashCode() : 0);
		result = 31 * result + (getMinImpPrest() != null ? getMinImpPrest().hashCode() : 0);
		result = 31 * result + (getMaxImpPrest() != null ? getMaxImpPrest().hashCode() : 0);
		result = 31 * result + (getEntidadUg() != null ? getEntidadUg().hashCode() : 0);
		result = 31 * result + (getCodProductoUg() != null ? getCodProductoUg().hashCode() : 0);
		result = 31 * result + (getCodSubpUg() != null ? getCodSubpUg().hashCode() : 0);
		result = 31 * result + (getTipoOferta() != null ? getTipoOferta().hashCode() : 0);
		return result;
	}
}
