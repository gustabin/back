/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosRespuesta de Cuenta Producto Online.
 */
public class DatosRespuestaCuentaProductoOnline {

	/** lista de errores*. */
	@JsonProperty("ListaErrores")
	private List<ItemError> listaErrores;

	/** The resultado. */
	@JsonProperty("Resultado")
	private List<ResultadoCuentaProductoOL> resultado;

	/** The lista resultado por producto. */
	@JsonProperty("ListaResultadoPorProducto")
	private List<ProductoEntity> listaResultadoPorProducto;

	
	/**
	 * Devuelve el resultado del producto solicidato 0: Ok 1: Error parcial 2:
	 * Error total.
	 *
	 * @param codigoProductoBuscado
	 *            the codigo producto buscado
	 * @return the resultado producto por codigo
	 */
	public ProductoEntity getResultadoProductoPorCodigo(String codigoProductoBuscado){
		for (ProductoEntity resultadoProducto : this.listaResultadoPorProducto) {
			if(resultadoProducto.getCodigoProducto().equals(codigoProductoBuscado)){
				return resultadoProducto;
			}
		}
		return null;
	}
	
	/**
	 * Gets the lista resultado por producto.
	 *
	 * @return the lista resultado por producto
	 */
	public List<ProductoEntity> getListaResultadoPorProducto() {
		return listaResultadoPorProducto;
	}

	/**
	 * Sets the lista resultado por producto.
	 *
	 * @param listaResultadoPorProducto
	 *            the new lista resultado por producto
	 */
	public void setListaResultadoPorProducto(List<ProductoEntity> listaResultadoPorProducto) {
		this.listaResultadoPorProducto = listaResultadoPorProducto;
	}

	/**
	 * Gets the lista errores.
	 *
	 * @return the lista errores
	 */
	public List<ItemError> getListaErrores() {
		return listaErrores;
	}

	/**
	 * Sets the lista errores.
	 *
	 * @param listaErrores
	 *            the new lista errores
	 */
	public void setListaErrores(List<ItemError> listaErrores) {
		this.listaErrores = listaErrores;
	}

	/**
	 * Gets the resultado.
	 *
	 * @return the resultado
	 */
	public List<ResultadoCuentaProductoOL> getResultado() {
		return resultado;
	}

	/**
	 * Sets the resultado.
	 *
	 * @param resultado
	 *            the new resultado
	 */
	public void setResultado(List<ResultadoCuentaProductoOL> resultado) {
		this.resultado = resultado;
	}

}
