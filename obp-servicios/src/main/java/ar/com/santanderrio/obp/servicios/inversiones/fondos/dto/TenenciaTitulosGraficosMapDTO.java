/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ListaTenenciaCuentaTitMoneda;

/**
 * The Class TenenciaTitulosGraficosMapDTO.
 */
public class TenenciaTitulosGraficosMapDTO {

	/** mapa de tenencias. */
	private Map<String, TenenciaGrafico> mapTenenciaGrafico;

	/** The Constant MONEDA_ARS. */
	private static final String MONEDA_ARS = "ARS";

	/** The Constant ACCIONES. */
	private static final String ACCIONES = "Acciones";

	/** The Constant COD_ACCIONES. */
	private static final String COD_ACCIONES = "ACC";

	/** The Constant CEDEAR. */
	private static final String CEDEAR = "Cedear";

	/** The Constant COD_CEDEAR. */
	private static final String COD_CEDEAR = "CDR";

	/** The Constant TITULOS_PUBLICOS. */
	private static final String TITULOS_PUBLICOS = "Titulos Publicos";

	/** The Constant COD_TITULOS_PUBLICOS. */
	private static final String COD_TITULOS_PUBLICOS = "PUB";

	/** The Constant TITULOS_PRIVADOS. */
	private static final String TITULOS_PRIVADOS = "Titulos Privados";

	/** The Constant COD_TITULOS_PRIVADOS. */
	private static final String COD_TITULOS_PRIVADOS = "PRV";

	/** The total ars. */
	private double totalArs;

	/** The total usd. */
	private double totalUsd;
	
	/** The total usd. */
	private double totalRexpresado;

    /** The valor null. */
    private boolean valorNull = false;
	
	/**
	 * Instantiates a new tenencia titulos graficos map DTO.
	 */
	public TenenciaTitulosGraficosMapDTO() {
		mapTenenciaGrafico = new HashMap<String, TenenciaGrafico>();
		mapTenenciaGrafico.put(COD_ACCIONES, new TenenciaGrafico());
		mapTenenciaGrafico.get(COD_ACCIONES).setProducto(ACCIONES);
		mapTenenciaGrafico.put(COD_CEDEAR, new TenenciaGrafico());
		mapTenenciaGrafico.get(COD_CEDEAR).setProducto(CEDEAR);
		mapTenenciaGrafico.put(COD_TITULOS_PUBLICOS, new TenenciaGrafico());
		mapTenenciaGrafico.get(COD_TITULOS_PUBLICOS).setProducto(TITULOS_PUBLICOS);
		mapTenenciaGrafico.put(COD_TITULOS_PRIVADOS, new TenenciaGrafico());
		mapTenenciaGrafico.get(COD_TITULOS_PRIVADOS).setProducto(TITULOS_PRIVADOS);
	}

	/**
	 * Cargar graficos.
	 *
	 * @param list
	 *            the list
	 */
	public void cargarGraficos(List<ListaTenenciaCuentaTitMoneda> list) {
		totalArs = 0;
		totalUsd = 0;
		totalRexpresado=0;
		
		for (ListaTenenciaCuentaTitMoneda tenencia : list) {
			if (mapTenenciaGrafico.get(tenencia.getCodigoTipoProducto()) != null) {
			    if(tenencia.getTenenciaValuada() == null || tenencia.getCotizacion() == null){
			        valorNull  = true;
			    }
				Double tenenciaValuada = tenencia.getTenenciaValuada() != null ? Double.valueOf(tenencia.getTenenciaValuada()) : Double.valueOf("0");
				Double tenenciarexpresada = tenencia.getTenenciaValuadaReexp() != null ? Double.valueOf(tenencia.getTenenciaValuadaReexp()) : Double.valueOf("0");
				if (MONEDA_ARS.equals(tenencia.getMoneda())) {
					mapTenenciaGrafico.get(tenencia.getCodigoTipoProducto()).addTenenciaPesos(tenenciaValuada);
					totalArs = totalArs + Math.abs(tenenciaValuada);

				} else {
					mapTenenciaGrafico.get(tenencia.getCodigoTipoProducto()).addTenenciaDolares(tenenciaValuada);
					totalUsd = totalUsd + Math.abs(tenenciaValuada);
					totalRexpresado = totalRexpresado + tenenciarexpresada;
				}
			}
		}
	}

	/**
	 * Creates the response.
	 *
	 * @return the tenencias graficos
	 */
	public TenenciasGraficos createResponse() {
		Double total = totalArs + totalUsd;	
		TenenciasGraficos productos = new TenenciasGraficos();
		productos.getTenenciaProductos().addAll(mapTenenciaGrafico.values());
		for (TenenciaGrafico producto : productos.getTenenciaProductos()) {
			if (totalArs>0.0)	
				producto.setTenenciaPesos((producto.getTenenciaPesos() * 100) / totalArs);
			if (totalUsd>0.0)	
				producto.setTenenciaDolares((producto.getTenenciaDolares() * 100) / totalUsd);

		}
		productos.setTotalArs((totalArs * 100) / total);
		productos.setTotalUsd((totalUsd * 100) / total);
		redondeoDe99(productos);
		productos.setValorNull(valorNull);
		
		return productos;
	}
	
	/**
	 * Redondeo de 99.
	 *
	 * @param producto
	 *            the producto
	 * @return the tenencias graficos
	 */
	private TenenciasGraficos redondeoDe99(TenenciasGraficos producto){
		
		String valorArs = String.valueOf(producto.getTotalArs()).substring(0, 2);
		String valorUsd = String.valueOf(producto.getTotalUsd()).substring(0, 2);
		
		if("99".equalsIgnoreCase(valorArs)){
			producto.setTotalArs(99);
			producto.setTotalUsd(1);
			
		}else if ("99".equalsIgnoreCase(valorUsd)){
			producto.setTotalArs(1);
			producto.setTotalUsd(99);
		}
		return producto;
		
	}

}
