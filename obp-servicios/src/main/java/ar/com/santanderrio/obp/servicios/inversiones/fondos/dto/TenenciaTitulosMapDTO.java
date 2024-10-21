/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ListaTenenciaCuentaTitMoneda;

/**
 * The Class TenenciaTitulosMapDTO.
 */
public class TenenciaTitulosMapDTO {

	/** mapa de tenencias. */
	private Map<String, TenenciaTitulosCuentaDTO> mapTenencias;

	/** The Constant CODIGO_SIN_CUENTAS. */
	private static final String MONEDA_ARS = "ARS";
	
	/** The Constant BANCA_PRIVADA. */
	public static final String BANCA_PRIVADA = "BP";

	/** The Constant BANCA_PERSONAL. */
	public static final String BANCA_PERSONAL = "BR";

	/** The Constant TENENCIA_ESTA_HABILITADA. */
	public static final String TENENCIA_ESTA_HABILITADA = "S";
	
	/**
	 * Instantiates a new tenencia titulos map DTO.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @param banca
	 *            the banca
	 */
	public TenenciaTitulosMapDTO(List<CuentaTituloDTO> cuentas, String banca) {
		mapTenencias = new HashMap<String, TenenciaTitulosCuentaDTO>();
		for (CuentaTituloDTO cuenta : cuentas) {
			mapTenencias.put(ISBANStringUtils.eliminarCeros(BANCA_PERSONAL.equalsIgnoreCase(banca)
					? cuenta.getNroCuenta() : cuenta.getCuentaOperativaSinFormatear()), new TenenciaTitulosCuentaDTO());
			mapTenencias
					.get(ISBANStringUtils.eliminarCeros(BANCA_PERSONAL.equalsIgnoreCase(banca) ? cuenta.getNroCuenta()
							: cuenta.getCuentaOperativaSinFormatear()))
					.setNumeroCuenta(ISBANStringUtils
							.formatearNumeroCuenta(BANCA_PERSONAL.equalsIgnoreCase(banca) ? cuenta.getNroCuenta()
									: cuenta.getCuentaOperativaSinFormatear()));
		}
	}

	/**
	 * Carga las tenencias por producto al Map de tenencias.
	 *
	 * @param list
	 *            the list
	 */
	public void cargarTenencias(List<ListaTenenciaCuentaTitMoneda> list) {

		for (ListaTenenciaCuentaTitMoneda tenencia : list) {
			
			TenenciaTitulosDTO tenenciasDTO = new TenenciaTitulosDTO(); 
			tenenciasDTO.setCodigoEstadoTenencia(tenencia.getCodigoEstadoTenencia());
			tenenciasDTO.setHabilitarCompraVenta(TENENCIA_ESTA_HABILITADA.equals(tenencia.getHabilitado()) ? true : false);
			tenenciasDTO.setTipo(tenencia.getDescripcionTipoProducto());
			tenenciasDTO.setDescripcion(tenencia.getDescripcionEspecie());
			tenenciasDTO.setCodigoTipoProducto(tenencia.getCodigoTipoProducto());
			tenenciasDTO.setCantidadValorNominal(Double.valueOf(tenencia.getTenenciaNominal()));
			if(tenencia.getCotizacion() == null)  {
			    tenenciasDTO.setPrecioMercado(null);
			} else {
			    tenenciasDTO.setPrecioMercado(Double.valueOf(tenencia.getCotizacion()));
			}
			if(tenencia.getTenenciaValuada() == null){
			    tenenciasDTO.setTenenciaValuada(null);
            }else{
                tenenciasDTO.setTenenciaValuada(Double.valueOf(tenencia.getTenenciaValuada()));
            }
			tenenciasDTO.setCodigoRossi(tenencia.getCodigoEspecie());
			//CAMPOS PARA VER DETALLE
            tenenciasDTO.setCodigoEspecieMercado(tenencia.getCodigoEspecieMercado());
            tenenciasDTO.setFechaUltimaCotizacion(tenencia.getFechaCotizacion());
            tenenciasDTO.setPrecioPromedioDeCompra(tenencia.getPppc());
            tenenciasDTO.setValuacionAlCosto(tenencia.getTenenciaValuadaCompra());
            tenenciasDTO.setRentasCobradas(tenencia.getCuPPc());
            tenenciasDTO.setAmortizacionesCobradas(tenencia.getAmPPc());
            tenenciasDTO.setDividendosCobrados(tenencia.getDivPPc());
            tenenciasDTO.setEstado(tenencia.getDescripcionEstadoTen());
            tenenciasDTO.setCodigoProducto(tenencia.getCodigoProducto());
			if(tenencia.getResultadoBrutoCorregido() == null){
				tenenciasDTO.setResultado(null);
			}else{
				tenenciasDTO.setResultado(Double.valueOf(tenencia.getResultadoBrutoCorregido()));
			}
			
			tenenciasDTO.setProducto(tenencia.getDescripcionTipoProducto());
			if (MONEDA_ARS.equals(tenencia.getMoneda())) {
				mapTenencias.get(ISBANStringUtils.eliminarCeros(tenencia.getNumeroCuenta())).getListaPesos()
						.add(tenenciasDTO);
				if (tenenciasDTO.getResultado() != null){
					mapTenencias.get(tenencia.getNumeroCuenta()).addTotalResultadoPesos(tenenciasDTO.getResultado());
				}
				if (tenenciasDTO.getTenenciaValuada() != null){
				    mapTenencias.get(tenencia.getNumeroCuenta())
				        .addTotalTenenciaValuadaPesos(tenenciasDTO.getTenenciaValuada());
				}
			} else {
				mapTenencias.get(ISBANStringUtils.eliminarCeros(tenencia.getNumeroCuenta())).getListaDolares()
						.add(tenenciasDTO);
				if (tenenciasDTO.getResultado() != null){
					mapTenencias.get(tenencia.getNumeroCuenta()).addTotalResultadoDolares(tenenciasDTO.getResultado());
				}
				if (tenenciasDTO.getTenenciaValuada() != null){
                    mapTenencias.get(tenencia.getNumeroCuenta())
                        .addTotalTenenciaValuadaDolares(tenenciasDTO.getTenenciaValuada());
                }
			}
			mapTenencias.get(tenencia.getNumeroCuenta()).setSinTenencias(false);
			if (tenencia.getCodEstadoCuentaTitulo() == null
					|| !"0".equals(tenencia.getCodEstadoCuentaTitulo().trim())) {
				mapTenencias.get(tenencia.getNumeroCuenta()).setCuentaBloqueada(true);
			}
		}

	}

	/**
	 * Crea un objeto TenenciaConsolidadaPorProductoDTO con la suma de la
	 * tenencia reexpresada en cada moneda y los totales de tenencia por
	 * producto.
	 *
	 * @return the tenencia consolidada por producto DTO
	 */
	public TenenciaTitulosOutDTO createResponse() {
		TenenciaTitulosOutDTO tenenciasDTO = new TenenciaTitulosOutDTO();
		tenenciasDTO.getList().addAll(mapTenencias.values());
		return tenenciasDTO;
	}

}
