/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;

/**
 * The Class PagoPendiente.
 */
public class PagoPendiente extends Entity implements Comparable<PagoPendiente> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codigo empresa. */
	private String codigoEmpresa = "";

	/** The nombre empresa. */
	@Deprecated // usar MedioPago
	private String nombreEmpresa;

	/** The nombre empresa abreviado. Se usa para las tarjetas de credito */
	private String nombreEmpresaAbreviado;

	/** The codigo cliente empresa. */
	private String codigoClienteEmpresa = "";

	/** The vencimiento. */
	private Date vencimiento;

	/** The fecha pago programado. */
	private Date fechaPagoProgramado;

	/** The divisa. */
	private DivisaEnum divisa;

	/** The importe. */
	private BigDecimal importe;

	/** The importe USS. */
	private BigDecimal importeUSS = BigDecimal.ZERO;

	/** The identificacion de factura. */
	private String identificacionDeFactura = "";

	/** The tipo pago empresa. */
	private TipoPagoEmpresaPMC tipoPagoEmpresa;

	/** The tipo pago. */
	private TipoDePagoEnum tipoPago;

	/** The editable. */
	private boolean editable = false;

	/** The numero cuotas. */
	private String numeroCuotas;

	/** The cantidad cuotas. */
	private String cantidadCuotas;

	/** The tipo prestamo. */
	private String tipoPrestamo;

	/** The pago minimo. */
	private BigDecimal pagoMinimo;

	/** The descripcion. */
	private String descripcion = "";

	/** The informacion adicional. */
	private String informacionAdicional = "";

	/** The datos adicionales. */
	private String datosAdicionales = "";

	/** The datos pago automatico. */
	// DatosEntity de pago automatico
	private DatosPagoAutomaticoEntity datosPagoAutomatico;

	/** The identificador fecha stop. */
	private String identificadorFechaStop;

	/** The cuit empresa. */
	@Deprecated // usar MedioPago
	private String cuitEmpresa;

	/** The orden firmante. */
	private String ordenFirmante;

	/** The nombre empresa iatx. */
	private String nombreEmpresaIatx;

	/** The numero cuenta banco dolares. */
	private Integer numeroCuentaBancoDolares;

	/** The numero cuenta banco pesos. */
	private Integer numeroCuentaBancoPesos;

	/** The numero de VEP, 12 primeros digitos de factura. */
	private String numeroDeVEP;

	/** The tipo cuenta tarjeta. */
	private TipoCuentaTarjeta tipoCuentaTarjeta;

	/** Periodo. */
	private String periodo;
	
	/** The editableDetalle. */
	private boolean editableDetalle;

	/**
	 * Nro de Tarjeta de credito utilizado para la eliminacion de pago
	 * programado de tarjeta de credito.
	 */
	private String nroTarjetaCredito;

	/**
	 * Medio de Pago, se utiliza para transportar todos los datos de la empresa,
	 * que luego se pasaran a MedioPagoView.
	 */
	private MedioPago medioPago;

	/** numero Cuenta de TarjetaCredito. */
	private String numeroCuentaDeTarjetaCredito;

	/** Informacion de CNSTJCPAGP 100 tipo cuenta banco pesos. */
	private String tipoCuentaBancoPesos;

	/** tipo cuenta banco pesos. */
	private String tipoCuentaBancoDolares;

	/** Sucursale banco pesos. */
	private String sucursalBancoPesos;

	/** Sucursale banco dolares. */
	private String sucursalBancoDolares;

	/** The mensaje. */
	private String nroCuentaOrigen;

	/** frecuencia recarga. */
	private String frecuenciaRecarga;

	/** nro cuenta debto. */
	private String aliasCuentaDebito;

	/** nro cuenta producto. */
	private String fechaInicioRecarga;

	/** nro cuenta producto. */
	private String nroCuentaProducto;

	/** fedcha Proxima Recarga. */
	private String fechaProxRecarga;

	/** nro cuenta debto. */
	private String nroCuentaDebito;

	/** tipo cuenta debito. */
	private String tipoCuentaDebito;

	/** campo alias de tarjeta de credito. */
	private boolean alias = false;
	
	/** The tiene stop debit. */
	private Boolean tieneStopDebit = Boolean.FALSE;

	/** The habilitar stop debit. */
	private Boolean habilitarStopDebit = Boolean.FALSE;

	/**
	 * Instantiates a new pago pendiente.
	 */
	public PagoPendiente() {
		super();
	}

	/**
	 * Instantiates a new pago pendiente basado en un PagoView.
	 *
	 * @param unPago
	 *            the un pago
	 */
	public PagoPendiente(PagoMultipleView unPago) {
		this.codigoEmpresa = unPago.getCodigoEmpresa();
		this.codigoClienteEmpresa = unPago.getIdentificacionCliente();
		this.cuitEmpresa = unPago.getCuitEmpleador();
		this.identificacionDeFactura = unPago.getNumeroFactura();
		this.importe = new BigDecimal(unPago.getMonto());
		this.importeUSS = new BigDecimal(unPago.getMonto());
		this.setDivisa(DivisaEnum.PESO);
	}

	/**
	 * Gets the informacion adicional.
	 *
	 * @return the informacion adicional
	 */
	public String getInformacionAdicional() {
		return informacionAdicional;
	}

	/**
	 * Sets the informacion adicional.
	 *
	 * @param informacionAdicional
	 *            the new informacion adicional
	 */
	public void setInformacionAdicional(String informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
	}

	/**
	 * Gets the datos adicionales.
	 *
	 * @return the datos adicionales
	 */
	public String getDatosAdicionales() {
		return datosAdicionales;
	}

	/**
	 * Sets the datos adicionales.
	 *
	 * @param datosAdicionales
	 *            the new datos adicionales
	 */
	public void setDatosAdicionales(String datosAdicionales) {
		this.datosAdicionales = datosAdicionales;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the codigo empresa.
	 *
	 * @return the codigo empresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * Sets the codigo empresa.
	 *
	 * @param codigoEmpresa
	 *            the new codigo empresa
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
	 * Gets the nombre empresa.
	 *
	 * @return the nombre empresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * Sets the nombre empresa.
	 *
	 * @param nombreEmpresa
	 *            the new nombre empresa
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * Gets the nombre empresa abreviado.
	 *
	 * @return the nombre empresa abreviado
	 */
	public String getNombreEmpresaAbreviado() {
		return nombreEmpresaAbreviado;
	}

	/**
	 * Sets the nombre empresa abreviado.
	 *
	 * @param nombreEmpresaAbreviado
	 *            the new nombre empresa abreviado
	 */
	public void setNombreEmpresaAbreviado(String nombreEmpresaAbreviado) {
		this.nombreEmpresaAbreviado = nombreEmpresaAbreviado;
	}

	/**
	 * Gets the codigo cliente empresa.
	 *
	 * @return the codigo cliente empresa
	 */
	public String getCodigoClienteEmpresa() {
		return codigoClienteEmpresa;
	}

	/**
	 * Sets the codigo cliente empresa.
	 *
	 * @param codigoClienteEmpresa
	 *            the new codigo cliente empresa
	 */
	public void setCodigoClienteEmpresa(String codigoClienteEmpresa) {
		this.codigoClienteEmpresa = codigoClienteEmpresa;
	}

	/**
	 * Gets the vencimiento.
	 *
	 * @return the vencimiento
	 */
	public Date getVencimiento() {
		return vencimiento == null ? null : new Date(vencimiento.getTime());
	}

	/**
	 * Sets the vencimiento.
	 *
	 * @param vencimiento
	 *            the new vencimiento
	 */
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento == null ? null : new Date(vencimiento.getTime());
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public DivisaEnum getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the new divisa
	 */
	public void setDivisa(DivisaEnum divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Checks if is pago automatico.
	 *
	 * @return true, if is pago automatico
	 */
	public boolean isPagoAutomatico() {
		return datosPagoAutomatico != null;
	}

	/**
	 * Gets the identificacion de factura.
	 *
	 * @return the identificacion de factura
	 */
	public String getIdentificacionDeFactura() {
		return identificacionDeFactura;
	}

	/**
	 * Sets the identificacion de factura.
	 *
	 * @param identificacionDeFactura
	 *            the new identificacion de factura
	 */
	public void setIdentificacionDeFactura(String identificacionDeFactura) {
		this.identificacionDeFactura = identificacionDeFactura;
	}

	/**
	 * Gets the tipo pago empresa.
	 *
	 * @return the tipo pago empresa
	 */
	public TipoPagoEmpresaPMC getTipoPagoEmpresa() {
		return tipoPagoEmpresa;
	}

	/**
	 * Sets the tipo pago empresa.
	 *
	 * @param tipoPagoEmpresa
	 *            the new tipo pago empresa
	 */
	public void setTipoPagoEmpresa(TipoPagoEmpresaPMC tipoPagoEmpresa) {
		this.tipoPagoEmpresa = tipoPagoEmpresa;
	}

	/**
	 * Checks if is editable.
	 *
	 * @return true, if is editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * Sets the editable.
	 *
	 * @param editable
	 *            the new editable
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * Gets the tipo pago.
	 *
	 * @return the tipoPago
	 */
	public TipoDePagoEnum getTipoPago() {
		return tipoPago;
	}

	/**
	 * Sets the tipo pago.
	 *
	 * @param tipoPago
	 *            the tipoPago to set
	 */
	public void setTipoPago(TipoDePagoEnum tipoPago) {
		this.tipoPago = tipoPago;
	}

	/**
	 * Gets the numero cuotas.
	 *
	 * @return the numeroCuotas
	 */
	public String getNumeroCuotas() {
		return numeroCuotas;
	}

	/**
	 * Sets the numero cuotas.
	 *
	 * @param numeroCuotas
	 *            the numeroCuotas to set
	 */
	public void setNumeroCuotas(String numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}

	/**
	 * Gets the cantidad cuotas.
	 *
	 * @return the cantidadCuotas
	 */
	public String getCantidadCuotas() {
		return cantidadCuotas;
	}

	/**
	 * Sets the cantidad cuotas.
	 *
	 * @param cantidadCuotas
	 *            the cantidadCuotas to set
	 */
	public void setCantidadCuotas(String cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	/**
	 * Gets the tipo prestamo.
	 *
	 * @return the tipoPrestamo
	 */
	public String getTipoPrestamo() {
		return tipoPrestamo;
	}

	/**
	 * Sets the tipo prestamo.
	 *
	 * @param tipoPrestamo
	 *            the tipoPrestamo to set
	 */
	public void setTipoPrestamo(String tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PagoPendiente [codigoEmpresa=");
		builder.append(codigoEmpresa);
		builder.append(", nombreEmpresa=");
		builder.append(nombreEmpresa);
		builder.append(", codigoClienteEmpresa=");
		builder.append(codigoClienteEmpresa);
		builder.append(", vencimiento=");
		builder.append(vencimiento);
		builder.append(", divisa=");
		builder.append(divisa);
		builder.append(", importe=");
		builder.append(importe);
		builder.append(", pagoAutomatico=");
		builder.append(this.isPagoAutomatico());
		builder.append(", identificacionDeFactura=");
		builder.append(identificacionDeFactura);
		builder.append(", tipoPagoEmpresa=");
		builder.append(tipoPagoEmpresa);
		builder.append(", tipoPago=");
		builder.append(tipoPago);
		builder.append(periodo);
		builder.append(cantidadCuotas);
		builder.append("]");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder().append(codigoEmpresa).append(vencimiento);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PagoPendiente other = (PagoPendiente) obj;
		if (vencimiento == null) {
			if (other.vencimiento != null) {
				return false;
			}
		} else if (!vencimiento.equals(other.vencimiento)) {
			return false;
		}
		if (codigoEmpresa == null) {
			if (other.codigoEmpresa != null) {
				return false;
			}
		} else if (!codigoEmpresa.equals(other.codigoEmpresa)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(PagoPendiente otro) {
		// se toma que null es mayor
		if (this.getVencimiento() == null && (otro == null || otro.getVencimiento() == null)) { 
			return 0;
		}
		if(this.getVencimiento() == null){
		    return 1;
		}
		if (otro == null || otro.getVencimiento() == null) {
			return -1;
		}
		// comienzo
		return this.getVencimiento().compareTo(otro.getVencimiento());
	}

	/**
	 * Gets the importe USS.
	 *
	 * @return the importeUSS
	 */
	public BigDecimal getImporteUSS() {
		return importeUSS;
	}

	/**
	 * Sets the importe USS.
	 *
	 * @param importeUSS
	 *            the importeUSS to set
	 */
	public void setImporteUSS(BigDecimal importeUSS) {
		this.importeUSS = importeUSS;
	}

	/**
	 * Gets the pago minimo.
	 *
	 * @return the pagoMinimo
	 */
	public BigDecimal getPagoMinimo() {
		return pagoMinimo;
	}

	/**
	 * Sets the pago minimo.
	 *
	 * @param pagoMinimo
	 *            the pagoMinimo to set
	 */
	public void setPagoMinimo(BigDecimal pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}

	/**
	 * Gets the datos pago automatico.
	 *
	 * @return the datos pago automatico
	 */
	public DatosPagoAutomaticoEntity getDatosPagoAutomatico() {
		return datosPagoAutomatico;
	}

	/**
	 * Sets the datos pago automatico.
	 *
	 * @param datosPagoAutomatico
	 *            the new datos pago automatico
	 */
	public void setDatosPagoAutomatico(DatosPagoAutomaticoEntity datosPagoAutomatico) {
		this.datosPagoAutomatico = datosPagoAutomatico;
	}

	/**
	 * Gets the orden firmante.
	 *
	 * @return the orden firmante
	 */
	public String getOrdenFirmante() {
		return ordenFirmante;
	}

	/**
	 * Sets the orden firmante.
	 *
	 * @param ordenFirmante
	 *            the new orden firmante
	 */
	public void setOrdenFirmante(String ordenFirmante) {
		this.ordenFirmante = ordenFirmante;
	}

	/**
	 * Gets the cuit empresa.
	 *
	 * @return the cuit empresa
	 */
	public String getCuitEmpresa() {
		return cuitEmpresa;
	}

	/**
	 * Sets the cuit empresa.
	 *
	 * @param cuitEmpresa
	 *            the new cuit empresa
	 */
	public void setCuitEmpresa(String cuitEmpresa) {
		this.cuitEmpresa = cuitEmpresa;
	}

	/**
	 * Gets the identificador fecha stop.
	 *
	 * @return the identificador fecha stop
	 */
	public String getIdentificadorFechaStop() {
		return identificadorFechaStop;
	}

	/**
	 * Sets the identificador fecha stop.
	 *
	 * @param identificadorFechaStop
	 *            the new identificador fecha stop
	 */
	public void setIdentificadorFechaStop(String identificadorFechaStop) {
		this.identificadorFechaStop = identificadorFechaStop;
	}

	/**
	 * Gets the nombre empresa iatx.
	 *
	 * @return the nombre empresa iatx
	 */
	public String getNombreEmpresaIatx() {
		return nombreEmpresaIatx;
	}

	/**
	 * Sets the nombre empresa iatx.
	 *
	 * @param nombreEmpresaIatx
	 *            the new nombre empresa iatx
	 */
	public void setNombreEmpresaIatx(String nombreEmpresaIatx) {
		this.nombreEmpresaIatx = nombreEmpresaIatx;
	}

	/**
	 * Gets the fecha pago programado.
	 *
	 * @return the fecha pago programado
	 */
	public Date getFechaPagoProgramado() {
		return fechaPagoProgramado;
	}

	/**
	 * Sets the fecha pago programado.
	 *
	 * @param fechaPagoProgramado
	 *            the new fecha pago programado
	 */
	public void setFechaPagoProgramado(Date fechaPagoProgramado) {
		this.fechaPagoProgramado = fechaPagoProgramado;
	}

	/**
	 * Gets the numero cuenta banco dolares.
	 *
	 * @return the numero cuenta banco dolares
	 */
	public Integer getNumeroCuentaBancoDolares() {
		return numeroCuentaBancoDolares;
	}

	/**
	 * Sets the numero cuenta banco dolares.
	 *
	 * @param numeroCuentaBancoDolares
	 *            the new numero cuenta banco dolares
	 */
	public void setNumeroCuentaBancoDolares(Integer numeroCuentaBancoDolares) {
		this.numeroCuentaBancoDolares = numeroCuentaBancoDolares;
	}

	/**
	 * Gets the numero cuenta banco pesos.
	 *
	 * @return the numero cuenta banco pesos
	 */
	public Integer getNumeroCuentaBancoPesos() {
		return numeroCuentaBancoPesos;
	}

	/**
	 * Sets the numero cuenta banco pesos.
	 *
	 * @param numeroCuentaBancoPesos
	 *            the new numero cuenta banco pesos
	 */
	public void setNumeroCuentaBancoPesos(Integer numeroCuentaBancoPesos) {
		this.numeroCuentaBancoPesos = numeroCuentaBancoPesos;
	}

	/**
	 * Gets the tipo cuenta tarjeta.
	 *
	 * @return the tipo cuenta tarjeta
	 */
	public TipoCuentaTarjeta getTipoCuentaTarjeta() {
		return tipoCuentaTarjeta;
	}

	/**
	 * Sets the tipo cuenta tarjeta.
	 *
	 * @param tipoCuentaTarjeta
	 *            the new tipo cuenta tarjeta
	 */
	public void setTipoCuentaTarjeta(TipoCuentaTarjeta tipoCuentaTarjeta) {
		this.tipoCuentaTarjeta = tipoCuentaTarjeta;
	}

	/**
	 * Gets the numero de VEP.
	 *
	 * @return the numeroDeVEP
	 */
	public String getNumeroDeVEP() {
		return numeroDeVEP;
	}

	/**
	 * Sets the numero de VEP.
	 *
	 * @param numeroDeVEP
	 *            the numeroDeVEP to set
	 */
	public void setNumeroDeVEP(String numeroDeVEP) {
		this.numeroDeVEP = numeroDeVEP;
	}

	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * Sets the periodo.
	 *
	 * @param periodo
	 *            the new periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * Gets the medio pago.
	 *
	 * @return the medio pago
	 */
	public MedioPago getMedioPago() {
		return medioPago;
	}

	/**
	 * Sets the medio pago.
	 *
	 * @param medioPago
	 *            the new medio pago
	 */
	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	/**
	 * Gets the nro tarjeta credito.
	 *
	 * @return the nro tarjeta credito
	 */
	public String getNroTarjetaCredito() {
		return nroTarjetaCredito;
	}

	/**
	 * Sets the nro tarjeta credito.
	 *
	 * @param nroTarjetaCredito
	 *            the new nro tarjeta credito
	 */
	public void setNroTarjetaCredito(String nroTarjetaCredito) {
		this.nroTarjetaCredito = nroTarjetaCredito;
	}

	/**
	 * Gets the numero cuenta de tarjeta credito.
	 *
	 * @return the numeroCuentaDeTarjetaCredito
	 */
	public String getNumeroCuentaDeTarjetaCredito() {
		return numeroCuentaDeTarjetaCredito;
	}

	/**
	 * Sets the numero cuenta de tarjeta credito.
	 *
	 * @param numeroCuentaDeTarjetaCredito
	 *            the numeroCuentaDeTarjetaCredito to set
	 */
	public void setNumeroCuentaDeTarjetaCredito(String numeroCuentaDeTarjetaCredito) {
		this.numeroCuentaDeTarjetaCredito = numeroCuentaDeTarjetaCredito;
	}

	/**
	 * Gets the tipo cuenta banco pesos.
	 *
	 * @return the tipo cuenta banco pesos
	 */
	public String getTipoCuentaBancoPesos() {
		return tipoCuentaBancoPesos;
	}

	/**
	 * Sets the tipo cuenta banco pesos.
	 *
	 * @param tipoCuentaBancoPesos
	 *            the new tipo cuenta banco pesos
	 */
	public void setTipoCuentaBancoPesos(String tipoCuentaBancoPesos) {
		this.tipoCuentaBancoPesos = tipoCuentaBancoPesos;
	}

	/**
	 * Gets the tipo cuenta banco dolares.
	 *
	 * @return the tipo cuenta banco dolares
	 */
	public String getTipoCuentaBancoDolares() {
		return tipoCuentaBancoDolares;
	}

	/**
	 * Sets the tipo cuenta banco dolares.
	 *
	 * @param tipoCuentaBancoDolares
	 *            the new tipo cuenta banco dolares
	 */
	public void setTipoCuentaBancoDolares(String tipoCuentaBancoDolares) {
		this.tipoCuentaBancoDolares = tipoCuentaBancoDolares;
	}

	/**
	 * Gets the sucursal banco pesos.
	 *
	 * @return the sucursal banco pesos
	 */
	public String getSucursalBancoPesos() {
		return sucursalBancoPesos;
	}

	/**
	 * Sets the sucursal banco pesos.
	 *
	 * @param sucursalBancoPesos
	 *            the new sucursal banco pesos
	 */
	public void setSucursalBancoPesos(String sucursalBancoPesos) {
		this.sucursalBancoPesos = sucursalBancoPesos;
	}

	/**
	 * Gets the sucursal banco dolares.
	 *
	 * @return the sucursal banco dolares
	 */
	public String getSucursalBancoDolares() {
		return sucursalBancoDolares;
	}

	/**
	 * Sets the sucursal banco dolares.
	 *
	 * @param sucursalBancoDolares
	 *            the new sucursal banco dolares
	 */
	public void setSucursalBancoDolares(String sucursalBancoDolares) {
		this.sucursalBancoDolares = sucursalBancoDolares;
	}

	/**
	 * Checks if is alias.
	 *
	 * @return the alias
	 */
	public boolean isAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(boolean alias) {
		this.alias = alias;
	}

	/**
	 * Sets the tipoCuentaDebito.
	 *
	 * @return the tipo cuenta debito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipoCuentaDebito.
	 *
	 * @param tipoCuentaDebito
	 *            the tipoCuentaDebito to set
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * gets the nroCuentaDebito.
	 *
	 * @return the nro cuenta debito
	 */
	public String getNroCuentaDebito() {
		return nroCuentaDebito;
	}

	/**
	 * Sets the nroCuentaDebito.
	 *
	 * @param nroCuentaDebito
	 *            the nroCuentaDebito to set
	 */
	public void setNroCuentaDebito(String nroCuentaDebito) {
		this.nroCuentaDebito = nroCuentaDebito;
	}

	/**
	 * Sets the aliasCuentaDebito.
	 *
	 * @return the alias cuenta debito
	 */
	public String getAliasCuentaDebito() {
		return aliasCuentaDebito;
	}

	/**
	 * Sets the aliasCuentaDebito.
	 *
	 * @param aliasCuentaDebito
	 *            the aliasCuentaDebito to set
	 */
	public void setAliasCuentaDebito(String aliasCuentaDebito) {
		this.aliasCuentaDebito = aliasCuentaDebito;
	}

	/**
	 * Sets the nroCuentaProducto.
	 *
	 * @param nroCuentaProducto
	 *            the nroCuentaProducto to set
	 */
	public void setNroCuentaProducto(String nroCuentaProducto) {
		this.nroCuentaProducto = nroCuentaProducto;
	}

	/**
	 * gets the nroCuentaProducto.
	 *
	 * @return the nro cuenta producto
	 */
	public String getNroCuentaProducto() {
		return nroCuentaProducto;
	}

	/**
	 * Sets the fechaInicioRecarga.
	 *
	 * @param fechaInicioRecarga
	 *            the alfechaInicioRecargaias to set
	 */
	public void setFechaInicioRecarga(String fechaInicioRecarga) {
		this.fechaInicioRecarga = fechaInicioRecarga;
	}

	/**
	 * gets the alias.
	 *
	 * @return the fecha inicio recarga
	 */
	public String getFechaInicioRecarga() {
		return fechaInicioRecarga;
	}

	/**
	 * Sets the fechaProxRecarga.
	 *
	 * @param fechaProxRecarga
	 *            the fechaProxRecarga to set
	 */
	public void setFechaProxRecarga(String fechaProxRecarga) {
		this.fechaProxRecarga = fechaProxRecarga;
	}

	/**
	 * Sets the fechaProxRecarga.
	 *
	 * @return the fecha prox recarga
	 */
	public String getFechaProxRecarga() {
		return fechaProxRecarga;
	}

	/**
	 * Sets the frecuenciaRecarga.
	 *
	 * @param frecuenciaRecarga
	 *            the frecuenciaRecarga to set
	 */
	public void setFrecuenciaRecarga(String frecuenciaRecarga) {
		this.frecuenciaRecarga = frecuenciaRecarga;
	}

	/**
	 * gets the frecuenciaRecarga.
	 *
	 * @return the frecuencia recarga
	 */
	public String getFrecuenciaRecarga() {
		return frecuenciaRecarga;
	}

	/**
	 * Sets the nroCuentaOrigen.
	 *
	 * @param nroCuentaOrigen
	 *            the nroCuentaOrigen to set
	 */
	public void setNroCuentaOrigen(String nroCuentaOrigen) {
		this.nroCuentaOrigen = nroCuentaOrigen;
	}

	/**
	 * gets the nroCuentaOrigen.
	 *
	 * @return the nro cuenta origen
	 */
	public String getNroCuentaOrigen() {
		return nroCuentaOrigen;
	}

    /**
	 * Gets the tiene stop debit.
	 *
	 * @return the tiene stop debit
	 */
    public Boolean getTieneStopDebit() {
        return tieneStopDebit;
    }

    /**
	 * Sets the tiene stop debit.
	 *
	 * @param tieneStopDebit
	 *            the new tiene stop debit
	 */
    public void setTieneStopDebit(Boolean tieneStopDebit) {
    	  this.tieneStopDebit = BooleanUtils.isTrue(tieneStopDebit);
    }

   
    /**
	 * Checks if is editableDetalle.
	 *
	 * @return true, if is editable
	 */
	public boolean isEditableDetalle() {
		return editableDetalle;
	}

	/**
	 * Sets the editableDetalle.
	 *
	 * @param editable
	 *            the new editable
	 */
	public void setEditableDetalle(boolean editableDetalle) {
		this.editableDetalle = editableDetalle;
	}

    /**
     * @return the habilitarStopDebit
     */
    public Boolean getHabilitarStopDebit() {
        return habilitarStopDebit;
    }

    /**
     * @param habilitarStopDebit the habilitarStopDebit to set
     */
    public void setHabilitarStopDebit(Boolean habilitarStopDebit) {
        this.habilitarStopDebit = habilitarStopDebit;
    }

}
