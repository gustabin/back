/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class EfectivizacionAltaChequesEntity.
 */
@Record
public class EfectivizacionAltaChequesEntity {
	/** The header trama. */
    @Field
    private String headerTrama;

    /** Codigo retorno extendido. */
    @Field(handlerName = "codigoRetornoExtendidoHandler")
    private String codigoRetornoExtendido;
    
    /** The tipo cuenta credito. */
    @Field
    private String tipoCuentaCredito;
    
    /** The suc cuenta credito. */
    @Field
    private String sucCuentaCredito;    
    
    /** The nro cuenta credito. */
    @Field
    private String nroCuentaCredito;
    
    /** The cod est tramite final. */
    @Field
    private String codEstTramiteFinal;
    
    /** The des est tramite final. */
    @Field
    private String desEstTramiteFinal;
    
    /** The marca garantia. */
    @Field
    private String marcaGarantia;
    
    /** The marca afip. */
    @Field
    private String marcaAfip;
    
    /** The marca clte vinc. */
    @Field
    private String marcaClteVinc;
    
    /** The marca BCRA. */
    @Field
    private String marcaBCRA;
    
    /** The marca producto. */
    @Field
    private String marcaProducto;
    
    /** The marca tasa. */
    @Field
    private String marcaTasa;
    
    /** The fecha alta. */
    @Field
    private String fechaAlta;
    
    /** The divisa. */
    @Field
    private String divisa;
    
    /** The tasa aplic. */
    @Field
    private String tasaAplic;
    
    /** The tasa TEA. */
    @Field
    private String tasaTEA;
    
    /** The tasa CFT. */
    @Field
    private String tasaCFT;
    
    /** The cant chq acep. */
    @Field
    private String cantChqAcep;
    
    /** The sum imp chq acep. */
    @Field
    private String sumImpChqAcep;
    
    /** The sum com chq acep. */
    @Field
    private String sumComChqAcep;
    
    /** The sum int chq acep. */
    @Field
    private String sumIntChqAcep;

    /** The sum cf chq acep. */
    @Field
    private String sumCfChqAcep;
    
    /** The sum impu chq acep. */
    @Field
    private String sumImpuChqAcep;
    
    /** The imp neto chq acep. */
    @Field
    private String impNetoChqAcep;
    
    /** The comision adic. */
    @Field
    private String comisionAdic;
    
    /** The imp total acred. */
    @Field
    private String impTotalAcred;
    
    /** The cantidad registros. */
    @Field
    private Long cantidadRegistros;
    
	/** The operaciones. */
	@Segment(occursRef = "cantidadRegistros")
    private List<AltaChequeEntity> operaciones = new ArrayList<AltaChequeEntity>();


	/**
	 * Gets the tipo cuenta credito.
	 *
	 * @return the tipo cuenta credito
	 */
	public String getTipoCuentaCredito() {
		return tipoCuentaCredito;
	}

	/**
	 * Sets the tipo cuenta credito.
	 *
	 * @param tipoCuentaCredito
	 *            the new tipo cuenta credito
	 */
	public void setTipoCuentaCredito(String tipoCuentaCredito) {
		this.tipoCuentaCredito = tipoCuentaCredito;
	}

	/**
	 * Gets the suc cuenta credito.
	 *
	 * @return the suc cuenta credito
	 */
	public String getSucCuentaCredito() {
		return sucCuentaCredito;
	}

	/**
	 * Sets the suc cuenta credito.
	 *
	 * @param sucCuentaCredito
	 *            the new suc cuenta credito
	 */
	public void setSucCuentaCredito(String sucCuentaCredito) {
		this.sucCuentaCredito = sucCuentaCredito;
	}

	/**
	 * Gets the nro cuenta credito.
	 *
	 * @return the nro cuenta credito
	 */
	public String getNroCuentaCredito() {
		return nroCuentaCredito;
	}

	/**
	 * Sets the nro cuenta credito.
	 *
	 * @param nroCuentaCredito
	 *            the new nro cuenta credito
	 */
	public void setNroCuentaCredito(String nroCuentaCredito) {
		this.nroCuentaCredito = nroCuentaCredito;
	}

	/**
	 * Gets the cod est tramite final.
	 *
	 * @return the cod est tramite final
	 */
	public String getCodEstTramiteFinal() {
		return codEstTramiteFinal;
	}

	/**
	 * Sets the cod est tramite final.
	 *
	 * @param codEstTramiteFinal
	 *            the new cod est tramite final
	 */
	public void setCodEstTramiteFinal(String codEstTramiteFinal) {
		this.codEstTramiteFinal = codEstTramiteFinal;
	}

	/**
	 * Gets the des est tramite final.
	 *
	 * @return the des est tramite final
	 */
	public String getDesEstTramiteFinal() {
		return desEstTramiteFinal;
	}

	/**
	 * Sets the des est tramite final.
	 *
	 * @param desEstTramiteFinal
	 *            the new des est tramite final
	 */
	public void setDesEstTramiteFinal(String desEstTramiteFinal) {
		this.desEstTramiteFinal = desEstTramiteFinal;
	}

	/**
	 * Gets the marca garantia.
	 *
	 * @return the marca garantia
	 */
	public String getMarcaGarantia() {
		return marcaGarantia;
	}

	/**
	 * Sets the marca garantia.
	 *
	 * @param marcaGarantia
	 *            the new marca garantia
	 */
	public void setMarcaGarantia(String marcaGarantia) {
		this.marcaGarantia = marcaGarantia;
	}

	/**
	 * Gets the marca afip.
	 *
	 * @return the marca afip
	 */
	public String getMarcaAfip() {
		return marcaAfip;
	}

	/**
	 * Sets the marca afip.
	 *
	 * @param marcaAfip
	 *            the new marca afip
	 */
	public void setMarcaAfip(String marcaAfip) {
		this.marcaAfip = marcaAfip;
	}

	/**
	 * Gets the marca clte vinc.
	 *
	 * @return the marca clte vinc
	 */
	public String getMarcaClteVinc() {
		return marcaClteVinc;
	}

	/**
	 * Sets the marca clte vinc.
	 *
	 * @param marcaClteVinc
	 *            the new marca clte vinc
	 */
	public void setMarcaClteVinc(String marcaClteVinc) {
		this.marcaClteVinc = marcaClteVinc;
	}

	/**
	 * Gets the marca BCRA.
	 *
	 * @return the marca BCRA
	 */
	public String getMarcaBCRA() {
		return marcaBCRA;
	}

	/**
	 * Sets the marca BCRA.
	 *
	 * @param marcaBCRA
	 *            the new marca BCRA
	 */
	public void setMarcaBCRA(String marcaBCRA) {
		this.marcaBCRA = marcaBCRA;
	}

	/**
	 * Gets the marca producto.
	 *
	 * @return the marca producto
	 */
	public String getMarcaProducto() {
		return marcaProducto;
	}

	/**
	 * Sets the marca producto.
	 *
	 * @param marcaProducto
	 *            the new marca producto
	 */
	public void setMarcaProducto(String marcaProducto) {
		this.marcaProducto = marcaProducto;
	}

	/**
	 * Gets the marca tasa.
	 *
	 * @return the marca tasa
	 */
	public String getMarcaTasa() {
		return marcaTasa;
	}

	/**
	 * Sets the marca tasa.
	 *
	 * @param marcaTasa
	 *            the new marca tasa
	 */
	public void setMarcaTasa(String marcaTasa) {
		this.marcaTasa = marcaTasa;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fecha alta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the new fecha alta
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
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
	 * Gets the tasa aplic.
	 *
	 * @return the tasa aplic
	 */
	public String getTasaAplic() {
		return tasaAplic;
	}

	/**
	 * Sets the tasa aplic.
	 *
	 * @param tasaAplic
	 *            the new tasa aplic
	 */
	public void setTasaAplic(String tasaAplic) {
		this.tasaAplic = tasaAplic;
	}

	/**
	 * Gets the tasa TEA.
	 *
	 * @return the tasa TEA
	 */
	public String getTasaTEA() {
		return tasaTEA;
	}

	/**
	 * Sets the tasa TEA.
	 *
	 * @param tasaTEA
	 *            the new tasa TEA
	 */
	public void setTasaTEA(String tasaTEA) {
		this.tasaTEA = tasaTEA;
	}

	/**
	 * Gets the tasa CFT.
	 *
	 * @return the tasa CFT
	 */
	public String getTasaCFT() {
		return tasaCFT;
	}

	/**
	 * Sets the tasa CFT.
	 *
	 * @param tasaCFT
	 *            the new tasa CFT
	 */
	public void setTasaCFT(String tasaCFT) {
		this.tasaCFT = tasaCFT;
	}

	/**
	 * Gets the cant chq acep.
	 *
	 * @return the cant chq acep
	 */
	public String getCantChqAcep() {
		return cantChqAcep;
	}

	/**
	 * Sets the cant chq acep.
	 *
	 * @param cantChqAcep
	 *            the new cant chq acep
	 */
	public void setCantChqAcep(String cantChqAcep) {
		this.cantChqAcep = cantChqAcep;
	}

	/**
	 * Gets the sum imp chq acep.
	 *
	 * @return the sum imp chq acep
	 */
	public String getSumImpChqAcep() {
		return sumImpChqAcep;
	}

	/**
	 * Sets the sum imp chq acep.
	 *
	 * @param sumImpChqAcep
	 *            the new sum imp chq acep
	 */
	public void setSumImpChqAcep(String sumImpChqAcep) {
		this.sumImpChqAcep = sumImpChqAcep;
	}

	/**
	 * Gets the sum com chq acep.
	 *
	 * @return the sum com chq acep
	 */
	public String getSumComChqAcep() {
		return sumComChqAcep;
	}

	/**
	 * Sets the sum com chq acep.
	 *
	 * @param sumComChqAcep
	 *            the new sum com chq acep
	 */
	public void setSumComChqAcep(String sumComChqAcep) {
		this.sumComChqAcep = sumComChqAcep;
	}

	/**
	 * Gets the sum int chq acep.
	 *
	 * @return the sum int chq acep
	 */
	public String getSumIntChqAcep() {
		return sumIntChqAcep;
	}

	/**
	 * Sets the sum int chq acep.
	 *
	 * @param sumIntChqAcep
	 *            the new sum int chq acep
	 */
	public void setSumIntChqAcep(String sumIntChqAcep) {
		this.sumIntChqAcep = sumIntChqAcep;
	}

	/**
	 * Gets the sum cf chq acep.
	 *
	 * @return the sum cf chq acep
	 */
	public String getSumCfChqAcep() {
		return sumCfChqAcep;
	}

	/**
	 * Sets the sum cf chq acep.
	 *
	 * @param sumCfChqAcep
	 *            the new sum cf chq acep
	 */
	public void setSumCfChqAcep(String sumCfChqAcep) {
		this.sumCfChqAcep = sumCfChqAcep;
	}

	/**
	 * Gets the sum impu chq acep.
	 *
	 * @return the sum impu chq acep
	 */
	public String getSumImpuChqAcep() {
		return sumImpuChqAcep;
	}

	/**
	 * Sets the sum impu chq acep.
	 *
	 * @param sumImpuChqAcep
	 *            the new sum impu chq acep
	 */
	public void setSumImpuChqAcep(String sumImpuChqAcep) {
		this.sumImpuChqAcep = sumImpuChqAcep;
	}

	/**
	 * Gets the imp neto chq acep.
	 *
	 * @return the imp neto chq acep
	 */
	public String getImpNetoChqAcep() {
		return impNetoChqAcep;
	}

	/**
	 * Sets the imp neto chq acep.
	 *
	 * @param impNetoChqAcep
	 *            the new imp neto chq acep
	 */
	public void setImpNetoChqAcep(String impNetoChqAcep) {
		this.impNetoChqAcep = impNetoChqAcep;
	}

	/**
	 * Gets the comision adic.
	 *
	 * @return the comision adic
	 */
	public String getComisionAdic() {
		return comisionAdic;
	}

	/**
	 * Sets the comision adic.
	 *
	 * @param comisionAdic
	 *            the new comision adic
	 */
	public void setComisionAdic(String comisionAdic) {
		this.comisionAdic = comisionAdic;
	}

	/**
	 * Gets the imp total acred.
	 *
	 * @return the imp total acred
	 */
	public String getImpTotalAcred() {
		return impTotalAcred;
	}

	/**
	 * Sets the imp total acred.
	 *
	 * @param impTotalAcred
	 *            the new imp total acred
	 */
	public void setImpTotalAcred(String impTotalAcred) {
		this.impTotalAcred = impTotalAcred;
	}

	/**
	 * Gets the cantidad registros.
	 *
	 * @return the cantidad registros
	 */
	public Long getCantidadRegistros() {
		return cantidadRegistros;
	}

	/**
	 * Sets the cantidad registros.
	 *
	 * @param cantidadRegistros
	 *            the new cantidad registros
	 */
	public void setCantidadRegistros(Long cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}

	/**
	 * Gets the operaciones.
	 *
	 * @return the operaciones
	 */
	public List<AltaChequeEntity> getOperaciones() {
		return operaciones;
	}

	/**
	 * Sets the operaciones.
	 *
	 * @param operaciones
	 *            the new operaciones
	 */
	public void setOperaciones(List<AltaChequeEntity> operaciones) {
		this.operaciones = operaciones;
	}

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
    
    
}
