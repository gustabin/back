/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaInDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;

/**
 * The Class PagoInEntity.
 *
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @see {@link PagoMultipleDTO}
 * @see {@link PagoPMC}
 * @since Jan 17, 2016
 */
public class PagoInEntity {

    /** The estado pago. 0: ok, 1: rollback y 2: error parcial */
    private Integer estadoPago = null;

    /** The codigo empresa. [4]. CNSPESDEUD.Empresa o MediosDePago.PesFiid */
    private String codigoEmpresa;

    /** Solicitado por un mensaje parametrizado. */
    private String descripcionOperacionParaMensaje;

    /** Solicitado por un mensaje parametrizado. */
    private String empresaNombreFantasia;

    /** The fecha hora body. */
    private String fechaHoraBody;

    /**
     * The identificacion cliente. [19]. Para todos los casos de pago múltiple
     * el identificador se tomara desde el servicio: Si es Pago Afip sin deuda
     * informada y Si Pes_tipoPago = 2, CUIT del contribuyente
     * (CNSPESDEUD.Identif_Cliente_Empresa) [11] + 1er dato adicional período de
     * pago (MMAAAA) de configuración.
     *
     * Si el servicio es Vep y Para pago multiple: Si (Pes_tipoempresa= F y
     * Pes_TipoPago = 3) Entonces indicar CNSPESDEUD. Identif_Cliente_Empresa
     * 
     * Si el servicio es Vep y Para nuevo pago: Si (Pes_tipoempresa= F y
     * Pes_TipoPago = 3) Entonces indicar el cuit ingresado en el primer
     * identificador del pago sin guiones (CUIT del contribuyente) + (DNI
     * generador del vep [8]), los 8 caracteres centrales del segundo Nota: en
     * nuevo pago se concatenan los datos ingresados por pantalla, no modificar
     * esa lógica.
     * 
     * Otros casos: Indicar el número de identificación del cliente para la
     * empresa, este dato se encuentra en sesión
     * (CNSPESDEUD.Identif_Cliente_Empresa).
     */
    private String identificacion;

    /**
     * The tipo de monto. [1]. valores={3,0} Solo dos valores según el archivo
     * medios de pago.
     *
     * Pes_tipoPago=3 (de la empresa a abonar) y también ver si el importe a
     * abonar por el cliente es igual a = a CNSPESDEUD.Importe Entonces se
     * indicara el valor 3. (Esto sería que se abonó el pago completo de la
     * factura).
     * 
     * Pes_tipoPago=3 (de la empresa a abonar) y también ver si el importe a
     * abonar por el cliente es <> CNSPESDEUD.Importe Entonces se indicara el
     * valor 0.
     * 
     * Pes_tipoPago != 3 (de la empresa a abonar) Entonces se indicara el valor
     * 0.
     * 
     * (Esto sería NO tenemos factura informada, entonces no podemos determinar
     * si es el total o es otro valor)
     */
    private String tipoMonto;

    /**
     * The tipo de seleccion. [1] Si CNSPESDEUD.Factura es = vacio Entonces
     * indicar I (sin factura).
     * 
     * Si CNSPESDEUD.Factura es != vacio Entonces indicar F (con factura).
     */
    private String tipoSeleccion;

    /** The fecha de la ejecucion del pago. */
    private Date fechaDePago;

    /**
     * The numero factura. [20]
     * 
     * Con Factura (incluye caso Vep) - Si se está abonando una factura en
     * particular, entonces indicar CNSPESSDEU.Factura.
     * 
     * Sin Factura - Si el servicio a abonar corresponde a servicio doméstico:
     * Indicar el CUIT del empleador.
     * 
     * Si no es servicio doméstico: Enviar vacío para este caso.
     */
    private String numeroFactura;

    /**
     * Tipo de Cuenta [2] Indicar el tipo de cuenta seleccionado para el pago,
     * estos datos se encuentran en sesión. Si el cliente seleccionó Cuenta
     * Única (02), entonces: - Si Moneda = ARS -> 09 - Si Moneda = USD -> 10
     */
    private String tipoCuenta;

    /** The sucursal cuenta. */
    private String sucursalCuenta;

    /** The numero cuenta. [12] */
    private String numeroCuenta;

    /**
     * simbolo moneda. [3] CNSPESDEUD.Moneda
     */
    private String moneda;

    /**
     * The monto. [12,2] Completar con 0 a la izquierda e indicar el Importe a
     * abonar indicado por el usuario.
     */
    private String monto;

    /** The mensaje. */
    private String mensaje;

    /** The tipo error. */
    private TipoError tipoError;

    /** The reintentar. */
    private Boolean reintentar = Boolean.FALSE;

    /** Respuesta del servicio: The comprobante por servicio. */
    private String comprobantePorServicio;

    /** Respuesta del servicio: The numero control. */
    private String numeroControl;

    /** The fecha vencimiento. */
    private String fechaVencimiento;

    /** The nombre empresa. */
    private String nombreEmpresa;

    /** The descripcion tipo cuenta. */
    private String descripcionTipoCuenta;

    /** The monto mensaje feedback. */
    private String montoMensajeFeedback;

    /** The identificacion mensaje feedback. */
    private String identificacionMensajeFeedback;

    /** The operacion descripcion. */
    private String operacionDescripcion;

    /** The saldo cuenta origen. */
    private String saldoCuentaOrigen;

    /** The account nick name origen. */
    private String accountNickNameOrigen;

    /** The account nick name destino. */
    private String accountNickNameDestino;

    /** The nro tarjeta. */
    private String nroTarjeta;

    /** The tipo cuenta TC. */
    private Integer tipoCuentaTC;
    
    /** The vencimiento tarjeta. */
    private String vencimientoTarjeta;
    
    private boolean esErrorContemplado;
    
    private String numeroCuentaSinFormato;


	/**
     * Instantiates a new pago in entity.
     */
    public PagoInEntity() {
        super();
        this.operacionDescripcion = "pago";
    }

    /**
     * Instantiates a new pago in entity.
     *
     * @param nuevaRecargaInDTO
     *            the nueva recarga in DTO
     * @param cuenta
     *            the cuenta
     */
    public PagoInEntity(NuevaRecargaInDTO nuevaRecargaInDTO, Cuenta cuenta) {
        if (TipoCuenta.CUENTA_UNICA.equals(cuenta.getTipoCuentaEnum())) {
            this.tipoCuenta = "09";
        } else {
            this.tipoCuenta = cuenta.getTipoCuenta();
        }
        this.sucursalCuenta = cuenta.getNroSucursal();
        this.numeroCuenta = cuenta.getNroCuentaProducto();
        this.moneda = "ARS";
        this.monto = obtenerMonto(nuevaRecargaInDTO.getMontoId());
        this.tipoMonto = "0";
        this.tipoSeleccion = "I";
        this.numeroFactura = StringUtils.rightPad(" ", 20);
        this.codigoEmpresa = nuevaRecargaInDTO.getCodigoEmpresa();
        this.identificacion = nuevaRecargaInDTO.getIdentificacionCliente();
        this.empresaNombreFantasia = nuevaRecargaInDTO.getNombreFantasia();

        this.identificacionMensajeFeedback = nuevaRecargaInDTO.getIdentificacionMensajeFeedback();
        this.montoMensajeFeedback = nuevaRecargaInDTO.getMontoMensajeFeedback();
        this.operacionDescripcion = "Recarga";
        if (cuenta.esTarjetaDeCredito()) {
            this.tipoCuentaTC = cuenta.getTipoCuentaEnum().getCodigo();
            this.nroTarjeta = cuenta.getNroTarjetaCredito();
        }

    }

    private String obtenerMonto(String  datosConfirmacionRecarga) {
        String monto = datosConfirmacionRecarga;
        int cantDecimals = monto.length() - monto.indexOf('.');
        if(cantDecimals > monto.length()) {
            return monto + StringUtils.repeat("0",2);
        }
        monto = monto.replace(".","");
        return monto + org.apache.commons.lang3.StringUtils.repeat("0",3- cantDecimals);
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
     * Gets the descripcion operacion para mensaje.
     *
     * @return the descripcion operacion para mensaje
     */
    public String getDescripcionOperacionParaMensaje() {
        return descripcionOperacionParaMensaje;
    }

    /**
     * Sets the descripcion operacion para mensaje.
     *
     * @param descripcionOperacionParaMensaje
     *            the new descripcion operacion para mensaje
     */
    public void setDescripcionOperacionParaMensaje(String descripcionOperacionParaMensaje) {
        this.descripcionOperacionParaMensaje = descripcionOperacionParaMensaje;
    }

    /**
     * Gets the empresa nombre fantasia.
     *
     * @return the empresa nombre fantasia
     */
    public String getEmpresaNombreFantasia() {
        return empresaNombreFantasia;
    }

    /**
     * Sets the empresa nombre fantasia.
     *
     * @param empresaNombreFantasia
     *            the new empresa nombre fantasia
     */
    public void setEmpresaNombreFantasia(String empresaNombreFantasia) {
        this.empresaNombreFantasia = empresaNombreFantasia;
    }

    /**
     * Gets the fecha vencimiento.
     *
     * @return the fecha vencimiento
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Sets the fecha vencimiento.
     *
     * @param fechaVencimiento
     *            the new fecha vencimiento
     */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Gets the identificacion.
     *
     * @return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * Sets the identificacion.
     *
     * @param identificacion
     *            the new identificacion
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * Gets the tipo monto.
     *
     * @return the tipo monto
     */
    public String getTipoMonto() {
        return tipoMonto;
    }

    /**
     * Sets the tipo monto.
     *
     * @param tipoMonto
     *            the new tipo monto
     */
    public void setTipoMonto(String tipoMonto) {
        this.tipoMonto = tipoMonto;
    }

    /**
     * Gets the tipo seleccion.
     *
     * @return the tipo seleccion
     */
    public String getTipoSeleccion() {
        return tipoSeleccion;
    }

    /**
     * Sets the tipo seleccion.
     *
     * @param tipoSeleccion
     *            the new tipo seleccion
     */
    public void setTipoSeleccion(String tipoSeleccion) {
        this.tipoSeleccion = tipoSeleccion;
    }

    /**
     * Gets the numero factura.
     *
     * @return the numero factura
     */
    public String getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * Sets the numero factura.
     *
     * @param numeroFactura
     *            the new numero factura
     */
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    /**
     * Gets the tipo cuenta.
     *
     * @return the tipo cuenta
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Sets the tipo cuenta.
     *
     * @param tipoCuenta
     *            the new tipo cuenta
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * Gets the sucursal cuenta.
     *
     * @return the sucursal cuenta
     */
    public String getSucursalCuenta() {
        return sucursalCuenta;
    }

    /**
     * Sets the sucursal cuenta.
     *
     * @param sucursalCuenta
     *            the new sucursal cuenta
     */
    public void setSucursalCuenta(String sucursalCuenta) {
        this.sucursalCuenta = sucursalCuenta;
    }

    /**
     * Gets the numero cuenta.
     *
     * @return the numero cuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Sets the numero cuenta.
     *
     * @param numeroCuenta
     *            the new numero cuenta
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
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
     * Gets the monto.
     *
     * @return the monto
     */
    public String getMonto() {
        return monto;
    }

    /**
     * Sets the monto.
     *
     * @param monto
     *            the new monto
     */
    public void setMonto(String monto) {
        this.monto = monto;
    }

    /**
     * Gets the mensaje.
     *
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
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
     * Gets the tipo error.
     *
     * @return the tipo error
     */
    public TipoError getTipoError() {
        return tipoError;
    }

    /**
     * Sets the tipo error.
     *
     * @param tipoError
     *            the new tipo error
     */
    public void setTipoError(TipoError tipoError) {
        this.tipoError = tipoError;
    }

    /**
     * Gets the reintentar.
     *
     * @return the reintentar
     */
    public Boolean getReintentar() {
        return reintentar;
    }

    /**
     * Sets the reintentar.
     *
     * @param reintentar
     *            the new reintentar
     */
    public void setReintentar(Boolean reintentar) {
        this.reintentar = reintentar;
    }

    /**
     * Gets the comprobante por servicio.
     *
     * @return the comprobante por servicio
     */
    public String getComprobantePorServicio() {
        return comprobantePorServicio;
    }

    /**
     * Sets the comprobante por servicio.
     *
     * @param comprobantePorServicio
     *            the new comprobante por servicio
     */
    public void setComprobantePorServicio(String comprobantePorServicio) {
        this.comprobantePorServicio = comprobantePorServicio;
    }

    /**
     * Gets the numero control.
     *
     * @return the numero control
     */
    public String getNumeroControl() {
        return numeroControl;
    }

    /**
     * Sets the numero control.
     *
     * @param numeroControl
     *            the new numero control
     */
    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }

    /**
     * Gets the estado pago.
     *
     * @return the estado pago
     */
    public Integer getEstadoPago() {
        return estadoPago;
    }

    /**
     * Sets the estado pago.
     *
     * @param estadoPago
     *            the new estado pago
     */
    public void setEstadoPago(Integer estadoPago) {
        this.estadoPago = estadoPago;
    }

    /**
     * Gets the fecha de pago.
     *
     * @return the fechaDePago
     */
    public Date getFechaDePago() {
        return fechaDePago;
    }

    /**
     * Sets the fecha de pago.
     *
     * @param fechaDePago
     *            the fechaDePago to set
     */
    public void setFechaDePago(Date fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    /**
     * Sets the fecha de vencimiento.
     *
     * @param fechaVencimiento
     *            the new fecha de vencimiento
     */
    public void setFechaDeVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Gets the fecha de vencimiento.
     *
     * @return the fecha de vencimiento
     */
    public String getFechaDeVencimiento() {
        return this.fechaVencimiento;
    }

    /**
     * Gets the nombre empresa.
     *
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Sets the nombre empresa.
     *
     * @param nombreEmpresa
     *            the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * Gets the descripcion tipo cuenta.
     *
     * @return the descripcionTipoCuenta
     */
    public String getDescripcionTipoCuenta() {
        return descripcionTipoCuenta;
    }

    /**
     * Sets the descripcion tipo cuenta.
     *
     * @param descripcionTipoCuenta
     *            the descripcionTipoCuenta to set
     */
    public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
        this.descripcionTipoCuenta = descripcionTipoCuenta;
    }

    /**
     * Se recupera el monto formateado para el mensaje.
     *
     * @return the monto mensaje feedback
     */
    public String getMontoMensajeFeedback() {
        return this.montoMensajeFeedback;
    }

    /**
     * Sets the monto mensaje feedback.
     *
     * @param montoMensajeFeedback
     *            the new monto mensaje feedback
     */
    public void setMontoMensajeFeedback(String montoMensajeFeedback) {
        this.montoMensajeFeedback = montoMensajeFeedback;
    }

    /**
     * Se recupera el monto formateado para el mensaje.
     *
     * @return the identificador mensaje feedback
     */
    public String getIdentificadorMensajeFeedback() {
        return this.identificacionMensajeFeedback;
    }

    /**
     * Sets the identificacion mensaje feedback.
     *
     * @param identificacionMensajeFeedback
     *            the new identificacion mensaje feedback
     */
    public void setIdentificacionMensajeFeedback(String identificacionMensajeFeedback) {
        this.identificacionMensajeFeedback = identificacionMensajeFeedback;
    }

    /**
     * Gets the operacion descripcion.
     *
     * @return the operacion descripcion
     */
    public Object getOperacionDescripcion() {
        return this.operacionDescripcion;
    }

    /**
     * Gets the identificacion mensaje feedback.
     *
     * @return the identificacion mensaje feedback
     */
    public String getIdentificacionMensajeFeedback() {
        return identificacionMensajeFeedback;
    }

    /**
     * Sets the operacion descripcion.
     *
     * @param operacionDescripcion
     *            the new operacion descripcion
     */
    public void setOperacionDescripcion(String operacionDescripcion) {
        this.operacionDescripcion = operacionDescripcion;
    }

    /**
     * Gets the fecha hora body.
     *
     * @return the fecha hora body
     */
    public String getFechaHoraBody() {
        return fechaHoraBody;
    }

    /**
     * Sets the fecha hora body.
     *
     * @param fechaHoraBody
     *            the new fecha hora body
     */
    public void setFechaHoraBody(String fechaHoraBody) {
        this.fechaHoraBody = fechaHoraBody;
    }

    /**
     * Gets the saldo cuenta origen.
     *
     * @return the saldoCuentaOrigen
     */
    public String getSaldoCuentaOrigen() {
        return saldoCuentaOrigen;
    }

    /**
     * Sets the saldo cuenta origen.
     *
     * @param saldoCuentaOrigen
     *            the saldoCuentaOrigen to set
     */
    public void setSaldoCuentaOrigen(String saldoCuentaOrigen) {
        this.saldoCuentaOrigen = saldoCuentaOrigen;
    }

    /**
     * Gets the account nick name origen.
     *
     * @return the accountNickNameOrigen
     */
    public String getAccountNickNameOrigen() {
        return accountNickNameOrigen;
    }

    /**
     * Sets the account nick name origen.
     *
     * @param accountNickNameOrigen
     *            the accountNickNameOrigen to set
     */
    public void setAccountNickNameOrigen(String accountNickNameOrigen) {
        this.accountNickNameOrigen = accountNickNameOrigen;
    }

    /**
     * Gets the account nick name destino.
     *
     * @return the accountNickNameDestino
     */
    public String getAccountNickNameDestino() {
        return accountNickNameDestino;
    }

    /**
     * Sets the account nick name destino.
     *
     * @param accountNickNameDestino
     *            the accountNickNameDestino to set
     */
    public void setAccountNickNameDestino(String accountNickNameDestino) {
        this.accountNickNameDestino = accountNickNameDestino;
    }

    /**
     * Gets the nro tarjeta.
     *
     * @return the nroTarjeta
     */
    public String getNroTarjeta() {
        return nroTarjeta;
    }

    /**
     * Sets the nro tarjeta.
     *
     * @param nroTarjeta
     *            the nroTarjeta to set
     */
    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    /**
     * Gets the tipo cuenta TC.
     *
     * @return the tipoCuentaTC
     */
    public Integer getTipoCuentaTC() {
        return tipoCuentaTC;
    }

    /**
     * Sets the tipo cuenta TC.
     *
     * @param tipoCuentaTC
     *            the tipoCuentaTC to set
     */
    public void setTipoCuentaTC(Integer tipoCuentaTC) {
        this.tipoCuentaTC = tipoCuentaTC;
    }

    /**
	 * Gets the vencimiento tarjeta.
	 *
	 * @return the vencimiento tarjeta
	 */
    public String getVencimientoTarjeta() {
        return vencimientoTarjeta;
    }

    /**
	 * Sets the vencimiento tarjeta.
	 *
	 * @param vencimientoTarjeta
	 *            the new vencimiento tarjeta
	 */
    public void setVencimientoTarjeta(String vencimientoTarjeta) {
        this.vencimientoTarjeta = vencimientoTarjeta;
    }

    public boolean isEsErrorContemplado() {
    	return esErrorContemplado;
    }
    
    public void setEsErrorContemplado(boolean esErrorContemplado) {
    	this.esErrorContemplado = esErrorContemplado;
    }
        
    public String getNumeroCuentaSinFormato() {
		return numeroCuentaSinFormato;
	}

	public void setNumeroCuentaSinFormato(String numeroCuentaSinFormato) {
		this.numeroCuentaSinFormato = numeroCuentaSinFormato;
	}

	/**
     * ToString.
     *
     * @return the string
     */
    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this).append("estadoPago", estadoPago).append("codigoEmpresa", codigoEmpresa)
                .append("descripcionOperacionParaMensaje", descripcionOperacionParaMensaje)
                .append("empresaNombreFantasia", empresaNombreFantasia).append("identificacion", identificacion)
                .append("tipoMonto", tipoMonto).append("tipoSeleccion", tipoSeleccion)
                .append("fechaDePago", fechaDePago).append("numeroFactura", numeroFactura)
                .append("tipoCuenta", tipoCuenta).append("sucursalCuenta", sucursalCuenta)
                .append("numeroCuenta", numeroCuenta).append("moneda", moneda).append("monto", monto)
                .append("mensaje", mensaje).append("tipoError", tipoError).append("reintentar", reintentar)
                .append("comprobantePorServicio", comprobantePorServicio).append("numeroControl", numeroControl)
                .append("fechaVencimiento", fechaVencimiento).append("nombreEmpresa", nombreEmpresa)
                .append("descripcionTipoCuenta", descripcionTipoCuenta)
                .append("montoMensajeFeedback", montoMensajeFeedback)
                .append("identificacionMensajeFeedback", identificacionMensajeFeedback)
                .append("operacionDescripcion", operacionDescripcion).append("saldoCuentaOrigen", saldoCuentaOrigen)
                .append("accountNickNameDestino", accountNickNameDestino)
                .append("accountNickNameOrigen", accountNickNameOrigen).append("nroTarjeta", nroTarjeta).toString();

    }
}
