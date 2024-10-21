/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoProductoEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ProductoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ResultadoCuentaProductoOL;

/**
 * Entidad que encapsula todo el comportamiento de carga de tenencias y suma de
 * totales por producto.
 *
 * @author marcelo.ruiz
 */
public class TenenciasPorProductoDTO {

	/** codigo error. */
	private static final String CODIGO_ERROR_OK = "0";

	/** mapa de tenencias. */
	private Map<TipoProductoEnum, TenenciaPorProductoDTO> tenenciasPorProductoMap;

	/** The productos con error. */
	private boolean productosConError = false;
	
	/** The productos con error. */
	private ArrayList<String> listaProductosError;

	/** The resultado ok pesos. */
	private boolean resultadoOkPesos = false;

	/** The resultado ok dolares. */
	private boolean resultadoOkDolares = false;

	/** The Constant CODIGO_SIN_CUENTAS. */
	private static final String MONEDA_ARS = "ARS";

	/**
	 * Creo el objeto con la lista con los productos que posee el cliente, cada
	 * ProductoEntity contiene el resultado de la consulta (0|1|2).
	 *
	 * @param list
	 *            the list
	 */
	public TenenciasPorProductoDTO(List<ProductoEntity> list) {
		listaProductosError = new ArrayList<String>();
		tenenciasPorProductoMap = new HashMap<TipoProductoEnum, TenenciaPorProductoDTO>();
		for (TipoProductoEnum tipoEnum : TipoProductoEnum.values()) {
			tenenciasPorProductoMap.put(tipoEnum, new TenenciaPorProductoDTO());
			tenenciasPorProductoMap.get(tipoEnum).setProducto(tipoEnum.getCodigo());
		}
		for (ProductoEntity producto : list) {
			tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(producto.getCodigoProducto()))
			.setCodigoError(producto.getResultado());
			productosConError = productosConError || (CODIGO_ERROR_OK.equals(producto.getResultado()));
			if (!CODIGO_ERROR_OK.equals(producto.getResultado())){
				listaProductosError.add(producto.getCodigoProducto());
			}
		}
	}

	/**
	 * Si todos los productos tienen error, no se deben mostrar.
	 *
	 * @return true, if successful
	 */
	public boolean mostrarProductos() {
		return productosConError;
	}

	/**
	 * Mostrar resultado ok pesos.
	 *
	 * @return true, if successful
	 */
	public boolean mostrarResultadoOkPesos() {
		return resultadoOkPesos;
	}

	/**
	 * Mostrar resultado ok dolares.
	 *
	 * @return true, if successful
	 */
	public boolean mostrarResultadoOkDolares() {
		return resultadoOkDolares;
	}

	/**
	 * Carga las tenencias por producto al Map de tenencias.
	 *
	 * @param list
	 *            the list
	 * @param listaProductosError
	 *            the lista productos error
	 */
	public void cargarTenenciasPorProducto(List<ResultadoCuentaProductoOL> list, ArrayList<String> listaProductosError) {
		for (ResultadoCuentaProductoOL resultado : list) {
			if (!listaProductosError.contains(resultado.getCodProducto())){
				if (MONEDA_ARS.equals(resultado.getMoneda())) {
					//TODO Eliminar cuando funcione CUSTODIA
					if (!TipoProductoEnum.fromCodigoString(resultado.getCodProducto()).equals(TipoProductoEnum.CUSTODIA)) {
						efectuarLogicaNullPesos(resultado);
						tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto()))
								.addTenenciaValuadaCostoPesos(validarCampoNumerico(resultado.getTenenciaValuadaCompra()));
	
					}
					tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto()))
							.addTenenciaValuadaHoyPesos(new BigDecimal(resultado.getTenenciaValuada()));
					tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto()))
							.addTenenciaReexpresadaDolares(new BigDecimal(resultado.getTenenciaValuadaReexp()));
				} else {
					//TODO Eliminar cuando funcione CUSTODIA
					if (!TipoProductoEnum.fromCodigoString(resultado.getCodProducto()).equals(TipoProductoEnum.CUSTODIA)) {
						efectuarLogicaNullDolares(resultado);
						tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto()))
								.addTenenciaValuadaCostoDolares(validarCampoNumerico(resultado.getTenenciaValuadaCompra()));
					}
					tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto()))
							.addTenenciaValuadaHoyDolares(new BigDecimal(resultado.getTenenciaValuada()));
					tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto()))
							.addTenenciaReexpresadaPesos(new BigDecimal(resultado.getTenenciaValuadaReexp()));
				}
			}
		}
	}

	
	/**
	 * Valida que si es null no lo convierte a BigDecimal.
	 *
	 * @param tenenciaValuadaCompra
	 *            the tenencia valuada compra
	 * @return the big decimal
	 */
	private BigDecimal validarCampoNumerico(String tenenciaValuadaCompra) {
		if(tenenciaValuadaCompra != null){
			return new BigDecimal(tenenciaValuadaCompra);
		}
		return null;
	}

	/**
	 * Crea un objeto TenenciaConsolidadaPorProductoDTO con la suma de la
	 * tenencia reexpresada en cada moneda y los totales de tenencia por
	 * producto.
	 *
	 * @return the tenencia consolidada por producto DTO
	 */
	public TenenciaConsolidadaPorProductoDTO createTenenciaDTO() {
		TenenciaConsolidadaPorProductoDTO tenenciaDTO = new TenenciaConsolidadaPorProductoDTO();
		tenenciaDTO.getListaTenencia().addAll(tenenciasPorProductoMap.values());
		for (TenenciaPorProductoDTO tenencia : tenenciaDTO.getListaTenencia()) {
			if (CODIGO_ERROR_OK.equals(tenencia.getCodigoError())) {
				tenenciaDTO.addTenenciaReexpresadaDolares(tenencia.getTenenciaReexpresadaDolares());
				tenenciaDTO.addTenenciaReexpresadaPesos(tenencia.getTenenciaReexpresadaPesos());
				tenenciaDTO.addResultadoDolares(tenencia.getResultadoDolares());
				tenenciaDTO.addResultadoPesos(tenencia.getResultadoPesos());
				tenenciaDTO.addTotalTenenciaValuadaCostoDolares(tenencia.getTenenciaValuadaCostoDolares());
				tenenciaDTO.addTotalTenenciaValuadaCostoPesos(tenencia.getTenenciaValuadaCostoPesos());
				tenenciaDTO.addTotalTenenciaValuadaHoyDolares(tenencia.getTenenciaValuadaHoyDolares());
				tenenciaDTO.addTotalTenenciaValuadaHoyPesos(tenencia.getTenenciaValuadaHoyPesos());
				if (tenencia.getTenenciaValuadaHoyPesos().compareTo(BigDecimal.valueOf(0)) != 0) {
					resultadoOkPesos = true;
				}
				if (tenencia.getTenenciaValuadaHoyDolares().compareTo(BigDecimal.valueOf(0)) != 0) {
					resultadoOkDolares = true;
				}

			}
		}
		tenenciaDTO.sumaTotalTenenciaReexpresadaDolares(tenenciaDTO.getTotalTenenciaValuadaHoyDolares(),
				tenenciaDTO.getTenenciaReexpresadaDolares());
		tenenciaDTO.sumaTotalTenenciaReexpresadaPesos(tenenciaDTO.getTotalTenenciaValuadaHoyPesos(),
				tenenciaDTO.getTenenciaReexpresadaPesos());
		tenenciaDTO.setResultadoOkPesos(resultadoOkPesos);
		tenenciaDTO.setResultadoOkDolares(resultadoOkDolares);
		return tenenciaDTO;
	}
	
	/**
	 * Efectuar logica null pesos.
	 *
	 * @param resultado
	 *            the resultado
	 */
	private void efectuarLogicaNullPesos(ResultadoCuentaProductoOL resultado){
		if(TipoProductoEnum.fromCodigoString(resultado.getCodProducto()).equals(TipoProductoEnum.TITULOS_VALORES)){
			if(tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto())).getResultadoPesos() != null && resultado.getResultadoBrutoCorregido() != null){
				tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto()))
				.addResultadoPesos(new BigDecimal(resultado.getResultadoBrutoCorregido()));
			}else{
				tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto())).setResultadoPesos(null);
			}
		}else if(tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto())).getResultadoPesos() != null && resultado.getResultadoBruto() != null){
			tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto()))
			.addResultadoPesos(new BigDecimal(resultado.getResultadoBruto()));
		}else{
			tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto())).setResultadoPesos(null);
		}
	}
	
	/**
	 * Efectuar logica null dolares.
	 *
	 * @param resultado
	 *            the resultado
	 */
	private void efectuarLogicaNullDolares(ResultadoCuentaProductoOL resultado){
		if(TipoProductoEnum.fromCodigoString(resultado.getCodProducto()).equals(TipoProductoEnum.TITULOS_VALORES)){
			if(tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto())).getResultadoDolares() != null && resultado.getResultadoBrutoCorregido() != null){
				tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto()))
				.addResultadoDolares(new BigDecimal(resultado.getResultadoBrutoCorregido()));
			}else{
				tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto())).setResultadoDolares(null);
			}
			
		}else if(tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto())).getResultadoDolares() != null && resultado.getResultadoBruto() != null){
			tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto()))
			.addResultadoDolares(new BigDecimal(resultado.getResultadoBruto()));
		}else{
			tenenciasPorProductoMap.get(TipoProductoEnum.fromCodigoString(resultado.getCodProducto())).setResultadoDolares(null);
		}
	}

	/**
	 * retorna la lista de productos que vinieron con error.
	 *
	 * @return the lista productos error
	 */
	public ArrayList<String> getListaProductosError() {
		return listaProductosError;
	}
	
	
}
