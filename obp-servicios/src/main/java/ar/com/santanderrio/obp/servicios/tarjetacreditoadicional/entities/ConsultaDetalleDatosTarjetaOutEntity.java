/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class ConsultaDetalleDatosTarjetaOutEntity.
 */
@Record
public class ConsultaDetalleDatosTarjetaOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The tipo cuenta. */
	@Field
	private String tipoCuenta;

	/** The numero de cuenta. */
	@Field
	private String numeroDeCuenta;

	/** The producto. */
	@Field
	private String producto;

	/** The descripcion producto. */
	@Field
	private String descripcionProducto;

	/** The categoria. */
	@Field
	private String categoria;

	/** The descripcion de categoria. */
	@Field
	private String descripcionDeCategoria;

	/** The estado. */
	@Field
	private String estado;

	/** The sucursal. */
	@Field
	private String sucursal;

	/** The nombre Y apellido razón social. */
	@Field
	private String nombreYApellidoRazónSocial;

	/** The cartera. */
	@Field
	private String cartera;

	/** The duracion. */
	@Field
	private String duracion;

	/** The codigo limite compra. */
	@Field
	private String codigoLimiteCompra;

	/** The limite de compra. */
	@Field
	private String limiteDeCompra;

	/** The coeficiente. */
	@Field
	private String coeficiente;

	/** The limite credito. */
	@Field
	private String limiteCredito;

	/** The bonificacion. */
	@Field
	private String bonificacion;

	/** The cuotas. */
	@Field
	private String cuotas;

	/** The mantiene bonificacion. */
	@Field
	private String mantieneBonificacion;

	/** The descripcion mantiene bonificacion. */
	@Field
	private String descripcionMantieneBonificacion;

	/** The forma de pago. */
	@Field
	private String formaDePago;

	/** The aplicacion para debito aut. */
	@Field
	private String aplicacionParaDebitoAut;

	/** The sucursal para debito aut. */
	@Field
	private String sucursalParaDebitoAut;

	/** The tipo cuenta para debito aut. */
	@Field
	private String tipoCuentaParaDebitoAut;

	/** The numero cuenta para debito aut. */
	@Field
	private String numeroCuentaParaDebitoAut;

	/** The numero firmante para debito aut. */
	@Field
	private String numeroFirmanteParaDebitoAut;

	/** The calle postal. */
	@Field
	private String callePostal;

	/** The numero postal. */
	@Field
	private String numeroPostal;

	/** The piso postal. */
	@Field
	private String pisoPostal;

	/** The depto postal. */
	@Field
	private String deptoPostal;

	/** The numero de documento O loc U of postal. */
	@Field
	private String numeroDeDocumentoOLocUOfPostal;

	/** The codigo postal. */
	@Field
	private String codigoPostal;

	/** The codigo geografico. */
	@Field
	private String codigoGeografico;

	/** The codigo de provincia. */
	@Field
	private String codigoDeProvincia;

	/** The localidad partido postal. */
	@Field
	private String localidadPartidoPostal;

	/** The domicilio postal descripcion. */
	@Field
	private String domicilioPostalDescripcion;

	/** The te. */
	@Field
	private String te;

	/** The te contacto. */
	@Field
	private String teContacto;

	/** The descripcion forma de pago. */
	@Field
	private String descripcionFormaDePago;

	/** The campaña. */
	@Field
	private String campaña;

	/** The shot. */
	@Field
	private String shot;

	/** The canal venta. */
	@Field
	private String canalVenta;

	/** The descripcion canal venta. */
	@Field
	private String descripcionCanalVenta;

	/** The grupo de afinidad. */
	@Field
	private String grupoDeAfinidad;

	/** The descripcion grupo afinidad. */
	@Field
	private String descripcionGrupoAfinidad;

	/** The fecha alta. */
	@Field
	private String fechaAlta;

	/** The usuario alta. */
	@Field
	private String usuarioAlta;

	/** The fecha de baja. */
	@Field
	private String fechaDeBaja;

	/** The motivo de baja descripcion. */
	@Field
	private String motivoDeBajaDescripcion;

	/** The fecha ultima modificacion. */
	@Field
	private String fechaUltimaModificacion;

	/** The hora ultima modificacion. */
	@Field
	private String horaUltimaModificacion;

	/** The usuario ultima modificacion. */
	@Field
	private String usuarioUltimaModificacion;

	/** The modelo liquidacion. */
	@Field
	private String modeloLiquidacion;

	/** The promotor. */
	@Field
	private String promotor;

	/** The estado de cuenta. */
	@Field
	private String estadoDeCuenta;

	/** The descripcion estado cuenta. */
	@Field
	private String descripcionEstadoCuenta;

	/** The cartera 2. */
	@Field
	private String cartera_2;

	/** The descripcion cartera. */
	@Field
	private String descripcionCartera;

	/** The origen ultimo movimiento. */
	@Field
	private String origenUltimoMovimiento;

	/** The moneda limites. */
	@Field
	private String monedaLimites;

	/**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigoRetornoExtendido to set
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the numero de cuenta.
	 *
	 * @return the numeroDeCuenta
	 */
	public String getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	/**
	 * Sets the numero de cuenta.
	 *
	 * @param numeroDeCuenta
	 *            the numeroDeCuenta to set
	 */
	public void setNumeroDeCuenta(String numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

	/**
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto
	 *            the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * Gets the descripcion producto.
	 *
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * Sets the descripcion producto.
	 *
	 * @param descripcionProducto
	 *            the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * Gets the categoria.
	 *
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Sets the categoria.
	 *
	 * @param categoria
	 *            the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Gets the descripcion de categoria.
	 *
	 * @return the descripcionDeCategoria
	 */
	public String getDescripcionDeCategoria() {
		return descripcionDeCategoria;
	}

	/**
	 * Sets the descripcion de categoria.
	 *
	 * @param descripcionDeCategoria
	 *            the descripcionDeCategoria to set
	 */
	public void setDescripcionDeCategoria(String descripcionDeCategoria) {
		this.descripcionDeCategoria = descripcionDeCategoria;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the nombre Y apellido razón social.
	 *
	 * @return the nombreYApellidoRazónSocial
	 */
	public String getNombreYApellidoRazónSocial() {
		return nombreYApellidoRazónSocial;
	}

	/**
	 * Sets the nombre Y apellido razón social.
	 *
	 * @param nombreYApellidoRazónSocial
	 *            the nombreYApellidoRazónSocial to set
	 */
	public void setNombreYApellidoRazónSocial(String nombreYApellidoRazónSocial) {
		this.nombreYApellidoRazónSocial = nombreYApellidoRazónSocial;
	}

	/**
	 * Gets the cartera.
	 *
	 * @return the cartera
	 */
	public String getCartera() {
		return cartera;
	}

	/**
	 * Sets the cartera.
	 *
	 * @param cartera
	 *            the cartera to set
	 */
	public void setCartera(String cartera) {
		this.cartera = cartera;
	}

	/**
	 * Gets the duracion.
	 *
	 * @return the duracion
	 */
	public String getDuracion() {
		return duracion;
	}

	/**
	 * Sets the duracion.
	 *
	 * @param duracion
	 *            the duracion to set
	 */
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	/**
	 * Gets the codigo limite compra.
	 *
	 * @return the codigoLimiteCompra
	 */
	public String getCodigoLimiteCompra() {
		return codigoLimiteCompra;
	}

	/**
	 * Sets the codigo limite compra.
	 *
	 * @param codigoLimiteCompra
	 *            the codigoLimiteCompra to set
	 */
	public void setCodigoLimiteCompra(String codigoLimiteCompra) {
		this.codigoLimiteCompra = codigoLimiteCompra;
	}

	/**
	 * Gets the limite de compra.
	 *
	 * @return the limiteDeCompra
	 */
	public String getLimiteDeCompra() {
		return limiteDeCompra;
	}

	/**
	 * Sets the limite de compra.
	 *
	 * @param limiteDeCompra
	 *            the limiteDeCompra to set
	 */
	public void setLimiteDeCompra(String limiteDeCompra) {
		this.limiteDeCompra = limiteDeCompra;
	}

	/**
	 * Gets the coeficiente.
	 *
	 * @return the coeficiente
	 */
	public String getCoeficiente() {
		return coeficiente;
	}

	/**
	 * Sets the coeficiente.
	 *
	 * @param coeficiente
	 *            the coeficiente to set
	 */
	public void setCoeficiente(String coeficiente) {
		this.coeficiente = coeficiente;
	}

	/**
	 * Gets the limite credito.
	 *
	 * @return the limiteCredito
	 */
	public String getLimiteCredito() {
		return limiteCredito;
	}

	/**
	 * Sets the limite credito.
	 *
	 * @param limiteCredito
	 *            the limiteCredito to set
	 */
	public void setLimiteCredito(String limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	/**
	 * Gets the bonificacion.
	 *
	 * @return the bonificacion
	 */
	public String getBonificacion() {
		return bonificacion;
	}

	/**
	 * Sets the bonificacion.
	 *
	 * @param bonificacion
	 *            the bonificacion to set
	 */
	public void setBonificacion(String bonificacion) {
		this.bonificacion = bonificacion;
	}

	/**
	 * Gets the cuotas.
	 *
	 * @return the cuotas
	 */
	public String getCuotas() {
		return cuotas;
	}

	/**
	 * Sets the cuotas.
	 *
	 * @param cuotas
	 *            the cuotas to set
	 */
	public void setCuotas(String cuotas) {
		this.cuotas = cuotas;
	}

	/**
	 * Gets the mantiene bonificacion.
	 *
	 * @return the mantieneBonificacion
	 */
	public String getMantieneBonificacion() {
		return mantieneBonificacion;
	}

	/**
	 * Sets the mantiene bonificacion.
	 *
	 * @param mantieneBonificacion
	 *            the mantieneBonificacion to set
	 */
	public void setMantieneBonificacion(String mantieneBonificacion) {
		this.mantieneBonificacion = mantieneBonificacion;
	}

	/**
	 * Gets the descripcion mantiene bonificacion.
	 *
	 * @return the descripcionMantieneBonificacion
	 */
	public String getDescripcionMantieneBonificacion() {
		return descripcionMantieneBonificacion;
	}

	/**
	 * Sets the descripcion mantiene bonificacion.
	 *
	 * @param descripcionMantieneBonificacion
	 *            the descripcionMantieneBonificacion to set
	 */
	public void setDescripcionMantieneBonificacion(String descripcionMantieneBonificacion) {
		this.descripcionMantieneBonificacion = descripcionMantieneBonificacion;
	}

	/**
	 * Gets the forma de pago.
	 *
	 * @return the formaDePago
	 */
	public String getFormaDePago() {
		return formaDePago;
	}

	/**
	 * Sets the forma de pago.
	 *
	 * @param formaDePago
	 *            the formaDePago to set
	 */
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}

	/**
	 * Gets the aplicacion para debito aut.
	 *
	 * @return the aplicacionParaDebitoAut
	 */
	public String getAplicacionParaDebitoAut() {
		return aplicacionParaDebitoAut;
	}

	/**
	 * Sets the aplicacion para debito aut.
	 *
	 * @param aplicacionParaDebitoAut
	 *            the aplicacionParaDebitoAut to set
	 */
	public void setAplicacionParaDebitoAut(String aplicacionParaDebitoAut) {
		this.aplicacionParaDebitoAut = aplicacionParaDebitoAut;
	}

	/**
	 * Gets the sucursal para debito aut.
	 *
	 * @return the sucursalParaDebitoAut
	 */
	public String getSucursalParaDebitoAut() {
		return sucursalParaDebitoAut;
	}

	/**
	 * Sets the sucursal para debito aut.
	 *
	 * @param sucursalParaDebitoAut
	 *            the sucursalParaDebitoAut to set
	 */
	public void setSucursalParaDebitoAut(String sucursalParaDebitoAut) {
		this.sucursalParaDebitoAut = sucursalParaDebitoAut;
	}

	/**
	 * Gets the tipo cuenta para debito aut.
	 *
	 * @return the tipoCuentaParaDebitoAut
	 */
	public String getTipoCuentaParaDebitoAut() {
		return tipoCuentaParaDebitoAut;
	}

	/**
	 * Sets the tipo cuenta para debito aut.
	 *
	 * @param tipoCuentaParaDebitoAut
	 *            the tipoCuentaParaDebitoAut to set
	 */
	public void setTipoCuentaParaDebitoAut(String tipoCuentaParaDebitoAut) {
		this.tipoCuentaParaDebitoAut = tipoCuentaParaDebitoAut;
	}

	/**
	 * Gets the numero cuenta para debito aut.
	 *
	 * @return the numeroCuentaParaDebitoAut
	 */
	public String getNumeroCuentaParaDebitoAut() {
		return numeroCuentaParaDebitoAut;
	}

	/**
	 * Sets the numero cuenta para debito aut.
	 *
	 * @param numeroCuentaParaDebitoAut
	 *            the numeroCuentaParaDebitoAut to set
	 */
	public void setNumeroCuentaParaDebitoAut(String numeroCuentaParaDebitoAut) {
		this.numeroCuentaParaDebitoAut = numeroCuentaParaDebitoAut;
	}

	/**
	 * Gets the numero firmante para debito aut.
	 *
	 * @return the numeroFirmanteParaDebitoAut
	 */
	public String getNumeroFirmanteParaDebitoAut() {
		return numeroFirmanteParaDebitoAut;
	}

	/**
	 * Sets the numero firmante para debito aut.
	 *
	 * @param numeroFirmanteParaDebitoAut
	 *            the numeroFirmanteParaDebitoAut to set
	 */
	public void setNumeroFirmanteParaDebitoAut(String numeroFirmanteParaDebitoAut) {
		this.numeroFirmanteParaDebitoAut = numeroFirmanteParaDebitoAut;
	}

	/**
	 * Gets the calle postal.
	 *
	 * @return the callePostal
	 */
	public String getCallePostal() {
		return callePostal;
	}

	/**
	 * Sets the calle postal.
	 *
	 * @param callePostal
	 *            the callePostal to set
	 */
	public void setCallePostal(String callePostal) {
		this.callePostal = callePostal;
	}

	/**
	 * Gets the numero postal.
	 *
	 * @return the numeroPostal
	 */
	public String getNumeroPostal() {
		return numeroPostal;
	}

	/**
	 * Sets the numero postal.
	 *
	 * @param numeroPostal
	 *            the numeroPostal to set
	 */
	public void setNumeroPostal(String numeroPostal) {
		this.numeroPostal = numeroPostal;
	}

	/**
	 * Gets the piso postal.
	 *
	 * @return the pisoPostal
	 */
	public String getPisoPostal() {
		return pisoPostal;
	}

	/**
	 * Sets the piso postal.
	 *
	 * @param pisoPostal
	 *            the pisoPostal to set
	 */
	public void setPisoPostal(String pisoPostal) {
		this.pisoPostal = pisoPostal;
	}

	/**
	 * Gets the depto postal.
	 *
	 * @return the deptoPostal
	 */
	public String getDeptoPostal() {
		return deptoPostal;
	}

	/**
	 * Sets the depto postal.
	 *
	 * @param deptoPostal
	 *            the deptoPostal to set
	 */
	public void setDeptoPostal(String deptoPostal) {
		this.deptoPostal = deptoPostal;
	}

	/**
	 * Gets the numero de documento O loc U of postal.
	 *
	 * @return the numeroDeDocumentoOLocUOfPostal
	 */
	public String getNumeroDeDocumentoOLocUOfPostal() {
		return numeroDeDocumentoOLocUOfPostal;
	}

	/**
	 * Sets the numero de documento O loc U of postal.
	 *
	 * @param numeroDeDocumentoOLocUOfPostal
	 *            the numeroDeDocumentoOLocUOfPostal to set
	 */
	public void setNumeroDeDocumentoOLocUOfPostal(String numeroDeDocumentoOLocUOfPostal) {
		this.numeroDeDocumentoOLocUOfPostal = numeroDeDocumentoOLocUOfPostal;
	}

	/**
	 * Gets the codigo postal.
	 *
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Sets the codigo postal.
	 *
	 * @param codigoPostal
	 *            the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * Gets the codigo geografico.
	 *
	 * @return the codigoGeografico
	 */
	public String getCodigoGeografico() {
		return codigoGeografico;
	}

	/**
	 * Sets the codigo geografico.
	 *
	 * @param codigoGeografico
	 *            the codigoGeografico to set
	 */
	public void setCodigoGeografico(String codigoGeografico) {
		this.codigoGeografico = codigoGeografico;
	}

	/**
	 * Gets the codigo de provincia.
	 *
	 * @return the codigoDeProvincia
	 */
	public String getCodigoDeProvincia() {
		return codigoDeProvincia;
	}

	/**
	 * Sets the codigo de provincia.
	 *
	 * @param codigoDeProvincia
	 *            the codigoDeProvincia to set
	 */
	public void setCodigoDeProvincia(String codigoDeProvincia) {
		this.codigoDeProvincia = codigoDeProvincia;
	}

	/**
	 * Gets the localidad partido postal.
	 *
	 * @return the localidadPartidoPostal
	 */
	public String getLocalidadPartidoPostal() {
		return localidadPartidoPostal;
	}

	/**
	 * Sets the localidad partido postal.
	 *
	 * @param localidadPartidoPostal
	 *            the localidadPartidoPostal to set
	 */
	public void setLocalidadPartidoPostal(String localidadPartidoPostal) {
		this.localidadPartidoPostal = localidadPartidoPostal;
	}

	/**
	 * Gets the domicilio postal descripcion.
	 *
	 * @return the domicilioPostalDescripcion
	 */
	public String getDomicilioPostalDescripcion() {
		return domicilioPostalDescripcion;
	}

	/**
	 * Sets the domicilio postal descripcion.
	 *
	 * @param domicilioPostalDescripcion
	 *            the domicilioPostalDescripcion to set
	 */
	public void setDomicilioPostalDescripcion(String domicilioPostalDescripcion) {
		this.domicilioPostalDescripcion = domicilioPostalDescripcion;
	}

	/**
	 * Gets the te.
	 *
	 * @return the te
	 */
	public String getTe() {
		return te;
	}

	/**
	 * Sets the te.
	 *
	 * @param te
	 *            the te to set
	 */
	public void setTe(String te) {
		this.te = te;
	}

	/**
	 * Gets the te contacto.
	 *
	 * @return the teContacto
	 */
	public String getTeContacto() {
		return teContacto;
	}

	/**
	 * Sets the te contacto.
	 *
	 * @param teContacto
	 *            the teContacto to set
	 */
	public void setTeContacto(String teContacto) {
		this.teContacto = teContacto;
	}

	/**
	 * Gets the descripcion forma de pago.
	 *
	 * @return the descripcionFormaDePago
	 */
	public String getDescripcionFormaDePago() {
		return descripcionFormaDePago;
	}

	/**
	 * Sets the descripcion forma de pago.
	 *
	 * @param descripcionFormaDePago
	 *            the descripcionFormaDePago to set
	 */
	public void setDescripcionFormaDePago(String descripcionFormaDePago) {
		this.descripcionFormaDePago = descripcionFormaDePago;
	}

	/**
	 * Gets the campaña.
	 *
	 * @return the campaña
	 */
	public String getCampaña() {
		return campaña;
	}

	/**
	 * Sets the campaña.
	 *
	 * @param campaña
	 *            the campaña to set
	 */
	public void setCampaña(String campaña) {
		this.campaña = campaña;
	}

	/**
	 * Gets the shot.
	 *
	 * @return the shot
	 */
	public String getShot() {
		return shot;
	}

	/**
	 * Sets the shot.
	 *
	 * @param shot
	 *            the shot to set
	 */
	public void setShot(String shot) {
		this.shot = shot;
	}

	/**
	 * Gets the canal venta.
	 *
	 * @return the canalVenta
	 */
	public String getCanalVenta() {
		return canalVenta;
	}

	/**
	 * Sets the canal venta.
	 *
	 * @param canalVenta
	 *            the canalVenta to set
	 */
	public void setCanalVenta(String canalVenta) {
		this.canalVenta = canalVenta;
	}

	/**
	 * Gets the descripcion canal venta.
	 *
	 * @return the descripcionCanalVenta
	 */
	public String getDescripcionCanalVenta() {
		return descripcionCanalVenta;
	}

	/**
	 * Sets the descripcion canal venta.
	 *
	 * @param descripcionCanalVenta
	 *            the descripcionCanalVenta to set
	 */
	public void setDescripcionCanalVenta(String descripcionCanalVenta) {
		this.descripcionCanalVenta = descripcionCanalVenta;
	}

	/**
	 * Gets the grupo de afinidad.
	 *
	 * @return the grupoDeAfinidad
	 */
	public String getGrupoDeAfinidad() {
		return grupoDeAfinidad;
	}

	/**
	 * Sets the grupo de afinidad.
	 *
	 * @param grupoDeAfinidad
	 *            the grupoDeAfinidad to set
	 */
	public void setGrupoDeAfinidad(String grupoDeAfinidad) {
		this.grupoDeAfinidad = grupoDeAfinidad;
	}

	/**
	 * Gets the descripcion grupo afinidad.
	 *
	 * @return the descripcionGrupoAfinidad
	 */
	public String getDescripcionGrupoAfinidad() {
		return descripcionGrupoAfinidad;
	}

	/**
	 * Sets the descripcion grupo afinidad.
	 *
	 * @param descripcionGrupoAfinidad
	 *            the descripcionGrupoAfinidad to set
	 */
	public void setDescripcionGrupoAfinidad(String descripcionGrupoAfinidad) {
		this.descripcionGrupoAfinidad = descripcionGrupoAfinidad;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fechaAlta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the fechaAlta to set
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * Gets the usuario alta.
	 *
	 * @return the usuarioAlta
	 */
	public String getUsuarioAlta() {
		return usuarioAlta;
	}

	/**
	 * Sets the usuario alta.
	 *
	 * @param usuarioAlta
	 *            the usuarioAlta to set
	 */
	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	/**
	 * Gets the fecha de baja.
	 *
	 * @return the fechaDeBaja
	 */
	public String getFechaDeBaja() {
		return fechaDeBaja;
	}

	/**
	 * Sets the fecha de baja.
	 *
	 * @param fechaDeBaja
	 *            the fechaDeBaja to set
	 */
	public void setFechaDeBaja(String fechaDeBaja) {
		this.fechaDeBaja = fechaDeBaja;
	}

	/**
	 * Gets the motivo de baja descripcion.
	 *
	 * @return the motivoDeBajaDescripcion
	 */
	public String getMotivoDeBajaDescripcion() {
		return motivoDeBajaDescripcion;
	}

	/**
	 * Sets the motivo de baja descripcion.
	 *
	 * @param motivoDeBajaDescripcion
	 *            the motivoDeBajaDescripcion to set
	 */
	public void setMotivoDeBajaDescripcion(String motivoDeBajaDescripcion) {
		this.motivoDeBajaDescripcion = motivoDeBajaDescripcion;
	}

	/**
	 * Gets the fecha ultima modificacion.
	 *
	 * @return the fechaUltimaModificacion
	 */
	public String getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	/**
	 * Sets the fecha ultima modificacion.
	 *
	 * @param fechaUltimaModificacion
	 *            the fechaUltimaModificacion to set
	 */
	public void setFechaUltimaModificacion(String fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	/**
	 * Gets the hora ultima modificacion.
	 *
	 * @return the horaUltimaModificacion
	 */
	public String getHoraUltimaModificacion() {
		return horaUltimaModificacion;
	}

	/**
	 * Sets the hora ultima modificacion.
	 *
	 * @param horaUltimaModificacion
	 *            the horaUltimaModificacion to set
	 */
	public void setHoraUltimaModificacion(String horaUltimaModificacion) {
		this.horaUltimaModificacion = horaUltimaModificacion;
	}

	/**
	 * Gets the usuario ultima modificacion.
	 *
	 * @return the usuarioUltimaModificacion
	 */
	public String getUsuarioUltimaModificacion() {
		return usuarioUltimaModificacion;
	}

	/**
	 * Sets the usuario ultima modificacion.
	 *
	 * @param usuarioUltimaModificacion
	 *            the usuarioUltimaModificacion to set
	 */
	public void setUsuarioUltimaModificacion(String usuarioUltimaModificacion) {
		this.usuarioUltimaModificacion = usuarioUltimaModificacion;
	}

	/**
	 * Gets the modelo liquidacion.
	 *
	 * @return the modeloLiquidacion
	 */
	public String getModeloLiquidacion() {
		return modeloLiquidacion;
	}

	/**
	 * Sets the modelo liquidacion.
	 *
	 * @param modeloLiquidacion
	 *            the modeloLiquidacion to set
	 */
	public void setModeloLiquidacion(String modeloLiquidacion) {
		this.modeloLiquidacion = modeloLiquidacion;
	}

	/**
	 * Gets the promotor.
	 *
	 * @return the promotor
	 */
	public String getPromotor() {
		return promotor;
	}

	/**
	 * Sets the promotor.
	 *
	 * @param promotor
	 *            the promotor to set
	 */
	public void setPromotor(String promotor) {
		this.promotor = promotor;
	}

	/**
	 * Gets the estado de cuenta.
	 *
	 * @return the estadoDeCuenta
	 */
	public String getEstadoDeCuenta() {
		return estadoDeCuenta;
	}

	/**
	 * Sets the estado de cuenta.
	 *
	 * @param estadoDeCuenta
	 *            the estadoDeCuenta to set
	 */
	public void setEstadoDeCuenta(String estadoDeCuenta) {
		this.estadoDeCuenta = estadoDeCuenta;
	}

	/**
	 * Gets the descripcion estado cuenta.
	 *
	 * @return the descripcionEstadoCuenta
	 */
	public String getDescripcionEstadoCuenta() {
		return descripcionEstadoCuenta;
	}

	/**
	 * Sets the descripcion estado cuenta.
	 *
	 * @param descripcionEstadoCuenta
	 *            the descripcionEstadoCuenta to set
	 */
	public void setDescripcionEstadoCuenta(String descripcionEstadoCuenta) {
		this.descripcionEstadoCuenta = descripcionEstadoCuenta;
	}

	/**
	 * Gets the cartera 2.
	 *
	 * @return the cartera_2
	 */
	public String getCartera_2() {
		return cartera_2;
	}

	/**
	 * Sets the cartera 2.
	 *
	 * @param cartera_2
	 *            the cartera_2 to set
	 */
	public void setCartera_2(String cartera_2) {
		this.cartera_2 = cartera_2;
	}

	/**
	 * Gets the descripcion cartera.
	 *
	 * @return the descripcionCartera
	 */
	public String getDescripcionCartera() {
		return descripcionCartera;
	}

	/**
	 * Sets the descripcion cartera.
	 *
	 * @param descripcionCartera
	 *            the descripcionCartera to set
	 */
	public void setDescripcionCartera(String descripcionCartera) {
		this.descripcionCartera = descripcionCartera;
	}

	/**
	 * Gets the origen ultimo movimiento.
	 *
	 * @return the origenUltimoMovimiento
	 */
	public String getOrigenUltimoMovimiento() {
		return origenUltimoMovimiento;
	}

	/**
	 * Sets the origen ultimo movimiento.
	 *
	 * @param origenUltimoMovimiento
	 *            the origenUltimoMovimiento to set
	 */
	public void setOrigenUltimoMovimiento(String origenUltimoMovimiento) {
		this.origenUltimoMovimiento = origenUltimoMovimiento;
	}

	/**
	 * Gets the moneda limites.
	 *
	 * @return the monedaLimites
	 */
	public String getMonedaLimites() {
		return monedaLimites;
	}

	/**
	 * Sets the moneda limites.
	 *
	 * @param monedaLimites
	 *            the monedaLimites to set
	 */
	public void setMonedaLimites(String monedaLimites) {
		this.monedaLimites = monedaLimites;
	}

}
