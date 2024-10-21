/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaSeleccionView;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.Factura;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudaPagoComprasEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudaPagoComprasView;

/**
 * Clase de representacion de Medio de Pago que se utilizara como respuesta.
 */

@JsonSerialize(include = Inclusion.NON_NULL)
public class MedioPagoSelectionView implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 8737343508560779893L;
    
    /** Variable cantidad facturas. */
    private Integer cantidadFacturas;

    /** Variable cantidad cuentas. */
    private Integer cantidadCuentas;

    /** Variable lista facturas. */
    private List<Factura> listaFacturas;

    /** Variable lista cuentas. */
    private List<CuentaSeleccionView> listaCuentas;

    /** Variable fecha pago hoy. */
    private Boolean fechaPagoHoy = Boolean.TRUE;

    /** Variable cuenta seleccionada. */
    private Integer cuentaSeleccionada;

    /** Variable factura seleccionada. */
    private Integer facturaSeleccionada;

    /** Variable fecha pago. */
    private String fechaPago;

    /** Variable texto seleccion multiples facturas. */
    private String textoSeleccionMultiplesFacturas;
    
    /** The aviso cantidad maxima facturas. */
    private String avisoCantidadMaximaFacturas;

    /** The moneda. */
    private String moneda;

    /**
     * Getter para cantidad facturas.
     *
     * @return la cantidad facturas
     */
    public Integer getCantidadFacturas() {
        return cantidadFacturas;
    }

    /**
     * Setter para cantidad facturas.
     *
     * @param cantidadFacturas
     *            la nueva cantidad facturas
     */
    public void setCantidadFacturas(Integer cantidadFacturas) {
        this.cantidadFacturas = cantidadFacturas;
    }

    /**
     * Getter para cantidad cuentas.
     *
     * @return la cantidad cuentas
     */
    public Integer getCantidadCuentas() {
        return cantidadCuentas;
    }

    /**
     * Setter para cantidad cuentas.
     *
     * @param cantidadCuentas
     *            la nueva cantidad cuentas
     */
    public void setCantidadCuentas(Integer cantidadCuentas) {
        this.cantidadCuentas = cantidadCuentas;
    }

    /**
     * Getter para lista facturas.
     *
     * @return la lista facturas
     */
    public List<Factura> getListaFacturas() {
        return listaFacturas;
    }

    /**
     * Setter para lista facturas.
     *
     * @param facturas
     *            la nueva lista facturas
     */
    public void setListaFacturas(List<Factura> facturas) {
        this.listaFacturas = facturas;
    }

    /**
     * Setter para lista facturas.
     *
     * @param factura
     *            la nueva lista facturas
     */
    public void setListaFacturas(Factura factura) {
        if (CollectionUtils.isEmpty(this.listaFacturas)) {
            this.listaFacturas = new ArrayList<Factura>();
        }

        this.listaFacturas.add(factura);
    }

    /**
     * Gets the lista cuentas.
     *
     * @return the lista cuentas
     */
    public List<CuentaSeleccionView> getListaCuentas() {
        return listaCuentas;
    }

    /**
     * Sets the lista cuentas.
     *
     * @param listaCuentas
     *            the new lista cuentas
     */
    public void setListaCuentas(List<CuentaSeleccionView> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    /**
     * Getter para fecha pago hoy.
     *
     * @return la fecha pago hoy
     */
    public Boolean getFechaPagoHoy() {
        return fechaPagoHoy;
    }

    /**
     * Setter para fecha pago hoy.
     *
     * @param fechaPagoHoy
     *            la nueva fecha pago hoy
     */
    public void setFechaPagoHoy(Boolean fechaPagoHoy) {
        this.fechaPagoHoy = fechaPagoHoy;
    }

    /**
     * Getter para fecha pago.
     *
     * @return la fecha pago
     */
    public String getFechaPago() {
        return fechaPago;
    }

    /**
     * Setter para fecha pago. Si fechaPagoHoy es true, fecha de pago se carga con
     * la fecha actual.
     */
    public void setFechaPago() {
        if (Boolean.TRUE.equals(fechaPagoHoy)) {
            Date fecha = new Date();
            this.fechaPago = ISBANStringUtils.formatearFecha(fecha);
        }
    }

    /**
     * Setter para fecha pago. Si fechaPagoHoy es false, setear fecha de pago
     *
     * @param fechaPago
     *            la nueva fecha pago
     */
    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Getter para cuenta seleccionada.
     *
     * @return la cuenta seleccionada
     */
    public Integer getCuentaSeleccionada() {
        return cuentaSeleccionada;
    }

    /**
     * Setter para cuenta seleccionada.
     *
     * @param cuentaSeleccionada
     *            la nueva cuenta seleccionada
     */
    public void setCuentaSeleccionada(Integer cuentaSeleccionada) {
        this.cuentaSeleccionada = cuentaSeleccionada;
    }

    /**
     * Getter para factura seleccionada.
     *
     * @return la factura seleccionada
     */
    public Integer getFacturaSeleccionada() {
        return facturaSeleccionada;
    }

    /**
     * Setter para factura seleccionada.
     *
     * @param facturaSeleccionada
     *            la nueva factura seleccionada
     */
    public void setFacturaSeleccionada(Integer facturaSeleccionada) {
        this.facturaSeleccionada = facturaSeleccionada;
    }

    /**
     * Getter para texto seleccion multiples facturas.
     *
     * @return el texto para la pantalla de seleccion de facturas multiples
     */
    public String getTextoSeleccionMultiplesFacturas() {
        return textoSeleccionMultiplesFacturas;
    }

    /**
     * Setter para texto seleccion multiples facturas.
     *
     * @param textoSeleccionMultiplesFacturas
     *            el nuevo texto para la pantalla de seleccion de facturas multiples
     */
    public void setTextoSeleccionMultiplesFacturas(String textoSeleccionMultiplesFacturas) {
        this.textoSeleccionMultiplesFacturas = textoSeleccionMultiplesFacturas;
    }

	/**
	 * @return the avisoCantidadMaximaFacturas
	 */
	public String getAvisoCantidadMaximaFacturas() {
		return avisoCantidadMaximaFacturas;
	}

	/**
	 * @param avisoCantidadMaximaFacturas
	 *            the avisoCantidadMaximaFacturas to set
	 */
	public void setAvisoCantidadMaximaFacturas(String avisoCantidadMaximaFacturas) {
		this.avisoCantidadMaximaFacturas = avisoCantidadMaximaFacturas;
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
     * Sets the lista cuentas A view.
     *
     * @param listaCuentas
     *            the new lista cuentas A view
     */
    public void setListaCuentasAView(List<Cuenta> listaCuentas) {
        this.listaCuentas = new ArrayList<CuentaSeleccionView>();
        for (Cuenta cuenta : listaCuentas) {
            CuentaSeleccionView cuentaSeleccionView = new CuentaSeleccionView(cuenta);
            this.listaCuentas.add(cuentaSeleccionView);
        }
        this.cantidadCuentas = this.listaCuentas.size();
    }

    /**
     * Sets the lista cuentas A view.
     *
     * @param listaCuentas
     *            the lista cuentas
     * @param listaTarjetas
     *            the lista tarjetas
     */
    public void setListaCuentasAView(List<Cuenta> listaCuentas, List<Cuenta> listaTarjetas) {
        this.listaCuentas = new ArrayList<CuentaSeleccionView>();
        if (CollectionUtils.isNotEmpty(listaCuentas)) {
            for (Cuenta cuenta : listaCuentas) {
                CuentaSeleccionView cuentaSeleccionView = new CuentaSeleccionView(cuenta);
                this.listaCuentas.add(cuentaSeleccionView);
            }
        }
        if (CollectionUtils.isNotEmpty(listaTarjetas)) {
            for (Cuenta tarjeta : listaTarjetas) {
                CuentaSeleccionView cuentaSeleccionView = new CuentaSeleccionView(tarjeta);
                this.listaCuentas.add(cuentaSeleccionView);
            }
        }
        this.cantidadCuentas = this.listaCuentas.size();
    }

    /**
     * Sets the lista facturas A view.
     *
     * @param deudas
     *            the deudas
     * @param divisa
     *            the divisa
     */
    public void setListaFacturasAView(List<DeudaPagoComprasEntity> deudas, DivisaEnum divisa) {
        this.listaFacturas = new ArrayList<Factura>();
        if (CollectionUtils.isNotEmpty(deudas)) {
            for (DeudaPagoComprasEntity deuda : deudas) {
                DeudaPagoComprasView factura = new DeudaPagoComprasView();
                factura.entityAView(deuda, divisa);
                this.listaFacturas.add(factura);
            }
        }
        this.cantidadFacturas = this.listaFacturas.size();
    }
}