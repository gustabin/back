/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.general.entities.RangoFechaEnum;

/**
 * The Class ConsultaUltimosMovimientos.
 */
public class ConsultaUltimosMovimientos {

	/** The cuenta. */
	private AbstractCuenta cuenta;

	/** The fecha desde. */
	private Date fechaDesde;

	/** The fecha hasta. */
	private Date fechaHasta;

	/** The rango fecha. */
	private RangoFechaEnum rangoFecha;

	/** The importe desde. */
	private BigDecimal importeDesde;

	/** The importe hasta. */
	private BigDecimal importeHasta;

	/** The palabra clave. */
	private String palabraClave;

	/** The moneda. */
	private DivisaEnum moneda;

	/** The cantidad movimientos. */
	private Integer cantidadMovimientos;

	/** The tipo consulta. */
	private TipoConsultaMovimientos tipoConsulta;

	/** The orden movimientos. */
	private OrdenMovimientos ordenMovimientos;

	/** The indicador. */
	private String indicador;

	/** The is traspaso automatico. */
	private Boolean isTraspasoAutomatico;

	/** The offset. */
	private Integer offset;

	/** The tipo cuenta. */
	private TipoCuenta tipoCuenta;

	/** The cbu cuenta *. */
	private String cbuCuenta;

	/** The desde default. */
	private boolean desdeDefault;

	/** The numero consulta. */
	private int numeroConsulta;

	/** The numero cuenta. */
	private String numeroCuenta;

	/**
	 * Instantiates a new consulta ultimos movimientos.
	 */
	public ConsultaUltimosMovimientos() {

	}

	/**
	 * Instantiates a new consulta ultimos movimientos.
	 *
	 * @param consultaUltimosMovimientos the consulta ultimos movimientos
	 */
	public ConsultaUltimosMovimientos(ConsultaUltimosMovimientos consultaUltimosMovimientos) {
		super();
		this.cuenta = consultaUltimosMovimientos.getCuenta();
		this.fechaDesde = consultaUltimosMovimientos.getFechaDesde();
		this.fechaHasta = consultaUltimosMovimientos.getFechaHasta();
		this.importeDesde = consultaUltimosMovimientos.getImporteDesde();
		this.importeHasta = consultaUltimosMovimientos.getImporteHasta();
		this.palabraClave = consultaUltimosMovimientos.getPalabraClave();
		this.moneda = consultaUltimosMovimientos.getMoneda();
		this.cantidadMovimientos = consultaUltimosMovimientos.getCantidadMovimientos();
		this.tipoConsulta = consultaUltimosMovimientos.getTipoConsulta();
		this.ordenMovimientos = consultaUltimosMovimientos.getOrdenMovimientos();
		this.indicador = consultaUltimosMovimientos.getIndicador();
		this.isTraspasoAutomatico = consultaUltimosMovimientos.getIsTraspasoAutomatico();
		this.offset = consultaUltimosMovimientos.getOffset();
		this.rangoFecha = consultaUltimosMovimientos.getRangoFecha();
		this.desdeDefault = consultaUltimosMovimientos.getDesdeDefault();
	}

	/**
	 * Gets the rango fecha.
	 *
	 * @return the rango fecha
	 */
	public RangoFechaEnum getRangoFecha() {
		return rangoFecha;
	}

	/**
	 * Setter para rango fecha.
	 *
	 * @param rangoFecha el nuevo rango fecha
	 */
	public void setRangoFecha(RangoFechaEnum rangoFecha) {
		this.rangoFecha = rangoFecha;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public DivisaEnum getMoneda() {
		return moneda;
	}

	/**
	 * Setter para moneda.
	 *
	 * @param moneda el nuevo moneda
	 */
	public void setMoneda(DivisaEnum moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the importe desde.
	 *
	 * @return the importe desde
	 */
	public BigDecimal getImporteDesde() {
		return importeDesde;
	}

	/**
	 * Setter para importe desde.
	 *
	 * @param importeDesde el nuevo importe desde
	 */
	public void setImporteDesde(BigDecimal importeDesde) {
		this.importeDesde = importeDesde;
	}

	/**
	 * Gets the importe hasta.
	 *
	 * @return the importe hasta
	 */
	public BigDecimal getImporteHasta() {
		return importeHasta;
	}

	/**
	 * Setter para importe hasta.
	 *
	 * @param importeHasta el nuevo importe hasta
	 */
	public void setImporteHasta(BigDecimal importeHasta) {
		this.importeHasta = importeHasta;
	}

	/**
	 * Gets the palabra clave.
	 *
	 * @return the palabra clave
	 */
	public String getPalabraClave() {
		return palabraClave;
	}

	/**
	 * Setter para palabra clave.
	 *
	 * @param palabraClave el nuevo palabra clave
	 */
	public void setPalabraClave(String palabraClave) {
		this.palabraClave = palabraClave;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public AbstractCuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Setter para cuenta.
	 *
	 * @param cuenta el nuevo cuenta
	 */
	public void setCuenta(AbstractCuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the offset.
	 *
	 * @return the offset
	 */
	public Integer getOffset() {
		if (offset == null) {
			offset = new Integer(0);
		}
		return offset;
	}

	/**
	 * Setter para offset.
	 *
	 * @param offset el nuevo offset
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public Date getFechaDesde() {
		Date vuelta = null;
		if (this.fechaDesde != null) {
			vuelta = new Date(this.fechaDesde.getTime());
		}
		return vuelta;
	}

	/**
	 * Setter para fecha desde.
	 *
	 * @param fechaDesde el nuevo fecha desde
	 */
	public void setFechaDesde(Date fechaDesde) {
		Date inputFechaDesde = null;
		if (fechaDesde != null) {
			inputFechaDesde = new Date(fechaDesde.getTime());
		}
		this.fechaDesde = inputFechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public Date getFechaHasta() {
		Date vuelta = null;
		if (this.fechaHasta != null) {
			vuelta = new Date(this.fechaHasta.getTime());
		}
		return vuelta;
	}

	/**
	 * Setter para fecha hasta.
	 *
	 * @param fechaHasta el nuevo fecha hasta
	 */
	public void setFechaHasta(Date fechaHasta) {
		Date inputFechaHasta = null;
		if (fechaHasta != null) {
			inputFechaHasta = new Date(fechaHasta.getTime());
		}
		this.fechaHasta = inputFechaHasta;
	}

	/**
	 * Gets the cantidad movimientos.
	 *
	 * @return the cantidad movimientos
	 */
	public Integer getCantidadMovimientos() {
		return cantidadMovimientos;
	}

	/**
	 * Setter para cantidad movimientos.
	 *
	 * @param cantidadMovimientos el nuevo cantidad movimientos
	 */
	public void setCantidadMovimientos(Integer cantidadMovimientos) {
		this.cantidadMovimientos = cantidadMovimientos;
	}

	/**
	 * Gets the tipo consulta.
	 *
	 * @return the tipo consulta
	 */
	public TipoConsultaMovimientos getTipoConsulta() {
		return tipoConsulta;
	}

	/**
	 * Setter para tipo consulta.
	 *
	 * @param tipoConsulta el nuevo tipo consulta
	 */
	public void setTipoConsulta(TipoConsultaMovimientos tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	/**
	 * Gets the orden movimientos.
	 *
	 * @return the orden movimientos
	 */
	public OrdenMovimientos getOrdenMovimientos() {
		return ordenMovimientos;
	}

	/**
	 * Setter para orden movimientos.
	 *
	 * @param ordenMovimientos el nuevo orden movimientos
	 */
	public void setOrdenMovimientos(OrdenMovimientos ordenMovimientos) {
		this.ordenMovimientos = ordenMovimientos;
	}

	/**
	 * Gets the indicador.
	 *
	 * @return the indicador
	 */
	public String getIndicador() {
		return indicador;
	}

	/**
	 * Setter para indicador.
	 *
	 * @param indicador el nuevo indicador
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	/**
	 * Gets the checks if is traspaso automatico.
	 *
	 * @return the checks if is traspaso automatico
	 */
	public Boolean getIsTraspasoAutomatico() {
		return isTraspasoAutomatico;
	}

	/**
	 * Setter para checks if is traspaso automatico.
	 *
	 * @param isTraspasoAutomatico el nuevo checks if is traspaso automatico
	 */
	public void setIsTraspasoAutomatico(Boolean isTraspasoAutomatico) {
		this.isTraspasoAutomatico = isTraspasoAutomatico;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta the new tipo cuenta
	 */
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the cbu cuenta.
	 *
	 * @return the cbu cuenta
	 */
	public String getCbuCuenta() {
		return cbuCuenta;
	}

	/**
	 * Sets the cbu cuenta.
	 *
	 * @param cbuCuenta the new cbu cuenta
	 */
	public void setCbuCuenta(String cbuCuenta) {
		this.cbuCuenta = cbuCuenta;
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
	 * @param numeroCuenta the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the numero consulta.
	 *
	 * @return the numero consulta
	 */
	public int getNumeroConsulta() {
		return numeroConsulta;
	}

	/**
	 * Sets the numero consulta.
	 *
	 * @param numeroConsulta the new numero consulta
	 */
	public void setNumeroConsulta(int numeroConsulta) {
		this.numeroConsulta = numeroConsulta;
	}

	/**
	 * Gets the desde default.
	 *
	 * @return the desde default
	 */
	public boolean getDesdeDefault() {
		return desdeDefault;
	}

	/**
	 * Sets the desde default.
	 *
	 * @param desdeDefault the new desde default
	 */
	public void setDesdeDefault(boolean desdeDefault) {
		this.desdeDefault = desdeDefault;
	}

	/**
	 * Metodo utilizado para generar key del cache CACHE_NYO_CNSCTAMOVS
	 * 
	 * @return
	 */
	public String fechaDesdeHastaAsString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(fechaDesde) + sdf.format(fechaHasta);
	}
}