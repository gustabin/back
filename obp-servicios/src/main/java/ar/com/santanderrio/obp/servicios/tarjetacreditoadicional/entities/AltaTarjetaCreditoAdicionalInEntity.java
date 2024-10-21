/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities;

import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class AltaTarjetaCreditoAdicionalInEntity.
 */
public class AltaTarjetaCreditoAdicionalInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** The funcion. */
	private String funcion;

	/** The nro cuenta tarjeta. */
	private String nroCuentaTarjeta;

	/** The aplicacion cuenta relacionada. */
	private String aplicacionCuentaRelacionada;

	/** The sucursal cuenta relacionada. */
	private String sucursalCuentaRelacionada;

	/** The tipo cuenta cuenta relacionada. */
	private String tipoCuentaCuentaRelacionada;

	/** The numero de cuenta relacionada. */
	private String numeroDeCuentaRelacionada;

	/** The firmante cuenta relacionada. */
	private String firmanteCuentaRelacionada;

	/** The usuario operacion. */
	private String usuarioOperacion;

	/** The origen operacion. */
	private String origenOperacion;

	/** The sucursal usuario. */
	private String sucursalUsuario;

	/** The centro de costo. */
	private String centroDeCosto;

	/** The codigo de comercio. */
	private String codigoDeComercio;

	/** The codigo sucursal. */
	private String codigoSucursal;

	/** The codigo compra. */
	private String codigoCompra;

	/** The codigo producto. */
	private String codigoProducto;

	/** The codigo categoria. */
	private String codigoCategoria;

	/** The codigo cartera. */
	private String codigoCartera;

	/** The apellido Y nombre. */
	private String apellidoYNombre;

	/** The secuencia domicilio. */
	private String secuenciaDomicilio;

	/** The secuencia telefono. */
	private String secuenciaTelefono;

	/** The grupo afinidad. */
	private String grupoAfinidad;

	/** The canal venta. */
	private String canalVenta;

	/** The usuario venta. */
	private String usuarioVenta;

	/** The forma pago. */
	private String formaPago;

	/** The cuenta pago aplicacion. */
	private String cuentaPagoAplicacion;

	/** The cuenta pago sucursal. */
	private String cuentaPagoSucursal;

	/** The cuenta de pago tipo de cuenta. */
	private String cuentaDePagoTipoDeCuenta;

	/** The cuenta de pago numero. */
	private String cuentaDePagoNumero;

	/** The cuenta de pago firmante. */
	private String cuentaDePagoFirmante;

	/** The porcentaje limite compra. */
	private String porcentajeLimiteCompra;

	/** The importe limite credito. */
	private String importeLimiteCredito;

	/** The cargo renovacion. */
	private String cargoRenovacion;

	/** The cargo renovacion cuotas. */
	private String cargoRenovacionCuotas;

	/** The cargo MB. */
	private String cargoMB;

	/** The tipo paquete. */
	private String tipoPaquete;

	/** The plazo pago minimo. */
	private String plazoPagoMinimo;

	/** The empresa. */
	private String empresa;

	/** The modelo liquidacion. */
	private String modeloLiquidacion;

	/** The planta. */
	private String planta;

	/** The compañia seguro. */
	private String compañiaSeguro;

	/** The tipo persona. */
	private String tipoPersona;

	/** The marca. */
	private String marca;

	/** The control limite por tarjeta. */
	private String controlLimitePorTarjeta;

	/** The nup. */
	private String nup;

	/** The cantidad tarjetas. */
	private String cantidadTarjetas;

	/** The datos tarjetas. */
	private List<DatosTarjetaInEntity> datosTarjetas;

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the funcion.
	 *
	 * @return the funcion
	 */
	public String getFuncion() {
		return funcion;
	}

	/**
	 * Sets the funcion.
	 *
	 * @param funcion
	 *            the funcion to set
	 */
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	/**
	 * Gets the nro cuenta tarjeta.
	 *
	 * @return the nroCuentaTarjeta
	 */
	public String getNroCuentaTarjeta() {
		return nroCuentaTarjeta;
	}

	/**
	 * Sets the nro cuenta tarjeta.
	 *
	 * @param nroCuentaTarjeta
	 *            the nroCuentaTarjeta to set
	 */
	public void setNroCuentaTarjeta(String nroCuentaTarjeta) {
		this.nroCuentaTarjeta = nroCuentaTarjeta;
	}

	/**
	 * Gets the aplicacion cuenta relacionada.
	 *
	 * @return the aplicacionCuentaRelacionada
	 */
	public String getAplicacionCuentaRelacionada() {
		return aplicacionCuentaRelacionada;
	}

	/**
	 * Sets the aplicacion cuenta relacionada.
	 *
	 * @param aplicacionCuentaRelacionada
	 *            the aplicacionCuentaRelacionada to set
	 */
	public void setAplicacionCuentaRelacionada(String aplicacionCuentaRelacionada) {
		this.aplicacionCuentaRelacionada = aplicacionCuentaRelacionada;
	}

	/**
	 * Gets the sucursal cuenta relacionada.
	 *
	 * @return the sucursalCuentaRelacionada
	 */
	public String getSucursalCuentaRelacionada() {
		return sucursalCuentaRelacionada;
	}

	/**
	 * Sets the sucursal cuenta relacionada.
	 *
	 * @param sucursalCuentaRelacionada
	 *            the sucursalCuentaRelacionada to set
	 */
	public void setSucursalCuentaRelacionada(String sucursalCuentaRelacionada) {
		this.sucursalCuentaRelacionada = sucursalCuentaRelacionada;
	}

	/**
	 * Gets the tipo cuenta cuenta relacionada.
	 *
	 * @return the tipoCuentaCuentaRelacionada
	 */
	public String getTipoCuentaCuentaRelacionada() {
		return tipoCuentaCuentaRelacionada;
	}

	/**
	 * Sets the tipo cuenta cuenta relacionada.
	 *
	 * @param tipoCuentaCuentaRelacionada
	 *            the tipoCuentaCuentaRelacionada to set
	 */
	public void setTipoCuentaCuentaRelacionada(String tipoCuentaCuentaRelacionada) {
		this.tipoCuentaCuentaRelacionada = tipoCuentaCuentaRelacionada;
	}

	/**
	 * Gets the numero de cuenta relacionada.
	 *
	 * @return the numeroDeCuentaRelacionada
	 */
	public String getNumeroDeCuentaRelacionada() {
		return numeroDeCuentaRelacionada;
	}

	/**
	 * Sets the numero de cuenta relacionada.
	 *
	 * @param numeroDeCuentaRelacionada
	 *            the numeroDeCuentaRelacionada to set
	 */
	public void setNumeroDeCuentaRelacionada(String numeroDeCuentaRelacionada) {
		this.numeroDeCuentaRelacionada = numeroDeCuentaRelacionada;
	}

	/**
	 * Gets the firmante cuenta relacionada.
	 *
	 * @return the firmanteCuentaRelacionada
	 */
	public String getFirmanteCuentaRelacionada() {
		return firmanteCuentaRelacionada;
	}

	/**
	 * Sets the firmante cuenta relacionada.
	 *
	 * @param firmanteCuentaRelacionada
	 *            the firmanteCuentaRelacionada to set
	 */
	public void setFirmanteCuentaRelacionada(String firmanteCuentaRelacionada) {
		this.firmanteCuentaRelacionada = firmanteCuentaRelacionada;
	}

	/**
	 * Gets the usuario operacion.
	 *
	 * @return the usuarioOperacion
	 */
	public String getUsuarioOperacion() {
		return usuarioOperacion;
	}

	/**
	 * Sets the usuario operacion.
	 *
	 * @param usuarioOperacion
	 *            the usuarioOperacion to set
	 */
	public void setUsuarioOperacion(String usuarioOperacion) {
		this.usuarioOperacion = usuarioOperacion;
	}

	/**
	 * Gets the origen operacion.
	 *
	 * @return the origenOperacion
	 */
	public String getOrigenOperacion() {
		return origenOperacion;
	}

	/**
	 * Sets the origen operacion.
	 *
	 * @param origenOperacion
	 *            the origenOperacion to set
	 */
	public void setOrigenOperacion(String origenOperacion) {
		this.origenOperacion = origenOperacion;
	}

	/**
	 * Gets the sucursal usuario.
	 *
	 * @return the sucursalUsuario
	 */
	public String getSucursalUsuario() {
		return sucursalUsuario;
	}

	/**
	 * Sets the sucursal usuario.
	 *
	 * @param sucursalUsuario
	 *            the sucursalUsuario to set
	 */
	public void setSucursalUsuario(String sucursalUsuario) {
		this.sucursalUsuario = sucursalUsuario;
	}

	/**
	 * Gets the codigo de comercio.
	 *
	 * @return the codigoDeComercio
	 */
	public String getCodigoDeComercio() {
		return codigoDeComercio;
	}

	/**
	 * Sets the codigo de comercio.
	 *
	 * @param codigoDeComercio
	 *            the codigoDeComercio to set
	 */
	public void setCodigoDeComercio(String codigoDeComercio) {
		this.codigoDeComercio = codigoDeComercio;
	}

	/**
	 * Gets the codigo sucursal.
	 *
	 * @return the codigoSucursal
	 */
	public String getCodigoSucursal() {
		return codigoSucursal;
	}

	/**
	 * Sets the codigo sucursal.
	 *
	 * @param codigoSucursal
	 *            the codigoSucursal to set
	 */
	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	/**
	 * Gets the codigo compra.
	 *
	 * @return the codigoCompra
	 */
	public String getCodigoCompra() {
		return codigoCompra;
	}

	/**
	 * Sets the codigo compra.
	 *
	 * @param codigoCompra
	 *            the codigoCompra to set
	 */
	public void setCodigoCompra(String codigoCompra) {
		this.codigoCompra = codigoCompra;
	}

	/**
	 * Gets the codigo producto.
	 *
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * Sets the codigo producto.
	 *
	 * @param codigoProducto
	 *            the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * Gets the codigo categoria.
	 *
	 * @return the codigoCategoria
	 */
	public String getCodigoCategoria() {
		return codigoCategoria;
	}

	/**
	 * Sets the codigo categoria.
	 *
	 * @param codigoCategoria
	 *            the codigoCategoria to set
	 */
	public void setCodigoCategoria(String codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	/**
	 * Gets the codigo cartera.
	 *
	 * @return the codigoCartera
	 */
	public String getCodigoCartera() {
		return codigoCartera;
	}

	/**
	 * Sets the codigo cartera.
	 *
	 * @param codigoCartera
	 *            the codigoCartera to set
	 */
	public void setCodigoCartera(String codigoCartera) {
		this.codigoCartera = codigoCartera;
	}

	/**
	 * Gets the apellido Y nombre.
	 *
	 * @return the apellidoYNombre
	 */
	public String getApellidoYNombre() {
		return apellidoYNombre;
	}

	/**
	 * Sets the apellido Y nombre.
	 *
	 * @param apellidoYNombre
	 *            the apellidoYNombre to set
	 */
	public void setApellidoYNombre(String apellidoYNombre) {
		this.apellidoYNombre = apellidoYNombre;
	}

	/**
	 * Gets the secuencia domicilio.
	 *
	 * @return the secuenciaDomicilio
	 */
	public String getSecuenciaDomicilio() {
		return secuenciaDomicilio;
	}

	/**
	 * Sets the secuencia domicilio.
	 *
	 * @param secuenciaDomicilio
	 *            the secuenciaDomicilio to set
	 */
	public void setSecuenciaDomicilio(String secuenciaDomicilio) {
		this.secuenciaDomicilio = secuenciaDomicilio;
	}

	/**
	 * Gets the secuencia telefono.
	 *
	 * @return the secuenciaTelefono
	 */
	public String getSecuenciaTelefono() {
		return secuenciaTelefono;
	}

	/**
	 * Sets the secuencia telefono.
	 *
	 * @param secuenciaTelefono
	 *            the secuenciaTelefono to set
	 */
	public void setSecuenciaTelefono(String secuenciaTelefono) {
		this.secuenciaTelefono = secuenciaTelefono;
	}

	/**
	 * Gets the grupo afinidad.
	 *
	 * @return the grupoAfinidad
	 */
	public String getGrupoAfinidad() {
		return grupoAfinidad;
	}

	/**
	 * Sets the grupo afinidad.
	 *
	 * @param grupoAfinidad
	 *            the grupoAfinidad to set
	 */
	public void setGrupoAfinidad(String grupoAfinidad) {
		this.grupoAfinidad = grupoAfinidad;
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
	 * Gets the usuario venta.
	 *
	 * @return the usuarioVenta
	 */
	public String getUsuarioVenta() {
		return usuarioVenta;
	}

	/**
	 * Sets the usuario venta.
	 *
	 * @param usuarioVenta
	 *            the usuarioVenta to set
	 */
	public void setUsuarioVenta(String usuarioVenta) {
		this.usuarioVenta = usuarioVenta;
	}

	/**
	 * Gets the forma pago.
	 *
	 * @return the formaPago
	 */
	public String getFormaPago() {
		return formaPago;
	}

	/**
	 * Sets the forma pago.
	 *
	 * @param formaPago
	 *            the formaPago to set
	 */
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	/**
	 * Gets the cuenta pago aplicacion.
	 *
	 * @return the cuentaPagoAplicacion
	 */
	public String getCuentaPagoAplicacion() {
		return cuentaPagoAplicacion;
	}

	/**
	 * Sets the cuenta pago aplicacion.
	 *
	 * @param cuentaPagoAplicacion
	 *            the cuentaPagoAplicacion to set
	 */
	public void setCuentaPagoAplicacion(String cuentaPagoAplicacion) {
		this.cuentaPagoAplicacion = cuentaPagoAplicacion;
	}

	/**
	 * Gets the cuenta pago sucursal.
	 *
	 * @return the cuentaPagoSucursal
	 */
	public String getCuentaPagoSucursal() {
		return cuentaPagoSucursal;
	}

	/**
	 * Sets the cuenta pago sucursal.
	 *
	 * @param cuentaPagoSucursal
	 *            the cuentaPagoSucursal to set
	 */
	public void setCuentaPagoSucursal(String cuentaPagoSucursal) {
		this.cuentaPagoSucursal = cuentaPagoSucursal;
	}

	/**
	 * Gets the cuenta de pago tipo de cuenta.
	 *
	 * @return the cuentaDePagoTipoDeCuenta
	 */
	public String getCuentaDePagoTipoDeCuenta() {
		return cuentaDePagoTipoDeCuenta;
	}

	/**
	 * Sets the cuenta de pago tipo de cuenta.
	 *
	 * @param cuentaDePagoTipoDeCuenta
	 *            the cuentaDePagoTipoDeCuenta to set
	 */
	public void setCuentaDePagoTipoDeCuenta(String cuentaDePagoTipoDeCuenta) {
		this.cuentaDePagoTipoDeCuenta = cuentaDePagoTipoDeCuenta;
	}

	/**
	 * Gets the cuenta de pago numero.
	 *
	 * @return the cuentaDePagoNumero
	 */
	public String getCuentaDePagoNumero() {
		return cuentaDePagoNumero;
	}

	/**
	 * Sets the cuenta de pago numero.
	 *
	 * @param cuentaDePagoNumero
	 *            the cuentaDePagoNumero to set
	 */
	public void setCuentaDePagoNumero(String cuentaDePagoNumero) {
		this.cuentaDePagoNumero = cuentaDePagoNumero;
	}

	/**
	 * Gets the cuenta de pago firmante.
	 *
	 * @return the cuentaDePagoFirmante
	 */
	public String getCuentaDePagoFirmante() {
		return cuentaDePagoFirmante;
	}

	/**
	 * Sets the cuenta de pago firmante.
	 *
	 * @param cuentaDePagoFirmante
	 *            the cuentaDePagoFirmante to set
	 */
	public void setCuentaDePagoFirmante(String cuentaDePagoFirmante) {
		this.cuentaDePagoFirmante = cuentaDePagoFirmante;
	}

	/**
	 * Gets the centro de costo.
	 *
	 * @return the centro de costo
	 */
	public String getCentroDeCosto() {
		return centroDeCosto;
	}

	/**
	 * Sets the centro de costo.
	 *
	 * @param centroDeCosto
	 *            the new centro de costo
	 */
	public void setCentroDeCosto(String centroDeCosto) {
		this.centroDeCosto = centroDeCosto;
	}

	/**
	 * Gets the porcentaje limite compra.
	 *
	 * @return the porcentaje limite compra
	 */
	public String getPorcentajeLimiteCompra() {
		return porcentajeLimiteCompra;
	}

	/**
	 * Sets the porcentaje limite compra.
	 *
	 * @param porcentajeLimiteCompra
	 *            the new porcentaje limite compra
	 */
	public void setPorcentajeLimiteCompra(String porcentajeLimiteCompra) {
		this.porcentajeLimiteCompra = porcentajeLimiteCompra;
	}

	/**
	 * Gets the importe limite credito.
	 *
	 * @return the importeLimiteCredito
	 */
	public String getImporteLimiteCredito() {
		return importeLimiteCredito;
	}

	/**
	 * Sets the importe limite credito.
	 *
	 * @param importeLimiteCredito
	 *            the importeLimiteCredito to set
	 */
	public void setImporteLimiteCredito(String importeLimiteCredito) {
		this.importeLimiteCredito = importeLimiteCredito;
	}

	/**
	 * Gets the cargo renovacion.
	 *
	 * @return the cargoRenovacion
	 */
	public String getCargoRenovacion() {
		return cargoRenovacion;
	}

	/**
	 * Sets the cargo renovacion.
	 *
	 * @param cargoRenovacion
	 *            the cargoRenovacion to set
	 */
	public void setCargoRenovacion(String cargoRenovacion) {
		this.cargoRenovacion = cargoRenovacion;
	}

	/**
	 * Gets the cargo renovacion cuotas.
	 *
	 * @return the cargoRenovacionCuotas
	 */
	public String getCargoRenovacionCuotas() {
		return cargoRenovacionCuotas;
	}

	/**
	 * Sets the cargo renovacion cuotas.
	 *
	 * @param cargoRenovacionCuotas
	 *            the cargoRenovacionCuotas to set
	 */
	public void setCargoRenovacionCuotas(String cargoRenovacionCuotas) {
		this.cargoRenovacionCuotas = cargoRenovacionCuotas;
	}

	/**
	 * Gets the cargo MB.
	 *
	 * @return the cargoMB
	 */
	public String getCargoMB() {
		return cargoMB;
	}

	/**
	 * Sets the cargo MB.
	 *
	 * @param cargoMB
	 *            the cargoMB to set
	 */
	public void setCargoMB(String cargoMB) {
		this.cargoMB = cargoMB;
	}

	/**
	 * Gets the tipo paquete.
	 *
	 * @return the tipoPaquete
	 */
	public String getTipoPaquete() {
		return tipoPaquete;
	}

	/**
	 * Sets the tipo paquete.
	 *
	 * @param tipoPaquete
	 *            the tipoPaquete to set
	 */
	public void setTipoPaquete(String tipoPaquete) {
		this.tipoPaquete = tipoPaquete;
	}

	/**
	 * Gets the plazo pago minimo.
	 *
	 * @return the plazoPagoMinimo
	 */
	public String getPlazoPagoMinimo() {
		return plazoPagoMinimo;
	}

	/**
	 * Sets the plazo pago minimo.
	 *
	 * @param plazoPagoMinimo
	 *            the plazoPagoMinimo to set
	 */
	public void setPlazoPagoMinimo(String plazoPagoMinimo) {
		this.plazoPagoMinimo = plazoPagoMinimo;
	}

	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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
	 * Gets the planta.
	 *
	 * @return the planta
	 */
	public String getPlanta() {
		return planta;
	}

	/**
	 * Sets the planta.
	 *
	 * @param planta
	 *            the planta to set
	 */
	public void setPlanta(String planta) {
		this.planta = planta;
	}

	/**
	 * Gets the compañia seguro.
	 *
	 * @return the compañiaSeguro
	 */
	public String getCompañiaSeguro() {
		return compañiaSeguro;
	}

	/**
	 * Sets the compañia seguro.
	 *
	 * @param compañiaSeguro
	 *            the compañiaSeguro to set
	 */
	public void setCompañiaSeguro(String compañiaSeguro) {
		this.compañiaSeguro = compañiaSeguro;
	}

	/**
	 * Gets the tipo persona.
	 *
	 * @return the tipoPersona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}

	/**
	 * Sets the tipo persona.
	 *
	 * @param tipoPersona
	 *            the tipoPersona to set
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	/**
	 * Gets the marca.
	 *
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Sets the marca.
	 *
	 * @param marca
	 *            the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Gets the control limite por tarjeta.
	 *
	 * @return the controlLimitePorTarjeta
	 */
	public String getControlLimitePorTarjeta() {
		return controlLimitePorTarjeta;
	}

	/**
	 * Sets the control limite por tarjeta.
	 *
	 * @param controlLimitePorTarjeta
	 *            the controlLimitePorTarjeta to set
	 */
	public void setControlLimitePorTarjeta(String controlLimitePorTarjeta) {
		this.controlLimitePorTarjeta = controlLimitePorTarjeta;
	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the cantidad tarjetas.
	 *
	 * @return the cantidadTarjetas
	 */
	public String getCantidadTarjetas() {
		return cantidadTarjetas;
	}

	/**
	 * Sets the cantidad tarjetas.
	 *
	 * @param cantidadTarjetas
	 *            the cantidadTarjetas to set
	 */
	public void setCantidadTarjetas(String cantidadTarjetas) {
		this.cantidadTarjetas = cantidadTarjetas;
	}

	/**
	 * Gets the datos tarjetas.
	 *
	 * @return the datosTarjetas
	 */
	public List<DatosTarjetaInEntity> getDatosTarjetas() {
		return datosTarjetas;
	}

	/**
	 * Sets the datos tarjetas.
	 *
	 * @param datosTarjetas
	 *            the datosTarjetas to set
	 */
	public void setDatosTarjetas(List<DatosTarjetaInEntity> datosTarjetas) {
		this.datosTarjetas = datosTarjetas;
	}

}
