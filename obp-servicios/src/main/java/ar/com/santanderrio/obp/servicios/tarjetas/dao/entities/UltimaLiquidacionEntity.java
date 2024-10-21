/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.enums.Currency;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class UltimaLiquidacionEntity. Clase que representa el tag
 * /tarjetas/tarjeta/liquidacion/
 *
 * @author florencia.n.martinez
 */
@XmlRootElement(name = "liquidacion")
@XmlAccessorType(XmlAccessType.FIELD)
public class UltimaLiquidacionEntity {

	/** The String anterior. */
	@XmlElement(name = "anterior")
	private String anterior;

	/** The String numero. */
	@XmlElement(name = "numero")
	private String numero;

	/** The String proxima. */
	@XmlElement(name = "proxima")
	private String proxima;

	/** The String resumen. */
	@XmlElement(name = "resumen")
	private String resumen;

	/** The Total total. */
	@XmlElement(name = "total")
	private Total total;

	/** The DatosEntity datos. */
	@XmlElement(name = "datos")
	private DatosEntity datos;

	/** The DatosExtra datosExtra. */
	@XmlElement(name = "datosExtra")
	private DatosExtra datosExtra;

	/** The FechasUltimaLiquidacion fechas. */
	@XmlElement(name = "fechas")
	private FechasUltimaLiquidacion fechas;

	/** The PagosUltimaLiquidacion pagos. */
	@XmlElement(name = "pagos")
	private PagosUltimaLiquidacion pagos;

	/** The LimitesUltimaLiquidacion limites. */
	@XmlElement(name = "limites")
	private LimitesUltimaLiquidacionEntity limites;

	/** The SaldosTasasUltimaLiquidacion saldos. */
	@XmlElement(name = "saldos")
	private SaldosUltimaLiquidacion saldos;

	/** The TasasUltimaLiquidacion tasas. */
	@XmlElement(name = "tasas")
	private TasasUltimaLiquidacion tasas;

	/** The DetalleLiquidacion detalleLiquidacion. */
	@XmlElement(name = "detalleLiquidacion")
	private DetalleLiquidacion detalleLiquidacion;

	/** The Currency currency. */
	@XmlElement(name = "currency")
	private Currency currency;

	/**
	 * Gets the anterior.
	 *
	 * @return the anterior
	 */
	public String getAnterior() {
		return anterior;
	}

	/**
	 * Sets the anterior.
	 *
	 * @param anterior
	 *            the anterior to set
	 */
	public void setAnterior(String anterior) {
		this.anterior = anterior;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the proxima.
	 *
	 * @return the proxima
	 */
	public String getProxima() {
		return proxima;
	}

	/**
	 * Sets the proxima.
	 *
	 * @param proxima
	 *            the proxima to set
	 */
	public void setProxima(String proxima) {
		this.proxima = proxima;
	}

	/**
	 * Gets the resumen.
	 *
	 * @return the resumen
	 */
	public String getResumen() {
		return resumen;
	}

	/**
	 * Sets the resumen.
	 *
	 * @param resumen
	 *            the resumen to set
	 */
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public Total getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total
	 *            the total to set
	 */
	public void setTotal(Total total) {
		this.total = total;
	}

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosEntity getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosEntity datos) {
		this.datos = datos;
	}

	/**
	 * Gets the datos extra.
	 *
	 * @return the datosExtra
	 */
	public DatosExtra getDatosExtra() {
		return datosExtra;
	}

	/**
	 * Sets the datos extra.
	 *
	 * @param datosExtra
	 *            the datosExtra to set
	 */
	public void setDatosExtra(DatosExtra datosExtra) {
		this.datosExtra = datosExtra;
	}

	/**
	 * Gets the fechas.
	 *
	 * @return the fechas
	 */
	public FechasUltimaLiquidacion getFechas() {
		return fechas;
	}

	/**
	 * Sets the fechas.
	 *
	 * @param fechas
	 *            the fechas to set
	 */
	public void setFechas(FechasUltimaLiquidacion fechas) {
		this.fechas = fechas;
	}

	/**
	 * Gets the pagos.
	 *
	 * @return the pagos
	 */
	public PagosUltimaLiquidacion getPagos() {
		return pagos;
	}

	/**
	 * Sets the pagos.
	 *
	 * @param pagos
	 *            the pagos to set
	 */
	public void setPagos(PagosUltimaLiquidacion pagos) {
		this.pagos = pagos;
	}

	/**
	 * Gets the limites.
	 *
	 * @return the limites
	 */
	public LimitesUltimaLiquidacionEntity getLimites() {
		return limites;
	}

	/**
	 * Sets the limites.
	 *
	 * @param limites
	 *            the limites to set
	 */
	public void setLimites(LimitesUltimaLiquidacionEntity limites) {
		this.limites = limites;
	}

	/**
	 * Gets the saldos.
	 *
	 * @return the saldos
	 */
	public SaldosUltimaLiquidacion getSaldos() {
		return saldos;
	}

	/**
	 * Sets the saldos.
	 *
	 * @param saldos
	 *            the saldos to set
	 */
	public void setSaldos(SaldosUltimaLiquidacion saldos) {
		this.saldos = saldos;
	}

	/**
	 * Gets the tasas.
	 *
	 * @return the tasas
	 */
	public TasasUltimaLiquidacion getTasas() {
		return tasas;
	}

	/**
	 * Sets the tasas.
	 *
	 * @param tasas
	 *            the new tasas
	 */
	public void setTasas(TasasUltimaLiquidacion tasas) {
		this.tasas = tasas;
	}

	/**
	 * Gets the detalle liquidacion.
	 *
	 * @return the detalleLiquidacion
	 */
	public DetalleLiquidacion getDetalleLiquidacion() {
		return detalleLiquidacion;
	}

	/**
	 * Sets the detalle liquidacion.
	 *
	 * @param detalleLiquidacion
	 *            the detalleLiquidacion to set
	 */
	public void setDetalleLiquidacion(DetalleLiquidacion detalleLiquidacion) {
		this.detalleLiquidacion = detalleLiquidacion;
	}

	/**
	 * Gets the currency liquidacion.
	 *
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Sets the currency liquidacion.
	 *
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(anterior);
		hcb.append(datos);
		hcb.append(datosExtra);
		hcb.append(detalleLiquidacion);
		hcb.append(fechas);
		hcb.append(limites);
		hcb.append(numero);
		hcb.append(pagos);
		hcb.append(proxima);
		hcb.append(resumen);
		hcb.append(saldos);
		hcb.append(tasas);
		hcb.append(total);
		hcb.append(currency);
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UltimaLiquidacionEntity other = (UltimaLiquidacionEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(anterior, other.getAnterior());
		eb.append(datos, other.getDatos());
		eb.append(datosExtra, other.getDatosExtra());
		eb.append(detalleLiquidacion, other.getDetalleLiquidacion());
		eb.append(fechas, other.getFechas());
		eb.append(limites, other.getLimites());
		eb.append(numero, other.getNumero());
		eb.append(pagos, other.getPagos());
		eb.append(proxima, other.getProxima());
		eb.append(resumen, other.getResumen());
		eb.append(saldos, other.getSaldos());
		eb.append(tasas, other.getTasas());
		eb.append(total, other.getTotal());
		eb.append(currency, other.getCurrency());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "UltimaLiquidacionEntity [anterior=" + anterior + ", numero=" + numero + ", proxima=" + proxima
				+ ", resumen=" + resumen + ", total=" + total + ", datos=" + datos + ", datosExtra=" + datosExtra
				+ ", fechas=" + fechas + ", pagos=" + pagos + ", limites=" + limites + ", saldos=" + saldos + ", tasas="
				+ tasas + ", detalleLiquidacion=" + detalleLiquidacion + ",currency=" + currency + "]";
	}

}
