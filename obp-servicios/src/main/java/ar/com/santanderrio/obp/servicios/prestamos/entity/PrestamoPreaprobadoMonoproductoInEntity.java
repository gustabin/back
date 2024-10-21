package ar.com.santanderrio.obp.servicios.prestamos.entity;

import java.math.BigDecimal;

/**
 * Bean con datos de entrada para el servicio ALTFOPMOPE
 * 
 * @author A309331
 *
 */
public class PrestamoPreaprobadoMonoproductoInEntity {

	private String numProp;
	private String producto;
	private String subproProp;
	private String codDestino;
	private String cuotasProp;
	private String oficTitular;
	private String oficElevadora;
	private String oficPresentadora;
	private BigDecimal montoSolic;
	private String cccVin;
	private String cccAbo;
	private String nroCliente;
	private String nroCotitular1;
	private String nroCotitular2;
	private String nroCotitular3;
	private String nroCotitular4;
	private String nroCotitular5;
	private String divisa;
	private String canalVenta;
	private String oficialventa;
	private String oficialComercial;
	private String codInstrumto;
	private String fecAprobacion;
	private String fecFormalizacion;
	private String fecValor;
	private String fecPrimerVto;
	private String diaVto;
	private String tipTasa;
	private String indNegociabil;
	private BigDecimal tipAplicacion;
	private String clarevis;
	private String modAcreditac;
	private String tipCondicAlternat;
	private String codCondAlternat;
	private String codAgen;
	private String indContSeg;
	private String codCiaSeg;
	private String indBonifcta;
	private String codRiesgo;
	private String indIndexado;
	private String coeficiIndex;
	private String coeficiVisual;
	private String fase;

	public PrestamoPreaprobadoMonoproductoInEntity(String producto, String subproProp, String codDestino,
			String cuotasProp, String oficTitular, String oficElevadora, BigDecimal montoSolic, String cccVin,
			String cccAbo, String nroCliente, String divisa, String canalVenta, String codInstrumto,
			String fecAprobacion, String fecFormalizacion, String tipTasa, BigDecimal tipAplicacion, 
			String indNegociabil, String fechaPrimerVto,String indBonifcta, String fase) {
		this.numProp = "";
		this.producto = producto;
		this.subproProp = subproProp;
		this.codDestino = codDestino;
		this.cuotasProp = cuotasProp;
		this.oficTitular = oficTitular;
		this.oficElevadora = oficElevadora;
		this.oficPresentadora = "";
		this.montoSolic = montoSolic;
		this.cccVin = cccVin;
		this.cccAbo = cccAbo;
		this.nroCliente = nroCliente;
		this.nroCotitular1 = "";
		this.nroCotitular2 = "";
		this.nroCotitular3 = "";
		this.nroCotitular4 = "";
		this.nroCotitular5 = "";
		this.divisa = divisa;
		this.canalVenta = canalVenta;
		this.oficialventa = "";
		this.oficialComercial = "";
		this.codInstrumto = codInstrumto;
		this.fecAprobacion = fecAprobacion;
		this.fecFormalizacion = fecFormalizacion;
		this.fecValor = "";
		this.fecPrimerVto = fechaPrimerVto;
		this.diaVto = "";
		this.tipTasa = tipTasa;
		this.indNegociabil = indNegociabil;
		this.tipAplicacion = tipAplicacion;
		this.clarevis = "";
		this.modAcreditac = "";
		this.tipCondicAlternat = "";
		this.codCondAlternat = "";
		this.codAgen = "";
		this.indContSeg = "";
		this.codCiaSeg = "";
		this.indBonifcta = indBonifcta;
		this.codRiesgo = "";
		this.indIndexado = "";
		this.coeficiIndex = "";
		this.coeficiVisual = "";
		this.fase = fase;
	}

	public String getNumProp() {
		return numProp;
	}

	public void setNumProp(String numProp) {
		this.numProp = numProp;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getSubproProp() {
		return subproProp;
	}

	public void setSubproProp(String subproProp) {
		this.subproProp = subproProp;
	}

	public String getCodDestino() {
		return codDestino;
	}

	public void setCodDestino(String codDestino) {
		this.codDestino = codDestino;
	}

	public String getCuotasProp() {
		return cuotasProp;
	}

	public void setCuotasProp(String cuotasProp) {
		this.cuotasProp = cuotasProp;
	}

	public String getOficTitular() {
		return oficTitular;
	}

	public void setOficTitular(String oficTitular) {
		this.oficTitular = oficTitular;
	}

	public String getOficElevadora() {
		return oficElevadora;
	}

	public void setOficElevadora(String oficElevadora) {
		this.oficElevadora = oficElevadora;
	}

	public String getOficPresentadora() {
		return oficPresentadora;
	}

	public void setOficPresentadora(String oficPresentadora) {
		this.oficPresentadora = oficPresentadora;
	}

	public BigDecimal getMontoSolic() {
		return montoSolic;
	}

	public void setMontoSolic(BigDecimal montoSolic) {
		this.montoSolic = montoSolic;
	}

	public String getCccVin() {
		return cccVin;
	}

	public void setCccVin(String cccVin) {
		this.cccVin = cccVin;
	}

	public String getCccAbo() {
		return cccAbo;
	}

	public void setCccAbo(String cccAbo) {
		this.cccAbo = cccAbo;
	}

	public String getNroCliente() {
		return nroCliente;
	}

	public void setNroCliente(String nroCliente) {
		this.nroCliente = nroCliente;
	}

	public String getNroCotitular1() {
		return nroCotitular1;
	}

	public void setNroCotitular1(String nroCotitular1) {
		this.nroCotitular1 = nroCotitular1;
	}

	public String getNroCotitular2() {
		return nroCotitular2;
	}

	public void setNroCotitular2(String nroCotitular2) {
		this.nroCotitular2 = nroCotitular2;
	}

	public String getNroCotitular3() {
		return nroCotitular3;
	}

	public void setNroCotitular3(String nroCotitular3) {
		this.nroCotitular3 = nroCotitular3;
	}

	public String getNroCotitular4() {
		return nroCotitular4;
	}

	public void setNroCotitular4(String nroCotitular4) {
		this.nroCotitular4 = nroCotitular4;
	}

	public String getNroCotitular5() {
		return nroCotitular5;
	}

	public void setNroCotitular5(String nroCotitular5) {
		this.nroCotitular5 = nroCotitular5;
	}

	public String getDivisa() {
		return divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	public String getCanalVenta() {
		return canalVenta;
	}

	public void setCanalVenta(String canalVenta) {
		this.canalVenta = canalVenta;
	}

	public String getOficialventa() {
		return oficialventa;
	}

	public void setOficialventa(String oficialventa) {
		this.oficialventa = oficialventa;
	}

	public String getOficialComercial() {
		return oficialComercial;
	}

	public void setOficialComercial(String oficialComercial) {
		this.oficialComercial = oficialComercial;
	}

	public String getCodInstrumto() {
		return codInstrumto;
	}

	public void setCodInstrumto(String codInstrumto) {
		this.codInstrumto = codInstrumto;
	}

	public String getFecAprobacion() {
		return fecAprobacion;
	}

	public void setFecAprobacion(String fecAprobacion) {
		this.fecAprobacion = fecAprobacion;
	}

	public String getFecFormalizacion() {
		return fecFormalizacion;
	}

	public void setFecFormalizacion(String fecFormalizacion) {
		this.fecFormalizacion = fecFormalizacion;
	}

	public String getFecValor() {
		return fecValor;
	}

	public void setFecValor(String fecValor) {
		this.fecValor = fecValor;
	}

	public String getFecPrimerVto() {
		return fecPrimerVto;
	}

	public void setFecPrimerVto(String fecPrimerVto) {
		this.fecPrimerVto = fecPrimerVto;
	}

	public String getDiaVto() {
		return diaVto;
	}

	public void setDiaVto(String diaVto) {
		this.diaVto = diaVto;
	}

	public String getTipTasa() {
		return tipTasa;
	}

	public void setTipTasa(String tipTasa) {
		this.tipTasa = tipTasa;
	}

	public String getIndNegociabil() {
		return indNegociabil;
	}

	public void setIndNegociabil(String indNegociabil) {
		this.indNegociabil = indNegociabil;
	}

	public BigDecimal getTipAplicacion() {
		return tipAplicacion;
	}

	public void setTipAplicacion(BigDecimal tipAplicacion) {
		this.tipAplicacion = tipAplicacion;
	}

	public String getClarevis() {
		return clarevis;
	}

	public void setClarevis(String clarevis) {
		this.clarevis = clarevis;
	}

	public String getModAcreditac() {
		return modAcreditac;
	}

	public void setModAcreditac(String modAcreditac) {
		this.modAcreditac = modAcreditac;
	}

	public String getTipCondicAlternat() {
		return tipCondicAlternat;
	}

	public void setTipCondicAlternat(String tipCondicAlternat) {
		this.tipCondicAlternat = tipCondicAlternat;
	}

	public String getCodCondAlternat() {
		return codCondAlternat;
	}

	public void setCodCondAlternat(String codCondAlternat) {
		this.codCondAlternat = codCondAlternat;
	}

	public String getCodAgen() {
		return codAgen;
	}

	public void setCodAgen(String codAgen) {
		this.codAgen = codAgen;
	}

	public String getIndContSeg() {
		return indContSeg;
	}

	public void setIndContSeg(String indContSeg) {
		this.indContSeg = indContSeg;
	}

	public String getCodCiaSeg() {
		return codCiaSeg;
	}

	public void setCodCiaSeg(String codCiaSeg) {
		this.codCiaSeg = codCiaSeg;
	}

	public String getIndBonifcta() {
		return indBonifcta;
	}

	public void setIndBonifcta(String indBonifcta) {
		this.indBonifcta = indBonifcta;
	}

	public String getCodRiesgo() {
		return codRiesgo;
	}

	public void setCodRiesgo(String codRiesgo) {
		this.codRiesgo = codRiesgo;
	}

	public String getIndIndexado() {
		return indIndexado;
	}

	public void setIndIndexado(String indIndexado) {
		this.indIndexado = indIndexado;
	}

	public String getCoeficiIndex() {
		return coeficiIndex;
	}

	public void setCoeficiIndex(String coeficiIndex) {
		this.coeficiIndex = coeficiIndex;
	}

	public String getCoeficiVisual() {
		return coeficiVisual;
	}

	public void setCoeficiVisual(String coeficiVisual) {
		this.coeficiVisual = coeficiVisual;
	}

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

}
