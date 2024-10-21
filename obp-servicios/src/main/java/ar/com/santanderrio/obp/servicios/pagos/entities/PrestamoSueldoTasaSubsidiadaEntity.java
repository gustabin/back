package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.math.BigDecimal;
import java.util.List;

public class PrestamoSueldoTasaSubsidiadaEntity {

	private BigDecimal id;
	
	private BigDecimal solicitud;
	
	private BigDecimal tasa;
	
	private String fechaAlta;
	
	private String estado;
	
	private String tipoCliente;
	
	private String datos;
	
	private List<InfoEmpleadoPrestamoTasaSubsidiada> empleados;
	
	private String codigoRetorno;
	
	private String descRetorno;
	
	private String periodo;

	
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(BigDecimal solicitud) {
		this.solicitud = solicitud;
	}

	public BigDecimal getTasa() {
		return tasa;
	}

	public void setTasa(BigDecimal tasa) {
		this.tasa = tasa;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getDatos() {
		return datos;
	}

	public void setDatos(String datos) {
		this.datos = datos;
	}
	
	public List<InfoEmpleadoPrestamoTasaSubsidiada> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<InfoEmpleadoPrestamoTasaSubsidiada> empleados) {
		this.empleados = empleados;
	}

	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	public String getDescRetorno() {
		return descRetorno;
	}

	public void setDescRetorno(String descRetorno) {
		this.descRetorno = descRetorno;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
			
}
