package ar.com.santanderrio.obp.servicios.inversiones.fondos.excel;

import java.util.Map;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaFondosSuscritosDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoEspecie;

public class FciExcel implements Comparable<FciExcel> {

	private String tipo;

	private String fondo;

	private String cuotapartes;

	private String valorCuotaparte;

	private String tenenciaValuada;

	private String resultado;

	private int codigoAgrupador;

	private int ordenAgrupacion;

	public FciExcel() {
		super();
	}

	public FciExcel(String moneda, TenenciaFondosSuscritosDTO fondoDTO, boolean esVacio, Map<String, FondoEspecie> fondosMap) {

		if (esVacio) {
			this.tipo = "";
			this.fondo = "";
			this.tenenciaValuada = "";
			this.resultado = "";
		} else {
			this.tipo = fondoDTO.getTipoFondo();
			this.fondo = fondoDTO.getNombreFondo();
			if (fondosMap != null && fondosMap.containsKey(fondoDTO.getCodigoFondo())) {
				this.resultado = "-";
				this.valorCuotaparte = "-";
				this.tenenciaValuada = "-";
			} else {
				this.resultado = fondoDTO.getResultado() == null ? moneda + "-"
						: moneda + " " + formatearMonto(fondoDTO.getResultado().toString());
				this.valorCuotaparte = fondoDTO.getValorCuotaparte() == null ? moneda + "-"
						: moneda + " " + ISBANStringUtils
								.formatearConComaYVariosDecimales2(fondoDTO.getValorCuotaparte().toString(), 6);
				this.tenenciaValuada = fondoDTO.getValuacion() == null ? moneda + "-"
						: moneda + " " + formatearMonto(fondoDTO.getValuacion().toString());
			}
			this.cuotapartes = ISBANStringUtils
					.formatearConComaYVariosDecimales2(fondoDTO.getCantidadCuotapartes().toString(), 4);
			
			this.codigoAgrupador = Integer.parseInt(fondoDTO.getCodigoAgrupador().trim());
			this.ordenAgrupacion = Integer.parseInt(fondoDTO.getOrdenAgrupacion().trim());
		}
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFondo() {
		return fondo;
	}

	public void setFondo(String fondo) {
		this.fondo = fondo;
	}

	public String getCuotapartes() {
		return cuotapartes;
	}

	public void setCuotapartes(String cuotapartes) {
		this.cuotapartes = cuotapartes;
	}

	public String getValorCuotaparte() {
		return valorCuotaparte;
	}

	public void setValorCuotaparte(String valorCuotaparte) {
		this.valorCuotaparte = valorCuotaparte;
	}

	public String getTenenciaValuada() {
		return tenenciaValuada;
	}

	public void setTenenciaValuada(String tenenciaValuada) {
		this.tenenciaValuada = tenenciaValuada;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public int getCodigoAgrupador() {
		return codigoAgrupador;
	}

	public void setCodigoAgrupador(int codigoAgrupador) {
		this.codigoAgrupador = codigoAgrupador;
	}

	public int getOrdenAgrupacion() {
		return ordenAgrupacion;
	}

	public void setOrdenAgrupacion(int ordenAgrupacion) {
		this.ordenAgrupacion = ordenAgrupacion;
	}

	private String formatearMonto(String montoSinFormato) {

		String montoFormateado = ISBANStringUtils.formatearConComaYVariosDecimales2(montoSinFormato, 2);

		if (montoSinFormato.startsWith("-")) {
			return "-" + montoFormateado;
		}
		return montoFormateado;

	}

	@Override
	public int compareTo(FciExcel o) {

		int resultado = 0;
		if (codigoAgrupador < o.codigoAgrupador) {
			resultado = -1;
		}

		if (codigoAgrupador > o.codigoAgrupador) {
			resultado = 1;
		}

		if (resultado != 0) {
			return resultado;
		}

		int orden = 0;
		if (ordenAgrupacion < o.ordenAgrupacion) {
			orden = -1;
		}

		if (ordenAgrupacion > o.ordenAgrupacion) {
			orden = 1;
		}

		return orden;

	}

}
