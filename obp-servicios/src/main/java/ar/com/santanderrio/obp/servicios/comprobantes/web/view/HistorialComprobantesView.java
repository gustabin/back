package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.util.List;

public class HistorialComprobantesView {

	private String empresa;
	private String identificacion;
	private String tipoCuentaDestino;
	private List<ComprobanteHistorialView> comprobantes;

	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * @param identificacion
	 *            the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * @return the comprobantes
	 */
	public List<ComprobanteHistorialView> getComprobantes() {
		return comprobantes;
	}

	/**
	 * @param comprobantes
	 *            the comprobantes to set
	 */
	public void setComprobantes(List<ComprobanteHistorialView> comprobantes) {
		this.comprobantes = comprobantes;
	}

	/**
	 * @return the tipoCuentaDestino
	 */
	public String getTipoCuentaDestino() {
		return tipoCuentaDestino;
	}

	/**
	 * @param tipoCuentaDestino
	 *            the tipoCuentaDestino to set
	 */
	public void setTipoCuentaDestino(String tipoCuentaDestino) {
		this.tipoCuentaDestino = tipoCuentaDestino;
	}

}
