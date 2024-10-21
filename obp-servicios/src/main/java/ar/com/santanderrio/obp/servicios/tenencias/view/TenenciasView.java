/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto utilizado para el Input del TenenciasSEI.
 * 
 * @author
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TenenciasView {

	/** The saldo total pesos. */
	private String saldoTotalPesos;

	/** The saldo total dolares. */
	private String saldoTotalDolares;

	/** The coti dolar. */
	private String cotiDolar;

	/** The is citi. */
	private String tipoCliente;
	
	/** The is citi. */
	private Boolean isCiti = false;
	
	/** The is citi. */
	private Boolean isBancaPriv = false;

	/** The solapado. */
	private Boolean isSolapado = false;
	
	/** The bsr. */
	private Boolean isBsr = false;

	/** The cuentas. */
	private List<CuentaView> cuentas;

	/** The cuentas ME. */
	private List<CuentaView> cuentasME;

	/** The inversiones. */
	private InversionesView inversiones;

	/** The impuestos. */
	private List<ImpuestoView> impuestos;

	/** The prestamos. */
	private List<PrestamoView> prestamos;

	/** The tarjetas. */
	private List<TarjetaView> tarjetas;

	/** The rendimiento fondos. */
	private List<RendimientoFondoView> rendimientoFondos;

	/** The impuesto moneda extranjera. */
	private List<ImpuestoMonedaExtranjeraResumenView> impuestoMonedaExtranjera;
	
	/** The tenencias legal .*/
	private TenenciasLegalView tenenciasLegal;
	
	/** The lista anios. */
	private List<String> listaAnios;

		
	/**
	 * Gets the saldo total pesos.
	 *
	 * @return the saldoTotalPesos
	 */
	public String getSaldoTotalPesos() {
		return saldoTotalPesos;
	}

	/**
	 * Sets the saldo total pesos.
	 *
	 * @param saldoTotalPesos
	 *            the saldoTotalPesos to set
	 */
	public void setSaldoTotalPesos(String saldoTotalPesos) {
		this.saldoTotalPesos = saldoTotalPesos;
	}

	/**
	 * Gets the saldo total dolares.
	 *
	 * @return the saldoTotalDolares
	 */
	public String getSaldoTotalDolares() {
		return saldoTotalDolares;
	}

	/**
	 * Sets the saldo total dolares.
	 *
	 * @param saldoTotalDolares
	 *            the saldoTotalDolares to set
	 */
	public void setSaldoTotalDolares(String saldoTotalDolares) {
		this.saldoTotalDolares = saldoTotalDolares;
	}

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentaView> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the cuentas to set
	 */
	public void setCuentas(List<CuentaView> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the inversiones.
	 *
	 * @return the inversiones
	 */
	public InversionesView getInversiones() {
		return inversiones;
	}

	/**
	 * Sets the inversiones.
	 *
	 * @param inversiones
	 *            the inversiones to set
	 */
	public void setInversiones(InversionesView inversiones) {
		this.inversiones = inversiones;
	}

	/**
	 * Gets the impuestos.
	 *
	 * @return the impuestos
	 */
	public List<ImpuestoView> getImpuestos() {
		return impuestos;
	}

	/**
	 * Sets the impuestos.
	 *
	 * @param impuestos
	 *            the impuestos to set
	 */
	public void setImpuestos(List<ImpuestoView> impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * Gets the prestamos.
	 *
	 * @return the prestamos
	 */
	public List<PrestamoView> getPrestamos() {
		return prestamos;
	}

	/**
	 * Sets the prestamos.
	 *
	 * @param prestamos
	 *            the prestamos to set
	 */
	public void setPrestamos(List<PrestamoView> prestamos) {
		this.prestamos = prestamos;
	}

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<TarjetaView> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the tarjetas to set
	 */
	public void setTarjetas(List<TarjetaView> tarjetas) {
		this.tarjetas = tarjetas;
	}

	/**
	 * Gets the rendimiento fondos.
	 *
	 * @return the rendimientoFondos
	 */
	public List<RendimientoFondoView> getRendimientoFondos() {
		return rendimientoFondos;
	}

	/**
	 * Sets the rendimiento fondos.
	 *
	 * @param rendimientoFondos
	 *            the rendimientoFondos to set
	 */
	public void setRendimientoFondos(List<RendimientoFondoView> rendimientoFondos) {
		this.rendimientoFondos = rendimientoFondos;
	}

	/**
	 * Gets the impuesto moneda extranjera.
	 *
	 * @return the impuestoMonedaExtranjera
	 */
	public List<ImpuestoMonedaExtranjeraResumenView> getImpuestoMonedaExtranjera() {
		return impuestoMonedaExtranjera;
	}

	/**
	 * Sets the impuesto moneda extranjera.
	 *
	 * @param impuestoMonedaExtranjera
	 *            the impuestoMonedaExtranjera to set
	 */
	public void setImpuestoMonedaExtranjera(List<ImpuestoMonedaExtranjeraResumenView> impuestoMonedaExtranjera) {
		this.impuestoMonedaExtranjera = impuestoMonedaExtranjera;
	}

	/**
	 * Gets the coti dolar.
	 *
	 * @return the cotiDolar
	 */
	public String getCotiDolar() {
		return cotiDolar;
	}

	/**
	 * Sets the coti dolar.
	 *
	 * @param cotiDolar
	 *            the cotiDolar to set
	 */
	public void setCotiDolar(String cotiDolar) {
		this.cotiDolar = cotiDolar;
	}

	/**
	 * Gets the cuentas ME.
	 *
	 * @return the cuentasME
	 */
	public List<CuentaView> getCuentasME() {
		return cuentasME;
	}

	/**
	 * Sets the cuentas ME.
	 *
	 * @param cuentasME
	 *            the cuentasME to set
	 */
	public void setCuentasME(List<CuentaView> cuentasME) {
		this.cuentasME = cuentasME;
	}
	
	

	/**
	 * Gets the checks if is citi.
	 *
	 * @return the checks if is citi
	 */
	public Boolean getIsCiti() {
		return isCiti;
	}

	/**
	 * Sets the checks if is citi.
	 *
	 * @param isCiti the new checks if is citi
	 */
	public void setIsCiti(Boolean isCiti) {
		this.isCiti = isCiti;
	}
	
	

	/**
	 * Gets the checks if is banca priv.
	 *
	 * @return the checks if is banca priv
	 */
	public Boolean getIsBancaPriv() {
		return isBancaPriv;
	}

	/**
	 * Sets the checks if is banca priv.
	 *
	 * @param isBancaPriv the new checks if is banca priv
	 */
	public void setIsBancaPriv(Boolean isBancaPriv) {
		this.isBancaPriv = isBancaPriv;
	}

	/**
	 * Gets the checks if is solapado.
	 *
	 * @return the checks if is solapado
	 */
	public Boolean getIsSolapado() {
		return isSolapado;
	}

	/**
	 * Sets the checks if is solapado.
	 *
	 * @param isSolapado the new checks if is solapado
	 */
	public void setIsSolapado(Boolean isSolapado) {
		this.isSolapado = isSolapado;
	}

	/**
	 * Gets the checks if is bsr.
	 *
	 * @return the checks if is bsr
	 */
	public Boolean getIsBsr() {
		return isBsr;
	}

	/**
	 * Sets the checks if is bsr.
	 *
	 * @param isBsr the new checks if is bsr
	 */
	public void setIsBsr(Boolean isBsr) {
		this.isBsr = isBsr;
	}
	
	

	/**
	 * Gets the tipo cliente.
	 *
	 * @return the tipo cliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * Sets the tipo cliente.
	 *
	 * @param tipoCliente the new tipo cliente
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	
	
	/**
	 * Gets the tenencias legal.
	 *
	 * @return the tenenciasLegal
	 */
	public TenenciasLegalView getTenenciasLegal() {
		return tenenciasLegal;
	}

	/**
	 * Sets the tenencias legal.
	 *
	 * @param tenenciasLegal
	 *            the tenenciasLegal to set
	 */
	public void setTenenciasLegal(TenenciasLegalView tenenciasLegal) {
		this.tenenciasLegal = tenenciasLegal;
	}

	/**
	 * Gets the lista anios.
	 *
	 * @return the lista anios
	 */
	public List<String> getListaAnios() {
		return listaAnios;
	}

	/**
	 * Sets the lista anios.
	 *
	 * @param listaAnios the new lista anios
	 */
	public void setListaAnios(List<String> listaAnios) {
		this.listaAnios = listaAnios;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				// agregar los append con los atributos que correspondan
				// .append(extra)
				.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		TenenciasView other = (TenenciasView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb

				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				// agregar los appends con los atributos que corresponda, ej:
				// .append("Extra", extra)
				.toString();
	}

}
