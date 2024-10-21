/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class TenenciaProductosPorMonedaDTO.
 */
public class TenenciaProductosPorMonedaDTO {
	
	/** The Constant GUION_MEDIO. */
	private static final Object GUION_MEDIO = "-";

	/** The listaTenenciaProductos. */
	List<TenenciaPorProductoBPrivDTO> listaTenenciaProductos = new ArrayList<TenenciaPorProductoBPrivDTO>();

	/** The totalTenenciaValuadaHoy. */
	private String totalTenenciaValuadaHoy ="0";

	/** The totalTenenciaValuadaCosto. */
	private String totalTenenciaValuadaCosto ="";

	/** The totalResultado. */
	private String totalResultado ="";

	/** The moneda. */
	private String moneda;
	
	/** The monedaReexpresada. 
	 * Es el total de la tenencia en la otra moneda, expresada en la moneda actual.*/
	private String monedaReexpresada = "0";

	/** The totalMonedaReexpresada. 
	 * Es la suma de la tenencia total en la moneda actual y la tenencia de la otra moneda
	 * pero expresada en la moneda actual.*/
	private String totalMonedaReexpresada;
	
	/** The mostrarGraficos. */
	private boolean mostrarGraficos = true;

	
	/**
	 * Eliminar producto delista por codigo.
	 *
	 * @param codigoProducto
	 *            the codigo producto
	 */
	public void eliminarProductoDelistaPorCodigo(String codigoProducto){
		TenenciaPorProductoBPrivDTO elementoEliminar = null;
		for (TenenciaPorProductoBPrivDTO tenenciaPorProductoBPrivDTO : listaTenenciaProductos) {
			if(codigoProducto.equals(tenenciaPorProductoBPrivDTO.getProducto())){
				elementoEliminar = tenenciaPorProductoBPrivDTO;
			}
		}
		if(elementoEliminar != null){
			listaTenenciaProductos.remove(elementoEliminar);
		}
	}
	
	/**
	 * Gets the tenencia producto por codigo.
	 *
	 * @param codigoProducto
	 *            the codigo producto
	 * @return the tenencia producto por codigo
	 */
	public TenenciaPorProductoBPrivDTO getTenenciaProductoPorCodigo(String codigoProducto){
		for (TenenciaPorProductoBPrivDTO tenenciaPorProductoBPrivDTO : listaTenenciaProductos) {
			if(codigoProducto.equals(tenenciaPorProductoBPrivDTO.getProducto())){
				return tenenciaPorProductoBPrivDTO;
			}
		}
		return null;
	}
	
	/**
	 * Gets the lista tenencia productos.
	 *
	 * @return the lista tenencia productos
	 */
	public List<TenenciaPorProductoBPrivDTO> getListaTenenciaProductos() {
		return listaTenenciaProductos;
	}

	/**
	 * Sets the lista tenencia productos.
	 *
	 * @param listaTenenciaProductos
	 *            the new lista tenencia productos
	 */
	public void setListaTenenciaProductos(List<TenenciaPorProductoBPrivDTO> listaTenenciaProductos) {
		this.listaTenenciaProductos = listaTenenciaProductos;
	}

	/**
	 * Gets the total tenencia valuada hoy.
	 *
	 * @return the total tenencia valuada hoy
	 */
	public String getTotalTenenciaValuadaHoy() {
		return totalTenenciaValuadaHoy;
	}

	/**
	 * Sets the total tenencia valuada hoy.
	 *
	 * @param totalTenenciaValuadaHoy
	 *            the new total tenencia valuada hoy
	 */
	public void setTotalTenenciaValuadaHoy(String totalTenenciaValuadaHoy) {
		this.totalTenenciaValuadaHoy = totalTenenciaValuadaHoy;
	}

	/**
	 * Gets the total tenencia valuada costo.
	 *
	 * @return the total tenencia valuada costo
	 */
	public String getTotalTenenciaValuadaCosto() {
		return totalTenenciaValuadaCosto;
	}

	/**
	 * Sets the total tenencia valuada costo.
	 *
	 * @param totalTenenciaValuadaCosto
	 *            the new total tenencia valuada costo
	 */
	public void setTotalTenenciaValuadaCosto(String totalTenenciaValuadaCosto) {
		this.totalTenenciaValuadaCosto = totalTenenciaValuadaCosto;
	}

	/**
	 * Gets the total resultado.
	 *
	 * @return the total resultado
	 */
	public String getTotalResultado() {
		return totalResultado;
	}

	/**
	 * Sets the total resultado.
	 *
	 * @param totalResultado
	 *            the new total resultado
	 */
	public void setTotalResultado(String totalResultado) {
		this.totalResultado = totalResultado;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	
	/**
	 * Acumular total resultado.
	 *
	 * @param valor
	 *            the valor
	 */
	public void acumularTotalResultado(String valor){

		if(!GUION_MEDIO.equals(valor) && !GUION_MEDIO.equals(this.totalResultado) && !"".equals(this.totalResultado)){
			BigDecimal valorASumar = new BigDecimal(valor);
			BigDecimal totalResultadoBigDecimal = new BigDecimal(this.totalResultado);
			this.totalResultado = valorASumar.add(totalResultadoBigDecimal).toString();		
		}else{
			if("".equals(this.totalResultado) && GUION_MEDIO.equals(valor)){
				this.totalResultado = "-";
			}
			if(((GUION_MEDIO.equals(this.totalResultado)  || ("".equals(this.totalResultado))) && !GUION_MEDIO.equals(valor))){
				this.totalResultado = valor;
			}
		}
	}
	
	/**
	 * Acumular total tenencia valuada costo.
	 *
	 * @param valor
	 *            the valor
	 */
	public void acumularTotalTenenciaValuadaCosto(String valor){

		if(!GUION_MEDIO.equals(valor)  && !GUION_MEDIO.equals(this.totalTenenciaValuadaCosto) && !"".equals(this.totalTenenciaValuadaCosto)){
			BigDecimal valorASumar = new BigDecimal(valor);
			BigDecimal totalTenenciaValuadaCostoBigDecimal = new BigDecimal(this.getTotalTenenciaValuadaCosto());
			
			this.totalTenenciaValuadaCosto = valorASumar.add(totalTenenciaValuadaCostoBigDecimal).toString();
		}else{
			if("".equals(this.totalTenenciaValuadaCosto) && GUION_MEDIO.equals(valor)){
				this.totalTenenciaValuadaCosto = "-";
			}
			if(((GUION_MEDIO.equals(this.totalTenenciaValuadaCosto)  || ("".equals(this.totalTenenciaValuadaCosto))) && !GUION_MEDIO.equals(valor))){
				this.totalTenenciaValuadaCosto = valor;
			}
		}
	}
	
	/**
	 * Acumular total tenencia valuada hoy.
	 *
	 * @param valor
	 *            the valor
	 */
	public void acumularTotalTenenciaValuadaHoy(String valor){

		if(!GUION_MEDIO.equals(valor)){
			BigDecimal valorASumar = new BigDecimal(valor);
			BigDecimal totalTenenciaValuadaHoyBigDecimal = new BigDecimal(this.totalTenenciaValuadaHoy);
			
			this.totalTenenciaValuadaHoy = valorASumar.add(totalTenenciaValuadaHoyBigDecimal).toString();
		}
	}
	
	/**
	 * Acumular moneda reexpresada.
	 *
	 * @param valor
	 *            the valor
	 */
	public void acumularMonedaReexpresada(String valor){

		if(!GUION_MEDIO.equals(valor)){
			BigDecimal valorASumar = new BigDecimal(valor);
			BigDecimal acumuladorMonedaReexpresada = new BigDecimal(this.monedaReexpresada);
			this.monedaReexpresada = valorASumar.add(acumuladorMonedaReexpresada).toString();		
		}
	}

	/**
	 * Gets the moneda reexpresada.
	 *
	 * @return the moneda reexpresada
	 */
	public String getMonedaReexpresada() {
		return monedaReexpresada;
	}

	/**
	 * Sets the moneda reexpresada.
	 *
	 * @param monedaReexpresada
	 *            the new moneda reexpresada
	 */
	public void setMonedaReexpresada(String monedaReexpresada) {
		this.monedaReexpresada = monedaReexpresada;
	}

	/**
	 * Gets the total moneda reexpresada.
	 *
	 * @return the total moneda reexpresada
	 */
	public String getTotalMonedaReexpresada() {
		return totalMonedaReexpresada;
	}

	/**
	 * Sets the total moneda reexpresada.
	 *
	 * @param totalMonedaReexpresada
	 *            the new total moneda reexpresada
	 */
	public void setTotalMonedaReexpresada(String totalMonedaReexpresada) {
		this.totalMonedaReexpresada = totalMonedaReexpresada;
	}

	/**
	 * Checks if is mostrar graficos.
	 *
	 * @return true, if is mostrar graficos
	 */
	public boolean isMostrarGraficos() {
		return mostrarGraficos;
	}

	/**
	 * Sets the mostrar graficos.
	 *
	 * @param mostrarGraficos
	 *            the new mostrar graficos
	 */
	public void setMostrarGraficos(boolean mostrarGraficos) {
		this.mostrarGraficos = mostrarGraficos;
	}
	
	
}
