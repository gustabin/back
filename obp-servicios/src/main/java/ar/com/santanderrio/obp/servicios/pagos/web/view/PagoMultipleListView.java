/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.servicios.seguros.entities.EmisionOfertaIntegrada;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.base.web.view.View;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaSeleccionView;

// TODO: Auto-generated Javadoc
/**
 * The Class PagoMultipleView. DTO de respuesta para las solicitudes asociadas a
 * pago multiples
 *
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @see {@link PagoMultipleView}
 * @since Dec 29, 2016
 */
@XmlRootElement(name = "pagoMultipleListView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagoMultipleListView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5989915634272260628L;

	/** The todos OK. */
	private Boolean todosPagosOk;

	/** The error unico. */
	private Boolean errorUnico;

	/** The mensaje error unico. */
	private String mensajeErrorUnico;

	/** The reintentar error unico. */
	private Boolean reintentarErrorUnico;

	/** The legales. */
	private String legales;

	/** The fecha hora comprobante. */
	private String fechaHora;

	/** The List. */
	List<CuentaSeleccionView> cuentas = new ArrayList<CuentaSeleccionView>();

	/** The montos recarga. */
	List<MontoValorView> montosRecarga = new ArrayList<MontoValorView>();
	
	List<MontoValorView> montosRecargaNextel = new ArrayList<MontoValorView>();

	/** The pagos. */
	List<PagoMultipleView> pagos = new ArrayList<PagoMultipleView>();
	
	/** The montos recarga sube. */
	private List<MontoValorView> montosRecargaSube;
	
	
	/** The montos recarga movi. */
	private List<MontoValorView> montosRecargaMovi;

	/** The lista id pagos ok. */
	private int[] idPagosOk = null;

	private EmisionOfertaIntegrada emisionGastosProtegido;

	private String nroPolizaGastosProtegido;

	/**
	 * Instantiates a new pago multiple list view.
	 */
	public PagoMultipleListView() {
		super();
		generarListaMontosRecarga();
	}

	/**
	 * Instantiates a new pago multiple list view.
	 *
	 * @param id
	 *            the id
	 */
	public PagoMultipleListView(String id) {
		super(id);
		generarListaMontosRecarga();
	}

	/**
	 * Gets the pagos.
	 *
	 * @return the pagos
	 */
	public List<PagoMultipleView> getPagos() {
		return pagos;
	}

	/**
	 * Sets the pagos.
	 *
	 * @param pagos
	 *            the new pagos
	 */
	public void setPagos(List<PagoMultipleView> pagos) {
		this.pagos = pagos;
	}

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentaSeleccionView> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the new cuentas
	 */
	public void setCuentas(List<CuentaSeleccionView> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the montos recarga.
	 *
	 * @return the montos recarga
	 */
	public List<MontoValorView> getMontosRecarga() {
		return montosRecarga;
	}

	/**
	 * Sets the montos recarga.
	 *
	 * @param montosRecarga
	 *            the new montos recarga
	 */
	public void setMontosRecarga(List<MontoValorView> montosRecarga) {
		this.montosRecarga = montosRecarga;
	}
	
	

	public List<MontoValorView> getMontosRecargaNextel() {
		return montosRecargaNextel;
	}

	public void setMontosRecargaNextel(List<MontoValorView> montosRecargaNextel) {
		this.montosRecargaNextel = montosRecargaNextel;
	}

	/**
	 * Gets the error unico.
	 *
	 * @return the errorUnico
	 */
	public Boolean getErrorUnico() {
		return errorUnico;
	}

	/**
	 * Sets the error unico.
	 *
	 * @param errorUnico
	 *            the errorUnico to set
	 */
	public void setErrorUnico(Boolean errorUnico) {
		this.errorUnico = errorUnico;
	}

	/**
	 * Gets the mensaje error unico.
	 *
	 * @return the mensajeErrorUnico
	 */
	public String getMensajeErrorUnico() {
		return mensajeErrorUnico;
	}

	/**
	 * Sets the mensaje error unico.
	 *
	 * @param mensajeErrorUnico
	 *            the mensajeErrorUnico to set
	 */
	public void setMensajeErrorUnico(String mensajeErrorUnico) {
		this.mensajeErrorUnico = mensajeErrorUnico;
	}

	/**
	 * Gets the reintentar error unico.
	 *
	 * @return the reintentarErrorUnico
	 */
	public Boolean getReintentarErrorUnico() {
		return reintentarErrorUnico;
	}

	/**
	 * Sets the reintentar error unico.
	 *
	 * @param reintentarErrorUnico
	 *            the reintentarErrorUnico to set
	 */
	public void setReintentarErrorUnico(Boolean reintentarErrorUnico) {
		this.reintentarErrorUnico = reintentarErrorUnico;
	}

	/**
	 * Gets the id pagos ok.
	 *
	 * @return the id pagos ok
	 */
	public int[] getIdPagosOk() {
		return idPagosOk;
	}

	/**
	 * Sets the id pagos ok.
	 *
	 * @param idPagosOk
	 *            the new id pagos ok
	 */
	public void setIdPagosOk(int[] idPagosOk) {
		this.idPagosOk = idPagosOk;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the legales to set
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * Gets the todos pagos ok.
	 *
	 * @return the todos pagos ok
	 */
	public Boolean getTodosPagosOk() {
		return todosPagosOk;
	}

	/**
	 * Sets the todos pagos ok.
	 *
	 * @param todosPagosOk
	 *            the new todos pagos ok
	 */
	public void setTodosPagosOk(Boolean todosPagosOk) {
		this.todosPagosOk = todosPagosOk;
		if (todosPagosOk.equals(Boolean.TRUE)) {
			this.errorUnico = Boolean.FALSE;
			this.mensajeErrorUnico = null;
			this.reintentarErrorUnico = Boolean.FALSE;
		}
	}

	/**
	 * Generar lista montos recarga celular.
	 *
	 * @return the list
	 */
	private static List<MontoValorView> generarListaMontosRecarga() {
		List<MontoValorView> lista = new ArrayList<MontoValorView>();
		lista.add(new MontoValorView(BigDecimal.valueOf(20), "$ 20,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(30), "$ 30,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(40), "$ 40,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(50), "$ 50,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(60), "$ 60,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(70), "$ 70,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(80), "$ 80,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(90), "$ 90,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(100), "$ 100,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(150), "$ 150,00"));
		lista.add(new MontoValorView(BigDecimal.valueOf(200), "$ 200,00"));
		return lista;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fechaHora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the fechaHora to set
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

    /**
	 * Gets the montos recarga sube.
	 *
	 * @return the montos recarga sube
	 */
    public List<MontoValorView> getMontosRecargaSube() {
        return montosRecargaSube;
    }

    /**
	 * Sets the montos recarga sube.
	 *
	 * @param montosRecargaSube
	 *            the new montos recarga sube
	 */
    public void setMontosRecargaSube(List<MontoValorView> montosRecargaSube) {
        this.montosRecargaSube = montosRecargaSube;
    }

	/**
	 * Gets the montos recarga movi.
	 *
	 * @return the montos recarga movi
	 */
	public List<MontoValorView> getMontosRecargaMovi() {
		return montosRecargaMovi;
	}

	/**
	 * Sets the montos recarga movi.
	 *
	 * @param montosRecargaMovi the new montos recarga movi
	 */
	public void setMontosRecargaMovi(List<MontoValorView> montosRecargaMovi) {
		this.montosRecargaMovi = montosRecargaMovi;
	}

	public EmisionOfertaIntegrada getEmisionGastosProtegido() {
		return emisionGastosProtegido;
	}

	public void setEmisionGastosProtegido(EmisionOfertaIntegrada emisionGastosProtegido) {
		this.emisionGastosProtegido = emisionGastosProtegido;
	}

	public String getNroPolizaGastosProtegido() {
		return nroPolizaGastosProtegido;
	}

	public void setNroPolizaGastosProtegido(String nroPolizaGastosProtegido) {
		this.nroPolizaGastosProtegido = nroPolizaGastosProtegido;
	}
}
