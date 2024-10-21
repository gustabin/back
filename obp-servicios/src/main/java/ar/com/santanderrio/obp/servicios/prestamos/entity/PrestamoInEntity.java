/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudSimulacionView;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dto.ConfiguracionPrestamoDTO;

/**
 * The Class PrestamoInEntity.
 *
 * @author florencia.n.martinez
 */
public class PrestamoInEntity {

	/** The simulacion. */
	private final String simulacion = "S";

	/** The liquidacion. */
	private final String liquidacion = "L";

	/** The corresponde empleado. */
	private final String correspondeEmpleado = "EO";

	/** The no corresponde empleado. */
	private final String noCorrespondeEmpleado = "";

	/** The fase. */
	@Pattern(regexp = "S|L|1|2|3|4")
	private String fase;

	/** The tipo cuenta. */
	@Pattern(regexp = "[0-9]{2}")
	private String tipoCuenta;

	/** The sucursal cuenta. */
	@Pattern(regexp = "[0-9]{3}")
	private String sucursalCuenta;

	/** The numero cuenta. */
	@Pattern(regexp = "[0-9]{7}")
	private String numeroCuenta;

	/** The importe prestamo. */
	@Pattern(regexp = "[0-9]{14}")
	private String importePrestamo;

	/** The cantidad cuotas. */
	@Pattern(regexp = "[0-9]{3}")
	private String cantidadCuotas;

	/** The fecha primer cuota. */
	@Pattern(regexp = "[0-9]{8}")
	private String fechaPrimerCuota;

	/** The tipo tasa. */
	@Pattern(regexp = "[0-9A-Za-z ]{1}")
	private String tipoTasa;

	/** The cod producto UG. */
	@Pattern(regexp = "[0-9A-Za-z]{2}|[ ]{2}")
	private String codProductoUG;

	/** The cod subp UG. */
	@Pattern(regexp = "[0-9A-Za-z]{4}|[ ]{4}")
	private String codSubpUG;

	/** The dest fondos UG. */
	@Pattern(regexp = "[0-9A-Za-z]{5}|[ ]{5}")
	private String destFondosUG;

	/** The cod divisa O. */
	@Pattern(regexp = "[0-9A-Za-z]{3}|[ ]{3}")
	private String codDivisaO;

	/** The valor tasa. */
	@Pattern(regexp = "[0-9]{9}")
	private String valorTasa;

	/** The clausula rev UG. */
	@Pattern(regexp = "[0-9A-Za-z]{4}|[ ]{4}|[ ]{1}[0-9A-Za-z]{3}|[0-9A-Za-z]{3}|[ ]{3}")
	private String clausulaRevUG;

	/** The suc paquete. */
	@Pattern(regexp = "[0-9A-Za-z]{4}|[ ]{4}")
	private String sucPaquete;

	/** The num paquete. */
	@Pattern(regexp = "[0-9]{12}")
	private String numPaquete;

	/** The tpo poliza ds. */
	@Pattern(regexp = "[0-9A-Za-z]{3}|[ ]{3}")
	private String tpoPolizaDs;

	/** The tpo riesgo ds. */
	@Pattern(regexp = "[0-9A-Za-z]{3}|[ ]{3}")
	private String tpoRiesgoDs;

	/** The cod condici. */
	@Pattern(regexp = "EO|")
	private String codCondici;

	/** The nio. */
	@Pattern(regexp = "[ ]{24}")
	private String NIO;

	/** The dest fondos comer O. */
	@Pattern(regexp = "[0-9A-Za-z]{5}|[ ]{5}")
	private String destFondosComerO;

	/** The suc prest. */
	@Pattern(regexp = "0{4}")
	private String sucPrest;

	/** The num prest. */
	@Pattern(regexp = "0{12}")
	private String numPrest;

	/** The ent cuenta prove. */
	@Pattern(regexp = "[0-9A-Za-z]{4}|[ ]{4}")
	private String entCuentaProve;

	/** The suc cuenta prove. */
	@Pattern(regexp = "[0-9A-Za-z]{4}|[ ]{4}")
	private String sucCuentaProve;

	/** The nro cuenta prove. */
	@Pattern(regexp = "[0-9]{16}")
	private String nroCuentaProve;

	/** The divisa cta prove. */
	@Pattern(regexp = "[0-9A-Za-z]{3}|[ ]{3}")
	private String divisaCtaProve;

	/** The importe abono. */
	@Pattern(regexp = "0{17}")
	private String importeAbono;

	/** The Linea UVA. */
	@Pattern(regexp = "[S|N]{1}")
	private String indLineaUVA;
	
	/** The Constant PUNTO_STRING. */
	private static final String TIPO_TASA_FIJA = "F";
	
	/** The Constant PUNTO_STRING. */
	private static final String TIPO_TASA_VARIABLE = "V";
	
	/** The Constant PUNTO_STRING. */
	private static final String ES_UVA = "S";
	
	/** The Constant PUNTO_STRING. */
	private static final String NO_ES_UVA = "N";

	/**
	 * Instantiates a new prestamo in entity.
	 */
	public PrestamoInEntity() {
		super();
	}

	/**
	 * Instantiates a new prestamo in entity.
	 *
	 * @param esSimulacion
	 *            the es simulacion
	 * @param cuenta
	 *            the cuenta
	 * @param solicitudSimulacion
	 *            the solicitud simulacion
	 * @param configuracionPrestamoDTO
	 *            the configuracion prestamo DTO
	 */
	public PrestamoInEntity(String fase, Cuenta cuenta, SolicitudSimulacionView solicitudSimulacion,
			ConfiguracionPrestamoDTO configuracionPrestamoDTO) {
		this.fase = fase;
		this.tipoCuenta = String.format("%02d", Integer.valueOf(cuenta.getTipoCuentaSinUnificar()));
		this.sucursalCuenta = cuenta.getNroSucursal().substring(cuenta.getNroSucursal().length() - 3);
		this.numeroCuenta = cuenta.getNroCuentaProducto().substring(cuenta.getNroCuentaProducto().length() - 7);
		this.importePrestamo = String.format("%015.2f", solicitudSimulacion.importeToBigDecimal()).replaceAll("[.,]",
				"");
		this.cantidadCuotas = String.format("%03d", Integer.valueOf(solicitudSimulacion.getCuotaSeleccionada()));
		String fechaAdaptada = adaptarFechaServicio(solicitudSimulacion.getFechaSeleccionada().replaceAll("/", ""));
		this.fechaPrimerCuota = fechaAdaptada;
		PrestamoPermitidoEntity rangoCuota = configuracionPrestamoDTO.getPrestamoPermitido()
				.obtenerInfoPrestamoPorCuotas(Integer.parseInt(solicitudSimulacion.getCuotaSeleccionada()), 
				        solicitudSimulacion.isUva(), solicitudSimulacion.isTasaFija(), solicitudSimulacion.getIdRangoSeleccionado());
		if (solicitudSimulacion.isTasaFija()) {
			this.tipoTasa = TIPO_TASA_FIJA;
		} else {
			this.tipoTasa = TIPO_TASA_VARIABLE;
		}
		this.codProductoUG = rangoCuota.getCodProductoUg();
		this.codSubpUG = rangoCuota.getCodSubpUg();
		this.destFondosUG = solicitudSimulacion.getMotivoSeleccionado().getId().substring(9, 14);
		this.codDivisaO = "ARS";
		this.valorTasa = rangoCuota.getValorTasa();
		this.clausulaRevUG = rangoCuota.getClausulaRevUg();
		this.sucPaquete = String.format("%4s", cuenta.getSucursalPaquete());
		this.numPaquete = cuenta.getNumeroPaquete().substring(cuenta.getNumeroPaquete().length() - 12);
		this.tpoPolizaDs = rangoCuota.getTpoPolizaDs();
		this.tpoRiesgoDs = rangoCuota.getTpoRiesgoDs();
		this.codCondici = rangoCuota.esEmpleado() ? this.correspondeEmpleado : this.noCorrespondeEmpleado;
		this.NIO = String.format("%24s", "");
		this.destFondosComerO = rangoCuota.getDestFondosComerO();
		this.sucPrest = String.format("%04d", 0);
		this.numPrest = String.format("%012d", 0);
		this.entCuentaProve = rangoCuota.getEntCuentaProve();
		this.sucCuentaProve = rangoCuota.getSucCuentaProve();
		this.nroCuentaProve = String.format("%016d", Integer.valueOf(rangoCuota.getNroCuentaProve()));
		this.divisaCtaProve = rangoCuota.getDivisaCtaProve();
		this.importeAbono = String.format("%017d", 0);
		if (solicitudSimulacion.isUva()) {
			this.indLineaUVA = ES_UVA;
		} else {
			this.indLineaUVA = NO_ES_UVA;
		}
	}

	/**
	 * Gets the fase.
	 *
	 * @return the fase
	 */
	public String getFase() {
		return fase;
	}

	/**
	 * Sets the fase.
	 *
	 * @param fase
	 *            the fase to set
	 */
	public void setFase(String fase) {
		this.fase = fase;
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
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursalCuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the sucursalCuenta to set
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the importe prestamo.
	 *
	 * @return the importePrestamo
	 */
	public String getImportePrestamo() {
		return importePrestamo;
	}

	/**
	 * Sets the importe prestamo.
	 *
	 * @param importePrestamo
	 *            the importePrestamo to set
	 */
	public void setImportePrestamo(String importePrestamo) {
		this.importePrestamo = importePrestamo;
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
	 * Gets the fecha primer cuota.
	 *
	 * @return the fechaPrimerCuota
	 */
	public String getFechaPrimerCuota() {
		return fechaPrimerCuota;
	}

	/**
	 * Sets the fecha primer cuota.
	 *
	 * @param fechaPrimerCuota
	 *            the fechaPrimerCuota to set
	 */
	public void setFechaPrimerCuota(String fechaPrimerCuota) {
		this.fechaPrimerCuota = fechaPrimerCuota;
	}

	/**
	 * Gets the tipo tasa.
	 *
	 * @return the tipoTasa
	 */
	public String getTipoTasa() {
		return tipoTasa;
	}

	/**
	 * Sets the tipo tasa.
	 *
	 * @param tipoTasa
	 *            the tipoTasa to set
	 */
	public void setTipoTasa(String tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

	/**
	 * Gets the cod producto UG.
	 *
	 * @return the codProductoUG
	 */
	public String getCodProductoUG() {
		return codProductoUG;
	}

	/**
	 * Sets the cod producto UG.
	 *
	 * @param codProductoUG
	 *            the codProductoUG to set
	 */
	public void setCodProductoUG(String codProductoUG) {
		this.codProductoUG = codProductoUG;
	}

	/**
	 * Gets the cod subp UG.
	 *
	 * @return the codSubpUG
	 */
	public String getCodSubpUG() {
		return codSubpUG;
	}

	/**
	 * Sets the cod subp UG.
	 *
	 * @param codSubpUG
	 *            the codSubpUG to set
	 */
	public void setCodSubpUG(String codSubpUG) {
		this.codSubpUG = codSubpUG;
	}

	/**
	 * Gets the dest fondos UG.
	 *
	 * @return the destFondosUG
	 */
	public String getDestFondosUG() {
		return destFondosUG;
	}

	/**
	 * Sets the dest fondos UG.
	 *
	 * @param destFondosUG
	 *            the destFondosUG to set
	 */
	public void setDestFondosUG(String destFondosUG) {
		this.destFondosUG = destFondosUG;
	}

	/**
	 * Gets the cod divisa O.
	 *
	 * @return the codDivisaO
	 */
	public String getCodDivisaO() {
		return codDivisaO;
	}

	/**
	 * Sets the cod divisa O.
	 *
	 * @param codDivisaO
	 *            the codDivisaO to set
	 */
	public void setCodDivisaO(String codDivisaO) {
		this.codDivisaO = codDivisaO;
	}

	/**
	 * Gets the valor tasa.
	 *
	 * @return the valorTasa
	 */
	public String getValorTasa() {
		return valorTasa;
	}

	/**
	 * Sets the valor tasa.
	 *
	 * @param valorTasa
	 *            the valorTasa to set
	 */
	public void setValorTasa(String valorTasa) {
		this.valorTasa = valorTasa;
	}

	/**
	 * Gets the clausula rev UG.
	 *
	 * @return the clausulaRevUG
	 */
	public String getClausulaRevUG() {
		return clausulaRevUG;
	}

	/**
	 * Sets the clausula rev UG.
	 *
	 * @param clausulaRevUG
	 *            the clausulaRevUG to set
	 */
	public void setClausulaRevUG(String clausulaRevUG) {
		this.clausulaRevUG = clausulaRevUG;
	}

	/**
	 * Gets the suc paquete.
	 *
	 * @return the sucPaquete
	 */
	public String getSucPaquete() {
		return sucPaquete;
	}

	/**
	 * Sets the suc paquete.
	 *
	 * @param sucPaquete
	 *            the sucPaquete to set
	 */
	public void setSucPaquete(String sucPaquete) {
		this.sucPaquete = sucPaquete;
	}

	/**
	 * Gets the num paquete.
	 *
	 * @return the numPaquete
	 */
	public String getNumPaquete() {
		return numPaquete;
	}

	/**
	 * Sets the num paquete.
	 *
	 * @param numPaquete
	 *            the numPaquete to set
	 */
	public void setNumPaquete(String numPaquete) {
		this.numPaquete = numPaquete;
	}

	/**
	 * Gets the tpo poliza ds.
	 *
	 * @return the tpoPolizaDs
	 */
	public String getTpoPolizaDs() {
		return tpoPolizaDs;
	}

	/**
	 * Sets the tpo poliza ds.
	 *
	 * @param tpoPolizaDs
	 *            the tpoPolizaDs to set
	 */
	public void setTpoPolizaDs(String tpoPolizaDs) {
		this.tpoPolizaDs = tpoPolizaDs;
	}

	/**
	 * Gets the tpo riesgo ds.
	 *
	 * @return the tpoRiesgoDs
	 */
	public String getTpoRiesgoDs() {
		return tpoRiesgoDs;
	}

	/**
	 * Sets the tpo riesgo ds.
	 *
	 * @param tpoRiesgoDs
	 *            the tpoRiesgoDs to set
	 */
	public void setTpoRiesgoDs(String tpoRiesgoDs) {
		this.tpoRiesgoDs = tpoRiesgoDs;
	}

	/**
	 * Gets the cod condici.
	 *
	 * @return the codCondici
	 */
	public String getCodCondici() {
		return codCondici;
	}

	/**
	 * Sets the cod condici.
	 *
	 * @param codCondici
	 *            the codCondici to set
	 */
	public void setCodCondici(String codCondici) {
		this.codCondici = codCondici;
	}

	/**
	 * Gets the nio.
	 *
	 * @return the nIO
	 */
	public String getNIO() {
		return NIO;
	}

	/**
	 * Sets the nio.
	 *
	 * @param nIO
	 *            the nIO to set
	 */
	public void setNIO(String nIO) {
		NIO = nIO;
	}

	/**
	 * Gets the dest fondos comer O.
	 *
	 * @return the destFondosComerO
	 */
	public String getDestFondosComerO() {
		return destFondosComerO;
	}

	/**
	 * Sets the dest fondos comer O.
	 *
	 * @param destFondosComerO
	 *            the destFondosComerO to set
	 */
	public void setDestFondosComerO(String destFondosComerO) {
		this.destFondosComerO = destFondosComerO;
	}

	/**
	 * Gets the suc prest.
	 *
	 * @return the sucPrest
	 */
	public String getSucPrest() {
		return sucPrest;
	}

	/**
	 * Sets the suc prest.
	 *
	 * @param sucPrest
	 *            the sucPrest to set
	 */
	public void setSucPrest(String sucPrest) {
		this.sucPrest = sucPrest;
	}

	/**
	 * Gets the num prest.
	 *
	 * @return the numPrest
	 */
	public String getNumPrest() {
		return numPrest;
	}

	/**
	 * Sets the num prest.
	 *
	 * @param numPrest
	 *            the numPrest to set
	 */
	public void setNumPrest(String numPrest) {
		this.numPrest = numPrest;
	}

	/**
	 * Gets the ent cuenta prove.
	 *
	 * @return the entCuentaProve
	 */
	public String getEntCuentaProve() {
		return entCuentaProve;
	}

	/**
	 * Sets the ent cuenta prove.
	 *
	 * @param entCuentaProve
	 *            the entCuentaProve to set
	 */
	public void setEntCuentaProve(String entCuentaProve) {
		this.entCuentaProve = entCuentaProve;
	}

	/**
	 * Gets the suc cuenta prove.
	 *
	 * @return the sucCuentaProve
	 */
	public String getSucCuentaProve() {
		return sucCuentaProve;
	}

	/**
	 * Sets the suc cuenta prove.
	 *
	 * @param sucCuentaProve
	 *            the sucCuentaProve to set
	 */
	public void setSucCuentaProve(String sucCuentaProve) {
		this.sucCuentaProve = sucCuentaProve;
	}

	/**
	 * Gets the nro cuenta prove.
	 *
	 * @return the nroCuentaProve
	 */
	public String getNroCuentaProve() {
		return nroCuentaProve;
	}

	/**
	 * Sets the nro cuenta prove.
	 *
	 * @param nroCuentaProve
	 *            the nroCuentaProve to set
	 */
	public void setNroCuentaProve(String nroCuentaProve) {
		this.nroCuentaProve = nroCuentaProve;
	}

	/**
	 * Gets the divisa cta prove.
	 *
	 * @return the divisaCtaProve
	 */
	public String getDivisaCtaProve() {
		return divisaCtaProve;
	}

	/**
	 * Sets the divisa cta prove.
	 *
	 * @param divisaCtaProve
	 *            the divisaCtaProve to set
	 */
	public void setDivisaCtaProve(String divisaCtaProve) {
		this.divisaCtaProve = divisaCtaProve;
	}

	/**
	 * Gets the importe abono.
	 *
	 * @return the importeAbono
	 */
	public String getImporteAbono() {
		return importeAbono;
	}

	/**
	 * Sets the importe abono.
	 *
	 * @param importeAbono
	 *            the importeAbono to set
	 */
	public void setImporteAbono(String importeAbono) {
		this.importeAbono = importeAbono;
	}

	/**
	 * Gets the ind linea UVA.
	 *
	 * @return the lineaUVA
	 */
	public String getIndLineaUVA() {
		return indLineaUVA;
	}

	/**
	 * Sets the ind linea UVA.
	 *
	 * @param indLineaUVA
	 *            the new ind linea UVA
	 */
	public void setIndLineaUVA(String indLineaUVA) {
		this.indLineaUVA = indLineaUVA;
	}

	/**
	 * Adaptar fecha servicio.
	 *
	 * @param fechaSinAdaptar
	 *            the fecha sin adaptar
	 * @return the string
	 */
	public String adaptarFechaServicio(String fechaSinAdaptar) {

		String dia = fechaSinAdaptar.substring(0, 2);
		String mes = fechaSinAdaptar.substring(2, 4);
		String anio = fechaSinAdaptar.substring(4, fechaSinAdaptar.length());
		return anio + mes + dia;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(NIO);
		hcb.append(cantidadCuotas);
		hcb.append(clausulaRevUG);
		hcb.append(codCondici);
		hcb.append(codDivisaO);
		hcb.append(codProductoUG);
		hcb.append(codSubpUG);
		hcb.append(destFondosComerO);
		hcb.append(destFondosUG);
		hcb.append(divisaCtaProve);
		hcb.append(entCuentaProve);
		hcb.append(fase);
		hcb.append(fechaPrimerCuota);
		hcb.append(importeAbono);
		hcb.append(importePrestamo);
		hcb.append(indLineaUVA);
		hcb.append(nroCuentaProve);
		hcb.append(numPaquete);
		hcb.append(numPrest);
		hcb.append(numeroCuenta);
		hcb.append(sucCuentaProve);
		hcb.append(sucPaquete);
		hcb.append(sucPrest);
		hcb.append(sucursalCuenta);
		hcb.append(tipoCuenta);
		hcb.append(tipoTasa);
		hcb.append(tpoPolizaDs);
		hcb.append(tpoRiesgoDs);
		hcb.append(valorTasa);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrestamoInEntity other = (PrestamoInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(NIO, other.getNIO());
		eb.append(cantidadCuotas, other.getCantidadCuotas());
		eb.append(clausulaRevUG, other.getClausulaRevUG());
		eb.append(codCondici, other.getCodCondici());
		eb.append(codDivisaO, other.getCodDivisaO());
		eb.append(codProductoUG, other.getCodProductoUG());
		eb.append(codSubpUG, other.getCodSubpUG());
		eb.append(destFondosComerO, other.getDestFondosComerO());
		eb.append(destFondosUG, other.getDestFondosUG());
		eb.append(divisaCtaProve, other.getDivisaCtaProve());
		eb.append(entCuentaProve, other.getEntCuentaProve());
		eb.append(fase, other.getFase());
		eb.append(fechaPrimerCuota, other.getFechaPrimerCuota());
		eb.append(importeAbono, other.getImporteAbono());
		eb.append(importePrestamo, other.getImportePrestamo());
		eb.append(indLineaUVA,other.getIndLineaUVA());
		eb.append(nroCuentaProve, other.getNroCuentaProve());
		eb.append(numPaquete, other.getNumPaquete());
		eb.append(numPrest, other.getNumPrest());
		eb.append(numeroCuenta, other.getNumeroCuenta());
		eb.append(sucCuentaProve, other.getSucCuentaProve());
		eb.append(sucPaquete, other.getSucPaquete());
		eb.append(sucPrest, other.getSucPrest());
		eb.append(sucursalCuenta, other.getSucursalCuenta());
		eb.append(tipoCuenta, other.getTipoCuenta());
		eb.append(tipoTasa, other.getTipoTasa());
		eb.append(tpoPolizaDs, other.getTpoPolizaDs());
		eb.append(tpoRiesgoDs, other.getTpoRiesgoDs());
		eb.append(valorTasa, other.getValorTasa());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("fase", fase).append("tipoCuenta", tipoCuenta)
				.append("sucursalCuenta", sucursalCuenta).append("numeroCuenta", numeroCuenta)
				.append("importePrestamo", importePrestamo).append("cantidadCuotas", cantidadCuotas)
				.append("fechaPrimerCuota", fechaPrimerCuota).append("tipoTasa", tipoTasa)
				.append("codProductoUG", codProductoUG).append("codSubpUG", codSubpUG)
				.append("destFondosUG", destFondosUG).append("codDivisaO", codDivisaO).append("valorTasa", valorTasa)
				.append("clausulaRevUG", clausulaRevUG).append("sucPaquete", sucPaquete)
				.append("numPaquete", numPaquete).append("tpoPolizaDs", tpoPolizaDs).append("tpoRiesgoDs", tpoRiesgoDs)
				.append("codCondici", codCondici).append("NIO", NIO).append("destFondosComerO", destFondosComerO)
				.append("sucPrest", sucPrest).append("numPrest", numPrest).append("entCuentaProve", entCuentaProve)
				.append("sucCuentaProve", sucCuentaProve).append("nroCuentaProve", nroCuentaProve)
				.append("divisaCtaProve", divisaCtaProve).append("importeAbono", importeAbono)
				.append("lineaUVA",indLineaUVA).toString();
	}

}