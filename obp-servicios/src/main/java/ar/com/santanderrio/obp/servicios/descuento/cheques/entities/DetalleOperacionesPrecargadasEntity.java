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
 * The Class DetalleOperacionesPrecargadasEntity.
 */
@Record
public class DetalleOperacionesPrecargadasEntity {

	 /** The header trama. */
    @Field
    private String headerTrama;

    /** Codigo retorno extendido. */
    @Field(handlerName = "codigoRetornoExtendidoHandler")
    private String codigoRetornoExtendido;
    
    /** The ind rellamada. */
    @Field
    private String indRellamada;
    
    /** The tipo cuenta credito. */
    @Field
    private String tipoCuentaCredito;
    
    /** The suc cuenta credito. */
    @Field
    private String sucCuentaCredito;
    
    /** The nro cuenta credito. */
    @Field
    private String nroCuentaCredito;
    
    /** The estado tramite. */
    @Field
    private String estadoTramite;
    
    /** The desc est tramite. */
    @Field
    private String descEstTramite;
    
    /** The divisa. */
    @Field
    private String divisa;
    
    /** The apellido clte. */
    @Field
    private String apellidoClte;
    
    /** The nombre clte. */
    @Field
    private String nombreClte;
    
    /** The fec recalculo. */
    @Field
    private String fecRecalculo;
    
    /** The form 487. */
    @Field
    private String form487;
    
    /** The mot rech cliente. */
    @Field
    private String motRechCliente;
    
    /** The calificado. */
    @Field
    private String calificado;
    
    /** The linea. */
    @Field
    private String linea;
    
    /** The disponible. */
    @Field
    private String disponible;
    
    /** The tasa maxima. */
    @Field
    private String tasaMaxima;
    
    /** The tasa minima. */
    @Field
    private String tasaMinima;
    
    /** The tasa sugerida. */
    @Field
    private String tasaSugerida;
    
    /** The tasa aplicada. */
    @Field
    private String tasaAplicada;
    
    /** The tasa tea. */
    @Field
    private String tasaTea;
    
    /** The tasa CFT. */
    @Field
    private String tasaCFT;
    
    /** The destino fondos. */
    @Field
    private String destinoFondos;
    
    /** The marca garantia. */
    @Field
    private String marcaGarantia;
    
    /** The marca afip. */
    @Field
    private String marcaAfip;
    
    /** The marca CLTE vinc. */
    @Field
    private String marcaCLTEVinc;
    
    /** The marca BCRA. */
    @Field
    private String marcaBCRA;
    
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
    
    /** The imp comision adic. */
    @Field
    private String impComisionAdic;
    
    /** The imp total acred. */
    @Field
    private String impTotalAcred;
    
    /** The imp total rech. */
    @Field
    private String impTotalRech;
    
    /** The canal origen tramite. */
    @Field
    private String canalOrigenTramite;
    
    /** The est tramite det. */
    @Field
    private String estTramiteDet;
    
    /** The desc est tramite det. */
    @Field
    private String descEstTramiteDet;
    
    /** The marca ret gerente. */
    @Field
    private String marcaRetGerente;
    
    /** The marca ret producto. */
    @Field
    private String marcaRetProducto;
    
    /** The marca devoluc SS. */
    @Field
    private String marcaDevolucSS;
    
    /** The Fecha de alta del tramite. */
    @Field
    private String FechaDeAltaDelTramite;
    
    /** The Mot rech cli amig. */
    @Field
    private String MotRechCliAmig;
    
    /** The cantidad registros. */
    @Field
    private Long cantidadRegistros;
    
	/** The operaciones. */
	@Segment(occursRef = "cantidadRegistros")
    private List<DetalleOperacionPrecargadaEntity> operaciones = new ArrayList<DetalleOperacionPrecargadaEntity>();

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
	 * Gets the ind rellamada.
	 *
	 * @return the ind rellamada
	 */
	public String getIndRellamada() {
		return indRellamada;
	}

	/**
	 * Sets the ind rellamada.
	 *
	 * @param indRellamada
	 *            the new ind rellamada
	 */
	public void setIndRellamada(String indRellamada) {
		this.indRellamada = indRellamada;
	}

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
	 * Gets the estado tramite.
	 *
	 * @return the estado tramite
	 */
	public String getEstadoTramite() {
		return estadoTramite;
	}

	/**
	 * Sets the estado tramite.
	 *
	 * @param estadoTramite
	 *            the new estado tramite
	 */
	public void setEstadoTramite(String estadoTramite) {
		this.estadoTramite = estadoTramite;
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
	 * Gets the apellido clte.
	 *
	 * @return the apellido clte
	 */
	public String getApellidoClte() {
		return apellidoClte;
	}

	/**
	 * Sets the apellido clte.
	 *
	 * @param apellidoClte
	 *            the new apellido clte
	 */
	public void setApellidoClte(String apellidoClte) {
		this.apellidoClte = apellidoClte;
	}

	/**
	 * Gets the nombre clte.
	 *
	 * @return the nombre clte
	 */
	public String getNombreClte() {
		return nombreClte;
	}

	/**
	 * Sets the nombre clte.
	 *
	 * @param nombreClte
	 *            the new nombre clte
	 */
	public void setNombreClte(String nombreClte) {
		this.nombreClte = nombreClte;
	}

	/**
	 * Gets the fec recalculo.
	 *
	 * @return the fec recalculo
	 */
	public String getFecRecalculo() {
		return fecRecalculo;
	}

	/**
	 * Sets the fec recalculo.
	 *
	 * @param fecRecalculo
	 *            the new fec recalculo
	 */
	public void setFecRecalculo(String fecRecalculo) {
		this.fecRecalculo = fecRecalculo;
	}

	/**
	 * Gets the form 487.
	 *
	 * @return the form 487
	 */
	public String getForm487() {
		return form487;
	}

	/**
	 * Sets the form 487.
	 *
	 * @param form487
	 *            the new form 487
	 */
	public void setForm487(String form487) {
		this.form487 = form487;
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
	 * Gets the calificado.
	 *
	 * @return the calificado
	 */
	public String getCalificado() {
		return calificado;
	}

	/**
	 * Sets the calificado.
	 *
	 * @param calificado
	 *            the new calificado
	 */
	public void setCalificado(String calificado) {
		this.calificado = calificado;
	}

	/**
	 * Gets the linea.
	 *
	 * @return the linea
	 */
	public String getLinea() {
		return linea;
	}

	/**
	 * Sets the linea.
	 *
	 * @param linea
	 *            the new linea
	 */
	public void setLinea(String linea) {
		this.linea = linea;
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
	 * Gets the tasa maxima.
	 *
	 * @return the tasa maxima
	 */
	public String getTasaMaxima() {
		return tasaMaxima;
	}

	/**
	 * Sets the tasa maxima.
	 *
	 * @param tasaMaxima
	 *            the new tasa maxima
	 */
	public void setTasaMaxima(String tasaMaxima) {
		this.tasaMaxima = tasaMaxima;
	}

	/**
	 * Gets the tasa minima.
	 *
	 * @return the tasa minima
	 */
	public String getTasaMinima() {
		return tasaMinima;
	}

	/**
	 * Sets the tasa minima.
	 *
	 * @param tasaMinima
	 *            the new tasa minima
	 */
	public void setTasaMinima(String tasaMinima) {
		this.tasaMinima = tasaMinima;
	}

	/**
	 * Gets the tasa sugerida.
	 *
	 * @return the tasa sugerida
	 */
	public String getTasaSugerida() {
		return tasaSugerida;
	}

	/**
	 * Sets the tasa sugerida.
	 *
	 * @param tasaSugerida
	 *            the new tasa sugerida
	 */
	public void setTasaSugerida(String tasaSugerida) {
		this.tasaSugerida = tasaSugerida;
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
	 * Gets the tasa tea.
	 *
	 * @return the tasa tea
	 */
	public String getTasaTea() {
		return tasaTea;
	}

	/**
	 * Sets the tasa tea.
	 *
	 * @param tasaTea
	 *            the new tasa tea
	 */
	public void setTasaTea(String tasaTea) {
		this.tasaTea = tasaTea;
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
	 * Gets the destino fondos.
	 *
	 * @return the destino fondos
	 */
	public String getDestinoFondos() {
		return destinoFondos;
	}

	/**
	 * Sets the destino fondos.
	 *
	 * @param destinoFondos
	 *            the new destino fondos
	 */
	public void setDestinoFondos(String destinoFondos) {
		this.destinoFondos = destinoFondos;
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
	 * Gets the marca CLTE vinc.
	 *
	 * @return the marca CLTE vinc
	 */
	public String getMarcaCLTEVinc() {
		return marcaCLTEVinc;
	}

	/**
	 * Sets the marca CLTE vinc.
	 *
	 * @param marcaCLTEVinc
	 *            the new marca CLTE vinc
	 */
	public void setMarcaCLTEVinc(String marcaCLTEVinc) {
		this.marcaCLTEVinc = marcaCLTEVinc;
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
	 * Gets the imp comision adic.
	 *
	 * @return the imp comision adic
	 */
	public String getImpComisionAdic() {
		return impComisionAdic;
	}

	/**
	 * Sets the imp comision adic.
	 *
	 * @param impComisionAdic
	 *            the new imp comision adic
	 */
	public void setImpComisionAdic(String impComisionAdic) {
		this.impComisionAdic = impComisionAdic;
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
	 * Gets the imp total rech.
	 *
	 * @return the imp total rech
	 */
	public String getImpTotalRech() {
		return impTotalRech;
	}

	/**
	 * Sets the imp total rech.
	 *
	 * @param impTotalRech
	 *            the new imp total rech
	 */
	public void setImpTotalRech(String impTotalRech) {
		this.impTotalRech = impTotalRech;
	}

	/**
	 * Gets the canal origen tramite.
	 *
	 * @return the canal origen tramite
	 */
	public String getCanalOrigenTramite() {
		return canalOrigenTramite;
	}

	/**
	 * Sets the canal origen tramite.
	 *
	 * @param canalOrigenTramite
	 *            the new canal origen tramite
	 */
	public void setCanalOrigenTramite(String canalOrigenTramite) {
		this.canalOrigenTramite = canalOrigenTramite;
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
	 * Gets the marca ret gerente.
	 *
	 * @return the marca ret gerente
	 */
	public String getMarcaRetGerente() {
		return marcaRetGerente;
	}

	/**
	 * Sets the marca ret gerente.
	 *
	 * @param marcaRetGerente
	 *            the new marca ret gerente
	 */
	public void setMarcaRetGerente(String marcaRetGerente) {
		this.marcaRetGerente = marcaRetGerente;
	}

	/**
	 * Gets the marca ret producto.
	 *
	 * @return the marca ret producto
	 */
	public String getMarcaRetProducto() {
		return marcaRetProducto;
	}

	/**
	 * Sets the marca ret producto.
	 *
	 * @param marcaRetProducto
	 *            the new marca ret producto
	 */
	public void setMarcaRetProducto(String marcaRetProducto) {
		this.marcaRetProducto = marcaRetProducto;
	}

	/**
	 * Gets the marca devoluc SS.
	 *
	 * @return the marca devoluc SS
	 */
	public String getMarcaDevolucSS() {
		return marcaDevolucSS;
	}

	/**
	 * Sets the marca devoluc SS.
	 *
	 * @param marcaDevolucSS
	 *            the new marca devoluc SS
	 */
	public void setMarcaDevolucSS(String marcaDevolucSS) {
		this.marcaDevolucSS = marcaDevolucSS;
	}

	/**
	 * Gets the fecha de alta del tramite.
	 *
	 * @return the fecha de alta del tramite
	 */
	public String getFechaDeAltaDelTramite() {
		return FechaDeAltaDelTramite;
	}

	/**
	 * Sets the fecha de alta del tramite.
	 *
	 * @param fechaDeAltaDelTramite
	 *            the new fecha de alta del tramite
	 */
	public void setFechaDeAltaDelTramite(String fechaDeAltaDelTramite) {
		FechaDeAltaDelTramite = fechaDeAltaDelTramite;
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
	public List<DetalleOperacionPrecargadaEntity> getOperaciones() {
		return operaciones;
	}

	/**
	 * Sets the operaciones.
	 *
	 * @param operaciones
	 *            the new operaciones
	 */
	public void setOperaciones(List<DetalleOperacionPrecargadaEntity> operaciones) {
		this.operaciones = operaciones;
	}
    
    
    
}
