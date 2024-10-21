/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.base.web.view.View;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IdentificacionCuentaView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDePagoEnum;

/**
 * 
 * Modificado para "Datos Adicionales" de Detalle de Deuda/Pago. DTF:16416.
 * 
 * @author B039636
 * @LastUpdate Nov 11, 2016 by emilio.watemberg@itrsa.com.ar: se agrego el dto
 *             de empresa y cuentas con la que se paga la tarjeta.
 *
 */
@XmlRootElement(name = "pagoPendienteView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagoPendienteView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codigo empresa. */
	private String codigoEmpresa;

	/** The nombre empresa. */
	private String nombreEmpresa;

	/** The descripcion tarjeta. */
	private String descripcionTarjeta;

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The codigo cliente empresa. */
	private String codigoClienteEmpresa;

	/** The vencimiento. */
	private String vencimiento;

	/** The importe. */
	private String importe;

	/** The importe USS. */
	private String importeUSS;

	/** The moneda. */
	private String moneda;

	/** The pago automatico. */
	private boolean pagoAutomatico;

	/** The identificacion factura. */
	private String identificacionFactura;

	/** The tipo pago. */
	private String tipoPago;

	/** The tipo pago descripcion. */
	private String tipoPagoDescripcion;

	/** The editable. */
	private boolean editable;

	/** The icono. */
	private String icono;

	/** The tooltip. */
	private String tooltip;

	/** The datos adicionales. */
	private String datosAdicionales = "";

	/** The informacion adicional. */
	private String informacionAdicional = "";

	/** The boton habilitado. */
	// para pagos automaticos
	private boolean botonHabilitado = true;

	/** The medio de pago. */
	// TODO: respetar el nombre del tipo y refactorizar Front
	private IdentificacionCuentaView medioDePago;

	/** Datos medio de pagos en dolares. */
	private IdentificacionCuentaView medioDePagoDolares;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The fecha. */
	private String fecha;

	/** The hora. */
	private String hora;

	/** Nuevo Limite de adhesion DDI de pago automatico. */
	private String nuevoImporteLimiteAdhesion;

	/** The importe limite adhesion. */
	private String importeLimiteAdhesion;
	// private String mensajeImporteLimite;

	/** The permite baja adhesion. */
	private boolean permiteBajaAdhesion = false;

	/** The cuit. */
	private String cuit;

	/** The mensaje. */
	private String mensaje;

	/** The mensaje. */
	private String nroCuentaOrigen;
	
	/** The editableDetalle. */
	private boolean editableDetalle;

	/**
	 * The tiene stop debit: permite habilitar o deshabilitar el boton de
	 * stopDebit.
	 */
	private boolean tieneStopDebit = false;

	/**
	 * The existe mensaje contextual. se utiliza esta propiedad para habilitar
	 * el boton stop debit.
	 */
	private boolean existeMensajeContextual = false;

	/**
	 * The mensaje contextual: se utiliza esta propiedad para habilitar el boton
	 * stop debit.
	 */
	private String mensajeContextual;

	/** The Constant DA. */
	public static final String DA = "DA";

	/** The Constant TC. */
	public static final String TC = "SR";

	/** The Constant PS. */
	public static final String PS = "PS";

	/** The Constant PR. */
	public static final String PR = "SR";

	/** The Constant ICONO_PAGO_PUNTUAL. */
	public static final String ICONO_PAGO_PUNTUAL = "p";

	/** The Constant ICONO_PAGO_AUTOMATICO. */
	public static final String ICONO_PAGO_AUTOMATICO = "a";

	/** The Constant TOOLTIP_PAGO_AUTOMATICO. */
	public static final String TOOLTIP_PAGO_AUTOMATICO = "Este pago se debitar� autom�ticamente todos los meses";

	/** The Constant TOOLTIP_PAGO_PROGRAMADO. */
	public static final String TOOLTIP_PAGO_PROGRAMADO = "Este pago se ejecutar� por �nica vez el d�a de la programaci�n";

	/** The Constant TOOLTIP_PAGO_PUNTUAL. */
	public static final String TOOLTIP_PAGO_PUNTUAL = "Este pago se debe realizar manualmente";

	/** The Constant SIN_DESCRIPCION. */
	public static final String SIN_DESCRIPCION = "";

	/** The is pago mis cuentas. */
	private Boolean isPagoMisCuentas = false;

	/** The is automatico total. */
	private Boolean isAutomaticoTotal = false;

	/** The is tarjetas. */
	private Boolean isTarjetas = false;

	/** The is automatico minimo. */
	private Boolean isAutomaticoMinimo = false;

	/** The is puntual. */
	private Boolean isPuntual = false;

	/** The is pago programado. */
	private Boolean isPagoProgramado = false;

	/** The is prestamos. */
	private Boolean isPrestamos;

	/** The is debito directo. */
	private Boolean isDebitoDirecto;

	/** The fecha pago programado. */
	private String fechaPagoProgramado;

	/** The pago minimo. */
	private String pagoMinimo;

	/** The numero cuenta banco dolares. */
	private String numeroCuentaBancoDolares;

	/** The alias cuenta dolares. */
	private String aliasCuentaDolares;

	/** The numero cuenta banco pesos. */
	private String numeroCuentaBancoPesos;

	/** The alias cuenta pesos. */
	private String aliasCuentaPesos;

	/** The tipo tarjeta codigo. */
	private String tipoTarjetaCodigo;

	/**
	 * Si permite recarga, DTF:9775.
	 * 
	 * @author Manuel.Vargas
	 **/
	private Boolean isRecargar = false;

	/** Numero de vep. */
	private String numeroDeVEP = null;

	/** Cantidad de cuotas. */
	private String cantidadDeCuotas;

	/** Numero de periodo. */
	private String periodo;

	/** fedcha Proxima Recarga. */
	private String fechaProxRecarga;

	/** frecuencia recarga. */
	private String frecuenciaRecarga;

	/** tipo cuenta debito. */
	private String tipoCuentaDebito;

	/** nro cuenta debto. */
	private String nroCuentaDebito;

	/** nro cuenta debto. */
	private String aliasCuentaDebito;

	/** nro cuenta producto. */
	private String nroCuentaProducto;

	/** nro cuenta producto. */
	private String fechaInicioRecarga;

	/** Datos de empresa que se usan para NuevoPago. @emilio.watemberg. */
	// *** TODO: migrar el front para que use la propiedad MedioPagoView para
	// leer los datos
	// de la empresa y luego limpiar estas propiedades de empresa sueltas en
	// esta entidad (PagoPendienteView).
	private MedioPagoView empresa;

	/** The is recargable. */
	private boolean isRecargable;

	/**
	 * Checks if is recargable.
	 *
	 * @return true, if is recargable
	 */
	public boolean isRecargable() {
		return isRecargable;
	}

	/**
	 * Sets the checks if is recargable.
	 *
	 * @param isRecargable
	 *            the new checks if is recargable
	 */
	public void setIsRecargable(boolean isRecargable) {
		this.isRecargable = isRecargable;
	}

	/** alias para Tarjeta de Credito. */
	private boolean alias = false;

	/** The tiene cuenta en pesos. */
	private boolean tieneCuentaEnPesos;

	/**
	 * constructor por defecto.
	 */
	public PagoPendienteView() {
		super();
	}

	/**
	 * Instantiates a new pago pendiente view.
	 *
	 * @author leonardo.medina
	 * @param tipoPago
	 *            the tipo pago
	 * @since Sep 12, 2016.
	 */
	public PagoPendienteView(TipoDePagoEnum tipoPago) {
		super();
		if (TipoDePagoEnum.PAGOMISCUENTASPUNTUAL.equals(tipoPago)) {
			this.isPagoMisCuentas = true;
			this.isPuntual = true;
		}

		if (TipoDePagoEnum.PAGOMISCUENTASDEBITO.equals(tipoPago)) {
			this.isPagoMisCuentas = true;
			this.isAutomaticoTotal = true;
		}

		if (TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOTOTAL.equals(tipoPago)) {
			this.isTarjetas = true;
			this.isAutomaticoTotal = true;
		}

		if (TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOMINIMO.equals(tipoPago)) {
			this.isTarjetas = true;
			this.isAutomaticoMinimo = true;
		}

		if (TipoDePagoEnum.TARJETAPAGOPUNTUAL.equals(tipoPago)) {
			this.isTarjetas = true;
			this.isPuntual = true;
		}

		if (TipoDePagoEnum.PAGOPROGRAMADO.equals(tipoPago)) {
			this.isTarjetas = true;
			this.isPagoProgramado = true;
		}
		if (TipoDePagoEnum.PAGOMISCUENTASERRORCONSULTADEBITOAUTOMATICO.equals(tipoPago)) {
            this.isPagoMisCuentas = true;
            this.isPuntual = true;
        }
		

		this.isPrestamos = TipoDePagoEnum.PRESTAMODEBITOENCUENTA.equals(tipoPago)
				|| TipoDePagoEnum.PRESTAMOSPAGOPUNTUAL.equals(tipoPago);
		this.isDebitoDirecto = TipoDePagoEnum.SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA.equals(tipoPago);

	}

	/**
	 * Instantiates a new pago pendiente view.
	 *
	 * @param id
	 *            the id
	 */
	public PagoPendienteView(String id) {
		super(id);
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
	 * Gets the descripcion tarjeta.
	 *
	 * @return the descripcion tarjeta
	 */
	public String getDescripcionTarjeta() {
		return descripcionTarjeta;
	}

	/**
	 * Sets the descripcion tarjeta.
	 *
	 * @param descripcionTarjeta
	 *            the new descripcion tarjeta
	 */
	public void setDescripcionTarjeta(String descripcionTarjeta) {
		this.descripcionTarjeta = descripcionTarjeta;
	}

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numero tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the new numero tarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
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
	public String getVencimiento() {
		return vencimiento;
	}

	/**
	 * Sets the vencimiento.
	 *
	 * @param vencimiento
	 *            the new vencimiento
	 */
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the identificacion factura.
	 *
	 * @return the identificacion factura
	 */
	public String getIdentificacionFactura() {
		return identificacionFactura;
	}

	/**
	 * Sets the identificacion factura.
	 *
	 * @param identificacionFactura
	 *            the new identificacion factura
	 */
	public void setIdentificacionFactura(String identificacionFactura) {
		this.identificacionFactura = identificacionFactura;
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
	 * @return the tipo pago
	 */
	public String getTipoPago() {
		return tipoPago;
	}

	/**
	 * Sets the tipo pago.
	 *
	 * @param tipoPago
	 *            the new tipo pago
	 */
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	/**
	 * Checks if is pago automatico.
	 *
	 * @return the pagoAutomatico
	 */
	public boolean isPagoAutomatico() {
		return pagoAutomatico;
	}

	/**
	 * Sets the pago automatico.
	 *
	 * @param pagoAutomatico
	 *            the pagoAutomatico to set
	 */
	public void setPagoAutomatico(boolean pagoAutomatico) {
		this.pagoAutomatico = pagoAutomatico;
	}

	/**
	 * Gets the tipo pago descripcion.
	 *
	 * @return the tipoPagoDescripcion
	 */
	public String getTipoPagoDescripcion() {
		return tipoPagoDescripcion;
	}

	/**
	 * Sets the tipo pago descripcion.
	 *
	 * @param tipoPagoDescripcion
	 *            the tipoPagoDescripcion to set
	 */
	public void setTipoPagoDescripcion(String tipoPagoDescripcion) {
		this.tipoPagoDescripcion = tipoPagoDescripcion;
	}

	/**
	 * Gets the importe USS.
	 *
	 * @return the importeUSS
	 */
	public String getImporteUSS() {
		return importeUSS;
	}

	/**
	 * Sets the importe USS.
	 *
	 * @param importeUSS
	 *            the importeUSS to set
	 */
	public void setImporteUSS(String importeUSS) {
		this.importeUSS = importeUSS;
	}

	/**
	 * Gets the icono.
	 *
	 * @return the icono
	 */
	public String getIcono() {
		return icono;
	}

	/**
	 * Sets the icono.
	 *
	 * @param icono
	 *            the icono to set
	 */
	public void setIcono(String icono) {
		this.icono = icono;
	}

	/**
	 * Gets the tooltip.
	 *
	 * @return the tooltip
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * Sets the tooltip.
	 *
	 * @param tooltip
	 *            the tooltip to set
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
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
	 * Gets the medio de pago.
	 *
	 * @return the medio de pago
	 */
	public IdentificacionCuentaView getMedioDePago() {
		return medioDePago;
	}

	/**
	 * Sets the medio de pago.
	 *
	 * @param medioDePago
	 *            the new medio de pago
	 */
	public void setMedioDePago(IdentificacionCuentaView medioDePago) {
		this.medioDePago = medioDePago;
	}

	/**
	 * Checks if is boton habilitado.
	 *
	 * @return true, if is boton habilitado
	 */
	public boolean isBotonHabilitado() {
		return botonHabilitado;
	}

	/**
	 * Sets the boton habilitado.
	 *
	 * @param botonHabilitado
	 *            the new boton habilitado
	 */
	public void setBotonHabilitado(boolean botonHabilitado) {
		this.botonHabilitado = botonHabilitado;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numero comprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the new numero comprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the importe limite adhesion.
	 *
	 * @return the importe limite adhesion
	 */
	public String getImporteLimiteAdhesion() {
		return importeLimiteAdhesion;
	}

	/**
	 * Sets the importe limite adhesion.
	 *
	 * @param mensajeImporteLimite
	 *            the new importe limite adhesion
	 */
	public void setImporteLimiteAdhesion(String mensajeImporteLimite) {
		this.importeLimiteAdhesion = mensajeImporteLimite;
	}

	/**
	 * Gets the nuevo importe limite adhesion.
	 *
	 * @return the nuevo importe limite adhesion
	 */
	public String getNuevoImporteLimiteAdhesion() {
		return nuevoImporteLimiteAdhesion;
	}

	/**
	 * Sets the nuevo importe limite adhesion.
	 *
	 * @param nuevoImporteLimiteAdhesion
	 *            the new nuevo importe limite adhesion
	 */
	public void setNuevoImporteLimiteAdhesion(String nuevoImporteLimiteAdhesion) {
		this.nuevoImporteLimiteAdhesion = nuevoImporteLimiteAdhesion;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the new hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Checks if is permite baja adhesion.
	 *
	 * @return true, if is permite baja adhesion
	 */
	public boolean isPermiteBajaAdhesion() {
		return permiteBajaAdhesion;
	}

	/**
	 * Sets the permite baja adhesion.
	 *
	 * @param permiteBajaAdhesion
	 *            the new permite baja adhesion
	 */
	public void setPermiteBajaAdhesion(boolean permiteBajaAdhesion) {
		this.permiteBajaAdhesion = permiteBajaAdhesion;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the new cuit
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Gets the tiene stop debit.
	 *
	 * @return the tiene stop debit
	 */
	public boolean getTieneStopDebit() {
		return tieneStopDebit;
	}

	/**
	 * Sets the tiene stop debit.
	 *
	 * @param tieneStopDebit
	 *            the new tiene stop debit
	 */
	public void setTieneStopDebit(boolean tieneStopDebit) {
		this.tieneStopDebit = tieneStopDebit;
	}

	/**
	 * Checks if is existe mensaje contextual.
	 *
	 * @return true, if is existe mensaje contextual
	 */
	public boolean isExisteMensajeContextual() {
		return existeMensajeContextual;
	}

	/**
	 * Sets the existe mensaje contextual.
	 *
	 * @param existeMensajeContextual
	 *            the new existe mensaje contextual
	 */
	public void setExisteMensajeContextual(boolean existeMensajeContextual) {
		this.existeMensajeContextual = existeMensajeContextual;
	}

	/**
	 * Gets the mensaje contextual.
	 *
	 * @return the mensaje contextual
	 */
	public String getMensajeContextual() {
		return mensajeContextual;
	}

	/**
	 * Sets the mensaje contextual.
	 *
	 * @param mensajeContextual
	 *            the new mensaje contextual
	 */
	public void setMensajeContextual(String mensajeContextual) {
		this.mensajeContextual = mensajeContextual;
	}

	/**
	 * Checks if is pago mis cuentas.
	 *
	 * @return true, if is pago mis cuentas
	 */
	public boolean isPagoMisCuentas() {
		return isPagoMisCuentas;
	}

	/**
	 * Sets the pago mis cuentas.
	 *
	 * @param isPagoMisCuentas
	 *            the new pago mis cuentas
	 */
	public void setPagoMisCuentas(boolean isPagoMisCuentas) {
		this.isPagoMisCuentas = isPagoMisCuentas;
	}

	/**
	 * Checks if is tarjetas.
	 *
	 * @return true, if is tarjetas
	 */
	public boolean isTarjetas() {
		return isTarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param isTarjetas
	 *            the new tarjetas
	 */
	public void setTarjetas(boolean isTarjetas) {
		this.isTarjetas = isTarjetas;
	}

	/**
	 * Checks if is prestamos.
	 *
	 * @return true, if is prestamos
	 */
	public boolean isPrestamos() {
		return isPrestamos;
	}

	/**
	 * Sets the prestamos.
	 *
	 * @param isPrestamos
	 *            the new prestamos
	 */
	public void setPrestamos(boolean isPrestamos) {
		this.isPrestamos = isPrestamos;
	}

	/**
	 * Checks if is debito directo.
	 *
	 * @return true, if is debito directo
	 */
	public boolean isDebitoDirecto() {
		return isDebitoDirecto;
	}

	/**
	 * Sets the debito directo.
	 *
	 * @param isDebitoDirecto
	 *            the new debito directo
	 */
	public void setDebitoDirecto(boolean isDebitoDirecto) {
		this.isDebitoDirecto = isDebitoDirecto;
	}

	/**
	 * Checks if is puntual.
	 *
	 * @return true, if is puntual
	 */
	public boolean isPuntual() {
		return isPuntual;
	}

	/**
	 * Sets the puntual.
	 *
	 * @param isPuntual
	 *            the new puntual
	 */
	public void setPuntual(boolean isPuntual) {
		this.isPuntual = isPuntual;
	}

	/**
	 * Checks if is automatico total.
	 *
	 * @return true, if is automatico total
	 */
	public boolean isAutomaticoTotal() {
		return isAutomaticoTotal;
	}

	/**
	 * Sets the automatico total.
	 *
	 * @param isAutomaticoTotal
	 *            the new automatico total
	 */
	public void setAutomaticoTotal(boolean isAutomaticoTotal) {
		this.isAutomaticoTotal = isAutomaticoTotal;
	}

	/**
	 * Checks if is automatico minimo.
	 *
	 * @return true, if is automatico minimo
	 */
	public boolean isAutomaticoMinimo() {
		return isAutomaticoMinimo;
	}

	/**
	 * Sets the automatico minimo.
	 *
	 * @param isAutomaticoMinimo
	 *            the new automatico minimo
	 */
	public void setAutomaticoMinimo(boolean isAutomaticoMinimo) {
		this.isAutomaticoMinimo = isAutomaticoMinimo;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return this.mensaje;
	}

	/**
	 * Gets the checks if is pago mis cuentas.
	 *
	 * @return the checks if is pago mis cuentas
	 */
	public Boolean getIsPagoMisCuentas() {
		return isPagoMisCuentas;
	}

	/**
	 * Sets the checks if is pago mis cuentas.
	 *
	 * @param isPagoMisCuentas
	 *            the new checks if is pago mis cuentas
	 */
	public void setIsPagoMisCuentas(Boolean isPagoMisCuentas) {
		this.isPagoMisCuentas = isPagoMisCuentas;
	}

	/**
	 * Gets the checks if is automatico total.
	 *
	 * @return the checks if is automatico total
	 */
	public Boolean getIsAutomaticoTotal() {
		return isAutomaticoTotal;
	}

	/**
	 * Sets the checks if is automatico total.
	 *
	 * @param isAutomaticoTotal
	 *            the new checks if is automatico total
	 */
	public void setIsAutomaticoTotal(Boolean isAutomaticoTotal) {
		this.isAutomaticoTotal = isAutomaticoTotal;
	}

	/**
	 * Gets the checks if is tarjetas.
	 *
	 * @return the checks if is tarjetas
	 */
	public Boolean getIsTarjetas() {
		return isTarjetas;
	}

	/**
	 * Sets the checks if is tarjetas.
	 *
	 * @param isTarjetas
	 *            the new checks if is tarjetas
	 */
	public void setIsTarjetas(Boolean isTarjetas) {
		this.isTarjetas = isTarjetas;
	}

	/**
	 * Gets the checks if is automatico minimo.
	 *
	 * @return the checks if is automatico minimo
	 */
	public Boolean getIsAutomaticoMinimo() {
		return isAutomaticoMinimo;
	}

	/**
	 * Sets the checks if is automatico minimo.
	 *
	 * @param isAutomaticoMinimo
	 *            the new checks if is automatico minimo
	 */
	public void setIsAutomaticoMinimo(Boolean isAutomaticoMinimo) {
		this.isAutomaticoMinimo = isAutomaticoMinimo;
	}

	/**
	 * Gets the checks if is puntual.
	 *
	 * @return the checks if is puntual
	 */
	public Boolean getIsPuntual() {
		return isPuntual;
	}

	/**
	 * Sets the checks if is puntual.
	 *
	 * @param isPuntual
	 *            the new checks if is puntual
	 */
	public void setIsPuntual(Boolean isPuntual) {
		this.isPuntual = isPuntual;
	}

	/**
	 * Gets the checks if is pago programado.
	 *
	 * @return the checks if is pago programado
	 */
	public Boolean getIsPagoProgramado() {
		return isPagoProgramado;
	}

	/**
	 * Sets the checks if is pago programado.
	 *
	 * @param isPagoProgramado
	 *            the new checks if is pago programado
	 */
	public void setIsPagoProgramado(Boolean isPagoProgramado) {
		this.isPagoProgramado = isPagoProgramado;
	}

	/**
	 * Gets the checks if is prestamos.
	 *
	 * @return the checks if is prestamos
	 */
	public Boolean getIsPrestamos() {
		return isPrestamos;
	}

	/**
	 * Sets the checks if is prestamos.
	 *
	 * @param isPrestamos
	 *            the new checks if is prestamos
	 */
	public void setIsPrestamos(Boolean isPrestamos) {
		this.isPrestamos = isPrestamos;
	}

	/**
	 * Gets the checks if is debito directo.
	 *
	 * @return the checks if is debito directo
	 */
	public Boolean getIsDebitoDirecto() {
		return isDebitoDirecto;
	}

	/**
	 * Sets the checks if is debito directo.
	 *
	 * @param isDebitoDirecto
	 *            the new checks if is debito directo
	 */
	public void setIsDebitoDirecto(Boolean isDebitoDirecto) {
		this.isDebitoDirecto = isDebitoDirecto;
	}

	/**
	 * Gets the fecha pago programado.
	 *
	 * @return the fecha pago programado
	 */
	public String getFechaPagoProgramado() {
		return fechaPagoProgramado;
	}

	/**
	 * Sets the fecha pago programado.
	 *
	 * @param fechaPagoProgramado
	 *            the new fecha pago programado
	 */
	public void setFechaPagoProgramado(String fechaPagoProgramado) {
		this.fechaPagoProgramado = fechaPagoProgramado;
	}

	/**
	 * Sets the pago minimo.
	 *
	 * @param pagoMinimo
	 *            the new pago minimo
	 */
	public void setPagoMinimo(String pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}

	/**
	 * Gets the pago minimo.
	 *
	 * @return the pago minimo
	 */
	public String getPagoMinimo() {
		return pagoMinimo;
	}

	/**
	 * Sets the numero cuenta banco dolares.
	 *
	 * @param numeroCuentaBancoDolares
	 *            the new numero cuenta banco dolares
	 */
	public void setNumeroCuentaBancoDolares(String numeroCuentaBancoDolares) {
		this.numeroCuentaBancoDolares = numeroCuentaBancoDolares;
	}

	/**
	 * Gets the numero cuenta banco dolares.
	 *
	 * @return the numero cuenta banco dolares
	 */
	public String getNumeroCuentaBancoDolares() {
		return numeroCuentaBancoDolares;
	}

	/**
	 * Sets the alias cuenta dolares.
	 *
	 * @param alias
	 *            the new alias cuenta dolares
	 */
	public void setAliasCuentaDolares(String alias) {
		this.aliasCuentaDolares = alias;
	}

	/**
	 * Gets the alias cuenta dolares.
	 *
	 * @return the alias cuenta dolares
	 */
	public String getAliasCuentaDolares() {
		return aliasCuentaDolares;
	}

	/**
	 * Sets the nummero cuenta banco pesos.
	 *
	 * @param numeroCuentaBancoPesos
	 *            the new nummero cuenta banco pesos
	 */
	public void setNummeroCuentaBancoPesos(String numeroCuentaBancoPesos) {
		this.numeroCuentaBancoPesos = numeroCuentaBancoPesos;
	}

	/**
	 * Gets the numero cuenta banco pesos.
	 *
	 * @return the numero cuenta banco pesos
	 */
	public String getNumeroCuentaBancoPesos() {
		return numeroCuentaBancoPesos;
	}

	/**
	 * Sets the numero cuenta banco pesos.
	 *
	 * @param numeroCuentaBancoPesos
	 *            the new numero cuenta banco pesos
	 */
	public void setNumeroCuentaBancoPesos(String numeroCuentaBancoPesos) {
		this.numeroCuentaBancoPesos = numeroCuentaBancoPesos;
	}

	/**
	 * Sets the alias cuenta pesos.
	 *
	 * @param alias
	 *            the new alias cuenta pesos
	 */
	public void setAliasCuentaPesos(String alias) {
		this.aliasCuentaPesos = alias;
	}

	/**
	 * Gets the alias cuenta pesos.
	 *
	 * @return the alias cuenta pesos
	 */
	public String getAliasCuentaPesos() {
		return aliasCuentaPesos;
	}

	/**
	 * Gets the tipo tarjeta codigo.
	 *
	 * @return the tipo tarjeta codigo
	 */
	public String getTipoTarjetaCodigo() {
		return tipoTarjetaCodigo;
	}

	/**
	 * Sets the tipo tarjeta codigo.
	 *
	 * @param tipoTarjetaCodigo
	 *            the new tipo tarjeta codigo
	 */
	public void setTipoTarjetaCodigo(String tipoTarjetaCodigo) {
		this.tipoTarjetaCodigo = tipoTarjetaCodigo;
	}

	/**
	 * Gets the isRecarga field.
	 *
	 * @return isRecargar
	 */
	public Boolean getIsRecargar() {
		return isRecargar;
	}

	/**
	 * Sets the isRecarga.
	 *
	 * @param isRecargar
	 *            the new checks if is recargar
	 */
	public void setIsRecargar(Boolean isRecargar) {
		this.isRecargar = isRecargar;
	}

	/**
	 * Gets the numero de VEP.
	 *
	 * @return the numero de VEP
	 */
	public String getNumeroDeVEP() {
		return numeroDeVEP;
	}

	/**
	 * Sets the numero de VEP.
	 *
	 * @param numeroDeVEP
	 *            the new numero de VEP
	 */
	public void setNumeroDeVEP(String numeroDeVEP) {
		this.numeroDeVEP = numeroDeVEP;
	}

	/**
	 * Gets the cantidad de cuotas.
	 *
	 * @return the cantidad de cuotas
	 */
	public String getCantidadDeCuotas() {
		return cantidadDeCuotas;
	}

	/**
	 * Sets the cantidad de cuotas.
	 *
	 * @param cantidadDeCuotas
	 *            the new cantidad de cuotas
	 */
	public void setCantidadDeCuotas(String cantidadDeCuotas) {
		this.cantidadDeCuotas = cantidadDeCuotas;
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
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public MedioPagoView getEmpresa() {
		return empresa;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa
	 *            the new empresa
	 */
	public void setEmpresa(MedioPagoView empresa) {
		this.empresa = empresa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(botonHabilitado);
		hcb.append(codigoClienteEmpresa);
		hcb.append(codigoEmpresa);
		hcb.append(cuit);
		hcb.append(datosAdicionales);
		hcb.append(editable);
		hcb.append(fecha);
		hcb.append(hora);
		hcb.append(icono);
		hcb.append(identificacionFactura);
		hcb.append(importe);
		hcb.append(importeLimiteAdhesion);
		hcb.append(importeUSS);
		hcb.append(informacionAdicional);
		hcb.append(medioDePago);
		hcb.append(moneda);
		hcb.append(nombreEmpresa);
		hcb.append(nuevoImporteLimiteAdhesion);
		hcb.append(identificacionFactura);
		hcb.append(numeroComprobante);
		hcb.append(pagoAutomatico);
		hcb.append(tipoPago);
		hcb.append(tipoPagoDescripcion);
		hcb.append(tooltip);
		hcb.append(vencimiento);
		hcb.append(mensaje);
		hcb.append(empresa);
		hcb.append(editableDetalle);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		PagoPendienteView other = (PagoPendienteView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(botonHabilitado, other.isBotonHabilitado());
		eb.append(codigoClienteEmpresa, other.getCodigoClienteEmpresa());
		eb.append(codigoEmpresa, other.getCodigoEmpresa());
		eb.append(cuit, other.getCuit());
		eb.append(datosAdicionales, other.getDatosAdicionales());
		eb.append(editable, other.isEditable());
		eb.append(fecha, other.getFecha());
		eb.append(hora, other.getHora());
		eb.append(icono, other.getIcono());
		eb.append(identificacionFactura, other.getIdentificacionFactura());
		eb.append(importe, other.getImporte());
		eb.append(importeLimiteAdhesion, other.getImporteLimiteAdhesion());
		eb.append(importeUSS, other.getImporteUSS());
		eb.append(informacionAdicional, other.getInformacionAdicional());
		eb.append(medioDePago, other.getMedioDePago());
		eb.append(moneda, other.getMoneda());
		eb.append(nombreEmpresa, other.getNombreEmpresa());
		eb.append(nuevoImporteLimiteAdhesion, other.getNuevoImporteLimiteAdhesion());
		eb.append(numeroComprobante, other.getNumeroComprobante());
		eb.append(permiteBajaAdhesion, other.isPermiteBajaAdhesion());
		eb.append(tipoPago, other.getTipoPago());
		eb.append(tipoPagoDescripcion, other.getTipoPagoDescripcion());
		eb.append(tooltip, other.getTooltip());
		eb.append(vencimiento, other.getVencimiento());
		eb.append(existeMensajeContextual, other.isExisteMensajeContextual());
		eb.append(mensajeContextual, other.mensajeContextual);
		eb.append(mensaje, other.getMensaje());
		eb.append(empresa, other.getEmpresa());
		eb.append(editableDetalle, other.isEditableDetalle());
		return eb.isEquals();

	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PagoPendienteView [codigoEmpresa=" + codigoEmpresa + ", nombreEmpresa=" + nombreEmpresa
				+ ", codigoClienteEmpresa=" + codigoClienteEmpresa + ", vencimiento=" + vencimiento + ", importe="
				+ importe + ", importeUSS=" + importeUSS + ", moneda=" + moneda + ", pagoAutomatico=" + pagoAutomatico
				+ ", identificacionFactura=" + identificacionFactura + ", tipoPago=" + tipoPago
				+ ", tipoPagoDescripcion=" + tipoPagoDescripcion + ", editable=" + editable + ",editableDetalle=" + editableDetalle + ", icono=" + icono
				+ ", tooltip=" + tooltip + ", datosAdicionales=" + datosAdicionales + ", informacionAdicional="
				+ informacionAdicional + ", botonHabilitado=" + botonHabilitado + ", medioDePago=" + medioDePago
				+ ", numeroComprobante=" + numeroComprobante + ", fecha=" + fecha + ", hora=" + hora
				+ ", nuevoImporteLimiteAdhesion=" + nuevoImporteLimiteAdhesion + ", importeLimiteAdhesion="
				+ importeLimiteAdhesion + ", permiteBajaAdhesion=" + permiteBajaAdhesion + ", cuit=" + cuit
				+ ", mensaje=" + mensaje + ", existeMensajeContextual=" + existeMensajeContextual
				+ ", mensajeContextual=" + mensajeContextual + cantidadDeCuotas + periodo + "]";
	}

	/**
	 * Gets the medio pago dolares.
	 *
	 * @return the medioPagoDolares
	 */
	public IdentificacionCuentaView getMedioPagoDolares() {
		return medioDePagoDolares;
	}

	/**
	 * Sets the medio pago dolares.
	 *
	 * @param medioPagoDolares
	 *            the medioPagoDolares to set
	 */
	public void setMedioPagoDolares(IdentificacionCuentaView medioPagoDolares) {
		this.medioDePagoDolares = medioPagoDolares;
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
	 * Gets the fecha prox recarga.
	 *
	 * @return the fecha prox recarga
	 */
	public String getFechaProxRecarga() {
		return fechaProxRecarga;
	}

	/**
	 * Sets the fecha prox recarga.
	 *
	 * @param fechaProxRecarga
	 *            the new fecha prox recarga
	 */
	public void setFechaProxRecarga(String fechaProxRecarga) {
		this.fechaProxRecarga = fechaProxRecarga;
	}

	/**
	 * Gets the frecuencia recarga.
	 *
	 * @return the frecuencia recarga
	 */
	public String getFrecuenciaRecarga() {
		return frecuenciaRecarga;
	}

	/**
	 * Sets the frecuencia recarga.
	 *
	 * @param frecuenciaRecarga
	 *            the new frecuencia recarga
	 */
	public void setFrecuenciaRecarga(String frecuenciaRecarga) {
		this.frecuenciaRecarga = frecuenciaRecarga;
	}

	/**
	 * Gets the nro cuenta origen.
	 *
	 * @return the nro cuenta origen
	 */
	public String getNroCuentaOrigen() {
		return nroCuentaOrigen;
	}

	/**
	 * Sets the nro cuenta origen.
	 *
	 * @param nroCuentaOrigen
	 *            the new nro cuenta origen
	 */
	public void setNroCuentaOrigen(String nroCuentaOrigen) {
		this.nroCuentaOrigen = nroCuentaOrigen;
	}

	/**
	 * Gets the nro cuenta producto.
	 *
	 * @return the nro cuenta producto
	 */
	public String getNroCuentaProducto() {
		return nroCuentaProducto;
	}

	/**
	 * Sets the nro cuenta producto.
	 *
	 * @param nroCuentaProducto
	 *            the new nro cuenta producto
	 */
	public void setNroCuentaProducto(String nroCuentaProducto) {
		this.nroCuentaProducto = nroCuentaProducto;
	}

	/**
	 * Gets the medio de pago dolares.
	 *
	 * @return the medio de pago dolares
	 */
	public IdentificacionCuentaView getMedioDePagoDolares() {
		return medioDePagoDolares;
	}

	/**
	 * Sets the medio de pago dolares.
	 *
	 * @param medioDePagoDolares
	 *            the new medio de pago dolares
	 */
	public void setMedioDePagoDolares(IdentificacionCuentaView medioDePagoDolares) {
		this.medioDePagoDolares = medioDePagoDolares;
	}

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipo cuenta debito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the new tipo cuenta debito
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the nro cuenta debito.
	 *
	 * @return the nro cuenta debito
	 */
	public String getNroCuentaDebito() {
		return nroCuentaDebito;
	}

	/**
	 * Sets the nro cuenta debito.
	 *
	 * @param nroCuentaDebito
	 *            the new nro cuenta debito
	 */
	public void setNroCuentaDebito(String nroCuentaDebito) {
		this.nroCuentaDebito = nroCuentaDebito;
	}

	/**
	 * Sets the recargable.
	 *
	 * @param isRecargable
	 *            the new recargable
	 */
	public void setRecargable(boolean isRecargable) {
		this.isRecargable = isRecargable;
	}

	/**
	 * Gets the alias cuenta debito.
	 *
	 * @return the alias cuenta debito
	 */
	public String getAliasCuentaDebito() {
		return aliasCuentaDebito;
	}

	/**
	 * Sets the alias cuenta debito.
	 *
	 * @param aliasCuentaDebito
	 *            the new alias cuenta debito
	 */
	public void setAliasCuentaDebito(String aliasCuentaDebito) {
		this.aliasCuentaDebito = aliasCuentaDebito;
	}

	/**
	 * Gets the fecha inicio recarga.
	 *
	 * @return the fecha inicio recarga
	 */
	public String getFechaInicioRecarga() {
		return fechaInicioRecarga;
	}

	/**
	 * Sets the fecha inicio recarga.
	 *
	 * @param fechaInicioRecarga
	 *            the new fecha inicio recarga
	 */
	public void setFechaInicioRecarga(String fechaInicioRecarga) {
		this.fechaInicioRecarga = fechaInicioRecarga;
	}

	/**
	 * Checks if is tiene cuenta en pesos.
	 *
	 * @return true, if is tiene cuenta en pesos
	 */
	public boolean isTieneCuentaEnPesos() {
		return tieneCuentaEnPesos;
	}

	/**
	 * Sets the tiene cuenta en pesos.
	 *
	 * @param tieneCuentaEnPesos
	 *            the new tiene cuenta en pesos
	 */
	public void setTieneCuentaEnPesos(boolean tieneCuentaEnPesos) {
		this.tieneCuentaEnPesos = tieneCuentaEnPesos;
	}

	
	public boolean isEditableDetalle() {
		return editableDetalle;
	}

	public void setEditableDetalle(boolean editableDetalle) {
		this.editableDetalle = editableDetalle;
	}
	
	

}
