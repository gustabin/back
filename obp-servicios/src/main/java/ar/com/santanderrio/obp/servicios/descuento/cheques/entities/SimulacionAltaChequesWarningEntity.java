package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class SimulacionAltaChequesWarningEntity.
 */
@Record
public class SimulacionAltaChequesWarningEntity {

    /** The header trama. */
    @Field
    private String headerTrama;

    /** Codigo retorno extendido. */
    @Field(handlerName = "codigoRetornoExtendidoHandler")
    private String codigoRetornoExtendido;

    /** The error system. */
    @Field
    private String errorSystem;
    
    @Field
    private Integer cantErrors;

    /** The error message. */
    @Segment(occursRef = "cantErrors")
	private List<MensajeErrorEntity> errorMessages;

    /** The tipo cuenta. */
    @Field
    private String tipoCuenta;
    
    /** The suc cuenta. */
    @Field
    private String sucCuenta;
    
    /** The nro cuenta. */
    @Field
    private String nroCuenta;
    
    /** The nro tramite. */
    @Field
    private String nroTramite;
    
    /** The disponible. */
    @Field
    private String disponible;
    
    /** The tasa aplicada. */
    @Field
    private String tasaAplicada;
    
    /** The tasa tem. */
    @Field
    private String tasaTem;
    
    /** The tasa cft. */
    @Field
    private String tasaCft;
    
    /** The marca garantia. */
    @Field
    private String marcaGarantia;
    
    /** The marca afip. */
    @Field
    private String marcaAfip;
    
    /** The marca clte vinc. */
    @Field
    private String marcaClteVinc;
    
    /** The marca bcra. */
    @Field
    private String marcaBcra;
    
    /** The marca producto. */
    @Field
    private String marcaProducto;
    
    /** The marca tasa. */
    @Field
    private String marcaTasa;
    
    /** The cant chq acep. */
    @Field
    private String cantChqAcep;
    
    /** The cant chq rech. */
    @Field
    private String cantChqRech;
    
    /** The sum impu chq acep. */
    @Field
    private String sumImpuChqAcep;
    
    /** The sum com chq acep. */
    @Field
    private String sumComChqAcep;
    
    /** The sum int chq acep. */
    @Field
    private String sumIntChqAcep;
    
    /** The sum cf chq acep. */
    @Field
    private String sumCfChqAcep;
    
    /** The sum impues chq acep. */
    @Field
    private String sumImpuesChqAcep;
    
    /** The imp neto chq acep. */
    @Field
    private String impNetoChqAcep;
    
    /** The comision adic. */
    @Field
    private String comisionAdic;
    
    /** The imp total acred. */
    @Field
    private String impTotalAcred;
    
    /** The imp total chq rech. */
    @Field
    private String impTotalChqRech;
    
    /** The est tramite. */
    @Field
    private String estTramite;
    
    /** The desc est tramite. */
    @Field
    private String descEstTramite;
    
    /** The fecha recalculo. */
    @Field
    private String fechaRecalculo;
    
    /** The mot rech cliente. */
    @Field
    private String motRechCliente;
    
    /** The est tramite det. */
    @Field
    private String estTramiteDet;
    
    /** The desc est tramite det. */
    @Field
    private String descEstTramiteDet;
    
    /** The Mot rech cli amig. */
    @Field
    private String MotRechCliAmig;
    
    /** The cantidad registros. */
    @Field
    private Long cantidadRegistros;
    
	/** The operaciones. */
	@Segment(occursRef = "cantidadRegistros")
    private List<SimulacionAltaChequeEntity> operaciones = new ArrayList<SimulacionAltaChequeEntity>();

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
	 * Gets the error system.
	 *
	 * @return the error system
	 */
	public String getErrorSystem() {
		return errorSystem;
	}

	/**
	 * Sets the error system.
	 *
	 * @param errorSystem the new error system
	 */
	public void setErrorSystem(String errorSystem) {
		this.errorSystem = errorSystem;
	}

	/**
	 * Gets the cant errors.
	 *
	 * @return the cant errors
	 */
	public Integer getCantErrors() {
		return cantErrors;
	}

	/**
	 * Sets the cant errors.
	 *
	 * @param cantErrors the new cant errors
	 */
	public void setCantErrors(Integer cantErrors) {
		this.cantErrors = cantErrors;
	}

	/**
	 * Gets the error messages.
	 *
	 * @return the error messages
	 */
	public List<MensajeErrorEntity> getErrorMessages() {
		return errorMessages;
	}

	/**
	 * Sets the error messages.
	 *
	 * @param errorMessages the new error messages
	 */
	public void setErrorMessages(List<MensajeErrorEntity> errorMessages) {
		this.errorMessages = errorMessages;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the suc cuenta.
	 *
	 * @return the suc cuenta
	 */
	public String getSucCuenta() {
		return sucCuenta;
	}

	/**
	 * Sets the suc cuenta.
	 *
	 * @param sucCuenta
	 *            the new suc cuenta
	 */
	public void setSucCuenta(String sucCuenta) {
		this.sucCuenta = sucCuenta;
	}

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the nro tramite.
	 *
	 * @return the nro tramite
	 */
	public String getNroTramite() {
		return nroTramite;
	}

	/**
	 * Sets the nro tramite.
	 *
	 * @param nroTramite
	 *            the new nro tramite
	 */
	public void setNroTramite(String nroTramite) {
		this.nroTramite = nroTramite;
	}

	/**
	 * Gets the disponible.
	 *
	 * @return the disponible
	 */
	public String getDisponible() {
		return disponible;
	}

	/**
	 * Sets the disponible.
	 *
	 * @param disponible
	 *            the new disponible
	 */
	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}

	/**
	 * Gets the tasa aplicada.
	 *
	 * @return the tasa aplicada
	 */
	public String getTasaAplicada() {
		return tasaAplicada;
	}

	/**
	 * Sets the tasa aplicada.
	 *
	 * @param tasaAplicada
	 *            the new tasa aplicada
	 */
	public void setTasaAplicada(String tasaAplicada) {
		this.tasaAplicada = tasaAplicada;
	}

	/**
	 * Gets the tasa tem.
	 *
	 * @return the tasa tem
	 */
	public String getTasaTem() {
		return tasaTem;
	}

	/**
	 * Sets the tasa tem.
	 *
	 * @param tasaTem
	 *            the new tasa tem
	 */
	public void setTasaTem(String tasaTem) {
		this.tasaTem = tasaTem;
	}

	/**
	 * Gets the tasa cft.
	 *
	 * @return the tasa cft
	 */
	public String getTasaCft() {
		return tasaCft;
	}

	/**
	 * Sets the tasa cft.
	 *
	 * @param tasaCft
	 *            the new tasa cft
	 */
	public void setTasaCft(String tasaCft) {
		this.tasaCft = tasaCft;
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
	 * Gets the marca bcra.
	 *
	 * @return the marca bcra
	 */
	public String getMarcaBcra() {
		return marcaBcra;
	}

	/**
	 * Sets the marca bcra.
	 *
	 * @param marcaBcra
	 *            the new marca bcra
	 */
	public void setMarcaBcra(String marcaBcra) {
		this.marcaBcra = marcaBcra;
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
	 * Gets the cant chq rech.
	 *
	 * @return the cant chq rech
	 */
	public String getCantChqRech() {
		return cantChqRech;
	}

	/**
	 * Sets the cant chq rech.
	 *
	 * @param cantChqRech
	 *            the new cant chq rech
	 */
	public void setCantChqRech(String cantChqRech) {
		this.cantChqRech = cantChqRech;
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
	 * Gets the sum impues chq acep.
	 *
	 * @return the sum impues chq acep
	 */
	public String getSumImpuesChqAcep() {
		return sumImpuesChqAcep;
	}

	/**
	 * Sets the sum impues chq acep.
	 *
	 * @param sumImpuesChqAcep
	 *            the new sum impues chq acep
	 */
	public void setSumImpuesChqAcep(String sumImpuesChqAcep) {
		this.sumImpuesChqAcep = sumImpuesChqAcep;
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
	 * Gets the imp total chq rech.
	 *
	 * @return the imp total chq rech
	 */
	public String getImpTotalChqRech() {
		return impTotalChqRech;
	}

	/**
	 * Sets the imp total chq rech.
	 *
	 * @param impTotalChqRech
	 *            the new imp total chq rech
	 */
	public void setImpTotalChqRech(String impTotalChqRech) {
		this.impTotalChqRech = impTotalChqRech;
	}

	/**
	 * Gets the est tramite.
	 *
	 * @return the est tramite
	 */
	public String getEstTramite() {
		return estTramite;
	}

	/**
	 * Sets the est tramite.
	 *
	 * @param estTramite
	 *            the new est tramite
	 */
	public void setEstTramite(String estTramite) {
		this.estTramite = estTramite;
	}

	/**
	 * Gets the desc est tramite.
	 *
	 * @return the desc est tramite
	 */
	public String getDescEstTramite() {
		return descEstTramite;
	}

	/**
	 * Sets the desc est tramite.
	 *
	 * @param descEstTramite
	 *            the new desc est tramite
	 */
	public void setDescEstTramite(String descEstTramite) {
		this.descEstTramite = descEstTramite;
	}

	/**
	 * Gets the fecha recalculo.
	 *
	 * @return the fecha recalculo
	 */
	public String getFechaRecalculo() {
		return fechaRecalculo;
	}

	/**
	 * Sets the fecha recalculo.
	 *
	 * @param fechaRecalculo
	 *            the new fecha recalculo
	 */
	public void setFechaRecalculo(String fechaRecalculo) {
		this.fechaRecalculo = fechaRecalculo;
	}

	/**
	 * Gets the mot rech cliente.
	 *
	 * @return the mot rech cliente
	 */
	public String getMotRechCliente() {
		return motRechCliente;
	}

	/**
	 * Sets the mot rech cliente.
	 *
	 * @param motRechCliente
	 *            the new mot rech cliente
	 */
	public void setMotRechCliente(String motRechCliente) {
		this.motRechCliente = motRechCliente;
	}

	/**
	 * Gets the est tramite det.
	 *
	 * @return the est tramite det
	 */
	public String getEstTramiteDet() {
		return estTramiteDet;
	}

	/**
	 * Sets the est tramite det.
	 *
	 * @param estTramiteDet
	 *            the new est tramite det
	 */
	public void setEstTramiteDet(String estTramiteDet) {
		this.estTramiteDet = estTramiteDet;
	}

	/**
	 * Gets the desc est tramite det.
	 *
	 * @return the desc est tramite det
	 */
	public String getDescEstTramiteDet() {
		return descEstTramiteDet;
	}

	/**
	 * Sets the desc est tramite det.
	 *
	 * @param descEstTramiteDet
	 *            the new desc est tramite det
	 */
	public void setDescEstTramiteDet(String descEstTramiteDet) {
		this.descEstTramiteDet = descEstTramiteDet;
	}

	/**
	 * Gets the mot rech cli amig.
	 *
	 * @return the mot rech cli amig
	 */
	public String getMotRechCliAmig() {
		return MotRechCliAmig;
	}

	/**
	 * Sets the mot rech cli amig.
	 *
	 * @param motRechCliAmig
	 *            the new mot rech cli amig
	 */
	public void setMotRechCliAmig(String motRechCliAmig) {
		MotRechCliAmig = motRechCliAmig;
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
	public List<SimulacionAltaChequeEntity> getOperaciones() {
		return operaciones;
	}

	/**
	 * Sets the operaciones.
	 *
	 * @param operaciones
	 *            the new operaciones
	 */
	public void setOperaciones(List<SimulacionAltaChequeEntity> operaciones) {
		this.operaciones = operaciones;
	}
	
	
	
}
