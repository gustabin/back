package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

public class SimularPrecancelarUVAInView {

	private String cuentaPlazo;

	private String numeroSecuencia;

	private String fechaConstitucion;

	public String getCuentaPlazo() {
		return cuentaPlazo;
	}

	public void setCuentaPlazo(String cuentaPlazo) {
		this.cuentaPlazo = cuentaPlazo;
	}

	public String getNumeroSecuencia() {
		return numeroSecuencia;
	}

	public void setNumeroSecuencia(String numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}

	public String getFechaConstitucion() {
		return fechaConstitucion;
	}

	public void setFechaConstitucion(String fechaConstitucion) {
		this.fechaConstitucion = fechaConstitucion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuentaPlazo == null) ? 0 : cuentaPlazo.hashCode());
		result = prime * result + ((fechaConstitucion == null) ? 0 : fechaConstitucion.hashCode());
		result = prime * result + ((numeroSecuencia == null) ? 0 : numeroSecuencia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimularPrecancelarUVAInView other = (SimularPrecancelarUVAInView) obj;
		if (cuentaPlazo == null) {
			if (other.cuentaPlazo != null)
				return false;
		} else if (!cuentaPlazo.equals(other.cuentaPlazo))
			return false;
		if (fechaConstitucion == null) {
			if (other.fechaConstitucion != null)
				return false;
		} else if (!fechaConstitucion.equals(other.fechaConstitucion))
			return false;
		if (numeroSecuencia == null) {
			if (other.numeroSecuencia != null)
				return false;
		} else if (!numeroSecuencia.equals(other.numeroSecuencia))
			return false;
		return true;
	}
}
