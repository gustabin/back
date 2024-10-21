package ar.com.santanderrio.obp.servicios.prestamos.entity;

public class CancelarPrestamoInEntity {

	private String sucursalPmo;
	
	private String cuentaPmo;
	
	private String divisa;
	
	private String motivoCancelacion;
	
	private String indSimulacion;
	
	private String fecValor;
	
	private String formaPagCargo;
	
	private String sucursalAltairCargo;
	
	private String cuentaAltairCargo;
	
	private String codigoConcepto;
	
	private String tipoApl;
	
	private String impApl;

	private CancelarPrestamoInEntity(Builder builder) {
		this.sucursalPmo = builder.sucursalPmo;
		this.cuentaPmo = builder.cuentaPmo;
		this.divisa = builder.divisa;
		this.motivoCancelacion = builder.motivoCancelacion;
		this.indSimulacion = builder.indSimulacion;
		this.fecValor = builder.fecValor;
		this.formaPagCargo = builder.formaPagCargo;
		this.sucursalAltairCargo = builder.sucursalAltairCargo;
		this.cuentaAltairCargo = builder.cuentaAltairCargo;
		this.codigoConcepto = builder.codigoConcepto;
		this.tipoApl = builder.tipoApl;
		this.impApl = builder.impApl;
	};

	public static Builder builder() {
		return new Builder();
	}

	public String getSucursalPmo() {
		return sucursalPmo;
	}

	public void setSucursalPmo(String sucursalPmo) {
		this.sucursalPmo = sucursalPmo;
	}

	public String getCuentaPmo() {
		return cuentaPmo;
	}

	public void setCuentaPmo(String cuentaPmo) {
		this.cuentaPmo = cuentaPmo;
	}

	public String getDivisa() {
		return divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}

	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}

	public String getIndSimulacion() {
		return indSimulacion;
	}

	public void setIndSimulacion(String indSimulacion) {
		this.indSimulacion = indSimulacion;
	}

	public String getFecValor() {
		return fecValor;
	}

	public void setFecValor(String fecValor) {
		this.fecValor = fecValor;
	}

	public String getFormaPagCargo() {
		return formaPagCargo;
	}

	public void setFormaPagCargo(String formaPagCargo) {
		this.formaPagCargo = formaPagCargo;
	}

	public String getSucursalAltairCargo() {
		return sucursalAltairCargo;
	}

	public void setSucursalAltairCargo(String sucursalAltairCargo) {
		this.sucursalAltairCargo = sucursalAltairCargo;
	}

	public String getCuentaAltairCargo() {
		return cuentaAltairCargo;
	}

	public void setCuentaAltairCargo(String cuentaAltairCargo) {
		this.cuentaAltairCargo = cuentaAltairCargo;
	}

	public String getCodigoConcepto() {
		return codigoConcepto;
	}

	public void setCodigoConcepto(String codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}

	public String getTipoApl() {
		return tipoApl;
	}

	public void setTipoApl(String tipoApl) {
		this.tipoApl = tipoApl;
	}

	public String getImpApl() {
		return impApl;
	}

	public void setImpApl(String impApl) {
		this.impApl = impApl;
	}

	public static class Builder {

		private String sucursalPmo;
		private String cuentaPmo;
		private String divisa;
		private String motivoCancelacion;
		private String indSimulacion;
		private String fecValor;
		private String formaPagCargo;
		private String sucursalAltairCargo;
		private String cuentaAltairCargo;
		private String codigoConcepto;
		private String tipoApl;
		private String impApl;

		public Builder sucursalPmo(String sucursalPmo) {
			this.sucursalPmo = sucursalPmo;
			return this;
		}

		public Builder cuentaPmo(String cuentaPmo) {
			this.cuentaPmo = cuentaPmo;
			return this;
		}

		public Builder divisa(String divisa) {
			this.divisa = divisa;
			return this;
		}

		public Builder motivoCancelacion(String motivoCancelacion){
			this.motivoCancelacion = motivoCancelacion;
			return this;
		}

		public Builder indSimulacion(String indSimulacion){
			this.indSimulacion = indSimulacion;
			return this;
		}

		public Builder fecValor(String fecValor){
			this.fecValor = fecValor;
			return this;
		}

		public Builder formaPagCargo(String formaPagCargo) {
			this.formaPagCargo = formaPagCargo;
			return this;
		}

		public Builder sucursalAltairCargo(String sucursalAltairCargo) {
			this.sucursalAltairCargo = sucursalAltairCargo;
			return this;
		}

		public Builder cuentaAltairCargo(String cuentaAltairCargo) {
			this.cuentaAltairCargo = cuentaAltairCargo;
			return this;
		}

		public Builder codigoConcepto(String codigoConcepto) {
			this.codigoConcepto = codigoConcepto;
			return this;
		}

		public Builder tipoApl(String tipoApl) {
			this.tipoApl = tipoApl;
			return this;
		}

		public Builder impApl(String impApl) {
			this.impApl = impApl;
			return this;
		}

		public CancelarPrestamoInEntity build() {
			return new CancelarPrestamoInEntity(this);
		}
	}
}
