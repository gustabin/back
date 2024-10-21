package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.pagos.entities.InfoEmpleadoPrestamoTasaSubsidiada;

public class ComprobantePrestamoTasaSubsidiadaView {

	private BigDecimal idPrestamos;

	private BigDecimal idSolicitud;

	private String datos;

	private BigDecimal monto;
	
	private String montoFormateado;

	private String cuotasDetalle;

	private String tasaInteres;

	private CuentaView cuentaSeleccionada;

	private String emailContacto;

	private String nroComprobante;

	private List<InfoEmpleadoPrestamoTasaSubsidiada> listaEmpleados;

	private static final String PATH_REPO = "classpath:/report/prestamos/comprobante/";

	private static final String NOMBRE_ARCHIVO_JASPER = "comprobantePrestamoTasaSub.jasper";

	private static final String MONEDA_PESOS = "$ ";

	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(PATH_REPO + NOMBRE_ARCHIVO_JASPER).getPath();
	}

	public HashMap<String, Object> obtenerParametros() {

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("nroComprobante", getNroComprobante());
		parameters.put("monto", MONEDA_PESOS + getMontoFormateado());
		parameters.put("cuotasPrestamo", getCuotasDetalle());
		parameters.put("tasa", getTasaInteres());
		parameters.put("cuentaDebito", getCuentaSeleccionada().getNumero());
		parameters.put("tipoCuenta", getCuentaSeleccionada().getDescripcionTipoCuenta());
		parameters.put("email", getEmailContacto());
		parameters.put("empleados", getListaEmpleados());
		parameters.put("textoFooter", "");
		parameters.put("legales", "");
		return parameters;
	}

	public BigDecimal getIdPrestamos() {
		return idPrestamos;
	}

	public BigDecimal getIdSolicitud() {
		return idSolicitud;
	}

	public String getDatos() {
		return datos;
	}

	public void setIdPrestamos(BigDecimal idPrestamos) {
		this.idPrestamos = idPrestamos;
	}

	public void setIdSolicitud(BigDecimal idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public void setDatos(String datos) {
		this.datos = datos;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getMontoFormateado() {
		return montoFormateado;
	}

	public void setMontoFormateado(String montoFormateado) {
		this.montoFormateado = montoFormateado;
	}

	public String getCuotasDetalle() {
		return cuotasDetalle;
	}

	public String getTasaInteres() {
		return tasaInteres;
	}

	public CuentaView getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public String getEmailContacto() {
		return emailContacto;
	}

	public String getNroComprobante() {
		return nroComprobante;
	}

	public List<InfoEmpleadoPrestamoTasaSubsidiada> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setCuotasDetalle(String cuotasDetalle) {
		this.cuotasDetalle = cuotasDetalle;
	}

	public void setTasaInteres(String tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	public void setCuentaSeleccionada(CuentaView cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}

	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public void setListaEmpleados(List<InfoEmpleadoPrestamoTasaSubsidiada> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

}
