/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class DeudaPrestamoEntity.
 */
@Record
public class DeudaPrestamoEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The ceros. */
	@Field
	private String ceros;

	/** The divisa. */
	@Field
	private String divisa;

	/** The nbdivic. */
	@Field
	private String nbdivic;

	/** The sitduob. */
	@Field
	private String sitduob;

	/** The fecsitob. */
	@Field
	private String fecsitob;

	/** The sitdeusu. */
	@Field
	private String sitdeusu;

	/** The fecsitsu. */
	@Field
	private String fecsitsu;

	/** The tipoapli int. */
	@Field
	private String tipoapliInt;

	/** The tot cap venc. */
	@Field
	private String totCapVenc;

	/** The tot int ord venc. */
	@Field
	private String totIntOrdVenc;

	/** The tot comi venc. */
	@Field
	private String totComiVenc;

	/** The tot gas venc. */
	@Field
	private String totGasVenc;

	/** The tot seg venc. */
	@Field
	private String totSegVenc;

	/** The tot impt venc. */
	@Field
	private String totImptVenc;

	/** The tot imp comcobex venc. */
	@Field
	private String totImpComcobexVenc;

	/** The tot imp mor venc. */
	@Field
	private String totImpMorVenc;

	/** The tot imp cps venc. */
	@Field
	private String totImpCpsVenc;

	/** The tot deuda venc. */
	@Field
	private String totDeudaVenc;

	/** The salteor. */
	@Field
	private String salteor;

	/** The saldo ajuste. */
	@Field
	private String saldoAjuste;

	/** The capinire. */
	@Field
	private String capinire;

	/** The intinire. */
	@Field
	private String intinire;

	/** The cominire. */
	@Field
	private String cominire;

	/** The gasinire. */
	@Field
	private String gasinire;

	/** The seginire. */
	@Field
	private String seginire;

	/** The impinire. */
	@Field
	private String impinire;

	/** The tot deuda no venc. */
	@Field
	private String totDeudaNoVenc;

	/** The cantidad ocurrencias. */
	@Field()
	private Long cantidadOcurrencias;

	/** The lista repeticiones. */
	@Segment(occursRef = "cantidadOcurrencias")
	private List<CuotaPrestamoEntity> listaRepeticiones = new ArrayList<CuotaPrestamoEntity>();

	/** The nro comprobante. */
	private String nroComprobante;

	/** The fecha. */
	private String fecha;

	/** The codigo retorno extendido. */
	private String codigoRetornoExtendido;

	/**
	 * Gets the header trama.
	 *
	 * @return the header trama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the new header trama
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigo retorno extendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the new codigo retorno extendido
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the ceros.
	 *
	 * @return the ceros
	 */
	public String getCeros() {
		return ceros;
	}

	/**
	 * Sets the ceros.
	 *
	 * @param ceros
	 *            the new ceros
	 */
	public void setCeros(String ceros) {
		this.ceros = ceros;
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
	 *            the new divisa
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the nbdivic.
	 *
	 * @return the nbdivic
	 */
	public String getNbdivic() {
		return nbdivic;
	}

	/**
	 * Sets the nbdivic.
	 *
	 * @param nbdivic
	 *            the new nbdivic
	 */
	public void setNbdivic(String nbdivic) {
		this.nbdivic = nbdivic;
	}

	/**
	 * Gets the sitduob.
	 *
	 * @return the sitduob
	 */
	public String getSitduob() {
		return sitduob;
	}

	/**
	 * Sets the sitduob.
	 *
	 * @param sitduob
	 *            the new sitduob
	 */
	public void setSitduob(String sitduob) {
		this.sitduob = sitduob;
	}

	/**
	 * Gets the fecsitob.
	 *
	 * @return the fecsitob
	 */
	public String getFecsitob() {
		return fecsitob;
	}

	/**
	 * Sets the fecsitob.
	 *
	 * @param fecsitob
	 *            the new fecsitob
	 */
	public void setFecsitob(String fecsitob) {
		this.fecsitob = fecsitob;
	}

	/**
	 * Gets the sitdeusu.
	 *
	 * @return the sitdeusu
	 */
	public String getSitdeusu() {
		return sitdeusu;
	}

	/**
	 * Sets the sitdeusu.
	 *
	 * @param sitdeusu
	 *            the new sitdeusu
	 */
	public void setSitdeusu(String sitdeusu) {
		this.sitdeusu = sitdeusu;
	}

	/**
	 * Gets the fecsitsu.
	 *
	 * @return the fecsitsu
	 */
	public String getFecsitsu() {
		return fecsitsu;
	}

	/**
	 * Sets the fecsitsu.
	 *
	 * @param fecsitsu
	 *            the new fecsitsu
	 */
	public void setFecsitsu(String fecsitsu) {
		this.fecsitsu = fecsitsu;
	}

	/**
	 * Gets the tipoapli int.
	 *
	 * @return the tipoapli int
	 */
	public String getTipoapliInt() {
		return tipoapliInt;
	}

	/**
	 * Sets the tipoapli int.
	 *
	 * @param tipoapliInt
	 *            the new tipoapli int
	 */
	public void setTipoapliInt(String tipoapliInt) {
		this.tipoapliInt = tipoapliInt;
	}

	/**
	 * Gets the tot cap venc.
	 *
	 * @return the tot cap venc
	 */
	public String getTotCapVenc() {
		return totCapVenc;
	}

	/**
	 * Sets the tot cap venc.
	 *
	 * @param totCapVenc
	 *            the new tot cap venc
	 */
	public void setTotCapVenc(String totCapVenc) {
		this.totCapVenc = totCapVenc;
	}

	/**
	 * Gets the tot int ord venc.
	 *
	 * @return the tot int ord venc
	 */
	public String getTotIntOrdVenc() {
		return totIntOrdVenc;
	}

	/**
	 * Sets the tot int ord venc.
	 *
	 * @param totIntOrdVenc
	 *            the new tot int ord venc
	 */
	public void setTotIntOrdVenc(String totIntOrdVenc) {
		this.totIntOrdVenc = totIntOrdVenc;
	}

	/**
	 * Gets the tot comi venc.
	 *
	 * @return the tot comi venc
	 */
	public String getTotComiVenc() {
		return totComiVenc;
	}

	/**
	 * Sets the tot comi venc.
	 *
	 * @param totComiVenc
	 *            the new tot comi venc
	 */
	public void setTotComiVenc(String totComiVenc) {
		this.totComiVenc = totComiVenc;
	}

	/**
	 * Gets the tot gas venc.
	 *
	 * @return the tot gas venc
	 */
	public String getTotGasVenc() {
		return totGasVenc;
	}

	/**
	 * Sets the tot gas venc.
	 *
	 * @param totGasVenc
	 *            the new tot gas venc
	 */
	public void setTotGasVenc(String totGasVenc) {
		this.totGasVenc = totGasVenc;
	}

	/**
	 * Gets the tot seg venc.
	 *
	 * @return the tot seg venc
	 */
	public String getTotSegVenc() {
		return totSegVenc;
	}

	/**
	 * Sets the tot seg venc.
	 *
	 * @param totSegVenc
	 *            the new tot seg venc
	 */
	public void setTotSegVenc(String totSegVenc) {
		this.totSegVenc = totSegVenc;
	}

	/**
	 * Gets the tot impt venc.
	 *
	 * @return the tot impt venc
	 */
	public String getTotImptVenc() {
		return totImptVenc;
	}

	/**
	 * Sets the tot impt venc.
	 *
	 * @param totImptVenc
	 *            the new tot impt venc
	 */
	public void setTotImptVenc(String totImptVenc) {
		this.totImptVenc = totImptVenc;
	}

	/**
	 * Gets the tot imp comcobex venc.
	 *
	 * @return the tot imp comcobex venc
	 */
	public String getTotImpComcobexVenc() {
		return totImpComcobexVenc;
	}

	/**
	 * Sets the tot imp comcobex venc.
	 *
	 * @param totImpComcobexVenc
	 *            the new tot imp comcobex venc
	 */
	public void setTotImpComcobexVenc(String totImpComcobexVenc) {
		this.totImpComcobexVenc = totImpComcobexVenc;
	}

	/**
	 * Gets the tot imp mor venc.
	 *
	 * @return the tot imp mor venc
	 */
	public String getTotImpMorVenc() {
		return totImpMorVenc;
	}

	/**
	 * Sets the tot imp mor venc.
	 *
	 * @param totImpMorVenc
	 *            the new tot imp mor venc
	 */
	public void setTotImpMorVenc(String totImpMorVenc) {
		this.totImpMorVenc = totImpMorVenc;
	}

	/**
	 * Gets the tot imp cps venc.
	 *
	 * @return the tot imp cps venc
	 */
	public String getTotImpCpsVenc() {
		return totImpCpsVenc;
	}

	/**
	 * Sets the tot imp cps venc.
	 *
	 * @param totImpCpsVenc
	 *            the new tot imp cps venc
	 */
	public void setTotImpCpsVenc(String totImpCpsVenc) {
		this.totImpCpsVenc = totImpCpsVenc;
	}

	/**
	 * Gets the tot deuda venc.
	 *
	 * @return the tot deuda venc
	 */
	public String getTotDeudaVenc() {
		return totDeudaVenc;
	}

	/**
	 * Sets the tot deuda venc.
	 *
	 * @param totDeudaVenc
	 *            the new tot deuda venc
	 */
	public void setTotDeudaVenc(String totDeudaVenc) {
		this.totDeudaVenc = totDeudaVenc;
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
	 * Gets the saldo ajuste.
	 *
	 * @return the saldo ajuste
	 */
	public String getSaldoAjuste() {
		return saldoAjuste;
	}

	/**
	 * Sets the saldo ajuste.
	 *
	 * @param saldoAjuste
	 *            the new saldo ajuste
	 */
	public void setSaldoAjuste(String saldoAjuste) {
		this.saldoAjuste = saldoAjuste;
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
	 *            the new capinire
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
	 *            the new intinire
	 */
	public void setIntinire(String intinire) {
		this.intinire = intinire;
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
	 *            the new cominire
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
	 *            the new gasinire
	 */
	public void setGasinire(String gasinire) {
		this.gasinire = gasinire;
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
	 *            the new seginire
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
	 *            the new impinire
	 */
	public void setImpinire(String impinire) {
		this.impinire = impinire;
	}

	/**
	 * Gets the tot deuda no venc.
	 *
	 * @return the tot deuda no venc
	 */
	public String getTotDeudaNoVenc() {
		return totDeudaNoVenc;
	}

	/**
	 * Sets the tot deuda no venc.
	 *
	 * @param totDeudaNoVenc
	 *            the new tot deuda no venc
	 */
	public void setTotDeudaNoVenc(String totDeudaNoVenc) {
		this.totDeudaNoVenc = totDeudaNoVenc;
	}

	/**
	 * Gets the cantidad ocurrencias.
	 *
	 * @return the cantidad ocurrencias
	 */
	public Long getCantidadOcurrencias() {
		return cantidadOcurrencias;
	}

	/**
	 * Sets the cantidad ocurrencias.
	 *
	 * @param cantidadOcurrencias
	 *            the new cantidad ocurrencias
	 */
	public void setCantidadOcurrencias(Long cantidadOcurrencias) {
		this.cantidadOcurrencias = cantidadOcurrencias;
	}

	/**
	 * Gets the lista repeticiones.
	 *
	 * @return the lista repeticiones
	 */
	public List<CuotaPrestamoEntity> getListaRepeticiones() {
		return listaRepeticiones;
	}

	/**
	 * Sets the lista repeticiones.
	 *
	 * @param listaRepeticiones
	 *            the new lista repeticiones
	 */
	public void setListaRepeticiones(List<CuotaPrestamoEntity> listaRepeticiones) {
		this.listaRepeticiones = listaRepeticiones;
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

}
