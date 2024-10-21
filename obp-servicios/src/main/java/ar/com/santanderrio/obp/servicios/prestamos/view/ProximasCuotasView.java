/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.CuentaView;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.PrestamoCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CuotaPrestamoEntity;

/**
 * The Class ProximasCuotasView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include = Inclusion.NON_NULL)
public class ProximasCuotasView {

	/** The titulo. */
	private String titulo;

	/** The numero. */
	private String numero;

	/** The monto. */
	private String monto;

	/** The cuotas pagadas. */
	private String cuotasPagadas;

	/** The plazo. */
	private String plazo;

	/** The motivo. */
	private String motivo;

	/** The legales. */
	private List<String>legales = new ArrayList<String>();

	/** The cuotas. */
	private List<ProximaCuotaView> cuotas;

	/** The cuotas fechas. */
	private List<CuotasFechaView> cuotasFechas;

	/**
	 * Instantiates a new proximas cuotas view.
	 */
	public ProximasCuotasView() {
		super();
	}

	/**
	 * Instantiates a new proximas cuotas view.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param prestamo
	 *            the prestamo
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 */
	public ProximasCuotasView(Cuenta cuenta, Prestamo prestamo, ConsultaPrestamo consultaPrestamo) {
		this.titulo = TipoPrestamoEnum.fromIdString(cuenta.getClaseCuenta()).getDescripcion();
		this.numero = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()) + "-" + ISBANStringUtils
				.agregarBarraNumeroPrestamo(ISBANStringUtils.formateadorConCerosIzq(cuenta.getNroCuentaProducto(), 12));
		this.monto = consultaPrestamo.getMontoPrestamo();
		this.plazo = consultaPrestamo.getPlazo();
		this.motivo = consultaPrestamo.getMotivoPrestamo();
		this.cuotasPagadas = prestamo != null ? String.valueOf(Integer.parseInt(prestamo.getNumeroRecibo()) - 1) : "-";
	}

	/**
	 * Instantiates a new proximas cuotas view.
	 *
	 * @param cuenta
	 *            the cuenta
	 */
	public ProximasCuotasView(Cuenta cuenta, ConsultaPrestamo consultaPrestamo) {
		this.titulo = TipoPrestamoEnum.fromIdString(cuenta.getClaseCuenta()).getDescripcion();
		this.numero = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()) + "-" + ISBANStringUtils
				.agregarBarraNumeroPrestamo(ISBANStringUtils.formateadorConCerosIzq(cuenta.getNroCuentaProducto(), 12));
		this.monto = consultaPrestamo.getMontoPrestamo();
        this.plazo = consultaPrestamo.getPlazo();
        this.motivo = consultaPrestamo.getMotivoPrestamo();
        this.cuotasPagadas = "-";
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo
	 *            the new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
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
	 * Gets the cuotas pagadas.
	 *
	 * @return the cuotas pagadas
	 */
	public String getCuotasPagadas() {
		return cuotasPagadas;
	}

	/**
	 * Sets the cuotas pagadas.
	 *
	 * @param cuotasPagadas
	 *            the new cuotas pagadas
	 */
	public void setCuotasPagadas(String cuotasPagadas) {
		this.cuotasPagadas = cuotasPagadas;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the new plazo
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the motivo.
	 *
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * Sets the motivo.
	 *
	 * @param motivo
	 *            the new motivo
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
//	public ArrayList<String> getLegales() {
//		return legales;
//	}
	public List<String> getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the legales to set
	 */
//	public void setLegales(ArrayList<String> legales) {
//		this.legales = legales;
//	}

	public void setLegales(List<String> legales) {
		this.legales = legales;
	}

	/**
	 * Gets the cuotas.
	 *
	 * @return the cuotas
	 */
	public List<ProximaCuotaView> getCuotas() {
		return cuotas;
	}

	/**
	 * Sets the cuotas.
	 *
	 * @param proximasCuotas
	 *            the new cuotas
	 */
	public void setCuotas(List<ProximaCuotaView> proximasCuotas) {
		this.cuotas = proximasCuotas;
	}

	/**
	 * Gets the cuotas fechas.
	 *
	 * @return the cuotas fechas
	 */
	public List<CuotasFechaView> getCuotasFechas() {
		return cuotasFechas;
	}

	/**
	 * Sets the cuotas fechas.
	 *
	 * @param cuotasFechas
	 *            the new cuotas fechas
	 */
	public void setCuotasFechas(List<CuotasFechaView> cuotasFechas) {
		this.cuotasFechas = cuotasFechas;
	}

	/**
	 * Sets the proximas cuotas.
	 *
	 * @param proximasCuotasEntity
	 *            the proximas cuotas entity
	 * @param tipoPrestamo
	 *            the tipo prestamo
	 * @param prestamo
	 *            the prestamo
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @param cuentaDebitoView
	 *            the cuenta debito view
	 */
	public void setProximasCuotas(List<CuotaPrestamoEntity> proximasCuotasEntity, TipoPrestamoEnum tipoPrestamo,
			Prestamo prestamo, ConsultaPrestamo consultaPrestamo, CuentaView cuentaDebitoView) {
		String nombreTipoPrestamo = TipoPrestamoEnum.PERSONAL == tipoPrestamo ? "PERSONAL"
				: TipoPrestamoEnum.HIPOTECARIOS == tipoPrestamo ? "HIPOTECARIO" : "PRENDARIO";
		this.cuotas = new ArrayList<ProximaCuotaView>();
		for (CuotaPrestamoEntity cuotaPrestamoEntity : proximasCuotasEntity) {
			ProximaCuotaView proximaCuota = new ProximaCuotaView(cuotaPrestamoEntity, nombreTipoPrestamo);
			proximaCuota.armarDetalleCuota(cuotaPrestamoEntity, prestamo, consultaPrestamo, this, cuentaDebitoView);
			this.cuotas.add(proximaCuota);
		}
	}

	/**
	 * Sets the cuotas pagas.
	 *
	 * @param cuotasPagasEntity
	 *            the cuotas pagas entity
	 * @param tipoPrestamo
	 *            the tipo prestamo
	 * @param interviniente
	 *            the interviniente
	 * @param cuentaDebitoView
	 *            the cuenta debito view
	 */
	public void setCuotasPagas(ConsultaCuotaPagaOutEntity cuotasPagasEntity, TipoPrestamoEnum tipoPrestamo, 
			Interviniente interviniente, CuentaView cuentaDebitoView) {
		String nombreTipoPrestamo = TipoPrestamoEnum.PERSONAL == tipoPrestamo ? "PERSONAL"
				: TipoPrestamoEnum.HIPOTECARIOS == tipoPrestamo ? "HIPOTECARIO" : "PRENDARIO";
		this.cuotasFechas = new ArrayList<CuotasFechaView>();
		if (!CollectionUtils.isEmpty(cuotasPagasEntity.getCuotasPagas())) {
			Collections.reverse(cuotasPagasEntity.getCuotasPagas());
			for (PrestamoCuotaPagaOutEntity cuotaPrestamoEntity : cuotasPagasEntity.getCuotasPagas()) {
				ProximaCuotaView proximaCuota = new ProximaCuotaView(cuotaPrestamoEntity, nombreTipoPrestamo);
				proximaCuota.armarDetalleCuotaPaga(cuotaPrestamoEntity, this, interviniente,
						cuentaDebitoView);
				if (proximaCuota.getVencimiento() != null) {
					String anio = proximaCuota.getVencimiento().substring(6, 10);
					obtenerViewDeFecha(anio).addCuota(proximaCuota);
				}
			}
		}
	}

	/**
	 * Obtener view de fecha.
	 *
	 * @param anio
	 *            the anio
	 * @return the cuotas fecha view
	 */
	private CuotasFechaView obtenerViewDeFecha(String anio) {
		for (CuotasFechaView cuotasFecha : this.cuotasFechas) {
			if (anio.equals(cuotasFecha.getFecha())) {
				return cuotasFecha;
			}
		}
		CuotasFechaView cuotasFecha = new CuotasFechaView(anio);
		this.cuotasFechas.add(cuotasFecha);
		return cuotasFecha;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cuotas);
		hcb.append(cuotasFechas);
		hcb.append(cuotasPagadas);
		hcb.append(monto);
		hcb.append(motivo);
		hcb.append(numero);
		hcb.append(plazo);
		hcb.append(titulo);
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
		ProximasCuotasView other = (ProximasCuotasView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cuotas, other.getCuotas());
		eb.append(cuotasFechas, other.getCuotasFechas());
		eb.append(cuotasPagadas, other.getCuotasPagadas());
		eb.append(monto, other.getMonto());
		eb.append(motivo, other.getMotivo());
		eb.append(numero, other.getNumero());
		eb.append(plazo, other.getPlazo());
		eb.append(titulo, other.getTitulo());
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
		return new ToStringBuilder(this).append("titulo", titulo).append("numero", numero).append("monto", monto)
				.append("cuotasPagadas", cuotasPagadas).append("plazo", plazo).append("motivo", motivo)
				.append("legales", legales).append("cuotas", cuotas).append("cuotasFechas", cuotasFechas).toString();
	}

}