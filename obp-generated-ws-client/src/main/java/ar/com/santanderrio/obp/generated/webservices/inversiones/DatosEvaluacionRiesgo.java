package ar.com.santanderrio.obp.generated.webservices.inversiones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosEvaluacionRiesgo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.MESAC.ServiceContracts.Parameters")
public class DatosEvaluacionRiesgo {

	@XmlElement(name = "CanalId")
	private String canalId;
	@XmlElement(name = "CanalTipo")
	private String canalTipo;	
	@XmlElement(name = "CanalVersion")
	private String canalVersion;
	@XmlElement(name = "FechaNac")
	private String fechaNac;
	@XmlElement(name = "IdCliente")
	private String idCliente;
	@XmlElement(name = "SubcanalId")
	private String subcanalId;
	@XmlElement(name = "SubcanalTipo")
	private String subcanalTipo;
	@XmlElement(name = "TipoCliente")
	private String tipoCliente;
	@XmlElement(name = "TipoClienteId")
	private String tipoClienteId;
	@XmlElement(name = "UsuarioAtrib")
	private String usuarioAtrib;
	@XmlElement(name = "UsuarioId")
	private String usuarioId;    
	@XmlElement(name = "UsuarioPwd")
	private String usuarioPwd; 
	@XmlElement(name = "UsuarioTipo")
	private String usuarioTipo; 
	@XmlElement(name = "CodCanal")
	private String codCanal; 
	@XmlElement(name = "CompraVenta")
	private String compraVenta; 
	@XmlElement(name = "Especie")
	private String especie; 
	@XmlElement(name = "EspecieOrigen")
	private String especieOrigen; 
	@XmlElement(name = "ImporteDebCred")
	private String importeDebCred; 
	@XmlElement(name = "Moneda")
	private String moneda; 
	@XmlElement(name = "Monto")
	private String monto; 
	@XmlElement(name = "NroCuenta")
	private String nroCuenta; 
	@XmlElement(name = "NroCuentaTitulo")
	private String nroCuentaTitulo; 
	@XmlElement(name = "Nup")
	private String nup; 
	@XmlElement(name = "Producto")
	private String producto; 
	@XmlElement(name = "SucursalCuenta")
	private String sucursalCuenta; 
	@XmlElement(name = "TipoCuenta")
	private String tipoCuenta; 
	@XmlElement(name = "TipoOperacion")
	private String tipoOperacion;
	@XmlElement(name = "Cantidad")
	private String cantidad;
	
	public String getCanalId() {
		return canalId;
	}
	public void setCanalId(String canalId) {
		this.canalId = canalId;
	}
	public String getCanalTipo() {
		return canalTipo;
	}
	public void setCanalTipo(String canalTipo) {
		this.canalTipo = canalTipo;
	}
	public String getCanalVersion() {
		return canalVersion;
	}
	public void setCanalVersion(String canalVersion) {
		this.canalVersion = canalVersion;
	}
	public String getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getSubcanalId() {
		return subcanalId;
	}
	public void setSubcanalId(String subcanalId) {
		this.subcanalId = subcanalId;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public String getTipoClienteId() {
		return tipoClienteId;
	}
	public void setTipoClienteId(String tipoClienteId) {
		this.tipoClienteId = tipoClienteId;
	}
	public String getUsuarioAtrib() {
		return usuarioAtrib;
	}
	public void setUsuarioAtrib(String usuarioAtrib) {
		this.usuarioAtrib = usuarioAtrib;
	}
	public String getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getUsuarioPwd() {
		return usuarioPwd;
	}
	public void setUsuarioPwd(String usuarioPwd) {
		this.usuarioPwd = usuarioPwd;
	}
	public String getUsuarioTipo() {
		return usuarioTipo;
	}
	public void setUsuarioTipo(String usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}
	public String getCodCanal() {
		return codCanal;
	}
	public void setCodCanal(String codCanal) {
		this.codCanal = codCanal;
	}
	public String getCompraVenta() {
		return compraVenta;
	}
	public void setCompraVenta(String compraVenta) {
		this.compraVenta = compraVenta;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public String getEspecieOrigen() {
		return especieOrigen;
	}
	public void setEspecieOrigen(String especieOrigen) {
		this.especieOrigen = especieOrigen;
	}
	public String getImporteDebCred() {
		return importeDebCred;
	}
	public void setImporteDebCred(String importeDebCred) {
		this.importeDebCred = importeDebCred;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getNroCuenta() {
		return nroCuenta;
	}
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	public String getNroCuentaTitulo() {
		return nroCuentaTitulo;
	}
	public void setNroCuentaTitulo(String nroCuentaTitulo) {
		this.nroCuentaTitulo = nroCuentaTitulo;
	}
	public String getNup() {
		return nup;
	}
	public void setNup(String nup) {
		this.nup = nup;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public String getSubcanalTipo() {
		return subcanalTipo;
	}
	public void setSubcanalTipo(String subcanalTipo) {
		this.subcanalTipo = subcanalTipo;
	} 
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	} 
	

}
