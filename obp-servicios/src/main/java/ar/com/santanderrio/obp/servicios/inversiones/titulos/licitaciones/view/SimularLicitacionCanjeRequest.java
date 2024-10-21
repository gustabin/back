package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

public class SimularLicitacionCanjeRequest{
	
	//ObtenerCanalTramo
	private Long codigoTramoCanal;
	private Long codigoPliego;
	private Long codigoTramo;
	private String tipoCuenta;
	private String cuentaTit;
	private String codigoEspecie;
	private String moneda;
	private String correoElectronico;
	private String renovacion;
	private String nombreArchivoCondiciones;
	
	//ConsultarTenenciaRenovable
	private String sucursal;
	private String tipoCuentaOper;
	private String cuentaOper;
	private Double precio;
	private Double coeficiente;
	private String especieRenovacion;
	private String lugarRenovacion;
	
	//Valores ingresados por usuario
	private Double cantidad;

	public Long getCodigoTramoCanal() {
		return codigoTramoCanal;
	}

	public void setCodigoTramoCanal(Long codigoTramoCanal) {
		this.codigoTramoCanal = codigoTramoCanal;
	}

	public Long getCodigoPliego() {
		return codigoPliego;
	}

	public void setCodigoPliego(Long codigoPliego) {
		this.codigoPliego = codigoPliego;
	}

	public Long getCodigoTramo() {
		return codigoTramo;
	}

	public void setCodigoTramo(Long codigoTramo) {
		this.codigoTramo = codigoTramo;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getCuentaTit() {
		return cuentaTit;
	}

	public void setCuentaTit(String cuentaTit) {
		this.cuentaTit = cuentaTit;
	}

	public String getCodigoEspecie() {
		return codigoEspecie;
	}

	public void setCodigoEspecie(String codigoEspecie) {
		this.codigoEspecie = codigoEspecie;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getRenovacion() {
		return renovacion;
	}

	public void setRenovacion(String renovacion) {
		this.renovacion = renovacion;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getTipoCuentaOper() {
		return tipoCuentaOper;
	}

	public void setTipoCuentaOper(String tipoCuentaOper) {
		this.tipoCuentaOper = tipoCuentaOper;
	}

	public String getCuentaOper() {
		return cuentaOper;
	}

	public void setCuentaOper(String cuentaOper) {
		this.cuentaOper = cuentaOper;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(Double coeficiente) {
		this.coeficiente = coeficiente;
	}

	public String getEspecieRenovacion() {
		return especieRenovacion;
	}

	public void setEspecieRenovacion(String especieRenovacion) {
		this.especieRenovacion = especieRenovacion;
	}

	public String getLugarRenovacion() {
		return lugarRenovacion;
	}

	public void setLugarRenovacion(String lugarRenovacion) {
		this.lugarRenovacion = lugarRenovacion;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public String getNombreArchivoCondiciones() {
		return nombreArchivoCondiciones;
	}

	public void setNombreArchivoCondiciones(String nombreArchivoCondiciones) {
		this.nombreArchivoCondiciones = nombreArchivoCondiciones;
	}

}
