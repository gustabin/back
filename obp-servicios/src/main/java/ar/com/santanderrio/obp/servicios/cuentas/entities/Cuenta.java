/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Cuenta.
 */
public class Cuenta extends AbstractCuenta {

    /** The Constant INDICADOR_SOBREGIRO_ACTIVO. */
    private static final String INDICADOR_SOBREGIRO_ACTIVO = "S";

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The Constant TIPO_CUENTAS_PARA_PAGO. */
    private static final String[] TIPO_CUENTAS_PARA_PAGO = new String[] { "00", "01", "02", "03", "04" };

    /** The Constant TIPOCTA_VISARECARGABLE. */
    public static final String TIPOCTA_VISARECARGABLE = "77";

    /** The Constant TIPOCTA_BANELCO. */
    public static final String TIPOCTA_BANELCO = "05";

    /** The Constant TIPOCTA_TITULO. */
    public static final String TIPOCTA_TITULO = "08";

    /** The Constant TIPOCTA_CU. */
    public static final String TIPOCTA_CU = "02";

    /** The Constant TIPOCTA_CUP. */
    public static final String TIPOCTA_CUP = "09";

    /** The Constant TIPOCTA_CUD. */
    public static final String TIPOCTA_CUD = "10";

    /** The Constant MONEDA_DOLAR_WS. */
    public static final String MONEDA_DOLAR_BANELCO = "840";

    /** The Constant MONEDA_PESOS_WS. */
    public static final String MONEDA_PESOS_BANELCO = "032";
    
    /** tipo cuenta master     */
    public static final String TIPOCTA_MASTER = "06";
    
    /** tipo cuenta visa */
    public static final String TIPOCTA_VISA = "07";
    
    /** tipo cuenta amex*/
    public static final String TIPOCTA_AMEX = "42";

    /** The index RawClient. */
    private int index = -1;

    /** The saldo aper caja ahorro pesos. */
    private BigDecimal saldoAperCajaAhorroPesos;

    /** The saldo aper cuenta corriente pesos. */
    private BigDecimal saldoAperCuentaCorrientePesos;

    /**
     * The tipo cuenta sin unificar. Tipo real de la cuenta ya que en
     * dentificacion.java se unifican las cuentas CUP y CUD en cuenta CU.
     * 
     */
    private String tipoCuentaSinUnificar = "";

    /** The tipo cuenta sin unificar dls. */
    private String tipoCuentaSinUnificarDls = "";

    /** The nro orden firmante. */
    private String nroOrdenFirmante = "";

    /** The codigo aplicacion. */
    private String codigoAplicacion = "";

    /** The clase cuenta. */
    private String claseCuenta = "";

    /** The forma de operar. */
    private String formaDeOperar = "";

    /** The nro tarjeta credito. */
    /*
     * es de 20
     */
    private String nroTarjetaCredito = "";

    /** The estado tarjeta credito. */
    private String estadoTarjetaCredito = "";

    /** The calidad de participacion. */
    private String calidadDeParticipacion = "";

    /** The codigo paquete. */
    private String codigoPaquete = "";

    /** The clase paquete. */
    private String clasePaquete = "";

    /** si es CU se usan los de abajo. */
    private String saldoCuenta = "";

    /** The saldo CU pesos. */
    private String saldoCUPesos = "";

    /** The saldo CU dls. */
    private String saldoCUDls = "";

    /** The saldo para conformar. */
    private String saldoParaConformar = "";

    /** The deposito efectivo. */
    private String depositoEfectivo = "";

    /** The subproducto altair. */
    private String subproductoAltair = "";

    /** The moneda altair. */
    private String monedaAltair = "";

    /** The grupo afinidad. */
    private String grupoAfinidad = "";

    /** The oficina altair. */
    private String oficinaAltair = "";

    /** The contrato altair. */
    private String contratoAltair = "";

    /** The sucursal paquete. */
    private String sucursalPaquete = "";

    /** The numero paquete. */
    private String numeroPaquete = "";

    /** The convenio PAS. */
    private boolean convenioPAS = false;

    /** The habilitada para transferir. */
    private boolean habilitadaParaTransferir = false;

    /** Nro de cuenta titulo para cuenta de banca privada*. */
    private String nroCuentaTit;

    /** The intervinientes. titular y cotitulares */
    private List<Interviniente> intervinientes = null;
    
    /**  The isPivote. */
    private boolean isPivote = false;
    
    /**  The repatriaciion. */
    private int cuentaOPRepatriacion;
    
    private String sucursalCtaOpRep;

    /** The Constant TITULOS. */
    public static final String TITULOS = "Títulos";

    /**
     * Instantiates a new cuenta.
     */
    public Cuenta() {
        super();
    }

    /**
     * Instantiates a new cuenta.
     *
     * @param cuenta
     *            the cuenta
     * @param tipoCuenta
     *            the tipo cuenta
     */
    public Cuenta(Cuenta cuenta, TipoCuenta tipoCuenta) {
        this.tipoCuentaEnum = tipoCuenta;
        this.nroSucursal = cuenta.getNroSucursal();
        this.nroCuentaProducto = cuenta.getNroCuentaProducto();
        this.codigoTitularidad = cuenta.getCodigoTitularidad();
        this.saldoCuenta = cuenta.getSaldoCuenta();
        this.saldoCUDls = cuenta.getSaldoCUDls();
        this.saldoCUPesos = cuenta.getSaldoCUPesos();
        this.setCbu(cuenta.getCbu());
    }

    /**
     * Gets the index.
     *
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the index.
     *
     * @param index
     *            the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Gets the tipo cuenta sin unificar.
     *
     * @return the tipoCuentaSinUnificar
     */
    public String getTipoCuentaSinUnificar() {
        return tipoCuentaSinUnificar;
    }

    /**
     * Sets the tipo cuenta sin unificar.
     *
     * @param tipoCuentaSinUnificar
     *            the tipoCuentaSinUnificar to set
     */
    public void setTipoCuentaSinUnificar(String tipoCuentaSinUnificar) {
        this.tipoCuentaSinUnificar = tipoCuentaSinUnificar;
    }

    /**
     * Gets the nro orden firmante.
     *
     * @return the nroOrdenFirmante
     */
    public String getNroOrdenFirmante() {
        return nroOrdenFirmante;
    }

    /**
     * Sets the nro orden firmante.
     *
     * @param nroOrdenFirmante
     *            the nroOrdenFirmante to set
     */
    public void setNroOrdenFirmante(String nroOrdenFirmante) {
        this.nroOrdenFirmante = nroOrdenFirmante;
    }

    /**
     * Gets the codigo aplicacion.
     *
     * @return the codigoAplicacion
     */
    public String getCodigoAplicacion() {
        return codigoAplicacion;
    }

    /**
     * Sets the codigo aplicacion.
     *
     * @param codigoAplicacion
     *            the codigoAplicacion to set
     */
    public void setCodigoAplicacion(String codigoAplicacion) {
        this.codigoAplicacion = codigoAplicacion;
    }

    /**
     * Gets the clase cuenta.
     *
     * @return the claseCuenta
     */
    public String getClaseCuenta() {
        return claseCuenta;
    }

    /**
     * Sets the clase cuenta.
     *
     * @param claseCuenta
     *            the claseCuenta to set
     */
    public void setClaseCuenta(String claseCuenta) {
        this.claseCuenta = claseCuenta;
    }

    /**
     * Gets the forma de operar.
     *
     * @return the formaDeOperar
     */
    public String getFormaDeOperar() {
        return formaDeOperar;
    }

    /**
     * Sets the forma de operar.
     *
     * @param formaDeOperar
     *            the formaDeOperar to set
     */
    public void setFormaDeOperar(String formaDeOperar) {
        this.formaDeOperar = formaDeOperar;
    }

    /**
     * Gets the nro tarjeta credito.
     *
     * @return the nroTarjetaCredito
     */
    public String getNroTarjetaCredito() {
        return nroTarjetaCredito;
    }

    /**
     * Sets the nro tarjeta credito.
     *
     * @param nroTarjetaCredito
     *            the nroTarjetaCredito to set
     */
    public void setNroTarjetaCredito(String nroTarjetaCredito) {
        this.nroTarjetaCredito = nroTarjetaCredito;
    }

    /**
     * Gets the estado tarjeta credito.
     *
     * @return the estadoTarjetaCredito
     */
    public String getEstadoTarjetaCredito() {
        return estadoTarjetaCredito;
    }

    /**
     * Sets the estado tarjeta credito.
     *
     * @param estadoTarjetaCredito
     *            the estadoTarjetaCredito to set
     */
    public void setEstadoTarjetaCredito(String estadoTarjetaCredito) {
        this.estadoTarjetaCredito = estadoTarjetaCredito;
    }

    /**
     * Gets the calidad de participacion.
     *
     * @return the calidadDeParticipacion
     */
    public String getCalidadDeParticipacion() {
        return calidadDeParticipacion;
    }

    /**
     * Sets the calidad de participacion.
     *
     * @param calidadDeParticipacion
     *            the calidadDeParticipacion to set
     */
    public void setCalidadDeParticipacion(String calidadDeParticipacion) {
        this.calidadDeParticipacion = calidadDeParticipacion;
    }

    /**
     * Gets the codigo paquete.
     *
     * @return the codigoPaquete
     */
    public String getCodigoPaquete() {
        return codigoPaquete;
    }

    /**
     * Sets the codigo paquete.
     *
     * @param codigoPaquete
     *            the codigoPaquete to set
     */
    public void setCodigoPaquete(String codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    /**
     * Gets the clase paquete.
     *
     * @return the clasePaquete
     */
    public String getClasePaquete() {
        return clasePaquete;
    }

    /**
     * Sets the clase paquete.
     *
     * @param clasePaquete
     *            the clasePaquete to set
     */
    public void setClasePaquete(String clasePaquete) {
        this.clasePaquete = clasePaquete;
    }

    /**
     * Gets the saldo cuenta.
     *
     * @return the saldoCuenta
     */
    public String getSaldoCuenta() {
        return saldoCuenta;
    }

    /**
     * Sets the saldo cuenta.
     *
     * @param saldoCuenta
     *            the saldoCuenta to set
     */
    public void setSaldoCuenta(String saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    /**
     * Gets the saldo CU pesos.
     *
     * @return the saldoCUPesos
     */
    public String getSaldoCUPesos() {
        return saldoCUPesos;
    }

    /**
     * Sets the saldo CU pesos.
     *
     * @param saldoCUPesos
     *            the saldoCUPesos to set
     */
    public void setSaldoCUPesos(String saldoCUPesos) {
        this.saldoCUPesos = saldoCUPesos;
    }

    /**
     * Gets the saldo CU dls.
     *
     * @return the saldoCUDls
     */
    public String getSaldoCUDls() {
        return saldoCUDls;
    }

    /**
     * Sets the saldo CU dls.
     *
     * @param saldoCUDls
     *            the saldoCUDls to set
     */
    public void setSaldoCUDls(String saldoCUDls) {
        this.saldoCUDls = saldoCUDls;
    }

    /**
     * Gets the saldo para conformar.
     *
     * @return the saldoParaConformar
     */
    public String getSaldoParaConformar() {
        return saldoParaConformar;
    }

    /**
     * Sets the saldo para conformar.
     *
     * @param saldoParaConformar
     *            the saldoParaConformar to set
     */
    public void setSaldoParaConformar(String saldoParaConformar) {
        this.saldoParaConformar = saldoParaConformar;
    }

    /**
     * Gets the deposito efectivo.
     *
     * @return the depositoEfectivo
     */
    public String getDepositoEfectivo() {
        return depositoEfectivo;
    }

    /**
     * Sets the deposito efectivo.
     *
     * @param depositoEfectivo
     *            the depositoEfectivo to set
     */
    public void setDepositoEfectivo(String depositoEfectivo) {
        this.depositoEfectivo = depositoEfectivo;
    }

    /**
     * Gets the subproducto altair.
     *
     * @return the subproductoAltair
     */
    public String getSubproductoAltair() {
        return subproductoAltair;
    }

    /**
     * Sets the subproducto altair.
     *
     * @param subproductoAltair
     *            the subproductoAltair to set
     */
    public void setSubproductoAltair(String subproductoAltair) {
        this.subproductoAltair = subproductoAltair;
    }

    /**
     * Gets the moneda altair.
     *
     * @return the monedaAltair
     */
    public String getMonedaAltair() {
        return monedaAltair;
    }

    /**
     * Sets the moneda altair.
     *
     * @param monedaAltair
     *            the monedaAltair to set
     */
    public void setMonedaAltair(String monedaAltair) {
        this.monedaAltair = monedaAltair;
    }

    /**
	 * Conversión de las monedas de Prisma, fuente this.monedaAltair.<br/>
	 * "$" --> "32"<br/>
	 * "U$S" --> "840"<br/>
	 *
	 * @return the moneda banelco
	 */
    public String getMonedaBanelco() {
        if (DivisaEnum.PESO.getCodigo().equals(monedaAltair)) {
            return MONEDA_PESOS_BANELCO;
        } else if (DivisaEnum.DOLAR.getCodigo().equals(monedaAltair)) {
            return MONEDA_DOLAR_BANELCO;
        }
        return StringUtils.EMPTY;
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
     * Gets the oficina altair.
     *
     * @return the oficinaAltair
     */
    public String getOficinaAltair() {
        return oficinaAltair;
    }

    /**
     * Sets the oficina altair.
     *
     * @param oficinaAltair
     *            the oficinaAltair to set
     */
    public void setOficinaAltair(String oficinaAltair) {
        this.oficinaAltair = oficinaAltair;
    }

    /**
     * Gets the contrato altair.
     *
     * @return the contratoAltair
     */
    public String getContratoAltair() {
        return contratoAltair;
    }

    /**
     * Sets the contrato altair.
     *
     * @param contratoAltair
     *            the contratoAltair to set
     */
    public void setContratoAltair(String contratoAltair) {
        this.contratoAltair = contratoAltair;
    }

    /**
     * Gets the sucursal paquete.
     *
     * @return the sucursalPaquete
     */
    public String getSucursalPaquete() {
        return sucursalPaquete;
    }

    /**
     * Sets the sucursal paquete.
     *
     * @param sucursalPaquete
     *            the sucursalPaquete to set
     */
    public void setSucursalPaquete(String sucursalPaquete) {
        this.sucursalPaquete = sucursalPaquete;
    }

    /**
     * Gets the numero paquete.
     *
     * @return the numeroPaquete
     */
    public String getNumeroPaquete() {
        return numeroPaquete;
    }

    /**
     * Sets the numero paquete.
     *
     * @param numeroPaquete
     *            the numeroPaquete to set
     */
    public void setNumeroPaquete(String numeroPaquete) {
        this.numeroPaquete = numeroPaquete;
    }

    /**
     * Checks if is convenio PAS.
     *
     * @return the convenioPAS
     */
    public boolean isConvenioPAS() {
        return convenioPAS;
    }

    /**
     * Sets the convenio PAS.
     *
     * @param convenioPAS
     *            the convenioPAS to set
     */
    public void setConvenioPAS(boolean convenioPAS) {
        this.convenioPAS = convenioPAS;
    }

    /**
     * Checks if is habilitada para transferir.
     *
     * @return the habilitadaParaTransferir
     */
    public boolean isHabilitadaParaTransferir() {
        return habilitadaParaTransferir;
    }

    /**
     * Sets the habilitada para transferir.
     *
     * @param habilitadaParaTransferir
     *            the habilitadaParaTransferir to set
     */
    public void setHabilitadaParaTransferir(boolean habilitadaParaTransferir) {
        this.habilitadaParaTransferir = habilitadaParaTransferir;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta#
     * isCuentaCerrada()
     */
    @Override
    public Boolean isCuentaCerrada() {
        return false;
    }

    /**
     * Gets the tipo cuenta sin unificar dls.
     *
     * @return the tipo cuenta sin unificar dls
     */
    public String getTipoCuentaSinUnificarDls() {
        return tipoCuentaSinUnificarDls;
    }

    /**
     * Sets the tipo cuenta sin unificar dls.
     *
     * @param tipoCuentaSinUnificarDls
     *            the new tipo cuenta sin unificar dls
     */
    public void setTipoCuentaSinUnificarDls(String tipoCuentaSinUnificarDls) {
        this.tipoCuentaSinUnificarDls = tipoCuentaSinUnificarDls;
    }
    
    
    /**
     * Checks if isPivote.
     *
     * @return true, if is isPivote
     */
    public boolean isPivote() {
		return isPivote;
	}

	/**
	 * Sets the pivote.
	 *
	 * @param isPivote the new isPivote
	 */
	public void setPivote(boolean isPivote) {
		this.isPivote = isPivote;
	}

	/**
     * Devuelve verdadero si la cuenta corresponde a una tarjeta de credito.
     *
     * @return true, if successful
     */
    public boolean esTarjetaDeCredito() {
        return TipoCuenta.VISA.equals(this.getTipoCuentaEnum()) || TipoCuenta.AMEX.equals(this.getTipoCuentaEnum())
                || TipoCuenta.MASTERCARD.equals(this.getTipoCuentaEnum());
    }

    /**
     * Devuelve verdadero si la cuenta corresponde a una tarjeta para ser
     * enviada a FECP.
     *
     * @return true, if successful
     */
    public boolean esTarjetaParaEnviarAFecp() {
        if (this.esTarjetaVisa() && this.esValidaParaRealizarPago()) {
            return true;
        }

        if (this.esTarjetaAmex() && this.esValidaParaRealizarPago()) {
            return true;
        }
		
        if (this.esTarjetaMaster() && this.esValidaParaRealizarPago()) {
        	return true;
        }
        
        if (Cuenta.TIPOCTA_BANELCO.equals(this.getTipoCuenta())) {
            return true;
        }

        return false;
    }

    /**
     * Es valida para realizar pago.
     *
     * @return true, if successful
     */
    public boolean esValidaParaRealizarPago() {
        return "20".equals(this.getEstadoTarjetaCredito()) || "22".equals(this.getEstadoTarjetaCredito());
    }

    /**
     * Es tarjeta visa.
     *
     * @return true, if successful
     */
    public boolean esTarjetaVisa() {
        return TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo().equals(this.getTipoCuenta());
    }
    
    public boolean esTarjetaVisacontactless () {
        return TipoCuentaTarjeta.getTipoctaVisarecargable().equals(this.getTipoCuenta() );
    }
    
    /**
     * Es tarjeta master.
     *
     * @return true, if successful
     */
    public boolean esTarjetaMaster() {
        return TipoCuentaTarjeta.TIPOCTA_MASTER.getCodigo().equals(this.getTipoCuenta());
    }

    /**
	 * Es tarjeta de debito.
	 *
	 * @return true, if successful
	 */
    public boolean esBanelco() {
        return TipoCuenta.BANELCO.getCodigo().equals(this.getTipoCuentaEnum().getCodigo());
    }
    
    
    /**
   	 * Es Amex.
   	 *
   	 * @return true, if successful
   	 */
    public boolean esAmex() {
    	return TipoCuenta.AMEX.equals(this.getTipoCuentaEnum());
    	
    }
    
    /**
   	 * Es Visa.
   	 *
   	 * @return true, if successful
   	 */
    public boolean esVisa() {
    	return TipoCuenta.VISA.equals(this.getTipoCuentaEnum());
    }

    /**
     * Es Titular Tarjeta Visa.
     *
     * @return true, if successful
     */
    public boolean esTitularTarjetaVisa() {
        return this.esTarjetaVisa() && esTitular();
    }

    /**
     * Es titular tarjeta american express.
     *
     * @return true, if successful
     */
    public boolean esTitularTarjetaAmex() {
        return this.esTarjetaAmex() && esTitular();
    }
    /**
     * Es titular tarjeta master.
     *
     * @return true, if successful
     */
    public boolean esTitularTarjetaMaster() {
    	return this.esTarjetaMaster() && esTitular();
    }
    
    /**
     * Verifica si es titular de cuenta.
     *
     * @return true, if successful
     */
    public boolean esTitular() {
        return TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(this.getCodigoTitularidad());
    }
    
    /**
     * Es adicional.
     *
     * @return true, if successful
     */
    public boolean esAdicional() {
        return TarjetaUtils.CODIGO_TITULARIDAD_ADICIONAL.equals(this.getCodigoTitularidad());
    }

    /**
     * Es visa recargable.
     *
     * @return true, if successful
     */
    public boolean esVisaRecargable() {
        return esTarjetaVisa() && esRecargable();
    }
    
    
    /**
     * Es visa recargable.
     *
     * @return true, if successful
     */
    public boolean esVisaRecargableContactless() {
        return ((esTarjetaVisa()|| esTarjetaVisacontactless()) && esRecargable());
    }
    
    /**
     * Es recargable.
     *
     * @return true, if successful
     */
    public boolean esRecargable() {
        return TarjetaUtils.VISA_RECARGABLE.equals(this.claseCuenta);
    }

    /**
     * Es tarjeta amex.
     *
     * @return true, if successful
     */
    public boolean esTarjetaAmex() {
        return TipoCuentaTarjeta.TIPOCTA_AMEX.getCodigo().equals(this.getTipoCuenta());
    }
    
    /**
     * Es tajeta master.
     *
     * @return true, if successful
     */
    public boolean esTajetaMaster() {
        return TipoCuentaTarjeta.TIPOCTA_MASTER.getCodigo().equals(this.getTipoCuenta());
    }
    
    /**
     * Verifica el estado de tarjeta de credito.
     *
     * @return boolean
     */
    public boolean esEstadoTarjetaCredito() {
    	return TarjetaUtils.ESTADO_TARJETA_CREDITO.equals(this.getEstadoTarjetaCredito())
    			||TarjetaUtils.ESTADO_TARJETA_CREDITO_ACTIVA_NO_RENOVAR.equals(this.getEstadoTarjetaCredito());
    }
    
    /**
     * Corresponde nro tarjeta.
     *
     * @param nroTarjetaEnmascarada
     *            the nro tarjeta enmascarada
     * @return true, if successful
     */
    public boolean correspondeNroTarjeta(String nroTarjetaEnmascarada) {
        if (StringUtils.isBlank(nroTarjetaEnmascarada)) {
            return false;
        }

        if (esTarjetaDeCredito()
                && StringUtils.endsWith(nroTarjetaEnmascarada, TarjetaUtils.obtenerNroTarjetaEnmascarada(this))) {
            return true;
        }

        return false;
    }

    /**
     * Es prestamo prendario.
     *
     * @return true, if successful
     */
    public boolean esPrestamoPrendario() {
        return "2".equals(this.claseCuenta);
    }

    /**
     * Es prestamo personal.
     *
     * @return true, if successful
     */
    public boolean esPrestamoPersonal() {
        return "4".equals(this.claseCuenta);
    }

    /**
     * Es prestamo hipotecario.
     *
     * @return true, if successful
     */
    public boolean esPrestamoHipotecario() {
        return "8".equals(this.claseCuenta);
    }

    /**
     * Es prestamo OpenCredit.
     *
     * @return true, if successful
     */
    public boolean esOpenCredit() {
        return ("O".equals(this.getClaseCuenta()) && TipoCuentaPrestamosEnum
                .fromCodigo(this.getTipoCuenta()) == TipoCuentaPrestamosEnum.TIPOCTA_PRESTAMO30);
    }

    /**
     * Es tipo pago.
     *
     * @return true, if successful
     */
    public boolean esTipoPago() {
        return ArrayUtils.contains(TIPO_CUENTAS_PARA_PAGO, this.getTipoCuenta());
    }

    /**
     * Checks if is cuenta principal.
     *
     * @return true, if is cuenta principal
     */
    public boolean esCuentaPrincipal() {
        final String jerarquiaPrincipal = "P";

        if (!TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo().equals(this.getTipoCuenta())
                && !TipoCuentaTarjeta.TIPOCTA_AMEX.getCodigo().equals(this.getTipoCuenta())) {

            return jerarquiaPrincipal.equalsIgnoreCase(this.getJerarquiaCuenta());
        }
        return false;
    }

    /**
     * Es saldo dolares.
     *
     * @return true, if successful
     */
    public boolean esSaldoDolares() {
        return TipoCuenta.CAJA_AHORRO_DOLARES.equals(this.getTipoCuentaEnum())
                || TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(this.getTipoCuentaEnum())
                || esCuentaUnica();
    }

    /**
     * Es saldo pesos.
     *
     * @return true, if successful
     */
    // se define como cuenta unica por los selectores al tener una cuenta unica
    // tenemos cuenta en dolares y pesos
    public boolean esSaldoPesos() {
        return TipoCuenta.CAJA_AHORRO_PESOS.equals(this.getTipoCuentaEnum())
                || TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(this.getTipoCuentaEnum()) || esCuentaUnica();
    }

    /**
     * Es cuenta unica.
     *
     * @return true, if successful
     */
    public boolean esCuentaUnica() {
        return TipoCuenta.CUENTA_UNICA.equals(this.getTipoCuentaEnum())
                || TipoCuenta.CUENTA_UNICA_DOLARES.equals(this.getTipoCuentaEnum())
                || TipoCuenta.CUENTA_UNICA_PESOS.equals(this.getTipoCuentaEnum());
    }

    /**
     * Es cuenta unica en pesos.
     *
     * @return true, if successful
     */
    public boolean esCuentaUnicaPesos() {
        return TipoCuenta.CUENTA_UNICA_PESOS.equals(this.getTipoCuentaEnum());
    }

    /**
     * Es cuenta unica en dolares.
     *
     * @return true, if successful
     */
    public boolean esCuentaUnicaDolares() {
        return TipoCuenta.CUENTA_UNICA_DOLARES.equals(this.getTipoCuentaEnum());
    }

    /**
     * Gets the saldo aper caja ahorro pesos.
     *
     * @return the saldoAperCajaAhorroPesos
     */
    public BigDecimal getSaldoAperCajaAhorroPesos() {
        return saldoAperCajaAhorroPesos;
    }

    /**
     * Sets the saldo aper caja ahorro pesos.
     *
     * @param saldoAperCajaAhorroPesos
     *            the saldoAperCajaAhorroPesos to set
     */
    public void setSaldoAperCajaAhorroPesos(BigDecimal saldoAperCajaAhorroPesos) {
        this.saldoAperCajaAhorroPesos = saldoAperCajaAhorroPesos;
    }

    /**
     * Gets the saldo aper cuenta corriente pesos.
     *
     * @return the saldoAperCuentaCorrientePesos
     */
    public BigDecimal getSaldoAperCuentaCorrientePesos() {
        return saldoAperCuentaCorrientePesos;
    }

    /**
     * Sets the saldo aper cuenta corriente pesos.
     *
     * @param saldoAperCuentaCorrientePesos
     *            the saldoAperCuentaCorrientePesos to set
     */
    public void setSaldoAperCuentaCorrientePesos(BigDecimal saldoAperCuentaCorrientePesos) {
        this.saldoAperCuentaCorrientePesos = saldoAperCuentaCorrientePesos;
    }

    /**
     * Obtener saldo formateado.
     *
     * @return the string
     */
    public String obtenerSaldoFormateado() {
        // quiere decir que es cuenta corriente o caja de ahorro
        if (!esCuentaUnica()) {
            return ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(this.getSaldoCuenta()));
        } else {
            return ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(this.getSaldoCUPesos()));
        }
    }

    /**
     * Obtener saldo.
     *
     * @return the string
     */
    // quiere decir que es cuenta corriente o caja de ahorro
    public String obtenerSaldo() {
        return esCuentaUnica() ? this.getSaldoCUPesos() : this.getSaldoCuenta();
    }

    /**
     * Carga el saldo al campo correspondiente.
     *
     * @param saldo
     *            the saldo
     */
    public void cargarSaldo(BigDecimal saldo) {
        if (esCuentaUnica()) {
            if (esCuentaUnicaPesos()) {
                this.saldoCUPesos = String.valueOf(saldo);
            } else {
                this.saldoCUDls = String.valueOf(saldo);
            }
        }
        this.saldoCuenta = ISBANStringUtils.formatearSaldoConSigno(saldo);
    }

    /**
     * Obtener nro cuenta formateado.
     *
     * @return the string
     */
    public String obtenerNroCuentaFormateado() {
        String nroSucursal = this.getNroSucursal().substring(1, this.getNroSucursal().length());
        int subStringPos = 0;

        if (this.getTipoCuentaEnum().getAbreviatura().equals(TITULOS)) {
            subStringPos = 8;
        } else {
            subStringPos = 9;
        }
        String nroCuentaParteUno = this.getNroCuentaProducto().substring(subStringPos, this.getNroCuentaProducto().length() - 1);
        String nroCuentaParteDos = this.getNroCuentaProducto().substring(this.getNroCuentaProducto().length() - 1,
                this.getNroCuentaProducto().length());

        return nroSucursal + "-" + nroCuentaParteUno + "/" + nroCuentaParteDos;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("index", index).append("tipoCuentaSinUnificar", tipoCuentaSinUnificar)
                .append("tipoCuenta", getTipoCuentaEnum()).append("nroSucursal", getNroSucursal())
                .append("nroOrdenFirmante", nroOrdenFirmante).append("codigoAplicacion", codigoAplicacion)
                .append("claseCuenta", claseCuenta).append("formaDeOperar", formaDeOperar)
                .append("nroTarjetaCredito", nroTarjetaCredito).append("estadoTarjetaCredito", estadoTarjetaCredito)
                .append("calidadDeParticipacion", calidadDeParticipacion).append("codigoPaquete", codigoPaquete)
                .append("clasePaquete", clasePaquete).append(" saldoCuenta", saldoCuenta)
                .append("saldoCUPesos", saldoCUPesos).append("saldoCUDls", saldoCUDls)
                .append("saldoParaConformar", saldoParaConformar).append("depositoEfectivo", depositoEfectivo)
                .append("subproductoAltair", subproductoAltair).append("monedaAltair", monedaAltair)
                .append("grupoAfinidad", grupoAfinidad).append("oficinaAltair", oficinaAltair)
                .append(" contratoAltair", contratoAltair).append("sucursalPaquete", sucursalPaquete)
                .append("numeroPaquete", numeroPaquete).append("convenioPAS", convenioPAS)
                .append("habilitadaParaTransferir", habilitadaParaTransferir).append("intervinientes", intervinientes)
                .append("esTarjetaParaEnviarAFecp", esTarjetaParaEnviarAFecp()).toString();
    }

    /**
     * Gets the nro cuenta tit.
     *
     * @return the nro cuenta tit
     */
    public String getNroCuentaTit() {
        return nroCuentaTit;
    }

    /**
     * Sets the nro cuenta tit.
     *
     * @param nroCuentaTit
     *            the new nro cuenta tit
     */
    public void setNroCuentaTit(String nroCuentaTit) {
        this.nroCuentaTit = nroCuentaTit;
    }

    /**
     * Gets the intervinientes.
     *
     * @return the intervinientes
     */
    public List<Interviniente> getIntervinientes() {
        return intervinientes;
    }

    /**
     * Sets the intervinientes.
     *
     * @param intervinientes
     *            the new intervinientes
     */
    public void setIntervinientes(List<Interviniente> intervinientes) {
        this.intervinientes = intervinientes;
    }

    /**
     * Puede realizar solicitud traspaso.
     *
     * @return the boolean
     */
    public Boolean puedeRealizarSolicitudTraspaso() {
        return this.isCuentaUnica() && !StringUtils.EMPTY.equals(getEstadoTarjetaCredito())
                && esIndicadorSobregiroHabilitado();
    }

    /**
     * Es indicador sobregiro habilitado.
     *
     * @return the boolean
     */
    private Boolean esIndicadorSobregiroHabilitado() {
        if (INDICADOR_SOBREGIRO_ACTIVO.equals(getIndicadorSobregiro())) {
            return true;
        }
        return false;
    }

    /**
     * Obtener tipo cuenta sin unificar dolares.
     *
     * @return the string
     */
    // Cuando obtengo la cuenta de los datos en sesion, devuelve
    // CUENTA_UNICA_PESOS aunque hayas elegido moneda dolares, ojo con esto
    public String obtenerTipoCuentaSinUnificarDolares() {
        if (TipoCuenta.CUENTA_UNICA.getDescripcionConMoneda().equals(this.getTipoCuentaEnum().getDescripcion())) {
            return getTipoCuentaSinUnificarDls();
        }
        return getTipoCuentaSinUnificar();
    }

    /**
     * Checks if is cuenta pesos.
     * 
     * @return true, if is cuenta pesos
     */
    public boolean isCuentaPesos() {

        return TipoCuenta.CAJA_AHORRO_PESOS.equals(this.getTipoCuentaEnum())
                || TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(this.getTipoCuentaEnum());
    }

    /**
     * Checks if is cuenta dolares.
     * 
     * @return true, if is cuenta dolares
     */
    public boolean isCuentaDolares() {

        return TipoCuenta.CAJA_AHORRO_DOLARES.equals(this.getTipoCuentaEnum())
                || TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(this.getTipoCuentaEnum());
    }

    /**
     * Verifica si es titular de tarjeta de credito.
     *
     * @return true, if successful
     */
    public boolean esTitularTarjeta() {
        return this.esTitular() && this.esTarjetaDeCredito();
    }

    /**
	 * Obtener saldo dolares formateado.
	 *
	 * @return the string
	 */
    public String obtenerSaldoDolaresFormateado() {
        if (!esSaldoDolares()) {
            return ISBANStringUtils.formatearSaldoSinAbs(BigDecimal.ZERO);
        } else if (!esCuentaUnica()) {
            return ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(this.getSaldoCuenta()));
        } else {
            return ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(this.getSaldoCUDls()));
        }
    }
    
    /**
	 * Obtener saldo dolares.
	 *
	 * @return the string
	 */
    public String obtenerSaldoDolares() {
        return esCuentaUnica() ? this.getSaldoCUDls() : this.getSaldoCuenta();
    }

	public int getCuentaOPRepatriacion() {
		return cuentaOPRepatriacion;
	}

	public void setCuentaOPRepatriacion(int cuenteOPRepatriacion) {
		this.cuentaOPRepatriacion = cuenteOPRepatriacion;
	}

	public String getSucursalCtaOpRep() {
		return sucursalCtaOpRep;
	}

	public void setSucursalCtaOpRep(String sucursalCtaOpRep) {
		this.sucursalCtaOpRep = sucursalCtaOpRep;
	}
    
	
    
    
}
