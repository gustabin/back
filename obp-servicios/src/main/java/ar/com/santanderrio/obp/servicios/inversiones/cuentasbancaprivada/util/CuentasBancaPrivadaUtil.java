package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoIdentificacion;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.DetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaOutEntity;
/**
 * The Class CuentasBancaPrivadaUtil.
 */
public class CuentasBancaPrivadaUtil {
    
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CuentasBancaPrivadaUtil.class);
	
	/** The Constant GUION. */
     public static final String GUION = "-";
    
    /** The Constant MIN_VALOR_SUCURSAL_BP. */
     public static final int MIN_VALOR_SUCURSAL_BP = 250;
     
    /** The Constant MAX_VALOR_SUCURSAL_BP. */
     public  static final int MAX_VALOR_SUCURSAL_BP = 259;

 	/** The Constant PAQUETE_BP_3001. */
 	private static final Object PAQUETE_BP_3001 = "3001";

 	/** The Constant PAQUETE_BP_3101. */
 	private static final Object PAQUETE_BP_3101 = "3101";

 	/** The Constant PROD_ALTAIR_CTA_REPATR. */
 	public static final Object PROD_ALTAIR_CTA_REPATR = "03";

 	/** The Constant PROD_ALTAIR_CTA_REPATR. */
 	public static final Object SUBPROD_ALTAIR_CTA_REPATR = "0005";

 	/** The Constant PROD_ALTAIR_CTA_TRANSACCIONAL. */
 	public static final Object PROD_ALTAIR_CTA_TRANSACCIONAL = "07";

 	/** The Constant SUBPROD_ALTAIR_0005. */
 	public static final Object SUBPROD_ALTAIR_0005 = "0005";

 	public static final String SUBPROD_ALTAIR_REPATR_0007 = "0007";

    /**
     * Convertir cuentas view.
     *
     * @param respuesta the respuesta
     * @return the respuesta
     */
    static public Respuesta<CuentasView> convertirCuentasView(Respuesta<List<ResumenDetalleCuenta>> respuesta) {
        List<CuentasAdhesionDebitoView> cuentas = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasView cuentaView = new CuentasView();
        CuentasAdhesionDebitoView cuenta;
        List<DetalleCuenta> detalleCuentaList = new ArrayList<DetalleCuenta>();
        Respuesta<CuentasView> respuestaView = new Respuesta<CuentasView>();
        List<ResumenDetalleCuenta> resumenDetalleCuentas = respuesta.getRespuesta();
        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())
                || EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {
            Iterator<ResumenDetalleCuenta> it = resumenDetalleCuentas.iterator();
            while (it.hasNext()) {
                ResumenDetalleCuenta resumenDetalleCuenta = it.next();
                cuenta = obtenerCuenta(resumenDetalleCuenta);
                cuentas.add(cuenta);
                IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
                identificacionCuenta.setNroCuentaProducto(resumenDetalleCuenta.getNroCuentaProducto());
                identificacionCuenta.setNroSucursal(resumenDetalleCuenta.getNroSucursal());
                DetalleCuenta detalleCuenta = new DetalleCuenta();
                detalleCuenta.setIdentificacionCuenta(identificacionCuenta);
                detalleCuenta.setIsTraspasoAutomatico(cuenta.getIsTraspasoAutomatico());
                detalleCuentaList.add(detalleCuenta);
            }
            cuentaView.setCuentas(cuentas);
            respuestaView.setRespuesta(cuentaView);
            respuestaView.setItemMensajeRespuesta(respuesta.getItemsMensajeRespuesta());
            respuestaView.setEstadoRespuesta(respuesta.getEstadoRespuesta());
            respuestaView.setRespuestaVacia(false);
        } else {
            respuestaView.setEstadoRespuesta(respuesta.getEstadoRespuesta());
            respuestaView.setItemMensajeRespuesta(respuesta.getItemsMensajeRespuesta());
            respuestaView.setRespuestaVacia(true);
        }
        return respuestaView;
    }
    /**
     * Obtener cuenta.
     *
     * @param resumenDetalleCuenta the resumen detalle cuenta
     * @return the cuentas adhesion debito view
     */
    static public CuentasAdhesionDebitoView obtenerCuenta(ResumenDetalleCuenta resumenDetalleCuenta) {
        CuentasAdhesionDebitoView cuenta = getCuentasAdhesionDebitoView(resumenDetalleCuenta.getCliente());
        cuenta.setDescripcionTipoCuenta(obtenerDescripcionTipoCuenta(resumenDetalleCuenta.getTipoCuenta()));
        cuenta.setAbreviaturaTipoCuenta(obtenerAbreviaturaTipoCuenta(resumenDetalleCuenta.getTipoCuenta()));
        cuenta.setNumero(resumenDetalleCuenta.getNumeroCuentaYSucursal());
        cuenta.setCbu(resumenDetalleCuenta.getCbu());
        cuenta.setIsTraspasoAutomatico(resumenDetalleCuenta.isCuentaTraspasoAutomatico());
        cuenta.setIsCerrada(resumenDetalleCuenta.isCuentaCerrada());
        cuenta.setShowSolicitarTraspaso(resumenDetalleCuenta.getShowSolicitarTraspaso());
        cuenta.setShowRealizarTraspaso(resumenDetalleCuenta.getShowRealizarTraspaso());
        cuenta.setIsCuentaUnica(resumenDetalleCuenta.isCuentaUnica());
        cuenta.setIsFavorito(resumenDetalleCuenta.isFavorita());
        cuenta.setAlias(resumenDetalleCuenta.getAlias());
        cuenta.setFechaDesdeMovimiento(resumenDetalleCuenta.getFechaDesdeMovimiento());
        cuenta.setFechaHastaMovimiento(resumenDetalleCuenta.getFechaHastaMovimiento());
        if (StringUtils.isEmpty(cuenta.getAlias())) {
            cuenta.setHasAlias(false);
        } else {
            cuenta.setHasAlias(true);
        }
        cuenta.setSaldoDescubierto(
                ISBANStringUtils.formatearSaldo(resumenDetalleCuenta.getSaldoDescubiertoDisponible()));
        cuenta.setSaldoCuentaCorriente(
                ISBANStringUtils.formatearSaldo(resumenDetalleCuenta.getSaldoCuentaCorrientePesos()));
        cuenta.setSaldoCajaAhorro(ISBANStringUtils.formatearSaldo(resumenDetalleCuenta.getSaldoCajaAhorroPesos()));
        cuenta.setSignoSaldoCajaAhorro(StringUtils.EMPTY);
        cuenta.setSignoSaldoCuentaCorriente(StringUtils.EMPTY);
        // igual a cero
        if (resumenDetalleCuenta.getDisponibleDescubierto().compareTo(BigDecimal.ZERO) == 0) {
            cuenta.setIsSaldoDescubiertoCero(true);
        }
        boolean cuentaUnica = resumenDetalleCuenta.isCuentaUnica();
        if (cuentaUnica) {
            boolean traspasoAutomatico = resumenDetalleCuenta.isCuentaTraspasoAutomatico();
            if (traspasoAutomatico) {
                setSaldoPesos(cuenta, resumenDetalleCuenta.getSaldoPesos());
                setSaldoDolares(cuenta, resumenDetalleCuenta.getSaldoDolares());
            } else {
                setSaldoPesos(cuenta, resumenDetalleCuenta.getSaldoUnificado());
                setSaldoDolares(cuenta, resumenDetalleCuenta.getSaldoCajaAhorroDolares());
                // tooltip
                boolean convenioPas = resumenDetalleCuenta.isConvenioPAS();
                if (convenioPas) {
                    setSaldoCajaAhorro(cuenta, resumenDetalleCuenta.getSaldoCuentaSueldoPesos());
                    cuenta.setIsCuentaSueldo(Boolean.TRUE);
                } else {
                    setSaldoCajaAhorro(cuenta, resumenDetalleCuenta.getSaldoCajaAhorroPesos());
                    cuenta.setIsCuentaSueldo(Boolean.FALSE);
                }
                setSaldoCuentaCorriente(cuenta, resumenDetalleCuenta.getSaldoCuentaCorrientePesos());
            }
        } else {
            setSaldoDolares(cuenta, resumenDetalleCuenta.getSaldoDolares());
            setSaldoPesos(cuenta, resumenDetalleCuenta.getSaldoPesos());
        }
        cuenta.setShowSaldoPesos(isShowPesos(resumenDetalleCuenta.getTipoCuenta()));
        cuenta.setShowSaldoDolares(isShowDolares(resumenDetalleCuenta.getTipoCuenta()));
        // disponible utilizado
        cuenta.setIsDescubiertoUtilizado(resumenDetalleCuenta.isDescubiertoUtilizado());
        cuenta.setShowDescubierto(resumenDetalleCuenta.mostrarDescubierto());
        return cuenta;
    }
    
    /**
     * Gets the cuentas adhesion debito view.
     *
     * @param cliente the cliente
     * @return the cuentas adhesion debito view
     */
    static public CuentasAdhesionDebitoView getCuentasAdhesionDebitoView(Cliente cliente) {
        CuentasAdhesionDebitoView cuenta = new CuentasAdhesionDebitoView();
        cuenta.setNombreCliente(WordUtils.capitalizeFully(cliente.getNombre()));
        cuenta.setApellidoCliente(WordUtils.capitalizeFully(cliente.getApellido1()));
        TipoIdentificacion ti = TipoIdentificacion.fromKey(cliente.getTipoDocumento());
        cuenta.setTipoIdentificacion(ti.getDescripcion());
        cuenta.setNumeroIdentificacion(formatearDNIConPuntos(cliente.getDni()));
        return cuenta;
    }
    /**
     * Formatear DNI con puntos.
     *
     * @param dni
     *            the dni
     * @return the string
     */
    static public String formatearDNIConPuntos(String dni) {
        NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.GERMAN);
        String dniFinal = null;
        if (StringUtils.isNotBlank(dni)) {
            Long dniLong = Long.parseLong(dni);
            dniFinal = numberFormatter.format(dniLong);
        }
        return dniFinal;
    }
    
    /**
     * Sets the saldo cuenta corriente.
     *
     * @param cuenta the cuenta
     * @param saldoCuentaCorrientePesos the saldo cuenta corriente pesos
     */
    static public void setSaldoCuentaCorriente(CuentasAdhesionDebitoView cuenta, BigDecimal saldoCuentaCorrientePesos) {
        cuenta.setSaldoCuentaCorriente(ISBANStringUtils.formatearSaldo(saldoCuentaCorrientePesos));
        cuenta.setSignoSaldoCuentaCorriente(StringUtils.EMPTY);
        if (saldoCuentaCorrientePesos.compareTo(BigDecimal.ZERO) == 0) {
            cuenta.setIsSaldoCuentaCorrienteCero(true);
        } else if (saldoCuentaCorrientePesos.compareTo(BigDecimal.ZERO) < 0) {
            cuenta.setSignoSaldoCuentaCorriente(GUION);
            cuenta.setIsSaldoCuentaCorrienteNegativo(true);
        }
    }
    /**
     * Sets the saldo caja ahorro.
     *
     * @param cuenta
     *            the cuenta
     * @param saldoCajaAhorroPesos
     *            the saldo caja ahorro pesos
     */
    static public void setSaldoCajaAhorro(CuentasAdhesionDebitoView cuenta, BigDecimal saldoCajaAhorroPesos) {
        cuenta.setSaldoCajaAhorro(ISBANStringUtils.formatearSaldo(saldoCajaAhorroPesos));
        cuenta.setSignoSaldoCajaAhorro(StringUtils.EMPTY);
        if (saldoCajaAhorroPesos.compareTo(BigDecimal.ZERO) == 0) {
            cuenta.setIsSaldoCajaAhorroCero(true);
        } else if (saldoCajaAhorroPesos.compareTo(BigDecimal.ZERO) < 0) {
            cuenta.setSignoSaldoCajaAhorro(GUION);
            cuenta.setIsSaldoCajaAhorroNegativo(true);
        }
    }
    /**
     * Sets the saldo dolares.
     *
     * @param cuenta
     *            the cuenta
     * @param saldoDolares
     *            the saldo dolares
     */
    static public void setSaldoDolares(CuentasAdhesionDebitoView cuenta, BigDecimal saldoDolares) {
        cuenta.setSaldoDolares(ISBANStringUtils.formatearSaldo(saldoDolares));
        cuenta.setSignoSaldoDolares(StringUtils.EMPTY);
        if (saldoDolares.compareTo(BigDecimal.ZERO) == 0) {
            cuenta.setIsSaldoDolaresCero(true);
        } else if (saldoDolares.compareTo(BigDecimal.ZERO) < 0) {
            cuenta.setSignoSaldoDolares(GUION);
            cuenta.setIsSaldoDolaresNegativo(true);
        }
    }
    /**
     * Sets the saldo pesos.
     *
     * @param cuenta
     *            the cuenta
     * @param saldoPesos
     *            the saldo pesos
     */
    static public void setSaldoPesos(CuentasAdhesionDebitoView cuenta, BigDecimal saldoPesos) {
        cuenta.setSaldoPesos(ISBANStringUtils.formatearSaldo(saldoPesos));
        cuenta.setSignoSaldoPesos(StringUtils.EMPTY);
        // igual a cero
        if (saldoPesos.compareTo(BigDecimal.ZERO) == 0) {
            cuenta.setIsSaldoPesosCero(true);
        } else if (saldoPesos.compareTo(BigDecimal.ZERO) < 0) {
            cuenta.setSignoSaldoPesos(GUION);
            cuenta.setIsSaldoPesosNegativo(true);
        }
    }
    
    /**
     * Checks if is show dolares.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @return true, if is show dolares
     */
    static public boolean isShowDolares(String tipoCuenta) {
        boolean result = false;
        if (TipoCuenta.CUENTA_UNICA.equals(TipoCuenta.fromCodigo(tipoCuenta))) {
            result = true;
        }
        if (TipoCuenta.fromCodigo(tipoCuenta).equals(TipoCuenta.CAJA_AHORRO_DOLARES)) {
            result = true;
        }
        if (TipoCuenta.fromCodigo(tipoCuenta).equals(TipoCuenta.CUENTA_CORRIENTE_DOLARES)) {
            result = true;
        }
        return result;
    }
    /**
     * Checks if is show pesos.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @return true, if is show pesos
     */
    static public boolean isShowPesos(String tipoCuenta) {
        boolean result = false;
        if (TipoCuenta.fromCodigo(tipoCuenta).equals(TipoCuenta.CUENTA_UNICA)) {
            result = true;
        }
        if (TipoCuenta.fromCodigo(tipoCuenta).equals(TipoCuenta.CAJA_AHORRO_PESOS)) {
            result = true;
        }
        if (TipoCuenta.fromCodigo(tipoCuenta).equals(TipoCuenta.CUENTA_CORRIENTE_PESOS)) {
            result = true;
        }
        return result;
    }
    /**
     * Obtener descripcion tipo cuenta.
     *
     * @param codigo
     *            the codigo
     * @return the string
     */
    static public String obtenerDescripcionTipoCuenta(String codigo) {
        TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(codigo);
        String descripcion = codigo;
        if (tipoCuenta != null) {
            descripcion = tipoCuenta.getDescripcionConMoneda();
        }
        return descripcion;
    }
    
    /**
     * Obtener abreviatura tipo cuenta.
     *
     * @param codigo
     *            the codigo
     * @return the string
     */
    static public String obtenerAbreviaturaTipoCuenta(String codigo) {
        TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(codigo);
        String descripcion = codigo;
        if (tipoCuenta != null) {
            descripcion = tipoCuenta.getAbreviatura();
        }
        return descripcion;
    }
    
    
      /**
     * Checks if is banca privada.
     *
     * @param cuentaOrigen the cuenta origen
     * @param cuentaDestino the cuenta destino
     * @return the boolean
     */
    static  public Boolean isBancaPrivada(Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        if (cuentaOrigen != null && isCuentaBP(cuentaOrigen.getNroSucursal())) {
            return true;
        } else if (cuentaDestino != null && isCuentaBP(cuentaDestino.getNroSucursal())) {
            return true;
        }
        return false;
    }
    /**
     * Checks if is cuenta BP.
     *
     * @param cuenta the cuenta
     * @return true, if is cuenta BP
     */
    static public  boolean isCuentaBP(String nroSucursalCuenta) {
    	if(ISBANStringUtils.isEmptyOrNull(nroSucursalCuenta) || nroSucursalCuenta.trim().isEmpty()) { 
    		return false;
    	}
        int nroSucursal = Integer.parseInt(nroSucursalCuenta);
        return (nroSucursal >= MIN_VALOR_SUCURSAL_BP && nroSucursal <= MAX_VALOR_SUCURSAL_BP);
    }
    
    /**
     * Cuentas BP monetarias activas.
     *
     * @param cliente the cliente
     * @return the boolean
     */
    
    static public Boolean cuentasBPMonetariasActivas(Cliente cliente) { 
        List<Cuenta> cuentas = cliente.getCuentasPrivadas();
        for (Cuenta cuenta : cuentas) {
            if (cuenta.isTipoCuentaMonetaria()) {
                return true;
            }
        }
        return false;
    }
    
    static public Boolean isCuentaPrivada(Cuenta cuentaBP) {
    	if(cuentaBP != null && isCuentaBP(cuentaBP.getNroSucursal()) && 
    			(cuentaBP.isCuentaPesos() || cuentaBP.isCuentaDolares() || cuentaBP.isCuentaUnica())) {
    		return Boolean.TRUE;
    	}
    	return Boolean.FALSE;
    }

    public static Boolean isCuentaHabilitadaTransferenciaBP(Cuenta cuenta) {
        return isCuentaPrivada(cuenta) && (cuenta.getProductoAltair().equals(PROD_ALTAIR_CTA_TRANSACCIONAL)
                || (cuenta.getProductoAltair().equals(PROD_ALTAIR_CTA_REPATR)
                    && cuenta.getSubproductoAltair().equals(SUBPROD_ALTAIR_CTA_REPATR)));
    }

	/**
	 * Inits the resumen detalle cuenta.
	 *
	 * @param abstractCuenta the abstract cuenta
	 * @param saldoCuenta the saldo cuenta
	 * @return the resumen detalle cuenta
	 */
	static public ResumenDetalleCuenta initResumenDetalleCuenta(AbstractCuenta abstractCuenta, ConsultaSaldoCtasConAperturaOutEntity saldoCuenta) {
        ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
        resumenDetalleCuenta.setCuentaPrincipal(abstractCuenta.isCuentaPrincipal());
        resumenDetalleCuenta.setNroCuentaProducto(abstractCuenta.getNroCuentaProducto());
        resumenDetalleCuenta.setNroSucursal(abstractCuenta.getNroSucursal());
        resumenDetalleCuenta.setTipoCuenta(abstractCuenta.getTipoCuenta());
        resumenDetalleCuenta.setCuentaUnica(abstractCuenta.isCuentaUnica());
        resumenDetalleCuenta.setCuentaCerrada(abstractCuenta.isCuentaCerrada());
        resumenDetalleCuenta.setFavorita(abstractCuenta.getIsFavorita());
        resumenDetalleCuenta.setAlias(abstractCuenta.getAlias());
        resumenDetalleCuenta.setCbu(abstractCuenta.getCbu());
        
        if("02".equals(abstractCuenta.getTipoCuenta())) {
            resumenDetalleCuenta.setSaldoDolares(getSaldoBigDecimal(saldoCuenta.getSaldoDolares()));
            resumenDetalleCuenta.setSaldoPesos(getSaldoBigDecimal(saldoCuenta.getSaldo()));
            resumenDetalleCuenta.setCuentaTraspasoAutomatico(true);
        }
        
        if(TipoCuenta.CAJA_AHORRO_PESOS.equals(abstractCuenta.getTipoCuentaEnum()) || 
        		TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(abstractCuenta.getTipoCuentaEnum())  ) {
            resumenDetalleCuenta.setSaldoPesos(getSaldoBigDecimal(saldoCuenta.getSaldo()));
        }
        
        if( TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(abstractCuenta.getTipoCuentaEnum()) || 
        		TipoCuenta.CAJA_AHORRO_DOLARES.equals(abstractCuenta.getTipoCuentaEnum())) {
        	resumenDetalleCuenta.setSaldoDolares(getSaldoBigDecimal(saldoCuenta.getSaldoDolares()));
        }
        if (!abstractCuenta.isCuentaCerrada()) {
            resumenDetalleCuenta.setConvenioPAS(((Cuenta) abstractCuenta).isConvenioPAS());
        }
        
        resumenDetalleCuenta.setCodigoTitularidad(abstractCuenta.getCodigoTitularidad());
        resumenDetalleCuenta.setCliente(abstractCuenta.getCliente());
        return resumenDetalleCuenta;
    }
	
	   /**
     * Gets the saldo big decimal.
     *
     * @param saldo the saldo
     * @return the saldo big decimal
     */
    static public BigDecimal getSaldoBigDecimal(String saldo) {
        try {
            return ISBANStringUtils.convertirStrToBigDecimal(saldo, 2);
        } catch (ImporteConvertException e) {
            LOGGER.info("Error al parsear importe o no existe importe", e);
        }
        return null;
    }
    
	/**
	 * Busca la cuenta a partir de IdentificacionCuenta y las cuentas del cliente
	 * en sesion.
	 *
	 * @param id the id
	 * @param cliente            the cliente
	 * @return the cuenta
	 */
	static public Cuenta buscarCuentaPorId(IdentificacionCuenta id, Cliente cliente) {
		Cuenta cuentaEncontrada = null;
		for (Cuenta cuenta : cliente.getCuentasPrivadas()) {
			if (coincideCuentaId(cuenta, id)) {
				cuentaEncontrada = cuenta;
			}
		}
		return cuentaEncontrada;
	}

    
	/**
	 * Coincide cuenta id.
	 *
	 * @param cuenta the cuenta
	 * @param id the id
	 * @return true, if successful
	 */
	static private boolean coincideCuentaId(Cuenta cuenta, IdentificacionCuenta id) {
		boolean eqNroCuentaProducto = Long.parseLong(cuenta.getNroCuentaProducto()) == Long
				.parseLong(id.getNroCuentaProducto());
		boolean eqNroSucursal = Long.parseLong(cuenta.getNroSucursal()) == Long.parseLong(id.getNroSucursal());
		return eqNroCuentaProducto && eqNroSucursal;
	}
	
 	/**
	  * Valida cuenta BP comex.
	  *
	  * @param cuentaBP the cuenta BP
	  * @return true, if successful
	  */
	static public boolean validaCuentaBPComex(Cuenta cuentaBP) {
		if (PROD_ALTAIR_CTA_TRANSACCIONAL.equals(cuentaBP.getProductoAltair()) && 
				(PAQUETE_BP_3001.equals(cuentaBP.getCodigoPaquete()) || PAQUETE_BP_3101.equals(cuentaBP.getCodigoPaquete()))) {
			return true;
		} else if (PROD_ALTAIR_CTA_REPATR.equals(cuentaBP.getProductoAltair()) &&
                (SUBPROD_ALTAIR_0005.equals(cuentaBP.getSubproductoAltair()) ||
                    SUBPROD_ALTAIR_REPATR_0007.equals(cuentaBP.getSubproductoAltair()))) {
			return true;
		}
		return false;
	}

}