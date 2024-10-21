/*
 * 
 */

package ar.com.santanderrio.obp.servicios.clientes.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.com.santanderrio.obp.servicios.prestamos.utils.EnumPrestamosCuotasRefinanciadas;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.TipoContratoEnum;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.AccesosInversiones;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaCerrada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerSaldoCuentasCustodiaResponse;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class Cliente.
 *
 * @author Jonatan_Bocian
 */
public class Cliente extends ResumenCliente {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Cliente.class);

	public static final String EARLY_IRREGULAR = "EARLY IRREGULAR";
	public static final String EARLY_CURRENT= "EARLY CURRENT";
	public static final String CUOTAPHONE = "CUOTAPHONE";

	/** The nombre. */
	private String nombre;

	/** The apellido1. */
	private String apellido1;

	/** The apellido2. */
	private String apellido2;

	/** The cliente select. */
	private Boolean clienteSelect = false;

	/** The cliente Banca Privada. */
	private Boolean clienteBancaPrivada = false;

	/** The cliente platinum. */
	private Boolean clientePlatinum = false;

	/** The cliente gold. */
	private Boolean clienteGold = false;

	/** The cuentas. */
	private List<Cuenta> cuentas;
	
	/** The cuentas privadas. */
	private List<Cuenta> cuentasPrivadas;
	
	/**
	 * Cuentas banca privada, el par cta titulo y cta operativa relacionadas.
	 */
	private List<CuentaBancaPrivada> cuentasBancaPrivada;

	/** Lista de cuentas titulo retail. */
	private List<Cuenta> cuentasRetail;
	
	/** Lista de cuentas titulo retail repatriacion. */
	private List<Cuenta> cuentasTitRtlRepatriacion;
	
	/** Lista de cuentas titulo retail repatriacion. */
	private List<Cuenta> cuentasTitBPRepatriacion;
	
	/** The cuentas cerradas. */
	private List<CuentaCerrada> cuentasCerradas;

	/** The segmento. */
	private Segmento segmento = new Segmento();

	/** Tipo de CUIL=L o CUIT=T. */
	private String tipoCUILCUIT;

	/** numero de CUIL / CUIT. */
	private String numeroCUILCUIT;

	/** The soft token habilitado offset. */
	private static int SOFT_TOKEN_HABILITADO_OFFSET = 4;

	/** The soft token habilitado char. */
	private static char SOFT_TOKEN_HABILITADO_CHAR = '3';

	/** The Constant NRO_TC. */
	public static final String NRO_TC = "00000000000000000000";

	/** The os. */
	private String os = "";

	/** The contratos. */
	private HashMap<TipoContratoEnum, Boolean> contratos;

	/** The tiene trj cred. */
	private Boolean tieneTrjCred = null;

	/** The cuenta validacion. */
	private Cuenta cuentaValidacion = null;

	/** The primer ingreso. */
	private boolean primerIngreso = false;

	/** The cuenta is ExCitiCliente. */
	private Boolean isExCiti = null;

	/** The cuenta is CuentaMigrada. */
	private Boolean isCuentaMigrada = Boolean.FALSE;

	/** The sexo. */
	private String sexo;

	/** The cuentas custodia. */
	private List<DatosObtenerSaldoCuentasCustodiaResponse> cuentasCustodia = new ArrayList<DatosObtenerSaldoCuentasCustodiaResponse>();

    /** The cuentas titulo RTL. */
    private List<CuentaTituloView> cuentasTituloRTL = new ArrayList<CuentaTituloView>();
    
    /** The sin productos. */
    private Boolean sinProductos =  Boolean.FALSE;

    /** The Constant UN_ESPACIO. */
    public static final String UN_ESPACIO = " ";
    
    /** The nroDocCnsDocXNup. */
    private String nroDocCnsDocXNup;

    /** The tipoDocCnsDocXNup. */
    private String tipoDocCnsDocXNup;
    
    private boolean hostNocturno;
    /** The Programa beneficios. */
    private String programaBeneficios = "";
    
    /** The is nova. */
    private boolean isNova;
    
    /** The email login mya. */
    private String emailLoginMya="";

    /** The queue turn id. */
    private String queueTurnId;

	/** The type refinancing. */
	private String tipoRefinanciacion;
	
	private AccesosInversiones accesosInversiones;


	/**
     * Instantiates a new cliente.
     */
    public Cliente() {
        cuentas = new ArrayList<Cuenta>();
        cuentasPrivadas = new ArrayList<Cuenta>();
        cuentasRetail = new ArrayList<Cuenta>();
        cuentasBancaPrivada = new ArrayList<CuentaBancaPrivada>();
        contratos = new HashMap<TipoContratoEnum, Boolean>();
        cuentasTitRtlRepatriacion=new ArrayList<Cuenta>();
        cuentasTitBPRepatriacion=new ArrayList<Cuenta>();
        
    }

	/**
	 * Instantiates a new cliente.
	 *
	 * @param resumenCliente
	 *            the resumen cliente
	 */
	public Cliente(ResumenCliente resumenCliente) {
		this();
		if (resumenCliente != null) {
			this.setUsuarioRacf(resumenCliente.getUsuarioRacf());
			this.setPasswordRacf(resumenCliente.getPasswordRacf());
			this.setTipoRacf(resumenCliente.getTipoRacf());
			this.setDni(resumenCliente.getDni());
			this.setNup(resumenCliente.getNup());
			this.setFechaNacimiento(resumenCliente.getFechaNacimiento());
			this.setTipoPersona(resumenCliente.getTipoPersona());
			this.setMarcaANPH(resumenCliente.getMarcaANPH());
			this.setTipoClave(resumenCliente.getTipoClave());
			this.setTipoDocumento(resumenCliente.getTipoDocumento());
			this.setValorSinonimo(resumenCliente.getValorSinonimo());
		}
	}

	/**
	 * Gets the cliente select.
	 *
	 * @return the clienteSelect
	 */
	public Boolean getClienteSelect() {
		return clienteSelect;
	}

	/**
	 * Setter para cliente select.
	 *
	 * @param clienteSelect
	 *            the clienteSelect to set
	 */
	public void setClienteSelect(Boolean clienteSelect) {
		this.clienteSelect = clienteSelect;
	}

	/**
	 * Gets the cliente banca privada.
	 *
	 * @return the cliente banca privada
	 */
	public Boolean getClienteBancaPrivada() {
		return clienteBancaPrivada;
	}

	/**
	 * Sets the cliente banca privada.
	 *
	 * @param clienteBancaPrivada
	 *            the new cliente banca privada
	 */
	public void setClienteBancaPrivada(Boolean clienteBancaPrivada) {
		this.clienteBancaPrivada = clienteBancaPrivada;
	}

	/**
	 * Gets the cliente platinum.
	 *
	 * @return the clientePlatinum
	 */
	public Boolean getClientePlatinum() {
		return clientePlatinum;
	}

	/**
	 * Setter para cliente platinum.
	 *
	 * @param clientePlatinum
	 *            the clientePlatinum to set
	 */
	public void setClientePlatinum(Boolean clientePlatinum) {
		this.clientePlatinum = clientePlatinum;
	}

	/**
	 * Gets the cliente gold.
	 *
	 * @return the clienteGold
	 */
	public Boolean getClienteGold() {
		return clienteGold;
	}

	/**
	 * Setter para cliente gold.
	 *
	 * @param clienteGold
	 *            the clienteGold to set
	 */
	public void setClienteGold(Boolean clienteGold) {
		this.clienteGold = clienteGold;
	}

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	/**
	 * Setter para cuentas.
	 *
	 * @param cuentas
	 *            the cuentas to set
	 */
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the cuentas privadas.
	 *
	 * @return the cuentasPrivadas
	 */
	public List<Cuenta> getCuentasPrivadas() {
		return cuentasPrivadas;
	}

	/**
	 * Setter para cuentas privadas.
	 *
	 * @param cuentasPrivadas
	 *            the cuentasPrivadas to set
	 */
	public void setCuentasPrivadas(List<Cuenta> cuentasPrivadas) {
		this.cuentasPrivadas = cuentasPrivadas;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter para nombre.
	 *
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the apellido1.
	 *
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * Setter para apellido1.
	 *
	 * @param apellido1
	 *            the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * Gets the apellido2.
	 *
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * Setter para apellido2.
	 *
	 * @param apellido2
	 *            the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	/**
	 * Gets the cuentas cerradas.
	 *
	 * @return the cuentasCerradas
	 */
	public List<CuentaCerrada> getCuentasCerradas() {
		return cuentasCerradas;
	}

	/**
	 * Setter para cuentas cerradas.
	 *
	 * @param cuentasCerradas
	 *            the cuentasCerradas to set
	 */
	public void setCuentasCerradas(List<CuentaCerrada> cuentasCerradas) {
		this.cuentasCerradas = cuentasCerradas;
	}

	/**
	 * Gets the segmento.
	 *
	 * @return the segmento
	 */
	public Segmento getSegmento() {
		return segmento;
	}

	/**
	 * Gets the tipo CUILCUIT.
	 *
	 * @return the tipoCUILCUIT
	 */
	public String getTipoCUILCUIT() {
		return tipoCUILCUIT;
	}

	/**
	 * Sets the tipo CUILCUIT.
	 *
	 * @param tipoCUILCUIT
	 *            the tipoCUILCUIT to set
	 */
	public void setTipoCUILCUIT(String tipoCUILCUIT) {
		this.tipoCUILCUIT = tipoCUILCUIT;
	}

	/**
	 * Gets the numero CUILCUIT.
	 *
	 * @return the numeroCUILCUIT
	 */
	public String getNumeroCUILCUIT() {
		return numeroCUILCUIT;
	}

	/**
	 * Sets the numero CUILCUIT.
	 *
	 * @param numeroCUILCUIT
	 *            the numeroCUILCUIT to set
	 */
	public void setNumeroCUILCUIT(String numeroCUILCUIT) {
		this.numeroCUILCUIT = numeroCUILCUIT;
	}

	/**
	 * Devuelve true si el cliente tiene asociada una tarjeta de coordenas
	 * Grupo_Afinidad <> 000000 --> tiene tarjeta de coordenadas.
	 *
	 * @return true si el cliente tiene asociado una tarjeta de coordenadas
	 */
	public boolean tieneTarjetaCoordenadas() {

		for (Cuenta cuenta : this.getCuentas()) {
			if (cuenta.getTipoCuenta().equals(Cuenta.TIPOCTA_BANELCO) && !"000000".equals(cuenta.getGrupoAfinidad())) {
				return true;
			}
		}
		for (Cuenta cuenta : this.getCuentasPrivadas()) {
            if (cuenta.getTipoCuenta().equals(Cuenta.TIPOCTA_BANELCO) && !"000000".equals(cuenta.getGrupoAfinidad())) {
                return true;
            }
        }
		return false;
	}

	/**
	 * Tiene tarjetas debito sin coordenadas ni token.
	 *
	 * @return true, if successful
	 */
	public boolean tieneTarjetasDebitoSinCoordenadasNiToken() {
		List<Boolean> listaDeTarjetas = new ArrayList<Boolean>();

		for (Cuenta cuenta : this.getCuentas()) {
			if (cuenta.getTipoCuenta().equals(Cuenta.TIPOCTA_BANELCO)) {
				if ("000000".equals(cuenta.getGrupoAfinidad())) {
					listaDeTarjetas.add(Boolean.TRUE);
				} else {
					listaDeTarjetas.add(Boolean.FALSE);
				}
			}
		}
		return validadorDeTarjetas(listaDeTarjetas);
	}

	/**
	 * Validador de tarjetas.
	 *
	 * @param listaDeTarjetas
	 *            the lista de tarjetas
	 * @return true, if successful
	 */
	public boolean validadorDeTarjetas(List<Boolean> listaDeTarjetas) {
		if (listaDeTarjetas.size() == 0) {
			return false;
		} else if (listaDeTarjetas.size() == 1) {
			return listaDeTarjetas.get(0);
		} else if (listaDeTarjetas.size() > 1 && listaDeTarjetas.contains(Boolean.FALSE)) {
			return false;
		} else {
			return true;
		}

	}
	
	public boolean tieneTarjetasDeDebito() {
		boolean tieneTarjetaDeDebito = false;
		for (Cuenta cuenta : this.getCuentas()) {
			if (cuenta.esBanelco() && cuenta.esTitular()) {
				tieneTarjetaDeDebito = true;
			}
		}
		return tieneTarjetaDeDebito;
	}

	/**
	 * Busca si en al menos una cuenta banelco esta habilitado a usar softtoken.
	 *
	 * @return true, if successful
	 */
	public boolean tieneSoftToken() {
		List<Cuenta> cuentas = this.getCuentas();
		for (Cuenta cuenta : cuentas) {
			if (TipoCuenta.BANELCO.equals(cuenta.getTipoCuentaEnum())
					&& cuenta.getGrupoAfinidad().length() > SOFT_TOKEN_HABILITADO_OFFSET
					&& SOFT_TOKEN_HABILITADO_CHAR == cuenta.getGrupoAfinidad().charAt(SOFT_TOKEN_HABILITADO_OFFSET)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the tiene trj cred.
	 *
	 * @return the tiene trj cred
	 */
	public Boolean getTieneTrjCred() {
		return tieneTrjCred;
	}

	/**
	 * Sets the tiene trj cred.
	 *
	 * @param tieneTrjCred
	 *            the new tiene trj cred
	 */
	public void setTieneTrjCred(Boolean tieneTrjCred) {
		this.tieneTrjCred = tieneTrjCred;
	}

	/**
	 * Sets the cuenta validacion.
	 *
	 * @param cuentaValidacion
	 *            the new cuenta validacion
	 */
	public void setCuentaValidacion(Cuenta cuentaValidacion) {
		this.cuentaValidacion = cuentaValidacion;
	}

	/**
	 * Gets the cuenta validacion.
	 *
	 * @return the cuenta validacion
	 */
	public Cuenta getCuentaValidacion() {
		return this.cuentaValidacion;
	}

	/**
	 * Setter para segmento.
	 *
	 * @param segmento
	 *            el nuevo segmento
	 */
	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		HashCodeBuilder hcb = new HashCodeBuilder().append(apellido1).append(apellido2).append(clienteGold)
				.append(clientePlatinum).append(clienteSelect).append(segmento).append(nombre);
		return hcb.toHashCode();

	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
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
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Cliente other = (Cliente) obj;
		return this.getNup().equals(other.getNup());
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
		return new ToStringBuilder(this).append("nombre", nombre).append("apellido1", apellido1)
				.append("apellido2", apellido2).append("clienteSelect", clienteSelect)
				.append("clientePlatinum", clientePlatinum).append("clienteGold", clienteGold)
				.append("cuentas", cuentas).append("cuentasPrivadas", cuentasPrivadas)
				.append("cuentasBancaPrivada", cuentasBancaPrivada).append("cuentasRetail", cuentasRetail)
				.append("cuentasCerradas", cuentasCerradas).append("segmento", segmento)
				.append("tipoCUILCUIT", tipoCUILCUIT).append("numeroCUILCUIT" + numeroCUILCUIT).append("os", os)
				.append("contratos", contratos).append("tieneTrjCred", tieneTrjCred)
				.append("cuentaValidacion", cuentaValidacion).append("isExCiti", this.isExCiti).toString();
	}

	/**
	 * Devuelve los ultimos 4 digitos de la tarjeta Banelco para mostrar en
	 * front.
	 *
	 * @return the ultimos digitos banelco
	 */
	public String getUltimosDigitosBanelco() {
		int cantidadDigitosAMostrar = 4;

		for (Cuenta cuenta : this.getCuentas()) {
			if (Cuenta.TIPOCTA_BANELCO.equals(cuenta.getTipoCuenta())) {
				return StringUtils.right(cuenta.getNroTarjetaCredito(), cantidadDigitosAMostrar);
			}
		}
		for (Cuenta cuenta : this.getCuentasPrivadas()) {
            if (Cuenta.TIPOCTA_BANELCO.equals(cuenta.getTipoCuenta())) {
                return StringUtils.right(cuenta.getNroTarjetaCredito(), cantidadDigitosAMostrar);
            }
        }
		return StringUtils.EMPTY;
	}

	/**
	 * Retorna el CBU de la cuenta pasada como parametro.
	 *
	 * @param cbu
	 *            the cbu
	 * @return the string
	 */
	public String obtenerCBUCuenta(String cbu) {

		for (Cuenta cuenta : getCuentas()) {
			String nroCuenta = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()) + "-"
					+ ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
			if (nroCuenta.equals(cbu)) {
				return cuenta.getCbu();
			}

		}
        for (Cuenta cuenta : getCuentasPrivadas()) {
            String nroCuenta = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()) + "-"
                    + ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
            if (nroCuenta.equals(cbu)) {
                return cuenta.getCbu();
            }
        }
		return StringUtils.EMPTY;
	}

	/**
	 * Retorna la cuenta pasado como parametro el CBU.
	 *
	 * @param cbu
	 *            the cbu
	 * @return the cuenta
	 */
	public Cuenta getCuenta(String cbu) {
		if (cbu == null) {
			return null;
		}
		for (Cuenta cuenta : getCuentas()) {
			if (cbu.equals(cuenta.getCbu())) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * Retorna la cuenta del nro pasado como parametro.
	 *
	 * @author emilio.watemberg
	 * @param nro
	 *            the nro
	 * @return the cuenta
	 * @since Nov 9, 2016
	 */
	public Cuenta getCuentaPorNumero(String nro) {
		if (nro == null) {
			return null;
		}
		for (Cuenta cuenta : getCuentas()) {
			if (nro.equals(cuenta.getNroCuentaProducto())) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * Gets the cuenta por index.
	 *
	 * @param nro
	 *            the nro
	 * @return the cuenta por index
	 */
	public Cuenta getCuentaPorIndex(String nro) {
		if (nro == null) {
			return null;
		}
		for (Cuenta cuenta : getCuentas()) {
			if (Integer.valueOf(nro).equals(cuenta.getIndex())) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * Gets the cuenta si contiene numero.
	 *
	 * @param nro
	 *            the nro
	 * @return the cuenta si contiene numero
	 */
	public Cuenta getCuentaSiContieneNumero(String nro) {
		if (nro == null) {
			return null;
		}
		for (Cuenta cuenta : getCuentas()) {
			if (cuenta.getNroCuentaProducto().contains(nro)) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * Gets the cuenta retail si contiene numero.
	 *
	 * @param nro
	 *            the nro
	 * @return the cuenta retail si contiene numero
	 */
	public Cuenta getCuentaRetailSiContieneNumero(String nro) {
		if (nro == null) {
			return null;
		}
		for (Cuenta cuenta : getCuentasRetail()) {
			if (cuenta.getNroCuentaProducto().contains(nro)) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * Gets the cuenta operativa si contiene numero.
	 *
	 * @param nro
	 *            the nro
	 * @return the cuenta operativa si contiene numero
	 */
	public Cuenta getCuentaOperativaSiContieneNumero(String nro) {
		if (nro == null) {
			return null;
		}
		for (CuentaBancaPrivada cuenta : getCuentasBancaPrivada()) {
			if (cuenta.getCuentaOperativa().getNroCuentaProducto().contains(nro)) {
				return cuenta.getCuentaOperativa();
			}
		}
		return null;
	}
	
	/**
	 * Gets the cuenta operativa si contiene numero.
	 *
	 * @param nro
	 *            the nro
	 * @return the cuenta operativa si contiene numero
	 */
	public Cuenta getCuentaPrivadaSiContieneNumero(String nro) {
		if (nro == null) {
			return null;
		}
		for (Cuenta cuenta : getCuentasPrivadas()) {
			if (cuenta.getNroCuentaProducto().contains(nro)) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * Gets the cuenta por numero.
	 *
	 * @param nro
	 *            the nro
	 * @return the cuenta por numero
	 */
	public Cuenta getCuentaPorNumero(Integer nro) {
		if (nro == null) {
			return null;
		}
		BigDecimal nroBD = new BigDecimal(nro);
		for (Cuenta cuenta : getCuentas()) {
			if (nroBD.equals(new BigDecimal(cuenta.getNroCuentaProducto()))) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * filtra las cuentas que corresponden a tarjetas de credito.
	 *
	 * @return the cuentas tarjeta de credito
	 */
	public List<Cuenta> getCuentasTarjetaDeCredito() {
		List<Cuenta> cuentasTarjeta = new ArrayList<Cuenta>();

		for (Cuenta cuenta : this.getCuentas()) {
			if (cuenta.esTarjetaDeCredito()) {
				cuentasTarjeta.add(cuenta);
			}
		}

		return cuentasTarjeta;
	}

	/**
	 * Filtra las cuentas monetarias: 00 (Cuenta corriente pesos), 01(Caja de
	 * ahorro pesos), 03 (Cuenta corriente dolares), 04 (Caja de ahorro
	 * dolares), 09 (Cuenta bimonetaria, saldo en pesos ) y 10 (Cuenta
	 * bimonetaria, saldo dólares).
	 *
	 * @return the cuentas monetarias
	 */
	public List<Cuenta> getCuentasMonetarias() {
		List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();

		for (Cuenta cuenta : this.getCuentas()) {
			if (cuenta.esSaldoPesos() || cuenta.esSaldoDolares()) {
				cuentasMonetarias.add(cuenta);
			}
		}

		return cuentasMonetarias;
	}

	/**
	 * Gets the cuentas tarjeta de credito titular.
	 *
	 * @return the cuentas tarjeta de credito titular
	 */
	public List<Cuenta> getCuentasTarjetaDeCreditoTitular() {
		List<Cuenta> cuentasTarjeta = new ArrayList<Cuenta>();

		for (Cuenta cuenta : this.getCuentas()) {
			if (cuenta.esTarjetaDeCredito()
					&& TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(cuenta.getCodigoTitularidad())) {
				cuentasTarjeta.add(cuenta);
			}
		}

		return cuentasTarjeta;
	}

	/**
	 * filtra las cuentas que corresponden a tarjetas de credito VISA.
	 *
	 * @return the cuentas tarjeta de credito
	 */
	public List<Cuenta> getCuentasTarjetaDeCreditoVISA() {
		List<Cuenta> cuentasTarjetaVisa = new ArrayList<Cuenta>();

		for (Cuenta cuenta : this.getCuentas()) {
			if ("20".equals(cuenta.getEstadoTarjetaCredito()) && TipoCuenta.VISA.equals(cuenta.getTipoCuentaEnum())
					&& !TarjetaUtils.VISA_RECARGABLE.equals(cuenta.getClaseCuenta())) {
				cuentasTarjetaVisa.add(cuenta);
			}
		}

		return cuentasTarjetaVisa;
	}

	/**
	 * filtra las cuentas que corresponden a tarjetas de credito AMEX.
	 *
	 * @return the cuentas tarjeta de credito
	 */
	public List<Cuenta> getCuentasTarjetaDeCreditoAMEX() {
		List<Cuenta> cuentasTarjetaAmex = new ArrayList<Cuenta>();

		for (Cuenta cuenta : this.getCuentas()) {
			if ("20".equals(cuenta.getEstadoTarjetaCredito()) && TipoCuenta.AMEX.equals(cuenta.getTipoCuentaEnum())) {
				cuentasTarjetaAmex.add(cuenta);
			}
		}

		return cuentasTarjetaAmex;
	}

	/**
	 * filtra las cuentas que corresponden a tarjetas de credito.
	 *
	 * @return the cuentas tarjeta de credito
	 */
	public List<Cuenta> getCuentasTarjetaParaFecp() {
		List<Cuenta> cuentasTarjeta = new ArrayList<Cuenta>();

		for (Cuenta cuenta : this.getCuentas()) {
			if (cuenta.esTarjetaParaEnviarAFecp()) {
				cuentasTarjeta.add(cuenta);
			}
		}

		return cuentasTarjeta;
	}

	/**
	 * Gets the cuentas tarjeta credito.
	 *
	 * @return the cuentas tarjeta credito
	 */
	public List<Cuenta> getCuentasTarjetaCredito() {
		List<Cuenta> cuentasTarjeta = new ArrayList<Cuenta>();

		for (Cuenta cuenta : this.getCuentas()) {
			if (!Cuenta.TIPOCTA_BANELCO.equals(cuenta.getTipoCuenta()) 
					&& cuenta.esTarjetaParaEnviarAFecp() && "TI".equals(cuenta.getCodigoTitularidad())) {
				cuentasTarjeta.add(cuenta);
			}
		}

		return cuentasTarjeta;
	}

	/**
	 * Devuelve una tarjeta de credito a partir de un numero enmascarado.
	 *
	 * @author mariano.g.pulera
	 * @param nroTarjetaEnmascarada
	 *            the nro tarjeta enmascarada
	 * @return the cuentas tarjeta de credito
	 */
	public Cuenta getTarjeta(String nroTarjetaEnmascarada) {

		for (Cuenta cuenta : cuentas) {
			if (cuenta.correspondeNroTarjeta(nroTarjetaEnmascarada)) {
				return cuenta;
			}
		}
		LOGGER.debug("Error tarjeta inexistente numero mascara: " + nroTarjetaEnmascarada);
		return null;
	}

	/**
	 * Gets the tarjeta desde nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @return the tarjeta desde nro tarjeta
	 */
	public Cuenta getTarjetaDesdeNroTarjeta(String nroTarjeta) {

		for (Cuenta cuenta : cuentas) {
			if (cuenta.getNroTarjetaCredito().equals(nroTarjeta)) {
				return cuenta;
			}
		}
		LOGGER.debug("Error tarjeta inexistente numero mascara: " + nroTarjeta);
		return null;
	}

	/**
	 * Checks if is primer ingreso.
	 *
	 * @return true, if is primer ingreso
	 */
	public boolean isPrimerIngreso() {
		return primerIngreso;
	}

	/**
	 * Sets the primer ingreso.
	 *
	 * @param primerIngreso
	 *            the new primer ingreso
	 */
	public void setPrimerIngreso(boolean primerIngreso) {
		this.primerIngreso = primerIngreso;
	}

	/**
	 * Gets the cuentas banca privada.
	 *
	 * @return cuentasBancaPrivada
	 */
	public List<CuentaBancaPrivada> getCuentasBancaPrivada() {
		return cuentasBancaPrivada;
	}

	/**
	 * Sets the cuentas banca privada.
	 *
	 * @param cuentasBancaPrivada
	 *            the new cuentas banca privada
	 */
	public void setCuentasBancaPrivada(List<CuentaBancaPrivada> cuentasBancaPrivada) {
		this.cuentasBancaPrivada = cuentasBancaPrivada;
	}

	/**
	 * Gets the cuentas retail.
	 *
	 * @return the cuentas retail
	 */
	public List<Cuenta> getCuentasRetail() {
		return cuentasRetail;
	}

	/**
	 * Sets the cuentas retail.
	 *
	 * @param cuentasRetail
	 *            the new cuentas retail
	 */
	public void setCuentasRetail(List<Cuenta> cuentasRetail) {
		this.cuentasRetail = cuentasRetail;
	}

	/**
	 * Gets the cuentas para efectuar pago.
	 *
	 * @return the cuentas para efectuar pago
	 */
	public List<Cuenta> getCuentasParaEfectuarPago() {
		List<Cuenta> cuentasParaPago = new ArrayList<Cuenta>();

		for (Cuenta cuenta : this.getCuentas()) {
			if (cuenta.esTipoPago()) {
				cuentasParaPago.add(cuenta);
			}
		}

		return cuentasParaPago;
	}

	/**
	 * Obtener prestamos disponibles.
	 *
	 * @return the prestamos disponibles
	 */
	public PrestamosDisponibles obtenerPrestamosDisponibles() {
		PrestamosDisponibles prestamosDisponibles = new PrestamosDisponibles();
		for (Cuenta cuenta : cuentas) {
			if (cuenta.esPrestamoHipotecario()) {
				prestamosDisponibles.setTieneHipotecario(true);
			}

			if (cuenta.esPrestamoPersonal()) {
				prestamosDisponibles.setTienePersonal(true);
				if(EnumPrestamosCuotasRefinanciadas.contiene(cuenta.getProductoAltair(), cuenta.getSubproductoAltair())){
					prestamosDisponibles.setContieneRefinanciacion(true);
				}
			}

			if (cuenta.esPrestamoPrendario()) {
				prestamosDisponibles.setTienePrendario(true);
			}

			if (cuenta.esOpenCredit()) {
				prestamosDisponibles.setTieneOpenCredit(true);
			}
		}

		return prestamosDisponibles;
	}

	/**
	 * crear una entidad para llegar al dao.
	 *
	 * @return entidad de entrada.
	 */

	public Cuenta obtenerCuentaPrincipal() {

		// busco la cuenta principal

		if (CollectionUtils.isEmpty(this.getCuentas())) {
			return null;
		}

		for (Cuenta cuenta : this.getCuentas()) {
			if (cuenta.esCuentaPrincipal()) {
				return cuenta;
			}
		}

		return null;
	}

	/**
	 * devuelve las cuentas unicas en pesos del cliente (para simulador de
	 * prestamo).
	 *
	 * @author mariano.g.pulera
	 * @return listado de cuentas unicas
	 */
	public List<Cuenta> getCuentasUnicasPesos() {
		List<Cuenta> cuentasUnicas = new ArrayList<Cuenta>();

		for (Cuenta cuenta : this.getCuentas()) {
			if (cuenta.esCuentaUnicaPesos()) {
				if (cuenta.esCuentaPrincipal()) {
					cuentasUnicas.add(0, cuenta);
				} else {
					cuentasUnicas.add(cuenta);
				}
			}
		}

		return cuentasUnicas;
	}
	
	
	/**
	 * devuelve las cuentas en pesos del cliente (para
	 * prestamo Sueldos).
	 *
	 * @return the cuentas en pesos
	 */
	public List<Cuenta> getCuentasEnPesos() {
		List<Cuenta> cuentasEnPesos = new ArrayList<Cuenta>();
		for (Cuenta cuenta : this.getCuentas()) {
			if (cuenta.esCuentaUnicaPesos()) {
				if (cuenta.esCuentaPrincipal()) {
					cuentasEnPesos.add(0, cuenta);
				} else {
					cuentasEnPesos.add(cuenta);
				}
			}else if (cuenta.isCuentaPesos()) {
				if (cuenta.esCuentaPrincipal()) {
					cuentasEnPesos.add(0, cuenta);
				} else {
					cuentasEnPesos.add(cuenta);
				}
			}
		}

		return cuentasEnPesos;
	}

	/**
	 * Obtener prestamo por ID.
	 *
	 * @param id
	 *            the id
	 * @return the cuenta
	 */
	public Cuenta obtenerPrestamoPorID(String id) {
		int index = Integer.parseInt(id);
		for (Cuenta cuenta : this.getCuentas()) {
			if (index == cuenta.getIndex()) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * Gets the os.
	 *
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * Sets the os.
	 *
	 * @param os
	 *            the new os
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * Gets the contratos.
	 *
	 * @return the contratos
	 */
	public HashMap<TipoContratoEnum, Boolean> getContratos() {
		return contratos;
	}

	/**
	 * Sets the contratos.
	 *
	 * @param contratos
	 *            the contratos
	 */
	public void setContratos(HashMap<TipoContratoEnum, Boolean> contratos) {
		this.contratos = contratos;
	}

	/**
	 * Gets the cuentas custodia.
	 *
	 * @return the cuentasCustodia
	 */
	public List<DatosObtenerSaldoCuentasCustodiaResponse> getCuentasCustodia() {
		return cuentasCustodia;
	}

	/**
	 * Sets the cuentas custodia.
	 *
	 * @param cuentasCustodia
	 *            the cuentasCustodia to set
	 */
	public void setCuentasCustodia(List<DatosObtenerSaldoCuentasCustodiaResponse> cuentasCustodia) {
		this.cuentasCustodia = cuentasCustodia;
	}

	/**
	 * Gets the cuentas titulo RTL.
	 *
	 * @return the cuentasTituloRTL
	 */
	public List<CuentaTituloView> getCuentasTituloRTL() {
		return cuentasTituloRTL;
	}

	/**
	 * Sets the cuentas titulo RTL.
	 *
	 * @param cuentasTituloRTL
	 *            the cuentasTituloRTL to set
	 */
	public void setCuentasTituloRTL(List<CuentaTituloView> cuentasTituloRTL) {
		this.cuentasTituloRTL = cuentasTituloRTL;
	}

	/**
	 * Es nup valido.
	 *
	 * @return the boolean
	 */
	public Boolean esNupValido() {
		return this.getNup().matches("^[0-9]{8}$");
	}

	/**
	 * retorna verdadero si encuentra una cuenta en pesos independientemente si
	 * es unica o no.
	 *
	 * @return true, if is tiene cuenta en pesos
	 */
	public boolean isTieneCuentaEnPesos() {

		if (CollectionUtils.isEmpty(this.cuentas)) {
			return false;
		}

		for (Cuenta cuenta : this.cuentas) {
			if (cuenta.esSaldoPesos()) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Obtiene una cuenta unica en pesos del cliente.
	 *
	 * @return the cuenta
	 */
	public Cuenta obtenerCuentaUnicaEnPesos() {
		if (CollectionUtils.isEmpty(this.getCuentas())) {
			return null;
		}

		for (Cuenta cuenta : this.getCuentas()) {
			if (cuenta.esCuentaUnicaPesos()) {
				return cuenta;
			}
		}

		return null;
	}

	/**
	 * Gets the checks if is ex citi.
	 *
	 * @return the checks if is ex citi
	 */
	public Boolean getIsExCiti() {
		return isExCiti;
	}

	/**
	 * Sets the checks if is ex citi.
	 *
	 * @param isExCiti
	 *            the new checks if is ex citi
	 */
	public void setIsExCiti(Boolean isExCiti) {
		this.isExCiti = isExCiti;
	}

	/**
	 * Se crean metodos en diferentes ramas para ser utilizado en funcionalidad
	 * pago de tarjeta y marcacion por viaje.
	 *
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Sets the sexo.
	 *
	 * @param sexo
	 *            the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Devuelve si la cuenta fue migrada.
	 *
	 * @return the isCuentaMigrada
	 */
	public Boolean getIsCuentaMigrada() {
		return isCuentaMigrada;
	}

	/**
	 * Sets the checks if is cuenta migrada.
	 *
	 * @param isCuentaMigrada
	 *            the isCuentaMigrada to set
	 */
	public void setIsCuentaMigrada(Boolean isCuentaMigrada) {
		this.isCuentaMigrada = isCuentaMigrada;
	}
	
	/**
	 * Obtiene el nombre, el apellido 1 y el apellido 2 concatenados
	 * Ej: Nicolas Perez Martin.
	 *
	 * @return String
	 */
	public String obtenerNombreYApellidos() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.nombre);
		builder.append(UN_ESPACIO);
		builder.append(this.apellido1);
		if(!this.apellido2.isEmpty()) {
			builder.append(UN_ESPACIO);
			builder.append(this.apellido2);			
		}
		return builder.toString();
	}

	/**
	 * Gets the sin productos.
	 *
	 * @return the sin productos
	 */
	public Boolean getSinProductos() {
		return sinProductos;
	}

	/**
	 * Sets the sin productos.
	 *
	 * @param sinProductos the new sin productos
	 */
	public void setSinProductos(Boolean sinProductos) {
		this.sinProductos = sinProductos;
	}
	
	/**
	 * Es mono producto tarjeta.
	 *
	 * @return true, if successful
	 */
	public boolean esMonoProductoTarjeta() {
		if (CollectionUtils.isEmpty(this.cuentas)) {
			return false;
		}
		for (Cuenta cuenta : this.cuentas) {
			if (!cuenta.esTarjetaDeCredito()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Puede transferir bpriv.
	 *
	 * @return true, if successful
	 */
	public boolean puedeTransferirBpriv() {
		TipoCuenta[] list = {TipoCuenta.CUENTA_CORRIENTE_PESOS, TipoCuenta.CAJA_AHORRO_PESOS,
				TipoCuenta.CUENTA_UNICA, TipoCuenta.CUENTA_CORRIENTE_DOLARES, TipoCuenta.CAJA_AHORRO_DOLARES,
				TipoCuenta.CUENTA_UNICA_PESOS, TipoCuenta.CUENTA_UNICA_DOLARES};
		for (Cuenta cuenta : this.getCuentas()) {
			if (ArrayUtils.contains(list, cuenta.getTipoCuentaEnum())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * get cuentas banca privada transferibles.
	 * si el numero de sucursal de la cuenta esta entre 250 y 259 &&
	 * si la cuenta es tipo CuentaUnica || es pesos || es dolares. Entonces es
	 * cuentas banca privada transferibles
	 * @return true, List<Cuenta>
	 */
	public List<Cuenta> getCuentasBPTransferibles() {
		List<Cuenta> cuentasBPTransferibles = new ArrayList<Cuenta>();
		if(this.getCuentasPrivadas() == null || this.getCuentasPrivadas().isEmpty()) {
			return cuentasBPTransferibles;
		}
		for (Cuenta cuentaBP : this.getCuentasPrivadas()) {
			if( CuentasBancaPrivadaUtil.isCuentaPrivada(cuentaBP) ) {
				cuentasBPTransferibles.add(cuentaBP);
			}
		}
		return cuentasBPTransferibles;
	}

	/**
	 * Gets the nroDocCnsDocXNup.
	 *
	 * @return the nroDocCnsDocXNup
	 */
	public String getNroDocCnsDocXNup() {
		return nroDocCnsDocXNup;
	}

	/**
	 * Sets the nro doc cns doc X nup.
	 *
	 * @param nroDocCnsDocXNup the new nroDocCnsDocXNup
	 */
	public void setNroDocCnsDocXNup(String nroDocCnsDocXNup) {
		this.nroDocCnsDocXNup = nroDocCnsDocXNup;
	}

	/**
	 * Gets the tipoDocCnsDocXNup.
	 *
	 * @return the tipoDocCnsDocXNup
	 */
	public String getTipoDocCnsDocXNup() {
		return tipoDocCnsDocXNup;
	}

	/**
	 * Sets the tipoDocCnsDocXNup.
	 *
	 * @param tipoDocCnsDocXNup the new tipoDocCnsDocXNup
	 */
	public void setTipoDocCnsDocXNup(String tipoDocCnsDocXNup) {
		this.tipoDocCnsDocXNup = tipoDocCnsDocXNup;
	}
	
	/**
	 * Checks if is host nocturno.
	 *
	 * @return true, if is host nocturno
	 */
	public boolean isHostNocturno() {
		return hostNocturno;
	}

	/**
	 * Sets the host nocturno.
	 *
	 * @param hostNocturno the new host nocturno
	 */
	public void setHostNocturno(boolean hostNocturno) {
		this.hostNocturno = hostNocturno;
	}
	

	/**
	 * Gets the programa beneficios.
	 *
	 * @return the programa beneficios
	 */
	public String getProgramaBeneficios() {
		return programaBeneficios;
	}

	/**
	 * Sets the programa beneficios.
	 *
	 * @param programaBeneficios the new programa beneficios
	 */
	public void setProgramaBeneficios(String programaBeneficios) {
		this.programaBeneficios = programaBeneficios;
	}

	/**
	 * Checks if is nova.
	 *
	 * @return true, if is nova
	 */
	public boolean isNova() {
		return isNova;
	}

	/**
	 * Sets the nova.
	 *
	 * @param isNova the new nova
	 */
	public void setNova(boolean isNova) {
		this.isNova = isNova;
	}

	/**
	 * Gets the email login mya.
	 *
	 * @return the email login mya
	 */
	public String getEmailLoginMya() {
		return emailLoginMya;
	}

	/**
	 * Sets the email login mya.
	 *
	 * @param emailLoginMya the new email login mya
	 */
	public void setEmailLoginMya(String emailLoginMya) {
		this.emailLoginMya = emailLoginMya;
	}

	/**
	 * Gets the cuentas tit rtl repatriacion.
	 *
	 * @return the cuentas tit rtl repatriacion
	 */
	public List<Cuenta> getCuentasTitRtlRepatriacion() {
		return cuentasTitRtlRepatriacion;
	}

	/**
	 * Sets the cuentas tit rtl repatriacion.
	 *
	 * @param cuentasTitRtlRepatriacion the new cuentas tit rtl repatriacion
	 */
	public void setCuentasTitRtlRepatriacion(List<Cuenta> cuentasTitRtlRepatriacion) {
		this.cuentasTitRtlRepatriacion = cuentasTitRtlRepatriacion;
	}

	/**
	 * Gets the cuentas tit BP repatriacion.
	 *
	 * @return the cuentas tit BP repatriacion
	 */
	public List<Cuenta> getCuentasTitBPRepatriacion() {
		return cuentasTitBPRepatriacion;
	}

	/**
	 * Sets the cuentas tit BP repatriacion.
	 *
	 * @param cuentasTitBPRepatriacion the new cuentas tit BP repatriacion
	 */
	public void setCuentasTitBPRepatriacion(List<Cuenta> cuentasTitBPRepatriacion) {
		this.cuentasTitBPRepatriacion = cuentasTitBPRepatriacion;
	}

	/**
	 * Gets the queue turn id.
	 *
	 * @return the queue turn id
	 */
	public String getQueueTurnId() {
		return queueTurnId;
	}

	/**
	 * Sets the queue turn id.
	 *
	 * @param queueTurnId the new queue turn id
	 */
	public void setQueueTurnId(String queueTurnId) {
		this.queueTurnId = queueTurnId;
	}

	public void setTipoOfertaRefinanciacion(String tipoRefinanciacion) {
		this.tipoRefinanciacion = tipoRefinanciacion;
	}

	public String getTipoOfertaRefinanciacion() {
		return this.tipoRefinanciacion;
	}
	
	public AccesosInversiones getAccesosInversiones() {
		return this.accesosInversiones;
	}
	
	public void setAccesosInversiones(AccesosInversiones accesosInversiones) {
		this.accesosInversiones = accesosInversiones;
	}
}
